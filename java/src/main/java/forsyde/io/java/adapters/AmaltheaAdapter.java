package forsyde.io.java.adapters;

import forsyde.io.java.adapters.amalthea.*;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.app4mc.amalthea.model.*;

import java.util.*;
import java.util.stream.Stream;

public class AmaltheaAdapter implements ModelAdapter<Amalthea>, Amalthea2ForSyDeAdapterMixin, ForSyDe2AmaltheaMappingAdapterMixin, LF2AmaltheaAdapterMixin,
	ForSyDe2AmaltheaHWAdapter, ForSyDe2AmaltheaOSAdapterMixin,
	EquivalenceModel2ModelMixin<Vertex, INamed> {

	protected Set<Pair<Vertex, INamed>> equivalences = new HashSet<>();

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
