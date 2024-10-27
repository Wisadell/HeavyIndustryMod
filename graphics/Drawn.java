package heavyindustry.graphics;

import heavyindustry.content.*;
import heavyindustry.math.*;
import arc.*;
import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.graphics.*;

import static mindustry.Vars.*;

public class Drawn {
    private static final Vec2 vec21 = new Vec2(), vec31 = new Vec2(), vec32 = new Vec2(), vec33 = new Vec2(), vec34 = new Vec2(), rv = new Vec2();
    private static final Rand rand = new Rand();
    public static final float sinScl = 1f;

    public static void drawSnow(float x, float y, float rad, float rot, Color color){
        Draw.color(color);
        for(int i = 0; i < 6; i++){
            float angle = 60 * i + rot;
            Drawf.tri(x + Angles.trnsx(angle, rad), y + Angles.trnsy(angle, rad), rad/3, rad, angle - 180);
            Drawf.tri(x + Angles.trnsx(angle, rad), y + Angles.trnsy(angle, rad), rad/3, rad/4, angle);
        }
        Draw.reset();
    }

    public static void circlePercent(float x, float y, float rad, float percent, float angle) {
//        Lines.swirl(x, y, rad, 360 * percent, angle);
        float p = Mathf.clamp(percent);

        int sides = Lines.circleVertices(rad);

        float space = 360.0F / (float)sides;
        float len = 2 * rad * Mathf.sinDeg(space / 2);
        float hstep = Lines.getStroke() / 2.0F / Mathf.cosDeg(space / 2.0F);
        float r1 = rad - hstep;
        float r2 = rad + hstep;

        int i;

        for(i = 0; i < sides * p - 1; ++i){
            float a = space * (float)i + angle;
            float cos = Mathf.cosDeg(a);
            float sin = Mathf.sinDeg(a);
            float cos2 = Mathf.cosDeg(a + space);
            float sin2 = Mathf.sinDeg(a + space);
            Fill.quad(x + r1 * cos, y + r1 * sin, x + r1 * cos2, y + r1 * sin2, x + r2 * cos2, y + r2 * sin2, x + r2 * cos, y + r2 * sin);
        }

        float a = space * i + angle;
        float cos = Mathf.cosDeg(a);
        float sin = Mathf.sinDeg(a);
        float cos2 = Mathf.cosDeg(a + space);
        float sin2 = Mathf.sinDeg(a + space);
        float f = sides * p - i;
        vec21.trns(a, 0, len * (f - 1));
        Fill.quad(x + r1 * cos, y + r1 * sin, x + r1 * cos2 + vec21.x, y + r1 * sin2 + vec21.y, x + r2 * cos2 + vec21.x, y + r2 * sin2 + vec21.y, x + r2 * cos, y + r2 * sin);
    }

    public static void circlePercentFlip(float x, float y, float rad, float in, float scl){
        float f = Mathf.cos(in % (scl * 3f), scl, 1.1f);
        circlePercent(x, y, rad, f > 0 ? f : -f, in + -90 * Mathf.sign(f));
    }

    public static void link(Buildingc from, Buildingc to, Color color){
        float
                sin = Mathf.absin(Time.time * sinScl, 6f, 1f),
                r1 = from.block().size / 2f * tilesize + sin,
                x1 = from.getX(), x2 = to.getX(), y1 = from.getY(), y2 = to.getY(),
                r2 = to.block().size / 2f * tilesize + sin;

        Draw.color(color);

        Lines.square(x2, y2, to.block().size * tilesize / 2f + 1.0f);

        Tmp.v1.trns(from.angleTo(to), r1);
        Tmp.v2.trns(to.angleTo(from), r2);
        int signs = (int)(from.dst(to) / tilesize);

        Lines.stroke(4, Pal.gray);
        Lines.dashLine(x1 + Tmp.v1.x, y1 + Tmp.v1.y, x2 + Tmp.v2.x, y2 + Tmp.v2.y, signs);
        Lines.stroke(2, color);
        Lines.dashLine(x1 + Tmp.v1.x, y1 + Tmp.v1.y, x2 + Tmp.v2.x, y2 + Tmp.v2.y, signs);

        Drawf.arrow(x1, y1, x2, y2, from.block().size * tilesize / 2f + sin, 4 + sin, color);

        Drawf.circles(x2, y2, r2, color);
    }

    public static float rotator_90(float in, float margin){
        return 90 * HIInterp.pow10.apply(Mathf.curve(in, margin, 1 - margin));
    }

    public static float cameraDstScl(float x, float y, float norDst){
        vec21.set(Core.camera.position);
        float dst = Mathf.dst(x, y, vec21.x, vec21.y);
        return 1 - Mathf.clamp(dst / norDst);
    }

    public static void tri(float x, float y, float width, float length, float angle){
        float wx = Angles.trnsx(angle + 90, width), wy = Angles.trnsy(angle + 90, width);
        Fill.tri(x + wx, y + wy, x - wx, y - wy, Angles.trnsx(angle, length) + x, Angles.trnsy(angle, length) + y);
    }

