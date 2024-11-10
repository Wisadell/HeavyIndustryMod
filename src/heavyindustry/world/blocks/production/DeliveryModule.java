package heavyindustry.world.blocks.production;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.graphics.*;
import heavyindustry.graphics.*;

/**
 * A module that directly converts drill bit products into another item.
 */
public class DeliveryModule extends DrillModule {
    public DeliveryModule(String name) {
        super(name);
        powerMul = 1.2f;
        powerExtra = 300f;
        coreSend = true;
    }

    public class DeliveryModuleBuild extends DrillModuleBuild{
        @Override
        public void draw() {
            super.draw();

            Draw.z(Layer.effect);
            Draw.color(team.color, Color.white, 0.2f);
            Lines.stroke(1.2f * smoothWarmup);

            float ang1 = Drawn.rotator_90(Drawn.cycle(Time.time / 4f, 0, 45), 0.15f);
            float ang2 = Drawn.rotator_90(Drawn.cycle(Time.time / 3f, 0, 120), 0.15f);

            Lines.spikes(x, y, 8 + 4 * Mathf.sinDeg(Time.time * 3f + 20), 3 + Mathf.sinDeg(Time.time * 2.5f), 4, ang1 + 45);
            Lines.spikes(x, y, 7 + 3 * Mathf.sinDeg(Time.time * 3.2f), 4 + 1.2f * Mathf.sinDeg(Time.time * 2.2f), 4, ang2);

            Lines.square(x, y, 8, Time.time / 8f);
            Lines.square(x, y, 8, -Time.time / 8f);

        }
    }
}
