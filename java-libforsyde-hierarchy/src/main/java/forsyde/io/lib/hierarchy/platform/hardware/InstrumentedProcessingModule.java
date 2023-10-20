package forsyde.io.lib.hierarchy.platform.hardware;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

import java.util.Map;
import java.util.Set;

/**
 * A InstrumentedProcessingModule enriches `GenericProcessingModule` with provision numbers so that
 * an analysis and synthesis tool is able to estimate the total amount of execution time is required to execute
 * a bunch of instructions in this processing element.
 * <p>
 * If this processing element exhibits higher level of parallelism (see `GenericProcessingModule`), then the provisions
 * should always be provided _per parallel "thread"_.
 * For example, if the processing element is a typical dual-core, the model instructions per cycle property
 * should be as the intructions per cycle _per core_, not their summed total.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface InstrumentedProcessingModule extends GenericProcessingModule {

    /**
     * <p>
     * A map of computational provisions per cycle for different instantiations of this instrumented processing module.
     * These are <b>performance provision</b>.
     * When a number of a certain instruction is given "x" more, it means semantically that a hosted process element
     * uses "x" operations in the same time period.
     * </p>
     *
     * <p>
     * For example, there could be a "generic" instantiation, a "power" instantiation and a
     * "slow" so that the provisions can be expressed
     * as an associative array with these three possibilties as follow.
     * </p>
     *
     * <pre>
     * computationalRequirements: {
     *     "generic": {
     *         "intadd": 0.5,
     *         "floatadd": 0.001,
     *         "branch": 1.0,
     *         ...
     *     },
     *     "slow": {
     *          "intadd": 0.05,
     *          "floatadd": 0.00001,
     *          "branch": 0.2,
     *           ...
     *      },
     *     "power": {
     *         "intadd": 1.0,
     *         ...
     *     }
     * }
     * </pre>
     *
     * <p>
     * Where the real numbers describe the amount of the requirements (provisions) is provided per clock cycle.
     * So, if you want the amount of requirements (provisions) this processing element is giving per second, you
     * simply do:
     * </p>
     *
     * <pre>
     *   instructions per second = clock frequency * instructions per clock cycle
     * </pre>
     */
    @Property
    Map<String, Map<String, Double>> modalInstructionsPerCycle();


    /**
     * <p>
     * A set of possible instructions categories, e.g. instruction set architectures, so that the memory requirements
     * can be properly determined.
     * These are <b>memory requirements</b>.
     * </p>
     *
     * <p>
     * For example, there could be a "RISCV" instruction category and a "niosII" instruction category
     * as a set as follows.
     * </p>
     *
     * <pre>
     * modalInstructionCategory: [
     *      "RISCV",
     *      "niosII"
     * ]
     * </pre>
     * In this case, the FPGA implementation tries to capture the logic area consumed by the synthesized behaviour.
     */
    @Property
    Set<String> modalInstructionCategory();

//    @Property
//    default Map<String, Map<String, Integer>> modalInstructionsPerCycleDenominators() {
//        return modalInstructionsPerCycleNumerators().entrySet().stream().collect(Collectors.toMap(
//                Map.Entry::getKey,
//                e -> e.getValue().entrySet().stream().collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        ee -> 1
//                ))
//        ));
//    };
}
