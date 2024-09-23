package heavyindustry.core;

import heavyindustry.content.*;
import heavyindustry.gen.*;
import heavyindustry.graphics.*;
import heavyindustry.ui.dialogs.*;
import heavyindustry.world.meta.*;
import java.util.*;
import arc.*;
import arc.flabel.*;
import arc.scene.*;
import arc.scene.ui.*;
import arc.util.*;
import mindustry.Vars;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

import static arc.Core.*;
import static mindustry.Vars.*;

/** Main entry point of the mod. Handles startup things like content loading, entity registering, and utility bindings. */
public class HeavyIndustryMod extends Mod{
    public static final String modName = "heavy-industry";
    public static String name(String add){
        return modName + "-" + add;
    }

    public static boolean onlyPlugIn = settings.getBool(name("plug-in-mode"));

    private static final String linkGitHub = "https://github.com/Wisadell/HeavyIndustryMod", author = "Wisadell";

    public HeavyIndustryMod(){
        Log.info("Loaded HeavyIndustry Mod constructor.");
        Events.on(ClientLoadEvent.class, e -> {
            HIIcon.load();
            if(onlyPlugIn) return;
            Element label = new FLabel(bundle.get("mod.heavy-industry.author") + author);
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
                    table.add(label).left().row();
                    table.add(bundle.get("mod.heavy-industry.class")).left().growX().wrap().pad(4).labelAlign(Align.left).row();
                    table.add(bundle.get("mod.heavy-industry.note")).left().growX().wrap().width(550f).maxWidth(600f).pad(4f).labelAlign(Align.left).row();
                    table.add(bundle.get("mod.heavy-industry.prompt")).left().growX().wrap().width(550f).maxWidth(600f).pad(4f).labelAlign(Align.left).row();
                }).grow().center().maxWidth(600f);
            }};
            dialog.show();
        });

        Events.on(FileTreeInitEvent.class, e -> {
            app.post(() -> {
                HIShaders.init();
                HICacheLayer.init();
            });
        });

        Events.on(DisposeEvent.class, e -> {
            HIShaders.dispose();
        });
    }

    @Override
    public void loadContent(){
        EntityRegistry.register();
        HISounds.load();
        if(onlyPlugIn) return;
        Log.info("Loading some heavy industry mod content.");
        HIItems.load();
        HILiquids.load();
        HIAttribute.load();
        HIStatusEffects.load();
        HIBullets.load();
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

        settings.defaults(name("plug-in-mode"), false);

        Vars.mods.locateMod(modName).meta.hidden = onlyPlugIn;
        if(onlyPlugIn){
            Mods.LoadedMod mod = Vars.mods.locateMod(modName);
            mod.meta.displayName = mod.meta.displayName + "-Plug-In";
            mod.meta.version = Vars.mods.locateMod(modName).meta.version + "-plug-in";
        }

        if(ui != null && ui.settings != null){
            BaseDialog dialog = new BaseDialog("tips");
            Runnable exit = () -> {
                dialog.hide();
                app.exit();
            };
            dialog.cont.add(bundle.format("mod.heavy-industry.reset-exit"));
            dialog.buttons.button("@confirm", exit).center().size(150, 50);

            ui.settings.addCategory(bundle.format("mod.heavy-industry.settings"), t -> {
                t.pref(new SettingsMenuDialog.SettingsTable.CheckSetting(name("plug-in-mode"), false, null) {
                    @Override
                    public void add(SettingsMenuDialog.SettingsTable table) {
                        CheckBox box = new CheckBox(title);

                        box.update(() -> box.setChecked(settings.getBool(name)));

                        box.changed(() -> {
                            settings.put(name, box.isChecked());
                            dialog.show();
                        });
                        box.left();
                        addDesc(table.add(box).left().padTop(3f).get());
                        table.row();
                    }
                });
            });
        }

        //Replace the original technology tree
        HIResearchDialog dialog = new HIResearchDialog();
        ResearchDialog research = ui.research;
        research.shown(() -> {
            dialog.show();
            Objects.requireNonNull(research);
            Time.runTask(1f, research::hide);
        });
    }
}
