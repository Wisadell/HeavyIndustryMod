package HeavyIndustry.util;

import static arc.Core.atlas;

import org.jetbrains.annotations.Contract;

import arc.graphics.g2d.TextureRegion;
import arc.util.*;

public class HIUtls {
    public static String stringsFixed(float value){
        return Strings.autoFixed(value, 2);
    }
    @Contract(pure = true)
    public static int reverse(int rotation) {
        return switch(rotation) {
            case 0 -> 2; case 2 -> 0;case 1 -> 3; case 3 -> 1;
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
}
