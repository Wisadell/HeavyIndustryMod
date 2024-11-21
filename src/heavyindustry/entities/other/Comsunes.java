package heavyindustry.entities.other;

import arc.*;
import arc.func.*;
import arc.math.*;
import arc.scene.event.*;
import arc.scene.ui.Image;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import mindustry.core.*;
import mindustry.ctype.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.meta.*;
import heavyindustry.entities.other.Components.*;
import heavyindustry.ui.display.*;
import heavyindustry.util.HIUtils.*;

import static mindustry.Vars.*;

@SuppressWarnings({"rawtypes", "unchecked"})
public final class Comsunes {
    public static abstract class BaseConsume<T extends ConsumerBuildComp>{
        public BaseConsumers parent;
        public Floatf<T> consMultiplier;


        /** Types of consumption. */
        public abstract ConsumeType<?> type();

        public boolean hasIcons(){
            return true;
        }

        public abstract void buildIcons(Table table);

        public abstract void merge(BaseConsume<T> other);

        public abstract void consume(T entity);
        public abstract void update(T entity);
        public abstract void display(Stats stats);
        public abstract void build(T entity, Table table);
        public void buildBars(T entity, Table bars){}
        public abstract float efficiency(T entity);

        public abstract Seq<Content> filter();

        public float multiple(T entity){
            return (consMultiplier == null? 1: consMultiplier.get(entity))*entity.consMultiplier();
        }

        public <N extends ConsumerBuildComp> BaseConsume<T> setMultiple(Floatf<N> multiple){
            consMultiplier = (Floatf<T>) multiple;
            return this;
        }
    }

    /** A consumption list that records all information related to consumption, including production time, options, etc. */
    public static class BaseConsumers {
        protected final ObjectMap<ConsumeType<?>, BaseConsume<?>> cons = new ObjectMap<>();
        protected static final Seq<BaseConsume<?>> tmpCons = new Seq<>();

        /** This value controls the time consumed in production. */
        public float craftTime = 60;
        /** Whether to display the time required for consumption in the statistics column. */
        public boolean showTime = false;

        /** Is it an optional consumable item. */
        public final boolean optional;
        public boolean optionalAlwaysValid = true;

        /** The objective function to be executed with updates when the optional list is available*/
        public Cons2<ConsumerBuildComp, BaseConsumers> optionalDef = (entity, cons) -> {};
        /** Function for displaying custom content in statistical information. */
        public Cons2<Stats, BaseConsumers> display = (stats, cons) -> {};
        /** Do you want to overwrite the default item display with a custom display. */
        public boolean customDisplayOnly;

        public Prov<Visibility> selectable = () -> Visibility.usable;

        public Floatf<ConsumerBuildComp> consDelta = e -> e.getBuilding().delta()*e.consEfficiency();
        /** The available controllers for this consumption. */
        public Seq<Boolf<ConsumerBuildComp>> valid = new Seq<>();
        /** Consuming triggers, triggered when the consumed trigger() method is executed. */
        public Seq<Cons<ConsumerBuildComp>> triggers = new Seq<>();

        protected final ObjectMap<ConsumeType<?>, ObjectSet<Content>> filter = new ObjectMap<>();
        protected final ObjectMap<ConsumeType<?>, ObjectSet<Content>> otherFilter = new ObjectMap<>();
        protected final ObjectMap<ConsumeType<?>, ObjectSet<Content>> selfAccess = new ObjectMap<>();

        public BaseConsumers(boolean optional){
            this.optional = optional;
        }

        public void initFilter(){
            filter.clear();
            for(ObjectMap.Entry<ConsumeType<?>, BaseConsume<?>> entry: cons){
                Seq<Content> cont = entry.value.filter();
                if(cont != null) filter.get(entry.key, ObjectSet::new).addAll(cont);
            }
            for(ObjectMap.Entry<ConsumeType<?>, ObjectSet<Content>> entry: otherFilter){
                filter.get(entry.key, ObjectSet::new).addAll(entry.value);
            }
        }

        public void addToFilter(ConsumeType<?> type, Content content){
            otherFilter.get(type, ObjectSet::new).add(content);
        }

        public void addSelfAccess(ConsumeType<?> type, Content content){
            selfAccess.get(type, ObjectSet::new).add(content);
        }

        public BaseConsumers time(float time){
            this.craftTime = time;
            showTime = time > 0;
            return this;
        }

        public BaseConsumers overdriveValid(boolean valid){
            this.consDelta = valid?
                    e -> e.getBuilding().delta()*e.consEfficiency():
                    e -> Time.delta*e.consEfficiency();
            return this;
        }

