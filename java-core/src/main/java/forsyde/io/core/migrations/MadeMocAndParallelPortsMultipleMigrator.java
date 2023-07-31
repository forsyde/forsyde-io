package forsyde.io.core.migrations;

import forsyde.io.core.SystemGraph;

public class MadeMocAndParallelPortsMultipleMigrator implements SystemGraphMigrator {
    @Override
    public boolean effect(SystemGraph systemGraph) {
//        for (Vertex v : systemGraph.vertexSet()) {
//            if (v.hasTrait("moc::sy::SYMap") && v.hasPort("combFunction")) {
//                v.addPort("combFunctions");
//                systemGraph.outgoingEdgesOf(v).forEach(edgeInfo -> {
//                    if (edgeInfo.getSourcePort().map(p -> p.equals("combFunction")).orElse(false)) {
//                        systemGraph.connect(v, systemGraph.getEdgeTarget(edgeInfo), "combFunctions", null, "moc::AbstractionEdge");
//                    }
//                });
//            }
//            if (v.hasTrait("parallel::MapV") && v.hasPort("kernel")) {
//                v.addPort("kernels");
//                systemGraph.outgoingEdgesOf(v).forEach(edgeInfo -> {
//                    if (edgeInfo.getSourcePort().map(p -> p.equals("combFunction")).orElse(false)) {
//                        systemGraph.connect(v, systemGraph.getEdgeTarget(edgeInfo), "kernels", null, "moc::AbstractionEdge");
//                    }
//                });
//            }
//            if (v.hasTrait("parallel::ReduceV") && v.hasPort("kernel")) {
//                v.addPort("kernels");
//                systemGraph.outgoingEdgesOf(v).forEach(edgeInfo -> {
//                    if (edgeInfo.getSourcePort().map(p -> p.equals("combFunction")).orElse(false)) {
//                        systemGraph.connect(v, systemGraph.getEdgeTarget(edgeInfo), "kernels", null, "moc::AbstractionEdge");
//                    }
//                });
//            }
//        }
        return true;
    }

    @Override
    public String getName() {
        return "MadeMocAndParallelPortsMultipleMigrator";
    }
}
