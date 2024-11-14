package heavyindustry.graphics;

import heavyindustry.content.*;
import arc.graphics.*;
import mindustry.content.*;
import mindustry.graphics.*;

public final class HIPal {
    /** Static read-only palettes that are used throughout the mod. */
    public static final Color
            miku = Color.valueOf("39c5bb"),
            nanocoreGreen = HIItems.nanocore.color,
            nanocoreErekirOrange = HIItems.nanocoreErekir.color,
            uraniumGrey = HIItems.uranium.color,
            chromiumGrey = HIItems.chromium.color,
            brightSteelBlue = Color.valueOf("b0c4de"),
            lightGrey = Color.valueOf("e3e3e3"),
            darkGrey = Color.valueOf("737373"),
            lightYellow = Color.valueOf("ffe176"),
            canaryYellow = Color.valueOf("feebb3"),
            orangeBack = Color.valueOf("ff7f24"),
            regenerating = HIStatusEffects.regenerating.color,
            ancient = Items.surgeAlloy.color.cpy().lerp(Pal.accent, 0.115f),
            ancientHeat = Color.red.cpy().mul(1.075f),
            ancientLight = ancient.cpy().lerp(Color.white, 0.7f),
            ancientLightMid = ancient.cpy().lerp(Color.white, 0.4f),
            thurmixRed = Color.valueOf("#ff9492"),
            thurmixRedLight = Color.valueOf("#ffced0"),
            thurmixRedDark = thurmixRed.cpy().lerp(Color.black, 0.9f),
            rainBowRed = Color.valueOf("ff8787"),
            cold = Color.valueOf("6bc7ff"),
            transColor = new Color(0, 0, 0, 0),
            fexCrystal = Color.valueOf("ff9584"),
            matrixNet = Color.valueOf("d3fdff"),
            matrixNetDark = Color.valueOf("9ecbcd"),
            ion = Color.valueOf("d1d19f"),
            dew = Color.valueOf("ff6214"),
            frost = Color.valueOf("aff7ff"),
            winter = Color.valueOf("6ca5ff");

}
