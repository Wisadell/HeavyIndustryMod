package heavyindustry.world.consumers;

import arc.func.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.consumers.*;

public class ConsumeLiquidDynamicF extends Consume {
    public final Func<Building, LiquidStack[]> liquids;

    @SuppressWarnings("unchecked")
    public <T extends Building> ConsumeLiquidDynamicF(Func<T, LiquidStack[]> liquids) {
        this.liquids = (Func<Building, LiquidStack[]>) liquids;
    }

    @Override
    public void apply(Block block) {
        if (liquids != null) {
            block.hasLiquids = true;
        }
    }

    @Override
    public void update(Building build) {
        if (liquids != null && liquids.get(build) != null) {
            float mult = multiplier.get(build);
            for (LiquidStack stack : liquids.get(build)) {
                build.liquids.remove(stack.liquid, stack.amount * build.edelta() * mult);
            }
        }
    }

    @Override
    public float efficiency(Building build) {
        if (liquids != null && liquids.get(build) != null) {
            float ed = build.edelta();
            if (ed <= 0.00000001f) return 0f;
            float min = 1f;
            for (LiquidStack stack : liquids.get(build)) {
                min = Math.min(build.liquids.get(stack.liquid) / (stack.amount * ed * multiplier.get(build)), min);
            }
            return min;
        } else {
            return 1f;
        }
    }
}
