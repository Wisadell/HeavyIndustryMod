package heavyindustry.world.blocks.distribution

import heavyindustry.util.HIUtils
import heavyindustry.world.meta.HIStat
import arc.Core
import arc.graphics.Blending
import arc.graphics.Color
import arc.graphics.g2d.Draw
import arc.graphics.g2d.TextureRegion
import arc.math.Mathf
import arc.math.geom.Geometry
import arc.math.geom.Point2
import arc.util.Eachable
import arc.util.Time
import arc.util.Tmp
import mindustry.content.Blocks
import mindustry.entities.units.BuildPlan
import mindustry.gen.Building
import mindustry.gen.Teamc
import mindustry.gen.Unit
import mindustry.graphics.Drawf
import mindustry.graphics.Layer
import mindustry.graphics.Pal
import mindustry.type.Item
import mindustry.world.Block
import mindustry.world.Edges
import mindustry.world.Tile
import mindustry.world.blocks.Autotiler.SliceMode
import mindustry.world.blocks.distribution.Conveyor
import mindustry.world.blocks.distribution.DirectionalUnloader
import mindustry.world.blocks.distribution.Duct
import mindustry.world.blocks.distribution.Router
import mindustry.world.draw.DrawBlock
import mindustry.world.draw.DrawDefault
import mindustry.world.meta.StatUnit
import kotlin.math.min
import kotlin.math.sin

import mindustry.Vars.itemSize
import mindustry.Vars.state
import mindustry.Vars.tilesize
import mindustry.Vars.world

/**
 * Are you still troubled by the 20 sprites of traditional conveyor belts?
 *
 * This Type only sprites 3 textures to handle!
 * @author Wisadell
 */
open class BeltConveyor(name: String) : Conveyor(name) {
    lateinit var conveyorPartAtlas: TextureRegion
    lateinit var edgePartAtlas:TextureRegion
    lateinit var conveyorParts: Array<Array<TextureRegion>>
    lateinit var edgeParts: Array<Array<TextureRegion>>

    override fun load() {
        super.load()
        uiIcon = Core.atlas.find("$name-icon")

        conveyorPartAtlas = Core.atlas.find("$name-base")
        edgePartAtlas = Core.atlas.find("$name-edge")

        conveyorParts = conveyorPartAtlas.split(32, 32)
        edgeParts = edgePartAtlas.split(32, 32)
    }

    override fun drawPlanRegion(plan: BuildPlan, list: Eachable<BuildPlan>) {
        Draw.rect(uiIcon, plan.drawx(), plan.drawy(), (plan.rotation * 90).toFloat())
    }

    override fun icons(): Array<TextureRegion> {
        return arrayOf(uiIcon)
    }

    override fun blends(tile: Tile, rotation: Int, otherx: Int, othery: Int, otherrot: Int, otherblock: Block): Boolean {
        return if (noSideBlend) (otherblock.outputsItems() && blendsArmored(tile, rotation, otherx, othery, otherrot, otherblock)) || (lookingAt(tile, rotation, otherx, othery, otherblock) && otherblock.hasItems) else super.blends(tile, rotation, otherx, othery, otherrot, otherblock)
    }

    override fun blendsArmored(tile: Tile, rotation: Int, otherx: Int, othery: Int, otherrot: Int, otherblock: Block): Boolean {
        return if (noSideBlend) (Point2.equals(tile.x + Geometry.d4(rotation).x, tile.y + Geometry.d4(rotation).y, otherx, othery) || ((!otherblock.rotatedOutput(otherx, othery) && Edges.getFacingEdge(otherblock, otherx, othery, tile) != null && Edges.getFacingEdge(otherblock, otherx, othery, tile).relativeTo(tile).toInt() == rotation) || (otherblock is Conveyor && otherblock.rotatedOutput(otherx, othery) && Point2.equals(otherx + Geometry.d4(otherrot).x, othery + Geometry.d4(otherrot).y, tile.x.toInt(), tile.y.toInt())))) else super.blendsArmored(tile, rotation, otherx, othery, otherrot, otherblock)
    }

