package forsyde.io.java.migrations;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;

public class ParallelSkeletonsNameMigrator implements SystemGraphMigrator {
    @Override
    public boolean effect(ForSyDeSystemGraph forSyDeSystemGraph) {
        for (Vertex v : forSyDeSystemGraph.vertexSet()) {
            if (v.hasTrait("parallel::Map")) {
                v.addTraits(VertexTrait.PARALLEL_MAPV);
            }
            if (v.hasTrait("parallel::Reduce")) {
                v.addTraits(VertexTrait.PARALLEL_REDUCEV);
            }
        }
        return true;
    }

    @Override
    public String getName() {
        return "ParallelSkeletonsNameMigrator";
    }
}
