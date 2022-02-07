package forsyde.io.java.generator.specs;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
public class VertexTraitSpec {

    public String name;
    public String comment = "";
    @JsonIdentityReference(alwaysAsId = true)
    @JsonSerialize(contentAs = VertexTraitSpec.class)
    public List<VertexTraitSpec> refinedTraits = new ArrayList<>();
    @JsonAlias("required_ports")
    public List<PortSpec> requiredPorts = new ArrayList<>();
    @JsonAlias("required_properties")
    public List<PropertySpec> requiredProperties = new ArrayList<>();
}
