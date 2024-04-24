package forsyde.io.lib;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.migrations.SystemGraphMigrator;
import forsyde.io.lib.hierarchy.ForSyDeHierarchy;

public class InstrumentationRenamingMigration implements SystemGraphMigrator {
    @Override
    @SuppressWarnings("deprecation")
    public boolean effect(SystemGraph systemGraph) {
        for (var v :
                systemGraph.vertexSet()) {
            ForSyDeHierarchy.InstrumentedBehaviour.tryView(systemGraph, v).ifPresent(instrumentedBehaviourViewer -> {
              var newIn = ForSyDeHierarchy.InstrumentedSoftwareBehaviour.enforce(instrumentedBehaviourViewer);
            });
        }
        return true;
    }

}
