package forsyde.io.lib;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.inference.SystemGraphInference;
import forsyde.io.lib.hierarchy.ForSyDeHierarchy;
import forsyde.io.lib.hierarchy.behavior.FunctionLikeEntity;
import forsyde.io.lib.hierarchy.behavior.moc.sy.SYMap;
import forsyde.io.lib.hierarchy.behavior.parallel.MapV;
import forsyde.io.lib.hierarchy.behavior.parallel.ReduceV;
import forsyde.io.lib.hierarchy.behavior.parallel.VectorizableViewer;
import forsyde.io.lib.hierarchy.implementation.functional.InstrumentedSoftwareBehaviour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is "inference" is a simple propagator of computational requirements,
 * bringing comptuational requirements "up" from defining inner functions as much as possible
 */
public class ComputationalRequirementsPropagator implements SystemGraphInference {
    @Override
    public void infer(SystemGraph systemGraph) {
        // first, we do the SY + SDF collection
        if (systemGraph.vertexSet().stream().anyMatch(v -> ForSyDeHierarchy.InstrumentedSoftwareBehaviour.tryView(systemGraph, v).isPresent())) {
            for (var v : systemGraph.vertexSet()) {
                ForSyDeHierarchy.SYMap.tryView(systemGraph, v).filter(x -> ForSyDeHierarchy.InstrumentedSoftwareBehaviour.tryView(x).isEmpty()).ifPresent(this::propagate);
//            ForSyDeHierarchy.SDFActor.tryView(systemGraph, v).ifPresent(queue::add);
            }
        }
        // now the heap-based recursion can start
    }

    private List<Integer> merge(List<Integer> d1, List<Integer> d2) {
        var merged = new ArrayList<Integer>(Math.max(d1.size(), d2.size()));
        for (int i = 0; i < d1.size() && i < d2.size(); i++) {
            merged.add(i, Math.max(d1.get(i), d2.get(i)));
        }
        for (int i = d2.size(); i < d1.size(); i++) {
            merged.add(i, d1.get(i));
        }
        for (int i = d1.size(); i < d2.size(); i++) {
            merged.add(i, d2.get(i));
        }
        return merged;
    }

    private Map<String, Map<String, Long>> propagate(FunctionLikeEntity behaviourEntity, List<Integer> containerInputDimensions, List<Integer> containerOutputDimensions) {
        return ForSyDeHierarchy.InstrumentedSoftwareBehaviour.tryView(behaviourEntity).filter(x -> !x.computationalRequirements().isEmpty()).map(InstrumentedSoftwareBehaviour::computationalRequirements)
                .orElseGet(() ->
                ForSyDeHierarchy.SYMap.tryView(behaviourEntity).map(this::propagate).orElseGet(() ->
                ForSyDeHierarchy.MapV.tryView(behaviourEntity).map(m -> propagate(m, containerInputDimensions, containerOutputDimensions)).orElseGet(() ->
                ForSyDeHierarchy.ReduceV.tryView(behaviourEntity).map(m -> propagate(m, containerInputDimensions, containerOutputDimensions)).orElseGet(HashMap::new)
                )
                )
                );
    }

    private Map<String, Map<String, Long>> propagate(SYMap syMap) {
        var ins = ForSyDeHierarchy.InstrumentedSoftwareBehaviour.enforce(syMap);
        if (ins.computationalRequirements().isEmpty()) {
            var inputVectors = syMap.getViewedSystemGraph().incomingEdgesOf(syMap.getViewedVertex()).stream().filter(e -> e.getTargetPort().map(p -> syMap.inputPorts().contains(p)).orElse(false))
                    .map(e -> syMap.getViewedSystemGraph().getEdgeSource(e))
                    .flatMap(inp -> ForSyDeHierarchy.SYSignal.tryView(syMap.getViewedSystemGraph(), inp).stream())
                    .flatMap(inSig -> inSig.dataType().stream())
                    .flatMap(inDat -> ForSyDeHierarchy.Vectorizable.tryView(inDat).stream())
                    .map(VectorizableViewer::dimensions)
                    .reduce(List.of(), this::merge);
            var outputVectors = syMap.getViewedSystemGraph().outgoingEdgesOf(syMap.getViewedVertex()).stream().filter(e -> e.getSourcePort().map(p -> syMap.outputPorts().contains(p)).orElse(false))
                    .map(e -> syMap.getViewedSystemGraph().getEdgeTarget(e))
                    .flatMap(inp -> ForSyDeHierarchy.SYSignal.tryView(syMap.getViewedSystemGraph(), inp).stream())
                    .flatMap(outSig -> outSig.dataType().stream())
                    .flatMap(outDat -> ForSyDeHierarchy.Vectorizable.tryView(outDat).stream())
                    .map(VectorizableViewer::dimensions)
                    .reduce(List.of(), this::merge);
            var m = syMap.combFunctions().stream().map(comb -> propagate(comb, inputVectors, outputVectors)).reduce(new HashMap<>(), this::merge);
            ins.computationalRequirements(m);
        }
        return ins.computationalRequirements();
    }

