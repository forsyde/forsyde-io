package forsyde.io.java.generator;

import forsyde.io.java.generator.specs.EdgeTraitSpec;
import forsyde.io.java.generator.specs.TraitHierarchySpec;
import forsyde.io.java.generator.specs.VertexTraitSpec;

import java.util.Collection;
import java.util.stream.Collectors;

public interface HasSpecCatalogGeneration {

    default String getMarkdownDocumentation(Collection<TraitHierarchySpec> traitHierarchySpecs, int headerLevel) {
        StringBuilder output = new StringBuilder();
//        output.append("#".repeat(Math.max(0, headerLevel)));
        for (TraitHierarchySpec traitHierarchySpec : traitHierarchySpecs) {
            output.append(getMarkdownDocumentation(traitHierarchySpec, headerLevel + 1)).append("\n");
        }
        return output.toString();
    }

    default String getMarkdownDocumentation(TraitHierarchySpec traitHierarchySpec, int headerLevel) {
        StringBuilder output = new StringBuilder();
        output.append("#".repeat(Math.max(0, headerLevel))).append(" ");
        output.append(traitHierarchySpec.canonicalName).append("\n\n");
        for (var v : traitHierarchySpec.vertexTraits.values()) {
            output.append(getMarkdownDocumentation(v, headerLevel + 1)).append("\n");
        }
        return output.toString();
    }

    default String getMarkdownDocumentation(VertexTraitSpec vertexTraitSpec, int headerLevel) {
        StringBuilder output = new StringBuilder();
        output.append("#".repeat(Math.max(0, headerLevel))).append(" ");
        output.append(vertexTraitSpec.canonicalName).append("\n\n");
        output.append(vertexTraitSpec.htmlDescription != null ? vertexTraitSpec.htmlDescription : "No description exists.").append("\n\n");
        output.append("Refines: ").append(vertexTraitSpec.refinedTraits.stream().map(x -> '`' + x.canonicalName + '`').collect(Collectors.joining(", "))).append("\n\n");
        output.append("Required ports:").append("\n\n");
        for (var e : vertexTraitSpec.requiredPorts.entrySet()) {
            output.append(" - **").append(e.getKey()).append("**: ");
            if (e.getValue().incoming && e.getValue().outgoing) {
                output.append(" A bidirectional port of ");
            }
            if (e.getValue().multiple && !e.getValue().optional) {
                output.append(" multiple ");
            }
            output.append('`').append(e.getValue().vertexTrait.canonicalName).append('`').append(" vertices");
            if (e.getValue().edgeTrait != null) {
                output.append(" connected by ").append('`').append(e.getValue().edgeTrait.canonicalName).append('`').append(" edges");
            }
            output.append(".\n");
        }
        output.append("Required properties:").append("\n\n");
        for (var e : vertexTraitSpec.requiredProperties.entrySet()) {
            output.append(" - **").append(e.getKey()).append(":** ");
            output.append(".\n");
        }
        return output.toString();
    }

    default String getMarkdownDocumentation(EdgeTraitSpec edgeTraitSpec, int headerLevel) {
        StringBuilder output = new StringBuilder();
        output.append("#".repeat(Math.max(0, headerLevel))).append(" ");
        output.append(edgeTraitSpec.canonicalName).append("\n\n");
        output.append(edgeTraitSpec.htmlDescription != null ? edgeTraitSpec.htmlDescription : "No description exists.").append("\n\n");
        output.append("#".repeat(Math.max(0, headerLevel + 1))).append(" ");
        return output.toString();
    }
}