    open inner class BeltConveyorBuild : ConveyorBuild() {
        override fun acceptItem(source: Building, item: Item): Boolean {
            return if (noSideBlend) super.acceptItem(source, item) && (source.block is Conveyor || Edges.getFacingEdge(source.tile(), tile).relativeTo(tile).toInt() == rotation) else super.acceptItem(source, item)
        }

        override fun draw() {
            val frame = if (enabled && clogHeat <= 0.5f) (((Time.time * speed * 8f * timeScale * efficiency)) % 4).toInt() else 0

            //draw extra conveyors facing this one for non-square tiling purposes
            Draw.z(Layer.blockUnder)
            for (i in 0..3) {
                if ((blending and (1 shl i)) != 0) {
                    val dir = rotation - i
                    val rot = (if (i == 0) rotation * 90 else (dir) * 90).toFloat()

                    Draw.rect(sliced(conveyorParts[frame][0], if (i != 0) SliceMode.bottom else SliceMode.top), x + Geometry.d4x(dir) * tilesize * 0.75f, y + Geometry.d4y(dir) * tilesize * 0.75f, rot)
                    Draw.rect(sliced(edgeParts[(tile.x + tile.y) % 2][0], if (i != 0) SliceMode.bottom else SliceMode.top), x + Geometry.d4x(dir) * tilesize * 0.75f, y + Geometry.d4y(dir) * tilesize * 0.75f, rot)
                }
            }

            Draw.z(Layer.block - 0.2f)

            Draw.rect(conveyorParts[frame][blendbits], x, y, (tilesize * blendsclx).toFloat(), (tilesize * blendscly).toFloat(), (rotation * 90).toFloat())
            Draw.rect(edgeParts[(tile.x + tile.y) % 2][blendbits], x, y, (tilesize * blendsclx).toFloat(), (tilesize * blendscly).toFloat(), (rotation * 90).toFloat())

            Draw.z(Layer.block - 0.1f)
            val layer = Layer.block - 0.1f
            val wwidth = world.unitWidth().toFloat()
            val wheight = world.unitHeight().toFloat()
            val scaling = 0.01f

            for (i in 0 until len) {
                val item = ids[i]
                Tmp.v1.trns((rotation * 90).toFloat(), tilesize.toFloat(), 0f)
                Tmp.v2.trns((rotation * 90).toFloat(), -tilesize / 2f, xs[i] * tilesize / 2f)

                val ix = (x + Tmp.v1.x * ys[i] + Tmp.v2.x)
                val iy = (y + Tmp.v1.y * ys[i] + Tmp.v2.y)

                //keep draw position deterministic.
                Draw.z(layer + (ix / wwidth + iy / wheight) * scaling)
                Draw.rect(item.fullIcon, ix, iy,itemSize, itemSize)
            }
        }
    }
}

/**
 * Compared to the original conveyor belt, there is an additional sprites on top of the item layer.
 * @author Wisadell
 */
open class CoveredConveyor(name: String) : BeltConveyor(name) {
    lateinit var coverRegions: Array<TextureRegion>
    lateinit var inputRegion: TextureRegion
    lateinit var outputRegion: TextureRegion

    override fun load() {
        super.load()
        inputRegion = Core.atlas.find("$name-cover-in")
        outputRegion = Core.atlas.find("$name-cover-out")

        coverRegions = HIUtils.split("$name-cover", 32, 0)
    }

    override fun drawPlanRegion(plan: BuildPlan, list: Eachable<BuildPlan>) {
        super.drawPlanRegion(plan, list)

        val bits = getTiling(plan, list) ?: return

        val region = coverRegions[bits[0]]
        Draw.rect(region, plan.drawx(), plan.drawy(), region.width * bits[1] * Draw.scl, region.height * bits[2] * Draw.scl, (plan.rotation * 90).toFloat())
    }

