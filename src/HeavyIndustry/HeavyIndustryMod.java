package HeavyIndustry;

import HeavyIndustry.content.*;
import HeavyIndustry.gen.HIIcon;
import HeavyIndustry.gen.HISounds;
import HeavyIndustry.graphics.HICacheLayer;
import HeavyIndustry.graphics.HIShaders;
import HeavyIndustry.ui.dialogs.HIResearchDialog;
import HeavyIndustry.world.meta.HIAttribute;
import java.util.Objects;
import arc.*;
import arc.flabel.FLabel;
import arc.scene.ui.Label;
import arc.util.*;
import mindustry.Vars;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

import static arc.Core.app;

public class HeavyIndustryMod extends Mod{
    public static String ModName = "heavy-industry";
    public static String name(String add){
        return ModName + "-" + add;
    }
    public static Mods.LoadedMod modInfo;
    public HeavyIndustryMod(){
        Log.info("Loaded HeavyIndustry Mod constructor.");
        Events.on(ClientLoadEvent.class, e -> {
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("@mod.heavy-industry.name");
                dialog.buttons.button("@close", dialog::hide).size(100f, 50f);
                dialog.cont.pane(table -> {
                    table.image(Core.atlas.find(name("img"))).left().size(600, 600).pad(3).row();
                    table.add("@mod.heavy-industry.version").left().growX().wrap().pad(4).labelAlign(Align.left).row();
                    Label flabel1 = new FLabel("@mod.heavy-industry.author");
                    table.add(flabel1).left().row();
                    table.add("Heavy Industry Java Mod").left().growX().wrap().pad(4).labelAlign(Align.left).row();
                    table.add("@mod.heavy-industry.note").left().growX().wrap().width(550).maxWidth(600).pad(4).labelAlign(Align.left).row();
                    table.add("@mod.heavy-industry.prompt").left().growX().wrap().width(550).maxWidth(600).pad(4).labelAlign(Align.left).row();
                }).grow().center().maxWidth(600);
                dialog.show();
            });
        });

        Events.on(ClientLoadEvent.class, ignored ->
            HIIcon.load()
        );

        Events.on(FileTreeInitEvent.class, e -> {
            app.post(HIShaders::init);
            app.post(HICacheLayer::init);
        });

        Events.on(DisposeEvent.class, e ->
                HIShaders.dispose()
        );
    }
    @Override
    public void loadContent(){
        Log.info("Loading some heavy industry mod content.");
        HISounds.load();
        HIItems.load();
        HILiquids.load();
        HIAttribute.load();
        HIStatusEffects.load();
        HIUnitTypes.load();
        HIBlocks.load();
        HIWeathers.load();
        HIOverride.load();
        HIPlanets.load();
        HISectorPresets.load();
        HITechTree.load();
    }

    @Override
    public void init(){
        super.init();
        HIResearchDialog dialog = new HIResearchDialog();
        ResearchDialog research = Vars.ui.research;
        research.shown(() -> {
            dialog.show();
            Objects.requireNonNull(research);
            Time.runTask(1f, research::hide);
        });
    }
}
