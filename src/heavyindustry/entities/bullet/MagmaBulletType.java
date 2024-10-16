package heavyindustry.entities.bullet;

import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.graphics.*;
import mindustry.gen.*;
import heavyindustry.entities.*;

public class MagmaBulletType extends BulletType {
    public float radius, shake;

    public float crackRadius = -1;

    public MagmaBulletType(){
        this(1f, 1f);
    }

    public MagmaBulletType(float damage, float radius){
        super(0.001f, damage);
        this.radius = radius;

        hitEffect = Fx.fireballsmoke;
        despawnEffect = shootEffect = smokeEffect = Fx.none;
        lifetime = 16f;
        hitColor = Pal.slagOrange;
        makeFire = true;
        keepVelocity = backMove = false;
        hittable = absorbable = false;
        collides = collidesTiles = false;
        collidesGround = true;
        collidesAir = false;
        puddleLiquid = Liquids.slag;
        puddleAmount = 250f;
        displayAmmoMultiplier = false;
        status = StatusEffects.melting;
    }

    @Override
    public float continuousDamage(){
        return damage / 5f * 60f;
    }

    @Override
    public float estimateDPS(){
        //assume firing duration is about 100 by default, may not be accurate there's no way of knowing in this method.
        //assume it pierces 3 blocks/units
        return damage * 100f / 5f * 3f;
    }

    @Override
    public void init(){
        super.init();

        if(crackRadius < 0) crackRadius = radius * 2f;
    }

    @Override
    public void init(Bullet b){
        super.init(b);

        b.data = new IntSeq();
    }

    @Override
    public void update(Bullet b){
        //damage every 5 ticks
        if(b.timer(1, 5f) && b.data instanceof IntSeq tiles){
            Damage.damage(b.team, b.x, b.y, radius * b.fout(), damage * b.damageMultiplier(), true, collidesAir, collidesGround);
            if(status != StatusEffects.none) Damage.status(b.team, b.x, b.y, radius * b.fout(), status, statusDuration, collidesAir, collidesGround);

            Tmp.r1.setSize(radius * 2f * b.fout()).setCenter(b.x, b.y);
            Units.nearbyEnemies(b.team, Tmp.r1, u -> {
                if(u.within(b, radius * b.fout())){
                    if(puddleLiquid != null) Puddles.deposit(u.tileOn(), puddleLiquid, puddleAmount);
                    if(makeFire) Fires.create(u.tileOn());
                    tiles.add(u.tileOn().pos());
                }
            });

            HIDamage.trueEachBlock(b.x, b.y, radius * b.fout(), build -> {
                if(build.team == b.team) return;
                if(puddleLiquid != null) Puddles.deposit(build.tileOn(), puddleLiquid, puddleAmount);
                if(makeFire) Fires.create(build.tileOn());
            });
        }

        if(shake > 0){
            Effect.shake(shake, shake, b);
        }
    }

    @Override
    public void draw(Bullet b){
        //Nothing to draw
    }
}
