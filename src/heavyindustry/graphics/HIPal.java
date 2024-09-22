package heavyindustry.graphics;

import heavyindustry.content.*;
import arc.graphics.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.pattern.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.turrets.*;

import java.util.*;

import static mindustry.Vars.*;

public class HIPal {
    /** Static read-only palettes that are used throughout the mod. */
    public static final Color
            nanocoreGreen = HIItems.nanocore.color,
            highEnergyYellow = HIItems.highEnergyFabric.color,
            uraniumGrey = HIItems.uranium.color,
            chromiumGrey = HIItems.chromium.color,
            iceBlue = Color.valueOf("c0ecff"),
            iceBlueDark = Color.valueOf("6d90bc"),
            ancient = Items.surgeAlloy.color.cpy().lerp(Pal.accent, 0.115f),
            ancientHeat = Color.red.cpy().mul(1.075f),
            ancientLight = ancient.cpy().lerp(Color.white, 0.7f),
            lightSky = Color.valueOf("#8db0ff"),
            lightSkyBack = lightSky.cpy().lerp(Color.white, 0.2f),
            lightSkyMiddle = lightSky.cpy().lerp(Color.white, 0.6f),
            lightSkyFront = lightSky.cpy().lerp(Color.white, 0.77f),
            thurmixRed = Color.valueOf("#ff9492"),
            thurmixRedLight = Color.valueOf("#ffced0"),
            thurmixRedDark = thurmixRed.cpy().lerp(Color.black, 0.9f),
            rainBowRed = Color.valueOf("ff8787"),
            MIKU = Color.valueOf("39c5bb");
    public static Color
            EC1 = new Color(),
            EC2 = new Color(),
            EC3 = new Color(),
            EC4 = new Color(),
            EC5 = new Color(),
            EC6 = new Color(),
            EC7 = new Color(),
            EC8 = new Color(),
            EC9 = new Color(),
            EC10 = new Color(),
            EC11 = new Color(),
            EC12 = new Color(),
            EC13 = new Color(),
            EC14 = new Color(),
            EC15 = new Color(),
            EC16 = new Color(),
            EC17 = new Color(),
            EC18 = new Color(),
            EC19 = new Color(),
            EC20 = new Color();

    public static class EPos implements Position {
        public float x, y;

        public HIPal.EPos set(float x, float y){
            this.x = x;
            this.y = y;
            return this;
        }

        @Override
        public float getX() {
            return x;
        }

        @Override
        public float getY() {
            return y;
        }
    }

    public static Position pos(float x, float y){
        return new Position() {
            @Override
            public float getX() {
                return x;
            }

            @Override
            public float getY() {
                return y;
            }
        };
    }

    public static float dx(float px, float r, float angle){
        return px + r * (float) Math.cos(angle * Math.PI/180);
    }

    public static float dy(float py, float r, float angle){
        return py + r * (float) Math.sin(angle * Math.PI/180);
    }

    public static float posx(float x, float length, float angle){
        float a = (float) ((Math.PI * angle)/180);
        float cos = (float) Math.cos(a);
        return x + length * cos;
    }
    public static float posy(float y, float length, float angle){
        float a = (float) ((Math.PI * angle)/180);
        float sin = (float) Math.sin(a);
        return y + length * sin;
    }

    public static boolean isInstanceButNotSubclass(Object obj, Class<?> clazz) {
        if (clazz.isInstance(obj)) {
            try {
                if (getClassSubclassHierarchy(obj.getClass()).contains(clazz)) {
                    return false;
                }
            } catch (ClassCastException e) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public static Seq<Class<?>> getClassSubclassHierarchy(Class<?> clazz) {
        Class<?> c = clazz.getSuperclass();
        Seq<Class<?>> hierarchy = new Seq<>();
        while (c != null) {
            hierarchy.add(c);
            Class<?>[] interfaces = c.getInterfaces();
            hierarchy.addAll(Arrays.asList(interfaces));
            c = c.getSuperclass();
        }
        return hierarchy;
    }

    public static Seq<Turret> turrets(){
        Seq<Turret> turretSeq = new Seq<>();
        int size = content.blocks().size;
        for(int i = 0; i < size; i++){
            Block b = content.block(i);
            if(b instanceof Turret){
                turretSeq.addUnique((Turret) b);
            }
        }
        return turretSeq;
    }

    public static Seq<BulletType> bulletTypes(){
        Seq<BulletType> bullets = new Seq<>();
        for(Turret t : turrets()){
            if(t instanceof ItemTurret){
                for(Item i : ((ItemTurret) t).ammoTypes.keys()){
                    BulletType b = ((ItemTurret) t).ammoTypes.get(i);
                    if(t.shoot.shots == 1 || b instanceof PointBulletType || b instanceof ArtilleryBulletType){
                        bullets.add(b);
                    } else {
                        BulletType bulletType = new BulletType() {{
                            fragBullet = b;
                            fragBullets = t.shoot.shots;
                            fragAngle = 0;
                            if (t.shoot instanceof ShootSpread) {
                                fragSpread = ((ShootSpread) (t.shoot)).spread;
                            }
                            fragRandomSpread = t.inaccuracy;
                            fragVelocityMin = 1 - t.velocityRnd;
                            absorbable = hittable = collides = collidesGround = collidesAir = false;
                            despawnHit = true;
                            lifetime = damage = speed = 0;
                            hitEffect = despawnEffect = Fx.none;
                        }};
                        bullets.add(bulletType);
                    }
                }
            }
        }
        return bullets;
    }

    public static Bullet anyOtherCreate(Bullet bullet, BulletType bt, Entityc owner, Team team, float x, float y, float angle, float damage, float velocityScl, float lifetimeScl, Object data, Mover mover, float aimX, float aimY){
        bullet.type = bt;
        bullet.owner = owner;
        bullet.team = team;
        bullet.time = 0f;
        bullet.originX = x;
        bullet.originY = y;
        if(!(aimX == -1f && aimY == -1f)){
            bullet.aimTile = world.tileWorld(aimX, aimY);
        }
        bullet.aimX = aimX;
        bullet.aimY = aimY;

        bullet.initVel(angle, bt.speed * velocityScl);
        if(bt.backMove){
            bullet.set(x - bullet.vel.x * Time.delta, y - bullet.vel.y * Time.delta);
        }else{
            bullet.set(x, y);
        }
        bullet.lifetime = bt.lifetime * lifetimeScl;
        bullet.data = data;
        bullet.drag = bt.drag;
        bullet.hitSize = bt.hitSize;
        bullet.mover = mover;
        bullet.damage = (damage < 0 ? bt.damage : damage) * bullet.damageMultiplier();
        if(bullet.trail != null){
            bullet.trail.clear();
        }
        bullet.add();

        if(bt.keepVelocity && owner instanceof Velc) bullet.vel.add(((Velc)owner).vel());

        return bullet;
    }
}
