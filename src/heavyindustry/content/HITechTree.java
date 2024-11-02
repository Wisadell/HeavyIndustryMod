package heavyindustry.content;

import arc.struct.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.game.Objectives.*;
import mindustry.type.*;

import static heavyindustry.content.HIBlocks.*;
import static heavyindustry.content.HIUnitTypes.*;
import static mindustry.content.Blocks.*;
import static mindustry.content.UnitTypes.*;
import static mindustry.content.SectorPresets.*;
import static mindustry.content.TechTree.*;

/**
 * Sets up content {@link TechNode tech tree nodes}. Loaded after every other content is instantiated.
 * @author Wisadell
 */
public final class HITechTree {
    public static TechNode context = null;
    public static void load(){
        //items,liquids
        addToNode(Liquids.water, () -> nodeProduce(HILiquids.brine, () -> {}));
        addToNode(Liquids.oil, () -> nodeProduce(HILiquids.nitratedOil, () -> {}));
        addToNode(Liquids.ozone, () -> nodeProduce(HILiquids.methane, () -> {}));
        addToNode(Items.sand, () -> {
            nodeProduce(HIItems.rareEarth, () -> {});
            nodeProduce(HIItems.salt, () -> {});
        });
        addToNode(Items.silicon, () -> nodeProduce(HIItems.nanocore, () -> nodeProduce(HILiquids.nanofluid, () -> {})));
        addToNode(Items.thorium, () -> nodeProduce(HIItems.uranium, () -> nodeProduce(HIItems.chromium, () -> {})));
        addToNode(Items.surgeAlloy, () -> nodeProduce(HIItems.heavyAlloy, () -> {}));
        //items,liquids-erekir
        addToNode(Items.tungsten, () -> {
            nodeProduce(HIItems.uranium, () -> {});
            nodeProduce(HIItems.chromium, () -> {});
        });
        addToNode(Items.oxide, () -> nodeProduce(HIItems.nanocoreErekir, () -> {}));
        //wall
        addToNode(copperWall, () -> node(armoredWall, () -> node(armoredWallLarge, () -> node(armoredWallHuge, () -> node(armoredWallGigantic, () -> {})))));
        addToNode(copperWallLarge, () -> node(copperWallHuge, () -> node(copperWallGigantic, () -> {})));
        addToNode(titaniumWallLarge, () -> node(titaniumWallHuge, () -> node(titaniumWallGigantic, () -> {})));
        addToNode(thoriumWall, () -> node(uraniumWall, () -> {
            node(uraniumWallLarge, () -> {});
            node(chromiumWall, () -> {
                node(chromiumWallLarge, () -> {});
                node(chromiumDoor, () -> node(chromiumDoorLarge, () -> {}));
            });
        }));
        addToNode(surgeWall, () -> node(heavyAlloyWall, () -> {
            node(heavyAlloyWallLarge, () -> {});
            node(nanoCompositeWall, () -> node(nanoCompositeWallLarge, () -> {}));
        }));
        //wall-erekir
        addToNode(berylliumWallLarge, () -> node(berylliumWallHuge, () -> node(berylliumWallGigantic, () -> {})));
        addToNode(tungstenWallLarge, () -> {
            node(tungstenWallHuge, () -> node(tungstenWallGigantic, () -> {}));
            node(aparajito, () -> node(aparajitoLarge, () -> {}));
        });
        addToNode(blastDoor, () -> node(blastDoorLarge, () -> node(blastDoorHuge, () -> {})));
        addToNode(reinforcedSurgeWallLarge, () -> node(reinforcedSurgeWallHuge, () -> node(reinforcedSurgeWallGigantic, () -> {})));
        addToNode(carbideWallLarge, () -> node(carbideWallHuge, () -> node(carbideWallGigantic, () -> {})));
        addToNode(shieldedWall, () -> node(shieldedWallLarge, () -> node(shieldedWallHuge, () -> {})));
        //drill
        addToNode(pneumaticDrill, () -> {
            node(titaniumDrill, () -> {});
            node(sporeFarm, () -> {});
        });
        addToNode(waterExtractor, () -> {
            node(largeWaterExtractor, () -> {});
            node(slagExtractor, () -> {});
        });
        addToNode(blastDrill, () -> node(cuttingDrill, Seq.with(new SectorComplete(impact0078)), () -> {
            node(beamDrill, () -> {
                node(speedModule, () -> {});
                node(refineModule, () -> {});
                node(deliveryModule, () -> {});
            });
            node(implosionDrill, () -> {});
        }));
        addToNode(oilExtractor, () -> node(oilRig, () -> {}));
        //drill-erekir
        addToNode(cliffCrusher, () -> node(largeCliffCrusher, ItemStack.with(Items.graphite, 1600, Items.silicon, 600, Items.beryllium, 1200, Items.tungsten, 500), Seq.with(new OnSector(lake)), () -> {}));
        addToNode(impactDrill, () -> node(minerPoint, Seq.with(new Research(electrolyzer)), () -> node(minerCenter, Seq.with(new Research(atmosphericConcentrator)), () -> {})));
        addToNode(largePlasmaBore, () -> node(heavyPlasmaBore, ItemStack.with(Items.silicon, 6000, Items.oxide, 3000, Items.beryllium, 7000, Items.tungsten, 5000, Items.carbide, 2000), () -> {}));
        //distribution
        addToNode(sorter, () -> node(multiSorter, () -> {}));
        addToNode(junction, () -> {
            node(invertedJunction, () -> {});
            node(itemLiquidJunction, () -> {});
        });
        addToNode(plastaniumConveyor, () -> {
            node(plastaniumRouter, () -> {});
            node(plastaniumBridge, () -> {});
            node(stackHelper, () -> {});
        });
        addToNode(phaseConveyor, () -> node(phaseItemNode, () -> {}));
        addToNode(titaniumConveyor, () -> node(chromiumEfficientConveyor, () -> {
            node(chromiumArmorConveyor, () -> node(chromiumStackConveyor, () -> {
                node(chromiumStackRouter, () -> {});
                node(chromiumStackBridge, () -> {});
            }));
            node(chromiumTubeConveyor, () -> node(chromiumTubeDistributor, () -> {}));
            node(chromiumItemBridge, () -> {});
            node(chromiumRouter, () -> {});
            node(chromiumJunction, () -> node(chromiumInvertedJunction, () -> {}));
        }));
        //distribution-erekir
        addToNode(duct, () -> {
            node(ductJunction, () -> {});
            node(ductMultiSorter, () -> {});
        });
        addToNode(armoredDuct, () -> node(armoredDuctBridge, () -> {}));
        addToNode(ductUnloader, () -> node(rapidDuctUnloader, () -> {}));
        //liquid
        addToNode(liquidRouter, () -> {
            node(liquidOverflowValve, () -> node(liquidUnderflowValve, () -> {}));
            node(liquidSorter, () -> {});
            node(liquidValve, () -> {});
        });
        addToNode(liquidContainer, () -> node(liquidUnloader, () -> {}));
        addToNode(impulsePump, () -> node(turboPump, () -> {}));
        addToNode(phaseConduit, () -> node(phaseLiquidNode, () -> {}));
        addToNode(platedConduit, () -> node(chromiumArmorConduit, () -> {
            node(chromiumLiquidBridge, () -> {});
            node(chromiumArmorLiquidContainer, () -> node(chromiumArmorLiquidTank, () -> {}));
        }));
        //liquid-erekir
        addToNode(reinforcedLiquidContainer, () -> node(reinforcedLiquidUnloader, () -> {}));
        addToNode(reinforcedLiquidRouter, () -> {
            node(reinforcedLiquidOverflowValve, () -> node(reinforcedLiquidUnderflowValve, () -> {}));
            node(reinforcedLiquidSorter, () -> {});
            node(reinforcedLiquidValve, () -> {});
        });
        removeNode(reinforcedPump);
        addToNode(reinforcedConduit, () -> node(smallReinforcedPump, Seq.with(new OnSector(basin)), () -> node(reinforcedPump, () -> node(largeReinforcedPump, () -> {}))));
        //power
        addToNode(powerNode, () -> node(smartPowerNode, () -> node(powerAnalyzer, () -> {})));
        addToNode(powerNodeLarge, () -> node(powerNodeHuge, () -> node(powerNodePhase, () -> {})));
        addToNode(thoriumReactor, () -> node(uraniumReactor, () -> {}));
        addToNode(impactReactor, () -> node(hypermagneticReactor, () -> {}));
        addToNode(batteryLarge, () -> {
            node(hugeBattery, () -> {});
            node(armoredCoatedBattery, () -> {});
        });
        //power-erekir
        addToNode(beamNode, () -> {
            node(smartBeamNode, () -> node(reinforcedPowerAnalyzer, () -> {}));
            node(beamDiode, () -> {});
            node(beamInsulator, () -> {});
        });
        addToNode(turbineCondenser, () -> node(liquidConsumeGenerator, ItemStack.with(Items.beryllium, 2200, Items.graphite, 2400, Items.silicon, 2300, Items.tungsten, 1600, Items.oxide, 60), () -> {}));
        //production
        addToNode(kiln, () -> node(largeKiln, () -> {}));
        addToNode(pulverizer, () -> node(largePulverizer, () -> {
            node(uraniumSynthesizer, Seq.with(new OnSector(desolateRift)), () -> {});
            node(chromiumSynthesizer, Seq.with(new OnSector(desolateRift)), () -> {});
        }));
        addToNode(melter, () -> {
            node(largeMelter, () -> {});
            node(clarifier, () -> {});
        });
        addToNode(surgeSmelter, () -> node(heavyAlloySmelter, () -> {}));
        addToNode(disassembler, () -> node(metalAnalyzer, Seq.with(new OnSector(desolateRift)), () -> {}));
        addToNode(cryofluidMixer, () -> {
            node(largeCryofluidMixer, Seq.with(new SectorComplete(impact0078)), () -> {});
            node(nanocoreActivator, () -> {});
        });
        addToNode(pyratiteMixer, () -> node(largePyratiteMixer, Seq.with(new SectorComplete(facility32m)), () -> {}));
        addToNode(blastMixer, () -> node(largeBlastMixer, () -> {}));
        addToNode(cultivator, () -> node(largeCultivator, Seq.with(new SectorComplete(taintedWoods)), () -> {}));
        addToNode(plastaniumCompressor, () -> node(largePlastaniumCompressor, Seq.with(new SectorComplete(facility32m)), () -> {}));
        addToNode(surgeSmelter, ()-> node(largeSurgeSmelter, () -> {}));
        addToNode(siliconCrucible, () -> node(blastSiliconSmelter, () -> {}));
        addToNode(siliconSmelter, () -> node(nanocoreConstructor, Seq.with(new SectorComplete(impact0078)), () -> node(nanocorePrinter, () -> {})));
        addToNode(sporePress, () -> node(nitrificationReactor, () -> node(nitratedOilSedimentationTank, () -> {})));
        addToNode(phaseWeaver, () -> node(largePhaseWeaver, () -> node(phaseFusionInstrument, () -> {})));
        //production-erekir
        addToNode(siliconArcFurnace, () -> {
            node(chemicalSiliconSmelter, ItemStack.with(Items.graphite, 2800, Items.silicon, 1000, Items.tungsten, 2400, Items.oxide, 50), () -> {});
            node(ventHeater, () -> {});
            node(nanocoreConstructorErekir, Seq.with(new OnSector(crossroads)), () -> node(nanocorePrinterErekir, Seq.with(new OnSector(origin)), () -> {}));
        });
        addToNode(electricHeater, () -> {
            node(largeElectricHeater, ItemStack.with(Items.tungsten, 3000, Items.oxide, 2400, Items.carbide, 800), () -> {});
            node(liquidFuelHeater, () -> {});
            node(heatReactor, () -> {});
            node(uraniumFuser, Seq.with(new OnSector(origin)), () ->  node(chromiumFuser, () -> {}));
        });
        addToNode(oxidationChamber, () -> node(largeOxidationChamber, ItemStack.with(Items.tungsten, 3600, Items.graphite, 4400, Items.silicon, 4400, Items.beryllium, 6400, Items.oxide, 600, Items.carbide, 1400), () -> {}));
        addToNode(surgeCrucible, () -> node(largeSurgeCrucible, ItemStack.with(Items.graphite, 4400, Items.silicon, 4000, Items.tungsten, 4800, Items.oxide, 960, Items.surgeAlloy, 1600), () -> {}));
        addToNode(carbideCrucible, () -> node(largeCarbideCrucible, ItemStack.with(Items.thorium, 6000, Items.tungsten, 8000, Items.oxide, 1000, Items.carbide, 1200), () -> {}));
        //defense
        addToNode(coreShard, () -> node(detonator, () -> {}));
        addToNode(illuminator, () -> node(lighthouse, () -> {}));
        addToNode(mendProjector, () -> node(mendDome, () -> {}));
        addToNode(overdriveDome, () -> node(assignOverdrive, () -> {}));
        addToNode(forceProjector, () -> node(largeShieldGenerator, () -> {}));
        //defense-erekir
        addToNode(radar, () -> node(largeRadar, ItemStack.with(Items.graphite, 3600, Items.silicon, 3200, Items.beryllium, 600, Items.tungsten, 200, Items.oxide, 10), () -> {}));
        //storage
        addToNode(router, () -> node(bin, ItemStack.with(Items.copper, 550, Items.lead, 350), () -> node(machineryUnloader, ItemStack.with(Items.copper, 300, Items.lead, 200), () -> {})));
        addToNode(vault, () -> {
            node(cargo, () -> {});
            node(coreStorage, () -> {});
        });
        addToNode(unloader, () -> node(rapidUnloader, () -> node(rapidDirectionalUnloader, () -> {})));
        //storage-erekir
        addToNode(reinforcedVault, () -> node(reinforcedCoreStorage, () -> {}));
        //payload
        addToNode(payloadConveyor, () -> {
            node(payloadJunction, () -> {});
            node(payloadRail, () -> {});
        });
        //payload-erekir
        addToNode(reinforcedPayloadConveyor, () -> {
            node(reinforcedPayloadJunction, () -> {});
            node(reinforcedPayloadRail, () -> {});
        });
        //unit
        addToNode(tetrativeReconstructor, () -> node(titanReconstructor, () -> node(experimentalUnitFactory, () -> {})));
        //unit-erekir
        addToNode(unitRepairTower, () -> node(largeUnitRepairTower, ItemStack.with(Items.graphite, 2400, Items.silicon, 3000, Items.tungsten, 2600, Items.oxide, 1200, Items.carbide, 600), Seq.with(new OnSector(siege)), () -> {}));
        addToNode(basicAssemblerModule, () -> node(seniorAssemblerModule, () -> {}));
        //logic
        addToNode(memoryCell, () -> node(buffrerdMemoryCell, () -> node(buffrerdMemoryBank, () -> {})));
        addToNode(hyperProcessor, () -> node(matrixProcessor, () -> {}));
        addToNode(largeLogicDisplay, () -> node(hugeLogicDisplay, () -> {}));
        addToNode(switchBlock, () -> node(heatSink, () -> {
            node(heatFan, () -> {});
            node(heatSinkLarge, () -> {});
        }));
        //turret
        addToNode(segment, () -> node(dissipation, () -> {}));
        addToNode(duo, () -> {
            node(rocketLauncher, Seq.with(new SectorComplete(ruinousShores)), () -> node(multipleRocketLauncher, Seq.with(new SectorComplete(windsweptIslands)), () -> {
                node(largeRocketLauncher, Seq.with(new SectorComplete(facility32m)), () -> {});
                node(rocketSilo, Seq.with(new SectorComplete(tarFields)), () -> {});
            }));
            node(cloudbreaker, () -> {});
        });
        addToNode(scorch, () -> node(dragonBreath, () -> {}));
        addToNode(arc, () -> node(coilBlaster, () -> node(hurricane, () -> {})));
        addToNode(salvo, () -> {
            node(spike, () -> node(fissure, () -> {}));
            node(minigun, () -> {});
        });
        addToNode(meltdown, () -> {
            node(blaze, () -> {});
            node(judgement, () -> {});
        });
        //turret-erekir
        addToNode(breach, () -> node(rupture, () -> {}));
        //tier6
        addToNode(dagger, () -> node(vanguard, () -> node(striker, () -> node(counterattack, () -> node(crush, () -> node(destruction, () -> node(purgatory, () -> {})))))));
        addToNode(reign, () -> node(suzerain, () -> {}));
        addToNode(corvus, () -> node(supernova, () -> {}));
        addToNode(toxopid, () -> node(cancer, () -> {}));
        addToNode(eclipse, () -> node(sunlit, () -> {}));
        addToNode(oct, () -> node(windstorm, () -> {}));
        addToNode(omura, () -> node(mosasaur, () -> {}));
        addToNode(navanax, () -> node(killerWhale, () -> {}));
        //tier6-erekir
        addToNode(conquer, () -> node(dominate, () -> {}));
        addToNode(collaris, () -> node(oracle, () -> {}));
        addToNode(disrupt, () -> node(havoc, () -> {}));
        //sector presets
    }

    public static void addToNode(UnlockableContent content, Runnable children){
        context = TechTree.all.find(t -> t.content == content);
        children.run();
    }

    public static void removeNode(UnlockableContent content){
        context = TechTree.all.find(t -> t.content == content);
        if(context != null){
            context.remove();
        }
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

    public static void nodeProduce(UnlockableContent content, Seq<Objective> objectives, Runnable children){
        node(content, content.researchRequirements(), objectives.add(new Produce(content)), children);
    }

    public static void nodeProduce(UnlockableContent content, Runnable children){
        nodeProduce(content, new Seq<>(), children);
    }
}
