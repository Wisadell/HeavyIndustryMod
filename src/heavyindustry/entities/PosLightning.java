package heavyindustry.entities;

import heavyindustry.content.HIFx;
import heavyindustry.struct.Vec2Seq;
import heavyindustry.entities.bullet.EffectBulletType;
import arc.func.Cons;
import arc.graphics.Color;
import arc.math.Mathf;
import arc.math.Rand;
import arc.math.geom.Geometry;
import arc.math.geom.Position;
import arc.math.geom.Rect;
import arc.math.geom.Vec2;
import arc.struct.FloatSeq;
import arc.struct.Seq;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.core.World;
import mindustry.entities.Lightning;
import mindustry.entities.Units;
import mindustry.entities.bullet.BulletType;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.gen.Bullet;
import mindustry.gen.Entityc;
import mindustry.gen.Healthc;

public class PosLightning {
    public static final BulletType hitter = new EffectBulletType(5f){{
        absorbable = true;
        collides = collidesAir = collidesGround = collidesTiles = true;
        status = StatusEffects.shocked;
        statusDuration = 10f;
        hittable = false;
    }};

    public static final Cons<Position> none = p -> {};
    public static final float lifetime = Fx.chainLightning.lifetime;
    public static final float WIDTH = 2.5f;
    public static final float RANGE_RAND = 5f;
    public static final float ROT_DST = Vars.tilesize * 0.6f;
    public static float trueHitChance = 1;

    public static void setHitChance(float f){
        trueHitChance = f;
    }

    public static void setHitChanceDef(){
        trueHitChance = 1;
    }

    private static float getBoltRandomRange() {return Mathf.random(1f, 7f); }

    private static Building furthest;
    private static final Rect rect = new Rect();
    private static final Rand rand = new Rand();
    private static final FloatSeq floatSeq = new FloatSeq();
    private static final Vec2 tmp1 = new Vec2(), tmp2 = new Vec2(), tmp3 = new Vec2();
    public static void createRange(Bullet owner, float range, int maxHit, Color color, boolean createSubLightning, float width, int lightningNum, Cons<Position> hitPointMovement) {
        createRange(owner, owner, owner.team, range, maxHit, color, createSubLightning, 0, 0, width, lightningNum, hitPointMovement);
    }

    public static void createRange(Bullet owner, boolean hitAir, boolean hitGround, Position from, Team team, float range, int maxHit, Color color, boolean createSubLightning, float damage, int subLightningLength, float width, int lightningNum, Cons<Position> hitPointMovement) {
        Seq<Healthc> entities = new Seq<>();
        whetherAdd(entities, team, rect.setSize(range * 2f).setCenter(from.getX(), from.getY()), maxHit, hitGround, hitAir);
        for (Healthc p : entities)create(owner, team, from, p, color, createSubLightning, damage, subLightningLength, width, lightningNum, hitPointMovement);
    }


    public static void createRange(Bullet owner, Position from, Team team, float range, int maxHit, Color color, boolean createSubLightning, float damage, int subLightningLength, float width, int lightningNum, Cons<Position> hitPointMovement) {
        createRange(owner, owner == null || owner.type.collidesAir, owner == null || owner.type.collidesGround, from, team, range, maxHit, color, createSubLightning, damage, subLightningLength, width, lightningNum, hitPointMovement);
    }

    public static void createLength(Bullet owner, Team team, Position from, float length, float angle, Color color, boolean createSubLightning, float damage, int subLightningLength, float width, int lightningNum, Cons<Position> hitPointMovement){
        create(owner, team, from, tmp2.trns(angle, length).add(from), color, createSubLightning, damage, subLightningLength, width, lightningNum, hitPointMovement);
    }

    public static void create(Entityc owner, Team team, Position from, Position target, Color color, boolean createSubLightning, float damage, int subLightningLength, float lightningWidth, int lightningNum, Cons<Position> hitPointMovement) {
        if(!Mathf.chance(trueHitChance))return;
        Position sureTarget = findInterceptedPoint(from, target, team);
        hitPointMovement.get(sureTarget);

        if(createSubLightning){
            if(owner instanceof Bullet b){
                for(int i = 0; i < b.type.lightning; i++) Lightning.create(b, color, b.type.lightningDamage < 0.0F ? b.damage : b.type.lightningDamage, sureTarget.getX(), sureTarget.getY(), b.rotation() + Mathf.range(b.type.lightningCone / 2.0F) + b.type.lightningAngle, b.type.lightningLength + Mathf.random(b.type.lightningLengthRand));
            }
            else for(int i = 0; i < 3; i++)Lightning.create(team, color, damage <= 0 ? 1f : damage, sureTarget.getX(), sureTarget.getY(), Mathf.random(360f), subLightningLength);
        }

        float realDamage = damage;

        if(realDamage <= 0){
            if(owner instanceof Bullet b){
                realDamage = b.damage > 0 ? b.damage : 1;
            }else realDamage = 1;
        }

        hitter.create(owner, team, sureTarget.getX(), sureTarget.getY(), 1).damage(realDamage);

        createEffect(from, sureTarget, color, lightningNum, lightningWidth);
    }

