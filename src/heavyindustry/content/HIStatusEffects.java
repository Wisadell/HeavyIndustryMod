package heavyindustry.content;

import heavyindustry.util.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;

import static mindustry.content.StatusEffects.*;

/**
 * Sets up content {@link StatusEffect status effects}. Loaded after every other content is instantiated.
 * @author Wisadell
 */
public final class HIStatusEffects {
    public static StatusEffect
            overheat,regenerating,breached,flamePoint,ultFireBurn,
            territoryFieldIncrease,territoryFieldSuppress;

    /** HIStatusEffects should not be instantiated. */
    private HIStatusEffects() {}

    public static void load() {
        overheat = new StatusEffect("overheat") {{
            color = Color.valueOf("ffdcd8");
            disarm = true;
            dragMultiplier = 1f;
            speedMultiplier = 0.5f;
            damage = 5f;
            effectChance = 0.35f;
            effect = HIFx.glowParticle;
        }};
        regenerating = new StatusEffect("regenerating") {{
            color = Color.valueOf("97ffa8");
            damage = -4;
            effectChance = 0.3f;
            effect = HIFx.glowParticle;
            init(() -> opposite(sapped, slow, breached));
        }};
        breached = new StatusEffect("breached") {{
            color = Color.valueOf("666484");
            healthMultiplier = 0.9f;
            speedMultiplier = 0.8f;
            reloadMultiplier = 0.9f;
            transitionDamage = 220f;
            permanent = true;
        }};
        flamePoint = new StatusEffect("flame-point") {{
            damage = 0.2f;
            color = Pal.lightFlame;
            parentizeEffect = true;
            effect = new Effect(36, e -> {
                if (!(e.data instanceof Unit unit)) return;
                Lines.stroke(2 * e.foutpow(), Items.blastCompound.color);
                for (int i = 0; i < 3; i++) {
                    float a = 360 / 3f * i + e.time * 6;
                    float x = Utils.dx(e.x, Math.max(6, unit.hitSize / 2f), a), y = Utils.dy(e.y, Math.max(6, unit.hitSize / 2f), a);
                    Lines.lineAngle(x, y, a - 120, Math.max(3, unit.hitSize / 4f) * e.foutpow());
                    Lines.lineAngle(x, y, a + 120, Math.max(3, unit.hitSize / 4f) * e.foutpow());
                }
            });
            speedMultiplier = 0.9f;
        }
            @Override
            public void update(Unit unit, float time) {
                if (damage > 0) {
                    unit.damageContinuousPierce(damage);
                } else if (damage < 0) {
                    unit.heal(-1f * damage * Time.delta);
                }

                if (effect != Fx.none && Mathf.chanceDelta(effectChance)) {
                    effect.at(unit.x, unit.y, 0, color, parentizeEffect ? unit : null);
                }
            }
        };
        ultFireBurn = new StatusEffect("ult-fire-burn") {{
            color = Pal.techBlue;
            damage = 15f;
            speedMultiplier = 1.2f;
            effect = HIFx.ultFireBurn;
        }};
        territoryFieldIncrease = new StatusEffect("territory-field-increase") {{
            color = Color.valueOf("ea8878");
            buildSpeedMultiplier = 1.5f;
            speedMultiplier = 1.1f;
            reloadMultiplier = 1.2f;
            damage = -0.2f;
            effectChance = 0.07f;
            effect = Fx.overclocked;
        }};
        territoryFieldSuppress = new StatusEffect("territory-field-suppress") {{
            color = Color.valueOf("8b9bb4");
            speedMultiplier = 0.85f;
            reloadMultiplier = 0.8f;
            damage = 15 / 60f;
            effectChance = 0.07f;
            effect = Fx.overclocked;
        }};
    }
}
