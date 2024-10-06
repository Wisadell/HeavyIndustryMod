package heavyindustry.world.blocks.storage;

import arc.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.ui.*;
import mindustry.world.blocks.storage.CoreBlock.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.meta.*;
import mindustry.world.modules.*;

/**
 * Connect the core warehouse.
 * @author Wisadell
 * @author Yuria
 */
public class CoreLinkBlock extends StorageBlock {
    protected CoreBuild tmpCoreBuild;

    public CoreLinkBlock(String name) {
        super(name);
        update = true;
        hasItems = true;
        itemCapacity = 0;
        configurable = true;
        replaceable = false;
    }

    @Override
    protected TextureRegion[] icons() {
        return teamRegion.found() ? new TextureRegion[]{region, teamRegions[Team.sharded.id]} : new TextureRegion[]{region};
    }

    @Override
    public boolean isAccessible() {
        return true;
    }

    @Override
    public void setStats() {
        super.setStats();
        stats.remove(Stat.itemCapacity);
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("warmup", (CoreLinkBuild entity) -> new Bar(() -> Mathf.equal(entity.warmup, 1, 0.015f) ? Core.bundle.get("done") : Core.bundle.get("research.load"), () -> Mathf.equal(entity.warmup, 1, 0.015f) ? Pal.heal : Pal.redderDust, () -> entity.warmup));
        removeBar("items");
        addBar("items", (CoreLinkBuild entity) -> new Bar(
                () -> Core.bundle.format("bar.items", entity.items.total()),
                () -> Pal.items,
                () -> (float)(entity.items.total() / ((tmpCoreBuild = entity.core()) == null ? Integer.MAX_VALUE : tmpCoreBuild.storageCapacity)))
        );
    }

    public class CoreLinkBuild extends StorageBuild {
        protected float warmup, progress;

        @Override
        public void updateTile(){
            if(efficiency() > 0 && core() != null){
                if(Mathf.equal(warmup, 1, 0.015F))warmup = 1f;
                else warmup = Mathf.lerpDelta(warmup, 1, 0.01f);
            }else{
                if(Mathf.equal(warmup, 0, 0.015F))warmup = 0f;
                else warmup = Mathf.lerpDelta(warmup, 0, 0.03f);
            }

            progress += warmup * efficiency() * Time.delta;

            if(Mathf.equal(warmup, 1, 0.015F)){
                if(linkedCore == null || !linkedCore.isValid() && core() != null){
                    linkedCore = core();
                    items = linkedCore.items;
                }
            }else{
                linkedCore = null;
                items = new ItemModule();
            }
        }

        @Override
        public boolean canPickup(){
            return false;
        }

        @Override
        public void drawSelect(){}

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(warmup);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            warmup = read.f();
        }
    }
}
