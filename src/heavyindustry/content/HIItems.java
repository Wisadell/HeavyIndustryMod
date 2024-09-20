package heavyindustry.content;

import arc.graphics.*;
import mindustry.type.*;

/** Defines the {@linkplain Item item} this mod offers. */
public class HIItems {
    public static Item
            rareEarth,
            nanocore,highEnergyFabric,
            uranium,chromium,heavyAlloy;
    public static void load(){
        rareEarth = new Item("rare-earth", Color.valueOf("b1bd99")){{
            hardness = 1;
            radioactivity = 0.1f;
            buildable = false;
            lowPriority = true;
        }};
        nanocore = new Item("nanocore", Color.valueOf("76d081")){{
            cost = -0.75f;
            hardness = 6;
        }};
        highEnergyFabric = new Item("high-energy-fabric", Color.valueOf("eec591")){{
            cost = 1.8f;
            hardness = 3;
            healthScaling = 0.25f;
            radioactivity = 4;
        }};
        uranium = new Item("uranium", Color.valueOf("a5b2c2")){{
            cost = 4;
            hardness = 7;
            healthScaling = 1.4f;
            radioactivity = 2f;
        }};
        chromium = new Item("chromium", Color.valueOf("8f94b3")){{
            cost = 12;
            hardness = 8;
            healthScaling = 1.8f;
        }};
        heavyAlloy = new Item("heavy-alloy", Color.valueOf("686b7b")){{
            cost = 9;
            hardness = 10;
            healthScaling = 2.2f;
            radioactivity = 0.1f;
        }};
    }
}