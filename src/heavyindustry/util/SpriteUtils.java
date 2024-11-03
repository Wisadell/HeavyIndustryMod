package heavyindustry.util;

import arc.graphics.g2d.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;

import java.util.*;

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
        try {
            Integer[] indices = new Integer[atlasIndex412raw.length];
            for (int i = 0; i < atlasIndex412raw.length; i++) {
                indices[i] = i;
            }

            Arrays.sort(indices, Comparator.comparingInt(a -> atlasIndex412raw[a]));

            for (int i = 0; i < indices.length; i++) {
                atlasIndex412[indices[i]] = i;
            }

            for (int i = 0; i < atlasIndex412raw.length; i++) {
                atlasIndex412map.put(atlasIndex412raw[i], atlasIndex412[i]);
            }
        } catch (Throwable e) {
            Log.err(e);
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
