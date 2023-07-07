package forsyde.io.lib.platform.hardware;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

import java.util.Map;
import java.util.stream.Collectors;

@RegisterTrait(IForSyDeHierarchy.class)
public interface InstrumentedGenericProcessingModule extends GenericProcessingModule {

    @Property
    Map<String, Map<String, Integer>> modalInstructionsPerCycleNumerators();

    @Property
    default Map<String, Map<String, Integer>> modalInstructionsPerCycleDenominators() {
        return modalInstructionsPerCycleNumerators().entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> e.getValue().entrySet().stream().collect(Collectors.toMap(
                        Map.Entry::getKey,
                        ee -> 1
                ))
        ));
    };
}
