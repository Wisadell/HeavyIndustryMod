package heavyindustry.util;

import arc.func.*;
import arc.graphics.Color;
import arc.graphics.Pixmap;
import arc.graphics.Texture;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.PixmapRegion;
import arc.graphics.g2d.TextureAtlas;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.math.geom.Point2;
import arc.math.geom.Position;
import arc.math.geom.Vec2;
import arc.scene.style.Drawable;
import arc.scene.ui.ImageButton;
import arc.struct.IntIntMap;
import arc.struct.ObjectMap;
import arc.struct.Seq;
import arc.util.Eachable;
import arc.util.Time;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.ctype.UnlockableContent;
import mindustry.entities.Mover;
import mindustry.entities.bullet.ArtilleryBulletType;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.bullet.PointBulletType;
import mindustry.entities.pattern.ShootSpread;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.gen.Bullet;
import mindustry.gen.Entityc;
import mindustry.gen.Velc;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.Turret;
import org.jetbrains.annotations.Contract;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static arc.Core.atlas;
import static arc.Core.bundle;
import static heavyindustry.core.HeavyIndustryMod.name;
import static mindustry.Vars.*;

/**
 * Input-output utilities, providing very specific functions that aren't really commonly used, but often enough to require me to write a class for it.
 * @author Wisadell
 */
public final class HIUtils {
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

