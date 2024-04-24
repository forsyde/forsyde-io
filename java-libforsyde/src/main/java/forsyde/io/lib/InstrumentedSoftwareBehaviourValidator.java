package forsyde.io.lib;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.validation.SystemGraphValidation;
import forsyde.io.lib.hierarchy.ForSyDeHierarchy;

import java.util.Optional;

public class InstrumentedSoftwareBehaviourValidator implements SystemGraphValidation {
    @Override
    public Optional<String> validate(SystemGraph systemGraph) {
        for (var v :
                systemGraph.vertexSet()) {
            return ForSyDeHierarchy.InstrumentedSoftwareBehaviour.tryView(systemGraph, v).flatMap(instrumentedSoftwareBehaviourViewer -> {
                if (!instrumentedSoftwareBehaviourViewer.computationalRequirements().keySet().equals(instrumentedSoftwareBehaviourViewer.maxSizeInBits().keySet())) {
                    return Optional.of("The vertex '%s' declares InstrumentedSoftwareBehaviour but computationalRequirements and maxSizeInBits  do not have matching implementation names.".formatted(instrumentedSoftwareBehaviourViewer.getIdentifier()));
                } else {
                    return Optional.empty();
                }
            });
        }
        return Optional.empty();
    }
}
