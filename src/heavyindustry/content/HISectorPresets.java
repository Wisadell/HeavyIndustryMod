package heavyindustry.content;

import mindustry.type.*;

import static mindustry.content.Planets.*;

/**
 * Defines the {@linkplain SectorPreset maps} this mod offers.
 * @author E-Nightingale
 */
public final class HISectorPresets {
    public static SectorPreset
            //serpulo
            volcanicArchipelago,ironBridgeCoast,moltenRiftValley,frozenPlateau
            //erekir
            ;

    /** HISectorPresets should not be instantiated. */
    private HISectorPresets() {}

    public static void load() {
        //serpulo
        volcanicArchipelago = new SectorPreset("volcanicArchipelago", serpulo, 111) {{
            captureWave = 55;
            difficulty = 10f;
        }};
        ironBridgeCoast = new SectorPreset("ironBridgeCoast", serpulo, 243) {{
            captureWave = 65;
            difficulty = 11f;
        }};
        moltenRiftValley = new SectorPreset("moltenRiftValley", serpulo, 125) {{
            captureWave = 65;
            difficulty = 11f;
        }};
        frozenPlateau = new SectorPreset("frozenPlateau", serpulo, 132) {{
            captureWave = 75;
            difficulty = 12f;
        }};
    }
}
