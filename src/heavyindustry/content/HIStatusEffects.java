package heavyindustry.content;

import arc.graphics.Color;
import arc.math.Interp;
import mindustry.content.StatusEffects;
import mindustry.entities.effect.ParticleEffect;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;

import static heavyindustry.core.HeavyIndustryMod.name;

public class HIStatusEffects {
    public static StatusEffect repair,armorReduction;
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
    }
}