    open inner class CoveredConveyorBuild : BeltConveyorBuild() {
        var backCap = false
        var leftCap = false
        var rightCap = false
        var frontCap = false

        override fun draw() {
            val frame = if (enabled && clogHeat <= 0.5f) (((Time.time * speed * 8f * timeScale * efficiency)) % 4).toInt() else 0

            //draw extra conveyors facing this one for non-square tiling purposes
            Draw.z(Layer.blockUnder)
            for (i in 0..3) {
                if ((blending and (1 shl i)) != 0) {
                    val dir = rotation - i
                    val rot = (if (i == 0) rotation * 90 else (dir) * 90).toFloat()

                    Draw.rect(sliced(conveyorParts[frame][0], if (i != 0) SliceMode.bottom else SliceMode.top), x + Geometry.d4x(dir) * tilesize * 0.75f, y + Geometry.d4y(dir) * tilesize * 0.75f, rot)
                }
            }

            Draw.z(Layer.block - 0.2f)

            Draw.rect(conveyorParts[frame][blendbits], x, y, (tilesize * blendsclx).toFloat(), (tilesize * blendscly).toFloat(), (rotation * 90).toFloat())

            Draw.z(Layer.block - 0.1f)
            val layer = Layer.block - 0.1f
            val wwidth = world.unitWidth().toFloat()
            val wheight = world.unitHeight().toFloat()
            val scaling = 0.01f

            for (i in 0 until len) {
                val item = ids[i]
                Tmp.v1.trns((rotation * 90).toFloat(), tilesize.toFloat(), 0f)
                Tmp.v2.trns((rotation * 90).toFloat(), -tilesize / 2f, xs[i] * tilesize / 2f)

                val ix = (x + Tmp.v1.x * ys[i] + Tmp.v2.x)
                val iy = (y + Tmp.v1.y * ys[i] + Tmp.v2.y)

                //keep draw position deterministic.
                Draw.z(layer + (ix / wwidth + iy / wheight) * scaling)
                Draw.rect(item.fullIcon, ix, iy, itemSize, itemSize)
            }

            Draw.z(Layer.block - 0.08f)
            Draw.rect(coverRegions[blendbits], x, y, (tilesize * blendsclx).toFloat(), (tilesize * blendscly).toFloat(), (rotation * 90).toFloat())

            if (frontCap) Draw.rect(outputRegion, x, y, rotdeg())
            if (!backCap) Draw.rect(inputRegion, x, y, rotdeg())
            if (leftCap) Draw.rect(inputRegion, x, y, rotdeg() - 90f)
            if (rightCap) Draw.rect(inputRegion, x, y, rotdeg() + 90f)
        }

        override fun unitOn(unit: Unit) {
            //There is a cover, can't slide on this thing
        }

        override fun onProximityUpdate() {
            super.onProximityUpdate()

            frontCap = nextc == null || block !== nextc.block

            val backB = back()
            backCap = (blendbits == 1 || blendbits == 4) || (HIUtils.relativeDirection(backB, this) == 0) && backB.block === block

            val leftB = left()
            leftCap = blendbits != 0 && HIUtils.relativeDirection(leftB, this) == 0 && leftB.block !== block

            val rightB = right()
            rightCap = blendbits != 0 && HIUtils.relativeDirection(rightB, this) == 0 && rightB.block !== block
        }
    }
}

/**
 * Compared to CoveredConverter, its upper layer texture has been changed to one that can have light and shadow effects.
 * @author Wisadell
 */
open class TubeConveyor(name: String) : BeltConveyor(name) {
    companion object {
        val itemSpace = 0.4f
        val tiles: Array<IntArray> = arrayOf(
            intArrayOf(),
            intArrayOf(0, 2), intArrayOf(1, 3), intArrayOf(0, 1),
            intArrayOf(0, 2), intArrayOf(0, 2), intArrayOf(1, 2),
            intArrayOf(0, 1, 2), intArrayOf(1, 3), intArrayOf(0, 3),
            intArrayOf(1, 3), intArrayOf(0, 1, 3), intArrayOf(2, 3),
            intArrayOf(0, 2, 3), intArrayOf(1, 2, 3), intArrayOf(0, 1, 2, 3)
        )
    }

