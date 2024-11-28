package heavyindustry.gen;

import mindustry.gen.*;

public interface DrillModulec extends Buildingc {
    DrillFc drillBuild();

    float smoothWarmup();

    float targetWarmup();

    void drillBuild(DrillFc drillBuild);

    void smoothWarmup(float smoothWarmup);

    void targetWarmup(float targetWarmup);

    void apply(DrillFc tile);

    boolean canApply(DrillFc tile);
}
