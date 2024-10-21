package heavyindustry.entities.part;

import mindustry.entities.part.DrawPart.*;

public class HIDrawPart {
    public static final PartProgress
            recoilWarmup = p -> Math.max(0, p.warmup - p.recoil),
            recoilWarmupSep = p -> p.warmup - p.recoil;
}
