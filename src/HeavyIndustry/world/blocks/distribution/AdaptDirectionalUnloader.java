package HeavyIndustry.world.blocks.distribution;

import mindustry.world.blocks.distribution.DirectionalUnloader;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

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
