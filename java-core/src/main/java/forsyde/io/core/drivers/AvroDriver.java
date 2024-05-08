package forsyde.io.core.drivers;

import forsyde.io.core.EdgeInfo;
import forsyde.io.core.OpaqueTrait;
import forsyde.io.core.SystemGraph;
import forsyde.io.core.avro.Vertex;
import forsyde.io.core.avro.VertexProperty;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileStream;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Driver that reads ForSyDe IO Avro system graph files.
 */
public class AvroDriver implements ModelDriver {

    static final DatumReader<forsyde.io.core.avro.SystemGraph> systemGraphDatumReader = new SpecificDatumReader<forsyde.io.core.avro.SystemGraph>(forsyde.io.core.avro.SystemGraph.class);
    static final DatumWriter<forsyde.io.core.avro.SystemGraph> systemGraphDatumWriter = new SpecificDatumWriter<>(forsyde.io.core.avro.SystemGraph.class);
    static final DataFileWriter<forsyde.io.core.avro.SystemGraph> systemGraphFileWriter = new DataFileWriter<>(systemGraphDatumWriter);

    @Override
    public List<String> inputExtensions() {
        return List.of("favro");
    }

    @Override
    public List<String> outputExtensions() {
        return List.of("favro");
    }

    @Override
    public SystemGraph loadModel(InputStream in) throws Exception {
        var systemGraphDataFileReader = new DataFileStream<forsyde.io.core.avro.SystemGraph>(in, systemGraphDatumReader);
        forsyde.io.core.avro.SystemGraph avroSystemGraph = null;
        SystemGraph systemGraph = new SystemGraph();
        while (systemGraphDataFileReader.hasNext()) {
            avroSystemGraph = systemGraphDataFileReader.next(avroSystemGraph);
            for (var avroV : avroSystemGraph.getVertices()) {
                var vertex = systemGraph.newVertex(avroV.getIdentifier().toString());
                for (var t : avroV.getTraits()) {
                    vertex.addTrait(new OpaqueTrait(t.toString()));
                }
                for (var p : avroV.getPorts()) {
                    vertex.addPort(p.toString());
                }
                for (var propK: avroV.getProperties().keySet()) {
                    vertex.putProperty(propK.toString(), fromVertexProperty(avroV.getProperties().get(propK)));
                }
            }
            for (var avroE : avroSystemGraph.getEdges()) {
                var src = systemGraph.newVertex(avroE.getSource().toString());
                var dst = systemGraph.newVertex(avroE.getTarget().toString());
                var edge = new EdgeInfo(src, dst);
                if (avroE.getSourcePort() != null) edge.setSourcePort(avroE.getSourcePort().toString());
                if (avroE.getTargetPort() != null) edge.setSourcePort(avroE.getTargetPort().toString());
                for (var t : avroE.getTraits()) {
                    edge.addTraits(new OpaqueTrait(t.toString()));
                }
                systemGraph.addEdge(src, dst, edge);
            }
        }
        return systemGraph;
    }

    @Override
    public void writeModel(SystemGraph model, OutputStream out) throws Exception {
        systemGraphFileWriter.create(forsyde.io.core.avro.SystemGraph.getClassSchema(), out);
        systemGraphFileWriter.close();
    }

    @Override
    public String printModel(SystemGraph model) throws Exception {
        return "";
    }

    private Object fromVertexProperty(VertexProperty vertexProperty) {
        if (vertexProperty.getValue() instanceof Integer i) {
            return i;
        } else if (vertexProperty.getValue() instanceof Long l) {
            return l;
        } else if (vertexProperty.getValue() instanceof Float f) {
            return f;
        } else if (vertexProperty.getValue() instanceof Double d) {
            return d;
        } else if (vertexProperty.getValue() instanceof Boolean b) {
            return b;
        } else if (vertexProperty.getValue() instanceof Map<?, ?> map) {
            var newMap = new HashMap<String, Object>();
            // due to how avro works, the keys are always string, so there is no loss here
            for (var k: map.keySet()) {
                if (map.get(k) instanceof VertexProperty v) {
                    newMap.put(k.toString(), fromVertexProperty(v));
                }
            }
            return newMap;
        } else if (vertexProperty.getValue() instanceof List<?> arr) {
            var newList = new ArrayList<Object>();
            // due to how avro works, the keys are always string, so there is no loss here
            for (var elem : arr) {
                if (elem instanceof VertexProperty v) {
                    newList.add(fromVertexProperty(v));
                }
            }
            return newList;
        } else {
            return vertexProperty.getValue().toString();
        }
    }
}
