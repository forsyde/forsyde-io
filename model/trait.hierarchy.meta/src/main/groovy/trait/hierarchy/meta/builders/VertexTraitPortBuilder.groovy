package trait.hierarchy.meta.builders

import forsyde.io.trait.hierarchy.HierarchyFactory
import forsyde.io.trait.hierarchy.PortDirectionEnum
import forsyde.io.trait.hierarchy.PortSpec

class VertexTraitPortBuilder {

    PortSpec portSpec = HierarchyFactory.INSTANCE.createPortSpec();

    VertexTraitPortBuilder(String name) {
        portSpec.setName(name)
        portSpec.setDirection(PortDirectionEnum.BIDIRECTIONAL)
        portSpec.setMultiple(false)
        portSpec.setOrdered(false)
    }

    VertexTraitPortBuilder is(String... modifiers) {
        if (modifiers.contains("multiple"))
            portSpec.multiple = true
        else if (modifiers.contains("single"))
            portSpec.multiple = false
        if (modifiers.contains("ordered"))
            portSpec.ordered = true
        else if (modifiers.contains("unordered"))
            portSpec.ordered = false
        if (modifiers.contains("in"))
            portSpec.direction = PortDirectionEnum.BACKWARD
        else if (modifiers.contains("out"))
            portSpec.direction = PortDirectionEnum.FORWARD
        return this
    }

    VertexTraitPortBuilder of(String name) {
        return this
    }

    PortSpec build() {
        return portSpec
    }

}