    lateinit var topRegion: Array<Array<TextureRegion>>
    lateinit var capRegion: Array<TextureRegion>

    override fun drawPlanRegion(plan: BuildPlan, list: Eachable<BuildPlan>) {
        super.drawPlanRegion(plan, list)
        val directionals = arrayOfNulls<BuildPlan>(4)
        list.each { other: BuildPlan ->
            if (other.breaking || other === plan) return@each
            for ((i, point) in Geometry.d4.withIndex()) {
                val x = plan.x + point.x
                val y = plan.y + point.y
                if ((x >= other.x - (other.block.size - 1) / 2 && x <= other.x + (other.block.size / 2)) && y >= other.y - (other.block.size - 1) / 2 && y <= other.y + (other.block.size / 2)) {
                    if ((if (other.block is Conveyor) (plan.rotation == i || (other.rotation + 2) % 4 == i) else ((plan.rotation == i && other.block.acceptsItems) || (plan.rotation != i && other.block.outputsItems())))) {
                        directionals[i] = other
                    }
                }
            }
        }

        var mask = 0
        for (i in directionals.indices) {
            if (directionals[i] != null) {
                mask += (1 shl i)
            }
        }
        mask = mask or (1 shl plan.rotation)
        Draw.rect(topRegion[0][mask], plan.drawx(), plan.drawy(), 0f)
        for (i in tiles[mask]) {
            if (directionals[i] == null || (if (directionals[i]!!.block is Conveyor) (directionals[i]!!.rotation + 2) % 4 == plan.rotation else ((plan.rotation == i && !directionals[i]!!.block.acceptsItems) || (plan.rotation != i && !directionals[i]!!.block.outputsItems())))) {
                val id = if (i == 0 || i == 3) 1 else 0
                Draw.rect(capRegion[id], plan.drawx(), plan.drawy(), (if (i == 0 || i == 2) 0 else -90).toFloat())
            }
        }
    }

    override fun load() {
        super.load()
        topRegion = HIUtils.splitLayers("$name-sheet", 32, 2)
        capRegion = arrayOf(topRegion[1][0], topRegion[1][1])
    }

    override fun icons(): Array<TextureRegion> {
        return arrayOf(Core.atlas.find("$name-icon-editor"))
    }