        public <T extends ConsumerBuildComp> BaseConsumers setConsDelta(Floatf<T> delta){
            this.consDelta = (Floatf<ConsumerBuildComp>) delta;
            return this;
        }

        public <T extends ConsumerBuildComp> BaseConsumers consValidCondition(Boolf<T> cond){
            this.valid.add((Boolf<ConsumerBuildComp>) cond);
            return this;
        }

        public <T extends ConsumerBuildComp> BaseConsumers setConsTrigger(Cons<T> cond){
            this.triggers.add((Cons<ConsumerBuildComp>) cond);
            return this;
        }

        public float delta(ConsumerBuildComp entity){
            return optional? entity.getBuilding().delta()*entity.optionalConsEff(this)*Math.max(1, entity.consEfficiency()): consDelta.get(entity);
        }

        public ConsumeItems<? extends ConsumerBuildComp> item(Item item, int amount){
            return items(new ItemStack(item, amount));
        }

        public ConsumeItems<? extends ConsumerBuildComp> items(ItemStack... items){
            return add(new ConsumeItems<>(items));
        }

        public ConsumeLiquids<? extends ConsumerBuildComp> liquid(Liquid liquid, float amount){
            return liquids(new LiquidStack(liquid, amount));
        }

        public ConsumeLiquids<? extends ConsumerBuildComp> liquids(LiquidStack... liquids){
            return add(new ConsumeLiquids<>(liquids));
        }

        public ConsumePower<? extends ConsumerBuildComp> power(float usage){
            return add(new ConsumePower<>(usage, 0));
        }

        public ConsumePower<? extends ConsumerBuildComp> power(float usage, float capacity){
            return add(new ConsumePower<>(usage, capacity));
        }

        public <T extends ConsumerBuildComp> ConsumePower<?> powerCond(float usage, float capacity, Boolf<T> cons){
            return add(new ConsumePower(usage, capacity){
                @Override
                public float requestedPower(Building entity){
                    return ((Boolf)cons).get(entity) ? super.requestedPower(entity) : 0f;
                }
            });
        }

        public <T extends ConsumerBuildComp> ConsumePower<?> powerDynamic(Floatf<T> cons, float capacity, Cons<Stats> statBuilder){
            return add(new ConsumePower(0, capacity){
                @Override
                public float requestedPower(Building entity){
                    return ((Floatf)cons).get(entity);
                }

                @Override
                public void display(Stats stats){
                    statBuilder.get(stats);
                }
            });
        }

        public ConsumePower<? extends ConsumerBuildComp> powerBuffer(float usage, float capacity){
            return add(new ConsumePower<>(usage, capacity));
        }

        public <T extends BaseConsume<? extends ConsumerBuildComp>> T add(T consume){
            BaseConsume c = cons.get(consume.type());
            if(c == null){
                cons.put(consume.type(), consume);
                consume.parent = this;
                return consume;
            }else c.merge(consume);
            return (T) c;
        }

        public <T extends BaseConsume<? extends ConsumerBuildComp>> T get(ConsumeType<T> type){
            return (T) cons.get(type);
        }

        public Iterable<BaseConsume<? extends ConsumerBuildComp>> all(){
            tmpCons.clear();

            for(ConsumeType<?> type : ConsumeType.all()) {
                BaseConsume<?> c = cons.get(type);
                if (c != null) tmpCons.add(c);
            }

            return tmpCons;
        }

        public void remove(ConsumeType<?> type){
            cons.remove(type);
        }

        public void display(Stats stats){
            if(cons.size > 0 && !customDisplayOnly){
                if(showTime) stats.add(Stat.productionTime, craftTime / 60f, StatUnit.seconds);
                for(BaseConsume<?> c: all()){
                    c.display(stats);
                }
            }

            display.get(stats, this);
        }

        public boolean filter(ConsumeType<?> type, Content content){
            return filter.get(type, Empties.nilSetO()).contains(content);
        }

        public boolean selfAccess(ConsumeType<?> type, Content content){
            return selfAccess.get(type, Empties.nilSetO()).contains(content);
        }

        public enum Visibility{
            usable(Touchable.enabled),
            unusable(Touchable.disabled),
            hidden(Touchable.disabled);

            public final Touchable buttonValid;

            Visibility(Touchable touchable){
                buttonValid = touchable;
            }
        }
    }

    public static class ConsFilter{
        public ObjectMap<ConsumeType<?>, ObjectSet<Content>> optionalFilter = new ObjectMap<>();
        public ObjectMap<ConsumeType<?>, ObjectSet<Content>> allFilter = new ObjectMap<>();

