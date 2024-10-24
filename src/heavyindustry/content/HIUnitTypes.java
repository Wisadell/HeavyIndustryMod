package heavyindustry.content;

import heavyindustry.ai.*;
import heavyindustry.gen.*;
import heavyindustry.graphics.*;
import heavyindustry.world.draw.*;
import heavyindustry.entities.abilities.*;
import heavyindustry.entities.effect.*;
import heavyindustry.entities.bullet.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import mindustry.ai.*;
import mindustry.ai.types.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.type.unit.*;
import mindustry.type.weapons.*;
import mindustry.world.meta.*;

import static heavyindustry.core.HeavyIndustryMod.*;
import static mindustry.Vars.*;
import static mindustry.gen.EntityMapping.*;

/**
 * Defines the {@linkplain UnitType units} this mod offers.
 * @author Wisadell
 */
public class HIUnitTypes {
    //one day, someone asks me : why not use xxxUnit::new? ha, I say : I don't know...
    static {
        //vanilla-tank
        nameMap.put(name("vanguard"), idMap[43]);
        nameMap.put(name("striker"), idMap[43]);
        nameMap.put(name("counterattack"), idMap[43]);
        nameMap.put(name("crush"), idMap[43]);
        nameMap.put(name("destruction"), idMap[43]);
        nameMap.put(name("purgatory"), idMap[43]);
        //vanilla-tier6
        nameMap.put(name("suzerain"), idMap[4]);
        nameMap.put(name("supernova"), idMap[24]);
        nameMap.put(name("cancer"), idMap[33]);
        nameMap.put(name("sunlit"), idMap[3]);
        nameMap.put(name("windstorm"), idMap[5]);
        nameMap.put(name("mosasaur"), idMap[20]);
        nameMap.put(name("killer-whale"), idMap[20]);
        //vanilla-erekir-tier6
        nameMap.put(name("dominate"), idMap[43]);
        nameMap.put(name("oracle"), idMap[24]);
        nameMap.put(name("havoc"), idMap[5]);
        //miner-erekir
        nameMap.put(name("miner"), idMap[36]);
        nameMap.put(name("large-miner"), idMap[36]);
        //other
        nameMap.put(name("pioneer"), LegsPayloadUnit::new);
        nameMap.put(name("vulture"), idMap[3]);
        nameMap.put(name("burner"), idMap[4]);
        //boss
        nameMap.put(name("tiger"), idMap[4]);
        nameMap.put(name("thunder"), idMap[43]);
    }

    public static UnitType
            //vanilla-tank
            vanguard,striker,counterattack,crush,destruction,purgatory,
            //vanilla-tier6
            suzerain,supernova,cancer,sunlit,windstorm,mosasaur,killerWhale,
            //vanilla-tier6-erekir
            dominate,oracle,havoc,
            //miner-erekir
            miner,largeMiner,
            //other
            pioneer,vulture,burner,
            //boss
            tiger,thunder;

