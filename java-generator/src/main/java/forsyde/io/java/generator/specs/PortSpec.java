package forsyde.io.java.generator.specs;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Optional;

@JsonSerialize
public class PortSpec {

    public String name;
    @JsonAlias("vertex_trait")
    @JsonIdentityReference(alwaysAsId = true)
    public VertexTraitSpec vertexTrait;

    @JsonAlias("edge_trait")
    @JsonIdentityReference(alwaysAsId = true)
    public EdgeTraitSpec edgeTrait;
    public transient String absoluteVertexTraitName;
    public transient String absoluteEdgeTraitName;
    public transient String relativeVertexTraitName;
    public transient String relativeEdgeTraitName;
    public boolean multiple = true;
    public boolean optional = false;
    public boolean outgoing = true;
    public boolean incoming = true;



}
