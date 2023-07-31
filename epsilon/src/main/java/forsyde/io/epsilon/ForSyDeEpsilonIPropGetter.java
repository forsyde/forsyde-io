package forsyde.io.epsilon;

import java.util.Map;

import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;

public class ForSyDeEpsilonIPropGetter implements IPropertyGetter {
	
	protected IEolContext context;
	protected ModuleElement ast;

	@Override
	public boolean hasProperty(Object object, String property) {
		if (object instanceof Vertex) {
			switch (property) {
			case "id": return true;
			case "ports": return true;
			case "properties": return true;
			case "traits": return true;
			default:
				Vertex v = (Vertex) object;
				return v.properties.containsKey(property);
			}
		} else if (object instanceof Edge) {
			switch (property) {
			case "source": return true;
			case "target": return true;
			case "sourceport": return true;
			case "targetport": return true;
			case "traits": return true;
			}
		} else if (object instanceof ForSyDeModel) {
			switch (property) {
			case "edges": return true;
			case "vertexes": return true;
			}
		}
		return false;
	}
	
	public boolean isPropertySet(Object object, String property) {
		if (object instanceof Vertex) {
			Vertex v = (Vertex) object;
			switch (property) {
			case "id": return v.identifier != null && !v.identifier.isBlank();
			case "ports": return true;
			case "properties": return true;
			case "traits": return true;
			default:
				return v.properties.containsKey(property);
			}
		} else if (object instanceof Edge) {
			Edge e = (Edge) object;
			switch (property) {
			case "source": return e.source != null;
			case "target": return e.target != null;
			case "sourceport": return e.sourcePort.isPresent();
			case "targetport": return e.targetPort.isPresent();
			case "traits": return true;
			}
		} else if (object instanceof ForSyDeModel) {
			switch (property) {
			case "edges": return true;
			case "vertexes": return true;
			}
		}
		return false;
	}

	@Override
	public Object invoke(Object object, String property) throws EolRuntimeException {
		if (object instanceof Vertex) {
			Vertex v = (Vertex) object;
			switch (property) {
			case "id": return v.identifier;
			case "ports": return v.ports;
			case "properties": return v.properties;
			case "traits": return v.vertexTraits;
			default:
				return v.properties.get(property);
			}
		} else if (object instanceof Edge) {
			Edge e = (Edge) object;
			switch (property) {
			case "source": return e.source != null;
			case "target": return e.target != null;
			case "sourceport": return e.sourcePort.isPresent();
			case "targetport": return e.targetPort.isPresent();
			case "traits": return true;
			}
		} else if (object instanceof ForSyDeModel) {
			switch (property) {
			case "edges": return true;
			case "vertexes": return true;
			}
		} else if (object instanceof Map<?, ?>) {
			Map<String, Object> mapping = (Map<String, Object>) object;
			return mapping.get(property);
		}
		throw new EolRuntimeException("Element " + object.toString() + " does not contain property " + property);
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
