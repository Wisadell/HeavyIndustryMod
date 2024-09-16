package heavyindustry.content;

import heavyindustry.ai.*;
import heavyindustry.core.*;
import heavyindustry.gen.*;
import heavyindustry.graphics.*;
import heavyindustry.world.draw.*;
import heavyindustry.entities.effect.*;
import heavyindustry.entities.bullet.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import mindustry.Vars;
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

import static heavyindustry.core.HeavyIndustryMod.name;
import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.stroke;
import static mindustry.Vars.tilePayload;

/** Defines the {@linkplain UnitType units} this mod offers. */
public class HIUnitTypes {
    //one day, someone asks me : why not use xxxUnit::new? ha, I say : I don't know...
    static {
        //tier6
        EntityMapping.nameMap.put(name("suzerain"), EntityMapping.idMap[4]);
        EntityMapping.nameMap.put(name("supernova"), EntityMapping.idMap[24]);
        EntityMapping.nameMap.put(name("cancer"), EntityMapping.idMap[33]);
        EntityMapping.nameMap.put(name("sunlit"), EntityMapping.idMap[3]);
        EntityMapping.nameMap.put(name("windstorm"), EntityMapping.idMap[5]);
        EntityMapping.nameMap.put(name("mosasaur"), EntityMapping.idMap[20]);
        EntityMapping.nameMap.put(name("killer-whale"), EntityMapping.idMap[20]);
        //erekir-tier6
        EntityMapping.nameMap.put(name("dominate"), EntityMapping.idMap[43]);
        EntityMapping.nameMap.put(name("oracle"), EntityMapping.idMap[24]);
        EntityMapping.nameMap.put(name("havoc"), EntityMapping.idMap[5]);
        //other
        EntityMapping.nameMap.put(name("scavenger"), TankLegacyUnit::create);
        EntityMapping.nameMap.put(name("pioneer"), LegsPayloadLegacyUnit::create);
        EntityMapping.nameMap.put(name("burner"), EntityMapping.idMap[4]);
        //other-erekir
        EntityMapping.nameMap.put(name("draug"), NoCoreDepositBuildingTetherLegsUnit::create);
        //elite
        EntityMapping.nameMap.put(name("desolation-lord"), LegsPayloadLegacyUnit::create);
        //boss
        EntityMapping.nameMap.put(name("thunder"), EntityMapping.idMap[43]);
    }

    public static UnitType
            //tier6
            suzerain,supernova,cancer,sunlit,windstorm,mosasaur,killerWhale,
            //erekir-tier6
            dominate,oracle,havoc,
            //other
            scavenger,
            pioneer,
            burner,
            //other-erekir
            draug,
            //elite
            desolationLord,
            //boss
            thunder;