    private Map<String, Map<String, Long>> propagate(MapV mapV, List<Integer> containerInputDimensions, List<Integer> containerOutputDimensions) {
        var ins = ForSyDeHierarchy.InstrumentedSoftwareBehaviour.enforce(mapV);
        // check on input vectors
        var inputVectors = mapV.getViewedSystemGraph().incomingEdgesOf(mapV)
                .stream().filter(e -> e.getTargetPort().map(p -> mapV.inputPorts().contains(p)).orElse(false))
                .flatMap(e -> ForSyDeHierarchy.Vectorizable.tryView(mapV.getViewedSystemGraph(), mapV.getViewedSystemGraph().getEdgeSource(e)).stream())
                .map(VectorizableViewer::dimensions)
                .reduce(List.of(), this::merge);
        var outputVectors = mapV.getViewedSystemGraph().outgoingEdgesOf(mapV)
                .stream().filter(e -> e.getSourcePort().map(p -> mapV.outputPorts().contains(p)).orElse(false))
                .flatMap(e -> ForSyDeHierarchy.Vectorizable.tryView(mapV.getViewedSystemGraph(), mapV.getViewedSystemGraph().getEdgeTarget(e)).stream())
                .map(VectorizableViewer::dimensions)
                .reduce(List.of(), this::merge);
        var inputMultiplier = inputVectors.isEmpty() ? containerInputDimensions.get(0) : inputVectors.get(0);
        var outputMultiplier = outputVectors.isEmpty() ? containerOutputDimensions.get(0) : outputVectors.get(0);
        var multiplier = Math.max(inputMultiplier, outputMultiplier);
        var m = mapV.kernels().stream().map(comb -> {
            var newInputs = (inputVectors.isEmpty() ? containerInputDimensions : inputVectors);
            var newOuputs = (outputVectors.isEmpty() ? containerOutputDimensions : outputVectors);
            return propagate(comb, newInputs.subList(1, newInputs.size()), newOuputs.subList(1, newOuputs.size()));
        }).reduce(new HashMap<>(), this::merge);
        // multiply all entries
        for (var k1 : m.keySet()) {
            for (var k2: m.get(k1).keySet()) {
                m.get(k1).put(k2, m.get(k1).get(k2) * multiplier);
            }
        }
        ins.computationalRequirements(m);
        return m;
    }

    private Map<String, Map<String, Long>> propagate(ReduceV reduceV, List<Integer> containerInputDimensions, List<Integer> containerOutputDimensions) {
        var ins = ForSyDeHierarchy.InstrumentedSoftwareBehaviour.enforce(reduceV);
        // get size of input array
        var inputVectors = reduceV.getViewedSystemGraph().incomingEdgesOf(reduceV)
                .stream().filter(e -> e.getTargetPort().map(p -> reduceV.inputArray().equals(p)).orElse(false))
                .flatMap(e -> ForSyDeHierarchy.Vectorizable.tryView(reduceV.getViewedSystemGraph(), reduceV.getViewedSystemGraph().getEdgeSource(e)).stream())
                .map(VectorizableViewer::dimensions)
                .reduce(List.of(), this::merge);
        var outputVectors = reduceV.getViewedSystemGraph().outgoingEdgesOf(reduceV)
                .stream().filter(e -> e.getSourcePort().map(p -> reduceV.outputScalar().equals(p)).orElse(false))
                .flatMap(e -> ForSyDeHierarchy.Vectorizable.tryView(reduceV.getViewedSystemGraph(), reduceV.getViewedSystemGraph().getEdgeTarget(e)).stream())
                .map(VectorizableViewer::dimensions)
                .reduce(List.of(), this::merge);
        var multiplier = inputVectors.isEmpty() ? containerInputDimensions.get(0) : inputVectors.get(0);
        var m = reduceV.kernels().stream().map(comb -> {
            var newInputs = (inputVectors.isEmpty() ? containerInputDimensions : inputVectors);
            var newOuputs = (outputVectors.isEmpty() ? containerOutputDimensions : outputVectors);
            return propagate(comb, newInputs.subList(1, newInputs.size()), newOuputs);
        }).reduce(new HashMap<>(), this::merge);
        // multiply all entries
        for (var k1 : m.keySet()) {
            for (var k2: m.get(k1).keySet()) {
                m.get(k1).put(k2, m.get(k1).get(k2) * multiplier);
            }
        }
        ins.computationalRequirements(m);
        return m;
    }

    private Map<String, Map<String, Long>> merge(Map<String, Map<String, Long>> toMergeLeft, Map<String, Map<String, Long>> toMergeRight) {
        var merged = new HashMap<String, Map<String, Long>>();
        for (var k : toMergeLeft.keySet()) {
            var inner = new HashMap<String, Long>();
            for (var innerK : toMergeLeft.get(k).keySet()) {
                if (toMergeRight.containsKey(k) && toMergeRight.get(k).containsKey(innerK)) {
                    inner.put(innerK, toMergeLeft.get(k).get(innerK) + toMergeRight.get(k).get(innerK));
                } else {
                    inner.put(innerK, toMergeLeft.get(k).get(innerK));
                }
            }
            merged.put(k, inner);
        }
        for (var k : toMergeRight.keySet()) {
            var inner = merged.getOrDefault(k, new HashMap<>());
            for (var innerK : toMergeRight.get(k).keySet()) {
                if (toMergeLeft.containsKey(k) && toMergeLeft.get(k).containsKey(innerK)) {
                    inner.put(innerK, toMergeLeft.get(k).get(innerK) + toMergeRight.get(k).get(innerK));
                } else {
                    inner.put(innerK, toMergeRight.get(k).get(innerK));
                }
            }
            merged.put(k, inner);
        }
        return merged;
    }

    @Override
    public String getName() {
        return "ComputationalRequirementsPropagator";
    }
}
