package forsyde.io.lib.hierarchy.behavior.moc;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

/**
 * The common parent of all MoC elements in a system graph.
 *
 * <p>
 * A MoC in this hierarchy is understood as in the paper:
 *</p>
 *<p>
 *   Lee, E.A., Sangiovanni-Vincentelli, A., 1998. A framework for comparing models of computation.
 *   IEEE Transactions on Computer-Aided Design of Integrated Circuits and Systems 17, 1217–1229.
 *   <a href="https://doi.org/10.1109/43.736561">DOI</a>
 * </p>
 * <p>
 *     This trait does not enforce anything on its own, but serves as a "categorical"
 *     trait which can be used to susbet any MoCs present in a system graph.
 *     To get more information on a vertex and its MoC-like behaviour, one should
 *     directly query for specific MoCs, e.g. SYProcess or SDFActor.
 * </p>
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface MoCEntity extends VertexViewer {


}
