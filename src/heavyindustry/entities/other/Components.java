package heavyindustry.entities.other;

import arc.func.*;
import arc.math.*;
import arc.math.geom.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.io.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.meta.*;
import mindustry.world.modules.*;
import heavyindustry.entities.other.Chains.*;
import heavyindustry.entities.other.Consumers.*;
import heavyindustry.entities.other.Modules.*;
import heavyindustry.entities.other.Producers.*;
import heavyindustry.func.*;
import heavyindustry.util.HIUtils.*;
import heavyindustry.world.meta.*;

import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;

@SuppressWarnings({"rawtypes", "unchecked"})
public final class Components {
    /**
     * The append variable interface is used to provide dynamic append variables for types, providing operations such as {@code get}, {@code set}, {@code handle} on variables, but non-removable variables.
     * <br>(However, in fact, you can forcibly delete the variable mapping table by calling the {@link ExtraVariableComp # extra()} method, but this goes against the original design intention of this type.)
     *
     * @apiNote I really hate the encapsulation of raw data types, it is definitely the most disgusting design in Java.
     * @since 1.6
     */
    @SuppressWarnings("unchecked")
    public interface ExtraVariableComp {
        /**
         * Variable mapping table entry, automatically or manually bound to a mapping object, which will serve as a container for storing dynamic variables as objects.
         * <br><i>Do not encourage actively calling this method to directly manipulate variables.</i>
         */
        Map<String, Object> extra();

        /**
         * Get the value of a dynamic variable, return null if the variable does not exist
         * @param <T> Get the type of variable.
         * @param field Variable.
         */
        default <T> T getVar(String field){
            return (T) extra().get(field);
        }

        /**
         * Retrieve the value of a dynamic variable, and if the variable does not exist, return the default value given.
         * <br><strong>Note: </strong>If the variable does not exist, the default value will be returned directly and will not be added to the variable table.
         * @param <T> Get the type of variable.
         * @param field Variable.
         * @param def Default value.
         */
        default <T> T getVar(String field, T def){
            return (T) extra().getOrDefault(field, def);
        }

        /**
         * Get the value of a dynamic variable.
         * If the variable does not exist, return the return value of the given initialization function and assign this value to the given variable.
         * This is usually used for convenient variable value initialization.
         * @param <T> Get the type of variable.
         * @param field Variable.
         * @param initial Initial value function.
         */
        default <T> T getVar(String field, Prov<T> initial){
            return (T) extra().computeIfAbsent(field, e -> initial.get());
        }

        /**
         * Get the value of a dynamic variable, throw an exception if the variable does not exist.
         * @param <T> Get the type of variable.
         * @param field Variable.
         * @throws NoSuchFieldException If the obtained variable does not exist.
         */
        default <T> T getVarThr(String field) throws NoSuchFieldException {
            if (!extra().containsKey(field))
                throw new NoSuchFieldException("no such field with name: " + field);

            return (T) extra().get(field);
        }

        /**
         * Set the value of the specified variable
         * @param <T> Set the type of variable.
         * @param field Variable.
         * @param value Variable values set.
         * @return The original value of the variable before it was set.
         */
        default <T> T setVar(String field, T value){
            return (T) extra().put(field, value);
        }

        /**
         * Use a function to process the value of a variable and update the value of the variable with the return value.
         * @param <T> Set the type of variable.
         * @param field Variable.
         * @param cons Variable handling function.
         * @param def Default value of variable.
         * @return The updated variable value, which is the return value of the function.
         */
        default <T> T handleVar(String field, Function<T, T> cons, T def){
            T res;
            setVar(field, res = cons.apply(getVar(field, def)));

            return res;
        }

        //-----------------------
        //Optimization and overloading of primitive data type operations.
        //
        //Java's primitive data type boxing must be one of the top ten foolish behaviors in programming language history.
        //-----------------------

        /**
         * Set boolean type variable value.
         * @see ExtraVariableComp#setVar(String, Object)
         * @throws ClassCastException If the variable already exists and is not a boolean wrapper type or atomized reference.
         */
        default boolean setVar(String field, boolean value){
            Object res = getVar(field);

            if (res instanceof AtomicBoolean b){
                boolean r = b.get();
                b.set(value);
                return r;
            }
            else if (res instanceof Boolean n){
                extra().put(field, new AtomicBoolean(value));
                return n;
            }
            else if (res == null){
                extra().put(field, new AtomicBoolean(value));
                return false;
            }

            throw new ClassCastException(res + " is not a boolean value or atomic boolean");
        }

        /**
         * Get boolean variable value.
         * @see ExtraVariableComp#getVar(String, Object)
         * @throws ClassCastException If the variable already exists and is not a boolean wrapper type or atomized reference.
         */
        default boolean getVar(String field, boolean def){
            Object res = getVar(field);
            if (res == null) return def;

            if (res instanceof AtomicBoolean i) return i.get();
            else if (res instanceof Boolean n) return n;

            throw new ClassCastException(res + " is not a boolean value or atomic boolean");
        }

        /**
         * Retrieve the boolean variable value and initialize the variable value when it does not exist.
         * @see ExtraVariableComp#getVar(String, Prov)
         * @throws ClassCastException If the variable already exists and is not a boolean wrapper type or atomized reference.
         */
        default boolean getVar(String field, Boolp initial){
            Object res = getVar(field);
            if (res == null){
                boolean b = initial.get();
                extra().put(field, new AtomicBoolean(b));
                return b;
            }

            if (res instanceof AtomicBoolean b) return b.get();
            else if (res instanceof Boolean n) return n;

            throw new ClassCastException(res + " is not a boolean value or atomic boolean");
        }