        public void applyFilter(Iterable<BaseConsumers> consumers, Iterable<BaseConsumers> optional){
            if(optional != null){
                for(BaseConsumers cons: optional){
                    handle(cons, optionalFilter);
                    handle(cons, allFilter);
                }
            }

            if(consumers != null){
                for(BaseConsumers cons: consumers){
                    handle(cons, allFilter);
                    for(ObjectMap.Entry<ConsumeType<?>, ObjectSet<Content>> access: cons.selfAccess){
                        allFilter.get(access.key, ObjectSet::new).addAll();
                    }
                }
            }
        }

        /**
         * Filter, which will determine whether the input object is accepted under the specified type for the currently selected area.
         * If the optional filter has added the target object, return true.
         * @param type Filter type.
         * @param target Target object through filter.
         * @param acceptAll Do you accept all the requirements on the list?
         * @return Boolean value, whether to accept this object or not.
         * */
        public boolean filter(ConsumerBuildComp entity, ConsumeType<?> type, Content target, boolean acceptAll){
            if(optionalFilter.containsKey(type) && optionalFilter.get(type).contains(target)) return true;

            if(acceptAll) return allFilter.containsKey(type) && allFilter.get(type).contains(target);

            return entity.consumer().current != null && entity.consumer().current.filter(type, target);
        }

        private void handle(BaseConsumers cons, ObjectMap<ConsumeType<?>, ObjectSet<Content>> map){
            for(BaseConsume<?> c: cons.all()){
                if((c.filter()) != null){
                    ObjectSet<Content> all = allFilter.get(c.type(), ObjectSet::new);
                    ObjectSet<Content> set = map.get(c.type(), ObjectSet::new);
                    for(Content o: c.filter()){
                        set.add(o);
                        all.add(o);
                    }
                }
            }
        }
    }

    public static abstract class ConsumeItemBase<T extends ConsumerBuildComp> extends BaseConsume<T>{
        public ItemStack[] consItems;
        public int displayLim = 4;

        @Override
        public ConsumeType<?> type(){
            return ConsumeType.item;
        }

        public static void buildItemIcons(Table table, ItemStack[] items, boolean or, int limit) {
            int count = 0;
            for (ItemStack stack : items) {
                count++;
                if (count > 0 && or) table.add("/").set(Cell.defaults()).fill();
                if (limit >= 0 && count > limit){
                    table.add("...");
                    break;
                }

                table.add(new ItemImage(stack));
            }
        }
    }

    public static class ConsumeItemCond<T extends Building & ConsumerBuildComp> extends ConsumeItemBase<T>{
        public float minRadioactivity, maxRadioactivity;
        public float minFlammability, maxFlammability;
        public float minCharge, maxCharge;
        public float minExplosiveness, maxExplosiveness;

        public int usage;
        public Boolf<Item> filter;

        public Item getCurrCons(T entity){
            for(ItemStack stack: consItems){
                if(entity.items.get(stack.item) >= stack.amount) return stack.item;
            }
            return null;
        }

        public ItemStack[] getCons(){
            if(consItems == null){
                Seq<ItemStack> seq = new Seq<>();
                for(Item item: content.items()){
                    if(filter != null && !filter.get(item)) continue;

                    if(minRadioactivity != maxRadioactivity){
                        if(item.radioactivity > maxRadioactivity || item.radioactivity < minRadioactivity) continue;
                    }
                    if(minFlammability != maxFlammability){
                        if(item.flammability > maxFlammability || item.flammability < minFlammability) continue;
                    }
                    if(minCharge != maxCharge){
                        if(item.charge > maxCharge || item.charge < minCharge) continue;
                    }
                    if(minExplosiveness != maxExplosiveness){
                        if(item.explosiveness > maxExplosiveness || item.explosiveness < minExplosiveness) continue;
                    }

                    seq.add(new ItemStack(item, usage));
                }
                consItems = seq.toArray(ItemStack.class);
            }

            return consItems;
        }

        @Override
        public void buildIcons(Table table) {
            buildItemIcons(table, getCons(), true, displayLim);
        }

