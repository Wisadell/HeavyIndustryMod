package heavyindustry.graphics.trail;

import arc.util.pooling.Pool.*;
import arc.util.pooling.*;
import mindustry.graphics.*;

public class PoolableTrail extends Trail implements Poolable {
    private PoolableTrail(int length) {
        super(length);
    }

    public static PoolableTrail copy(int length) {
        return Pools.obtain(PoolableTrail.class, () -> new PoolableTrail(length));
    }

    @Override
    public PoolableTrail copy() {
        PoolableTrail out = new PoolableTrail(length);
        out.points.addAll(points);
        out.lastX = lastX;
        out.lastY = lastY;
        return out;
    }

    @Override
    public void reset() {
        clear();
    }
}