        /**
         * Use processing functions to handle boolean variable values and update variable values with return values.
         * @see ExtraVariableComp#handleVar(String, Function, Object)
         * @throws ClassCastException If the variable already exists and is not a boolean wrapper type or atomized reference.
         */
        default boolean handleVar(String field, BoolTrans handle, boolean def){
            boolean b;
            setVar(field, b = handle.get(getVar(field, def)));

            return b;
        }

        /**
         * Set the value of an int type variable.
         * @see ExtraVariableComp#setVar(String, Object)
         * @throws ClassCastException If the variable already exists and is not an int wrapper type or atomized reference.
         */
        default int setVar(String field, int value){
            Object res = getVar(field);

            if (res instanceof AtomicInteger i){
                int r = i.get();
                i.set(value);
                return r;
            }
            else if (res instanceof Number n){
                extra().put(field, new AtomicInteger(value));
                return n.intValue();
            }
            else if (res == null){
                extra().put(field, new AtomicInteger(value));
                return 0;
            }

            throw new ClassCastException(res + " is not a number or atomic integer");
        }

        /**
         * Get the value of the int variable.
         * @see ExtraVariableComp#getVar(String, Object)
         * @throws ClassCastException If the variable already exists and is not an int wrapper type or atomized reference.
         */
        default int getVar(String field, int def){
            Object res = getVar(field);
            if (res == null) return def;

            if (res instanceof AtomicInteger i) return i.get();
            else if (res instanceof Number n) return n.intValue();

            throw new ClassCastException(res + " is not a number or atomic integer");
        }

        /**
         * Retrieve the value of an int variable and initialize the variable value when it does not exist.
         * @see ExtraVariableComp#getVar(String, Prov)
         * @throws ClassCastException If the variable already exists and is not an int wrapper type or atomized reference.
         */
        default int getVar(String field, Intp initial){
            Object res = getVar(field);
            if (res == null){
                int b = initial.get();
                extra().put(field, new AtomicInteger(b));
                return b;
            }

            if (res instanceof AtomicInteger i) return i.get();
            else if (res instanceof Number n) return n.intValue();

            throw new ClassCastException(res + " is not a number or atomic integer");
        }

        /**
         * Use processing functions to handle int variable values and update variable values with return values.
         * @see ExtraVariableComp#handleVar(String, Function, Object)
         * @throws ClassCastException If the variable already exists and is not an int wrapper type or atomized reference.
         */
        default int handleVar(String field, IntTrans handle, int def){
            int i;
            setVar(field, i = handle.get(getVar(field, def)));

            return i;
        }

        /**
         * Set the value of a long type variable.
         * @see ExtraVariableComp#setVar(String, Object)
         * @throws ClassCastException If the variable already exists and is not a long wrapper type or atomized reference.
         */
        default long setVar(String field, long value){
            Object res = getVar(field);

            if (res instanceof AtomicLong l){
                long r = l.get();
                l.set(value);
                return r;
            }
            else if (res instanceof Number n){
                extra().put(field, new AtomicLong(value));
                return n.longValue();
            }
            else if (res == null){
                extra().put(field, new AtomicLong(value));
                return 0;
            }

            throw new ClassCastException(res + " is not a number or atomic long");
        }

        /**
         * Get the value of a long variable.
         * @see ExtraVariableComp#getVar(String, Object)
         * @throws ClassCastException If the variable already exists and is not a long wrapper type or atomized reference.
         */
        default long getVar(String field, long def){
            Object res = getVar(field);
            if(res == null) return def;

            if(res instanceof AtomicLong l) return l.get();
            else if(res instanceof Number n) return n.longValue();

            throw new ClassCastException(res + " is not a number or atomic long");
        }

        /**
         * Retrieve the value of a long variable and initialize the variable value when it does not exist.
         * @see ExtraVariableComp#getVar(String, Prov)
         * @throws ClassCastException If the variable already exists and is not a long wrapper type or atomized reference.
         */
        default long getVar(String field, Longp initial){
            Object res = getVar(field);
            if(res == null){
                long l = initial.get();
                extra().put(field, new AtomicLong(l));
                return l;
            }

            if(res instanceof AtomicLong l) return l.get();
            else if(res instanceof Number n) return n.longValue();

            throw new ClassCastException(res + " is not a number or atomic long");
        }

        /**
         * Use processing functions to handle long variable values and update variable values with return values.
         * @see ExtraVariableComp#handleVar(String, Function, Object)
         * @throws ClassCastException If the variable already exists and is not a long wrapper type or atomized reference.
         */
        default long handleVar(String field, LongTrans handle, long def){
            long l;
            setVar(field, l = handle.get(getVar(field, def)));

            return l;
        }

        /**
         * Set float type variable value.
         * @see ExtraVariableComp#setVar(String, Object)
         * @throws ClassCastException If the variable already exists and is not a float wrapper type or a single element float array.
         */
        default float setVar(String field, float value){
            Object res = getVar(field);

            if(res instanceof float[] a && a.length == 1){
                float r = a[0];
                a[0] = value;
                return r;
            }else if(res instanceof Number n){
                extra().put(field, new float[]{value});
                return n.floatValue();
            }else if(res == null){
                extra().put(field, new float[]{value});
                return 0;
            }

            throw new ClassCastException(res + " is not a number or single float reference array");
        }

