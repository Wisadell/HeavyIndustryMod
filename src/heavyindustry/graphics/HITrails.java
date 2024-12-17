package heavyindustry.graphics;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.gl.*;
import arc.math.*;
import arc.struct.*;
import arc.util.*;
import arc.util.pooling.Pool.*;
import arc.util.pooling.*;
import mindustry.gen.*;
import mindustry.graphics.*;

import static arc.Core.*;
import static heavyindustry.core.HeavyIndustryMod.*;
import static heavyindustry.util.StructUtils.*;

public final class HITrails {
    private HITrails() {}

    public static TexturedTrail singlePhantasmal(String name, int length, TrailAttrib... attributes) {
        return new TexturedTrail(length, name, attributes) {{
            blend = Blending.additive;
            fadeInterp = Interp.pow2In;
            sideFadeInterp = Interp.pow3In;
            mixInterp = Interp.pow10In;
            gradientInterp = Interp.pow2Out;
            fadeColor = new Color(0.3f, 0.5f, 1f);
            shrink = 0f;
            fadeAlpha = 1f;
            mixAlpha = 1f;
        }};
    }

    /** Taken from Project Unity and modified a bit. */
    public static class CritTrail extends Trail {
        protected final FloatSeq points;
        protected float lastX = -1, lastY = -1, counter = 0f;

        public CritTrail(int length) {
            super(length);
            points = new FloatSeq(length * 4);
        }

        @Override
        public CritTrail copy() {
            CritTrail out = new CritTrail(length);
            out.points.addAll(points);
            out.lastX = lastX;
            out.lastY = lastY;
            return out;
        }

        @Override
        public int size() {
            return points.size / 4;
        }

        @Override
        public void drawCap(Color color, float width) {
            if (points.size > 0) {
                Draw.color(color);
                float[] items = points.items;
                int i = points.size - 4;
                float x1 = items[i], y1 = items[i + 1], w1 = items[i + 2], ai = items[i + 3], w = w1 * width / ((float) points.size / 4) * i / 4f * 2f;
                if (w1 <= 0.001f) return;
                Draw.rect("hcircle", x1, y1, w, w, -Mathf.radDeg * ai + 180f);
                Draw.reset();
            }
        }

        @Override
        public void draw(Color color, float width) {
            Draw.color(color);
            float[] items = points.items;

            for (int i = 0; i < points.size - 4; i += 4) {
                float x1 = items[i], y1 = items[i + 1], w1 = items[i + 2], a1 = items[i + 3],
                        x2 = items[i + 4], y2 = items[i + 5], w2 = items[i + 6], a2 = items[i + 7];
                float size = width / ((float) points.size / 4);
                if (w1 <= 0.001f || w2 <= 0.001f) continue;

                float cx = Mathf.sin(a1) * i / 4f * size * w1, cy = Mathf.cos(a1) * i / 4f * size * w1,
                        nx = Mathf.sin(a2) * (i / 4f + 1) * size * w2, ny = Mathf.cos(a2) * (i / 4f + 1) * size * w2;
                Fill.quad(x1 - cx, y1 - cy, x1 + cx, y1 + cy, x2 + nx, y2 + ny, x2 - nx, y2 - ny);
            }

            Draw.reset();
        }

        /** Removes the last point from the trail at intervals. */
        @Override
        public void shorten() {
            if ((counter += Time.delta) >= 0.99f) {
                if (points.size >= 4) {
                    points.removeRange(0, 3);
                }

                counter = 0f;
            }
        }

        /** Adds a new point to the trail at intervals. */
        @Override
        public void update(float x, float y) {
            updateRot(x, y, Angles.angle(x, y, lastX, lastY));
        }

        /** Adds a new point with a width multiplier to the trail at intervals. */
        @Override
        public void update(float x, float y, float width) {
            update(x, y, width, Angles.angle(x, y, lastX, lastY));
        }

        /** Adds a new point with a specific rotation to the trail at intervals. */
        public void updateRot(float x, float y, float rotation) {
            update(x, y, 1f, rotation);
        }

        /** Adds a new point with a width multiplier and specific rotation to the trail at intervals. */
        public void update(float x, float y, float width, float rotation) {
            if ((counter += Time.delta) >= 0.99f) {
                if (points.size > length * 4) {
                    points.removeRange(0, 3);
                }

                points.add(x, y, width, -rotation * Mathf.degRad);

                counter = 0f;

                lastX = x;
                lastY = y;
            }
        }
    }

