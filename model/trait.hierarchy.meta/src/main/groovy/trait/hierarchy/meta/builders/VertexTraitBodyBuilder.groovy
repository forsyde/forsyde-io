package trait.hierarchy.meta.builders

import forsyde.io.trait.hierarchy.PortSpec
import forsyde.io.trait.hierarchy.VertexPropertySpec
import forsyde.io.trait.hierarchy.VertexTraitSpec

class VertexTraitBodyBuilder {

    List<PortSpec> portSpecList = new ArrayList<>();
    List<VertexPropertySpec> vertexPropertySpecList = new ArrayList<>();

    VertexTraitPortBuilder port(String name) {
        def p = new VertexTraitPortBuilder(name)
        portSpecList.add(p.build())
        return p
    }

}
