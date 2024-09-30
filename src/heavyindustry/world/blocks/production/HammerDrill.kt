package heavyindustry.world.blocks.production

import arc.Core
import arc.graphics.g2d.Draw
import arc.graphics.g2d.TextureRegion
import arc.math.Mathf
import arc.util.Eachable
import heavyindustry.gen.HISounds
import mindustry.entities.units.BuildPlan
import mindustry.game.Team
import mindustry.gen.Sounds
import mindustry.graphics.Layer
import mindustry.graphics.Pal

open class HammerDrill(name: String) : ImplosionDrill(name) {
    lateinit var hammerRegion: TextureRegion

    init {
        drillSound = HISounds.hammer
        ambientSound = Sounds.none
    }

    override fun load() {
        super.load()
        hammerRegion = Core.atlas.find("$name-hammer")
    }

    override fun drawPlanRegion(plan: BuildPlan, list: Eachable<BuildPlan>) {
        super.drawPlanRegion(plan, list)
        Draw.rect(hammerRegion, plan.drawx(), plan.drawy())
    }

    override fun icons(): Array<TextureRegion> {
        return if (teamRegion.found()) arrayOf(baseRegion, topRegion, teamRegions[Team.sharded.id], hammerRegion) else arrayOf(baseRegion, topRegion, hammerRegion)
    }

    open inner class HammerDrillBuild : ImplosionDrillBuild() {
        override fun draw() {
            Draw.rect(baseRegion, x, y)
            if (warmup > 0f) {
                drawMining()
            }

            Draw.z(Layer.blockOver - 4f)

            Draw.rect(topRegion, x, y)

            drawTeamTop()

            val fract = Mathf.clamp(smoothProgress, 0.25f, 0.3f)
            Draw.color(Pal.shadow, Pal.shadow.a)
            Draw.rect(hammerRegion, x - (fract - 0.25f) * 40, y - (fract - 0.25f) * 40, hammerRegion.width * fract, hammerRegion.width * fract)
            Draw.color()
            Draw.z(Layer.blockAdditive)
            Draw.rect(hammerRegion, x, y, hammerRegion.width * fract, hammerRegion.height * fract)

            if (outputItem() != null && drawMineItem) {
                Draw.color(dominantItem.color)
                Draw.rect(oreRegion, x, y)
                Draw.color()
            }
        }
    }
}
