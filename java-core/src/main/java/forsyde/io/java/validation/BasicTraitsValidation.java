package forsyde.io.java.validation;

import forsyde.io.java.core.SystemGraph;
import forsyde.io.java.core.Trait;
import forsyde.io.java.core.Vertex;

import java.util.Optional;

public class BasicTraitsValidation implements SystemGraphValidation {
    @Override
    public Optional<String> validate(SystemGraph systemGraph) {
        for (Vertex v: systemGraph.vertexSet()) {
            for (Trait trait : v.getTraits()) {

            }
        }
        return Optional.empty();
    }
}
