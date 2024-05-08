package forsyde.io.core.drivers;

import forsyde.io.core.*;
import forsyde.io.java.adapters.fiodl.ForSyDeFioDL;
import forsyde.io.java.adapters.fiodl.ForSyDeFioDLBaseVisitor;
import forsyde.io.java.adapters.fiodl.ForSyDeFioDLLex;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class FiodlDriver extends ForSyDeFioDLBaseVisitor<SystemGraph> implements ModelDriver {

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
    public SystemGraph loadModel(InputStream in) throws Exception {
        final ForSyDeFioDLLex ForSyDeFioDLLex = new ForSyDeFioDLLex(CharStreams.fromStream(in));
        final CommonTokenStream commonTokenStream = new CommonTokenStream(ForSyDeFioDLLex);
        final ForSyDeFioDL ForSyDeFioDL = new ForSyDeFioDL(commonTokenStream);
        return visitSystemGraphDirect(ForSyDeFioDL.systemGraph());
    }

    @Override
    public String printModel(SystemGraph model) throws Exception {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("systemgraph {\n");
        for (Vertex v: model.vertexSet()) {
            stringBuilder.append("  vertex ").append('"').append(v.getIdentifier()).append('"').append("\n")
                    .append("  [").append(v.getTraits().stream().map(Trait::getName).sorted().collect(Collectors.joining(", "))).append("]\n")
                    .append("  (").append(v.getPorts().stream().sorted().collect(Collectors.joining(", "))).append(")\n");
            if (v.getPropertiesNames().isEmpty())
                stringBuilder.append("  {}\n");
            else {
                stringBuilder.append("  {\n")
                        .append(v.getPropertiesNames().stream().map(name ->
                                " ".repeat(4) + "\"" + name +"\": " + writeVertexPropertyCode(v.getProperty(name), 4)
                        ).collect(Collectors.joining(",\n")))
                        .append("\n  }\n");
            }
        }
        for (EdgeInfo e : model.edgeSet()) {
            stringBuilder.append("  edge ");
            stringBuilder.append("[").append(e.edgeTraits.stream().map(Trait::getName).sorted().collect(Collectors.joining(","))).append("] ");
            stringBuilder.append("from ").append('"').append(e.sourceId).append('"').append(" ");
            e.getSourcePort().ifPresent(p -> stringBuilder.append("port \"").append(p).append("\" "));
            stringBuilder.append("to ").append('"').append(e.targetId).append('"').append(" ");
            e.getTargetPort().ifPresent(p -> stringBuilder.append("port \"").append(p).append("\""));
            stringBuilder.append("\n");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    @Override
    public void writeModel(SystemGraph model, OutputStream out) throws Exception {
        out.write(printModel(model).getBytes(StandardCharsets.UTF_8));
    }

    public String writeVertexPropertyCode(Object property, int identLevel) {
        if (property instanceof String) return  "\"" + property + "\"";
        if (property instanceof Integer) return  property + "_i";
        if (property instanceof Boolean) return  ((Boolean) property) ? "1_b" : "0_b";
        if (property instanceof Float) return  String.format(Locale.ENGLISH, "%17.9f", ((Float) property)) + "_32";
        if (property instanceof Double) return  String.format(Locale.ENGLISH, "%18.11f", ((Double) property)) + "_64";
        if (property instanceof Long) return  property + "_l";
        if (property instanceof List<?>) return "[\n" +
                ((List<Object>) property).stream().map(v ->
                        " ".repeat(identLevel + 2) + writeVertexPropertyCode(v, identLevel + 2)).collect(Collectors.joining(",\n")) +
                "\n" + " ".repeat(identLevel) + "]";
        if (property instanceof Map<?, ?>) {
            if (((Map<Object, ?>) property).keySet().stream().anyMatch(i -> i instanceof Integer)) {
                return "{\n" +
                        ((Map<Integer, Object>) property).entrySet().stream()
                                .map(e ->
                                        " ".repeat(identLevel + 2) + e.getKey().toString() +"_i: " + writeVertexPropertyCode(e.getValue(), identLevel + 2))
                                .collect(Collectors.joining(",\n")) +
                        "\n" + " ".repeat(identLevel) + "}";
            } else if (((Map<Object, ?>) property).keySet().stream().anyMatch(i -> i instanceof String)) {
                return "{\n" +
                        ((Map<String, Object>) property).entrySet().stream()
                        .map(e ->
                                " ".repeat(identLevel + 2) + "\"" + e.getKey() +"\": " + writeVertexPropertyCode(e.getValue(), identLevel + 2))
                        .collect(Collectors.joining(",\n")) +
                        "\n" + " ".repeat(identLevel) + "}";
            }
        }
        return property.toString();
    }

    public Object visitNumberDirect(ForSyDeFioDL.NumberContext ctx) throws FioDLSyntaxException {
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

//    public String visitStringValDirect(ForSyDeFioDL.StringValContext ctx) throws FioDLSyntaxException {
//        if (ctx.content != null) {
//            return ctx.content.stream().map(Token::getText).collect(Collectors.joining());
//        }
////        else if (ctx.multiLineContent != null) {
////            System.out.println("multi");
////            System.out.println(ctx.multiLineContent.stream().map(Token::getText).collect(Collectors.joining("\n")));
////            return VertexProperty.create(
////                    ctx.multiLineContent.stream().map(Token::getText).collect(Collectors.joining("\n"))
////            );
////        }
//        else {
//            throw new FioDLSyntaxException("Could not parse string property at " + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine());
//        }
//    }

    public SystemGraph visitSystemGraphDirect(ForSyDeFioDL.SystemGraphContext ctx) throws FioDLSyntaxException, InconsistentModelException {
        final SystemGraph newModel = new SystemGraph();
        for (ForSyDeFioDL.VertexContext vertexContext : ctx.vertex()) {
            final Vertex vertex = visitVertexDirect(vertexContext);
            newModel.addVertex(vertex);
        }
        for (ForSyDeFioDL.EdgeContext edgeContext : ctx.edges) {
            final EdgeInfo edgeInfo = visitEdgeDirect(edgeContext);
            final Vertex source = newModel.vertexSet().stream().filter(v -> v.getIdentifier().equals(edgeInfo.sourceId)).findFirst().orElseThrow(() ->
                    new InconsistentModelException("edge at " + edgeContext.getStart().getLine() + ":" + edgeContext.getStart().getCharPositionInLine() +
                            " declares source '" + edgeInfo.sourceId +"' that does not exist."));
            final Vertex target = newModel.vertexSet().stream().filter(v -> v.getIdentifier().equals(edgeInfo.targetId)).findFirst().orElseThrow(() ->
                    new InconsistentModelException("edge at " + edgeContext.getStart().getLine() + ":" + edgeContext.getStart().getCharPositionInLine() +
                            " declares target '" + edgeInfo.targetId +"' that does not exist."));
            if (!edgeInfo.getSourcePort().map(source::hasPort).orElse(true)) {
                throw new InconsistentModelException("edge at " + edgeContext.getStart().getLine() + ":" + edgeContext.getStart().getCharPositionInLine() +
                        " declares port '" + edgeInfo.getSourcePort().get() +"' at source " +
                        source.getIdentifier() + " which it does not declare.");
            }
            if (!edgeInfo.getTargetPort().map(target::hasPort).orElse(true)) {
                throw new InconsistentModelException("edge at " + edgeContext.getStart().getLine() + ":" + edgeContext.getStart().getCharPositionInLine() +
                        " declares port '" + edgeInfo.getTargetPort().get() +"' at target " +
                        target.getIdentifier() + " which it does not declare.");
            }
            newModel.addEdge(source, target, edgeInfo);
        }
        return newModel;
    }

    protected Vertex visitVertexDirect(ForSyDeFioDL.VertexContext ctx) throws FioDLSyntaxException {
        final Vertex newVertex = new Vertex(ctx.name.getText());
        for (Token traitToken : ctx.traits) {
            newVertex.addTraits(new OpaqueTrait(traitToken.getText()));
        }
        for (Token portToken : ctx.ports) {
            newVertex.addPort(portToken.getText());
        }
        for (int i = 0; i < ctx.propertyNames.size(); i++) {
//            final String propName = visitStringValDirect(ctx.propertyNames.get(i));
            final String propName = ctx.propertyNames.get(i).getText();
            final Object propVal = visitVertexPropertyValueDirect(ctx.propertyValues.get(i));
            newVertex.putProperty(propName, propVal);
        }
        return newVertex;
    }

    public Object visitVertexPropertyKeyDirect(ForSyDeFioDL.VertexPropertyMapKeyContext ctx) throws FioDLSyntaxException {
        if (ctx.number() != null) {
            return visitNumberDirect(ctx.number());
        } else if (ctx.stringValue != null) {
            return ctx.stringValue.getText();
//            return visitStringValDirect(ctx.stringVal());
        } else {
            throw new FioDLSyntaxException("Could not parse map key property at " + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine());
        }
    }

    public Object visitVertexPropertyValueDirect(ForSyDeFioDL.VertexPropertyValueContext ctx) throws FioDLSyntaxException {
        if (ctx.number() != null) {
            return visitNumberDirect(ctx.number());
        } else if (ctx.booleanValue != null) {
            return ctx.booleanValue.getText().equals("1_b");
        } else if (ctx.vertexPropertyArray() != null) {
            final ForSyDeFioDL.VertexPropertyArrayContext arrayContext = ctx.vertexPropertyArray();
            final List<Object> props = new ArrayList<>(arrayContext.arrayEntries.size());
            for (ForSyDeFioDL.VertexPropertyValueContext child : arrayContext.arrayEntries) {
                props.add(visitVertexPropertyValueDirect(child));
            }
            return props;
        } else if (ctx.vertexPropertyMap() != null) {
            final ForSyDeFioDL.VertexPropertyMapContext mapContext = ctx.vertexPropertyMap();
            boolean isIntMap = true;
            Object firstKey;
            // we also catch index out of bounds exception because some dictionaries may be empty, just like {}
            try {
                firstKey = visitVertexPropertyKeyDirect(mapContext.mapKey.get(0));
                if (firstKey instanceof String) {
                    isIntMap = false;
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                isIntMap = false;
            }
            final Map<Object, Object> props = new HashMap<>(mapContext.mapKey.size());
            for (int i = 0; i < mapContext.mapKey.size(); i++) {
                final Object value = visitVertexPropertyValueDirect(mapContext.mapValue.get(i));
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
            return props;
        } else {
            return ctx.stringValue != null ? ctx.stringValue.getText() : "";
//            throw new FioDLSyntaxException("Could not parse property at " + ctx.getStart().getLine() + ":" + ctx.getStart().getCharPositionInLine());
        }
    }

    public EdgeInfo visitEdgeDirect(ForSyDeFioDL.EdgeContext ctx) {
        final EdgeInfo edgeInfo = new EdgeInfo(ctx.source.getText(), ctx.target.getText());
        if (ctx.sourceport != null) edgeInfo.setSourcePort(ctx.sourceport.getText());
        if (ctx.targetport != null) edgeInfo.setTargetPort(ctx.targetport.getText());
        for (Token traitToken : ctx.traits) {
            // TODO: fix this!
            edgeInfo.addTraits(new OpaqueTrait(traitToken.getText()));
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
