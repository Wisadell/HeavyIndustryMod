package HeavyIndustry.util;

import mindustry.type.ItemStack;
import mindustry.type.UnitType;
import mindustry.world.blocks.units.UnitFactory;

public class HIUnitPlan extends UnitFactory.UnitPlan {
    public UnitType resultUnit;
    public HIUnitPlan(UnitType unit, UnitType resultUnit, float time, ItemStack[] requirements) {
        super(unit, time, requirements);
        this.resultUnit = resultUnit;
    }
}
