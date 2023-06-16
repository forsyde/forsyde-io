package forsyde.io.core.validation;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.Trait;
import forsyde.io.core.Vertex;

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