        @Override
        public void merge(BaseConsume<T> other){
            if(other instanceof ConsumeItemCond cons){
                minRadioactivity = Math.min(cons.minRadioactivity, minRadioactivity);
                minFlammability = Math.min(cons.minFlammability, minFlammability);
                minCharge = Math.min(cons.minCharge, minCharge);
                minExplosiveness = Math.min(cons.minExplosiveness, minExplosiveness);

                maxRadioactivity = Math.max(cons.maxRadioactivity, maxRadioactivity);
                maxFlammability = Math.max(cons.maxFlammability, maxFlammability);
                maxCharge = Math.max(cons.maxCharge, maxCharge);
                maxExplosiveness = Math.max(cons.maxExplosiveness, maxExplosiveness);

                usage += cons.usage;

                consItems = null;
                getCons();
            }else throw new IllegalArgumentException("only merge consume with same type");
        }

        @Override
        public void consume(T entity){
            ItemStack[] cons = getCons();
            if(cons.length == 0) return;

            Item curr = getCurrCons(entity);
            if(curr == null) return;
            for(ItemStack con: cons){
                if(con.item == curr){
                    entity.items.remove(con.item, con.amount);
                }
            }
        }

        @Override
        public void update(T entity){}

        @Override
        public void display(Stats stats){
            stats.add(Stat.input, table -> {
                table.row();
                table.table(t -> {
                    t.defaults().left().fill().padLeft(6);
                    t.add(Core.bundle.get("misc.item") + ":");

                    int count = 0;
                    for(ItemStack stack: getCons()){
                        if(count != 0) t.add("[gray]/[]");
                        if(count != 0 && count % 6 == 0) t.row();
                        t.add(new ItemDisplay(stack.item, stack.amount*60, true));
                        count++;
                    }
                }).left().padLeft(5);
            });
        }

        @Override
        public void build(T entity, Table table){
            Seq<Item> list = content.items().select(l -> !l.isHidden() && filter.get(l));
            MultiReqImage image = new MultiReqImage();
            list.each(item -> image.add(new ReqImage(item.uiIcon, () ->
                    entity.items != null && entity.items.get(item) > 0)));

            table.add(image).size(8 * 4);
        }

        @Override
        public float efficiency(T entity){
            ItemStack[] cons = getCons();
            if(cons.length == 0) return 1;

            Item curr = getCurrCons(entity);
            if(curr == null) return 0;

            for(ItemStack con: cons){
                if(curr == con.item) return 1;
            }
            return 0;
        }

        @Override
        public Seq<Content> filter(){
            return Seq.with(getCons()).map(s -> s.item);
        }
    }

    public static class ConsumeItems<T extends Building & ConsumerBuildComp> extends ConsumeItemBase<T>{
        private static final ObjectMap<Item, ItemStack> TMP = new ObjectMap<>();

        public boolean showPerSecond = true;

        public ConsumeItems(ItemStack[] items){
            this.consItems = items;
        }

        @Override
        public void buildIcons(Table table) {
            buildItemIcons(table, consItems, false, displayLim);
        }

        @Override
        public void merge(BaseConsume<T> other){
            if(other instanceof ConsumeItems cons){
                TMP.clear();
                for(ItemStack stack: consItems){
                    TMP.put(stack.item, stack);
                }

                for(ItemStack stack: cons.consItems){
                    TMP.get(stack.item, () -> new ItemStack(stack.item, 0)).amount += stack.amount;
                }

                consItems = TMP.values().toSeq().sort((a, b) -> a.item.id - b.item.id).toArray(ItemStack.class);
                return;
            }
            throw new IllegalArgumentException("only merge consume with same type");
        }

        @Override
        public void consume(T object){
            float f = multiple(object);
            for(ItemStack stack : consItems){
                int amount = stack.amount*((int)Math.floor(f)) + Mathf.num(Math.random()<f%1);
                object.items.remove(stack.item, amount);
            }
        }

        @Override
        public void update(T entity) {}

        @Override
        public void display(Stats stats) {
            stats.add(Stat.input, table -> {
                table.row();
                table.table(t -> {
                    t.defaults().left().grow().fill().padLeft(6);
                    t.add(Core.bundle.get("misc.item") + ":");
                    for(ItemStack stack: consItems){
                        t.add(showPerSecond? new ItemDisplay(stack.item, stack.amount, parent.craftTime, true):
                                new ItemDisplay(stack.item, stack.amount, true));
                    }
                }).left().padLeft(5);
            });
        }

        @Override
        public void build(T entity, Table table) {
            for(ItemStack stack : consItems){
                int amount = (int)(stack.amount*multiple(entity));
                if(amount == 0 && !entity.consumer().valid()) amount = stack.amount;

                int n = amount;
                table.add(new ReqImage(new ItemImage(stack.item.uiIcon, stack.amount),
                        () -> entity.items != null && entity.items.has(stack.item, n))).padRight(8);
            }
            table.row();
        }

