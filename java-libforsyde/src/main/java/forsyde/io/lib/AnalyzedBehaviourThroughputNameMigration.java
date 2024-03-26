package forsyde.io.lib;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.migrations.SystemGraphMigrator;
import forsyde.io.lib.hierarchy.ForSyDeHierarchy;

public class AnalyzedBehaviourThroughputNameMigration implements SystemGraphMigrator {
    @Override
    public boolean effect(SystemGraph systemGraph) {
        for (var v : systemGraph.vertexSet()) {
            ForSyDeHierarchy.AnalyzedBehavior.tryView(systemGraph, v).ifPresent(analyzedBehaviorViewer -> {
                if (!v.hasProperty("throughputInSecsNumerator") && v.hasProperty("setThroughputInSecsNumerator") && v.getProperty("setThroughputInSecsNumerator") instanceof Long thNum) {
                    analyzedBehaviorViewer.throughputInSecsNumerator(thNum);
                }
                if (!v.hasProperty("throughputInSecsDenominator") && v.hasProperty("setThroughputInSecsDenominator") && v.getProperty("setThroughputInSecsDenominator") instanceof Long thDen) {
                    analyzedBehaviorViewer.throughputInSecsDenominator(thDen);
                }
            });
        }
        return false;
    }
}
