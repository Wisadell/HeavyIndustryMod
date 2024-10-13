package heavyindustry.world.consumers;

import arc.func.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.consumers.*;

public class ConsumeItemDynamicF extends Consume {
    public final Func<Building, ItemStack[]> items;

    @SuppressWarnings("unchecked")
    public <T extends Building> ConsumeItemDynamicF(Func<T, ItemStack[]> items) {
        this.items = (Func<Building, ItemStack[]>) items;
    }

    @Override
    public void apply(Block block) {
        if (items != null) {
            block.hasItems = true;
            block.acceptsItems = true;
        }
    }

    @Override
    public void trigger(Building build) {
        if (items != null && items.get(build) != null) {
            for (ItemStack stack : items.get(build)) {
                build.items.remove(stack.item, Math.round(stack.amount * multiplier.get(build)));
            }
        }
    }

    @Override
    public float efficiency(Building build) {
        if (items != null && items.get(build) != null) {
            return build.consumeTriggerValid() || build.items.has(items.get(build), multiplier.get(build)) ? 1f : 0f;
        } else {
            return 1f;
        }
    }
}
