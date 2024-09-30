package heavyindustry.world.blocks.distribution

import heavyindustry.util.HIUtils
import arc.Core
import arc.graphics.g2d.Draw
import arc.graphics.g2d.TextureRegion
import arc.math.geom.Geometry
import arc.math.geom.Point2
import arc.util.Eachable
import arc.util.Time
import arc.util.Tmp
import mindustry.entities.units.BuildPlan
import mindustry.gen.Building
import mindustry.gen.Teamc
import mindustry.gen.Unit
import mindustry.graphics.Layer
import mindustry.type.Item
import mindustry.world.Block
import mindustry.world.Edges
import mindustry.world.Tile
import mindustry.world.blocks.Autotiler.SliceMode
import mindustry.world.blocks.distribution.Conveyor
import kotlin.math.min

import mindustry.Vars.itemSize
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
