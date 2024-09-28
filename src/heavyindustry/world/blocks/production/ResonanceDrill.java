package heavyindustry.world.blocks.production;

import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import heavyindustry.content.*;
import heavyindustry.graphics.*;

public class ResonanceDrill extends AdaptDrill {
    public ResonanceDrill(String name) {
        super(name);
        mineSpeed = 5;
        mineCount = 2;

        powerConsBase = 0f;

        updateEffect = HIFx.resonance;
    }

    public class ResonanceDrillBuild extends AdaptDrillBuild {
        public void drawMining(){
            float rad = 9.2f + Mathf.absin(8, 1);
            float base = (Time.time / 70f);
            Tmp.c1.set(HIPal.thurmixRed).a(warmup/1.1f);
            //Draw.z(Layer.effect);
            Draw.color(Tmp.c1);
            Lines.stroke(2f);
            for(int i = 0; i < 32; i++){
                Mathf.rand.setSeed(id + hashCode() + i);
                float fin = (Mathf.rand.random(1f) + base) % 1f, fout = 1f - fin;
                float angle = Mathf.rand.random(360f) + ((Time.time * 2.2f) % 360f);
                float len = 12.5f * Interp.pow2.apply(fout);
                Lines.lineAngle(
                        x + Angles.trnsx(angle, len),
                        y + Angles.trnsy(angle, len),
                        angle, 6 * fin
                );
            }

            Tmp.c1.set(HIPal.thurmixRed).a(warmup/1.3f);
            Draw.color(Tmp.c1);
            Lines.stroke(2f);
            Lines.circle(x, y, rad);

            Draw.reset();
        }
    }
}