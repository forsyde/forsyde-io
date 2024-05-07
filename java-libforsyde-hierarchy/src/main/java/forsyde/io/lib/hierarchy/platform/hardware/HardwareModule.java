package forsyde.io.lib.hierarchy.platform.hardware;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;


/**
 * This trait represents any hardware element in the platform.
 * It does not distinguish between analog or digital.
 * The main purpose of this trait is to have a common "parent" trait
 * for all traits relevant to the hardware parts of a platform.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface HardwareModule extends VertexViewer {

}
