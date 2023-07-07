package forsyde.io.lib.platform.hardware;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

@RegisterTrait(IForSyDeHierarchy.class)
public interface DigitalModule extends HardwareModule {

    @Property
    default long operatingFrequencyInHertz() {return 1L;};


}
