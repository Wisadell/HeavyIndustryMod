package heavyindustry.world.blocks.sandbox;

import arc.scene.style.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.ui.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.power.*;
import mindustry.world.meta.*;

import static arc.Core.*;
import static mindustry.Vars.*;

public class ResourceSource extends PowerGenerator {
    public ResourceSource(String name) {
        super(name);
        hasItems = hasLiquids = hasPower = true;
        outputsPower = outputsLiquid = true;
        clearOnDoubleTap = true;
        update = configurable = true;
        saveConfig = true;
        displayFlow = false;
        itemCapacity = 100;
        liquidCapacity = 100f;
        envEnabled = Env.any;
        buildVisibility = BuildVisibility.sandboxOnly;
        group = BlockGroup.transportation;

        config(Config.class, (ResourceSourceBuild b, Config config) -> b.con = config);
        configClear((ResourceSourceBuild b) -> b.con = new Config());
    }

    @Override
    public boolean outputsItems() {
        return true;
    }

    @Override
    public void setBars() {
        super.setBars();
        removeBar("liquid");
    }

    public class ResourceSourceBuild extends GeneratorBuild implements HeatBlock {
        protected Config con = new Config();

        @Override
        public void buildConfiguration(Table table) {
            resourceSourceSelector(table.table(Styles.black6).get(), con, () -> configure(con));
        }

        @Override
        public Config config() {
            return con;
        }

        @Override
        public float getPowerProduction(){
            return enabled ? con.power : 0f;
        }

        @Override
        public float heatFrac(){
            return 1;
        }
        @Override
        public float heat(){
            return con.heat;
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            for (int i = 0; i < content.items().size; i++) if (read.bool()) con.itemBits.set(i);
            for (int i = 0; i < content.liquids().size; i++) if (read.bool()) con.liquidBits.set(i);
            con.heatValue = read.f();
            con.power = read.f();
            con.heat = read.f();
        }

        @Override
        public void updateTile() {
            for (int i = 0; i < con.itemBits.length(); i++) if (con.itemBits.get(i)) {
                items.set(content.item(i), 1);
                dump(content.item(i));
                items.set(content.item(i), 0);
            }
            for (int i = 0; i < con.liquidBits.length(); i++) if (con.liquidBits.get(i)) {
                liquids.add(content.liquid(i), liquidCapacity);
                dumpLiquid(content.liquid(i));
            }
        }

        @Override
        public void write(Writes write) {
            super.write(write);
            for (int i = 0; i < content.items().size; i++) write.bool(con.itemBits.get(i));
            for (int i = 0; i < content.liquids().size; i++) write.bool(con.liquidBits.get(i));
            write.f(con.heatValue);
            write.f(con.power);
            write.f(con.heat);
        }
    }

    public static class Config {
        public Bits
                itemBits = new Bits(content.items().size),
                liquidBits = new Bits(content.liquids().size);
        public float heatValue, power, heat;
    }

    static ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle() {{
        checked = down = Styles.flatOver;
    }};

    static TextureRegionDrawable whiteui = (TextureRegionDrawable) Tex.whiteui;

    public static void resourceSourceSelector(Table cont, Config def, Runnable run) {
        cont.table(c -> {
            c.add(bundle.get("category.items")).color(Pal.accent).padBottom(10f);
            c.add();
            c.add(bundle.get("category.liquids")).color(Pal.accent).padBottom(10f).row();

            c.pane(Styles.smallPane, items -> content.items().each(item -> {
                Button button = items.button(new TextureRegionDrawable(item.uiIcon), style, () -> {
                    def.itemBits.flip(content.items().indexOf(item));
                    run.run();
                }).tooltip(item.localizedName).size(32f).pad(2.5f).get();
                button.setChecked(def.itemBits.get(content.items().indexOf(item)));
                if ((content.items().indexOf(item) + 1) % 4 == 0) items.row();
            })).maxHeight(148f);
            c.image(whiteui).growY().padLeft(10f).padRight(10f);
            c.pane(Styles.smallPane, liquids -> content.liquids().each(liquid -> {
                Button button = liquids.button(new TextureRegionDrawable(liquid.uiIcon), style, () -> {
                    def.liquidBits.flip(content.liquids().indexOf(liquid));
                    run.run();
                }).tooltip(liquid.localizedName).size(32f).pad(2.5f).get();
                button.setChecked(def.liquidBits.get(content.liquids().indexOf(liquid)));
                if ((content.liquids().indexOf(liquid) + 1) % 4 == 0) liquids.row();
            })).maxHeight(148f);
        }).row();
        cont.image(whiteui).growX().padTop(10f).padBottom(10f).row();
        cont.table(t -> {
            t.add(bundle.get("category.power") + ": ");
            t.field(def.power + "", TextField.TextFieldFilter.floatsOnly, s -> {
                def.power = Strings.parseFloat(s, 0f);
                run.run();
            });
        }).row();
        cont.table(t -> {
            t.add(bundle.get("bar.heat") + ": ");
            t.field(def.heat + "", TextField.TextFieldFilter.floatsOnly, s -> {
                def.heat = Strings.parseFloat(s, 0f);
                run.run();
            });
        });
        cont.margin(10f);
    }
}
