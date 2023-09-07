package forsyde.io.lib.hierarchy.constraints;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;
import forsyde.io.lib.hierarchy.platform.hardware.GenericProcessingModule;

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
