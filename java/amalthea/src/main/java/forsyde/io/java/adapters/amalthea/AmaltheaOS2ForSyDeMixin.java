package forsyde.io.java.adapters.amalthea;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import org.eclipse.app4mc.amalthea.model.*;

import java.util.Map;

public interface AmaltheaOS2ForSyDeMixin extends EquivalenceModel2ModelMixin<INamed, Vertex> {

    default void fromOSToForSyDe(Amalthea amalthea, ForSyDeSystemGraph forSyDeSystemGraph) {
        if (amalthea.getOsModel() != null) {
            fromOSModelToBinding(amalthea, forSyDeSystemGraph);
        }
    }

    default void fromOSModelToBinding(Amalthea amalthea, ForSyDeSystemGraph model) {
        for (OperatingSystem os: amalthea.getOsModel().getOperatingSystems()) {
            for(TaskScheduler taskScheduler : os.getTaskSchedulers()) {
                final Vertex platformVertex = new Vertex(os.getName() + "." + taskScheduler.getName(), VertexTrait.PLATFORM_PLATFORMELEM);
                // this is very messy, but it is required as app4mc encapsualted everything
                // delightfully in a Value type that apparently does not play nice with Java types.
                final Boolean isPreemptive = taskScheduler.getSchedulingParameters()
                        .stream().anyMatch(p ->
                                p.getKey() != null && p.getKey().getName().equals("preemptive") &&
                                p.getValue() != null && p.getValue() instanceof BooleanObject && ((BooleanObject) p.getValue()).isValue() ||
                                (p.getKey().getDefaultValue() != null && p.getKey().getDefaultValue() instanceof BooleanObject &&
                                        ((BooleanObject) p.getKey().getDefaultValue()).isValue()));
                // simply check if any parameter is named 'priority'.
                final Boolean hasPriority = taskScheduler.getDefinition().getProcessParameters()
                        .stream().anyMatch(p ->
                                p.getName().equals("priority"));
                if (isPreemptive && hasPriority) {
                    platformVertex.addTraits(VertexTrait.PLATFORM_RUNTIME_FIXEDPRIORITYSCHEDULER);
                }
                addEquivalence(os, platformVertex);
                addEquivalence(taskScheduler, platformVertex);
                model.addVertex(platformVertex);
                for (SchedulerAllocation allocation : amalthea.getMappingModel().getSchedulerAllocation()) {
                    if (allocation.getScheduler().equals(taskScheduler)) {
                        equivalent(allocation.getExecutingPU()).ifPresent(puVertex -> {
                            model.connect(platformVertex, puVertex, EdgeTrait.DECISION_ABSTRACTALLOCATION);
                        });
                    }
                }
            }
        }
    }
}
