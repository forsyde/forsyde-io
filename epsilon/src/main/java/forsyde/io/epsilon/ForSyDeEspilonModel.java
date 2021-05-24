package forsyde.io.epsilon;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.compile.m3.Metamodel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.exceptions.models.EolNotInstantiableModelElementTypeException;
import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;
import org.eclipse.epsilon.eol.execute.introspection.IPropertySetter;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.eclipse.epsilon.eol.models.transactions.IModelTransactionSupport;
import org.jgrapht.Graphs;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.drivers.ForSyDeModelHandler;

public class ForSyDeEspilonModel extends ForSyDeModel implements IModel {
	

	private static final long serialVersionUID = 1L;
	public String modelName = "Model";

	@Override
	public void load(StringProperties properties) throws EolModelLoadingException {
		//TODO what is this?
	}

	@Override
	public void load(StringProperties properties, String basePath) throws EolModelLoadingException {
		try {
			Graphs.addGraph(this, ForSyDeModelHandler.loadModel(basePath));
		} catch (Exception e) {
			throw new EolModelLoadingException(e, this);		
		}
	}

	@Override
	public void load(StringProperties properties, IRelativePathResolver relativePathResolver)
			throws EolModelLoadingException {
		// TODO implement this
	}

	@Override
	public void load() throws EolModelLoadingException {
		//TODO what is this?
	}

	@Override
	public String getName() {
		return modelName;
	}

	@Override
	public void setName(String name) {
		modelName = name;
	}

	@Override
	public List<String> getAliases() {
		return List.of("ForSyDeIO", "ForSyDe");
	}

	@Override
	public Object getEnumerationValue(String enumeration, String label) throws EolEnumerationValueNotFoundException {
		if (enumeration.equals("VertexTrait")) {
			return VertexTrait.valueOf(label);
		} else if (enumeration.equals("EdgeTrait")) {
			return EdgeTrait.valueOf(label);
		} else {
			throw new EolEnumerationValueNotFoundException(enumeration, label, getName());
		}
	}

	@Override
	public Collection<?> allContents() {
		Set<Object> contents = new HashSet<>();
		contents.add(this);
		contents.addAll(vertexSet());
		contents.addAll(edgeSet());
		return contents;
	}

	@Override
	public Collection<?> getAllOfType(String type) throws EolModelElementTypeNotFoundException {
		if (type.equals("ForSyDeModel")) {
			return Set.of(this);
		} else if (type.equals("Vertex")) {
			return vertexSet();
		} else if (type.equals("Edge")) {
			return edgeSet();
		} else if (isVertexTrait(type)) {
			VertexTrait vt = VertexTrait.valueOf(type);
			return vertexSet().stream().filter(v -> v.vertexTraits.contains(vt)).collect(Collectors.toSet());
		} else if (isEdgeTrait(type)) {
			EdgeTrait et = EdgeTrait.valueOf(type);
			return edgeSet().stream().filter(e -> e.edgeTraits.contains(et)).collect(Collectors.toSet());
		} else {
			throw new EolModelElementTypeNotFoundException(getName(), "ForSyDeIOMeta");
		}
	}

	@Override
	public Collection<?> getAllOfKind(String type) throws EolModelElementTypeNotFoundException {
		if (type.equals("ForSyDeModel")) {
			return Set.of(this);
		} else if (type.equals("Vertex")) {
			return vertexSet();
		} else if (type.equals("Edge")) {
			return edgeSet();
		} else if (isVertexTrait(type)) {
			VertexTrait vt = VertexTrait.valueOf(type);
			return vertexSet().stream().filter(
					v -> v.vertexTraits.stream().anyMatch(vtrait -> vtrait.refines(vt))
					).collect(Collectors.toSet());
		} else if (isEdgeTrait(type)) {
			EdgeTrait et = EdgeTrait.valueOf(type);
			return edgeSet().stream().filter(
					e -> e.edgeTraits.stream().anyMatch(etrait -> etrait.refines(et))
					).collect(Collectors.toSet());
		} else {
			throw new EolModelElementTypeNotFoundException(getName(), "ForSyDeIOMeta");
		}
	}

	@Override
	public Object getTypeOf(Object instance) {
		if (instance instanceof Vertex) {
			return Vertex.class;
		} else if (instance instanceof Edge) {
			return Edge.class;
		} else if (instance instanceof ForSyDeModel) {
			return ForSyDeModel.class;
		}
		return null;
	}

	@Override
	public String getTypeNameOf(Object instance) {
		if (instance instanceof Vertex) {
			return "Vertex";
		} else if (instance instanceof Edge) {
			return "Edge";
		} else if (instance instanceof ForSyDeModel) {
			return "ForSyDeModel";
		} else if (instance instanceof VertexTrait) {
			return instance.toString();
		} else if (instance instanceof EdgeTrait) {
			return instance.toString();
		} else {
			return "";
		}
	}

	@Override
	public String getFullyQualifiedTypeNameOf(Object instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createInstance(String type)
			throws EolModelElementTypeNotFoundException, EolNotInstantiableModelElementTypeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createInstance(String type, Collection<Object> parameters)
			throws EolModelElementTypeNotFoundException, EolNotInstantiableModelElementTypeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getElementById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getElementId(Object instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setElementId(Object instance, String newId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteElement(Object instance) throws EolRuntimeException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isOfKind(Object instance, String type) throws EolModelElementTypeNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOfType(Object instance, String type) throws EolModelElementTypeNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean owns(Object instance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean knowsAboutProperty(Object instance, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPropertySet(Object instance, String property) throws EolRuntimeException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInstantiable(String type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isModelElement(Object instance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasType(String type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean store(String location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean store() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public IPropertyGetter getPropertyGetter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPropertySetter getPropertySetter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isStoredOnDisposal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setStoredOnDisposal(boolean storedOnDisposal) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isReadOnLoad() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setReadOnLoad(boolean readOnLoad) {
		// TODO Auto-generated method stub

	}

	@Override
	public IModelTransactionSupport getTransactionSupport() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Metamodel getMetamodel(StringProperties properties, IRelativePathResolver resolver) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected boolean isVertexTrait(String type) {
		return Stream.of(VertexTrait.values()).anyMatch(vt -> vt.getName().equals(type));
	}
	
	protected boolean isEdgeTrait(String type) {
		return Stream.of(EdgeTrait.values()).anyMatch(et -> et.getName().equals(type));
	}

}
