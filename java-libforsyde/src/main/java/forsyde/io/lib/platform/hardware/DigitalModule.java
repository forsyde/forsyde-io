package forsyde.io.lib.platform.hardware;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;


/**
 * This trait represents a digital hardware element in the platform.
 * As such, it requires the presence of an operating frequency property.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface DigitalModule extends HardwareModule {

    @Property
    default long operatingFrequencyInHertz() {return 1L;};


}
