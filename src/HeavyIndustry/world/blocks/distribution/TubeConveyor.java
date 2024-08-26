package HeavyIndustry.world.blocks.distribution;

import HeavyIndustry.util.HIUtls;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.util.Eachable;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.entities.units.BuildPlan;
import mindustry.gen.Building;
import mindustry.gen.Teamc;
import mindustry.gen.Unit;
import mindustry.graphics.Layer;
import mindustry.type.Item;
import mindustry.world.blocks.distribution.Conveyor;

import static HeavyIndustry.util.HIUtls.reverse;
import static arc.Core.atlas;
import static mindustry.Vars.itemSize;
import static mindustry.Vars.tilesize;
import static mindustry.Vars.world;

/**
 * It may have defects, such as seams in the texture connection, and I don't want to fix it anymore.
 * What kind of mental state was I in when I made this thing?
 */
public class TubeConveyor extends Conveyor {
    private static final float itemSpace = 0.4f;
    public static final int[][] tiles = new int[][]{
            {},
            {0, 2}, {1, 3}, {0, 1},
            {0, 2}, {0, 2}, {1, 2},
            {0, 1, 2}, {1, 3}, {0, 3},
            {1, 3}, {0, 1, 3}, {2, 3},
            {0, 2, 3}, {1, 2, 3}, {0, 1, 2, 3}
    };
    public TextureRegion[][] topRegion;
    public TextureRegion[] capRegion;
    public TubeConveyor(String name){
        super(name);
        buildType = TubeConveyorBuild::new;
    }

    @Override
    public void drawPlanRegion(BuildPlan req, Eachable<BuildPlan> list){
        super.drawPlanRegion(req, list);
        BuildPlan[] directionals = new BuildPlan[4];
        list.each(other -> {
            if(other.breaking || other == req) return;

            int i = 0;
            for(Point2 point : Geometry.d4){
                int x = req.x + point.x, y = req.y + point.y;
                if(x >= other.x -(other.block.size - 1) / 2 && x <= other.x + (other.block.size / 2) && y >= other.y -(other.block.size - 1) / 2 && y <= other.y + (other.block.size / 2)){
                    if ((other.block instanceof Conveyor ? (req.rotation == i || (other.rotation + 2) % 4 == i) : ((req.rotation == i && other.block.acceptsItems) || (req.rotation != i && other.block.outputsItems())))) {
                        directionals[i] = other;
                    }
                }
                i++;
            }
        });

        int mask = 0;
        for(int i = 0; i < directionals.length; i++) {
            if (directionals[i] != null) {
                mask += (1 << i);
            }
        }
        mask |= (1 << req.rotation);
        Draw.rect(topRegion[0][mask], req.drawx(), req.drawy(), 0);
        for(int i : tiles[mask]){
            if(directionals[i] == null || (directionals[i].block instanceof Conveyor ? (directionals[i].rotation + 2) % 4 == req.rotation : ((req.rotation == i && !directionals[i].block.acceptsItems) || (req.rotation != i && !directionals[i].block.outputsItems())))){
                int id = i == 0 || i == 3 ? 1 : 0;
                Draw.rect(capRegion[id], req.drawx(), req.drawy(), i == 0 || i == 2 ? 0 : -90);
            }
        }
    }

    @Override
    public void load(){
        super.load();
        topRegion = HIUtls.splitLayers(name + "-sheet", 32, 2);
        capRegion = new TextureRegion[] { topRegion[1][0], topRegion[1][1] };
        uiIcon = atlas.find(name + "-icon");
    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{atlas.find(name + "-icon-editor")};
    }

    public class TubeConveyorBuild extends ConveyorBuild{
        public int tiling = 0;
        public int calls = 0;
        public void updateProximity() {
            super.updateProximity();
            calls++;
        }

        public Building buildAt(int i){
            return nearby(i);
        }

        public boolean valid(int i){
            Building b = buildAt(i);
            return b != null && (b instanceof TubeConveyorBuild ? (b.front() != null && b.front() == this) : b.block.acceptsItems);
        }

        public boolean isEnd(int i){
            var b = buildAt(i);
            return (!valid(i) && (b == null ? null : b.block) != this.block) || (b instanceof ConveyorBuild && ((b.rotation + 2) % 4 == rotation || (b.front() != this && back() == b)));
        }

        @Override
        public void draw(){
            int frame = enabled && clogHeat <= 0.5f ? (int) (((Time.time * speed * 8f * timeScale * efficiency)) % 4) : 0;

            Draw.z(Layer.blockUnder);
            for(int i = 0; i < 4; i++){
                if((blending & (1 << i)) != 0){
                    int dir = rotation - i;
                    float rot = i == 0 ? rotation * 90 : (dir) * 90;

                    Draw.rect(sliced(regions[0][frame], i != 0 ? SliceMode.bottom : SliceMode.top), x + Geometry.d4x(dir) * tilesize * 0.75f, y + Geometry.d4y(dir) * tilesize * 0.75f, rot);
                }
            }

            Draw.z(Layer.block - 0.25f);

            Draw.rect(regions[blendbits][frame], x, y, tilesize * blendsclx, tilesize * blendscly, rotation * 90);

            Draw.z(Layer.block - 0.2f);
            float layer = Layer.block - 0.2f, wwidth = world.unitWidth(), wheight = world.unitHeight(), scaling = 0.01f;

            for(int i = 0; i < len; i++){
                Item item = ids[i];
                Tmp.v1.trns(rotation * 90, tilesize, 0);
                Tmp.v2.trns(rotation * 90, -tilesize / 2f, xs[i] * tilesize / 2f);

                float ix = (x + Tmp.v1.x * ys[i] + Tmp.v2.x), iy = (y + Tmp.v1.y * ys[i] + Tmp.v2.y);

                Draw.z(layer + (ix / wwidth + iy / wheight) * scaling);
                Draw.rect(item.fullIcon, ix, iy, itemSize, itemSize);
            }

            Draw.z(Layer.block - 0.15f);
            Draw.rect(topRegion[0][tiling], x, y, 0);
            int[] placementID = tiles[tiling];
            for(int i : placementID){
                if(isEnd(i)){
                    int id = i == 0 || i == 3 ? 1 : 0;
                    Draw.rect(capRegion[id], x, y, i == 0 || i == 2 ? 0 : -90);
                }
            }
        }

        @Override
        public int acceptStack(Item item, int amount, Teamc source){
            if(isEnd(reverse(rotation)) && items.total() >= 2) return 0;
            if(isEnd(reverse(rotation)) && isEnd(rotation) && items.total() >= 1) return 0;
            return Math.min((int)(minitem / itemSpace), amount);
        }

        @Override
        public void unitOn(Unit unit){
        }

        @Override
        public void drawCracks(){
            Draw.z(Layer.block);
            super.drawCracks();
        }

        @Override
        public void onProximityUpdate(){
            super.onProximityUpdate();
            noSleep();
            next = front();
            nextc = next instanceof TubeConveyorBuild && next.team == team ? (TubeConveyorBuild)next : null;
            aligned = nextc != null && rotation == next.rotation;

            tiling = 0;
            for(int i = 0; i < 4; i++){
                Building otherblock = nearby(i);
                if (otherblock == null) continue;
                if ((otherblock.block instanceof Conveyor ? (rotation == i || (otherblock.rotation + 2) % 4 == i) : ((rotation == i && otherblock.block.acceptsItems) || (rotation != i && otherblock.block.outputsItems())))) {
                    tiling |= (1 << i);
                }
            }
            tiling |= 1 << rotation;
        }
    }
}
