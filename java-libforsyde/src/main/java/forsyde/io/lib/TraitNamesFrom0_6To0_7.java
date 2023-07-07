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
            for (var t : ForSyDeHierarchy.containedTraits) {
                for (var vt : v.getTraits()) {
                    if (t.getName().contains(vt.getName())) {
                        traitToAdd.add(t);
                    }
                }
            }
            v.addTraits(traitToAdd);
        }
        for (var e : systemGraph.edgeSet()) {
            var traitToAdd = new HashSet<Trait>();
            for (var t : ForSyDeHierarchy.containedTraits) {
                for (var et : e.getTraits()) {
                    if (t.getName().contains(et.getName())) {
                        traitToAdd.add(t);
                    }
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