    open inner class TubeConveyorBuild : BeltConveyorBuild() {
        @JvmField var tiling = 0
        @JvmField var calls = 0

        override fun updateProximity() {
            super.updateProximity()
            calls++
        }

        fun buildAt(i: Int): Building? {
            return nearby(i)
        }

        fun valid(i: Int): Boolean {
            val b = buildAt(i)
            return b != null && (if (b is TubeConveyorBuild) (b.front() != null && b.front() === this) else (b.block.acceptsItems || b.block.outputsItems()))
        }

        fun isEnd(i: Int): Boolean {
            val b = buildAt(i)
            return (!valid(i) && b?.block !== this.block) || (b is ConveyorBuild && ((b.rotation + 2) % 4 == rotation || (b.front() !== this && back() === b)))
        }

        override fun draw() {
            val frame = if (enabled && clogHeat <= 0.5f) (((Time.time * speed * 8f * timeScale * efficiency)) % 4).toInt() else 0

            //draw extra conveyors facing this one for non-square tiling purposes
            Draw.z(Layer.blockUnder)
            for (i in 0..3) {
                if ((blending and (1 shl i)) != 0) {
                    val dir = rotation - i
                    val rot = (if (i == 0) rotation * 90 else (dir) * 90).toFloat()

                    Draw.rect(sliced(conveyorParts[frame][0], if (i != 0) SliceMode.bottom else SliceMode.top), x + Geometry.d4x(dir) * tilesize * 0.75f, y + Geometry.d4y(dir) * tilesize * 0.75f, rot)
                    Draw.rect(sliced(topRegion[0][1], if (i != 0) SliceMode.bottom else SliceMode.top), x + Geometry.d4x(dir) * tilesize * 0.75f, y + Geometry.d4y(dir) * tilesize * 0.75f, rot)
                }
            }

            Draw.z(Layer.block - 0.25f)

            Draw.rect(conveyorParts[frame][blendbits], x, y, (tilesize * blendsclx).toFloat(), (tilesize * blendscly).toFloat(), (rotation * 90).toFloat())

            Draw.z(Layer.block - 0.2f)
            val layer = Layer.block - 0.2f
            val wwidth = world.unitWidth().toFloat()
            val wheight = world.unitHeight().toFloat()
            val scaling = 0.01f

            for (i in 0 until len) {
                val item = ids[i]
                Tmp.v1.trns((rotation * 90).toFloat(), tilesize.toFloat(), 0f)
                Tmp.v2.trns((rotation * 90).toFloat(), -tilesize / 2f, xs[i] * tilesize / 2f)

                val ix = (x + Tmp.v1.x * ys[i] + Tmp.v2.x)
                val iy = (y + Tmp.v1.y * ys[i] + Tmp.v2.y)

                //keep draw position deterministic.
                Draw.z(layer + (ix / wwidth + iy / wheight) * scaling)
                Draw.rect(item.fullIcon, ix, iy, itemSize, itemSize)
            }

            Draw.z(Layer.block - 0.15f)
            Draw.rect(topRegion[0][tiling], x, y, 0f)
            val placementID = tiles[tiling]
            for (i in placementID) {
                if (isEnd(i)) {
                    val id = if (i == 0 || i == 3) 1 else 0
                    Draw.rect(capRegion[id], x, y, (if (i == 0 || i == 2) 0 else -90).toFloat())
                }
            }
        }

        override fun acceptStack(item: Item, amount: Int, source: Teamc): Int {
            if (isEnd(HIUtils.reverse(rotation)) && items.total() >= 2) return 0
            if (isEnd(HIUtils.reverse(rotation)) && isEnd(rotation) && items.total() >= 1) return 0
            return min((minitem / itemSpace).toInt().toDouble(), amount.toDouble()).toInt()
        }

        override fun unitOn(unit: Unit) {
        }

        override fun drawCracks() {
            Draw.z(Layer.block)
            super.drawCracks()
        }

        override fun onProximityUpdate() {
            super.onProximityUpdate()
            noSleep()
            next = front()
            nextc = if (next is TubeConveyorBuild && next.team === team) next as TubeConveyorBuild else null
            aligned = nextc != null && rotation == next.rotation

            tiling = 0
            for (i in 0..3) {
                val otherblock = nearby(i) ?: continue
                if ((if (otherblock.block is Conveyor) (rotation == i || (otherblock.rotation + 2) % 4 == i) else ((rotation == i && otherblock.block.acceptsItems) || (rotation != i && otherblock.block.outputsItems())))) {
                    tiling = tiling or (1 shl i)
                }
            }
            tiling = tiling or (1 shl rotation)
        }
    }
}

open class TubeDistributor(name: String) : Router(name) {
    @JvmField var drawer: DrawBlock = DrawDefault()

    lateinit var rotorRegion: TextureRegion
    lateinit var lockedRegion1: TextureRegion
    lateinit var lockedRegion2: TextureRegion

    init {
        rotate = true
    }

    override fun load() {
        super.load()
        drawer.load(this)
        rotorRegion = Core.atlas.find("$name-rotator")
        lockedRegion1 = Core.atlas.find("$name-locked-side1")
        lockedRegion2 = Core.atlas.find("$name-locked-side2")
        uiIcon = Core.atlas.find("$name-icon")
    }

