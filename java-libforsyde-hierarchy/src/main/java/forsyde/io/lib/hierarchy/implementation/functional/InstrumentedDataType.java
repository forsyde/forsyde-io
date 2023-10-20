package forsyde.io.lib.hierarchy.implementation.functional;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

import java.util.Map;

/**
 * This class adds instrumentation data to any data-like vertex.
 * This trait is made separate from other data-like traits, e.g. vectorizable,
 * so that the instrumentation information can be used in a courser-grain fashion whenever
 * necessary. Say, in academic studies or pre-design studies.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface InstrumentedDataType extends VertexViewer {

    /**
     * <p>
     * A map of memory requirements for different implementations of this instrumented data type.
     * These are <b>memory requirements</b>.
     * When a number of a certain operation is "x" larger, it means semantically that a host storage element
     * must give "x" more space to this data type so that it can be stored.
     * This is the data-type analogous of <code>maxSizeInBits</code> for <code>InstrumentedBehaviour</code>.
     * </p>
     *
     * <p>
     * For example, there could be a "ANSI-C" data type, a "CUDA" data type and a
     * "FPGA logic area implementation" data type so that the memory requirements can be expressed
     * as an associative array with these three possibilities as follow.
     * </p>
     *
     * <pre>
     * maxSizeInBits: {
     *     "ANSI-C": 1000L,
     *     "CUDA": 500L,
     *     "FPGA logic area implementation": 200L
     * }
     * </pre>
     * In this case, the FPGA implementation tries to capture the logic area consumed by the synthesized data-type.
     *
     */
    @Property
    Map<String, Long> maxSizeInBits();
}
