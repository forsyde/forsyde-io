package forsyde.io.lib.hierarchy.platform.hardware;

import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

/**
 * A LogicProgrammableAreaModule represents any digital hardware module capable of doing "compute".
 * Typical real things fitting to this trait abstraction are CPU Cores, GPUs and ASICs.
 * If the element can be abstracted into a black-box that "runs computation", this trait captures it.
 *
 * This trait can also be used to represent compound processing elements, like multi-core CPUs _without_ their memories.
 * But beware: analysis and synthesis algorithms might expect these elements to always be "single-core-like",
 * so it is always good to be in sync with the assumptions of the tool using this trait.
 *
 * This black-boxed level of _true_ parallelism is captured through the property `maximumComputationParallelism`,
 * which is 1 by default.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface LogicProgrammableModule extends HardwareModule {

    @Property
    int availableLogicArea();
}
