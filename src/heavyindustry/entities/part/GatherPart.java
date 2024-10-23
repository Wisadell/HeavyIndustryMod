package heavyindustry.entities.part;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.*;
import mindustry.entities.part.*;
import mindustry.graphics.*;

import java.util.*;

import static heavyindustry.content.HIGet.*;

public class GatherPart extends DrawPart {
    public float x, y;
    public float rd;
    public float gatherRM = 0.5f;
    public int amount = 180;
    public float sk = 0.3f;
    public float skEnd = 0.3f;
    public float moveX, moveY;

    public Color color = Color.white;
    public Color[] colors;
    public boolean colorful = true;
    public float shiftSpeed = 5;
    public float hue = 360;
    public float alpha = 1f;

    public float layerLow = Layer.turret;
    public float layerHigh = Layer.bullet;

    public PartProgress progress = PartProgress.warmup;

    private final Vec2 u = new Vec2(), c = new Vec2();

    @Override
    public void draw(PartParams params) {
        float wp = progress.getClamp(params);
        float mx = wp * moveX, my = moveY * wp;
        Tmp.v1.set(x + mx, y + my).rotate(params.rotation - 90);
        float px = params.x + Tmp.v1.x, py = params.y + Tmp.v1.y;

        float rot = params.rotation - 90;
        float rb = rd - rd * wp * gatherRM;
        float z = Draw.z();
        Draw.color(color);

        int j = 0;
        for(int i = 0; i < amount; i++){
            if(i < amount/2f && i > 0) Draw.z(layerLow -0.1f);
            else Draw.z(layerHigh + 0.1f);
            if(colorful) Draw.color(rainStart(colors[i]).a(alpha).shiftHue(Time.time * shiftSpeed + hue/amount * i));
            float a = 360f/amount * i;
            u.trns(rot,
                    rd * Mathf.cosDeg(a),
                    rb * Mathf.sinDeg(a)
            );
            Fill.circle(px + u.x, py + u.y, (sk * wp * (i < amount/2f && i > 0 ? 0.9f : 1f)) + skEnd);
        }

        Draw.reset();
        Draw.z(z);
    }

    @Override
    public void load(String name) {
        if(colorful){
            colors = new Color[amount];
            Arrays.fill(colors, new Color());
        }
    }
}
