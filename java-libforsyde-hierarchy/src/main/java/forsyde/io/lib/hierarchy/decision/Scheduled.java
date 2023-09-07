package forsyde.io.lib.hierarchy.decision;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.platform.runtime.AbstractRuntime;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

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
