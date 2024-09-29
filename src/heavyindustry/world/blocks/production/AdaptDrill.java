package heavyindustry.world.blocks.production;

import arc.*;
import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
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
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.environment.StaticWall;
import mindustry.world.meta.*;
import heavyindustry.world.consumers.*;
import heavyindustry.world.meta.*;
import heavyindustry.ui.*;

import static mindustry.Vars.*;

/**
 * A drill bit that can enhance and expand its functionality through an external module.
 * @author LaoHuaJi
 * @author Wisadell
 */
public class AdaptDrill extends Block {
    final static Rand globalEffectRand = new Rand(0);

    /** output speed in items/sec. */
    public float mineSpeed = 5;
    /** output count once. */
    public int mineCount = 2;
    /** Whether to draw the item this drill is mining. */
    public boolean drawMineItem = false;

    /** yeah i want to make whitelist. */
    public int mineTier;
    /** Special exemption item that this drill can't mine. */
    public Seq<Item> blockedItem = new Seq<>();

    /** return variables for countOre. */
    protected int maxOreTileReq = -1;

    protected @Nullable Item returnItem;
    protected int returnCount;
    protected final ObjectIntMap<Item> oreCount = new ObjectIntMap<>();
    protected final Seq<Item> itemArray = new Seq<>();

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

    public AdaptDrill(String name) {
        super(name);
        size = 1;

        update = true;
        solid = true;

        drawCracks = false;

        hasItems = true;
        hasLiquids = false;
        itemCapacity = 40;

        canOverdrive = false;

        ambientSound = Sounds.drill;
        ambientSoundVolume = 0.018f;

        group = BlockGroup.drills;
        flags = EnumSet.of(BlockFlag.drill);

        drawTeamOverlay = false;

        consumePower(AdaptDrillBuild::getPowerCons);
    }

    @SuppressWarnings("unchecked")
    public <T extends Building> void consumePower(Floatf<T> usage){
        consume(new AdaptConsumerPower((Floatf<Building>) usage));
    }

    //TODO Is this a good idea to begin with?
    public float getMineSpeedHardnessMul(Item item){
        if (item == null) return 0f;
        if (item.hardness == 0) return 2f;
        if (item.hardness <= 2) return 1.5f;
        if (item.hardness <= 4) return 1f;
        return 0.8f;
    }

    @Override
    public void init() {
        super.init();
        if(drillEffectRnd < 0) drillEffectRnd = size;
        if(maxOreTileReq < 0) maxOreTileReq = size * size;
    }

    @Override
    public void load() {
        super.load();
        baseRegion = Core.atlas.find(name + "-bottom");
        topRegion = Core.atlas.find(name + "-top");
        oreRegion = Core.atlas.find(name + "-ore");
    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{baseRegion, topRegion};
    }

    @Override
    public void setBars(){
        barMap.clear();
        addBar("health", e -> new ExtendBar(Core.bundle.format("bar.hi-health", e.health(), health, Strings.autoFixed(e.healthf() * 100, 0)), Pal.health, e::healthf, Iconc.add + "").blink(Color.white));
        addBar("power", (AdaptDrillBuild e) -> new ExtendBar(
                Core.bundle.format("bar.hi-power-detail", Strings.autoFixed(e.getPowerCons() * 60f, 0), Strings.autoFixed((e.powerConsMul), 1), e.powerConsExtra),
                Pal.powerBar,
                () -> (Mathf.zero(consPower.requestedPower(e)) && e.power.graph.getPowerProduced() + e.power.graph.getBatteryStored() > 0f) ? 1f : e.power.status,
                Iconc.power + ""));
        addBar("outputOre", (AdaptDrillBuild e) -> new ExtendBar(e::getMineInfo, e::getMineColor, () -> 1f, Iconc.settings + ""));
        addBar("drillSpeed", (AdaptDrillBuild e) -> new ExtendBar(
                () -> Core.bundle.format("bar.hi-drill-speed", Strings.autoFixed(e.getMineSpeed(), 2), Strings.autoFixed((e.boostMul - 1) * 100, 1), e.boostFinalMul),
                () -> Pal.ammo,
                () -> e.warmup,
                Iconc.production + ""));
    }

