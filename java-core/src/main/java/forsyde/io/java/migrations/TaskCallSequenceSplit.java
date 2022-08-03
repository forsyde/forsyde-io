package forsyde.io.java.migrations;

import forsyde.io.java.core.*;

import java.util.HashMap;

public class TaskCallSequenceSplit implements SystemGraphMigrator {

    @Override
    public String getName() {
        return "TaskCallSequenceSplit";
    }

    @Override
    public boolean effect(ForSyDeSystemGraph forSyDeSystemGraph) {
        for (Vertex v : forSyDeSystemGraph.vertexSet()) {
            if (v.hasTrait("execution::Task")) {
                v.addPort("initSequence");
                v.addPort("loopSequence");
                v.putProperty("__loopSequence_ordering__",
                        v.properties.getOrDefault("__callSequence_ordering__",
                                VertexProperty.create(new HashMap<String, Integer>())));
                forSyDeSystemGraph.outgoingEdgesOf(v).stream()
                        .filter(e -> e.getSourcePort().orElse("").equals("callSequence"))
                        .map(forSyDeSystemGraph::getEdgeTarget)
                        .forEach(dst -> {
                            forSyDeSystemGraph.connect(v, dst, "loopSequence", null, "execution::ExecutionEdge");
                        });
            }
        }
        return true;
    }
}
