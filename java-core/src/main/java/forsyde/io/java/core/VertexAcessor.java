package forsyde.io.java.core;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class VertexAcessor {

    public enum VertexPortDirection {
        INCOMING,
        OUTGOING,
        BIDIRECTIONAL
    }

    @Deprecated
    static public Optional<Vertex> getNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, String traitName) {
        final Trait t = VertexTrait.fromName(traitName);
        return getNamedPort(model, v, portName, t, VertexPortDirection.BIDIRECTIONAL);
    }


    @Deprecated
    static public Optional<Vertex> getNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, String traitName, String direction) {
        Trait t = VertexTrait.fromName(traitName);
        if (direction.equalsIgnoreCase("outgoing")) {
            return getNamedPort(model, v, portName, t, VertexPortDirection.OUTGOING);
        } else if (direction.equalsIgnoreCase("incoming")) {
            return getNamedPort(model, v, portName, t, VertexPortDirection.INCOMING);
        } else {
            return getNamedPort(model, v, portName, t, VertexPortDirection.BIDIRECTIONAL);
        }
    }

    static public Optional<Vertex> getNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, Trait trait) {
        return getNamedPort(model, v, portName, trait, VertexPortDirection.BIDIRECTIONAL);
    }

    static public Optional<Vertex> getNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, Trait trait, VertexPortDirection direction) {
        //Trait t = traitFromString(traitName);
        Stream<Vertex> outStream = model.outgoingEdgesOf(v).stream()
                .filter(e -> e.sourcePort.map(p -> p.equals(portName)).orElse(false))
                .map(model::getEdgeTarget)
                .filter(vv -> vv.hasTrait(trait));
        Stream<Vertex> inStream = model.incomingEdgesOf(v).stream()
                .filter(e -> e.targetPort.map(p -> p.equals(portName)).orElse(false))
                .map(model::getEdgeSource)
                .filter(vv -> vv.hasTrait(trait));
        switch (direction) {
            case OUTGOING:
                return outStream.findAny();
            case INCOMING:
                return inStream.findAny();
            case BIDIRECTIONAL:
            default:
                return Stream.concat(outStream, inStream).findAny();
        }
    }

    static public Optional<Vertex> getNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, Trait trait, VertexPortDirection direction, EdgeTrait edgeTrait) {
        //Trait t = traitFromString(traitName);
        Stream<Vertex> outStream = model.outgoingEdgesOf(v).stream()
                .filter(e -> e.sourcePort.map(p -> p.equals(portName)).orElse(false))
                .filter(e -> e.hasTrait(edgeTrait))
                .map(model::getEdgeTarget)
                .filter(vv -> vv.hasTrait(trait));
        Stream<Vertex> inStream = model.incomingEdgesOf(v).stream()
                .filter(e -> e.targetPort.map(p -> p.equals(portName)).orElse(false))
                .filter(e -> e.hasTrait(edgeTrait))
                .map(model::getEdgeSource)
                .filter(vv -> vv.hasTrait(trait));
        switch (direction) {
            case OUTGOING:
                return outStream.findAny();
            case INCOMING:
                return inStream.findAny();
            case BIDIRECTIONAL:
            default:
                return Stream.concat(outStream, inStream).findAny();
        }
    }

    @Deprecated
    static public Set<Vertex> getMultipleNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, String traitName) {
        return getMultipleNamedPort(model, v, portName, traitName, "BIDIRECTIONAL");
    }

    @Deprecated
    static public Set<Vertex> getMultipleNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, String traitName, String direction) {
        Trait t = VertexTrait.fromName(traitName);
        if (direction.equalsIgnoreCase("outgoing")) {
            return getMultipleNamedPort(model, v, portName, t, VertexPortDirection.OUTGOING);
        } else if (direction.equalsIgnoreCase("incoming")) {
            return getMultipleNamedPort(model, v, portName, t, VertexPortDirection.INCOMING);
        } else {
            return getMultipleNamedPort(model, v, portName, t, VertexPortDirection.BIDIRECTIONAL);
        }
    }

    static public Set<Vertex> getMultipleNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, Trait trait, VertexPortDirection direction) {
        Stream<Vertex> outStream = model.outgoingEdgesOf(v).stream()
                .filter(e -> e.sourcePort.map(p -> p.equals(portName)).orElse(false))
                .map(model::getEdgeTarget)
                .filter(vv -> vv.hasTrait(trait));
        Stream<Vertex> inStream = model.incomingEdgesOf(v).stream()
                .filter(e -> e.targetPort.map(p -> p.equals(portName)).orElse(false))
                .map(model::getEdgeSource)
                .filter(vv -> vv.hasTrait(trait));
        switch (direction) {
            case OUTGOING:
                return outStream.collect(Collectors.toSet());
            case INCOMING:
                return inStream.collect(Collectors.toSet());
            case BIDIRECTIONAL:
            default:
                return Stream.concat(outStream, inStream).collect(Collectors.toSet());
        }
    }

    static public Set<Vertex> getMultipleNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, Trait trait, VertexPortDirection direction, EdgeTrait edgeTrait) {
        Stream<Vertex> outStream = model.outgoingEdgesOf(v).stream()
                .filter(e -> e.sourcePort.map(p -> p.equals(portName)).orElse(false))
                .filter(e -> e.hasTrait(edgeTrait))
                .map(model::getEdgeTarget)
                .filter(vv -> vv.hasTrait(trait));
        Stream<Vertex> inStream = model.incomingEdgesOf(v).stream()
                .filter(e -> e.targetPort.map(p -> p.equals(portName)).orElse(false))
                .filter(e -> e.hasTrait(edgeTrait))
                .map(model::getEdgeSource)
                .filter(vv -> vv.hasTrait(trait));
        switch (direction) {
            case OUTGOING:
                return outStream.collect(Collectors.toSet());
            case INCOMING:
                return inStream.collect(Collectors.toSet());
            case BIDIRECTIONAL:
            default:
                return Stream.concat(outStream, inStream).collect(Collectors.toSet());
        }
    }

    @Deprecated
    static public List<Vertex> getOrderedMultipleNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, String traitName) {
        Trait t = VertexTrait.fromName(traitName);
        return getOrderedMultipleNamedPort(model, v, portName, t, VertexPortDirection.BIDIRECTIONAL);
    }

    @Deprecated
    static public List<Vertex> getOrderedMultipleNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, String traitName, String direction) {
        Trait t = VertexTrait.fromName(traitName);
        if (direction.equalsIgnoreCase("outgoing")) {
            return getOrderedMultipleNamedPort(model, v, portName, t, VertexPortDirection.OUTGOING);
        } else if (direction.equalsIgnoreCase("incoming")) {
            return getOrderedMultipleNamedPort(model, v, portName, t, VertexPortDirection.INCOMING);
        } else {
            return getOrderedMultipleNamedPort(model, v, portName, t, VertexPortDirection.BIDIRECTIONAL);
        }
    }

    static public List<Vertex> getOrderedMultipleNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, Trait t, VertexPortDirection direction) {
        @SuppressWarnings("unchecked")
        Map<String, Integer> order = (Map<String, Integer>) v.getProperties()
                .get("__" + portName + "_ordering__")
                .unwrap();
        Stream<Vertex> outStream = model.outgoingEdgesOf(v).stream()
                .filter(e -> e.sourcePort.map(p -> p.equals(portName)).orElse(false))
                .map(model::getEdgeTarget)
                .filter(vv -> vv.hasTrait(t));
        Stream<Vertex> inStream = model.incomingEdgesOf(v).stream()
                .filter(e -> e.targetPort.map(p -> p.equals(portName)).orElse(false))
                .map(model::getEdgeSource)
                .filter(vv -> vv.hasTrait(t));
        switch (direction) {
            case OUTGOING:
                return outStream.sorted(Comparator.comparing(v2 -> order.getOrDefault(v2.identifier, 0))).collect(Collectors.toList());
            case INCOMING:
                return inStream.sorted(Comparator.comparing(v2 -> order.getOrDefault(v2.identifier, 0))).collect(Collectors.toList());
            case BIDIRECTIONAL:
            default:
                return Stream.concat(outStream, inStream).sorted(Comparator.comparing(v2 -> order.getOrDefault(v2.identifier, 0))).collect(Collectors.toList());
        }
    }

    static public List<Vertex> getOrderedMultipleNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, Trait t, VertexPortDirection direction, EdgeTrait edgeTrait) {
        @SuppressWarnings("unchecked")
        Map<String, Integer> order = (Map<String, Integer>) v.getProperties()
                .get("__" + portName + "_ordering__")
                .unwrap();
        Stream<Vertex> outStream = model.outgoingEdgesOf(v).stream()
                .filter(e -> e.sourcePort.map(p -> p.equals(portName)).orElse(false))
                .filter(e -> e.hasTrait(edgeTrait))
                .map(model::getEdgeTarget)
                .filter(vv -> vv.hasTrait(t));
        Stream<Vertex> inStream = model.incomingEdgesOf(v).stream()
                .filter(e -> e.targetPort.map(p -> p.equals(portName)).orElse(false))
                .filter(e -> e.hasTrait(edgeTrait))
                .map(model::getEdgeSource)
                .filter(vv -> vv.hasTrait(t));
        switch (direction) {
            case OUTGOING:
                return outStream.sorted(Comparator.comparing(v2 -> order.getOrDefault(v2.identifier, 0))).collect(Collectors.toList());
            case INCOMING:
                return inStream.sorted(Comparator.comparing(v2 -> order.getOrDefault(v2.identifier, 0))).collect(Collectors.toList());
            case BIDIRECTIONAL:
            default:
                return Stream.concat(outStream, inStream).sorted(Comparator.comparing(v2 -> order.getOrDefault(v2.identifier, 0))).collect(Collectors.toList());
        }
    }

    public static<Tsrc extends VertexViewer, Tdst extends VertexViewer> boolean setNamedPort(ForSyDeSystemGraph model, Tsrc src,  Tdst dst, String srcPortName, String dstPortName, EdgeTrait... ts) {
        return setNamedPort(model, src.getViewedVertex(), dst.getViewedVertex(), srcPortName, dstPortName, ts);
    }

    public static boolean setNamedPort(ForSyDeSystemGraph model, Vertex src,  Vertex dst, String srcPortName, String dstPortName, EdgeTrait... ts) {
        if (srcPortName != null && !src.ports.contains(srcPortName)) src.getPorts().add(srcPortName);
        if (dstPortName != null && !dst.ports.contains(dstPortName)) dst.getPorts().add(dstPortName);
        return model.connect(src, dst, srcPortName, dstPortName, ts);
    }

    public static<Tsrc extends VertexViewer, Tdst extends VertexViewer> boolean addMultipleNamedPort(ForSyDeSystemGraph model, Tsrc src,  Tdst dst, String srcPortName, String dstPortName, EdgeTrait... ts) {
        return addMultipleNamedPort(model, src.getViewedVertex(),  dst.getViewedVertex(), srcPortName, dstPortName, ts);
    }

    public static<Tsrc extends VertexViewer, Tdst extends VertexViewer, TdstS extends Iterable<Tdst>> boolean addMultipleNamedPort(ForSyDeSystemGraph model, Tsrc src,  TdstS dsts, String srcPortName, String dstPortName, EdgeTrait... ts) {
        boolean defined = true;
        for (Tdst dst : dsts) {
            defined = defined && addMultipleNamedPort(model, src, dst, srcPortName, dstPortName, ts);
        }
        return defined;
    }

    public static<Tsrc extends VertexViewer, Tdst extends VertexViewer, TsrcS extends Iterable<Tsrc>> boolean addMultipleNamedPort(ForSyDeSystemGraph model, TsrcS srcs,  Tdst dst, String srcPortName, String dstPortName, EdgeTrait... ts) {
        boolean defined = true;
        for (Tsrc src: srcs) {
            defined = defined && addMultipleNamedPort(model, src, dst, srcPortName, dstPortName, ts);
        }
        return defined;
    }

    public static boolean addMultipleNamedPort(ForSyDeSystemGraph model, Vertex src,  Vertex dst, String srcPortName, String dstPortName, EdgeTrait... ts) {
        if (srcPortName != null && !src.ports.contains(srcPortName)) src.getPorts().add(srcPortName);
        if (dstPortName != null && !dst.ports.contains(dstPortName)) dst.getPorts().add(dstPortName);
        return model.connect(src, dst, srcPortName, dstPortName, ts);
    }

    public static boolean insertOrderedMultipleNamedPort(ForSyDeSystemGraph model, Vertex src,  Vertex dst, String srcPortName, String dstPortName, EdgeTrait... ts) {
        return insertOrderedMultipleNamedPort(model, src,  dst, srcPortName, dstPortName, 0, ts);
    }

    public static<Tsrc extends VertexViewer, Tdst extends VertexViewer> boolean insertOrderedMultipleNamedPort(ForSyDeSystemGraph model, Tsrc src,  Tdst dst, String srcPortName, String dstPortName, EdgeTrait... ts) {
        return insertOrderedMultipleNamedPort(model, src.getViewedVertex(),  dst.getViewedVertex(), srcPortName, dstPortName, 0, ts);
    }

    public static<Tsrc extends VertexViewer, Tdst extends VertexViewer, TdstS extends Iterable<Tdst>> boolean insertOrderedMultipleNamedPort(ForSyDeSystemGraph model, Tsrc src,  TdstS dsts, String srcPortName, String dstPortName, EdgeTrait... ts) {
        boolean defined = true;
        int i = 0;
        for (Tdst dst : dsts) {
            defined = defined && insertOrderedMultipleNamedPort(model, src.getViewedVertex(),  dst.getViewedVertex(), srcPortName, dstPortName, i, ts);
            i += 1;
        }
        return defined;
    }

    public static<Tsrc extends VertexViewer, Tdst extends VertexViewer, TsrcS extends Iterable<Tsrc>> boolean insertOrderedMultipleNamedPort(ForSyDeSystemGraph model, TsrcS srcs,  Tdst dst, String srcPortName, String dstPortName, EdgeTrait... ts) {
        boolean defined = true;
        int i = 0;
        for (Tsrc src : srcs) {
            defined = defined && insertOrderedMultipleNamedPort(model, src.getViewedVertex(),  dst.getViewedVertex(), srcPortName, dstPortName, i, ts);
            i += 1;
        }
        return defined;
    }

    public static boolean insertOrderedMultipleNamedPort(ForSyDeSystemGraph model, Vertex src,  Vertex dst, String srcPortName, String dstPortName, int pos, EdgeTrait... ts) {
        final Map<String, Integer> order = (Map<String, Integer>) src.getProperties()
                .get("__" + srcPortName + "_ordering__")
                .unwrap();
        if (srcPortName != null && !src.ports.contains(srcPortName)) src.getPorts().add(srcPortName);
        if (dstPortName != null && !dst.ports.contains(dstPortName)) dst.getPorts().add(dstPortName);
        order.put(dst.identifier, pos);
        src.properties.put("__" + srcPortName + "_ordering__", VertexProperty.create(order));
        return model.connect(src, dst, srcPortName, dstPortName, ts);
    }

}