    public static class PoolableTrail extends Trail implements Poolable {
        private PoolableTrail(int length) {
            super(length);
        }

        public static PoolableTrail copy(int length) {
            return Pools.obtain(PoolableTrail.class, () -> new PoolableTrail(length));
        }

        @Override
        public PoolableTrail copy() {
            PoolableTrail out = new PoolableTrail(length);
            out.points.addAll(points);
            out.lastX = lastX;
            out.lastY = lastY;
            return out;
        }

        @Override
        public void reset() {
            clear();
        }
    }

    public static class ShaderTrail extends Trail {
        public Shader shader;

        public ShaderTrail(int length, Shader shader) {
            super(length);
            this.shader = shader;
        }

        @Override
        public void draw(Color color, float width) {
            float[] items = points.items;

            for (int i = 0; i < points.size; i += 3) {
                float x1 = items[i], y1 = items[i + 1], w1 = items[i + 2];
                float x2, y2, w2;

                //last position is always lastX/Y/W
                if (i < points.size - 3) {
                    x2 = items[i + 3];
                    y2 = items[i + 4];
                    w2 = items[i + 5];
                } else {
                    x2 = lastX;
                    y2 = lastY;
                    w2 = lastW;
                }

                float z2 = -Angles.angleRad(x1, y1, x2, y2);
                //end of the trail (i = 0) has the same angle as the next.
                if (w1 <= 0.001f || w2 <= 0.001f) continue;

                Draw.blit(shader);

                lastAngle = z2;
            }

            Draw.reset();
        }

        @Override
        public ShaderTrail copy() {
            ShaderTrail out = new ShaderTrail(length, shader);
            out.points.addAll(points);
            out.lastX = lastX;
            out.lastY = lastY;
            return out;
        }
    }

    public static class TexturedTrail extends BaseTrail {
        private static final float[] vertices = new float[24];
        private static final Color tmp = new Color();

        public final String name;
        public TextureRegion region, capRegion;

        public float shrink = 1f;
        public float fadeAlpha = 0f;
        public float mixAlpha = 0.5f;
        public Color fadeColor = Color.white;
        public Interp gradientInterp = Interp.linear;
        public Interp fadeInterp = Interp.pow2In;
        public Interp sideFadeInterp = Interp.pow3In;
        public Interp mixInterp = Interp.pow5In;

        public TexturedTrail(int length, String name, TrailAttrib... attributes) {
            super(length, attributes);
            this.name = name;
        }

        public TexturedTrail(int length, String name, TrailRotation rot, TrailAttrib... attributes) {
            super(length, rot, attributes);
            this.name = name;
        }

        @Override
        protected TexturedTrail copyBlank() {
            return new TexturedTrail(length, name, rot, copyArray(attributes, TrailAttrib::copy));
        }

        @Override
        protected void copyAssign(BaseTrail out) {
            super.copyAssign(out);
            if (out instanceof TexturedTrail o) {
                o.region = region;
                o.capRegion = capRegion;

                o.shrink = shrink;
                o.fadeAlpha = fadeAlpha;
                o.mixAlpha = mixAlpha;
                o.fadeColor = fadeColor;
                o.gradientInterp = gradientInterp;
                o.fadeInterp = fadeInterp;
                o.sideFadeInterp = sideFadeInterp;
                o.mixInterp = mixInterp;
            }
        }

