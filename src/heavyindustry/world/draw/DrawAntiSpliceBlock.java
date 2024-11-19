package heavyindustry.world.draw;

import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.geom.*;
import arc.util.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.world.*;
import mindustry.world.draw.*;
import heavyindustry.util.*;

import static arc.Core.*;

@SuppressWarnings("unchecked")
public class DrawAntiSpliceBlock<E> extends DrawBlock {
    private final static String[] splices = {"right", "right_top", "top", "left_top", "left", "left_bot", "bot", "right_bot"};

    public TextureRegion[] drawRegions = new TextureRegion[/*2^8=*/256];//Space for time, don't use this type too much.
    public Boolf2<BuildPlan, BuildPlan> planSplicer = (plan, other) -> false;
    public Intf<E> splicer = e -> 0;

    public TextureRegion icon;

    public float layerOffset = 0.0001f;
    public boolean layerRec = true;

    public boolean interConner;

    @Override
    public void load(Block block) {
        icon = atlas.find(block.name + "_icon");

        Pixmap[] regions = new Pixmap[8];
        Pixmap[] inner = new Pixmap[4];

        for (int i = 0; i < regions.length; i++) {
            regions[i] = atlas.getPixmap(block.name + "_" + splices[i]).crop();
        }
        for (int i = 0; i < inner.length; i++) {
            inner[i] = atlas.getPixmap(block.name + "_" + splices[i * 2 + 1] + "_inner").crop();
        }

        for (int i = 0; i < drawRegions.length; i++) {
            drawRegions[i] = getRegionWithBit(regions, inner, i);
        }
    }

    @Override
    public TextureRegion[] icons(Block block) {
        return new TextureRegion[]{icon};
    }

    protected TextureRegion getRegionWithBit(Pixmap[] regions, Pixmap[] inner, int i) {
        Pixmap map = new Pixmap(regions[0].width, regions[0].height);

        for (int dir = 0; dir < 8; dir++) {
            if (dir%2 == 0){
                if ((i & (1 << dir)) == 0) map.draw(regions[dir], true);
            }
        }
        for (int dir = 0; dir < 8; dir++) {
            if ((dir + 1)%2 == 0){
                int dirBit = 1 << (dir + 1)%8 | 1 << (dir - 1);
                if ((i & dirBit) == 0) map.draw(regions[dir], true);
                else if ((i & dirBit) == dirBit && (interConner || (i & (1 << dir)) == 0)) map.draw(inner[dir/2], true);
            }
        }

        Pixmaps.bleed(map, 2);
        Texture tex = new Texture(map);
        tex.setFilter(Texture.TextureFilter.linear);
        return new TextureRegion(tex);
    }

    @Override
    public void draw(Building build) {
        float z = Draw.z();
        Draw.z(z + layerOffset);
        Draw.rect(drawRegions[splicer.get((E) build)], build.x, build.y);
        if (layerRec) Draw.z(z);
    }

    @Override
    public void drawPlan(Block block, BuildPlan plan, Eachable<BuildPlan> list){
        int data = 0;
        Block planBlock = plan.block;

        t: for(int i = 0; i < 8; i++){
            Block other = null;
            for(Point2 p: DirEdges.get8(plan.block.size, i)){
                int x = plan.x + p.x;
                int y = plan.y + p.y;
                BuildPlan[] target = {null};

                list.each(pl -> {
                    if(target[0] != null) return;
                    if(pl.x == x && pl.y == y){
                        target[0] = pl;
                    }
                });

                if(target[0] == null) continue t;

                if(other == null){
                    if(planSplicer.get(plan, target[0])){
                        other = target[0].block;
                    }else{
                        continue t;
                    }
                }else if(other != planBlock || !planSplicer.get(plan, target[0])){
                    continue t;
                }
            }
            data |= 1 << i;
        }

        Draw.rect(drawRegions[data], plan.drawx(), plan.drawy());
    }
}
