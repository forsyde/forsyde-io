import forsyde.io.bridge.forsyde.systemc.ForSyDeSystemCDriver;
import forsyde.io.bridge.sdf3.drivers.SDF3Driver;
import forsyde.io.core.ModelHandler;
import forsyde.io.lib.LibForSyDeModelHandler;
import forsyde.io.lib.TraitNamesFrom0_6To0_7;
import forsyde.io.lib.hierarchy.ForSyDeHierarchy;
import forsyde.io.visual.kgt.drivers.KGTDriver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class PerformJavaTests {

    @Test
    void testMigration2Cores() throws Exception {
        var handler = new ModelHandler().registerTraitHierarchy(new ForSyDeHierarchy()).registerSystemGraphMigrator(new TraitNamesFrom0_6To0_7());
        var m = handler.loadModel(Paths.get("examples/platform/toy_tiled_2core.fiodl"));
        var pes = m.vertexSet().stream().flatMap(v -> ForSyDeHierarchy.GenericProcessingModule.tryView(m, v).stream()).count();
        Assertions.assertEquals(2, pes);
        var mes = m.vertexSet().stream().flatMap(v -> ForSyDeHierarchy.GenericMemoryModule.tryView(m, v).stream()).count();
        Assertions.assertEquals(2, mes);
        var ces = m.vertexSet().stream().flatMap(v -> ForSyDeHierarchy.GenericCommunicationModule.tryView(m, v).stream()).count();
        Assertions.assertEquals(3, ces);
    }

    @Test
    void testMigrationBusPlatform() throws Exception {
        var handler = new ModelHandler().registerTraitHierarchy(new ForSyDeHierarchy()).registerSystemGraphMigrator(new TraitNamesFrom0_6To0_7());
        var m = handler.loadModel(Paths.get("examples/platform/bus_small_with_hwacc.fiodl"));
        var pes = m.vertexSet().stream().flatMap(v -> ForSyDeHierarchy.GenericProcessingModule.tryView(m, v).stream()).count();
        Assertions.assertEquals(8, pes);
        var mes = m.vertexSet().stream().flatMap(v -> ForSyDeHierarchy.GenericMemoryModule.tryView(m, v).stream()).count();
        Assertions.assertEquals(8, mes);
        var ces = m.vertexSet().stream().flatMap(v -> ForSyDeHierarchy.GenericCommunicationModule.tryView(m, v).stream()).count();
        Assertions.assertEquals(9, ces);
    }

    @Test
    void testBridgeToSDF3() throws Exception {
        var handler = new ModelHandler().registerTraitHierarchy(new ForSyDeHierarchy())
                .registerDriver(new SDF3Driver())
                .registerSystemGraphMigrator(new TraitNamesFrom0_6To0_7());
        var m = handler.loadModel(Paths.get("examples/external/sdf3/c_rasta.hsdf.xml"));
        Assertions.assertTrue(m.queryVertex("powspec").isPresent());
        m.queryVertex("powspec").ifPresent(actor -> {
            Assertions.assertTrue(ForSyDeHierarchy.SDFActor.tryView(m, actor).isPresent());
            Assertions.assertTrue( actor.getPorts().contains("p2_0"));
            Assertions.assertTrue( actor.getPorts().contains("p1_0"));
            ForSyDeHierarchy.SDFActor.tryView(m, actor).ifPresent(sdfActorViewer -> {
                Assertions.assertTrue(sdfActorViewer.production().containsKey("p2_0"));
                Assertions.assertTrue(sdfActorViewer.consumption().containsKey("p1_0"));
            });
        });
    }

    @Test
    void testMigrationworkload() throws Exception {
        var handler = new ModelHandler().registerTraitHierarchy(new ForSyDeHierarchy()).registerSystemGraphMigrator(new TraitNamesFrom0_6To0_7());
        var m = handler.loadModel(Paths.get("examples/workload/case_study_radar.fiodl"));
        Assertions.assertEquals(5, m.vertexSet().stream().flatMap(v -> ForSyDeHierarchy.Task.tryView(m, v).stream()).count());
    }

//    @Test
//    void justReadSystemC() throws Exception {
//        var driver = new ForSyDeSystemCDriver();
//        var handler = new ModelHandler().registerDriver(driver).registerDriver(new KGTDriver()).registerTraitHierarchy(new ForSyDeHierarchy());
//        var m = handler.loadModel(Paths.get("examples/systemc/toy_sy.cpp"));
//        handler.writeModel(m, "from_systemc.fiodl");
//        handler.writeModel(m, "from_systemc.kgt");
//    }
//
//    @Test
//    void justPropagate() throws Exception {
//        var handler = new ModelHandler().registerDriver(new KGTDriver());
//        LibForSyDeModelHandler.registerLibForSyDe(handler);
//        var m = handler.loadModel(Paths.get("examples/sy/imageProcessingSY.fiodl"));
//        handler.writeModel(m, "from_pre.fiodl");
//        handler.writeModel(m, "output.kgt");
//    }

//    @Test
//    void generateVisual() throws Exception {
//        var handler = new ModelHandler().registerTraitHierarchy(new ForSyDeHierarchy()).registerSystemGraphMigrator(new TraitNamesFrom0_6To0_7()).registerDriver(new KGTDriver());
////        var m = handler.loadModel(Paths.get("examples/sdf/toy_sdf_tiny.fiodl"));
//        var m = handler.loadModel(Paths.get("zynq.fiodl"));
//        handler.writeModel(m, "visual.kgt");
//    }
}
