package heavyindustry.gen;

import mindustry.gen.*;
import mindustry.world.blocks.storage.CoreBlock.*;

public interface NoCoreDepositc extends Unitc {
    @Override
    default CoreBuild closestCore(){
         return null;
    }
}
