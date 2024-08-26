package HeavyIndustry.world.meta;

import mindustry.world.meta.Stat;

import static HeavyIndustry.HeavyIndustryMod.name;

public class HIStat {
    public static final Stat
            minSpeed = new Stat(name("min-speed")),
            maxSpeed = new Stat(name("max-speed")),
            baseHealChance = new Stat(name("baseHealChance"));
}
