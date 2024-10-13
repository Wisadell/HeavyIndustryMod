package heavyindustry.world.blocks.distribution;

import arc.Core;
import heavyindustry.world.meta.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.*;
import mindustry.graphics.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.meta.*;

import static heavyindustry.util.HIUtils.*;
import static arc.Core.*;
import static mindustry.Vars.*;

/**
 * Like BeltConversor, it only requires two or three sprites.
 * <p>
 * At the same time, it has added a consumable power acceleration function.
 * @author Wisadell
 */
public class AdaptDuct extends Duct {
    public TextureRegion[] botAtlas, topAtlas, glowAtlas;

    public float glowAlpha = 1f;
    public Color glowColor = Pal.redLight;

    public float baseEfficiency = 0f;

    public AdaptDuct(String name) {
        super(name);
        noUpdateDisabled = false;
    }

    @Override
    public void load() {
        super.load();
        uiIcon = atlas.find(name + "-icon");

        botAtlas = split(name + "-bot", 32, 0);
        topAtlas = split(name + "-top", 32, 0);
        glowAtlas = split(name + "-glow", 32, 0);
    }

    @Override
    public TextureRegion[] icons() {
        return new TextureRegion[]{uiIcon};
    }

    @Override
    public void setStats() {
        super.setStats();

        stats.add(HIStat.itemsMovedBoost, 60f / (speed / (1f + baseEfficiency)), StatUnit.itemsSecond);
    }

    public class AdaptDuctBuild extends DuctBuild {
        @Override
        public void draw() {
            float rotation = rotdeg();
            int r = this.rotation;

            //draw extra ducts facing this one for tiling purposes
            for (int i = 0; i < 4; i++) {
                if ((blending & (1 << i)) != 0) {
                    int dir = r - i;
                    float rot = i == 0 ? rotation : (dir) * 90;
                    drawAt(x + Geometry.d4x(dir) * tilesize * 0.75f, y + Geometry.d4y(dir) * tilesize * 0.75f, 0, rot, i != 0 ? SliceMode.bottom : SliceMode.top);
                }
            }

            //draw item
            if (current != null) {
                Draw.z(Layer.blockUnder + 0.1f);
                Tmp.v1.set(Geometry.d4x(recDir) * tilesize / 2f, Geometry.d4y(recDir) * tilesize / 2f).lerp(Geometry.d4x(r) * tilesize / 2f, Geometry.d4y(r) * tilesize / 2f, Mathf.clamp((progress + 1f) / 2f));

                Draw.rect(current.fullIcon, x + Tmp.v1.x, y + Tmp.v1.y, itemSize, itemSize);
            }

            Draw.scl(xscl, yscl);
            drawAt(x, y, blendbits, rotation, SliceMode.none);
            Draw.reset();
        }

        @Override
        protected void drawAt(float x, float y, int bits, float rotation, SliceMode slice) {
            Draw.z(Layer.blockUnder);
            Draw.rect(sliced(botAtlas[bits], slice), x, y, rotation);

            Draw.z(Layer.blockUnder + 0.2f);
            Draw.color(transparentColor);
            Draw.rect(sliced(botAtlas[bits], slice), x, y, rotation);
            Draw.color();
            Draw.rect(sliced(topAtlas[bits], slice), x, y, rotation);

            if (sliced(glowAtlas[bits], slice).found() && power != null && power.status > 0f) {
                Draw.z(Layer.blockAdditive);
                Draw.color(glowColor, glowAlpha * power.status);
                Draw.blend(Blending.additive);
                Draw.rect(sliced(glowAtlas[bits], slice), x, y, rotation);
                Draw.blend();
                Draw.color();
            }
        }

        @Override
        public void updateTile() {
            float eff = power.status > 0f ? (power.status + baseEfficiency) : 1f;
            progress += delta() * eff / speed * 2f;
            super.updateTile();
        }
    }
}