        /**
         * Get float variable value.
         * @see ExtraVariableComp#getVar(String, Object)
         * @throws ClassCastException If the variable already exists and is not a float wrapper type or a single element float array.
         */
        default float getVar(String field, float def){
            Object res = getVar(field);
            if(res == null) return def;

            if(res instanceof float[] f && f.length == 1) return f[0];
            else if(res instanceof Number n) return n.floatValue();

            throw new ClassCastException(res + " is not a number or single float reference array");
        }

        /**
         * Retrieve the float variable value and initialize the variable value when it does not exist.
         * @see ExtraVariableComp#getVar(String, Prov)
         * @throws ClassCastException If the variable already exists and is not a float wrapper type or a single element float array.
         */
        default float getVar(String field, Floatp initial){
            Object res = getVar(field);
            if(res == null){
                float f = initial.get();
                extra().put(field, new float[]{f});
                return f;
            }

            if(res instanceof float[] l && l.length == 1) return l[0];
            else if(res instanceof Number n) return n.longValue();

            throw new ClassCastException(res + " is not a number or single float reference array");
        }

        /**
         * Use processing functions to handle float variable values and update variable values with return values.
         * @see ExtraVariableComp#handleVar(String, Function, Object)
         * @throws ClassCastException If the variable already exists and is not a float wrapper type or a single element float array.
         */
        default float handleVar(String field, FloatTrans handle, float def){
            float trans;
            setVar(field, trans = handle.get(getVar(field, def)));

            return trans;
        }

        /**
         * Set the value of a double type variable.
         * @see ExtraVariableComp#setVar(String, Object)
         * @throws ClassCastException If the variable already exists and is not a float wrapper type or a single element double array.
         */
        default double setVar(String field, double value){
            Object res = getVar(field);

            if(res instanceof double[] a && a.length == 1){
                double r = a[0];
                a[0] = value;
                return r;
            }else if (res instanceof Number n){
                extra().put(field, new double[]{value});
                return n.doubleValue();
            }else if (res == null){
                extra().put(field, new double[]{value});
                return 0;
            }

            throw new ClassCastException(res + " is not a number or single double reference array");
        }

        /**
         * Get double variable value.
         * @see ExtraVariableComp#getVar(String, Object)
         * @throws ClassCastException If the variable already exists and is not a float wrapper type or a single element double array.
         */
        default double getVar(String field, double def){
            Object res = getVar(field);
            if(res == null) return def;

            if(res instanceof double[] f && f.length == 1) return f[0];
            else if(res instanceof Number n) return n.doubleValue();

            throw new ClassCastException(res + " is not a number or single double reference array");
        }

        /**
         * Retrieve the value of a double variable and initialize the variable value when it does not exist.
         * @see ExtraVariableComp#getVar(String, Prov)
         * @throws ClassCastException If the variable already exists and is not a double wrapper type or a single element double array.
         */
        default double getVar(String field, Doublep initial){
            Object res = getVar(field);
            if (res == null){
                double d = initial.get();
                extra().put(field, new double[]{d});
                return d;
            }

            if(res instanceof double[] d && d.length == 1) return d[0];
            else if(res instanceof Number n) return n.doubleValue();

            throw new ClassCastException(res + " is not a number or single double reference array");
        }

        /**
         * Use processing functions to handle double variable values and update variable values with return values.
         * @see ExtraVariableComp#handleVar(String, Function, Object)
         * @throws ClassCastException If the variable already exists and is not a double wrapper type or a single element double array.
         */
        default double handleVar(String field, DoubleTrans handle, double def){
            double d;
            setVar(field, d = handle.get(getVar(field, def)));

            return d;
        }
    }

    /**
     * The basic interface of building components should actually use a build manager to construct buildings, but Anuke's approach makes it difficult.
     * @since 1.0
     */
    public interface BuildCompBase{
        /**
         * The generic check for obtaining building blocks requires the implementation class to be a subclass of {@link Building}.
         * @param clazz Returned block type.
         */
        default <T> T getBlock(Class<T> clazz){
            Block block = getBuilding().block;
            if(clazz.isAssignableFrom(block.getClass())){
                return (T) block;
            }
            return null;
        }

        /** To obtain the {@link Block} of this building, you need to. */
        default Block getBlock(){
            return getBlock(Block.class);
        }

        /**
         * Get instances of the type with checked generics.
         * @param clazz Return base class type.
         *
         * @throws ClassCastException If the class containing the interface is not a subclass of {@link Building}.
         */
        default <T> T getBuilding(Class<T> clazz){
            if(clazz.isAssignableFrom(getClass())) return (T)this;
            throw new ClassCastException(getClass() + " cannot cast to " + clazz);
        }

        /**
         * Get Building.
         * @throws ClassCastException If the class containing the interface is not a subclass of {@link Building}.
         */
        default Building getBuilding(){
            if(Building.class.isAssignableFrom(getClass())) return (Building) this;
            throw new ClassCastException(getClass() + " cannot cast to " + Building.class);
        }

        /** Retrieve instances of this type using unchecked generics. */
        default <T> T getBuild(){
            return (T) this;
        }

        /** Get the tile of the block. */
        Tile getTile();

        /** Obtain the items' module. */
        ItemModule items();

