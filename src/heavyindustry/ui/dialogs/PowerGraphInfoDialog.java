package heavyindustry.ui.dialogs;

import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.ui.*;
import mindustry.ui.dialogs.*;
import mindustry.world.blocks.power.*;
import heavyindustry.ui.*;
import heavyindustry.ui.elements.*;
import heavyindustry.ui.elements.PowerInfoGroup.*;

import static arc.Core.*;

public class PowerGraphInfoDialog extends BaseDialog {
    private final float updateInterval = 60; //Update every second

    private final IntSet opened = new IntSet();
    private final IntMap<Seq<Building>> producers = new IntMap<>();
    private final IntMap<Seq<Building>> consumers = new IntMap<>();
    private final IntMap<Seq<Building>> batteries = new IntMap<>();
    private final InfoToggled collToggled = (int id, boolean open) -> {
        if(open){
            opened.add(id);
        }else{
            opened.remove(id);
        }
    };
    private PowerGraph graph;
    private float updateTimer;

    private Table infoTable;
    private PowerInfoType currType = PowerInfoType.producer;

    public PowerGraphInfoDialog(){
        super("@hi-power-info-title");

        cont.table(modes -> {
            modes.button("@hi-power-info-producer", Styles.togglet, () -> {
                currType = PowerInfoType.producer;
                refresh();
            }).growX().update(b -> {
                b.setText(selectionTitle(PowerInfoType.producer));
                b.setChecked(currType == PowerInfoType.producer);
            });
            modes.button("@hi-power-info-consumer", Styles.togglet, () -> {
                currType = PowerInfoType.consumer;
                refresh();
            }).growX().update(b -> {
                b.setText(selectionTitle(PowerInfoType.consumer));
                b.setChecked(currType == PowerInfoType.consumer);
            });
            modes.button("@hi-power-info-battery", Styles.togglet, () -> {
                currType = PowerInfoType.battery;
                refresh();
            }).growX().update(b -> {
                b.setText(selectionTitle(PowerInfoType.battery));
                b.setChecked(currType == PowerInfoType.battery);
            });
        }).growX().top();

        cont.row();

        cont.pane(p -> infoTable = p.table().grow().top().get()).growX().expandY().top();

        hidden(() -> {
            graph = null;
            opened.clear();
            clearData();
        });

        update(() -> {
            updateTimer += Time.delta;
            if(updateTimer >= updateInterval){
                updateTimer %= updateInterval;
                updateListings();
            }
        });

        onResize(this::refresh);

        addCloseButton();
    }

    public void show(PowerGraph graph){
        this.graph = graph;
        updateListings();
        show();
    }

    private String selectionTitle(PowerInfoType type){
        if(graph == null) return "";

        return switch(type){
            case producer -> bundle.get("hi-power-info-producer") + " - " + bundle.format("hi-power-info-persec", "[#98ffa9]+" + HIUI.formatAmount(graph.getLastScaledPowerIn() * 60));
            case consumer -> bundle.get("hi-power-info-consumer") + " - " + bundle.format("hi-power-info-persec", "[#e55454]-" + HIUI.formatAmount(graph.getLastScaledPowerOut() * 60));
            case battery -> bundle.get("hi-power-info-battery") + " - [#fbad67]" + HIUI.formatAmount(graph.getLastPowerStored()) + "[gray]/[]" + HIUI.formatAmount(graph.getLastCapacity());
        };
    }

    private void refresh(){
        infoTable.clear();

        switch(currType){
            case producer -> {
                IntSeq prodKeys = producers.keys().toArray();
                prodKeys.sort();
                prodKeys.each(id -> {
                    infoTable.add(new PowerInfoGroup(producers.get(id), PowerInfoType.producer, opened.contains(id), collToggled)).growX().top().padBottom(6f);
                    infoTable.row();
                });
            }
            case consumer -> {
                IntSeq consKeys = consumers.keys().toArray();
                consKeys.sort();
                consKeys.each(id -> {
                    infoTable.add(new PowerInfoGroup(consumers.get(id), PowerInfoType.consumer, opened.contains(id), collToggled)).growX().top().padBottom(6f);
                    infoTable.row();
                });
            }
            case battery -> {
                IntSeq battKeys = batteries.keys().toArray();
                battKeys.sort();
                battKeys.each(id -> {
                    infoTable.add(new PowerInfoGroup(batteries.get(id), PowerInfoType.battery, opened.contains(id), collToggled)).growX().top().padBottom(6f);
                    infoTable.row();
                });
            }
        }
    }

    private void updateListings(){
        if(graph == null) return;

        clearData();

        graph.producers.each(p -> {
            producers.get(p.block.id, Seq::new).add(p);
        });
        graph.consumers.each(p -> {
            consumers.get(p.block.id, Seq::new).add(p);
        });
        graph.batteries.each(p -> {
            batteries.get(p.block.id, Seq::new).add(p);
        });

        refresh();
    }

    private void clearData(){
        producers.clear();
        consumers.clear();
        batteries.clear();
    }

    public enum PowerInfoType{
        producer,
        consumer,
        battery;
    }
}
