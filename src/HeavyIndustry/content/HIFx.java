package HeavyIndustry.content;

import static java.util.Objects.hash;

import HeavyIndustry.world.drawer.DrawFunc;
import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.Angles;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.Rand;
import arc.struct.IntMap;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.Vars;
import mindustry.entities.Effect;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;

import static HeavyIndustry.HeavyIndustryMod.name;
import static HeavyIndustry.content.HIGet.*;
import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.circle;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.*;
import static mindustry.content.Fx.v;

public class HIFx {
    public static Effect uraniumShoot = new Effect(12, e -> {
        color(Color.white, Color.valueOf("a5b2c2"), e.fin());
        stroke(e.fout() * 1.2f + 0.5f);
        randLenVectors(e.id, 7, 25f * e.finpow(), e.rotation, 50f, (x, y) -> {
            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fin() * 5f + 2f);
        });
    });
    public static Effect ellipse(float startRad, int num, float lifetime, Color color){
        return ellipse(startRad, 2, num, lifetime, color);
    }

    public static Effect ellipse(float startRad, float rad, int num, float lifetime, Color color){
        return new Effect(lifetime, e ->{
            float length = startRad * e.fin();
            float width = length/2;

            Draw.color(color);

            //half
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
            Draw.color(HIGet.EC9.set(rainBowRed).shiftHue(Time.time * 2.0f));
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
            Tmp.v1.trns(e.rotation, 0, (2 - track) * Vars.tilesize * width);
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
    //NP
    private static final Rand rand = new Rand();
    public static final IntMap<Effect> same = new IntMap<>();

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
    public static Effect get(String m, Color c, Effect effect){
        int hash = hash(m, c);
        Effect or = same.get(hash);
        if(or == null)same.put(hash, effect);
        return or == null ? effect : or;
    }

    public static Effect crossBlast(Color color){
        return get("crossBlast", color, crossBlast(color, 72));
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
}
