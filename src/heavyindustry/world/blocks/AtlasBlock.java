package heavyindustry.world.blocks;

import heavyindustry.util.*;
import arc.graphics.g2d.*;
import arc.math.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.*;

public class AtlasBlock extends Block {
    public TextureRegion[] splitRegion, splitShadowRegion;

    public AtlasBlock(String name) {
        super(name);
        variants = 1;
    }

    @Override
    public void load(){
        super.load();
        splitRegion = HIUtils.split(name, 32, 0);
        splitShadowRegion = HIUtils.split(name + "-shadow", 32, 0);
    }

    @Override
    public void drawBase(Tile tile) {
        if(tile.build != null) tile.build.draw();
        else Draw.rect(splitRegion[Mathf.randomSeed(tile.pos(), 0, Math.max(0, variants - 1))], tile.drawx(), tile.drawy());
    }

    @Override
    public void drawShadow(Tile tile) {
        Draw.color(0f, 0f, 0f, BlockRenderer.shadowColor.a);
        Draw.rect(splitShadowRegion[Mathf.randomSeed(tile.pos(), 0, Math.max(0, variants - 1))], tile.drawx(), tile.drawy(), tile.build == null ? 0f : tile.build.drawrot());
        Draw.color();
    }

    public class AtlasBuild extends Building {
        @Override
        public void draw() {
            Draw.rect(splitRegion[Mathf.randomSeed(tile.pos(), 0, Math.max(0, variants - 1))], x, y, drawrot());

            drawTeamTop();
        }
    }
}
