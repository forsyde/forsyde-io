package forsyde.io.lib;

import forsyde.io.core.OpaqueTrait;
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
                    if (vt instanceof OpaqueTrait opaqueTrait && t.getName().contains(opaqueTrait.getName())) {
                        v.addTraits(t);
                    }
                }
                System.out.println(v.getIdentifier());
                // mapping for the hardware parts of the platform
                if (vt.getName().contains("platform::")) {
                    System.out.println("matched");
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
//                if (vt.getName().contains("InstrumentedCommunicationModule")) {
//                    ForSyDeHierarchy.InstrumentedCommunicationModule.enforce(systemGraph, v);
//                }
//                if (vt.getName().contains("GenericCommunicationModule")) {
//                    ForSyDeHierarchy.GenericCommunicationModule.enforce(systemGraph, v);
//                }
//                if (vt.getName().contains("InstrumentedProcessingModule")) {
//                    ForSyDeHierarchy.InstrumentedProcessingModule.enforce(systemGraph, v);
//                }
//                if (vt.getName().contains("GenericProcessingModule")) {
//                    ForSyDeHierarchy.GenericProcessingModule.enforce(systemGraph, v);
//                }
            }
        }
        for (var e : systemGraph.edgeSet()) {
            var traitToAdd = new HashSet<Trait>();
            for (var et : e.getTraits()) {
                for (var t : ForSyDeHierarchy.containedTraits) {
                    if (et instanceof OpaqueTrait opaqueTrait && t.getName().contains(opaqueTrait.getName())) {
                        traitToAdd.add(t);
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
