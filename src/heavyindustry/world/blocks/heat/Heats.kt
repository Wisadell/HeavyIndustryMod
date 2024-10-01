package heavyindustry.world.blocks.heat

import mindustry.graphics.Pal
import mindustry.ui.Bar
import mindustry.world.blocks.heat.HeatProducer

open class LiquidFuelHeater(name: String) : HeatProducer(name) {
    init {
        hasLiquids = true
    }

    override fun setBars() {
        super.setBars()
        removeBar("heat")
        addBar("heat") { entity: LiquidFuelHeaterBuild ->
            Bar("bar.heat", Pal.lightOrange) { entity.heat / heatOutput }
        }
    }

    open inner class LiquidFuelHeaterBuild : HeatProducerBuild() {
        fun liquidHeat(): Float {
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
