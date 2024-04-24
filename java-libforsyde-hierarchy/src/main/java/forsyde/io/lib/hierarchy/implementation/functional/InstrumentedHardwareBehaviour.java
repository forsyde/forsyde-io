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
public interface InstrumentedHardwareBehaviour extends BehaviourEntity {

    /**
     * <p>
     * A map of resource requirements for different implementations of this instrumented behavior.
     * These are <b>resource requirements</b> or <b>allocation requirements</b>.
     * When a number of a certain operation is "x" larger, it means semantically that a host element
     * must give "x" more resources to implement this behaviour.
     * </p>
     *
     * <p>
     * For example, there could be a "FPGA" implementation and a "SiLago" implementation
     * so that the requirements can be expressed
     * as an associative array with these two possibilties as follow.
     * </p>
     *
     * <pre>
     * resourceRequirements: {
     *     "FPGA": {
     *         "LUTS": 10,
     *         "gates" 100,
     *         ...
     *     },
     *     "SiLago": {
     *         "FFTFIMP": 1,
     *         ...
     *     }
     * }
     * </pre>
     *
     * <p>
     *     The names of the each implementation should match the ones of "latencyInSecs" and "energyPerExecutionInJoules".
     * </p>
     */
    @Property
    Map<String, Map<String, Long>> resourceRequirements();

    /**
     * <p>
     * A map of lantencies for different implementations of this instrumented behavior.
     * This also captures the equivalent of "execution time" from a pure hardware perspective.
     * </p>
     *
     * <p>
     * For example, an "FPGA" implementation could have 12 ps of latency, which is then encoded
     * </p>
     *
     * <pre>
     * latencyInSecsNumerators: {
     *     "FPGA": 12,
     *     ...
     * },
     * latencyInSecsDenominators: {
     *     "FPGA": 1000000000000
     * }
     * </pre>
     *
     * <p>
     *     The names of the each implementation should match the ones of "resourceRequirements" and "energyPerExecutionInJoules".
     * </p>
     */
    @Property
    Map<String, Long> latencyInSecsNumerators();

    /**
     * <p>
     * A map of lantencies for different implementations of this instrumented behavior.
     * This also captures the equivalent of "execution time" from a pure hardware perspective.
     * Take a look at "latencyInSecsNumerators" for the documentation of this property.
     * </p>
     *
     * @see #latencyInSecsNumerators
     */
    @Property
    Map<String, Long> latencyInSecsDenominators();

    /**
     * This property captures the amount of energy spent in one iteration of this behaviour.
     * That is, the amount of energy in Joules spent in producing any output, given any input.
     * In software terms, this captures the energy consumed by the hardware-implemented function
     * from its start to finish.
     * <p>
     *     The names of the each implementation should match the ones of "resourceRequirements" and "latencyInSecs".
     * </p>
     */
    @Property
    Map<String, Double> energyPerExecutionInJoules();

    /**
     * <p>
     * This property captures the amount of hardware area required to implement this behaviour at FPGA elements uniformly.
     * </p>
     */
     default long requiredFPGAHardwareImplementationArea() {
         return resourceRequirements().getOrDefault("FPGA", Map.of()).getOrDefault("Area", 0L);
     }
}