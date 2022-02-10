package forsyde.io.java.adapters.amalthea;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import org.eclipse.app4mc.amalthea.model.Amalthea;
import org.eclipse.app4mc.amalthea.model.INamed;

public interface AmaltheaMapping2ForSyDeMixin extends EquivalenceModel2ModelMixin<INamed, Vertex> {

    default void fromMappingToForSyDe(Amalthea amalthea, ForSyDeSystemGraph forSyDeSystemGraph) {
        if (amalthea.getMappingModel() != null) {

            amalthea.getMappingModel().getSchedulerAllocation().forEach(schedulerAllocation -> {
                equivalent(schedulerAllocation.getScheduler()).ifPresent(schedulerVertex -> {
                    equivalent(schedulerAllocation.getExecutingPU()).ifPresent(puVertex -> {
                        schedulerVertex.addTraits(VertexTrait.DECISION_ALLOCATED);
                        schedulerVertex.ports.add("allocationHost");
                        forSyDeSystemGraph.connect(schedulerVertex, puVertex, "allocationHost", EdgeTrait.DECISION_ABSTRACTALLOCATION);
                        puVertex.addTraits(VertexTrait.VISUALIZATION_GREYBOX);
                        puVertex.ports.add("contained");
                        forSyDeSystemGraph.connect(puVertex, schedulerVertex, "contained", EdgeTrait.VISUALIZATION_VISUALCONTAINMENT);
                    });
                });
            });

            amalthea.getMappingModel().getTaskAllocation().forEach(taskAllocation -> {
                equivalent(taskAllocation.getScheduler()).ifPresent(schedulerVertex -> {
                    equivalent(taskAllocation.getTask()).ifPresent(taskVertex -> {
                        taskVertex.addTraits(VertexTrait.DECISION_SCHEDULED);
                        taskVertex.ports.add("scheduler");
                        forSyDeSystemGraph.connect(taskVertex, schedulerVertex, "scheduler", EdgeTrait.DECISION_ABSTRACTSCHEDULING);
                        schedulerVertex.addTraits(VertexTrait.VISUALIZATION_GREYBOX);
                        schedulerVertex.ports.add("contained");
                        forSyDeSystemGraph.connect(schedulerVertex, taskVertex, "contained", EdgeTrait.VISUALIZATION_VISUALCONTAINMENT);
                    });
                });
            });

            amalthea.getMappingModel().getMemoryMapping().forEach(memoryMapping -> {
                equivalent(memoryMapping.getMemory()).ifPresent(memoryVertex -> {
                    equivalent(memoryMapping.getAbstractElement()).ifPresent(elementVertex -> {
                        elementVertex.addTraits(VertexTrait.DECISION_MEMORYMAPPED);
                        elementVertex.addTraits(VertexTrait.DECISION_ALLOCATED);
                        elementVertex.ports.add("mappingHost");
                        elementVertex.ports.add("allocationHost");
                        forSyDeSystemGraph.connect(elementVertex, memoryVertex, "mappingHost", EdgeTrait.DECISION_ABSTRACTMAPPING);
                        forSyDeSystemGraph.connect(elementVertex, memoryVertex, "allocationHost", EdgeTrait.DECISION_ABSTRACTALLOCATION);
                        memoryVertex.addTraits(VertexTrait.VISUALIZATION_GREYBOX);
                        memoryVertex.ports.add("contained");
                        forSyDeSystemGraph.connect(memoryVertex, elementVertex, "contained", EdgeTrait.VISUALIZATION_VISUALCONTAINMENT);
                    });
                });
            });
        }
    }
}