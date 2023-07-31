package forsyde.io.lib.behavior.parallel;

import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.core.annotations.WithEdgeTrait;
import forsyde.io.lib.IForSyDeHierarchy;
import forsyde.io.lib.behavior.BehaviourEntity;
import forsyde.io.lib.behavior.BehaviourCompositionEdge;

import java.util.List;
import java.util.Set;


/**
 * A MapV skeleton specifies that all the entries of the output vector can be computed
 * independently from each other, using each of the input vector entries.
 *
 * Like this: let v = [v[1], v[2], v[3], ..., v[n]] be the input vector and o be an equivalent output vector;
 * then a MapV with kernels f1, f2, ..., fm guarantess that:
 *
 * o[i] = fm(...f3(f2(f1(v[i])))...)
 *
 * or,
 *
 * o[i] = (fm ... f3 . f2 . f1) (v[i])
 *
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface MapV extends ParallelSkeleton {

    @OutPort
    @WithEdgeTrait(BehaviourCompositionEdge.class)
    Set<BehaviourEntity> kernels();

    @Property
    List<String> outputPorts();

    @Property
    List<String> inputPorts();

}