        @Override
        public float efficiency(T entity){
            if(entity.items == null) return 0;
            for(ItemStack stack: consItems){
                if(entity.items == null || entity.items.get(stack.item) < stack.amount*multiple(entity)) return 0;
            }
            return 1;
        }

        @Override
        public Seq<Content> filter(){
            return Seq.with(consItems).map(s -> s.item);
        }
    }

    public static abstract class ConsumeLiquidBase<T extends Building & ConsumerBuildComp> extends BaseConsume<T>{
        public LiquidStack[] consLiquids;
        public int displayLim = 4;

        @Override
        public ConsumeType<?> type(){
            return ConsumeType.liquid;
        }

        @Override
        public void buildBars(T entity, Table bars) {
            for (LiquidStack stack : consLiquids) {
                bars.add(new Bar(
                        () -> stack.liquid.localizedName,
                        () -> stack.liquid.barColor != null? stack.liquid.barColor: stack.liquid.color,
                        () -> Math.min(entity.liquids.get(stack.liquid) / entity.block.liquidCapacity, 1f)
                ));
                bars.row();
            }
        }

        public static void buildLiquidIcons(Table table, LiquidStack[] liquids, boolean or, int limit){
            int count = 0;
            for (LiquidStack stack: liquids) {
                count++;
                if (count > 0 && or) table.add("/").set(Cell.defaults()).fillX();
                if (limit >= 0 && count > limit){
                    table.add("...");
                    break;
                }

                table.stack(
                        new Table(o -> {
                            o.left();
                            o.add(new Image(stack.liquid.fullIcon)).size(32f).scaling(Scaling.fit);
                        }),
                        new Table(t -> {
                            t.left().bottom();
                            t.add(stack.amount*60 >= 1000 ? UI.formatAmount((long) (stack.amount*60))+ "/s" : Mathf.round(stack.amount*600)/10f + "/s").style(Styles.outlineLabel);
                            t.pack();
                        })
                );
            }
        }
    }

    public static class ConsumeLiquidCond<T extends Building & ConsumerBuildComp> extends ConsumeLiquidBase<T>{
        public float minTemperature, maxTemperature;
        public float minFlammability, maxFlammability;
        public float minHeatCapacity, maxHeatCapacity;
        public float minViscosity, maxViscosity;
        public float minExplosiveness, maxExplosiveness;
        public int consGas = -1;
        public boolean isCoolant;

        public float usage;
        public Boolf<Liquid> filter;
        public Floatf<Liquid> usageMultiplier = e -> 1;
        public Floatf<Liquid> liquidEfficiency = l -> 1;

        public Liquid getCurrCons(T entity){
            for(LiquidStack liquid: consLiquids){
                if(entity.liquids.get(liquid.liquid) > 0.001f) return liquid.liquid;
            }
            return null;
        }

        public LiquidStack[] getCons(){
            if(consLiquids == null){
                Seq<LiquidStack> seq = new Seq<>();
                for(Liquid liquid: content.liquids()){
                    if(filter != null && !filter.get(liquid)) continue;

                    if(minTemperature != maxTemperature){
                        if(liquid.temperature > maxTemperature || liquid.temperature < minTemperature) continue;
                    }
                    if(minFlammability != maxFlammability){
                        if(liquid.flammability > maxFlammability || liquid.flammability < minFlammability) continue;
                    }
                    if(minHeatCapacity != maxHeatCapacity){
                        if(liquid.heatCapacity > maxHeatCapacity || liquid.heatCapacity < minHeatCapacity) continue;
                    }
                    if(minExplosiveness != maxExplosiveness){
                        if(liquid.explosiveness > maxExplosiveness || liquid.explosiveness < minExplosiveness) continue;
                    }
                    if(minViscosity != maxViscosity){
                        if(liquid.viscosity > maxViscosity || liquid.viscosity < minViscosity) continue;
                    }

                    if(isCoolant && !liquid.coolant) continue;

                    if((consGas == 1 && !liquid.gas) || (consGas == 0 && liquid.gas)) continue;

                    seq.add(new LiquidStack(liquid, usage*usageMultiplier.get(liquid)));
                }

                consLiquids = seq.toArray(LiquidStack.class);
            }

            return consLiquids;
        }

        @Override
        public void buildIcons(Table table) {
            buildLiquidIcons(table, getCons(), true, displayLim);
        }

