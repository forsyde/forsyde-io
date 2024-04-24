package forsyde.io.java.generator.specs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class TraitHierarchySpec {

    public String canonicalName;
    public Map<String, VertexTraitSpec> vertexTraits = new HashMap();
    public Map<String, EdgeTraitSpec> edgeTraits = new HashMap();

    public List<String> prefixNamespace() {
        var l = canonicalName.replace(".", "::").split("::");
        return Arrays.stream(l).limit(l.length - 1).collect(Collectors.toList());
    }

    public String getSimpleName() {
        var l = canonicalName.replace(".", "::").split("::");
        return l[l.length - 1];
    }

    public String getJavaCanonicalName() {
        return canonicalName.replace("::", ".");
    }


}
