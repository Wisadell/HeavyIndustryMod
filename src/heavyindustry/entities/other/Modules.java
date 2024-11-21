package heavyindustry.entities.other;

import arc.func.*;
import arc.math.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.io.*;
import mindustry.world.meta.*;
import mindustry.world.modules.*;
import heavyindustry.entities.other.Components.*;
import heavyindustry.entities.other.Comsunes.*;
import heavyindustry.entities.other.Chains.*;
import heavyindustry.entities.other.Producers.*;

import java.util.*;

@SuppressWarnings({"rawtypes", "unchecked"})
public final class Modules {
    /** The consumer module of the producer is used to centrally process the material requirements of the blocks, providing optional requirements and their special triggers. */
    public static class BaseConsumeModule extends BlockModule{
        private static final float[] ZERO = {0};

        protected final ConsumerBuildComp entity;
        protected final ObjectMap<BaseConsumers, float[]> optProgress = new ObjectMap<>();
        protected final ObjectMap<BaseConsumers, float[]> optEfficiency = new ObjectMap<>();

        public BaseConsumers current;
        public BaseConsumers optionalCurr;
        public boolean valid;

        public float consEfficiency, powerOtherEff;

        private float powerCons;

        public BaseConsumeModule(ConsumerBuildComp entity){
            this.entity = entity;
            current = entity.getConsumerBlock().consumers() != null && entity.consumeCurrent() != -1?
                    entity.getConsumerBlock().consumers().get(entity.consumeCurrent()): null;
        }

        public void build(Table table){
            if(current != null) for(BaseConsume cons: current.all()){
                cons.build(entity.getBuilding(ConsumerBuildComp.class), table);
            }
        }

        public Seq<BaseConsumers> get(){
            return entity.getConsumerBlock().consumers();
        }

        public Seq<BaseConsumers> getOptional(){
            return entity.getConsumerBlock().optionalCons();
        }

        public BlockStatus status(){
            if(!entity.shouldConsume()){
                return BlockStatus.noOutput;
            }

            if(!valid){
                return BlockStatus.noInput;
            }

            return BlockStatus.active;
        }

        public boolean hasConsume(){
            return !get().isEmpty();
        }

        public boolean hasOptional(){
            return !getOptional().isEmpty();
        }

        public float getOptionalEff(BaseConsumers consumers){
            return optEfficiency.get(consumers, ZERO)[0];
        }

        public float getPowerUsage(){
            return powerCons*((BaseConsume<ConsumerBuildComp>)current.get(ConsumeType.power)).multiple(entity)*powerOtherEff;
        }

        public void setCurrent(){
            current = entity.consumeCurrent() == -1? null: get().get(entity.consumeCurrent());
        }

        public void update(){
            setCurrent();
            powerCons = 0;
            if((!hasOptional() && !hasConsume())) return;

            valid = true;

            //Only perform consumption updates when the consumption list is selected.
            if(current != null){
                boolean preValid = valid();

                for(Boolf<ConsumerBuildComp> b: current.valid){
                    valid &= b.get(entity);
                }
                consEfficiency = powerOtherEff = valid? 1: 0;
                for(BaseConsume cons: current.all()){
                    float eff = cons.efficiency(entity.getBuilding(ConsumerBuildComp.class));
                    if(cons instanceof ConsumePower){
                        powerCons += ((ConsumePower) cons).requestedPower(entity.getBuild());
                    }
                    else powerOtherEff *= eff;

                    consEfficiency *= eff;
                    valid &= eff > 0.0001f;

                    if(!valid){
                        consEfficiency = 0;
                        break;
                    }

                    if(preValid && entity.shouldConsume()){
                        cons.update(entity.getBuilding(ConsumerBuildComp.class));
                    }
                }
            }

            updateOptional();
        }

        public void updateOptional() {
            if(getOptional() != null){
                BaseConsumers cons;
                boolean onlyOne = entity.getConsumerBlock().oneOfOptionCons();
                for(int id=0; id<getOptional().size; id++){
                    cons = getOptional().get(id);

                    float optionalEff = 1;
                    for(Boolf<ConsumerBuildComp> b: cons.valid){
                        optionalEff *= b.get(entity)? 1: 0;
                    }
                    for(BaseConsume c: cons.all()){
                        optionalEff *= c.efficiency(entity.getBuilding(ConsumerBuildComp.class));
                    }
                    optEfficiency.get(cons, () -> new float[1])[0] = optionalEff;

                    if(optionalEff > 0.0001f){
                        optionalCurr = cons;

                        if(!entity.shouldConsumeOptions() || (!cons.optionalAlwaysValid && !valid)) continue;
                        for(BaseConsume c: cons.all()){
                            c.update(entity.getBuilding(ConsumerBuildComp.class));
                            if(c instanceof ConsumePower) powerCons += ((ConsumePower) c).requestedPower(entity.getBuild());
                        }

                        if(cons.craftTime > 0){
                            float[] arr = optProgress.get(cons, () -> new float[]{0});
                            arr[0] += 1/cons.craftTime*cons.delta(entity);
                            while(arr[0] >= 1){
                                arr[0] %= 1;
                                triggerOpt(id);
                            }
                        }

                        cons.optionalDef.get(entity, cons);
                        if(onlyOne) break;
                    }
                }
            }
        }

        public float consDelta(){
            return current == null? 0: current.delta(entity);
        }

        /** Retrieve the consumption list of the specified index. */
        public BaseConsumers get(int index){
            return get().get(index);
        }

