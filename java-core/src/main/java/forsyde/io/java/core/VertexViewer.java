package forsyde.io.java.core;

import java.util.Map;
import java.util.Set;

public interface VertexViewer {
	
    Vertex getViewedVertex();

    default String getIdentifier() {
        return getViewedVertex().getIdentifier();
    };

    default Set<String> getPorts() {
        return getViewedVertex().getPorts();
    };

    default Set<Trait> getTraits() {
        return getViewedVertex().getTraits();
    };

    default Map<String, VertexProperty> getProperties() {
        return getViewedVertex().getProperties();
    }

    /*
    private Optional<Vertex> getNamedPort(ForSyDeSystemGraph model, String portName, String traitName) {
    	return getNamedPort(model, portName, traitName, "BIDIRECTIONAL");
    }
    
    default Optional<Vertex> getNamedPort(ForSyDeSystemGraph model, String portName, String traitName, String direction) {
    	Vertex v = getViewedVertex();
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
    
    default Set<Vertex> getMultipleNamedPort(ForSyDeSystemGraph model, String portName, String traitName) {
    	return getMultipleNamedPort(model, portName, traitName, "BIDIRECTIONAL");
    }
    
    default Set<Vertex> getMultipleNamedPort(ForSyDeSystemGraph model, String portName, String traitName, String direction) {
    	Vertex v = getViewedVertex();
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
    
    default List<Vertex> getOrderedMultipleNamedPort(ForSyDeSystemGraph model, String portName, String traitName) {
    	return getOrderedMultipleNamedPort(model, portName, traitName, "BIDIRECTIONAL");
    }
    
    default List<Vertex> getOrderedMultipleNamedPort(ForSyDeSystemGraph model, String portName, String traitName, String direction) {
    	Vertex v = getViewedVertex();
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
    
    private Trait traitFromString(String traitName) {
    	try {
    		return VertexTrait.valueOf(traitName);
    	} catch (IllegalArgumentException e) {
			return new OpaqueTrait(traitName);
		}
    }
    */
//	default<T> T getNamedProperty(String propName, Class<T> propType) {
//    	return propType.cast(getViewedVertex().getProperties().get(propName).unwrap()); 
//    }
//    
//    default boolean setNamedProperty(String propName, Object propValue) {
//    	return getViewedVertex().putProperty(propName, propValue);
//    }

}