        @Override
        protected void drawSegment(Color color, float width, float[] points, int len, int offset) {
            if (region == null) region = atlas.find(name, name("trail"));

            int stride = this.stride;
            float
                    u = region.u, v = region.v2, u2 = region.u2, v2 = region.v, uh = Mathf.lerp(u, u2, 0.5f),
                    x1 = x(points, offset), y1 = y(points, offset), w1 = width(points, offset), r1 = angle(points, offset), p1 = progress(points, offset),
                    x2, y2, w2, r2, p2;

            if (offset < len - stride) {
                int next = offset + stride;
                x2 = x(points, next);
                y2 = y(points, next);
                w2 = width(points, next);
                r2 = angle(points, next);
                p2 = progress(points, next);

                //TODO Should probably interpolate too if it's being shortened.
                if (offset == 0 && len == length * stride) {
                    x1 = Mathf.lerp(x1, x2, counter);
                    y1 = Mathf.lerp(y1, y2, counter);
                    w1 = Mathf.lerp(w1, w2, counter);
                    r1 = Mathf.slerpRad(r1, r2, counter);
                    // Don't interpolate `p1` into `p2`, as it is already handled.
                }
            } else {
                x2 = lastX;
                y2 = lastY;
                w2 = lastW;
                r2 = lastAngle;
                p2 = (float) len / stride / length;
            }

            float
                    fs1 = Mathf.map(p1, 1f - shrink, 1f) * width * w1,
                    fs2 = Mathf.map(p2, 1f - shrink, 1f) * width * w2,

                    cx = Mathf.sin(r1) * fs1, cy = Mathf.cos(r1) * fs1,
                    nx = Mathf.sin(r2) * fs2, ny = Mathf.cos(r2) * fs2,

                    mv1 = Mathf.lerp(v, v2, p1), mv2 = Mathf.lerp(v, v2, p2),
                    cv1 = p1 * fadeAlpha + (1f - fadeAlpha), cv2 = p2 * fadeAlpha + (1f - fadeAlpha),
                    col1 = tmp.set(Draw.getColor()).lerp(fadeColor, gradientInterp.apply(1f - p1)).a(fadeInterp.apply(cv1)).clamp().toFloatBits(),
                    col1h = tmp.set(Draw.getColor()).lerp(fadeColor, gradientInterp.apply(1f - p1)).a(sideFadeInterp.apply(cv1)).clamp().toFloatBits(),
                    col2 = tmp.set(Draw.getColor()).lerp(fadeColor, gradientInterp.apply(1f - p2)).a(fadeInterp.apply(cv2)).clamp().toFloatBits(),
                    col2h = tmp.set(Draw.getColor()).lerp(fadeColor, gradientInterp.apply(1f - p2)).a(sideFadeInterp.apply(cv2)).clamp().toFloatBits(),
                    mix1 = tmp.set(color).a(mixInterp.apply(p1 * mixAlpha)).clamp().toFloatBits(),
                    mix2 = tmp.set(color).a(mixInterp.apply(p2 * mixAlpha)).clamp().toFloatBits();

            vertices[0] = x1 - cx;
            vertices[1] = y1 - cy;
            vertices[2] = col1h;
            vertices[3] = u;
            vertices[4] = mv1;
            vertices[5] = mix1;

            vertices[6] = x1;
            vertices[7] = y1;
            vertices[8] = col1;
            vertices[9] = uh;
            vertices[10] = mv1;
            vertices[11] = mix1;

            vertices[12] = x2;
            vertices[13] = y2;
            vertices[14] = col2;
            vertices[15] = uh;
            vertices[16] = mv2;
            vertices[17] = mix2;

            vertices[18] = x2 - nx;
            vertices[19] = y2 - ny;
            vertices[20] = col2h;
            vertices[21] = u;
            vertices[22] = mv2;
            vertices[23] = mix2;

            Draw.vert(region.texture, vertices, 0, 24);

            vertices[0] = x1 + cx;
            vertices[1] = y1 + cy;
            vertices[2] = col1h;
            vertices[3] = u2;
            vertices[4] = mv1;
            vertices[5] = mix1;

            vertices[6] = x1;
            vertices[7] = y1;
            vertices[8] = col1;
            vertices[9] = uh;
            vertices[10] = mv1;
            vertices[11] = mix1;

            vertices[12] = x2;
            vertices[13] = y2;
            vertices[14] = col2;
            vertices[15] = uh;
            vertices[16] = mv2;
            vertices[17] = mix2;

            vertices[18] = x2 + nx;
            vertices[19] = y2 + ny;
            vertices[20] = col2h;
            vertices[21] = u2;
            vertices[22] = mv2;
            vertices[23] = mix2;

            Draw.vert(region.texture, vertices, 0, 24);
        }

