package forsyde.io.core.migrations;

import forsyde.io.core.SystemGraph;

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
