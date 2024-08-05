package HeavyIndustry.content;

import HeavyIndustry.entities.PositionLightning;
import HeavyIndustry.graphics.Drawm;
import HeavyIndustry.graphics.HIPal;
import HeavyIndustry.math.Math3D;
import HeavyIndustry.struct.Vec2Seq;
import HeavyIndustry.world.draw.DrawFunc;
import HeavyIndustry.entities.bullet.HailStoneBulletType;
import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.Angles;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.Rand;
import arc.math.geom.Vec2;
import arc.math.geom.Vec3;
import arc.struct.IntMap;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;

import static HeavyIndustry.HeavyIndustryMod.name;
import static HeavyIndustry.graphics.HIPal.*;
import static java.util.Objects.hash;
import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.circle;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.*;
import static mindustry.Vars.state;
import static mindustry.Vars.tilesize;
import static mindustry.content.Fx.v;

public class HIFx {
    public static final Vec2 vec = new Vec2(), vec2 = new Vec2();
    public static Effect uraniumShoot = new Effect(12, e -> {
        color(Color.white, uraniumGrey, e.fin());
        stroke(e.fout() * 1.2f + 0.5f);
        randLenVectors(e.id, 7, 25f * e.finpow(), e.rotation, 50f, (x, y) -> lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fin() * 5f + 2f));
    });

    public static Effect casing(float life){
        return new Effect(life, e -> {
            color(Pal.lightOrange, Color.lightGray, Pal.lightishGray, e.fin());
            alpha(e.fout(0.5f));
            float rot = Math.abs(e.rotation) + 90f;
            int i = -Mathf.sign(e.rotation);
            float len = (2f + e.finpow() * 10f) * i;
            float lr = rot + e.fin() * 20f * i;
            rect(Core.atlas.find("casing"),
                    e.x + trnsx(lr, len) + Mathf.randomSeedRange(e.id + i + 7, 3f * e.fin()),
                    e.y + trnsy(lr, len) + Mathf.randomSeedRange(e.id + i + 8, 3f * e.fin()),
                    2f, 3f, rot + e.fin() * 50f * i
            );
        }).layer(Layer.bullet);
    }

    public static Effect ellipse(float startRad, int num, float lifetime, Color color){
        return ellipse(startRad, 2, num, lifetime, color);
    }

    public static Effect ellipse(float startRad, float rad, int num, float lifetime, Color color){
        return new Effect(lifetime, e ->{
            float length = startRad * e.fin();
            float width = length/2;

            Draw.color(color);

            for(int i = 0; i <= num; i++){
                float rot = -90f + 180f * i / (float)num;
                Tmp.v1.trnsExact(rot, width);

                point(
                        (Tmp.v1.x) / width * length,
                        Tmp.v1.y,
                        e.x, e.y,
                        e.rotation + 90,
                        rad * e.fout()
                );
            }
            for(int i = 0; i <= num; i++){
                float rot = 90f + 180f * i / (float)num;
                Tmp.v1.trnsExact(rot, width);

                point(
                        (Tmp.v1.x) / width * length,
                        Tmp.v1.y,
                        e.x, e.y,
                        e.rotation + 90,
                        rad * e.fout()
                );
            }
        });
    }
    public static Effect edessp(float lifetime){
        return new Effect(lifetime, e -> {
            if(!(e.data instanceof Object[] objects) || objects.length < 4) return;
            if(!(objects[0] instanceof TextureRegion region)) return;
            if(!(objects[1] instanceof Float range)) return;
            if(!(objects[2] instanceof Float rot)) return;
            if(!(objects[3] instanceof Float rRot)) return;

            float ex = e.x + Angles.trnsx(e.rotation + rRot * e.fin(), range * e.fout()),
                    ey = e.y + Angles.trnsy(e.rotation + rRot * e.fin(), range * e.fout());
            Draw.rect(region, ex, ey, region.width/3f * e.fin(), region.height/3f * e.fin(), rot);
        }).followParent(true);
    }
    private static void point(float x, float y, float baseX, float baseY, float rotation, float rad){
        Tmp.v1.set(x, y).rotateRadExact(rotation * Mathf.degRad);
        Fill.circle(Tmp.v1.x + baseX, Tmp.v1.y + baseY, rad);
    }
    public static Effect fireworksShoot(float r){
        return new Effect(30, e -> {
            Draw.z(Layer.effect - 0.1f);
            Draw.color(HIPal.EC9.set(rainBowRed).shiftHue(Time.time * 2.0f));
            Angles.randLenVectors(e.id, 1, e.fin() * 20f, e.rotation + r, 0, (x, y) -> Fill.circle(e.x + x, e.y + y, 2 * e.fout()));
            Angles.randLenVectors(e.id, 1, e.fin() * 20f, e.rotation - r, 0, (x, y) -> Fill.circle(e.x + x, e.y + y, 2 * e.fout()));
            Draw.blend();
            Draw.reset();
        });
    }

    public static Effect normalIceTrail = new Effect(90, e -> DrawFunc.drawSnow(e.x, e.y, e.rotation * e.foutpow(), e.fin() * 180f, e.color));

    public static Effect aimEffect(float lifetime, Color color, float width, float length, float spacing){
        return new Effect(lifetime, length, e -> {
            Draw.color(color);
            TextureRegion region = Core.atlas.find(name("aim-shoot"));
            float track = Mathf.curve(e.fin(Interp.pow2Out), 0, 0.25f) * Mathf.curve(e.fout(Interp.pow4Out), 0, 0.3f) * e.fin();
            for(int i = 0; i <= length / spacing; i++){
                Tmp.v1.trns(e.rotation, i * spacing);
                float f = Interp.pow3Out.apply(Mathf.clamp((e.fin() * length - i * spacing) / spacing)) * (0.6f + track * 0.4f);
                Draw.rect(region, e.x + Tmp.v1.x, e.y + Tmp.v1.y, 155 * Draw.scl * f, 155 * Draw.scl * f, e.rotation - 90);
            }
            Tmp.v1.trns(e.rotation, 0, (2 - track) * tilesize * width);
            Lines.stroke(track * 2);
            for(int i : Mathf.signs){
                Lines.lineAngle(e.x + Tmp.v1.x * i, e.y + Tmp.v1.y * i, e.rotation, length * (0.75f + track / 4) * Mathf.curve(e.fout(Interp.pow5Out), 0, 0.1f));
            }
        });
    }
    public static Effect expFtEffect(int amount, float size, float len, float lifetime, float startDelay){
        return new Effect(lifetime, e -> {
            float length = len + e.finpow() * 20f;
            rand.setSeed(e.id);
            for(int i = 0; i < amount; i++){
                v.trns(rand.random(360f), rand.random(length));
                float sizer = rand.random(size/2, size);

                e.scaled(e.lifetime * rand.random(0.5f, 1f), b -> {
                    color(Pal.darkerGray, b.fslope() * 0.93f);

                    Fill.circle(e.x + v.x, e.y + v.y, sizer + b.fslope() * 1.2f);
                });
            }
        }).startDelay(startDelay);
    }
    public static Effect fiammettaExp(float r){
        return new Effect(30, e -> {
            float fin = Math.min(e.time/10, 1), fout = 1 - ((e.time - 10)/(e.lifetime - 10));
            Draw.color(HIItems.highEnergyFabric.color.cpy().a(e.time > 10 ? 0.3f * fout : 0.3f));
            Fill.circle(e.x, e.y, r * fin);
            float ww = r * 2f * fin, hh = r * 2f * fin;
            Draw.color(HIItems.highEnergyFabric.color.cpy().a(e.time > 10 ? fout : 1));
            Draw.rect(Core.atlas.find(name("firebird-light")), e.x, e.y, ww, hh);
        });
    }
    public static Effect normalTrail = new Effect(90, e -> {
        Draw.color(e.color);
        float r = e.rotation;
        Fill.circle(e.x, e.y, r * e.foutpow());
    }).layer(Layer.bullet - 1f);
    //N
    public static final float EFFECT_BOTTOM = Layer.bullet - 0.11f;
    private static final Rand rand = new Rand();
    public static final IntMap<Effect> same = new IntMap<>();

    public static float fslope(float fin){
        return (0.5f - Math.abs(fin - 0.5f)) * 2f;
    }
    public static Effect squareRand(Color color, float sizeMin, float sizeMax){
        return new Effect(20f, sizeMax * 2f, e -> {
            Draw.color(Color.white, color, e.fin() + 0.15f);
            if(e.id % 2 == 0){
                Lines.stroke(1.5f * e.fout(Interp.pow3Out));
                Lines.square(e.x, e.y, Mathf.randomSeed(e.id, sizeMin, sizeMax) * e.fin(Interp.pow2Out) + 3, 45);
            }else{
                Fill.square(e.x, e.y, Mathf.randomSeed(e.id, sizeMin * 0.5f, sizeMin * 0.8f) * e.fout(Interp.pow2Out), 45);
            }
        });
    }
    public static Effect crossBlast(Color color, float size){
        return crossBlast(color, size, 0);
    }
    public static Effect crossBlast(Color color, float size, float rotate){
        return new Effect(Mathf.clamp(size / 3f, 35f, 240f), size * 2, e -> {
            color(color, Color.white, e.fout() * 0.55f);
            Drawf.light(e.x, e.y, e.fout() * size, color, 0.7f);
            e.scaled(10f, i -> {
                stroke(1.35f * i.fout());
                circle(e.x, e.y, size * 0.7f * i.finpow());
            });
            rand.setSeed(e.id);
            float sizeDiv = size / 1.5f;
            float randL = rand.random(sizeDiv);
            for(int i = 0; i < 4; i++){
                DrawFunc.tri(e.x, e.y, size / 20 * (e.fout() * 3f + 1) / 4 * (e.fout(Interp.pow3In) + 0.5f) / 1.5f, (sizeDiv + randL) * Mathf.curve(e.fin(), 0, 0.05f) * e.fout(Interp.pow3), i * 90 + rotate);
            }
        });
    }

    public static Effect get(String m, Color c, Effect effect){
        int hash = hash(m, c);
        Effect or = same.get(hash);
        if(or == null)same.put(hash, effect);
        return or == null ? effect : or;
    }

    public static Effect shootLine(float size, float angleRange){
        int num = Mathf.clamp((int)size / 6, 6, 20);
        float thick = Mathf.clamp(0.75f, 2f, size / 22f);

        return new Effect(37f, e -> {
            color(e.color, Color.white, e.fout() * 0.7f);
            rand.setSeed(e.id);
            DrawFunc.randLenVectors(e.id, num, 4 + (size * 1.2f) * e.fin(), size * 0.15f * e.fin(), e.rotation, angleRange, (x, y) -> {
                Lines.stroke(thick * e.fout(0.32f));
                lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), (e.fslope() + e.fin()) * 0.5f * (size * rand.random(0.15f, 0.5f) + rand.random(2f)) + rand.random(2f));
                Drawf.light(e.x + x, e.y + y, e.fslope() * (size * 0.5f + 14f) + 3, e.color, 0.7f);
            });
        });
    }

    public static Effect shootCircleSmall(Color color){
        return get("shootCircleSmall", color, new Effect(30, e -> {
            color(color, Color.white, e.fout() * 0.75f);
            rand.setSeed(e.id);
            randLenVectors(e.id, 3, 3 + 23 * e.fin(), e.rotation, 22, (x, y) -> {
                Fill.circle(e.x + x, e.y + y, e.fout() * rand.random(1.5f, 3.2f));
                Drawf.light(e.x + x, e.y + y, e.fout() * 4.5f, color, 0.7f);
            });
        }));
    }

    public static Effect square(Color color, float lifetime, int num, float range, float size){
        return new Effect(lifetime, e -> {
            Draw.color(color);
            rand.setSeed(e.id);
            randLenVectors(e.id, num, range * e.finpow(), (x, y) -> {
                float s = e.fout(Interp.pow3In) * (size + rand.range(size / 3f));
                Fill.square(e.x + x, e.y + y, s, 45);
                Drawf.light(e.x + x, e.y + y, s * 2.25f, color, 0.7f);
            });
        });
    }

    public static Effect sharpBlast(Color colorExternal, Color colorInternal, float lifetime, float range){
        return new Effect(lifetime, range * 2, e -> {
            Angles.randLenVectors(e.id, (int)Mathf.clamp(range / 8, 4, 18), range / 8, range * (1 + e.fout(Interp.pow2OutInverse)) / 2f, (x, y) -> {
                float angle = Mathf.angle(x, y);
                float width = e.foutpowdown() * rand.random(range / 6, range / 3) / 2 * e.fout();

                rand.setSeed(e.id);
                float length = rand.random(range / 2, range * 1.1f) * e.fout();

                Draw.color(colorExternal);
                DrawFunc.tri(e.x + x, e.y + y, width, range / 3 * e.fout(Interp.pow2In), angle - 180);
                DrawFunc.tri(e.x + x, e.y + y, width, length, angle);

                Draw.color(colorInternal);

                width *= e.fout();

                DrawFunc.tri(e.x + x, e.y + y, width / 2, range / 3 * e.fout(Interp.pow2In) * 0.9f * e.fout(), angle - 180);
                DrawFunc.tri(e.x + x, e.y + y, width / 2, length / 1.5f * e.fout(), angle);
            });
        });
    }

    public static Effect instBomb(Color color){
        return get("instBomb", color, instBombSize(color, 4, 80f));
    }

    public static Effect instBombSize(Color color, int num, float size){
        return new Effect(22.0F, size * 1.5f, e -> {
            Draw.color(color);
            Lines.stroke(e.fout() * 4.0F);
            Lines.circle(e.x, e.y, 4.0F + e.finpow() * size / 4f);
            Drawf.light(e.x, e.y, e.fout() * size, color, 0.7f);

            int i;
            for(i = 0; i < num; ++i) {
                DrawFunc.tri(e.x, e.y, size / 12f, size * e.fout(), (float)(i * 90 + 45));
            }

            Draw.color();

            for(i = 0; i < num; ++i) {
                DrawFunc.tri(e.x, e.y, size / 26f, size / 2.5f * e.fout(), (float)(i * 90 + 45));
            }
        });
    }

    public static Effect instHit(Color color){return get("instHit", color, instHit(color, 5, 50)); }

    public static Effect instHit(Color color, int num, float size){
        return new Effect(20.0F, size * 1.5f, e -> {
            rand.setSeed(e.id);

            for(int i = 0; i < 2; ++i) {
                Draw.color(i == 0 ? color : color.cpy().lerp(Color.white, 0.25f));
                float m = i == 0 ? 1.0F : 0.5F;


                for(int j = 0; j < num; ++j) {
                    float rot = e.rotation + rand.range(size);
                    float w = 15.0F * e.fout() * m;
                    DrawFunc.tri(e.x, e.y, w, (size + rand.range( size * 0.6f)) * m, rot);
                    DrawFunc.tri(e.x, e.y, w, size * 0.3f * m, rot + 180.0F);
                }
            }

            e.scaled(12.0F, (c) -> {
                Draw.color(color.cpy().lerp(Color.white, 0.25f));
                Lines.stroke(c.fout() * 2.0F + 0.2F);
                Lines.circle(e.x, e.y, c.fin() * size * 1.1f);
            });

            e.scaled(18.0F, (c) -> {
                Draw.color(color);
                Angles.randLenVectors(e.id, 25, 8.0F + e.fin() * size * 1.25f, e.rotation, 60.0F, (x, y) -> {
                    Fill.square(e.x + x, e.y + y, c.fout() * 3.0F, 45.0F);
                });
            });

            Drawf.light(e.x, e.y, e.fout() * size, color, 0.7f);
        });
    }

    public static Effect genericCharge(Color color, float size, float range, float lifetime){
        return new Effect(lifetime, e -> {
            color(color);
            Lines.stroke(size / 7f * e.fin());

            randLenVectors(e.id, 15, 3f + 60f * e.fout(), e.rotation, range, (x, y) -> {
                lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * size + size / 4f);
                Drawf.light(e.x + x, e.y + y, e.fout(0.25f) * size, color, 0.7f);
            });

            Fill.circle(e.x, e.y, size * 0.48f * Interp.pow3Out.apply(e.fin()));
        });
    }

    public static Effect lightningHitSmall(Color color){
        return get("lightningHitSmall", color, new Effect(20, e -> {
            color(color, Color.white, e.fout() * 0.7f);
            randLenVectors(e.id, 5, 18 * e.fin(), (x, y) -> {
                lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 6 + 2);
                Drawf.light(e.x + x, e.y + y, e.fin() * 12 * e.fout(0.25f), color, 0.7f);
            });
        }));
    }
    public static Effect lightningHitSmall = new Effect(Fx.chainLightning.lifetime, e -> {
        color(Color.white, e.color, e.fin() + 0.25f);

        e.scaled(7f, s -> {
            stroke(0.5f + s.fout());
            Lines.circle(e.x, e.y, s.fin() * (e.rotation + 12f));
        });

        stroke(0.75f + e.fout());

        randLenVectors(e.id, 6, e.fin() * e.rotation + 7f, (x, y) -> lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fout() * 4 + 2f));

        Fill.circle(e.x, e.y, 2.5f * e.fout());
    });
    public static Effect lightningFade = (new Effect(PositionLightning.lifetime, 1200.0f, e -> {
        if(!(e.data instanceof Vec2Seq)) return;
        Vec2Seq points = e.data();

        e.lifetime = points.size() < 2 ? 0 : 1000;
        int strokeOffset = (int)e.rotation;


        if(points.size() > strokeOffset + 1 && strokeOffset > 0 && points.size() > 2){
            points.removeRange(0, points.size() - strokeOffset - 1);
        }

        if(!state.isPaused() && points.any()){
            points.remove(0);
        }

        if(points.size() < 2)return;

        Vec2 data = points.peekTmp();
        float stroke = data.x;
        float fadeOffset = data.y;

        Draw.color(e.color);
        for(int i = 1; i < points.size() - 1; i++){
            Lines.stroke(Mathf.clamp((i + fadeOffset / 2f) / points.size() * (strokeOffset - (points.size() - i)) / strokeOffset) * stroke);
            Vec2 from = points.setVec2(i - 1, Tmp.v1);
            Vec2 to = points.setVec2(i, Tmp.v2);
            Lines.line(from.x, from.y, to.x, to.y, false);
            Fill.circle(from.x, from.y, Lines.getStroke() / 2);
        }

        Vec2 last = points.tmpVec2(points.size() - 2);
        Fill.circle(last.x, last.y, Lines.getStroke() / 2);
    })).layer(Layer.effect - 0.001f);
    public static Effect hugeTrail = new Effect(40f, e -> {
        Draw.color(e.color);
        Draw.alpha(e.fout(0.85f) * 0.85f);
        Angles.randLenVectors(e.id, 6, 2.0F + e.rotation * 5f * e.finpow(), (x, y) -> {
            Fill.circle(e.x + x / 2.0F, e.y + y / 2.0F, e.fout(Interp.pow3Out) * e.rotation);
        });
    });
    public static Effect hugeSmokeGray = new Effect(40f, e -> {
        Draw.color(Color.gray, Color.darkGray, e.fin());
        Angles.randLenVectors(e.id, 6, 2.0F + 19.0F * e.finpow(), (x, y) -> Fill.circle(e.x + x / 2.0F, e.y + y / 2.0F, e.fout() * 2f));
        e.scaled(25f, i -> Angles.randLenVectors(e.id, 6, 2.0F + 19.0F * i.finpow(), (x, y) -> Fill.circle(e.x + x, e.y + y, i.fout() * 4.0F)));
    });
    public static Effect hugeSmoke = new Effect(40f, e -> {
        Draw.color(e.color);
        Angles.randLenVectors(e.id, 6, 2.0F + 19.0F * e.finpow(), (x, y) -> Fill.circle(e.x + x / 2.0F, e.y + y / 2.0F, e.fout() * 2f));
        e.scaled(25f, i -> Angles.randLenVectors(e.id, 6, 2.0F + 19.0F * i.finpow(), (x, y) -> Fill.circle(e.x + x, e.y + y, i.fout() * 4.0F)));
    });
    public static Effect hugeSmokeLong = new Effect(120f, e -> {
        Draw.color(e.color);
        Angles.randLenVectors(e.id, 6, 2.0F + 19.0F * e.finpow(), (x, y) -> Fill.circle(e.x + x / 2.0F, e.y + y / 2.0F, e.fout() * 2f));
        e.scaled(25f, i -> Angles.randLenVectors(e.id, 6, 2.0F + 19.0F * i.finpow(), (x, y) -> Fill.circle(e.x + x, e.y + y, i.fout() * 4.0F)));
    });
    public static Effect square45_4_45 = new Effect(45f, e-> {
        Draw.color(e.color);
        randLenVectors(e.id, 5, 20f * e.finpow(), (x, y) -> {
            Fill.square(e.x + x, e.y + y, 4f * e.fout(), 45);
            Drawf.light(e.x + x, e.y + y, e.fout() * 6f, e.color, 0.7f);
        });
    });
    public static Effect square45_6_45 = new Effect(45f, e-> {
        Draw.color(e.color, Color.white, e.fout() * 0.6f);
        randLenVectors(e.id, 6, 27f * e.finpow(), (x, y) -> {
            Fill.square(e.x + x, e.y + y, 5f * e.fout(), 45);
            Drawf.light(e.x + x, e.y + y, e.fout() * 9F, e.color, 0.7f);
        });
    });
    public static Effect square45_6_45_Charge = new Effect(90f, e-> {
        Draw.color(e.color, Color.white, e.fin() * 0.6f);
        randLenVectors(e.id, 12, 60 * e.fout(Interp.pow4Out), (x, y) -> {
            Fill.square(e.x + x, e.y + y, 5f * e.fin(), 45);
            Drawf.light(e.x + x, e.y + y, e.fin() * 9F, e.color, 0.7f);
        });

        Lines.stroke(2f * e.fin());
        Lines.circle(e.x, e.y, 80 * e.fout(Interp.pow5Out));
    });
    public static Effect lightningHitLarge = new Effect(50f, 180f, e -> {
        color(e.color);
        Drawf.light(e.x, e.y, e.fout() * 90f, e.color, 0.7f);
        e.scaled(25f, t -> {
            stroke(3f * t.fout());
            circle(e.x, e.y, 3f + t.fin(Interp.pow3Out) * 80f);
        });
        Fill.circle(e.x, e.y, e.fout() * 8f);
        randLenVectors(e.id + 1, 4, 1f + 60f * e.finpow(), (x, y) -> Fill.circle(e.x + x, e.y + y, e.fout() * 5f));

        color(Color.gray);
        Angles.randLenVectors(e.id, 8, 2.0F + 30.0F * e.finpow(), (x, y) -> Fill.circle(e.x + x, e.y + y, e.fout() * 4.0F + 0.5F));
    });
    public static Effect hitSpark = new Effect(45, e -> {
        color(e.color, Color.white, e.fout() * 0.3f);
        stroke(e.fout() * 1.6f);

        rand.setSeed(e.id);
        randLenVectors(e.id, 8, e.finpow() * 20f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * rand.random(1.95f, 4.25f) + 1f);
        });
    });
    public static Effect hitSparkLarge = new Effect(40, e -> {
        color(e.color, Color.white, e.fout() * 0.3f);
        stroke(e.fout() * 1.6f);

        rand.setSeed(e.id);
        randLenVectors(e.id, 18, e.finpow() * 27f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * rand.random(4, 8) + 2f);
        });
    });
    public static Effect shareDamage = new Effect(45f, e-> {
        if(!(e.data instanceof Number))return;
        Draw.color(e.color);
        Draw.alpha(((Number)e.data()).floatValue() * e.fout());
        Fill.square(e.x, e.y, e.rotation);
    });
    public static Effect lightningSpark = new Effect(Fx.chainLightning.lifetime, e -> {
        color(Color.white, e.color, e.fin() + 0.25f);

        stroke(0.65f + e.fout());

        randLenVectors(e.id, 3, e.fin() * e.rotation + 6f, (x, y) -> lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fout() * 4 + 2f));

        Fill.circle(e.x, e.y, 2.5f * e.fout());
    });
    public static Effect trailToGray = new Effect(50.0F, e -> {
        Draw.color(e.color, Color.gray, e.fin());
        randLenVectors(e.id, 2, tilesize * e.fin(), (x, y) -> Fill.circle(e.x + x, e.y + y, e.rotation * e.fout()));
    });
    //O
    public static Effect bigExplosionStone = new Effect(80f, e -> Angles.randLenVectors(e.id, 22, e.fin() * 50f, (x, y) -> {
        float elevation = Interp.bounceIn.apply(e.fout() - 0.3f) * (Mathf.randomSeed((int) Angles.angle(x, y), 30f, 60f));

        Draw.z(Layer.power + 0.1f);
        Draw.color(Pal.shadow);
        Fill.circle(e.x + x, e.y + y, 12f);

        Draw.z(Layer.power + 0.2f);
        Draw.color(e.color);
        Fill.circle(e.x + x, e.y + y + elevation, 12f);
    }));
    public static Effect explosionStone = new Effect(60f, e -> Angles.randLenVectors(e.id, 12, e.fin() * 50f, (x, y) -> {
        float elevation = Interp.bounceIn.apply(e.fout() - 0.3f) * (Mathf.randomSeed((int) Angles.angle(x, y), 30f, 60f));

        Draw.z(Layer.power + 0.1f);
        Draw.color(Pal.shadow);
        Fill.circle(e.x + x, e.y + y, 12f);

        Draw.z(Layer.power + 0.2f);
        Draw.color(e.color);
        Fill.circle(e.x + x, e.y + y + elevation, 12f);
    }));
    public static Effect fellStone = new Effect(120f, e -> {
        if(!(e.data instanceof HailStoneBulletType.HailStoneData data)) return;

        vec2.trns(Mathf.randomSeed(e.id) * 360, data.fallTime/2 + Mathf.randomSeed(e.id + 1) * data.fallTime);
        float scl = Interp.bounceIn.apply(e.fout() - 0.3f);
        float rot = vec2.angle();
        float x = e.x + (vec2.x * e.finpow()), y = e.y + (vec2.y * e.finpow());

        Draw.z(Layer.power + 0.1f);
        Drawm.shadow(data.region, x, y, rot, Math.min(e.fout(), Pal.shadow.a));

        Draw.z(Layer.power + 0.2f);
        Draw.color(e.color);
        Draw.alpha(e.fout());
        Draw.rect(data.region, x, y + (scl * data.fallTime/2), rot);
    });
    public static Effect staticStone = new Effect(250f, e -> {
        if(!(e.data instanceof HailStoneBulletType.HailStoneData data)) return;

        Draw.z(Layer.power + 0.1f);
        Draw.color(e.color);
        Draw.alpha(e.fout());
        Draw.rect(data.region, e.x, e.y, Mathf.randomSeed(e.id) * 360);
    });
    public static Effect windTail = new Effect(100f, e -> {

        Draw.color(Color.white);
        Draw.z(Layer.space - 0.1f);

        rand.setSeed(e.id);

        float rx = rand.random(-1, 1) + 0.01f, ry = rand.random(-1, 1) - 0.01f, dis = rand.random(120, 200);
        float force = rand.random(10, 40);
        float z = rand.random(0, 10);
        Vec3[] windTailPoints = new Vec3[12];

        for(int i = 0; i < 12; i++){
            float scl = (e.fin() - i * 0.05f);
            float x = (scl * dis) + Mathf.cos(scl * 10) * force * rx;
            float y = Mathf.sin(scl * 10) * force * ry;

            vec.trns(e.rotation,x, y);
            vec.add(e.x, e.y);
            vec.add(Math3D.xOffset(e.x, z), Math3D.yOffset(e.y, z));

            windTailPoints[i] = new Vec3(vec.x, vec.y, e.fslope());
        }

        for (int i = 0; i < windTailPoints.length - 1; i++) {
            Vec3 v1 = windTailPoints[i];
            Vec3 v2 = windTailPoints[i + 1];

            Draw.alpha(Mathf.clamp(v1.z, 0.04f, 0.1f));
            Lines.stroke(v1.z);
            Lines.line(v1.x, v1.y, v2.x, v2.y);
        }
    });
}
