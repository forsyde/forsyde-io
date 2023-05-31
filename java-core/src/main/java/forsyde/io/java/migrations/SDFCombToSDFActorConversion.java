package forsyde.io.java.migrations;

import forsyde.io.java.core.SystemGraph;
import forsyde.io.java.core.Vertex;

public class SDFCombToSDFActorConversion implements SystemGraphMigrator {

    @Override
    public boolean effect(SystemGraph systemGraph) {
//        for (Vertex v : systemGraph.vertexSet()) {
//            if (v.hasTrait("moc::sdf::SDFComb")) {
//                v.addTraits("moc::sdf::SDFActor");
//            }
//        }
        return true;
    }

    @Override
    public String getName() {
        return "SDFCombToSDFActorConversion";
    }
}
