package forsyde.io.java.adapters;

import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.viewers.*;
import org.eclipse.app4mc.amalthea.model.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AmaltheaAdapter implements ModelAdapter<Amalthea> {

	@Override
	public ForSyDeModel convert(Amalthea inputModel) {
		final ForSyDeModel forSyDeModel = new ForSyDeModel();
		Map<ReferableBaseObject, Vertex> transformed = Map.of();
		for (HwStructure structure : inputModel.getHwModel().getStructures()) {
			transformed = fromStructureToVertex(forSyDeModel, structure);
		}
		// edges after all vertexes axist
		for (HwStructure structure : inputModel.getHwModel().getStructures()) {
			fromStructureToEdges(forSyDeModel, structure, transformed);
		}
		return forSyDeModel;
	}

	@Override
	public Amalthea convert(ForSyDeModel inputModel) {
		Map<Vertex, ReferableBaseObject> transformed = new HashMap<>();
		return null;
	}

	protected Map<ReferableBaseObject, Vertex> fromStructureToVertex(ForSyDeModel model, HwStructure structure) {
		return fromStructureToVertex(model, structure, "");
	}

	protected Map<ReferableBaseObject, Vertex> fromStructureToVertex(ForSyDeModel model, HwStructure structure,
			String prefix) {
		final Vertex structureVertex = new Vertex(prefix + structure.getName(), VertexTrait.AbstractStructure);
		final HashMap<ReferableBaseObject, Vertex> transformed = new HashMap<>();
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
						.setMaxFlitSizeInBits(Long.valueOf(connectionHandler.getDefinition().getMaxBurstSize() * 8));
				switch (connectionHandler.getDefinition().getPolicy()) {
				case ROUND_ROBIN:
					moduleVertex.addTraits(VertexTrait.RoundRobinInterconnect);
					final RoundRobinInterconnectViewer rrVertex = new RoundRobinInterconnectViewer(moduleVertex);
					rrVertex.setAllocatedWeights(new HashMap<>());
					rrVertex.setTotalWeights(0);
					break;
				default:
					break;
				}
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
			Map<ReferableBaseObject, Vertex> transformed) {
		for (HwStructure childStructure : structure.getStructures()) {
			fromStructureToEdges(model, childStructure, transformed);
		}
		for (HwModule module : structure.getModules()) {
			final Vertex moduleVertex = transformed.get(module);
			if (module instanceof ProcessingUnit) {
				final ProcessingUnit processingUnit = (ProcessingUnit) module;
				for (HwAccessElement hwAccessElement : processingUnit.getAccessElements()) {
					Vertex prev = transformed.get(hwAccessElement.getSource());
					for (HwPathElement elem : hwAccessElement.getAccessPath().getPathElements()) {
						final Vertex cur = transformed.get(elem);
						model.connect(prev, cur, EdgeTrait.AbstractPhysicalConnection);
						prev = cur;
					}
					model.connect(prev, transformed.get(hwAccessElement.getDestination()),
							EdgeTrait.AbstractPhysicalConnection);
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

		}
	}

	public void fromVertexToPU(ForSyDeModel model, Amalthea target, GenericProcessingModule pu, Map<Vertex, ReferableBaseObject> transformed) {
		ProcessingUnit amaltheaPu = AmaltheaFactory.eINSTANCE.createProcessingUnit();
		amaltheaPu.setName(pu.getViewedVertex().getIdentifier());
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
