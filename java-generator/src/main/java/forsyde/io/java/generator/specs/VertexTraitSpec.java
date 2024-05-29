package forsyde.io.java.generator.specs;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.*;
import java.util.stream.Collectors;

@JsonSerialize
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "canonicalName")
public class VertexTraitSpec {

    public String canonicalName;
    @JsonAlias("html_description")
    public String htmlDescription = "";
    @JsonIdentityReference(alwaysAsId = true)
    @JsonSerialize(contentAs = VertexTraitSpec.class)
    @JsonAlias("refined_traits")
    public Set<VertexTraitSpec> refinedTraits = new HashSet<>();
    public transient List<String> absoluteRefinedTraitNames = new ArrayList<>();
    public transient List<String> relativeRefinedTraitNames = new ArrayList<>();
    @JsonAlias("required_ports")
    public Map<String, PortSpec> requiredPorts = new HashMap<>();
    @JsonAlias("required_properties")
    public Map<String, PropertySpec> requiredProperties = new HashMap<>();

    public transient TraitHierarchySpec hierarchySpec = null;

//    @Override
//    public String getName() {
//        return name;
//    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VertexTraitSpec{");
        sb.append("name='").append(canonicalName).append('\'');
        sb.append(", refinedTraits=").append(refinedTraits);
        sb.append('}');
        return sb.toString();
    }

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
