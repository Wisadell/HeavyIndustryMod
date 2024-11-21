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