    public static final TextureRegion[] EMP_REGIONS = new TextureRegion[0];

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
            if(b instanceof Turret t){
                turretSeq.addUnique(t);
            }
        }
        return turretSeq;
    }

    /** turret and unit only, not use contents.bullets() */
    public static Seq<BulletType> bulletTypes(){//use item
        Seq<BulletType> bullets = new Seq<>();
        for(Turret t : turrets()){
            if(t instanceof ItemTurret it){
                for(Item i : it.ammoTypes.keys()){
                    BulletType b = it.ammoTypes.get(i);
                    if(t.shoot.shots == 1 || b instanceof PointBulletType || b instanceof ArtilleryBulletType){
                        bullets.add(b);
                    } else {
                        BulletType bulletType = new BulletType() {{
                            fragBullet = b;
                            fragBullets = t.shoot.shots;
                            fragAngle = 0;
                            if (t.shoot instanceof ShootSpread s) {
                                fragSpread = s.spread;
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

        if(bt.keepVelocity && owner instanceof Velc v) bullet.vel.add(v.vel());

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

    public static final class SpriteUtils {
        public static final int[] atlasIndex44 = {
                0, 2, 10, 8,
                4, 6, 14, 12,
                5, 7, 15, 13,
                1, 3, 11, 9
        };

        public static final int[] atlasIndex412raw = {
                0, 2, 10, 8, 143, 46, 78, 31, 38, 111, 110, 76,
                4, 6, 14, 12, 39, 127, 239, 77, 55, 95, 175, 207,
                5, 7, 15, 13, 23, 191, 223, 141, 63, 255, 240, 205,
                1, 3, 11, 9, 79, 27, 139, 47, 19, 155, 159, 137
        };

        public static final int[] atlasIndex412 = new int[atlasIndex412raw.length];
        public static final IntIntMap atlasIndex412map = new IntIntMap();

        public static final byte[] atlasIndex412tile = {
                39, 36, 39, 36, 27, 16, 27, 24, 39, 36, 39, 36, 27, 16, 27, 24,
                38, 37, 38, 37, 17, 41, 17, 43, 38, 37, 38, 37, 26, 21, 26, 25,
                39, 36, 39, 36, 27, 16, 27, 24, 39, 36, 39, 36, 27, 16, 27, 24,
                38, 37, 38, 37, 17, 41, 17, 43, 38, 37, 38, 37, 26, 21, 26, 25,
                3, 4, 3, 4, 15, 40, 15, 20, 3, 4, 3, 4, 15, 40, 15, 20,
                5, 28, 5, 28, 29, 10, 29, 23, 5, 28, 5, 28, 31, 11, 31, 32,
                3, 4, 3, 4, 15, 40, 15, 20, 3, 4, 3, 4, 15, 40, 15, 20,
                2, 30, 2, 30, 9, 47, 9, 22, 2, 30, 2, 30, 14, 44, 14, 6,
                39, 36, 39, 36, 27, 16, 27, 24, 39, 36, 39, 36, 27, 16, 27, 24,
                38, 37, 38, 37, 17, 41, 17, 43, 38, 37, 38, 37, 26, 21, 26, 25,
                39, 36, 39, 36, 27, 16, 27, 24, 39, 36, 39, 36, 27, 16, 27, 24,
                38, 37, 38, 37, 17, 41, 17, 43, 38, 37, 38, 37, 26, 21, 26, 25,
                3, 0, 3, 0, 15, 42, 15, 12, 3, 0, 3, 0, 15, 42, 15, 12,
                5, 8, 5, 8, 29, 35, 29, 33, 5, 8, 5, 8, 31, 34, 31, 7,
                3, 0, 3, 0, 15, 42, 15, 12, 3, 0, 3, 0, 15, 42, 15, 12,
                2, 1, 2, 1, 9, 45, 9, 19, 2, 1, 2, 1, 14, 18, 14, 13
        };

        public static final Point2[] orthogonalPos = {
                new Point2(0, 1),
                new Point2(1, 0),
                new Point2(0, -1),
                new Point2(-1, 0),
        };

        public static final Point2[][] diagonalPos = {
                new Point2[]{ new Point2(1, 0), new Point2(1, 1), new Point2(0, 1)},
                new Point2[]{ new Point2(1, 0), new Point2(1, -1), new Point2(0, -1)},
                new Point2[]{ new Point2(-1, 0), new Point2(-1, -1), new Point2(0, -1)},
                new Point2[]{ new Point2(-1, 0), new Point2(-1, 1), new Point2(0, 1)},
        };

        public static final Point2[] proximityPos = {
                new Point2(0, 1),
                new Point2(1, 0),
                new Point2(0, -1),
                new Point2(-1, 0),

                new Point2(1, 1),
                new Point2(1, -1),
                new Point2(-1, -1),
                new Point2(-1, 1),
        };

        static {
            Integer[] indices = new Integer[atlasIndex412raw.length];
            for (int i = 0; i < atlasIndex412raw.length; i++) {
                indices[i] = i;
            }

            for (int i = 1; i < indices.length; i++) {
                int key = indices[i];
                int keyValue = atlasIndex412raw[key];
                int j = i - 1;

                while (j >= 0 && atlasIndex412raw[indices[j]] > keyValue) {
                    indices[j + 1] = indices[j];
                    j = j - 1;
                }
                indices[j + 1] = key;
            }

            for (int i = 0; i < indices.length; i++) {
                atlasIndex412[indices[i]] = i;
            }

            for (int i = 0; i < atlasIndex412raw.length; i++) {
                atlasIndex412map.put(atlasIndex412raw[i], atlasIndex412[i]);
            }
        }

        public static TextureRegion[] split(String name, int tileWidth, int tileHeight){
            return split(name, tileWidth, tileHeight, 0);
        }

        public static TextureRegion[] split(String name, int tileWidth, int tileHeight, int pad){
            return split(name, tileWidth, tileHeight, pad, null);
        }

        public static TextureRegion[] split(String name, int tileWidth, int tileHeight, int pad, int[] indexMap){
            TextureRegion region = atlas.find(name);

            int x = region.getX();
            int y = region.getY();
            int width = region.width;
            int height = region.height;

            int pWidth = tileWidth + pad * 2;
            int pHeight = tileHeight + pad * 2;

            int sw = width / pWidth;
            int sh = height / pHeight;

            int startX = x;
            TextureRegion[] tiles = new TextureRegion[sw * sh];
            for(int cy = 0; cy < sh; cy++, y += pHeight){
                x = startX;
                for(int cx = 0; cx < sw; cx++, x += pWidth){
                    int index = cx + cy * sw;
                    if (indexMap != null){
                        tiles[indexMap[index]] = new TextureRegion(region.texture, x + pad, y + pad, tileWidth, tileHeight);
                    }else {
                        tiles[index] = new TextureRegion(region.texture, x + pad, y + pad, tileWidth, tileHeight);
                    }
                }
            }

            return tiles;
        }

        /**
         * Rotate one {@link Pixmap} by a multiple of 90 degrees. This method does not change the original pixmap and returns a copy.
         * @param target The target pixmap to be rotated.
         * @param rotate Rotation angle coefficient, the actual rotation angle is 90 * rotate.
         * @return A rotated pixmap copy.
         */
        public static Pixmap rotatePixmap90(Pixmap target, int rotate){
            Pixmap res = new Pixmap(target.width, target.height);

            for(int x = 0; x < target.width; x++){
                for(int y = 0; y < target.height; y++){
                    int c = target.get(x, y);
                    switch(Mathf.mod(-rotate, 4)){
                        case 0 -> res.set(x, y, c);
                        case 1 -> res.set(target.width - y - 1, x, c);
                        case 2 -> res.set(target.width - x - 1, target.height - y - 1, c);
                        case 3 -> res.set(y, target.height - x - 1, c);
                    }
                }
            }

            return res;
        }
    }

    /**
     * Struct utilities, providing some stateless iterative utilities such as reduce.
     * @author Wisadell
     */
    @SuppressWarnings("unchecked")
    public static final class StructUtils {
        private static final Object[] emptyArray = new Object[0];
        private static final Empty<?> empty = new Empty<>();

        public static <T> Empty<T> empty(){
            // SAFETY: Has no references or casts to T, so type erasure shouldn't mess everything up.
            return (Empty<T>)empty;
        }

        public static <T> T[] emptyArray(){
            // SAFETY: If users require the type to be exactly T[], they can use reflection instead.
            return (T[])emptyArray;
        }

        public static <T> T[] copyArray(T[] array, Func<T, T> copy){
            T[] out = array.clone();
            for(int i = 0, len = out.length; i < len; i++) out[i] = copy.get(out[i]);
            return out;
        }

        public static <T> boolean any(T[] array, Boolf<T> pred){
            for(T e : array) if(pred.get(e)) return true;
            return false;
        }

        public static <T> boolean all(T[] array, Boolf<T> pred){
            for(T e : array) if(!pred.get(e)) return false;
            return true;
        }

        public static <T> void each(T[] array, Cons<? super T> cons){
            each(array, 0, array.length, cons);
        }

        public static <T> void each(T[] array, int offset, int length, Cons<? super T> cons){
            for(int i = offset, len = i + length; i < len; i++) cons.get(array[i]);
        }

        public static <T> Single<T> iter(T item){
            return new Single<>(item);
        }

        public static <T> Iter<T> iter(T... array){
            return iter(array, 0, array.length);
        }

        public static <T> Iter<T> iter(T[] array, int offset, int length){
            return new Iter<>(array, offset, length);
        }

        public static <T> Chain<T> chain(Iterator<T> first, Iterator<T> second){
            return new Chain<>(first, second);
        }

        public static <T, R> R reduce(T[] array, R initial, Func2<T, R, R> reduce){
            for(var item : array) initial = reduce.get(item, initial);
            return initial;
        }

        public static <T> int reducei(T[] array, int initial, Reducei<T> reduce){
            for(var item : array) initial = reduce.get(item, initial);
            return initial;
        }

        public static <T> int sumi(T[] array, Intf<T> extract){
            return reducei(array, 0, (item, accum) -> accum + extract.get(item));
        }

        public static <T> float reducef(T[] array, float initial, Reducef<T> reduce){
            for(var item : array) initial = reduce.get(item, initial);
            return initial;
        }

        public static <T> float average(T[] array, Floatf<T> extract){
            return reducef(array, 0f, (item, accum) -> accum + extract.get(item)) / array.length;
        }

        public static <T> T[] resize(T[] array, int newSize, T fill){
            var type = array.getClass().getComponentType();
            return resize(array, size -> (T[]) Array.newInstance(type, size), newSize, fill);
        }

        public static <T> T[] resize(T[] array, ArrayCreator<T> create, int newSize, T fill){
            if(array.length == newSize) return array;

            var out = create.get(newSize);
            System.arraycopy(array, 0, out, 0, Math.min(array.length, newSize));

            if(fill != null && newSize > array.length) Arrays.fill(out, array.length, newSize, fill);
            return out;
        }

        public static <T> boolean arrayEq(T[] first, T[] second, Boolf2<T, T> eq){
            if(first.length != second.length) return false;
            for(int i = 0; i < first.length; i++){
                if(!eq.get(first[i], second[i])) return false;
            }
            return true;
        }

        public interface Reducei<T>{
            int get(T item, int accum);
        }

        public interface Reducef<T>{
            float get(T item, float accum);
        }

        public interface ArrayCreator<T>{
            T[] get(int size);
        }

        public static class Empty<T> implements Iterable<T>, Iterator<T>, Eachable<T>{
            @Override
            public Empty<T> iterator(){
                return this;
            }

            @Override
            public boolean hasNext(){
                return false;
            }

            @Override
            public T next(){
                throw new NoSuchElementException("Empty<T> has no elements.");
            }

            @Override
            public void each(Cons<? super T> cons){}
        }

        public static class Single<T> implements Iterable<T>, Iterator<T>, Eachable<T>{
            protected final T item;
            protected boolean done;

            public Single(T item){
                this.item = item;
            }

            @Override
            public Single<T> iterator(){
                return this;
            }

            @Override
            public boolean hasNext(){
                return !done;
            }

            @Override
            public T next(){
                if(done) return null;
                done = true;
                return item;
            }

            @Override
            public void each(Cons<? super T> cons){
                if(!done) cons.get(item);
            }
        }

        public static class Iter<T> implements Iterable<T>, Iterator<T>, Eachable<T>{
            protected final T[] array;
            protected final int offset, length;
            protected int index = 0;

            public Iter(T[] array, int offset, int length){
                this.array = array;
                this.offset = offset;
                this.length = length;
            }

            @Override
            public Iter<T> iterator(){
                return this;
            }

            @Override
            public boolean hasNext(){
                return index < length - offset;
            }

            @Override
            public T next(){
                if(hasNext()) return array[offset + index++];
                else return null;
            }

            @Override
            public void each(Cons<? super T> cons){
                while(hasNext()) cons.get(array[offset + index++]);
            }
        }

        public static class Chain<T> implements Iterable<T>, Iterator<T>, Eachable<T>{
            protected final Iterator<T> first, second;

            public Chain(Iterator<T> first, Iterator<T> second){
                this.first = first;
                this.second = second;
            }

            @Override
            public Chain<T> iterator(){
                return this;
            }

            @Override
            public boolean hasNext(){
                return first.hasNext() || second.hasNext();
            }

            @Override
            public T next(){
                return first.hasNext() ? first.next() : second.next();
            }

            @Override
            public void each(Cons<? super T> cons){
                while(first.hasNext()) cons.get(first.next());
                while(second.hasNext()) cons.get(second.next());
            }
        }
    }

    /**
     * Tool set used for directional edge coordinate traversal.
     * @since 1.5
     */
    public static final class DirEdges {
        private static final Point2[][][] edges = new Point2[maxBlockSize + 1][4][0];
        private static final Point2[][][] angle = new Point2[maxBlockSize + 1][4][1];

        static {
            edges[0] = new Point2[4][0];
            angle[0] = new Point2[4][0];

            for(int i = 1; i < maxBlockSize; i++){
                int off = (i + 1) % 2;
                int rad = i / 2;
                int minOff = -rad + off;

                for(int j = 0; j < 4; j++){
                    edges[i][j] = new Point2[i];
                    for(int m = minOff; m <= rad; m++){
                        switch(j){
                            case 0 -> edges[i][j][m + rad - off] = new Point2(rad + 1, m);
                            case 1 -> edges[i][j][m + rad - off] = new Point2(m, rad + 1);
                            case 2 -> edges[i][j][m + rad - off] = new Point2(-rad - 1 + off, m);
                            case 3 -> edges[i][j][m + rad - off] = new Point2(m, -rad - 1 + off);
                        }
                    }

                    angle[i][j] = new Point2[]{
                            switch(j){
                                case 0 -> new Point2(rad + 1, rad + 1);
                                case 1 -> new Point2(-rad - 1 + off, rad + 1);
                                case 2 -> new Point2(-rad - 1 + off, -rad - 1 + off);
                                case 3 -> new Point2(rad + 1, -rad - 1 + off);
                                default -> throw new IllegalStateException("Unexpected value: " + j);
                            }
                    };

                    Arrays.sort(edges[i][j], (a, b) -> Float.compare(Mathf.angle(a.x, a.y), Mathf.angle(b.x, b.y)));
                }
            }
        }

        /**
         * Obtain all coordinate arrays of the edges of a block of a certain size in one direction.
         * @param size The size of the block.
         * @param direction Direction, integer, top left and bottom right order are 0 1 2 3, modulo.
         */
        public static Point2[] get(int size, int direction){
            if(size < 0 || size > maxBlockSize) throw new RuntimeException("Block size must be between 0 and " + maxBlockSize);

            return edges[size][Mathf.mod(direction, 4)];
        }

        /**
         * Obtain all coordinate arrays of a block of a certain size on one side of a direction including the corner edges.
         * @param size The size of the block.
         * @param direction Direction, integer, take 0 on the right, add 1 clockwise in sequence, take the corner position.
         */
        public static Point2[] get8(int size, int direction){
            if(size < 0 || size > maxBlockSize) throw new RuntimeException("Block size must be between 0 and " + maxBlockSize);

            int dir = Mathf.mod(direction, 8);
            return dir % 2 == 0 ? edges[size][dir / 2] : angle[size][dir / 2];
        }

        /**
         * Traverse the edge coordinates on one side using callbacks.
         * @param tile The central floor must have blocks in the correct state on this floor.
         * @param direction Traverse in the lateral direction.
         * @param angles Do you need to select four corners?
         * @param posCons Callback function for edge coordinates.
         */
        public static void eachDirPos(Tile tile, int direction, boolean angles, Floatc2 posCons){
            tile = tile.build.tile;
            Point2[] arr = angles ? get8(tile.block().size, direction) : get(tile.block().size, direction);

            for(Point2 p: arr){
                posCons.get(tile.x + p.x, tile.y + p.y);
            }
        }
    }
}
