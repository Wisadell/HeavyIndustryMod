package heavyindustry.content;

import heavyindustry.core.*;
import mindustry.content.Planets;
import mindustry.type.SectorPreset;

/** Defines the {@linkplain SectorPreset maps} this mod offers. */
public class HISectorPresets {
    public static SectorPreset
            //serpulo
            iceboundTributary,whiteoutPlains,desertWastes,snowyLands,sunkenPier,coastalCliffs,fallenStronghold,bombardmentWarzone;
    /** Instantiates all contents. Called in the main thread in {@link HeavyIndustryMod#loadContent()}. */
    public static void load(){
        iceboundTributary = new SectorPreset("iceboundTributary", Planets.serpulo, 99){{
            difficulty = 3;
            captureWave = 25;
        }};
        whiteoutPlains = new SectorPreset("whiteoutPlains", Planets.serpulo, 239){{
            difficulty = 4;
            captureWave = 30;
        }};
        desertWastes = new SectorPreset("desertWastes", Planets.serpulo, 82){{
            difficulty = 5;
        }};
        snowyLands = new SectorPreset("snowyLands", Planets.serpulo, 66){{
            difficulty = 6;
            captureWave = 45;
        }};
        sunkenPier = new SectorPreset("sunkenPier", Planets.serpulo, 111){{
            difficulty = 6;
            captureWave = 50;
        }};
        coastalCliffs = new SectorPreset("coastalCliffs", Planets.serpulo, 7){{
            difficulty = 8;
            captureWave = 35;
        }};
        fallenStronghold = new SectorPreset("fallenStronghold", Planets.serpulo, 225){{
            difficulty = 9;
            captureWave = 50;
        }};
        bombardmentWarzone = new SectorPreset("bombardmentWarzone", Planets.serpulo, 65){{
            difficulty = 11;
            captureWave = 145;
        }};
    }
}
