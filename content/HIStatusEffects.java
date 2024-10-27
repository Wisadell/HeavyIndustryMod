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
public class HIStatusEffects {
    public static StatusEffect regenerating,breached,flamePoint,ultFireBurn;
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
    }
}