        /** Obtain the liquids' module. */
        LiquidModule liquids();
    }

    /**
     * The block information component of the chain block, used for blocks, provides necessary descriptive properties for {@linkplain ChainsBuildComp chain building}.
     * @since 1.5
     */
    public interface ChainsBlockComp {
        /** The maximum x-axis span of a continuous structure. */
        int maxWidth();

        /** The maximum y-axis span of a continuous structure. */
        int maxHeight();

        /**
         * Can this block form a continuous structure with the target block? Both blocks need to be linked to each other to form a continuous structure.
         * @param other Target Block.
         */
        default boolean chainable(ChainsBlockComp other){
            return getClass().isAssignableFrom(other.getClass());
        }

        /** Set statistical data for blocks. */
        default void setChainsStats(Stats stats){
            stats.add(HIStat.maxStructureSize, "@x@", maxWidth(), maxHeight());
        };
    }

    /**
     * The interface component of chain blocks, which provides the ability to perform certain behaviors when blocks are placed continuously, and adds triggers for changes that occur on a continuous structure of a block.
     * @since 1.5
     */
    public interface ChainsBuildComp extends BuildCompBase, Posc, Iterable<ChainsBuildComp>{
        Seq<ChainsBuildComp> tempSeq = new Seq<>();

        /** This is a foolish approach But there doesn't seem to be a good solution, in other words, it is used to maintain the original structure without changing it when reloading the archive. */
        IntSet loadingInvalidPos();

        /** Chain structured containers are the core of continuous structure preservation and behavior triggering. */
        ChainsModule chains();

        default ChainsBlockComp getChainsBlock(){
            return getBlock(ChainsBlockComp.class);
        }

        default void onChainsAdded(){
            for(ChainsBuildComp other : chainBuilds()){
                if(loadingInvalidPos().contains(other.getTile().pos())) continue;
                if(canChain(other) && other.canChain(this)) other.chains().container.add(chains().container);
            }
            if(!loadingInvalidPos().isEmpty()) loadingInvalidPos().clear();
        }

        /**
         * Can it form a continuous structure with the target building? Only when it can be connected to the target can it form a continuous structure.
         * @param other Target building.
         */
        default boolean canChain(ChainsBuildComp other){
            if(!getChainsBlock().chainable(other.getChainsBlock())) return false;

            return chains().container.inlerp(this, other);
        }

        void onChainsRemoved();

        /** Get other chain blocks that this building can connect to. Note that this returns a shared container, please save a copy if needed. */
        default Seq<ChainsBuildComp> chainBuilds(){
            tempSeq.clear();
            for(Building other: getBuilding().proximity){
                if(other instanceof ChainsBuildComp comp && canChain(comp) && comp.canChain(this)){
                    tempSeq.add(comp);
                }
            }
            return tempSeq;
        }

        /** Iterative implementation, this component can directly traverse all structural members through for each. */
        @Override
        default Iterator<ChainsBuildComp> iterator(){
            return chains().container.all.iterator();
        }

        /**
         * When a new {@linkplain ChainsContainer chain structure container} is created in this block, it is called.
         * @param old Replaced original container.
         */
        default void containerCreated(ChainsContainer old){}

        /**
         * Called when this block is added to a chain structure.
         * @param old When added to a continuous structure, the current container of the block will be replaced by the target container, and the original container that was replaced will be passed in from this parameter.
         */
        default void chainsAdded(ChainsContainer old){}

        /**
         * Call when removing from {@linkplain ChainsContainer chain structure container} in this block.
         * @param children This block is linked to other surrounding blocks.
         */
        default void chainsRemoved(Seq<ChainsBuildComp> children){}

        /**
         * When a {@linkplain ChainsContainer} performs a traversal search and adds the block to the container, it is called.
         * @param old When added to a continuous structure, the current container of the block will be replaced by the target container, and the original container that was replaced will be passed in from this parameter.
         */
        default void chainsFlowed(ChainsContainer old){}

        /** Called <strong>after</strong> any part of the continuous structure changes. */
        default void onChainsUpdated(){}

        default void writeChains(Writes write){
            tempSeq.clear();
            for(Building building: getBuilding().proximity){
                if(building instanceof ChainsBuildComp chain && chain.chains().container != chains().container){
                    tempSeq.add(chain);
                }
            }

            write.i(tempSeq.size);
            for(ChainsBuildComp comp: tempSeq){
                write.i(comp.getTile().pos());
            }
        }

        default void readChains(Reads read){
            int size = read.i();
            for(int i = 0; i < size; i++){
                loadingInvalidPos().add(read.i());
            }
        }
    }

    /**
     * Consume component, adding the ability to mark consumable items to blocks.
     * @since 1.0
     */
    public interface ConsumerBlockComp{
        /** List of consumption of blocks. */
        Seq<BaseConsumers> consumers();

        /** List of optional consumption options for blocks. */
        Seq<BaseConsumers> optionalCons();

        /** Does this block only select the top option when consuming the optional list once. */
        boolean oneOfOptionCons();

        ConsFilter filter();

        /** Create a new consumption list, insert it into the container, and return it. */
        default BaseConsumers newConsume(){
            BaseConsumers consume = new BaseConsumers(false);
            consumers().add(consume);
            return consume;
        }

