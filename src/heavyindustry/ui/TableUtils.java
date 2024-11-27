package heavyindustry.ui;

import arc.audio.*;
import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.input.*;
import arc.math.*;
import arc.math.geom.*;
import arc.scene.*;
import arc.scene.actions.*;
import arc.scene.event.*;
import arc.scene.style.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import mindustry.core.*;
import mindustry.ctype.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.modules.*;
import heavyindustry.ui.dialogs.*;

import java.text.*;

import static arc.Core.*;
import static mindustry.Vars.*;

public final class TableUtils {
    private static final Vec2 ctrlVec = new Vec2();

    private static final DecimalFormat df = new DecimalFormat("######0.0");
    private static final Vec2 point = new Vec2(-1, -1);
    private static long lastToast;

    private static Table pTable = new Table(), floatTable = new Table();

    public static final float LEN = 60f;
    public static final float OFFSET = 12f;
    public static String format(float value) {
        return df.format(value);
    }

    public static PowerGraphInfoDialog powerInfoDialog;

    public static void init() {
        powerInfoDialog = new PowerGraphInfoDialog();
    }

    /** Based on {@link UI#formatAmount(long)} but for floats. */
    public static String formatAmount(float number) {
        if (number == Float.MAX_VALUE) return "infinite";
        if (number == Float.MIN_VALUE) return "-infinite";

        float mag = Math.abs(number);
        String sign = number < 0 ? "-" : "";
        if (mag >= 1_000_000_000f) {
            return sign + Strings.fixed(mag / 1_000_000_000f, 2) + "[gray]" + UI.billions + "[]";
        } else if (mag >= 1_000_000f) {
            return sign + Strings.fixed(mag / 1_000_000f, 2) + "[gray]" + UI.millions + "[]";
        } else if (mag >= 1000f) {
            return sign + Strings.fixed(mag / 1000f, 2) + "[gray]" + UI.thousands + "[]";
        } else {
            return sign + Strings.fixed(mag, 2);
        }
    }

    public static void collapseTextToTable(Table t, String text) {
        Table ic = new Table();
        ic.add(text).wrap().fillX().width(500f).padTop(2).padBottom(6).left();
        ic.row();
        Collapser coll = new Collapser(ic, true);
        coll.setDuration(0.1f);
        t.row();
        t.table(st -> {
            st.add(bundle.get("hi-click-to-show")).center();
            st.row();
            st.button(Icon.downOpen, Styles.emptyi, () -> coll.toggle(true)).update(i -> i.getStyle().imageUp = (!coll.isCollapsed() ? Icon.upOpen : Icon.downOpen)).pad(5).size(8).center();
        }).left();
        t.row();
        t.add(coll);
        t.row();
    }

    public static void addToTable(UnlockableContent c, Table t) {
        t.row();
        t.table(log -> {
            log.table(Styles.grayPanel, img -> {
                img.button(bt -> bt.image(c.uiIcon).size(64).pad(5), Styles.cleari, () -> ui.content.show(c)).left().tooltip("click to show");
            }).pad(10).margin(10).left();
            log.image(Tex.whiteui, Pal.accent).growY().width(3).pad(4).margin(5).left();
            log.table(info -> {
                Label n = info.add(c.localizedName).wrap().fillX().left().maxWidth(graphics.getWidth() / 2f).get();
                info.row();
                info.image(Tex.whiteui, Pal.accent).left().width(n.getWidth() * 1.3f).height(3f).row();
                info.add(c.description).wrap().fillX().left().width(graphics.getWidth() / 2f).padTop(10).row();
                info.image(Tex.whiteui, Pal.accent).left().width(graphics.getWidth() / 2f).height(3f).row();
            }).left().pad(6);
        });
    }

    private static boolean pointValid() {
        return point.x >= 0 && point.y >= 0 && point.x <= world.width() * tilesize && point.y <= world.height() * tilesize;
    }

    private static class Inner extends Table {
        Inner(){
            name = "INNER";
            background(Tex.paneSolid);

            left();
            table(table -> {
                table.button(Icon.cancel, Styles.cleari, () -> {
                    actions(Actions.touchable(Touchable.disabled), Actions.moveBy(-width, 0, 0.4f, Interp.pow3In), Actions.remove());
                }).width(LEN).growY();
            }).growY().fillX().padRight(OFFSET);
        }

