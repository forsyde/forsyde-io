package forsyde.io.lib;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.inference.SystemGraphInference;
import forsyde.io.lib.hierarchy.ForSyDeHierarchy;
import forsyde.io.lib.hierarchy.behavior.DataTypeLike;
import forsyde.io.lib.hierarchy.behavior.FunctionLikeEntity;
import forsyde.io.lib.hierarchy.behavior.moc.sdf.SDFActor;
import forsyde.io.lib.hierarchy.behavior.moc.sdf.SDFChannel;
import forsyde.io.lib.hierarchy.behavior.moc.sy.SYMap;
import forsyde.io.lib.hierarchy.behavior.moc.sy.SYSignal;
import forsyde.io.lib.hierarchy.behavior.parallel.MapV;
import forsyde.io.lib.hierarchy.behavior.parallel.ReduceV;
import forsyde.io.lib.hierarchy.behavior.parallel.Vectorizable;
import forsyde.io.lib.hierarchy.behavior.parallel.VectorizableViewer;
import forsyde.io.lib.hierarchy.implementation.functional.InstrumentedBehaviourViewer;
import forsyde.io.lib.hierarchy.implementation.functional.InstrumentedDataType;
import forsyde.io.lib.hierarchy.implementation.functional.InstrumentedSoftwareBehaviour;
import forsyde.io.lib.hierarchy.implementation.functional.InstrumentedSoftwareBehaviourViewer;

import java.util.*;

/**
 * This is "inference" is a simple propagator of memory requirements,
 * bringing comptuational requirements "up" from defining inner functions as much as possible
 */
public class MemoryRequirementsPropagator implements SystemGraphInference {
    @Override
    public void infer(SystemGraph systemGraph) {
        // first, we do the SY + SDF collection
        if (systemGraph.vertexSet().stream().anyMatch(v -> ForSyDeHierarchy.InstrumentedSoftwareBehaviour.tryView(systemGraph, v).isPresent())) {
            for (var v : systemGraph.vertexSet()) {
                ForSyDeHierarchy.SYMap.tryView(systemGraph, v).ifPresent(this::propagate);
                ForSyDeHierarchy.SYSignal.tryView(systemGraph, v).ifPresent(this::propagate);
                ForSyDeHierarchy.SDFActor.tryView(systemGraph, v).ifPresent(this::propagate);
                ForSyDeHierarchy.SDFChannel.tryView(systemGraph, v).ifPresent(this::propagate);
            }
        }
        // now the heap-based recursion can start
    }

    private Map<String, Long> propagate(FunctionLikeEntity behaviourEntity) {
        return ForSyDeHierarchy.InstrumentedSoftwareBehaviour.tryView(behaviourEntity).filter(x -> !x.maxSizeInBits().isEmpty()).map(InstrumentedSoftwareBehaviour::maxSizeInBits)
                .orElseGet(() ->
                ForSyDeHierarchy.SYMap.tryView(behaviourEntity).map(this::propagate).orElseGet(() ->
                ForSyDeHierarchy.MapV.tryView(behaviourEntity).map(this::propagate).orElseGet(() ->
                ForSyDeHierarchy.ReduceV.tryView(behaviourEntity).map(this::propagate).orElseGet(HashMap::new)
                )
                )
                );
    }

    private Map<String, Long> propagate(SDFActor sdfActor) {
        var ins = ForSyDeHierarchy.InstrumentedSoftwareBehaviour.enforce(sdfActor);
        var internalVariables = new HashSet<DataTypeLike>();
        sdfActor.combFunctions().forEach(functionLikeEntity -> {
            functionLikeEntity.getViewedSystemGraph().incomingEdgesOf(functionLikeEntity)
                    .stream().filter(e -> e.getTargetPort().map(p -> functionLikeEntity.inputPorts().contains(p)).orElse(false))
                    .flatMap(e -> ForSyDeHierarchy.DataTypeLike.tryView(functionLikeEntity.getViewedSystemGraph(), functionLikeEntity.getViewedSystemGraph().getEdgeSource(e)).stream())
                    .forEach(internalVariables::add);
        });
        sdfActor.combFunctions().forEach(functionLikeEntity -> {
            functionLikeEntity.getViewedSystemGraph().outgoingEdgesOf(functionLikeEntity)
                    .stream().filter(e -> e.getSourcePort().map(p -> functionLikeEntity.outputPorts().contains(p)).orElse(false))
                    .flatMap(e -> ForSyDeHierarchy.DataTypeLike.tryView(functionLikeEntity.getViewedSystemGraph(), functionLikeEntity.getViewedSystemGraph().getEdgeTarget(e)).stream())
                    .forEach(internalVariables::add);
        });
        var m = sdfActor.combFunctions().stream().map(this::propagate).reduce(new HashMap<>(), this::merge);
        var sigs = internalVariables.stream().map(this::propagate).reduce(new HashMap<>(), this::merge);
        ins.maxSizeInBits(merge(m, sigs));
        return ins.maxSizeInBits();
    }

