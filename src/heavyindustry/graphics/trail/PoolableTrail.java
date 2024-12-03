package heavyindustry.graphics.trail;

import arc.util.pooling.Pool.*;
import arc.util.pooling.*;
import mindustry.graphics.*;

public class PoolableTrail extends Trail implements Poolable {
    private PoolableTrail() {
        super(0);
    }

    @Override
    public PoolableTrail copy() {
        return null;
    }

    public static PoolableTrail copy(int length) {
        PoolableTrail trail = Pools.obtain(PoolableTrail.class, PoolableTrail::new);
        trail.length = length;

        return trail;
    }

    @Override
    public void reset() {
        clear();
    }
}
