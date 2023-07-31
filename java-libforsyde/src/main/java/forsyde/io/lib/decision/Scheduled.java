package forsyde.io.lib.decision;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.IForSyDeHierarchy;
import forsyde.io.lib.platform.runtime.AbstractRuntime;

/**
 * A Scheduled trait describes the scheduling of any kind of a process by a scheduler.
 * This is intended to be used as the result of scheduling and mapping techniques,
 * by "annotating" the scheduled process.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface Scheduled extends VertexViewer {

    @OutPort
    AbstractRuntime runtimeHost();
}
