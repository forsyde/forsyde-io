package forsyde.io.lib.hierarchy.platform.hardware;

import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

/**
 * A GenericCommunicationModule represents any digital hardware module capable of doing "communication".
 * Typical real things fitting to this trait abstraction are buses, crossbar switches, routers.
 * If the element can be abstracted into a black-box that "transmits data", this trait captures it.
 *
 * Currently, this trait does not tell anything about the arbitration that the communication element performs.
 * If no information is avaiable to specialize the arbitration scheme used at a hardware level, any tool working
 * with this trait can assume that a fair round-robin is used.
 *
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface GenericCommunicationModule extends DigitalModule {
}
