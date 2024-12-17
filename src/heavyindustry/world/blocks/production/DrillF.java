package heavyindustry.world.blocks.production;

import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
import heavyindustry.content.*;
import heavyindustry.gen.*;
import heavyindustry.graphics.*;
import heavyindustry.math.*;
import heavyindustry.world.consumers.*;
import heavyindustry.world.meta.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.units.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.logic.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.meta.*;

import static arc.Core.*;
import static mindustry.Vars.*;

/**
 * A drill bit that can enhance and expand its functionality through an external module.
 */
public abstract class DrillF extends Block {
    protected final ObjectIntMap<Item> oreCount = new ObjectIntMap<>();
    protected final Seq<Item> itemArray = new Seq<>();

    /** output speed in items/sec. */
    public float mineSpeed = 5;
    /** output count once. */
    public int mineCount = 2;
    /** Whether to draw the item this drill is mining. */
    public boolean drawMineItem = false;
    /** yeah, I want to make whitelist. */
    public int mineTier;
    /** Special exemption item that this drill can't mine. */
    public Seq<Item> blockedItem = new Seq<>();

    public TextureRegion baseRegion, topRegion, oreRegion;

    public float powerConsBase;

    /** Chance of displaying the effect. Useful for extremely fast drills. */
    public float updateEffectChance = 0.02f;
    /** Effect randomly played while drilling. */
    public Effect updateEffect = Fx.none;
    /** Effect played when an item is produced. This is colored. */
    public Effect drillEffect = Fx.none;
    /** Drill effect randomness. Block size by default. */
    public float drillEffectRnd = -1f;

    public float maxBoost = 0f;

    /** return variables for countOre. */
    protected int maxOreTileReq = -1;
    protected @Nullable Item returnItem;
    protected int returnCount;

    public DrillF(String name) {
        super(name);
        size = 1;

        update = true;
        solid = true;

        drawCracks = false;

        hasItems = true;
        hasLiquids = false;
        itemCapacity = 40;

        ambientSound = Sounds.drill;
        ambientSoundVolume = 0.018f;

        group = BlockGroup.drills;
        flags = EnumSet.of(BlockFlag.drill);

        drawTeamOverlay = false;

        consumePower(DrillBuildF::getPowerCons);
    }

    @SuppressWarnings("unchecked")
    public <T extends Building> void consumePower(Floatf<T> usage) {
        consume(new ConsumerPowerF((Floatf<Building>) usage));
    }

    //TODO Is this a good idea to begin with?
    public float getMineSpeedHardnessMul(Item item) {
        if (item == null) return 0f;
        return switch (item.hardness) {
            case 0 -> 2f;
            case 1, 2 -> 1.5f;
            case 3, 4 -> 1f;
            case 5, 6 -> 0.8f;
            default -> 0.6f;
        };
    }

    @Override
    public void init() {
        super.init();
        if (drillEffectRnd < 0) drillEffectRnd = size;
        if (Mathm.confine(maxOreTileReq, 0, size * size)) maxOreTileReq = size * size;
    }

    @Override
    public void load() {
        super.load();
        baseRegion = atlas.find(name + "-bottom");
        topRegion = atlas.find(name + "-top");
        oreRegion = atlas.find(name + "-ore");
    }

    @Override
    public TextureRegion[] icons() {
        return teamRegion.found() ? new TextureRegion[]{baseRegion, topRegion, teamRegions[Team.sharded.id]} : new TextureRegion[]{baseRegion, topRegion};
    }

    @Override
    public void setBars() {
        super.setBars();
        addBar("drillSpeed", (DrillBuildF tile) -> new Bar(() -> bundle.format("bar.drillspeed", Strings.fixed(tile.getMineSpeed(), 2)), () -> Pal.ammo, () -> tile.warmup));
    }

    public float mineInterval() {
        return (60f / mineSpeed) * mineCount;
    }

