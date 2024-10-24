package heavyindustry.world.blocks.defense.turrets;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.entities.bullet.*;
import mindustry.graphics.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static heavyindustry.util.HIUtils.*;
import static arc.Core.*;
import static mindustry.Vars.*;

/**
 * Realize muzzle rotation and firing of Minigun.
 * @author Wisadell
 * @author MEEPofFaith
 */
public class MinigunTurret extends ItemTurret {
    public float windupSpeed = 0.2f, windDownSpeed = 0.1f, minFiringSpeed = 3f, logicSpeedScl = 0.25f, maxSpeed = 30f;
    public float barX, barY, barStroke, barLength;
    public float barWidth = 1.5f, barHeight = 0.75f;

    public MinigunTurret(String name){
        super(name);

        drawer = new DrawMinigun();
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.remove(Stat.reload);
        stats.add(Stat.reload, stringsFixed(minFiringSpeed / 90f * 60f * shoot.shots) + " - " + stringsFixed(maxSpeed / 90f * 60f * shoot.shots) + StatUnit.perSecond.localized());
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("hi-minigun-speed", (MinigunTurretBuild e) -> new Bar(() -> bundle.format("bar.hi-minigun-speed", stringsFixed(e.speedf() * 100f)), e::barColor, e::speedf));
    }

    public class MinigunTurretBuild extends ItemTurretBuild{
        protected float[] heats = {0f, 0f, 0f, 0f};
        protected float spinSpeed, spin;

        public Color barColor(){
            return spinSpeed > minFiringSpeed ? team.color : team.palette[2];
        }

        @Override
        public void updateTile(){
            boolean notShooting = !hasAmmo() || !isShooting() || !isActive();
            if(notShooting){
                spinSpeed = Mathf.approachDelta(spinSpeed, 0, windDownSpeed);
            }

            if(spinSpeed > getMaxSpeed()){
                spinSpeed = Mathf.approachDelta(spinSpeed, getMaxSpeed(), windDownSpeed);
            }

            for(int i = 0; i < 4; i++){
                heats[i] = Math.max(heats[i] - Time.delta / cooldownTime, 0);
            }

            super.updateTile();
        }

        @Override
        protected void updateShooting(){
            if(!hasAmmo()) return;

            spinSpeed = Mathf.approachDelta(spinSpeed, getMaxSpeed(), windupSpeed * peekAmmo().reloadMultiplier * timeScale);

            if(reloadCounter >= 90 && spinSpeed > minFiringSpeed){
                BulletType type = peekAmmo();

                shoot(type);

                reloadCounter = spin % 90;

                heats[Mathf.floor(spin - 90) % 360 / 90] = 1f;
            }
        }

        @Override
        protected void updateReload(){
            boolean shooting = hasAmmo() && isShooting() && isActive();
            float multiplier = hasAmmo() ? peekAmmo().reloadMultiplier : 1f;
            float add = spinSpeed * multiplier * Time.delta;
            if(shooting && coolant != null && coolant.efficiency(this) > 0 && efficiency > 0){
                float capacity = coolant instanceof ConsumeLiquidFilter filter ? filter.getConsumed(this).heatCapacity : 1f;
                coolant.update(this);
                add += coolant.amount * edelta() * capacity * coolantMultiplier;

                if(Mathf.chance(0.06 * coolant.amount)){
                    coolEffect.at(x + Mathf.range(size * tilesize / 2f), y + Mathf.range(size * tilesize / 2f));
                }
            }
            spin += add;
            reloadCounter += add;
        }

        protected float getMaxSpeed(){
            return maxSpeed * (!isControlled() && logicControlled() && logicShooting ? logicSpeedScl : 1f);
        }

        protected float speedf(){
            return spinSpeed / maxSpeed;
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(spinSpeed);
            write.f(spin % 360f);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);

            if(revision >= 2){
                spinSpeed = read.f();

                if(revision >= 3){
                    spin = read.f();
                }
            }
        }

        @Override
        public byte version(){
            return 3;
        }
    }

    public static class DrawMinigun extends DrawTurret {
        public TextureRegion barrel, barrelOutline;

        @Override
        public void getRegionsToOutline(Block block, Seq<TextureRegion> out) {
            super.getRegionsToOutline(block, out);
            out.add(barrel);
        }

        @Override
        public void load(Block block){
            super.load(block);

            barrel = atlas.find(block.name + "-barrel");
            barrelOutline = atlas.find(block.name + "-barrel-outline");
        }

        @Override
        public void drawTurret(Turret block, TurretBuild build){
            if(!(block instanceof MinigunTurret mb && build instanceof MinigunTurretBuild m)) return;

            Vec2 v = Tmp.v1;

            Draw.z(Layer.turret- 0.01f);
            Draw.rect(outline, build.x + m.recoilOffset.x, build.y + m.recoilOffset.y, build.drawrot());
            for(int i = 0; i < 4; i++){
                Draw.z(Layer.turret - 0.01f);
                v.trns(m.rotation - 90f, mb.barWidth * Mathf.cosDeg(m.spin - 90 * i), mb.barHeight * Mathf.sinDeg(m.spin - 90 * i)).add(m.recoilOffset);
                Draw.rect(barrelOutline, m.x + v.x, m.y + v.y, m.drawrot());
                Draw.z(Layer.turret - 0.005f - Mathf.sinDeg(m.spin - 90 * i) / 1000f);
                Draw.rect(barrel, m.x + v.x, m.y + v.y, m.drawrot());
                if(m.heats[i] > 0.001f){
                    Drawf.additive(heat, mb.heatColor.write(Tmp.c1).a(m.heats[i]), m.x + v.x, m.y + v.y, m.drawrot(), Draw.z());
                }
            }

            Draw.z(Layer.turret);
            super.drawTurret(block, build);

            if(m.speedf() > 0.0001f){
                Draw.color(m.barColor());
                Lines.stroke(mb.barStroke);
                for(int i = 0; i < 2; i++){
                    v.trns(m.drawrot(), mb.barX * Mathf.signs[i], mb.barY).add(m.recoilOffset);
                    Lines.lineAngle(m.x + v.x, m.y + v.y, m.rotation, mb.barLength * Mathf.clamp(m.speedf()), false);
                }
            }
        }

        @Override
        public void drawHeat(Turret block, TurretBuild build){
            //Don't
        }
    }
}
