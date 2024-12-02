package heavyindustry.graphics;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.gl.*;
import arc.math.*;
import mindustry.graphics.*;

public class ShaderTrail extends Trail {
    public Shader shader;

    public ShaderTrail(int length, Shader shader) {
        super(length);
        this.shader = shader;
    }

    @Override
    public void draw(Color color, float width) {
        float[] items = points.items;

        for (int i = 0; i < points.size; i += 3) {
            float x1 = items[i], y1 = items[i + 1], w1 = items[i + 2];
            float x2, y2, w2;

            //last position is always lastX/Y/W
            if (i < points.size - 3) {
                x2 = items[i + 3];
                y2 = items[i + 4];
                w2 = items[i + 5];
            } else {
                x2 = lastX;
                y2 = lastY;
                w2 = lastW;
            }

            float z2 = -Angles.angleRad(x1, y1, x2, y2);
            //end of the trail (i = 0) has the same angle as the next.
            if (w1 <= 0.001f || w2 <= 0.001f) continue;

            Draw.blit(shader);

            lastAngle = z2;
        }

        Draw.reset();
    }

    @Override
    public ShaderTrail copy() {
        ShaderTrail out = new ShaderTrail(length, shader);
        out.points.addAll(points);
        out.lastX = lastX;
        out.lastY = lastY;
        return out;
    }
}
