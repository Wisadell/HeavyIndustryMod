package heavyindustry.world.blocks.distribution;

import arc.*;
import arc.graphics.g2d.*;
import arc.math.geom.*;
import arc.util.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.blocks.distribution.*;

import static heavyindustry.util.HIUtils.*;
import static mindustry.Vars.*;

/**
 * Compared to CoveredConverter, its upper layer texture has been changed to one that can have light and shadow effects.
 * @author Wisadell
 */
public class TubeConveyor extends BeltConveyor {
    public static final float itemSpace = 0.4f;
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

    public TubeConveyor(String name) {
        super(name);
    }

    @Override
    public void load() {
        super.load();
        topRegion = splitLayers(name + "-sheet", 32, 2);
        capRegion = new TextureRegion[]{topRegion[1][0], topRegion[1][1]};
        uiIcon = Core.atlas.find(name + "-icon");
    }

    @Override
    public TextureRegion[] icons() {
        return new TextureRegion[]{Core.atlas.find(name + "-icon-editor")};
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

    public class TubeConveyorBuild extends BeltConveyorBuild {
        public int tiling = 0;
        public int calls = 0;

        @Override
        public void updateProximity() {
            super.updateProximity();
            calls++;
        }

        public Building buildAt(int i){
            return nearby(i);
        }

        public boolean valid(int i){
            Building b = buildAt(i);
            return b != null && (b instanceof TubeConveyorBuild ? (b.front() != null && b.front() == this) : b.block.acceptsItems || b.block.outputsItems());
        }

        public boolean isEnd(int i){
            Building b = buildAt(i);
            return (!valid(i) && (b == null ? null : b.block) != this.block) || (b instanceof ConveyorBuild && ((b.rotation + 2) % 4 == rotation || (b.front() != this && back() == b)));
        }

        @Override
        public void draw() {
            int frame = enabled && clogHeat <= 0.5f ? (int) (((Time.time * speed * 8f * timeScale * efficiency)) % 4) : 0;

            //draw extra conveyors facing this one for non-square tiling purposes
            Draw.z(Layer.blockUnder);
            for(int i = 0; i < 4; i++){
                if((blending & (1 << i)) != 0){
                    int dir = rotation - i;
                    float rot = i == 0 ? rotation * 90 : (dir) * 90;

                    Draw.rect(sliced(conveyorAtlas[0][frame], i != 0 ? SliceMode.bottom : SliceMode.top), x + Geometry.d4x(dir) * tilesize * 0.75f, y + Geometry.d4y(dir) * tilesize * 0.75f, rot);
                    Draw.rect(sliced(topRegion[0][frame], i != 0 ? SliceMode.bottom : SliceMode.top), x + Geometry.d4x(dir) * tilesize * 0.75f, y + Geometry.d4y(dir) * tilesize * 0.75f, rot);
                }
            }

            Draw.z(Layer.block - 0.25f);

            Draw.rect(conveyorAtlas[blendbits][frame], x, y, tilesize * blendsclx, tilesize * blendscly, rotation * 90);

            Draw.z(Layer.block - 0.2f);
            float layer = Layer.block - 0.2f, wwidth = world.unitWidth(), wheight = world.unitHeight(), scaling = 0.01f;

            for(int i = 0; i < len; i++){
                Item item = ids[i];
                Tmp.v1.trns(rotation * 90, tilesize, 0);
                Tmp.v2.trns(rotation * 90, -tilesize / 2f, xs[i] * tilesize / 2f);

                float ix = (x + Tmp.v1.x * ys[i] + Tmp.v2.x), iy = (y + Tmp.v1.y * ys[i] + Tmp.v2.y);

                //keep draw position deterministic.
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
        public void drawCracks(){
            Draw.z(Layer.block);
            super.drawCracks();
        }

        @Override
        public void unitOn(Unit unit){
        }

        @Override
        public void onProximityUpdate(){
            super.onProximityUpdate();
            noSleep();
            next = front();
            nextc = next instanceof TubeConveyorBuild d ? d : null;

            tiling = 0;
            for(int i = 0; i < 4; i++){
                Building otherBlock = nearby(i);
                if (otherBlock == null) continue;
                if ((otherBlock.block instanceof Conveyor ? (rotation == i || (otherBlock.rotation + 2) % 4 == i) : ((rotation == i && otherBlock.block.acceptsItems) || (rotation != i && otherBlock.block.outputsItems())))) {
                    tiling |= (1 << i);
                }
            }
            tiling |= 1 << rotation;
        }
    }
}
