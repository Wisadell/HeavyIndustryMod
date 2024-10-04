package heavyindustry.content;

import heavyindustry.gen.*;
import heavyindustry.type.weather.*;
import heavyindustry.entities.bullet.*;
import arc.graphics.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.weather.*;
import mindustry.world.meta.*;

import static heavyindustry.core.HeavyIndustryMod.*;

/**
 * Defines the {@linkplain Weather weather} this mod offers.
 * @author Wisadell
 */
public class HIWeathers {
    public static Weather rockfall,hailStone,wind,blizzard;
    public static void load(){
        rockfall = new HailStormWeather("rockfall"){{
            drawParticles = inBounceCam = drawNoise = false;
            duration = 15f * Time.toMinutes;
            bulletChange = 0.5f;
            soundVol = 0.05f;
            sound = HISounds.hailRain;
            setBullets(
                    new HailStoneBulletType(name("rockfall-giant"), 1){{
                        hitEffect = Fx.explosion.layer(Layer.power);
                        hitSound = HISounds.giantHailstoneHit;
                        hitSoundVolume = 6;
                        despawnEffect = Fx.none;
                        damage = 2000f;
                        splashDamage = 4000f;
                        splashDamageRadius = 116;
                        fallTime = 200f;
                        hitShake = 40f;
                    }}, 1/800f,
                    new HailStoneBulletType(name("rockfall-big"), 3){{
                        hitEffect = Fx.explosion.layer(Layer.power);
                        hitSound = HISounds.bigHailstoneHit;
                        hitSoundVolume = 0.2f;
                        despawnEffect = HIFx.staticStone;
                        damage = splashDamage = 95f;
                        splashDamageRadius = 40f;
                        canCollideFalling = pierce = true;
                        fallingDamage = 120f;
                        fallingRadius = 30f;
                        minDistanceFallingCollide = 15f;
                        hitFallingEffect = HIFx.bigExplosionStone;
                        hitFallingColor = Color.valueOf("5e9098");
                    }}, 1/200f,
                    new HailStoneBulletType(name("rockfall-middle"), 2){{
                        hitEffect = Fx.dynamicWave.layer(Layer.power);
                        despawnEffect = HIFx.fellStone;
                        damage = splashDamage = 10f;
                        splashDamageRadius = 25f;
                        canCollideFalling = true;
                        fallingDamage = 25f;
                        fallingRadius = 15f;
                        minDistanceFallingCollide = 5f;
                        hitFallingEffect = HIFx.explosionStone;
                        hitFallingColor = Color.valueOf("5e9098");
                    }}, 1/6f,
                    new HailStoneBulletType(name("rockfall-small"), 5){{
                        hitEffect = Fx.none;
                        despawnEffect = HIFx.fellStone;
                        splashDamage = 0f;
                        splashDamageRadius = 0;
                    }}, 1f
            );
        }};
        hailStone = new HailStormWeather("hail-storm"){{
            attrs.set(Attribute.light, -0.5f);
            drawParticles = inBounceCam = drawNoise = false;
            duration = 15f * Time.toMinutes;
            bulletChange = 0.5f;
            soundVol = 0.05f;
            sound = HISounds.hailRain;
            setBullets(
                    new HailStoneBulletType(name("hailstone-big"), 3){{
                        hitEffect = Fx.explosion.layer(Layer.power);
                        hitSound = HISounds.bigHailstoneHit;
                        hitSoundVolume = 0.2f;
                        despawnEffect = HIFx.staticStone;
                        splashDamage = 95f;
                        splashDamageRadius = 40f;
                        canCollideFalling = pierce = true;
                        fallingDamage = 120f;
                        fallingRadius = 30f;
                        minDistanceFallingCollide = 15f;
                        hitFallingEffect = HIFx.bigExplosionStone;
                        hitFallingColor = Color.valueOf("5867ac");
                    }}, 1 / 1600f,
                    new HailStoneBulletType(name("hailstone-middle"), 2){{
                        hitEffect = Fx.dynamicWave.layer(Layer.power);
                        despawnEffect = HIFx.fellStone;
                        splashDamage = 10f;
                        splashDamageRadius = 25f;
                        canCollideFalling = true;
                        fallingDamage = 25f;
                        fallingRadius = 15f;
                        minDistanceFallingCollide = 5f;
                        hitFallingEffect = HIFx.explosionStone;
                        hitFallingColor = Color.valueOf("5867ac");
                    }}, 1 / 12f,
                    new HailStoneBulletType(name("hailstone-small"), 5){{
                        hitEffect = Fx.none;
                        despawnEffect = HIFx.fellStone;
                        splashDamage = 0f;
                        splashDamageRadius = 0;
                    }}, 1f
            );
        }};
        wind = new EffectWeather("wind"){{
            weatherFx = HIFx.windTail;
            particleRegion = "particle";
            sizeMax = 5f;
            sizeMin = 1f;
            density = 1600;
            baseSpeed = 5.4f;
            minAlpha = 0.05f;
            maxAlpha = 0.18f;
            force = 0.1f;
            sound = Sounds.wind2;
            soundVol = 0.8f;
            maxSpawn = 2;
            duration = 8f * Time.toMinutes;
        }};
        blizzard = new ParticleWeather("blizzard"){{
            particleRegion = "particle";
            sizeMax = 14f;
            sizeMin = 3f;
            density = 600f;
            baseSpeed = 15f;
            yspeed = -2.5f;
            xspeed = 8f;
            minAlpha = 0.75f;
            maxAlpha = 0.9f;
            sound = Sounds.windhowl;
            soundVol = 0.25f;
            soundVolOscMag = 1.5f;
            soundVolOscScl = 1100f;
            soundVolMin = 0.15f;
        }};
    }
}
