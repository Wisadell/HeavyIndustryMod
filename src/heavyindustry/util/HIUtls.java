package heavyindustry.util;

import static arc.Core.atlas;

import org.jetbrains.annotations.Contract;

import arc.graphics.g2d.TextureRegion;
import arc.util.*;

/**
 * Input-output utilities, providing very specific functions that aren't really commonly used, but often enough to
 * require me to write a class for it.
 */
public class HIUtls {
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

    /**
     * bittiler stuff
     * <p>
     * Original code from Serilia[<a href="https://github.com/Froomeeth/Serilia/blob/main/src/serilia/util/SeUtil.java#L64C1-L64C1">...</a>]
     */
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
}
