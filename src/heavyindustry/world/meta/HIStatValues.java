package heavyindustry.world.meta;

import arc.graphics.g2d.*;
import arc.math.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.meta.*;

import static arc.Core.*;
import static mindustry.Vars.*;

public class HIStatValues {

    public static <T extends UnlockableContent> StatValue ammo(ObjectMap<T, BulletType[]> map){
        return ammo(map, 0, false);
    }

    public static <T extends UnlockableContent> StatValue ammo(ObjectMap<T, BulletType[]> map, boolean showUnit){
        return ammo(map, 0, showUnit);
    }

    public static <T extends UnlockableContent> StatValue ammo(ObjectMap<T, BulletType[]> map, int indent, boolean showUnit){
        return table -> {
            table.row();

            Seq<T> orderedKeys = map.keys().toSeq();
            orderedKeys.sort();

            for(T t : orderedKeys) {
                boolean compact = t instanceof UnitType && !showUnit || indent > 0;
                if (!compact && !(t instanceof Turret)) {
                    table.image(icon(t)).size(3 * 8).padRight(4).right().top();
                    table.add(t.localizedName).padRight(10).left().top();
                }
                table.row();
                for(BulletType type : map.get(t)){
                    if (type.spawnUnit != null && type.spawnUnit.weapons.size > 0) {
                        ammo(ObjectMap.of(t, type.spawnUnit.weapons.first().bullet), indent, false).display(table);
                        return;
                    }

                    table.table(bt -> {
                        bt.left().defaults().padRight(3).left();

                        if (type.damage > 0 && (type.collides || type.splashDamage <= 0)) {
                            if (type.continuousDamage() > 0) {
                                bt.add(bundle.format("bullet.damage", type.continuousDamage()) + StatUnit.perSecond.localized());
                            } else {
                                bt.add(bundle.format("bullet.damage", type.damage));
                            }
                        }

                        if (type.buildingDamageMultiplier != 1) {
                            sep(bt, bundle.format("bullet.buildingdamage", (int) (type.buildingDamageMultiplier * 100)));
                        }

                        if (type.rangeChange != 0 && !compact) {
                            sep(bt, bundle.format("bullet.range", (type.rangeChange > 0 ? "+" : "-") + Strings.autoFixed(type.rangeChange / tilesize, 1)));
                        }

                        if (type.splashDamage > 0) {
                            sep(bt, bundle.format("bullet.splashdamage", (int) type.splashDamage, Strings.fixed(type.splashDamageRadius / tilesize, 1)));
                        }

                        if (!compact && !Mathf.equal(type.ammoMultiplier, 1f) && type.displayAmmoMultiplier && (!(t instanceof Turret) || ((Turret)t).displayAmmoMultiplier)) {
                            sep(bt, bundle.format("bullet.multiplier", (int) type.ammoMultiplier));
                        }

                        if (!compact && !Mathf.equal(type.reloadMultiplier, 1f)) {
                            sep(bt, bundle.format("bullet.reload", Strings.autoFixed(type.reloadMultiplier, 2)));
                        }

                        if (type.knockback > 0) {
                            sep(bt, bundle.format("bullet.knockback", Strings.autoFixed(type.knockback, 2)));
                        }

                        if (type.healPercent > 0f) {
                            sep(bt, bundle.format("bullet.healpercent", Strings.autoFixed(type.healPercent, 2)));
                        }

                        if (type.healAmount > 0f) {
                            sep(bt,bundle.format("bullet.healamount", Strings.autoFixed(type.healAmount, 2)));
                        }

                        if (type.pierce || type.pierceCap != -1) {
                            sep(bt, type.pierceCap == -1 ? "@bullet.infinitepierce" : bundle.format("bullet.pierce", type.pierceCap));
                        }

                        if (type.incendAmount > 0) {
                            sep(bt, "@bullet.incendiary");
                        }

                        if (type.homingPower > 0.01f) {
                            sep(bt, "@bullet.homing");
                        }

                        if (type.lightning > 0) {
                            sep(bt, bundle.format("bullet.lightning", type.lightning, type.lightningDamage < 0 ? type.damage : type.lightningDamage));
                        }

                        if (type.pierceArmor) {
                            sep(bt, "@bullet.armorpierce");
                        }

                        if (type.status != StatusEffects.none) {
                            sep(bt, (type.status.minfo.mod == null ? type.status.emoji() : "") + "[stat]" + type.status.localizedName + "[lightgray] ~ [stat]" + ((int) (type.statusDuration / 60f)) + "[lightgray] " + bundle.get("unit.seconds"));
                        }

                        if (type.fragBullet != null) {
                            sep(bt, bundle.format("bullet.frags", type.fragBullets));
                            bt.row();

                            StatValues.ammo(ObjectMap.of(t, type.fragBullet), indent + 1, false).display(bt);
                        }
                    }).padTop(compact ? 0 : -9).padLeft(indent * 8).left().get().background(compact ? null : Tex.underline);

                    table.row();
                }
            }
        };
    }

    public static StatValue teslaZapping(float damage, float maxTargets, StatusEffect status){
        return table -> {
            table.row();
            table.table(t -> {
                t.left().defaults().padRight(3).left();

                t.add(bundle.format("bullet.lightning", maxTargets, damage));
                t.row();

                if(status != StatusEffects.none){
                    t.add((status.minfo.mod == null ? status.emoji() : "") + "[stat]" + status.localizedName);
                }
            }).padTop(-9).left().get().background(Tex.underline);
        };
    }

    private static void sep(Table table, String text){
        table.row();
        table.add(text);
    }

    private static TextureRegion icon(UnlockableContent t){
        return t.uiIcon;
    }
}
