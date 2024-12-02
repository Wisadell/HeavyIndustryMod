package heavyindustry.entities.bullet;

import arc.math.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;

public class LightningBulletTypeF extends BulletType {
    public LightningBulletTypeF() {
        damage = 1f;
        speed = 0f;
        lifetime = 1;
        shootEffect = hitEffect = despawnEffect = Fx.none;
        despawnHit = false;
        keepVelocity = false;
        hittable = false;
        status = StatusEffects.shocked;
    }

    @Override
    protected float calculateRange() {
        return (lightningLength + lightningLengthRand / 2f) * 6f;
    }

    @Override
    public float estimateDPS() {
        return super.estimateDPS() * Math.max(lightningLength / 10f, 1);
    }

    @Override
    public void draw(Bullet b) {}

    @Override
    public void init(Bullet b) {
        Lightning.create(b, lightningColor, damage, b.x, b.y, b.rotation(), lightningLength + Mathf.random(lightningLengthRand));
    }
}
