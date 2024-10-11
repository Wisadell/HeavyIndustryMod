package heavyindustry.world.blocks.heat;

import arc.*;
import arc.graphics.g2d.*;
import arc.util.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.world.blocks.heat.*;

import static heavyindustry.util.HIUtils.*;

public class HeatPipe extends HeatConductor {
    public TextureRegion[] heatRegion;
    public TextureRegion[][] fullRegion;

    public HeatPipe(String name)  {
        super(name);
        size = 1;
    }

    @Override
    public void load() {
        super.load();
        uiIcon = Core.atlas.find(name + "-icon");
        fullRegion = splitLayers(name + "-full", 32, 2);
        heatRegion = split(name + "-heat", 32, 0);
    }

    @Override
    public TextureRegion getPlanRegion(BuildPlan plan, Eachable<BuildPlan> list) {
        return uiIcon;
    }

    @Override
    public TextureRegion[] icons() {
        return new TextureRegion[]{uiIcon};
    }

    public class HeatPipeBuild extends HeatConductorBuild {
        public int tiling = 0;

        @Override
        public void draw() {
            Draw.rect(fullRegion[0][tiling], x, y, 0);
            //TODO Draw the heat by the heating core.
            Draw.rect(fullRegion[1][tiling], x, y, 0);
        }

        @Override
        public void onProximityUpdate() {
            super.onProximityUpdate();
            tiling = 0;
            for (int i = 0; i < 4; i++) {
                Building otherBlock = nearby(i);
                if (otherBlock instanceof HeatPipeBuild ? (rotation == i || (otherBlock.rotation + 2) % 4 == i) : (rotation == i && otherBlock instanceof HeatBlock)) {
                    tiling |= (1 << i);
                }
            }
            tiling |= 1 << rotation;
        }
    }
}
