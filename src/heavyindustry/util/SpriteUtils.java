package heavyindustry.util;

import arc.graphics.g2d.*;
import arc.math.geom.*;
import arc.struct.*;

import static arc.Core.*;

public final class SpriteUtils {
    public static final int[] atlasIndex44 = {
            0, 2, 10, 8,
            4, 6, 14, 12,
            5, 7, 15, 13,
            1, 3, 11, 9
    };

    public static final int[] atlasIndex412raw = {
            0, 2, 10, 8, 143, 46, 78, 31, 38, 111, 110, 76,
            4, 6, 14, 12, 39, 127, 239, 77, 55, 95, 175, 207,
            5, 7, 15, 13, 23, 191, 223, 141, 63, 255, 240, 205,
            1, 3, 11, 9, 79, 27, 139, 47, 19, 155, 159, 137
    };

    public static final int[] atlasIndex412 = new int[atlasIndex412raw.length];
    public static final IntIntMap atlasIndex412map = new IntIntMap();

    public static final byte[] atlasIndex412tile = {
            39, 36, 39, 36, 27, 16, 27, 24, 39, 36, 39, 36, 27, 16, 27, 24,
            38, 37, 38, 37, 17, 41, 17, 43, 38, 37, 38, 37, 26, 21, 26, 25,
            39, 36, 39, 36, 27, 16, 27, 24, 39, 36, 39, 36, 27, 16, 27, 24,
            38, 37, 38, 37, 17, 41, 17, 43, 38, 37, 38, 37, 26, 21, 26, 25,
            3, 4, 3, 4, 15, 40, 15, 20, 3, 4, 3, 4, 15, 40, 15, 20,
            5, 28, 5, 28, 29, 10, 29, 23, 5, 28, 5, 28, 31, 11, 31, 32,
            3, 4, 3, 4, 15, 40, 15, 20, 3, 4, 3, 4, 15, 40, 15, 20,
            2, 30, 2, 30, 9, 47, 9, 22, 2, 30, 2, 30, 14, 44, 14, 6,
            39, 36, 39, 36, 27, 16, 27, 24, 39, 36, 39, 36, 27, 16, 27, 24,
            38, 37, 38, 37, 17, 41, 17, 43, 38, 37, 38, 37, 26, 21, 26, 25,
            39, 36, 39, 36, 27, 16, 27, 24, 39, 36, 39, 36, 27, 16, 27, 24,
            38, 37, 38, 37, 17, 41, 17, 43, 38, 37, 38, 37, 26, 21, 26, 25,
            3, 0, 3, 0, 15, 42, 15, 12, 3, 0, 3, 0, 15, 42, 15, 12,
            5, 8, 5, 8, 29, 35, 29, 33, 5, 8, 5, 8, 31, 34, 31, 7,
            3, 0, 3, 0, 15, 42, 15, 12, 3, 0, 3, 0, 15, 42, 15, 12,
            2, 1, 2, 1, 9, 45, 9, 19, 2, 1, 2, 1, 14, 18, 14, 13
    };

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
        Integer[] indices = new Integer[atlasIndex412raw.length];
        for (int i = 0; i < atlasIndex412raw.length; i++) {
            indices[i] = i;
        }

        for (int i = 1; i < indices.length; i++) {
            int key = indices[i];
            int keyValue = atlasIndex412raw[key];
            int j = i - 1;

            while (j >= 0 && atlasIndex412raw[indices[j]] > keyValue) {
                indices[j + 1] = indices[j];
                j = j - 1;
            }
            indices[j + 1] = key;
        }

        for (int i = 0; i < indices.length; i++) {
            atlasIndex412[indices[i]] = i;
        }

        for (int i = 0; i < atlasIndex412raw.length; i++) {
            atlasIndex412map.put(atlasIndex412raw[i], atlasIndex412[i]);
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
