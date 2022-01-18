package trait.hierarchy.meta.builders

import forsyde.io.trait.hierarchy.HierarchyFactory
import forsyde.io.trait.hierarchy.PortSpec
import forsyde.io.trait.hierarchy.VertexPropertySpec
import forsyde.io.trait.hierarchy.VertexTraitSpec

class VertexTraitBuilder {

    TraitHierarchyBuilder containingBuilder;

    VertexTraitSpec vertexTraitSpec = HierarchyFactory.INSTANCE.createVertexTraitSpec();

    VertexTraitBuilder(String name) {
        vertexTraitSpec.setName(name)
    }

    VertexTraitBuilder refines(String... traitNames) {
        for (String name : traitNames) {
            vertexTraitSpec.refinedTraits.add(containingBuilder.queryVertexByName(name))
        }
        return this
    }

    def is(@DelegatesTo(VertexTraitBodyBuilder) Closure body) {
        def b = new VertexTraitBodyBuilder()
        def code = body.rehydrate(b, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
        for (PortSpec portSpec: b.portSpecList)
            vertexTraitSpec.requiredPorts.add(portSpec)
        for (VertexPropertySpec vertexPropertySpec: b.vertexPropertySpecList)
            vertexTraitSpec.requiredProperties.add(vertexPropertySpec)
    }

    VertexTraitSpec build() {
        return vertexTraitSpec
    }

}
