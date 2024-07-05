package HeavyIndustry.content;

import arc.graphics.Color;
import mindustry.type.Item;

public class HIItems {
    public static Item rareEarth = new Item("rare-earth", Color.valueOf("b1bd99")){{
        hardness = 1;
        radioactivity = 0.1f;
        buildable = false;
    }};
    public static Item nanocore = new Item("nanocore", Color.valueOf("76d081")){{
        cost = -0.75f;
        hardness = 6;
        flammability = 9f;
        alwaysUnlocked = false;
    }};
    public static Item highEnergyFabric = new Item("high-energy-fabric", Color.valueOf("eec591")){{
        cost = 1.8f;
        healthScaling = 0.25f;
        radioactivity = 4;
        explosiveness = 1.8f;
    }};
    public static Item uranium = new Item("uranium", Color.valueOf("a5b2c2")){{
        cost = 4;
        hardness = 7;
        healthScaling = 1.4f;
        radioactivity = 2f;
        alwaysUnlocked = false;
    }};
    public static Item chromium = new Item("chromium", Color.valueOf("8f94b3")){{
        cost = 12;
        hardness = 8;
        healthScaling = 1.8f;
        alwaysUnlocked = false;
    }};
    public static Item heavyAlloy = new Item("heavy-alloy", Color.valueOf("686b7b")){{
        cost = 9;
        hardness = 9;
        healthScaling = 2.2f;
        alwaysUnlocked = false;
    }};
}
