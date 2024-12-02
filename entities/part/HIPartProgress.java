package heavyindustry.entities.part;

import mindustry.entities.part.DrawPart.*;

public final class HIPartProgress {
    /** HIPartProgress should not be instantiated. */
    private HIPartProgress() {}

    public static final PartProgress
            recoilWarmup = p -> Math.max(0, p.warmup - p.recoil),
            recoilWarmupSep = p -> p.warmup - p.recoil;
}