    public float mineInterval(){
        return (60f / mineSpeed) * mineCount;
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(Stat.drillSpeed, mineSpeed, StatUnit.itemsSecond);
        stats.add(Stat.drillTier, table -> {
            table.row();
            table.table(c -> {
                int i = 0;
                for (Block block : content.blocks()){
                    if (block.itemDrop == null || (blockedItem.contains(block.itemDrop) || block.itemDrop.hardness > mineTier)) continue;
                    if (block instanceof StaticWall || (block instanceof Floor && ((Floor) block).wallOre)) continue;//Screen out the wall ore.

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
                    if(++i % 2 == 0) c.row();
                }
            }).growX().colspan(table.getColumns());
        });
        stats.add(HIStat.maxBoostPercent, Core.bundle.get("stat.hi-percent"), Strings.autoFixed(maxBoost * 100, 0));
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        if(isMultiblock()){
            for(Tile other : tile.getLinkedTilesAs(this, tempTiles)){
                if(canMine(other)){
                    return true;
                }
            }
            return false;
        }else{
            return canMine(tile);
        }
    }

    @Override
    public void drawPlanConfigTop(BuildPlan plan, Eachable<BuildPlan> list){
        if(!plan.worldContext) return;
        Tile tile = plan.tile();
        if(tile == null) return;

        countOre(tile);
        if(returnItem == null || !drawMineItem) return;
        Draw.color(returnItem.color);
        Draw.rect(oreRegion, plan.drawx(), plan.drawy());
        Draw.color();
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        Tile tile = world.tile(x, y);
        if(tile == null) return;

        countOre(tile);

        if(returnItem != null){
            String oreCountText = (returnCount < maxOreTileReq? "[sky](": "[heal](") + returnCount + "/" +  maxOreTileReq + ")[] " + Strings.autoFixed(mineSpeed * Mathf.clamp((float) returnCount / maxOreTileReq) * getMineSpeedHardnessMul(returnItem), 1) + "/s";
            float width = drawPlaceText(oreCountText, x, y, valid);
            float dx = x * tilesize + offset - width/2f - 4f, dy = y * tilesize + offset + size * tilesize / 2f + 5, s = iconSmall / 4f;
            Draw.mixcol(Color.darkGray, 1f);
            Draw.rect(returnItem.fullIcon, dx, dy - 1, s, s);
            Draw.reset();
            Draw.rect(returnItem.fullIcon, dx, dy, s, s);
        }else {
            Tile to = tile.getLinkedTilesAs(this, tempTiles).find(t -> t.drop() != null && t.drop().hardness > mineTier || blockedItem.contains(t.drop()));
            Item item = to == null ? null : to.drop();
            if(item != null){
                drawPlaceText(Core.bundle.get("bar.drilltierreq"), x, y, valid);
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

        for(Tile other : tile.getLinkedTilesAs(this, tempTiles)){
            if(canMine(other)){
                oreCount.increment(getDrop(other), 0, 1);
            }
        }

        for(Item item : oreCount.keys()){
            itemArray.add(item);
        }

        itemArray.sort((item1, item2) -> {
            int type = Boolean.compare(!item1.lowPriority, !item2.lowPriority);
            if(type != 0) return type;
            int amounts = Integer.compare(oreCount.get(item1, 0), oreCount.get(item2, 0));
            if(amounts != 0) return amounts;
            return Integer.compare(item1.id, item2.id);
        });

        if(itemArray.size == 0){
            return;
        }

        returnItem = itemArray.peek();
        returnCount = Math.min(oreCount.get(itemArray.peek(), 0), maxOreTileReq);
    }

    protected boolean canMine(Tile tile){
        if(tile == null || tile.block().isStatic()) return false;
        Item drops = tile.drop();
        return drops != null && (!blockedItem.contains(drops) || drops.hardness > mineTier);
    }

    protected Item getDrop(Tile tile){
        return tile.drop();
    }

    public class AdaptDrillBuild extends Building {
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
        public Seq<DrillModule.DrillModuleBuild> modules = new Seq<>();

        public float maxBoost(){
            return maxBoost;
        }

        @Override
        public void onProximityUpdate(){
            super.onProximityUpdate();

            countOre(tile);
            dominantItem = returnItem;
            dominantItems = returnCount;

            updateDrillModule();
        }

        public Item outputItem(){
            return dominantItem != null? convertItem != null? convertItem: dominantItem: null;
        }

        @Override
        public void updateTile() {
            tryDump();
            updateProgress();
            updateOutput();
            updateEffect();
        }

        protected void updateOutput(){
            if (progress > mineInterval()){
                int outCount = (int) (progress / mineInterval()) * mineCount;
                for (int i = 0; i < outCount; i++){
                    if (outputItem() != null){
                        if (coreSend && core() != null && core().acceptItem(this, outputItem())){
                            core().handleItem(this, outputItem());
                        }else {
                            offload(outputItem());
                        }
                    }
                }
                progress %= mineInterval();

                if(wasVisible && Mathf.chanceDelta(updateEffectChance * warmup)) drillEffect.at(x + Mathf.range(drillEffectRnd), y + Mathf.range(drillEffectRnd), dominantItem.color);
            }
        }

        @Override
        public void drawSelect() {
            super.drawSelect();

            if(outputItem() != null){
                float dx = x - size * tilesize / 2f, dy = y + size * tilesize / 2f, s = iconSmall / 4f;
                Draw.mixcol(Color.darkGray, 1f);
                Draw.rect(outputItem().fullIcon, dx, dy - 1, s, s);
                Draw.reset();
                Draw.rect(outputItem().fullIcon, dx, dy, s, s);
            }

            Drawf.selected(this, Pal.accent);
            for (DrillModule.DrillModuleBuild module: modules){
                Drawf.selected(module, Pal.accent);
            }
        }

        @Override
        public void draw() {
            Draw.rect(baseRegion, x, y);
            if (warmup > 0f){
                drawMining();
            }

            Draw.z(Layer.blockOver - 4f);

            Draw.rect(topRegion, x, y);
            if(outputItem() != null && drawMineItem){
                Draw.color(dominantItem.color);
                Draw.rect(oreRegion, x, y);
                Draw.color();
            }

            drawTeamTop();
        }

        public void drawMining(){}

        protected void tryDump(){
            if(timer(timerDump, dumpTime)){
                if (outputItem() != null){
                    if (coreSend && items.has(outputItem()) && core() != null && core().acceptItem(this, outputItem())){
                        items.remove(outputItem(), 1);
                        core().handleItem(this, outputItem());
                    }else {
                        dump(items.has(outputItem()) ? outputItem() : null);
                    }
                }
            }
        }

        protected void updateEffect(){
            if (!headless && warmup > 0.8f && efficiency > 0 && outputItem() != null && globalEffectRand.chance(updateEffectChance * boostScl())){
                updateEffect.at(x + globalEffectRand.range(size * 3.6f), y + globalEffectRand.range(size * 3.6f), outputItem().color);
            }
        }

        protected void resetModule(){
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
            for (DrillModule.DrillModuleBuild module: modules){
                module.drillBuild = null;
            }
        }

        public void updateDrillModule(){
            resetModule();
            for (Building building: proximity){
                if (building instanceof DrillModule.DrillModuleBuild module) {
                    if (module.canApply(this)){
                        module.drillBuild = this;
                        modules.add(module);
                        module.apply(this);
                    }
                }
            }
        }

        public String getMineInfo(){
            return outputItem() == null?
                    Iconc.cancel + " No Available Resource": convertItem == null?
                    Fonts.getUnicodeStr(outputItem().name) + " " + outputItem().localizedName:
                    Fonts.getUnicodeStr(dominantItem.name) + " " + dominantItem.localizedName + " -> " + Fonts.getUnicodeStr(outputItem().name) + " " + outputItem().localizedName;
        }

        public Color getMineColor(){
            return outputItem() == null? Pal.darkishGray: Tmp.c1.set(outputItem().color).lerp(Color.black, 0.2f);
        }

        //notice in tick
        public float getPowerCons(){
            return (powerConsBase * powerConsMul + powerConsExtra) / 60f;
        }

        protected void updateProgress(){
            if (items.total() < itemCapacity){
                progress += edelta() * Mathf.clamp((float) dominantItems / maxOreTileReq) * boostScl();
            }
            if (!headless){
                if (items.total() < itemCapacity && dominantItems > 0 && efficiency > 0){
                    warmup = Mathf.approachDelta(warmup, efficiency, 0.01f);
                }else {
                    warmup = Mathf.approachDelta(warmup, 0, 0.01f);
                }
            }
        }

        public float boostScl(){
            return boostMul * boostFinalMul * getMineSpeedHardnessMul(dominantItem);
        }

        protected float getMineSpeed(){
            return Mathf.clamp((float) dominantItems / maxOreTileReq) * boostScl() * mineSpeed;
        }

        @Override
        public Object senseObject(LAccess sensor){
            if(sensor == LAccess.firstItem) return dominantItem;
            return super.senseObject(sensor);
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(progress);
            write.f(warmup);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            progress = read.f();
            warmup = read.f();
        }
    }
}
