package sdf;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.drivers.ForSyDeModelHandler;
import forsyde.io.java.typed.viewers.moc.sdf.SDFComb;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SDFTests {

    // thread safe as it is only a function holder
    final ForSyDeModelHandler forSyDeModelHandler = new ForSyDeModelHandler();

    @Test
    public void sobelSDFModel() throws Exception {
        final ForSyDeSystemGraph forSyDeSystemGraph = forSyDeModelHandler.loadModel("examples/sdf/sobel2mpsoc.forsyde.xmi");
        final Set<SDFComb> actors = forSyDeSystemGraph.vertexSet().stream().filter(SDFComb::conforms)
                .map(SDFComb::enforce).collect(Collectors.toSet());
        assertEquals(4, actors.size());
        assertEquals(1, 2);
    }

}
