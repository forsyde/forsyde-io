package forsyde.io.lib;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.validation.SystemGraphValidation;
import forsyde.io.lib.hierarchy.ForSyDeHierarchy;

import java.util.Optional;

public class InstrumentedHardwareBehaviourValidator implements SystemGraphValidation {
    @Override
    public Optional<String> validate(SystemGraph systemGraph) {
        for (var v :
                systemGraph.vertexSet()) {
            return ForSyDeHierarchy.InstrumentedHardwareBehaviour.tryView(systemGraph, v).flatMap(instrumentedHardwareBehaviourViewer -> {
                if (
                        !instrumentedHardwareBehaviourViewer.resourceRequirements().keySet().equals(instrumentedHardwareBehaviourViewer.latencyInSecsNumerators().keySet()) ||
                            !instrumentedHardwareBehaviourViewer.latencyInSecsNumerators().keySet().equals(instrumentedHardwareBehaviourViewer.latencyInSecsDenominators().keySet()) ||
                            !instrumentedHardwareBehaviourViewer.latencyInSecsDenominators().keySet().equals(instrumentedHardwareBehaviourViewer.energyPerExecutionInJoules().keySet())
                ) {
                    return Optional.of("The vertex '%s' declares InstrumentedHardwareBehaviour but does not have matching implementation names for its properties.");
                } else {
                    return Optional.empty();
                }
            });
        }
        return Optional.empty();
    }
}
