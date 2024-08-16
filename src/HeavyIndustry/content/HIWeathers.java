package HeavyIndustry.content;

import static HeavyIndustry.HeavyIndustryMod.name;

import HeavyIndustry.gen.*;
import HeavyIndustry.type.weather.*;
import HeavyIndustry.entities.bullet.*;
import arc.graphics.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.meta.*;

public class HIWeathers {
    public static Weather hailStone,wind;
    public static void load(){
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
    }
}
