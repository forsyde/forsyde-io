package forsyde.io.java.generator.specs;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
public class VertexTraitSpec {

    public String name;
    public String comment = "";
    @JsonIdentityReference(alwaysAsId = true)
    @JsonSerialize(contentAs = VertexTraitSpec.class)
    public List<VertexTraitSpec> refinedTraits = new ArrayList<>();
    public transient List<String> absoluteRefinedTraitNames = new ArrayList<>();
    public transient List<String> relativeRefinedTraitNames = new ArrayList<>();
    @JsonAlias("required_ports")
    public List<PortSpec> requiredPorts = new ArrayList<>();
    @JsonAlias("required_properties")
    public List<PropertySpec> requiredProperties = new ArrayList<>();

    public List<String> getNamespaces() {
        final List<String> names = name.startsWith("::") ?
                Arrays.asList(name.replaceFirst("::", "").split("::")) :
                Arrays.asList(name.split("::"));
        return names.subList(0, names.size()-1);
    }

    public String getTraitLocalName() {
        final List<String> names = name.startsWith("::") ?
                Arrays.asList(name.replaceFirst("::", "").split("::")) :
                Arrays.asList(name.split("::"));
        return names.get(names.size()-1);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VertexTraitSpec{");
        sb.append("name='").append(name).append('\'');
        sb.append(", refinedTraits=").append(refinedTraits);
        sb.append('}');
        return sb.toString();
    }
}
