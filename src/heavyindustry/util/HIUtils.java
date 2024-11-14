package heavyindustry.util;

import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.scene.style.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.pattern.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.turrets.*;
import org.jetbrains.annotations.*;

import java.util.*;

import static arc.Core.*;
import static mindustry.Vars.*;
import static heavyindustry.core.HeavyIndustryMod.*;

/**
 * Input-output utilities, providing very specific functions that aren't really commonly used, but often enough to require me to write a class for it.
 * @author Wisadell
 */
public final class HIUtils {
    public static String stringsFixed(float value){
        return Strings.autoFixed(value, 2);
    }

    @Contract(pure = true)
    public static int reverse(int rotation){
        return switch(rotation){
            case 0 -> 2; case 2 -> 0; case 1 -> 3; case 3 -> 1;
            default -> throw new IllegalStateException("Unexpected value: " + rotation);
        };
    }

    public static TextureRegion[][] splitLayers(String name, int size, int layerCount){
        TextureRegion[][] layers = new TextureRegion[layerCount][];

        for(int i = 0; i < layerCount; i++){
            layers[i] = split(name, size, i);
        }
        return layers;
    }

    public static TextureRegion[] split(String name, int size, int layer){
        TextureRegion textures = atlas.find(name);
        int margin = 0;
        int countX = textures.width / size;
        TextureRegion[] tiles = new TextureRegion[countX];

        for(int i = 0; i < countX; i++){
            tiles[i] = new TextureRegion(textures, i * (margin + size), layer * (margin + size), size, size);
        }
        return tiles;
    }

    /**
     * Gets multiple regions inside a {@link TextureRegion}.
     * @param width The amount of regions horizontally.
     * @param height The amount of regions vertically.
     */
    public static TextureRegion[] split(String name, int size, int width, int height){
        TextureRegion textures = atlas.find(name);
        int textureSize = width * height;
        TextureRegion[] regions = new TextureRegion[textureSize];

        float tileWidth = (textures.u2 - textures.u) / width;
        float tileHeight = (textures.v2 - textures.v) / height;

        for(int i = 0; i < textureSize; i++){
            float tileX = ((float)(i % width)) / width;
            float tileY = ((float)(i / width)) / height;
            TextureRegion region = new TextureRegion(textures);

            //start coordinate
            region.u = Mathf.map(tileX, 0f, 1f, region.u, region.u2) + tileWidth * 0.02f;
            region.v = Mathf.map(tileY, 0f, 1f, region.v, region.v2) + tileHeight * 0.02f;
            //end coordinate
            region.u2 = region.u + tileWidth * 0.96f;
            region.v2 = region.v + tileHeight * 0.96f;

            region.width = region.height = size;

            regions[i] = region;
        }
        return regions;
    }

    /**
     * {@link Tile#relativeTo(int, int)} does not account for building rotation.
     */
    public static int relativeDirection(Building from, Building to){
        if(from == null || to == null) return -1;
        if(from.x == to.x && from.y > to.y) return (7 - from.rotation) % 4;
        if(from.x == to.x && from.y < to.y) return (5 - from.rotation) % 4;
        if(from.x > to.x && from.y == to.y) return (6 - from.rotation) % 4;
        if(from.x < to.x && from.y == to.y) return (4 - from.rotation) % 4;
        return -1;
    }

    public static Color
            c1 = new Color(),
            c2 = new Color(),
            c3 = new Color(),
            c4 = new Color(),
            c5 = new Color(),
            c6 = new Color(),
            c7 = new Color(),
            c8 = new Color(),
            c9 = new Color(),
            c10 = new Color();

    public static Vec2
            v1 = new Vec2(),
            v2 = new Vec2(),
            v3 = new Vec2();

    public static Seq<UnlockableContent> donorItems = new Seq<>();
    public static Seq<UnlockableContent> developerItems = new Seq<>();

    private static final String DONOR = bundle.get("hi-donor-item");
    private static final String DEVELOPER = bundle.get("hi-developer-item");

    public static void loadItems(){
        for(UnlockableContent c : donorItems){
            c.description = (c.description == null ? DONOR : c.description + "\n" + DONOR);
        }
        for(UnlockableContent c : developerItems){
            c.description = (c.description == null ? DEVELOPER : c.description + "\n" + DEVELOPER);
        }
    }

    public static class ExtendedPosition implements Position {
        public float x, y;

