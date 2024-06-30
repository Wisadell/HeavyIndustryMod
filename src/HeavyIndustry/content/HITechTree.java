package HeavyIndustry.content;

import arc.struct.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.game.Objectives.*;
import mindustry.type.*;

import static HeavyIndustry.content.HIBlocks.*;
import static HeavyIndustry.content.HIUnitTypes.*;
import static HeavyIndustry.content.HISectorPresets.*;
import static mindustry.content.Blocks.*;
import static mindustry.content.UnitTypes.*;
import static mindustry.content.SectorPresets.*;
import static mindustry.content.TechTree.*;

public class HITechTree {
    public static TechNode context = null;
    public static void load() {
        //items,liquids
        addToNode(Liquids.water, () -> {
            nodeProduce(Liquids.cyanogen, () -> {});
            nodeProduce(Liquids.ozone, () -> {});
            nodeProduce(HILiquids.methane, () -> {});
        });
        addToNode(Liquids.oil, () -> nodeProduce(HILiquids.nitratedOil, () -> {}));
        addToNode(Items.silicon, () -> nodeProduce(HIItems.nanocore, () -> nodeProduce(HILiquids.nanofluid, () -> {})));
        addToNode(Items.thorium, () -> nodeProduce(HIItems.uranium, () -> nodeProduce(HIItems.chromium, () -> {})));
        addToNode(Items.surgeAlloy, () -> nodeProduce(HIItems.heavyAlloy, () -> {}));
        addToNode(Items.phaseFabric, () -> nodeProduce(HIItems.highEnergyFabric, () -> {}));
        //wall
        addToNode(copperWall, () -> node(armoredWall, () -> node(armoredWallLarge)));
        addToNode(thoriumWall, () -> node(uraniumWall, () -> {
            node(uraniumWallLarge);
            node(chromiumWall, () -> {
                node(chromiumWallLarge);
                node(chromiumDoor, () -> node(chromiumDoorLarge));
            });
        }));
        addToNode(surgeWall, () -> node(heavyAlloyWall, () -> node(heavyAlloyWallLarge)));
        //drill
        addToNode(waterExtractor, () -> {
            node(largeWaterExtractor);
            node(slagExtractor);
        });
        addToNode(blastDrill, () -> {
            node(reinforcedDrill, Seq.with(new SectorComplete(planetaryTerminal)), () -> {});
            node(beamDrill);
        });
        //drill-erekir
        addToNode(cliffCrusher, () -> node(largeCliffCrusher, Seq.with(new OnSector(lake)), () -> {}));
        addToNode(largePlasmaBore, () -> node(heavyPlasmaBore));
        //distribution
        addToNode(junction, () -> node(invertedJunction));
        addToNode(plastaniumConveyor, () -> node(stackHelper));
        addToNode(phaseConveyor, () -> node(highEnergyItemNode));
        addToNode(titaniumConveyor, () -> node(chromiumEfficientConveyor, () -> {
            node(chromiumArmorConveyor, () -> node(chromiumConveyor));
            node(chromiumItemBridge);
            node(chromiumRouter);
            node(chromiumJunction);
            node(chromiumInvertedJunction);
        }));
        //distribution-erekir
        addToNode(ductRouter, () -> node(reinforcedSorter, () -> {
            node(reinforcedInvertedSorter);
            node(reinforcedOverflowGate, () -> node(reinforcedUnderflowGate));
        }));
        addToNode(armoredDuct, () -> {
            node(armoredDuctBridge);
            node(waveDuct, () -> node(waveDuctBridge));
        });
        addToNode(ductUnloader, () -> node(rapidDuctUnloader));
        //liquid
        addToNode(phaseConduit, () -> node(highEnergyLiquidNode));
        addToNode(platedConduit, () -> node(chromiumArmorConduit, () -> {
            node(chromiumLiquidBridge);
            node(chromiumArmorLiquidTank);
        }));
        //liquid-erekir
        addToNode(reinforcedLiquidRouter, () -> {
            node(liquidSorter);
            node(liquidValve);
        });
        addToNode(reinforcedConduit, () -> {
            node(smallReinforcedPump, Seq.with(new OnSector(basin)), () -> {});
            node(largeReinforcedPump);
        });
        //power
        addToNode(powerNodeLarge, () -> node(powerNodeGiant));
        addToNode(thoriumReactor, () -> node(uraniumReactor));
        addToNode(batteryLarge, () -> node(armoredCoatedBattery));
        //power-erekir
        addToNode(turbineCondenser, () -> node(liquidConsumeGenerator));
        //production
        addToNode(kiln, () -> node(largeKiln, Seq.with(new SectorComplete(windsweptIslands)), () -> {}));
        addToNode(pulverizer, () -> node(largePulverizer, () -> {
            node(uraniumSynthesizer, Seq.with(new SectorComplete(fallenStronghold)), () -> {});
            node(chromiumSynthesizer, Seq.with(new SectorComplete(fallenStronghold)), () -> {});
        }));
        addToNode(surgeSmelter, () -> node(heavyAlloySmelter));
        addToNode(cryofluidMixer, () -> {
            node(largeCryofluidMixer, Seq.with(new SectorComplete(impact0078)), () -> {});
            node(activator);
        });
        addToNode(pyratiteMixer, () -> node(largePyratiteMixer, Seq.with(new SectorComplete(facility32m)), () -> {}));
        addToNode(blastMixer, () -> node(largeBlastMixer, Seq.with(new SectorComplete(facility32m)), () -> {}));
        addToNode(cultivator, () -> node(largeCultivator, Seq.with(new SectorComplete(facility32m)), () -> {}));
        addToNode(plastaniumCompressor, () -> node(largePlastaniumCompressor, Seq.with(new SectorComplete(tarFields)), () -> {}));
        addToNode(coalCentrifuge, () -> node(largeCoalCentrifuge, Seq.with(new SectorComplete(tarFields)), () -> {}));
        addToNode(siliconCrucible, () -> node(blastSiliconSmelter));
        addToNode(siliconSmelter, () -> node(nanocoreConstructor, Seq.with(new SectorComplete(impact0078)), () -> node(nanocorePrinter)));
        addToNode(sporePress, () -> node(nitrificationReactor, () -> node(nitratedOilSedimentationTank)));
        addToNode(phaseWeaver, () -> node(energizer, Seq.with(new SectorComplete(fallenStronghold)), () -> {
            node(largeEnergizer, Seq.with(new SectorComplete(bombardmentWarzone)), () -> {});
            node(highEnergyFabricFusionInstrument);
        }));
        //production-erekir
        addToNode(siliconArcFurnace, () -> {
            node(chemicalSiliconSmelter);
            node(ventHeater);
        });
        addToNode(electricHeater, () -> {
            node(largeElectricHeater);
            node(liquidFuelHeater);
        });
        addToNode(oxidationChamber, () -> node(largeOxidationChamber));
        addToNode(surgeCrucible, () -> node(largeSurgeCrucible));
        addToNode(carbideCrucible, () -> node(largeCarbideCrucible));
        //defense
        addToNode(mendProjector, () -> node(mendDome));
        //defense-erekir
        addToNode(radar, () -> node(largeRadar));
        //storage
        addToNode(coreShard, () -> node(coreBeStationed));
        addToNode(router, () -> node(bin));
        addToNode(vault, () -> node(cargo));
        addToNode(unloader, () -> node(rapidUnloader));
        //unit
        addToNode(tetrativeReconstructor, () -> node(titanReconstructor, () -> node(experimentalUnitFactory, Seq.with(new Research(suzerain), new Research(supernova), new Research(cancer), new Research(sunlit), new Research(windstorm), new Research(harpoon), new Research(killerWhale), new SectorComplete(bombardmentWarzone)), () -> {})));
        //unit-erekir
        addToNode(unitRepairTower, () -> node(largeUnitRepairTower, Seq.with(new OnSector(siege)), () -> {}));
        addToNode(basicAssemblerModule, () -> node(seniorAssemblerModule));
        //turret
        addToNode(segment, () -> node(dissipation));
        addToNode(duo, () -> {
            node(rocketLauncher, Seq.with(new SectorComplete(ruinousShores)), () -> node(multipleRocketLauncher, Seq.with(new SectorComplete(stainedMountains)), () -> {
                node(largeRocketLauncher, Seq.with(new SectorComplete(extractionOutpost)), () -> node(fireworks, Seq.with(new SectorComplete(bombardmentWarzone)), () -> {}));
                node(rocketSilo, Seq.with(new SectorComplete(tarFields)), () -> node(caelum, Seq.with(new SectorComplete(impact0078)), () -> {}));
            }));
            node(cloudbreaker);
        });
        addToNode(tsunami, () -> node(furnace, Seq.with(new SectorComplete(nuclearComplex)), () -> {}));
        addToNode(meltdown, () -> {
            node(frost, Seq.with(new SectorComplete(fallenStronghold)), () -> {});
            node(thermoelectricIon, Seq.with(new SectorComplete(bombardmentWarzone)), () -> {});
        });
        addToNode(foreshadow, () -> node(fiammetta));
        //turret-erekir
        addToNode(disperse, () -> node(tracer, Seq.with(new OnSector(stronghold)), () -> {}));
        addToNode(lustre, () -> node(shadow));
        //T6
        addToNode(reign, () -> node(suzerain));
        addToNode(corvus, () -> node(supernova));
        addToNode(toxopid, () -> node(cancer));
        addToNode(eclipse, () -> node(sunlit));
        addToNode(oct, () -> node(windstorm));
        addToNode(omura, () -> node(harpoon));
        addToNode(navanax, () -> node(killerWhale));
        //erekir-T6
        addToNode(conquer, () -> node(dominate));
        addToNode(collaris, () -> node(oracle));
        addToNode(disrupt, () -> node(havoc));
        //sector presets
        addToNode(SectorPresets.craters, () -> {
            node(iceboundTributary, Seq.with(new SectorComplete(SectorPresets.craters), new Research(Items.metaglass)), () -> {});
            node(facility32m, Seq.with(new SectorComplete(SectorPresets.craters)), () -> {});
        });
        addToNode(ruinousShores, () -> {
            node(desertWastes, Seq.with(new SectorComplete(ruinousShores), new Research(airFactory)), () -> {});
            node(whiteoutPlains, Seq.with(new SectorComplete(ruinousShores)), () -> {});
        });
        addToNode(windsweptIslands, () -> node(snowyLands, Seq.with(new SectorComplete(windsweptIslands)), () -> {}));
        addToNode(coastline, () -> node(sunkenPier, Seq.with(new SectorComplete(coastline)), () -> {}));
        addToNode(desolateRift, () -> node(fallenStronghold, Seq.with(new SectorComplete(desolateRift)), () -> {}));
        addToNode(planetaryTerminal, () -> node(bombardmentWarzone, Seq.with(new SectorComplete(planetaryTerminal)), () -> {}));
    }
    public static void addToNode(UnlockableContent p, Runnable c) {
        context = TechTree.all.find(t -> t.content == p);
        c.run();
    }

    public static void node(UnlockableContent content, Runnable children){
        node(content, content.researchRequirements(), children);
    }

    public static void node(UnlockableContent content, ItemStack[] requirements, Runnable children){
        node(content, requirements, null, children);
    }

    public static void node(UnlockableContent content, ItemStack[] requirements, Seq<Objective> objectives, Runnable children){
        TechNode node = new TechNode(context, content, requirements);
        if(objectives != null){
            node.objectives.addAll(objectives);
        }

        TechNode prev = context;
        context = node;
        children.run();
        context = prev;
    }

    public static void node(UnlockableContent content, Seq<Objective> objectives, Runnable children){
        node(content, content.researchRequirements(), objectives, children);
    }

    public static void node(UnlockableContent block){
        node(block, () -> {});
    }

    public static void nodeProduce(UnlockableContent content, Seq<Objective> objectives, Runnable children){
        node(content, content.researchRequirements(), objectives.add(new Produce(content)), children);
    }

    public static void nodeProduce(UnlockableContent content, Runnable children){
        nodeProduce(content, new Seq<>(), children);
    }
}
