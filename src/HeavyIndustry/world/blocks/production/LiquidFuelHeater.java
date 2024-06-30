package HeavyIndustry.world.blocks.production;

import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.heat.HeatProducer;

public class LiquidFuelHeater extends HeatProducer {
    public LiquidFuelHeater(String name) {
        super(name);
        hasLiquids = true;
    }

    @Override
    public void setBars() {
        super.setBars();
        removeBar("heat");
        addBar("heat", (LiquidFuelHeaterBuild entity) -> new Bar("bar.heat", Pal.lightOrange, () -> entity.heat / heatOutput));
    }

    public class LiquidFuelHeaterBuild extends HeatProducerBuild{
        public float liquidHeat(){
            if(liquids.current() != null){
                return liquids.current().flammability;
            } else {
                return 0;
            }
        }

        @Override
        public float heat(){
            return heat * liquidHeat();
        }
    }
}
