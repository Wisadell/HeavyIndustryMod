package heavyindustry.gen;

import arc.func.*;
import arc.struct.*;
import mindustry.gen.*;

public final class EntityRegister {
    private static final ObjectIntMap<Class<? extends Entityc>> ids = new ObjectIntMap<>();

    private static final ObjectMap<String, Prov<? extends Entityc>> map = new ObjectMap<>();

    /** EntityRegister should not be instantiated. */
    private EntityRegister() {}

    public static <T extends Entityc> void register(String name, Class<T> type, Prov<? extends T> prov) {
        map.put(name, prov);
        ids.put(type, EntityMapping.register(name, prov));
    }

    public static int getId(Class<? extends Entityc> type) {
        return ids.get(type, -1);
    }

    public static void register() {
        register("PayloadLegsUnit", PayloadLegsUnit.class, PayloadLegsUnit::new);
        register("BuildingTetherPayloadLegsUnit", BuildingTetherPayloadLegsUnit.class, BuildingTetherPayloadLegsUnit::new);
        register("OrnitopterUnit", OrnitopterUnit.class, OrnitopterUnit::new);
        register("CopterUnit", CopterUnit.class, CopterUnit::new);
        register("EnergyUnit", EnergyUnit.class, EnergyUnit::new);
        register("UltFire", UltFire.class, UltFire::new);
    }
}
