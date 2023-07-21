package forsyde.io.lib;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.Trait;
import forsyde.io.core.migrations.SystemGraphMigrator;

import java.util.HashSet;

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
                    traitToAdd.add(ForSyDeHierarchy.VertexTraits.ArrayBufferLike);
                }
                if (vt.getName().contains("TokenizableDataBlock")) {
                    traitToAdd.add(ForSyDeHierarchy.VertexTraits.ArrayBufferLike);
                }
                if (vt.getName().contains("ANSICBlackBoxExecutable")) {
                    traitToAdd.add(ForSyDeHierarchy.VertexTraits.HasANSICImplementation);
                }
                if (vt.getName().contains("InstrumentedExecutable") || vt.getName().contains("Executable")) {
                    traitToAdd.add(ForSyDeHierarchy.VertexTraits.BehaviourEntity);
                }
            }
            if (traitToAdd.contains(ForSyDeHierarchy.VertexTraits.RegisterLike)) {
                var reg = ForSyDeHierarchy.RegisterLike.enforce(systemGraph, v);
                reg.sizeInBits((Long) v.getProperty("maxSizeInBits"));
            }
            if (traitToAdd.contains(ForSyDeHierarchy.VertexTraits.ArrayBufferLike)) {
                var buf = ForSyDeHierarchy.ArrayBufferLike.enforce(systemGraph, v);
                buf.elementSizeInBits((Long) v.getProperty("tokenSizeInBits"));
                var maxElems = ((Long) v.getProperty("maxSizeInBits")) / buf.elementSizeInBits();
                buf.maxElements((int) maxElems);
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
