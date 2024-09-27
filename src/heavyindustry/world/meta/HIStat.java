package heavyindustry.world.meta;

import mindustry.world.meta.*;

public class HIStat {
    public static final Stat
            minSpeed = new Stat("hi-min-speed"),
            maxSpeed = new Stat("hi-max-speed"),
            baseHealChance = new Stat("hi-base-heal-chance"),
            itemsMovedBoost = new Stat("hi-items-moved-boost", StatCat.optional),
            powerConsModifier = new Stat("hi-power-cons-modifier", StatCat.function),
            minerBoosModifier = new Stat("hi-miner-boost-modifier", StatCat.function),
            itemConvertList = new Stat("hi-item-convert-list", StatCat.function);
}
