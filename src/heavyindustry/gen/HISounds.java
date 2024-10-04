package heavyindustry.gen;

import arc.audio.*;

import static mindustry.Vars.*;

public class HISounds {
    public static Sound
            ct1 = new Sound(),
            dbz1 = new Sound(),
            dd1 = new Sound(),
            fj = new Sound(),
            jg1 = new Sound(),
            flak = new Sound(),
            flak2 = new Sound(),
            gauss = new Sound(),
            fissure = new Sound(),
            hugeBlast = new Sound(),
            largeBeam = new Sound(),
            hammer = new Sound(),
            hailRain = new Sound(),
            bigHailstoneHit = new Sound(),
            giantHailstoneHit = new Sound();

    public static void load() {
        ct1 = tree.loadSound("ct1");
        dbz1 = tree.loadSound("dbz1");
        dd1 = tree.loadSound("dd1");
        fj = tree.loadSound("fj");
        jg1 = tree.loadSound("jg1");
        flak = tree.loadSound("flak");
        flak2 = tree.loadSound("flak2");
        gauss = tree.loadSound("gauss");
        fissure = tree.loadSound("fissure");
        hugeBlast = tree.loadSound("hugeBlast");
        largeBeam = tree.loadSound("largeBeam");
        hammer = tree.loadSound("hammer");
        hailRain = tree.loadSound("hailRain");
        bigHailstoneHit = tree.loadSound("bigHailstoneHit");
        giantHailstoneHit = tree.loadSound("giantHailstoneHit");
    }
}
