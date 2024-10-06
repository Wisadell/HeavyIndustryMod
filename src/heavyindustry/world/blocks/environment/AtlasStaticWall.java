package heavyindustry.world.blocks.environment;

import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;

import static heavyindustry.util.HIUtils.*;
import static mindustry.Vars.*;

public class AtlasStaticWall extends StaticWall {
    public TextureRegion[] splitRegion;

    public AtlasStaticWall(String name) {
        super(name);
    }

    @Override
    public void drawBase(Tile tile){
        int rx = tile.x / 2 * 2;
        int ry = tile.y / 2 * 2;

        if(eq(rx, ry) && Mathf.randomSeed(Point2.pack(rx, ry)) < 0.5) Draw.rect(split[tile.x % 2][1 - tile.y % 2], tile.worldx(), tile.worldy());
        else Draw.rect(splitRegion[Mathf.randomSeed(tile.pos(), 0, Math.max(0, splitRegion.length - 1))], tile.worldx(), tile.worldy());

        //draw ore on top
        if(tile.overlay().wallOre){
            tile.overlay().drawBase(tile);
        }
    }

    @Override
    public void load() {
        super.load();
        splitRegion = split(name + "-full", 32, 0);
    }

    protected boolean eq(int rx, int ry){
        return rx < world.width() - 1 && ry < world.height() - 1
                && world.tile(rx + 1, ry).block() == this
                && world.tile(rx, ry + 1).block() == this
                && world.tile(rx, ry).block() == this
                && world.tile(rx + 1, ry + 1).block() == this;
    }
}
