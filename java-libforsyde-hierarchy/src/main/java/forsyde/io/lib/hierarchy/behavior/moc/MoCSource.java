package forsyde.io.lib.hierarchy.behavior.moc;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;
import forsyde.io.lib.hierarchy.behavior.DataTypeLike;

/**
 * A boundary signal between a MoC process network and anything outside this network.
 *
 * <p>
 *     All MoCs can use this source trait since all MoCs can be compared in the light of a discrete-event model,
 *     or, the tagged-signal model (see the reference of <code>MoCEntity</code>).
 *     The key element of this trait is the timing beahviour of the events it generates.
 *     That is, their periodicity or lack of any periodicity thereof.
 *     Note that a MoCSource is a data-type-like entity, as it is intended to capture MoC signals that get
 *     "replenished" due to their connection with the outside world.
 * </p>
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface MoCSource extends MoCEntity, DataTypeLike {

    /**
     * The numerator part of the "eventProductionRate" property.
     *
     * <p>
     *     The event inter-arrival rate defines how fast this MoC source gets replenished in the light of
     *     tagged-signal model, or the discrete-event model, equivalently.
     *     The faster the rate, the faster the data present in this boundary signal is "fresh".
     *     This notion can differ along different MoCs.
     *     For example, in the Synchronous MoC, this means that a new data is available in the signal (<code>SYSignal</code>) and
     *     <b>should be consumed at all costs</b>. In the SDF MoC, this means that a new token is available
     *     in the signal and can be consumed anytime as long as the buffer implementing this boundary signal (<code>SDFChannel</code>) does
     *     not overflow.
     * </p>
     */
    @Property
    long eventProductionRateNumerator();

    /**
     * The denominator part of the "eventProductionRate" property. Defaults to 1.
     * <p>
     *     see <code>eventProductionRateNumerator</code> for the full information on this property.
     * </p>
     *
     */
    @Property
    default long eventProductionRateDenominator() {
        return 1L;
    };

}
