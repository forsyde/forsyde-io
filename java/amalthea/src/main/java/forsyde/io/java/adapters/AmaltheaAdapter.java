package forsyde.io.java.adapters;

import forsyde.io.java.adapters.amalthea.*;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.app4mc.amalthea.model.Amalthea;
import org.eclipse.app4mc.amalthea.model.AmaltheaFactory;
import org.eclipse.app4mc.amalthea.model.HwStructure;
import org.eclipse.app4mc.amalthea.model.INamed;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class AmaltheaAdapter implements ModelAdapter<Amalthea>, Amalthea2ForSyDeAdapterMixin, ForSyDe2AmaltheaMappingAdapterMixin, LF2AmaltheaAdapterMixin,
		ForSyDe2AmaltheaHWAdapter, ForSyDe2AmaltheaOSAdapterMixin,
        EquivalenceModel2ModelMixin<Vertex, INamed> {

	protected Set<Pair<Vertex, INamed>> equivalences = new HashSet<>();

	@Override
	public ForSyDeSystemGraph convert(Amalthea inputModel) {
		final ForSyDeSystemGraph forSyDeSystemGraph = new ForSyDeSystemGraph();
		Map<INamed, Vertex> transformed = Map.of();
		for (HwStructure structure : inputModel.getHwModel().getStructures()) {
			transformed = fromStructureToVertex(forSyDeSystemGraph, structure);
		}
		// edges after all vertexes axist
		for (HwStructure structure : inputModel.getHwModel().getStructures()) {
			fromStructureToEdges(forSyDeSystemGraph, structure, transformed);
		}
		oSModelToBinding(inputModel, forSyDeSystemGraph, transformed);
		return forSyDeSystemGraph;
	}

	@Override
	public Amalthea convert(ForSyDeSystemGraph inputModel) {
		Amalthea target = AmaltheaFactory.eINSTANCE.createAmalthea();
		fromVertexesToHWModel(inputModel, target);
		fromVertexesToOSModel(inputModel, target);
		convertToSWModel(inputModel, target);
		fromEdgesToMappings(inputModel, target);
		return target;
	}

	@Override
	public Stream<INamed> equivalents(Vertex source) {
		return equivalences.stream().filter(p -> p.getLeft().equals(source)).map(Pair::getRight);
	}

	@Override
	public void addEquivalence(Vertex source, INamed elem) {
		equivalences.add(Pair.of(source, elem));
	}


	@Override
	public boolean contains(INamed elem) {
		return equivalences.stream().anyMatch(p -> p.getRight().equals(elem));
	}

	@Override
	public boolean hasEquivalent(Vertex elem) {
		return equivalences.stream().anyMatch(p -> p.getLeft().equals(elem));
	}
}
