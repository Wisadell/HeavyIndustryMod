package heavyindustry.world.blocks.storage;

import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import mindustry.content.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.storage.CoreBlock.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

/**
 * Connect the core warehouse. It has no startup time or consumption.
 * @author Wisadell
 */
public class CoreLinkBlock extends Block  {
    public CoreLinkBlock(String name){
        super(name);
        hasItems = true;
        solid = true;
        update = true;
        destructible = true;
        separateItemCapacity = true;
        group = BlockGroup.transportation;
        flags = EnumSet.of(BlockFlag.storage);
        envEnabled = Env.any;
        canOverdrive = false;
    }

    @Override
    protected TextureRegion[] icons(){
        return teamRegion.found() ? new TextureRegion[]{region, teamRegions[Team.sharded.id]} : new TextureRegion[]{region};
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.remove(Stat.itemCapacity);
    }

    @Override
    public boolean outputsItems(){
        return false;
    }

    public class CoreLinkBuild extends Building{
        public Building linkedCore;

        @Override
        public void created(){
            super.created();

            linkedCore = team.core();
            if(linkedCore != null) items = linkedCore.items;
        }

        @Override
        public void updateTile(){
            if(linkedCore == null) linkedCore = team.core();
            if(linkedCore != null) items = linkedCore.items;
        }

        @Override
        public boolean acceptItem(Building source, Item item){
            return linkedCore != null && linkedCore.acceptItem(source, item);
        }

        @Override
        public void handleItem(Building source, Item item){
            if(linkedCore != null){
                if(linkedCore.items.get(item) >= ((CoreBuild)linkedCore).storageCapacity){
                    if(Mathf.chance(0.3)){
                        Fx.coreBurn.at(this);
                    }
                }
                ((CoreBuild)linkedCore).noEffect = true;
                linkedCore.handleItem(source, item);
            }
        }

        @Override
        public boolean canUnload(){
            return super.canUnload() && linkedCore != null && canConsume();
        }

        @Override
        public void itemTaken(Item item){
            if(linkedCore != null){
                linkedCore.itemTaken(item);
            }
        }

        @Override
        public int removeStack(Item item, int amount){
            if(linkedCore != null){
                int result = super.removeStack(item, amount);

                if(team == state.rules.defaultTeam && state.isCampaign()){
                    state.rules.sector.info.handleCoreItem(item, -result);
                }
                return result;
            }
            return 0;
        }

        @Override
        public int getMaximumAccepted(Item item){
            return linkedCore != null ? linkedCore.getMaximumAccepted(item) : 0;
        }

        @Override
        public int explosionItemCap(){
            //Items are teleported in from the core, the link itself does not contain items.
            return 0;
        }

        @Override
        public void drawSelect(){
            //outlines around core
            if(linkedCore != null){
                linkedCore.drawSelect();
            }

            //outlines around self
            Draw.color(Pal.accent);
            for(int i = 0; i < 4; i++){
                Point2 p = Geometry.d8edge[i];
                float offset = -Math.max(block.size - 1, 0) / 2f * tilesize;
                Draw.rect("block-select", x + offset * p.x, y + offset * p.y, i * 90);
            }
        }

        @Override
        public boolean canPickup(){
            return false;
        }
    }
}
