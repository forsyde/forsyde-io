package forsyde.io.java.adapters;

import forsyde.io.java.adapters.amalthea.ForSyDe2AmaltheaHWAdapter;
import forsyde.io.java.adapters.amalthea.ForSyDe2AmaltheaMappingAdapter;
import forsyde.io.java.adapters.amalthea.ForSyDe2AmaltheaOSAdapter;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.viewers.*;
import org.eclipse.app4mc.amalthea.model.*;

import java.lang.System;
import java.util.*;
import java.util.stream.Collectors;

public class AmaltheaAdapter implements ModelAdapter<Amalthea> {

	@Override
	public ForSyDeModel convert(Amalthea inputModel) {
		final ForSyDeModel forSyDeModel = new ForSyDeModel();
		Map<INamed, Vertex> transformed = Map.of();
		for (HwStructure structure : inputModel.getHwModel().getStructures()) {
			transformed = fromStructureToVertex(forSyDeModel, structure);
		}
		// edges after all vertexes axist
		for (HwStructure structure : inputModel.getHwModel().getStructures()) {
			fromStructureToEdges(forSyDeModel, structure, transformed);
		}
		oSModelToBinding(inputModel, forSyDeModel, transformed);
		return forSyDeModel;
	}

	@Override
	public Amalthea convert(ForSyDeModel inputModel) {
		Amalthea target = AmaltheaFactory.eINSTANCE.createAmalthea();
		Map<Vertex, INamed> transformed = new HashMap<>();
		ForSyDe2AmaltheaHWAdapter.fromVertexesToHWModel(inputModel, target, transformed);
		ForSyDe2AmaltheaOSAdapter.fromVertexesToOSModel(inputModel, target, transformed);
		ForSyDe2AmaltheaMappingAdapter.fromEdgesToMappings(inputModel, target, transformed);
		return target;
	}

	protected void fromPUIntoVertex(ProcessingUnit pu, Vertex v) {
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

	protected void fromCUIntoVertex(ConnectionHandler connectionHandler, Vertex v) {
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

	protected Map<INamed, Vertex> fromStructureToVertex(ForSyDeModel model, HwStructure structure) {
		return fromStructureToVertex(model, structure, "");
	}

	protected Map<INamed, Vertex> fromStructureToVertex(ForSyDeModel model, HwStructure structure,
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

	protected void fromStructureToEdges(ForSyDeModel model, HwStructure structure,
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

	protected void oSModelToBinding(Amalthea amalthea, ForSyDeModel model, Map<INamed, Vertex> transformed) {
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

	public void fromVertexesToModules(ForSyDeModel model, Amalthea target, Map<Vertex, ReferableBaseObject> transformed) {
		final Set<AbstractDigitalModule> modules = model.vertexSet()
				.stream().filter(AbstractDigitalModule::conforms)
				.map(v -> AbstractDigitalModule.safeCast(v).get())
				.collect(Collectors.toSet());
		for (AbstractDigitalModule p : modules) {
			if (GenericProcessingModule.conforms(p)) {

			}
		}
	}

	public void fromVertexToPU(GenericProcessingModule pu, Map<Vertex, ReferableBaseObject> transformed) {
		ProcessingUnit amaltheaPu = AmaltheaFactory.eINSTANCE.createProcessingUnit();
		amaltheaPu.setName(pu.getViewedVertex().getIdentifier());
		transformed.put(pu.getViewedVertex(), amaltheaPu);
	}

	public void fromVertexesToStructures(ForSyDeModel model, Map<Vertex, ReferableBaseObject> transformed) {
		final Set<AbstractStructure> platforms = model.vertexSet()
				.stream().filter(AbstractStructure::conforms)
				.map(v -> AbstractStructure.safeCast(v).get())
				.collect(Collectors.toSet());
		for (AbstractStructure p : platforms) {
			HwStructure hwStructure = AmaltheaFactory.eINSTANCE.createHwStructure();
			hwStructure.setName(p.getViewedVertex().getIdentifier());
			transformed.put(p.getViewedVertex(), hwStructure);
		}
		for (AbstractStructure parent : platforms) {
			for (AbstractStructure child : platforms) {
				if (model.hasConnection(parent, child, "submodules", null)) {
					final HwStructure parentHwStructure = (HwStructure) transformed.get(parent.getViewedVertex());
					final HwStructure childHwStructure = (HwStructure) transformed.get(child.getViewedVertex());
					parentHwStructure.getStructures().add(childHwStructure);
					// remove the 'namespace' from the child identifier
					if (child.getViewedVertex().getIdentifier().contains(
							"." + parent.getViewedVertex().getIdentifier()
					)) {
						final String newName = child.getViewedVertex().getIdentifier()
								.replace("." + parent.getViewedVertex().getIdentifier(), "");
						childHwStructure.setName(newName);
					}
				}
			}
		}
	}

	protected Long fromFrequencyToLong(Frequency freq) {
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
