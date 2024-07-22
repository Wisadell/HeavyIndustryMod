package HeavyIndustry.content;

import arc.Events;
import arc.graphics.Color;
import arc.math.Interp;
import mindustry.content.StatusEffects;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.ParticleEffect;
import mindustry.game.EventType;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;

import static HeavyIndustry.HeavyIndustryMod.name;
import static mindustry.Vars.state;

public class HIStatusEffects {
    public static StatusEffect nanoRepair,armorReduction,breakdown,echoFlame;
    public static void load(){
        nanoRepair = new StatusEffect("nano-repair"){{
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
                unit.armor -= 0.005f;
            }
        };
        breakdown = new StatusEffect("breakdown"){{
            color = Color.valueOf("feebb3");
            damage = 2;
            healthMultiplier = 0.95f;
            speedMultiplier = 0.85f;
            dragMultiplier = 1.14f;
            effectChance = 0.1f;
            effect = new MultiEffect(new ParticleEffect(){{
                particles = 2;
                baseLength = sizeTo = 0f;
                length = 25f;
                lifetime = 10f;
                sizeFrom = 2f;
                colorFrom = colorTo = Color.valueOf("feebb3");
            }}, new ParticleEffect(){{
                particles = 2;
                line = true;
                strokeTo = lenTo = baseLength = 0f;
                interp = Interp.slowFast;
                strokeFrom = 2f;
                lenFrom = lifetime = 10f;
                length = 23f;
                colorFrom = colorTo = Color.valueOf("feebb3");
            }});
        }};
        echoFlame = new StatusEffect("echo-flame"){{
            color = Color.valueOf("f2ff9c");
            damage = 40f;
            transitionDamage = 3600f;
            dragMultiplier = 1.8f;
            effect = new MultiEffect(new ParticleEffect(){{
                particles = 1;
                baseLength = 25;
                length = 35;
                lifetime = 60;
                spin = 5;
                interp = Interp.fastSlow;
                region = name("triangle");
                sizeFrom = 16;
                sizeTo = 0;
                colorFrom = colorTo = Color.valueOf("f2ff9c");
            }}, new ParticleEffect(){{
                particles = 3;
                baseLength = sizeTo = 0;
                length = 125;
                lifetime = 30;
                sizeFrom = 9;
                interp = Interp.fastSlow;
                colorFrom = Color.valueOf("f2ff9c80");
                colorTo = Color.valueOf("f2ff9c");
            }});
            init(() -> {
                affinity(breakdown, (unit, result, time) -> {
                    float pierceFraction = 0.3f;

                    unit.damagePierce(transitionDamage * pierceFraction);
                    unit.damage(transitionDamage * (1f - pierceFraction));
                    if(unit.team == state.rules.waveTeam){
                        Events.fire(EventType.Trigger.shock);
                    }
                });
                opposite(StatusEffects.freezing, StatusEffects.wet, StatusEffects.sporeSlowed);
            });
        }};
    }
}
