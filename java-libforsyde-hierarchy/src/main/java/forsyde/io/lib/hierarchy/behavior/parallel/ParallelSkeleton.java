package forsyde.io.lib.hierarchy.behavior.parallel;

import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.lib.hierarchy.behavior.FunctionLikeEntity;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

/**
 * An algorithmic parallel skeleton is a specification that explicitly
 * enables a computation to be done in parallel. That is, if a computation is a
 * certain parallel skeleton, we know that it can be implemented with the parallelization
 * of that skeleton. For example, for the MapV skeleton, we know that all the entries of the
 * output vector can be computed independently from each entry in the input vector,
 * inpendently if the implementation is made in sequential software, parallel software or even hardware.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface ParallelSkeleton extends FunctionLikeEntity {
}
