package heavyindustry.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.effect.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;

import static heavyindustry.core.HeavyIndustryMod.*;
import static mindustry.content.StatusEffects.*;

/**
 * Sets up content {@link StatusEffect status effects}. Loaded after every other content is instantiated.
 * @author Wisadell
 */
public final class HIStatusEffects {
    public static StatusEffect
            overheat,regenerating,breached,flamePoint,ultFireBurn,
            territoryFieldIncrease,territoryFieldSuppress;
    public static void load(){
        overheat = new StatusEffect("overheat"){{
            color = Color.valueOf("ffdcd8");
            disarm = true;
            dragMultiplier = 1f;
            speedMultiplier = 0.5f;
            damage = 5f;
            effectChance = 0.35f;
            effect = new ParticleEffect(){{
                lifetime = 30;
                length = 16;
                sizeFrom = 3;
                sizeTo = 0;
                colorFrom = Color.valueOf("ff5845");
                colorTo = color;
            }};
        }};
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
        }
            public boolean pierceArmor = false;

            @Override
            public void applied(Unit unit, float time, boolean extend) {
                if (unit.armor > 0f) {
                    pierceArmor = true;
                    unit.armor /= 2f;
                }
                super.applied(unit, time, extend);
            }

            @Override
            public void onRemoved(Unit unit) {
                if (pierceArmor) {
                    pierceArmor = false;
                    unit.armor *= 2f;
                }
            }
        };
        flamePoint = new StatusEffect("flame-point"){{
            damage = 0.2f;
            color = Pal.lightFlame;
            parentizeEffect = true;
            effect = new Effect(36, e -> {
                if(!(e.data instanceof Unit unit)) return;
                Lines.stroke(2 * e.foutpow(), Items.blastCompound.color);
                for(int i = 0; i < 3; i++){
                    float a = 360 / 3f * i + e.time * 6;
                    float x = HIGet.dx(e.x, Math.max(6, unit.hitSize / 2f), a), y = HIGet.dy(e.y, Math.max(6, unit.hitSize / 2f), a);
                    Lines.lineAngle(x, y, a - 120, Math.max(3, unit.hitSize / 4f) * e.foutpow());
                    Lines.lineAngle(x, y, a + 120, Math.max(3, unit.hitSize / 4f) * e.foutpow());
                }
            });
            speedMultiplier = 0.9f;
        }
            @Override
            public void update(Unit unit, float time) {
                if(damage > 0){
                    unit.damageContinuousPierce(damage);
                }else if(damage < 0){
                    unit.heal(-1f * damage * Time.delta);
                }

                if(effect != Fx.none && Mathf.chanceDelta(effectChance)){
                    effect.at(unit.x, unit.y, 0, color, parentizeEffect ? unit : null);
                }
            }
        };
        ultFireBurn = new StatusEffect("ult-fire-burn"){{
            color = Pal.techBlue;
            damage = 1.5f;
            speedMultiplier = 1.2f;
            effect = HIFx.ultFireBurn;
        }};
        territoryFieldIncrease = new StatusEffect("territory-field-increase"){{
            color = Color.valueOf("ea8878");
            buildSpeedMultiplier = 2;
            speedMultiplier = 1.3f;
            reloadMultiplier = 2;
            damage = -0.2f;
            effectChance = 0.07f;
            effect = Fx.overclocked;
        }};
        territoryFieldSuppress = new StatusEffect("territory-field-suppress"){{
            color = Color.valueOf("8b9bb4");
            speedMultiplier = 0.4f;
            reloadMultiplier = 0.5f;
            damage = 15/60f;
            effectChance = 0.07f;
            effect = Fx.overclocked;
        }};
    }
}