    public static void createRandom(Bullet owner, Team team, Position from, float rand, Color color, boolean createSubLightning, float damage, int subLightningLength, float width, int lightningNum, Cons<Position> hitPointMovement){
        create(owner, team, from, tmp2.rnd(rand).scl(Mathf.random(1f)).add(from), color, createSubLightning, damage, subLightningLength, width, lightningNum, hitPointMovement);
    }

    public static void createRandom(Team team, Position from, float rand, Color color, boolean createSubLightning, float damage, int subLightningLength, float width, int lightningNum, Cons<Position> hitPointMovement){
        createRandom(null, team, from, rand, color, createSubLightning, damage, subLightningLength, width, lightningNum, hitPointMovement);
    }

    public static void createRandomRange(Team team, Position from, float rand, Color color, boolean createSubLightning, float damage, int subLightningLength, float width, int lightningNum, int generateNum, Cons<Position> hitPointMovement){
        createRandomRange(null, team, from, rand, color, createSubLightning, damage, subLightningLength, width, lightningNum, generateNum, hitPointMovement);
    }

    public static void createRandomRange(Bullet owner, float rand, Color color, boolean createSubLightning, float damage, float width, int lightningNum, int generateNum, Cons<Position> hitPointMovement){
        createRandomRange(owner, owner.team, owner, rand, color, createSubLightning, damage, owner.type.lightningLength + Mathf.random(owner.type.lightningLengthRand), width, lightningNum, generateNum, hitPointMovement);
    }

    public static void createRandomRange(Bullet owner, Team team, Position from, float rand, Color color, boolean createSubLightning, float damage, int subLightningLength, float width, int lightningNum, int generateNum, Cons<Position> hitPointMovement){
        for (int i = 0; i < generateNum; i++) {
            createRandom(owner, team, from, rand, color, createSubLightning, damage, subLightningLength, width, lightningNum, hitPointMovement);
        }
    }

    public static void createEffect(Position from, float length, float angle, Color color, int lightningNum, float width){
        if(Vars.headless)return;
        createEffect(from, tmp2.trns(angle, length).add(from), color, lightningNum, width);
    }

    public static void createEffect(Position from, Position to, Color color, int lightningNum, float width){
        if(Vars.headless)return;

        if(lightningNum < 1){
            Fx.chainLightning.at(from.getX(), from.getY(), 0, color, new Vec2().set(to));
        }else{
            float dst = from.dst(to);

            for(int i = 0; i < lightningNum; i++){
                float len = getBoltRandomRange();
                float randRange = len * RANGE_RAND;

                floatSeq.clear();
                FloatSeq randomArray = floatSeq;
                for(int num = 0; num < dst / (ROT_DST * len) + 1; num++){
                    randomArray.add(Mathf.range(randRange) / (num * 0.025f + 1));
                }
                createBoltEffect(color, width, computeVectors(randomArray, from, to));
            }
        }
    }

    public static Position findInterceptedPoint(Position from, Position target, Team fromTeam) {
        furthest = null;
        return Geometry.raycast(
                World.toTile(from.getX()),
                World.toTile(from.getY()),
                World.toTile(target.getX()),
                World.toTile(target.getY()),
                (x, y) -> (furthest = Vars.world.build(x, y)) != null && furthest.team() != fromTeam && furthest.block().insulated
        ) && furthest != null ? furthest : target;
    }

    private static void whetherAdd(Seq<Healthc> points, Team team, Rect selectRect, int maxHit, boolean targetGround, boolean targetAir) {
        Units.nearbyEnemies(team, selectRect, unit -> {
            if(unit.checkTarget(targetAir, targetGround))points.add(unit);
        });

        if(targetGround){
            selectRect.getCenter(tmp3);
            Units.nearbyBuildings(tmp3.x, tmp3.y, selectRect.getHeight() / 2, b -> {
                if(b.team != team && b.isValid())points.add(b);
            });
        }

        points.shuffle();
        points.truncate(maxHit);
    }

    public static void createBoltEffect(Color color, float width, Vec2Seq vets) {
        vets.each(((x, y) -> {
            if(Mathf.chance(0.0855))HIFx.lightningSpark.at(x, y, rand.random(2f + width, 4f + width), color);
        }));
        HIFx.posLightning.at((vets.firstTmp().x + vets.peekTmp().x) / 2f, (vets.firstTmp().y + vets.peekTmp().y) / 2f, width, color, vets);
    }

    private static Vec2Seq computeVectors(FloatSeq randomVec, Position from, Position to){
        int param = randomVec.size;
        float angle = from.angleTo(to);

        Vec2Seq lines = new Vec2Seq(param);
        tmp1.trns(angle, from.dst(to) / (param - 1));

        lines.add(from);
        for (int i = 1; i < param - 2; i ++)lines.add(tmp3.trns(angle - 90, randomVec.get(i)).add(tmp1, i).add(from.getX(), from.getY()));
        lines.add(to);

        return lines;
    }
}