package heavyindustry.world.blocks.heat

import arc.func.Prov
import mindustry.graphics.Pal
import mindustry.ui.Bar
import mindustry.world.blocks.heat.HeatProducer

open class LiquidFuelHeater(name: String?) : HeatProducer(name) {
    init {
        hasLiquids = true
        buildType = Prov { LiquidFuelHeaterBuild() }
    }

    override fun setBars() {
        super.setBars()
        removeBar("heat")
        addBar("heat") { entity: LiquidFuelHeaterBuild ->
            Bar("bar.heat", Pal.lightOrange) { entity.heat / heatOutput }
        }
    }

    inner class LiquidFuelHeaterBuild : HeatProducerBuild() {
        private fun liquidHeat(): Float {
            return if (liquids.current() != null) {
                liquids.current().flammability
            } else {
                0f
            }
        }

        override fun heat(): Float {
            return heat * liquidHeat()
        }
    }
}
