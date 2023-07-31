package forsyde.io.core.validation;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.Trait;
import forsyde.io.core.Vertex;
import forsyde.io.core.VertexTrait;

import java.util.Optional;

public class BasicTraitsValidation implements SystemGraphValidation {
    @Override
    public Optional<String> validate(SystemGraph systemGraph) {
        for (Vertex v: systemGraph.vertexSet()) {
            for (Trait trait : v.getTraits()) {
                if (trait instanceof VertexTrait vertexTrait) {
                    for (String port: vertexTrait.requiredPorts()) {
                        if (!v.getPorts().contains(port)) {
                            return Optional.of(new StringBuilder()
                                            .append("Vertex ")
                                            .append(v.getIdentifier())
                                            .append(" is missing port ")
                                            .append(port)
                                            .append(" due to trait ")
                                            .append(trait.getName())
                                    .toString());
                        }
                    }
                    for (String prop: vertexTrait.requiredProperties()) {
                        return Optional.of(new StringBuilder()
                                .append("Vertex ")
                                .append(v.getIdentifier())
                                .append(" is missing property ")
                                .append(prop)
                                .append(" due to trait ")
                                .append(trait.getName())
                                .toString());
                    }
                }
            }
        }
        return Optional.empty();
    }
}
