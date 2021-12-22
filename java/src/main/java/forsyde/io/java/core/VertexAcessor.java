package forsyde.io.java.core;

import java.util.*;
import java.util.stream.Collectors;

public class VertexAcessor {

    static public Optional<Vertex> getNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, String traitName) {
        return getNamedPort(model, v, portName, traitName, "BIDIRECTIONAL");
    }

    static public Optional<Vertex> getNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, String traitName, String direction) {
        Trait t = traitFromString(traitName);
        if (direction.equalsIgnoreCase("outgoing") || direction.equalsIgnoreCase("bidirectional")) {
            Set<Edge> outEdges = model.outgoingEdgesOf(v);
            Optional<Vertex> dst = outEdges.stream()
                    .filter(e -> e.sourcePort.map(p -> p.equals(portName)).orElse(false))
                    .map(e -> e.target)
                    .filter(vv -> vv.hasTrait(t))
                    .findAny();
            if (dst.isPresent())
                return dst;
        }
        if (direction.equalsIgnoreCase("incoming") || direction.equalsIgnoreCase("bidirectional")) {
            Set<Edge> inEdges = model.incomingEdgesOf(v);
            Optional<Vertex> src = inEdges.stream()
                    .filter(e -> e.targetPort.map(p -> p.equals(portName)).orElse(false))
                    .map(e -> e.source)
                    .filter(vv -> vv.hasTrait(t))
                    .findAny();
            if (src.isPresent())
                return src;
        }
        return Optional.empty();
    }

    static public Set<Vertex> getMultipleNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, String traitName) {
        return getMultipleNamedPort(model, v, portName, traitName, "BIDIRECTIONAL");
    }

    static public Set<Vertex> getMultipleNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, String traitName, String direction) {
        Set<Vertex> vs = new HashSet<>();
        Trait t = traitFromString(traitName);
        if (direction.equalsIgnoreCase("outgoing") || direction.equalsIgnoreCase("bidirectional")) {
            Set<Edge> outEdges = model.outgoingEdgesOf(v);
            vs.addAll(outEdges.stream()
                    .filter(e -> e.sourcePort.map(p -> p.equals(portName)).orElse(false))
                    .map(e -> e.target)
                    .filter(vv -> vv.hasTrait(t))
                    .collect(Collectors.toSet()));
        }
        if (direction.equalsIgnoreCase("incoming") || direction.equalsIgnoreCase("bidirectional")) {
            Set<Edge> inEdges = model.incomingEdgesOf(v);
            vs.addAll(inEdges.stream()
                    .filter(e -> e.targetPort.map(p -> p.equals(portName)).orElse(false))
                    .map(e -> e.source)
                    .filter(vv -> vv.hasTrait(t))
                    .collect(Collectors.toSet()));
        }
        return vs;
    }

    static public List<Vertex> getOrderedMultipleNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, String traitName) {
        return getOrderedMultipleNamedPort(model, v, portName, traitName, "BIDIRECTIONAL");
    }

    static public List<Vertex> getOrderedMultipleNamedPort(ForSyDeSystemGraph model, Vertex v, String portName, String traitName, String direction) {
        Trait t = traitFromString(traitName);
        @SuppressWarnings("unchecked")
        Map<String, Integer> order = (Map<String, Integer>) v.getProperties()
                .get("__" + portName + "_ordering__")
                .unwrap();
        List<Vertex> vs = new ArrayList<>(order.size());
        if (direction.equalsIgnoreCase("outgoing") || direction.equalsIgnoreCase("bidirectional")) {
            Set<Edge> outEdges = model.outgoingEdgesOf(v);
            outEdges.stream()
                    .filter(e -> e.sourcePort.map(p -> p.equals(portName)).orElse(false))
                    .map(e -> e.target)
                    .filter(vv -> vv.hasTrait(t))
                    .forEach(dst -> {
                        vs.set(order.get(dst.identifier), dst);
                    });
        }
        if (direction.equalsIgnoreCase("incoming") || direction.equalsIgnoreCase("bidirectional")) {
            Set<Edge> inEdges = model.incomingEdgesOf(v);
            inEdges.stream()
                    .filter(e -> e.targetPort.map(p -> p.equals(portName)).orElse(false))
                    .map(e -> e.source)
                    .filter(vv -> vv.hasTrait(t))
                    .forEach(dst -> {
                        vs.set(order.get(dst.identifier), dst);
                    });
        }
        return vs;
    }

    static private Trait traitFromString(String traitName) {
        try {
            return VertexTrait.valueOf(traitName);
        } catch (IllegalArgumentException e) {
            return new OpaqueTrait(traitName);
        }
    }
}