        @Override
        public void merge(BaseConsume<T> other){
            if(other instanceof ConsumeLiquidCond cons){
                minTemperature = Math.min(cons.minTemperature, minTemperature);
                minFlammability = Math.min(cons.minFlammability, minFlammability);
                minHeatCapacity = Math.min(cons.minHeatCapacity, minHeatCapacity);
                minViscosity = Math.min(cons.minViscosity, minViscosity);
                minExplosiveness = Math.min(cons.minExplosiveness, minExplosiveness);

                maxTemperature = Math.max(cons.maxTemperature, maxTemperature);
                maxFlammability = Math.max(cons.maxFlammability, maxFlammability);
                maxHeatCapacity = Math.max(cons.maxHeatCapacity, maxHeatCapacity);
                maxViscosity = Math.max(cons.maxViscosity, maxViscosity);
                maxExplosiveness = Math.max(cons.maxExplosiveness, maxExplosiveness);

                usage += cons.usage;
                Floatf<Liquid> mul = usageMultiplier, mulO = cons.usageMultiplier;
                usageMultiplier = l -> mul.get(l)*mulO.get(l);

                consLiquids = null;
                getCons();
            }
            else throw new IllegalArgumentException("only merge consume with same type");
        }

        @Override
        public void consume(T entity){}

        @Override
        public void update(T entity){
            LiquidStack[] cons = getCons();
            if(cons.length == 0) return;

            Liquid curr = getCurrCons(entity);
            if(curr == null) return;

            for(LiquidStack con: cons){
                if(con.liquid == curr){
                    entity.liquids.remove(con.liquid, con.amount*parent.delta(entity)*multiple(entity));
                    return;
                }
            }
        }

        @Override
        public void display(Stats stats){
            stats.add(Stat.input, table -> {
                table.row();
                table.table(t -> {
                    t.defaults().left().fill().padLeft(6);
                    t.add(Core.bundle.get("misc.liquid") + ":");

                    int count = 0;
                    for(LiquidStack stack: getCons()){
                        if(count != 0) t.add("[gray]/[]");
                        if(count != 0 && count % 6 == 0) t.row();
                        t.add(new LiquidDisplay(stack.liquid, stack.amount*60, true));
                        count++;
                    }
                }).left().padLeft(5);
            });
        }

        @Override
        public void build(T entity, Table table){
            Seq<Liquid> list = content.liquids().select(l -> !l.isHidden() && filter.get(l));
            MultiReqImage image = new MultiReqImage();
            list.each(liquid -> image.add(new ReqImage(liquid.uiIcon, () ->
                    entity.liquids != null && entity.liquids.get(liquid) > 0)));

            table.add(image).size(8 * 4);
        }

        @Override
        public float efficiency(T entity){
            LiquidStack[] cons = getCons();
            if(cons.length == 0) return 1;

            Liquid curr = getCurrCons(entity);
            if(curr == null) return 0;

            for(LiquidStack stack: cons){
                if(curr == stack.liquid){
                    return liquidEfficiency.get(stack.liquid)*Mathf.clamp(entity.liquids.get(stack.liquid)/stack.amount);
                }
            }
            return 0;
        }

        @Override
        public Seq<Content> filter(){
            return Seq.with(getCons()).map(s -> s.liquid);
        }
    }

    public static class ConsumeLiquids<T extends Building & ConsumerBuildComp> extends ConsumeLiquidBase<T>{
        private static final ObjectMap<Liquid, LiquidStack> TMP = new ObjectMap<>();

        public boolean portion = false;

        public ConsumeLiquids(LiquidStack[] liquids){
            this.consLiquids = liquids;
        }

        public ConsumeType<?> type(){
            return ConsumeType.liquid;
        }

        @Override
        public void buildIcons(Table table) {
            buildLiquidIcons(table, consLiquids, false, displayLim);
        }

        public void portion(){
            this.portion = true;
        }

        @Override
        public void merge(BaseConsume<T> other){
            if(other instanceof ConsumeLiquids cons){
                TMP.clear();
                for(LiquidStack stack: consLiquids){
                    TMP.put(stack.liquid, stack);
                }

                for(LiquidStack stack: cons.consLiquids){
                    TMP.get(stack.liquid, () -> new LiquidStack(stack.liquid, 0)).amount += stack.amount;
                }

                consLiquids = TMP.values().toSeq().sort((a, b) -> a.liquid.id - b.liquid.id).toArray(LiquidStack.class);
                return;
            }
            throw new IllegalArgumentException("only merge consume with same type");
        }

        @Override
        public void consume(T entity) {
            if(portion) for(LiquidStack stack: consLiquids){
                entity.liquids.remove(stack.liquid, stack.amount*60*multiple(entity));
            }
        }

