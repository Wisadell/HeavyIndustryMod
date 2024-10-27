package heavyindustry.graphics;

import mindustry.graphics.*;
import mindustry.graphics.CacheLayer.*;

/**
 * Defines the {@linkplain CacheLayer cache layer}s this mod offers.
 * @author Wisadell
 */
public class HICacheLayer {
    public static ShaderLayer dalani,brine,nanofluid;

    /** Loads the cache layers. */
    public static void init() {
        dalani = new ShaderLayer(HIShaders.dalani);
        brine = new ShaderLayer(HIShaders.brine);
        nanofluid = new ShaderLayer(HIShaders.nanofluid);
        CacheLayer.add(dalani,brine,nanofluid);
    }
}
