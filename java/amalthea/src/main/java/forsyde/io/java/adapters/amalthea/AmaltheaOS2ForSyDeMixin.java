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
        fromOSModelToBinding(amalthea, forSyDeSystemGraph);
    }

    default void fromOSModelToBinding(Amalthea amalthea, ForSyDeSystemGraph model) {
        for (OperatingSystem os: amalthea.getOsModel().getOperatingSystems()) {
            for(TaskScheduler taskScheduler : os.getTaskSchedulers()) {
                final Vertex platformVertex = new Vertex(os.getName() + "." + taskScheduler.getName(), VertexTrait.PLATFORM_PLATFORMELEM);
                if (taskScheduler.getSchedulingAlgorithm() instanceof FixedPriorityPreemptive) {
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
