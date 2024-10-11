package heavyindustry.content;

import mindustry.type.*;

import static mindustry.content.Planets.*;

/**
 * Defines the {@linkplain SectorPreset maps} this mod offers.
 * @author Wisadell
 */
public class HISectorPresets {
    public static SectorPreset
            //serpulo
            iceboundTributary,whiteoutPlains,desertWastes,snowyLands,sunkenPier,coastalCliffs,fallenStronghold,bombardmentWarzone
            //erekir
            ;

    public static void load(){
        iceboundTributary = new SectorPreset("iceboundTributary", serpulo, 99){{
            difficulty = 3;
            captureWave = 25;
        }};
        whiteoutPlains = new SectorPreset("whiteoutPlains", serpulo, 239){{
            difficulty = 4;
            captureWave = 30;
        }};
        desertWastes = new SectorPreset("desertWastes", serpulo, 82){{
            difficulty = 5;
        }};
        snowyLands = new SectorPreset("snowyLands", serpulo, 66){{
            difficulty = 6;
            captureWave = 45;
        }};
        sunkenPier = new SectorPreset("sunkenPier", serpulo, 111){{
            difficulty = 6;
            captureWave = 50;
        }};
        coastalCliffs = new SectorPreset("coastalCliffs", serpulo, 7){{
            difficulty = 8;
            captureWave = 35;
        }};
        fallenStronghold = new SectorPreset("fallenStronghold", serpulo, 225){{
            difficulty = 9;
            captureWave = 50;
        }};
        bombardmentWarzone = new SectorPreset("bombardmentWarzone", serpulo, 65){{
            difficulty = 11;
            captureWave = 145;
        }};
    }
}