        @Override
        protected void forceDrawCap(Color color, float width) {
            if (capRegion == null) capRegion = atlas.find(name + "-cap", name("trail-cap"));

            int len = points.size;
            float
                    rv = (float) len / stride / length,
                    alpha = rv * fadeAlpha + (1f - fadeAlpha),
                    w = Mathf.map(rv, 1f - shrink, 1f) * width * lastW * 2f,
                    h = ((float) capRegion.height / capRegion.width) * w,

                    angle = unconvRotation(lastAngle) - 90f,
                    u = capRegion.u, v = capRegion.v2, u2 = capRegion.u2, v2 = capRegion.v, uh = Mathf.lerp(u, u2, 0.5f),
                    cx = Mathf.cosDeg(angle) * w / 2f, cy = Mathf.sinDeg(angle) * w / 2f,
                    x1 = lastX, y1 = lastY,
                    x2 = lastX + Mathf.cosDeg(angle + 90f) * h, y2 = lastY + Mathf.sinDeg(angle + 90f) * h,

                    col1 = tmp.set(Draw.getColor()).lerp(fadeColor, gradientInterp.apply(1f - rv)).a(fadeInterp.apply(alpha)).clamp().toFloatBits(),
                    col1h = tmp.set(Draw.getColor()).lerp(fadeColor, gradientInterp.apply(1f - rv)).a(sideFadeInterp.apply(alpha)).clamp().toFloatBits(),
                    col2 = tmp.set(Draw.getColor()).lerp(fadeColor, gradientInterp.apply(1f - rv)).a(fadeInterp.apply(alpha)).clamp().toFloatBits(),
                    col2h = tmp.set(Draw.getColor()).lerp(fadeColor, gradientInterp.apply(1f - rv)).a(sideFadeInterp.apply(alpha)).clamp().toFloatBits(),
                    mix1 = tmp.set(color).a(mixInterp.apply(rv * mixAlpha)).clamp().toFloatBits(),
                    mix2 = tmp.set(color).a(mixInterp.apply(rv * mixAlpha)).clamp().toFloatBits();

            vertices[0] = x1 - cx;
            vertices[1] = y1 - cy;
            vertices[2] = col1h;
            vertices[3] = u;
            vertices[4] = v;
            vertices[5] = mix1;

            vertices[6] = x1;
            vertices[7] = y1;
            vertices[8] = col1;
            vertices[9] = uh;
            vertices[10] = v;
            vertices[11] = mix1;

            vertices[12] = x2;
            vertices[13] = y2;
            vertices[14] = col2;
            vertices[15] = uh;
            vertices[16] = v2;
            vertices[17] = mix2;

            vertices[18] = x2 - cx;
            vertices[19] = y2 - cy;
            vertices[20] = col2h;
            vertices[21] = u;
            vertices[22] = v2;
            vertices[23] = mix2;

            Draw.vert(capRegion.texture, vertices, 0, 24);

            vertices[0] = x1 + cx;
            vertices[1] = y1 + cy;
            vertices[2] = col1h;
            vertices[3] = u2;
            vertices[4] = v;
            vertices[5] = mix1;

            vertices[6] = x1;
            vertices[7] = y1;
            vertices[8] = col1;
            vertices[9] = uh;
            vertices[10] = v;
            vertices[11] = mix1;

            vertices[12] = x2;
            vertices[13] = y2;
            vertices[14] = col2;
            vertices[15] = uh;
            vertices[16] = v2;
            vertices[17] = mix2;

            vertices[18] = x2 + cx;
            vertices[19] = y2 + cy;
            vertices[20] = col2h;
            vertices[21] = u2;
            vertices[22] = v2;
            vertices[23] = mix2;

            Draw.vert(capRegion.texture, vertices, 0, 24);
        }
    }

    public static abstract class BaseTrail extends Trail {
        public final TrailAttrib[] attributes;
        public final int stride;
        public TrailRotation rot;

        public boolean forceCap;
        public float baseWidth = 1f;
        public Blending blend = Blending.normal;

        protected float headX = Float.NaN, headY = Float.NaN, headW = Float.NaN, headAngle = Float.NaN;

        public BaseTrail(int length, TrailAttrib... attributes) {
            this(length, BaseTrail::defRotation, attributes);
        }

        public BaseTrail(int length, TrailRotation rot, TrailAttrib... attributes) {
            super(length);
            this.attributes = attributes;
            this.rot = rot;

            stride = baseStride() + sumi(attributes, t -> t.count);
            points.items = new float[length * stride];

            lastX = lastY = lastW = lastAngle = Float.NaN;
        }