        @Override
        public void update(T entity) {
            if(!portion) for(LiquidStack stack: consLiquids){
                entity.liquids.remove(stack.liquid, stack.amount*parent.delta(entity)*multiple(entity));
            }
        }

        @Override
        public void display(Stats stats) {
            stats.add(Stat.input, table -> {
                table.row();
                table.table(t -> {
                    t.defaults().left().fill().padLeft(6);
                    t.add(Core.bundle.get("misc.liquid") + ":");
                    for(LiquidStack stack: consLiquids){
                        t.add(new LiquidDisplay(stack.liquid, stack.amount*60, true));
                    }
                }).left().padLeft(5);
            });
        }

        @Override
        public void build(T entity, Table table) {
            for(LiquidStack stack : consLiquids){
                table.add(new ReqImage(stack.liquid.uiIcon,
                        () -> entity.liquids != null && entity.liquids.get(stack.liquid) > 0)).padRight(8);
            }
            table.row();
        }

        @Override
        public float efficiency(T entity){
            if(entity.liquids == null) return 0;
            if(portion){
                for(LiquidStack stack: consLiquids){
                    if(entity.liquids.get(stack.liquid) < stack.amount*multiple(entity)*60) return 0;
                }
                return 1;
            }else{
                float min = 1;

                for(LiquidStack stack: consLiquids){
                    min = Math.min(entity.liquids.get(stack.liquid)/(stack.amount*multiple(entity)), min);
                }

                if(min < 0.0001f) return 0;
                return Mathf.clamp(min);
            }
        }

        @Override
        public Seq<Content> filter(){
            return Seq.with(consLiquids).map(s -> s.liquid);
        }
    }

    public static class ConsumePayload<T extends Building & ConsumerBuildComp> extends BaseConsume<T>{
        private static final ObjectMap<UnlockableContent, PayloadStack> TMP = new ObjectMap<>();

        public int displayLim = 4;
        public PayloadStack[] payloads;

        public ConsumePayload(PayloadStack[] payloads){
            this.payloads = payloads;
        }

        @Override
        public ConsumeType<?> type(){
            return ConsumeType.payload;
        }

        @Override
        public void buildIcons(Table table) {
            buildPayloadIcons(table, payloads, displayLim);
        }

        public static void buildPayloadIcons(Table table, PayloadStack[] payloads, int displayLim) {
            int count = 0;
            for (PayloadStack stack : payloads) {
                count++;
                if (displayLim >= 0 && count > displayLim){
                    table.add("...");
                    break;
                }

                table.stack(
                        new Table(o -> {
                            o.left();
                            o.add(new Image(stack.item.fullIcon)).size(32f).scaling(Scaling.fit);
                        }),
                        new Table(t -> {
                            t.left().bottom();
                            t.add(stack.amount + "").style(Styles.outlineLabel);
                            t.pack();
                        })
                );
            }
        }

        @Override
        public void merge(BaseConsume<T> other){
            if(other instanceof ConsumePayload cons){
                TMP.clear();
                for(PayloadStack stack: payloads){
                    TMP.put(stack.item, stack);
                }

                for(PayloadStack stack: ((ConsumePayload<T>)cons).payloads){
                    TMP.get(stack.item, () -> new PayloadStack(stack.item, 0)).amount += stack.amount;
                }

                payloads = TMP.values().toSeq().sort((a, b) -> a.item.id - b.item.id).toArray(PayloadStack.class);
            }
            else throw new IllegalArgumentException("only merge consume with same type");
        }

        @Override
        public float efficiency(T build){
            for(PayloadStack stack : payloads){
                if(!build.getPayloads().contains(stack.item, stack.amount)){
                    return 0f;
                }
            }
            return 1f;
        }

        @Override
        public void consume(T build){
            for(PayloadStack stack : payloads){
                build.getPayloads().remove(stack.item, stack.amount);
            }
        }

        @Override
        public void update(T entity){}

        @Override
        public void display(Stats stats){
            for(PayloadStack stack : payloads){
                stats.add(Stat.input, t -> {
                    t.add(new ItemImage(stack));
                    t.add(stack.item.localizedName).padLeft(4).padRight(4);
                });
            }
        }

        @Override
        public void build(Building build, Table table){
            PayloadSeq inv = build.getPayloads();

            table.table(c -> {
                int i = 0;
                for(var stack : payloads){
                    c.add(new ReqImage(new ItemImage(stack.item.uiIcon, stack.amount),
                            () -> inv.contains(stack.item, stack.amount))).padRight(8);
                    if(++i % 4 == 0) c.row();
                }
            }).left();
        }

        @Override
        public Seq<Content> filter(){
            return Seq.with(payloads).map(s -> s.item);
        }
    }

