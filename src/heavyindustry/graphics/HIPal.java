package heavyindustry.graphics;

import arc.graphics.*;
import heavyindustry.content.*;
import mindustry.content.*;
import mindustry.graphics.*;

import static arc.graphics.Color.*;

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
            winter = Color.valueOf("6ca5ff"),
            monolithLight = valueOf("c0ecff"),
            monolith = valueOf("87ceeb"),
            monolithDark = valueOf("6586b0"),
            monolithAtmosphere = valueOf("001e6360"),
            coldcolor = valueOf("6bc7ff"),
            heatcolor =  Pal.turretHeat,
            outline = Pal.darkerMetal,
            darkOutline = valueOf("38383d"),
            darkerOutline = valueOf("2e3142"),
            blueprintCol = valueOf("649fb7"),
            blueprintColAccent = valueOf("a6cad9"),
            bgCol = valueOf("323232"),
            bgColMid = valueOf("525252"),
            expLaser = valueOf("F9DBB1"),
            exp = valueOf("84ff00"),
            expMax = valueOf("90ff00"),
            expBack = valueOf("4d8f07"),
            lava = valueOf("ff2a00"),
            lava2 = valueOf("ffcc00"),
            dense = valueOf("ffbeb8"),
            dirium = valueOf("96f7c3"),
            diriumLight = valueOf("ccffe4"),
            coldColor = valueOf("6bc7ff"),
            deepRed = Color.valueOf("f25555"),
            deepBlue = Color.valueOf("554deb"),
            passive = valueOf("61caff"),
            armor = valueOf("e09e75"),
            lancerSap1 = Pal.lancerLaser.cpy().lerp(Pal.sapBullet, 0.167f),
            lancerSap2 = Pal.lancerLaser.cpy().lerp(Pal.sapBullet, 0.333f),
            lancerSap3 = Pal.lancerLaser.cpy().lerp(Pal.sapBullet, 0.5f),
            lancerSap4 = Pal.lancerLaser.cpy().lerp(Pal.sapBullet, 0.667f),
            lancerSap5 = Pal.lancerLaser.cpy().lerp(Pal.sapBullet, 0.833f),
            lancerDir1 = Pal.lancerLaser.cpy().lerp(diriumLight, 0.25f),
            lancerDir2 = Pal.lancerLaser.cpy().lerp(diriumLight, 0.5f),
            lancerDir3 = Pal.lancerLaser.cpy().lerp(diriumLight, 0.75f);

    /** HIPal should not be instantiated. */
    private HIPal() {}
}