    /** Instantiates all contents. Called in the main thread in {@link HeavyIndustryMod#loadContent()}. */
    public static void load(){
        //tier6
        suzerain = new UnitType("suzerain"){{
            speed = 0.3f;
            hitSize = 40f;
            rotateSpeed = 1.65f;
            health = 63000;
            armor = 40f;
            mechStepParticles = true;
            stepShake = 1f;
            drownTimeMultiplier = 8f;
            mechFrontSway = 1.9f;
            mechSideSway = 0.6f;
            shadowElevation = 0.1f;
            groundLayer = 74;
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
                shoot.shotDelay = 4;
                ejectEffect = Fx.casing4;
                shootSound = Sounds.bang;
                bullet = new BasicBulletType(15f, 210){{
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
                    statusDuration = 330;
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
            mineSpeed = 7;
            mineTier = 5;
            buildSpeed = 3;
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
            groundLayer = 75;
            speed = 0.3f;
            immunities = ObjectSet.with(StatusEffects.sapped, StatusEffects.wet, StatusEffects.electrified);
            drawShields = false;
            abilities.add(new EnergyFieldAbility(60, 90, 200){{
                maxTargets = 25;
                healPercent = 6;
                hitUnits = false;
                y = -20;
            }});
            weapons.add(new Weapon(name("supernova-weapon")){{
                mirror = false;
                top = false;
                y = 0f;
                x = 0f;
                shootY = 14f;
                reload = 170;
                shake = 4f;
                parentizeEffects = true;
                continuous = true;
                cooldownTime = 200;
                shoot.firstShotDelay = 59;
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
                shootY = 6;
                beamWidth = 1;
                repairSpeed = 4.2f;
                bullet = new BulletType(){{
                    maxRange = 180;
                }};
            }});
        }};
        cancer = new UnitType("cancer"){{
            speed = 0.4f;
            hitSize = 33f;
            health = 54000;
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
            legSplashDamage = 100;
            legSplashRange = 64;
            hovering = true;
            shadowElevation = 0.95f;
            groundLayer = Layer.legUnit;
            itemCapacity = 200;
            weapons.add(new Weapon("large-purple-mount"){{
                y = -5f;
                x = 14f;
                shootY = 7f;
                reload = 30;
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
                    ammoMultiplier = 2;
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
            baseRotateSpeed = 20;
            engineOffset = 41;
            engineSize = 11;
            flying = true;
            lowAltitude = true;
            health = 60000;
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
                cooldownTime = 120;
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
            }}, new Weapon(name("sunlit-m")){{
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
            }}, new Weapon(name("sunlit-bubble")){{
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
            health = 61000;
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
            buildBeamOffset = 43;
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
                        color(Pal.heal);
                        for(int s : Mathf.signs){
                            Drawf.tri(e.x, e.y, 4f, 30f * e.fslope(), e.rotation + 90f*s);
                        }
                    });
                    hitEffect = new Effect(50f, 100f, e -> {
                        e.scaled(7f, b -> {
                            color(Pal.heal, b.fout());
                            Fill.circle(e.x, e.y, rad);
                        });
                        color(Pal.heal);
                        stroke(e.fout() * 3f);
                        Lines.circle(e.x, e.y, rad);
                        int points = 10;
                        float offset = Mathf.randomSeed(e.id, 360f);
                        for(int i = 0; i < points; i++){
                            float angle = i* 360f / points + offset;
                            Drawf.tri(e.x + Angles.trnsx(angle, rad), e.y + Angles.trnsy(angle, rad), 6f, 50f * e.fout(), angle);
                        }
                        Fill.circle(e.x, e.y, 12f * e.fout());
                        color();
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
            armor = 46;
            drag = 0.2f;
            speed = 0.6f;
            accel = 0.2f;
            hitSize = 60;
            rotateSpeed = 0.9f;
            health = 63000;
            itemCapacity = 350;
            ammoType = new ItemAmmoType(HIItems.uranium);
            abilities.add(new ShieldRegenFieldAbility(100, 1500, 60 * 4, 200));
            weapons.addAll(new Weapon(name("mosasaur-weapon-rail")){{
                shake = 6;
                shootY = 23;
                bullet = new RailBulletType(){{
                    shootEffect = Fx.railShoot;
                    length = 600;
                    pierceEffect = Fx.railHit;
                    hitEffect = Fx.massiveExplosion;
                    smokeEffect = Fx.shootBig2;
                    damage = 1750;
                    pointEffectSpace = 30f;
                    pointEffect = Fx.railTrail;
                    pierceDamageFactor = 0.6f;
                }};
                mirror = false;
                top = false;
                rotate = true;
                rotateSpeed = 2;
                x = 0;
                y = 5;
                ejectEffect = Fx.none;
                shootSound = Sounds.railgun;
                reload = 110;
                cooldownTime = 90;
                recoil = 5;
            }}, new Weapon(name("mosasaur-weapon-missile")){{
                x = 24;
                y = 1;
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
                bullet = new MissileBulletType(6, 70){{
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
            armor = 48;
            drag = 0.2f;
            speed = 0.7f;
            accel = 0.2f;
            hitSize = 60;
            rotateSpeed = 1f;
            health = 62500;
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
            weapons.addAll(new Weapon(name("killer-whale-weapons")){{
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
                        color(Pal.heal);
                        for(int s : Mathf.signs){
                            Drawf.tri(e.x, e.y, 4f, 30f * e.fslope(), e.rotation + 90f*s);
                        }
                    });
                    hitEffect = new Effect(50f, 100f, e -> {
                        e.scaled(7f, b -> {
                            color(Pal.heal, b.fout());
                            Fill.circle(e.x, e.y, rad);
                        });
                        color(Pal.heal);
                        stroke(e.fout() * 3f);
                        Lines.circle(e.x, e.y, rad);
                        int points = 10;
                        float offset = Mathf.randomSeed(e.id, 360f);
                        for(int i = 0; i < points; i++){
                            float angle = i* 360f / points + offset;
                            Drawf.tri(e.x + Angles.trnsx(angle, rad), e.y + Angles.trnsy(angle, rad), 6f, 50f * e.fout(), angle);
                        }
                        Fill.circle(e.x, e.y, 12f * e.fout());
                        color();
                        Fill.circle(e.x, e.y, 6f * e.fout());
                        Drawf.light(e.x, e.y, rad * 1.6f, Pal.heal, e.fout());
                    });
                }};
            }});
        }};
        //erekir-tier6
        dominate = new TankUnitType("dominate"){{
            hitSize = 57f;
            treadPullOffset = 1;
            speed = 0.48f;
            health = 60000;
            armor = 52f;
            crushDamage = 10f;
            rotateSpeed = 0.8f;
            treadRects = new Rect[]{new Rect(-113, 34, 70, 100), new Rect(-113, -113, 70, 90)};
            itemCapacity = 360;
            weapons.add(new Weapon(name("dominate-weapon")){{
                mirror = false;
                rotate = true;
                layerOffset = 0.1f;
                rotateSpeed = 0.9f;
                shootSound = Sounds.release;
                reload = 180;
                recoil = 5.5f;
                shake = 5;
                x = 0;
                y = -1f;
                minWarmup = 0.9f;
                parts.addAll(new PartBow(){{
                    color = Color.valueOf("feb380");
                    turretTk = 6;
                    bowFY = -4;
                    bowMoveY = -33 - bowFY;
                    bowTk = 6;
                    bowWidth = 28;
                    bowHeight = 12f;
                }}, new BowHalo(){{
                    color = Color.valueOf("feb380");
                    stroke = 3;
                    radius = 9;
                    w1 = 2.8f;
                    h1 = 6;
                    w2 = 4;
                    h2 = 13;
                    y = -21;
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
                    stroke = 0;
                    strokeTo = 2;
                    radius = 14;
                    layer = Layer.effect;
                    y = -21;
                }}, new AimPart(){{
                    layer = Layer.effect;
                    y = 15;
                    width = 0.9f;
                    length = 10 * 8;
                    spacing = 10;
                    color = Color.valueOf("feb380");
                }});
                bullet = new BallistaBulletType(Color.valueOf("feb380")){{
                    hitSound = despawnSound = Sounds.explosionbig;
                    damage = 410;
                    splashDamage = 390;
                    splashDamageRadius = 12 * 8f;
                    buildingDamageMultiplier = 0.8f;
                    hitEffect = despawnEffect = new ExplosionEffect(){{
                        lifetime = 30f;
                        waveStroke = 5f;
                        waveLife = 10f;
                        waveRad = splashDamageRadius;
                        waveColor = Color.valueOf("feb380");
                        smokes = 7;
                        smokeSize = 13;
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
                    speed = 10;
                    trailWidth = 7;
                    trailLength = 12;
                    trailColor = Color.valueOf("feb380");
                    fragBullet = null;
                    fragBullets= 0;
                    healPercent = -1;
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
                weapons.add(new Weapon("locus-weapon"){{
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
            health = 51000;
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
                bullet = new ArtilleryBulletType(5.5f, 420){{
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
                    fragBullet = new BasicBulletType(5.5f, 90){{
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
            }}, new Weapon(name("oracle-weapon")){{
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
                bullet = new MissileBulletType(4.8f, 70){{
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
                y = -3.2f;
                particles = parts;
            }});
            for(float i : new float[]{14.2f, -14.2f}){
                abilities.add(new SuppressionFieldAbility(){{
                    orbRadius = orbRad;
                    particleSize = partRad;
                    y = -12.4f;
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
            setEnginesMirror(new UnitEngine(95 / 4f, -56 / 4f, 5f, 330f), new UnitEngine(89 / 4f, -95 / 4f, 4f, 315f));
        }};
        //other
        scavenger = new TankUnitType("scavenger"){{
            hitSize = 26f;
            treadPullOffset = 5;
            speed = 0.64f;
            rotateSpeed = 1.5f;
            health = 3500;
            armor = 10f;
            itemCapacity = 1000;
            treadRects = new Rect[]{new Rect(16 - 60f, 48 - 70f, 30, 75), new Rect(44 - 60f, 17 - 70f, 17, 60)};
            researchCostMultiplier = 0f;
            buildSpeed = 4.5f;
            weapons.add(new Weapon(){{
                x = 22f / 4f;
                y = -3f;
                shootX = -3f / 4f;
                shootY = 4.5f / 4f;
                rotate = true;
                rotateSpeed = 35f;
                reload = 35f;
                shootSound = Sounds.laser;
                bullet = new LaserBulletType(){{
                    damage = 45f;
                    sideAngle = 30f;
                    sideWidth = 1f;
                    sideLength = 5.25f * 8;
                    length = 13.75f * 8f;
                    colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
                }};
            }}, new RepairBeamWeapon("repair-beam-weapon-center-large"){{
                x = 8.5f;
                y = -3.5f;
                shootY = 6;
                beamWidth = 1;
                repairSpeed = 1.8f;
                bullet = new BulletType(){{
                    maxRange = 120;
                }};
            }});
        }};
        Seq<Item> ores = Seq.with(Items.copper, Items.lead, Items.titanium, Items.thorium, Items.beryllium, Items.graphite, Items.tungsten, HIItems.uranium, HIItems.chromium);
        pioneer = new UnitType("pioneer"){{
            speed = 0.3f;
            hitSize = 40f;
            health = 35000;
            armor = 28;
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
            ammoType = new ItemAmmoType(Items.graphite, 12);
            legSplashDamage = 200;
            legSplashRange = 64;
            hovering = true;
            shadowElevation = 0.95f;
            groundLayer = Layer.legUnit;
            mineTier = 5;
            mineSpeed = 6f;
            mineWalls = true;
            mineItems = ores;
            itemCapacity = 3000;
            buildSpeed = 6f;
            payloadCapacity = (5.5f * 5.5f) * tilePayload;
        }};
        burner = new UnitType("burner"){{
            speed = 0.36f;
            hitSize = 22f;
            rotateSpeed = 2.1f;
            health = 16700;
            armor = 17f;
            mechFrontSway = 1f;
            ammoType = new PowerAmmoType(500);
            mechStepParticles = true;
            stepShake = 0.15f;
            singleTarget = true;
            drownTimeMultiplier = 4f;
            range = 128f;
            weapons.add(new Weapon("scepter-weapon"){{
                mirror = top = false;
                y = 1f;
                x = 16f;
                shootY = 8f;
                reload = 12f;
                recoil = 2f;
                shake = 2f;
                ejectEffect = HIFx.casing(50f);
                shootSound = Sounds.flame;
                inaccuracy = 3f;
                shoot.shots = 3;
                shoot.shotDelay = 4f;
                bullet = new FlameBulletType(Pal.lightPyraFlame, Pal.darkPyraFlame, Color.gray, range + 8f, 12, 72, 22){{
                    damage = 225;
                    collidesAir = true;
                    statusDuration = 60 * 6;
                    ammoMultiplier = 4;
                }
                    @Override
                    public void update(Bullet b) {
                        Seq<Healthc> seq = new Seq<>();
                        float r = flameCone * (1 - b.foutpow());
                        Vars.indexer.allBuildings(b.x, b.y, r, seq::addUnique);
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
        //other-erekir
        draug = new ErekirUnitType("draug"){{
            controller = u -> new DepotMinerAI();
            isEnemy = false;
            allowedInPayloads = false;
            logicControllable = false;
            playerControllable = false;
            hidden = true;
            hideDetails = false;
            hitSize = 14f;
            speed = 1f;
            rotateSpeed = 2.5f;
            health = 1300;
            armor = 5f;
            omniMovement = false;
            rotateMoveFirst = true;
            itemOffsetY = 5f;
            itemCapacity = 50;
            mineTier = 5;
            mineSpeed = 6f;
            mineWalls = true;
            mineItems = Seq.with(Items.beryllium, Items.graphite, Items.tungsten);
            allowLegStep = true;
            legCount = 6;
            legGroupSize = 3;
            legLength = 12f;
            lockLegBase = true;
            legContinuousMove = true;
            legExtension = -3f;
            legBaseOffset = 5f;
            legMaxLength = 1.1f;
            legMinLength = 0.2f;
            legForwardScl = 1f;
            legMoveSpace = 2.5f;
            hovering = true;
            weapons.add(new Weapon(name("draug-weapon")){{
                x = 22f / 4f;
                y = -3f;
                shootX = -3f / 4f;
                shootY = 4.5f / 4f;
                rotate = true;
                rotateSpeed = 35f;
                reload = 35f;
                shootSound = Sounds.laser;
                bullet = new LaserBulletType(){{
                    damage = 45f;
                    sideAngle = 30f;
                    sideWidth = 1f;
                    sideLength = 5.25f * 8;
                    length = 13.75f * 8f;
                    colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
                }};
            }});
            abilities.add(new RegenAbility(){{
                percentAmount = 1f / (90f * 60f) * 100f;
            }});
        }};
        //elite
        desolationLord = new UnitType("desolation-lord"){{
            speed = 0.3f;
            hitSize = 40f;
            health = 85000;
            armor = 36;
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
            ammoType = new PowerAmmoType(4000);
            legSplashDamage = 200;
            legSplashRange = 64;
            hovering = true;
            shadowElevation = 0.95f;
            groundLayer = Layer.legUnit;
            mineTier = 5;
            mineSpeed = 6f;
            mineWalls = true;
            mineItems = ores;
            itemCapacity = 1500;
            buildSpeed = 4f;
            payloadCapacity = (6.5f * 6.5f) * tilePayload;
            weapons.add(new Weapon(name("desolation-lord-weapon")){{
                mirror = false;
                rotate = true;
                layerOffset = 0.1f;
                rotateSpeed = 0.9f;
                shootSound = Sounds.release;
                reload = 180;
                recoil = 5.5f;
                shake = 5;
                x = 0;
                y = -1f;
                minWarmup = 0.9f;
                bullet = new BallistaBulletType(Color.valueOf("feb380")){{
                    hitSound = despawnSound = Sounds.explosionbig;
                    damage = 410;
                    splashDamage = 390;
                    splashDamageRadius = 12 * 8f;
                    buildingDamageMultiplier = 0.8f;
                    hitEffect = despawnEffect = new ExplosionEffect(){{
                        lifetime = 30f;
                        waveStroke = 5f;
                        waveLife = 10f;
                        waveRad = splashDamageRadius;
                        waveColor = Color.valueOf("feb380");
                        smokes = 7;
                        smokeSize = 13;
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
                    speed = 10;
                    trailWidth = 7;
                    trailLength = 12;
                    trailColor = Color.valueOf("feb380");
                    fragBullet = null;
                    fragBullets= 0;
                    healPercent = -1;
                }
                    @Override
                    public void draw(Bullet b){
                        super.draw(b);
                        Draw.color(Color.valueOf("feb380"));
                        Drawf.tri(b.x, b.y, 13f, 12f, b.rotation());
                    }
                };
            }});
        }};
        //boss
        thunder = new UnitType("thunder"){{
            health = 122000f;
            armor = 115f;
            rotateSpeed = 1f;
            speed = 0.5f;
            squareShape = true;
            omniMovement = false;
            rotateMoveFirst = true;
            envDisabled = Env.none;
            hitSize = 72f;
            accel = 0.25f;
            treadRects = new Rect[]{new Rect(-85.0f, -104.0f, 28.0f, 208.0f)};
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
                bullet = new AccelBulletType(200, "missile-large"){{
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
                    lightningDamage = damage / 4f + 10f;
                    lightningLength = 7;
                    lightningLengthRand = 16;
                    splashDamageRadius = 36f;
                    splashDamage = damage / 2f;
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
                        color(trailColor, Color.white, e.fout() * 0.66f);
                        for(int s : Mathf.signs){
                            DrawFunc.tri(e.x, e.y, 3f, 30f * Mathf.curve(e.fin(), 0, 0.1f) * e.fout(0.9f), e.rotation + 145f * s);
                        }
                    });
                    hitEffect = new MultiEffect(HIFx.square45_6_45, HIFx.hitSparkLarge);
                    despawnEffect = HIFx.lightningHitLarge;
                }
                    @Override
                    public void hitTile(Bullet b, Building build, float x, float y, float initialHealth, boolean direct){
                        super.hitTile(b, build, x, y, initialHealth, direct);
                        if(build.block.armor > 10 || build.block.absorbLasers)b.time(b.lifetime());
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
                    bullet = new BasicBulletType(12f,180f){{
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