        public ExtendedPosition set(float x, float y){
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

    @Contract(value = "_, _ -> new", pure = true)
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

    /** turret and unit only, not use contents.bullets() */
    public static Seq<BulletType> bulletTypes(){//use item
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

    //use for cst bullet
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

    public static void liquid(ObjectMap<Integer, Cons<Liquid>> cons, String name, Color color, float exp, float fla, float htc, float vis, float temp) {
        for (int i = 1 ; i < 10 ; i++){
            int index = i;
            var l = new Liquid(name + index, color){{
                explosiveness = exp * index;
                flammability = fla * index;
                heatCapacity = htc * index;
                viscosity = vis * index;
                temperature = temp / index;
            }};
            if(cons != null && cons.size > 0 && cons.containsKey(i)){
                cons.get(i).get(l);
            }
        }
    }

    public static void liquid(String name, Color color, float exp, float fla, float htc, float vis, float temp) {
        liquid(null, name, color, exp, fla, htc, vis, temp);
    }

    public static void item(ObjectMap<Integer, Cons<Item>> cons, String name, Color color, float exp, float fla, float cos, float radio, float chg, float health) {
        for (int i = 1 ; i < 10 ; i++){
            int index = i;
            var item = new Item(name + index, color){{
                explosiveness = exp * index;
                flammability = fla * index;
                cost = cos * index;
                radioactivity = radio * index;
                charge = chg * index;
                healthScaling = health * index;
            }};
            if(cons != null && cons.size > 0 && cons.containsKey(i)){
                cons.get(i).get(item);
            }
        }
    }

    /**
     * 1. Cannot use {@link Vars#content}
     * 2. Cannot be used for init() anymore
     * @author guiY
     */
    public static void test(){
        int size = 40;
        for(var l : new Liquid[]{Liquids.water, Liquids.slag, Liquids.oil, Liquids.cryofluid,
                Liquids.arkycite, Liquids.gallium, Liquids.neoplasm,
                Liquids.ozone, Liquids.hydrogen, Liquids.nitrogen, Liquids.cyanogen}){
            if(l.hidden) continue;
            ObjectMap<Integer, Cons<Liquid>> cons = getEntries(l, size);
            liquid(cons, l.name, l.color, l.explosiveness, l.flammability, l.heatCapacity, l.viscosity, l.temperature);
        }

        for(var item : new Item[]{Items.scrap, Items.copper, Items.lead, Items.graphite, Items.coal, Items.titanium, Items.thorium, Items.silicon, Items.plastanium,
                Items.phaseFabric, Items.surgeAlloy, Items.sporePod, Items.sand, Items.blastCompound, Items.pyratite, Items.metaglass,
                Items.beryllium, Items.tungsten, Items.oxide, Items.carbide, Items.fissileMatter, Items.dormantCyst}){
            if(item.hidden) continue;
            ObjectMap<Integer, Cons<Item>> cons = getEntries(item, size);
            item(cons, item.name, item.color, item.explosiveness, item.flammability, item.cost, item.radioactivity, item.charge, item.healthScaling);
        }
        Draw.color();
    }

    private static ObjectMap<Integer, Cons<Item>> getEntries(Item item, int size) {
        ObjectMap<Integer, Cons<Item>> cons = new ObjectMap<>();
        for(int i = 1; i < 10; i++){
            int finalI = i;
            cons.put(i, it -> {
                PixmapRegion base = atlas.getPixmap(item.uiIcon);
                var mix = base.crop();
                var number = atlas.find(name("number-" + finalI));
                if(number.found()) {
                    PixmapRegion region = TextureAtlas.blankAtlas().getPixmap(number);

                    mix.draw(region.pixmap, region.x, region.y, region.width, region.height, 0, base.height - size, size, size, false, true);
                }

                it.uiIcon = it.fullIcon = new TextureRegion(new Texture(mix));

                it.buildable = item.buildable;
                it.hardness = item.hardness + finalI;
                it.lowPriority = item.lowPriority;
            });
        }
        return cons;
    }

    private static ObjectMap<Integer, Cons<Liquid>> getEntries(Liquid liquid, int size) {
        ObjectMap<Integer, Cons<Liquid>> cons = new ObjectMap<>();
        for(int i = 1; i < 10; i++){
            int finalI = i;
            cons.put(i, ld -> {
                PixmapRegion base = atlas.getPixmap(liquid.uiIcon);
                var mix = base.crop();
                var number = atlas.find(name("number-" + finalI));
                if(number.found()) {
                    PixmapRegion region = TextureAtlas.blankAtlas().getPixmap(number);

                    mix.draw(region.pixmap, region.x, region.y, region.width, region.height, 0, base.height - size, size, size, false, true);
                }

                ld.uiIcon = ld.fullIcon = new TextureRegion(new Texture(mix));
            });
        }
        return cons;
    }

    public static ImageButton selfStyleImageButton(Drawable imageUp, ImageButton.ImageButtonStyle is, Runnable listener){
        ImageButton ib = new ImageButton(new ImageButton.ImageButtonStyle(null, null, null, imageUp, null, null));
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle(is);
        style.imageUp = imageUp;
        ib.setStyle(style);
        if(listener != null) ib.changed(listener);
        return ib;
    }
}
