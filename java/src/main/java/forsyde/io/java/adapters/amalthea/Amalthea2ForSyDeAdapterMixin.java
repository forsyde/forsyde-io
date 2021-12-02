package forsyde.io.java.adapters.amalthea;

import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.viewers.*;
import org.eclipse.app4mc.amalthea.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface Amalthea2ForSyDeAdapterMixin {

    default void fromPUIntoVertex(ProcessingUnit pu, Vertex v) {
        ProcessingUnitDefinition puDef = pu.getDefinition();
        v.addTraits(VertexTrait.ProfiledProcessingModule);
        ProfiledProcessingModule profPu = ProfiledProcessingModule.safeCast(v).get();
        HashMap<String, HashMap<String, Long>> provisions = new HashMap<>();
        HashMap<String, Long> provisionsInner = new HashMap<>();
        for (HwFeature feature : puDef.getFeatures()) {
            if (feature.getContainingCategory().getName().startsWith("Provisions")) {
                provisionsInner.put(feature.getName(), Math.round(feature.getValue()));
            }
        }
        provisions.put(puDef.getName(), provisionsInner);
        profPu.setProvisions(provisions);
    }

    default void fromCUIntoVertex(ConnectionHandler connectionHandler, Vertex v) {
        switch (connectionHandler.getDefinition().getPolicy()) {
            case ROUND_ROBIN:
                v.addTraits(VertexTrait.RoundRobinInterconnect);
                final RoundRobinInterconnect rrVertex = new RoundRobinInterconnectViewer(v);
                List<HwConnection> connections = connectionHandler.getPorts().stream().flatMap(p -> p.getConnections().stream())
                        .filter(p -> p.getPort1().getNamedContainer().equals(connectionHandler)).collect(Collectors.toList());
                HashMap<String, Integer> allocation = new HashMap<>();
                connections.forEach(p -> allocation.put(p.getPort2().getNamedContainer().getName(), 1));
                rrVertex.setAllocatedWeights(allocation);
                rrVertex.setTotalWeights(allocation.size());
                break;
            default:
                break;
        }
    }

    default Map<INamed, Vertex> fromStructureToVertex(ForSyDeSystemGraph model, HwStructure structure) {
        return fromStructureToVertex(model, structure, "");
    }

    default Map<INamed, Vertex> fromStructureToVertex(ForSyDeSystemGraph model, HwStructure structure,
                                                      String prefix) {
        final Vertex structureVertex = new Vertex(prefix + structure.getName(), VertexTrait.AbstractStructure);
        final HashMap<INamed, Vertex> transformed = new HashMap<>();
        model.addVertex(structureVertex);
        transformed.put(structure, structureVertex);
        structureVertex.ports.add("submodules");
        for (HwStructure childStructure : structure.getStructures()) {
            transformed.putAll(fromStructureToVertex(model, childStructure, prefix + structure.getName() + "."));
            model.connect(structureVertex, transformed.get(childStructure), "submodules",
                    EdgeTrait.AbstractStructuralConnection);
        }
        for (HwModule module : structure.getModules()) {
            final Vertex moduleVertex = new Vertex(prefix + structure.getName() + "." + module.getName());
            moduleVertex.putProperty("nominal_frequency_in_hertz",
                    fromFrequencyToLong(module.getFrequencyDomain().getDefaultValue()));
            transformed.put(module, moduleVertex);
            for (HwPort port : module.getPorts()) {
                moduleVertex.ports.add(port.getName());
            }
            if (module instanceof ProcessingUnit) {
                ProcessingUnit processingUnit = (ProcessingUnit) module;
                moduleVertex.addTraits(VertexTrait.GenericProcessingModule);
                fromPUIntoVertex(processingUnit, moduleVertex);
            } else if (module instanceof Memory) {
                Memory memory = (Memory) module;
                moduleVertex.addTraits(VertexTrait.GenericMemoryModule);
                moduleVertex.putProperty("max_memory_in_bits", memory.getDefinition().getSize().getNumberBits());
            } else if (module instanceof Cache) {
                Cache cache = (Cache) module;
                moduleVertex.addTraits(VertexTrait.GenericCacheModule);
            } else if (module instanceof ConnectionHandler) {
                ConnectionHandler connectionHandler = (ConnectionHandler) module;
                moduleVertex.addTraits(VertexTrait.GenericDigitalInterconnect);
                final GenericDigitalInterconnectViewer interconnectVertex = new GenericDigitalInterconnectViewer(
                        moduleVertex);
                interconnectVertex.setMaxConcurrentFlits(connectionHandler.getDefinition().getMaxConcurrentTransfers());
                // burst size is always in B, it seems
                interconnectVertex
                        .setMaxFlitSizeInBits((long) (connectionHandler.getDefinition().getMaxBurstSize() * 8L));

                fromCUIntoVertex(connectionHandler, moduleVertex);
                moduleVertex.addTraits(VertexTrait.GenericDigitalInterconnect);
            }
            model.addVertex(moduleVertex);
            model.connect(structureVertex, moduleVertex, "submodules", EdgeTrait.AbstractStructuralConnection);
        }
        for (HwPort port : structure.getPorts()) {
            structureVertex.ports.add(port.getName());
        }
        return transformed;
    }

    default void fromStructureToEdges(ForSyDeSystemGraph model, HwStructure structure,
                                      Map<INamed, Vertex> transformed) {
        for (HwStructure childStructure : structure.getStructures()) {
            fromStructureToEdges(model, childStructure, transformed);
        }
        for (HwConnection connection : structure.getConnections()) {
            final Vertex sourceVertex = transformed.get(connection.getPort1().getNamedContainer());
            final Vertex targetVertex = transformed.get(connection.getPort2().getNamedContainer());
            model.connect(sourceVertex, targetVertex, connection.getPort1().getName(), connection.getPort2().getName(), EdgeTrait.AbstractPhysicalConnection);
        }
	/*	for (HwModule module : structure.getModules()) {
			final Vertex moduleVertex = transformed.get(module);
			if (module instanceof ProcessingUnit) {
				final ProcessingUnit processingUnit = (ProcessingUnit) module;
				for (HwAccessElement hwAccessElement : processingUnit.getAccessElements()) {
					Vertex prev = transformed.get(hwAccessElement.getSource());
					if (hwAccessElement.getAccessPath() != null) {
						for (HwPathElement elem : hwAccessElement.getAccessPath().getPathElements()) {
							final Vertex cur = transformed.get(elem);
							model.connect(prev, cur, EdgeTrait.AbstractPhysicalConnection);
							prev = cur;
						}
					}
					model.connect(prev, transformed.get(hwAccessElement.getDestination()),
							EdgeTrait.AbstractPhysicalConnection);
				}
			}
		}*/
    }

    default void oSModelToBinding(Amalthea amalthea, ForSyDeSystemGraph model, Map<INamed, Vertex> transformed) {
        for (OperatingSystem os: amalthea.getOsModel().getOperatingSystems()) {
            for(TaskScheduler taskScheduler : os.getTaskSchedulers()) {
                final Vertex platformVertex = new Vertex(os.getName() + "." + taskScheduler.getName(), VertexTrait.PlatformLayer);
                if (taskScheduler.getSchedulingAlgorithm() instanceof FixedPriorityPreemptive) {
                    platformVertex.addTraits(VertexTrait.FixedPriorityScheduler);
                }
                transformed.put(os, platformVertex);
                transformed.put(taskScheduler, platformVertex);
                model.addVertex(platformVertex);
                for (SchedulerAllocation allocation : amalthea.getMappingModel().getSchedulerAllocation()) {
                    if (allocation.getScheduler().equals(taskScheduler)) {
                        final Vertex puVertex = transformed.get(allocation.getExecutingPU());
                        model.connect(platformVertex, puVertex, EdgeTrait.AbstractAllocation);
                    }
                }
            }
        }
    }

    default Long fromFrequencyToLong(Frequency freq) {
        double multiplier = 1.0;
        switch (freq.getUnit()) {
            case HZ:
                multiplier = 1.0;
                break;
            case KHZ:
                multiplier = 1000.0;
                break;
            case MHZ:
                multiplier = 1000000.0;
                break;
            case GHZ:
                multiplier = 1000000000.0;
                break;
            default:
                break;
        }
        return Double.valueOf(freq.getValue() * multiplier).longValue();
    }
}
