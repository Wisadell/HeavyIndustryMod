package heavyindustry.graphics;

import mindustry.graphics.*;
import mindustry.graphics.CacheLayer.*;

/**
 * Defines the {@linkplain CacheLayer cache layer}s this mod offers.
 * @author Wisadell
 */
public final class HICacheLayer {
    public static ShaderLayer nanofluid,dalani;

    /** Loads the cache layers. */
    public static void init() {
        nanofluid = new ShaderLayer(HIShaders.nanofluid);
        dalani = new ShaderLayer(HIShaders.dalani);
        CacheLayer.add(nanofluid,dalani);
    }
}
