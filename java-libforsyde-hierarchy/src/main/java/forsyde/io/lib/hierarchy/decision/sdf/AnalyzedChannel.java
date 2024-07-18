package forsyde.io.lib.hierarchy.decision.sdf;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;
import forsyde.io.lib.hierarchy.behavior.moc.sdf.SDFChannel;

/**
 * This trait imbues the SDF channel trait with additional information acquired after mapping
 * the SDF channel to platform resources, such as timing information.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface AnalyzedChannel extends SDFChannel {

    /**
     * This property captures the amount of time in seconds required for a message
     * from the _producer_ to be ready for the _consumer_.
     * <p>
     * That is, it is the time taken as:
     * <p>
     * <code>
     *   token_size_in_bit * interconnect_time_per_bit * producer_token_rate
     * </code>
     */
    @Property
    double incomingMessageTimeInSecs();
}
