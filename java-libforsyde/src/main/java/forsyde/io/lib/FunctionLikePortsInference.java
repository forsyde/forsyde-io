package forsyde.io.lib;

import forsyde.io.core.EdgeInfo;
import forsyde.io.core.SystemGraph;
import forsyde.io.core.Vertex;
import forsyde.io.core.inference.SystemGraphInference;
import forsyde.io.lib.hierarchy.ForSyDeHierarchy;

import java.util.List;
import java.util.stream.Collectors;

public class FunctionLikePortsInference implements SystemGraphInference {
    @Override
    public void infer(SystemGraph systemGraph) {
        for (var v : systemGraph.vertexSet()) {
            ForSyDeHierarchy.ReduceV.tryView(systemGraph, v).ifPresent(reduceVViewer -> {
                if (reduceVViewer.inputArray() != null) {
                    reduceVViewer.inputPorts(List.of(reduceVViewer.inputArray()));
                }
                if (reduceVViewer.outputScalar() != null) {
                    reduceVViewer.outputPorts(List.of(reduceVViewer.outputScalar()));
                }
            });
            ForSyDeHierarchy.SYProcess.tryView(systemGraph, v).ifPresent(syProcessViewer -> {
                var inputs = systemGraph.incomingEdgesOf(v).stream().filter(e -> {
                    var src = systemGraph.getEdgeSource(e);
                    return ForSyDeHierarchy.SYSignal.tryView(systemGraph, src).map(sySignalViewer -> sySignalViewer.consumers().contains(syProcessViewer)).orElse(false);
                }).flatMap(e -> e.getTargetPort().stream()).collect(Collectors.toList());
                var outputs = systemGraph.incomingEdgesOf(v).stream().filter(e -> {
                    var dst = systemGraph.getEdgeTarget(e);
                    return ForSyDeHierarchy.SYSignal.tryView(systemGraph, dst).flatMap(sySignalViewer -> sySignalViewer.producer().map(prod -> prod.equals(syProcessViewer))).orElse(false);
                }).flatMap(e -> e.getSourcePort().stream()).collect(Collectors.toList());
                syProcessViewer.inputPorts(inputs);
                syProcessViewer.outputPorts(outputs);
            });
//            ForSyDeHierarchy.SDFActor.tryView(systemGraph, v).ifPresent(sdfActorViewer -> {
//                var inputs = systemGraph.incomingEdgesOf(v).stream().filter(e -> {
//                    var src = systemGraph.getEdgeSource(e);
//                    return ForSyDeHierarchy.SDFChannel.tryView(systemGraph, src).flatMap(sdfChannelViewer -> sdfChannelViewer.consumer().map(con -> con.equals(sdfActorViewer))).orElse(false);
//                }).flatMap(e -> e.getTargetPort().stream()).collect(Collectors.toList());
//                var outputs = systemGraph.incomingEdgesOf(v).stream().filter(e -> {
//                    var dst = systemGraph.getEdgeTarget(e);
//                    return ForSyDeHierarchy.SDFChannel.tryView(systemGraph, dst).flatMap(sdfChannelViewer -> sdfChannelViewer.producer().map(prod -> prod.equals(sdfActorViewer))).orElse(false);
//                }).flatMap(e -> e.getSourcePort().stream()).collect(Collectors.toList());
//                sdfActorViewer.inputPorts(inputs);
//                sdfActorViewer.outputPorts(outputs);
//            });
        }
    }

    @Override
    public String getName() {
        return "FunctionLikePortsInference";
    }
}
