package forsyde.io.lib.hierarchy.platform.hardware;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

import java.util.List;
import java.util.Map;

/**
 * A CommunicationModulePortSpecification enriches `GenericCommunicationModule` with how ports of 
 * the viewed `Vertex` are connected. This trait allows can therefore be used to model the
 * communication ports of a `CommunicationModule` in a more detailed way. In realistic scenarios,
 * a switch may not route all of its ports to the the other ports in the switch. Take for example a
 * memory switch, which has a link to the physical memory and multiple links that are in place
 * for access to the memory via the memory switch. Specifying that the external links can route
 * internally in the switch to the memory port is a good example of how this trait can be used 
 * and thus also imply that the memory switch does not allow communication between these 
 * external links.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface CommunicationModulePortSpecification extends GenericCommunicationModule {

    /**
     * A map of port connections. Having a switch with ports ('a', 'b', 'c') where 'a' and 'b' 
     * are ports for external connections and 'c' connects to the physical memory.
     * <p>
     * The map representing would look be:
     * <pre>{@code
     * portConnections: {
     *   "a": ["c"],
     *   "b": ["c"]
     * }
     * }</pre>
     * </p>
     */
    @Property
    Map<String, List<String>> portConnections();
}
