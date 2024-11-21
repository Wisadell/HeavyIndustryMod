package heavyindustry.entities.other;

import arc.Core;
import arc.Events;
import arc.func.*;
import arc.graphics.*;
import arc.math.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import mindustry.ctype.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.meta.*;
import heavyindustry.entities.other.Components.*;
import heavyindustry.entities.other.Comsunes.*;
import heavyindustry.ui.display.*;

@SuppressWarnings({"unchecked", "rawtypes"})
public final class Producers {
    public static abstract class BaseProduce<T extends ProducerBuildComp>{
        public Floatf<T> prodMultiplier;

        public BaseProducers parent;
        public boolean blockWhenFull = true;

        /** Output resource type. */
        public abstract ProduceType<?> type();

        public Color color(){
            return null;
        }

        public boolean hasIcons(){
            return true;
        }

        public abstract void buildIcons(Table table);
        public abstract void merge(BaseProduce<T> other);
        public abstract void produce(T entity);
        public abstract void update(T entity);
        public abstract void display(Stats stats);
        public void buildBars(T entity, Table bars){}
        public abstract boolean valid(T entity);

        public void dump(T entity){}

        public float multiple(T entity){
            return (prodMultiplier == null? 1: prodMultiplier.get(entity))*entity.prodMultiplier();
        }

        public <N extends ProducerBuildComp> BaseProduce<T> setMultiple(Floatf<N> multiple){
            prodMultiplier = (Floatf<T>) multiple;
            return this;
        }
    }

    /** Output list, bind a consumption list, and execute the corresponding production list while executing consumption to achieve factory production. */
    public static class BaseProducers{
        final static Color TRANS = new Color(0, 0, 0, 0);
        protected static final Seq<BaseProduce<?>> tmpProd = new Seq<>();

        protected final ObjectMap<ProduceType<?>, BaseProduce<?>> prod = new ObjectMap<>();

        /** The color of this production item is usually used to quickly select colors for purposes such as determining the top of the drawing. */
        public Color color = TRANS;
        /** Post initialized variables should not be manually changed, as they are bound to consumption items that match this production. */
        public BaseConsumers cons;

        public BaseProducers setColor(Color color){
            this.color = color;
            return this;
        }

        public ProduceItems<?> item(Item item, int amount){
            return items(new ItemStack(item, amount));
        }

        public ProduceItems<?> items(ItemStack... items){
            return add(new ProduceItems<>(items));
        }

        public ProduceLiquids<?> liquid(Liquid liquid, float amount){
            return liquids(new LiquidStack(liquid, amount));
        }

        public ProduceLiquids<?> liquids(LiquidStack... liquids){
            return add(new ProduceLiquids<>(liquids));
        }

        public ProducePower<?> power(float prod){
            return add(new ProducePower<>(prod));
        }

        public <T extends BaseProduce<?>> T add(T produce){
            BaseProduce p = prod.get(produce.type());
            if(p == null){
                prod.put(produce.type(), produce);
                produce.parent = this;
                if(color == TRANS && produce.color() != null){
                    color = produce.color();
                }
                return produce;
            }else p.merge(produce);
            return (T) p;
        }

        public <T extends BaseProduce<?>> T get(ProduceType<T> type){
            return (T) prod.get(type);
        }

        public Iterable<BaseProduce<?>> all(){
            tmpProd.clear();

            for(ProduceType<?> type : ProduceType.all()) {
                BaseProduce<?> p = prod.get(type);
                if(p != null) tmpProd.add(p);
            }

            return tmpProd;
        }

        public void remove(ProduceType<?> type){
            prod.remove(type);
        }

        public void display(Stats stats){
            if(prod.size > 0){
                for(BaseProduce<?> p: prod.values().toSeq().sort((a, b) -> a.type().id() - b.type().id())){
                    p.display(stats);
                }
            }
        }
    }

    public static class ProduceItems<T extends Building & ProducerBuildComp> extends BaseProduce<T>{
        private static final ObjectMap<Item, ItemStack> TMP = new ObjectMap<>();

        public boolean showPerSecond = true;

        public int displayLim = 4;

        //Control whether the product is randomly generated (i.e. whether it is a separator)
        public boolean random = false;
        public ItemStack[] items;

        public ProduceItems(ItemStack[] items){
            this.items = items;
        }

        public ProduceItems<T> random(){
            this.random = true;
            return this;
        }

        @Override
        public ProduceType<ProduceItems<?>> type() {
            return ProduceType.item;
        }

