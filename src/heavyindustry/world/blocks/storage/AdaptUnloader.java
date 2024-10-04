package heavyindustry.world.blocks.storage;

import mindustry.world.blocks.storage.*;

/**
 * A unloader that is not affected by game frame rates.
 * @author Wisadell
 */
public class AdaptUnloader extends Unloader {
    public AdaptUnloader(String name) {
        super(name);
    }

    public class AdaptUnloaderBuild extends UnloaderBuild {
        private float counter;

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
