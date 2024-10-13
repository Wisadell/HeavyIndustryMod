package heavyindustry.core;

import arc.struct.*;
import arc.util.*;
import mindustry.world.consumers.*;

import java.lang.reflect.*;

import static mindustry.content.Blocks.*;

class HIReflector {
    static void load() {
        try {
            Field field = disassembler.getClass().getClassLoader().loadClass("mindustry.world.Block").getDeclaredField("consumeBuilder");
            field.setAccessible(true);

            @SuppressWarnings("unchecked")
            Seq<Consume> consumeBuilder = (Seq<Consume>) field.get(disassembler);

            consumeBuilder.removeAll(b -> b instanceof ConsumeItems);
        } catch (Exception e) {
            Log.err(e);
        }
    }
}
