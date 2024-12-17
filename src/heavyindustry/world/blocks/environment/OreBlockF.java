package heavyindustry.world.blocks.environment;

import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;

import static arc.Core.*;
import static mindustry.Vars.*;

public class OreBlockF extends OreBlock {
    public int variantsLarge = 2;
    public TextureRegion[] larges;
    public TextureRegion[][][] split;
    public float overChance = 1;

    public OreBlockF(String name, Item ore) {
        super(name, ore);
    }

    protected boolean findAtlas(TextureRegion... ts) {
        if (ts.length == 0) return false;
        for (TextureRegion t : ts) {
            if (!atlas.isFound(t)) return false;
        }
        return true;
    }

    @Override
    public void drawBase(Tile tile) {
        int rx = tile.x / 2 * 2;
        int ry = tile.y / 2 * 2;

        if (findAtlas(larges) && eq(rx, ry) && Mathf.randomSeed(Point2.pack(rx, ry)) < overChance) {
            Draw.rect(split[Mathf.randomSeed(Point2.pack(rx, ry), 0, Math.max(0, larges.length - 1))][tile.x % 2][1 - tile.y % 2], tile.worldx(), tile.worldy());
        } else if (variants > 0) {
            Draw.rect(variantRegions[Mathf.randomSeed(tile.pos(), 0, Math.max(0, variantRegions.length - 1))], tile.worldx(), tile.worldy());
        } else {
            Draw.rect(region, tile.worldx(), tile.worldy());
        }
    }

    @Override
    public void load() {
        super.load();
        if (variantsLarge > 0) {
            larges = new TextureRegion[variantsLarge];
            split = new TextureRegion[variantsLarge][][];
            for (int i = 0; i < variantsLarge; i++) {
                larges[i] = atlas.find(name + "-large" + i);
                split[i] = larges[i].split(32, 32);
            }
        }
    }

    protected boolean eq(int rx, int ry) {
        return rx < world.width() - 1 && ry < world.height() - 1
                && world.tile(rx + 1, ry).overlay() == this
                && world.tile(rx, ry + 1).overlay() == this
                && world.tile(rx, ry).overlay() == this
                && world.tile(rx + 1, ry + 1).overlay() == this;
    }
}
