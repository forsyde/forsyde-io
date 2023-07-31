package forsyde.io.java.generator.specs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class TraitHierarchy {

    public List<VertexTraitSpec> vertexTraits = new ArrayList<>();
    public List<EdgeTraitSpec> edgeTraits = new ArrayList<>();
}
