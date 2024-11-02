package heavyindustry.util;

import arc.graphics.g2d.*;
import arc.math.geom.*;
import arc.struct.*;

import java.util.*;

import static arc.Core.*;

public final class SpriteUtil {
    public static final int[] atlasIndex_4_4 = {
            0b00000000, 0b00000010, 0b00001010, 0b00001000,
            0b00000100, 0b00000110, 0b00001110, 0b00001100,
            0b00000101, 0b00000111, 0b00001111, 0b00001101,
            0b00000001, 0b00000011, 0b00001011, 0b00001001,
    };

    public static final int[] atlasIndex_4_12_raw = {
            0b00000000, 0b00000010, 0b00001010, 0b00001000, 0b10001111, 0b00101110, 0b01001110, 0b00011111, 0b00100110, 0b01101111, 0b01101110, 0b01001100,
            0b00000100, 0b00000110, 0b00001110, 0b00001100, 0b00100111, 0b01111111, 0b11101111, 0b01001101, 0b00110111, 0b01011111, 0b10101111, 0b11001111,
            0b00000101, 0b00000111, 0b00001111, 0b00001101, 0b00010111, 0b10111111, 0b11011111, 0b10001101, 0b00111111, 0b11111111, 0b11110000, 0b11001101,
            0b00000001, 0b00000011, 0b00001011, 0b00001001, 0b01001111, 0b00011011, 0b10001011, 0b00101111, 0b00010011, 0b10011011, 0b10011111, 0b10001001,
    };

    public static final int[] atlasIndex_4_12 = new int[atlasIndex_4_12_raw.length];
    public static final IntIntMap atlasIndex_4_12_map = new IntIntMap();

    public static final Point2[] orthogonalPos = {
            new Point2(0, 1),
            new Point2(1, 0),
            new Point2(0, -1),
            new Point2(-1, 0),
    };

    public static final Point2[][] diagonalPos = {
            new Point2[]{ new Point2(1, 0), new Point2(1, 1), new Point2(0, 1)},
            new Point2[]{ new Point2(1, 0), new Point2(1, -1), new Point2(0, -1)},
            new Point2[]{ new Point2(-1, 0), new Point2(-1, -1), new Point2(0, -1)},
            new Point2[]{ new Point2(-1, 0), new Point2(-1, 1), new Point2(0, 1)},
    };

    public static final Point2[] proximityPos = {
            new Point2(0, 1),
            new Point2(1, 0),
            new Point2(0, -1),
            new Point2(-1, 0),

            new Point2(1, 1),
            new Point2(1, -1),
            new Point2(-1, -1),
            new Point2(-1, 1),
    };

    static {
        Integer[] indices = new Integer[atlasIndex_4_12_raw.length];
        for (int i = 0; i < atlasIndex_4_12_raw.length; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, Comparator.comparingInt(a -> atlasIndex_4_12_raw[a]));

        for (int i = 0; i < indices.length; i++) {
            atlasIndex_4_12[indices[i]] = i;
        }
        for (int i = 0; i < atlasIndex_4_12_raw.length; i++) {
            atlasIndex_4_12_map.put(atlasIndex_4_12_raw[i], atlasIndex_4_12[i]);
        }
    }

    public static TextureRegion[] split(String name, int tileWidth, int tileHeight){
        return split(name, tileWidth, tileHeight, 0);
    }

    public static TextureRegion[] split(String name, int tileWidth, int tileHeight, int pad){
        return split(name, tileWidth, tileHeight, pad, null);
    }

    public static TextureRegion[] split(String name, int tileWidth, int tileHeight, int pad, int[] indexMap){
        TextureRegion region = atlas.find(name);

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
                int index = cx + cy * sw;
                if (indexMap != null){
                    tiles[indexMap[index]] = new TextureRegion(region.texture, x + pad, y + pad, tileWidth, tileHeight);
                }else {
                    tiles[index] = new TextureRegion(region.texture, x + pad, y + pad, tileWidth, tileHeight);
                }
            }
        }

        return tiles;
    }
}
