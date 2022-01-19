package forsyde.io.java.generator;

import forsyde.io.java.generator.dsl.ForSyDeTraitDSLListener;
import forsyde.io.java.generator.dsl.ForSyDeTraitDSLParser;
import forsyde.io.java.generator.specs.TraitHierarchy;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.concurrent.ConcurrentMap;

public class ForSyDeIOTraitDSLListener implements ForSyDeTraitDSLListener {

    TraitHierarchy traitHierarchy = new TraitHierarchy();

    @Override
    public void enterEdgeTrait(ForSyDeTraitDSLParser.EdgeTraitContext ctx) {

    }

    @Override
    public void exitEdgeTrait(ForSyDeTraitDSLParser.EdgeTraitContext ctx) {

    }

    @Override
    public void enterVertexPort(ForSyDeTraitDSLParser.VertexPortContext ctx) {

    }

    @Override
    public void exitVertexPort(ForSyDeTraitDSLParser.VertexPortContext ctx) {

    }

    @Override
    public void enterVertexProperty(ForSyDeTraitDSLParser.VertexPropertyContext ctx) {

    }

    @Override
    public void exitVertexProperty(ForSyDeTraitDSLParser.VertexPropertyContext ctx) {

    }

    @Override
    public void enterVertexTrait(ForSyDeTraitDSLParser.VertexTraitContext ctx) {

    }

    @Override
    public void exitVertexTrait(ForSyDeTraitDSLParser.VertexTraitContext ctx) {

    }

    @Override
    public void enterTraitHierarchy(ForSyDeTraitDSLParser.TraitHierarchyContext ctx) {

    }

    @Override
    public void exitTraitHierarchy(ForSyDeTraitDSLParser.TraitHierarchyContext ctx) {

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
}
