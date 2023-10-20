package forsyde.io.lib;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.migrations.SystemGraphMigrator;
import forsyde.io.lib.hierarchy.ForSyDeHierarchy;

public class SDFActorsWithFunctionMigrator implements SystemGraphMigrator {
    @Override
    public boolean effect(SystemGraph systemGraph) {
        for (var v : systemGraph.vertexSet()) {
            ForSyDeHierarchy.SDFActor.tryView(systemGraph, v).ifPresent(sdfActorViewer -> {
                for (var e : systemGraph.outgoingEdgesOf(v)) {
                    if (e.hasTrait(ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge)) {
                        var dst = systemGraph.getEdgeTarget(e);
                        ForSyDeHierarchy.BehaviourEntity.tryView(systemGraph, dst).ifPresent(child -> {
                            var childFunc = ForSyDeHierarchy.FunctionLikeEntity.enforce(child);
                            // heuristic to connect the input and output ports of the old trait
                            for (var actorToFunc : systemGraph.getAllEdges(sdfActorViewer, childFunc)) {
                                if (actorToFunc.getSourcePort().isPresent()) {
                                    actorToFunc.getTargetPort().ifPresent(dstPort -> {
                                        childFunc.inputPorts().add(dstPort);
                                    });
                                }
                            }
                            for (var funcToActor : systemGraph.getAllEdges(childFunc, sdfActorViewer)) {
                                if (funcToActor.getTargetPort().isPresent()) {
                                    funcToActor.getSourcePort().ifPresent(srcPort -> {
                                        childFunc.outputPorts().add(srcPort);
                                    });
                                }
                            }
                        });
                    }
                }
            });
        }
        return true;
    }

    @Override
    public String getName() {
        return "SDFActorsWithFunctionMigrator";
    }
}