    override fun drawPlanRegion(plan: BuildPlan, list: Eachable<BuildPlan>) {
        super.drawPlanRegion(plan, list)
        Draw.rect(Core.atlas.find("$name-bottom"), plan.drawx(), plan.drawy())
        Draw.rect(rotorRegion, plan.drawx(), plan.drawy())
        Draw.rect(region, plan.drawx(), plan.drawy())
        Draw.rect(
            if (plan.rotation > 1) lockedRegion2 else lockedRegion1,
            plan.drawx(),
            plan.drawy(),
            (plan.rotation * 90).toFloat()
        )
    }

    public override fun icons(): Array<TextureRegion> {
        return arrayOf(Core.atlas.find("$name-icon"))
    }

    open inner class TubeDistributorBuild : RouterBuild() {
        @JvmField var lastTargetAngle = 0
        @JvmField var lastSourceAngle = 0
        @JvmField var time = 0f
        @JvmField var rot = 0f
        @JvmField var angle = 0f
        @JvmField var lastRot = 0f
        @JvmField var lastRotation = rotation

        override fun updateTile() {
            if (lastItem == null && items.any()) {
                lastItem = items.first()
            }

            if (lastItem != null) {
                time += 1f / speed * delta()

                val target = getTileTarget(lastItem, lastInput, false)

                if (target == null && time >= 0.7f) {
                    rot = lastRot
                    time = 0.7f
                }

                if (target != null && (time >= 1f)) {
                    getTileTarget(lastItem, lastInput, true)
                    target.handleItem(this, lastItem)
                    items.remove(lastItem, 1)
                    lastItem = null
                }

                if (lastInput != null && lastItem != null) {
                    val sa = sourceAngle()
                    val ta = targetAngle()

                    angle = (if ((sa == 0)) (if (ta == 2) 1 else if ((ta == 0 || ta == 3)) -1 else 1) else if ((sa == 2)) if ((ta == 0 || ta == 1)) -1 else 1 else if ((sa == 1)) if ((ta == 0 || ta == 3)) -1 else 1 else if ((ta == 0 || ta == 1)) 1 else -1).toFloat()
                }

                if (items.total() > 0 && !state.isPaused) {
                    lastRot = rot
                    rot += speed * angle * delta()
                }
            }
        }

        override fun acceptItem(source: Building, item: Item): Boolean {
            return team === source.team && lastItem == null && items.total() == 0 && front() !== source
        }

        override fun handleItem(source: Building, item: Item) {
            items.add(item, 1)
            lastItem = item
            time = 0f
            lastInput = source.tile()
        }

        override fun removeStack(item: Item, amount: Int): Int {
            val result = super.removeStack(item, amount)
            if (result != 0 && item === lastItem) {
                lastItem = null
            }
            return result
        }

        fun sourceAngle(): Int {
            for (sourceAngle in 0..3) {
                if (nearby(sourceAngle) === lastInput.build) {
                    lastSourceAngle = sourceAngle
                    return sourceAngle
                }
            }
            return lastSourceAngle
        }

        fun targetAngle(): Int {
            val target = getTileTarget(lastItem, lastInput, false)
            if (target != null) {
                for (targetAngle in 0..3) {
                    if (nearby(targetAngle) === target) {
                        lastTargetAngle = targetAngle
                        return targetAngle
                    }
                }
            }
            return lastTargetAngle
        }

        fun drawItem() {
            if (lastInput != null && lastInput.build != null && lastItem != null) {
                val isf = HIUtils.reverse(sourceAngle()) == targetAngle() || sourceAngle() == targetAngle()
                val alignment = targetAngle() == 0 || targetAngle() == 2
                val ox: Float
                val oy: Float
                val s = (size * 4).toFloat()
                val s2 = s * 2
                val linearMove = sin(Math.PI * time).toFloat() / 2.4f * s

                if (alignment) {
                    if (isf) {
                        if (sourceAngle() == targetAngle()) {
                            oy = if (time >= 0.5f) linearMove else -linearMove
                            ox = if (time >= 0.5f) (time * s2 - s) * (if (targetAngle() == 0) 1 else -1) else (time * s2 - s) * (if (targetAngle() == 0) -1 else 1)
                        } else {
                            oy = linearMove
                            ox = (time * s2 - s) * (if (targetAngle() == 0) 1 else -1)
                        }
                    } else {
                        oy = if (sourceAngle() == 1) (time * -s + s) else (time * s - s)
                        ox = time * s * (if (targetAngle() == 0) 1 else -1)
                    }
                } else {
                    if (isf) {
                        if (sourceAngle() == targetAngle()) {
                            ox = if (time >= 0.5f) linearMove else -linearMove
                            oy = if (time >= 0.5f) (time * s2 - s) * (if (targetAngle() == 1) 1 else -1) else (time * s2 - s) * (if (targetAngle() == 1) -1 else 1)
                        } else {
                            ox = sin(Math.PI * time).toFloat() / 2.4f * s
                            oy = (time * s2 - s) * (if (targetAngle() == 1) 1 else -1)
                        }
                    } else {
                        ox = if (sourceAngle() == 0) (time * -s + s) else (time * s - s)
                        oy = time * s * (if (targetAngle() == 1) 1 else -1)
                    }
                }

                Draw.rect(lastItem.fullIcon, x + ox, y + oy, itemSize, itemSize)
            }
        }

        override fun draw() {
            drawer.draw(this)
            Draw.z(Layer.block - 0.2f)
            drawItem()
            Draw.z(Layer.block - 0.15f)
            Drawf.spinSprite(rotorRegion, x, y, rot % 360)
            Draw.rect(region, x, y)
            Draw.rect(if (rotation > 1) lockedRegion2 else lockedRegion1, x, y, rotdeg())
        }

        override fun getTileTarget(item: Item, from: Tile, set: Boolean): Building? {
            val counter = lastRotation
            for (i in 0 until proximity.size) {
                val other = proximity[(i + counter) % proximity.size]
                if (set) lastRotation = ((lastRotation + 1) % proximity.size).toByte().toInt()
                if (other.tile === from && from.block() === Blocks.overflowGate) continue
                if (other.acceptItem(this, item)) {
                    return other
                }
            }
            return null
        }
    }
}

