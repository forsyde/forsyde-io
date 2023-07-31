package forsyde.io.epsilon;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.introspection.IPropertySetter;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Port;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;

public class ForSyDeEpsilonIPropSetter implements IPropertySetter {
	
	protected IEolContext context;
	protected ModuleElement ast;
	private Object object;
	private String property;

	@Override
	public void invoke(Object value) throws EolRuntimeException {
		if (object instanceof Vertex) {
			Vertex v = (Vertex) object;
			switch (property) {
			case "id": v.identifier = value instanceof String ? (String) value : v.identifier;
			case "ports": v.ports = value instanceof List<?> ? (List<Port>) value : v.ports;
			case "properties": v.properties = value instanceof Map<?, ?> ? (Map<String, Object>) value : v.properties;
			case "traits": v.vertexTraits = value instanceof Set<?> ? (Set<VertexTrait>) value : v.vertexTraits;
			default:
				v.properties.put(property, value);
			}
		} else if (object instanceof Edge) {
			Edge e = (Edge) object;
			switch (property) {
			case "source": e.source = value instanceof Vertex ? (Vertex) value : e.source;
			case "target": e.target = value instanceof Vertex ? (Vertex) value : e.target;
			case "sourceport": e.sourcePort = value instanceof Port ? Optional.of((Port) value) : e.sourcePort;
			case "targetport": e.targetPort = value instanceof Port ? Optional.of((Port) value) : e.targetPort;
			case "traits": e.edgeTraits = value instanceof Set<?> ? (Set<EdgeTrait>) value : e.edgeTraits;
			}
		} 
//		else if (object instanceof ForSyDeModel) {
//			switch (property) {
//			case "edges": return true;
//			case "vertexes": return true;
//			}
//		} 
		else if (object instanceof Map<?, ?>) {
			Map<String, Object> mapping = (Map<String, Object>) object;
			mapping.put(property, value);
		}
		throw new EolRuntimeException("Element " + object.toString() + " does not contain property " + property);
	}

	@Override
	public void setProperty(String property) {
		this.property = property;
	}

	@Override
	public String getProperty() {
		return property;
	}

	@Override
	public void setObject(Object object) {
		this.object = object;	
	}

	@Override
	public Object getObject() {
		return object;
	}

	@Override
	public ModuleElement getAst() {
		return ast;
	}

	@Override
	public void setAst(ModuleElement ast) {
		this.ast = ast;
	}

	@Override
	public void setContext(IEolContext context) {
		this.context = context;
	}

	@Override
	public IEolContext getContext() {
		return context;
	}

}