    public static void load(){
        //vanilla-tank
        vanguard = new UnitType("vanguard"){{
            squareShape = true;
            omniMovement = false;
            rotateMoveFirst = false;
            rotateSpeed = 3f;
            speed = 2.4f;
            hitSize = 9.5f;
            ammoCapacity = 300;
            health = 250f;
            armor = 5f;
            drag = 0.08f;
            accel = 0.1f;
            itemCapacity = 5;
            faceTarget = false;
            abilities.add(new StatusFieldAbility(StatusEffects.overclock, 250f, 300f, 30f){{
                applyEffect = Fx.none;
                activeEffect = new WaveEffect(){{
                    lifetime = 10f;
                    sizeFrom = 8f;
                    sizeTo = 40f;
                    strokeFrom = 2f;
                    strokeTo = 0f;
                    colorFrom = colorTo = Color.white;
                }};
            }});
            weapons.add(new Weapon(name("vanguard-weapon")){{
                reload = 7.6f;
                recoil = 0f;
                x = 0f;
                y = 0f;
                rotate = true;
                rotateSpeed = 15f;
                mirror = false;
                inaccuracy = 0.5f;
                ejectEffect = Fx.casing1;
                shootSound = Sounds.shoot;
                alternate = false;
                bullet = new BasicBulletType(9f, 10f){{
                    buildingDamageMultiplier = 0.8f;
                    lifetime = 18f;
                    width = 3f;
                    height = 10f;
                }};
            }});
        }};
        striker = new UnitType("striker"){{
            squareShape = true;
            omniMovement = false;
            rotateMoveFirst = false;
            hovering = true;
            canDrown = false;
            speed = 1.8f;
            hitSize = 18f;
            ammoType = new ItemAmmoType(Items.graphite);
            ammoCapacity = 80;
            health = 660f;
            armor = 5f;
            drag = 0.08f;
            accel = 0.1f;
            rotateSpeed = 3f;
            itemCapacity = 0;
            faceTarget = false;
            weapons.add(new Weapon(name("striker-weapon")){{
                reload = 120;
                x = 0f;
                y = -1f;
                rotate = true;
                rotateSpeed = 9f;
                mirror = false;
                inaccuracy = 0f;
                ejectEffect = Fx.casing2;
                shootSound = Sounds.artillery;
                alternate = false;
                bullet = new BasicBulletType(11f, 108f){{
                    hitSize = 40;
                    splashDamageRadius = 46;
                    splashDamage = 96;
                    damage = 108;
                    status = StatusEffects.blasted;
                    statusDuration = 60;
                    sprite = name("missile");
                    backColor = HIPal.orangeBack;
                    frontColor = HIPal.lightGrey;
                    lifetime = 30f;
                    speed = 11f;
                    homingPower = 0.03f;
                    homingRange = 80f;
                    knockback = 8;
                    width = 12f;
                    height = 40f;
                    ammoMultiplier = 3f;
                    despawnEffect = Fx.none;
                    shootEffect = Fx.shootPyraFlame;
                    hitEffect = new MultiEffect(new ParticleEffect(){{
                        particles = 8;
                        sizeFrom = 6f;
                        sizeTo = 0f;
                        length = 25f;
                        baseLength = 23f;
                        lifetime = 35f;
                        colorFrom = colorTo = HIPal.darkGrey;
                    }}, new ParticleEffect(){{
                        particles = 12;
                        line = true;
                        length = 43f;
                        baseLength = 3f;
                        lifetime = 22f;
                        colorFrom = Color.white;
                        colorTo = HIPal.lightYellow;
                    }}, new WaveEffect(){{
                        lifetime = 10f;
                        sizeFrom = 1f;
                        sizeTo = 48f;
                        strokeFrom = 2f;
                        strokeTo = 0f;
                        colorFrom = HIPal.lightYellow;
                        colorTo = Color.white;
                    }});
                }};
            }});
        }};
        counterattack = new UnitType("counterattack"){{
            treadFrames = 8;
            treadPullOffset = 8;
            treadRects = new Rect[]{new Rect(-45f, -45f, 24f, 88f)};
            speed = 1.3f;
            hitSize = 20f;
            ammoType = new ItemAmmoType(Items.blastCompound);
            ammoCapacity = 16;
            squareShape = true;
            omniMovement = false;
            rotateMoveFirst = false;
            health = 1200f;
            armor = 13f;
            rotateSpeed = 2f;
            itemCapacity = 0;
            faceTarget = false;
            weapons.add(new Weapon(name("counterattack-weapon")){{
                reload = 12f;
                x = 0f;
                y = 0f;
                rotate = true;
                mirror = true;
                alternate = false;
                rotateSpeed = 1.3f;
                minWarmup = 0.93f;
                shootWarmupSpeed = 0.05f;
                parts.add(new RegionPart("-top"){{
                    mirror = true;
                    under = true;
                    moveY = -4f;
                    progress = PartProgress.warmup;
                }});
                xRand = 4f;
                inaccuracy = 6f;
                shootSound = Sounds.missile;
                shootStatus = StatusEffects.unmoving;
                shootStatusDuration = 200f;
                velocityRnd = 0.1f;
                bullet = new MissileBulletType(12f, 36f){{
                    sprite = name("missile");
                    backColor = Color.valueOf("ff7055");
                    frontColor = HIPal.lightGrey;
                    width = 8f;
                    height = 45f;
                    trailChance = 0f;
                    trailInterval = 1f;
                    trailEffect = new ParticleEffect(){{
                        particles = 3;
                        length = 30f;
                        baseLength = sizeTo = 0f;
                        sizeInterp = Interp.pow5In;
                        lifetime = 10f;
                        colorFrom = HIPal.lightGrey;
                        colorTo = HIPal.lightGrey.cpy().a(0.5f);
                        sizeFrom = 2.6f;
                        cone = 8f;
                    }};
                    trailRotation = true;
                    splashDamage = 105f;
                    splashDamageRadius = 45f;
                    buildingDamageMultiplier = 1.33f;
                    status = StatusEffects.blasted;
                    shootEffect = Fx.shootSmallFlame;
                    smokeEffect = new ParticleEffect(){{
                        particles = 9;
                        interp = Interp.pow10Out;
                        sizeInterp = Interp.pow10In;
                        sizeFrom = 6f;
                        sizeTo = 0f;
                        length = -58f;
                        baseLength = -20f;
                        lifetime = 42f;
                        colorFrom = colorTo = HIPal.darkGrey.cpy().a(0.55f);
                        cone = 40f;
                        layer = 49f;
                    }};
                    lifetime = 41.6f;
                    hitShake = 2;
                    hitSound = Sounds.explosion;
                    hitEffect = new MultiEffect(new ParticleEffect(){{
                        particles = 8;
                        sizeFrom = 10f;
                        sizeTo = 0f;
                        length = lifetime = 35f;
                        baseLength = 33f;
                        colorFrom = colorTo = HIPal.darkGrey;
                    }}, new ParticleEffect(){{
                        particles = 12;
                        line = true;
                        strokeFrom = 2f;
                        strokeTo = 0f;
                        lenFrom = 16f;
                        lenTo = 8f;
                        length = 66f;
                        baseLength = 3f;
                        lifetime = 12f;
                        colorFrom = Color.white;
                        colorTo = HIPal.lightYellow;
                    }}, new ParticleEffect(){{
                        particles = 1;
                        sizeFrom = baseLength = 0f;
                        sizeTo = 45f;
                        length = 0f;
                        sizeInterp = Interp.pow5Out;
                        lifetime = 12f;
                        layer = 50f;
                        colorFrom = Color.white.cpy().a(0.5f);
                        colorTo = HIPal.darkGrey;
                    }}, new WaveEffect(){{
                        lifetime = 10;
                        sizeFrom = strokeTo = 0f;
                        sizeTo = 48f;
                        interp = Interp.circleOut;
                        strokeFrom = 15f;
                        layer = 50f;
                        colorFrom = Color.white;
                        colorTo = HIPal.darkGrey.cpy().a(0.5f);
                    }});
                    despawnEffect = Fx.flakExplosionBig;
                }};
            }});
            parts.add(new RegionPart("-bracket"){{
                mirror = true;
                y = -4.75f;
                layer = 59f;
                moveX = 5.55f;
                moveY = -8f;
                moveRot = -30f;
                progress = PartProgress.warmup;
            }});
        }};
        crush = new UnitType("crush"){{
            squareShape = true;
            omniMovement = false;
            rotateMoveFirst = false;
            speed = 1.2f;
            hitSize = 28f;
            crushDamage = 2.33f;
            drownTimeMultiplier = 2f;
            treadPullOffset = 0;
            treadFrames = 8;
            treadRects = new Rect[]{new Rect(-67f, -84f, 39f, 167f)};
            ammoType = new ItemAmmoType(Items.surgeAlloy);
            ammoCapacity = 120;
            targetAir = true;
            health = 11000f;
            armor = 16f;
            rotateSpeed = 1.5f;
            itemCapacity = 0;
            faceTarget = false;
            immunities = ObjectSet.with(StatusEffects.burning, StatusEffects.shocked);
            targetFlags = new BlockFlag[]{BlockFlag.repair, BlockFlag.turret};
            abilities.add(new StatusFieldAbility(StatusEffects.overclock, 1200f, 1200f, 45f){{
                applyEffect = Fx.none;
                activeEffect = new WaveEffect(){{
                    lifetime = 10;
                    sizeFrom = 8f;
                    sizeTo = 40f;
                    strokeFrom = 2f;
                    strokeTo = 0f;
                    colorFrom = colorTo = HIPal.canaryYellow;
                }};
            }});
            weapons.add(new Weapon(name("crush-weapon")){{
                reload = 90f;
                shootY = 19.2f;
                x = 0f;
                y = 0f;
                rotate = true;
                rotateSpeed = 1.9f;
                mirror = false;
                recoil = 4f;
                inaccuracy = 0f;
                shootSound = Sounds.laser;
                shake = 3f;
                alternate = false;
                bullet = new PointBulletType(){{
                    trailEffect = new ParticleEffect(){{
                        particles = 1;
                        length = strokeTo = cone = 0f;
                        baseLength = 1f;
                        lifetime = lenFrom = lenTo = 10;
                        line = true;
                        randLength = false;
                        strokeFrom = 4f;
                        colorFrom = colorTo = HIPal.canaryYellow;
                    }};
                    trailSpacing = 9f;
                    damage = 360f;
                    splashDamage = 340f;
                    splashDamageRadius = 16f;
                    buildingDamageMultiplier = 1.1f;
                    speed = 20f;
                    lifetime = 15f;
                    hitSound = Sounds.lasercharge2;
                    smokeEffect = Fx.bigShockwave;
                    shootEffect = new ParticleEffect(){{
                        particles = 1;
                        sizeFrom = 5f;
                        sizeTo = length = baseLength = 0f;
                        lifetime = 11f;
                        colorFrom = colorTo = HIPal.canaryYellow;
                    }};
                    hitEffect = new ParticleEffect(){{
                        particles = 1;
                        sizeFrom = 10f;
                        sizeTo = length = baseLength = 0f;
                        lifetime = 15f;
                        colorFrom = colorTo = HIPal.canaryYellow;
                    }};
                    despawnEffect = Fx.bigShockwave;
                    fragLifeMin = 1f;
                    fragVelocityMax = 0f;
                    fragBullets = 1;
                    fragBullet = new BasicBulletType(){{
                        lifetime = 15;
                        height = width = 0f;
                        collides = false;
                        hittable = false;
                        absorbable = false;
                        //status = TODO;
                        statusDuration = 180;
                        buildingDamageMultiplier = 1.2f;
                        splashDamageRadius = 66.4f;
                        splashDamage = 395f;
                        hitShake = 1f;
                        hitSound = Sounds.plasmaboom;
                        hitEffect = new MultiEffect(new ParticleEffect(){{
                            particles = 4;
                            sizeFrom = 15f;
                            sizeTo = 0f;
                            length = 20f;
                            baseLength = 48f;
                            lifetime = 25f;
                            colorFrom = HIPal.canaryYellow.cpy().a(0.5f);
                            colorTo = HIPal.canaryYellow.cpy().a(0f);
                        }}, new ParticleEffect(){{
                            particles = 22;
                            line = true;
                            strokeFrom = 3f;
                            strokeTo = lenTo = baseLength = 0f;
                            lenFrom = lifetime = 20f;
                            length = 63f;
                            colorFrom = colorTo = HIPal.canaryYellow;
                        }}, new WaveEffect(){{
                            lifetime = 25f;
                            sizeFrom = strokeTo = 0f;
                            sizeTo = 66f;
                            strokeFrom = 3f;
                            colorFrom = colorTo = HIPal.canaryYellow;
                        }});
                        despawnEffect = Fx.none;
                        fragBullets = 4;
                        fragBullet = new PointBulletType(){{
                            trailSpacing = 9;
                            trailEffect = new ParticleEffect(){{
                                particles = 1;
                                length = 0f;
                                baseLength = 1f;
                                lifetime = 6f;
                                line = true;
                                randLength = false;
                                lenFrom = lenTo = 10f;
                                strokeFrom = 2f;
                                strokeTo = cone = 0f;
                                colorFrom = colorTo = HIPal.canaryYellow;
                            }};
                            lifetime = 8;
                            speed = 15;
                            //status = TODO;
                            statusDuration = 60f;
                            buildingDamageMultiplier = 1.2f;
                            splashDamageRadius = 10f;
                            splashDamage = 80f;
                            hitShake = 1f;
                            hitSound = Sounds.laser;
                            hitEffect = new ParticleEffect(){{
                                particles = 1;
                                sizeFrom = 5f;
                                sizeTo = length = baseLength = 0f;
                                lifetime = 11f;
                                colorFrom = colorTo = HIPal.canaryYellow;
                            }};
                            despawnEffect = Fx.none;
                        }};
                    }};
                }};
            }});
        }};
        destruction = new UnitType("destruction"){{
            squareShape = true;
            omniMovement = false;
            rotateMoveFirst = false;
            speed = 1f;
            hitSize = 48f;
            drownTimeMultiplier = 2.6f;
            crushDamage = 6f;
            treadRects = new Rect[]{new Rect(-86f, -108f, 42f, 112f), new Rect(-72f, -124f, 21f, 16f), new Rect(-86f, 9f, 42f, 119f)};
            ammoType = new ItemAmmoType(Items.surgeAlloy);
            ammoCapacity = 50;
            targetAir = true;
            health = 28000f;
            armor = 28f;
            rotateSpeed = 1.22f;
            itemCapacity = 30;
            faceTarget = false;
            immunities = ObjectSet.with(StatusEffects.burning, StatusEffects.shocked);
            targetFlags = new BlockFlag[]{BlockFlag.repair, BlockFlag.turret};
            weapons.add(new Weapon(name("destruction-weapon")){{
                reload = 110f;
                x = 0f;
                y = -0.5f;
                shootY = 33f;
                cooldownTime = 100f;
                rotate = true;
                top = true;
                rotateSpeed = 1.6f;
                recoil = 5f;
                mirror = false;
                inaccuracy = 0f;
                shootSound = Sounds.mediumCannon;
                shake = 8;
                bullet = new BasicBulletType(31f, 750f){{
                    splashDamage = 415f;
                    splashDamageRadius = 55f;
                    buildingDamageMultiplier = 1.2f;
                    lifetime = 15f;
                    //status = TODO;
                    statusDuration = 400f;
                    lightning = 2;
                    lightningDamage = 110f;
                    lightningLength = 15;
                    lightningColor = backColor = trailColor = HIPal.canaryYellow;
                    shrinkY = 0f;
                    frontColor = Color.white;
                    trailLength = 15;
                    trailWidth = 2.2f;
                    pierce = true;
                    pierceCap = 4;
                    knockback = 8f;
                    hitEffect = new MultiEffect(new ParticleEffect(){{
                        particles = 9;
                        sizeFrom = 10f;
                        sizeTo = baseLength = 0f;
                        length = 65f;
                        lifetime = 15f;
                        colorFrom = HIPal.canaryYellow;
                        colorTo = Color.white;
                        cone = 40f;
                    }}, new WaveEffect(){{
                        lifetime = 10f;
                        sizeFrom = 2f;
                        sizeTo = 60f;
                        strokeFrom = 10f;
                        strokeTo = 0f;
                        colorFrom = HIPal.canaryYellow;
                    }});
                    shootEffect = new MultiEffect(new ParticleEffect(){{
                        particles = 6;
                        sizeFrom = 8f;
                        sizeTo = baseLength = 0f;
                        length = 55f;
                        lifetime = 33f;
                        colorFrom = HIPal.canaryYellow;
                        colorTo = Color.white;
                        cone = 35f;
                    }}, new WaveEffect(){{
                        lifetime = 10f;
                        sizeFrom = strokeTo = 0f;
                        sizeTo = 30f;
                        strokeFrom = 3f;
                        colorFrom = HIPal.canaryYellow;
                    }});
                    smokeEffect = Fx.smokeCloud;
                    width = 16f;
                    height = 28f;
                    hitSound = Sounds.plasmaboom;
                    fragLifeMin = 0.1f;
                    fragBullets = 3;
                    fragRandomSpread = 60;
                    fragBullet = new PointBulletType(){{
                        trailEffect = Fx.none;
                        despawnEffect = Fx.none;
                        status = StatusEffects.blasted;
                        hitEffect = new MultiEffect(new ParticleEffect(){{
                            particles = 9;
                            sizeFrom = 8f;
                            sizeTo = baseLength = 0f;
                            length = 55f;
                            lifetime = 15f;
                            colorFrom = colorTo = HIPal.canaryYellow;
                        }}, new WaveEffect(){{
                            lifetime = 15f;
                            sizeFrom = 2f;
                            sizeTo = 40f;
                            strokeFrom = 6f;
                            strokeTo = 0f;
                            colorFrom = colorTo = HIPal.canaryYellow;
                        }});
                        hitSound = Sounds.laser;
                        collides = false;
                        damage = 550f;
                        splashDamageRadius = 40f;
                        splashDamage = 175f;
                        buildingDamageMultiplier = 1.1f;
                        lifetime = 10;
                        speed = 8f;
                    }};
                    despawnEffect = Fx.bigShockwave;
                }};
                parts.add(new RegionPart("-barrel"){{
                    mirror = true;
                    under = true;
                    moveY = -4;
                    heatProgress = PartProgress.recoil;
                    progress = PartProgress.recoil;
                }});
            }});
        }};
        purgatory = new UnitType("purgatory"){{
            squareShape = true;
            omniMovement = false;
            rotateMoveFirst = false;
            drownTimeMultiplier = 5f;
            speed = 0.8f;
            crushDamage = 10f;
            treadRects = new Rect[]{new Rect(-115f, 118f, 52f, 48f), new Rect(-118f, -160f, 79f, 144f)};
            hitSize = 66f;
            immunities = ObjectSet.with(StatusEffects.burning, StatusEffects.shocked, StatusEffects.electrified, StatusEffects.unmoving);
            ammoType = new ItemAmmoType(Items.surgeAlloy);
            ammoCapacity = 80;
            targetAir = true;
            health = 82000f;
            armor = 36f;
            drag = 0.3f;
            rotateSpeed = 1f;
            itemCapacity = 55;
            faceTarget = false;
            weapons.add(new Weapon(name("purgatory-weapon")){{
                reload = 78f;
                x = 0f;
                y = 0f;
                shoot = new ShootBarrel(){{
                    shots = 2;
                    shotDelay = 8f;
                    barrels = new float[]{
                            -9f, 40f, 0f,
                            9f, 40f, 0f};
                }};
                cooldownTime = 100f;
                rotate = true;
                rotateSpeed = 2f;
                recoil = 6f;
                mirror = false;
                inaccuracy = 0.5f;
                shootSound = Sounds.largeCannon;
                shake = 8f;
                bullet = new BasicBulletType(24f, 810f){{
                    splashDamage = 385f;
                    splashDamageRadius = 80f;
                    buildingDamageMultiplier = 1.1f;
                    sprite = name("missile");
                    width = 10;
                    height = 26;
                    hitSize = 18f;
                    lifetime = 17f;
                    drag = -0.01f;
                    absorbable = false;
                    hittable = false;
                    pierce = true;
                    pierceArmor = true;
                    pierceBuilding = true;
                    pierceCap = 3;
                    hitShake = 5;
                    status = StatusEffects.unmoving;
                    statusDuration = 80;
                    lightning = 2;
                    lightningDamage = 110f;
                    lightningLength = 15;
                    lightningColor = backColor = trailColor = HIPal.canaryYellow;
                    frontColor = Color.white;
                    trailLength = 8;
                    trailWidth = 4;
                    trailRotation = true;
                    trailChance = 1;
                    trailInterval = 33;
                    trailEffect = new ParticleEffect(){{
                        particles = 3;
                        sizeFrom = 3f;
                        sizeTo = baseLength = 0f;
                        interp = Interp.circleOut;
                        sizeInterp =  Interp.pow3In;
                        length = 10f;
                        lifetime = 8f;
                        colorFrom = colorTo = HIPal.canaryYellow;
                    }};
                    shrinkY = 0f;
                    hitEffect = new MultiEffect(new ParticleEffect(){{
                        particles = 9;
                        sizeFrom = 10f;
                        sizeTo = 0f;
                        length = 90f;
                        baseLength = 8f;
                        lifetime = 35f;
                        colorFrom = colorTo = HIPal.canaryYellow;
                    }}, new WaveEffect(){{
                        lifetime = 10;
                        sizeFrom = 2f;
                        sizeTo = 60f;
                        strokeFrom = 10f;
                        strokeTo = 0f;
                        colorFrom = colorTo = HIPal.canaryYellow;
                    }});
                    hitSound = Sounds.plasmaboom;
                    shootEffect = new MultiEffect(new ParticleEffect(){{
                        particles = 6;
                        line = true;
                        strokeFrom = 6f;
                        strokeTo = lenTo = baseLength = 0f;
                        lenFrom = 25f;
                        length = 50f;
                        lifetime = 11f;
                        colorFrom = colorTo = HIPal.canaryYellow;
                        cone = 15f;
                    }}, new WaveEffect(){{
                        lifetime = 10;
                        sizeFrom = strokeTo = 0f;
                        sizeTo = 35f;
                        strokeFrom = 4f;
                        colorFrom = colorTo = HIPal.canaryYellow;
                    }});
                    smokeEffect = Fx.smokeCloud;
                    despawnEffect = new ParticleEffect(){{
                        particles = 1;
                        sizeFrom = 10f;
                        sizeTo = length = baseLength = 0f;
                        lifetime = 65f;
                        colorFrom = HIPal.canaryYellow;
                        colorTo = Color.white;
                    }};
                }};
            }});
        }};
        //vanilla-tier6
        suzerain = new UnitType("suzerain"){{
            speed = 0.3f;
            hitSize = 40f;
            rotateSpeed = 1.65f;
            health = 63000f;
            armor = 40f;
            mechStepParticles = true;
            stepShake = 1f;
            drownTimeMultiplier = 8f;
            mechFrontSway = 1.9f;
            mechSideSway = 0.6f;
            shadowElevation = 0.1f;
            groundLayer = 74f;
            itemCapacity = 200;
            ammoType = new ItemAmmoType(HIItems.uranium);
            weapons.add(new Weapon(name("suzerain-weapon")){{
                y = 0f;
                x = 25f;
                shootY = 17f;
                reload = 36f;
                recoil = 5f;
                shake = 2f;
                shoot.shots = 3;
                shoot.shotDelay = 4f;
                ejectEffect = Fx.casing4;
                shootSound = Sounds.bang;
                bullet = new BasicBulletType(15f, 210f){{
                    pierceArmor = true;
                    pierce = true;
                    pierceCap = 10;
                    width = 14f;
                    height = 33f;
                    lifetime = 21f;
                    shootEffect = Fx.shootBig;
                    fragVelocityMin = 0.4f;
                    hitEffect = Fx.blastExplosion;
                    splashDamage = 40f;
                    splashDamageRadius = 22f;
                    status = StatusEffects.melting;
                    statusDuration = 330f;
                    fragBullets = 1;
                    fragLifeMin = 0f;
                    fragRandomSpread = 30f;
                    fragBullet = new BasicBulletType(9f, 70){{
                        width = 10f;
                        height = 10f;
                        pierceArmor = true;
                        pierce = true;
                        pierceBuilding = true;
                        pierceCap = 3;
                        lifetime = 20f;
                        hitEffect = Fx.flakExplosion;
                        splashDamage = 40f;
                        splashDamageRadius = 12f;
                    }};
                }};
            }});
        }};
        supernova = new UnitType("supernova"){{
            hitSize = 41f;
            health = 59000f;
            armor = 32f;
            flying = false;
            mineSpeed = 7f;
            mineTier = 5;
            buildSpeed = 3f;
            stepShake = 1.8f;
            rotateSpeed = 1.8f;
            drownTimeMultiplier = 8f;
            legCount = 6;
            legLength = 24f;
            legBaseOffset = 3f;
            legMoveSpace = 1.5f;
            legForwardScl = 0.58f;
            hovering = true;
            allowLegStep = true;
            shadowElevation = 0.2f;
            ammoType = new PowerAmmoType(3500);
            groundLayer = 75f;
            speed = 0.3f;
            immunities = ObjectSet.with(StatusEffects.sapped, StatusEffects.wet, StatusEffects.electrified);
            drawShields = false;
            abilities.add(new EnergyFieldAbility(60, 90, 200){{
                maxTargets = 25;
                healPercent = 6f;
                hitUnits = false;
                y = -20;
            }});
            weapons.add(new Weapon(name("supernova-weapon")){{
                mirror = false;
                top = false;
                y = 0f;
                x = 0f;
                shootY = 14f;
                reload = 170f;
                shake = 4f;
                parentizeEffects = true;
                continuous = true;
                cooldownTime = 200f;
                shoot.firstShotDelay = 59f;
                chargeSound = Sounds.lasercharge2;
                shootSound = Sounds.beam;
                recoil = 0f;
                bullet = new ContinuousLaserBulletType(){{
                    damage = 110f;
                    length = 340f;
                    hitEffect = Fx.hitMeltHeal;
                    drawSize = 420f;
                    lifetime = 200f;
                    shake = 1f;
                    despawnEffect = Fx.smokeCloud;
                    smokeEffect = Fx.none;
                    chargeEffect = Fx.greenLaserChargeSmall;
                    incendChance = 0.1f;
                    incendSpread = 8f;
                    incendAmount = 1;
                    healPercent = 1.3f;
                    collidesTeam = true;
                    colors = new Color[]{Pal.heal.cpy().a(.2f), Pal.heal.cpy().a(.5f), Pal.heal.cpy().mul(1.2f), Color.white};
                }};
            }}, new RepairBeamWeapon("repair-beam-weapon-center-large"){{
                x = 10.5f;
                y = -4.5f;
                shootY = 6f;
                beamWidth = 1f;
                repairSpeed = 4.2f;
                bullet = new BulletType(){{
                    maxRange = 180f;
                }};
            }});
        }};
        cancer = new UnitType("cancer"){{
            speed = 0.4f;
            hitSize = 33f;
            health = 54000f;
            armor = 38f;
            rotateSpeed = 1.9f;
            drownTimeMultiplier = 4f;
            legCount = 8;
            legMoveSpace = 0.8f;
            legPairOffset = 3;
            legLength = 80f;
            legExtension = -22;
            legBaseOffset = 8f;
            stepShake = 1f;
            legLengthScl = 0.93f;
            rippleScale = 3.4f;
            legSpeed = 0.18f;
            ammoType = new ItemAmmoType(Items.graphite, 8);
            legSplashDamage = 100f;
            legSplashRange = 64f;
            hovering = true;
            shadowElevation = 0.95f;
            groundLayer = Layer.legUnit;
            itemCapacity = 200;
            weapons.add(new Weapon("large-purple-mount"){{
                y = -5f;
                x = 14f;
                shootY = 7f;
                reload = 30f;
                shake = 4f;
                rotateSpeed = 2f;
                ejectEffect = Fx.casing1;
                shootSound = Sounds.shootBig;
                rotate = true;
                shadow = 12f;
                recoil = 3f;
                shoot = new ShootSpread(3, 14f);
                bullet = new ShrapnelBulletType(){{
                    length = 120f;
                    damage = 190f;
                    width = 25f;
                    serrationLenScl = 7f;
                    serrationSpaceOffset = 60f;
                    serrationFadeOffset = 0f;
                    serrations = 10;
                    serrationWidth = 6f;
                    ammoMultiplier = 2f;
                    fromColor = Pal.sapBullet;
                    toColor = Pal.sapBulletBack;
                    shootEffect = smokeEffect = Fx.sparkShoot;
                }};
            }}, new Weapon(name("cancer-cannon")){{
                y = -9f;
                x = 0f;
                shootY = 28f;
                mirror = false;
                reload = 210;
                shake = 10f;
                recoil = 10f;
                rotateSpeed = 1f;
                ejectEffect = Fx.casing3;
                shootSound = Sounds.artillery;
                rotate = true;
                shadow = 30f;
                rotationLimit = 80f;
                bullet = new ArtilleryBulletType(3.5f, 150){{
                    hitEffect = Fx.sapExplosion;
                    knockback = 0.8f;
                    lifetime = 80f;
                    width = height = 35f;
                    collidesTiles = collides = true;
                    ammoMultiplier = 4f;
                    splashDamageRadius = 140f;
                    splashDamage = 125f;
                    backColor = Pal.sapBulletBack;
                    frontColor = lightningColor = Pal.sapBullet;
                    lightning = 5;
                    lightningLength = 20;
                    smokeEffect = Fx.shootBigSmoke2;
                    hitShake = 10f;
                    lightRadius = 40f;
                    lightColor = Pal.sap;
                    lightOpacity = 0.6f;
                    status = StatusEffects.sapped;
                    statusDuration = 60f * 10;
                    fragLifeMin = 0.3f;
                    fragBullets = 9;
                    fragBullet = new ArtilleryBulletType(2.5f, 50){{
                        hitEffect = Fx.sapExplosion;
                        knockback = 0.8f;
                        lifetime = 90f;
                        width = height = 25f;
                        collidesTiles = false;
                        splashDamageRadius = 70f;
                        splashDamage = 40f;
                        backColor = Pal.sapBulletBack;
                        frontColor = lightningColor = Pal.sapBullet;
                        lightning = 2;
                        lightningLength = 5;
                        smokeEffect = Fx.shootBigSmoke2;
                        hitShake = 5f;
                        lightRadius = 30f;
                        lightColor = Pal.sap;
                        lightOpacity = 0.5f;
                        status = StatusEffects.sapped;
                        statusDuration = 60f * 10;
                    }};
                }};
            }});
        }};
        sunlit = new UnitType("sunlit"){{
            speed = 0.51f;
            accel = 0.04f;
            drag = 0.04f;
            rotateSpeed = 1f;
            baseRotateSpeed = 20f;
            engineOffset = 41f;
            engineSize = 11f;
            flying = true;
            lowAltitude = true;
            health = 60000f;
            hitSize = 62f;
            armor = 45f;
            targetFlags = new BlockFlag[]{BlockFlag.reactor, BlockFlag.battery, BlockFlag.core, null};
            ammoType = new ItemAmmoType(HIItems.uranium);
            itemCapacity = 460;
            abilities.add(new EnergyFieldAbility(220f, 90f, 192f){{
                color = Color.valueOf("ffa665");
                status = StatusEffects.burning;
                statusDuration = 180f;
                maxTargets = 30;
                healPercent = 0.8f;
            }});
            weapons.add(new Weapon(name("sunlit-weapon")){{
                shake = 1f;
                shootY = 9f;
                x = 18f;
                y = 5f;
                rotateSpeed = 5f;
                reload = 120f;
                recoil = 4f;
                shootSound = Sounds.beam;
                continuous = true;
                cooldownTime = 120f;
                shadow = 20f;
                rotate = true;
                bullet = new ContinuousLaserBulletType(){{
                    damage = 72f;
                    width = 4f;
                    length = 200f;
                    drawSize = 200f;
                    lifetime = 180f;
                    shake = 1f;
                    hitEffect = Fx.hitMeltHeal;
                    shootEffect = Fx.shootHeal;
                    smokeEffect = Fx.none;
                    largeHit = false;
                    incendChance = 0.03f;
                    incendSpread = 5f;
                    incendAmount = 1;
                    collidesTeam = true;
                }};
            }}, new Weapon("large-artillery"){{
                x = 35f;
                y = 23f;
                rotateSpeed = 2f;
                reload = 9f;
                shootSound = Sounds.shootBig;
                shadow = 7f;
                rotate = true;
                recoil = 0.5f;
                shootY = 8f;
                bullet = new FlakBulletType(8f, 35){{
                    shootEffect = Fx.shootTitan;
                    ammoMultiplier = 4f;
                    splashDamage = 75f;
                    splashDamageRadius = 25f;
                    collidesGround = true;
                    lifetime = 32f;
                    status = StatusEffects.blasted;
                }};
            }}, new Weapon("large-artillery"){{
                y = 29f;
                x = 14f;
                reload = 8f;
                ejectEffect = Fx.casing1;
                rotateSpeed = 2f;
                shake = 1f;
                shootSound = Sounds.shootBig;
                rotate = true;
                shadow = 7f;
                shootY = 12f;
                bullet = new BasicBulletType(10f, 115f){{
                    width = 11f;
                    height = 20f;
                    shootEffect = Fx.shootTitan;
                    ammoMultiplier = 2f;
                    splashDamage = 25f;
                    splashDamageRadius = 15f;
                    lifetime = 30f;
                    pierceArmor = true;
                    pierce = true;
                    pierceCap = 3;
                    status = StatusEffects.melting;
                    statusDuration = 330;
                }};
            }});
        }};
        windstorm = new UnitType("windstorm"){{
            aiController = FlyingFollowAI::new;
            armor = 41f;
            health = 61000f;
            speed = 0.8f;
            rotateSpeed = 1f;
            accel = 0.04f;
            drag = 0.018f;
            flying = true;
            engineOffset = 28f;
            engineSize = 9f;
            faceTarget = false;
            hitSize = 66f;
            payloadCapacity = (6.5f * 6.5f) * tilePayload;
            buildSpeed = 4f;
            drawShields = false;
            lowAltitude = true;
            buildBeamOffset = 43f;
            itemCapacity = 540;
            abilities.add(new ForceFieldAbility(180f, 6f, 12000f, 60f * 8, 6, 0f), new RepairFieldAbility(290f, 60f * 2, 160f));
            ammoType = new PowerAmmoType(2500);
            weapons.add(new Weapon("emp-cannon-mount"){{
                rotate = true;
                x = 25f;
                y = 3f;
                reload = 65f;
                shake = 3f;
                rotateSpeed = 2f;
                shadow = 30f;
                shootY = 7f;
                recoil = 4f;
                cooldownTime = reload - 10f;
                shootSound = Sounds.laser;
                bullet = new EmpBulletType(){{
                    float rad = 100f;
                    scaleLife = true;
                    lightOpacity = 0.7f;
                    unitDamageScl = 0.8f;
                    healPercent = 20f;
                    timeIncrease = 3f;
                    timeDuration = 60f * 20f;
                    powerDamageScl = 3f;
                    damage = 120f;
                    hitColor = lightColor = Pal.heal;
                    lightRadius = 70f;
                    shootEffect = Fx.hitEmpSpark;
                    smokeEffect = Fx.shootBigSmoke2;
                    lifetime = 80f;
                    sprite = "circle-bullet";
                    backColor = Pal.heal;
                    frontColor = Color.white;
                    width = height = 12f;
                    shrinkY = 0f;
                    speed = 5f;
                    trailLength = 20;
                    trailWidth = 6f;
                    trailColor = Pal.heal;
                    trailInterval = 3f;
                    splashDamage = 140f;
                    splashDamageRadius = rad;
                    hitShake = 4f;
                    trailRotation = true;
                    status = StatusEffects.electrified;
                    hitSound = Sounds.plasmaboom;
                    clipSize = 250f;
                    trailEffect = new Effect(16f, e -> {
                        Draw.color(Pal.heal);
                        for(int s : Mathf.signs){
                            Drawf.tri(e.x, e.y, 4f, 30f * e.fslope(), e.rotation + 90f*s);
                        }
                    });
                    hitEffect = new Effect(50f, 100f, e -> {
                        e.scaled(7f, b -> {
                            Draw.color(Pal.heal, b.fout());
                            Fill.circle(e.x, e.y, rad);
                        });
                        Draw.color(Pal.heal);
                        Lines.stroke(e.fout() * 3f);
                        Lines.circle(e.x, e.y, rad);
                        int points = 10;
                        float offset = Mathf.randomSeed(e.id, 360f);
                        for(int i = 0; i < points; i++){
                            float angle = i* 360f / points + offset;
                            Drawf.tri(e.x + Angles.trnsx(angle, rad), e.y + Angles.trnsy(angle, rad), 6f, 50f * e.fout(), angle);
                        }
                        Fill.circle(e.x, e.y, 12f * e.fout());
                        Draw.color();
                        Fill.circle(e.x, e.y, 6f * e.fout());
                        Drawf.light(e.x, e.y, rad * 1.6f, Pal.heal, e.fout());
                    });
                }};
            }});
        }};
        mosasaur = new UnitType("mosasaur"){{
            trailLength = 70;
            waveTrailX = 25f;
            waveTrailY = -32f;
            trailScl = 3.5f;
            armor = 46f;
            drag = 0.2f;
            speed = 0.6f;
            accel = 0.2f;
            hitSize = 60f;
            rotateSpeed = 0.9f;
            health = 63000f;
            itemCapacity = 350;
            ammoType = new ItemAmmoType(HIItems.uranium);
            abilities.add(new ShieldRegenFieldAbility(100f, 1500f, 60f * 4, 200f));
            weapons.addAll(new Weapon(name("mosasaur-weapon-rail")){{
                shake = 6f;
                shootY = 23f;
                bullet = new RailBulletType(){{
                    shootEffect = Fx.railShoot;
                    length = 600f;
                    pierceEffect = Fx.railHit;
                    hitEffect = Fx.massiveExplosion;
                    smokeEffect = Fx.shootBig2;
                    damage = 1750f;
                    pointEffectSpace = 30f;
                    pointEffect = Fx.railTrail;
                    pierceDamageFactor = 0.6f;
                }};
                mirror = false;
                top = false;
                rotate = true;
                rotateSpeed = 2f;
                x = 0f;
                y = 5f;
                ejectEffect = Fx.none;
                shootSound = Sounds.railgun;
                reload = 110f;
                cooldownTime = 90f;
                recoil = 5f;
            }}, new Weapon(name("mosasaur-weapon-missile")){{
                x = 24f;
                y = 1f;
                rotate = true;
                rotateSpeed = 4f;
                mirror = true;
                shadow = 20f;
                shootY = 4.5f;
                recoil = 4f;
                reload = 45f;
                velocityRnd = 0.4f;
                inaccuracy = 7f;
                ejectEffect = Fx.none;
                shake = 1f;
                shootSound = Sounds.missile;
                shoot = new ShootAlternate(){{
                    shots = 6;
                    shotDelay = 1.5f;
                    spread = 4f;
                    barrels = 3;
                }};
                bullet = new MissileBulletType(6f, 70f){{
                    homingPower = 0.12f;
                    width = height = 8f;
                    shrinkX = shrinkY = 0f;
                    drag = -0.003f;
                    homingRange = 80f;
                    keepVelocity = false;
                    splashDamageRadius = 45f;
                    splashDamage = 95f;
                    lifetime = 72f;
                    trailColor = Pal.bulletYellowBack;
                    backColor = Pal.bulletYellowBack;
                    frontColor = Pal.bulletYellow;
                    hitEffect = Fx.blastExplosion;
                    despawnEffect = Fx.blastExplosion;
                    weaveScale = 8f;
                    weaveMag = 2f;
                }};
            }});
        }};
        killerWhale = new UnitType("killer-whale"){{
            armor = 48f;
            drag = 0.2f;
            speed = 0.7f;
            accel = 0.2f;
            hitSize = 60;
            rotateSpeed = 1f;
            health = 62500f;
            itemCapacity = 800;
            ammoType = new PowerAmmoType(1800f);
            buildSpeed = 12;
            abilities.add(new SuppressionFieldAbility() {{
                orbRadius = 5f;
                particleSize = 3f;
                y = -16f;
                particles = 10;
                color = particleColor = /*effectColor = */Pal.heal;
            }});
            weapons.addAll(new Weapon("emp-cannon-mount"){{
                rotate = true;
                x = 18f;
                y = 7f;
                reload = 65f;
                shake = 3f;
                rotateSpeed = 2f;
                shadow = 30f;
                shootY = 7f;
                recoil = 4f;
                cooldownTime = reload - 10f;
                shootSound = Sounds.laser;
                bullet = new EmpBulletType(){{
                    float rad = 100f;
                    scaleLife = true;
                    lightOpacity = 0.7f;
                    unitDamageScl = 0.8f;
                    healPercent = 20f;
                    timeIncrease = 3f;
                    timeDuration = 60f * 20f;
                    powerDamageScl = 3f;
                    damage = 120;
                    hitColor = lightColor = Pal.heal;
                    lightRadius = 70f;
                    shootEffect = Fx.hitEmpSpark;
                    smokeEffect = Fx.shootBigSmoke2;
                    lifetime = 80f;
                    sprite = "circle-bullet";
                    backColor = Pal.heal;
                    frontColor = Color.white;
                    width = height = 12f;
                    shrinkY = 0f;
                    speed = 5f;
                    trailLength = 20;
                    trailWidth = 6f;
                    trailColor = Pal.heal;
                    trailInterval = 3f;
                    splashDamage = 140f;
                    splashDamageRadius = rad;
                    hitShake = 4f;
                    trailRotation = true;
                    status = StatusEffects.electrified;
                    hitSound = Sounds.plasmaboom;
                    clipSize = 250f;
                    trailEffect = new Effect(16f, e -> {
                        Draw.color(Pal.heal);
                        for(int s : Mathf.signs){
                            Drawf.tri(e.x, e.y, 4f, 30f * e.fslope(), e.rotation + 90f*s);
                        }
                    });
                    hitEffect = new Effect(50f, 100f, e -> {
                        e.scaled(7f, b -> {
                            Draw.color(Pal.heal, b.fout());
                            Fill.circle(e.x, e.y, rad);
                        });
                        Draw.color(Pal.heal);
                        Lines.stroke(e.fout() * 3f);
                        Lines.circle(e.x, e.y, rad);
                        int points = 10;
                        float offset = Mathf.randomSeed(e.id, 360f);
                        for(int i = 0; i < points; i++){
                            float angle = i* 360f / points + offset;
                            Drawf.tri(e.x + Angles.trnsx(angle, rad), e.y + Angles.trnsy(angle, rad), 6f, 50f * e.fout(), angle);
                        }
                        Fill.circle(e.x, e.y, 12f * e.fout());
                        Draw.color();
                        Fill.circle(e.x, e.y, 6f * e.fout());
                        Drawf.light(e.x, e.y, rad * 1.6f, Pal.heal, e.fout());
                    });
                }};
            }});
        }};
        //vanilla-tier6-erekir
        dominate = new TankUnitType("dominate"){{
            hitSize = 57f;
            treadPullOffset = 1;
            speed = 0.48f;
            health = 60000f;
            armor = 55f;
            crushDamage = 10f;
            rotateSpeed = 0.8f;
            treadRects = new Rect[]{new Rect(-113f, 34f, 70f, 100f), new Rect(-113f, -113f, 70f, 90f)};
            itemCapacity = 360;
            weapons.add(new Weapon(name("dominate-weapon")){{
                mirror = false;
                rotate = true;
                layerOffset = 0.1f;
                rotateSpeed = 0.9f;
                shootSound = Sounds.release;
                reload = 180f;
                recoil = 5.5f;
                shake = 5;
                x = 0;
                y = -1f;
                minWarmup = 0.9f;
                parts.addAll(new PartBow(){{
                    color = Color.valueOf("feb380");
                    turretTk = 6f;
                    bowFY = -4f;
                    bowMoveY = -33f - bowFY;
                    bowTk = 6f;
                    bowWidth = 28f;
                    bowHeight = 12f;
                }}, new BowHalo(){{
                    color = Color.valueOf("feb380");
                    stroke = 3f;
                    radius = 9f;
                    w1 = 2.8f;
                    h1 = 6f;
                    w2 = 4f;
                    h2 = 13f;
                    y = -21f;
                    sinWave = false;
                }}, new RegionPart("-glow") {{
                    color = Color.valueOf("feb380");
                    blending = Blending.additive;
                    outline = mirror = false;
                }}, new ShapePart() {{
                    progress = PartProgress.warmup.delay(0.5f);
                    color = Color.valueOf("feb380");
                    circle = true;
                    hollow = true;
                    stroke = 0f;
                    strokeTo = 2f;
                    radius = 14f;
                    layer = Layer.effect;
                    y = -21f;
                }}, new AimPart(){{
                    layer = Layer.effect;
                    y = 15f;
                    width = 0.9f;
                    length = 10f * 8;
                    spacing = 10f;
                    color = Color.valueOf("feb380");
                }});
                bullet = new BasicBulletType(10f, 660f){{
                    hitSound = despawnSound = Sounds.explosionbig;
                    splashDamage = 960f;
                    splashDamageRadius = 12f * 8;
                    buildingDamageMultiplier = 0.8f;
                    hitEffect = despawnEffect = new ExplosionEffect(){{
                        lifetime = 30f;
                        waveStroke = 5f;
                        waveLife = 10f;
                        waveRad = splashDamageRadius;
                        waveColor = Color.valueOf("feb380");
                        smokes = 7;
                        smokeSize = 13f;
                        smokeColor = Color.valueOf("feb380");
                        smokeRad = splashDamageRadius;
                        sparkColor = Color.valueOf("feb380");
                        sparks = 14;
                        sparkRad = splashDamageRadius;
                        sparkLen = 6f;
                        sparkStroke = 2f;
                    }};
                    pierce = true;
                    pierceCap = 2;
                    pierceBuilding = true;
                    trailWidth = 7f;
                    trailLength = 12;
                    trailColor = Color.valueOf("feb380");
                    healPercent = -1f;
                    despawnHit = true;
                    keepVelocity = false;
                    reflectable = false;
                }
                    @Override
                    public void draw(Bullet b){
                        super.draw(b);
                        Draw.color(Color.valueOf("feb380"));
                        Drawf.tri(b.x, b.y, 13f, 12f, b.rotation());
                    }
                };
            }});
            int i = 0;
            for(float f : new float[]{19f, -19f}){
                int fi = i ++;
                weapons.add(new Weapon(name("dominate-weapon-small")){{
                    reload = 35f + fi * 5;
                    x = 18f;
                    y = f;
                    shootY = 5.5f;
                    recoil = 2f;
                    rotate = true;
                    rotateSpeed = 2f;
                    shootCone = 2;
                    shootSound = Sounds.dullExplosion;
                    bullet = new BasicBulletType(9f, 150){{
                        sprite = "missile-large";
                        width = 8f;
                        height = 14.5f;
                        lifetime = 38f;
                        hitSize = 6.5f;
                        pierceCap = 2;
                        pierce = true;
                        pierceBuilding = true;
                        hitColor = backColor = trailColor = Color.valueOf("feb380");
                        frontColor = Color.white;
                        trailWidth = 2.8f;
                        trailLength = 8;
                        hitEffect = despawnEffect = Fx.blastExplosion;
                        shootEffect = Fx.shootTitan;
                        smokeEffect = Fx.shootSmokeTitan;
                        splashDamageRadius = 20f;
                        splashDamage = 50f;
                        trailEffect = Fx.hitSquaresColor;
                        trailRotation = true;
                        trailInterval = 3f;
                        fragBullets = 4;
                        fragBullet = new BasicBulletType(5f, 35){{
                            sprite = "missile-large";
                            width = 5f;
                            height = 7f;
                            lifetime = 15f;
                            hitSize = 4f;
                            pierceCap = 3;
                            pierce = true;
                            pierceBuilding = true;
                            hitColor = backColor = trailColor = Color.valueOf("feb380");
                            frontColor = Color.white;
                            trailWidth = 1.7f;
                            trailLength = 3;
                            drag = 0.01f;
                            despawnEffect = hitEffect = Fx.hitBulletColor;
                        }};
                    }};
                }});
            }
            parts.add(new RegionPart("-glow"){{
                color = Color.red;
                blending = Blending.additive;
                layer = -1f;
                outline = false;
            }});
        }};
        oracle = new ErekirUnitType("oracle"){{
            drag = 0.1f;
            speed = 0.9f;
            hitSize = 50f;
            health = 47000f;
            armor = 30f;
            rotateSpeed = 1.6f;
            lockLegBase = true;
            legContinuousMove = true;
            legStraightness = 0.4f;
            baseLegStraightness = 1.2f;
            legCount = 8;
            legLength = 40f;
            legForwardScl = 2.4f;
            legMoveSpace = 1.1f;
            rippleScale = 1.2f;
            stepShake = 0.5f;
            legGroupSize = 2;
            legExtension = 2f;
            legBaseOffset = 12f;
            legStraightLength = 1.1f;
            legMaxLength = 1.2f;
            ammoType = new PowerAmmoType(2000);
            legSplashDamage = 84;
            legSplashRange = 46;
            drownTimeMultiplier = 3f;
            hovering = true;
            shadowElevation = 0.4f;
            groundLayer = Layer.legUnit;
            targetAir = false;
            alwaysShootWhenMoving = true;
            itemCapacity = 340;
            weapons.add(new Weapon("collaris-weapon"){{
                shootSound = Sounds.pulseBlast;
                mirror = true;
                rotationLimit = 30f;
                rotateSpeed = 0.4f;
                rotate = true;
                x = 16.3f;
                y = -12f;
                shootY = 64f / 4f;
                recoil = 4f;
                reload = 130f;
                cooldownTime = reload * 1.2f;
                shake = 7f;
                layerOffset = 0.02f;
                shadow = 10f;
                shootStatus = StatusEffects.slow;
                shootStatusDuration = reload + 1f;
                shoot.shots = 1;
                heatColor = Color.red;
                for(int i = 0; i < 5; i++){
                    int fi = i;
                    parts.add(new RegionPart("-blade"){{
                        under = true;
                        layerOffset = -0.001f;
                        heatColor = Pal.techBlue;
                        heatProgress = PartProgress.heat.add(0.2f).min(PartProgress.warmup);
                        progress = PartProgress.warmup.blend(PartProgress.reload, 0.1f);
                        x = 13.5f / 4f;
                        y = 10f / 4f - fi * 2f;
                        moveY = 1f - fi * 1f;
                        moveX = fi * 0.3f;
                        moveRot = -45f - fi * 17f;
                        moves.add(new PartMove(PartProgress.reload.inv().mul(1.8f).inv().curve(fi / 5f, 0.2f), 0f, 0f, 36f));
                    }});
                }
                bullet = new ArtilleryBulletType(5.5f, 420f){{
                    collidesTiles = collides = true;
                    lifetime = 70f;
                    shootEffect = Fx.shootBigColor;
                    smokeEffect = Fx.shootSmokeSquareBig;
                    frontColor = Color.white;
                    trailEffect = new MultiEffect(Fx.artilleryTrail, Fx.artilleryTrailSmoke);
                    hitSound = Sounds.none;
                    width = 18f;
                    height = 24f;
                    lightColor = trailColor = hitColor = backColor = Pal.techBlue;
                    lightRadius = 40f;
                    lightOpacity = 0.7f;
                    trailWidth = 4.5f;
                    trailLength = 19;
                    trailChance = -1f;
                    despawnEffect = Fx.none;
                    despawnSound = Sounds.dullExplosion;
                    hitEffect = despawnEffect = new ExplosionEffect(){{
                        lifetime = 34f;
                        waveStroke = 4f;
                        waveColor = sparkColor = trailColor;
                        waveRad = 25f;
                        smokeSize = 0f;
                        smokeSizeBase = 0f;
                        sparks = 10;
                        sparkRad = 25f;
                        sparkLen = 8f;
                        sparkStroke = 3f;
                    }};
                    splashDamage = 175f;
                    splashDamageRadius = 50f;
                    fragBullets = 15;
                    fragVelocityMin = 0.5f;
                    fragRandomSpread = 130f;
                    fragLifeMin = 0.3f;
                    despawnShake = 5f;
                    fragBullet = new BasicBulletType(5.5f, 90f){{
                        pierceCap = 2;
                        pierceBuilding = true;
                        homingPower = 0.09f;
                        homingRange = 150f;
                        lifetime = 50f;
                        shootEffect = Fx.shootBigColor;
                        smokeEffect = Fx.shootSmokeSquareBig;
                        frontColor = Color.white;
                        hitSound = Sounds.none;
                        width = 12f;
                        height = 20f;
                        lightColor = trailColor = hitColor = backColor = Pal.techBlue;
                        lightRadius = 40f;
                        lightOpacity = 0.7f;
                        trailWidth = 2.2f;
                        trailLength = 7;
                        trailChance = -1f;
                        collidesAir = false;
                        despawnEffect = Fx.none;
                        splashDamage = 72f;
                        splashDamageRadius = 40f;
                        hitEffect = despawnEffect = new MultiEffect(new ExplosionEffect(){{
                            lifetime = 30f;
                            waveStroke = 2f;
                            waveColor = sparkColor = trailColor;
                            waveRad = 5f;
                            smokeSize = 0f;
                            smokeSizeBase = 0f;
                            sparks = 5;
                            sparkRad = 20f;
                            sparkLen = 6f;
                            sparkStroke = 2f;
                        }}, Fx.blastExplosion);
                    }};
                }};
            }}, new Weapon(name("oracle-weapon-small")){{
                shootSound = Sounds.malignShoot;
                mirror = true;
                rotate = true;
                rotateSpeed = 3;
                x = 18f;
                y = 13f;
                shootY = 47 / 4f;
                recoil = 3f;
                reload = 40f;
                shake = 3f;
                cooldownTime = 40f;
                shoot.shots = 3;
                inaccuracy = 3f;
                velocityRnd = 0.33f;
                heatColor = Color.red;
                bullet = new MissileBulletType(4.8f, 70f){{
                    homingPower = 0.2f;
                    weaveMag = 4;
                    weaveScale = 4;
                    lifetime = 65f;
                    shootEffect = Fx.shootBig2;
                    smokeEffect = Fx.shootSmokeTitan;
                    splashDamage = 80f;
                    splashDamageRadius = 30f;
                    frontColor = Color.white;
                    hitSound = Sounds.none;
                    width = height = 10f;
                    lightColor = trailColor = backColor = Pal.techBlue;
                    lightRadius = 40f;
                    lightOpacity = 0.7f;
                    trailWidth = 2.8f;
                    trailLength = 20;
                    trailChance = -1f;
                    despawnSound = Sounds.dullExplosion;
                    despawnEffect = Fx.none;
                    hitEffect = new ExplosionEffect(){{
                        lifetime = 20f;
                        waveStroke = 2f;
                        waveColor = sparkColor = trailColor;
                        waveRad = 12f;
                        smokeSize = 0f;
                        smokeSizeBase = 0f;
                        sparks = 10;
                        sparkRad = 35f;
                        sparkLen = 4f;
                        sparkStroke = 1.5f;
                    }};
                }};
            }}, new PointDefenseWeapon(name("oracle-point-defense")){{
                x = 11.2f;
                y = 25f;
                reload = 6f;
                targetInterval = 9f;
                targetSwitchInterval = 12f;
                recoil = 0.5f;
                bullet = new BulletType(){{
                    shootSound = Sounds.lasershoot;
                    shootEffect = Fx.sparkShoot;
                    hitEffect = Fx.pointHit;
                    maxRange = 200f;
                    damage = 96f;
                }};
            }});
        }};
        havoc = new ErekirUnitType("havoc"){{
            aiController = FlyingFollowAI::new;
            envDisabled = 0;
            lowAltitude = false;
            flying = true;
            drag = 0.07f;
            speed = 1f;
            rotateSpeed = 2f;
            accel = 0.1f;
            health = 28000f;
            armor = 27f;
            hitSize = 46f;
            payloadCapacity = Mathf.sqr(7f) * tilePayload;
            engineSize = 6f;
            engineOffset = 25.25f;
            itemCapacity = 360;
            float orbRad = 8f, partRad = 9f;
            int parts = 9;
            abilities.add(new SuppressionFieldAbility(){{
                orbRadius = orbRad;
                particleSize = partRad;
                y = 10f;
                particles = parts;
            }});
            for(float i : new float[]{14.2f, -14.2f}){
                abilities.add(new SuppressionFieldAbility(){{
                    orbRadius = orbRad;
                    particleSize = partRad;
                    y = -8f;
                    x = i;
                    particles = parts;
                    display = active = false;
                }});
            }
            weapons.add(new Weapon("disrupt-weapon"){{
                shootSound = Sounds.missileLarge;
                x = 19.5f;
                y = -2.5f;
                mirror = true;
                rotate = true;
                rotateSpeed = 0.4f;
                reload = 70f;
                layerOffset = -20f;
                recoil = 1f;
                rotationLimit = 22f;
                minWarmup = 0.95f;
                shootWarmupSpeed = 0.1f;
                shootY = 2f;
                shootCone = 40f;
                shoot.shots = 3;
                shoot.shotDelay = 5f;
                inaccuracy = 28f;
                parts.add(new RegionPart("-blade"){{
                    heatProgress = PartProgress.warmup;
                    progress = PartProgress.warmup.blend(PartProgress.reload, 0.15f);
                    heatColor = Color.valueOf("9c50ff");
                    x = 5 / 4f;
                    y = 0f;
                    moveRot = -33f;
                    moveY = -1f;
                    moveX = -1f;
                    under = true;
                    mirror = true;
                }});
                bullet = new CtrlMissileBulletType(name("havoc-missile"), -1, -1){{
                    shootEffect = Fx.sparkShoot;
                    smokeEffect = Fx.shootSmokeTitan;
                    hitColor = Pal.suppress;
                    maxRange = 5f;
                    speed = 5.4f;
                    keepVelocity = false;
                    homingDelay = 10f;
                    trailColor = Pal.sapBulletBack;
                    trailLength = 8;
                    hitEffect = despawnEffect = new ExplosionEffect(){{
                        lifetime = 50f;
                        waveStroke = 5f;
                        waveLife = 8f;
                        waveColor = Color.white;
                        sparkColor = smokeColor = Pal.suppress;
                        waveRad = 40f;
                        smokeSize = 4f;
                        smokes = 7;
                        smokeSizeBase = 0f;
                        sparks = 10;
                        sparkRad = 40f;
                        sparkLen = 6f;
                        sparkStroke = 2f;
                    }};
                    damage = 160;
                    splashDamage = 220;
                    splashDamageRadius = 30;
                    buildingDamageMultiplier = 0.5f;
                    parts.add(new ShapePart(){{
                        layer = Layer.effect;
                        circle = true;
                        y = -0.25f;
                        radius = 1.5f;
                        color = Pal.suppress;
                        colorTo = Color.white;
                        progress = PartProgress.life.curve(Interp.pow5In);
                    }});
                }};
            }});
            setEnginesMirror(new UnitEngine(95f / 4f, -56f / 4, 5f, 330f), new UnitEngine(89f / 4, -95f / 4, 4f, 315f));
        }};
        //miner-erekir
        miner = new ErekirUnitType("miner"){{
            defaultCommand = UnitCommand.mineCommand;
            controller = u -> new MinerPointAI();
            flying = true;
            drag = 0.06f;
            accel = 0.12f;
            speed = 1.5f;
            health = 100;
            engineSize = 1.8f;
            engineOffset = 5.7f;
            range = 50f;
            hitSize = 12f;
            itemCapacity = 20;
            isEnemy = false;
            payloadCapacity = 0;
            mineTier = 10;//The stronghold determines the tier.
            mineSpeed = 1.6f;
            mineWalls = true;
            mineFloor = true;
            useUnitCap = false;
            logicControllable = false;
            playerControllable = false;
            allowedInPayloads = false;
            createWreck = false;
            envEnabled = Env.any;
            envDisabled = Env.none;
            hidden = true;
            targetable = false;
            hittable = false;
            targetPriority = -2;
            setEnginesMirror(new UnitEngine(24 / 4f, -24 / 4f, 2.3f, 315f));
        }};
        largeMiner = new ErekirUnitType("large-miner"){{
            defaultCommand = UnitCommand.mineCommand;
            controller = u -> new MinerPointAI();
            flying = true;
            drag = 0.06f;
            accel = 0.12f;
            speed = 1.5f;
            health = 100;
            engineSize = 2.6f;
            engineOffset = 9.8f;
            range = 50f;
            mineRange = 100f;
            hitSize = 16f;
            itemCapacity = 50;
            isEnemy = false;
            payloadCapacity = 0;
            mineTier = 10;
            mineSpeed = 3.2f;
            mineWalls = true;
            mineFloor = true;
            useUnitCap = false;
            logicControllable = false;
            playerControllable = false;
            allowedInPayloads = false;
            createWreck = false;
            envEnabled = Env.any;
            envDisabled = Env.none;
            hidden = true;
            targetable = false;
            hittable = false;
            targetPriority = -2;
            setEnginesMirror(new UnitEngine(40 / 4f, -40 / 4f, 3f, 315f));
        }};
        //other
        pioneer = new UnitType("pioneer"){{
            drag = 0.1f;
            speed = 0.62f;
            hitSize = 23f;
            health = 7200f;
            armor = 14f;
            rotateSpeed = 2.7f;
            legCount = 6;
            legMoveSpace = 1f;
            legPairOffset = 3f;
            legLength = 30f;
            legExtension = -15f;
            legBaseOffset = 10f;
            stepShake = 1f;
            legLengthScl = 0.96f;
            rippleScale = 2f;
            legSpeed = 0.2f;
            ammoType = new PowerAmmoType(2000);
            legSplashDamage = 32f;
            legSplashRange = 30f;
            drownTimeMultiplier = 2f;
            hovering = true;
            shadowElevation = 0.65f;
            groundLayer = Layer.legUnit;
            payloadCapacity = (3 * 3) * tilePayload;
            buildSpeed = 2.5f;
            buildBeamOffset = 2.3f;
            range = 140f;
            mineTier = 5;
            mineSpeed = 10.5f;
            weapons.add(new Weapon("avert-weapon"){{
                top = false;
                shake = 2f;
                shootY = 4f;
                x = 10.5f;
                reload = 55f;
                recoil = 4f;
                shootSound = Sounds.laser;
                bullet = new LaserBulletType(){{
                    damage = 45f;
                    recoil = 1f;
                    sideAngle = 45f;
                    sideWidth = 1f;
                    sideLength = 70f;
                    healPercent = 10f;
                    collidesTeam = true;
                    length = 135f;
                    colors = new Color[]{Pal.sapBulletBack.cpy().a(0.4f), Pal.sapBullet, Color.white};
                }};
            }});
        }};
        vulture = new UnitType("vulture"){{
            aiController = SurroundAI::new;
            weapons.add(new Weapon(){{
                top = false;
                rotate = true;
                alternate = true;
                mirror = false;
                x = 0f;
                y = -10f;
                reload = 25f;
                inaccuracy = 3f;
                ejectEffect = Fx.none;
                bullet = new AccelBulletType(6f, 50f, "missile-large"){{
                    shrinkX = shrinkY = 0.35f;
                    buildingDamageMultiplier = 1.5f;
                    keepVelocity = false;
                    velocityBegin = 0.5f;
                    velocityIncrease = 3f;
                    accelerateBegin = 0.01f;
                    accelerateEnd = 0.9f;
                    homingPower = 0f;
                    hitColor = trailColor = lightningColor = backColor = lightColor = Pal.accent;
                    frontColor = Pal.bulletYellow;
                    splashDamageRadius = 20;
                    splashDamage = damage * 0.3f;
                    width = height = 8f;
                    trailChance = 0.2f;
                    trailParam = 1.75f;
                    trailEffect = HIFx.trailToGray;
                    lifetime = 90f;
                    collidesAir = false;
                    hitSound = Sounds.explosion;
                    hitEffect = HIFx.square45_4_45;
                    shootEffect = HIFx.circleSplash;
                    smokeEffect = Fx.shootBigSmoke;
                    despawnEffect = HIFx.crossBlast(hitColor, 50f);
                }};
                shootSound = HISounds.blaster;
            }});
            abilities.add(new JavelinAbility(20f, 5f, 29f){{
                minDamage = 5f;
                minSpeed = 2f;
                maxSpeed = 4f;
                magX = 0.2f;
                magY = 0.1f;
            }});
            targetAir = false;
            maxRange = 200;
            engineOffset = 14.0F;
            engineSize = 4f;
            speed = 5f;
            accel = 0.04F;
            drag = 0.0075F;
            circleTarget = true;
            hitSize = 14f;
            health = 1000f;
            baseRotateSpeed = 1.5f;
            rotateSpeed = 2.5f;
            armor = 10.5f;
            flying = true;
        }};
        burner = new UnitType("burner"){{
            speed = 0.36f;
            hitSize = 24f;
            rotateSpeed = 2.1f;
            health = 16700;
            armor = 32f;
            mechFrontSway = 1f;
            ammoType = new PowerAmmoType(500);
            mechStepParticles = true;
            stepShake = 0.15f;
            singleTarget = true;
            drownTimeMultiplier = 4f;
            range = 168f;
            weapons.add(new Weapon("scepter-weapon"){{
                mirror = top = false;
                y = 1f;
                x = 16f;
                shootY = 8f;
                reload = 6f;
                recoil = 2f;
                shake = 1f;
                shootSound = Sounds.flame;
                inaccuracy = 3f;
                bullet = new FlameBulletType(Pal.lightPyraFlame, Pal.darkPyraFlame, Color.gray, range + 8f, 8, 72, 22f){{
                    damage = 225f;
                    collidesAir = true;
                    statusDuration = 60f * 6;
                    ammoMultiplier = 4f;
                }
                    @Override
                    public void update(Bullet b) {
                        Seq<Healthc> seq = new Seq<>();
                        float r = flameCone * (1 - b.foutpow());
                        indexer.allBuildings(b.x, b.y, r, seq::addUnique);
                        Units.nearby(b.x - r, b.y - r, r * 2, r * 2, u -> {
                            if(u.type != null && u.type.targetable && b.within(u, r)) seq.addUnique(u);
                        });
                        for(int i = 0; i < seq.size; i++){
                            Healthc hc = seq.get(i);
                            if(hc != null && !hc.dead()) {
                                if(!b.hasCollided(hc.id())) {
                                    if(hc.health() <= damage) hc.kill();
                                    else hc.health(hc.health() - damage);
                                    b.collided.add(hc.id());
                                }
                            }
                        }
                    }
                };
            }
                @Override
                public float range(){
                    return range;
                }
            });
        }};
        //boss
        tiger = new UnitType("tiger"){{
            drawShields = false;
            engineOffset = 18f;
            engineSize = 9f;
            speed = 0.32f;
            hitSize = 52f;
            health = 182000f;
            buildSpeed = 4f;
            armor = 86f;
            envDisabled = Env.none;
            ammoType = new PowerAmmoType(3000f);
            weapons.add(new Weapon(name("tiger-cannon")){{
                top = false;
                rotate = true;
                rotationLimit = 13f;
                rotateSpeed = 0.75f;
                alternate = true;
                shake = 3.5f;
                shootY = 32f;
                x = 42f;
                y = -2f;
                recoil = 3.4f;
                predictTarget = true;
                shootCone = 30f;
                reload = 60f;
                parts.add(new RegionPart("-shooter"){{
                    under = turretShading = true;
                    outline = true;
                    mirror = false;
                    moveY = -8f;
                    progress = PartProgress.recoil;
                }});
                shoot = new ShootPattern(){{
                    shots = 3;
                    shotDelay = 3.5f;
                }};
                velocityRnd = 0.075f;
                inaccuracy = 6.0F;
                ejectEffect = Fx.none;
                bullet = new BasicBulletType(8, 200f, name("strike")){{
                    trailColor = lightningColor = backColor = lightColor = Pal.techBlue;
                    frontColor = Pal.techBlue;
                    lightning = 2;
                    lightningCone = 360;
                    lightningLengthRand = lightningLength = 8;
                    homingPower = 0;
                    scaleLife = true;
                    collides = false;
                    trailLength = 15;
                    trailWidth = 3.5f;
                    splashDamage = lightningDamage = damage;
                    splashDamageRadius = 48f;
                    lifetime = 95f;
                    width = 22f;
                    height = 35f;
                    trailEffect = HIFx.trailToGray;
                    trailParam = 3f;
                    trailChance = 0.35f;
                    hitShake = 7f;
                    hitSound = Sounds.explosion;
                    hitEffect = HIFx.hitSpark(backColor, 75f, 24, 95f, 2.8f, 16);
                    smokeEffect = new MultiEffect(HIFx.hugeSmokeGray, HIFx.circleSplash(backColor, 60f, 8, 60f, 6));
                    shootEffect = HIFx.hitSpark(backColor, 30f, 15, 35f, 1.7f, 8);
                    despawnEffect = HIFx.blast(backColor, 60);
                    fragBullet = HIBullets.basicSkyFrag;
                    fragBullets = 5;
                    fragLifeMax = 0.6f;
                    fragLifeMin = 0.2f;
                    fragVelocityMax = 0.35f;
                    fragVelocityMin = 0.074f;
                }
                    @Override
                    public void hit(Bullet b, float x, float y){
                        super.hit(b, x, y);
                        UltFire.createChance(b, splashDamageRadius, 0.4f);
                    }
                };
                shootSound = Sounds.artillery;
            }}, new Weapon(){{
                mirror = false;
                rotate = true;
                rotateSpeed = 25f;
                x = 0;
                y = 12f;
                recoil = 2.7f;
                shootY = 7f;
                shootCone = 40f;
                velocityRnd = 0.075f;
                reload = 150f;
                xRand = 18f;
                shoot = new ShootSine(){{
                    shots = 12;
                    shotDelay = 4f;
                }};
                inaccuracy = 5f;
                ejectEffect = Fx.none;
                bullet = HIBullets.annMissile;
                shootSound = HISounds.launch;
            }}, new Weapon(){{
                x = 26f;
                y = -12.5f;
                reload = 60f;
                shoot = new ShootPattern(){{
                    shots = 3;
                    shotDelay = 8f;
                }};
                shake = 3f;
                shootX = 2;
                xRand = 5;
                mirror = true;
                rotateSpeed = 2.5f;
                alternate = true;
                shootSound = HISounds.launch;
                shootCone = 30f;
                shootY = 5f;
                top = true;
                rotate = true;
                bullet = new BasicBulletType(5.25f, 150f, name("strike")){{
                    lifetime = 60;
                    knockback = 12f;
                    width = 11f;
                    height = 28f;
                    trailWidth = 2.2f;
                    trailLength = 20;
                    drawSize = 300f;
                    homingDelay = 5f;
                    homingPower = 0.0075f;
                    homingRange = 140f;
                    splashDamageRadius = 16f;
                    splashDamage = damage * 0.75f;
                    backColor = lightColor = lightningColor = trailColor = hitColor = frontColor = Pal.techBlue;
                    hitEffect = HIFx.circleSplash(backColor, 40f, 4, 40f, 6f);
                    despawnEffect = HIFx.hitSparkLarge;
                    shootEffect = HIFx.shootCircleSmall(backColor);
                    smokeEffect = Fx.shootBigSmoke2;
                    trailChance = 0.6f;
                    trailEffect = HIFx.trailToGray;
                    hitShake = 3f;
                    hitSound = Sounds.plasmaboom;
                }};
            }}, new Weapon(){{
                mirror = false;
                top = alternate = autoTarget = rotate = true;
                predictTarget = controllable = false;
                x = 0f;
                y = 14f;
                reload = 12f;
                recoil = 3f;
                inaccuracy = 0;
                shoot = new ShootPattern();
                rotateSpeed = 25f;
                shootSound = HISounds.gauss;
                bullet = new ShrapnelBulletType(){{
                    lifetime = 45f;
                    length = 200f;
                    damage = 180.0F;
                    status = StatusEffects.shocked;
                    statusDuration = 60f;
                    fromColor = toColor = Pal.techBlue;
                    serrationSpaceOffset = 40f;
                    width = 6f;
                    shootEffect = HIFx.lightningHitSmall(fromColor);
                    smokeEffect = new MultiEffect(HIFx.techBlueCircleSplash, new Effect(lifetime + 10f, b -> {
                        Draw.color(fromColor, b.fin());
                        Fill.circle(b.x, b.y, (width / 1.75f) * b.fout());
                    }));
                }};
            }});
            for(float[] i : new float[][]{{22f, 18f}, {25f, 2f}}){
                weapons.add(new PointDefenseWeapon(name("tiger-cannon-small")){{
                    x = i[0];
                    y = i[1];
                    color = Pal.techBlue;
                    mirror = top = alternate = true;
                    reload = 6.0F;
                    targetInterval = 6.0F;
                    targetSwitchInterval = 6.0F;
                    bullet = new BulletType(){{
                        shootEffect = HIFx.shootLineSmall(color);
                        hitEffect = HIFx.lightningHitSmall;
                        hitColor = color;
                        maxRange = 240.0F;
                        damage = 150f;
                    }};
                }});
            }
            groundLayer = Layer.legUnit + 0.1f;
            mechLandShake = 12f;
            stepShake = 5f;
            rotateSpeed = 1f;
            fallSpeed = 0.03f;
            mechStepParticles = true;
            canDrown = false;
            mechFrontSway = 2.2f;
            mechSideSway = 0.8f;
            canBoost = true;
            boostMultiplier = 2.5f;
        }};
        thunder = new UnitType("thunder"){{
            health = 226000f;
            armor = 115f;
            rotateSpeed = 1f;
            speed = 0.66f;
            squareShape = true;
            omniMovement = false;
            rotateMoveFirst = true;
            envDisabled = Env.none;
            hitSize = 70f;
            accel = 0.25f;
            treadRects = new Rect[]{new Rect(-115f, 118f, 52f, 48f), new Rect(-118f, -160f, 79f, 144f)};
            hoverable = hovering = true;
            ammoType = new PowerAmmoType(3000);
            crushDamage = 20;
            weapons.add(new Weapon(name("thunder-turret")){{
                x = y = 0;
                rotate = true;
                rotateSpeed = 3.5f;
                mirror = alternate = false;
                layerOffset = 0.15f;
                shootWarmupSpeed /= 2f;
                minWarmup = 0.9f;
                recoil = 2.25f;
                shake = 8f;
                reload = 120f;
                shootY = 25;
                cooldownTime = 45f;
                heatColor = HIPal.ancientHeat;
                shoot = new ShootAlternate(12.3f){{
                    shots = 2;
                    shotDelay = 0f;
                }};
                inaccuracy = 1.3f;
                shootSound = HISounds.flak;
                bullet = new AccelBulletType(1f, 1100f, "missile-large"){{
                    lightOpacity = 0.7f;
                    healPercent = 20f;
                    reflectable = false;
                    knockback = 3f;
                    impact = true;
                    velocityBegin = 1f;
                    velocityIncrease = 18f;
                    accelerateBegin = 0.05f;
                    accelerateEnd = 0.55f;
                    pierce = pierceBuilding = true;
                    pierceCap = 5;
                    lightningColor = backColor = trailColor = hitColor = lightColor = HIPal.ancient;
                    lightRadius = 70f;
                    shootEffect = new EffectWrapper(HIFx.shootLine(33f, 32), backColor);
                    smokeEffect = HIFx.hugeSmokeLong;
                    lifetime = 40f;
                    frontColor = Color.white;
                    lightning = 2;
                    lightningDamage = damage / 8f + 10f;
                    lightningLength = 7;
                    lightningLengthRand = 16;
                    splashDamageRadius = 36f;
                    splashDamage = damage / 3f;
                    width = 13f;
                    height = 35f;
                    speed = 8f;
                    trailLength = 20;
                    trailWidth = 2.3f;
                    trailInterval = 1.76f;
                    hitShake = 8f;
                    trailRotation = true;
                    keepVelocity = true;
                    hitSound = Sounds.plasmaboom;
                    trailEffect = new Effect(10f, e -> {
                        Draw.color(trailColor, Color.white, e.fout() * 0.66f);
                        for(int s : Mathf.signs){
                            Drawn.tri(e.x, e.y, 3f, 30f * Mathf.curve(e.fin(), 0, 0.1f) * e.fout(0.9f), e.rotation + 145f * s);
                        }
                    });
                    hitEffect = new MultiEffect(HIFx.square45_6_45, HIFx.hitSparkLarge);
                    despawnEffect = HIFx.lightningHitLarge;
                }
                    @Override
                    public void hitTile(Bullet b, Building build, float x, float y, float initialHealth, boolean direct){
                        super.hitTile(b, build, x, y, initialHealth, direct);
                        if(build.block.armor > 10f || build.block.absorbLasers)b.time(b.lifetime());
                    }
                };
                parts.add(new RegionPart("-panel"){{
                    outline = mirror = true;
                    x = y = 0;
                    moveX = -2;
                    moveRot = -6f;
                    moveY = -2f;
                    progress = PartProgress.warmup;
                }}, new RegionPart("-barrel"){{
                    under = outline = true;
                    x = y = 0;
                    moveY = -6f;
                    progress = PartProgress.recoil;
                }}, new HaloPart(){{
                    shapes = 1;
                    layer = Layer.bullet + 1f;
                    x = 0;
                    y = -32f;
                    color = HIPal.ancient;
                    hollow = true;
                    sides = 4;
                    stroke = 0f;
                    strokeTo = 1.1f;
                    radiusTo = 6f;
                    rotateSpeed = 1.2f;
                    progress = PartProgress.warmup.blend(PartProgress.reload, 0.3f);
                }});
            }});
            int i = 0;
            for(float f : new float[]{11f, -13f}){
                int fi = i ++;
                weapons.add(new Weapon(name("thunder-laser")){{
                    reload = 35f + fi * 5;
                    x = 18.5f;
                    y = f;
                    shootY = 3f;
                    shootX = -1f;
                    recoil = 1f;
                    mirror = rotate = true;
                    rotateSpeed = 4f;
                    autoTarget = true;
                    controllable = false;
                    shootSound = HISounds.gauss;
                    bullet = new BasicBulletType(12f,220f){{
                        width = 9f;
                        height = 28f;
                        trailWidth = 1.3f;
                        trailLength = 7;
                        lifetime = 39f;
                        drag = 0.015f;
                        trailColor = hitColor = backColor = lightColor = lightningColor = HIPal.ancient;
                        frontColor = Color.white;
                        pierce = pierceArmor = true;
                        pierceCap = 3;
                        smokeEffect = Fx.shootSmallSmoke;
                        shootEffect = HIFx.shootCircleSmall(backColor);
                        despawnEffect = HIFx.square45_4_45;
                        hitEffect = HIFx.hitSpark;
                    }};
                }});
            }
            drownTimeMultiplier = 26f;
        }};
    }
}
