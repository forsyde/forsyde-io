package forsyde.io.java.migrations;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;

public class SDFCombToSDFActorConversion implements SystemGraphMigrator {

    @Override
    public boolean effect(ForSyDeSystemGraph forSyDeSystemGraph) {
        for (Vertex v : forSyDeSystemGraph.vertexSet()) {
            if (v.hasTrait("moc::sdf::SDFComb")) {
                v.addTraits("moc::sdf::SDFActor");
            }
        }
        return true;
    }

    @Override
    public String getName() {
        return "SDFCombToSDFActorConversion";
    }
}
