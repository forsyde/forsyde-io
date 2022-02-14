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

    static public Optional<Vertex> getNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, String traitName) {
        final Trait t = VertexTrait.fromName(traitName);
        return getNamedPort(model, v, portName, t, VertexPortDirection.BIDIRECTIONAL);
    }


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

    static public Set<Vertex> getMultipleNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, String traitName) {
        return getMultipleNamedPort(model, v, portName, traitName, "BIDIRECTIONAL");
    }

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

    static public List<Vertex> getOrderedMultipleNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, String traitName) {
        Trait t = VertexTrait.fromName(traitName);
        return getOrderedMultipleNamedPort(model, v, portName, t, VertexPortDirection.BIDIRECTIONAL);
    }

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

}
