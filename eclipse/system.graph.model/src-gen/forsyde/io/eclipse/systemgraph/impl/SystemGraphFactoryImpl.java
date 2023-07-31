/**
 */
package forsyde.io.eclipse.systemgraph.impl;

import forsyde.io.eclipse.systemgraph.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SystemGraphFactoryImpl extends EFactoryImpl implements SystemGraphFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SystemGraphFactory init() {
		try {
			SystemGraphFactory theSystemGraphFactory = (SystemGraphFactory)EPackage.Registry.INSTANCE.getEFactory(SystemGraphPackage.eNS_URI);
			if (theSystemGraphFactory != null) {
				return theSystemGraphFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SystemGraphFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemGraphFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SystemGraphPackage.STRING_VERTEX_PROPERTY: return createStringVertexProperty();
			case SystemGraphPackage.INT_VERTEX_PROPERTY: return createIntVertexProperty();
			case SystemGraphPackage.BOOLEAN_VERTEX_PROPERTY: return createBooleanVertexProperty();
			case SystemGraphPackage.FLOAT_VERTEX_PROPERTY: return createFloatVertexProperty();
			case SystemGraphPackage.DOUBLE_VERTEX_PROPERTY: return createDoubleVertexProperty();
			case SystemGraphPackage.LONG_VERTEX_PROPERTY: return createLongVertexProperty();
			case SystemGraphPackage.ARRAY_VERTEX_PROPERTY: return createArrayVertexProperty();
			case SystemGraphPackage.INT_MAP_VERTEX_PROPERTY: return createIntMapVertexProperty();
			case SystemGraphPackage.STRING_MAP_VERTEX_PROPERTY: return createStringMapVertexProperty();
			case SystemGraphPackage.VERTEX: return createVertex();
			case SystemGraphPackage.EDGE: return createEdge();
			case SystemGraphPackage.FOR_SY_DE_SYSTEM_GRAPH: return createForSyDeSystemGraph();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringVertexProperty createStringVertexProperty() {
		StringVertexPropertyImpl stringVertexProperty = new StringVertexPropertyImpl();
		return stringVertexProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntVertexProperty createIntVertexProperty() {
		IntVertexPropertyImpl intVertexProperty = new IntVertexPropertyImpl();
		return intVertexProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanVertexProperty createBooleanVertexProperty() {
		BooleanVertexPropertyImpl booleanVertexProperty = new BooleanVertexPropertyImpl();
		return booleanVertexProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FloatVertexProperty createFloatVertexProperty() {
		FloatVertexPropertyImpl floatVertexProperty = new FloatVertexPropertyImpl();
		return floatVertexProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DoubleVertexProperty createDoubleVertexProperty() {
		DoubleVertexPropertyImpl doubleVertexProperty = new DoubleVertexPropertyImpl();
		return doubleVertexProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LongVertexProperty createLongVertexProperty() {
		LongVertexPropertyImpl longVertexProperty = new LongVertexPropertyImpl();
		return longVertexProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayVertexProperty createArrayVertexProperty() {
		ArrayVertexPropertyImpl arrayVertexProperty = new ArrayVertexPropertyImpl();
		return arrayVertexProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntMapVertexProperty createIntMapVertexProperty() {
		IntMapVertexPropertyImpl intMapVertexProperty = new IntMapVertexPropertyImpl();
		return intMapVertexProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringMapVertexProperty createStringMapVertexProperty() {
		StringMapVertexPropertyImpl stringMapVertexProperty = new StringMapVertexPropertyImpl();
		return stringMapVertexProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Vertex createVertex() {
		VertexImpl vertex = new VertexImpl();
		return vertex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Edge createEdge() {
		EdgeImpl edge = new EdgeImpl();
		return edge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ForSyDeSystemGraph createForSyDeSystemGraph() {
		ForSyDeSystemGraphImpl forSyDeSystemGraph = new ForSyDeSystemGraphImpl();
		return forSyDeSystemGraph;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemGraphPackage getSystemGraphPackage() {
		return (SystemGraphPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SystemGraphPackage getPackage() {
		return SystemGraphPackage.eINSTANCE;
	}

} //SystemGraphFactoryImpl