        @Override
        public Color color(){
            return items[0].item.color;
        }

        @Override
        public void buildIcons(Table table) {
            if (random){
                ItemStack[] i = new ItemStack[items.length];
                for(int l = 0; l < i.length; l++) {
                    i[l] = items[l].copy();
                    i[l].amount = 0;
                }
                ConsumeItemBase.buildItemIcons(table, i, true, displayLim);
            }else ConsumeItemBase.buildItemIcons(table, items, false, displayLim);
        }

        @Override
        public void merge(BaseProduce<T> other){
            if(other instanceof ProduceItems cons){
                TMP.clear();
                for(ItemStack stack: items){
                    TMP.put(stack.item, stack);
                }

                for(ItemStack stack: cons.items){
                    TMP.get(stack.item, () -> new ItemStack(stack.item, 0)).amount += stack.amount;
                }

                items = TMP.values().toSeq().sort((a, b) -> a.item.id - b.item.id).toArray(ItemStack.class);
                return;
            }
            throw new IllegalArgumentException("only merge production with same type");
        }

        @Override
        public void produce(T entity){
            float f = multiple(entity);
            if(!random){
                for(ItemStack stack: items){
                    int amount = stack.amount*((int)Math.floor(f)) + Mathf.num(Math.random()<f%1);
                    amount = Math.min(amount, entity.block.itemCapacity - entity.items.get(stack.item));
                    for (int i = 0; i < amount; i++) {
                        entity.handleItem(entity, stack.item);
                    }
                }
            }else{
                //Randomly generate a product, change the amount parameter to weight.
                int sum = 0;
                for(ItemStack stack : items){
                    sum += stack.amount;
                }

                int i = Mathf.random(sum);
                int count = 0;
                Item item = null;

                for(ItemStack stack : items){
                    if(i >= count && i < count + stack.amount){
                        item = stack.item;
                        break;
                    }
                    count += stack.amount;
                }
                if(item != null){
                    int amount = (int)(Math.floor(f) + Mathf.num(Math.random()<f%1));
                    amount = Math.min(amount, entity.block.itemCapacity - entity.items.get(item));
                    for (int l = 0; l < amount; l++) {
                        entity.handleItem(entity, item);
                    }
                }
            }
        }

        @Override
        public void update(Building entity) {

        }

        @Override
        public void dump(Building entity) {
            for(ItemStack stack : items){
                if(entity.items.get(stack.item) > 0) entity.dump(stack.item);
            }
        }

        @Override
        public void display(Stats stats) {
            stats.add(Stat.output, table -> {
                table.row();
                table.table(t -> {
                    t.defaults().left().fill().padLeft(6);
                    t.add(Core.bundle.get("misc.item") + ":").left();
                    if(!random){
                        for(ItemStack stack: items){
                            t.add(showPerSecond? new ItemDisplay(stack.item, stack.amount, parent.cons.craftTime, true):
                                    new ItemDisplay(stack.item, stack.amount, true));
                        }
                    }else{
                        int[] total = {0};
                        int[] n = {items.length, items.length};
                        t.table(item -> {
                            for(ItemStack stack: items){
                                item.add(new ItemDisplay(stack.item, 0, true));
                                total[0] += stack.amount;
                                if(--n[0] > 0) item.add("/");
                            }
                            item.row();
                            for(ItemStack stack: items){
                                item.add("[gray]" + (int)(((float)stack.amount)/((float)total[0])*100) + "%");
                                if(--n[1] > 0) item.add();
                            }
                        });
                    }
                }).left().padLeft(5);
            });
        }

        @Override
        public boolean valid(T entity){
            if(entity.items == null) return false;

            boolean res = false;
            for(ItemStack stack : items){
                if(entity.items.get(stack.item) + stack.amount*multiple(entity) > entity.block.itemCapacity){
                    if(blockWhenFull) return false;
                }else res = true;
            }
            return res;
        }
    }

    public static class ProduceLiquids<T extends Building & ProducerBuildComp> extends BaseProduce<T>{
        private static final ObjectMap<Liquid, LiquidStack> TMP = new ObjectMap<>();

        public int displayLim = 4;
        public boolean portion = false;
        public LiquidStack[] liquids;

        public ProduceLiquids(LiquidStack[] liquids){
            this.liquids = liquids;
        }

        public ProduceLiquids<T> portion(){
            this.portion = true;
            return this;
        }