        public void init(float width) {
            setSize(width, starter.getHeight());
            setPosition(-this.width, starter.originY);
        }
    }

    private static final Table starter = new Table(Tex.paneSolid) {

    };

    public static final TextArea textArea = headless ? null : new TextArea("");

    public static int getLineNum(String string) {
        String dex = string.replaceAll("\r", "\n");
        return dex.split("\n").length;
    }

    public static void disableTable() {
        scene.root.removeChild(starter);
    }

    public static void showTable() {
        scene.root.addChildAt(3, starter);
    }

    public static void showInner(Table parent, Table children) {
        Inner inner = new Inner();

        parent.addChildAt(parent.getZIndex() + 1, inner);
        inner.init(parent.getWidth() + children.getWidth() + OFFSET);

        children.fill().pack();
        children.setTransform(true);
        inner.addChildAt(parent.getZIndex() + 2, children);
        inner.setScale(parent.scaleX, parent.scaleY);
        children.setScale(parent.scaleX, parent.scaleY);


        children.setPosition(inner.getWidth() - children.getWidth(), inner.y + (inner.getHeight() - children.getHeight()) / 2);

        inner.actions(Actions.moveTo(0, inner.y, 0.35f, Interp.pow3Out));
    }

    public static Table tableImageShrink(TextureRegion tex, float size, Table table) {
        return tableImageShrink(tex, size, table, c -> {});
    }

    public static Table tableImageShrink(TextureRegion tex, float size, Table table, Cons<Image> modifier) {
        float parma = Math.max(tex.height, tex.width);
        float f = Math.min(size, parma);
        Image image = new Image(tex);
        modifier.get(image);
        table.add(image).size(tex.width * f / parma, tex.height * f / parma);

        return table;
    }

    public static void itemStack(Table parent, ItemStack stack, ItemModule itemModule) {
        float size = LEN - OFFSET;
        parent.table(t -> {
            t.image(stack.item.fullIcon).size(size).left();
            t.table(n -> {
                Label l = new Label("");
                n.add(stack.item.localizedName + " ").left();
                n.add(l).left();
                n.add("/" + UI.formatAmount(stack.amount)).left().growX();
                n.update(() -> {
                    int amount = itemModule == null ? 0 : itemModule.get(stack.item);
                    l.setText(UI.formatAmount(amount));
                    l.setColor(amount < stack.amount ? Pal.redderDust : Color.white);
                });
            }).growX().height(size).padLeft(OFFSET / 2).left();
        }).growX().height(size).left().row();
    }

