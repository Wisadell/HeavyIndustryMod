package heavyindustry.world.blocks.distribution;

import mindustry.world.blocks.distribution.*;

/**
 * A directional unloader that is not affected by game frame rates.
 * @author Wisadell
 */
public class AdaptDirectionalUnloader extends DirectionalUnloader {
    public AdaptDirectionalUnloader(String name) {
        super(name);
    }

    public class AdaptDirectionalUnloaderBuild extends DirectionalUnloaderBuild {
        private float counter;

        /** Make its uninstallation speed no longer affected by frame rate. */
        @Override
        public void updateTile() {
            counter += edelta();
            final float limit = speed;

            while (counter >= limit) {
                unloadTimer = speed;
                super.updateTile();
                counter -= limit;
            }
        }
    }
}
