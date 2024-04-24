package forsyde.io.lib.hierarchy.behavior.moc.sdf;

import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.core.annotations.WithEdgeTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;
import forsyde.io.lib.hierarchy.behavior.BehaviourCompositionEdge;
import forsyde.io.lib.hierarchy.behavior.FunctionLikeEntity;
import forsyde.io.lib.hierarchy.behavior.moc.MoCEntity;

import java.util.Map;
import java.util.Set;

@RegisterTrait(IForSyDeHierarchy.class)
public interface SDFActor extends MoCEntity {

    @Property
    Map<String, Integer> consumption();

    @Property
    Map<String, Integer> production();

    @OutPort
    @WithEdgeTrait(BehaviourCompositionEdge.class)
    Set<FunctionLikeEntity> combFunctions();

}
