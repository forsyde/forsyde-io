package forsyde.io.java.migrations;

import forsyde.io.java.core.SystemGraph;
import forsyde.io.java.core.Vertex;

public class ParallelSkeletonsNameMigrator implements SystemGraphMigrator {
    @Override
    public boolean effect(SystemGraph systemGraph) {
//        for (Vertex v : systemGraph.vertexSet()) {
//            if (v.hasTrait("parallel::Map")) {
//                v.addTraits(VertexTrait.PARALLEL_MAPV);
//            }
//            if (v.hasTrait("parallel::Reduce")) {
//                v.addTraits(VertexTrait.PARALLEL_REDUCEV);
//            }
//        }
        return true;
    }

    @Override
    public String getName() {
        return "ParallelSkeletonsNameMigrator";
    }
}
