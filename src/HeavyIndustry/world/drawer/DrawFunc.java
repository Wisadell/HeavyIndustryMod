package HeavyIndustry.world.drawer;

import HeavyIndustry.math.HIInterp;
import arc.func.Floatc2;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.Rand;
import arc.math.geom.Vec2;
import mindustry.graphics.Drawf;

public class DrawFunc {
    private static final Vec2
            vec21 = new Vec2();
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

    //NP
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
}
