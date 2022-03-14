package forsyde.io.java.drivers;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexProperty;
import forsyde.io.java.core.VertexTrait;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class ForSyDeFiodlHandler implements ForSyDeModelDriver {


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
        return null;
    }

    @Override
    public void writeModel(ForSyDeSystemGraph model, OutputStream out) throws Exception {

    }
}
