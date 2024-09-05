package heavyindustry.core;

import heavyindustry.content.*;
import heavyindustry.gen.*;
import heavyindustry.graphics.*;
import heavyindustry.ui.dialogs.*;
import heavyindustry.world.meta.*;
import java.util.Objects;
import arc.*;
import arc.flabel.*;
import arc.util.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

import static arc.Core.*;
import static mindustry.Vars.*;

/** Main entry point of the mod. Handles startup things like content loading, entity registering, and utility bindings. */
public class HeavyIndustryMod extends Mod{
    public static String ModName = "heavy-industry";
    public static String name(String add){
        return ModName + "-" + add;
    }

    private static final String linkGitHub = "https://github.com/Wisadell/HeavyIndustryMod";
    public HeavyIndustryMod(){
        Log.info("Loaded HeavyIndustry Mod constructor.");
        Events.on(ClientLoadEvent.class, e -> {
            HIIcon.load();
            BaseDialog dialog = new BaseDialog(bundle.get("mod.heavy-industry.name")){{
                buttons.button("@close", this::hide).size(210f, 64f);
                buttons.button((bundle.get("mod.heavy-industry.linkGithub")), () -> {
                    if (!app.openURI(linkGitHub)) {
                        ui.showErrorMessage("@linkfail");
                        app.setClipboardText(linkGitHub);
                    }
                }).size(210f, 64f);
                cont.pane(table -> {
                    table.image(atlas.find(name("cover"))).left().size(600f, 287f).pad(3f).row();
                    table.add(bundle.get("mod.heavy-industry.version")).left().growX().wrap().pad(4f).labelAlign(Align.left).row();
                    table.add(new FLabel(bundle.get("mod.heavy-industry.author"))).left().row();
                    table.add(bundle.get("mod.heavy-industry.class")).left().growX().wrap().pad(4).labelAlign(Align.left).row();
                    table.add(bundle.get("mod.heavy-industry.note")).left().growX().wrap().width(550f).maxWidth(600f).pad(4f).labelAlign(Align.left).row();
                    table.add(bundle.get("mod.heavy-industry.prompt")).left().growX().wrap().width(550f).maxWidth(600f).pad(4f).labelAlign(Align.left).row();
                }).grow().center().maxWidth(600f);
            }};
            dialog.show();
        });

        Events.on(FileTreeInitEvent.class, e -> {
            HIModels.load();
            app.post(() -> {
                HIShaders.init();
                HICacheLayer.init();
            });
        });

        Events.on(MusicRegisterEvent.class, e -> {
            HIMusics.load();
        });

        Events.on(DisposeEvent.class, e -> {
            HIShaders.dispose();
        });
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
        ResearchDialog research = ui.research;
        research.shown(() -> {
            dialog.show();
            Objects.requireNonNull(research);
            Time.runTask(1f, research::hide);
        });
    }
}