        public static float defRotation(BaseTrail trail, float lastX, float lastY, float lastAngle, float x, float y) {
            return Mathf.zero(Mathf.dst2(x, y, lastX, lastY), 0.001f) ? lastAngle : -Angles.angleRad(x, y, lastX, lastY);
        }

        public static TrailRotation entRotation(Rotc unit) {
            return (t, lastX, lastY, lastAngle, x, y) -> !unit.isAdded() ? defRotation(t, lastX, lastY, lastAngle, x, y) : convRotation(unit.rotation());
        }

        public static float convRotation(float degrees) {
            return (degrees - 180f) * -Mathf.degRad;
        }

        public static float unconvRotation(float angle) {
            return -Mathf.radDeg * angle + 180f;
        }

        public int baseStride() {
            // x, y, width, angle, progress.
            return 2 + 1 + 1 + 1;
        }

        @Override
        public BaseTrail copy() {
            var out = copyBlank();
            copyAssign(out);

            return out;
        }

        protected abstract BaseTrail copyBlank();

        protected void copyAssign(BaseTrail out) {
            // `Trail` fields.
            out.points.addAll(points);
            out.lastX = lastX;
            out.lastY = lastY;
            out.lastW = lastW;
            out.lastAngle = lastAngle;
            out.counter = counter;

            // `CTrail` fields.
            out.forceCap = forceCap;
            out.baseWidth = baseWidth;
            out.blend = blend;

            out.headX = headX;
            out.headY = headY;
            out.headW = headW;
            out.headAngle = headAngle;
        }

        @Override
        public int size() {
            return points.size / stride;
        }

        @Override
        public void draw(Color color, float width) {
            int len = points.size;
            if (len == 0) return;

            width *= baseWidth;

            Draw.blend(blend);
            if (forceCap) forceDrawCap(color, width);

            var items = points.items;
            for (int i = 0, stride = this.stride; i < len; i += stride) drawSegment(color, width, items, len, i);
            Draw.blend();
        }

        @Override
        public void drawCap(Color color, float width) {
            if (!forceCap && points.size > 0) {
                Draw.blend(blend);
                forceDrawCap(color, width * baseWidth);
                Draw.blend();
            }
        }

        protected abstract void drawSegment(Color color, float width, float[] points, int len, int offset);

        protected abstract void forceDrawCap(Color color, float width);

        @Override
        public void shorten() {
            int count = (int) (counter += Time.delta), stride = this.stride;
            counter -= count;

            if (count > 0 && points.size >= stride) points.removeRange(0, Math.min(count * stride, points.size) - 1);

            var items = points.items;
            for (int i = 0, len = points.size; i < len; i += stride) {
                int offset = i;
                eachAttrib((attrib, off) -> attrib.update(this, items, offset, off));
            }

            calculateProgress();
        }

        @Override
        public void update(float x, float y, float width) {
            if (Float.isNaN(lastX)) lastX = headX = x;
            if (Float.isNaN(lastY)) lastY = headY = y;
            if (Float.isNaN(lastW)) lastW = headW = width;

            int len = points.size, stride = this.stride;
            var items = points.items;

            // May be NaN if this is the first `update(x, y, width)`, since there's only one point. That's okay though, since
            // in most cases nothing can be meaningfully drawn anyway. In case where it changed from NaN to valid, update all
            // vertices with invalid angles.
            float angle = rot.get(this, lastX, lastY, lastAngle, x, y);
            if (!Float.isNaN(angle) && Float.isNaN(lastAngle)) {
                lastAngle = headAngle = angle;
                for (int i = 0; i < len && Float.isNaN(angle(items, i)); i += stride) {
                    angle(items, i, angle);
                }
            }

            int count = (int) (counter += Time.delta);
            counter -= count;

            if (count > 0) {
                if (len > 0) {
                    int prev = len - stride;
                    float f = counter / (count + 1f);

                    headX = Mathf.lerp(x, x(items, prev), f);
                    headY = Mathf.lerp(y, y(items, prev), f);
                    headW = Mathf.lerp(width, width(items, prev), f);
                    headAngle = Mathf.slerpRad(angle, angle(items, prev), f);
                } else {
                    headX = x;
                    headY = y;
                    headW = width;
                    headAngle = angle;
                }

                int added = count * stride;
                if (len > length * stride - added) {
                    points.removeRange(0, added - 1);
                    len = points.size;
                }

                if (count > 1) {
                    if (len > 0) {
                        int prev = len - stride;
                        float fromX = x(items, prev), fromY = y(items, prev), fromW = width(items, prev), fromAngle = angle(items, prev);

                        for (int i = 0; i < count; i++) {
                            float f = (i + 1f) / count;

                            point(Mathf.lerp(fromX, headX, f), Mathf.lerp(fromY, headY, f), Mathf.lerp(fromW, headW, f), Mathf.slerpRad(fromAngle, headAngle, f), items, len);
                            len += stride;
                        }
                    } else {
                        for (int i = 0; i < count; i++) {
                            point(headX, headY, headW, headAngle, items, len);
                            len += stride;
                        }
                    }
                } else {
                    point(headX, headY, headW, headAngle, items, len);
                    len += stride;
                }

                points.size = len;
            }

            for (int i = 0; i < len; i += stride) {
                int offset = i;
                eachAttrib((attrib, off) -> attrib.update(this, items, offset, off));
            }

            eachAttrib((attrib, off) -> attrib.postUpdate(this, off));

            lastX = x;
            lastY = y;
            lastW = width;
            lastAngle = angle;
            calculateProgress();
        }