    public static void surround(long id, float x, float y, float rad, int num, float innerSize, float outerSize, float interp){
        Rand rand = HIFx.rand0;

        rand.setSeed(id);
        for(int i = 0; i < num; i++){
            float len = rad * rand.random(0.75f, 1.5f);
            vec21.trns(rand.random(360f) + rand.range(2f) * (1.5f - Mathf.curve(len, rad * 0.75f, rad * 1.5f)) * Time.time, len);
            float angle = vec21.angle();
            vec21.add(x, y);
            tri(vec21.x, vec21.y, (interp + 1) * outerSize + rand.random(0, outerSize / 8), outerSize * (Interp.exp5In.apply(interp) + 0.25f) / 2f, angle);
            tri(vec21.x, vec21.y, (interp + 1) / 2 * innerSize + rand.random(0, innerSize / 8), innerSize * (Interp.exp5In.apply(interp) + 0.5f), angle - 180);
        }
    }

    public static void randLenVectors(long seed, int amount, float length, float minLength, float angle, float range, Floatc2 cons){
        rand.setSeed(seed);
        for(int i = 0; i < amount; i++){
            vec21.trns(angle + rand.range(range), minLength  + rand.random(length));
            cons.get(vec21.x, vec21.y);
        }
    }

    public static float cycle(float in, float phaseOffset, float T){
        return (in + phaseOffset) % T / T;
    }

    public static void basicLaser(float x, float y, float x2, float y2, float stroke, float circleScl){
        Lines.stroke(stroke);
        Lines.line(x, y, x2, y2, false);
        Fill.circle(x, y, stroke * circleScl);
        Fill.circle(x2, y2, stroke * circleScl);
        Lines.stroke(1f);
    }

    public static void basicLaser(float x, float y, float x2, float y2, float stroke){
        basicLaser(x, y, x2, y2, stroke, 0.95f);
    }

    public static void randFadeLightningEffect(float x, float y, float range, float lightningPieceLength, Color color, boolean in){
        randFadeLightningEffectScl(x, y, range, 0.55f, 1.1f, lightningPieceLength, color, in);
    }

    public static void randFadeLightningEffectScl(float x, float y, float range, float sclMin, float sclMax, float lightningPieceLength, Color color, boolean in){
        vec21.rnd(range).scl(Mathf.random(sclMin, sclMax)).add(x, y);
        (in ? HIFx.chainLightningFadeReversed : HIFx.chainLightningFade).at(x, y, lightningPieceLength, color, vec21.cpy());
    }

    public static void construct(Building t, TextureRegion region, Color color1, Color color2, float rotation, float progress, float alpha, float time){
        construct(t, region, color1, color2, rotation, progress, alpha, time, t.block.size * tilesize - 4f);
    }

    public static void construct(Building t, TextureRegion region, Color color1, Color color2, float rotation, float progress, float alpha, float time, float size){
        Shaders.build.region = region;
        Shaders.build.progress = progress;
        Shaders.build.color.set(color1);
        Shaders.build.color.a = alpha;
        Shaders.build.time = -time / 20f;

        Draw.shader(Shaders.build);
        Draw.rect(region, t.x, t.y, rotation);
        Draw.shader();

        Draw.color(color2);
        Draw.alpha(alpha);

        Lines.lineAngleCenter(t.x + Mathf.sin(time, 20f, size / 2f), t.y, 90, size);

        Draw.reset();
    }

    public static void spinningCircle(int seed, float time, float x, float y, float radius, int spikes, float spikeDuration, float durationRnd, float spikeWidth, float spikeHeight, float pointOffset){
        spinningCircle(seed, time, time, x, y, radius, spikes, spikeDuration, durationRnd, spikeWidth, spikeHeight, pointOffset);
    }

    public static void spinningCircle(int seed, float angle, float time, float x, float y, float radius, int spikes, float spikeDuration, float durationRnd, float spikeWidth, float spikeHeight, float pointOffset){
        Fill.circle(x, y, radius);

        for(int i = 0; i < spikes; i++){
            float d = spikeDuration + Mathf.randomSeedRange(seed + i + spikes, durationRnd);
            float timeOffset = Mathf.randomSeed((seed + i) * 314L, 0f, d);
            int timeSeed = Mathf.floor((time + timeOffset) / d);
            float a = angle + Mathf.randomSeed(Math.max(timeSeed, 1) + ((i + seed) * 245L), 360f);
            float fin = ((time + timeOffset) % d) / d;
            float fslope = (0.5f - Math.abs(fin - 0.5f)) * 2f;
            vec31.trns(a + spikeWidth / 2f, radius).add(x, y);
            vec32.trns(a - spikeWidth / 2f, radius).add(x, y);
            vec33.trns(a + pointOffset, radius + spikeHeight * fslope).add(x, y);
            Fill.tri(vec31.x, vec31.y, vec32.x, vec32.y, vec33.x, vec33.y);
        }
    }
}
