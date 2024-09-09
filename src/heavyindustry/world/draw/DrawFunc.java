package heavyindustry.world.draw;

import static mindustry.Vars.tilesize;

import heavyindustry.math.HIInterp;
import arc.func.Floatc2;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.Rand;
import arc.math.geom.Vec2;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.gen.Buildingc;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;

public class DrawFunc {
    private static final Vec2 vec21 = new Vec2();
    private static final Rand rand = new Rand();

    public static void drawSnow(float x, float y, float rad, float rot, Color color){
        Draw.color(color);
        for(int i = 0; i < 6; i++){
            float angle = 60 * i + rot;
            Drawf.tri(x + Angles.trnsx(angle, rad), y + Angles.trnsy(angle, rad), rad/3, rad, angle - 180);
            Drawf.tri(x + Angles.trnsx(angle, rad), y + Angles.trnsy(angle, rad), rad/3, rad/4, angle);
        }
        Draw.reset();
    }

    //N
    public static final float sinScl = 1f;
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
    public static void tri(float x, float y, float width, float length, float angle){
        float wx = Angles.trnsx(angle + 90, width), wy = Angles.trnsy(angle + 90, width);
        Fill.tri(x + wx, y + wy, x - wx, y - wy, Angles.trnsx(angle, length) + x, Angles.trnsy(angle, length) + y);
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
}
