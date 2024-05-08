package forsyde.io.lib.hierarchy.decision;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;
import forsyde.io.lib.hierarchy.platform.hardware.LogicProgrammableModule;

/**
 * This trait captures elements that will be synthetized in a logic programmable area like FPGAs.
 * <p>
 * The actual elements being synthetized can be software or hardware elements, i.e. behaviours that will become
 * ASICs or soft hardware modules.
 *
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface LogicProgrammableSynthetized extends VertexViewer {

    @OutPort
    LogicProgrammableModule hostLogicProgrammableModule();
}
