package heavyindustry.core;

import arc.*;
import arc.flabel.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.scene.*;
import arc.scene.event.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import heavyindustry.content.*;
import heavyindustry.files.*;
import heavyindustry.game.*;
import heavyindustry.gen.*;
import heavyindustry.graphics.Draws.*;
import heavyindustry.graphics.*;
import heavyindustry.graphics.g3d.*;
import heavyindustry.input.*;
import heavyindustry.net.*;
import heavyindustry.ui.*;
import heavyindustry.ui.dialogs.*;
import heavyindustry.util.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.mod.Mods.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.ui.dialogs.*;
import mindustry.ui.dialogs.SettingsMenuDialog.*;
import mindustry.ui.dialogs.SettingsMenuDialog.SettingsTable.*;
import mindustry.ui.fragments.*;

import java.time.*;
import java.time.format.*;
import java.util.*;

import static arc.Core.*;
import static mindustry.Vars.*;

/**
 * Main entry point of the mod. Handles startup things like content loading, entity registering, and utility bindings.
 *
 * @author Eipusino
 */
public final class HeavyIndustryMod extends Mod {
    /** Commonly used static read-only String. do not change unless you know what you're doing. */
    public static final String modName = "heavy-industry";

    /** Omitting longer mod names is generally used to load mod sprites. */
    public static String name(String add) {
        return modName + "-" + add;
    }

    public static final boolean onlyPlugIn = settings.getBool("hi-plug-in-mode"), developer = settings.getBool("hi-developer-mode");

    private static final String linkGitHub = "https://github.com/Eipusino/HeavyIndustryMod", author = "Eipusino";

    /** jar internal navigation. */
    public static InternalFileTree internalTree = new InternalFileTree(HeavyIndustryMod.class);

    /** Modules present in both servers and clients. */
    public static InputAggregator inputAggregator;

    /** Modules only present in clients, typically rendering or auxiliary input utilities. */
    public static RenderContext renderContext;
    public static ModelPropDrawer modelPropDrawer;

    private static LoadedMod mod;

    private static boolean noRunEnv = false;

    public HeavyIndustryMod() {
        if (!headless && gl30 == null) {
            noRunEnv = true;
            Log.warn("HeavyIndustryMod only runs with OpenGL 3.0 (on desktop) or OpenGL ES 3.0 (on android) and above!");
        }

        if (noRunEnv()) return;

        Log.info("Loaded HeavyIndustry Mod constructor.");

        HIClassMap.load();

        Events.on(ClientLoadEvent.class, e -> {
            try {
                ReflectUtils.set(MenuFragment.class, ui.menufrag, "renderer", new MenuRendererF());
            } catch (Exception ex) {
                Log.err("Failed to replace renderer", ex);
            }

            HIIcon.load();

            if (onlyPlugIn) return;

            showDialog();
            showMultipleMods();
        });

        app.post(() -> mod = mods.getMod(HeavyIndustryMod.class));

        Events.on(FileTreeInitEvent.class, e -> {
            if (!headless) {
                HISounds.load();
                HIModels.load();
                app.post(() -> {
                    HIShaders.init();
                    HICacheLayer.init();

                    inputAggregator = new InputAggregator();
                    renderContext = new RenderContext();
                    modelPropDrawer = new ModelPropDrawer(HIShaders.modelProp, 8192, 16384);
                });
            }
        });

        Events.on(MusicRegisterEvent.class, e -> {
            if (!headless)
                HIMusics.load();
        });

        Events.on(DisposeEvent.class, e -> {
            if (!headless)
                HIShaders.dispose();
        });

        Utils.init();
    }

    @Override
    public void loadContent() {
        if (noRunEnv()) return;
        HICall.init();

        EntityRegister.register();
        WorldRegister.register();

        if (onlyPlugIn) return;

        HITeams.load();
        HIItems.load();
        HIStatusEffects.load();
        HILiquids.load();
        HIBullets.load();
        HIUnitTypes.load();
        HIBlocks.load();
        HIWeathers.load();
        HIOverride.load();
        HIPlanets.load();
        HISectorPresets.load();
        HITechTree.load();

        Utils.loadItems();
    }

