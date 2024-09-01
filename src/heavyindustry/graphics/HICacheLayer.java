package heavyindustry.graphics;

import mindustry.graphics.*;
import mindustry.graphics.CacheLayer.*;

public class HICacheLayer {
    public static ShaderLayer nanofluid;
    public static void init() {
        nanofluid = new ShaderLayer(HIShaders.nanofluid);
        CacheLayer.add(nanofluid);
    }
}
