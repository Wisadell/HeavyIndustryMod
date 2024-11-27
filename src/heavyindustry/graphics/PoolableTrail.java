package heavyindustry.graphics;

import arc.util.pooling.*;
import arc.util.pooling.Pool.*;
import mindustry.graphics.*;

public class PoolableTrail extends Trail implements Poolable {
    /**
     * @deprecated deprecated.
     * @see PoolableTrail#copy(int) copy(int length)
     */
    @Deprecated
    public PoolableTrail(int length) {
        super(length);
    }

    private PoolableTrail() {
        super(0);
    }

    /**
     * @deprecated deprecated.
     * @see PoolableTrail#copy(int) copy(int length)
     */
    @Deprecated
    @Override
    public PoolableTrail copy() {
        PoolableTrail out = new PoolableTrail(length);
        out.points.addAll(points);
        out.lastX = lastX;
        out.lastY = lastY;
        return out;
    }

    public static PoolableTrail copy(int length){
        PoolableTrail trail = Pools.obtain(PoolableTrail.class, PoolableTrail::new);
        trail.length = length;

        return trail;
    }

    @Override
    public void reset() {
        clear();
    }
}
