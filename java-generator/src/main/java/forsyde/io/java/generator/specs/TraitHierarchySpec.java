package forsyde.io.java.generator.specs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.lang.model.element.PackageElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class TraitHierarchySpec {
    public List<String> prefixNamespace = new ArrayList<>();
    public Map<String, VertexTraitSpec> vertexTraits = new HashMap();
    public Map<String, EdgeTraitSpec> edgeTraits = new HashMap();
}
