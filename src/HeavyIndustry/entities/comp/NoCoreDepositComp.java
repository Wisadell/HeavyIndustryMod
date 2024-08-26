package HeavyIndustry.entities.comp;

//import mindustry.annotations.Annotations.*;
import mindustry.gen.*;
import mindustry.world.blocks.storage.*;

//@Component
public abstract class NoCoreDepositComp implements Unitc, Teamc {

    /**
     * If closestCore is null, it cannot deposit items into it. Kinda hacky but there's no other option.
     */
    //@Replace
    public CoreBlock.CoreBuild closestCore(){
        return null;
    }
}
