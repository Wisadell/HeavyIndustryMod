package heavyindustry.content;

import arc.graphics.*;
import heavyindustry.core.*;
import heavyindustry.entities.bullet.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.gen.*;
import mindustry.graphics.Pal;

/**
 * Defines the {@linkplain BulletType bullet} this mod offers.
 * @author Wisadell
 */
public class HIBullets {
    public static BulletType hyperBlast,hyperBlastLinker;

    /** Instantiates all contents. Called in the main thread in {@link HeavyIndustryMod#loadContent()}. */
    public static void load(){
        hyperBlast = new BasicBulletType(3.3f, 400){{
            lifetime = 60;

            trailLength = 15;
            drawSize = 250f;

            drag = 0.0075f;

            despawnEffect = hitEffect = HIFx.lightningHitLarge(Pal.techBlue);
            knockback = 12f;
            width = 15f;
            height = 37f;
            splashDamageRadius = 40f;
            splashDamage = lightningDamage = damage * 0.75f;
            backColor = lightColor = lightningColor = trailColor = Pal.techBlue;
            frontColor = Color.white;
            lightning = 3;
            lightningLength = 8;
            smokeEffect = Fx.shootBigSmoke2;
            trailChance = 0.6f;
            trailEffect = HIFx.trailToGray;
            hitShake = 3f;
            hitSound = Sounds.plasmaboom;
        }};
        hyperBlastLinker = new LightningLinkerBulletType(){{
            effectLightningChance = 0.15f;
            damage = 220;
            backColor = trailColor = lightColor = lightningColor = hitColor = Pal.techBlue;
            size = 8f;
            frontColor = Pal.techBlue.cpy().lerp(Color.white, 0.25f);
            range = 200f;

            trailWidth = 8f;
            trailLength = 20;

            speed = 5f;

            linkRange = 280f;

            maxHit = 8;
            drag = 0.085f;
            hitSound = Sounds.explosionbig;
            splashDamageRadius = 120f;
            splashDamage = lightningDamage = damage / 4f;
            lifetime = 50f;

            scaleLife = false;

            despawnEffect = HIFx.lightningHitLarge(hitColor);
            hitEffect = new MultiEffect(HIFx.hitSpark(backColor, 65f, 22, splashDamageRadius, 4, 16), HIFx.blast(backColor, splashDamageRadius / 2f));
            shootEffect = HIFx.hitSpark(backColor, 45f, 12, 60, 3, 8);
            smokeEffect = HIFx.hugeSmokeGray;
        }};
    }
}
