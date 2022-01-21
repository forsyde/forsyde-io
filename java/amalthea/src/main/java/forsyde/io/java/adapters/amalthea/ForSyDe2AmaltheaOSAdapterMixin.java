package forsyde.io.java.adapters.amalthea;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.typed.viewers.*;
import forsyde.io.java.typed.viewers.platform.runtime.FixedPriorityScheduler;
import forsyde.io.java.typed.viewers.platform.runtime.RoundRobinScheduler;
import forsyde.io.java.typed.viewers.platform.runtime.TimeTriggeredScheduler;
import org.eclipse.app4mc.amalthea.model.*;

public interface ForSyDe2AmaltheaOSAdapterMixin extends EquivalenceModel2ModelMixin<Vertex, INamed> {

    default void fromVertexesToOSModel(ForSyDeSystemGraph model, Amalthea target) {
        target.setOsModel(AmaltheaFactory.eINSTANCE.createOSModel());
//        fromVertexesToModules(model, target, cache);
//        fromVertexesToStructures(model, target, cache);
//        fromEdgesToConnections(model, target, cache);
        for (Vertex v : model.vertexSet()) {
            final String newId = v.getIdentifier().replace(".", "_");
            if (FixedPriorityScheduler.conforms(v)) {
                final OperatingSystem operatingSystem = AmaltheaFactory.eINSTANCE.createOperatingSystem();
                final TaskScheduler taskScheduler = AmaltheaFactory.eINSTANCE.createTaskScheduler();
                operatingSystem.setName(newId);
                taskScheduler.setSchedulingAlgorithm(AmaltheaFactory.eINSTANCE.createPriorityBasedRoundRobin());
                operatingSystem.getTaskSchedulers().add(taskScheduler);
                addEquivalence(v, operatingSystem);
                target.getOsModel().getOperatingSystems().add(operatingSystem);
            } else if (RoundRobinScheduler.conforms(v)) {
                final OperatingSystem operatingSystem = AmaltheaFactory.eINSTANCE.createOperatingSystem();
                final TaskScheduler taskScheduler = AmaltheaFactory.eINSTANCE.createTaskScheduler();
                operatingSystem.setName(newId);
                taskScheduler.setName(newId + "_RRScheduler");
                taskScheduler.setSchedulingAlgorithm(AmaltheaFactory.eINSTANCE.createPriorityBasedRoundRobin());
                operatingSystem.getTaskSchedulers().add(taskScheduler);
                addEquivalence(v, operatingSystem);
                target.getOsModel().getOperatingSystems().add(operatingSystem);
            } else if (TimeTriggeredScheduler.conforms(v)) {
                final OperatingSystem operatingSystem = AmaltheaFactory.eINSTANCE.createOperatingSystem();
                final TaskScheduler taskScheduler = AmaltheaFactory.eINSTANCE.createTaskScheduler();
                operatingSystem.setName(newId);
                taskScheduler.setName(newId + "_TTScheduler");
                taskScheduler.setSchedulingAlgorithm(AmaltheaFactory.eINSTANCE.createUserSpecificSchedulingAlgorithm());
                operatingSystem.getTaskSchedulers().add(taskScheduler);
                addEquivalence(v, operatingSystem);
                target.getOsModel().getOperatingSystems().add(operatingSystem);
            }
        }
    }

}
