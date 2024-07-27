package HeavyIndustry.world.game;

import mindustry.entities.part.DrawPart.PartProgress;

public class HIPartProgress {
    public static final PartProgress recoilWarmup = p -> Math.max(0, p.warmup - p.recoil);
    public static final PartProgress recoilWarmupSep = p -> p.warmup - p.recoil;
}
