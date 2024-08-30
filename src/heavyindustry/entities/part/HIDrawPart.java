package heavyindustry.entities.part;

import mindustry.entities.part.DrawPart.PartProgress;

public class HIDrawPart {
    public static final PartProgress recoilWarmup = p -> Math.max(0, p.warmup - p.recoil);
    public static final PartProgress recoilWarmupSep = p -> p.warmup - p.recoil;
}
