package forsyde.io.java.generator;

import forsyde.io.java.generator.dsl.ForSyDeTraitDSLListener;
import forsyde.io.java.generator.dsl.ForSyDeTraitDSLParser;
import forsyde.io.java.generator.specs.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;
import java.util.stream.Collectors;

public class ForSyDeIOTraitDSLListener  implements ForSyDeTraitDSLListener {

    TraitHierarchy traitHierarchy = new TraitHierarchy();
    String namespace = "";
    Map<ForSyDeTraitDSLParser.EdgeTraitContext, EdgeTraitSpec> declaredEdgeTraitSpecMap = new HashMap<>();
    Map<ForSyDeTraitDSLParser.VertexTraitContext, VertexTraitSpec> declaredVertexTraitSpecMap = new HashMap<>();
    Map<ForSyDeTraitDSLParser.VertexPortContext, PortSpec> portSpecMap = new HashMap<>();
    Map<ForSyDeTraitDSLParser.VertexPropertyTypeContext, PropertyTypeSpec> propertyTypeSpecMap = new HashMap<>();
    Map<ForSyDeTraitDSLParser.VertexPropertyContext, PropertySpec> propertySpecMap = new HashMap<>();

    @Override
    public void enterEdgeTrait(ForSyDeTraitDSLParser.EdgeTraitContext ctx) {

    }

    @Override
    public void exitEdgeTrait(ForSyDeTraitDSLParser.EdgeTraitContext ctx) {
        final EdgeTraitSpec edgeTraitSpec = new EdgeTraitSpec();
        declaredEdgeTraitSpecMap.put(ctx, edgeTraitSpec);
        edgeTraitSpec.name = namespace + "::" + ctx.name.getText();
        for (final Token token : ctx.refinedTraits) {
            edgeTraitSpec.absoluteRefinedTraitNames.add(token.getText());
            // absolute reference or local reference
            if (token.getText().contains("::")) {
                edgeTraitSpec.absoluteRefinedTraitNames.add(token.getText());
                //vertexRefinedParent.add(token.getText())
            } else {
                // add the namespace in a local reference
                edgeTraitSpec.absoluteRefinedTraitNames.add(namespace + "::" + token.getText());
                //vertexRefinedParent.add(namespace + "::" + token.getText());
            }
        }
        for (final Token token : ctx.sourceVertexes) {
            edgeTraitSpec.sourceTraitNames.add(token.getText());
        }
        for (final Token token : ctx.targetVertexes) {
            edgeTraitSpec.targetTraitNames.add(token.getText());
        }
        traitHierarchy.edgeTraits.add(edgeTraitSpec);
    }

    @Override
    public void enterVertexPort(ForSyDeTraitDSLParser.VertexPortContext ctx) {

    }

