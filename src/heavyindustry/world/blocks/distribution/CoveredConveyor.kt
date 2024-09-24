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
import mindustry.gen.Unit
import mindustry.graphics.Layer
import mindustry.world.blocks.Autotiler.SliceMode

import mindustry.Vars.itemSize
import mindustry.Vars.tilesize
import mindustry.Vars.world

/**
 * Compared to the original conveyor belt, there is an additional sprites on top of the item layer.
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
