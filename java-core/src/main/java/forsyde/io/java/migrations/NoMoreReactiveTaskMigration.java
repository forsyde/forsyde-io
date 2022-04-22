package forsyde.io.java.migrations;

import forsyde.io.java.core.EdgeInfo;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;

public class NoMoreReactiveTaskMigration implements SystemGraphMigrator {

    @Override
    public String getName() {
        return "NoMoreReactiveTaskMigration";
    }

    @Override
    public boolean effect(ForSyDeSystemGraph forSyDeSystemGraph) {
        for (Vertex v : forSyDeSystemGraph.vertexSet()) {
            if (v.hasTrait("execution::UpsampleReactiveStimulus")) {
                v.addTraits("execution::Upsample");
                v.ports.add("activated");
                v.ports.add("activators");
                for (EdgeInfo e : forSyDeSystemGraph.incomingEdgesOf(v)) {
                    final Vertex src = forSyDeSystemGraph.getEdgeSource(e);
                    forSyDeSystemGraph.connect(src, v, e.sourcePort.orElse("activated"), "activators", "execution::EventEdge");
                }
                for (EdgeInfo e : forSyDeSystemGraph.outgoingEdgesOf(v)) {
                    final Vertex dst = forSyDeSystemGraph.getEdgeTarget(e);
                    forSyDeSystemGraph.connect(v, dst, "activated", e.targetPort.orElse("activators"), "execution::EventEdge");
                }
            }
            if (v.hasTrait("execution::DownsampleReactiveStimulus")) {
                v.addTraits("execution::Downsample");
                v.ports.add("activated");
                v.ports.add("activators");
                for (EdgeInfo e : forSyDeSystemGraph.incomingEdgesOf(v)) {
                    final Vertex src = forSyDeSystemGraph.getEdgeSource(e);
                    forSyDeSystemGraph.connect(src, v, e.sourcePort.orElse("activated"), "activators", "execution::EventEdge");
                }
                for (EdgeInfo e : forSyDeSystemGraph.outgoingEdgesOf(v)) {
                    final Vertex dst = forSyDeSystemGraph.getEdgeTarget(e);
                    forSyDeSystemGraph.connect(v, dst, "activated", e.targetPort.orElse("activators"), "execution::EventEdge");
                }
            }
            if (v.hasTrait("execution::SimpleReactiveStimulus") || v.hasTrait("execution::MultiANDReactiveStimulus")) {
                forSyDeSystemGraph.incomingEdgesOf(v).stream().map(forSyDeSystemGraph::getEdgeSource).forEach(src -> {
                    forSyDeSystemGraph.outgoingEdgesOf(v).stream().map(forSyDeSystemGraph::getEdgeTarget).forEach(dst -> {
                        if (src.hasTrait("execution::Task") && dst.hasTrait("execution::Task")) {
                            src.ports.add("activated");
                            dst.ports.add("activators");
                            forSyDeSystemGraph.connect(src, dst, "activated", "activators", "execution::EventEdge");
                        }
                    });
                });
            }
            if (v.hasTrait("execution::PeriodicTask")) {
                v.ports.add("activated");
                for (EdgeInfo e : forSyDeSystemGraph.outgoingEdgesOf(v)) {
                    final Vertex dst = forSyDeSystemGraph.getEdgeTarget(e);
                    forSyDeSystemGraph.connect(v, dst, "activated", e.targetPort.orElse("activators"), "execution::EventEdge");
                }
            }
        }
        return true;
    }
}
