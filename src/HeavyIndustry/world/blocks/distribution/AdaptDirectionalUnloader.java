package HeavyIndustry.world.blocks.distribution;

import mindustry.world.blocks.distribution.DirectionalUnloader;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

public class AdaptDirectionalUnloader extends DirectionalUnloader {
    public AdaptDirectionalUnloader(String name) {
        super(name);
        buildType = AdaptDirectionalUnloaderBuild::new;
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.remove(Stat.speed);
        stats.add(Stat.speed, speed, StatUnit.itemsSecond);
    }

    public class AdaptDirectionalUnloaderBuild extends DirectionalUnloaderBuild{
        private float counter;

        @Override
        public void updateTile(){
            counter += edelta();
            final float limit = 60f / speed;

            while(counter >= limit){
                unloadTimer = speed;
                super.updateTile();
                counter -= limit;
            }
        }
    }
}
