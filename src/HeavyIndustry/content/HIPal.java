package HeavyIndustry.content;

import arc.graphics.Color;
import mindustry.content.Items;
import mindustry.graphics.Pal;

public class HIPal {
    public static Color
            breakdownYellow = HIStatusEffects.breakdown.color,
            echoFlameYellow = HIStatusEffects.echoFlame.color,
            ancient = Items.surgeAlloy.color.cpy().lerp(Pal.accent, 0.115f);
}