    private Map<String, Long> propagate(SYMap syMap) {
        var ins = ForSyDeHierarchy.InstrumentedSoftwareBehaviour.enforce(syMap);
        var internalVariables = new HashSet<DataTypeLike>();
        syMap.combFunctions().forEach(functionLikeEntity -> {
            functionLikeEntity.getViewedSystemGraph().incomingEdgesOf(functionLikeEntity)
                    .stream().filter(e -> e.getTargetPort().map(p -> functionLikeEntity.inputPorts().contains(p)).orElse(false))
                    .flatMap(e -> ForSyDeHierarchy.DataTypeLike.tryView(functionLikeEntity.getViewedSystemGraph(), functionLikeEntity.getViewedSystemGraph().getEdgeSource(e)).stream())
                    .forEach(internalVariables::add);
        });
        syMap.combFunctions().forEach(functionLikeEntity -> {
            functionLikeEntity.getViewedSystemGraph().outgoingEdgesOf(functionLikeEntity)
                    .stream().filter(e -> e.getSourcePort().map(p -> functionLikeEntity.outputPorts().contains(p)).orElse(false))
                    .flatMap(e -> ForSyDeHierarchy.DataTypeLike.tryView(functionLikeEntity.getViewedSystemGraph(), functionLikeEntity.getViewedSystemGraph().getEdgeTarget(e)).stream())
                    .forEach(internalVariables::add);
        });
        var m = syMap.combFunctions().stream().map(this::propagate).reduce(new HashMap<>(), this::merge);
        var sigs = internalVariables.stream().map(this::propagate).reduce(new HashMap<>(), this::merge);
        ins.maxSizeInBits(merge(m, sigs));
        return ins.maxSizeInBits();
    }

    private Map<String, Long> propagate(MapV mapV) {
        var ins = ForSyDeHierarchy.InstrumentedSoftwareBehaviour.enforce(mapV);
        var internalVectors = new HashSet<Vectorizable>();
        // check on input vectors
        mapV.kernels().forEach(functionLikeEntity -> {
            functionLikeEntity.getViewedSystemGraph().incomingEdgesOf(functionLikeEntity)
                    .stream().filter(e -> e.getTargetPort().map(p -> functionLikeEntity.inputPorts().contains(p)).orElse(false))
                    .flatMap(e -> ForSyDeHierarchy.Vectorizable.tryView(functionLikeEntity.getViewedSystemGraph(), functionLikeEntity.getViewedSystemGraph().getEdgeSource(e)).stream())
                    .forEach(internalVectors::add);
        });
        mapV.kernels().forEach(functionLikeEntity -> {
            functionLikeEntity.getViewedSystemGraph().outgoingEdgesOf(functionLikeEntity)
                    .stream().filter(e -> e.getSourcePort().map(p -> functionLikeEntity.outputPorts().contains(p)).orElse(false))
                    .flatMap(e -> ForSyDeHierarchy.Vectorizable.tryView(functionLikeEntity.getViewedSystemGraph(), functionLikeEntity.getViewedSystemGraph().getEdgeTarget(e)).stream())
                    .forEach(internalVectors::add);
        });
        var m = mapV.kernels().stream().map(this::propagate).reduce(new HashMap<>(), this::merge);
        var sigs = internalVectors.stream().map(this::propagate).reduce(new HashMap<>(), this::merge);
        // multiply all entries
        ins.maxSizeInBits(merge(m, sigs));
        return ins.maxSizeInBits();
    }

