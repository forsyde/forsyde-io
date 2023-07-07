package forsyde.io.lib.platform.hardware;

import forsyde.io.core.EdgeTrait;
import forsyde.io.core.VertexTrait;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;

import java.util.List;

/**
 * A PhysicalConnection represents a physical link between hardware modules.
 * This is rather self-explanatory. If two modules have ports connected and mapped between them,
 * physical links ought to exists as the connections.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface PhysicalConnection extends EdgeTrait {


}
