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
        protected float counter;

        @Override
        public void updateTile() {
            counter += edelta();

            while (counter >= speed) {
                unloadTimer = speed;
                super.updateTile();
                counter -= speed;
            }
        }
    }
}
