package heavyindustry.graphics.g2d;

import arc.graphics.*;
import arc.graphics.g2d.*;

public abstract class BaseBatch extends Batch {
    protected final Color color = new Color(1, 1, 1, 1);
    protected final Color mixColor = Color.clear;

    protected Mesh mesh;

    protected boolean sortAscending = true;

    @Override
    protected void z(float z) {
        this.z = sortAscending ? z : -z;
    }

    /** Sets the sorting order. The batch must be flushed for this to take effect properly. */
    protected void setSortAscending(boolean ascend) {
        sortAscending = ascend;
    }

    protected void setColor(float r, float g, float b, float a) {
        color.set(r, g, b, a);
        colorPacked = color.toFloatBits();
    }

    protected Color getColor() {
        return color;
    }

    protected void setColor(Color tint) {
        color.set(tint);
        colorPacked = tint.toFloatBits();
    }

    @Override
    protected void setPackedColor(float packedColor) {
        this.color.abgr8888(packedColor);
        super.setPackedColor(packedColor);
    }

    protected void setMixColor(float r, float g, float b, float a) {
        mixColor.set(r, g, b, a);
        mixColorPacked = mixColor.toFloatBits();
    }

    protected Color getMixColor() {
        return mixColor;
    }

    protected void setMixColor(Color tint) {
        mixColor.set(tint);
        mixColorPacked = tint.toFloatBits();
    }

    @Override
    protected void setPackedMixColor(float packedColor) {
        this.mixColor.abgr8888(packedColor);
        super.setPackedMixColor(packedColor);
    }

    @Override
    public void dispose() {
        if (mesh != null) {
            mesh.dispose();
        }
        super.dispose();
    }
}
