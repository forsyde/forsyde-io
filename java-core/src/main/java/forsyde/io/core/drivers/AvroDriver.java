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
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Driver that reads ForSyDe IO Avro system graph files.
 */
public class AvroDriver implements ModelDriver {

    static final DatumReader<forsyde.io.core.avro.SystemGraph> systemGraphDatumReader = new SpecificDatumReader<forsyde.io.core.avro.SystemGraph>(forsyde.io.core.avro.SystemGraph.class);
    static final DatumWriter<forsyde.io.core.avro.SystemGraph> systemGraphDatumWriter = new SpecificDatumWriter<>(forsyde.io.core.avro.SystemGraph.class);
    static final DataFileWriter<forsyde.io.core.avro.SystemGraph> systemGraphFileWriter = new DataFileWriter<>(systemGraphDatumWriter);

    @Override
    public List<String> inputExtensions() {
        return List.of("favro", "fio.avro");
    }

    @Override
    public List<String> outputExtensions() {
        return List.of("favro", "fio.avro");
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
        var avroSystemGraph = toAvroSystemGraph(model);
        systemGraphFileWriter.append(avroSystemGraph);
        systemGraphFileWriter.close();
    }

    private forsyde.io.core.avro.SystemGraph toAvroSystemGraph(SystemGraph model) {
        var avroSystemGraph = new forsyde.io.core.avro.SystemGraph();
        List<Vertex> avroVertices = new ArrayList<>();
        List<forsyde.io.core.avro.EdgeInfo> avroEdges = new ArrayList<>();
        for (var e : model.edgeSet()) {
            var avroEBuilder = forsyde.io.core.avro.EdgeInfo.newBuilder()
                .setSource(e.getSource())
                .setTarget(e.getTarget());
            e.getSourcePort().ifPresent(avroEBuilder::setSourcePort);
            e.getTargetPort().ifPresent(avroEBuilder::setTargetPort);
            List<CharSequence> traits = e.getTraits().stream().map(forsyde.io.core.Trait::getName).collect(Collectors.toList());
            avroEBuilder.setTraits(traits);
            avroEdges.add(avroEBuilder.build());
        }
        for (var v : model.vertexSet()) {
            var avroVBuilder = Vertex.newBuilder().setIdentifier(v.getIdentifier());
            List<CharSequence> ports = v.getPorts().stream().collect(Collectors.toList());
            List<CharSequence> traits = v.getTraits().stream().map(forsyde.io.core.Trait::getName).collect(Collectors.toList());
            avroVBuilder.setTraits(traits);
            avroVBuilder.setPorts(ports);
            Map<CharSequence, VertexProperty> properties = new HashMap<>();
            for (var propK: v.getPropertiesNames()) {
                properties.put(propK, toVertexProperty(v.getProperty(propK)));
            }
            avroVBuilder.setProperties(properties);
            avroVertices.add(avroVBuilder.build());
        }
        avroSystemGraph.setEdges(avroEdges);
        avroSystemGraph.setVertices(avroVertices);
        return avroSystemGraph;
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

    private VertexProperty toVertexProperty(int v) {
        return VertexProperty.newBuilder().setValue(Integer.valueOf(v)).build();
    }

    private VertexProperty toVertexProperty(long v) {
        return VertexProperty.newBuilder().setValue(Long.valueOf(v)).build();
    }

    private VertexProperty toVertexProperty(double v) {
        return VertexProperty.newBuilder().setValue(Double.valueOf(v)).build();
    }

    private VertexProperty toVertexProperty(boolean v) {
        return VertexProperty.newBuilder().setValue(Boolean.valueOf(v)).build();
    }

    private VertexProperty toVertexProperty(float v) {
        return VertexProperty.newBuilder().setValue(Float.valueOf(v)).build();
    }

    private VertexProperty toVertexProperty(Object object) {
        var vertexProperty = new VertexProperty();
        if (object instanceof Integer i) {
            vertexProperty.setValue(i);
        } else if (object instanceof Long l) {
            vertexProperty.setValue(l);
        } else if (object instanceof Float f) {
            vertexProperty.setValue(f);
        } else if (object instanceof Double d) {
            vertexProperty.setValue(d);
        } else if (object instanceof Boolean b) {
            vertexProperty.setValue(b);
        } else if (object instanceof Map<?, ?> map) {
            var newMap = new HashMap<String, VertexProperty>();
            // due to how avro works, the keys are always string, so there is no loss here
            for (var k: map.keySet()) {
                newMap.put(k.toString(), toVertexProperty(map.get(k)));
            }
            vertexProperty.setValue(newMap);
        } else if (object instanceof List<?> arr) {
            var newList = new ArrayList<VertexProperty>();
            // due to how avro works, the keys are always string, so there is no loss here
            for (var elem : arr) {
                newList.add(toVertexProperty(elem));
            }
            vertexProperty.setValue(newList);
        } else {
            vertexProperty.setValue(object.toString());
        }
        return vertexProperty;
    }
}
