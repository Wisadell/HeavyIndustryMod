package heavyindustry.content;

import arc.graphics.*;
import arc.math.*;
import mindustry.entities.effect.*;
import mindustry.graphics.*;
import mindustry.type.*;

import static heavyindustry.core.HeavyIndustryMod.*;
import static mindustry.content.StatusEffects.*;

/**
 * Sets up content {@link StatusEffect status effects}. Loaded after every other content is instantiated.
 * @author Wisadell
 */
public class HIStatusEffects {
    public static StatusEffect regenerating,breached,ultFireBurn;
    public static void load(){
        regenerating = new StatusEffect("regenerating"){{
            color = Color.valueOf("97ffa8");
            damage = -4;
            effectChance = 0.3f;
            effect = new ParticleEffect(){{
                particles = 1;
                baseLength = lifetime = 30f;
                length = -30f;
                spin = 6f;
                interp = Interp.pow3Out;
                sizeInterp = Interp.pow3In;
                region = name("triangle");
                sizeFrom = 1f;
                sizeTo = 0f;
                colorFrom = colorTo = color;
            }};
            init(() -> opposite(sapped, slow, breached));
        }};
        breached = new StatusEffect("breached"){{
            color = Color.valueOf("666484");
            healthMultiplier = 0.9f;
            speedMultiplier = 0.8f;
            reloadMultiplier = 0.9f;
            transitionDamage = 220f;
            permanent = true;
        }};
        ultFireBurn = new StatusEffect("ult-fire-burn"){{
            color = Pal.techBlue;
            damage = 1.5f;
            speedMultiplier = 1.2f;
            effect = HIFx.ultFireBurn;
        }};
    }
}
