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
    public static void load(){
        //items,liquids
        addToNode(Liquids.oil, () -> nodeProduce(HILiquids.nitratedOil, () -> {}));
        addToNode(Liquids.ozone, () -> nodeProduce(HILiquids.methane, () -> {}));
        addToNode(Items.sand, () -> nodeProduce(HIItems.rareEarth, () -> {}));
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
        addToNode(surgeWall, () -> node(heavyAlloyWall, () -> {
            node(heavyAlloyWallLarge);
            node(nanoCompositeWall, () -> node(nanoCompositeWallLarge));
        }));
        //drill
        addToNode(waterExtractor, () -> {
            node(largeWaterExtractor);
            node(slagExtractor);
        });
        addToNode(blastDrill, () -> {
            node(reinforcedDrill, Seq.with(new SectorComplete(planetaryTerminal)), () -> {});
            node(beamDrill);
        });
        addToNode(oilExtractor, () -> node(reinforcedOilExtractor));
        //drill-erekir
        addToNode(cliffCrusher, () -> node(largeCliffCrusher, ItemStack.with(Items.graphite, 1600, Items.silicon, 600, Items.beryllium, 1200, Items.tungsten, 500), Seq.with(new OnSector(lake)), () -> {}));
        addToNode(largePlasmaBore, () -> node(heavyPlasmaBore, ItemStack.with(Items.silicon, 6000, Items.oxide, 3000, Items.beryllium, 7000, Items.tungsten, 5000, Items.carbide, 2000), () -> {}));
        //distribution
        addToNode(junction, () -> node(invertedJunction));
        addToNode(plastaniumConveyor, () -> node(stackHelper));
        addToNode(phaseConveyor, () -> node(highEnergyItemNode));
        addToNode(titaniumConveyor, () -> node(chromiumEfficientConveyor, () -> {
            node(chromiumArmorConveyor, () -> node(chromiumStackConveyor, () -> node(chromiumStackRouter)));
            node(chromiumItemBridge);
            node(chromiumRouter);
            node(chromiumJunction);
            node(chromiumInvertedJunction);
        }));
        //distribution-erekir
        addToNode(armoredDuct, () -> {
            node(armoredDuctBridge);
            node(waveDuct, () -> {
                node(waveDuctRouter, () -> node(overflowWaveDuct, () -> node(underflowWaveDuct)));
                node(waveDuctBridge);
            });
        });
        addToNode(ductUnloader, () -> node(rapidDuctUnloader));
        //liquid
        addToNode(impulsePump, () -> node(turboPump));
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
        addToNode(powerNode, () -> node(windTurbine));
        addToNode(powerNodeLarge, () -> node(powerNodeGiant, () -> node(powerNodeHighEnergy)));
        addToNode(thoriumReactor, () -> node(uraniumReactor));
        addToNode(batteryLarge, () -> node(armoredCoatedBattery));
        //power-erekir
        addToNode(turbineCondenser, () -> node(liquidConsumeGenerator, ItemStack.with(Items.beryllium, 2200, Items.graphite, 2400, Items.silicon, 2300, Items.tungsten, 1600, Items.oxide, 60), () -> {}));
        //production
        addToNode(kiln, () -> node(largeKiln, Seq.with(new SectorComplete(windsweptIslands)), () -> {}));
        addToNode(pulverizer, () -> node(largePulverizer, () -> {
            node(uraniumSynthesizer, Seq.with(new OnSector(chernobog)), () -> {});
            node(chromiumSynthesizer, Seq.with(new OnSector(chernobog)), () -> {});
        }));
        addToNode(melter, () -> node(largeMelter));
        addToNode(surgeSmelter, () -> node(heavyAlloySmelter));
        addToNode(disassembler, () -> node(metalAnalyzer, Seq.with(new OnSector(chernobog)), () -> {}));
        addToNode(cryofluidMixer, () -> {
            node(largeCryofluidMixer, Seq.with(new SectorComplete(impact0078)), () -> {});
            node(activator);
        });
        addToNode(pyratiteMixer, () -> node(largePyratiteMixer, Seq.with(new SectorComplete(facility32m)), () -> {}));
        addToNode(blastMixer, () -> node(largeBlastMixer, Seq.with(new SectorComplete(facility32m)), () -> {}));
        addToNode(cultivator, () -> node(largeCultivator, Seq.with(new SectorComplete(facility32m)), () -> {}));
        addToNode(plastaniumCompressor, () -> node(largePlastaniumCompressor, Seq.with(new SectorComplete(tarFields)), () -> {}));
        addToNode(surgeSmelter, ()-> node(largeSurgeSmelter));
        addToNode(coalCentrifuge, () -> node(largeCoalCentrifuge, Seq.with(new SectorComplete(tarFields)), () -> {}));
        addToNode(siliconCrucible, () -> node(blastSiliconSmelter));
        addToNode(siliconSmelter, () -> node(nanocoreConstructor, Seq.with(new SectorComplete(impact0078)), () -> node(nanocorePrinter)));
        addToNode(sporePress, () -> node(nitrificationReactor, () -> node(nitratedOilSedimentationTank)));
        addToNode(phaseWeaver, () -> {
            node(energizer, Seq.with(new SectorComplete(impact0078)), () -> {
                node(largeEnergizer);
                node(highEnergyFabricFusionInstrument);
            });
            node(highEnergyPhaseWeaver);
        });
        //production-erekir
        addToNode(siliconArcFurnace, () -> {
            node(chemicalSiliconSmelter, ItemStack.with(Items.graphite, 2800, Items.silicon, 1000, Items.tungsten, 2400, Items.oxide, 50), () -> {});
            node(ventHeater);
        });
        addToNode(electricHeater, () -> {
            node(largeElectricHeater, ItemStack.with(Items.tungsten, 3000, Items.oxide, 2400, Items.carbide, 800), () -> {});
            node(liquidFuelHeater);
            node(heatReactor);
        });
        addToNode(oxidationChamber, () -> node(largeOxidationChamber, ItemStack.with(Items.tungsten, 3600, Items.graphite, 4400, Items.silicon, 4400, Items.beryllium, 6400, Items.oxide, 600, Items.carbide, 1400), () -> {}));
        addToNode(surgeCrucible, () -> node(largeSurgeCrucible, ItemStack.with(Items.graphite, 4400, Items.silicon, 4000, Items.tungsten, 4800, Items.oxide, 960, Items.surgeAlloy, 1600), () -> {}));
        addToNode(carbideCrucible, () -> node(largeCarbideCrucible, ItemStack.with(Items.thorium, 6000, Items.tungsten, 8000, Items.oxide, 1000, Items.carbide, 1200), () -> {}));
        //defense
        addToNode(illuminator, () -> node(lighthouse));
        addToNode(mendProjector, () -> node(mendDome));
        addToNode(overdriveDome, () -> node(assignOverdrive));
        //defense-erekir
        addToNode(radar, () -> node(largeRadar, ItemStack.with(Items.graphite, 3600, Items.silicon, 3200, Items.beryllium, 600, Items.tungsten, 200, Items.oxide, 10), () -> {}));
        //storage
        addToNode(coreShard, () -> node(coreBeStationed, ItemStack.with(Items.copper, 500, Items.lead, 350, Items.silicon, 200), () -> {}));
        addToNode(router, () -> node(bin, ItemStack.with(Items.copper, 550, Items.lead, 350), () -> {}));
        addToNode(vault, () -> node(cargo));
        addToNode(unloader, () -> node(rapidUnloader, () -> node(rapidDirectionalUnloader)));
        //unit
        addToNode(tetrativeReconstructor, () -> node(titanReconstructor, () -> node(experimentalUnitFactory, Seq.with(new SectorComplete(bombardmentWarzone)), () -> {})));
        //unit-erekir
        addToNode(unitRepairTower, () -> node(largeUnitRepairTower, ItemStack.with(Items.graphite, 2400, Items.silicon, 3000, Items.tungsten, 2600, Items.oxide, 1200, Items.carbide, 600), Seq.with(new OnSector(siege)), () -> {}));
        addToNode(basicAssemblerModule, () -> node(seniorAssemblerModule));
        //logic
        addToNode(memoryCell, () -> node(buffrerdMemoryCell, () -> node(buffrerdMemoryBank)));
        //turret
        addToNode(segment, () -> node(dissipation));
        addToNode(duo, () -> {
            node(rocketLauncher, Seq.with(new SectorComplete(ruinousShores)), () -> node(multipleRocketLauncher, Seq.with(new SectorComplete(stainedMountains)), () -> {
                node(largeRocketLauncher, Seq.with(new SectorComplete(extractionOutpost)), () -> node(spark, () -> node(fireworks, Seq.with(new SectorComplete(bombardmentWarzone)), () -> {})));
                node(rocketSilo, Seq.with(new SectorComplete(tarFields)), () -> node(caelum, Seq.with(new SectorComplete(impact0078)), () -> {}));
            }));
            node(cloudbreaker);
        });
        addToNode(salvo, () -> {
            node(rend, () -> node(fissure));
            node(minigun);
        });
        addToNode(cyclone, () -> node(splitPlum));
        addToNode(meltdown, () -> {
            node(frost, Seq.with(new SectorComplete(fallenStronghold)), () -> {});
            node(thermoelectricIon, Seq.with(new SectorComplete(bombardmentWarzone)), () -> {});
            node(concentration);
        });
        addToNode(foreshadow, () -> {
            node(fiammetta);
            node(wisadel);
        });
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
        addToNode(desolateRift, () -> {
            node(fallenStronghold, Seq.with(new SectorComplete(desolateRift)), () -> {});
            node(coastalCliffs, Seq.with(new SectorComplete(desolateRift), new Research(overdriveProjector), new Research(swarmer), new Research(foreshadow), new Research(navanax)), () -> {});
        });
        addToNode(planetaryTerminal, () -> {
            node(bombardmentWarzone, Seq.with(new SectorComplete(planetaryTerminal)), () -> {});
            node(chernobog, Seq.with(new SectorComplete(planetaryTerminal)), () -> {});
        });
    }
    public static void addToNode(UnlockableContent p, Runnable c){
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
