package heavyindustry.content;

import arc.graphics.*;
import arc.math.*;
import mindustry.content.*;
import mindustry.entities.effect.*;
import mindustry.gen.*;
import mindustry.graphics.Pal;
import mindustry.type.*;

import static heavyindustry.core.HeavyIndustryMod.*;

/**
 * Sets up content {@link StatusEffect status effects}. Loaded after every other content is instantiated.
 * @author Wisadell
 */
public class HIStatusEffects {
    public static StatusEffect repair,armorReduction,ultFireBurn;
    public static void load(){
        repair = new StatusEffect("repair"){{
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
            init(() -> opposite(StatusEffects.slow, armorReduction));
        }};
        armorReduction = new StatusEffect("armor-reduction"){{
            color = Color.valueOf("666484");
            healthMultiplier = 0.9f;
            speedMultiplier = 0.8f;
            reloadMultiplier = 0.9f;
            transitionDamage = 220f;
            permanent = true;
        }
            @Override
            public void update(Unit unit, float time){
                super.update(unit, time);
                if (unit.armor > 0f) unit.armor /= 2f;
            }
        };
        ultFireBurn = new StatusEffect("ult-fire-burn"){{
            color = Pal.techBlue;
            damage = 1.5f;
            speedMultiplier = 1.2f;
            effect = HIFx.ultFireBurn;
        }};
    }
}
