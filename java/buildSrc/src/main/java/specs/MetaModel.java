package specs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaModel {
    public List<VertexTraitSpec> vertexTraits;
    public List<EdgeTraitSpec> edgeTraits;
}
