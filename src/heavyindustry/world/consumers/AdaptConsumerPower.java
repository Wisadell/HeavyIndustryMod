package heavyindustry.world.consumers;

import arc.func.*;
import mindustry.gen.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;

public class AdaptConsumerPower extends ConsumePower {
    private final Floatf<Building> usage;

    public AdaptConsumerPower(Floatf<Building> usage){
        super(0, 0, false);
        this.usage = usage;
    }

    @Override
    public float requestedPower(Building entity){
        return usage.get(entity);
    }

    @Override
    public void display(Stats stats){}

    public float efficiency(Building build){
        return usage.get(build) != 0? build.power.status : 1f;
    }
}
