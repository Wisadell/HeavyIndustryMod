package HeavyIndustry.content;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Interp;
import arc.math.Mathf;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.content.*;
import mindustry.entities.Effect;
import mindustry.entities.abilities.MoveEffectAbility;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.gen.*;
import mindustry.graphics.Trail;
import mindustry.type.*;
import mindustry.type.ammo.PowerAmmoType;
import mindustry.type.unit.MissileUnitType;

import static HeavyIndustry.HeavyIndustryMod.name;
import static arc.graphics.g2d.Draw.color;
import static mindustry.Vars.headless;

public class HIBullets {
    public static BulletType
            rocketLauncherBullet1,rocketLauncherBullet2,rocketLauncherBullet3,multipleRocketLauncherBullet1,multipleRocketLauncherBullet2,multipleRocketLauncherBullet3,
            largeRocketLauncherBullet1,largeRocketLauncherBullet2,rocketSiloBullet1,rocketSiloBullet2,rocketSiloBullet3,rocketSiloBullet4,caelumBullet1,caelumBullet2,
            cloudbreakerBullet1,cloudbreakerBullet2,cloudbreakerBullet3,
            furnaceBullet1,furnaceBullet2,thermoelectricIonBullet1,fiammettaBullet1,fiammettaBullet2,
            tracerBullet1,shadowBullet1,shadowBullet2;
    public static void load(){
        rocketLauncherBullet1 = new MissileBulletType(8f, 30f){{
            splashDamage = 15f;
            splashDamageRadius = 18f;
            lifetime = 25f;
            sprite = name("rocket");
            backColor = Color.valueOf("b0c4de");
            frontColor = Color.valueOf("e3e3e3");
            trailColor = Color.valueOf("b0c4de");
            homingPower = 0.03f;
            homingRange = 40f;
            width = 4f;
            height = 16f;
            ammoMultiplier = 2f;
            hitEffect = Fx.flakExplosion;
        }};
        rocketLauncherBullet2 = new MissileBulletType(8f, 18f){{
            splashDamage = 36f;
            splashDamageRadius = 24f;
            lifetime = 25f;
            sprite = name("rocket");
            backColor = Color.valueOf("ffb90f");
            frontColor = Color.valueOf("e3e3e3");
            trailColor = Color.valueOf("ffb90f");
            homingPower = 0.03f;
            homingRange = 40f;
            width = 4f;
            height = 16f;
            ammoMultiplier = 2f;
            hitEffect = Fx.flakExplosionBig;
        }};
        rocketLauncherBullet3 = new MissileBulletType(8f, 16f){{
            splashDamage = 47f;
            splashDamageRadius = 32f;
            lifetime = 25f;
            sprite = name("rocket");
            backColor = Color.valueOf("ff7f24");
            frontColor = Color.valueOf("e3e3e3");
            trailColor = Color.valueOf("ff7f24");
            homingPower = 0.03f;
            homingRange = 40f;
            width = 4f;
            height = 16f;
            ammoMultiplier = 2f;
            hitEffect = Fx.flakExplosionBig;
        }};
        multipleRocketLauncherBullet1 = new MissileBulletType(9f, 30f){{
            splashDamage = 15f;
            splashDamageRadius = 18f;
            lifetime = 35f;
            sprite = name("rocket");
            backColor = Color.valueOf("b0c4de");
            frontColor = Color.valueOf("e3e3e3");
            trailColor = Color.valueOf("b0c4de");
            homingPower = 0.03f;
            homingRange = 40f;
            width = 4f;
            height = 16f;
            ammoMultiplier = 2f;
            hitEffect = Fx.flakExplosion;
        }};
        multipleRocketLauncherBullet2 = new MissileBulletType(9f, 18f){{
            splashDamage = 36f;
            splashDamageRadius = 24f;
            lifetime = 35f;
            sprite = name("rocket");
            backColor = Color.valueOf("ffb90f");
            frontColor = Color.valueOf("e3e3e3");
            trailColor = Color.valueOf("ffb90f");
            homingPower = 0.03f;
            homingRange = 40f;
            width = 4f;
            height = 16f;
            ammoMultiplier = 2f;
            hitEffect = Fx.flakExplosionBig;
        }};
        multipleRocketLauncherBullet3 = new MissileBulletType(9f, 16f){{
            splashDamage = 47f;
            splashDamageRadius = 32f;
            lifetime = 35f;
            sprite = name("rocket");
            backColor = Color.valueOf("ff7f24");
            frontColor = Color.valueOf("e3e3e3");
            trailColor = Color.valueOf("ff7f24");
            homingPower = 0.03f;
            homingRange = 40f;
            width = 4f;
            height = 16f;
            ammoMultiplier = 2f;
            hitEffect = Fx.flakExplosionBig;
        }};
        largeRocketLauncherBullet1 = new MissileBulletType(10f, 55f){{
            shrinkY = 0;
            inaccuracy = 4;
            trailChance = 0.8f;
            homingRange = 80;
            splashDamage = 85f;
            splashDamageRadius = 36f;
            lifetime = 32;
            hitShake = 2;
            sprite = name("large-missile");
            backColor = Color.valueOf("ffb90f");
            frontColor = Color.valueOf("e3e3e3");
            trailColor = Color.valueOf("ffb90f");
            status = StatusEffects.burning;
            statusDuration = 600;
            width = 16;
            height = 40;
            ammoMultiplier = 3;
            shootEffect = new MultiEffect(
                    Fx.shootBig2,
                    Fx.shootPyraFlame,
                    Fx.shootPyraFlame
            );
            despawnEffect = Fx.flakExplosion;
            hitEffect = new MultiEffect(
                    new ParticleEffect(){{
                        particles = 8;
                        sizeFrom = 8;
                        sizeTo = 0;
                        length = 15;
                        baseLength = 15;
                        lifetime = 35;
                        colorFrom = colorTo = Color.valueOf("737373");
                    }},
                    new ParticleEffect(){{
                        particles = 12;
                        line = true;
                        length = 30;
                        baseLength = 8;
                        lifetime = 22;
                        colorFrom = Color.white;
                        colorTo = Color.valueOf("ffe176");
                    }},
                    new WaveEffect(){{
                        lifetime = 10;
                        sizeFrom = 1;
                        sizeTo = 36;
                        strokeFrom = 8;
                        strokeTo = 0;
                        colorFrom = Color.valueOf("ffe176");
                        colorTo = Color.valueOf("ffe176");
                    }}
            );
        }};
        largeRocketLauncherBullet2 = new MissileBulletType(10f, 58f){{
            shrinkY = 0;
            inaccuracy = 4;
            trailChance = 0.8f;
            homingRange = 80;
            splashDamage = 166f;
            splashDamageRadius = 76f;
            lifetime = 32;
            hitShake = 2;
            sprite = name("large-missile");
            backColor = Color.valueOf("ff7f24");
            frontColor = Color.valueOf("e3e3e3");
            trailColor = Color.valueOf("ff7f24");
            status = StatusEffects.burning;
            statusDuration = 600;
            width = 16;
            height = 40;
            ammoMultiplier = 3;
            shootEffect = new MultiEffect(
                    Fx.shootBig2,
                    Fx.shootPyraFlame,
                    Fx.shootPyraFlame
            );
            despawnEffect = Fx.flakExplosion;
            hitEffect = new MultiEffect(
                    new ParticleEffect(){{
                        particles = 8;
                        sizeFrom = 8;
                        sizeTo = 0;
                        length = 15;
                        baseLength = 15;
                        lifetime = 35;
                        colorFrom = colorTo = Color.valueOf("737373");
                    }},
                    new ParticleEffect(){{
                        particles = 12;
                        line = true;
                        length = 30;
                        baseLength = 8;
                        lifetime = 22;
                        colorFrom = Color.white;
                        colorTo = Color.valueOf("ffe176");
                    }},
                    new WaveEffect(){{
                        lifetime = 10;
                        sizeFrom = 1;
                        sizeTo = 36;
                        strokeFrom = 8;
                        strokeTo = 0;
                        colorFrom = colorTo = Color.valueOf("ffe176");
                    }}
            );
        }};
        rocketSiloBullet1 = new MissileBulletType(8f, 22f){{
            buildingDamageMultiplier = 0.3f;
            splashDamage = 15f;
            splashDamageRadius = 18f;
            knockback = 0.7f;
            lifetime = 135f;
            homingDelay = 10f;
            homingRange = 800f;
            homingPower = 0.15f;
            sprite = name("missile");
            backColor = Color.valueOf("b0c4de");
            frontColor = Color.valueOf("e3e3e3");
            trailLength = 15;
            trailWidth = 1.5f;
            trailColor = Color.valueOf("ffffff80");
            trailEffect = Fx.none;
            width = 10f;
            height = 40f;
            hitShake = 1f;
            ammoMultiplier = 2f;
            smokeEffect = Fx.shootSmallFlame;
            hitEffect = Fx.flakExplosion;
        }};
        rocketSiloBullet2 = new MissileBulletType(7f, 14f){{
            buildingDamageMultiplier = 0.3f;
            splashDamage = 39f;
            splashDamageRadius = 32f;
            status = StatusEffects.burning;
            statusDuration = 600;
            makeFire = true;
            lifetime = 145f;
            homingPower = 0.15f;
            homingDelay = 10f;
            homingRange = 800f;
            sprite = name("missile");
            backColor = Color.valueOf("ffb90f");
            frontColor = Color.valueOf("e3e3e3");
            trailLength = 15;
            trailWidth = 1.5f;
            trailColor = Color.valueOf("ffffff80");
            trailEffect = Fx.none;
            width = 10f;
            height = 40f;
            hitShake = 1f;
            ammoMultiplier = 2f;
            smokeEffect = Fx.shootSmallFlame;
            hitEffect = Fx.flakExplosionBig;
        }};
        rocketSiloBullet3 = new MissileBulletType(7f, 17f){{
            buildingDamageMultiplier = 0.3f;
            splashDamage = 55f;
            splashDamageRadius = 45f;
            knockback = 3;
            status = StatusEffects.blasted;
            lifetime = 145f;
            homingPower = 0.15f;
            homingDelay = 10f;
            homingRange = 800f;
            sprite = name("missile");
            backColor = Color.valueOf("ff7055");
            frontColor = Color.valueOf("e3e3e3");
            trailLength = 15;
            trailWidth = 1.5f;
            trailColor = Color.valueOf("ffffff80");
            trailEffect = Fx.none;
            width = 10f;
            height = 40f;
            hitShake = 1f;
            ammoMultiplier = 2f;
            smokeEffect = Fx.shootSmallFlame;
            hitEffect = Fx.flakExplosionBig;
        }};
        rocketSiloBullet4 = new MissileBulletType(9f, 47f){{
            buildingDamageMultiplier = 0.3f;
            splashDamage = 75f;
            splashDamageRadius = 45f;
            lightningDamage = 17;
            lightning = 3;
            lightningLength = 8;
            knockback = 3;
            status = StatusEffects.blasted;
            lifetime = 125f;
            homingPower = 0.15f;
            homingDelay = 10f;
            homingRange = 800f;
            sprite = name("missile");
            backColor = Color.valueOf("f2e770");
            frontColor = Color.valueOf("e3e3e3");
            trailLength = 16;
            trailWidth = 2.5f;
            trailColor = Color.valueOf("ffffff80");
            trailEffect =Fx.none;
            width = 13f;
            height = 48f;
            hitShake = 1f;
            ammoMultiplier = 3f;
            smokeEffect =Fx.shootSmallFlame;
            hitEffect = Fx.flakExplosionBig;
        }};
        caelumBullet1 = new FlakBulletType(6f, 33f){{
            splashDamageRadius = 64f;
            splashDamage = 255f;
            knockback = 10;
            hitSize = 50;
            shrinkY = 0;
            hitSound = Sounds.explosionbig;
            hitSoundVolume = 2;
            lifetime = 145;
            status = StatusEffects.blasted;
            homingDelay = 15;
            homingPower = 0.08f;
            homingRange = 120;
            width = 15;
            height = 55;
            sprite = name("missile");
            backColor = Color.valueOf("ff7055");
            frontColor = Color.valueOf("e3e3e3");
            trailLength = 40;
            trailWidth = 2;
            trailColor = Color.valueOf("ffffff80");
            trailChance = 1;
            trailInterval = 16;
            trailEffect = new ParticleEffect(){{
                particles = 3;
                length = 16;
                baseLength = 1;
                lifetime = 45;
                colorFrom = colorTo = Color.valueOf("e3e3e380");
                sizeFrom = 3;
                sizeTo = 0;
            }};
            hitShake = 5;
            ammoMultiplier = 4;
            reloadMultiplier = 1.7f;
            shootEffect = Fx.shootTitan;
            smokeEffect = Fx.shootPyraFlame;
            hitEffect = new MultiEffect(
                    new ParticleEffect(){{
                        particles = 18;
                        sizeFrom = 10;
                        sizeTo = 0;
                        length = 35;
                        baseLength = 43;
                        lifetime = 35;
                        colorFrom = colorTo = Color.valueOf("737373");
                    }},
                    new ParticleEffect(){{
                        particles = 32;
                        line = true;
                        sizeFrom = 9;
                        sizeTo = 0;
                        length = 43;
                        baseLength = 33;
                        lifetime = 22;
                        colorFrom = Color.white;
                        colorTo = Color.valueOf("ffe176");
                    }},
                    new WaveEffect(){{
                        lifetime = 15;
                        sizeFrom = 1;
                        sizeTo = 70;
                        strokeFrom = 8;
                        strokeTo = 0;
                        colorFrom = Color.valueOf("ffe176");
                        colorTo = Color.white;
                    }}
            );
        }};
        caelumBullet2 = new BasicBulletType(10f, 360f){{
            collidesGround = false;
            status = StatusEffects.melting;
            statusDuration = 180;
            pierceArmor = true;
            hitShake = 2;
            homingDelay = 15;
            homingPower = 0.12f;
            homingRange = 160;
            lifetime = 98;
            hitSound = Sounds.shotgun;
            hitSoundVolume = 0.2f;
            width = 15;
            height = 53;
            shrinkY = 0;
            ammoMultiplier = 1;
            sprite = name("rocket");
            backColor = Color.valueOf("a5b2c2");
            frontColor = Color.valueOf("e3e3e3");
            trailLength = 20;
            trailWidth = 1.7f;
            trailColor = Color.white;
            trailChance = 1f;
            trailInterval = 1f;
            trailRotation = true;
            trailEffect = new ParticleEffect(){{
                particles = 2;
                length = -40f;
                baseLength = 0f;
                lifetime = 15f;
                cone = 20f;
                colorFrom = Color.valueOf("e3e3e380");
                colorTo = Color.valueOf("e3e3e300");
                sizeFrom = 1f;
                sizeTo = 5f;
            }};
            shootEffect = Fx.shootTitan;
            smokeEffect = Fx.shootPyraFlame;
            hitEffect = new MultiEffect(
                    new ParticleEffect(){{
                        particles = 9;
                        sizeFrom = 3f;
                        sizeTo = 0f;
                        length = 33f;
                        baseLength = 6f;
                        lifetime = 25f;
                        colorFrom = colorTo = Color.valueOf("737373");
                    }},
                    new ParticleEffect(){{
                        particles = 15;
                        line = true;
                        sizeFrom = 4f;
                        sizeTo = lenFrom = 0f;
                        lenTo = 10f;
                        length = 83f;
                        baseLength = 3f;
                        lifetime = 10f;
                        colorFrom = Color.white;
                        colorTo = Color.valueOf("ffe176");
                    }},
                    new WaveEffect(){{
                        lifetime = 10f;
                        sizeFrom = 1f;
                        sizeTo = strokeFrom = strokeTo = 0f;
                        colorFrom = Color.valueOf("ffe176");
                        colorTo = Color.white;
                    }}
            );
        }};
        cloudbreakerBullet1 = new BasicBulletType(14f, 220f){{
            lifetime = 25f;
            width = 14f;
            height = 25f;
            trailLength = 5;
            trailWidth = 1.5f;
            trailColor = Color.valueOf("8da1e350");
            trailChance = 0.5f;
            trailRotation = true;
            trailEffect = new ParticleEffect(){{
                sizeInterp = Interp.pow3In;
                particles = 3;
                strokeFrom = 1f;
                strokeTo = lenTo = baseLength = 0f;
                line = true;
                lenFrom = 8f;
                length = 15f;
                lifetime = 10f;
                colorFrom = Color.white;
                colorTo = Color.valueOf("8da1e3");
                cone = 10f;
            }};
            pierceArmor = true;
            pierce = true;
            pierceBuilding = false;
            pierceCap = 3;
            shootEffect = Fx.bigShockwave;
            smokeEffect = Fx.shootBigSmoke;
            hitEffect = new ParticleEffect(){{
                particles = 15;
                line = true;
                strokeFrom = 1f;
                strokeTo = lenTo = baseLength = 0f;
                lenFrom = 10f;
                length = 50f;
                lifetime = 10f;
                colorFrom = Color.valueOf("ffe176");
                colorTo = Color.white;
                cone = 60f;
            }};
            despawnEffect = Fx.none;
            ammoMultiplier = 2;
        }};
        cloudbreakerBullet2 = new BasicBulletType(17.5f, 280f){{
            lifetime = 25f;
            rangeChange = 70f;
            width = 15f;
            height = 25f;
            trailLength = 6;
            trailWidth = 1.8f;
            trailColor = Color.valueOf("f9a3c750");
            trailChance = 0.8f;
            trailRotation = true;
            trailEffect = new ParticleEffect(){{
                sizeInterp = Interp.pow3In;
                particles = 3;
                strokeFrom = 1;
                strokeTo = lenTo = baseLength = 0;
                line = true;
                lenFrom = 8;
                length = 15;
                lifetime = 15;
                colorFrom = Color.white;
                colorTo = Color.valueOf("f9a3c7");
                cone = 10;
            }};
            pierceArmor = true;
            pierce = true;
            pierceBuilding = false;
            pierceCap = 5;
            shootEffect = Fx.bigShockwave;
            smokeEffect = Fx.shootBigSmoke;
            hitEffect = new ParticleEffect(){{
                particles = 15;
                line = true;
                strokeFrom = 1;
                strokeTo = lenTo = baseLength = 0;
                lenFrom = 10;
                length = 70;
                lifetime = 10;
                colorFrom = Color.valueOf("ffe176");
                colorTo = Color.white;
                cone = 60;
            }};
            despawnEffect = Fx.none;
            ammoMultiplier = 3;
        }};
        cloudbreakerBullet3 = new BasicBulletType(20f, 360f){{
            lifetime = 25f;
            rangeChange = 120f;
            width = 15f;
            height = 26f;
            trailLength = 6;
            trailWidth = 1.8f;
            trailColor = Color.valueOf("a5b2c250");
            trailChance = 0.8f;
            trailRotation = true;
            status = StatusEffects.melting;
            statusDuration = 240f;
            trailEffect = new ParticleEffect(){{
                sizeInterp = Interp.pow3In;
                particles = 3;
                strokeFrom = 1.1f;
                strokeTo = lenTo = baseLength = 0f;
                line = true;
                lenFrom = 8f;
                length = 45f;
                lifetime = 10f;
                colorFrom = Color.white;
                colorTo = Color.valueOf("a5b2c2");
                cone = 10f;
            }};
            pierceArmor = true;
            pierce = true;
            pierceBuilding = false;
            pierceCap = 10;
            shootEffect = Fx.bigShockwave;
            smokeEffect = Fx.shootBigSmoke;
            hitEffect = new ParticleEffect(){{
                particles = 15;
                line = true;
                strokeFrom = 5;
                strokeTo = lenTo = baseLength = 0;
                lenFrom = 16;
                length = 100;
                lifetime = 10;
                colorFrom = Color.valueOf("ffe176");
                colorTo = Color.white;
                cone = 60;
            }};
            despawnEffect = Fx.none;
            ammoMultiplier = 4;
        }};
        furnaceBullet1 = new ContinuousFlameBulletType(){{
            damage = 40f;
            length = 170;
            knockback = 0.5f;
            pierceCap = 2;
            buildingDamageMultiplier = 0.3f;
            colors = new Color[]{Color.valueOf("ffd37fa1"), Color.valueOf("ffd37fcc"), Color.valueOf("ffd37f"), Color.valueOf("ffe6b7"), Color.valueOf("d8e2ff")};
            lightColor = hitColor = Color.valueOf("ffd37f");
            flareColor = Color.valueOf("fbd367");
        }};
        furnaceBullet2 = new ContinuousFlameBulletType(){{
            damage = 60f;
            rangeChange = 30;
            length = 200;
            knockback = 1f;
            pierceCap = 2;
            buildingDamageMultiplier = 0.3f;
            colors = new Color[]{Color.valueOf("92abff7f"), Color.valueOf("92abffa2"), Color.valueOf("92abffd3"), Color.valueOf("92abff"), Color.valueOf("d4e0ff")};
            lightColor = hitColor = flareColor = Color.valueOf("92abff");
        }};
        thermoelectricIonBullet1 = new PointBulletType(){{
            lifetime = 50f;
            speed = 50f;
            damage = 3220f;
            splashDamageRadius = 64f;
            splashDamage = 5660f;
            ammoMultiplier = 1;
            hitSound = Sounds.plasmaboom;
            chargeEffect = new MultiEffect(
                    new ParticleEffect(){{
                        particles = 25;
                        sizeInterp = Interp.pow5In;
                        sizeFrom = 0f;
                        sizeTo = 4f;
                        length = 100f;
                        baseLength = -100f;
                        lifetime = 60f;
                        colorFrom = Color.valueOf("f2ff9c40");
                        colorTo = HIPal.echoFlameYellow;
                    }},
                    new ParticleEffect(){{
                        sizeInterp = Interp.pow3In;
                        particles = 1;
                        sizeFrom = length = baseLength = 0f;
                        sizeTo = 15f;
                        lifetime = 56f;
                        colorFrom = colorTo = HIPal.echoFlameYellow;
                    }},
                    new ParticleEffect(){{
                        particles = 1;
                        sizeFrom = 15f;
                        sizeTo = 10f;
                        length = baseLength = 0f;
                        startDelay = 55f;
                        lifetime = 21f;
                        colorFrom = colorTo = HIPal.echoFlameYellow;
                    }}
            );
            smokeEffect = new ParticleEffect(){{
                particles = 10;
                sizeFrom = 8f;
                interp = Interp.pow5Out;
                sizeInterp = Interp.pow10In;
                sizeTo = baseLength = 0f;
                length = 60f;
                lifetime = 125f;
                colorFrom = Color.valueOf("f2ff9c80");
                colorTo = Color.valueOf("f2ff9c40");
            }};
            despawnEffect = new ParticleEffect(){{
                particles = 15;
                sizeFrom = 3f;
                sizeTo = baseLength = 0f;
                length = 100f;
                lifetime = 10f;
                colorFrom = colorTo = HIPal.echoFlameYellow;
                cone = 60f;
            }};
            hitEffect = new MultiEffect(
                    new ParticleEffect(){{
                        particles = 1;
                        sizeFrom = 20f;
                        sizeTo = length = baseLength = 0f;
                        lifetime = 55f;
                        colorFrom = colorTo = HIPal.echoFlameYellow;
                    }},
                    new WaveEffect(){{
                        lifetime = 13;
                        sizeFrom = 5f;
                        sizeTo = 150f;
                        strokeFrom = 50f;
                        strokeTo = 0f;
                        colorFrom = HIPal.echoFlameYellow;
                        colorTo = Color.valueOf("f2ff9c40");
                    }},
                    new ParticleEffect(){{
                        particles = 30;
                        sizeFrom = 8f;
                        sizeTo = baseLength = 0f;
                        length = 70f;
                        lifetime = 20f;
                        colorFrom = colorTo = HIPal.echoFlameYellow;
                    }},
                    Fx.titanExplosion,
                    Fx.titanSmoke
            );
            hitColor = HIPal.echoFlameYellow;
            trailSpacing = 12;
            trailEffect = new MultiEffect(
                    new ParticleEffect(){{
                        sizeInterp = Interp.pow5Out;
                        particles = 1;
                        length = strokeFrom = cone = 0f;
                        baseLength = 0.2f;
                        lifetime = 11f;
                        line = true;
                        randLength = false;
                        lenFrom = lenTo = 13f;
                        strokeTo = 8.5f;
                        colorFrom = colorTo = HIPal.echoFlameYellow;
                    }},
                    new ParticleEffect(){{
                        particles = 1;
                        length = strokeTo = cone = 0f;
                        baseLength = 0.2f;
                        startDelay = 8.5f;
                        lifetime = 41.5f;
                        line = true;
                        randLength = false;
                        lenFrom = lenTo = 13f;
                        strokeFrom = 8.5f;
                        colorFrom = colorTo = HIPal.echoFlameYellow;
                    }},
                    new ParticleEffect(){{
                        particles = 2;
                        sizeFrom = 3f;
                        interp = Interp.pow10Out;
                        sizeInterp = Interp.pow5In;
                        sizeTo = baseLength = 0f;
                        length = 13f;
                        lifetime = 55f;
                        colorFrom = colorTo = HIPal.echoFlameYellow;
                    }}
            );
            fragBullets = 4;
            fragBullet = new PointBulletType(){{
                trailSpacing = 9;
                trailEffect = new MultiEffect(
                        new ParticleEffect(){{
                            sizeInterp = Interp.pow5Out;
                            particles = 1;
                            length = strokeFrom = cone = 0f;
                            baseLength = 1f;
                            lifetime = 10f;
                            line = true;
                            randLength = false;
                            lenFrom = lenTo = 10f;
                            strokeTo = 5f;
                            colorFrom = colorTo = HIPal.echoFlameYellow;
                        }},
                        new ParticleEffect(){{
                            particles = 1;
                            length = strokeTo = cone = 0f;
                            baseLength = 1f;
                            startDelay = 8.5f;
                            lifetime = 11.5f;
                            line = true;
                            randLength = false;
                            lenFrom = lenTo = 10f;
                            strokeFrom = 8.5f;
                            colorFrom = colorTo = HIPal.echoFlameYellow;
                        }}
                );
                lifetime = 8f;
                speed = 10f;
                status = HIStatusEffects.echoFlame;
                statusDuration = 0.25f;
                buildingDamageMultiplier = 0.8f;
                damage = 54f;
                splashDamageRadius = 8f;
                splashDamage = 7f;
                hitShake = 1f;
                hitSound = Sounds.laser;
                hitEffect = new MultiEffect(
                        new ParticleEffect(){{
                            particles = 1;
                            sizeFrom = 8f;
                            sizeTo = length = baseLength = 0f;
                            lifetime = 21f;
                            colorFrom = colorTo = HIPal.echoFlameYellow;
                        }},
                        new ParticleEffect(){{
                            particles = 8;
                            length = 30f;
                            baseLength = lenTo = strokeTo = 0f;
                            lifetime = 15f;
                            line = true;
                            lenFrom = 30f;
                            strokeFrom = 3.3f;
                            colorFrom = colorTo = HIPal.echoFlameYellow;
                        }}
                );
                despawnEffect = Fx.none;
            }};
        }};
        tracerBullet1 = new BasicBulletType(0f, 1){{
            smokeEffect = Fx.shootSmallFlame;
            ammoMultiplier = 1f;
            spawnUnit = new MissileUnitType("tracer-missile"){{
                speed = 4f;
                lifetime = 180;
                rotateSpeed = 2.5f;
                deathSound = Sounds.explosionbig;
                missileAccelTime = 30;
                targetGround = false;
                lowAltitude = true;
                hitSize = 12;
                health = 90;
                fogRadius = 2;
                engineColor = trailColor = Color.valueOf("c0ecff");
                trailLength = 11;
                setEnginesMirror(
                        new UnitEngine(-4, -4, 2.2f, -135f),
                        new UnitEngine(4, -4, 2.2f, -45f)
                );
                engineOffset = 8;
                engineSize = 2.3f;
                maxRange = 30;
                ammoType = new PowerAmmoType(100);
                weapons.add(new Weapon(){{
                    x = y = 0;
                    reload = 1;
                    mirror = false;
                    rotate = true;
                    shake = 3;
                    shootSound = Sounds.explosion;
                    shootOnDeath = true;
                    bullet = new FlakBulletType(4, 16){{
                        ammoMultiplier = 1;
                        instantDisappear = true;
                        killShooter = true;
                        splashDamageRadius = 50;
                        splashDamage = 45;
                        hitShake = 3;
                        shootEffect = Fx.none;
                        smokeEffect = Fx.none;
                        despawnEffect = Fx.none;
                        hitEffect = new MultiEffect(
                                new ParticleEffect(){{
                                    particles = 23;
                                    sizeFrom = 13;
                                    sizeTo = 0;
                                    length = 45;
                                    baseLength = 30;
                                    interp = sizeInterp = Interp.pow10Out;
                                    region = name("diamond");
                                    lifetime = 85;
                                    colorFrom = Color.valueOf("c0ecff");
                                    colorTo = Color.valueOf("c0ecff00");
                                }},
                                new WaveEffect(){{
                                    lifetime = 11;
                                    sizeFrom = strokeTo = 0;
                                    sizeTo = 75;
                                    strokeFrom = 5;
                                    colorFrom = colorTo = Color.valueOf("c0ecff");
                                }}
                        );
                        fragLifeMin = 0.3f;
                        fragBullets = 4;
                        fragBullet = new FlakBulletType(4f, 11){{
                            status = StatusEffects.slow;
                            statusDuration = 6;
                            homingRange = 300;
                            homingPower = 0.15f;
                            splashDamageRadius = 35;
                            splashDamage = 48;
                            hitSound = Sounds.explosion;
                            shootEffect = Fx.none;
                            smokeEffect = Fx.none;
                            hitEffect = new MultiEffect(
                                    new ParticleEffect(){{
                                        particles = 8;
                                        sizeFrom = 5;
                                        sizeTo = baseLength = 0;
                                        length = 35;
                                        region = name("diamond");
                                        lifetime = 25;
                                        colorFrom = colorTo = Color.valueOf("c0ecff");
                                    }},
                                    new WaveEffect(){{
                                        lifetime = 15;
                                        sizeFrom = strokeTo = 0;
                                        sizeTo = 35;
                                        strokeFrom = 2;
                                        colorFrom = colorTo = Color.valueOf("c0ecff");
                                    }}
                            );
                            despawnEffect = new ParticleEffect(){{
                                particles = 1;
                                sizeFrom = 3;
                                sizeTo = length = baseLength = 0;
                                lifetime = 8;
                                colorFrom = colorTo = Color.valueOf("c0ecff");
                            }};
                            sprite = name("tracer-bullet");
                            frontColor = Color.white;
                            backColor = trailColor = Color.valueOf("c0ecff");
                            trailLength = 11;
                            trailWidth = 2;
                            shrinkX = shrinkY = 0;
                            width = height = 4;
                            lifetime = 30;
                        }};
                    }};
                }});
                abilities.add(new MoveEffectAbility(){{
                    effect = new ParticleEffect(){{
                        particles = 2;
                        sizeFrom = 6;
                        sizeTo = 0;
                        length = 10;
                        lifetime = 50;
                        interp = Interp.pow10Out;
                        sizeInterp = Interp.pow5In;
                        colorFrom = colorTo = Color.valueOf("c0ecff40");
                    }};
                    interval = 7f;
                    y = -8f;
                    color = Color.valueOf("c0ecff");
                }});
            }};
        }};
        shadowBullet1 = new BasicBulletType(15, 420){{
            inaccuracy = 1;
            splashDamageRadius = 38;
            splashDamage = 180;
            buildingDamageMultiplier = 0.3f;
            lifetime = 36;
            width = 20;
            height = 33;
            pierce = true;
            pierceCap = 3;
            pierceArmor = true;
            status = StatusEffects.slow;
            statusDuration = 33;
            backColor = frontColor = trailColor = Color.valueOf("ffe176");
            trailLength = 13;
            trailWidth = 4.5f;
            trailChance = 1;
            trailInterval = 8;
            trailEffect = new ParticleEffect(){{
                region = name("diamond");
                particles = 2;
                sizeFrom = 6;
                sizeTo = 0;
                length = 10;
                lifetime = 30;
                interp = Interp.pow10Out;
                sizeInterp = Interp.pow5In;
                colorFrom = Color.valueOf("ffa166A0");
                colorTo = Color.valueOf("ffa16650");
            }};
            hitShake = 3;
            hitEffect = new MultiEffect(
                    new ParticleEffect(){{
                        particles = 16;
                        strokeFrom = 2.22f;
                        strokeTo = lenTo = baseLength = 0;
                        lenFrom = 35;
                        sizeInterp = Interp.pow5In;
                        line = true;
                        length = 80;
                        lifetime = 30;
                        colorFrom = colorTo = Color.valueOf("ffa166");
                        cone = 23;
                    }},
                    new ParticleEffect(){{
                        particles = 18;
                        strokeFrom = 2;
                        strokeTo = lenTo = baseLength = 0;
                        lenFrom = 24;
                        interp = Interp.pow5Out;
                        sizeInterp = Interp.pow5In;
                        line = true;
                        length = -60;
                        lifetime = 10;
                        colorFrom = colorTo = Color.valueOf("ffa166");
                        cone = 50;
                    }},
                    new ParticleEffect(){{
                        particles = 6;
                        strokeFrom = 9;
                        strokeTo = 0;
                        region = name("diamond");
                        interp = Interp.pow5Out;
                        sizeInterp = Interp.pow5In;
                        length = 45;
                        baseLength = 8;
                        lifetime = 43;
                        colorFrom = Color.valueOf("ffa166a0");
                        colorTo = Color.valueOf("ffa166d0");
                    }},
                    new WaveEffect(){{
                        lifetime = 18;
                        sizeFrom = strokeTo = 0;
                        sizeTo = 38;
                        strokeFrom = 5;
                        colorFrom = colorTo = Color.valueOf("ffa166");
                    }}
            );
            shootEffect = new ParticleEffect(){{
                particles = 6;
                line = true;
                strokeFrom = 3;
                strokeTo = lenTo = baseLength = 0;
                lenFrom = 16;
                length = 55;
                lifetime = 25;
                colorFrom = colorTo = Color.valueOf("ffa166");
                cone = 16;
            }};
            despawnEffect = Fx.none;
            smokeEffect = new ParticleEffect(){{
                particles = 6;
                lenTo = baseLength = 0;
                interp = Interp.pow10Out;
                sizeInterp = Interp.pow5In;
                length = 55;
                lifetime = 25;
                colorFrom = colorTo = Color.valueOf("ffa166A0");
                cone = 20;
            }};
            fragBullets = 18;
            fragRandomSpread = 60;
            fragBullet = new LiquidBulletType(Liquids.slag){{
                damage = 46;
                puddleSize = 16f;
                orbSize = 5f;
                knockback = 1.75f;
                statusDuration = 600f;
                status = StatusEffects.melting;
                pierceArmor = true;
                pierce = true;
                pierceCap = 2;
                speed = 8f;
                lifetime = 20f;
                hitEffect = new ParticleEffect(){{
                    particles = 5;
                    interp = Interp.pow5Out;
                    region = name("diamond");
                    sizeFrom = 5f;
                    sizeTo = baseLength = 0f;
                    length = 20f;
                    lifetime = 18f;
                    colorFrom = colorTo = Color.valueOf("ffa166");
                    cone = 60f;
                }};
            }};
            ammoMultiplier = 2;
        }};
        shadowBullet2 = new BasicBulletType(28f, 2160f){{
            rangeChange = 160f;
            buildingDamageMultiplier = 0.5f;
            lifetime = 28f;
            width = 20f;
            height = 34f;
            pierceArmor = true;
            pierce = true;
            pierceCap = 5;
            status = StatusEffects.melting;
            statusDuration = 360f;
            backColor = trailColor = Color.valueOf("feebb3");
            frontColor = Color.valueOf("ffa166");
            shrinkY = 0f;
            trailLength = 13;
            trailWidth = 5f;
            trailChance = 0f;
            trailInterval = 0.2f;
            trailEffect = new ParticleEffect(){{
                region = name("diamond");
                particles = 3;
                sizeFrom = 5;
                sizeTo = 0;
                length = 16;
                lifetime = 22;
                interp = Interp.pow10Out;
                sizeInterp = Interp.pow5In;
                colorFrom = Color.valueOf("ffa166");
                colorTo = Color.valueOf("feebb3a0");
            }};
            hitShake = 3;
            hitEffect = new MultiEffect(
                    new ParticleEffect(){{
                        particles = 16;
                        strokeFrom = 2.22f;
                        strokeTo = lenTo = baseLength = 0;
                        lenFrom = 35;
                        sizeInterp = Interp.pow5In;
                        line = true;
                        length = 80;
                        lifetime = 30;
                        colorFrom = colorTo = Color.valueOf("feebb3");
                        cone = 23;
                    }},
                    new ParticleEffect(){{
                        particles = 18;
                        strokeFrom = 2;
                        strokeTo = lenTo = baseLength = 0;
                        lenFrom = 24;
                        interp = Interp.pow5Out;
                        sizeInterp = Interp.pow5In;
                        line = true;
                        length = -60;
                        lifetime = 10;
                        colorFrom = colorTo = Color.valueOf("feebb3");
                        cone = 50;
                    }},
                    new ParticleEffect(){{
                        particles = 6;
                        strokeFrom = 9;
                        strokeTo = 0;
                        region = name("diamond");
                        interp = Interp.pow5Out;
                        sizeInterp = Interp.pow5In;
                        length = 45;
                        baseLength = 8;
                        lifetime = 43;
                        colorFrom = Color.valueOf("ffa166a0");
                        colorTo = Color.valueOf("feebb3d0");
                    }},
                    new WaveEffect(){{
                        lifetime = 8;
                        sizeFrom = strokeTo = 0;
                        sizeTo = 30;
                        strokeFrom = 10;
                        colorFrom = Color.white;
                        colorTo = Color.valueOf("feebb3");
                    }}
            );
            shootEffect = new ParticleEffect(){{
                particles = 6;
                line = true;
                strokeFrom = 3;
                strokeTo = lenTo = baseLength = 0;
                lenFrom = 16;
                length = 55;
                lifetime = 25;
                colorFrom = colorTo = Color.valueOf("feebb3");
                cone = 16;
            }};
            despawnEffect = Fx.none;
            smokeEffect = new ParticleEffect(){{
                particles = 6;
                lenTo = baseLength = 0;
                interp = Interp.pow10Out;
                sizeInterp = Interp.pow5In;
                length = 55;
                lifetime = 25;
                colorFrom = colorTo = Color.valueOf("feebb3");
                cone = 20;
            }};
            fragBullets = 1;
            fragLifeMin = 0f;
            fragRandomSpread = 30f;
            fragBullet = new BasicBulletType(16f, 210){{
                width = 10f;
                height = 14f;
                pierceArmor = true;
                pierce = true;
                pierceBuilding = true;
                status = StatusEffects.slow;
                statusDuration = 33;
                pierceCap = 3;
                lifetime = 15f;
                hitEffect = Fx.flakExplosion;
                backColor = trailColor = Color.valueOf("feebb3");
                frontColor = Color.valueOf("ffa166");
                trailWidth = 1.7f;
                trailLength = 3;
                splashDamage = 50f;
                splashDamageRadius = 11f;
            }};
            ammoMultiplier = 3;
        }};
        fiammettaBullet1 = new BulletType(){{
            chargeEffect = HIFx.aimEffect(40, HIItems.highEnergyFabric.color, 1.5f, range, 13);
            ammoMultiplier = 2;
            damage = 0;
            collides = collidesTiles = false;
            splashDamageRadius = 10 * 8;
            splashDamage = 400;
            lifetime = 30;
            speed = 15;
            pierce = true;
            pierceBuilding = true;
            hittable = false;
            absorbable = false;
            reflectable = false;
            intervalBullet = new BulletType(){{
                lifetime = 32;
                speed = 0;
                despawnEffect = hitEffect = new MultiEffect(new Effect(30, e -> {
                    float r = Math.min(10 * 8 * e.fin(), 6 * 8);
                    Draw.color(HIItems.highEnergyFabric.color.cpy().a(e.fout()));
                    Fill.circle(e.x, e.y, r);
                    float ww = r * 2f, hh = r * 2f;
                    Draw.color(HIItems.highEnergyFabric.color.cpy().a(e.fout()));
                    Draw.rect(Core.atlas.find(name("firebird-light")), e.x, e.y, ww, hh);
                }), HIFx.expFtEffect(5, 12, 6 * 4, 30, 0.2f));
                despawnSound = hitSound = Sounds.explosion;
                collides = absorbable = hittable = false;
                splashDamageRadius = 6 * 8;
                splashDamage = 300;
            }
                @Override
                public void draw(Bullet b) {
                    super.draw(b);
                    float ft = (b.time > 16 ? b.fout() * 2 : 1);
                    Lines.stroke(5, HIItems.highEnergyFabric.color);
                    Lines.circle(b.x, b.y, 12 * ft);
                    Lines.poly(b.x, b.y, 3, 12 * ft, b.fout() * 180);
                }
            };
            intervalDelay = 4;
            intervalSpread = intervalRandomSpread = 0;
            bulletInterval = 4;
            hitSize = 20;
            despawnEffect = new MultiEffect(new Effect(30, e -> {
                float r = Math.min(16 * 8 * e.fin(), 10 * 8);
                Draw.color(HIItems.highEnergyFabric.color.cpy().a(e.fout()));
                Fill.circle(e.x, e.y, r);
                float ww = r * 2f, hh = r * 2f;
                Draw.color(HIItems.highEnergyFabric.color.cpy().a(e.fout()));
                Draw.rect(Core.atlas.find(name("firebird-light")), e.x, e.y, ww, hh);
            }), HIFx.expFtEffect(6, 15, 10 * 4, 30, 0.2f));
            despawnSound = Sounds.explosion;
            hitEffect = Fx.none;
            trailLength = 15;
            trailColor = HIItems.highEnergyFabric.color;
            trailWidth = 4;
            trailRotation = true;
            trailEffect = new Effect(15, e ->{
                color(e.color);
                for(int x : new int[]{-20, 20}){
                    Tmp.v1.set(x, -10).rotate(e.rotation - 90);
                    Fill.circle(e.x + Tmp.v1.x, e.y + Tmp.v1.y, 4 * e.foutpow());
                }
            });
            trailInterval = 0.1f;
        }
            @Override
            public void draw(Bullet b) {
                super.draw(b);
                Draw.color(HIItems.highEnergyFabric.color);
                Draw.rect(Core.atlas.find(name("phx")), b.x, b.y,48, 48,  b.rotation() - 90);
            }
        };
        BulletType fall = new BulletType(){{
            speed = 0;
            lifetime = 20;
            collides = collidesTiles = hittable = absorbable = false;
            collidesAir = collidesGround = true;
            splashDamage = 3500;
            splashDamageRadius = 14 * 8f;
            despawnEffect = hitEffect = new MultiEffect(HIFx.expFtEffect(10, 15, splashDamageRadius, 30, 0.2f), HIFx.fiammettaExp(splashDamageRadius), new Effect(20, e -> {
                Lines.stroke(16 * e.fout(), HIItems.highEnergyFabric.color);
                Lines.circle(e.x, e.y, (splashDamageRadius + 56) * e.fin());
            }));
            keepVelocity = false;
            buildingDamageMultiplier = 0.6f;
            hitSound = despawnSound = Sounds.largeExplosion;
            despawnShake = hitShake = 8;
        }
            @Override
            public void draw(Bullet b) {
                TextureRegion region = Core.atlas.find(name("mb-fireworks"));
                if(b.time < 10){
                    float fin = b.time/10, fout = 1 - fin;
                    float ww = 15 * 8, hh = 15 * 8 * fout;
                    Draw.color(HIItems.highEnergyFabric.color);
                    Draw.alpha(fin);
                    Draw.rect(region, b.x, b.y, ww, hh, b.rotation() - 90);
                }
                Draw.color(HIItems.highEnergyFabric.color);
                Draw.alpha(b.fin());
                Fill.circle(b.x, b.y, 20 * (b.time < 10 ? b.fin() * 2 : b.fout() * 2));
            }
        };
        fiammettaBullet2 = new ArtilleryBulletType(){{
            speed = 10;
            ammoMultiplier = 2;
            splashDamage = 3500;
            splashDamageRadius = 14 * 8f;
            hittable = absorbable = false;
            collides = collidesTiles = false;
            collidesAir = collidesGround = false;
            despawnEffect = Fx.none;
            hitEffect = Fx.none;
            trailEffect = Fx.none;
            fragOnHit = false;
            rangeChange = 10 * 8;
            trailLength = 20;
            trailWidth = 12;
            trailColor = HIItems.highEnergyFabric.color.cpy().a(0.6f);
            buildingDamageMultiplier = 0.3f;
        }
            @Override
            public void update(Bullet b) {
                super.update(b);
                HIFx.normalTrail.at(b.x + Mathf.random(-10, 10), b.y + Mathf.random(-10, 10), 15 * b.fin(), HIItems.highEnergyFabric.color.cpy().a(0.6f));
            }
            @Override
            public void updateTrail(Bullet b) {
                if(!headless && trailLength > 0){
                    if(b.trail == null){
                        b.trail = new Trail(trailLength);
                    }
                    b.trail.length = 2 + (int) (trailLength * b.fin());
                    b.trail.update(b.x, b.y, trailInterp.apply(b.fin()) * (1f + (trailSinMag > 0 ? Mathf.absin(Time.time, trailSinScl, trailSinMag) : 0f)));
                }
            }
            @Override
            public void draw(Bullet b) {
                TextureRegion region = Core.atlas.find(name("mb-fireworks"));
                float ww = 15 * 8 * b.fin(), hh = 15 * 8 * b.fin();
                Draw.color(HIItems.highEnergyFabric.color);
                Draw.rect(region, b.x, b.y, ww, hh, b.rotation() - 90);
                drawTrail(b);
            }
            @Override
            public void drawTrail(Bullet b) {
                if(trailLength > 0 && b.trail != null){
                    float z = Draw.z();
                    Draw.z(z - 0.0001f);
                    b.trail.draw(trailColor, 2 + trailWidth * b.fin());
                    Draw.z(z);
                }
            }
            @Override
            public void createFrags(Bullet b, float x, float y) {
                fall.create(b, b.x, b.y, b.rotation());
            }
        };
    }
}