        public void calculateProgress() {
            int len = points.size;
            if (len == 0) return;

            int stride = this.stride;
            var items = points.items;

            float max = 0f;
            for (int i = 0; i < len; i += stride) {
                float x1 = x(items, i), y1 = y(items, i), x2, y2;
                if (i < len - stride) {
                    x2 = x(items, i + stride);
                    y2 = y(items, i + stride);
                } else {
                    x2 = lastX;
                    y2 = lastY;
                }

                //TODO Should probably interpolate too if it's being shortened.
                if (i == 0 && len == length * stride) {
                    x1 = Mathf.lerp(x1, x2, counter);
                    y1 = Mathf.lerp(y1, y2, counter);
                }

                float dst = Mathf.dst(x1, y1, x2, y2);

                progress(items, i, max);
                max += dst;
            }

            float frac = (float) len / stride / length;
            for (int i = 0; i < len; i += stride) {
                progress(items, i, (progress(items, i) / max) * frac);
            }
        }

        public void eachAttrib(AttribCons cons) {
            for (int i = 0, off = baseStride(); i < attributes.length; i++) {
                var attrib = attributes[i];
                cons.get(attrib, off);

                off += attrib.count;
            }
        }

        public void point(float x, float y, float width, float angle, float[] points, int offset) {
            basePoint(x, y, width, angle, points, offset);
            eachAttrib((attrib, off) -> attrib.point(this, x, y, width, angle, points, offset, off));
        }

        public void basePoint(float x, float y, float width, float angle, float[] points, int offset) {
            points[offset] = x;
            points[offset + 1] = y;
            points[offset + 2] = width;
            points[offset + 3] = angle;
            points[offset + 4] = 0f;
        }

        public float x(float[] points, int offset) {
            return points[offset];
        }

        public void x(float[] points, int offset, float x) {
            points[offset] = x;
        }

        public float y(float[] points, int offset) {
            return points[offset + 1];
        }

        public void y(float[] points, int offset, float y) {
            points[offset + 1] = y;
        }

        public float width(float[] points, int offset) {
            return points[offset + 2];
        }

        public void width(float[] points, int offset, float width) {
            points[offset + 2] = width;
        }

        public float angle(float[] points, int offset) {
            return points[offset + 3];
        }

        public void angle(float[] points, int offset, float angle) {
            points[offset + 3] = angle;
        }

        public float progress(float[] points, int offset) {
            return points[offset + 4];
        }

        public void progress(float[] points, int offset, float progress) {
            points[offset + 4] = progress;
        }

        public interface TrailRotation {
            float get(BaseTrail trail, float lastX, float lastY, float lastAngle, float x, float y);
        }

        public interface AttribCons {
            void get(TrailAttrib attrib, int offset);
        }
    }

    public static abstract class TrailAttrib {
        public final int count;

        protected TrailAttrib(int count) {
            this.count = count;
        }

        public abstract void point(BaseTrail trail, float x, float y, float width, float angle, float[] points, int baseOffset, int offset);

        public abstract void update(BaseTrail trail, float[] points, int baseOffset, int offset);

        public abstract void postUpdate(BaseTrail trail, int offset);

        public abstract TrailAttrib copy();
    }
}
