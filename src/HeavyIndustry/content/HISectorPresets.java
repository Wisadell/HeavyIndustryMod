package HeavyIndustry.content;

import mindustry.content.Planets;
import mindustry.type.SectorPreset;

public class HISectorPresets {
    public static SectorPreset
    //discord
            iceboundTributary, facility32m, whiteoutPlains, desertWastes, snowyLands, sunkenPier, fallenStronghold, bombardmentWarzone,
    //punk hazard
            chernobog;
    public static void load(){
        iceboundTributary = new SectorPreset("iceboundTributary", Planets.serpulo, 99){{
            difficulty = 3;
            captureWave = 25;
        }};
        facility32m = new SectorPreset("facility32m", Planets.serpulo, 170){{
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
        fallenStronghold = new SectorPreset("fallenStronghold", Planets.serpulo, 225){{
            difficulty = 9;
            captureWave = 50;
        }};
        bombardmentWarzone = new SectorPreset("bombardmentWarzone", Planets.serpulo, 65){{
            difficulty = 11;
            captureWave = 145;
        }};
        //punk hazard
        chernobog = new SectorPreset("chernobog", HIPlanets.punkHazard, 0){{
            difficulty = 2;
            captureWave = 100;
        }};
    }
}
