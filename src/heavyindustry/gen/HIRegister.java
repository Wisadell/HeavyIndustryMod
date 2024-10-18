package heavyindustry.gen;

import heavyindustry.world.blocks.storage.*;
import arc.func.*;
import arc.struct.*;
import mindustry.gen.*;

public class HIRegister {
    private static final ObjectIntMap<Class<? extends Entityc>> ids = new ObjectIntMap<>();

    private static final ObjectMap<String, Prov<? extends Entityc>> map = new ObjectMap<>();

    public static <T extends Entityc> void register(String name, Class<T> type, Prov<? extends T> prov){
        map.put(name, prov);
        ids.put(type, EntityMapping.register(name, prov));
    }

    public static int getID(Class<? extends Entityc> type){
        return ids.get(type, -1);
    }

    public static void register(){
    }

    public static void clear(){
        CoreStorageBlock.clear();
    }
}
