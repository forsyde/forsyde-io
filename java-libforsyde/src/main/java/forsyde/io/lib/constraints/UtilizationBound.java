package forsyde.io.lib.constraints;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;
import forsyde.io.lib.platform.hardware.GenericProcessingModule;

/**
 * This trait describe an utilization constraint for a GenericProcessingModule,
 * where the sum of all things using it, including any runtime, must not exceed a certain
 * percentage.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface UtilizationBound extends GenericProcessingModule {

    @Property
    default Double maxUtilization() {return 1.0;}
}
