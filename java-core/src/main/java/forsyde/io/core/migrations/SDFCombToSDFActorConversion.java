package forsyde.io.core.migrations;

import forsyde.io.core.SystemGraph;

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
