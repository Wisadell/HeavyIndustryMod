package heavyindustry.util;

import arc.graphics.g2d.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.type.*;
import mindustry.world.*;
import org.jetbrains.annotations.*;

import static arc.Core.*;

/** Input-output utilities, providing very specific functions that aren't really commonly used, but often enough to require me to write a class for it. */
public class HIUtils {
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

    public static Item oreDrop(Tile tile){
        if(tile == null) return null;

        if(tile.block() != Blocks.air){
            return tile.wallDrop();
        }else{
            return tile.drop();
        }
    }
}
