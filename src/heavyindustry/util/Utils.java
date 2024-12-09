package heavyindustry.util;

import arc.*;
import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.g2d.TextureAtlas.*;
import arc.math.*;
import arc.math.geom.*;
import arc.scene.style.*;
import arc.scene.ui.*;
import arc.scene.ui.ImageButton.*;
import arc.struct.*;
import arc.util.*;
import arc.util.pooling.*;
import heavyindustry.struct.*;
import mindustry.content.*;
import mindustry.core.*;
import mindustry.ctype.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.pattern.*;
import mindustry.game.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.draw.*;
import org.jetbrains.annotations.*;

import java.io.*;
import java.util.*;
import java.util.zip.*;

import static arc.Core.*;
import static heavyindustry.core.HeavyIndustryMod.*;
import static mindustry.Vars.*;

/**
 * Input-output utilities, providing very specific functions that aren't really commonly used, but often enough to require me to write a class for it.
 *
 * @author E-Nightingale
 */
public final class Utils {
    public static final TextureRegion[] EMP_REGIONS = new TextureRegion[0];

    private static final String DONOR = bundle.get("hi-donor-item");
    private static final String DEVELOPER = bundle.get("hi-developer-item");

    public static Color
            c1 = new Color(), c2 = new Color(), c3 = new Color(), c4 = new Color(), c5 = new Color(),
            c6 = new Color(), c7 = new Color(), c8 = new Color(), c9 = new Color(), c10 = new Color();

    public static Vec2
            v1 = new Vec2(), v2 = new Vec2(), v3 = new Vec2();

    public static Seq<UnlockableContent> donorItems = new Seq<>();
    public static Seq<UnlockableContent> developerItems = new Seq<>();

    private static Posc result;
    private static float cdist;
    private static int idx;
    private static final Vec2 tV = new Vec2(), tV2 = new Vec2();
    private static final IntSet collidedBlocks = new IntSet();
    private static final Rect rect = new Rect(), rectAlt = new Rect(), hitRect = new Rect();
    private static final BoolGrid collideLineCollided = new BoolGrid();
    private static final IntSeq lineCast = new IntSeq(), lineCastNext = new IntSeq();
    private static final Seq<Hit> hitEffects = new Seq<>();
    private static Building tmpBuilding;
    private static Unit tmpUnit;
    private static boolean hit, hitB;

    /** Utils should not be instantiated. */
    private Utils() {}

    public static void init() {
        Events.on(WorldLoadEvent.class, event -> collideLineCollided.resize(world.width(), world.height()));
    }

    public static void loadItems() {
        for (UnlockableContent c : donorItems) {
            c.description = (c.description == null ? DONOR : c.description + "\n" + DONOR);
        }
        for (UnlockableContent c : developerItems) {
            c.description = (c.description == null ? DEVELOPER : c.description + "\n" + DEVELOPER);
        }
    }

    @Contract(pure = true)
    public static int reverse(int rotation) {
        return switch (rotation) {
            case 0 -> 2; case 2 -> 0; case 1 -> 3; case 3 -> 1;
            default -> throw new IllegalStateException("Unexpected value: " + rotation);
        };
    }

    public static TextureRegion[][] splitLayers(String name, int size, int layerCount) {
        TextureRegion[][] layers = new TextureRegion[layerCount][];

        for (int i = 0; i < layerCount; i++) {
            layers[i] = split(name, size, i);
        }
        return layers;
    }

    public static TextureRegion[] split(String name, int size, int layer) {
        TextureRegion textures = atlas.find(name);
        int margin = 0;
        int countX = textures.width / size;
        TextureRegion[] tiles = new TextureRegion[countX];

        for (int i = 0; i < countX; i++) {
            tiles[i] = new TextureRegion(textures, i * (margin + size), layer * (margin + size), size, size);
        }
        return tiles;
    }