        /**
         * Create an optional consumption list, insert it into the container, and return it.
         * @param validDef When this consumable item is available, the behavior to be performed each time it is refreshed.
         * @param displayDef Used to set statistical entries and display the optional consumption function.
         */
        default <T extends ConsumerBuildComp> BaseConsumers newOptionalConsume(Cons2<T, BaseConsumers> validDef, Cons2<Stats, BaseConsumers> displayDef){
            BaseConsumers consume = new BaseConsumers(true){{
                optionalDef = (Cons2<ConsumerBuildComp, BaseConsumers>) validDef;
                display = displayDef;
            }};
            optionalCons().add(consume);
            return consume;
        }

        /** To add blocks to the energy network, it is necessary to initialize an existing energy consumer as a proxy, which is called by the {@link Block#init()} entry in componentized implementation. */
        default void initPower(){
            Block block = (Block)this;

            if(block.consumesPower){
                block.consumePowerDynamic(e -> {
                    ConsumerBuildComp entity = (ConsumerBuildComp) e;
                    if(entity.consumer().current == null) return 0f;
                    if(entity.getBuilding().tile().build == null || entity.consumeCurrent() == -1 || !entity.consumer().excludeValid(ConsumeType.power))
                        return 0f;

                    ConsumePower<?> cons = entity.consumer().current.get(ConsumeType.power);
                    if(cons == null) return 0;

                    if(cons.buffered){
                        return (1f - entity.getBuilding().power.status)*cons.capacity;
                    }else{
                        return entity.consumer().getPowerUsage()*Mathf.num(entity.shouldConsume());
                    }
                });
            }
        }

        default void initFilter(){
            filter().applyFilter(consumers(), optionalCons());
            for(BaseConsumers consumer: consumers()){
                consumer.initFilter();
            }
        }
    }

    /**
     * Consumer components enable blocks to have the ability to consume and check resources.
     * @since 1.0
     */
    public interface ConsumerBuildComp extends BuildCompBase {
        /** Index of currently selected consumption items. */
        int consumeCurrent();

        /** Obtain consumption module. */
        BaseConsumeModule consumer();

        default void updateConsumer(){
            consumer().update();
        }

        default float consMultiplier(){
            return 1;
        }

        /** Current consumption execution efficiency, from 0-1. */
        default float consEfficiency(){
            return consumer().consEfficiency;
        }

        default float optionalConsEff(BaseConsumers consumers){
            return consumer().getOptionalEff(consumers);
        }

        /** Obtain the Consumerblock for this block. */
        default ConsumerBlockComp getConsumerBlock(){
            return getBlock(ConsumerBlockComp.class);
        }

        /** Obtain the Nuclear Energy Block for this block. */
        default ConsumerBuildComp getConsumerBuilding(){
            return getBlock(ConsumerBuildComp.class);
        }

        /** Does the consumption condition of the current consumption list for this block meet. */
        default boolean consumeValid(){
            return consumer() == null || !consumer().hasConsume() || consumer().valid();
        }

        /** Should this block currently consume the consumption list. */
        default boolean shouldConsume(){
            return consumer() != null && consumeCurrent() != -1;
        }

        /** Should this block currently consume the optional consumption list. */
        default boolean shouldConsumeOptions(){
            return shouldConsume() && consumer().hasOptional();
        }

        default void buildConsumerBars(Table bars){
            if(consumer().current != null){
                for(BaseConsume<? extends ConsumerBuildComp> consume : consumer().current.all()) {
                    ((BaseConsume<ConsumerBuildComp>) consume).buildBars(this, bars);
                }
            }
        }
    }

    /**
     * Factory block component, describing some necessary attributes in {@linkplain FactoryBuildComp Factory Building}
     * @since 1.4
     */
    public interface FactoryBlockComp extends ProducerBlockComp{
        /** The thermal efficiency of a block, interpolated from 0-1, represents the speed of the block from startup to maximum efficiency. This is the interpolation of {@link Mathf#lerpDelta(float, float, float)}. */
        float warmupSpeed();

        /** The cooling rate of the block, interpolated from 0-1, is the speed at which the block completely stops. This is the interpolation of {@link Mathf#lerpDelta(float, float, float)}. */
        float stopSpeed();

        static void buildRecipe(Table table, BaseConsumers consumers, BaseProducers producers){
            Stats stats = new Stats();

            if(consumers != null){
                consumers.display(stats);
            }
            if(producers != null) {
                producers.display(stats);
            }

            buildStatTable(table, stats);
        }

        static void buildStatTable(Table table, Stats stat){
            for(StatCat cat : stat.toMap().keys()){
                OrderedMap<Stat, Seq<StatValue>> map = stat.toMap().get(cat);
                if(map.size == 0) continue;

                if(stat.useCategories){
                    table.add("@category." + cat.name).color(Pal.accent).fillX();
                    table.row();
                }

                for(Stat state : map.keys()){
                    table.table(inset -> {
                        inset.left();
                        inset.add("[lightgray]" + state.localized() + ":[] ").left();
                        Seq<StatValue> arr = map.get(state);
                        for(StatValue value : arr){
                            value.display(inset);
                            inset.add().size(10f);
                        }
                    }).fillX().padLeft(10);
                    table.row();
                }
            }
        }
    }

    /**
     * The interface component of the factory building, which endows blocks with the behavior of production/manufacturing. The factory behavior integrates the behaviors of {@link ConsumerBuildComp} and {@link ProducerBlockComp} and describes the default implementation of manufacturing behavior.
     * @since 1.4
     */
    public interface FactoryBuildComp extends ProducerBuildComp{
        /** {@code getter-}Production schedule. */
        float progress();

