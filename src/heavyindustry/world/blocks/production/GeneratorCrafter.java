package heavyindustry.world.blocks.production;

import arc.*;
import arc.math.*;
import arc.util.*;
import mindustry.graphics.*;
import mindustry.ui.*;
import mindustry.world.blocks.production.*;
import mindustry.world.meta.*;

/**
 * A factory that can produce electricity.
 * <p>
 * You are already a mature GenericCrafter,
 * it's time to learn how to generate electricity on your own.
 * @author Wisadell
 */
public class GeneratorCrafter extends GenericCrafter {
    public float powerProduction = 1f;
    public GeneratorCrafter(String name){
        super(name);
        hasPower = true;
        consumesPower = false;
        outputsPower = true;
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.add(Stat.basePowerGeneration, powerProduction * 60f, StatUnit.powerSecond);
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("power", (GeneratorCrafterBuild build) -> new Bar(
                () -> Core.bundle.format("bar.poweroutput", Strings.fixed(build.getPowerProduction() * 60f * build.timeScale(), 1)),
                () -> Pal.powerBar,
                () -> Mathf.num(build.efficiency > 0f)
        ));
    }

    public class GeneratorCrafterBuild extends GenericCrafterBuild {
        @Override
        public float getPowerProduction(){
            return efficiency > 0f ? powerProduction : 0f;
        }
    }
}
