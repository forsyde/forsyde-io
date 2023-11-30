package forsyde.io.lib;

import forsyde.io.core.EdgeTrait;
import forsyde.io.core.OpaqueTrait;
import forsyde.io.core.SystemGraph;
import forsyde.io.core.Trait;
import forsyde.io.core.migrations.SystemGraphMigrator;
import forsyde.io.lib.hierarchy.ForSyDeHierarchy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This migrator is a best-effort migration from all LibForSyDe traits existing pre 0.7
 * to all traits existing post 0.7.
 */
public class TraitNamesFrom0_6To0_7 implements SystemGraphMigrator {
    @Override
    public boolean effect(SystemGraph systemGraph) {
        for (var e : systemGraph.edgeSet()) {
            var prevTraits = new HashSet<Trait>(e.getTraits());
            for (var et : prevTraits) {
                for (var t : ForSyDeHierarchy.containedTraits) {
                    if (et instanceof OpaqueTrait opaqueTrait && t.getName().contains(opaqueTrait.getName())) {
                        e.addTraits(t);
                    }
                }
                // mapping for the hardware parts of the platform
                if (et.getName().contains("platform::")) {
                    for (var t : ForSyDeHierarchy.containedTraits) {
                        if (t.getName().contains("platform::hardware::")) {
                            var splitVt = et.getName().split("::");
                            var splitT = t.getName().split("::");
                            var traitNameVt = splitVt[splitVt.length -1];
                            var traitNameT = splitT[splitT.length - 1];
                            if (traitNameVt.equals(traitNameT)) {
                                e.addTraits(t); // this assumes they are 1-to-1 mapping
                            }
                        }
                    }
                }
                // also change edge traits named 'DataEdge' to 'NetworkEdge' and other specialties
                if (et.getName().contains("SYDataEdge")) {
                    e.addTraits(ForSyDeHierarchy.EdgeTraits.SYNetworkEdge);
                }
                if (et.getName().contains("SDFDataEdge")) {
                    e.addTraits(ForSyDeHierarchy.EdgeTraits.SDFNetworkEdge);
                }
                if (et.getName().contains("AbstractionEdge")) {
                    e.addTraits(ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge);
                }
                if (et.getName().contains("ParallelContainer")) {
                    e.addTraits(ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge);
                }
            }
        }
        for (var v : systemGraph.vertexSet()) {
            var prevTraits = new HashSet<Trait>(v.getTraits());
            for (var vt : prevTraits) {
                for (var t : ForSyDeHierarchy.containedTraits) {
                    if (t.getName().endsWith(vt.getName())) {
                        v.addTraits(t);
                    }
                }
                // mapping for the hardware parts of the platform
                if (vt.getName().startsWith("platform::")) {
                    for (var t : ForSyDeHierarchy.containedTraits) {
                        if (t.getName().contains("platform::hardware::")) {
                            var splitVt = vt.getName().split("::");
                            var splitT = t.getName().split("::");
                            var traitNameVt = splitVt[splitVt.length -1];
                            var traitNameT = splitT[splitT.length - 1];
                            if (traitNameVt.equals(traitNameT)) {
                                v.addTraits(t); // this assumes they are 1-to-1 mapping
                            }
                        }
                    }
                }
                if (vt.getName().endsWith("DataBlock")) {
                    var reg = ForSyDeHierarchy.RegisterLike.enforce(systemGraph, v);
                    var prop = v.getProperty("maxSizeInBits");
                    if (prop instanceof Long l) {
                        reg.sizeInBits(l);
                    } else if (prop instanceof Map<?, ?> map && !map.isEmpty()) {
                        var maxL = -1L;
                        for (var mapVal : map.values()) {
                            if (mapVal instanceof Long mapL) {
                                maxL = Math.max(mapL, maxL);
                            }
                        }
                        if (maxL > -1L) {
                            reg.sizeInBits(maxL);
                        }
                    }
                }
                if (vt.getName().endsWith("TokenizableDataBlock")) {
                    var buf = ForSyDeHierarchy.RegisterArrayLike.enforce(systemGraph, v);
                    buf.elementSizeInBits((Long) v.getProperty("tokenSizeInBits"));
                }
                if (!vt.refines(ForSyDeHierarchy.VertexTraits.HasANSICImplementations) && (vt.getName().endsWith("ANSICBlackBoxExecutable") || vt.getName().endsWith("HasANSICImplementation"))) {
                    var impl = ForSyDeHierarchy.HasANSICImplementations.enforce(systemGraph, v);
                    impl.inlinedCodes().put("generic", (String) v.getProperty("inlinedCode"));
                    var f = ForSyDeHierarchy.FunctionLikeEntity.enforce(systemGraph, v);
                    f.inputPorts((List<String>) v.getProperty("inputArgumentPorts"));
                    var outputs = new ArrayList<String>();
                    outputs.addAll((List<String>) v.getProperty("outputArgumentPorts"));
                    outputs.add((String) v.getProperty("returnPort"));
                    f.outputPorts(outputs);
                }
                if (vt.getName().endsWith("Executable")) {
                    var f = ForSyDeHierarchy.FunctionLikeEntity.enforce(systemGraph, v);
                }
                if (vt.getName().endsWith("InstrumentedExecutable")) {
                    var op = ForSyDeHierarchy.InstrumentedBehaviour.enforce(systemGraph, v);
                    op.computationalRequirements((Map<String, Map<String, Long>>) v.getProperty("operationRequirements"));
                    var m = new HashMap<String, Long>();
                    var s = v.getProperty("sizeInBits");
                    if (s instanceof Integer) {
                        m.put("default", ((Integer) s).longValue());
                    } else {
                        m.put("default", (Long) s);
                    }
                    op.maxSizeInBits(m);
                }
                if (vt.getName().endsWith("AbstractScheduler")) {
                    ForSyDeHierarchy.AbstractRuntime.enforce(systemGraph, v);
                }
                if (vt.getName().endsWith("FixedPriorityScheduler")) {
                    var s = ForSyDeHierarchy.FixedPriorityScheduledRuntime.enforce(systemGraph, v);
                    if (v.hasProperty("preemption") && v.getProperty("preemption") instanceof Boolean b) {
                        s.supportsPreemption(b);
                    }
                }
                if (vt.getName().endsWith("StaticCyclicScheduler")) {
                    ForSyDeHierarchy.SuperLoopRuntime.enforce(systemGraph, v);
                }
                if (vt.getName().endsWith("RoundRobinScheduler")) {
                    var s = ForSyDeHierarchy.TimeDivisionMultiplexingRuntime.enforce(systemGraph, v);
                    if (v.hasProperty("maximumTimeSliceInCycles") && v.getProperty("maximumTimeSliceInCycles") instanceof Long l) {
                        s.maximumTimeSliceInClockCycles(l);
                    }
                    if (v.hasProperty("minimumTimeSliceInCycles") && v.getProperty("minimumTimeSliceInCycles") instanceof Long l) {
                        s.minimumTimeSliceInClockCycles(l);
                    }
                    s.frameSizeInClockCycles(s.maximumTimeSliceInClockCycles() * ((Integer) v.getProperty("maximumTimeSlices")));
                }
                if (vt.getName().endsWith("PeriodicStimulus")) {
                    ForSyDeHierarchy.PeriodicStimulator.enforce(systemGraph, v);
                }
                if (vt.getName().endsWith("SYSignal")) {
                    ForSyDeHierarchy.SYSignal.enforce(systemGraph, v);
                }
                if (vt.getName().endsWith("SDFChannel")) {
                    var c = ForSyDeHierarchy.SDFChannel.enforce(systemGraph, v);
                    if (c.numInitialTokens() == 0 && v.hasProperty("numOfInitialTokens") && v.getProperty("numOfInitialTokens") != c.numInitialTokens()) {
                        c.numInitialTokens((Integer) v.getProperty("numOfInitialTokens"));
                    }
                }
            }
        }
        // last post-mapping transformations
        for (var v : systemGraph.vertexSet()) {
            ForSyDeHierarchy.AbstractRuntime.tryView(systemGraph, v).ifPresent(runtime -> {
                var dstPEs = systemGraph.outgoingEdgesOf(v).stream().map(systemGraph::getEdgeTarget).flatMap(dst -> ForSyDeHierarchy.GenericProcessingModule.tryView(systemGraph, dst).stream()).collect(Collectors.toSet());
                for (var pe : dstPEs) {
                    runtime.host(pe);
                    runtime.addManaged(pe);
                }
            });
            ForSyDeHierarchy.SYSignal.tryView(systemGraph, v).ifPresent(sig -> {
                for (var e : Set.copyOf(systemGraph.incomingEdgesOf(v))) {
                    if (e.connectsTargetPort("input")) {
                        ForSyDeHierarchy.SYProcess.tryView(systemGraph, systemGraph.getEdgeSource(e)).ifPresent(src -> {
                            e.getSourcePort().ifPresentOrElse(port -> {
                                sig.producer(port, src);
                                for (var t : e.getTraits()) {
                                    if (t instanceof EdgeTrait edgeTrait) {
                                        sig.producer(port, src, edgeTrait);
                                    }
                                }
                            }, () -> {
                                sig.producer(src);
                                for (var t : e.getTraits()) {
                                    if (t instanceof EdgeTrait edgeTrait) {
                                        sig.producer(src, edgeTrait);
                                    }
                                }
                            });
                        });
                    }
                }
                for (var e : Set.copyOf(systemGraph.outgoingEdgesOf(v))) {
                    if (e.connectsSourcePort("output")) {
                        ForSyDeHierarchy.SYProcess.tryView(systemGraph, systemGraph.getEdgeTarget(e)).ifPresent(dst -> {
                            e.getTargetPort().ifPresentOrElse(port -> {
                                sig.addConsumers(port, dst);
                                for (var t : e.getTraits()) {
                                    if (t instanceof EdgeTrait edgeTrait) {
                                        sig.addConsumers(port, dst, edgeTrait);
                                    }
                                }
                            }, () -> {
                                sig.addConsumers(dst);
                                for (var t : e.getTraits()) {
                                    if (t instanceof EdgeTrait edgeTrait) {
                                        sig.addConsumers(dst, edgeTrait);
                                    }
                                }
                            });
                        });
                    }
                }
            });
            ForSyDeHierarchy.Vectorizable.tryView(systemGraph, v).ifPresent(vectorizableViewer -> {
                systemGraph.outgoingEdgesOf(vectorizableViewer).stream().filter(e -> e.connectsSourcePort("arrayItemType")).forEach(e -> e.addTraits(ForSyDeHierarchy.EdgeTraits.DataLikeCompositionEdge));
            });
        }
        return true;
    }

    @Override
    public String getName() {
        return "TraitNamesFrom0_6To0_7";
    }
}
