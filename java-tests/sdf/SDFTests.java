package sdf;

import forsyde.io.java.core.EdgeInfo;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexViewer;
import forsyde.io.java.drivers.ForSyDeModelHandler;
import forsyde.io.java.typed.viewers.moc.sdf.SDFChannel;
import forsyde.io.java.typed.viewers.moc.sdf.SDFComb;
import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.AsUndirectedGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.Set;
import java.util.stream.Collectors;

public class SDFTests {

    // thread safe as it is only a function holder
    final ForSyDeModelHandler forSyDeModelHandler = new ForSyDeModelHandler();

    @Test
    public void sobelSDFModel() throws Exception {
        final ForSyDeSystemGraph forSyDeSystemGraph = forSyDeModelHandler.loadModel("examples/sdf/sobel2mpsoc.forsyde.xmi");
        final Set<SDFComb> actors = forSyDeSystemGraph.vertexSet().stream().filter(SDFComb::conforms)
                .map(SDFComb::enforce).collect(Collectors.toSet());
        final Set<Vertex> actorsVertexes = actors.stream().map(VertexViewer::getViewedVertex)
                .collect(Collectors.toSet());
        final Set<SDFChannel> channels = forSyDeSystemGraph.vertexSet().stream()
                .filter(SDFChannel::conforms)
                .map(SDFChannel::enforce).collect(Collectors.toSet());
        final Graph<Vertex, EdgeInfo> undirected = new AsUndirectedGraph<>(forSyDeSystemGraph);
        final ConnectivityInspector<Vertex, EdgeInfo> inspector = new ConnectivityInspector<>(undirected);
        for (Vertex actorVertex: actorsVertexes) {
            Assertions.assertTrue(inspector.connectedSetOf(actorVertex).containsAll(actorsVertexes));
        }
        Assertions.assertEquals(4, actors.size());
        Assertions.assertEquals(4, channels.size());
    }

}
