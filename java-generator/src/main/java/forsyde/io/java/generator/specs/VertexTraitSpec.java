package forsyde.io.java.generator.specs;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.*;

@JsonSerialize
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
public class VertexTraitSpec {

    public String name;
    @JsonAlias("html_description")
    public String htmlDescription = "";
    @JsonAlias("refined_traits")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonSerialize(contentAs = VertexTraitSpec.class)
    public Set<VertexTraitSpec> refinedTraits = new HashSet<>();
    public transient List<String> absoluteRefinedTraitNames = new ArrayList<>();
    public transient List<String> relativeRefinedTraitNames = new ArrayList<>();
    @JsonAlias("required_ports")
    public Map<String, PortSpec> requiredPorts = new HashMap<>();
    @JsonAlias("required_properties")
    public Map<String, PropertySpec> requiredProperties = new HashMap<>();

//    @Override
//    public String getName() {
//        return name;
//    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VertexTraitSpec{");
        sb.append("name='").append(name).append('\'');
        sb.append(", refinedTraits=").append(refinedTraits);
        sb.append('}');
        return sb.toString();
    }

}
