package HeavyIndustry.gen;

import HeavyIndustry.HeavyIndustryMod;
import arc.audio.Sound;
import mindustry.Vars;

public class HISounds {
    public static Sound ct1;
    public static Sound dbz1;
    public static Sound dd1;
    public static Sound fj;
    public static Sound jg1;
    public static Sound flak;
    public static Sound flak2;
    public static Sound gauss;
    public static Sound fissure;
    public static Sound largeBeam;
    public static Sound hailRain;
    public static Sound bigHailstoneHit;

    public HISounds() {
    }

    public static void load() {
        ct1 = loadSound("ct1.ogg");
        dbz1 = loadSound("dbz1.ogg");
        dd1 = loadSound("dd1.ogg");
        fj = loadSound("fj.ogg");
        jg1 = loadSound("jg1.ogg");
        flak = loadSound("flak.ogg");
        flak2 = loadSound("flak2.ogg");
        gauss = loadSound("gauss.ogg");
        fissure = loadSound("fissure.ogg");
        largeBeam = loadSound("largeBeam.ogg");
        hailRain = loadSound("hailRain.ogg");
        bigHailstoneHit = loadSound("bigHailstoneHit.ogg");
    }

    public static Sound loadSound(String name) {
        return new Sound(HeavyIndustryMod.modInfo.root.child("sounds").child(name));
    }

    static {
        HeavyIndustryMod.modInfo = Vars.mods.getMod(HeavyIndustryMod.class);
        ct1 = new Sound();
        dbz1 = new Sound();
        dd1 = new Sound();
        fj = new Sound();
        jg1 = new Sound();
        flak = new Sound();
        flak2 = new Sound();
        gauss = new Sound();
        fissure = new Sound();
        largeBeam = new Sound();
        hailRain = new Sound();
        bigHailstoneHit = new Sound();
    }
}
