package forsyde.io.lib.hierarchy.implementation.functional;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;
import forsyde.io.lib.hierarchy.behavior.BehaviourEntity;

import java.util.Map;

/**
 * This class adds instrumentation data to any behaviour-like vertex.
 * This trait is made separate from other behaviour-like traits, e.g. synchronous processes,
 * so that the instrumentation information can be used in a courser-grain fashion whenever
 * necessary. Say, in academic studies or pre-design studies.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface InstrumentedSoftwareBehaviour extends BehaviourEntity {

    /**
     * <p>
     * A map of computational requirements for different implementations of this instrumented behavior.
     * These are <b>performance requirements</b>.
     * When a number of a certain operation is "x" larger, it means semantically that a host processing element
     * must give "x" more operations to this behaviour so that it completes.
     * </p>
     *
     * <p>
     * For example, there could be a "ANSI-C" implementation and a "CUDA" implementation
     * so that the requirements can be expressed
     * as an associative array with these two possibilties as follow.
     * </p>
     *
     * <pre>
     * computationalRequirements: {
     *     "ANSI-C": {
     *         "intadd": 4,
     *         "floatadd": 10,
     *         "branch": 200,
     *         ...
     *     },
     *     "CUDA": {
     *         "kerneladd": 5,
     *         ...
     *     }
     * }
     * </pre>
     *
     * The names of the each implementation should match the ones of "maxSizeInBits".
     */
    @Property
    Map<String, Map<String, Long>> computationalRequirements();

    /**
     * <p>
     * A map of memory requirements for different implementations of this instrumented behavior for different instruction categories.
     * These are <b>memory requirements</b>.
     * When a number of a certain operation is "x" larger, it means semantically that a host storage element
     * must give "x" more space to this behaviour so that it can be stored.
     * the memory of a behaviour semantically include both its "execution size" (instruction size in languages like C)
     * and "internal state" (internal variables in languages like C).
     * </p>
     *
     * <p>
     * For example, there could be a "RISCV" implementation and a "niosII" implementation
     * so that the computational requirements can be expressed
     * as an associative array with these three possibilities as follow.
     * </p>
     *
     * <pre>
     * maxSizeInBits: {
     *     "RISCV": 1000L,
     *     "niosII": 500L,
     * }
     * </pre>
     *
     * The names of the each implementation should match the ones of "computationalRequirements".
     */
    @Property
    Map<String, Long> maxSizeInBits();
}
