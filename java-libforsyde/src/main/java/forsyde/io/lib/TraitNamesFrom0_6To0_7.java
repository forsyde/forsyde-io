package forsyde.io.lib;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.Trait;
import forsyde.io.core.migrations.SystemGraphMigrator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class TraitNamesFrom0_6To0_7 implements SystemGraphMigrator {
    @Override
    public boolean effect(SystemGraph systemGraph) {
        for (var v : systemGraph.vertexSet()) {
            var prevTraits = new HashSet<Trait>(v.getTraits());
            for (var vt : prevTraits) {
                for (var t : ForSyDeHierarchy.containedTraits) {
                    if (!vt.getName().equals(t.getName()) && t.getName().contains(vt.getName())) {
                        v.addTraits(t);
                    }
                }
                if (vt.getName().contains("DataBlock")) {
                    var reg = ForSyDeHierarchy.RegisterLike.enforce(systemGraph, v);
                    reg.sizeInBits((Long) v.getProperty("maxSizeInBits"));
                }
                if (vt.getName().contains("TokenizableDataBlock")) {
                    var buf = ForSyDeHierarchy.RegisterArrayLike.enforce(systemGraph, v);
                    buf.elementSizeInBits((Long) v.getProperty("tokenSizeInBits"));
                }
                if (vt.getName().contains("ANSICBlackBoxExecutable")) {
                    ForSyDeHierarchy.HasANSICImplementation.enforce(systemGraph, v);
                }
                if (vt.getName().contains("Executable")) {
                    ForSyDeHierarchy.BehaviourEntity.enforce(systemGraph, v);
                }
                if (vt.getName().contains("InstrumentedExecutable")) {
                    var op = ForSyDeHierarchy.InstrumentedBehaviour.enforce(systemGraph, v);
                    op.computationalRequirements((Map<String, Map<String, Long>>) v.getProperty("operationRequirements"));
                    var m = new HashMap<String, Long>();
                    m.put("default", (Long) v.getProperty("sizeInBits"));
                    op.maxSizeInBits(m);
                }
                if (vt.getName().contains("AbstractScheduler")) {
                    ForSyDeHierarchy.AbstractRuntime.enforce(systemGraph, v);
                }
                if (vt.getName().contains("FixedPriorityScheduler")) {
                    var s = ForSyDeHierarchy.FixedPriorityScheduledRuntime.enforce(systemGraph, v);
                    s.supportsPreemption((Boolean) v.getProperty("preemptive"));
                }
                if (vt.getName().contains("StaticCyclicScheduler")) {
                    ForSyDeHierarchy.SuperLoopRuntime.enforce(systemGraph, v);
                }
                if (vt.getName().contains("RoundRobinScheduler")) {
                    var s = ForSyDeHierarchy.TimeDivisionMultiplexingRuntime.enforce(systemGraph, v);
                    s.maximumTimeSliceInClockCycles((Long) v.getProperty("maximumTimeSliceInCycles"));
                    s.minimumTimeSliceInClockCycles((Long) v.getProperty("minimumTimeSliceInCycles"));
                    s.frameSizeInClockCycles(s.maximumTimeSliceInClockCycles() * ((Long) v.getProperty("maximumTimeSlices")));
                }
            }
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
