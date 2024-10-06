package heavyindustry.math;

import arc.math.*;
import arc.math.Interp.*;

public interface HIInterp {
    Interp upThenFastDown = x -> 1.0115f * (1.833f * (0.9991f * x - 1.1f) + 0.2f / (0.9991f * x - 1.1f) + 2.2f);
    Interp artillery = x -> 1 - 2 * (x - 0.5f) * (x - 0.5f);
    Interp artilleryPlus = x -> 3 * x - 3 * x * x + 0.25f;
    Interp artilleryPlusReversed = x -> -3 * x + 3 * x * x + 1;
    BounceOut bounce5Out = new BounceOut(5);
    BounceIn bounce5In = new BounceIn(5);
    Pow pow10 = new Pow(10);
    PowIn pow1_5In = new PowIn(1.5f);
    Interp zero = a -> 0;
    Interp inOut = a -> 2 * (0.9f * a + 0.31f) + 1f / (5f * (a + 0.1f)) - 1.6f;
    Interp inOut2 = x -> 1.6243f * (0.9f * x + 0.46f) + 1 / (10 * (x + 0.1f)) -1.3f;
    Interp parabola4 = x -> 4 * (x - 0.5f) * (x - 0.5f);
    Interp parabola4Reversed = x -> -4 * (x - 0.5f) * (x - 0.5f) + 1;
    Interp parabola4Reversed_X4 = x -> (-4 * (x - 0.5f) * (x - 0.5f) + 1) * 2.75f;
    Interp laser = x -> Interp.pow10Out.apply(x * 1.5f) * Mathf.curve(1 - x, 0, 0.085f);
}