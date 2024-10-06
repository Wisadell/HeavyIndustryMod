package heavyindustry.world.blocks.distribution;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.scene.style.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.blocks.liquid.LiquidJunction;

import static heavyindustry.core.HeavyIndustryMod.*;
import static mindustry.Vars.*;

/**
 * Inverted Junction
 * @author guiY
 */
public class MultiInvertedJunction extends MultiJunction {
    public String placeSprite;

    public final int size = 1;

    public Color[] colors = {Color.valueOf("bf92f9"), Color.valueOf("c0ecff"), Color.valueOf("84f491"), Color.valueOf("fffa763")};

    public TextureRegion arrow1, arrow2;

    public MultiInvertedJunction(String name) {
        super(name);

        sync = true;
        configurable = true;
        config(Integer.class, (MultiInvertedJunctionBuild build, Integer loc) -> build.loc = loc);
    }

    @Override
    public void load() {
        super.load();
        arrow1 = Core.atlas.find(name("arrow-1"));
        arrow2 = Core.atlas.find(name("arrow-2"));
    }

    public class MultiInvertedJunctionBuild extends MultiJunctionBuild {
        public int loc = 1;

        @Override
        public void configured(Unit player, Object value) {
            super.configured(player, value);
            loc = (int)value;
        }

        @Override
        public void updateTile() {
            for(int i = 0; i < 4; i++){
                int p = (i + loc) % 4;
                if(buffer.indexes[i] > 0){
                    if(buffer.indexes[i] > capacity) buffer.indexes[i] = capacity;
                    long l = buffer.buffers[i][0];
                    float time = BufferItem.time(l);

                    if(Time.time >= time + speed / timeScale || Time.time < time){

                        Item item = content.item(BufferItem.item(l));
                        Building dest = nearby(p);

                        //skip blocks that don't want the item, keep waiting until they do
                        if(item == null || dest == null || !dest.acceptItem(this, item) || dest.team != team){
                            continue;
                        }

                        dest.handleItem(this, item);
                        System.arraycopy(buffer.buffers[i], 1, buffer.buffers[i], 0, buffer.indexes[i] - 1);
                        buffer.indexes[i] --;
                    }
                }
            }
        }

        @Override
        public void draw() {
            Draw.rect(Core.atlas.find(placeSprite), x, y);
            Draw.rect(Core.atlas.find(modName + "-junction-" + loc), x, y);
        }

        @Override
        public Building getLiquidDestination(Building source, Liquid liquid) {
            if(!enabled) return this;

            int relative = source.relativeTo(tile.x, tile.y);
            int dir = (relative + 4 + loc) % 4;
            Building next = nearby(dir);
            if(next == null || (!next.acceptLiquid(this, liquid) && !(next.block instanceof LiquidJunction))){
                return this;
            }
            return next.getLiquidDestination(this, liquid);
        }

        @Override
        public boolean acceptItem(Building source, Item item) {
            int relative = source.relativeTo(tile);

            if(relative == -1 || !buffer.accepts(relative)) return false;
            Building to = nearby((relative + loc) % 4);
            return to != null && to.team == team;
        }

        @Override
        public void drawSelect() {
            super.drawSelect();

            float sin = Mathf.sin(Time.time, 6, 0.6f);
            for (int i = 0; i < 4; i++){
                Draw.color(colors[i]);
                int in = loc == 1 ? 3 : 1;
                int input = (i + in)%4;
                Draw.rect(arrow1, x + Geometry.d4x(i) * (tilesize + sin), y + Geometry.d4y(i) * (tilesize + sin), 90*i);
                Draw.rect(arrow2, x + Geometry.d4x(input) * (tilesize - sin), y + Geometry.d4y(input) * (tilesize - sin), 90*input);
                Draw.color();
            }
        }

        @Override
        public void buildConfiguration(Table table) {
            table.button(new TextureRegionDrawable(Core.atlas.find(modName + "-flip", Core.atlas.find("clear"))), Styles.cleari, this::switchF).size(36f).tooltip("switch");
        }

        public void switchF(){
            loc = loc == 1 ? 3 : 1;
            deselect();
            configure(loc);
        }

        @Override
        public Integer config() {
            return loc;
        }

        @Override
        public void write(Writes write) {
            super.write(write);
            write.i(loc);
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            loc = read.i();
        }
    }
}
