package forsyde.io.lib.hierarchy.implementation.functional;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;
import forsyde.io.lib.hierarchy.behavior.BehaviourEntity;

import java.util.Map;

@RegisterTrait(IForSyDeHierarchy.class)
public interface InstrumentedBehaviour extends BehaviourEntity {

    @Property
    Map<String, Map<String, Long>> computationalRequirements();

    @Property
    Map<String, Long> maxSizeInBits();
}