    private Map<String, Long> propagate(ReduceV reduceV) {
        var ins = ForSyDeHierarchy.InstrumentedSoftwareBehaviour.enforce(reduceV);
        var internalVectors = new HashSet<Vectorizable>();
        // get size of input array
        reduceV.kernels().forEach(functionLikeEntity -> {
            functionLikeEntity.getViewedSystemGraph().incomingEdgesOf(functionLikeEntity)
                    .stream().filter(e -> e.getTargetPort().map(p -> functionLikeEntity.inputPorts().contains(p)).orElse(false))
                    .flatMap(e -> ForSyDeHierarchy.Vectorizable.tryView(functionLikeEntity.getViewedSystemGraph(), functionLikeEntity.getViewedSystemGraph().getEdgeSource(e)).stream())
                    .forEach(internalVectors::add);
        });
        reduceV.kernels().forEach(functionLikeEntity -> {
            functionLikeEntity.getViewedSystemGraph().outgoingEdgesOf(functionLikeEntity)
                    .stream().filter(e -> e.getSourcePort().map(p -> functionLikeEntity.outputPorts().contains(p)).orElse(false))
                    .flatMap(e -> ForSyDeHierarchy.Vectorizable.tryView(functionLikeEntity.getViewedSystemGraph(), functionLikeEntity.getViewedSystemGraph().getEdgeTarget(e)).stream())
                    .forEach(internalVectors::add);
        });
        var m = reduceV.kernels().stream().map(this::propagate).reduce(new HashMap<>(), this::merge);
        var sigs = internalVectors.stream().map(this::propagate).reduce(new HashMap<>(), this::merge);
        // multiply all entries
        ins.maxSizeInBits(merge(m, sigs));
        return ins.maxSizeInBits();
    }

    private Map<String, Long> propagate(SYSignal sySignal) {
        var ins = ForSyDeHierarchy.InstrumentedDataType.enforce(sySignal);
        var m = sySignal.dataType().map(this::propagate).orElse(new HashMap<>());
        ins.maxSizeInBits(m);
        return m;
    }

    private Map<String, Long> propagate(SDFChannel sdfChannel) {
        var ins = ForSyDeHierarchy.InstrumentedDataType.enforce(sdfChannel);
        var m = sdfChannel.tokenDataType().map(this::propagate).orElse(new HashMap<>());
        ins.maxSizeInBits(m);
        return m;
    }

    private Map<String, Long> propagate(DataTypeLike dataTypeLike) {
        return ForSyDeHierarchy.InstrumentedDataType.tryView(dataTypeLike).map(InstrumentedDataType::maxSizeInBits)
                .orElseGet(() ->
                        ForSyDeHierarchy.Vectorizable.tryView(dataTypeLike).map(this::propagate).orElseGet(HashMap::new)
                );
    }

    private Map<String, Long> propagate(Vectorizable vectorizable) {
        var multiplier = vectorizable.dimensions().stream().mapToInt(i -> i).reduce((a, b) -> a * b).orElse(1);
        var m = vectorizable.arrayItemType().map(this::propagate).orElse(new HashMap<>());
        m.replaceAll((k, v) -> v * multiplier);
        var ins = ForSyDeHierarchy.InstrumentedDataType.enforce(vectorizable);
        ins.maxSizeInBits(m);
        return m;
    }

    private Map<String, Long> merge(Map<String, Long> toMergeLeft, Map<String, Long> toMergeRight) {
        var merged = new HashMap<String, Long>();
        for (var k : toMergeLeft.keySet()) {
            if (toMergeRight.containsKey(k)) {
                merged.put(k, toMergeLeft.get(k) + toMergeRight.get(k));
            } else {
                merged.put(k, toMergeLeft.get(k));
            }
        }
        for (var k : toMergeRight.keySet()) {
            if (!toMergeLeft.containsKey(k)) {
                merged.put(k, toMergeRight.get(k));
            }
        }
        return merged;
    }

    @Override
    public String getName() {
        return "ComputationalRequirementsPropagator";
    }
}
