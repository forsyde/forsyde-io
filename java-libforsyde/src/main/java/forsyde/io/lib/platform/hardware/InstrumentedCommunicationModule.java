package forsyde.io.lib.platform.hardware;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

/**
 * A InstrumentedGenericCOmmunicationModule enriches `GenericCommunicationModule` with provision numbers so that
 * an analysis and synthesis tool is able to estimate the total amount of traversal time is required to transmit
 * a bunch of data through this communication element.
 *
 * You can assume that analysis and synthesis tools will compute the traversal time by:
 *
 *  traversalTime(dataSize) = initialLatency
 *                  + ceil(dataSize / flitSizeInBits) * (maxCyclesPerFlit / maxConcurrentFlits) / elementFrequency
 *
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface InstrumentedCommunicationModule extends GenericCommunicationModule{
    @Property
    Long flitSizeInBits();
    @Property
    Integer maxCyclesPerFlit();
    @Property
    Integer maxConcurrentFlits();
    @Property
    Long initialLatency();
}
