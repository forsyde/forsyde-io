package trait.hierarchy.meta.builders

import forsyde.io.trait.hierarchy.HierarchyFactory
import forsyde.io.trait.hierarchy.TraitHierarchy
import forsyde.io.trait.hierarchy.VertexTraitSpec

class TraitHierarchyBuilder {

    TraitHierarchyBuilder parentTraitHierarchyBuilder;

    TraitHierarchy traitHierarchy = HierarchyFactory.INSTANCE.createTraitHierarchy();

    VertexTraitBuilder vertex(String name) {
        def v = new VertexTraitBuilder(name)
        traitHierarchy.getVertexTraits().add(v)
        return v
    }

    VertexTraitSpec queryVertexByName(String name) {
        for (VertexTraitSpec t : traitHierarchy.vertexTraits) {
            if (t.name == name) return t
        }
        if (parentTraitHierarchyBuilder != null) return queryVertexByName(name)
    }

    TraitHierarchy build() {
        return traitHierarchy
    }
}
