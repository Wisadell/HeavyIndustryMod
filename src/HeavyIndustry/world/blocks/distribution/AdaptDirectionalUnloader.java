package HeavyIndustry.world.blocks.distribution;

import mindustry.world.blocks.distribution.DirectionalUnloader;

/**
 * The ultimate dream of Saturated Firepower.
 */
public class AdaptDirectionalUnloader extends DirectionalUnloader {
    public AdaptDirectionalUnloader(String name) {
        super(name);
        buildType = AdaptDirectionalUnloaderBuild::new;
    }

    public class AdaptDirectionalUnloaderBuild extends DirectionalUnloaderBuild{
        private float counter;

        @Override
        public void updateTile(){
            counter += edelta();
            final float limit = speed;

            while(counter >= limit){
                unloadTimer = speed;
                super.updateTile();
                counter -= limit;
            }
        }
    }
}
