package forsyde.io.java.adapters;

import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.viewers.GenericDigitalInterconnectViewer;
import forsyde.io.java.typed.viewers.RoundRobinInterconnectViewer;
import org.eclipse.app4mc.amalthea.model.*;

import java.lang.System;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;

public class AmaltheaAdapter implements ModelAdapter<Amalthea> {

    @Override
    public ForSyDeModel convert(Amalthea inputModel) {
        final ForSyDeModel forSyDeModel = new ForSyDeModel();
        for (HwStructure structure: inputModel.getHwModel().getStructures()) {
            fromStructureToVertex(forSyDeModel, structure);
        }
        return forSyDeModel;
    }

    @Override
    public Amalthea convert(ForSyDeModel inputModel) {
        return null;
    }

    protected Vertex fromStructureToVertex(ForSyDeModel model, HwStructure structure) {
        return fromStructureToVertex(model, structure, "");
    }

    protected Vertex fromStructureToVertex(ForSyDeModel model, HwStructure structure, String prefix) {
        final Vertex structureVertex = new Vertex(prefix + structure.getName(), VertexTrait.AbstractStructure);
        model.addVertex(structureVertex);
        structureVertex.ports.add("submodules");
        for (HwPort port : structure.getPorts()) {
            structureVertex.ports.add(port.getName());
        }
        for (HwStructure childStructure : structure.getStructures()) {
            final Vertex childStructureVertex = fromStructureToVertex(model, childStructure, prefix + structure.getName() + ".");
            model.connect(structureVertex, childStructureVertex, "submodules", EdgeTrait.AbstractStructuralConnection);
        }
        for (HwModule module: structure.getModules()) {
            final Vertex moduleVertex = new Vertex(prefix + structure.getName() + "." + module.getName());
            moduleVertex.putProperty("nominal_frequency_in_hertz",
                    fromFrequencyToLong(module.getFrequencyDomain().getDefaultValue()));
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
                final GenericDigitalInterconnectViewer interconnectVertex = new GenericDigitalInterconnectViewer(moduleVertex);
                interconnectVertex.setMaxConcurrentFlits(connectionHandler.getDefinition().getMaxConcurrentTransfers());
                // burst size is always in B, it seems
                interconnectVertex.setMaxFlitSizeInBits(Long.valueOf(connectionHandler.getDefinition().getMaxBurstSize() * 8));
                switch (connectionHandler.getDefinition().getPolicy()) {
                    case ROUND_ROBIN:
                        moduleVertex.addTraits(VertexTrait.RoundRobinInterconnect);
                        final RoundRobinInterconnectViewer rrVertex = new RoundRobinInterconnectViewer(moduleVertex);
                        rrVertex.setAllocatedWeights(new HashMap<>());
                        rrVertex.setTotalWeights(0);
                        break;
                    default: break;
                }
                moduleVertex.addTraits(VertexTrait.GenericDigitalInterconnect);
            }
            model.addVertex(moduleVertex);
            model.connect(structureVertex, moduleVertex, "submodules", EdgeTrait.AbstractStructuralConnection);
        }
        return structureVertex;
    }

    protected Long fromFrequencyToLong(Frequency freq) {
        Double multiplier = 1.0;
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
