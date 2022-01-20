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
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class ForSyDeIOTraitDSLListener  implements ForSyDeTraitDSLListener {

    TraitHierarchy traitHierarchy = new TraitHierarchy();
    String namespace = "";
    Map<ForSyDeTraitDSLParser.VertexTraitContext, VertexTraitSpec> vertexTraitSpecMap = new HashMap<>();
    Map<ForSyDeTraitDSLParser.VertexPortContext, PortSpec> portSpecMap = new HashMap<>();
    Map<ForSyDeTraitDSLParser.VertexPropertyTypeContext, PropertyTypeSpec> propertyTypeSpecMap = new HashMap<>();
    Map<ForSyDeTraitDSLParser.VertexPropertyContext, PropertySpec> propertySpecMap = new HashMap<>();

    @Override
    public void enterEdgeTrait(ForSyDeTraitDSLParser.EdgeTraitContext ctx) {

    }

    @Override
    public void exitEdgeTrait(ForSyDeTraitDSLParser.EdgeTraitContext ctx) {

    }

    @Override
    public void enterVertexPort(ForSyDeTraitDSLParser.VertexPortContext ctx) {
        final PortSpec portSpec = new PortSpec();
        portSpecMap.put(ctx, portSpec);
        portSpec.name = ctx.name.toString();
        final List<String> modifiers = ctx.modifiers.stream().map(Token::toString).collect(Collectors.toList());
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
        if (ctx.connectedVertexTrait.toString().contains("::")) {
            portSpec.vertexTraitName = ctx.connectedVertexTrait.toString();
        } else {
            // add the namespace in a local reference
            portSpec.vertexTraitName = namespace + "::" + ctx.connectedVertexTrait.toString();
        }
    }

    @Override
    public void exitVertexPort(ForSyDeTraitDSLParser.VertexPortContext ctx) {

    }

    protected PropertyTypeSpec buildFromContext(ForSyDeTraitDSLParser.VertexPropertyTypeContext ctx) {
        return ctx.typeName.toString().equals("int") ? PropertyTypeSpecs.IntVertexProperty() :
                ctx.typeName.toString().equals("integer") ? PropertyTypeSpecs.IntVertexProperty() :
                ctx.typeName.toString().equals("float") ? PropertyTypeSpecs.FloatVertexProperty() :
                ctx.typeName.toString().equals("bool") ? PropertyTypeSpecs.BooleanVertexProperty() :
                ctx.typeName.toString().equals("boolean") ? PropertyTypeSpecs.BooleanVertexProperty() :
                ctx.typeName.toString().equals("long") ? PropertyTypeSpecs.LongVertexProperty() :
                ctx.typeName.toString().equals("double") ? PropertyTypeSpecs.DoubleVertexProperty() :
                ctx.typeName.toString().equals("real") ? PropertyTypeSpecs.DoubleVertexProperty() :
                ctx.typeName.toString().equals("str") ? PropertyTypeSpecs.StringVertexProperty() :
                ctx.typeName.toString().equals("string") ? PropertyTypeSpecs.StringVertexProperty() :
                ctx.typeName.toString().equals("array") ? PropertyTypeSpecs.ArrayVertexProperty(buildFromContext(ctx.arrayType)) :
                ctx.typeName.toString().equals("intmap") ? PropertyTypeSpecs.IntMapVertexProperty(buildFromContext(ctx.intMapType)) :
                ctx.typeName.toString().equals("integermap") ? PropertyTypeSpecs.IntMapVertexProperty(buildFromContext(ctx.intMapType)) :
                ctx.typeName.toString().equals("intMap") ? PropertyTypeSpecs.IntMapVertexProperty(buildFromContext(ctx.intMapType)) :
                ctx.typeName.toString().equals("integerMap") ? PropertyTypeSpecs.IntMapVertexProperty(buildFromContext(ctx.intMapType)) :
                ctx.typeName.toString().equals("strmap") ? PropertyTypeSpecs.StringMapVertexProperty(buildFromContext(ctx.strMapType)) :
                ctx.typeName.toString().equals("stringmap") ? PropertyTypeSpecs.StringMapVertexProperty(buildFromContext(ctx.strMapType)) :
                ctx.typeName.toString().equals("strMap") ? PropertyTypeSpecs.StringMapVertexProperty(buildFromContext(ctx.strMapType)) :
                ctx.typeName.toString().equals("stringMap") ? PropertyTypeSpecs.StringMapVertexProperty(buildFromContext(ctx.strMapType)) :
                PropertyTypeSpecs.StringVertexProperty();
    }

    @Override
    public void enterVertexPropertyType(ForSyDeTraitDSLParser.VertexPropertyTypeContext ctx) {
        final PropertyTypeSpec propertyTypeSpec = buildFromContext(ctx);
        propertyTypeSpecMap.put(ctx, propertyTypeSpec);
    }

    @Override
    public void exitVertexPropertyType(ForSyDeTraitDSLParser.VertexPropertyTypeContext ctx) {

    }

    @Override
    public void enterVertexProperty(ForSyDeTraitDSLParser.VertexPropertyContext ctx) {
        final PropertySpec propertySpec = new PropertySpec();
        propertySpecMap.put(ctx, propertySpec);
        propertySpec.name = ctx.name.toString();
    }

    @Override
    public void exitVertexProperty(ForSyDeTraitDSLParser.VertexPropertyContext ctx) {
        final PropertySpec propertySpec = propertySpecMap.get(ctx);
        for (final ParseTree parseTree : ctx.children) {
            if (parseTree instanceof ForSyDeTraitDSLParser.VertexPropertyTypeContext) {
                propertySpec.type = propertyTypeSpecMap.get(parseTree);
            }
        }
    }

    @Override
    public void enterVertexTrait(ForSyDeTraitDSLParser.VertexTraitContext ctx) {
        final VertexTraitSpec vertexTraitSpec = new VertexTraitSpec();
        vertexTraitSpecMap.put(ctx, vertexTraitSpec);
        vertexTraitSpec.name = namespace + "::" + ctx.name;
        for (final Token token : ctx.refinedTraits) {
            vertexTraitSpec.refinedTraitNames.add(token.toString());
            // absolute reference or local reference
            if (token.toString().contains("::")) {
                vertexTraitSpec.refinedTraitNames.add(token.toString());
                //vertexRefinedParent.add(token.toString())
            } else {
                // add the namespace in a local reference
                vertexTraitSpec.refinedTraitNames.add(namespace + "::" + token.toString());
                //vertexRefinedParent.add(namespace + "::" + token.toString());
            }
        }
        traitHierarchy.vertexTraits.add(vertexTraitSpec);
    }

    @Override
    public void exitVertexTrait(ForSyDeTraitDSLParser.VertexTraitContext ctx) {
        final VertexTraitSpec vertexTraitSpec = vertexTraitSpecMap.get(ctx);
        for (final ParseTree parseTree : ctx.children) {
            if (parseTree instanceof ForSyDeTraitDSLParser.VertexPortContext) {
                vertexTraitSpec.requiredPorts.add(portSpecMap.get(parseTree));
            } else if (parseTree instanceof ForSyDeTraitDSLParser.VertexPropertyContext) {
                vertexTraitSpec.requiredProperties.add(propertySpecMap.get(parseTree));
            }
        }
    }

    @Override
    public void enterTraitHierarchy(ForSyDeTraitDSLParser.TraitHierarchyContext ctx) {
        namespace = namespace + "::" + ctx.namespace;
    }

    @Override
    public void exitTraitHierarchy(ForSyDeTraitDSLParser.TraitHierarchyContext ctx) {
        namespace = namespace.replaceAll("::" + ctx.namespace, "");
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
                if (vertexTraitSpec.refinedTraitNames.contains(vertexTraitSpecOther.name)) {
                    vertexTraitSpec.refinedTraits.add(vertexTraitSpecOther);
                }
            }
            // ports
            for (final PortSpec portSpec : vertexTraitSpec.requiredPorts) {
                for (final VertexTraitSpec vertexTraitSpecOther : traitHierarchy.vertexTraits) {
                    if (portSpec.vertexTraitName.equals(vertexTraitSpecOther.name)) {
                        portSpec.vertexTrait = vertexTraitSpecOther;
                    }
                }
            }
        }

    }

}