        @Override
        public void buildBars(T entity, Table bars) {
            for (LiquidStack stack : liquids) {
                bars.add(new Bar(
                        () -> stack.liquid.localizedName,
                        () -> stack.liquid.barColor != null? stack.liquid.barColor: stack.liquid.color,
                        () -> Math.min(entity.liquids.get(stack.liquid) / entity.block.liquidCapacity, 1f)
                ));
                bars.row();
            }
        }

        @Override
        public ProduceType<ProduceLiquids<?>> type(){
            return ProduceType.liquid;
        }

        @Override
        public Color color(){
            return liquids[0].liquid.color;
        }

        @Override
        public void buildIcons(Table table) {
            ConsumeLiquidBase.buildLiquidIcons(table, liquids, false, displayLim);
        }

        @Override
        public void merge(BaseProduce<T> other){
            if(other instanceof ProduceLiquids cons){
                TMP.clear();
                for(LiquidStack stack: liquids){
                    TMP.put(stack.liquid, stack);
                }

                for(LiquidStack stack: cons.liquids){
                    TMP.get(stack.liquid, () -> new LiquidStack(stack.liquid, 0)).amount += stack.amount;
                }

                liquids = TMP.values().toSeq().sort((a, b) -> a.liquid.id - b.liquid.id).toArray(LiquidStack.class);
                return;
            }
            throw new IllegalArgumentException("only merge production with same type");
        }

        @Override
        public void produce(T entity) {
            if(portion) for(LiquidStack stack: liquids){
                entity.handleLiquid(entity, stack.liquid, stack.amount*60);
            }
        }

        @Override
        public void update(T entity) {
            if(!portion) for(LiquidStack stack: liquids){
                float amount = stack.amount*parent.cons.delta(entity) * multiple(entity);
                amount = Math.min(amount, entity.block.liquidCapacity - entity.liquids.get(stack.liquid));
                entity.handleLiquid(entity, stack.liquid, amount);
            }
        }

        @Override
        public void dump(T entity) {
            for(LiquidStack stack: liquids){
                if(entity.liquids.get(stack.liquid) > 0.01f) entity.dumpLiquid(stack.liquid);
            }
        }

        @Override
        public void display(Stats stats) {
            stats.add(Stat.output, table -> {
                table.row();
                table.table(t -> {
                    t.defaults().left().fill().padLeft(6);
                    t.add(Core.bundle.get("misc.liquid") + ":").left();
                    for(LiquidStack stack: liquids){
                        t.add(new LiquidDisplay(stack.liquid, stack.amount*60, true));
                    }
                }).left().padLeft(5);
            });
        }

        @Override
        public boolean valid(T entity){
            if(entity.liquids == null) return false;

            boolean res = false;
            for(LiquidStack stack: liquids){
                if(entity.liquids.get(stack.liquid) + stack.amount*multiple(entity) > entity.block.liquidCapacity - 0.001f){
                    if(blockWhenFull) return false;
                }else res = true;
            }
            return res;
        }
    }

    public static class ProducePayload<T extends Building & ProducerBuildComp> extends BaseProduce<T>{
        private static final ObjectMap<UnlockableContent, PayloadStack> TMP = new ObjectMap<>();

        public int displayLim = 4;
        public PayloadStack[] payloads;
        public Func2<T, UnlockableContent, Payload> payloadMaker = this::makePayloadDef;
        public Boolf2<T, UnlockableContent> valid;

        public ProducePayload(PayloadStack[] payloads, Boolf2<T, UnlockableContent> valid){
            this.payloads = payloads;
            this.valid = valid;
        }

        private Payload makePayloadDef(T ent, UnlockableContent type){
            if(type instanceof UnitType unitType){
                Unit unit = unitType.create(ent.team);
                Events.fire(new EventType.UnitCreateEvent(unit, ent));
                return new UnitPayload(unit);
            }else if(type instanceof Block block){
                return new BuildPayload(block, ent.team);
            }
            throw new IllegalArgumentException("default payload maker can only make 'Building' and 'Unit', if you want to make other things, please use custom payload maker to field 'payloadMaker'");
        }

        @Override
        public ProduceType<?> type(){
            return ProduceType.payload;
        }

        @Override
        public void buildIcons(Table table) {
            ConsumePayload.buildPayloadIcons(table, payloads, displayLim);
        }

