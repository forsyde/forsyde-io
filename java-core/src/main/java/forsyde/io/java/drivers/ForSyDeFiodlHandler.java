package forsyde.io.java.drivers;

import forsyde.io.java.adapters.fiodl.ForSyDeFioDLBaseVisitor;
import forsyde.io.java.adapters.fiodl.ForSyDeFioDLLexer;
import forsyde.io.java.adapters.fiodl.ForSyDeFioDLParser;
import forsyde.io.java.core.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class ForSyDeFiodlHandler extends ForSyDeFioDLBaseVisitor<ForSyDeSystemGraph> implements ForSyDeModelDriver {

    public static class InconsistentModelException extends Exception {
        public InconsistentModelException(String message) {
            super(message);
        }
    }

    public static class FioDLSyntaxException extends Exception {
        public FioDLSyntaxException(String message) {
            super(message);
        }
    }


//    final Parser<Chr, VertexProperty> vertexPropertyParser = string("property").andR(Combinators.any()).andL("is");
//    final Parser<Chr, VertexTrait> vertexTraitsParserComma = chr(',').andR(Combinators.any()).map(n -> VertexTrait.fromName(n.));
//    final Parser<Chr, IList<VertexTrait>> vertexTraitsParser = Combinators.any().and()
//    final Parser<Chr, Vertex> vertexParser = string("vertex").andR(Combinators.any());
//    final Parser<Chr, ForSyDeSystemGraph> systemGraphParser = string("systemgraph").andR(string("{")).andL("}");

    @Override
    public List<String> inputExtensions() {
        return List.of("fiodl");
    }

    @Override
    public List<String> outputExtensions() {
        return List.of("fiodl");
    }

    @Override
    public ForSyDeSystemGraph loadModel(InputStream in) throws Exception {
        final ForSyDeFioDLLexer forSyDeFioDLLexer = new ForSyDeFioDLLexer(CharStreams.fromStream(in));
        final CommonTokenStream commonTokenStream = new CommonTokenStream(forSyDeFioDLLexer);
        final ForSyDeFioDLParser forSyDeFioDLParser = new ForSyDeFioDLParser(commonTokenStream);
        return visitSystemGraphDirect(forSyDeFioDLParser.systemGraph());
    }

    @Override
    public void writeModel(ForSyDeSystemGraph model, OutputStream out) throws Exception {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("systemgraph {");
        for (Vertex v: model.vertexSet()) {
            stringBuilder.append("  vertex ").append(v.getIdentifier()).append("\n")
                .append("  [").append(v.vertexTraits.stream().map(Trait::getName).sorted().collect(Collectors.joining(", "))).append("]\n")
                .append("  (").append(v.ports.stream().sorted().collect(Collectors.joining(", "))).append(")\n");
            if (v.properties.isEmpty())
                stringBuilder.append("  {}\n");
            else {
                stringBuilder.append("  {\n")
                    .append(v.properties.entrySet().stream().map(e ->
                    " ".repeat(4) + "\"" + e.getKey() +"\": " + writeVertexPropertyCode(e.getValue(), 4)
                ).collect(Collectors.joining(",\n")))
                    .append("\n  }\n");
            }
        }
        for (EdgeInfo e : model.edgeSet()) {
            stringBuilder.append("edge ");
            stringBuilder.append("[").append(e.edgeTraits.stream().map(Trait::getName).sorted().collect(Collectors.joining(","))).append("] ");
            stringBuilder.append("from ").append(e.sourceId).append(" ");
            e.getSourcePort().ifPresent(p -> stringBuilder.append("port " + p + " "));
            stringBuilder.append("to ").append(e.targetId).append(" ");
            e.getTargetPort().ifPresent(p -> stringBuilder.append("port " + p));
            stringBuilder.append("\n");
        }
        stringBuilder.append("}");
        out.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
    }

    public String writeVertexPropertyCode(VertexProperty property, int identLevel) {
        return VertexProperties.cases()
                .StringVertexProperty(s -> "\"" + s + "\"")
                .IntVertexProperty(i -> i + "_i")
                .BooleanVertexProperty(b -> b.toString())
                .FloatVertexProperty(f -> f.toString() + "_32")
                .DoubleVertexProperty(d -> d.toString() + "_64")
                .LongVertexProperty(l -> l.toString() + "_l")
                .ArrayVertexProperty(a ->
                    "[\n" +
                    a.stream().map(v ->
                            " ".repeat(identLevel + 2) + writeVertexPropertyCode(v, identLevel + 2)).collect(Collectors.joining(",\n")) +
                    "\n" + " ".repeat(identLevel) + "]"
                )
                .IntMapVertexProperty(imap ->
                    "{\n" +
                    imap.entrySet().stream()
                            .map(e ->
                                    " ".repeat(identLevel + 2) + e.getKey().toString() +"_i: " + writeVertexPropertyCode(e.getValue(), identLevel + 2))
                            .collect(Collectors.joining(",\n")) +
                            "\n" + " ".repeat(identLevel) + "}"
                )
                .StringMapVertexProperty(smap ->
                    "{\n" +
                    smap.entrySet().stream()
                            .map(e ->
                                    " ".repeat(identLevel + 2) + "\"" + e.getKey() +"\": " + writeVertexPropertyCode(e.getValue(), identLevel + 2))
                            .collect(Collectors.joining(",\n")) +
                    "\n" + " ".repeat(identLevel) + "}"
                )
                .apply(property);
    }

    public Object visitNumberDirect(ForSyDeFioDLParser.NumberContext ctx) throws FioDLSyntaxException {
        if (ctx.intVal != null) {
            return Integer.parseInt(ctx.intVal.getText().replace("_i", ""));
        } else if (ctx.longVal != null) {
            return Long.parseLong(ctx.longVal.getText().replace("_l", ""));
        } else if (ctx.realVal != null) {
            final String[] numAndBits = ctx.realVal.getText().split("_");
            final int bits = Integer.parseInt(numAndBits[1]);
            if (bits <= 32) {
                return Float.parseFloat(numAndBits[0]);
            } else {
                return Double.parseDouble(numAndBits[0]);
            }
        } else {
            throw new FioDLSyntaxException("Could not parse property at " + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine());
        }
    }

    public String visitStringValDirect(ForSyDeFioDLParser.StringValContext ctx) throws FioDLSyntaxException {
        if (ctx.content != null) {
            return ctx.content.stream().map(Token::getText).collect(Collectors.joining());
        }
//        else if (ctx.multiLineContent != null) {
//            System.out.println("multi");
//            System.out.println(ctx.multiLineContent.stream().map(Token::getText).collect(Collectors.joining("\n")));
//            return VertexProperty.create(
//                    ctx.multiLineContent.stream().map(Token::getText).collect(Collectors.joining("\n"))
//            );
//        }
        else {
            throw new FioDLSyntaxException("Could not parse string property at " + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine());
        }
    }

    public ForSyDeSystemGraph visitSystemGraphDirect(ForSyDeFioDLParser.SystemGraphContext ctx) throws FioDLSyntaxException, InconsistentModelException {
        final ForSyDeSystemGraph newModel = new ForSyDeSystemGraph();
        for (ForSyDeFioDLParser.VertexContext vertexContext : ctx.vertex()) {
            final Vertex vertex = visitVertexDirect(vertexContext);
            newModel.addVertex(vertex);
        }
        for (ForSyDeFioDLParser.EdgeContext edgeContext : ctx.edges) {
            final EdgeInfo edgeInfo = visitEdgeDirect(edgeContext);
            final Vertex source = newModel.vertexSet().stream().filter(v -> v.getIdentifier().equals(edgeInfo.sourceId)).findFirst().orElseThrow(() ->
                    new InconsistentModelException("edge at " + edgeContext.getStart().getLine() + ":" + edgeContext.getStart().getCharPositionInLine() +
                            " declares source '" + edgeInfo.sourceId +"' that does not exist."));
            final Vertex target = newModel.vertexSet().stream().filter(v -> v.getIdentifier().equals(edgeInfo.targetId)).findFirst().orElseThrow(() ->
                    new InconsistentModelException("edge at " + edgeContext.getStart().getLine() + ":" + edgeContext.getStart().getCharPositionInLine() +
                            " declares target '" + edgeInfo.targetId +"' that does not exist."));
            if (!edgeInfo.getSourcePort().map(s -> source.ports.contains(s)).orElse(true)) {
                throw new InconsistentModelException("edge at " + edgeContext.getStart().getLine() + ":" + edgeContext.getStart().getCharPositionInLine() +
                        " declares port '" + edgeInfo.getSourcePort().get() +"' at source " +
                        source.getIdentifier() + " which it does not declare.");
            }
            if (!edgeInfo.getTargetPort().map(s -> target.ports.contains(s)).orElse(true)) {
                throw new InconsistentModelException("edge at " + edgeContext.getStart().getLine() + ":" + edgeContext.getStart().getCharPositionInLine() +
                        " declares port '" + edgeInfo.getTargetPort().get() +"' at target " +
                        target.getIdentifier() + " which it does not declare.");
            }
            newModel.addEdge(source, target, edgeInfo);
        }
        return newModel;
    }

    protected Vertex visitVertexDirect(ForSyDeFioDLParser.VertexContext ctx) throws FioDLSyntaxException {
        final Vertex newVertex = new Vertex(ctx.name.getText());
        for (Token traitToken : ctx.traits) {
            newVertex.addTraits(VertexTrait.fromName(traitToken.getText()));
        }
        for (Token portToken : ctx.ports) {
            newVertex.ports.add(portToken.getText());
        }
        for (int i = 0; i < ctx.propertyNames.size(); i++) {
            final String propName = visitStringValDirect(ctx.propertyNames.get(i));
            final VertexProperty propVal = visitVertexPropertyValueDirect(ctx.propertyValues.get(i));
            newVertex.properties.put(propName, propVal);
        }
        return newVertex;
    }

    public Object visitVertexPropertyKeyDirect(ForSyDeFioDLParser.VertexPropertyMapKeyContext ctx) throws FioDLSyntaxException {
        if (ctx.number() != null) {
            return visitNumberDirect(ctx.number());
        } else if (ctx.stringVal() != null) {
            return visitStringValDirect(ctx.stringVal());
        } else {
            throw new FioDLSyntaxException("Could not parse map key property at " + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine());
        }
    }

    public VertexProperty visitVertexPropertyValueDirect(ForSyDeFioDLParser.VertexPropertyValueContext ctx) throws FioDLSyntaxException {
        if (ctx.number() != null) {
            return VertexProperty.create(visitNumberDirect(ctx.number()));
        } else if (ctx.stringVal() != null) {
            return VertexProperty.create(visitStringValDirect(ctx.stringVal()));
        } else if (ctx.vertexPropertyArray() != null) {
            final ForSyDeFioDLParser.VertexPropertyArrayContext arrayContext = ctx.vertexPropertyArray();
            final List<VertexProperty> props = new ArrayList<>(arrayContext.arrayEntries.size());
            for (ForSyDeFioDLParser.VertexPropertyValueContext child : arrayContext.arrayEntries) {
                props.add(visitVertexPropertyValueDirect(child));
            }
            return VertexProperty.create(props);
        } else if (ctx.vertexPropertyMap() != null) {
            final ForSyDeFioDLParser.VertexPropertyMapContext mapContext = ctx.vertexPropertyMap();
            boolean isIntMap = true;
            Object firstKey = null;
            // we also catch index out of bounds exception because some dictionaries may be empty, just like {}
            try {
                firstKey = visitVertexPropertyKeyDirect(mapContext.mapKey.get(0));
                if (firstKey instanceof String) {
                    isIntMap = false;
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                isIntMap = false;
            }
            final Map<Object, VertexProperty> props = new HashMap<>(mapContext.mapKey.size());
            for (int i = 0; i < mapContext.mapKey.size(); i++) {
                final VertexProperty value = visitVertexPropertyValueDirect(mapContext.mapValue.get(i));
                if (isIntMap) {
                    try {
                        final Integer key = (Integer) visitVertexPropertyKeyDirect(mapContext.mapKey.get(i));
                        props.put(key, value);
                    } catch (NumberFormatException e) {
                        throw new FioDLSyntaxException("Expected an integer key at " + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine());
                    }
                } else {
                    final String key = (String) visitVertexPropertyKeyDirect(mapContext.mapKey.get(i));
                    props.put(key, value);
                }
            }
            return VertexProperty.create(props);
        } else {
            throw new FioDLSyntaxException("Could not parse property at " + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine());
        }
    }

    public EdgeInfo visitEdgeDirect(ForSyDeFioDLParser.EdgeContext ctx) {
        final EdgeInfo edgeInfo = new EdgeInfo(ctx.source.getText(), ctx.target.getText());
        if (ctx.sourceport != null) edgeInfo.sourcePort = ctx.sourceport.getText();
        if (ctx.targetport != null) edgeInfo.targetPort = ctx.targetport.getText();
        for (Token traitToken : ctx.traits) {
            edgeInfo.addTraits(EdgeTrait.fromName(traitToken.getText()));
        }
        return edgeInfo;
    }

//    public StringBuilder prettyPrintProperty(VertexProperty vertexProperty, PicoWriter picoWriter) {
//        VertexProperties.caseOf(vertexProperty)
//                .StringVertexProperty(s -> {picoWriter.writeln("\"" + s + "\""); return picoWriter;})
//                .IntVertexProperty(i ->  {picoWriter.writeln(i + "_i"); return picoWriter;})
//                .BooleanVertexProperty(b ->  {picoWriter.writeln(b.toString()); return picoWriter;})
//                .FloatVertexProperty(f ->  {picoWriter.writeln(f.toString() + "_32"); return picoWriter;})
//                .DoubleVertexProperty(d ->  {picoWriter.writeln(d.toString() + "_64"); return picoWriter;})
//                .LongVertexProperty(l ->  {picoWriter.writeln(l.toString() + "_l"); return picoWriter;})
//                .ArrayVertexProperty(a -> {
//                    picoWriter.writeln_r("[");
//                    a.forEach(v -> prettyPrintProperty(v, picoWriter));
//                    picoWriter.writeln_l("]");
//                    return picoWriter;
//                })
//                .IntMapVertexProperty(imap -> {
//                    picoWriter.writeln_r("{");
//                    imap.forEach((key, value) -> {
//                        picoWriter.write(key.toString() + "_i: ");
//                        prettyPrintProperty(value, picoWriter);
//                    });
//                    picoWriter.writeln_l("}");
//                    return picoWriter;
//                })
//                .StringMapVertexProperty(smap -> {
//                    picoWriter.writeln_r("{");
//                    smap.forEach((key, value) -> {
//                        picoWriter.write("\"" + key + "\": ");
//                        prettyPrintProperty(value, picoWriter);
//                    });
//                    picoWriter.writeln_l("}");
//                    return picoWriter;
//                });
//    }
}
