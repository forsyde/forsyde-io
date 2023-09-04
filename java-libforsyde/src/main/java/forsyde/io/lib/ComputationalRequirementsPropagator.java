package forsyde.io.lib;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.Vertex;
import forsyde.io.core.VertexViewer;
import forsyde.io.core.inference.SystemGraphInference;
import forsyde.io.lib.behavior.BehaviourEntity;
import forsyde.io.lib.behavior.moc.sy.SYMap;
import forsyde.io.lib.behavior.parallel.MapV;
import forsyde.io.lib.behavior.parallel.ReduceV;
import forsyde.io.lib.implementation.functional.InstrumentedBehaviourViewer;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This is "inference" is a simple propagator of computational requirements,
 * bringing comptuational requirements "up" from defining inner functions as much as possible
 */
public class ComputationalRequirementsPropagator implements SystemGraphInference {
    @Override
    public void infer(SystemGraph systemGraph) {
        // first, we do the SY + SDF collection
        for (var v : systemGraph.vertexSet()) {
            ForSyDeHierarchy.SYMap.tryView(systemGraph, v).ifPresent(this::propagate);
//            ForSyDeHierarchy.SDFActor.tryView(systemGraph, v).ifPresent(queue::add);
        }
        // now the heap-based recursion can start
    }

    private Map<String, Map<String, Long>> propagate(BehaviourEntity behaviourEntity) {
        return ForSyDeHierarchy.InstrumentedBehaviour.tryView(behaviourEntity).map(InstrumentedBehaviourViewer::computationalRequirements)
                .orElseGet(() ->
                ForSyDeHierarchy.SYMap.tryView(behaviourEntity).map(this::propagate).orElseGet(() ->
                ForSyDeHierarchy.MapV.tryView(behaviourEntity).map(this::propagate).orElseGet(() ->
                ForSyDeHierarchy.ReduceV.tryView(behaviourEntity).map(this::propagate).orElseGet(HashMap::new)
                )
                )
                );
    }

    private Map<String, Map<String, Long>> propagate(SYMap syMap) {
        var ins = ForSyDeHierarchy.InstrumentedBehaviour.enforce(syMap);
        var m = ins.computationalRequirements();
        for (var comb : syMap.combFunctions()) {
            var recursed = propagate(comb);
            mergeMapInPlace(m, recursed);
        }
        return m;
    }

    private Map<String, Map<String, Long>> propagate(MapV mapV) {
        var ins = ForSyDeHierarchy.InstrumentedBehaviour.enforce(mapV);
        var m = ins.computationalRequirements();
        var multiplier = 1;
        // check on input vectors
        for (var e : mapV.getViewedSystemGraph().incomingEdgesOf(mapV)) {
            if (e.getTargetPort().map(p -> mapV.inputPorts().contains(p)).orElse(false)) {
                var dimMult = ForSyDeHierarchy.Vectorizable.tryView(mapV.getViewedSystemGraph(), mapV.getViewedSystemGraph().getEdgeSource(e)).map(vectorizableViewer ->
                    vectorizableViewer.dimensions().stream().reduce(1, (a, b) -> a * b)
                ).orElse(1);
                multiplier = Math.max(dimMult, multiplier);
            }
        }
        // now output vectors
        for (var e : mapV.getViewedSystemGraph().outgoingEdgesOf(mapV)) {
            if (e.getSourcePort().map(p -> mapV.outputPorts().contains(p)).orElse(false)) {
                var dimMult = ForSyDeHierarchy.Vectorizable.tryView(mapV.getViewedSystemGraph(), mapV.getViewedSystemGraph().getEdgeTarget(e)).map(vectorizableViewer ->
                        vectorizableViewer.dimensions().stream().reduce(1, (a, b) -> a * b)
                ).orElse(1);
                multiplier = Math.max(dimMult, multiplier);
            }
        }
        for (var comb : mapV.kernels()) {
            var recursed = propagate(comb);
            mergeMapInPlace(m, recursed);
        }
        // multiply all entries
        for (var k1 : m.keySet()) {
            for (var k2: m.get(k1).keySet()) {
                m.get(k1).put(k2, m.get(k1).get(k2) * multiplier);
            }
        }
        return m;
    }

    private Map<String, Map<String, Long>> propagate(ReduceV reduceV) {
        var ins = ForSyDeHierarchy.InstrumentedBehaviour.enforce(reduceV);
        var m = ins.computationalRequirements();
        var multiplier = 1;
        // get size of input array
        for (var e : reduceV.getViewedSystemGraph().incomingEdgesOf(reduceV)) {
            if (e.getTargetPort().map(p -> reduceV.inputArray().equals(p)).orElse(false)) {
                var dimMult = ForSyDeHierarchy.Vectorizable.tryView(reduceV.getViewedSystemGraph(), reduceV.getViewedSystemGraph().getEdgeSource(e)).map(vectorizableViewer ->
                        vectorizableViewer.dimensions().get(0)
                ).orElse(1);
                multiplier = Math.max(dimMult, multiplier);
            }
        }
        for (var comb : reduceV.kernels()) {
            var recursed = propagate(comb);
            mergeMapInPlace(m, recursed);
        }
        // multiply all entries
        for (var k1 : m.keySet()) {
            for (var k2: m.get(k1).keySet()) {
                m.get(k1).put(k2, m.get(k1).get(k2) * multiplier);
            }
        }
        return m;
    }

    private Map<String, Map<String, Long>> mergeMapInPlace(Map<String, Map<String, Long>> target, Map<String, Map<String, Long>> mergee) {
        mergee.forEach((key1, value1) -> {
            target.merge(key1, value1, (a, b) -> {
                b.forEach((key, value) -> a.merge(key, value, Long::sum));
                return a;
            });
        });
        return target;
    }

    @Override
    public String getName() {
        return "ComputationalRequirementsPropagator";
    }
}
