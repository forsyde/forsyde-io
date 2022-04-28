package forsyde.io.java.migrations;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexProperty;

import java.util.HashMap;

public class TypedOperationPropertyToPorts implements SystemGraphMigrator {

    @Override
    public String getName() {
        return "TypedOperationPropertyToPorts";
    }

    @Override
    public boolean effect(ForSyDeSystemGraph forSyDeSystemGraph) {
        for (Vertex v : forSyDeSystemGraph.vertexSet()) {
            if (v.hasTrait("typing::TypedOperation")) {

                v.putProperty("_loopSequence_ordering_",
                        v.properties.getOrDefault("_callSequence_ordering_",
                                VertexProperty.create(new HashMap<String, Integer>())));
                forSyDeSystemGraph.outgoingEdgesOf(v).stream()
                        .filter(e -> e.sourcePort.orElse("").equals("callSequence"))
                        .map(forSyDeSystemGraph::getEdgeTarget)
                        .forEach(dst -> {
                            forSyDeSystemGraph.connect(v, dst, "loopSequence", null, "execution::ExecutionEdge");
                        });
            }
        }
        return true;
    }
}
