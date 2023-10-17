package forsyde.io.lib.hierarchy.decision;


import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;
import forsyde.io.lib.hierarchy.behavior.BehaviourEntity;

/**
 * This trait attaches analysis and optimisation data to a behavior entity.
 * It contains information like the behaviour's throughput.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface AnalyzedBehavior extends BehaviourEntity {

    @Property
    Long setThroughputInSecsNumerator();

    @Property
    Long setThroughputInSecsDenominator();


}
