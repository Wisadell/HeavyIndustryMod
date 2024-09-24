package heavyindustry.world.blocks.distribution

import heavyindustry.util.HIUtils
import arc.Core
import arc.graphics.g2d.Draw
import arc.graphics.g2d.TextureRegion
import arc.util.Eachable
import mindustry.entities.units.BuildPlan
import mindustry.gen.Unit
import mindustry.graphics.Layer

import mindustry.Vars.tilesize

open class CoveredConveyor(name: String) : BeltConveyor(name) {
    lateinit var coverRegions: Array<TextureRegion>
    lateinit var inputRegion: TextureRegion
    lateinit var outputRegion: TextureRegion

    override fun load() {
        super.load()
        inputRegion = Core.atlas.find("$name-in")
        outputRegion = Core.atlas.find("$name-out")

        coverRegions = HIUtils.split("$name-construct", 32, 0)
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
            super.draw()

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
