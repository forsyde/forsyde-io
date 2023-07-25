package forsyde.io.lib;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.Trait;
import forsyde.io.core.migrations.SystemGraphMigrator;

import java.util.HashSet;
import java.util.Map;

public class TraitNamesFrom0_6To0_7 implements SystemGraphMigrator {
    @Override
    public boolean effect(SystemGraph systemGraph) {
        for (var v : systemGraph.vertexSet()) {
            var traitToAdd = new HashSet<Trait>();
            for (var vt : v.getTraits()) {
                for (var t : ForSyDeHierarchy.containedTraits) {
                    if (t.getName().contains(vt.getName())) {
                        traitToAdd.add(t);
                    }
                }
                if (vt.getName().contains("DataBlock")) {
                    traitToAdd.add(ForSyDeHierarchy.VertexTraits.RegisterLike);
                }
                if (vt.getName().contains("TokenizableDataBlock")) {
                    traitToAdd.add(ForSyDeHierarchy.VertexTraits.RegisterArrayLike);
                }
                if (vt.getName().contains("ANSICBlackBoxExecutable")) {
                    traitToAdd.add(ForSyDeHierarchy.VertexTraits.HasANSICImplementation);
                }
                if (vt.getName().contains("Executable")) {
                    traitToAdd.add(ForSyDeHierarchy.VertexTraits.BehaviourEntity);
                }
                if (vt.getName().contains("InstrumentedExecutable")) {
                    traitToAdd.add(ForSyDeHierarchy.VertexTraits.InstrumentedBehaviour);
                }
                if (vt.getName().contains("AbstractScheduler")) {
                    traitToAdd.add(ForSyDeHierarchy.VertexTraits.AbstractRuntime);
                }
                if (vt.getName().contains("FixedPriorityScheduler")) {
                    traitToAdd.add(ForSyDeHierarchy.VertexTraits.FixedPriorityScheduledRuntime);
                }
                if (vt.getName().contains("StaticCyclicScheduler")) {
                    traitToAdd.add(ForSyDeHierarchy.VertexTraits.SuperLoopRuntime);
                }
                if (vt.getName().contains("RoundRobinScheduler")) {
                    traitToAdd.add(ForSyDeHierarchy.VertexTraits.TimeDivisionMultiplexingRuntime);
                }
            }
            if (traitToAdd.contains(ForSyDeHierarchy.VertexTraits.RegisterLike)) {
                var reg = ForSyDeHierarchy.RegisterLike.enforce(systemGraph, v);
                reg.sizeInBits((Long) v.getProperty("maxSizeInBits"));
            }
            if (traitToAdd.contains(ForSyDeHierarchy.VertexTraits.RegisterArrayLike)) {
                var buf = ForSyDeHierarchy.RegisterArrayLike.enforce(systemGraph, v);
                buf.elementSizeInBits((Long) v.getProperty("tokenSizeInBits"));
            }
            if (traitToAdd.contains(ForSyDeHierarchy.VertexTraits.InstrumentedBehaviour)) {
                var op = ForSyDeHierarchy.InstrumentedBehaviour.enforce(systemGraph, v);
                op.computationalRequirements((Map<String, Map<String, Long>>) v.getProperty("operationRequirements"));
                op.maxSizeInBits(Map.of("default", (Long) v.getProperty("sizeInBits")));
            }
            if (traitToAdd.contains(ForSyDeHierarchy.VertexTraits.AbstractRuntime)) {
                ForSyDeHierarchy.AbstractRuntime.enforce(systemGraph, v);
            }
            if (traitToAdd.contains(ForSyDeHierarchy.VertexTraits.FixedPriorityScheduledRuntime)) {
                var s = ForSyDeHierarchy.FixedPriorityScheduledRuntime.enforce(systemGraph, v);
                s.supportsPreemption((Boolean) v.getProperty("preemptive"));
            }
            if (traitToAdd.contains(ForSyDeHierarchy.VertexTraits.SuperLoopRuntime)) {
                ForSyDeHierarchy.SuperLoopRuntime.enforce(systemGraph, v);
            }
            if (traitToAdd.contains(ForSyDeHierarchy.VertexTraits.TimeDivisionMultiplexingRuntime)) {
                var s = ForSyDeHierarchy.TimeDivisionMultiplexingRuntime.enforce(systemGraph, v);
                s.maximumTimeSliceInClockCycles((Long) v.getProperty("maximumTimeSliceInCycles"));
                s.minimumTimeSliceInClockCycles((Long) v.getProperty("minimumTimeSliceInCycles"));
                s.frameSizeInClockCycles(s.maximumTimeSliceInClockCycles() * ((Long) v.getProperty("maximumTimeSlices")));
            }
            v.addTraits(traitToAdd);
        }
        for (var e : systemGraph.edgeSet()) {
            var traitToAdd = new HashSet<Trait>();
            for (var et : e.getTraits()) {
                for (var t : ForSyDeHierarchy.containedTraits) {
                    if (t.getName().contains(et.getName())) {
                        traitToAdd.add(t);
                    }
                }
                // also change edge traits named 'DataEdge' to 'NetworkEdge' and other specialties
                if (et.getName().contains("SYDataEdge")) {
                    traitToAdd.add(ForSyDeHierarchy.EdgeTraits.SYNetworkEdge);
                }
                if (et.getName().contains("SDFDataEdge")) {
                    traitToAdd.add(ForSyDeHierarchy.EdgeTraits.SDFNetworkEdge);
                }
                if (et.getName().contains("AbstractionEdge")) {
                    traitToAdd.add(ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge);
                }
                if (et.getName().contains("ParallelContainer")) {
                    traitToAdd.add(ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge);
                }
            }

            e.addTraits(traitToAdd);
        }
        return true;
    }

    @Override
    public String getName() {
        return "TraitNamesFrom0_6To0_7";
    }
}