        @Override
        public void merge(BaseProduce<T> other){
            if(other instanceof ProducePayload prod){
                TMP.clear();
                for(PayloadStack stack: payloads){
                    TMP.put(stack.item, stack);
                }

                for(PayloadStack stack: ((ProducePayload<T>)prod).payloads){
                    TMP.get(stack.item, () -> new PayloadStack(stack.item, 0)).amount += stack.amount;
                }

                payloads = TMP.values().toSeq().sort((a, b) -> a.item.id - b.item.id).toArray(PayloadStack.class);
            }else throw new IllegalArgumentException("only merge consume with same type");
        }

        @Override
        public void produce(T entity){
            for(PayloadStack stack: payloads){
                for(int i = 0; i < stack.amount; i++){
                    Payload payload = payloadMaker.get(entity, stack.item);
                    payload.set(entity.x, entity.y, entity.rotdeg());
                    if(entity.acceptPayload(entity, payload)) entity.handlePayload(entity, payload);
                }
            }
        }

        @Override
        public boolean valid(T entity){
            for(PayloadStack stack: payloads){
                if(!valid.get(entity, stack.item)) return false;
            }
            return true;
        }

        @Override
        public void update(T entity){}

        @Override
        public void display(Stats stats){
            for(PayloadStack stack : payloads){
                stats.add(Stat.output, t -> {
                    t.add(new ItemImage(stack));
                    t.add(stack.item.localizedName).padLeft(4).padRight(4);
                });
            }
        }
    }

    public static class ProducePower<T extends Building & ProducerBuildComp> extends BaseProduce<T>{
        public float powerProduction;
        public boolean showIcon = true;

        public ProducePower(float prod){
            powerProduction = prod;
        }

        @Override
        public ProduceType<ProducePower<?>> type(){
            return ProduceType.power;
        }

        @Override
        public boolean hasIcons() {
            return showIcon;
        }

        @Override
        public void buildIcons(Table table) {
            if (showIcon){
                ConsumePower.buildPowerImage(table, powerProduction);
            }
        }

        @Override
        public void merge(BaseProduce<T> other){
            if(other instanceof ProducePower cons){
                powerProduction += cons.powerProduction;
                return;
            }
            throw new IllegalArgumentException("only merge production with same type");
        }

        @Override
        public void produce(Building entity) {
            //Do not update energy production here.
        }

        @Override
        public void update(Building entity) {
            //No energy updates will be performed here.
        }

        @Override
        public void buildBars(T entity, Table bars) {
            Floatp prod = () -> entity.powerProdEfficiency()*entity.producer().current.get(ProduceType.power).powerProduction;
            Floatp cons = () -> {
                ConsumePower cp;
                return entity.block.consumesPower && entity.consumer().current != null
                        && (cp = entity.consumer().current.get(ConsumeType.power)) != null?
                        cp.usage*cp.multiple(entity): 0;
            };
            bars.add(new Bar(
                    () -> Core.bundle.format("bar.poweroutput", Strings.fixed(Math.max(prod.get() - cons.get(), 0)*60*entity.timeScale(), 1)),
                    () -> Pal.powerBar,
                    entity::powerProdEfficiency
            )).growX();
            bars.row();
        }

        @Override
        public void display(Stats stats) {
            stats.add(Stat.basePowerGeneration, powerProduction * 60.0f, StatUnit.powerSecond);
        }

        @Override
        public boolean valid(T entity){
            return entity.power != null;
        }
    }

    public static class ProduceType<T extends BaseProduce<?>>{
        private static final Seq<ProduceType<?>> allType = new Seq<>();

        private final int id;
        private final Class<T> type;

        public ProduceType(Class<T> type){
            id = allType.size;
            this.type = type;
            allType.add(this);
        }

        public Class<T> getType(){
            return type;
        }

        public final int id(){
            return id;
        }

        public static ProduceType<?>[] all(){
            return allType.toArray(ProduceType.class);
        }

        public static <E extends BaseProduce<?>> ProduceType<? extends E> add(Class<E> type){
            return new ProduceType<>(type);
        }

        public static final ProduceType<ProducePower<?>> power = (ProduceType<ProducePower<?>>) add(ProducePower.class);
        public static final ProduceType<ProduceItems<?>> item = (ProduceType<ProduceItems<?>>) add(ProduceItems.class);
        public static final ProduceType<ProduceLiquids<?>> liquid = (ProduceType<ProduceLiquids<?>>) add(ProduceLiquids.class);
        public static final ProduceType<ProducePayload<?>> payload = (ProduceType<ProducePayload<?>>) add(ProducePayload.class);
    }
}
