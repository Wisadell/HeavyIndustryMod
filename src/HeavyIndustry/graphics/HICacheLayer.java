package HeavyIndustry.graphics;

import mindustry.graphics.CacheLayer;
import mindustry.graphics.CacheLayer.ShaderLayer;

public class HICacheLayer {
    public static ShaderLayer nanofluid;
    public static void init() {
        nanofluid = new ShaderLayer(HIShaders.nanofluid);
        CacheLayer.add(nanofluid);
    }
}
