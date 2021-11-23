package forsyde.io.java.adapters.amalthea;

import forsyde.io.java.adapters.EquivalenceModel2ModelMixin;
import forsyde.io.java.core.Edge;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.typed.viewers.*;
import org.eclipse.app4mc.amalthea.model.*;

import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public interface ForSyDe2AmaltheaOSAdapterMixin extends EquivalenceModel2ModelMixin<Vertex, INamed> {

    default void fromVertexesToOSModel(ForSyDeModel model, Amalthea target) {
        target.setOsModel(AmaltheaFactory.eINSTANCE.createOSModel());
//        fromVertexesToModules(model, target, cache);
//        fromVertexesToStructures(model, target, cache);
//        fromEdgesToConnections(model, target, cache);
        for (Vertex v : model.vertexSet()) {
            if (FixedPriorityScheduler.conforms(v)) {
                final OperatingSystem operatingSystem = AmaltheaFactory.eINSTANCE.createOperatingSystem();
                final TaskScheduler taskScheduler = AmaltheaFactory.eINSTANCE.createTaskScheduler();
                operatingSystem.setName(v.getIdentifier());
                taskScheduler.setName(v.getIdentifier() + "FPScheduler");
                taskScheduler.setSchedulingAlgorithm(AmaltheaFactory.eINSTANCE.createFixedPriorityPreemptive());
                operatingSystem.getTaskSchedulers().add(taskScheduler);
                addEquivalence(v, operatingSystem);
                target.getOsModel().getOperatingSystems().add(operatingSystem);
            } else if (RoundRobinScheduler.conforms(v)) {
                final OperatingSystem operatingSystem = AmaltheaFactory.eINSTANCE.createOperatingSystem();
                final TaskScheduler taskScheduler = AmaltheaFactory.eINSTANCE.createTaskScheduler();
                operatingSystem.setName(v.getIdentifier());
                taskScheduler.setName(v.getIdentifier() + "RRScheduler");
                taskScheduler.setSchedulingAlgorithm(AmaltheaFactory.eINSTANCE.createPriorityBasedRoundRobin());
                operatingSystem.getTaskSchedulers().add(taskScheduler);
                addEquivalence(v, operatingSystem);
                target.getOsModel().getOperatingSystems().add(operatingSystem);
            } else if (TimeTriggeredScheduler.conforms(v)) {
                final OperatingSystem operatingSystem = AmaltheaFactory.eINSTANCE.createOperatingSystem();
                final TaskScheduler taskScheduler = AmaltheaFactory.eINSTANCE.createTaskScheduler();
                operatingSystem.setName(v.getIdentifier());
                taskScheduler.setName(v.getIdentifier() + "TTScheduler");
                taskScheduler.setSchedulingAlgorithm(AmaltheaFactory.eINSTANCE.createUserSpecificSchedulingAlgorithm());
                operatingSystem.getTaskSchedulers().add(taskScheduler);
                addEquivalence(v, operatingSystem);
                target.getOsModel().getOperatingSystems().add(operatingSystem);
            }
        }
    }

}
