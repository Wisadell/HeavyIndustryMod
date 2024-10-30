package heavyindustry.entities.abilities;

import arc.util.*;
import mindustry.entities.abilities.*;
import mindustry.gen.*;

import static arc.Core.*;

public class DeathAbility extends Ability {
    @Override
    public String localized() {
        return bundle.get("ability.death");
    }

    @Override
    public void update(Unit unit) {
        Groups.bullet.intersect(unit.x - 200, unit.y - 200, 200 * 2, 200 * 2, b -> {
            if (b.team != unit.team && unit.within(b, 200)){
                @Nullable Unit owner = null;
                if(b.owner instanceof Unit) owner = (Unit)b.owner;
                if(b.type.damage > unit.maxHealth / 2f || b.type.splashDamage > unit.maxHealth / 2f){
                    if(owner != null) owner.kill();
                    b.remove();
                }
                if(owner != null && (owner.maxHealth > unit.maxHealth * 2 || owner.type.armor >= unit.type.armor * 2)) owner.kill();

                Building building = null;
                if(b.owner instanceof Building) building = (Building) b.owner;
                if(b.type.damage > unit.maxHealth / 2f || b.type.splashDamage > unit.maxHealth / 2f){
                    if(building != null) building.kill();
                    b.remove();
                }
                if(building != null && building.health > unit.maxHealth * 2) building.kill();
            }
        });
    }
}