        /** {@code setter-}Production schedule. */
        void progress(float value);

        /** {@code getter-}Overall production progress. */
        float totalProgress();

        /** {@code setter-}Overall production progress. */
        void totalProgress(float value);

        /** {@code getter-}Interpolation for preheating work. */
        float warmup();

        /**{@code setter-}Interpolation for preheating work. */
        void warmup(float value);

        @Override
        default float consEfficiency(){
            return ProducerBuildComp.super.consEfficiency()*warmup();
        }

        default void updateFactory(){
            //Do not update when no formula is selected.
            if(produceCurrent() == -1 || producer().current == null){
                warmup(Mathf.lerpDelta(warmup(), 0, getFactoryBlock().stopSpeed()));
                return;
            }

            if(shouldConsume() && consumeValid()){
                progress(progress() + progressIncrease(consumer().current.craftTime));
                warmup(Mathf.lerpDelta(warmup(), 1, getFactoryBlock().warmupSpeed()));

                onCraftingUpdate();
            }else{
                warmup(Mathf.lerpDelta(warmup(), 0, getFactoryBlock().stopSpeed()));
            }

            totalProgress(totalProgress() + consumer().consDelta());

            while(progress() >= 1){
                progress(progress() - 1);
                consumer().trigger();
                producer().trigger();

                craftTrigger();
            }
        }

        /**
         * To process production, when the output is a building, it is necessary to correctly count the output items.
         * When the factory handles the item and passes it in, the added item will be added to the statistics.
         */
        default void handleProductItem(Building source, Item item){
            if (source == this){
                source.produced(item);
            }
        }

        default void readFactory(Reads read){
            progress(read.f());
            totalProgress(read.f());
            warmup(read.f());
        }

        default void writeFactory(Writes write){
            write.f(progress());
            write.f(totalProgress());
            write.f(warmup());
        }

        /** The current working efficiency of the machine, 0-1. */
        default float workEfficiency(){
            return consEfficiency();
        }

        /**
         * The sales increment of machine operation, under standard circumstances, is the reciprocal of production time, multiplied by the additional increment to return.
         * @param baseTime The benchmark time consumption for the current execution.
         */
        default float progressIncrease(float baseTime){
            return 1/baseTime*consumer().consDelta();
        }

        default FactoryBlockComp getFactoryBlock(){
            return getBlock(FactoryBlockComp.class);
        }

        @Override
        default boolean shouldConsume(){
            return ProducerBuildComp.super.shouldConsume() && productValid();
        }

        /** Called during a production execution in machine operation. */
        void craftTrigger();

        /** Every refresh call during machine operation. */
        void onCraftingUpdate();
    }

    /**
     * The component of the producer block enables the block to have the function of recording and outputting resource recipes.
     * @since 1.0*/
    public interface ProducerBlockComp extends ConsumerBlockComp{
        /** List of Production Lists. */
        Seq<BaseProducers> producers();

        /** Create a new production list, add it to the container, and return it. */
        default BaseProducers newProduce(){
            BaseProducers produce = new BaseProducers();
            producers().add(produce);
            return produce;
        }

        /** Initialize the matching consumption production list, and call it at the end of init(). */
        default void initProduct(){
            int b = producers().size;
            int a = consumers().size;
            //Control the production of adding/removing recipes to synchronize the recipes.
            while(a > b){
                BaseProducers p = new BaseProducers();
                producers().add(p);
                b++;
            }
            while(a < b){
                b--;
                producers().remove(b);
            }

            for(int index = 0; index< producers().size; index++){
                producers().get(index).cons = consumers().get(index);
            }
        }
    }

    /**
     * Producer component, which enables blocks to have the ability to produce and output resources on demand.
     * @since 1.0
     */
    public interface ProducerBuildComp extends BuildCompBase, ConsumerBuildComp{
        /** Index of the currently selected production project. */
        default int produceCurrent(){
            return consumeCurrent();
        }

        float powerProdEfficiency();

        void powerProdEfficiency(float powerProdEfficiency);

        default float prodMultiplier(){
            return consMultiplier();
        }

        /** Production components. */
        BaseProductModule producer();

        default void updateProducer(){
            producer().update();
        }

        /** Obtain the NuclearEnergyBlock for this block. */
        default ProducerBlockComp getProducerBlock(){
            return getBlock(ProducerBlockComp.class);
        }

        /** Obtain the NuclearEnergyBlock for this block. */
        default ProducerBuildComp getProducerBuilding(){
            return getBlock(ProducerBuildComp.class);
        }

        /** Is the current production available. */
        default boolean productValid(){
            return producer() == null || producer().valid();
        }

        /** Should production item updates be executed at present. */
        default boolean shouldProduct(){
            return producer() != null && produceCurrent() != -1;
        }

        default void buildProducerBars(Table bars){
            if(consumer().current != null){
                for(BaseProduce<? extends ProducerBuildComp> consume : producer().current.all()) {
                    ((BaseProduce<ProducerBuildComp>) consume).buildBars(this, bars);
                }
            }
        }

        default float getPowerProduction(){
            if(!getBlock().outputsPower || producer().current == null || producer().current.get(ProduceType.power) == null) return 0;
            powerProdEfficiency(Mathf.num(shouldConsume() && consumeValid())*consEfficiency()*((ProducePower)(producer().current.get(ProduceType.power))).multiple(this));
            return producer().getPowerProduct()*powerProdEfficiency();
        }
    }

