package heavyindustry.world.blocks.environment;

import arc.*;
import arc.graphics.g2d.*;
import arc.math.*;
import mindustry.graphics.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;

import static heavyindustry.util.HIUtils.*;

public class AtlasTallBlock extends TallBlock {
    public TextureRegion[] splitRegion, splitShadowRegion;

    public int splitSize = 32;

    public AtlasTallBlock(String name) {
        super(name);
        variants = 1;
    }

    @Override
    public void load(){
        super.load();
        region = Core.atlas.find(name);
        if (splitSize < 0) splitSize = 32;
        splitRegion = split(name + "-full", splitSize, 0);
        splitShadowRegion = split(name + "-shadow", splitSize, 0);
    }

    @Override
    public void drawBase(Tile tile) {
        float rot = Mathf.randomSeedRange(tile.pos() + 1, rotationRand);

        Draw.z(Layer.power - 1);
        Draw.color(0f, 0f, 0f, shadowAlpha);
        Draw.rect(splitShadowRegion[Mathf.randomSeed(tile.pos(), 0, Math.max(0, variants - 1))], tile.worldx() + shadowOffset, tile.worldy() + shadowOffset, rot);

        Draw.color();

        Draw.z(Layer.power + 1);
        Draw.rect(splitRegion[Mathf.randomSeed(tile.pos(), 0, Math.max(0, variants - 1))], tile.worldx(), tile.worldy(), rot);
    }

    @Override
    public TextureRegion[] icons() {
        return new TextureRegion[]{region};
    }
}
