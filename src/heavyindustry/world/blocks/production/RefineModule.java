package heavyindustry.world.blocks.production;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import heavyindustry.math.*;

/**
 * Make the drill output directly enter the core module.
 */
public class RefineModule extends DrillModule {
    public Color flameColor = Color.valueOf("f58349"), midColor = Color.valueOf("f2d585");
    public float flameRad = 1f, circleSpace = 2f, flameRadiusScl = 8f, flameRadiusMag = 0.6f, circleStroke = 1.5f;

    public float alpha = 0.5f;
    public int particles = 12;
    public float particleLife = 70f, particleRad = 7f, particleSize = 3f, fadeMargin = 0.4f, rotateScl = 1.5f;
    public Interp particleInterp = HIInterp.pow1_5In;

    public RefineModule(String name) {
        super(name);
        powerMul = 1f;
        powerExtra = 180f;
    }

    public class RefineModuleBuild extends DrillModuleBuild{
        public Rand rand = new Rand();

        @Override
        public void draw() {
            super.draw();
            Lines.stroke(circleStroke * smoothWarmup);

            float si = Mathf.absin(flameRadiusScl, flameRadiusMag);
            float a = alpha * smoothWarmup;
            Draw.blend(Blending.additive);

            Draw.color(midColor, a);
            Fill.circle(x, y, flameRad + si);

            Draw.color(flameColor, a);
            Lines.circle(x, y, (flameRad + circleSpace + si) * smoothWarmup);

            rand.setSeed(id);
            float base = (Time.time / particleLife);
            for(int i = 0; i < particles; i++){
                float fin = (rand.random(1f) + base) % 1f, fout = 1f - fin;
                float angle = rand.random(360f) + (Time.time / rotateScl) % 360f;
                float len = particleRad * particleInterp.apply(fout);
                Draw.alpha(a * (1f - Mathf.curve(fin, 1f - fadeMargin)));
                Fill.circle(
                        x + Angles.trnsx(angle, len),
                        y + Angles.trnsy(angle, len),
                        particleSize * fin * smoothWarmup
                );
            }

            Draw.blend();
            Draw.reset();
        }
    }
}
