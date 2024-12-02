package heavyindustry.gen;

import mindustry.gen.*;

public interface DrillModulec extends Buildingc {
    DrillFc drillBuild();

    float smoothWarmup();

    float targetWarmup();

    void drillBuild(DrillFc drillBuild);

    void smoothWarmup(float value);

    void targetWarmup(float value);

    void apply(DrillFc value);

    boolean canApply(DrillFc value);
}
