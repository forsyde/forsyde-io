package forsyde.io.bridge.forsyde.shallow.haskell;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.Vertex;
import forsyde.io.lib.ForSyDeHierarchy;
import forsyde.io.lib.behavior.moc.sdf.SDFActorViewer;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ForSyDeShallowHaskellVisitor extends HaskellParserBaseVisitor<SystemGraph> {

    /*
     * This method visits the top of the parse tree donig the following:
     * starts the model extraction from the parse tree by chaining visitor methods
     * creates the topSystem
     */

    @Override
    public SystemGraph visitModule(HaskellParser.ModuleContext ctx) {

        final SystemGraph systemGraph = new SystemGraph();
//        String filename = ctx.module_content().modid().getText();
//        topSystem.setFileName(filename);
//
        if (ctx.module_content() != null && ctx.module_content().where_module() != null && ctx.module_content().where_module().module_body() != null && ctx.module_content().where_module().module_body().body() != null) {
            visitBody(systemGraph, ctx.module_content().where_module().module_body().body());
        }
        if (ctx.body() != null) {
            visitBody(systemGraph, ctx.body());
        }

//        System.out.println(
//                "===========================\n" + "Model: " + model.toString() + "\n===========================");

        return systemGraph;

    }

    /*
     * This visitor visits the part of the tree where Forsyde Model declaration
     * starts.
     * It identifies they model type and starts building the related model object
     */
    public SystemGraph visitBody(SystemGraph systemGraph, HaskellParser.BodyContext ctx) {

        if (parseTreeSatisfies(ctx, (n) -> n.getText().contains("actorSDF"))) {

//            System.out.println("Model Type: SDF Model");

//            AntlrToSDFModel sdfModelVisitor = new AntlrToSDFModel();
//            Model model = sdfModelVisitor.visitTopdecls(ctx.topdecls());
//
//            return model;

        } else {

//            System.out.println("This files does not represent any legal model!");
//            System.exit(1);
        }
        return visitTopdecls(systemGraph, ctx.topdecls());

    }

    public SystemGraph visitTopdecls(SystemGraph systemGraph, HaskellParser.TopdeclsContext ctx) {

//        SDFModel sdfModel = new SDFModel();
//        ModelComponents modelComponents = new ModelComponents();
//
//        List<TopdeclContext> modelComponentTrees = ctx.topdecl();
        for (HaskellParser.TopdeclContext topdeclContext : ctx.topdecl()) {
            if (topdeclContext.decl_no_th() != null) {
                if (topdeclContext.decl_no_th().rhs() != null && topdeclContext.decl_no_th().rhs().exp().infixexp() != null) {
                    // this long stream of calls is beacuse of the many recursive ways Haskell can build expressionss...
                    // but it basically gets the first name coming AFTER the "=" sign.
                    final String potentialProcessConstructorName = topdeclContext.decl_no_th().rhs().exp().infixexp().exp10().fexp().aexp(0).aexp1().aexp2().qvar().getText();
                    if (potentialProcessConstructorName.contains("actor")) {
                        // we also get the first variable that appear BEFORE the "=" sign
                        final String processName = topdeclContext.decl_no_th().infixexp().exp10().fexp().aexp(0).aexp1().aexp2().qvar().getText();
                        final Vertex v = systemGraph.newVertex(processName);
                        final SDFActorViewer actor = ForSyDeHierarchy.SDFActor.enforce(systemGraph, v);
                        final int numInputs = Character.getNumericValue(potentialProcessConstructorName.charAt(5));
                        var consumptions = new ArrayList<Integer>();
                        final int numOutputs = Character.getNumericValue(potentialProcessConstructorName.charAt(6));
                        var productions = new ArrayList<Integer>();
                        // have to do some recursion based on the number of inputs...
                        if (numInputs == 1) {
                            // easy, we just get the number
                            consumptions.add(Integer.parseInt(topdeclContext.decl_no_th().rhs().exp().infixexp().exp10().fexp().aexp(1).aexp1().aexp2().getText()));
                        } else {
                            // harder, have to recurse on the commas.
                            var tIter = topdeclContext.decl_no_th().rhs().exp().infixexp().exp10().fexp().aexp(1).aexp1().aexp2().tup_exprs();
                            consumptions.add(Integer.parseInt(tIter.texp().getText()));
                            var next = tIter.commas_tup_tail().tup_tail();
                            consumptions.add(Integer.parseInt(next.texp().getText()));
                            while (next.commas_tup_tail() != null) {
                                next = next.commas_tup_tail().tup_tail();
                                consumptions.add(Integer.parseInt(next.texp().getText()));
                            }
                        }
                        // now we do exactly the same but for the outputs
                        if (numOutputs == 1) {
                            // easy, we just get the number
                            productions.add(Integer.parseInt(topdeclContext.decl_no_th().rhs().exp().infixexp().exp10().fexp().aexp(2).aexp1().aexp2().getText()));
                        } else {
                            // harder, have to recurse on the commas.
                            var tIter = topdeclContext.decl_no_th().rhs().exp().infixexp().exp10().fexp().aexp(2).aexp1().aexp2().tup_exprs();
                            productions.add(Integer.parseInt(tIter.texp().getText()));
                            var next = tIter.commas_tup_tail().tup_tail();
                            productions.add(Integer.parseInt(next.texp().getText()));
                            while (next.commas_tup_tail() != null) {
                                next = next.commas_tup_tail().tup_tail();
                                productions.add(Integer.parseInt(next.texp().getText()));
                            }
                        }
                        // extract port names assuming syntax: proc port1 port2 port3 ... = actorXYSDF (...) (..) f port1 port 2...
                        for (int i = 1; i < numInputs + 1; i++) {
                            if (topdeclContext.decl_no_th().infixexp().exp10().fexp().aexp(i) != null) {
                                final String portName = topdeclContext.decl_no_th().infixexp().exp10().fexp().aexp(i).aexp1().aexp2().qvar().getText();
                                v.addPort(portName);
                                actor.consumption().put(portName, consumptions.get(i - 1));
                            } else {
                                v.addPort("in" + (i - 1));
                                actor.consumption().put("in" + (i - 1), consumptions.get(i - 1));
                            }
                        }
                        for (int i = 4; i < numOutputs + 4; i++) {
                            if (topdeclContext.decl_no_th().rhs().exp().infixexp().exp10().fexp().aexp(i) != null) {
                                final String portName = topdeclContext.decl_no_th().rhs().exp().infixexp().exp10().fexp().aexp(i).aexp1().aexp2().qvar().getText();
                                v.addPort(portName);
                                actor.production().put(portName, productions.get(i - 4));
                            } else {
                                v.addPort("out" + (i - 4));
                                actor.production().put("out" + (i - 4), productions.get(i - 4));
                            }
                        }

                    }
                }
            }
        }

//        for (int i = 0; i < ctx.size(); i++) {
//
//            // First Leaf of the tree containing components always has the netlist
//            // definition
//            if (i == 0) {
//
//                ParseTree treeLeft = ctx.topdecl().get(0).decl_no_th().infixexp().exp10().fexp();
//                ParseTree treeRight = ctx.topdecl().get(0).decl_no_th().rhs().exp().infixexp().exp10().fexp();
//                DeclsContext treeRightNetlist = ctx.topdecl().get(0).decl_no_th().rhs().wherebinds().binds().decllist()
//                        .decls();
//
//                String modelName = treeLeft.getChild(0).getText();
//                sdfModel.setModelName(modelName);
//                System.out.println("Model Name: " + modelName);
//
//                for (int j = 1; j < treeLeft.getChildCount(); j++) {
//                    System.out.println("input signal " + j + " : " + treeLeft.getChild(j).getText());
//                    sdfModel.addInputSingal(treeLeft.getChild(j).getText());
//                }
//
//                for (int j = 0; j < treeRight.getChildCount(); j++) {
//                    System.out.println("output signal " + (j + 1) + " : " + treeRight.getChild(j).getText());
//                    sdfModel.addOutputSignal(treeRight.getChild(j).getText());
//                }
//
//                AntlrToNetlist netlistVisitor = new AntlrToNetlist();
//                Netlist netlist = netlistVisitor.visitDecls(treeRightNetlist);
//                System.out.println("Netlist ToString(): " + netlist.toString());
//
//                modelComponents.setNetlist(netlist);
//
//            }
//
//            // Tree parts containing the reserved actor keywords will be treated as actor
//            // declarations
//            else if (HelperFunctions.containsKeywords(ctx.topdecl().get(i), SDFModel.actorNames)) {
//
//                System.out.println("node: " + i + " is a actor node");
//
//                // Process declarations in AST has left and right leafs, where left contaions
//                // actor name and input signal names
//                // while right side contaions ActorName used by forsysde, consumptions
//                // productions and function applied by the actor respectively.
//                // we visit each leaf of the tree and extract the process declaration.
//
//                AntlrToActor actorVisitor = new AntlrToActor();
//                Decl_no_thContext actorAST = ctx.topdecl().get(i).decl_no_th();
//                SDFActor sdfActor = new SDFActor();
//                sdfActor = actorVisitor.visitDecl_no_th(actorAST);
//                modelComponents.addSDFActor(sdfActor);
//
//            }
//
//            // here we check if the node defines a delay process
//            else if (HelperFunctions.containsKeywords(ctx.topdecl().get(i), SDFModel.delayNames)) {
//
//                System.out.println("node: " + i + " is a delay node");
//                AntlrToSDFDelay sdfDelayVisitor = new AntlrToSDFDelay();
//                SDFDelay sdfDelay = new SDFDelay();
//                Decl_no_thContext delayAST = ctx.topdecl().get(i).decl_no_th();
//                sdfDelay = sdfDelayVisitor.visitDecl_no_th(delayAST);
//                modelComponents.addSDFDelay(sdfDelay);
//
//            }
//
//            // else is expected to be regular function declarations used to declare the
//            // functions run on the Actors.
//            else {
//
//                System.out.println("Function declaration found: " + ctx.topdecl().get(i).getText());
//                ActorFunction actorFunction = new ActorFunction(ctx.topdecl().get(i).getText());
//                sdfModel.addFunction(actorFunction);
//
//                // Todo:
//                // modify this part depending on the forsydeIO needs
//
//            }
//
//        }
//
//        sdfModel.setModelComponents(modelComponents);
//
//        return sdfModel;
        return systemGraph;
    }

    /*
     * Function searching parse tree for a specific condition
     */
    protected boolean parseTreeSatisfies(ParseTree node, Function<ParseTree, Boolean> check) {
        if (check.apply(node)) {
            return true;
        } else {
            for (int i = 0; i < node.getChildCount(); i++) {
                ParseTree child = node.getChild(i);
                return parseTreeSatisfies(child, check);
            }
        }
        return false;
    }

    /*
     * Function searching parse tree for a specific keyword
     */
    protected boolean containsKeyword(ParseTree node, String keyword) {
        if (node.getText().equals(keyword)) {
            // Keyword found, perform actions or return flag
            return true;
        }

        for (int i = 0; i < node.getChildCount(); i++) {
            ParseTree child = node.getChild(i);
            if (containsKeyword(child, keyword)) {
                // Keyword found in one of the child nodes
                return true;
            }
        }

        // Keyword not found in the current node or any of its children
        return false;
    }

    /*
     * Function searching parse tree for a list of keywords and returns true if it
     * matches one of them
     */
    protected boolean containsKeywords(ParseTree node, List<String> keywords) {
        for (String keyword : keywords) {
            if (containsKeyword(node, keyword)) {
                // Keyword found
                return true;
            }
        }

        // None of the keywords found
        return false;
    }

    /*
     * Function used for extracting delay values from a string representing the haskell style list of integers.
     */
    protected List<String> stringToList(String stringList) {
        String[] strNumbers = stringList.replaceAll("\\[|\\]", "").split(",");
        List<String> numbersList = new ArrayList<>();
        for (String strNumber : strNumbers) {
            numbersList.add(strNumber.trim());
        }
        return numbersList;
    }
}
