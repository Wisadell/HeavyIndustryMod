package HeavyIndustry.world.drawer;

import HeavyIndustry.math.HIInterp;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Mathf;
import arc.util.Time;
import mindustry.graphics.Drawf;

public class DrawFunc {
    public static void circlePercent(float x, float y, float rad, float percent, float angle){
        Lines.arc(x, y, rad, percent, angle);
    }

    public static void drawSnow(float x, float y, float rad, float rot, Color color){
        Draw.color(color);
        for(int i = 0; i < 6; i++){
            float angle = 60 * i + rot;
            Drawf.tri(x + Angles.trnsx(angle, rad), y + Angles.trnsy(angle, rad), rad/3, rad, angle - 180);
            Drawf.tri(x + Angles.trnsx(angle, rad), y + Angles.trnsy(angle, rad), rad/3, rad/4, angle);
        }
        Draw.reset();
    }

    public static float rotator_90(float in /*(0, 1)*/, float margin){
        return 90 * HIInterp.pow10.apply(Mathf.curve(in, margin, 1 - margin));
    }

    public static float cycle(float phaseOffset, float T){
        return (Time.time + phaseOffset) % T / T;
    }

    public static float cycle(float in, float phaseOffset, float T){
        return (in + phaseOffset) % T / T;
    }
    //NP
    public static void tri(float x, float y, float width, float length, float angle){
        float wx = Angles.trnsx(angle + 90, width), wy = Angles.trnsy(angle + 90, width);
        Fill.tri(x + wx, y + wy, x - wx, y - wy, Angles.trnsx(angle, length) + x, Angles.trnsy(angle, length) + y);
    }
}
