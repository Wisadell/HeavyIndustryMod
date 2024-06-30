package HeavyIndustry.content;

import HeavyIndustry.HeavyIndustryMod;
import arc.audio.Sound;
import mindustry.Vars;
import mindustry.mod.Mods;

public class HISounds {
    public static Mods.LoadedMod HI;
    public static Sound ct1;
    public static Sound dbz1;
    public static Sound dd1;
    public static Sound fj;
    public static Sound jg1;
    public static Sound largeBeam;

    public HISounds() {
    }

    public static void load() {
        ct1 = loadSound("ct1.ogg");
        dbz1 = loadSound("dbz1.ogg");
        dd1 = loadSound("dd1.ogg");
        fj = loadSound("fj.ogg");
        jg1 = loadSound("jg1.ogg");
        largeBeam = loadSound("largeBeam.ogg");
    }

    public static Sound loadSound(String name) {
        return new Sound(HI.root.child("sounds").child(name));
    }

    static {
        HI = Vars.mods.getMod(HeavyIndustryMod.class);
        ct1 = new Sound();
        dbz1 = new Sound();
        dd1 = new Sound();
        fj = new Sound();
        jg1 = new Sound();
        largeBeam = new Sound();
    }
}
