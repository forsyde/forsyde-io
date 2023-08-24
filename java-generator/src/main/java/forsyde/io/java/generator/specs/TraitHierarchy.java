package forsyde.io.java.generator.specs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class TraitHierarchy {
    public Map<String, TraitHierarchy> subHierarchies = new HashMap();
    public Map<String, VertexTraitSpec> vertexTraits = new HashMap();
    public Map<String, EdgeTraitSpec> edgeTraits = new HashMap();
}