        /** Retrieve the optional consumption list at the specified index. */
        public BaseConsumers getOptional(int index){
            return index < getOptional().size? getOptional().get(index) : null;
        }

        /** Trigger method for triggering the current main consumption item. */
        public void trigger(){
            if(current != null){
                for(BaseConsume cons: current.all()){
                    cons.consume(entity.getBuilding(ConsumerBuildComp.class));
                }
                for(Cons<ConsumerBuildComp> trigger: current.triggers){
                    trigger.get(entity);
                }
            }
        }

        /** Trigger method for triggering an optional consumable item. */
        public void triggerOpt(int id){
            if(getOptional() != null && getOptional().size > id){
                BaseConsumers cons = getOptional().get(id);
                for(BaseConsume c: cons.all()){
                    c.consume(entity.getBuilding(ConsumerBuildComp.class));
                }
                for(Cons<ConsumerBuildComp> trigger: cons.triggers){
                    trigger.get(entity);
                }
            };
        }

        /** Is the current consumption list available for all items except for the specified consumption item. */
        public boolean excludeValid(ConsumeType type){
            boolean temp = true;
            for(BaseConsume cons: current.all()){
                if(cons.type() == type) continue;
                temp &= cons.efficiency(entity.getBuilding(ConsumerBuildComp.class)) > 0.0001f;
            }
            return temp;
        }

        /** Is the current consumption list available. */
        public boolean valid(){
            return valid && entity.getBuilding().enabled;
        }

        /** The current consumption list specifies whether the consumption item is available. */
        public boolean valid(ConsumeType type){
            return current.get(type) != null && current.get(type).efficiency(entity.getBuilding(ConsumerBuildComp.class)) > 0.0001;
        }

        /** Is the specified consumption list available. */
        public boolean valid(int index){
            if(index >= get().size) return false;

            for(BaseConsume c: get().get(index).all()){
                if(c.efficiency(entity.getBuilding(ConsumerBuildComp.class)) < 0.0001f) return false;
            }

            return true;
        }

        @Override
        public void write(Writes write) {
            write.bool(valid);
        }

        @Override
        public void read(Reads read){
            valid = read.bool();
        }
    }

    /** Producer's output module, used for centralized processing of block production work. */
    public static class BaseProductModule extends BlockModule {
        public BaseConsumeModule consumer;

        public final ProducerBuildComp entity;
        public BaseProducers current;
        public boolean valid;

        public BaseProductModule(ProducerBuildComp entity){
            this.entity = entity;
            consumer = entity.consumer();
            current = entity.produceCurrent() != -1? entity.getProducerBlock().producers().get(entity.produceCurrent()) : null;
        }

        public Seq<BaseProducers> get(){
            return entity.getProducerBlock().producers();
        }

        public void trigger(){
            if(current != null) for(BaseProduce prod: current.all()){
                prod.produce(entity.getBuilding(ProducerBuildComp.class));
            }
        }

        public float getPowerProduct(){
            if(current == null) return 0;
            return current.get(ProduceType.power).powerProduction*entity.consumer().powerOtherEff*(Mathf.num(entity.shouldConsume() && entity.consumeValid())*((BaseProduce<ProducerBuildComp>)current.get(ProduceType.power)).multiple(entity));
        }

        public void setCurrent(){
            current = entity.consumeCurrent() == -1? null: get().get(entity.consumeCurrent());
        }

        public void update(){
            setCurrent();

            valid = true;
            //Output update only occurs when the production list is selected.
            if(current != null){
                setCurrent();
                boolean doprod = entity.consumeValid() && entity.shouldConsume() && entity.shouldProduct();
                boolean preValid = valid();

                boolean anyValid = false;
                for(BaseProduce prod : current.all()){
                    boolean v = prod.valid(entity.getBuilding(ProducerBuildComp.class));
                    anyValid |= v;
                    valid &= !prod.blockWhenFull || v;
                    if(doprod && preValid && v){
                        prod.update(entity.getBuilding(ProducerBuildComp.class));
                    }
                }
                if(!anyValid) valid = false;
            }

            //Export products externally at all times.
            doDump(entity);
        }

        public void doDump(ProducerBuildComp entity){
            if(current != null){
                for(BaseProduce prod: current.all()){
                    prod.dump(entity.getBuilding(ProducerBuildComp.class));
                }
            }
        }

        public boolean valid(){
            return valid && entity.getBuilding().enabled;
        }

        @Override
        public void write(Writes write) {
            write.bool(valid);
        }

        @Override
        public void read(Reads read){
            valid = read.bool();
        }
    }

    public static class ChainsModule extends BlockModule implements ExtraVariableComp {
        public ChainsBuildComp entity;
        public ChainsContainer container;

        public ChainsModule(ChainsBuildComp entity){
            this.entity = entity;
        }

        public ChainsContainer newContainer(){
            ChainsContainer old = entity.chains().container;

            entity.chains().container = new ChainsContainer();
            entity.containerCreated(old);

            entity.chains().container.add(entity);

            return entity.chains().container;
        }

        public void each(Cons<ChainsBuildComp> cons){
            for(ChainsBuildComp other: container.all){
                cons.get(other);
            }
        }

        @Override
        public Map<String, Object> extra() {
            return container.extra();
        }

        /**@deprecated Please use setVar(String, Object)
         * @see ChainsModule#setVar(String, Object) */
        @Deprecated
        public void putVar(String key, Object obj){
            container.setVar(key, obj);
        }

        @Override
        public void write(Writes write){}
    }
}