    /**
     * The block component that can be replaced and placed over can transmit information when the block is placed over, passing the old block to the new block.
     * @since 1.5
     */
    public interface ReplaceBuildComp extends BuildCompBase {
        ObjectMap<Tile, ReplaceBuildComp> replaceEntry = new ObjectMap<>();

        default void onRemoving(){
            Tile tile = getTile();
            replaceEntry.put(tile, this);
        }

        default void buildInitialized(){
            ReplaceBuildComp old = replaceEntry.get(getTile());
            if(old == null) return;
            onReplaced(old);
            replaceEntry.remove(getTile());
        }

        /**
         * Called when a valid replaceable block is placed and overlaid on a block, passing in the overwritten block.
         * @param old The block originally located at this position.
         */
        void onReplaced(ReplaceBuildComp old);
    }

    /** A block component with a secondary configuration menu requires this interface when opening the secondary configuration for block configuration. */
    public interface SecondableConfigBuildComp{
        /**
         * Method for establishing a secondary configuration panel.
         * @param table Panel layout table, edit this list.
         * @param target Target building for secondary configuration.
         */
        void buildSecondaryConfig(Table table, Building target);
    }

    /**
     * Building components for splicing blocks, record some necessary attributes for{@linkplain SpliceBuildComp splicing block building}.
     * <p><strong>This is an unstable API that may be adjusted to a more universal and efficient form in the future, which may cause API changes. Please use it with caution.</strong>
     * @since 1.5
     */
    public interface SpliceBlockComp extends ChainsBlockComp{
        boolean interCorner();

        boolean negativeSplice();
    }

    /**
     * Building components that splice blocks to automatically synchronize the status of surrounding blocks and record them, typically used to draw continuous connecting materials for blocks.
     * <p><strong>This is an unstable API that may be adjusted to a more universal and efficient form in the future, which may cause API changes. Please use it with caution.</strong>
     * @since 1.5
     */
    public interface SpliceBuildComp extends ChainsBuildComp{
        int splice();

        void splice(int arr);

        default int getSplice(){
            int result = 0;

            t: for(int i = 0; i < 8; i++){
                SpliceBuildComp other = null;
                for(Point2 p: DirEdges.get8(getBlock().size, i)){
                    if(other == null){
                        if(getBuilding().nearby(p.x, p.y) instanceof SpliceBuildComp oth && oth.chains().container == chains().container){
                            other = oth;
                        }else{
                            continue t;
                        }
                    }else if(other != getBuilding().nearby(p.x, p.y)){
                        continue t;
                    }
                }
                result |= 1 << i;
            }

            return result;
        }

        default void updateRegionBit(){
            splice(getSplice());
        }
    }

    /**
     * This interface provides the implementer with the ability to sequentially extract (or predict the extraction of) elements from a pile of elements.
     * @since 1.2
     */
    public interface Takeable extends BuildCompBase{
        ObjectMap<String, Heaps<?>> heaps();

        /**
         * Adding an output element heap, initializing it with a string name and a Seq container, is not absolutely necessary
         * @param name Heap naming, used for indexing.
         * @param targets All heap elements, select operations within them.
         */
        default void addHeap(String name, Seq<?> targets){
            heaps().put(name, new Heaps<>(targets));
        }

        /**
         * Adding an output element heap with a string as the name and a Seq container, as well as initializing a filter function that returns a Boolean value, is not absolutely necessary.
         * @param name Heap naming, used for indexing.
         * @param targets All heap elements, select operations within them.
         * @param valid Select filter.
         */
        default <T> void addHeap(String name, Seq<T> targets, Boolf<T> valid){
            heaps().put(name, new Heaps<>(targets, valid));
        }

        /**
         * Get an output element heap.
         * @param name Name saved in the container.
         */
        default <T> Heaps<T> getHeaps(String name){
            return (Heaps<T>) heaps().get(name);
        }

        /**
         * Retrieve the next element from the heap with the specified name. If the specified heap does not exist, a new heap will be created and added to the container.
         * @param name Pile name.
         */
        default Building getNext(String name){
            return getNext(name, true);
        }

        /**
         * Retrieve the next element from the heap with the specified name. If the specified heap does not exist, a new heap will be created and added to the container.
         * @param name Pile name.
         * @param targets Provide a list of elements in the heap.
         */
        default <T> T getNext(String name, Seq<T> targets){
            return getNext(name, targets, true);
        }

        /**
         * Retrieve the next element from the heap with the specified name. If the specified heap does not exist, a new heap will be created and added to the container. The elements of the heap will default to {@link Building#proximity} from building.
         * @param name Pile name.
         * @param valid Element filter.
         */
        default Building getNext(String name, Boolf<Building> valid){
            return getNext(name, valid, true);
        }

        /**
         * Retrieve the next element from the heap with the specified name. If the specified heap does not exist, a new heap will be created and added to the container.
         * @param name Pile name.
         * @param targets Provide a list of elements in the heap.
         * @param valid Element filter.
         */
        default <T> T getNext(String name, Seq<T> targets, Boolf<T> valid){
            return getNext(name, targets, valid, true);
        }

        /**
         * Predict the next element to be obtained. If the specified heap does not exist, a new heap will be created and added to the container. The elements of the heap will default to {@link Building#proximity} from building.
         * @param name Pile name.
         */
        default Building peek(String name){
            return getNext(name, false);
        }

