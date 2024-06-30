package HeavyIndustry.world.blocks.production;

import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.meta.*;

public class GeneratorCrafter extends GenericCrafter {
    public float powerProduction = 0f;
    public Stat generationType = Stat.basePowerGeneration;
    public GeneratorCrafter(String name){
        super(name);
        hasPower = true;
        consumesPower = false;
        outputsPower = true;
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(generationType, powerProduction * 60f, StatUnit.powerSecond);
    }
    public class GeneratorCrafterBuild extends GenericCrafterBuild {
        @Override
        public float getPowerProduction(){
            return enabled ? powerProduction : 0f;
        }
    }
}