    /** Only save the consumption parameters, and the actual application of energy consumption itself is still the default consumption. */
    public static class ConsumePower<T extends Building & ConsumerBuildComp> extends BaseConsume<T>{
        public float usage;
        public float capacity;
        public boolean buffered;

        public boolean showIcon;

        Seq<ConsumePower<T>> others = new Seq<>(ConsumePower.class);

        public ConsumePower(float usage, float capacity){
            this.usage = usage;
            this.buffered = capacity > 0;
            this.capacity = capacity;
        }

        public ConsumeType<?> type(){
            return ConsumeType.power;
        }

        @Override
        public boolean hasIcons() {
            return showIcon;
        }

        @Override
        public void buildIcons(Table table) {
            if (showIcon) {
                buildPowerImage(table, usage);
            }
        }

        public static void buildPowerImage(Table table, float usage) {
            table.stack(
                    new Table(o -> {
                        o.left();
                        o.add(new Image(Icon.power)).size(32f).scaling(Scaling.fit);
                    }),
                    new Table(t -> {
                        t.left().bottom();
                        t.add(usage *60 >= 1000 ? UI.formatAmount((long) (usage *60)) + "/s" : (int)(usage*60) + "/s").style(Styles.outlineLabel);
                        t.pack();
                    })
            );
        }

        @Override
        public void merge(BaseConsume<T> other){
            if(other instanceof ConsumePower cons){
                buffered |= cons.buffered;
                capacity += cons.capacity;

                others.add(cons);
                return;
            }
            throw new IllegalArgumentException("only merge consume with same type");
        }

        public float requestedPower(T entity){
            float res = usage;
            for(ConsumePower<T> other: others.items){
                if (other == null) continue;
                res += other.requestedPower(entity);
            }
            return res;
        }

        @Override
        public void buildBars(T entity, Table bars) {
            Boolp buffered = () -> entity.block.consPower.buffered;
            Floatp capacity = () -> entity.block.consPower.capacity;
            bars.add(new Bar(
                    () -> buffered.get() ? Core.bundle.format("bar.poweramount", Float.isNaN(entity.power.status*capacity.get())?
                            "<ERROR>": (int)(entity.power.status*capacity.get())): Core.bundle.get("bar.power"),
                    () -> Pal.powerBar,
                    () -> Mathf.zero(entity.block.consPower.requestedPower(entity)) && entity.power.graph.getPowerProduced() + entity.power.graph.getBatteryStored() > 0f? 1f: entity.power.status)).growX();
            bars.row();
        }

        @Override
        public void build(T tile, Table table){}

        @Override
        public void update(T entity){}

        @Override
        public float efficiency(T entity){
            if(entity.power == null) return 0;
            if(buffered){
                return entity.power.status > 0? 1: 0;
            }else{
                return entity.power.status;
            }
        }

        @Override
        public void display(Stats stats) {
            stats.add(Stat.powerUse, usage * 60f, StatUnit.powerSecond);
        }

        @Override
        public void consume(T entity) {}

        @Override
        public Seq<Content> filter(){
            return null;
        }
    }

    public static class ConsumeType<T extends BaseConsume<?>>{
        private static final Seq<ConsumeType<?>> allType = new Seq<>();
        private final int id;
        private final Class<T> type;
        private final ContentType contType;

        public ConsumeType(Class<T> type, ContentType cType){
            id = allType.size;
            this.type = type;
            contType = cType;
            allType.add(this);
        }

        public Class<T> getType(){
            return type;
        }

        public final ContentType cType(){
            return contType;
        }

        public final int id(){
            return id;
        }

        public static ConsumeType<?>[] all(){
            return allType.toArray(ConsumeType.class);
        }

        public static <E extends BaseConsume<?>> ConsumeType<? extends E> add(Class<E> type, ContentType cType){
            return new ConsumeType<>(type, cType);
        }

        public static final ConsumeType<ConsumePower<?>> power = (ConsumeType<ConsumePower<?>>) add(ConsumePower.class, null);
        public static final ConsumeType<ConsumeItemBase<?>> item = (ConsumeType<ConsumeItemBase<?>>) add(ConsumeItemBase.class, ContentType.item);
        public static final ConsumeType<ConsumeLiquidBase<?>> liquid = (ConsumeType<ConsumeLiquidBase<?>>) add(ConsumeLiquidBase.class, ContentType.liquid);
        public static final ConsumeType<ConsumePayload<?>> payload = (ConsumeType<ConsumePayload<?>>) add(ConsumePayload.class, null);
    }
}
