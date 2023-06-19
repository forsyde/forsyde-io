package forsyde.io.java.amalthea.adapters.mixins;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.Vertex;
import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.app4mc.amalthea.model.Amalthea;
import org.eclipse.app4mc.amalthea.model.INamed;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class ForSyDeToAmaltheaAdapter implements ForSyDe2AmaltheaHWAdapterMixin, ForSyDe2AmaltheaOSAdapterMixin
, ForSyDe2AmaltheaMappingAdapterMixin, LF2AmaltheaAdapterMixin, ForSyDe2AmaltheaStimulusMixin, ForSyDe2AmaltheaSWMixin {

    protected Set<Pair<Vertex, INamed>> equivalences = new HashSet<>();

    public void convert(SystemGraph inputModel, Amalthea target) {
        fromStimulusToForSyDe(inputModel, target);
        fromVertexesToHWModel(inputModel, target);
        fromVertexesToOSModel(inputModel, target);
        fromForSyDeToSW(inputModel, target);
        fromEdgesToMappings(inputModel, target);
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
