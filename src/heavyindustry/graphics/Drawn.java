package heavyindustry.graphics;

import arc.*;
import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.gl.*;
import arc.math.*;
import arc.math.geom.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import arc.util.pooling.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.ui.*;
import heavyindustry.content.*;
import heavyindustry.math.*;
import mindustry.world.*;

import static mindustry.Vars.*;
import static heavyindustry.core.HeavyIndustryMod.*;

public final class Drawn {
    static final Vec2
            v1 = new Vec2(), v2 = new Vec2(), v3 = new Vec2(), v4 = new Vec2(), v5 = new Vec2(),
            v6 = new Vec2(), v7 = new Vec2(), v8 = new Vec2(), v9 = new Vec2(), v10 = new Vec2(),
            rv = new Vec2();
    static final Vec3
            v31 = new Vec3(), v32 = new Vec3(), v33 = new Vec3(), v34 = new Vec3(), v35 = new Vec3(),
            v36 = new Vec3(), v37 = new Vec3(), v38 = new Vec3(), v39 = new Vec3(), v310 = new Vec3();

    static final Color
            c1 = new Color(), c2 = new Color(), c3 = new Color(), c4 = new Color(), c5 = new Color(),
            c6 = new Color(), c7 = new Color(), c8 = new Color(), c9 = new Color(), c10 = new Color();

    static final Rand rand = new Rand();

    public static final int[] oneArr = {1};

    public static final float sinScl = 1f;

    /** Drawn should not be instantiated. */
    private Drawn() {}

    public static void drawSnow(float x, float y, float rad, float rot, Color color) {
        Draw.color(color);
        for (int i = 0; i < 6; i++) {
            float angle = 60 * i + rot;
            Drawf.tri(x + Angles.trnsx(angle, rad), y + Angles.trnsy(angle, rad), rad/3, rad, angle - 180);
            Drawf.tri(x + Angles.trnsx(angle, rad), y + Angles.trnsy(angle, rad), rad/3, rad/4, angle);
        }
        Draw.reset();
    }