    @Override
    public void exitVertexPort(ForSyDeTraitDSLParser.VertexPortContext ctx) {
        final PortSpec portSpec = new PortSpec();
        portSpecMap.put(ctx, portSpec);
        portSpec.name = ctx.name.getText();
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
            portSpec.absoluteVertexTraitName = ctx.connectedVertexTrait.getText();
        } else {
            // add the namespace in a local reference
            portSpec.absoluteVertexTraitName = namespace.isBlank() ? ctx.connectedVertexTrait.getText() :
                    namespace + "::" + ctx.connectedVertexTrait.getText();
        }
        // absolute reference or local reference for edge trait
        if (ctx.connectingEdgeTrait != null) {
            if (ctx.connectingEdgeTrait.getText().contains("::")) {
                portSpec.absoluteEdgeTraitName = ctx.connectingEdgeTrait.getText();
            } else {
                // add the namespace in a local reference
                portSpec.absoluteEdgeTraitName = namespace.isBlank() ? ctx.connectingEdgeTrait.getText() :
                        namespace + "::" + ctx.connectingEdgeTrait.getText();
            }
        }
    }

    protected PropertyTypeSpec buildFromContext(ForSyDeTraitDSLParser.VertexPropertyTypeContext ctx) {
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
                ctx.typeName.getText().equals("array") ? PropertyTypeSpecs.ArrayVertexProperty(buildFromContext(ctx.arrayType)) :
                ctx.typeName.getText().equals("intmap") ? PropertyTypeSpecs.IntMapVertexProperty(buildFromContext(ctx.intMapType)) :
                ctx.typeName.getText().equals("integermap") ? PropertyTypeSpecs.IntMapVertexProperty(buildFromContext(ctx.intMapType)) :
                ctx.typeName.getText().equals("intMap") ? PropertyTypeSpecs.IntMapVertexProperty(buildFromContext(ctx.intMapType)) :
                ctx.typeName.getText().equals("integerMap") ? PropertyTypeSpecs.IntMapVertexProperty(buildFromContext(ctx.intMapType)) :
                ctx.typeName.getText().equals("strmap") ? PropertyTypeSpecs.StringMapVertexProperty(buildFromContext(ctx.strMapType)) :
                ctx.typeName.getText().equals("stringmap") ? PropertyTypeSpecs.StringMapVertexProperty(buildFromContext(ctx.strMapType)) :
                ctx.typeName.getText().equals("strMap") ? PropertyTypeSpecs.StringMapVertexProperty(buildFromContext(ctx.strMapType)) :
                ctx.typeName.getText().equals("stringMap") ? PropertyTypeSpecs.StringMapVertexProperty(buildFromContext(ctx.strMapType)) :
                PropertyTypeSpecs.StringVertexProperty();
    }

    @Override
    public void enterVertexPropertyType(ForSyDeTraitDSLParser.VertexPropertyTypeContext ctx) {
    }

    @Override
    public void exitVertexPropertyType(ForSyDeTraitDSLParser.VertexPropertyTypeContext ctx) {
        final PropertyTypeSpec propertyTypeSpec = buildFromContext(ctx);
        propertyTypeSpecMap.put(ctx, propertyTypeSpec);
    }

    @Override
    public void enterVertexProperty(ForSyDeTraitDSLParser.VertexPropertyContext ctx) {

    }

    @Override
    public void exitVertexProperty(ForSyDeTraitDSLParser.VertexPropertyContext ctx) {
        final PropertySpec propertySpec = new PropertySpec();
        propertySpecMap.put(ctx, propertySpec);
        propertySpec.name = ctx.name.getText();
        for (final ParseTree parseTree : ctx.children) {
            if (parseTree instanceof ForSyDeTraitDSLParser.VertexPropertyTypeContext) {
                propertySpec.type = propertyTypeSpecMap.get(parseTree);
            }
        }
    }

    @Override
    public void enterVertexTrait(ForSyDeTraitDSLParser.VertexTraitContext ctx) {
        /*
        final VertexTraitSpec vertexTraitSpec = new VertexTraitSpec();
        vertexTraitSpecMap.put(ctx, vertexTraitSpec);
        vertexTraitSpec.name = namespace.isBlank() ? ctx.name.getText() : namespace + "::" + ctx.name;
        for (final Token token : ctx.refinedTraits) {
            vertexTraitSpec.refinedTraitNames.add(token.getText());
            // absolute reference or local reference
            if (token.getText().contains("::")) {
                vertexTraitSpec.refinedTraitNames.add(token.getText());
                //vertexRefinedParent.add(token.getText())
            } else {
                // add the namespace in a local reference
                vertexTraitSpec.refinedTraitNames.add(namespace + "::" + token.getText());
                //vertexRefinedParent.add(namespace + "::" + token.getText());
            }
        }
        traitHierarchy.vertexTraits.add(vertexTraitSpec);
        */
    }

    @Override
    public void exitVertexTrait(ForSyDeTraitDSLParser.VertexTraitContext ctx) {
        final VertexTraitSpec vertexTraitSpec = new VertexTraitSpec();
        //final VertexTraitSpec vertexTraitSpec = vertexTraitSpecMap.get(ctx);
        vertexTraitSpec.name = namespace.isBlank() ? ctx.name.getText() : namespace + "::" + ctx.name.getText();
        for (final ParseTree parseTree : ctx.children) {
            if (parseTree instanceof ForSyDeTraitDSLParser.VertexPortContext) {
                vertexTraitSpec.requiredPorts.add(portSpecMap.get(parseTree));
            } else if (parseTree instanceof ForSyDeTraitDSLParser.VertexPropertyContext) {
                vertexTraitSpec.requiredProperties.add(propertySpecMap.get(parseTree));
            }
        }
        for (final Token token : ctx.refinedTraits) {
            vertexTraitSpec.absoluteRefinedTraitNames.add(token.getText());
            // absolute reference or local reference
            if (token.getText().contains("::")) {
                vertexTraitSpec.absoluteRefinedTraitNames.add(token.getText());
                //vertexRefinedParent.add(token.getText())
            } else {
                // add the namespace in a local reference
                vertexTraitSpec.absoluteRefinedTraitNames.add(namespace + "::" + token.getText());
                //vertexRefinedParent.add(namespace + "::" + token.getText());
            }
        }
        traitHierarchy.vertexTraits.add(vertexTraitSpec);
    }

    @Override
    public void enterTraitHierarchy(ForSyDeTraitDSLParser.TraitHierarchyContext ctx) {
        namespace = namespace.isBlank() ? ctx.namespace.getText() : namespace + "::" + ctx.namespace.getText();
    }

    @Override
    public void exitTraitHierarchy(ForSyDeTraitDSLParser.TraitHierarchyContext ctx) {
        namespace = namespace.contains("::") ? namespace.replaceAll("::" + ctx.namespace.getText(), "") :
                namespace.replaceAll(ctx.namespace.getText(), "");
    }

    @Override
    public void enterRootTraitHierarchy(ForSyDeTraitDSLParser.RootTraitHierarchyContext ctx) {

    }

    @Override
    public void exitRootTraitHierarchy(ForSyDeTraitDSLParser.RootTraitHierarchyContext ctx) {
        linkElements();
    }

    @Override
    public void visitTerminal(TerminalNode node) {

    }

    @Override
    public void visitErrorNode(ErrorNode node) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {

    }

    private void linkElements() {
        // vertexes
        for (final VertexTraitSpec vertexTraitSpec : traitHierarchy.vertexTraits) {
            // refinements
            for (final VertexTraitSpec vertexTraitSpecOther : traitHierarchy.vertexTraits) {
                if (vertexTraitSpec.absoluteRefinedTraitNames.contains(vertexTraitSpecOther.name)) {
                    vertexTraitSpec.refinedTraits.add(vertexTraitSpecOther);
                }
            }
            // ports
            for (final PortSpec portSpec : vertexTraitSpec.requiredPorts) {
                for (final VertexTraitSpec vertexTraitSpecOther : traitHierarchy.vertexTraits) {
                    if (portSpec.absoluteVertexTraitName.equals(vertexTraitSpecOther.name)) {
                        portSpec.vertexTrait = vertexTraitSpecOther;
                    }
                }
                if (portSpec.absoluteEdgeTraitName != null) {
                    for (final EdgeTraitSpec edgeTraitSpec : traitHierarchy.edgeTraits) {
                        if (portSpec.absoluteEdgeTraitName.equals(edgeTraitSpec.name)) {
                            portSpec.edgeTraitSpec = edgeTraitSpec;
                        }
                    }
                }
            }
        }

        // edges
        for (final EdgeTraitSpec edgeTraitSpec : traitHierarchy.edgeTraits) {
            // refinements
            for (final EdgeTraitSpec edgeTraitSpecOther : traitHierarchy.edgeTraits) {
                if (edgeTraitSpec.absoluteRefinedTraitNames.contains(edgeTraitSpecOther.name)) {
                    edgeTraitSpec.refinedTraits.add(edgeTraitSpecOther);
                }
            }
            // vertexes contraints
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

}
