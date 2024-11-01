package heavyindustry.world.blocks.environment;

import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;

import static arc.Core.atlas;
import static mindustry.Vars.*;

public class TileStaticWall extends StaticWall {
    public int variantsLarge = 2;
    public TextureRegion[] larges;
    public TextureRegion[][][] splitLarges;
    public float overChance = 1;

    public TileStaticWall(String name) {
        super(name);
    }

    protected boolean findAtlas(TextureRegion... ts){
        if(ts.length == 0) return false;
        for(var t : ts){
            if(!atlas.isFound(t)) return false;
        }
        return true;
    }

    @Override
    public void drawBase(Tile tile){
        int rx = tile.x / 2 * 2;
        int ry = tile.y / 2 * 2;

        if(findAtlas(larges) && eq(rx, ry) && Mathf.randomSeed(Point2.pack(rx, ry)) < overChance){
            Draw.rect(splitLarges[Mathf.randomSeed(Point2.pack(rx, ry), 0, Math.max(0, larges.length - 1))][tile.x % 2][1 - tile.y % 2], tile.worldx(), tile.worldy());
        }else if(variants > 0){
            Draw.rect(variantRegions[Mathf.randomSeed(tile.pos(), 0, Math.max(0, variantRegions.length - 1))], tile.worldx(), tile.worldy());
        }else{
            Draw.rect(region, tile.worldx(), tile.worldy());
        }

        //draw ore on top
        if(tile.overlay().wallOre){
            tile.overlay().drawBase(tile);
        }
    }

    @Override
    public void load(){
        super.load();
        if(variantsLarge > 0) {
            larges = new TextureRegion[variantsLarge];
            splitLarges = new TextureRegion[variantsLarge][][];
            for (int i = 0; i < variantsLarge; i++) {
                larges[i] = atlas.find(name + "-large" + i);
                splitLarges[i] = larges[i].split(32, 32);
            }
        }
    }

    private boolean eq(int rx, int ry){
        return rx < world.width() - 1 && ry < world.height() - 1
                && world.tile(rx + 1, ry).block() == this
                && world.tile(rx, ry + 1).block() == this
                && world.tile(rx, ry).block() == this
                && world.tile(rx + 1, ry + 1).block() == this;
    }
}
