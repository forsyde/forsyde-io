package forsyde.io.java.amalthea.adapters.mixins;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.core.EdgeTrait;
import forsyde.io.core.SystemGraph;
import forsyde.io.core.Vertex;
import forsyde.io.core.VertexTrait;
import org.eclipse.app4mc.amalthea.model.Amalthea;
import org.eclipse.app4mc.amalthea.model.INamed;

public interface AmaltheaMapping2ForSyDeMixin extends EquivalenceModel2ModelMixin<INamed, Vertex> {

    default void fromMappingToForSyDe(Amalthea amalthea, SystemGraph systemGraph) {
        if (amalthea.getMappingModel() != null) {

            amalthea.getMappingModel().getSchedulerAllocation().forEach(schedulerAllocation -> {
                equivalent(schedulerAllocation.getScheduler()).ifPresent(schedulerVertex -> {
                    equivalent(schedulerAllocation.getExecutingPU()).ifPresent(puVertex -> {
                        schedulerVertex.addTraits(VertexTrait.DECISION_ALLOCATED);
                        schedulerVertex.addPort("allocationHost");
                        systemGraph.connect(schedulerVertex, puVertex, "allocationHost", EdgeTrait.DECISION_ABSTRACTALLOCATION);
                        puVertex.addTraits(VertexTrait.VISUALIZATION_GREYBOX);
                        puVertex.addPort("contained");
                        systemGraph.connect(puVertex, schedulerVertex, "contained", EdgeTrait.VISUALIZATION_VISUALCONTAINMENT);
                    });
                });
            });

            amalthea.getMappingModel().getTaskAllocation().forEach(taskAllocation -> {
                equivalent(taskAllocation.getScheduler()).ifPresent(schedulerVertex -> {
                    equivalent(taskAllocation.getTask()).ifPresent(taskVertex -> {
                        taskVertex.addTraits(VertexTrait.DECISION_SCHEDULED);
                        taskVertex.addPort("scheduler");
                        systemGraph.connect(taskVertex, schedulerVertex, "scheduler", EdgeTrait.DECISION_ABSTRACTSCHEDULING);
                        schedulerVertex.addTraits(VertexTrait.VISUALIZATION_GREYBOX);
                        schedulerVertex.addPort("contained");
                        systemGraph.connect(schedulerVertex, taskVertex, "contained", EdgeTrait.VISUALIZATION_VISUALCONTAINMENT);
                    });
                });
            });

            amalthea.getMappingModel().getMemoryMapping().forEach(memoryMapping -> {
                equivalent(memoryMapping.getMemory()).ifPresent(memoryVertex -> {
                    equivalent(memoryMapping.getAbstractElement()).ifPresent(elementVertex -> {
                        elementVertex.addTraits(VertexTrait.DECISION_MEMORYMAPPED);
                        elementVertex.addTraits(VertexTrait.DECISION_ALLOCATED);
                        elementVertex.addPort("mappingHost");
                        elementVertex.addPort("allocationHost");
                        systemGraph.connect(elementVertex, memoryVertex, "mappingHost", EdgeTrait.DECISION_ABSTRACTMAPPING);
                        systemGraph.connect(elementVertex, memoryVertex, "allocationHost", EdgeTrait.DECISION_ABSTRACTALLOCATION);
                        memoryVertex.addTraits(VertexTrait.VISUALIZATION_GREYBOX);
                        memoryVertex.addPort("contained");
                        systemGraph.connect(memoryVertex, elementVertex, "contained", EdgeTrait.VISUALIZATION_VISUALCONTAINMENT);
                    });
                });
            });
        }
    }
}
