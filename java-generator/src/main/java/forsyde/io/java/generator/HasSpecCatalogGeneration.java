package forsyde.io.java.generator;

import forsyde.io.java.generator.specs.EdgeTraitSpec;
import forsyde.io.java.generator.specs.PropertyTypeSpec;
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
            } else if (e.getValue().incoming) {
                output.append(" An incoming port of ");
            } else if (e.getValue().outgoing) {
                output.append(" An outgoing port of ");
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
        output.append("\n\n");
        output.append("Required properties:").append("\n\n");
        for (var e : vertexTraitSpec.requiredProperties.entrySet()) {
            output.append(" - **").append(e.getKey()).append("** (`").append(e.getValue().type != null ? getMarkdownDocumentation(e.getValue().type) : "Anything").append("`): ");
            output.append(e.getValue().htmlDescription != null && !e.getValue().htmlDescription.isBlank() ? e.getValue().htmlDescription : "No description exists. ");
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

    default String getMarkdownDocumentation(PropertyTypeSpec propertyTypeSpec) {
        if (propertyTypeSpec instanceof PropertyTypeSpec.MapPropertyType mapPropertyType) {
            return "Map<" + mapPropertyType.getKeyType().map(this::getMarkdownDocumentation).orElse("Anything") + "," + mapPropertyType.getValueType().map(this::getMarkdownDocumentation).orElse("Anything") + ">";
        } else if (propertyTypeSpec instanceof PropertyTypeSpec.ArrayPropertyType arrayPropertyType) {
            return "Array<" + arrayPropertyType.getValueType().map(this::getMarkdownDocumentation).orElse("Anything") + ">";
        } else if (propertyTypeSpec instanceof PropertyTypeSpec.IntegerPropertyType integerPropertyType) {
            return "Integer<" + integerPropertyType.bits + " bits, " + (integerPropertyType.unsigned ? "unsigned" : "signed") + ">";
        } else if (propertyTypeSpec instanceof PropertyTypeSpec.RealPropertyType realPropertyType) {
            return "Real<" + realPropertyType.bits + " bits>";
        } else if (propertyTypeSpec instanceof PropertyTypeSpec.StringPropertyType stringPropertyType) {
            return "String";
        } else if (propertyTypeSpec instanceof PropertyTypeSpec.BooleanPropertyType booleanPropertyType) {
            return "Boolean";
        }
        return "Anything";
    }
}
