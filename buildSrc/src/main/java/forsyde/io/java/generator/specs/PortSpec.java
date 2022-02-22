package forsyde.io.java.generator.specs;

import com.fasterxml.jackson.annotation.*;

import java.util.Optional;


public class PortSpec {

    public String name;
    @JsonIdentityReference(alwaysAsId = true)
    public VertexTraitSpec vertexTrait;
    public EdgeTraitSpec edgeTraitSpec;
    public transient String vertexTraitName;
    public transient String edgeTraitName;
    public Optional<Boolean> multiple = Optional.of(true);
    public Optional<Boolean> ordered = Optional.of(false);
    public PortDirection direction = PortDirection.BIDIRECTIONAL;

}
