import forsyde.io.core.SystemGraph;
import forsyde.io.core.ModelHandler;
import forsyde.io.lib.ForSyDeHierarchy;
import forsyde.io.visual.kgt.drivers.KGTDriver;

class ZynqPlatform {
    public static void main(String[] args) throws Exception {

        SystemGraph sgraph = new SystemGraph();

        var zynqDualCoreArm = ForSyDeHierarchy.GenericProcessingModule.enforce(sgraph, sgraph.newVertex("ZYNQ_ARM_x2"));
        zynqDualCoreArm.maximumComputationParallelism(2);
        zynqDualCoreArm.operatingFrequencyInHertz(650L * 1000000L);
        // we also make it visualizable for later
        ForSyDeHierarchy.Visualizable.enforce(zynqDualCoreArm);

        var private_dram = ForSyDeHierarchy.GenericMemoryModule.enforce(sgraph, sgraph.newVertex("DRAM"));
        private_dram.operatingFrequencyInHertz(100L * 1000000L);
        // we also make it visualizable for later
        ForSyDeHierarchy.Visualizable.enforce(private_dram);

        // connect dual core to the memory
        sgraph.connect(zynqDualCoreArm, private_dram, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);
        sgraph.connect(private_dram, zynqDualCoreArm, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);

        var ps7_0_axi_bus = ForSyDeHierarchy.GenericCommunicationModule.enforce(sgraph, sgraph.newVertex("ps7_0_axi"));
        ps7_0_axi_bus.operatingFrequencyInHertz(100L * 1000000L);
        // we also make it visualizable for later
        ForSyDeHierarchy.Visualizable.enforce(ps7_0_axi_bus);

        // the dual core and the first axi bus
        sgraph.connect(zynqDualCoreArm, ps7_0_axi_bus, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);
        sgraph.connect(ps7_0_axi_bus, zynqDualCoreArm, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);

        var hp_video_axi_bus = ForSyDeHierarchy.GenericCommunicationModule.enforce(sgraph, sgraph.newVertex("hp_video_axi"));
        hp_video_axi_bus.operatingFrequencyInHertz(100L * 1000000L);
        // we also make it visualizable for later
        ForSyDeHierarchy.Visualizable.enforce(hp_video_axi_bus);

        // connect the busses
        sgraph.connect(ps7_0_axi_bus, hp_video_axi_bus, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);
        sgraph.connect(hp_video_axi_bus, ps7_0_axi_bus, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);

        var bram_axi_bus = ForSyDeHierarchy.GenericCommunicationModule.enforce(sgraph, sgraph.newVertex("bram_axi"));
        bram_axi_bus.operatingFrequencyInHertz(100L * 1000000L);
        // we also make it visualizable for later
        ForSyDeHierarchy.Visualizable.enforce(bram_axi_bus);

        // connect the busses
        sgraph.connect(ps7_0_axi_bus, bram_axi_bus, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);
        sgraph.connect(bram_axi_bus, ps7_0_axi_bus, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);

        var shared_bram = ForSyDeHierarchy.GenericMemoryModule.enforce(sgraph, sgraph.newVertex("Shared_BRAM"));
        shared_bram.operatingFrequencyInHertz(100L * 1000000L);
        // we also make it visualizable for later
        ForSyDeHierarchy.Visualizable.enforce(shared_bram);

        // connect the busses
        sgraph.connect(shared_bram, bram_axi_bus, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);
        sgraph.connect(bram_axi_bus, shared_bram, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);

        // make the acc
        var hp_video_ss = ForSyDeHierarchy.GenericProcessingModule.enforce(sgraph, sgraph.newVertex("hp_video_ss"));
        hp_video_ss.operatingFrequencyInHertz(142L * 1000000L);
        // we also make it visualizable for later
        ForSyDeHierarchy.Visualizable.enforce(hp_video_ss);

        // connect to bus
        sgraph.connect(hp_video_ss, hp_video_axi_bus, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);
        sgraph.connect(hp_video_axi_bus, hp_video_ss, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);



        // export to use anywhere else
        var handlerWithRegistrations = new ModelHandler()
                .registerTraitHierarchy(new ForSyDeHierarchy()) // not mandatory for exporting, but good practice to have it anyways
                .registerDriver(new KGTDriver()); // because we want to have the visualization

        // now the micro blazes
        for (int i = 0; i < 3; i++) {
            var ublazei_tile = ForSyDeHierarchy.GreyBox.enforce(sgraph, sgraph.newVertex("uBlaze" + i + "_tile"));
            var ublazei = ForSyDeHierarchy.GenericProcessingModule.enforce(sgraph, sgraph.newVertex("uBlaze" + i));
            ublazei.operatingFrequencyInHertz(100L * 1000000L);
            // we also make it visualizable for later
            ublazei_tile.addContained(ForSyDeHierarchy.Visualizable.enforce(ublazei));


            var localmemi = ForSyDeHierarchy.GenericMemoryModule.enforce(sgraph, sgraph.newVertex("OCM" + i));
            localmemi.operatingFrequencyInHertz(100L * 1000000L);
            // we also make it visualizable for later
            ublazei_tile.addContained(ForSyDeHierarchy.Visualizable.enforce(localmemi));

            // connect dual core to the memory
            sgraph.connect(ublazei, localmemi, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);
            sgraph.connect(localmemi, ublazei, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);

            var ublazi_axi_bus = ForSyDeHierarchy.GenericCommunicationModule.enforce(sgraph, sgraph.newVertex("uBlaze" + i + "_axi"));
            ublazi_axi_bus.operatingFrequencyInHertz(100L * 1000000L);
            // we also make it visualizable for later
            ublazei_tile.addContained(ForSyDeHierarchy.Visualizable.enforce(ublazi_axi_bus));

            // connect bus
            sgraph.connect(ublazei, ublazi_axi_bus, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);
            sgraph.connect(ublazi_axi_bus, ublazei, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);
            // and to other buses
            sgraph.connect(bram_axi_bus, ublazi_axi_bus, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);
            sgraph.connect(ublazi_axi_bus, bram_axi_bus, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);
            // now visual (no semantic meaning, just visuals)
            ublazei_tile.addPorts("buses");
            sgraph.connect(bram_axi_bus, ublazei_tile, null, "buses", ForSyDeHierarchy.EdgeTraits.VisualConnection);
            sgraph.connect(ublazei_tile, ublazi_axi_bus, "buses", ForSyDeHierarchy.EdgeTraits.VisualConnection);
            sgraph.connect(ublazi_axi_bus, ublazei_tile, null, "buses", ForSyDeHierarchy.EdgeTraits.VisualConnection);
            sgraph.connect(ublazei_tile, bram_axi_bus, "buses", ForSyDeHierarchy.EdgeTraits.VisualConnection);

            // now add message box
            var mboxi = ForSyDeHierarchy.GenericMemoryModule.enforce(sgraph, sgraph.newVertex("MBox" + i));
            mboxi.operatingFrequencyInHertz(100L * 1000000L);
            ForSyDeHierarchy.Visualizable.enforce(mboxi);
            // final connections
            sgraph.connect(mboxi, ublazi_axi_bus, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);
            sgraph.connect(ublazi_axi_bus, mboxi, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);
            sgraph.connect(mboxi, ps7_0_axi_bus, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);
            sgraph.connect(ps7_0_axi_bus, mboxi, ForSyDeHierarchy.EdgeTraits.PhysicalConnection, ForSyDeHierarchy.EdgeTraits.VisualConnection);
        }

        handlerWithRegistrations.writeModel(sgraph, "zynq_plat.fiodl");
        handlerWithRegistrations.writeModel(sgraph, "zynq_plat.kgt");
    }
}



