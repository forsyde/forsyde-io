package forsyde.io.java.generator;

import forsyde.io.java.generator.dsl.ForSyDeTraitDSLBaseVisitor;
import forsyde.io.java.generator.dsl.ForSyDeTraitDSLParser;
import forsyde.io.java.generator.exceptions.InconsistentTraitHierarchyException;
import forsyde.io.java.generator.specs.*;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ForSyDeIOTraitDSLVisitor extends ForSyDeTraitDSLBaseVisitor<TraitHierarchy> {


    private void linkElements(TraitHierarchy traitHierarchy) throws InconsistentTraitHierarchyException {
        // vertexes
        for (final VertexTraitSpec vertexTraitSpec : traitHierarchy.vertexTraits) {
            // refinements
            final List<String> refinements = new ArrayList<>(vertexTraitSpec.absoluteRefinedTraitNames);
            refinements.addAll(vertexTraitSpec.relativeRefinedTraitNames);
            for (final String refinedName : refinements) {
                final Optional<VertexTraitSpec> refinedtrait = traitHierarchy.vertexTraits.stream().filter(v -> v.name.equals(refinedName)).findAny();
                if (refinedtrait.isPresent())
                    vertexTraitSpec.refinedTraits.add(refinedtrait.get());
                else
                    throw new InconsistentTraitHierarchyException(
                            "Vertex trait '" +
                                    vertexTraitSpec.name +
                                    "' declared at " +
                                    vertexTraitSpec.getLine() + ":" + vertexTraitSpec.getColumn() +
                                    " refines " +
                                    refinedName +
                                    " which does not exist in the hierarchy."
                    );
            }
            // ports
            for (final PortSpec portSpec : vertexTraitSpec.requiredPorts) {
                // at least one of them must be non-null
                final String portVertexTraitName = portSpec.absoluteVertexTraitName != null ? portSpec.absoluteVertexTraitName
                    : portSpec.relativeVertexTraitName;
                final Optional<VertexTraitSpec> portVertexTrait = traitHierarchy.vertexTraits.stream().filter(v -> v.name.equals(portVertexTraitName)).findAny();
                if (portVertexTrait.isPresent())
                    portSpec.vertexTrait = portVertexTrait.get();
                else
                    throw new InconsistentTraitHierarchyException(
                            "Port '" +
                                    portSpec.name +
                                    "' of Vertex trait '" +
                                    vertexTraitSpec.name +
                                    "' declared at " +
                                    portSpec.getLine() + ":" + portSpec.getColumn() +
                                    " specifies vertex trait '" +
                                    portVertexTraitName +
                                    "' which does not exist in the hierarchy."
                    );
                final String portEdgeTraitName = portSpec.absoluteEdgeTraitName != null ? portSpec.absoluteEdgeTraitName :
                        portSpec.relativeEdgeTraitName != null ? portSpec.relativeEdgeTraitName : null;
                if (portEdgeTraitName != null) {
                    final Optional<EdgeTraitSpec> portEdgeTrait = traitHierarchy.edgeTraits.stream().filter(v -> v.name.equals(portEdgeTraitName)).findAny();
                    if (portEdgeTrait.isPresent())
                        portSpec.edgeTraitSpec = portEdgeTrait.get();
                    else
                        throw new InconsistentTraitHierarchyException(
                                "Port '" +
                                        portSpec.name +
                                        "' of Vertex trait '" +
                                        vertexTraitSpec.name +
                                        "' declared at " +
                                        portSpec.getLine() + ":" + portSpec.getColumn() +
                                        " specifies edge trait '" +
                                        portEdgeTraitName +
                                        "' which does not exist in the hierarchy."
                        );
                }
            }
        }

        // edges
        for (final EdgeTraitSpec edgeTraitSpec : traitHierarchy.edgeTraits) {
            // refinements
            final List<String> refinements = new ArrayList<>(edgeTraitSpec.absoluteRefinedTraitNames);
            refinements.addAll(edgeTraitSpec.relativeRefinedTraitNames);
            for (final String refinedName : refinements) {
                final Optional<EdgeTraitSpec> refinedtrait = traitHierarchy.edgeTraits.stream().filter(v -> v.name.equals(refinedName)).findAny();
                if (refinedtrait.isPresent())
                    edgeTraitSpec.refinedTraits.add(refinedtrait.get());
                else
                    throw new InconsistentTraitHierarchyException(
                            "Edge trait '" +
                                    edgeTraitSpec.name +
                                    "' declared at " +
                                    edgeTraitSpec.getLine() + ":" + edgeTraitSpec.getColumn() +
                                    " refines " +
                                    refinedName +
                                    " which does not exist in the hierarchy."
                    );
            }
            // vertexes constraints
            for (final VertexTraitSpec vertexTraitSpec : traitHierarchy.vertexTraits) {
                if (edgeTraitSpec.sourceTraitNames.contains(vertexTraitSpec.name)) {
                    edgeTraitSpec.sourceTraits.add(vertexTraitSpec);
                }
                if (edgeTraitSpec.targetTraitNames.contains(vertexTraitSpec.name)) {
                    edgeTraitSpec.targetTraits.add(vertexTraitSpec);
                }
            }
        }
    }


    public EdgeTraitSpec visitEdgeTraitTyped(ForSyDeTraitDSLParser.EdgeTraitContext ctx) {
        final EdgeTraitSpec edgeTraitSpec = new EdgeTraitSpec();
        edgeTraitSpec.name =ctx.name.getText();
        edgeTraitSpec.declaredLine = ctx.getStart().getLine();
        edgeTraitSpec.declaredColumn = ctx.getStart().getCharPositionInLine();
        for (final Token token : ctx.refinedTraits) {
            //edgeTraitSpec.absoluteRefinedTraitNames.add(token.getText());
            // absolute reference or local reference
            if (token.getText().contains("::")) {
                if (token.getText().startsWith("::"))
                    edgeTraitSpec.absoluteRefinedTraitNames.add(token.getText().substring(2));
                else
                    edgeTraitSpec.absoluteRefinedTraitNames.add(token.getText());
                //vertexRefinedParent.add(token.getText())
            } else {
                // add the namespace in a local reference
                edgeTraitSpec.relativeRefinedTraitNames.add(token.getText());
                //vertexRefinedParent.add(namespace + "::" + token.getText());
            }
        }
        for (final Token token : ctx.sourceVertexes) {
            edgeTraitSpec.sourceTraitNames.add(token.getText());
        }
        for (final Token token : ctx.targetVertexes) {
            edgeTraitSpec.targetTraitNames.add(token.getText());
        }
        return edgeTraitSpec;
    }

    public PortSpec visitVertexPortTyped(ForSyDeTraitDSLParser.VertexPortContext ctx) {
        final PortSpec portSpec = new PortSpec();
        portSpec.name = ctx.name.getText();
        portSpec.declaredLine = ctx.getStart().getLine();
        portSpec.declaredColumn = ctx.getStart().getCharPositionInLine();
        final List<String> modifiers = ctx.modifiers.stream().map(Token::getText).collect(Collectors.toList());
        if (modifiers.contains("ordered")) {
            portSpec.ordered = Optional.of(true);
        } else if (modifiers.contains("unordered")) {
            portSpec.ordered = Optional.of(false);
        }
        if (modifiers.contains("multiple")) {
            portSpec.multiple = Optional.of(true);
        } else if (modifiers.contains("single")) {
            portSpec.multiple = Optional.of(false);
        }
        if (modifiers.contains("in")) {
            portSpec.direction = PortDirection.INCOMING;
        } else if (modifiers.contains("out")) {
            portSpec.direction = PortDirection.OUTGOING;
        } else {
            portSpec.direction = PortDirection.BIDIRECTIONAL;
        }
        // absolute reference or local reference for vertex trait
        if (ctx.connectedVertexTrait.getText().contains("::")) {
            if (ctx.connectedVertexTrait.getText().startsWith("::"))
                portSpec.absoluteVertexTraitName = ctx.connectedVertexTrait.getText().substring(2);
            else
                portSpec.absoluteVertexTraitName = ctx.connectedVertexTrait.getText();
        } else {
            // add the namespace in a local reference
            portSpec.relativeVertexTraitName = ctx.connectedVertexTrait.getText();
        }
        // absolute reference or local reference for edge trait
        if (ctx.connectingEdgeTrait != null) {
            if (ctx.connectingEdgeTrait.getText().contains("::")) {
                if (ctx.connectingEdgeTrait.getText().startsWith("::"))
                    portSpec.absoluteEdgeTraitName = ctx.connectingEdgeTrait.getText().substring(2);
                else
                    portSpec.absoluteEdgeTraitName = ctx.connectingEdgeTrait.getText();
            } else {
                // add the namespace in a local reference
                portSpec.relativeEdgeTraitName = ctx.connectingEdgeTrait.getText();
            }
        }
        return portSpec;
    }

    public PropertyTypeSpec visitVertexPropertyTypeTyped(ForSyDeTraitDSLParser.VertexPropertyTypeContext ctx) {
        return ctx.typeName.getText().equals("int") ? PropertyTypeSpecs.IntVertexProperty() :
                ctx.typeName.getText().equals("integer") ? PropertyTypeSpecs.IntVertexProperty() :
                        ctx.typeName.getText().equals("float") ? PropertyTypeSpecs.FloatVertexProperty() :
                                ctx.typeName.getText().equals("bool") ? PropertyTypeSpecs.BooleanVertexProperty() :
                                        ctx.typeName.getText().equals("boolean") ? PropertyTypeSpecs.BooleanVertexProperty() :
                                                ctx.typeName.getText().equals("long") ? PropertyTypeSpecs.LongVertexProperty() :
                                                        ctx.typeName.getText().equals("double") ? PropertyTypeSpecs.DoubleVertexProperty() :
                                                                ctx.typeName.getText().equals("real") ? PropertyTypeSpecs.DoubleVertexProperty() :
                                                                        ctx.typeName.getText().equals("str") ? PropertyTypeSpecs.StringVertexProperty() :
                                                                                ctx.typeName.getText().equals("string") ? PropertyTypeSpecs.StringVertexProperty() :
                                                                                        ctx.typeName.getText().equals("array") ? PropertyTypeSpecs.ArrayVertexProperty(visitVertexPropertyTypeTyped(ctx.arrayType)) :
                                                                                                ctx.typeName.getText().equals("intmap") ? PropertyTypeSpecs.IntMapVertexProperty(visitVertexPropertyTypeTyped(ctx.intMapType)) :
                                                                                                        ctx.typeName.getText().equals("integermap") ? PropertyTypeSpecs.IntMapVertexProperty(visitVertexPropertyTypeTyped(ctx.intMapType)) :
                                                                                                                ctx.typeName.getText().equals("intMap") ? PropertyTypeSpecs.IntMapVertexProperty(visitVertexPropertyTypeTyped(ctx.intMapType)) :
                                                                                                                        ctx.typeName.getText().equals("integerMap") ? PropertyTypeSpecs.IntMapVertexProperty(visitVertexPropertyTypeTyped(ctx.intMapType)) :
                                                                                                                                ctx.typeName.getText().equals("strmap") ? PropertyTypeSpecs.StringMapVertexProperty(visitVertexPropertyTypeTyped(ctx.strMapType)) :
                                                                                                                                        ctx.typeName.getText().equals("stringmap") ? PropertyTypeSpecs.StringMapVertexProperty(visitVertexPropertyTypeTyped(ctx.strMapType)) :
                                                                                                                                                ctx.typeName.getText().equals("strMap") ? PropertyTypeSpecs.StringMapVertexProperty(visitVertexPropertyTypeTyped(ctx.strMapType)) :
                                                                                                                                                        ctx.typeName.getText().equals("stringMap") ? PropertyTypeSpecs.StringMapVertexProperty(visitVertexPropertyTypeTyped(ctx.strMapType)) :
                                                                                                                                                                PropertyTypeSpecs.StringVertexProperty();
    }

    public PropertySpec visitVertexPropertyTyped(ForSyDeTraitDSLParser.VertexPropertyContext ctx) {
        final PropertySpec propertySpec = new PropertySpec();
        propertySpec.name = ctx.name.getText();
        propertySpec.type = visitVertexPropertyTypeTyped(ctx.vertexPropertyType());
        return propertySpec;
    }

    public VertexTraitSpec visitVertexTraitTyped(ForSyDeTraitDSLParser.VertexTraitContext ctx) {
        final VertexTraitSpec vertexTraitSpec = new VertexTraitSpec();
        //final VertexTraitSpec vertexTraitSpec = vertexTraitSpecMap.get(ctx);
        vertexTraitSpec.name = ctx.name.getText();
        vertexTraitSpec.declaredLine = ctx.getStart().getLine();
        vertexTraitSpec.declaredColumn = ctx.getStart().getCharPositionInLine();
        vertexTraitSpec.requiredPorts.addAll(ctx.vertexPort().stream().map(this::visitVertexPortTyped).collect(Collectors.toList()));
        vertexTraitSpec.requiredProperties.addAll(ctx.vertexProperty().stream().map(this::visitVertexPropertyTyped).collect(Collectors.toList()));
        for (final Token token : ctx.refinedTraits) {
            //vertexTraitSpec.absoluteRefinedTraitNames.add(token.getText());
            // absolute reference or local reference
            if (token.getText().contains("::")) {
                if (token.getText().startsWith("::"))
                    vertexTraitSpec.absoluteRefinedTraitNames.add(token.getText().substring(2));
                else
                    vertexTraitSpec.absoluteRefinedTraitNames.add(token.getText());
                //vertexRefinedParent.add(token.getText())
            } else {
                // add the namespace in a local reference
                vertexTraitSpec.relativeRefinedTraitNames.add(token.getText());
                //vertexRefinedParent.add(namespace + "::" + token.getText());
            }
        }
        return vertexTraitSpec;
    }

    @Override
    public TraitHierarchy visitTraitHierarchy(ForSyDeTraitDSLParser.TraitHierarchyContext ctx) {
        final TraitHierarchy traitHierarchy = new TraitHierarchy();
        final String namespace = ctx.namespace.getText().isBlank() ? "" : ctx.namespace.getText();
        final List<TraitHierarchy> traitHierarchies = ctx.traitHierarchy().stream().map(this::visitTraitHierarchy).collect(Collectors.toList());
        // concatenate grand children with children
        final Stream<VertexTraitSpec> containedVertexTraits =
                Stream.concat(traitHierarchies.stream().flatMap(c -> c.vertexTraits.stream()), ctx.vertexTrait().stream()
                        .map(this::visitVertexTraitTyped))
                        .peek(v -> v.name = namespace + "::" + v.name)
                        .peek(v -> v.relativeRefinedTraitNames.replaceAll(s -> namespace + "::" + s))
                        .peek(v -> v.requiredPorts.replaceAll(p -> {
                            p.relativeVertexTraitName = p.relativeVertexTraitName != null ? namespace + "::" + p.relativeVertexTraitName : null;
                            p.relativeEdgeTraitName = p.relativeEdgeTraitName != null ? namespace + "::" + p.relativeEdgeTraitName : null;
                            return p;
                        }));
        final Stream<EdgeTraitSpec> containedEdgeTraits =
                Stream.concat(traitHierarchies.stream().flatMap(c -> c.edgeTraits.stream()), ctx.edgeTrait().stream()
                        .map(this::visitEdgeTraitTyped))
                        .peek(v -> v.name = namespace + "::" + v.name)
                        .peek(v -> v.relativeRefinedTraitNames.replaceAll(s -> namespace + "::" + s));
        traitHierarchy.vertexTraits.addAll(containedVertexTraits.collect(Collectors.toList()));
        traitHierarchy.edgeTraits.addAll(containedEdgeTraits.collect(Collectors.toList()));
        return traitHierarchy;
    }

    @Override
    public TraitHierarchy visitRootTraitHierarchy(ForSyDeTraitDSLParser.RootTraitHierarchyContext ctx) {
        final TraitHierarchy traitHierarchy = new TraitHierarchy();
        final String namespace = "";
        final List<TraitHierarchy> traitHierarchies = ctx.traitHierarchy().stream().map(this::visitTraitHierarchy).collect(Collectors.toList());
        // concatenate grand children with children
        final Stream<VertexTraitSpec> containedVertexTraits =
                Stream.concat(traitHierarchies.stream().flatMap(c -> c.vertexTraits.stream()), ctx.vertexTrait().stream()
                                .map(this::visitVertexTraitTyped));
//                        .peek(v -> v.name = namespace + "::" + v.name)
//                        .peek(v -> v.relativeRefinedTraitNames.replaceAll(s -> namespace + "::" + s))
//                        .peek(v -> v.requiredPorts.replaceAll(p -> {
//                            p.relativeVertexTraitName = p.relativeVertexTraitName != null ? namespace + "::" + p.relativeVertexTraitName : null;
//                            p.relativeEdgeTraitName = p.relativeEdgeTraitName != null ? namespace + "::" + p.relativeEdgeTraitName : null;
//                            return p;
//                        }));
        final Stream<EdgeTraitSpec> containedEdgeTraits =
                Stream.concat(traitHierarchies.stream().flatMap(c -> c.edgeTraits.stream()), ctx.edgeTrait().stream()
                                .map(this::visitEdgeTraitTyped));
//                        .peek(v -> v.name = namespace + "::" + v.name)
//                        .peek(v -> v.relativeRefinedTraitNames.replaceAll(s -> namespace + "::" + s));
        traitHierarchy.vertexTraits.addAll(containedVertexTraits.collect(Collectors.toList()));
        traitHierarchy.edgeTraits.addAll(containedEdgeTraits.collect(Collectors.toList()));
        // now do extra linking and more
        return traitHierarchy;
    }

    public TraitHierarchy getRootTraitHierarchy(ForSyDeTraitDSLParser.RootTraitHierarchyContext ctx) throws InconsistentTraitHierarchyException {
        final TraitHierarchy traitHierarchy = visitRootTraitHierarchy(ctx);
        linkElements(traitHierarchy);
        return traitHierarchy;
    }
}
