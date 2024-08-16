package HeavyIndustry;

import HeavyIndustry.content.*;
import HeavyIndustry.gen.HISounds;
import HeavyIndustry.graphics.HICacheLayer;
import HeavyIndustry.graphics.HIShaders;
import HeavyIndustry.ui.dialogs.HIResearchDialog;
import HeavyIndustry.world.HIClassMap;
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
    public HeavyIndustryMod(){
        Log.info("Loaded HeavyIndustry Mod constructor.");
        Events.on(ClientLoadEvent.class, e -> {
            BaseDialog dialog = new BaseDialog(Core.bundle.get("mod.heavy-industry.name"));
            dialog.buttons.button("@close", dialog::hide).size(210f, 64f);
            dialog.cont.pane(table -> {
                table.image(Core.atlas.find(name("cover"))).left().size(600f, 287f).pad(3f).row();
                table.add(Core.bundle.get("mod.heavy-industry.version")).left().growX().wrap().pad(4f).labelAlign(Align.left).row();
                Label flabel1 = new FLabel(Core.bundle.get("mod.heavy-industry.author"));
                table.add(flabel1).left().row();
                table.add(Core.bundle.get("mod.heavy-industry.class")).left().growX().wrap().pad(4).labelAlign(Align.left).row();
                table.add(Core.bundle.get("mod.heavy-industry.note")).left().growX().wrap().width(550f).maxWidth(600f).pad(4f).labelAlign(Align.left).row();
                table.add(Core.bundle.get("mod.heavy-industry.prompt")).left().growX().wrap().width(550f).maxWidth(600f).pad(4f).labelAlign(Align.left).row();
            }).grow().center().maxWidth(600f);
            dialog.show();
        });

        Events.on(FileTreeInitEvent.class, e -> app.post(() -> {
            HIShaders.init();
            HICacheLayer.init();
        }));

        Events.on(DisposeEvent.class, e ->
                HIShaders.dispose()
        );
    }
    @Override
    public void loadContent(){
        Log.info("Loading some heavy industry mod content.");
        HIClassMap.load();
        HISounds.load();
        HIItems.load();
        HILiquids.load();
        HIAttribute.load();
        HIStatusEffects.load();
        HIUnitTypes.load();
        HIBlocks.load();
        HIWeathers.load();
        HIOverride.load();
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
