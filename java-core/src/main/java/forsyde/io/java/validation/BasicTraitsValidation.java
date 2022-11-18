package forsyde.io.java.validation;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Trait;
import forsyde.io.java.core.Vertex;

import java.util.Optional;

public class BasicTraitsValidation implements SystemGraphValidation {
    @Override
    public Optional<String> validate(ForSyDeSystemGraph forSyDeSystemGraph) {
        for (Vertex v: forSyDeSystemGraph.vertexSet()) {
            for (Trait trait : v.getTraits()) {

            }
        }
        return Optional.empty();
    }
}
