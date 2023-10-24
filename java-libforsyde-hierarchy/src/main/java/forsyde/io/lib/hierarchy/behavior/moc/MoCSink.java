package forsyde.io.lib.hierarchy.behavior.moc;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;
import forsyde.io.lib.hierarchy.behavior.DataTypeLike;

/**
 * A boundary signal between a MoC process network and anything outside this network.
 *
 * <p>
 *     All MoCs can use this sink trait since all MoCs can be compared in the light of a discrete-event model,
 *     or, the tagged-signal model (see the reference of <code>MoCEntity</code>).
 *     The key element of this trait is the timing beahviour of the events it generates.
 *     That is, their periodicity or lack of any periodicity thereof.
 *     Note that a MoCSink is a data-type-like entity, as it is intended to capture MoC signals that get
 *     "consumed" due to their connection with the outside world.
 * </p>
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface MoCSink extends MoCEntity, DataTypeLike {

    /**
     * The numerator part of the "eventConsumptionRate" property.
     *
     * <p>
     *     The event departure rate defines how fast this MoC source gets consumed in the light of
     *     tagged-signal model, or the discrete-event model, equivalently.
     *     The faster the rate, the faster the data present in this boundary signal is "gone".
     *     This notion can differ along different MoCs.
     *     For example, in the Synchronous MoC, this means that the data available in the signal (<code>SYSignal</code>) is consumed and
     *     <b>should be replenished at all costs</b>. In the SDF MoC, this means that a token is consumed
     *     in the signal and can be replenished anytime as long as the buffer implementing this boundary signal (<code>SDFChannel</code>) does
     *     not underflow.
     * </p>
     */
    @Property
    long eventConsumptionRateNumerator();

    /**
     * The denominator part of the "eventConsumptionRate" property. Defaults to 1.
     * <p>
     *     see <code>eventConsumptionRateNumerator</code> for the full information on this property.
     * </p>
     *
     */
    @Property
    default long eventConsumptionRateDenominator() {
        return 1L;
    };

}
