package heavyindustry.world.blocks.distribution

import heavyindustry.util.HIUtils
import arc.Core
import arc.graphics.g2d.Draw
import arc.graphics.g2d.TextureRegion
import arc.math.geom.Geometry
import arc.util.Eachable
import arc.util.Time
import arc.util.Tmp
import mindustry.entities.units.BuildPlan
import mindustry.gen.Building
import mindustry.gen.Teamc
import mindustry.gen.Unit
import mindustry.graphics.Layer
import mindustry.type.Item
import mindustry.world.blocks.Autotiler.SliceMode
import mindustry.world.blocks.distribution.Conveyor
import kotlin.math.min

import mindustry.Vars.itemSize
import mindustry.Vars.tilesize
import mindustry.Vars.world

/**
 * Compared to CoveredConverter, its upper layer texture has been changed to one that can have light and shadow effects.
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
        var tiling: Int = 0
        var calls: Int = 0
        override fun updateProximity() {
            super.updateProximity()
            calls++
        }

        fun buildAt(i: Int): Building? {
            return nearby(i)
        }

        fun valid(i: Int): Boolean {
            val b = buildAt(i)
            return b != null && (if (b is TubeConveyorBuild) (b.front() != null && b.front() === this) else (b.block.acceptsItems || b.block.hasItems))
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

                    Draw.rect(sliced(regions[0][frame], if (i != 0) SliceMode.bottom else SliceMode.top), x + Geometry.d4x(dir) * tilesize * 0.75f, y + Geometry.d4y(dir) * tilesize * 0.75f, rot)
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