/**
 * Like BeltConversor, it only requires two or three sprites.
 *
 * At the same time, it has added a consumable power acceleration function.
 * @author Wisadell
 */
open class AdaptDuct(name: String) : Duct(name) {
    lateinit var topAtlas: Array<TextureRegion>
    lateinit var botAtlas: Array<TextureRegion>
    lateinit var glowAtlas: Array<TextureRegion>

    @JvmField var glowAlpha = 1f
    @JvmField var glowColor: Color = Pal.redLight

    @JvmField var baseEfficiency = 0f

    init {
        noUpdateDisabled = false
    }

    override fun setStats() {
        super.setStats()
        stats.add(HIStat.itemsMovedBoost, 60f / (speed / (1f + baseEfficiency)), StatUnit.itemsSecond)
    }

    override fun load() {
        super.load()
        botAtlas = HIUtils.split("$name-bot", 32, 0)
        topAtlas = HIUtils.split("$name-top", 32, 0)
        glowAtlas = HIUtils.split("$name-glow", 32, 0)
    }

    override fun drawPlanRegion(plan: BuildPlan, list: Eachable<BuildPlan>) {
        val bits = getTiling(plan, list) ?: return

        Draw.scl(bits[1].toFloat(), bits[2].toFloat())
        Draw.alpha(0.5f)
        Draw.rect(botAtlas[bits[0]], plan.drawx(), plan.drawy(), (plan.rotation * 90).toFloat())
        Draw.color()
        Draw.rect(topAtlas[bits[0]], plan.drawx(), plan.drawy(), (plan.rotation * 90).toFloat())
        Draw.scl()
    }

    override fun icons(): Array<TextureRegion> {
        return arrayOf(botAtlas[0], topAtlas[0])
    }

