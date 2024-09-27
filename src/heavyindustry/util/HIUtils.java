package heavyindustry.util;

import arc.graphics.g2d.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import org.jetbrains.annotations.*;

import static arc.Core.*;

/** Input-output utilities, providing very specific functions that aren't really commonly used, but often enough to require me to write a class for it. */
public final class HIUtils {
    public static String stringsFixed(float value){
        return Strings.autoFixed(value, 2);
    }

    @Contract(pure = true)
    public static int reverse(int rotation) {
        return switch(rotation) {
            case 0 -> 2; case 2 -> 0; case 1 -> 3; case 3 -> 1;
            default -> throw new IllegalStateException("Unexpected value: " + rotation);
        };
    }

    public static TextureRegion[][] splitLayers(String name, int size, int layerCount){
        TextureRegion[][] layers = new TextureRegion[layerCount][];

        for(int i = 0; i < layerCount; i++){
            layers[i] = split(name, size, i);
        }
        return layers;
    }

    public static TextureRegion[] split(String name, int size, int layer){
        TextureRegion tex = atlas.find(name);
        int margin = 0;
        int countX = tex.width / size;
        TextureRegion[] tiles = new TextureRegion[countX];

        for(int step = 0; step < countX; step++){
            tiles[step] = new TextureRegion(tex, step * (margin + size), layer * (margin + size), size, size);
        }
        return tiles;
    }

    public static TextureRegion[] splitRegionArray(TextureRegion region, int tileWidth, int tileHeight){
        if(region.texture == null) return null;
        int x = region.getX();
        int y = region.getY();
        int width = region.width;
        int height = region.height;

        int sw = width / tileWidth;
        int sh = height / tileHeight;

        int startX = x;
        TextureRegion[] tiles = new TextureRegion[sw * sh];
        for(int cy = 0; cy < sh; cy++, y += tileHeight){
            x = startX;
            for(int cx = 0; cx < sw; cx++, x += tileWidth){
                tiles[cx + cy * sw] = new TextureRegion(region.texture, x, y, tileWidth, tileHeight);
            }
        }

        return tiles;
    }

    public static TextureRegion[] splitRegionArray(TextureRegion region, int tileWidth, int tileHeight, int pad){
        if(region.texture == null) return null;
        int x = region.getX();
        int y = region.getY();
        int width = region.width;
        int height = region.height;

        int pWidth = tileWidth + pad * 2;
        int pHeight = tileHeight + pad * 2;

        int sw = width / pWidth;
        int sh = height / pHeight;

        int startX = x;
        TextureRegion[] tiles = new TextureRegion[sw * sh];
        for(int cy = 0; cy < sh; cy++, y += pHeight){
            x = startX;
            for(int cx = 0; cx < sw; cx++, x += pWidth){
                tiles[cx + cy * sw] = new TextureRegion(region.texture, x + pad, y + pad, tileWidth, tileHeight);
            }
        }

        return tiles;
    }

    /**
     * {@link Tile#relativeTo(int, int)} does not account for building rotation.
     * Taken from Goobrr/esoterum.
     * */
    public static int relativeDirection(Building from, Building to){
        if(from == null || to == null) return -1;
        if(from.x == to.x && from.y > to.y) return (7 - from.rotation) % 4;
        if(from.x == to.x && from.y < to.y) return (5 - from.rotation) % 4;
        if(from.x > to.x && from.y == to.y) return (6 - from.rotation) % 4;
        if(from.x < to.x && from.y == to.y) return (4 - from.rotation) % 4;
        return -1;
    }

    public static Item oreDrop(Tile tile){
        if(tile == null) return null;

        if(tile.block() != Blocks.air){
            return tile.wallDrop();
        }else{
            return tile.drop();
        }
    }
}
