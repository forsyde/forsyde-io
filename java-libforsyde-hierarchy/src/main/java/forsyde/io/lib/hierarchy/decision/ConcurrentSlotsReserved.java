package forsyde.io.lib.hierarchy.decision;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.platform.hardware.GenericCommunicationModule;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

import java.util.List;

/**
 * This trait is intended to describe communication reservation for communication modules
 * that allow slot reservations. A key example would be a TDM bus, where any data incoming from the
 * same processing element can share the same TDM slot.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface ConcurrentSlotsReserved extends GenericCommunicationModule {

    @Property
    List<List<String>> slotReservations();
}