    open inner class AdaptDuctBuild : DuctBuild() {
        override fun draw() {
            val r1 = rotdeg()
            val r2 = rotation

            //draw extra ducts facing this one for tiling purposes
            for (i in 0..3) {
                if ((blending and (1 shl i)) != 0) {
                    val dir = r2 - i
                    val rot = if (i == 0) r1 else ((dir) * 90).toFloat()
                    drawAt(x + Geometry.d4x(dir) * tilesize * 0.75f, y + Geometry.d4y(dir) * tilesize * 0.75f, 0, rot, if (i != 0) SliceMode.bottom else SliceMode.top)
                }
            }

            //draw item
            if (current != null) {
                Draw.z(Layer.blockUnder + 0.1f)
                Tmp.v1.set(Geometry.d4x(recDir) * tilesize / 2f, Geometry.d4y(recDir) * tilesize / 2f).lerp(Geometry.d4x(r2) * tilesize / 2f, Geometry.d4y(r2) * tilesize / 2f, Mathf.clamp((progress + 1f) / 2f))

                Draw.rect(current.fullIcon, x + Tmp.v1.x, y + Tmp.v1.y, itemSize, itemSize)
            }

            Draw.scl(xscl.toFloat(), yscl.toFloat())
            drawAt(x, y, blendbits, r1, SliceMode.none)
            Draw.reset()
        }

        override fun drawAt(x: Float, y: Float, bits: Int, rotation: Float, slice: SliceMode) {
            Draw.z(Layer.blockUnder)
            Draw.rect(sliced(botAtlas[bits], slice), x, y, rotation)

            Draw.z(Layer.blockUnder + 0.2f)
            Draw.color(transparentColor)
            Draw.rect(sliced(botAtlas[bits], slice), x, y, rotation)
            Draw.color()
            Draw.rect(sliced(topAtlas[bits], slice), x, y, rotation)

            if (sliced(glowAtlas[bits], slice).found() && power != null && power.status > 0f) {
                Draw.z(Layer.blockAdditive)
                Draw.color(glowColor, glowAlpha * power.status)
                Draw.blend(Blending.additive)
                Draw.rect(sliced(glowAtlas[bits], slice), x, y, rotation)
                Draw.blend()
                Draw.color()
            }
        }

        override fun updateTile() {
            val eff = if (power.status > 0f) (power.status + baseEfficiency) else 1f
            progress += this.delta() * eff / speed * 2f
            super.updateTile()
        }
    }
}

/**
 * A directional unloader that is not affected by game frame rates.
 * @author Wisadell
 */
open class AdaptDirectionalUnloader(name: String) : DirectionalUnloader(name) {
    open inner class AdaptDirectionalUnloaderBuild : DirectionalUnloaderBuild() {
        private var counter = 0f

        /** Make its uninstallation speed no longer affected by frame rate. */
        override fun updateTile() {
            counter += edelta()
            val limit = speed

            while (counter >= limit) {
                unloadTimer = speed
                super.updateTile()
                counter -= limit
            }
        }
    }
}

/**
 * An electrically powered and accelerated unloader is one that adjusts the acceleration parameters of the original version and assigns them back after acceleration.
 * @author Wisadell
 * @author guiY
 */
open class OverchargeDirectionalUnloader(name: String) : AdaptDirectionalUnloader(name) {
    init {
        hasPower = true
    }

    open inner class OverchargeDirectionalUnloaderBuild : AdaptDirectionalUnloaderBuild() {
        var ts = 1f
        var td = 0f
        var powers = 0f

        override fun delta(): Float {
            return Time.delta * ts
        }

        override fun applyBoost(intensity: Float, duration: Float) {
            if (intensity >= ts) td = td.coerceAtLeast(duration)
            ts = ts.coerceAtLeast(intensity)
        }

        override fun updateTile() {
            powers = power.status

            if (td > 0) {
                td -= Time.delta
                if (td <= 0) ts = 1f
            }

            timeScale = (ts * powers).coerceAtLeast(0.001f)
            timeScaleDuration = (td / powers).coerceAtLeast(0.001f)

            if (powers >= 0.999) super.updateTile()
        }
    }
}
