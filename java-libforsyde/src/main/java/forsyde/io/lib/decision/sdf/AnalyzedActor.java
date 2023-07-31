package forsyde.io.lib.decision.sdf;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;
import forsyde.io.lib.behavior.moc.sdf.SDFActor;

/**
 * This enables the "annotation" of analyzed SDF actors with throughput results
 * from mapping and scheduling techniques.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface AnalyzedActor extends SDFActor {

    @Property
    Long setThroughputInSecsNumerator();

    @Property
    Long setThroughputInSecsDenominator();
}
