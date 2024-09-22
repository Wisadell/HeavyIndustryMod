package heavyindustry.world.blocks.distribution

import heavyindustry.util.HIUtils
import arc.Core
import arc.graphics.g2d.Draw
import arc.graphics.g2d.TextureRegion
import arc.util.Eachable
import mindustry.Vars
import mindustry.entities.units.BuildPlan
import mindustry.gen.Unit
import mindustry.graphics.Layer
import mindustry.world.blocks.distribution.Conveyor

open class CoveredConveyor(name: String) : Conveyor(name) {
    var coverRegions: Array<TextureRegion?> = arrayOfNulls(5)
    lateinit var inputRegion: TextureRegion
    lateinit var outputRegion: TextureRegion

    override fun load() {
        super.load()
        for (i in 0..4) {
            coverRegions[i] = Core.atlas.find("$name-cover-$i")
        }
        inputRegion = Core.atlas.find("$name-cover-in")
        outputRegion = Core.atlas.find("$name-cover-out")
    }

    override fun drawPlanRegion(req: BuildPlan, list: Eachable<BuildPlan>) {
        super.drawPlanRegion(req, list)

        val bits = getTiling(req, list) ?: return

        val region = coverRegions[bits[0]]
        Draw.rect(region, req.drawx(), req.drawy(), region!!.width * bits[1] * Draw.scl, region.height * bits[2] * Draw.scl, (req.rotation * 90).toFloat())
    }

    override fun icons(): Array<TextureRegion?> {
        return arrayOf(regions[0][0], coverRegions[0])
    }

    open inner class CoveredConveyorBuild : ConveyorBuild() {
        var backCap: Boolean = false
        var leftCap: Boolean = false
        var rightCap: Boolean = false
        var frontCap: Boolean = false

        override fun draw() {
            super.draw()

            Draw.z(Layer.block - 0.08f)
            Draw.rect(coverRegions[blendbits], x, y, (Vars.tilesize * blendsclx).toFloat(), (Vars.tilesize * blendscly).toFloat(), (rotation * 90).toFloat())

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
