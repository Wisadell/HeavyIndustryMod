package heavyindustry.gen;

import arc.audio.*;

import static mindustry.Vars.*;

public final class HISounds {
    public static Sound
            ct1 = new Sound(),
            dbz1 = new Sound(),
            dd1 = new Sound(),
            fj = new Sound(),
            jg1 = new Sound(),
            flak = new Sound(),
            flak2 = new Sound(),
            launch = new Sound(),
            gauss = new Sound(),
            fissure = new Sound(),
            blaster = new Sound(),
            hugeShoot = new Sound(),
            hugeBlast = new Sound(),
            largeBeam = new Sound(),
            metalpipe = new Sound(),
            metalWalk = new Sound(),
            alert2 = new Sound(),
            hammer = new Sound(),
            coolingFan = new Sound(),
            hailRain = new Sound(),
            bigHailstoneHit = new Sound(),
            giantHailstoneHit = new Sound();

    /**
     * HISounds should not be instantiated.
     */
    private HISounds() {}

    public static void load() {
        ct1 = tree.loadSound("ct1");
        dbz1 = tree.loadSound("dbz1");
        dd1 = tree.loadSound("dd1");
        fj = tree.loadSound("fj");
        jg1 = tree.loadSound("jg1");
        flak = tree.loadSound("flak");
        flak2 = tree.loadSound("flak2");
        launch = tree.loadSound("launch");
        gauss = tree.loadSound("gauss");
        fissure = tree.loadSound("fissure");
        blaster = tree.loadSound("blaster");
        hugeShoot = tree.loadSound("hugeShoot");
        hugeBlast = tree.loadSound("hugeBlast");
        largeBeam = tree.loadSound("largeBeam");
        metalpipe = tree.loadSound("metalpipe");
        metalWalk = tree.loadSound("metalWalk");
        alert2 = tree.loadSound("alert2");
        hammer = tree.loadSound("hammer");
        coolingFan = tree.loadSound("coolingFan");
        hailRain = tree.loadSound("hailRain");
        bigHailstoneHit = tree.loadSound("bigHailstoneHit");
        giantHailstoneHit = tree.loadSound("giantHailstoneHit");
    }
}
