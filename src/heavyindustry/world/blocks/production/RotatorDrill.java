package heavyindustry.world.blocks.production;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.game.*;
import mindustry.graphics.*;

import static arc.Core.*;

/**
 * Draw the original drill bit rotation.
 *
 * @author Eipusino
 */
public class RotatorDrill extends DrillF {
    public TextureRegion rotatorRegion, rimRegion;

    /** Speed the drill bit rotates at. */
    public float rotateSpeed = 2f;

    public Color heatColor = Color.valueOf("ff5512");

    public boolean drawRim = false, drawSpinSprite = true;

    public RotatorDrill(String name) {
        super(name);
        drillEffect = Fx.mine;
    }

    @Override
    public void load() {
        super.load();
        rotatorRegion = atlas.find(name + "-rotator");
        rimRegion = atlas.find(name + "-rim");
    }

    @Override
    public TextureRegion[] icons() {
        return teamRegion.found() ? new TextureRegion[]{baseRegion, rotatorRegion, topRegion, teamRegions[Team.sharded.id]} : new TextureRegion[]{baseRegion, rotatorRegion, topRegion};
    }

    public class RotatorDrillBuild extends DrillBuildF {
        public float timeDrilled;

        @Override
        public void updateTile() {
            super.updateTile();
            timeDrilled += warmup * delta();
        }

        @Override
        public void draw() {
            float s = 0.3f;
            float ts = 0.6f;

            Draw.rect(baseRegion, x, y);
            if (warmup > 0f) {
                drawMining();
            }

            Draw.z(Layer.blockOver - 4f);

            if (drawSpinSprite) {
                Drawf.spinSprite(rotatorRegion, x, y, timeDrilled * rotateSpeed);
            } else {
                Draw.rect(rotatorRegion, x, y, timeDrilled * rotateSpeed);
            }

            Draw.rect(topRegion, x, y);
            if (outputItem() != null && drawMineItem) {
                Draw.color(dominantItem.color);
                Draw.rect(oreRegion, x, y);
                Draw.color();
            }

            if (drawRim) {
                Draw.color(heatColor);
                Draw.alpha(warmup * ts * (1f - s + Mathf.absin(Time.time, 3f, s)));
                Draw.blend(Blending.additive);
                Draw.rect(rimRegion, x, y);
                Draw.blend();
                Draw.color();
            }

            drawTeamTop();
        }
    }
}
