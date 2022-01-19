package forsyde.io.java.generator;

import forsyde.io.java.generator.dsl.ForSyDeTraitDSLListener;
import forsyde.io.java.generator.dsl.ForSyDeTraitDSLParser;
import forsyde.io.java.generator.specs.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class ForSyDeIOTraitDSLListener  implements ForSyDeTraitDSLListener {

    TraitHierarchy traitHierarchy = new TraitHierarchy();
    String namespace = "";
    List<String> vertexRefinedChild = new ArrayList<>();
    List<String> vertexRefinedParent = new ArrayList<>();
    Map<ForSyDeTraitDSLParser.VertexTraitContext, VertexTraitSpec> vertexTraitSpecMap = new HashMap<>();

    @Override
    public void enterEdgeTrait(ForSyDeTraitDSLParser.EdgeTraitContext ctx) {

    }

    @Override
    public void exitEdgeTrait(ForSyDeTraitDSLParser.EdgeTraitContext ctx) {

    }

    @Override
    public void enterVertexPort(ForSyDeTraitDSLParser.VertexPortContext ctx) {
        if (ctx.parent instanceof ForSyDeTraitDSLParser.VertexTraitContext) {
            final ForSyDeTraitDSLParser.VertexTraitContext parentCtx = (ForSyDeTraitDSLParser.VertexTraitContext) ctx.parent;
            final VertexTraitSpec parent = vertexTraitSpecMap.get(parentCtx);
            final PortSpec portSpec = new PortSpec();
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
            portSpec.vertexTrait = parent;
            parent.requiredPorts.add(portSpec);

        }
    }

    @Override
    public void exitVertexPort(ForSyDeTraitDSLParser.VertexPortContext ctx) {

    }

    protected PropertyTypeSpec buildFromContext(ForSyDeTraitDSLParser.VertexPropertyTypeContext ctx) {
        return ctx.typeName.toString().equals("int") ? PropertyTypeSpecs.IntVertexProperty() :
                ctx.typeName.toString().equals("integer") ? PropertyTypeSpecs.IntVertexProperty() :
                ctx.typeName.toString().equals("float") ? PropertyTypeSpecs.FloatVertexProperty() :
                PropertyTypeSpecs.StringVertexProperty();
    }

    @Override
    public void enterVertexPropertyType(ForSyDeTraitDSLParser.VertexPropertyTypeContext ctx) {
        final PropertyTypeSpec propertyTypeSpec = buildFromContext(ctx);
    }

    @Override
    public void exitVertexPropertyType(ForSyDeTraitDSLParser.VertexPropertyTypeContext ctx) {

    }

    @Override
    public void enterVertexProperty(ForSyDeTraitDSLParser.VertexPropertyContext ctx) {

    }

    @Override
    public void exitVertexProperty(ForSyDeTraitDSLParser.VertexPropertyContext ctx) {

    }

    @Override
    public void enterVertexTrait(ForSyDeTraitDSLParser.VertexTraitContext ctx) {
        final VertexTraitSpec vertexTraitSpec = new VertexTraitSpec();
        vertexTraitSpecMap.put(ctx, vertexTraitSpec);
        vertexTraitSpec.name = namespace + "::" + ctx.name;
        for (final Token token : ctx.refinedTraits) {
            vertexRefinedChild.add(vertexTraitSpec.name);
            if (token.toString().contains("::")) {
                vertexRefinedParent.add(token.toString());
            } else {
                vertexRefinedParent.add(namespace + "::" + token.toString());
            }
        }
        traitHierarchy.vertexTraits.add(vertexTraitSpec);
    }

    @Override
    public void exitVertexTrait(ForSyDeTraitDSLParser.VertexTraitContext ctx) {

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
        for (final VertexTraitSpec vertexTraitSpec : traitHierarchy.vertexTraits) {
            for (final VertexTraitSpec vertexTraitSpecOther : traitHierarchy.vertexTraits) {
                if (vertexRefinedChild.indexOf(vertexTraitSpec.name) == vertexRefinedParent.indexOf(vertexTraitSpecOther.name)) {
                    vertexTraitSpec.refinedTraits.add(vertexTraitSpecOther);
                }
            }
        }
    }

}
