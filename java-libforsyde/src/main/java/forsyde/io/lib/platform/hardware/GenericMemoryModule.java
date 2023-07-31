package forsyde.io.lib.platform.hardware;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

/**
 * A GenericMemoryModule represents any digital hardware module capable of "storing data".
 * Typical real things fitting to this trait abstraction are on-chip memories, DRAMs, SDRAMs etc.
 * If the element can be abstracted into a black-box that "stores data", this trait captures it.
 *
 * This trait _does not_ imply any arbitration scheme for parallel accesses to it.
 * Therefore, if there are two elements connecting to this memory module, one should put the memory module
 * behind a `GenericCommunicationModule` (or refined) module.
 * For the sake of avoiding ambiguity, tools consuming this trait can raise an "illegal" status if there are
 * multiple modules connecting to a memory module without going through a communication module first.
 *
 * If a `GenericMemoryModule` is connected directly to a `GenericProcessingModule` (or refined), it is implied
 * that this memory element is tightly-coupled to the processing element.
 *
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface GenericMemoryModule extends DigitalModule {

    @Property
    Long spaceInBits();
}
