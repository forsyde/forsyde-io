package forsyde.io.java.generator.specs;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class PortSpec {

    public String name;

    @JsonAlias("html_description")
    public String htmlDescription = "";
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
