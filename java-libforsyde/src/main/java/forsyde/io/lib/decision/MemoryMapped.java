package forsyde.io.lib.decision;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;
import forsyde.io.lib.platform.hardware.GenericMemoryModule;

/**
 * A MemoryMapped trait describes the mapping of any kind of a process or buffer/channel to a memory.
 * This is intended to be used as the result of scheduling and mapping techniques,
 * by "annotating" the memory mapped process.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface MemoryMapped extends VertexViewer {

    @OutPort
    GenericMemoryModule mappingHost();
}