    public static void selectPos(Table parentT, Cons<Point2> cons) {
        Prov<Touchable> original = parentT.touchablility;
        Touchable parentTouchable = parentT.touchable;

        parentT.touchablility = () -> Touchable.disabled;

        if(!pTable.hasParent()) ctrlVec.set(camera.unproject(input.mouse()));

        if (!pTable.hasParent()) pTable = new Table(Tex.clear) {{
            update(() -> {
                if (state.isMenu()) {
                    remove();
                } else {
                    Vec2 v = camera.project(World.toTile(ctrlVec.x) * tilesize, World.toTile(ctrlVec.y) * tilesize);
                    setPosition(v.x, v.y, 0);
                }
            });
        }
            @Override
            public void draw() {
                super.draw();

                Lines.stroke(9, Pal.gray);
                drawLines();
                Lines.stroke(3, Pal.accent);
                drawLines();
            }

            private void drawLines() {
                Lines.square(x, y, 28, 45);
                Lines.line(x - OFFSET * 4, y, 0, y);
                Lines.line(x + OFFSET * 4, y, graphics.getWidth(), y);
                Lines.line(x, y - OFFSET * 4, x, 0);
                Lines.line(x, y + OFFSET * 4, x, graphics.getHeight());
            }
        };

        if (!pTable.hasParent()) floatTable = new Table(Tex.clear) {{
            update(() -> {
                if (state.isMenu()) remove();
            });
            touchable = Touchable.enabled;
            setFillParent(true);

            addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, KeyCode button) {
                    ctrlVec.set(camera.unproject(x, y));
                    return false;
                }
            });
        }};

        pTable.button(Icon.cancel, Styles.emptyi, () -> {
            cons.get(Tmp.p1.set(World.toTile(ctrlVec.x), World.toTile(ctrlVec.y)));
            parentT.touchablility = original;
            parentT.touchable = parentTouchable;
            pTable.remove();
            floatTable.remove();
        }).center();

        scene.root.addChildAt(Math.max(parentT.getZIndex() - 1, 0), pTable);
        scene.root.addChildAt(Math.max(parentT.getZIndex() - 2, 0), floatTable);
    }

    private static void scheduleToast(Runnable run) {
        long duration = (int) (3.5 * 1000);
        long since = Time.timeSinceMillis(lastToast);
        if (since > duration) {
            lastToast = Time.millis();
            run.run();
        } else {
            Time.runTask((duration - since) / 1000f * 60f, run);
            lastToast += duration;
        }
    }

    public static void countdown(Element e, Floatp remainTime) {
        e.addListener(new Tooltip(t2 -> {
            t2.background(Tex.bar);
            t2.color.set(Color.black);
            t2.color.a = 0.35f;
            t2.add("Remain Time: 00:00 ").update(l -> {
                float remain = remainTime.get();
                l.setText("[gray]Remain Time: " + ((remain / Time.toSeconds > 15) ? "[]" : "[accent]") + Mathf.floor(remain / Time.toMinutes) + ":" + Mathf.floor((remain % Time.toMinutes) / Time.toSeconds));
            }).left().fillY().growX().row();
        }));
    }

    public static void showToast(Drawable icon, String text, Sound sound) {
        if (state.isMenu()) return;

        scheduleToast(() -> {
            sound.play();

            Table table = new Table(Tex.button);
            table.update(() -> {
                if (state.isMenu() || !ui.hudfrag.shown) {
                    table.remove();
                }
            });
            table.margin(12);
            table.image(icon).pad(3);
            table.add(text).wrap().width(280f).get().setAlignment(Align.center, Align.center);
            table.pack();

            //create container table which will align and move
            Table container = scene.table();
            container.top().add(table);
            container.setTranslation(0, table.getPrefHeight());
            container.actions(
                    Actions.translateBy(0, -table.getPrefHeight(), 1f, Interp.fade), Actions.delay(2.5f),
                    //nesting actions() calls is necessary so the right prefHeight() is used
                    Actions.run(() -> container.actions(Actions.translateBy(0, table.getPrefHeight(), 1f, Interp.fade), Actions.remove()))
            );
        });
    }

    public static void link(Table parent, Links.LinkEntry link) {
        parent.add(new LinkTable(link)).size(LinkTable.w + OFFSET * 2f, LinkTable.h).padTop(OFFSET / 2f).row();
    }

    public static class LinkTable extends Table {
        protected static float h = graphics.isPortrait() ? 90f : 80f;
        protected static float w = graphics.isPortrait() ? 330f : 600f;

        public static void sync() {
            h = graphics.isPortrait() ? 90f : 80f;
            w = graphics.isPortrait() ? 300f : 600f;
        }

        public LinkTable(Links.LinkEntry link) {
            background(Tex.underline);
            margin(0);
            table(img -> {
                img.image().height(h - OFFSET / 2).width(LEN).color(link.color);
                img.row();
                img.image().height(OFFSET / 2).width(LEN).color(link.color.cpy().mul(0.8f, 0.8f, 0.8f, 1f));
            }).expandY();

            table(i -> {
                i.background(Tex.buttonEdge3);
                i.image(link.icon);
            }).size(h - OFFSET / 2, h);

            table(inset -> {
                inset.add("[accent]" + link.title).growX().left();
                inset.row();
                inset.labelWrap(link.description).width(w - LEN).color(Color.lightGray).growX();
            }).padLeft(OFFSET / 1.5f);

            button(Icon.link, () -> {
                if (!app.openURI(link.link)) {
                    ui.showErrorMessage("@linkfail");
                    app.setClipboardText(link.link);
                }
            }).size(h);
        }
    }
}