    public static void circlePercent(float x, float y, float rad, float percent, float angle) {
        float p = Mathf.clamp(percent);

        int sides = Lines.circleVertices(rad);

        float space = 360f / (float) sides;
        float len = 2 * rad * Mathf.sinDeg(space / 2);
        float hstep = Lines.getStroke() / 2f / Mathf.cosDeg(space / 2f);
        float r1 = rad - hstep;
        float r2 = rad + hstep;

        int i;

        for (i = 0; i < sides * p - 1; ++i) {
            float a = space * (float) i + angle;
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
        v6.trns(a, 0, len * (f - 1));
        Fill.quad(x + r1 * cos, y + r1 * sin, x + r1 * cos2 + v6.x, y + r1 * sin2 + v6.y, x + r2 * cos2 + v6.x, y + r2 * sin2 + v6.y, x + r2 * cos, y + r2 * sin);
    }

    public static void circlePercentFlip(float x, float y, float rad, float in, float scl) {
        float f = Mathf.cos(in % (scl * 3f), scl, 1.1f);
        circlePercent(x, y, rad, f > 0 ? f : -f, in + -90 * Mathf.sign(f));
    }

    public static void link(Buildingc from, Buildingc to, Color color) {
        float
                sin = Mathf.absin(Time.time * sinScl, 6f, 1f),
                r1 = from.block().size / 2f * tilesize + sin,
                x1 = from.getX(), x2 = to.getX(), y1 = from.getY(), y2 = to.getY(),
                r2 = to.block().size / 2f * tilesize + sin;

        Draw.color(color);

        Lines.square(x2, y2, to.block().size * tilesize / 2f + 1f);

        Tmp.v1.trns(from.angleTo(to), r1);
        Tmp.v2.trns(to.angleTo(from), r2);
        int signs = (int) (from.dst(to) / tilesize);

        Lines.stroke(4, Pal.gray);
        Lines.dashLine(x1 + Tmp.v1.x, y1 + Tmp.v1.y, x2 + Tmp.v2.x, y2 + Tmp.v2.y, signs);
        Lines.stroke(2, color);
        Lines.dashLine(x1 + Tmp.v1.x, y1 + Tmp.v1.y, x2 + Tmp.v2.x, y2 + Tmp.v2.y, signs);

        Drawf.arrow(x1, y1, x2, y2, from.block().size * tilesize / 2f + sin, 4 + sin, color);

        Drawf.circles(x2, y2, r2, color);
    }

    public static float cameraDstScl(float x, float y, float norDst) {
        v6.set(Core.camera.position);
        float dst = Mathf.dst(x, y, v6.x, v6.y);
        return 1 - Mathf.clamp(dst / norDst);
    }

    public static void tri(float x, float y, float width, float length, float angle) {
        float wx = Angles.trnsx(angle + 90, width), wy = Angles.trnsy(angle + 90, width);
        Fill.tri(x + wx, y + wy, x - wx, y - wy, Angles.trnsx(angle, length) + x, Angles.trnsy(angle, length) + y);
    }

    public static void arrow(float x, float y, float width, float length, float backLength, float angle) {
        float wx = Angles.trnsx(angle + 90, width), wy = Angles.trnsy(angle + 90, width);
        float ox = Angles.trnsx(angle, backLength), oy = Angles.trnsy(angle, backLength);
        float cx = Angles.trnsx(angle, length) + x, cy = Angles.trnsy(angle, length) + y;
        Fill.tri(x + ox, y + oy, x - wx, y - wy, cx, cy);
        Fill.tri(x + wx, y + wy, x + ox, y + oy, cx, cy);
    }

    public static void surround(long id, float x, float y, float rad, int num, float innerSize, float outerSize, float interp) {
        Rand rand = HIFx.rand0;

        rand.setSeed(id);
        for (int i = 0; i < num; i++) {
            float len = rad * rand.random(0.75f, 1.5f);
            v6.trns(rand.random(360f) + rand.range(2f) * (1.5f - Mathf.curve(len, rad * 0.75f, rad * 1.5f)) * Time.time, len);
            float angle = v6.angle();
            v6.add(x, y);
            tri(v6.x, v6.y, (interp + 1) * outerSize + rand.random(0, outerSize / 8), outerSize * (Interp.exp5In.apply(interp) + 0.25f) / 2f, angle);
            tri(v6.x, v6.y, (interp + 1) / 2 * innerSize + rand.random(0, innerSize / 8), innerSize * (Interp.exp5In.apply(interp) + 0.5f), angle - 180);
        }
    }

    public static void randLenVectors(long seed, int amount, float length, float minLength, float angle, float range, Floatc2 cons) {
        rand.setSeed(seed);
        for (int i = 0; i < amount; i++) {
            v6.trns(angle + rand.range(range), minLength  + rand.random(length));
            cons.get(v6.x, v6.y);
        }
    }

    public static float rotator_90(float in, float margin) {
        return 90 * HIInterp.pow10.apply(Mathf.curve(in, margin, 1 - margin));
    }

    public static float rotator_90() {
        return 90 * Interp.pow5.apply(Mathf.curve(cycle_100(), 0.15f, 0.85f));
    }

    public static float rotator_120(float in , float margin) {
        return 120 * HIInterp.pow10.apply(Mathf.curve(in, margin, 1 - margin));
    }

    public static float rotator_180() {
        return 180 * Interp.pow5.apply(Mathf.curve(cycle_100(), 0.15f, 0.85f));
    }

    public static float rotator_360() {
        return 360 * Interp.pow5.apply(Mathf.curve(cycle(0, 270), 0.15f, 0.85f));
    }

    /** @return AN interpolation in (0, 1) */
    public static float cycle(float phaseOffset, float T) {
        return (Time.time + phaseOffset) % T / T;
    }

    public static float cycle(float in, float phaseOffset, float T) {
        return (in + phaseOffset) % T / T;
    }

    public static float cycle_100() {
        return Time.time % 100 / 100;
    }

    public static void basicLaser(float x, float y, float x2, float y2, float stroke, float circleScl) {
        Lines.stroke(stroke);
        Lines.line(x, y, x2, y2, false);
        Fill.circle(x, y, stroke * circleScl);
        Fill.circle(x2, y2, stroke * circleScl);
        Lines.stroke(1f);
    }

    public static void basicLaser(float x, float y, float x2, float y2, float stroke) {
        basicLaser(x, y, x2, y2, stroke, 0.95f);
    }

    public static void randFadeLightningEffect(float x, float y, float range, float lightningPieceLength, Color color, boolean in) {
        randFadeLightningEffectScl(x, y, range, 0.55f, 1.1f, lightningPieceLength, color, in);
    }

    public static void randFadeLightningEffectScl(float x, float y, float range, float sclMin, float sclMax, float lightningPieceLength, Color color, boolean in) {
        v6.rnd(range).scl(Mathf.random(sclMin, sclMax)).add(x, y);
        (in ? HIFx.chainLightningFadeReversed : HIFx.chainLightningFade).at(x, y, lightningPieceLength, color, v6.cpy());
    }

    public static void teleportUnitNet(Unit before, float x, float y, float angle, Player player) {
        if (net.active() || headless) {
            if (player != null) {
                player.set(x, y);
                player.snapInterpolation();
                player.snapSync();
                player.lastUpdated = player.updateSpacing = 0;
            }
            before.set(x, y);
            before.snapInterpolation();
            before.snapSync();
            before.updateSpacing = 0;
            before.lastUpdated = 0;
        } else {
            before.set(x, y);
        }
        before.rotation = angle;
    }

    public static void construct(Building t, TextureRegion region, Color color1, Color color2, float rotation, float progress, float alpha, float time) {
        construct(t, region, color1, color2, rotation, progress, alpha, time, t.block.size * tilesize - 4f);
    }

    public static void construct(Building t, TextureRegion region, Color color1, Color color2, float rotation, float progress, float alpha, float time, float size) {
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

    public static void selected(Buildingc tile, Color color) {
        Drawf.selected(tile.tile(), color);
    }

    public static void posSquareLink(Color color, float stroke, float size, boolean drawBottom, float x, float y, float x2, float y2) {
        posSquareLink(color, stroke, size, drawBottom, v6.set(x, y), v6.set(x2, y2));
    }

    public static void posSquareLink(Color color, float stroke, float size, boolean drawBottom, Position from, Position to) {
        posSquareLinkArr(color, stroke, size, drawBottom, false, from, to);
    }

    public static void posSquareLinkArr(Color color, float stroke, float size, boolean drawBottom, boolean linkLine, Position... pos) {
        if (pos.length < 2 || (!linkLine && pos[0] == null)) return;

        for (int c : drawBottom ? Mathf.signs : oneArr) {
            for (int i = 1; i < pos.length; i++) {
                if (pos[i] == null) continue;
                Position p1 = pos[i - 1], p2 = pos[i];
                Lines.stroke(stroke + 1 - c, c == 1 ? color : Pal.gray);
                if (linkLine) {
                    if (p1 == null) continue;
                    Lines.line(p2.getX(), p2.getY(), p1.getX(), p1.getY());
                } else {
                    Lines.line(p2.getX(), p2.getY(), pos[0].getX(), pos[0].getY());
                }
                Draw.reset();
            }

            for (Position p : pos) {
                if (p == null) continue;
                Draw.color(c == 1 ? color : Pal.gray);
                Fill.square(p.getX(), p.getY(), size + 1 -c / 1.5f, 45);
                Draw.reset();
            }
        }
    }

    public static void drawText(String text, float x, float y) {
        drawText(text, x, y, 1f);
    }

    public static void drawText(String text, float x, float y, float size) {
        Font font = Fonts.outline;
        GlyphLayout layout = Pools.obtain(GlyphLayout.class, GlyphLayout::new);
        boolean ints = font.usesIntegerPositions();
        font.setUseIntegerPositions(false);
        font.getData().setScale(size / 6f / Scl.scl(1f));

        layout.setText(font, text);
        font.draw(text, x, y, Align.center);

        font.setUseIntegerPositions(ints);
        font.setColor(Color.white);
        font.getData().setScale(1f);
        Draw.reset();
        Pools.free(layout);
    }

    public static void drawConnected(float x, float y, float size, Color color) {
        Draw.reset();
        float sin = Mathf.absin(Time.time * sinScl, 8f, 1.25f);

        for (int i = 0; i < 4; i++) {
            float length = size / 2f + 3 + sin;
            Tmp.v1.trns(i * 90, -length);
            Draw.color(Pal.gray);
            Draw.rect(modName + "-linked-arrow-back", x + Tmp.v1.x, y + Tmp.v1.y, i * 90);
            Draw.color(color);
            Draw.rect(modName + "-linked-arrow", x + Tmp.v1.x, y + Tmp.v1.y, i * 90);
        }
        Draw.reset();
    }

    public static void overlayText(String text, float x, float y, float offset, Color color, boolean underline) {
        overlayText(Fonts.outline, text, x, y, offset, 1, 0.25f, color, underline, false);
    }

    public static void overlayText(Font font, String text, float x, float y, float offset, float offsetScl, float size, Color color, boolean underline, boolean align) {
        GlyphLayout layout = Pools.obtain(GlyphLayout.class, GlyphLayout::new);
        boolean ints = font.usesIntegerPositions();
        font.setUseIntegerPositions(false);
        font.getData().setScale(size / Scl.scl(1f));
        layout.setText(font, text);
        font.setColor(color);

        float dy = offset + 3f;
        font.draw(text, x, y + layout.height / (align ? 2 : 1) + (dy + 1f) * offsetScl, 1);
        --dy;

        if (underline) {
            Lines.stroke(2f, Color.darkGray);
            Lines.line(x - layout.width / 2f - 2f, dy + y, x + layout.width / 2f + 1.5f, dy + y);
            Lines.stroke(1f, color);
            Lines.line(x - layout.width / 2f - 2f, dy + y, x + layout.width / 2f + 1.5f, dy + y);
            Draw.color();
        }

        font.setUseIntegerPositions(ints);
        font.setColor(Color.white);
        font.getData().setScale(1f);
        Draw.reset();
        Pools.free(layout);
    }

    public static void spinningCircle(int seed, float time, float x, float y, float radius, int spikes, float spikeDuration, float durationRnd, float spikeWidth, float spikeHeight, float pointOffset) {
        spinningCircle(seed, time, time, x, y, radius, spikes, spikeDuration, durationRnd, spikeWidth, spikeHeight, pointOffset);
    }

    public static void spinningCircle(int seed, float angle, float time, float x, float y, float radius, int spikes, float spikeDuration, float durationRnd, float spikeWidth, float spikeHeight, float pointOffset) {
        Fill.circle(x, y, radius);

        for (int i = 0; i < spikes; i++) {
            float d = spikeDuration + Mathf.randomSeedRange(seed + i + spikes, durationRnd);
            float timeOffset = Mathf.randomSeed((seed + i) * 314l, 0f, d);
            int timeSeed = Mathf.floor((time + timeOffset) / d);
            float a = angle + Mathf.randomSeed(Math.max(timeSeed, 1) + ((i + seed) * 245l), 360f);
            float fin = ((time + timeOffset) % d) / d;
            float fslope = (0.5f - Math.abs(fin - 0.5f)) * 2f;
            v7.trns(a + spikeWidth / 2f, radius).add(x, y);
            v8.trns(a - spikeWidth / 2f, radius).add(x, y);
            v9.trns(a + pointOffset, radius + spikeHeight * fslope).add(x, y);
            Fill.tri(v7.x, v7.y, v8.x, v8.y, v9.x, v9.y);
        }
    }

    private static void drawSpinSprite(TextureRegion[] regions, float x, float y, float w, float h, float r) {
        float ar = Mathf.mod(r, 360f);

        Draw.alpha(1f);
        if (ar > 45f && ar <= 225f) {
            Draw.rect(regions[0], x, y, w, h * -1f, r);
        } else {
            Draw.rect(regions[0], x, y, w, h, r);
        }

        if (ar >= 180 && ar < 270) { //Bottom Left
            float a = Interp.slope.apply(Mathf.curve(ar, 180, 270));
            Draw.alpha(a);
            Draw.rect(regions[1], x, y, w, h, r);
        } else if (ar < 90 && ar >= 0) { //Top Right
            float a = Interp.slope.apply(Mathf.curve(ar, 0, 90));
            Draw.alpha(a);
            Draw.rect(regions[2], x, y, w, h, r);
        }
        Draw.alpha(1f);
    }

    /** Draws a sprite that should be light-wise correct. Provided sprites must be similar in shape and face towards the right. */
    public static void spinSprite(TextureRegion[] regions, float x, float y, float w, float h, float r, float alpha) {
        if (alpha < 0.99f) {
            FrameBuffer buffer = renderer.effectBuffer;
            float z = Draw.z();
            float xScl = Draw.xscl, yScl = Draw.yscl;
            Draw.draw(z, () -> {
                buffer.begin(Color.clear);
                Draw.scl(xScl, yScl);
                drawSpinSprite(regions, x, y, w, h, r);
                buffer.end();

                HIShaders.alphaShader.alpha = alpha;
                buffer.blit(HIShaders.alphaShader);
            });
        }else{
            drawSpinSprite(regions, x, y, w, h, r);
        }
    }

    /** Draws a sprite that should be light-wise correct. Provided sprites must be similar in shape and face towards the right. */
    public static void spinSprite(TextureRegion[] regions, float x, float y, float w, float h, float r) {
        spinSprite(regions, x, y, w, h, r, 1f);
    }


    /** Draws a sprite that should be light-wise correct. Provided sprites must be similar in shape and face towards the right. */
    public static void spinSprite(TextureRegion[] regions, float x, float y, float r, float alpha) {
        spinSprite(regions, x, y, regions[0].width / 4f, regions[0].height / 4f, r, alpha);
    }

    /** Draws a sprite that should be light-wise correct. Provided sprites must be similar in shape and face towards the right. */
    public static void spinSprite(TextureRegion[] regions, float x, float y, float r) {
        spinSprite(regions, x, y, regions[0].width / 4f, regions[0].height / 4f, r);
    }
}
