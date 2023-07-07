package forsyde.io.lib.platform.hardware;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

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
