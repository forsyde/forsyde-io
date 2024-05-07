package forsyde.io.lib.hierarchy.decision;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;
import forsyde.io.lib.hierarchy.platform.hardware.LogicProgrammableModule;

/**
 * This trait captures elments that are to be synthetized in a programmable logic area, e.g. an FPGA.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface LogicProgrammableAreaMapped extends VertexViewer {

    @OutPort
    LogicProgrammableModule hostLogicProgrammableArea();
}
