package heavyindustry.graphics;

import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;

/**
 * Draws 2d region with applying {@link Mat3D}
 */
public final class Draw3d {
    public static final Mat3D tmpMat1 = new Mat3D();
    public static final Mat3D tmpMat2 = new Mat3D();
    public static final Mat3D tmpMat3 = new Mat3D();
    public static final Mat3D tmpMat4 = new Mat3D();
    public static final Mat3D tmpMat5 = new Mat3D();
    public static final Vec3 v1 = new Vec3(), v2 = new Vec3(), v3 = new Vec3(), v4 = new Vec3(), v5 = new Vec3(), v6 = new Vec3(), v7 = new Vec3();
    static float[] vertices = new float[3 * 2 * 4];

    /** Draw3d should not be instantiated. */
    private Draw3d() {}

    public static void rect(Mat3D mat3D, TextureRegion region, float x, float y, float width, float height, float rotation) {
        float originX = width / 2f;
        float originY = height / 2f;
        rect(mat3D, region, x, y, width, height, rotation, originX, originY);
    }

    public static void rect(Mat3D mat3D, TextureRegion region, float x, float y, float width, float height, float rotation, float originX, float originY) {
        int idx = 0;
        //bottom left and top right corner points relative to origin
        float worldOriginX = x + originX;
        float worldOriginY = y + originY;
        float fx = -originX;
        float fy = -originY;
        float fx2 = width - originX;
        float fy2 = height - originY;

        // rotate
        float cos = Mathf.cosDeg(rotation);
        float sin = Mathf.sinDeg(rotation);
        v5.set(worldOriginX, worldOriginY, 0);
        setPoint(mat3D, cos * fx - sin * fy, sin * fx + cos * fy, v1);
        setPoint(mat3D, cos * fx - sin * fy2, sin * fx + cos * fy2, v2);
        setPoint(mat3D, cos * fx2 - sin * fy2, sin * fx2 + cos * fy2, v3);
        setPoint(mat3D, cos * fx2 - sin * fy, sin * fx2 + cos * fy, v4);
        setPoint(mat3D, 0, 0, v6);


        float u = region.u;
        float v = region.v2;
        float u2 = region.u2;
        float _v2 = region.v;

        float color = Draw.getColor().toFloatBits();
        float mixColor = Draw.getMixColor().toFloatBits();

        float u5 = (u + u2) / 2f;
        float _v5 = (v + _v2) / 2f;
        idx = vertex(idx, v1, u, v, color, mixColor);
        idx = vertex(idx, v2, u, _v2, color, mixColor);
        doubleLast(idx, v6, color, mixColor, u5, _v5);
        Draw.vert(region.texture, vertices, 0, vertices.length);
        idx = 0;
        idx = vertex(idx, v2, u, _v2, color, mixColor);
        idx = vertex(idx, v3, u2, _v2, color, mixColor);
        doubleLast(idx, v6, color, mixColor, u5, _v5);
        Draw.vert(region.texture, vertices, 0, vertices.length);
        idx = 0;
        idx = vertex(idx, v3, u2, _v2, color, mixColor);
        idx = vertex(idx, v4, u2, v, color, mixColor);
        doubleLast(idx, v6, color, mixColor, u5, _v5);
        Draw.vert(region.texture, vertices, 0, vertices.length);
        idx = 0;
        idx = vertex(idx, v4, u2, v, color, mixColor);
        idx = vertex(idx, v1, u, v, color, mixColor);
        doubleLast(idx,v6 , color, mixColor, u5, _v5);
        Draw.vert(region.texture, vertices, 0, vertices.length);
    }

    private static void doubleLast(int idx, Vec3 vector, float color, float mixColor, float u5, float _v5) {
        idx = vertex(idx, vector, u5, _v5, color, mixColor);
        idx = vertex(idx, vector, u5, _v5, color, mixColor);
    }

    private static int vertex(int idx, Vec3 v1, float u, float v, float color, float mixColor) {
        vertices[idx++] = v1.x;
        vertices[idx++] = v1.y;
        vertices[idx++] = color;
        vertices[idx++] = u;
        vertices[idx++] = v;
        vertices[idx++] = mixColor;
        return idx;
    }

    private static void setPoint(Mat3D mat3D, float x, float y, Vec3 v) {
        v.set(x, y, 0);
        float len2 = v.len();
        Mat3D.prj(v, mat3D);
        v.x = transformCoord(v.x, v.z, len2);
        v.y = transformCoord(v.y, v.z, len2);
        v.add(v5);
    }

    private static float transformCoord(float coord, float z, float len2) {
        if (len2 == 0) return coord;
        return z > 0 ? coord / (z / len2 + 1) : coord * (-z / len2 + 1);
    }
}
