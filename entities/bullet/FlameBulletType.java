package heavyindustry.entities.bullet;

import heavyindustry.content.*;
import arc.graphics.*;
import arc.math.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;

import static mindustry.Vars.*;

public class FlameBulletType extends BulletType {
    public float flameLength, flameCone;
    public int particleNumber;

    public float damageBoost = 3f;

    public FlameBulletType(Color colorBegin, Color colorTo, Color colorEnd, float length,  float cone, int number, float lifetime){
        flameLength = length;
        flameCone = cone;
        particleNumber = number;
        this.lifetime = 0;
        damage = 22;
        speed = 0;
        hitEffect = Fx.none;
        smokeEffect = Fx.none;
        trailEffect = Fx.none;
        despawnEffect = Fx.none;
        pierce = true;
        collidesAir = false;
        absorbable = false;
        hittable = false;
        keepVelocity = false;
        status = StatusEffects.burning;
        statusDuration = 60 * 4;
        buildingDamageMultiplier = 0.4f;
        despawnHit = true;
        shootEffect = HIFx.flameShoot(colorBegin, colorTo, colorEnd, length, cone, number, lifetime);
    }

    @Override
    public void hit(Bullet b) {
        if(absorbable && b.absorbed) return;
        Units.nearbyEnemies(b.team, b.x, b.y, flameLength, unit -> {
            if(Angles.within(b.rotation(), b.angleTo(unit), flameCone) && unit.checkTarget(collidesAir, collidesGround)){
                Fx.hitFlameSmall.at(unit);
                unit.damage(damage * damageBoost);
                unit.apply(status, statusDuration);
            }
        });
        indexer.allBuildings(b.x, b.y, flameLength, other -> {
            if(other.team != b.team && Angles.within(b.rotation(), b.angleTo(other), flameCone)){
                Fx.hitFlameSmall.at(other);
                other.damage(damage * buildingDamageMultiplier * damageBoost);
            }
        });
    }
}
