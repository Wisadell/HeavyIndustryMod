package HeavyIndustry.gen;

import arc.Core;
import arc.assets.AssetDescriptor;
import arc.assets.loaders.SoundLoader;
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
        ct1 = loadSound("ct1");
        dbz1 = loadSound("dbz1");
        dd1 = loadSound("dd1");
        fj = loadSound("fj");
        jg1 = loadSound("jg1");
        flak = loadSound("flak");
        flak2 = loadSound("flak2");
        gauss = loadSound("gauss");
        fissure = loadSound("fissure");
        largeBeam = loadSound("largeBeam");
        hailRain = loadSound("hailRain");
        bigHailstoneHit = loadSound("bigHailstoneHit");
    }

    private static Sound loadSound(String soundName){
        if(!Vars.headless) {
            String name = "sounds/" + soundName;
            String path = Vars.tree.get(name + ".ogg").exists() ? name + ".ogg" : name + ".mp3";

            Sound sound = new Sound();

            AssetDescriptor<?> desc = Core.assets.load(path, Sound.class, new SoundLoader.SoundParameter(sound));
            desc.errored = Throwable::printStackTrace;

            return sound;

        } else {
            return new Sound();
        }
    }

    static {
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
