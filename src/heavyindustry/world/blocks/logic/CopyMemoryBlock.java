package heavyindustry.world.blocks.logic;

import mindustry.world.blocks.logic.*;

/**
 * @author guiY
 */
public class CopyMemoryBlock extends MemoryBlock {
    public CopyMemoryBlock(String name) {
        super(name);

        config(Object[].class, (CopyMemoryBuild build, Object[] ds) -> {
            for(int i = 0; i < ds.length; i++){
                if(ds[i] instanceof Double d) build.memory[i] = d;
            }
        });
    }

    public class CopyMemoryBuild extends MemoryBuild {
        public Object[] objects = new Object[memoryCapacity];

        public void updateMemory() {
            for (int i = 0; i < memory.length; i++) {
                objects[i] = memory[i];
            }
        }

        @Override
        public Object[] config() {
            updateMemory();
            return objects;
        }
    }
}
