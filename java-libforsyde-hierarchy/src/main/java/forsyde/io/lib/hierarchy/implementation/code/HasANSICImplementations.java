package forsyde.io.lib.hierarchy.implementation.code;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This trait enforces the vertex to have at least one inlined C-base implementation.
 *
 * There can be more than one, discriminted by a label for each inlined source code.
 * For example, a vertex can have a chunck of code with the label "riscv" for RISC-V
 * based processors and one with label "cude" to be implemented directly in a CUDA
 * based flow, whenever possible.
 *
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface HasANSICImplementations extends VertexViewer {

    @Property
    Map<String, String> inlinedCodes();

    @Property
    default List<String> inputArgumentPorts() {return new ArrayList<>();}

    @Property
    default List<String> outputArgumentPorts() {return new ArrayList<>();}

    @Property
    default String returnPort() {
        return "void";
    }
}
