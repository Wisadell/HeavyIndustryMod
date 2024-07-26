package HeavyIndustry.world.blocks.production;

import arc.Core;
import arc.math.Mathf;
import arc.util.Strings;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.meta.*;

public class GeneratorCrafter extends GenericCrafter {
    public float powerProduction;
    public GeneratorCrafter(String name){
        super(name);
        hasPower = true;
        consumesPower = false;
        outputsPower = true;
        powerProduction = 1f;
        buildType = GeneratorCrafterBuild::new;
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.add(Stat.basePowerGeneration, powerProduction * 60.0f, StatUnit.powerSecond);
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("power", (GeneratorCrafterBuild build) -> new Bar(
                () -> Core.bundle.format("bar.poweroutput", Strings.fixed(build.getPowerProduction() * 60.0f * build.timeScale(), 1)),
                () -> Pal.powerBar,
                () -> Mathf.num(build.efficiency > 0.0f)
        ));
    }
    public class GeneratorCrafterBuild extends GenericCrafterBuild {
        @Override
        public float getPowerProduction(){
            return efficiency > 0.0f ? powerProduction : 0.0f;
        }
    }
}
