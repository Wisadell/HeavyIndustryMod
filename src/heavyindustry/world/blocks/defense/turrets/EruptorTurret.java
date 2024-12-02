package heavyindustry.world.blocks.defense.turrets;

import arc.graphics.*;
import arc.math.geom.*;
import arc.util.*;
import heavyindustry.content.*;
import heavyindustry.entities.effect.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.blocks.defense.turrets.*;

public class EruptorTurret extends PowerTurret {
    public final int beamTimer = timers++;
    public float beamInterval = 2f;
    public Color beamColor = Pal.slagOrange;
    public LightningEffect beamEffect = HIFx.flameBeam;
    public Effect endEffect = HIFx.eruptorBurn;

    public float shootDuration = 60f;

    public EruptorTurret(String name) {
        super(name);

        targetAir = false;
        shootSound = Sounds.none;
        loopSound = Sounds.beam;
        loopSoundVolume = 2f;
        heatColor = Color.valueOf("f08913");
    }

    public class EruptorTurretBuild extends PowerTurretBuild {
        protected Bullet bullet;
        protected float bulletLife, lengthScl;

        @Override
        public void targetPosition(Posc pos) {
            if (!hasAmmo() || pos == null) return;
            Vec2 offset = Tmp.v1.setZero();

            //when delay is accurate, assume unit has moved by chargeTime already.
            if (accurateDelay && pos instanceof Hitboxc h) {
                offset.set(h.deltaX(), h.deltaY()).scl(shoot.firstShotDelay / Time.delta);
            }

            targetPos.set(Predict.intercept(this, pos, offset.x, offset.y, range / shootDuration));

            if (targetPos.isZero()) {
                targetPos.set(pos);
            }
        }

        @Override
        public boolean shouldConsume() {
            //still consumes power when bullet is around
            return bullet != null || isActive() || isShooting();
        }

        @Override
        public boolean isShooting() {
            return super.isShooting() || bullet != null;
        }

        @Override
        public void updateTile() {
            super.updateTile();

            if (bulletLife > 0 && bullet != null) {
                wasShooting = true;
                curRecoil = 1f;
                heat = 1f;

                Tmp.v1.trns(rotation, lengthScl * range, 0f);
                bullet.set(x + Tmp.v1.x, y + Tmp.v1.y);
                bullet.time(0f);
                bulletLife -= Time.delta / Math.max(efficiency, 0.00001f);
                lengthScl += Time.delta / shootDuration;
                if (timer(beamTimer, beamInterval)) {
                    Tmp.v1.trns(rotation, shootY - curRecoil);
                    beamEffect.at(x + Tmp.v1.x, y + Tmp.v1.y, bullet.x, bullet.y, beamColor);
                    endEffect.at(bullet, rotation);
                }
                if (bulletLife <= 0f) {
                    bullet = null;
                    lengthScl = 0f;
                }
            }
        }

        @Override
        public boolean shouldTurn() {
            return lengthScl < 0.001f;
        }

        @Override
        protected void updateReload() {
            if (bulletLife > 0 && bullet != null) {
                return;
            }

            super.updateReload();
        }

        @Override
        protected void updateCooling() {
            if (bulletLife > 0 && bullet != null) {
                return;
            }

            super.updateCooling();
        }
        @Override
        protected void updateShooting() {
            if (bulletLife > 0 && bullet != null) {
                return;
            }

            super.updateShooting();
        }

        @Override
        protected void handleBullet(Bullet bullet, float offsetX, float offsetY, float angleOffset) {
            if (bullet != null) {
                this.bullet = bullet;
                lengthScl = 0f;
                bulletLife = shootDuration;
            }
        }

        @Override
        public boolean shouldActiveSound() {
            return bullet != null;
        }
    }
}
