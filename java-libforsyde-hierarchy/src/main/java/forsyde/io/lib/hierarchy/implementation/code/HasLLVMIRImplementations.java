package forsyde.io.lib.hierarchy.implementation.code;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

import java.util.*;

/**
 * This trait enforces the vertex to have at least one inlined LLVM implementation.
 *
 * There can be more than one, discriminted by a label for each inlined source code.
 * For example, a vetex can have a chunck of code with the label "riscv" for RISC-V
 * based processors and one with label "cude" to be implemented directly in a CUDA
 * based flow, whenever possible.
 *
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface HasLLVMIRImplementations extends VertexViewer {

    @Property
    Map<String, String> inlinedLLVMIR();

    @Property
    default Map<String, String> inputArgumentPorts() {return new HashMap<>();}

    @Property
    default Map<String, String> outputArgumentPorts() {return new HashMap<>();}

    @Property
    Map<String, String> returnPort();
}
