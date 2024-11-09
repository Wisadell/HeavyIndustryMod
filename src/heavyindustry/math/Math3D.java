package heavyindustry.math;

import mindustry.*;

import static arc.Core.*;
import static arc.math.Mathf.*;

public final class Math3D {
    public static final float horiToVerti = 1f / 48f;

    public static float xOffset(float x, float height){
        return (x - camera.position.x) * hMul(height);
    }

    public static float yOffset(float y, float height){
        return (y - camera.position.y) * hMul(height);
    }

    public static float hMul(float height){
        return height(height) * Vars.renderer.getDisplayScale();
    }

    public static float height(float height){
        return height * horiToVerti;
    }

    public static float layerOffset(float x, float y){
        float max = Math.max(camera.width, camera.height);
        return -dst(x, y, camera.position.x, camera.position.y) / max / 1000f;
    }
}
