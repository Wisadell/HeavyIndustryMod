package heavyindustry.content;

import heavyindustry.entities.*;
import heavyindustry.entities.bullet.*;
import heavyindustry.entities.effect.*;
import heavyindustry.gen.*;
import heavyindustry.graphics.*;
import heavyindustry.math.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;

import static mindustry.Vars.*;
import static heavyindustry.core.HeavyIndustryMod.*;

/**
 * Some preset bullets. Perhaps it will be used multiple times.
 * @author Wisadell
 * @author Yuria
 */
public class HIBullets {
    public static BulletType
            hitter,ncBlackHole,nuBlackHole,executor,
            ultFireball,basicSkyFrag,annMissile,
            hyperBlast,hyperBlastLinker,
            arc9000frag,arc9000,arc9000hyper,
            //It is not recommended to use it directly.
            collapseFrag,collapse;

    @SuppressWarnings("unchecked")
    public static void load(){
        hitter = new EffectBulletType(15f, 500f, 600f){{
            speed = 0;

            hittable = false;

            scaledSplashDamage = true;
            collidesTiles = collidesGround = collides = collidesAir = true;
            lightningDamage = 200f;
            lightColor = lightningColor = trailColor = hitColor = HIPal.ancient;
            lightning = 5;
            lightningLength = 12;
            lightningLengthRand = 16;
            splashDamageRadius = 60f;
            hitShake = despawnShake = 20f;
            hitSound = despawnSound = Sounds.explosionbig;
            hitEffect = despawnEffect = new MultiEffect(HIFx.square45_8_45, HIFx.hitSparkHuge, HIFx.crossBlast_45);
        }
            @Override
            public void despawned(Bullet b){
                if(despawnHit){
                    hit(b);
                }else{
                    createUnits(b, b.x, b.y);
                }

                if(!fragOnHit){
                    createFrags(b, b.x, b.y);
                }

                despawnEffect.at(b.x, b.y, b.rotation(), lightColor);
                despawnSound.at(b);

                Effect.shake(despawnShake, despawnShake, b);
            }

            @Override
            public void hit(Bullet b, float x, float y){
                hitEffect.at(x, y, b.rotation(), lightColor);
                hitSound.at(x, y, hitSoundPitch, hitSoundVolume);

                Effect.shake(hitShake, hitShake, b);

                if(fragOnHit){
                    createFrags(b, x, y);
                }
                createPuddles(b, x, y);
                createIncend(b, x, y);
                createUnits(b, x, y);

                if(suppressionRange > 0){
                    //bullets are pooled, require separate Vec2 instance
                    Damage.applySuppression(b.team, b.x, b.y, suppressionRange, suppressionDuration, 0f, suppressionEffectChance, new Vec2(b.x, b.y));
                }

                createSplashDamage(b, x, y);

                for(int i = 0; i < lightning; i++){
                    Lightning.create(b, lightColor, lightningDamage < 0 ? damage : lightningDamage, b.x, b.y, b.rotation() + Mathf.range(lightningCone / 2) + lightningAngle, lightningLength + Mathf.random(lightningLengthRand));
                }
            }
        };
        ncBlackHole = new EffectBulletType(120f, 10000f, 3800f){{
            despawnHit = true;
            splashDamageRadius = 240;

            hittable = false;

            lightColor = HIPal.ancient;
            lightningDamage = 2000f;
            lightning = 2;
            lightningLength = 4;
            lightningLengthRand = 8;

            scaledSplashDamage = true;
            collidesAir = collidesGround = collidesTiles = true;
        }
            @Override
            public void draw(Bullet b){
                if(!(b.data instanceof Seq))return;
                Seq<Sized> data = (Seq<Sized>)b.data;

                Draw.color(lightColor, Color.white, b.fin() * 0.7f);
                Draw.alpha(b.fin(Interp.pow3Out) * 1.1f);
                Lines.stroke(2 * b.fout());
                for(Sized s : data){
                    if(s instanceof Building){
                        Fill.square(s.getX(), s.getY(), s.hitSize() / 2);
                    }else{
                        Lines.spikes(s.getX(), s.getY(), s.hitSize() * (0.5f + b.fout() * 2f), s.hitSize() / 2f * b.fslope() + 12 * b.fin(), 4, 45);
                    }
                }

                Drawf.light(b.x, b.y, b.fdata, lightColor, 0.3f + b.fin() * 0.8f);
            }

            public void hitT(Sized target, Entityc o, Team team, float x, float y){
                for(int i = 0; i < lightning; i++){
                    Lightning.create(team, lightColor, lightningDamage, x, y, Mathf.random(360), lightningLength + Mathf.random(lightningLengthRand));
                }

                if(target instanceof Unit){
                    if(((Unit)target).health > 1000) HIBullets.hitter.create(o, team, x, y, 0);
                }
            }

            @Override
            public void update(Bullet b){
                super.update(b);

                if(!(b.data instanceof Seq))return;
                Seq<Sized> data = (Seq<Sized>)b.data;
                data.remove(d -> !((Healthc)d).isValid());
            }

            @Override
            public void despawned(Bullet b){
                super.despawned(b);

                float rad = 33;

                Vec2 v = new Vec2().set(b);

                for(int i = 0; i < 5; i++){
                    Time.run(i * 0.35f + Mathf.random(2), () -> {
                        Tmp.v1.rnd(rad / 3).scl(Mathf.random());
                        HIFx.shuttle.at(v.x + Tmp.v1.x, v.y + Tmp.v1.y, Tmp.v1.angle(), lightColor, Mathf.random(rad * 3f, rad * 12f));
                    });
                }

                if(!(b.data instanceof Seq))return;
                Entityc o = b.owner();
                Seq<Sized> data = (Seq<Sized>)b.data;
                for(Sized s : data){
                    float size = Math.min(s.hitSize(), 85);
                    Time.run(Mathf.random(44), () -> {
                        if(Mathf.chance(0.32) || data.size < 8)HIFx.shuttle.at(s.getX(), s.getY(), 45, lightColor, Mathf.random(size * 3f, size * 12f));
                        hitT(s, o, b.team, s.getX(), s.getY());
                    });
                }

                createSplashDamage(b, b.x, b.y);
            }

            @Override
            public void init(Bullet b){
                super.init(b);
                if(!(b.data instanceof Float))return;
                float fdata = (Float)b.data();

                Seq<Sized> data = new Seq<>();

                indexer.eachBlock(null, b.x, b.y, fdata, bu -> bu.team != b.team, data::add);

                Groups.unit.intersect(b.x - fdata / 2, b.y - fdata / 2, fdata, fdata, u -> {
                    if(u.team != b.team)data.add(u);
                });

                b.data = data;

                HIFx.circleOut.at(b.x, b.y, fdata * 1.25f, lightColor);
            }
        };
        nuBlackHole = new EffectBulletType(20f, 10000f, 0f){{
            despawnHit = true;
            splashDamageRadius = 36;

            hittable = false;

            lightColor = hitColor = HIPal.ancient;
            lightningDamage = 2000f;
            lightning = 2;
            lightningLength = 4;
            lightningLengthRand = 8;

            scaledSplashDamage = true;
            collidesAir = collidesGround = collidesTiles = true;
        }
            @Override
            public void draw(Bullet b){
                if(!(b.data instanceof Seq))return;
                Seq<Sized> data = (Seq<Sized>)b.data;

                Draw.color(lightColor, Color.white, b.fin() * 0.7f);
                Draw.alpha(b.fin(Interp.pow3Out) * 1.1f);
                Lines.stroke(2 * b.fout());
                for(Sized s : data){
                    if(s instanceof Building){
                        Fill.square(s.getX(), s.getY(), s.hitSize() / 2);
                    }else{
                        Lines.spikes(s.getX(), s.getY(), s.hitSize() * (0.5f + b.fout() * 2f), s.hitSize() / 2f * b.fslope() + 12 * b.fin(), 4, 45);
                    }
                }

                Drawf.light(b.x, b.y, b.fdata, lightColor, 0.3f + b.fin() * 0.8f);
            }

            public void hitT(Entityc o, Team team, float x, float y){
                for(int i = 0; i < lightning; i++){
                    Lightning.create(team, lightColor, lightningDamage, x, y, Mathf.random(360), lightningLength + Mathf.random(lightningLengthRand));
                }

                HIBullets.hitter.create(o, team, x, y, 0, 3000, 1, 1, null);
            }

            @Override
            public void update(Bullet b){
                super.update(b);

                if(!(b.data instanceof Seq) || b.timer(0, 5))return;
                Seq<Sized> data = (Seq<Sized>)b.data;
                data.remove(d -> !((Healthc)d).isValid());
            }

            @Override
            public void despawned(Bullet b){
                super.despawned(b);

                if(!(b.data instanceof Seq))return;
                Entityc o = b.owner();
                Seq<Sized> data = (Seq<Sized>)b.data;
                for(Sized s : data){
                    float size = Math.min(s.hitSize(), 75);
                    if(Mathf.chance(0.32) || data.size < 8){
                        float sd = Mathf.random(size * 3f, size * 12f);

                        HIFx.shuttleDark.at(s.getX() + Mathf.range(size), s.getY() + Mathf.range(size), 45, lightColor, sd);
                    }
                    hitT(o, b.team, s.getX(), s.getY());
                }

                createSplashDamage(b, b.x, b.y);
            }

            @Override
            public void init(Bullet b){
                super.init(b);
                b.fdata = splashDamageRadius;

                Seq<Sized> data = new Seq<>();

                indexer.eachBlock(null, b.x, b.y, b.fdata, bu -> bu.team != b.team, data::add);

                Groups.unit.intersect(b.x - b.fdata / 2, b.y - b.fdata / 2, b.fdata, b.fdata, u -> {
                    if(u.team != b.team)data.add(u);
                });

                b.data = data;
            }
        };
        ultFireball = new FireBulletType(1f, 10){{
            colorFrom = colorMid = Pal.techBlue;
            lifetime = 12f;
            radius = 4f;
            trailEffect = HIFx.ultFireBurn;
        }
            @Override
            public void draw(Bullet b){
                Draw.color(colorFrom, colorMid, colorTo, b.fin());
                Fill.square(b.x, b.y, radius * b.fout(), 45);
                Draw.reset();
            }

            @Override
            public void update(Bullet b){
                if(Mathf.chanceDelta(fireTrailChance)){
                    UltFire.create(b.tileOn());
                }

                if(Mathf.chanceDelta(fireEffectChance)){
                    trailEffect.at(b.x, b.y);
                }

                if(Mathf.chanceDelta(fireEffectChance2)){
                    trailEffect2.at(b.x, b.y);
                }
            }
        };
        executor = new TrailFadeBulletType(28f, 1800f){{
            lifetime = 40f;
            trailLength = 90;
            trailWidth = 3.6F;
            tracers = 2;
            tracerFadeOffset = 20;
            keepVelocity = true;
            tracerSpacing = 10f;
            tracerUpdateSpacing *= 1.25f;
            removeAfterPierce = false;
            hitColor = backColor = lightColor = lightningColor = HIPal.ancient;
            trailColor = HIPal.ancientLightMid;
            frontColor = HIPal.ancientLight;
            width = 18f;
            height = 60f;
            homingPower = 0.01f;
            homingRange = 300f;
            homingDelay = 5f;
            hitSound = Sounds.plasmaboom;
            despawnShake = hitShake = 18f;
            statusDuration = 1200f;
            pierce = pierceArmor = pierceBuilding = true;
            lightning = 3;
            lightningLength = 6;
            lightningLengthRand = 18;
            lightningDamage = 400;
            smokeEffect = EffectWrapper.wrap(HIFx.hitSparkHuge, hitColor);
            shootEffect = HIFx.instShoot(backColor, frontColor);
            despawnEffect = HIFx.lightningHitLarge;
            hitEffect = new MultiEffect(HIFx.hitSpark(backColor, 75f, 24, 90f, 2f, 12f), HIFx.square45_6_45, HIFx.lineCircleOut(backColor, 18f, 20, 2), HIFx.sharpBlast(backColor, frontColor, 120f, 40f));
        }
            @Override
            public void createFrags(Bullet b, float x, float y){
                super.createFrags(b, x, y);
                HIBullets.nuBlackHole.create(b, x, y, 0);
            }
        };
        basicSkyFrag = new BasicBulletType(3.8f, 50){{
            speed = 6f;
            trailLength = 12;
            trailWidth = 2f;
            lifetime = 60;
            despawnEffect = HIFx.square45_4_45;
            knockback = 4f;
            width = 15f;
            height = 37f;
            lightningDamage = damage * 0.65f;
            backColor = lightColor = lightningColor = trailColor = hitColor = frontColor = Pal.techBlue;
            lightning = 2;
            lightningLength = lightningLengthRand = 3;
            smokeEffect = Fx.shootBigSmoke2;
            trailChance = 0.2f;
            trailEffect = HIFx.skyTrail;
            drag = 0.015f;
            hitShake = 2f;
            hitSound = Sounds.explosion;
            hitEffect = new Effect(45f, e -> {
                Fx.rand.setSeed(e.id);
                Draw.color(lightColor, e.fin());
                Lines.stroke(1.75f * e.fout());
                Lines.spikes(e.x, e.y, Fx.rand.random(14, 28) * e.finpow(), Fx.rand.random(1, 5) * e.fout() + Fx.rand.random(5, 8) * e.fin(HIInterp.parabola4Reversed), 4, 45);
                Lines.square(e.x, e.y, Fx.rand.random(4, 14) * e.fin(Interp.pow3Out), 45);
            });
        }
            @Override
            public void hit(Bullet b){
                super.hit(b);
                UltFire.createChance(b, 12, 0.0075f);
            }
        };
        annMissile = new BasicBulletType(5.6f, 80f, name("strike")){{
            trailColor = lightningColor = backColor = lightColor = frontColor = Pal.techBlue;
            lightning = 3;
            lightningCone = 360;
            lightningLengthRand = lightningLength = 9;
            splashDamageRadius = 60;
            splashDamage = lightningDamage = damage * 0.7f;
            range = 320f;
            scaleLife = true;
            width = 12f;
            height = 30f;
            trailLength = 15;
            drawSize = 250f;
            trailParam = 1.4f;
            trailChance = 0.35f;
            lifetime = 50f;
            homingDelay = 10f;
            homingPower = 0.05f;
            homingRange = 150f;
            hitEffect = HIFx.lightningHitLarge(lightColor);
            shootEffect = HIFx.hugeSmokeGray;
            smokeEffect = new Effect(45f, e -> {
                Draw.color(lightColor, Color.white, e.fout() * 0.7f);
                Angles.randLenVectors(e.id, 8, 5 + 55 * e.fin(), e.rotation, 45, (x, y) -> Fill.circle(e.x + x, e.y + y, e.fout() * 3f));
            });
            despawnEffect = new Effect(32f, e -> {
                Draw.color(Color.gray);
                Angles.randLenVectors(e.id + 1, 8, 2.0F + 30.0F * e.finpow(), (x, y) -> Fill.circle(e.x + x, e.y + y, e.fout() * 4.0F + 0.5F));
                Draw.color(lightColor, Color.white, e.fin());
                Lines.stroke(e.fout() * 2);
                Fill.circle(e.x, e.y, e.fout() * e.fout() * 13);
                Angles.randLenVectors(e.id, 4, 7 + 40 * e.fin(), (x, y) -> Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 8 + 3));
            });
        }};
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
        hyperBlastLinker = new LightningLinkerBulletType(5f, 220f){{
            effectLightningChance = 0.15f;
            backColor = trailColor = lightColor = lightningColor = hitColor = Pal.techBlue;
            size = 8f;
            frontColor = Pal.techBlue.cpy().lerp(Color.white, 0.25f);
            range = 200f;

            trailWidth = 8f;
            trailLength = 20;

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
        arc9000frag = new FlakBulletType(3.75f, 200){{
            trailColor = lightColor = lightningColor = backColor = frontColor = Pal.techBlue;

            trailLength = 14;
            trailWidth = 2.7f;
            trailRotation = true;
            trailInterval = 3;

            trailEffect = HIFx.polyTrail(backColor, frontColor, 4.65f, 22f);
            trailChance = 0f;
            despawnEffect = hitEffect = HIFx.techBlueExplosion;
            knockback = 12f;
            lifetime = 90f;
            width = 17f;
            height = 42f;
            hittable = false;
            collidesTiles = false;
            splashDamageRadius = 60f;
            splashDamage = damage * 0.6f;
            lightning = 3;
            lightningLength = 8;
            smokeEffect = Fx.shootBigSmoke2;
            hitShake = 8f;
            hitSound = Sounds.plasmaboom;
            status = StatusEffects.shocked;
        }};
        arc9000 = new LightningLinkerBulletType(2.75f, 200){{
            trailWidth = 4.5f;
            trailLength = 66;

            chargeEffect = new MultiEffect(HIFx.techBlueCharge, HIFx.techBlueChargeBegin);

            spreadEffect = slopeEffect = Fx.none;
            trailEffect = HIFx.hitSparkHuge;
            trailInterval = 5;

            backColor = trailColor = hitColor = lightColor = lightningColor = frontColor = Pal.techBlue;
            randomGenerateRange = 340f;
            randomLightningNum = 3;
            linkRange = 280f;
            range = 800f;

            drawSize = 500f;

            drag = 0.0035f;
            fragLifeMin = 0.3f;
            fragLifeMax = 1f;
            fragVelocityMin = 0.3f;
            fragVelocityMax = 1.25f;
            fragBullets = 14;
            intervalBullets = 2;
            intervalBullet = fragBullet = arc9000frag;
            hitSound = Sounds.explosionbig;
            splashDamageRadius = 120f;
            splashDamage = 1000;
            lightningDamage = 375f;

            hittable = false;

            collidesTiles = true;
            pierce = false;
            collides = false;
            ammoMultiplier = 1f;
            lifetime = 300;
            despawnEffect = HIFx.circleOut(hitColor, splashDamageRadius * 1.5f);
            hitEffect = HIFx.largeTechBlueHit;
            shootEffect = HIFx.techBlueShootBig;
            smokeEffect = HIFx.techBlueSmokeBig;
            hitSpacing = 3;
        }
            @Override
            public void update(Bullet b){
                super.update(b);

                if(b.timer(1, 6))for(int j = 0; j < 2; j++){
                    Drawn.randFadeLightningEffect(b.x, b.y, Mathf.random(360), Mathf.random(7, 12), backColor, Mathf.chance(0.5));
                }
            }

            @Override
            public void draw(Bullet b){
                Draw.color(backColor);
                Drawn.surround(b.id, b.x, b.y, size * 1.45f, 14, 7,11, (b.fin(HIInterp.parabola4Reversed) + 1f) / 2 * b.fout(0.1f));

                drawTrail(b);

                Draw.color(backColor);
                Fill.circle(b.x, b.y, size);

                Draw.z(HIFx.EFFECT_MASK);
                Draw.color(frontColor);
                Fill.circle(b.x, b.y, size * 0.62f);
                Draw.z(HIFx.EFFECT_BOTTOM);
                Draw.color(frontColor);
                Fill.circle(b.x, b.y, size * 0.66f);
                Draw.z(Layer.bullet);

                Drawf.light(b.x, b.y, size * 1.85f, backColor, 0.7f);
            }
        };
        arc9000hyper = new AccelBulletType(10f, 1000f){{
            drawSize = 1200f;
            width = height = shrinkX = shrinkY = 0;
            collides = false;
            despawnHit = false;
            collidesAir = collidesGround = collidesTiles = true;
            splashDamage = 4000f;

            velocityBegin = 6f;
            velocityIncrease = -5.9f;

            accelerateEnd = 0.75f;
            accelerateBegin = 0.1f;

            accelInterp = Interp.pow2;
            trailInterp = Interp.pow10Out;

            despawnSound = Sounds.plasmaboom;
            hitSound = Sounds.explosionbig;
            hitShake = 60;
            despawnShake = 100;
            lightning = 12;
            lightningDamage = 2000f;
            lightningLength = 50;
            lightningLengthRand = 80;

            fragBullets = 1;
            fragBullet = arc9000;
            fragVelocityMin = 0.4f;
            fragVelocityMax = 0.6f;
            fragLifeMin = 0.5f;
            fragLifeMax = 0.7f;

            trailWidth = 12F;
            trailLength = 120;
            ammoMultiplier = 1;

            hittable = false;

            scaleLife = true;
            splashDamageRadius = 400f;
            hitColor = lightColor = lightningColor = trailColor = Pal.techBlue;
            Effect effect = HIFx.crossBlast(hitColor, 420f, 45);
            effect.lifetime += 180;
            despawnEffect = HIFx.circleOut(hitColor, splashDamageRadius);
            hitEffect = new MultiEffect(HIFx.blast(hitColor, 200f), new Effect(180F, 600f, e -> {
                float rad = 120f;

                float f = (e.fin(Interp.pow10Out) + 8) / 9 * Mathf.curve(Interp.slowFast.apply(e.fout(0.75f)), 0f, 0.85f);

                Draw.alpha(0.9f * e.foutpowdown());
                Draw.color(Color.white, e.color, e.fin() + 0.6f);
                Fill.circle(e.x, e.y, rad * f);

                e.scaled(45f, i -> {
                    Lines.stroke(7f * i.fout());
                    Lines.circle(i.x, i.y, rad * 3f * i.finpowdown());
                    Lines.circle(i.x, i.y, rad * 2f * i.finpowdown());
                });

                Draw.color(Color.white);
                Fill.circle(e.x, e.y, rad * f * 0.75f);

                Drawf.light(e.x, e.y, rad * f * 2f, Draw.getColor(), 0.7f);
            }).layer(Layer.effect + 0.001f), effect, new Effect(260, 460f, e -> {
                Draw.blend(Blending.additive);
                Draw.z(Layer.flyingUnit - 0.8f);
                float radius = e.fin(Interp.pow3Out) * 230;
                Fill.light(e.x, e.y, Lines.circleVertices(radius), radius, Color.clear, Tmp.c1.set(Pal.techBlue).a(e.fout(Interp.pow10Out)));
                Draw.blend();
            }));
        }
            @Override
            public void draw(Bullet b){
                super.draw(b);

                Draw.color(Pal.techBlue, Color.white, b.fout() * 0.25f);

                float extend = Mathf.curve(b.fin(Interp.pow10Out), 0.075f, 1f);

                float chargeCircleFrontRad = 20;
                float width = chargeCircleFrontRad * 1.2f;
                Fill.circle(b.x, b.y, width * (b.fout() + 4) / 3.5f);

                float rotAngle = b.fdata;

                for(int i : Mathf.signs){
                    Drawn.tri(b.x, b.y, width * b.foutpowdown(), 200 + 570 * extend, rotAngle + 90 * i - 45);
                }

                for(int i : Mathf.signs){
                    Drawn.tri(b.x, b.y, width * b.foutpowdown(), 200 + 570 * extend, rotAngle + 90 * i + 45);
                }

                float cameraFin = (1 + 2 * Drawn.cameraDstScl(b.x, b.y, mobile ? 200 : 320)) / 3f;
                float triWidth = b.fout() * chargeCircleFrontRad * cameraFin;

                for(int i : Mathf.signs){
                    Fill.tri(b.x, b.y + triWidth, b.x, b.y - triWidth, b.x + i * cameraFin * chargeCircleFrontRad * (23 + Mathf.absin(10f, 0.75f)) * (b.fout() * 1.25f + 1f), b.y);
                }

                float rad = splashDamageRadius * b.fin(Interp.pow5Out) * Interp.circleOut.apply(b.fout(0.15f));
                Lines.stroke(8f * b.fin(Interp.pow2Out));
                Lines.circle(b.x, b.y, rad);

                Draw.color(Color.white);
                Fill.circle(b.x, b.y, width * (b.fout() + 4) / 5.5f);

                Drawf.light(b.x, b.y, rad, hitColor, 0.5f);
            }

            @Override
            public void init(Bullet b){
                super.init(b);
                b.fdata = Mathf.randomSeed(b.id, 90);
            }

            @Override
            public void update(Bullet b){
                super.update(b);
                b.fdata += b.vel.len() / 3f;
            }

            @Override
            public void despawned(Bullet b){
                super.despawned(b);

                Angles.randLenVectors(b.id, 8, splashDamageRadius / 1.25f, ((x, y) -> {
                    float nowX = b.x + x;
                    float nowY = b.y + y;

                    Vec2 vec2 = new Vec2(nowX, nowY);
                    Team team = b.team;
                    float mul = b.damageMultiplier();
                    Time.run(Mathf.random(6f, 24f) + Mathf.sqrt(x * x + y * y) / splashDamageRadius * 3f, () -> {
                        if(Mathf.chanceDelta(0.4f))hitSound.at(vec2.x, vec2.y, hitSoundPitch, hitSoundVolume);
                        despawnSound.at(vec2);
                        Effect.shake(hitShake, hitShake, vec2);

                        for(int i = 0; i < lightning / 2; i++){
                            Lightning.create(team, lightningColor, lightningDamage, vec2.x, vec2.y, Mathf.random(360f), lightningLength + Mathf.random(lightningLengthRand));
                        }

                        hitEffect.at(vec2.x, vec2.y, 0, hitColor);
                        hitSound.at(vec2.x, vec2.y, hitSoundPitch, hitSoundVolume);

                        if(fragBullet != null){
                            for(int i = 0; i < fragBullets; i++){
                                fragBullet.create(team.cores().firstOpt(), team, vec2.x, vec2.y, Mathf.random(360), Mathf.random(fragVelocityMin, fragVelocityMax), Mathf.random(fragLifeMin, fragLifeMax));
                            }
                        }

                        if(splashDamageRadius > 0 && !b.absorbed){
                            Damage.damage(team, vec2.x, vec2.y, splashDamageRadius, splashDamage * mul, collidesAir, collidesGround);

                            if(status != StatusEffects.none){
                                Damage.status(team, vec2.x, vec2.y, splashDamageRadius, status, statusDuration, collidesAir, collidesGround);
                            }
                        }
                    });
                }));
            }
        };
        collapseFrag = new LightningLinkerBulletType(){{
            effectLightningChance = 0.15f;
            damage = 200;
            backColor = trailColor = lightColor = lightningColor = hitColor = HIPal.thurmixRed;
            size = 10f;
            frontColor = HIPal.thurmixRedLight;
            range = 600f;
            spreadEffect = Fx.none;

            trailWidth = 8f;
            trailLength = 20;

            speed = 6f;

            linkRange = 280f;

            maxHit = 12;
            drag = 0.0065f;
            hitSound = Sounds.explosionbig;
            splashDamageRadius = 60f;
            splashDamage = lightningDamage = damage / 3f;
            lifetime = 130f;
            despawnEffect = HIFx.lightningHitLarge(hitColor);
            hitEffect = HIFx.sharpBlast(hitColor, frontColor, 35, splashDamageRadius * 1.25f);
            shootEffect = HIFx.hitSpark(backColor, 45f, 12, 60, 3, 8);
            smokeEffect = HIFx.hugeSmoke;
        }};
        collapse = new EffectBulletType(480f){{
            hittable = false;
            collides = false;
            collidesTiles = collidesAir = collidesGround = true;
            speed = 0.1f;

            despawnHit = true;
            keepVelocity = false;

            splashDamageRadius = 800f;
            splashDamage = 800f;

            lightningDamage = 200f;
            lightning = 36;
            lightningLength = 60;
            lightningLengthRand = 60;

            hitShake = despawnShake = 40f;
            drawSize = 800f;
            hitColor = lightColor = trailColor = lightningColor = HIPal.thurmixRed;

            fragBullets = 22;
            fragBullet = collapseFrag;
            hitSound = HISounds.hugeBlast;
            hitSoundVolume = 4f;

            fragLifeMax = 1.1f;
            fragLifeMin = 0.7f;
            fragVelocityMax = 0.6f;
            fragVelocityMin = 0.2f;

            status = StatusEffects.shocked;

            shootEffect = HIFx.lightningHitLarge(hitColor);

            hitEffect = HIFx.hitSpark(hitColor, 240f, 220, 900, 8, 27);
            despawnEffect = HIFx.collapserBulletExplode;
        }
            @Override
            public void despawned(Bullet b){
                super.despawned(b);

                Vec2 vec = new Vec2().set(b);

                float damageMulti = b.damageMultiplier();
                Team team = b.team;
                for(int i = 0; i < splashDamageRadius / (tilesize * 3.5f); i++){
                    int finalI = i;
                    Time.run(i * despawnEffect.lifetime / (splashDamageRadius / (tilesize * 2)), () -> Damage.damage(team, vec.x, vec.y, tilesize * (finalI + 6), splashDamage * damageMulti, true));
                }

                float rad = 120;
                float spacing = 2.5f;

                for(int k = 0; k < (despawnEffect.lifetime - HIFx.chainLightningFadeReversed.lifetime) / spacing; k++){
                    Time.run(k * spacing, () -> {
                        for(int j : Mathf.signs){
                            Vec2 v = Tmp.v6.rnd(rad * 2 + Mathf.random(rad * 4)).add(vec);
                            (j > 0 ? HIFx.chainLightningFade : HIFx.chainLightningFadeReversed).at(v.x, v.y, 12f, hitColor, vec);
                        }
                    });
                }
            }

            @Override
            public void update(Bullet b){
                float rad = 120;

                Effect.shake(8 * b.fin(), 6, b);

                if(b.timer(1, 12)){
                    Seq<Teamc> entites = new Seq<>();

                    Units.nearbyEnemies(b.team, b.x, b.y, rad * 2.5f * (1 + b.fin()) / 2, entites::add);

                    Units.nearbyBuildings(b.x, b.y, rad * 2.5f * (1 + b.fin()) / 2, e -> {
                        if(e.team != b.team)entites.add(e);
                    });

                    entites.shuffle();
                    entites.truncate(15);

                    for(Teamc e : entites){
                        PosLightning.create(b, b.team, b, e, lightningColor, false, lightningDamage, 5 + Mathf.random(5), PosLightning.WIDTH, 1, p -> HIFx.lightningHitSmall.at(p.getX(), p.getY(), 0, lightningColor));
                    }
                }

                if(b.lifetime() - b.time() > HIFx.chainLightningFadeReversed.lifetime)for(int i = 0; i < 2; i++){
                    if(Mathf.chanceDelta(0.2 * Mathf.curve(b.fin(), 0, 0.8f))){
                        for(int j : Mathf.signs){
                            Sounds.spark.at(b.x, b.y, 1f, 0.3f);
                            Vec2 v = Tmp.v6.rnd(rad / 2 + Mathf.random(rad * 2) * (1 + Mathf.curve(b.fin(), 0, 0.9f)) / 1.5f).add(b);
                            (j > 0 ? HIFx.chainLightningFade : HIFx.chainLightningFadeReversed).at(v.x, v.y, 12f, hitColor, b);
                        }
                    }
                }

                if(b.fin() > 0.05f && Mathf.chanceDelta(b.fin() * 0.3f + 0.02f)){
                    HISounds.blaster.at(b.x, b.y, 1f, 0.3f);
                    Tmp.v1.rnd(rad / 4 * b.fin());
                    HIFx.shuttleLerp.at(b.x + Tmp.v1.x, b.y + Tmp.v1.y, Tmp.v1.angle(), hitColor, Mathf.random(rad, rad * 3f) * (Mathf.curve(b.fin(Interp.pow2In), 0, 0.7f) + 2) / 3);
                }
            }

            @Override
            public void draw(Bullet b){
                float fin = Mathf.curve(b.fin(), 0, 0.02f);
                float f = fin * Mathf.curve(b.fout(), 0f, 0.1f);
                float rad = 120;

                float z = Draw.z();

                float circleF = (b.fout(Interp.pow2In) + 1) / 2;

                Draw.color(hitColor);
                Lines.stroke(rad / 20 * b.fin());
                Lines.circle(b.x, b.y, rad * b.fout(Interp.pow3In));
                Lines.circle(b.x, b.y, b.fin(Interp.circleOut) * rad * 3f * Mathf.curve(b.fout(), 0, 0.05f));

                Rand rand = HIFx.rand0;
                rand.setSeed(b.id);
                for(int i = 0; i < (int)(rad / 3); i++){
                    Tmp.v1.trns(rand.random(360f) + rand.range(1f) * rad / 5 * b.fin(Interp.pow2Out), rad / 2.05f * circleF + rand.random(rad * (1 + b.fin(Interp.circleOut)) / 1.8f));
                    float angle = Tmp.v1.angle();
                    Drawn.tri(b.x + Tmp.v1.x, b.y + Tmp.v1.y, (b.fin() + 1) / 2 * 28 + rand.random(0, 8), rad / 16 * (b.fin(Interp.exp5In) + 0.25f), angle);
                    Drawn.tri(b.x + Tmp.v1.x, b.y + Tmp.v1.y, (b.fin() + 1) / 2 * 12 + rand.random(0, 2), rad / 12 * (b.fin(Interp.exp5In) + 0.5f) / 1.2f, angle - 180);
                }

                Angles.randLenVectors(b.id + 1, (int)(rad / 3), rad / 4 * circleF, rad * (1 + b.fin(Interp.pow3Out)) / 3, (x, y) -> {
                    float angle = Mathf.angle(x, y);
                    Drawn.tri(b.x + x, b.y + y, rad / 8 * (1 + b.fout()) / 2.2f, (b.fout() * 3 + 1) / 3 * 25 + rand.random(4, 12) * (b.fout(Interp.circleOut) + 1) / 2, angle);
                    Drawn.tri(b.x + x, b.y + y, rad / 8 * (1 + b.fout()) / 2.2f, (b.fout() * 3 + 1) / 3 * 9 + rand.random(0, 2) * (b.fin() + 1) / 2, angle - 180);
                });

                Drawf.light(b.x, b.y, rad * f * (b.fin() + 1) * 2, Draw.getColor(), 0.7f);

                Draw.z(Layer.effect + 0.001f);
                Draw.color(hitColor);
                Fill.circle(b.x, b.y, rad * fin * circleF / 2f);
                Draw.color(HIPal.thurmixRedDark);
                Fill.circle(b.x, b.y, rad * fin * circleF * 0.75f / 2f);
                Draw.z(Layer.bullet - 0.1f);
                Draw.color(HIPal.thurmixRedDark);
                Fill.circle(b.x, b.y, rad * fin * circleF * 0.8f / 2f);
                Draw.z(z);
            }
        };
    }
}
