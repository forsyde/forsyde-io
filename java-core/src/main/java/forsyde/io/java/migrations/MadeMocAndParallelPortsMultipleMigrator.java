package forsyde.io.java.migrations;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;

public class MadeMocAndParallelPortsMultipleMigrator implements SystemGraphMigrator {
    @Override
    public boolean effect(ForSyDeSystemGraph forSyDeSystemGraph) {
        for (Vertex v : forSyDeSystemGraph.vertexSet()) {
            if (v.hasTrait("moc::sy::SYMap") && v.hasPort("combFunction")) {
                v.addPort("combFunctions");
                forSyDeSystemGraph.outgoingEdgesOf(v).forEach(edgeInfo -> {
                    if (edgeInfo.getSourcePort().map(p -> p.equals("combFunction")).orElse(false)) {
                        forSyDeSystemGraph.connect(v, forSyDeSystemGraph.getEdgeTarget(edgeInfo), "combFunctions", null, "moc::AbstractionEdge");
                    }
                });
            }
            if (v.hasTrait("parallel::MapV") && v.hasPort("kernel")) {
                v.addPort("kernels");
                forSyDeSystemGraph.outgoingEdgesOf(v).forEach(edgeInfo -> {
                    if (edgeInfo.getSourcePort().map(p -> p.equals("combFunction")).orElse(false)) {
                        forSyDeSystemGraph.connect(v, forSyDeSystemGraph.getEdgeTarget(edgeInfo), "kernels", null, "moc::AbstractionEdge");
                    }
                });
            }
            if (v.hasTrait("parallel::ReduceV") && v.hasPort("kernel")) {
                v.addPort("kernels");
                forSyDeSystemGraph.outgoingEdgesOf(v).forEach(edgeInfo -> {
                    if (edgeInfo.getSourcePort().map(p -> p.equals("combFunction")).orElse(false)) {
                        forSyDeSystemGraph.connect(v, forSyDeSystemGraph.getEdgeTarget(edgeInfo), "kernels", null, "moc::AbstractionEdge");
                    }
                });
            }
        }
        return true;
    }

    @Override
    public String getName() {
        return "MadeMocAndParallelPortsMultipleMigrator";
    }
}
