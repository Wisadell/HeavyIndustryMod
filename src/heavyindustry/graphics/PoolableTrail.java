package heavyindustry.graphics;

import arc.util.pooling.*;
import arc.util.pooling.Pool.*;
import mindustry.graphics.*;

public class PoolableTrail extends Trail implements Poolable {
    private PoolableTrail() {
        super(0);
    }

    @Override
    public Trail copy() {
        return null;
    }

    public static PoolableTrail get(int length){
        PoolableTrail trail = Pools.obtain(PoolableTrail.class, PoolableTrail::new);
        trail.length = length;

        return trail;
    }

    @Override
    public void reset() {
        clear();
    }
}