        /**
         * Predict the next element to be obtained, and if the specified heap does not exist, create a new heap and add it to the container.
         * @param name Pile name.
         * @param targets Provide a list of elements in the heap.
         */
        default <T> T peek(String name, Seq<T> targets){
            return getNext(name, targets, false);
        }

        /**
         * Predict the next element to be obtained. If the specified heap does not exist, a new heap will be created and added to the container. The elements of the heap will default to {@link Building#proximity} from building.
         * @param name Pile name.
         * @param valid Element filter.
         */
        default Building peek(String name, Boolf<Building> valid){
            return getNext(name, valid, false);
        }

        /**
         * Predict the next element to be obtained, and if the specified heap does not exist, create a new heap and add it to the container.
         * @param name Pile name.
         * @param targets Provide a list of elements in the heap.
         * @param valid Element filter.
         */
        default <T> T peek(String name, Seq<T> targets, Boolf<T> valid){
            return getNext(name, targets, valid, false);
        }

        /**
         * Retrieve the next element from the heap with the specified name. If the specified heap does not exist, a new heap will be created and added to the container. The elements of the heap will default to {@link Building#proximity} from building.
         * @param name Pile name.
         * @param increase Do you want to increase the counter once? If false, this method is used to predict the next element.
         */
        default Building getNext(String name, boolean increase){
            Heaps<Building> heaps;
            if((heaps = (Heaps<Building>) heaps().get(name)) == null){
                heaps = new Heaps<>(getBuilding().proximity);
                heaps().put(name, heaps);
            }
            return increase? heaps.next(): heaps.peek();
        }

        /**
         * Retrieve the next element from the heap with the specified name. If the specified heap does not exist, a new heap will be created and added to the container. The elements of the heap will default to {@link Building#proximity} from building.
         * @param name Pile name.
         * @param valid Element filter.
         * @param increase Do you want to increase the counter once? If false, this method is used to predict the next element.
         */
        default Building getNext(String name, Boolf<Building> valid, boolean increase){
            Heaps<Building> heaps;
            if((heaps = (Heaps<Building>) heaps().get(name)) == null){
                heaps = new Heaps<>(getBuilding().proximity, valid);
                heaps().put(name, heaps);
            }
            return increase? heaps.next(valid): heaps.peek(valid);
        }

        /**
         * Retrieve the next element from the heap with the specified name. If the specified heap does not exist, a new heap will be created and added to the container.
         * @param name Pile name.
         * @param targets Provide a list of elements in the heap.
         * @param increase Do you want to increase the counter once? If false, this method is used to predict the next element.
         */
        default <T> T getNext(String name, Seq<T> targets, boolean increase){
            Heaps<T> heaps;
            if((heaps = (Heaps<T>) heaps().get(name)) == null){
                heaps = new Heaps<>(targets);
                heaps().put(name, heaps);
            }
            return increase? heaps.next(targets): heaps.peek(targets);
        }

        /**
         * Retrieve the next element from the heap with the specified name. If the specified heap does not exist, a new heap will be created and added to the container.
         * @param name Pile name.
         * @param targets Provide a list of elements in the heap.
         * @param valid Element filter.
         * @param increase Do you want to increase the counter once? If false, this method is used to predict the next element.
         */
        default <T> T getNext(String name, Seq<T> targets, Boolf<T> valid, boolean increase){
            Heaps<T> heaps;
            if((heaps = (Heaps<T>) heaps().get(name)) == null){
                heaps = new Heaps<>(targets, valid);
                heaps().put(name, heaps);
            }
            return increase? heaps.next(targets, valid): heaps.peek(targets, valid);
        }

        /** Element heap, used to save and count the target elements that pop up. */
        class Heaps<T>{
            public Seq<T> targets = new Seq<>();
            public Boolf<T> valid = e -> true;
            public int heapCounter;

            public Heaps(){}

            public Heaps(Seq<T> defaultAll){
                this.targets = defaultAll;
            }

            public Heaps(Seq<T> targets, Boolf<T> valid){
                this.targets = targets;
                this.valid = valid;
            }

            public int increaseCount(int size){
                heapCounter = (heapCounter + 1)%size;
                return heapCounter;
            }

            public void setTargets(Seq<T> other){
                targets = other;
            }

            public void setValid(Boolf<T> other){
                valid = other;
            }

            public T next(){
                return next(targets, valid);
            }

            public T peek(){
                return peek(targets, valid);
            }

            public T next(Boolf<T> valid){
                return next(targets, valid);
            }

            public T peek(Boolf<T> valid){
                return peek(targets, valid);
            }

            public T next(Seq<T> targets){
                return next(targets, valid);
            }

            public T peek(Seq<T> targets){
                return peek(targets, valid);
            }

            public T next(Seq<T> targets, Boolf<T> valid){
                int size = targets.size;
                if(size == 0) return null;
                T result;
                for(T ignored : targets){
                    result = targets.get(increaseCount(size));
                    if(valid.get(result)) return result;
                }
                return null;
            }

            public T peek(Seq<T> targets, Boolf<T> valid){
                int size = targets.size, curr = heapCounter;
                if(size == 0) return null;
                T result;
                for(T ignored : targets){
                    curr = (curr + 1)%size;
                    result = targets.get(curr);
                    if(valid.get(result)) return result;
                }
                return null;
            }
        }
    }
}