    @Override
    public void setStats() {
        super.setStats();

        stats.add(Stat.powerUse, powerConsBase, StatUnit.powerSecond);
        stats.add(Stat.drillSpeed, mineSpeed, StatUnit.itemsSecond);
        stats.add(Stat.drillTier, table -> {
            table.row();
            table.table(c -> {
                int i = 0;
                for (Block block : content.blocks()) {
                    if (block.itemDrop == null || (blockedItem.contains(block.itemDrop) || block.itemDrop.hardness > mineTier))
                        continue;
                    if ((block instanceof Prop) || (block instanceof TallBlock) || (block instanceof Floor floor) && floor.wallOre)
                        continue;

                    c.table(Styles.grayPanel, b -> {
                        b.image(block.uiIcon).size(40).pad(10f).left().scaling(Scaling.fit);
                        b.table(info -> {
                            info.left();
                            info.add(block.localizedName).left().row();
                            info.add(block.itemDrop.emoji()).left();
                        }).grow();
                        b.add(Strings.autoFixed(mineSpeed * getMineSpeedHardnessMul(block.itemDrop), 1) + StatUnit.perSecond.localized())
                                .right().pad(10f).padRight(15f).color(Color.lightGray);
                    }).growX().pad(5);
                    if (++i % 2 == 0) c.row();
                }
            }).growX().colspan(table.getColumns());
        });
        stats.add(HIStat.maxBoostPercent, bundle.get("stat.hi-f-percent"), Strings.autoFixed(maxBoost * 100, 0));
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        if (isMultiblock()) {
            for (Tile other : tile.getLinkedTilesAs(this, tempTiles)) {
                if (canMine(other)) {
                    return true;
                }
            }
            return false;
        } else {
            return canMine(tile);
        }
    }

    @Override
    public void drawPlanConfigTop(BuildPlan plan, Eachable<BuildPlan> list) {
        if (!plan.worldContext) return;
        Tile tile = plan.tile();
        if (tile == null) return;

        countOre(tile);
        if (returnItem == null || !drawMineItem) return;
        Draw.color(returnItem.color);
        Draw.rect(oreRegion, plan.drawx(), plan.drawy());
        Draw.color();
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);

        Tile tile = world.tile(x, y);
        if (tile == null) return;

        countOre(tile);

