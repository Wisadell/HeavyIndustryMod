package heavyindustry.graphics;

import heavyindustry.content.*;
import arc.graphics.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.pattern.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.turrets.*;

import java.util.*;

import static mindustry.Vars.*;

public class HIPal {
    /** Static read-only palettes that are used throughout the mod. */
    public static final Color
            nanocoreGreen = HIItems.nanocore.color,
            nanocoreErekirOrange = HIItems.nanocoreErekir.color,
            uraniumGrey = HIItems.uranium.color,
            chromiumGrey = HIItems.chromium.color,
            iceBlue = Color.valueOf("c0ecff"),
            iceBlueDark = Color.valueOf("6d90bc"),
            ancient = Items.surgeAlloy.color.cpy().lerp(Pal.accent, 0.115f),
            ancientHeat = Color.red.cpy().mul(1.075f),
            ancientLight = ancient.cpy().lerp(Color.white, 0.7f),
            lightSky = Color.valueOf("#8db0ff"),
            lightSkyBack = lightSky.cpy().lerp(Color.white, 0.2f),
            lightSkyMiddle = lightSky.cpy().lerp(Color.white, 0.6f),
            lightSkyFront = lightSky.cpy().lerp(Color.white, 0.77f),
            thurmixRed = Color.valueOf("#ff9492"),
            thurmixRedLight = Color.valueOf("#ffced0"),
            thurmixRedDark = thurmixRed.cpy().lerp(Color.black, 0.9f),
            rainBowRed = Color.valueOf("ff8787");

}
