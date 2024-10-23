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
import static heavyindustry.util.HIUtils.*;

public class GatherRegionPart extends DrawPart {
    public float x, y;
    public float rd;
    public float gatherRM = 0.5f;
    public float moveX, moveY;

    public float shiftSpeed = 5;
    public float hue = 360;

    public Color color = Color.white;
    public boolean colorful = true;
    public TextureRegion[] regions = {};
    public Color[] colors;
    public float mainW = 2, mainH = 2;
    public float width = 7, height = 7;

    public float layerLow = Layer.turret;
    public float layerHigh = Layer.bullet;

    public boolean centerRot = true;
    public PartProgress progress = PartProgress.warmup;
    public PartProgress warmup = PartProgress.warmup;

    private final Vec2 u = new Vec2();
    private final Vec2 pos = new Vec2();

    @Override
    public void draw(PartParams params) {
        float wp = progress.getClamp(params);
        float wa = warmup.get(params);
        float mx = wp * moveX, my = moveY * wp;
        Tmp.v1.set(x + mx, y + my).rotate(params.rotation - 90);
        float px = params.x + Tmp.v1.x, py = params.y + Tmp.v1.y;

        float rot = params.rotation - 90;
        float rb = rd - rd * wp * gatherRM;
        float z = Draw.z();

        Draw.color(color);
        pos.set(px, py);
        for(int j = 0; j < regions.length; j++){
            if(colorful) Draw.color(rainStart(colors[j]).shiftHue(hue/regions.length * j));

            float a = 360f/regions.length * j + Time.time * shiftSpeed;
            if(a % 360 > 0 && a % 360 < 180) Draw.z(layerLow - 0.1f);
            else Draw.z(layerHigh + 0.1f);
            u.trns(rot,
                    rd * Mathf.cosDeg(a),
                    rb * Mathf.sinDeg(a)
            );
            float rx = px + u.x, ry = py + u.y;

            if(regions[j].found())
                Draw.rect(regions[j], rx, ry,
                        (mainW + width * Math.abs(Mathf.cosDeg(a + 90))) * wa,
                        (centerRot ? (mainH + height * Math.abs(Mathf.sinDeg(a + 90))) : (mainH + height)) * wa,
                        centerRot ? pos.angleTo(rx, ry) + 90 : rot);
        }

        Draw.reset();
        Draw.z(z);
    }

    @Override
    public void load(String name) {
        regions = split(name + "-ttf", 16, 0);
        if(colorful){
            colors = new Color[regions.length];
            Arrays.fill(colors, new Color());
        }
    }
}
