import forsyde.io.bridge.sdf3.drivers.SDF3Driver;
import forsyde.io.core.ModelHandler;
import forsyde.io.lib.ForSyDeHierarchy;
import forsyde.io.lib.TraitNamesFrom0_6To0_7;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PerformJavaTests {

    @Test
    void testMigration2Cores() throws Exception {
        var handler = new ModelHandler().registerTraitHierarchy(new ForSyDeHierarchy()).registerSystemGraphMigrator(new TraitNamesFrom0_6To0_7());
        var m = handler.loadModel("examples/platform/toy_tiled_2core.fiodl");
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
        var m = handler.loadModel("examples/platform/bus_small_with_hwacc.fiodl");
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
        var m = handler.loadModel("examples/external/sdf3/c_rasta.hsdf.xml");
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
}
