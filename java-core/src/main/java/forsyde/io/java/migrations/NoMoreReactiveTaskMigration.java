package forsyde.io.java.migrations;

import forsyde.io.java.core.EdgeInfo;
import forsyde.io.java.core.SystemGraph;
import forsyde.io.java.core.Vertex;

public class NoMoreReactiveTaskMigration implements SystemGraphMigrator {

    @Override
    public String getName() {
        return "NoMoreReactiveTaskMigration";
    }

    @Override
    public boolean effect(SystemGraph systemGraph) {
//        for (Vertex v : systemGraph.vertexSet()) {
//            if (v.hasTrait("execution::UpsampleReactiveStimulus")) {
//                v.addTraits("execution::Upsample");
//                v.addPort("activated");
//                v.addPort("activators");
//                for (EdgeInfo e : systemGraph.incomingEdgesOf(v)) {
//                    final Vertex src = systemGraph.getEdgeSource(e);
//                    systemGraph.connect(src, v, e.getSourcePort().orElse("activated"), "activators", "execution::EventEdge");
//                }
//                for (EdgeInfo e : systemGraph.outgoingEdgesOf(v)) {
//                    final Vertex dst = systemGraph.getEdgeTarget(e);
//                    systemGraph.connect(v, dst, "activated", e.getTargetPort().orElse("activators"), "execution::EventEdge");
//                }
//            }
//            if (v.hasTrait("execution::DownsampleReactiveStimulus")) {
//                v.addTraits("execution::Downsample");
//                v.addPort("activated");
//                v.addPort("activators");
//                for (EdgeInfo e : systemGraph.incomingEdgesOf(v)) {
//                    final Vertex src = systemGraph.getEdgeSource(e);
//                    systemGraph.connect(src, v, e.getSourcePort().orElse("activated"), "activators", "execution::EventEdge");
//                }
//                for (EdgeInfo e : systemGraph.outgoingEdgesOf(v)) {
//                    final Vertex dst = systemGraph.getEdgeTarget(e);
//                    systemGraph.connect(v, dst, "activated", e.getTargetPort().orElse("activators"), "execution::EventEdge");
//                }
//            }
//            if (v.hasTrait("execution::SimpleReactiveStimulus") || v.hasTrait("execution::MultiANDReactiveStimulus")) {
//                systemGraph.incomingEdgesOf(v).stream().map(systemGraph::getEdgeSource).forEach(src -> {
//                    systemGraph.outgoingEdgesOf(v).stream().map(systemGraph::getEdgeTarget).forEach(dst -> {
//                        if (src.hasTrait("execution::Task") && dst.hasTrait("execution::Task")) {
//                            src.addPort("activated");
//                            dst.addPort("activators");
//                            systemGraph.connect(src, dst, "activated", "activators", "execution::EventEdge");
//                        }
//                    });
//                });
//            }
//            if (v.hasTrait("execution::PeriodicTask")) {
//                v.addPort("activated");
//                v.addTraits("execution::Task");
//                for (EdgeInfo e : systemGraph.outgoingEdgesOf(v)) {
//                    final Vertex dst = systemGraph.getEdgeTarget(e);
//                    systemGraph.connect(v, dst, "activated", e.getTargetPort().orElse("activators"), "execution::EventEdge");
//                }
//            }
//        }
        return true;
    }
}
