package forsyde.io.java.migrations;

import forsyde.io.java.core.*;

import java.util.HashMap;
import java.util.Optional;

public class TaskCallSequenceSplit implements SystemGraphMigrator {

    @Override
    public String getName() {
        return "TaskCallSequenceSplit";
    }

    @Override
    public boolean effect(SystemGraph systemGraph) {
//        for (Vertex v : systemGraph.vertexSet()) {
//            if (v.hasTrait("execution::Task")) {
//                v.addPort("initSequence");
//                v.addPort("loopSequence");
//                v.putProperty("__loopSequence_ordering__",
//                        Optional.ofNullable(v.getProperty("__callSequence_ordering__")).orElse(
//                                new HashMap<String, Integer>()));
//                systemGraph.outgoingEdgesOf(v).stream()
//                        .filter(e -> e.getSourcePort().orElse("").equals("callSequence"))
//                        .map(systemGraph::getEdgeTarget)
//                        .forEach(dst -> {
//                            systemGraph.connect(v, dst, "loopSequence", null, "execution::ExecutionEdge");
//                        });
//            }
//        }
        return true;
    }
}
