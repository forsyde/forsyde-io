package forsyde.io.java.generator.specs;

import com.fasterxml.jackson.annotation.*;

import java.util.Optional;


public class PortSpec implements SourceTraceableSpec {

    public transient int declaredLine = 0;
    public transient int declaredColumn = 0;
    public String name;
    @JsonIdentityReference(alwaysAsId = true)
    public VertexTraitSpec vertexTrait;
    public EdgeTraitSpec edgeTraitSpec;
    public transient String absoluteVertexTraitName;
    public transient String absoluteEdgeTraitName;
    public transient String relativeVertexTraitName;
    public transient String relativeEdgeTraitName;
    public Optional<Boolean> multiple = Optional.of(true);
    public Optional<Boolean> ordered = Optional.of(false);
    public PortDirection direction = PortDirection.BIDIRECTIONAL;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PortSpec{");
        sb.append("name='").append(name).append('\'');
        sb.append(", vertexTrait=").append(vertexTrait);
        sb.append(", edgeTraitSpec=").append(edgeTraitSpec);
        sb.append(", multiple=").append(multiple);
        sb.append(", ordered=").append(ordered);
        sb.append(", direction=").append(direction);
        sb.append('}');
        return sb.toString();
    }


    @Override
    public int getLine() {
        return declaredLine;
    }

    @Override
    public int getColumn() {
        return declaredColumn;
    }

}
