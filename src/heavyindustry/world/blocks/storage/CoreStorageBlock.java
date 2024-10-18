package heavyindustry.world.blocks.storage;

import arc.graphics.g2d.*;
import arc.math.*;
import arc.struct.*;
import mindustry.*;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.blocks.storage.CoreBlock.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.meta.*;
import mindustry.world.modules.*;

import static arc.Core.*;
import static mindustry.Vars.*;

/**
 * Connect the core warehouse.
 * @author Wisadell
 */
public class CoreStorageBlock extends StorageBlock {
    protected CoreBuild tmpCoreBuild;

    public CoreStorageBlock(String name) {
        super(name);
        update = true;
        hasItems = true;
        itemCapacity = 0;
        configurable = true;
        replaceable = false;
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
        addBar("maxPlace", (CoreStorageBuild e) -> new Bar(
                () -> "Max Place | " + coreStorageBlock[e.team.id].size + " / " + maxPlaceNum(e.team),
                () -> coreStorageBlock[e.team.id].size < maxPlaceNum(e.team) ? Pal.accent : Pal.redderDust,
                () -> (float)coreStorageBlock[e.team.id].size / maxPlaceNum(e.team)));
        removeBar("items");
        addBar("items", (CoreStorageBuild e) -> new Bar(
                () -> bundle.format("bar.items", e.items.total()),
                () -> Pal.items,
                () -> (float)(e.items.total() / ((tmpCoreBuild = e.core()) == null ? Integer.MAX_VALUE : tmpCoreBuild.storageCapacity))));
    }

    public void drawPlace(int x, int y, int rotation, boolean valid) {
        if(maxPlaceNum(Vars.player.team()) <= coreStorageBlock[Vars.player.team().id].size){
            drawPlaceText("Maximum Placement Quantity Reached", x, y, false);
        }
    }

    public int maxPlaceNum(Team team){
        return (team == Vars.state.rules.waveTeam && !state.rules.pvp) || team.rules().cheat ? Integer.MAX_VALUE : Mathf.clamp(Vars.world.width() * Vars.world.height() / 10000, 3, 10);
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        return super.canPlaceOn(tile, team, rotation) && coreStorageBlock[team.id].size < maxPlaceNum(team);
    }

    public class CoreStorageBuild extends StorageBuild {
        @Override
        public void onRemoved(){
            super.onRemoved();
            coreStorageBlock[team.id].remove(this);
        }

        @Override
        public void created(){
            app.post(() -> coreStorageBlock[team.id].add(this));
        }

        @Override
        public void updateTile(){
            if(core() != null){
                if(linkedCore == null || !linkedCore.isValid()){
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
    }

    @SuppressWarnings("unchecked")
    protected static final ObjectSet<CoreStorageBuild>[] coreStorageBlock = new ObjectSet[Team.all.length];

    public static void clear(){
        for(ObjectSet<CoreStorageBuild> set : coreStorageBlock){
            set.clear();
        }
    }

    static{
        for(int i = 0; i < Team.all.length; i++){
            coreStorageBlock[i] = new ObjectSet<>(i < 6 ? 20 : 1);
        }
    }
}
