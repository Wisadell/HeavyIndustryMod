package heavyindustry.gen;

import mindustry.gen.*;

public interface DrillModulec extends Buildingc {
    Drillc drillBuild();

    float smoothWarmup();

    float targetWarmup();

    void drillBuild(Drillc drillBuild);

    void smoothWarmup(float value);

    void targetWarmup(float value);

    void apply(Drillc value);

    boolean canApply(Drillc value);
}
