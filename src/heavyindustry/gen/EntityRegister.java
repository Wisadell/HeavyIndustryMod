package heavyindustry.gen;

import arc.func.*;
import arc.struct.*;
import mindustry.gen.*;

public final class EntityRegister {
    private static final ObjectIntMap<Class<? extends Entityc>> ids = new ObjectIntMap<>();

    private static final ObjectMap<String, Prov<? extends Entityc>> map = new ObjectMap<>();

    public static <T extends Entityc> void reg(String name, Class<T> type, Prov<? extends T> prov) {
        map.put(name, prov);
        ids.put(type, EntityMapping.register(name, prov));
    }

    public static int getId(Class<? extends Entityc> type) {
        return ids.get(type, -1);
    }

    public static void load() {
        reg("PayloadLegsUnit", PayloadLegsUnit.class, PayloadLegsUnit::new);
        reg("BuildingTetherPayloadLegsUnit", BuildingTetherPayloadLegsUnit.class, BuildingTetherPayloadLegsUnit::new);
        reg("EnergyUnit", EnergyUnit.class, EnergyUnit::new);
        reg("UltFire", UltFire.class, UltFire::new);
    }
}