    @Override
    public void init() {
        if (noRunEnv()) return;
        if (!headless) {
            ScreenSampler.setup();

            UIUtils.init();
        }

        settings.defaults("hi-closed-dialog", false);
        settings.defaults("hi-closed-multiple-mods", false);
        settings.defaults("hi-tesla-range", true);
        settings.defaults("hi-plug-in-mode", false);

        if (!onlyPlugIn && mods.getMod("extra-utilities") == null && isAprilFoolsDay()) {
            HIOverride.loadAprilFoolsDay();
            if (ui != null)
                Events.on(ClientLoadEvent.class, e -> Time.runTask(10f, HeavyIndustryMod::showAprilFoolsDayDialog));
        }

        if (ui != null) {
            if (ui.settings != null) {
                BaseDialog dialog = new BaseDialog("tips");
                Runnable exit = () -> {
                    dialog.hide();
                    app.exit();
                };
                dialog.cont.add(bundle.format("hi-reset-exit"));
                dialog.buttons.button("@confirm", exit).center().size(150, 50);

                //add heavy-industry settings
                ui.settings.addCategory(bundle.format("hi-settings"), HIIcon.reactionIcon, t -> {
                    t.checkPref("hi-closed-dialog", false);
                    t.checkPref("hi-closed-multiple-mods", false);
                    t.checkPref("hi-tesla-range", true);
                    t.checkPref("hi-enable-serpulo-sector-invasion", true);
                    t.pref(new CheckSetting("hi-plug-in-mode", false, null) {
                        @Override
                        public void add(SettingsTable table) {
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
                    t.checkPref("hi-developer-mode", false);
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

        setString();
    }

    public static boolean isHeavyIndustry(@Nullable Content content) {
        return content != null && isHeavyIndustry(content.minfo.mod);
    }

    public static boolean isHeavyIndustry(@Nullable LoadedMod mod) {
        return mod != null && mod == mod();
    }

    public static LoadedMod mod() {
        if (mod == null) mod = mods.getMod(modName);
        return mod;
    }

    public static boolean noRunEnv() {
        return noRunEnv;
    }

    public static void resetSaves(Planet planet) {
        planet.sectors.each(sector -> {
            if (sector.hasSave()) {
                sector.save.delete();
                sector.save = null;
            }
        });
    }

    public static void resetTree(TechTree.TechNode root) {
        root.reset();
        root.content.clearUnlock();
        root.children.each(HeavyIndustryMod::resetTree);
    }

    public static boolean isAprilFoolsDay() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("MMdd");
        String fd = sdf.format(date);
        return fd.equals("0401");
    }

    private static void showDialog() {
        if (settings.getBool("hi-closed-dialog")) return;

        FLabel label = new FLabel(bundle.get("hi-author") + author);
        BaseDialog dialog = new BaseDialog(bundle.get("hi-name")) {{
            buttons.button("@close", this::hide).size(210f, 64f);
            buttons.button((bundle.get("hi-link-github")), () -> {
                if (!app.openURI(linkGitHub)) {
                    ui.showErrorMessage("@linkfail");
                    app.setClipboardText(linkGitHub);
                }
            }).size(210f, 64f);
            cont.pane(t -> {
                t.image(atlas.find(modName + "-cover")).left().size(600f, 338f).pad(3f).row();
                t.add(bundle.get("hi-version")).left().growX().wrap().pad(4f).labelAlign(Align.left).row();
                t.add(label).left().row();
                t.add(bundle.get("hi-class")).left().growX().wrap().pad(4).labelAlign(Align.left).row();
                t.add(bundle.get("hi-note")).left().growX().wrap().width(550f).maxWidth(600f).pad(4f).labelAlign(Align.left).row();
                t.add(bundle.get("hi-prompt")).left().growX().wrap().width(550f).maxWidth(600f).pad(4f).labelAlign(Align.left).row();
                t.add(bundle.get("hi-other")).left().growX().wrap().width(550f).maxWidth(600f).pad(4f).labelAlign(Align.left).row();
            }).grow().center().maxWidth(600f);
        }};
        dialog.show();
    }

    private static void showAprilFoolsDayDialog() {
        BaseDialog dialog = new BaseDialog(bundle.get("hi-name")) {
            int con = 0;
            float bx, by;
        {
            cont.add(bundle.get("hi-ap-main"));
            buttons.button("", this::hide).update(b -> {
                b.setText(con > 0 ? con == 5 ? bundle.get("hi-ap-happy") : bundle.get("hi-ap-click") : bundle.get("hi-ap-ok"));
                if (con > 0) {
                    b.x = bx;
                    b.y = by;
                }
            }).size(140, 50).center();
        }
            @Override
            public void hide() {
                if (con >= 5) {
                    super.hide();
                    return;
                }
                con++;
                bx = Mathf.random(width * 0.8f);
                by = Mathf.random(height * 0.8f);
            }
        };
        dialog.show();
    }

    private static void showMultipleMods() {
        if (settings.getBool("hi-closed-multiple-mods")) return;

        boolean announces = true;

        for (LoadedMod mod : mods.orderedMods())
            if (!modName.equals(mod.meta.name) && !mod.meta.hidden) {
                announces = false;
                break;
            }

        if (announces) return;
        BaseDialog dialog = new BaseDialog("oh-no") {
            float time = 300f;
            boolean canClose;
        {
            update(() -> canClose = (time -= Time.delta) <= 0f);
            cont.add(bundle.get("hi-multiple-mods"));
            buttons.button("", this::hide).update(b -> {
                b.setDisabled(!canClose);
                b.setText(canClose ? "@close" : String.format("%s(%ss)", "@close", Strings.fixed(time / 60f, 1)));
            }).size(210f, 64f);
        }};
        dialog.show();
    }

    private static void setString() {
        String massage = bundle.get("hi-random-massage");
        String[] massageSplit = massage.split("/");

        int length = massageSplit.length;

        String massageRand = massageSplit[Mathf.random(length - 1)];

        if (ui == null || mods.getMod("extra-utilities") != null) return;

        HIMenuFragment subTitle = new HIMenuFragment(massageRand);
        subTitle.build(ui.menuGroup);
    }

    public static class HIMenuFragment {
        protected static final Mat setMat = new Mat(), reMat = new Mat();
        protected static final Vec2 vec2 = new Vec2();

        protected String title = "oh no";

        public HIMenuFragment() {}

        public HIMenuFragment(String title) {
            this.title = title;
        }

        public void build(Group parent) {
            parent.fill((x, y, w, h) -> {
                TextureRegion logo = atlas.find("logo");
                float width = graphics.getWidth(), height = graphics.getHeight() - scene.marginTop;
                float logoscl = Scl.scl(1) * logo.scale;
                float logow = Math.min(logo.width * logoscl, graphics.getWidth() - Scl.scl(20));
                float logoh = logow * (float) logo.height / logo.width;

                float fx = (int) (width / 2f);
                float fy = (int) (height - 6 - logoh) + logoh / 2 - (graphics.isPortrait() ? Scl.scl(30f) : 0f);
                if (settings.getBool("macnotch")) {
                    fy -= Scl.scl(macNotchHeight);
                }

                float ex = fx + logow / 3 - Scl.scl(1f), ey = fy - logoh / 3f - Scl.scl(2f);
                float ang = 12 + Mathf.sin(Time.time, 8, 2f);

                float dst = Mathf.dst(ex, ey, 0, 0);
                vec2.set(0, 0);
                float dx = Utils.dx(0, dst, vec2.angleTo(ex, ey) + ang);
                float dy = Utils.dy(0, dst, vec2.angleTo(ex, ey) + ang);

                reMat.set(Draw.trans());

                Draw.trans(setMat.setToTranslation(ex - dx, ey - dy).rotate(ang));
                Fonts.outline.draw(title, ex, ey, Color.yellow, Math.min(30f / title.length(), 1.5f) + Mathf.sin(Time.time, 8, 0.2f), false, Align.center);

                Draw.trans(reMat);
            }).touchable = Touchable.disabled;
        }
    }
}
