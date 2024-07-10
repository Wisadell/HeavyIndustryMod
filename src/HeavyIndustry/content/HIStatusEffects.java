package HeavyIndustry.content;

import arc.graphics.Color;
import arc.math.Interp;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.ParticleEffect;
import mindustry.type.StatusEffect;

import static HeavyIndustry.HeavyIndustryMod.name;

public class HIStatusEffects {
    public static StatusEffect breakdown,echoFlame;
    public static void load(){
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
            damage = 70f;
            transitionDamage = 4300f;
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
        }};
    }
}
