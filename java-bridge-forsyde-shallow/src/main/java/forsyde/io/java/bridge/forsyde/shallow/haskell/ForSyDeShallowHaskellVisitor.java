package forsyde.io.java.bridge.forsyde.shallow.haskell;

import forsyde.io.java.core.SystemGraph;
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
        String filename = ctx.module_content().modid().getText();
//        topSystem.setFileName(filename);
//
        visitBody(systemGraph, ctx.module_content().where_module().module_body().body());

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

            System.out.println("Model Type: SDF Model");

//            AntlrToSDFModel sdfModelVisitor = new AntlrToSDFModel();
//            Model model = sdfModelVisitor.visitTopdecls(ctx.topdecls());
//
//            return model;

        } else {

//            System.out.println("This files does not represent any legal model!");
//            System.exit(1);
        }
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
