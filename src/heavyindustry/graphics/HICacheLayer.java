package heavyindustry.graphics;

import mindustry.graphics.*;
import mindustry.graphics.CacheLayer.*;

public class HICacheLayer {
    public static ShaderLayer nanofluid,dalani;
    public static void init() {
        nanofluid = new ShaderLayer(HIShaders.nanofluid);
        dalani = new ShaderLayer(HIShaders.dalani);
        CacheLayer.add(nanofluid,dalani);
    }
}
