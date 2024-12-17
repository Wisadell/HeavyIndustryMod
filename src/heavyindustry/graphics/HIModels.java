package heavyindustry.graphics;

import arc.files.*;

import static mindustry.Vars.*;

public final class HIModels {
    /** HIModels should not be instantiated. */
    private HIModels() {}

    /** Loads the 3D models. Client-side and main thread only! */
    public static void load() {
        //TODO
    }

    public static Fi file(String name) {
        return tree.get("scenes/" + name + ".gltf");
    }
}
