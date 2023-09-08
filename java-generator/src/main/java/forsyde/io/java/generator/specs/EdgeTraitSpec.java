package forsyde.io.java.generator.specs;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.*;
import java.util.stream.Collectors;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
public class EdgeTraitSpec {

    public String canonicalName;
    @JsonAlias("html_description")
    public String htmlDescription = "";
    @JsonAlias("refined_traits")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonSerialize(contentAs = VertexTraitSpec.class)
    public Set<EdgeTraitSpec> refinedTraits = new HashSet<>();
    public transient List<String> absoluteRefinedTraitNames = new ArrayList<>();
    public transient List<String> relativeRefinedTraitNames = new ArrayList<>();
    public transient List<VertexTraitSpec> sourceTraits = new ArrayList<>();
    public transient List<VertexTraitSpec> targetTraits = new ArrayList<>();
    public transient List<String> sourceTraitNames = new ArrayList<>();
    public transient List<String> targetTraitNames = new ArrayList<>();

    public transient TraitHierarchySpec traitHierarchySpec;

    public List<String> prefixNamespace() {
        var l = canonicalName.split("::");
        return Arrays.stream(l).limit(l.length - 1).collect(Collectors.toList());
    }

    public String getSimpleName() {
        var l = canonicalName.split("::");
        return l[l.length - 1];
    }

    public String getJavaCanonicalName() {
        return canonicalName.replace("::", ".");
    }

}
