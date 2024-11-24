package heavyindustry.world.blocks.production;

import heavyindustry.gen.*;
import arc.math.*;
import arc.util.*;
import arc.graphics.g2d.*;
import mindustry.entities.units.*;
import mindustry.game.Team;
import mindustry.gen.*;
import mindustry.graphics.*;

import static arc.Core.*;

public class HammerDrill extends ImplosionDrill {
    public TextureRegion hammerRegion;

    public HammerDrill(String name) {
        super(name);
        drillSound = HISounds.hammer;
        ambientSound = Sounds.none;
    }

    @Override
    public void load() {
        super.load();
        hammerRegion = atlas.find(name + "-hammer");
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list) {
        super.drawPlanRegion(plan, list);
        Draw.rect(hammerRegion, plan.drawx(), plan.drawy());
    }

    @Override
    public TextureRegion[] icons() {
        return teamRegion.found() ? new TextureRegion[]{baseRegion, topRegion, teamRegions[Team.sharded.id], hammerRegion} : new TextureRegion[]{baseRegion, topRegion, hammerRegion};
    }

    public class HammerDrillBuild extends ImplosionDrillBuild {
        @Override
        public void draw() {
            Draw.rect(baseRegion, x, y);
            if (warmup > 0f){
                drawMining();
            }

            Draw.z(Layer.blockOver - 4f);

            Draw.rect(topRegion, x, y);

            drawTeamTop();

            float fract = Mathf.clamp(smoothProgress, 0.25f, 0.3f);
            Draw.color(Pal.shadow, Pal.shadow.a);
            Draw.rect(hammerRegion, x - (fract - 0.25f) * 40, y - (fract - 0.25f) * 40, hammerRegion.width * fract, hammerRegion.width * fract);
            Draw.color();
            Draw.z(Layer.blockAdditive);
            Draw.rect(hammerRegion, x, y, hammerRegion.width * fract, hammerRegion.height * fract);

            if (outputItem() != null && drawMineItem) {
                Draw.color(dominantItem.color);
                Draw.rect(oreRegion, x, y);
                Draw.color();
            }
        }
    }
}
