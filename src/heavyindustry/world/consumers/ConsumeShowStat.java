package heavyindustry.world.consumers;

import arc.func.*;
import arc.scene.ui.layout.*;
import mindustry.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.consumers.*;
import heavyindustry.ui.display.*;

public class ConsumeShowStat extends Consume {
    public final Func<Building, ItemStack[]> items;
    public final Func<Building, LiquidStack[]> liquids;

    @SuppressWarnings("unchecked")
    public <T extends Building> ConsumeShowStat(Func<T, ItemStack[]> items, Func<T, LiquidStack[]> liquids) {
        this.items = (Func<Building, ItemStack[]>) items;
        this.liquids = (Func<Building, LiquidStack[]>) liquids;
    }

    @Override
    public void build(Building build, Table table) {
        ItemStack[][] currentItem = {items.get(build)};
        LiquidStack[][] currentLiquid = {liquids.get(build)};

        table.table(cont -> {
            table.update(() -> {
                if (currentItem[0] != items.get(build)) {
                    rebuild(build, cont);
                    currentItem[0] = items.get(build);
                }
                if (currentLiquid[0] != liquids.get(build)) {
                    rebuild(build, cont);
                    currentLiquid[0] = liquids.get(build);
                }
            });

            rebuild(build, cont);
        });
    }

    private void rebuild(Building build, Table table) {
        int i = 0;
        table.clear();

        if (items != null && items.get(build) != null) {
            for (ItemStack stack : items.get(build)) {
                table.add(new ReqImage(new ItemImage(stack.item.uiIcon, Math.round(stack.amount * multiplier.get(build))),
                        () -> build.items != null && build.items.has(stack.item, Math.round(stack.amount * multiplier.get(build))))).padRight(8).left();
                if (++i % 4 == 0) table.row();
            }
        }

        if (liquids != null && liquids.get(build) != null) {
            for (LiquidStack stack : liquids.get(build)) {
                table.add(new ReqImage(new LiquidDisplayF(stack.liquid, 0f, false),
                        () -> build.liquids != null && build.liquids.get(stack.liquid) > 0)).size(Vars.iconMed).padRight(8);
                if (++i % 4 == 0) table.row();
            }
        }
    }
}
