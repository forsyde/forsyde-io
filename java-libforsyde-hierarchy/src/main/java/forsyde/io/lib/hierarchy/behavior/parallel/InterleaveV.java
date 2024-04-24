package forsyde.io.lib.hierarchy.behavior.parallel;


import forsyde.io.core.annotations.*;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;

import java.util.Optional;

/**
 * This parallel skeleton describes the creation of overlapping areas from a Vectorizable.
 * As an example, consider the vector of integers [1, 4, 2];
 * An interleaving skeleton with stride of 1 and radius of 2 would generator the vector of vector [[1, 4], [4, 2]].
 * An interleaving skeleton with stride of 2 and radius of 2 would generator the vector of vector [[1, 4], [2]].
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface InterleaveV extends ParallelSkeleton {

    @InPort
    @WithEdgeTrait(ParallelComputationEdge.class)
    Optional<Vectorizable> inputArray();

    @OutPort
    @WithEdgeTrait(ParallelComputationEdge.class)
    Optional<Vectorizable> outputArrayOfArray();

    @Property
    default Integer stride() {return 1;};

    @Property
    Integer radius();
}