    /**
     * Gets multiple regions inside a {@link TextureRegion}.
     *
     * @param width  The amount of regions horizontally.
     * @param height The amount of regions vertically.
     */
    public static TextureRegion[] split(String name, int size, int width, int height) {
        TextureRegion textures = atlas.find(name);
        int textureSize = width * height;
        TextureRegion[] regions = new TextureRegion[textureSize];

        float tileWidth = (textures.u2 - textures.u) / width;
        float tileHeight = (textures.v2 - textures.v) / height;

        for (int i = 0; i < textureSize; i++) {
            float tileX = ((float) (i % width)) / width;
            float tileY = ((float) (i / width)) / height;
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

    /** {@link Tile#relativeTo(int, int)} does not account for building rotation. */
    public static int relativeDirection(Building from, Building to) {
        if (from == null || to == null) return -1;
        if (from.x == to.x && from.y > to.y) return (7 - from.rotation) % 4;
        if (from.x == to.x && from.y < to.y) return (5 - from.rotation) % 4;
        if (from.x > to.x && from.y == to.y) return (6 - from.rotation) % 4;
        if (from.x < to.x && from.y == to.y) return (4 - from.rotation) % 4;
        return -1;
    }

    public static DrawBlock base() {
        return base(0f);
    }

    public static DrawBlock base(float rotatorSpeed) {
        return new DrawMulti(new DrawRegion("-rotator", rotatorSpeed), new DrawDefault(), new DrawRegion("-top"));
    }

    @Contract(value = "_, _ -> new", pure = true)
    public static Position pos(float x, float y) {
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

    public static float dx(float px, float r, float angle) {
        return px + r * (float) Math.cos(angle * Math.PI / 180);
    }

    public static float dy(float py, float r, float angle) {
        return py + r * (float) Math.sin(angle * Math.PI / 180);
    }

    public static float posx(float x, float length, float angle) {
        float a = (float) ((Math.PI * angle) / 180);
        float cos = (float) Math.cos(a);
        return x + length * cos;
    }

    public static float posy(float y, float length, float angle) {
        float a = (float) ((Math.PI * angle) / 180);
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

    public static Seq<Turret> turrets() {
        Seq<Turret> turretSeq = new Seq<>();
        int size = content.blocks().size;
        for (int i = 0; i < size; i++) {
            Block b = content.block(i);
            if (b instanceof Turret t) {
                turretSeq.addUnique(t);
            }
        }
        return turretSeq;
    }

    /** turret and unit only, not use contents.bullets() */
    public static Seq<BulletType> bulletTypes() {//use item
        Seq<BulletType> bullets = new Seq<>();
        for (Turret t : turrets()) {
            if (t instanceof ItemTurret it) {
                for (Item i : it.ammoTypes.keys()) {
                    BulletType b = it.ammoTypes.get(i);
                    if (t.shoot.shots == 1 || b instanceof PointBulletType || b instanceof ArtilleryBulletType) {
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
    public static Bullet anyOtherCreate(Bullet bullet, BulletType bt, Entityc owner, Team team, float x, float y, float angle, float damage, float velocityScl, float lifetimeScl, Object data, Mover mover, float aimX, float aimY) {
        bullet.type = bt;
        bullet.owner = owner;
        bullet.team = team;
        bullet.time = 0f;
        bullet.originX = x;
        bullet.originY = y;
        if (!(aimX == -1f && aimY == -1f)) {
            bullet.aimTile = world.tileWorld(aimX, aimY);
        }
        bullet.aimX = aimX;
        bullet.aimY = aimY;

        bullet.initVel(angle, bt.speed * velocityScl);
        if (bt.backMove) {
            bullet.set(x - bullet.vel.x * Time.delta, y - bullet.vel.y * Time.delta);
        } else {
            bullet.set(x, y);
        }
        bullet.lifetime = bt.lifetime * lifetimeScl;
        bullet.data = data;
        bullet.drag = bt.drag;
        bullet.hitSize = bt.hitSize;
        bullet.mover = mover;
        bullet.damage = (damage < 0 ? bt.damage : damage) * bullet.damageMultiplier();
        if (bullet.trail != null) {
            bullet.trail.clear();
        }
        bullet.add();

        if (bt.keepVelocity && owner instanceof Velc v) bullet.vel.add(v.vel());

        return bullet;
    }

    public static void liquid(ObjectMap<Integer, Cons<Liquid>> cons, String name, Color color, float exp, float fla, float htc, float vis, float temp) {
        for (int i = 1; i < 10; i++) {
            int index = i;
            var l = new Liquid(name + index, color) {{
                explosiveness = exp * index;
                flammability = fla * index;
                heatCapacity = htc * index;
                viscosity = vis * index;
                temperature = temp / index;
            }};
            if (cons != null && cons.size > 0 && cons.containsKey(i)) {
                cons.get(i).get(l);
            }
        }
    }

    public static void liquid(String name, Color color, float exp, float fla, float htc, float vis, float temp) {
        liquid(null, name, color, exp, fla, htc, vis, temp);
    }

    public static void item(ObjectMap<Integer, Cons<Item>> cons, String name, Color color, float exp, float fla, float cos, float radio, float chg, float health) {
        for (int i = 1; i < 10; i++) {
            int index = i;
            Item item = new Item(name + index, color) {{
                explosiveness = exp * index;
                flammability = fla * index;
                cost = cos * index;
                radioactivity = radio * index;
                charge = chg * index;
                healthScaling = health * index;
            }};
            if (cons != null && cons.size > 0 && cons.containsKey(i)) {
                cons.get(i).get(item);
            }
        }
    }

    /**
     * 1. Cannot use {@link mindustry.Vars#content}
     * 2. Cannot be used for init() anymore
     *
     * @author guiY
     */
    public static void test() {
        int size = 40;
        for (Liquid l : new Liquid[]{Liquids.water, Liquids.slag, Liquids.oil, Liquids.cryofluid,
                Liquids.arkycite, Liquids.gallium, Liquids.neoplasm,
                Liquids.ozone, Liquids.hydrogen, Liquids.nitrogen, Liquids.cyanogen}) {
            if (l.hidden) continue;
            ObjectMap<Integer, Cons<Liquid>> cons = getEntries(l, size);
            liquid(cons, l.name, l.color, l.explosiveness, l.flammability, l.heatCapacity, l.viscosity, l.temperature);
        }

        for (Item item : new Item[]{Items.scrap, Items.copper, Items.lead, Items.graphite, Items.coal, Items.titanium, Items.thorium, Items.silicon, Items.plastanium,
                Items.phaseFabric, Items.surgeAlloy, Items.sporePod, Items.sand, Items.blastCompound, Items.pyratite, Items.metaglass,
                Items.beryllium, Items.tungsten, Items.oxide, Items.carbide, Items.fissileMatter, Items.dormantCyst}) {
            if (item.hidden) continue;
            ObjectMap<Integer, Cons<Item>> cons = getEntries(item, size);
            item(cons, item.name, item.color, item.explosiveness, item.flammability, item.cost, item.radioactivity, item.charge, item.healthScaling);
        }
        Draw.color();
    }

    private static ObjectMap<Integer, Cons<Item>> getEntries(Item item, int size) {
        ObjectMap<Integer, Cons<Item>> cons = new ObjectMap<>();
        for (int i = 1; i < 10; i++) {
            int finalI = i;
            cons.put(i, it -> {
                PixmapRegion base = atlas.getPixmap(item.uiIcon);
                Pixmap mix = base.crop();
                AtlasRegion number = atlas.find(name("number-" + finalI));
                if (number.found()) {
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
        for (int i = 1; i < 10; i++) {
            int finalI = i;
            cons.put(i, ld -> {
                PixmapRegion base = atlas.getPixmap(liquid.uiIcon);
                Pixmap mix = base.crop();
                AtlasRegion number = atlas.find(name("number-" + finalI));
                if (number.found()) {
                    PixmapRegion region = TextureAtlas.blankAtlas().getPixmap(number);

                    mix.draw(region.pixmap, region.x, region.y, region.width, region.height, 0, base.height - size, size, size, false, true);
                }

                ld.uiIcon = ld.fullIcon = new TextureRegion(new Texture(mix));
            });
        }
        return cons;
    }

    public static ImageButton selfStyleImageButton(Drawable imageUp, ImageButtonStyle is, Runnable listener) {
        ImageButton ib = new ImageButton(new ImageButtonStyle(null, null, null, imageUp, null, null));
        ImageButtonStyle style = new ImageButtonStyle(is);
        style.imageUp = imageUp;
        ib.setStyle(style);
        if (listener != null) ib.changed(listener);
        return ib;
    }

    public static void drawTiledFramesBar(float w, float h, float x, float y, Liquid liquid, float alpha) {
        TextureRegion region = renderer.fluidFrames[liquid.gas ? 1 : 0][liquid.getAnimationFrame()];

        Draw.color(liquid.color, liquid.color.a * alpha);
        Draw.rect(region, x + w / 2f, y + h / 2f, w, h);
        Draw.color();
    }

    public static boolean friendly(Liquid l) {
        return l.effect != StatusEffects.none && l.effect.damage <= 0.1f && (l.effect.damage < -0.01f || l.effect.healthMultiplier > 1.01f || l.effect.damageMultiplier > 1.01f);
    }

    public static Bullet nearestBullet(float x, float y, float range, Boolf<Bullet> boolf) {
        result = null;
        cdist = range;
        Tmp.r1.setCentered(x, y, range * 2);
        Groups.bullet.intersect(Tmp.r1.x, Tmp.r1.y, Tmp.r1.width, Tmp.r1.height, b -> {
            float dst = b.dst(x, y);
            if (boolf.get(b) && b.within(x, y, range + b.hitSize) && (result == null || dst < cdist)) {
                result = b;
                cdist = dst;
            }
        });

        return (Bullet) result;
    }

    public static float angleDistSigned(float a, float b) {
        a = (a + 360f) % 360f;
        b = (b + 360f) % 360f;

        float d = Math.abs(a - b) % 360f;
        int sign = (a - b >= 0f && a - b <= 180f) || (a - b <= -180f && a - b >= -360f) ? 1 : -1;

        return (d > 180f ? 360f - d : d) * sign;
    }

    public static float angleDistSigned(float a, float b, float start) {
        float dst = angleDistSigned(a, b);
        if (Math.abs(dst) > start) {
            return dst > 0 ? dst - start : dst + start;
        }

        return 0f;
    }

    public static float angleDist(float a, float b) {
        float d = Math.abs(a - b) % 360f;
        return (d > 180f ? 360f - d : d);
    }

    public static void shotgun(int points, float spacing, float offset, Floatc cons) {
        for (int i = 0; i < points; i++) {
            cons.get(i * spacing - (points - 1) * spacing / 2f + offset);
        }
    }

    public static float clampedAngle(float angle, float relative, float limit) {
        if (limit >= 180) return angle;
        if (limit <= 0) return relative;
        float dst = angleDistSigned(angle, relative);
        if (Math.abs(dst) > limit) {
            float val = dst > 0 ? dst - limit : dst + limit;
            return (angle - val) % 360f;
        }
        return angle;
    }

    public static void shotgunRange(int points, float range, float angle, Floatc cons) {
        if (points <= 1) {
            cons.get(angle);
            return;
        }

        for (int i = 0; i < points; i++) {
            float in = Mathf.lerp(-range, range, i / (points - 1f));
            cons.get(in + angle);
        }
    }

    public static float[] castConeTile(float wx, float wy, float range, float angle, float cone, int rays, Cons2<Building, Tile> consBuilding, Boolf<Tile> insulator) {
        return castConeTile(wx, wy, range, angle, cone, consBuilding, insulator, new float[rays]);
    }

    public static float[] castConeTile(float wx, float wy, float range, float angle, float cone, Cons2<Building, Tile> consBuilding, Boolf<Tile> insulator, float[] ref) {
        collidedBlocks.clear();
        idx = 0;
        float expand = 3;
        rect.setCentered(wx, wy, expand);

        shotgunRange(3, cone, angle, con -> {
            tV.trns(con, range).add(wx, wy);
            rectAlt.setCentered(tV.x, tV.y, expand);
            rect.merge(rectAlt);
        });

        if (insulator != null) {
            shotgunRange(ref.length, cone, angle, con -> {
                tV.trns(con, range).add(wx, wy);
                ref[idx] = range * range;
                World.raycastEachWorld(wx, wy, tV.x, tV.y, (x, y) -> {
                    Tile tile = world.tile(x, y);
                    if (tile != null && insulator.get(tile)) {
                        ref[idx] = Mathf.dst2(wx, wy, x * tilesize, y * tilesize);
                        return true;
                    }
                    return false;
                });
                idx++;
            });
        }

        int tx = Mathf.round(rect.x / tilesize);
        int ty = Mathf.round(rect.y / tilesize);
        int tw = tx + Mathf.round(rect.width / tilesize);
        int th = ty + Mathf.round(rect.height / tilesize);
        for (int x = tx; x <= tw; x++) {
            for (int y = ty; y <= th; y++) {
                float ofX = (x * tilesize) - wx, ofY = (y * tilesize) - wy;
                int angIdx = Mathf.clamp(Mathf.round(((angleDistSigned(Angles.angle(ofX, ofY), angle) + cone) / (cone * 2f)) * (ref.length - 1)), 0, ref.length - 1);
                float dst = ref[angIdx];
                float dst2 = Mathf.dst2(ofX, ofY);
                if (dst2 < dst && dst2 < range * range && angleDist(Angles.angle(ofX, ofY), angle) < cone) {
                    Tile tile = world.tile(x, y);
                    Building building = null;
                    if (tile != null) {
                        Building b = world.build(x, y);
                        if (b != null && !collidedBlocks.contains(b.id)) {
                            building = b;
                            collidedBlocks.add(b.id);
                        }

                        consBuilding.get(building, tile);
                    }
                }
            }
        }

        collidedBlocks.clear();
        return ref;
    }

    public static void castCone(float wx, float wy, float range, float angle, float cone, Cons4<Tile, Building, Float, Float> consTile) {
        castCone(wx, wy, range, angle, cone, consTile, null);
    }

    public static void castCone(float wx, float wy, float range, float angle, float cone, Cons3<Unit, Float, Float> consUnit) {
        castCone(wx, wy, range, angle, cone, null, consUnit);
    }

    public static void castCone(float wx, float wy, float range, float angle, float cone, Cons4<Tile, Building, Float, Float> consTile, Cons3<Unit, Float, Float> consUnit) {
        collidedBlocks.clear();
        float expand = 3;
        float rangeSquare = range * range;
        if (consTile != null) {
            rect.setCentered(wx, wy, expand);
            for (int i = 0; i < 3; i++) {
                float angleC = (-1 + i) * cone + angle;
                tV.trns(angleC, range).add(wx, wy);
                rectAlt.setCentered(tV.x, tV.y, expand);
                rect.merge(rectAlt);
            }

            int tx = Mathf.round(rect.x / tilesize);
            int ty = Mathf.round(rect.y / tilesize);
            int tw = tx + Mathf.round(rect.width / tilesize);
            int th = ty + Mathf.round(rect.height / tilesize);
            for (int x = tx; x <= tw; x++) {
                for (int y = ty; y <= th; y++) {
                    float temp = Angles.angle(wx, wy, x * tilesize, y * tilesize);
                    float tempDst = Mathf.dst(x * tilesize, y * tilesize, wx, wy);
                    if (tempDst >= rangeSquare || !Angles.within(temp, angle, cone)) continue;

                    Tile other = world.tile(x, y);
                    if (other == null) continue;
                    if (!collidedBlocks.contains(other.pos())) {
                        float dst = 1f - tempDst / range;
                        float anDst = 1f - Angles.angleDist(temp, angle) / cone;
                        consTile.get(other, other.build, dst, anDst);
                        collidedBlocks.add(other.pos());
                    }
                }
            }
        }

        if (consUnit != null) {
            Groups.unit.intersect(wx - range, wy - range, range * 2f, range * 2f, e -> {
                float temp = Angles.angle(wx, wy, e.x, e.y);
                float tempDst = Mathf.dst(e.x, e.y, wx, wy);
                if (tempDst >= rangeSquare || !Angles.within(temp, angle, cone)) return;

                float dst = 1f - tempDst / range;
                float anDst = 1f - Angles.angleDist(temp, angle) / cone;
                consUnit.get(e, dst, anDst);
            });
        }
    }

    public static float[] castCircle(float wx, float wy, float range, int rays, Boolf<Building> filter, Cons<Building> cons, Boolf<Tile> insulator) {
        collidedBlocks.clear();
        float[] cast = new float[rays];

        for (int i = 0; i < cast.length; i++) {
            cast[i] = range;
            float ang = i * (360f / cast.length);
            tV.trns(ang, range).add(wx, wy);
            int s = i;

            World.raycastEachWorld(wx, wy, tV.x, tV.y, (cx, cy) -> {
                Tile t = world.tile(cx, cy);
                if (t != null && t.block() != null && insulator.get(t)) {
                    float dst = t.dst(wx, wy);
                    cast[s] = dst;
                    return true;
                }

                return false;
            });
        }

        indexer.allBuildings(wx, wy, range, build -> {
            if (!filter.get(build)) return;
            float ang = Angles.angle(wx, wy, build.x, build.y);
            float dst = build.dst2(wx, wy) - ((build.hitSize() * build.hitSize()) / 2f);

            float d = cast[Mathf.mod(Mathf.round((ang % 360f) / (360f / cast.length)), cast.length)];
            if (dst <= d * d) cons.get(build);
        });

        return cast;
    }

    public static void collideLineRawEnemyRatio(Team team, float x, float y, float x2, float y2, float width, Boolf3<Building, Float, Boolean> buildingCons, Boolf2<Unit, Float> unitCons, Floatc2 effectHandler) {
        float minRatio = 0.05f;
        collideLineRawEnemy(team, x, y, x2, y2, width, (building, direct) -> {
            float size = (building.block.size * tilesize / 2f);
            float dst = Mathf.clamp(1f - ((Intersector.distanceSegmentPoint(x, y, x2, y2, building.x, building.y) - width) / size), minRatio, 1f);
            return buildingCons.get(building, dst, direct);
        }, unit -> {
            float size = (unit.hitSize / 2f);
            float dst = Mathf.clamp(1f - ((Intersector.distanceSegmentPoint(x, y, x2, y2, unit.x, unit.y) - width) / size), minRatio, 1f);
            return unitCons.get(unit, dst);
        }, effectHandler, true);
    }

    public static void collideLineRawEnemy(Team team, float x, float y, float x2, float y2, float width, boolean hitTiles, boolean hitUnits, boolean stopSort, HitHandler handler) {
        collideLineRaw(x, y, x2, y2, width, width, b -> b.team != team, u -> u.team != team, hitTiles, hitUnits, h -> h.dst2(x, y), handler, stopSort);
    }

    public static void collideLineRawEnemy(Team team, float x, float y, float x2, float y2, Boolf2<Building, Boolean> buildingCons, Cons<Unit> unitCons, Floatc2 effectHandler, boolean stopSort) {
        collideLineRaw(x, y, x2, y2, 3f, b -> b.team != team, u -> u.team != team, buildingCons, unitCons, healthc -> healthc.dst2(x, y), effectHandler, stopSort);
    }

    public static void collideLineRawEnemy(Team team, float x, float y, float x2, float y2, float width, Boolf2<Building, Boolean> buildingCons, Boolf<Unit> unitCons, Floatc2 effectHandler, boolean stopSort) {
        collideLineRaw(x, y, x2, y2, width, width, b -> b.team != team, u -> u.team != team, buildingCons, unitCons, healthc -> healthc.dst2(x, y), effectHandler, stopSort);
    }

    public static void collideLineRawEnemy(Team team, float x, float y, float x2, float y2, float width, Boolf2<Building, Boolean> buildingCons, Boolf<Unit> unitCons, Floatf<Healthc> sort, Floatc2 effectHandler, boolean stopSort) {
        collideLineRaw(x, y, x2, y2, width, width, b -> b.team != team, u -> u.team != team, buildingCons, unitCons, sort, effectHandler, stopSort);
    }

    public static void collideLineRawEnemy(Team team, float x, float y, float x2, float y2, float unitWidth, float tileWidth, Boolf2<Building, Boolean> buildingCons, Boolf<Unit> unitCons, Floatc2 effectHandler, boolean stopSort) {
        collideLineRaw(x, y, x2, y2, unitWidth, tileWidth, b -> b.team != team, u -> u.team != team, buildingCons, unitCons, healthc -> healthc.dst2(x, y), effectHandler, stopSort);
    }

    public static void collideLineRawEnemy(Team team, float x, float y, float x2, float y2, Boolf2<Building, Boolean> buildingCons, Boolf<Unit> unitCons, Floatc2 effectHandler, boolean stopSort) {
        collideLineRaw(x, y, x2, y2, 3f, b -> b.team != team, u -> u.team != team, buildingCons, unitCons, healthc -> healthc.dst2(x, y), effectHandler, stopSort);
    }

    public static void collideLineRawEnemy(Team team, float x, float y, float x2, float y2, Boolf2<Building, Boolean> buildingCons, Cons<Unit> unitCons, Floatf<Healthc> sort, Floatc2 effectHandler) {
        collideLineRaw(x, y, x2, y2, 3f, b -> b.team != team, u -> u.team != team, buildingCons, unitCons, sort, effectHandler);
    }

    public static void collideLineRawEnemy(Team team, float x, float y, float x2, float y2, float width, Boolf<Healthc> pred, Boolf2<Building, Boolean> buildingCons, Boolf<Unit> unitCons, Floatc2 effectHandler, boolean stopSort) {
        collideLineRaw(x, y, x2, y2, width, width, b -> b.team != team && pred.get(b), u -> u.team != team && pred.get(u), buildingCons, unitCons, healthc -> healthc.dst2(x, y), effectHandler, stopSort);
    }

    public static void collideLineRaw(float x, float y, float x2, float y2, float unitWidth, Boolf<Building> buildingFilter, Boolf<Unit> unitFilter, Boolf2<Building, Boolean> buildingCons, Cons<Unit> unitCons, Floatf<Healthc> sort, Floatc2 effectHandler) {
        collideLineRaw(x, y, x2, y2, unitWidth, buildingFilter, unitFilter, buildingCons, unitCons, sort, effectHandler, false);
    }

    public static void collideLineRaw(float x, float y, float x2, float y2, float unitWidth, Boolf<Building> buildingFilter, Boolf<Unit> unitFilter, Boolf2<Building, Boolean> buildingCons, Cons<Unit> unitCons, Floatf<Healthc> sort, Floatc2 effectHandler, boolean stopSort) {
        collideLineRaw(x, y, x2, y2, unitWidth, buildingFilter, unitFilter, buildingCons, unitCons == null ? null : unit -> {
            unitCons.get(unit);
            return false;
        }, sort, effectHandler, stopSort);
    }

    public static void collideLineRaw(float x, float y, float x2, float y2, float unitWidth, Boolf<Building> buildingFilter, Boolf<Unit> unitFilter, Boolf2<Building, Boolean> buildingCons, Boolf<Unit> unitCons, Floatf<Healthc> sort, Floatc2 effectHandler, boolean stopSort) {
        collideLineRaw(x, y, x2, y2, unitWidth, 0f, buildingFilter, unitFilter, buildingCons, unitCons, sort, effectHandler, stopSort);
    }

    public static void collideLineRaw(float x, float y, float x2, float y2, float unitWidth, float tileWidth, Boolf<Building> buildingFilter, Boolf<Unit> unitFilter, Boolf2<Building, Boolean> buildingCons, Boolf<Unit> unitCons, Floatf<Healthc> sort, Floatc2 effectHandler, boolean stopSort) {
        collideLineRaw(x, y, x2, y2, unitWidth, tileWidth, buildingFilter, unitFilter, buildingCons != null, unitCons != null, sort, (ex, ey, ent, direct) -> {
            boolean hit = false;
            if (unitCons != null && direct && ent instanceof Unit) {
                hit = unitCons.get((Unit)ent);
            } else if (buildingCons != null && ent instanceof Building) {
                hit = buildingCons.get((Building)ent, direct);
            }

            if (effectHandler != null && direct) effectHandler.get(ex, ey);
            return hit;
            }, stopSort
        );
    }

    public static void collideLineRaw(float x, float y, float x2, float y2, float unitWidth, float tileWidth, Boolf<Building> buildingFilter, Boolf<Unit> unitFilter, boolean hitTile, boolean hitUnit, Floatf<Healthc> sort, HitHandler hitHandler, boolean stopSort) {
        hitEffects.clear();
        lineCast.clear();
        lineCastNext.clear();
        collidedBlocks.clear();

        tV.set(x2, y2);
        if (hitTile) {
            collideLineCollided.clear();
            Runnable cast = () -> {
                hitB = false;
                lineCast.each(i -> {
                    int tx = Point2.x(i), ty = Point2.y(i);
                    Building build = world.build(tx, ty);

                    boolean hit = false;
                    if (build != null && (buildingFilter == null || buildingFilter.get(build)) && collidedBlocks.add(build.pos())) {
                        if (sort == null) {
                            hit = hitHandler.get(tx * tilesize, ty * tilesize, build, true);
                        } else {
                            hit = hitHandler.get(tx * tilesize, ty * tilesize, build, false);
                            Hit he = Pools.obtain(Hit.class, Hit::new);
                            he.ent = build;
                            he.x = tx * tilesize;
                            he.y = ty * tilesize;

                            hitEffects.add(he);
                        }

                        if (hit && !hitB) {
                            tV.trns(Angles.angle(x, y, x2, y2), Mathf.dst(x, y, build.x, build.y)).add(x, y);
                            hitB = true;
                        }
                    }

                    Vec2 segment = Intersector.nearestSegmentPoint(x, y, tV.x, tV.y, tx * tilesize, ty * tilesize, tV2);
                    if (!hit && tileWidth > 0f) {
                        for (Point2 p : Geometry.d8) {
                            int newX = (p.x + tx);
                            int newY = (p.y + ty);
                            boolean within = !hitB || Mathf.within(x / tilesize, y / tilesize, newX, newY, tV.dst(x, y) / tilesize);
                            if (segment.within(newX * tilesize, newY * tilesize, tileWidth) && collideLineCollided.within(newX, newY) && !collideLineCollided.get(newX, newY) && within) {
                                lineCastNext.add(Point2.pack(newX, newY));
                                collideLineCollided.set(newX, newY, true);
                            }
                        }
                    }
                });

                lineCast.clear();
                lineCast.addAll(lineCastNext);
                lineCastNext.clear();
            };

            World.raycastEachWorld(x, y, x2, y2, (cx, cy) -> {
                if (collideLineCollided.within(cx, cy) && !collideLineCollided.get(cx, cy)) {
                    lineCast.add(Point2.pack(cx, cy));
                    collideLineCollided.set(cx, cy, true);
                }

                cast.run();
                return hitB;
            });

            while (!lineCast.isEmpty()) cast.run();
        }

        if (hitUnit) {
            rect.setPosition(x, y).setSize(tV.x - x, tV.y - y);

            if (rect.width < 0) {
                rect.x += rect.width;
                rect.width *= -1;
            }

            if (rect.height < 0) {
                rect.y += rect.height;
                rect.height *= -1;
            }

            rect.grow(unitWidth * 2f);
            Groups.unit.intersect(rect.x, rect.y, rect.width, rect.height, unit -> {
                if (unitFilter == null || unitFilter.get(unit)) {
                    unit.hitbox(hitRect);
                    hitRect.grow(unitWidth * 2);

                    Vec2 vec = Geometry.raycastRect(x, y, tV.x, tV.y, hitRect);

                    if (vec != null) {
                        float scl = (unit.hitSize - unitWidth) / unit.hitSize;
                        vec.sub(unit).scl(scl).add(unit);
                        if (sort == null) {
                            hitHandler.get(vec.x, vec.y, unit, true);
                        } else {
                            Hit he = Pools.obtain(Hit.class, Hit::new);
                            he.ent = unit;
                            he.x = vec.x;
                            he.y = vec.y;
                            hitEffects.add(he);
                        }
                    }
                }
            });
        }

        if (sort != null) {
            hit = false;
            hitEffects.sort(he -> sort.get(he.ent)).each(he -> {
                if (!stopSort || !hit) {
                    hit = hitHandler.get(he.x, he.y, he.ent, true);
                }

                Pools.free(he);
            });
        }

        hitEffects.clear();
    }

    /**
     * Casts forward in a line.
     * @return the first encountered model.
     * There's an issue with the one in 126.2, which I fixed in a pr. This can be removed after the next Mindustry release.
     */
    public static Healthc linecast(Bullet hitter, float x, float y, float angle, float length) {
        tV.trns(angle, length);

        tmpBuilding = null;

        if (hitter.type.collidesGround) {
            World.raycastEachWorld(x, y, x + tV.x, y + tV.y, (cx, cy) -> {
                Building tile = world.build(cx, cy);
                if (tile != null && tile.team != hitter.team) {
                    tmpBuilding = tile;
                    return true;
                }
                return false;
            });
        }

        rect.setPosition(x, y).setSize(tV.x, tV.y);
        float x2 = tV.x + x, y2 = tV.y + y;

        if (rect.width < 0) {
            rect.x += rect.width;
            rect.width *= -1;
        }

        if (rect.height < 0) {
            rect.y += rect.height;
            rect.height *= -1;
        }

        float expand = 3f;

        rect.y -= expand;
        rect.x -= expand;
        rect.width += expand * 2;
        rect.height += expand * 2;

        tmpUnit = null;

        Units.nearbyEnemies(hitter.team, rect, e -> {
            if ((tmpUnit != null && e.dst2(x, y) > tmpUnit.dst2(x, y)) || !e.checkTarget(hitter.type.collidesAir, hitter.type.collidesGround)) return;

            e.hitbox(hitRect);
            Rect other = hitRect;
            other.y -= expand;
            other.x -= expand;
            other.width += expand * 2;
            other.height += expand * 2;

            Vec2 vec = Geometry.raycastRect(x, y, x2, y2, other);

            if (vec != null) {
                tmpUnit = e;
            }
        });

        if (tmpBuilding != null && tmpUnit != null) {
            if (Mathf.dst2(x, y, tmpBuilding.getX(), tmpBuilding.getY()) <= Mathf.dst2(x, y, tmpUnit.getX(), tmpUnit.getY())) {
                return tmpBuilding;
            }
        } else if (tmpBuilding != null) {
            return tmpBuilding;
        }

        return tmpUnit;
    }

    static class Hit implements Pool.Poolable {
        Healthc ent;
        float x, y;

        @Override
        public void reset() {
            ent = null;
            x = y = 0f;
        }
    }

    public interface HitHandler {
        boolean get(float x, float y, Healthc ent, boolean direct);
    }

    public static boolean isCrash = false;

    public static void crash() {
        isCrash = true;
    }

    public static byte[] compress(byte[] in) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            DeflaterOutputStream defl = new DeflaterOutputStream(out);
            defl.write(in);
            defl.flush();
            defl.close();

            var o = out.toByteArray();
            out.close();
            return o;
        } catch (Exception e) {
            Log.err(e);
            return null;
        }
    }

    public static byte[] decompress(byte[] in) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InflaterOutputStream infl = new InflaterOutputStream(out);
            infl.write(in);
            infl.flush();
            infl.close();
            var o = out.toByteArray();
            out.close();
            return o;
        } catch (Exception e) {
            Log.err(e);
            System.exit(150);
            return null;
        }
    }

    public static class ExtendedPosition implements Position {
        public float x, y;

        public ExtendedPosition set(float x, float y) {
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
}