        if (returnItem != null) {
            String oreCountText = (returnCount < maxOreTileReq ? "[sky](" : "[green](") + returnCount + "/" + maxOreTileReq + ")[] " + Strings.autoFixed(mineSpeed * Mathf.clamp((float) returnCount / maxOreTileReq) * getMineSpeedHardnessMul(returnItem), 1) + "/s";
            float width = drawPlaceText(oreCountText, x, y, valid);
            float dx = x * tilesize + offset - width / 2f - 4f, dy = y * tilesize + offset + size * tilesize / 2f + 5, s = iconSmall / 4f;
            Draw.mixcol(Color.darkGray, 1f);
            Draw.rect(returnItem.fullIcon, dx, dy - 1, s, s);
            Draw.reset();
            Draw.rect(returnItem.fullIcon, dx, dy, s, s);
        } else {
            Tile to = tile.getLinkedTilesAs(this, tempTiles).find(t -> t.drop() != null && t.drop().hardness > mineTier || blockedItem.contains(t.drop()));
            Item item = to == null ? null : to.drop();
            if (item != null) {
                drawPlaceText(bundle.get("bar.drilltierreq"), x, y, valid);
            } else {
                drawPlaceText("No Ores", x, y, valid);
            }
        }
    }

    protected void countOre(Tile tile) {
        returnItem = null;
        returnCount = 0;

        oreCount.clear();
        itemArray.clear();

        for (Tile other : tile.getLinkedTilesAs(this, tempTiles)) {
            if (canMine(other)) {
                oreCount.increment(getDrop(other), 0, 1);
            }
        }

        for (Item item : oreCount.keys()) {
            itemArray.add(item);
        }

        itemArray.sort((item1, item2) -> {
            int type = Boolean.compare(!item1.lowPriority, !item2.lowPriority);
            if (type != 0) return type;
            int amounts = Integer.compare(oreCount.get(item1, 0), oreCount.get(item2, 0));
            if (amounts != 0) return amounts;
            return Integer.compare(item1.id, item2.id);
        });

        if (itemArray.size == 0) {
            return;
        }

        returnItem = itemArray.peek();
        returnCount = Math.min(oreCount.get(itemArray.peek(), 0), maxOreTileReq);
    }

    protected boolean canMine(Tile tile) {
        if (tile == null || tile.block().isStatic()) return false;
        Item drops = tile.drop();
        return drops != null && (!blockedItem.contains(drops) || drops.hardness > mineTier);
    }

    protected Item getDrop(Tile tile) {
        return tile.drop();
    }

    public abstract class DrillBuildF extends Building implements Drillc {
        public float progress;

        //only for visual
        public float warmup;

        public int dominantItems;
        public Item dominantItem;
        public Item convertItem;

        public boolean coreSend = false;
        public float boostMul = 1f;
        public float boostFinalMul = 1f;

        //(base * multiplier) + extra
        public float powerConsMul = 1f;
        public float powerConsExtra = 0f;
        public Seq<DrillModulec> modules = new Seq<>();

        @Override
        public float progress() {
            return Mathf.clamp(progress / getMineSpeed());
        }

        @Override
        public float warmup() {
            return warmup;
        }

        @Override
        public float maxBoost() {
            return maxBoost;
        }

        @Override
        public void onProximityUpdate() {
            super.onProximityUpdate();

            countOre(tile);
            dominantItem = returnItem;
            dominantItems = returnCount;

            updateDrillModule();
        }

        public @Nullable Item outputItem() {
            return dominantItem != null ? convertItem != null ? convertItem : dominantItem : null;
        }

        @Override
        public void updateTile() {
            tryDump();
            updateProgress();
            updateOutput();
            updateEffect();
        }

        protected void updateOutput() {
            if (progress > mineInterval()) {
                int outCount = (int) (progress / mineInterval()) * mineCount;
                for (int i = 0; i < outCount; i++) {
                    if (outputItem() != null) {
                        if (coreSend && core() != null && core().acceptItem(this, outputItem())) {
                            core().handleItem(this, outputItem());
                        } else {
                            offload(outputItem());
                        }
                    }
                }
                progress %= mineInterval();

                if (wasVisible && Mathf.chanceDelta(updateEffectChance * warmup))
                    drillEffect.at(x + Mathf.range(drillEffectRnd), y + Mathf.range(drillEffectRnd), dominantItem.color);
            }
        }

        @Override
        public void drawSelect() {
            super.drawSelect();

            if (outputItem() != null) {
                float dx = x - size * tilesize / 2f, dy = y + size * tilesize / 2f, s = iconSmall / 4f;
                Draw.mixcol(Color.darkGray, 1f);
                Draw.rect(outputItem().fullIcon, dx, dy - 1, s, s);
                Draw.reset();
                Draw.rect(outputItem().fullIcon, dx, dy, s, s);
            }

            Drawf.selected(this, Pal.accent);
            for (DrillModulec module : modules) {
                Drawn.selected(module, Pal.accent);
            }
        }

        @Override
        public void draw() {
            Draw.rect(baseRegion, x, y);
            if (warmup > 0f) {
                drawMining();
            }

            Draw.z(Layer.blockOver - 4f);

            Draw.rect(topRegion, x, y);
            if (outputItem() != null && drawMineItem) {
                Draw.color(dominantItem.color);
                Draw.rect(oreRegion, x, y);
                Draw.color();
            }

            drawTeamTop();
        }

        public void drawMining() {
        }

        protected void tryDump() {
            if (timer(timerDump, dumpTime)) {
                if (outputItem() != null) {
                    if (coreSend && items.has(outputItem()) && core() != null && core().acceptItem(this, outputItem())) {
                        items.remove(outputItem(), 1);
                        core().handleItem(this, outputItem());
                    } else {
                        dump(items.has(outputItem()) ? outputItem() : null);
                    }
                }
            }
        }

        protected void updateEffect() {
            if (!headless && warmup > 0.8f && efficiency > 0 && outputItem() != null && HIFx.globalEffectRand.chance(updateEffectChance * boostScl())) {
                updateEffect.at(x + HIFx.globalEffectRand.range(size * 3.6f), y + HIFx.globalEffectRand.range(size * 3.6f), outputItem().color);
            }
        }

        protected void resetModule() {
            boostMul = 1f;
            boostFinalMul = 1f;
            powerConsMul = 1f;
            powerConsExtra = 0f;
            coreSend = false;
            convertItem = null;
            modules.clear();
        }

        @Override
        public void remove() {
            super.remove();
            for (DrillModulec module : modules) {
                module.drillBuild(null);
            }
        }

        public void updateDrillModule() {
            resetModule();
            for (Building building : proximity) {
                if (building instanceof DrillModulec module) {
                    if (module.canApply(this)) {
                        module.drillBuild(this);
                        modules.add(module);
                        module.apply(this);
                    }
                }
            }
        }

        //notice in tick
        public float getPowerCons() {
            return items.total() < itemCapacity ? ((powerConsBase * powerConsMul + powerConsExtra) / 60f) : 0f;
        }

        protected void updateProgress() {
            if (items.total() < itemCapacity) {
                progress += edelta() * Mathf.clamp((float) dominantItems / maxOreTileReq) * boostScl();
            }
            if (!headless) {
                if (items.total() < itemCapacity && dominantItems > 0 && efficiency > 0) {
                    warmup = Mathf.approachDelta(warmup, efficiency, 0.01f);
                } else {
                    warmup = Mathf.approachDelta(warmup, 0, 0.01f);
                }
            }
        }

        public float boostScl() {
            return boostMul * boostFinalMul * getMineSpeedHardnessMul(dominantItem);
        }

        protected float getMineSpeed() {
            return Mathf.clamp((float) dominantItems / maxOreTileReq) * boostScl() * mineSpeed * timeScale() * efficiency();
        }

        @Override
        public Object senseObject(LAccess sensor) {
            if (sensor == LAccess.firstItem) return dominantItem;
            return super.senseObject(sensor);
        }

        @Override
        public void write(Writes write) {
            super.write(write);
            write.f(progress);
            write.f(warmup);
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            progress = read.f();
            warmup = read.f();
        }

        @Override
        public int dominantItems() {
            return dominantItems;
        }

        @Override
        public Item dominantItem() {
            return dominantItem;
        }

        @Override
        public Item convertItem() {
            return convertItem;
        }

        @Override
        public boolean coreSend() {
            return coreSend;
        }

        @Override
        public float boostMul() {
            return boostMul;
        }

        @Override
        public float boostFinalMul() {
            return boostFinalMul;
        }

        @Override
        public float powerConsMul() {
            return powerConsMul;
        }

        @Override
        public float powerConsExtra() {
            return powerConsExtra;
        }

        @Override
        public Seq<DrillModulec> modules() {
            return modules;
        }

        @Override
        public void maxBoost(float value) {
            maxBoost = value;
        }

        @Override
        public void dominantItems(int value) {
            dominantItems = value;
        }

        @Override
        public void dominantItem(Item value) {
            dominantItem = value;
        }

        @Override
        public void convertItem(Item value) {
            convertItem = value;
        }

        @Override
        public void coreSend(boolean value) {
            coreSend = value;
        }

        @Override
        public void boostMul(float value) {
            boostMul = value;
        }

        @Override
        public void boostFinalMul(float value) {
            boostFinalMul = value;
        }

        @Override
        public void powerConsMul(float value) {
            powerConsMul = value;
        }

        @Override
        public void powerConsExtra(float value) {
            powerConsExtra = value;
        }

        @Override
        public void modules(Seq<DrillModulec> value) {
            modules = value;
        }
    }
}
