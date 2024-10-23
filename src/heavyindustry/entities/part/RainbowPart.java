package heavyindustry.entities.part;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.entities.part.*;

import java.util.*;

import static heavyindustry.content.HIGet.*;
import static heavyindustry.util.HIUtils.*;

public class RainbowPart extends RegionPart {
    public float shiftSpeed = 3f;
    public float mul = 180f;
    public PartProgress rainbowProgress = PartProgress.warmup;

    public TextureRegion[][] rainbows = {};
    public Color[] colors = {};
    public int splitSize = 32;

    public RainbowPart(){
        super();
    }

    public RainbowPart(String region){
        super(region);
    }

    public RainbowPart(String region, Blending blending, Color color){
        super(region, blending, color);
    }

    @Override
    public void draw(PartParams params) {
        if(rainbows[0].length > 0) {
            float z = Draw.z();
            if (layer > 0) Draw.z(layer);
            Draw.z(z + 0.001f);
            Draw.z(Draw.z() + layerOffset);

            float rainbowAlpha = rainbowProgress.getClamp(params);
            float prog = progress.getClamp(params), sclProg = growProgress.getClamp(params);
            float mx = moveX * prog, my = moveY * prog, mr = moveRot * prog + rotation,
                    gx = growX * sclProg, gy = growY * sclProg;

            if (moves.size > 0) {
                for (int i = 0; i < moves.size; i++) {
                    var move = moves.get(i);
                    float p = move.progress.getClamp(params);
                    mx += move.x * p;
                    my += move.y * p;
                    mr += move.rot * p;
                    gx += move.gx * p;
                    gy += move.gy * p;
                }
            }

            int len = mirror && params.sideOverride == -1 ? 2 : 1;
            float preXscl = Draw.xscl, preYscl = Draw.yscl;
            Draw.xscl *= xScl + gx;
            Draw.yscl *= yScl + gy;

            for (int s = 0; s < len; s++) {
                for (int r = 0; r < rainbows[0].length; r++) {
                    int i = params.sideOverride == -1 ? s : params.sideOverride;

                    TextureRegion region = rainbows[s][r];
                    float sign = (i == 0 ? 1 : -1) * params.sideMultiplier;
                    Tmp.v2.set((x + mx) * sign, y + my).rotateRadExact((params.rotation - 90) * Mathf.degRad);

                    float
                            rx = params.x + Tmp.v2.x,
                            ry = params.y + Tmp.v2.y,
                            rot = mr * sign + params.rotation - 90;

                    Draw.xscl *= sign;

                    if (region.found()) {
                        Draw.color(rainStart(colors[r]).a(rainbowAlpha).shiftHue(Time.time * shiftSpeed + r * mul / rainbows[0].length));
                        Draw.rect(region, rx, ry, rot);
                    }

                    Draw.xscl *= sign;
                }
            }

            Draw.color();
            Draw.mixcol();

            Draw.z(z);

            Draw.scl(preXscl, preYscl);
        }

        super.draw(params);
    }

    @Override
    public void load(String name) {
        super.load(name);
        String realName = this.name == null ? name + suffix : this.name;

        if(mirror && turretShading){
            rainbows = splitLayers(realName, splitSize, 2);
        }else{
            rainbows = splitLayers(realName, splitSize, 1);
        }
        colors = new Color[rainbows[0].length];
        Arrays.fill(colors, new Color());
    }
}
