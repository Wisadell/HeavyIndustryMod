package heavyindustry.world.draw;

import heavyindustry.world.blocks.distribution.*;
import arc.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.world.draw.*;

import static heavyindustry.core.HeavyIndustryMod.*;

public class DrawInvertedJunction extends DrawBlock {
    @Override
    public void draw(Building build) {
        InvertedJunction block = (InvertedJunction) build.block;
        InvertedJunction.InvertedJunctionBuild b = (InvertedJunction.InvertedJunctionBuild) build;
        drawB(block, b);
    }
    public void drawB(InvertedJunction block, InvertedJunction.InvertedJunctionBuild build){
        Draw.rect(Core.atlas.find(block.placeSprite), build.x, build.y);
        Draw.rect(Core.atlas.find(modName + "-junction-" + build.loc), build.x, build.y);
    }
}
