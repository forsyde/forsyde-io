/**
 */
package forsyde.io.eclipse.systemgraph;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see forsyde.io.eclipse.systemgraph.SystemGraphFactory
 * @model kind="package"
 * @generated
 */
public interface SystemGraphPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "systemgraph";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "systemgraph";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "systemgraph";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SystemGraphPackage eINSTANCE = forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl.init();

	/**
	 * The meta object id for the '{@link forsyde.io.eclipse.systemgraph.VertexProperty <em>Vertex Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see forsyde.io.eclipse.systemgraph.VertexProperty
	 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getVertexProperty()
	 * @generated
	 */
	int VERTEX_PROPERTY = 0;

	/**
	 * The number of structural features of the '<em>Vertex Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_PROPERTY_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Vertex Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_PROPERTY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link forsyde.io.eclipse.systemgraph.impl.StringVertexPropertyImpl <em>String Vertex Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see forsyde.io.eclipse.systemgraph.impl.StringVertexPropertyImpl
	 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getStringVertexProperty()
	 * @generated
	 */
	int STRING_VERTEX_PROPERTY = 1;

	/**
	 * The feature id for the '<em><b>String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VERTEX_PROPERTY__STRING = VERTEX_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Vertex Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VERTEX_PROPERTY_FEATURE_COUNT = VERTEX_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>String Vertex Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VERTEX_PROPERTY_OPERATION_COUNT = VERTEX_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link forsyde.io.eclipse.systemgraph.impl.IntVertexPropertyImpl <em>Int Vertex Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see forsyde.io.eclipse.systemgraph.impl.IntVertexPropertyImpl
	 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getIntVertexProperty()
	 * @generated
	 */
	int INT_VERTEX_PROPERTY = 2;

	/**
	 * The feature id for the '<em><b>Int Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_VERTEX_PROPERTY__INT_VALUE = VERTEX_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Int Vertex Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_VERTEX_PROPERTY_FEATURE_COUNT = VERTEX_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Int Vertex Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_VERTEX_PROPERTY_OPERATION_COUNT = VERTEX_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link forsyde.io.eclipse.systemgraph.impl.BooleanVertexPropertyImpl <em>Boolean Vertex Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see forsyde.io.eclipse.systemgraph.impl.BooleanVertexPropertyImpl
	 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getBooleanVertexProperty()
	 * @generated
	 */
	int BOOLEAN_VERTEX_PROPERTY = 3;

	/**
	 * The feature id for the '<em><b>Boolean Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VERTEX_PROPERTY__BOOLEAN_VALUE = VERTEX_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Vertex Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VERTEX_PROPERTY_FEATURE_COUNT = VERTEX_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Boolean Vertex Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VERTEX_PROPERTY_OPERATION_COUNT = VERTEX_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link forsyde.io.eclipse.systemgraph.impl.FloatVertexPropertyImpl <em>Float Vertex Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see forsyde.io.eclipse.systemgraph.impl.FloatVertexPropertyImpl
	 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getFloatVertexProperty()
	 * @generated
	 */
	int FLOAT_VERTEX_PROPERTY = 4;

	/**
	 * The feature id for the '<em><b>Float Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_VERTEX_PROPERTY__FLOAT_VALUE = VERTEX_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Float Vertex Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_VERTEX_PROPERTY_FEATURE_COUNT = VERTEX_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Float Vertex Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_VERTEX_PROPERTY_OPERATION_COUNT = VERTEX_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link forsyde.io.eclipse.systemgraph.impl.DoubleVertexPropertyImpl <em>Double Vertex Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see forsyde.io.eclipse.systemgraph.impl.DoubleVertexPropertyImpl
	 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getDoubleVertexProperty()
	 * @generated
	 */
	int DOUBLE_VERTEX_PROPERTY = 5;

	/**
	 * The feature id for the '<em><b>Double Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_VERTEX_PROPERTY__DOUBLE_VALUE = VERTEX_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Double Vertex Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_VERTEX_PROPERTY_FEATURE_COUNT = VERTEX_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Double Vertex Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_VERTEX_PROPERTY_OPERATION_COUNT = VERTEX_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link forsyde.io.eclipse.systemgraph.impl.LongVertexPropertyImpl <em>Long Vertex Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see forsyde.io.eclipse.systemgraph.impl.LongVertexPropertyImpl
	 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getLongVertexProperty()
	 * @generated
	 */
	int LONG_VERTEX_PROPERTY = 6;

	/**
	 * The feature id for the '<em><b>Long Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_VERTEX_PROPERTY__LONG_VALUE = VERTEX_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Long Vertex Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_VERTEX_PROPERTY_FEATURE_COUNT = VERTEX_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Long Vertex Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_VERTEX_PROPERTY_OPERATION_COUNT = VERTEX_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link forsyde.io.eclipse.systemgraph.impl.ArrayVertexPropertyImpl <em>Array Vertex Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see forsyde.io.eclipse.systemgraph.impl.ArrayVertexPropertyImpl
	 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getArrayVertexProperty()
	 * @generated
	 */
	int ARRAY_VERTEX_PROPERTY = 7;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_VERTEX_PROPERTY__VALUES = VERTEX_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Array Vertex Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_VERTEX_PROPERTY_FEATURE_COUNT = VERTEX_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Array Vertex Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_VERTEX_PROPERTY_OPERATION_COUNT = VERTEX_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link forsyde.io.eclipse.systemgraph.impl.IntMapVertexPropertyImpl <em>Int Map Vertex Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see forsyde.io.eclipse.systemgraph.impl.IntMapVertexPropertyImpl
	 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getIntMapVertexProperty()
	 * @generated
	 */
	int INT_MAP_VERTEX_PROPERTY = 8;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_MAP_VERTEX_PROPERTY__VALUES = VERTEX_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Indexes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_MAP_VERTEX_PROPERTY__INDEXES = VERTEX_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Int Map Vertex Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_MAP_VERTEX_PROPERTY_FEATURE_COUNT = VERTEX_PROPERTY_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Int Map Vertex Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INT_MAP_VERTEX_PROPERTY_OPERATION_COUNT = VERTEX_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link forsyde.io.eclipse.systemgraph.impl.StringMapVertexPropertyImpl <em>String Map Vertex Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see forsyde.io.eclipse.systemgraph.impl.StringMapVertexPropertyImpl
	 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getStringMapVertexProperty()
	 * @generated
	 */
	int STRING_MAP_VERTEX_PROPERTY = 9;

	/**
	 * The feature id for the '<em><b>Int Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_MAP_VERTEX_PROPERTY__INT_VALUE = INT_VERTEX_PROPERTY__INT_VALUE;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_MAP_VERTEX_PROPERTY__VALUES = INT_VERTEX_PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Indexes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_MAP_VERTEX_PROPERTY__INDEXES = INT_VERTEX_PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>String Map Vertex Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_MAP_VERTEX_PROPERTY_FEATURE_COUNT = INT_VERTEX_PROPERTY_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>String Map Vertex Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_MAP_VERTEX_PROPERTY_OPERATION_COUNT = INT_VERTEX_PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link forsyde.io.eclipse.systemgraph.impl.VertexImpl <em>Vertex</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see forsyde.io.eclipse.systemgraph.impl.VertexImpl
	 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getVertex()
	 * @generated
	 */
	int VERTEX = 10;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__IDENTIFIER = 0;

	/**
	 * The feature id for the '<em><b>Traits</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__TRAITS = 1;

	/**
	 * The feature id for the '<em><b>Ports</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__PORTS = 2;

	/**
	 * The feature id for the '<em><b>Properties Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__PROPERTIES_VALUES = 3;

	/**
	 * The feature id for the '<em><b>Properties Names</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX__PROPERTIES_NAMES = 4;

	/**
	 * The number of structural features of the '<em>Vertex</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Vertex</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERTEX_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link forsyde.io.eclipse.systemgraph.impl.EdgeImpl <em>Edge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see forsyde.io.eclipse.systemgraph.impl.EdgeImpl
	 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getEdge()
	 * @generated
	 */
	int EDGE = 11;

	/**
	 * The feature id for the '<em><b>Sourceport</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__SOURCEPORT = 0;

	/**
	 * The feature id for the '<em><b>Targetport</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__TARGETPORT = 1;

	/**
	 * The feature id for the '<em><b>Traits</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__TRAITS = 2;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__SOURCE = 3;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__TARGET = 4;

	/**
	 * The number of structural features of the '<em>Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link forsyde.io.eclipse.systemgraph.impl.ForSyDeSystemGraphImpl <em>For Sy De System Graph</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see forsyde.io.eclipse.systemgraph.impl.ForSyDeSystemGraphImpl
	 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getForSyDeSystemGraph()
	 * @generated
	 */
	int FOR_SY_DE_SYSTEM_GRAPH = 12;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_SY_DE_SYSTEM_GRAPH__NODES = 0;

	/**
	 * The feature id for the '<em><b>Edges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_SY_DE_SYSTEM_GRAPH__EDGES = 1;

	/**
	 * The number of structural features of the '<em>For Sy De System Graph</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_SY_DE_SYSTEM_GRAPH_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>For Sy De System Graph</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_SY_DE_SYSTEM_GRAPH_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link forsyde.io.eclipse.systemgraph.VertexProperty <em>Vertex Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vertex Property</em>'.
	 * @see forsyde.io.eclipse.systemgraph.VertexProperty
	 * @generated
	 */
	EClass getVertexProperty();

	/**
	 * Returns the meta object for class '{@link forsyde.io.eclipse.systemgraph.StringVertexProperty <em>String Vertex Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Vertex Property</em>'.
	 * @see forsyde.io.eclipse.systemgraph.StringVertexProperty
	 * @generated
	 */
	EClass getStringVertexProperty();

	/**
	 * Returns the meta object for the attribute '{@link forsyde.io.eclipse.systemgraph.StringVertexProperty#getString <em>String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String</em>'.
	 * @see forsyde.io.eclipse.systemgraph.StringVertexProperty#getString()
	 * @see #getStringVertexProperty()
	 * @generated
	 */
	EAttribute getStringVertexProperty_String();

	/**
	 * Returns the meta object for class '{@link forsyde.io.eclipse.systemgraph.IntVertexProperty <em>Int Vertex Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Int Vertex Property</em>'.
	 * @see forsyde.io.eclipse.systemgraph.IntVertexProperty
	 * @generated
	 */
	EClass getIntVertexProperty();

	/**
	 * Returns the meta object for the attribute '{@link forsyde.io.eclipse.systemgraph.IntVertexProperty#getIntValue <em>Int Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Int Value</em>'.
	 * @see forsyde.io.eclipse.systemgraph.IntVertexProperty#getIntValue()
	 * @see #getIntVertexProperty()
	 * @generated
	 */
	EAttribute getIntVertexProperty_IntValue();

	/**
	 * Returns the meta object for class '{@link forsyde.io.eclipse.systemgraph.BooleanVertexProperty <em>Boolean Vertex Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Vertex Property</em>'.
	 * @see forsyde.io.eclipse.systemgraph.BooleanVertexProperty
	 * @generated
	 */
	EClass getBooleanVertexProperty();

	/**
	 * Returns the meta object for the attribute '{@link forsyde.io.eclipse.systemgraph.BooleanVertexProperty#isBooleanValue <em>Boolean Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Boolean Value</em>'.
	 * @see forsyde.io.eclipse.systemgraph.BooleanVertexProperty#isBooleanValue()
	 * @see #getBooleanVertexProperty()
	 * @generated
	 */
	EAttribute getBooleanVertexProperty_BooleanValue();

	/**
	 * Returns the meta object for class '{@link forsyde.io.eclipse.systemgraph.FloatVertexProperty <em>Float Vertex Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Float Vertex Property</em>'.
	 * @see forsyde.io.eclipse.systemgraph.FloatVertexProperty
	 * @generated
	 */
	EClass getFloatVertexProperty();

	/**
	 * Returns the meta object for the attribute '{@link forsyde.io.eclipse.systemgraph.FloatVertexProperty#getFloatValue <em>Float Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Float Value</em>'.
	 * @see forsyde.io.eclipse.systemgraph.FloatVertexProperty#getFloatValue()
	 * @see #getFloatVertexProperty()
	 * @generated
	 */
	EAttribute getFloatVertexProperty_FloatValue();

	/**
	 * Returns the meta object for class '{@link forsyde.io.eclipse.systemgraph.DoubleVertexProperty <em>Double Vertex Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double Vertex Property</em>'.
	 * @see forsyde.io.eclipse.systemgraph.DoubleVertexProperty
	 * @generated
	 */
	EClass getDoubleVertexProperty();

	/**
	 * Returns the meta object for the attribute '{@link forsyde.io.eclipse.systemgraph.DoubleVertexProperty#getDoubleValue <em>Double Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Double Value</em>'.
	 * @see forsyde.io.eclipse.systemgraph.DoubleVertexProperty#getDoubleValue()
	 * @see #getDoubleVertexProperty()
	 * @generated
	 */
	EAttribute getDoubleVertexProperty_DoubleValue();

	/**
	 * Returns the meta object for class '{@link forsyde.io.eclipse.systemgraph.LongVertexProperty <em>Long Vertex Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Long Vertex Property</em>'.
	 * @see forsyde.io.eclipse.systemgraph.LongVertexProperty
	 * @generated
	 */
	EClass getLongVertexProperty();

	/**
	 * Returns the meta object for the attribute '{@link forsyde.io.eclipse.systemgraph.LongVertexProperty#getLongValue <em>Long Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Long Value</em>'.
	 * @see forsyde.io.eclipse.systemgraph.LongVertexProperty#getLongValue()
	 * @see #getLongVertexProperty()
	 * @generated
	 */
	EAttribute getLongVertexProperty_LongValue();

	/**
	 * Returns the meta object for class '{@link forsyde.io.eclipse.systemgraph.ArrayVertexProperty <em>Array Vertex Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array Vertex Property</em>'.
	 * @see forsyde.io.eclipse.systemgraph.ArrayVertexProperty
	 * @generated
	 */
	EClass getArrayVertexProperty();

	/**
	 * Returns the meta object for the containment reference list '{@link forsyde.io.eclipse.systemgraph.ArrayVertexProperty#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Values</em>'.
	 * @see forsyde.io.eclipse.systemgraph.ArrayVertexProperty#getValues()
	 * @see #getArrayVertexProperty()
	 * @generated
	 */
	EReference getArrayVertexProperty_Values();

	/**
	 * Returns the meta object for class '{@link forsyde.io.eclipse.systemgraph.IntMapVertexProperty <em>Int Map Vertex Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Int Map Vertex Property</em>'.
	 * @see forsyde.io.eclipse.systemgraph.IntMapVertexProperty
	 * @generated
	 */
	EClass getIntMapVertexProperty();

	/**
	 * Returns the meta object for the containment reference list '{@link forsyde.io.eclipse.systemgraph.IntMapVertexProperty#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Values</em>'.
	 * @see forsyde.io.eclipse.systemgraph.IntMapVertexProperty#getValues()
	 * @see #getIntMapVertexProperty()
	 * @generated
	 */
	EReference getIntMapVertexProperty_Values();

	/**
	 * Returns the meta object for the attribute list '{@link forsyde.io.eclipse.systemgraph.IntMapVertexProperty#getIndexes <em>Indexes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Indexes</em>'.
	 * @see forsyde.io.eclipse.systemgraph.IntMapVertexProperty#getIndexes()
	 * @see #getIntMapVertexProperty()
	 * @generated
	 */
	EAttribute getIntMapVertexProperty_Indexes();

	/**
	 * Returns the meta object for class '{@link forsyde.io.eclipse.systemgraph.StringMapVertexProperty <em>String Map Vertex Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Map Vertex Property</em>'.
	 * @see forsyde.io.eclipse.systemgraph.StringMapVertexProperty
	 * @generated
	 */
	EClass getStringMapVertexProperty();

	/**
	 * Returns the meta object for the containment reference list '{@link forsyde.io.eclipse.systemgraph.StringMapVertexProperty#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Values</em>'.
	 * @see forsyde.io.eclipse.systemgraph.StringMapVertexProperty#getValues()
	 * @see #getStringMapVertexProperty()
	 * @generated
	 */
	EReference getStringMapVertexProperty_Values();

	/**
	 * Returns the meta object for the attribute list '{@link forsyde.io.eclipse.systemgraph.StringMapVertexProperty#getIndexes <em>Indexes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Indexes</em>'.
	 * @see forsyde.io.eclipse.systemgraph.StringMapVertexProperty#getIndexes()
	 * @see #getStringMapVertexProperty()
	 * @generated
	 */
	EAttribute getStringMapVertexProperty_Indexes();

	/**
	 * Returns the meta object for class '{@link forsyde.io.eclipse.systemgraph.Vertex <em>Vertex</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vertex</em>'.
	 * @see forsyde.io.eclipse.systemgraph.Vertex
	 * @generated
	 */
	EClass getVertex();

	/**
	 * Returns the meta object for the attribute '{@link forsyde.io.eclipse.systemgraph.Vertex#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see forsyde.io.eclipse.systemgraph.Vertex#getIdentifier()
	 * @see #getVertex()
	 * @generated
	 */
	EAttribute getVertex_Identifier();

	/**
	 * Returns the meta object for the attribute list '{@link forsyde.io.eclipse.systemgraph.Vertex#getTraits <em>Traits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Traits</em>'.
	 * @see forsyde.io.eclipse.systemgraph.Vertex#getTraits()
	 * @see #getVertex()
	 * @generated
	 */
	EAttribute getVertex_Traits();

	/**
	 * Returns the meta object for the attribute list '{@link forsyde.io.eclipse.systemgraph.Vertex#getPorts <em>Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Ports</em>'.
	 * @see forsyde.io.eclipse.systemgraph.Vertex#getPorts()
	 * @see #getVertex()
	 * @generated
	 */
	EAttribute getVertex_Ports();

	/**
	 * Returns the meta object for the containment reference list '{@link forsyde.io.eclipse.systemgraph.Vertex#getPropertiesValues <em>Properties Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Properties Values</em>'.
	 * @see forsyde.io.eclipse.systemgraph.Vertex#getPropertiesValues()
	 * @see #getVertex()
	 * @generated
	 */
	EReference getVertex_PropertiesValues();

	/**
	 * Returns the meta object for the attribute list '{@link forsyde.io.eclipse.systemgraph.Vertex#getPropertiesNames <em>Properties Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Properties Names</em>'.
	 * @see forsyde.io.eclipse.systemgraph.Vertex#getPropertiesNames()
	 * @see #getVertex()
	 * @generated
	 */
	EAttribute getVertex_PropertiesNames();

	/**
	 * Returns the meta object for class '{@link forsyde.io.eclipse.systemgraph.Edge <em>Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Edge</em>'.
	 * @see forsyde.io.eclipse.systemgraph.Edge
	 * @generated
	 */
	EClass getEdge();

	/**
	 * Returns the meta object for the attribute '{@link forsyde.io.eclipse.systemgraph.Edge#getSourceport <em>Sourceport</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sourceport</em>'.
	 * @see forsyde.io.eclipse.systemgraph.Edge#getSourceport()
	 * @see #getEdge()
	 * @generated
	 */
	EAttribute getEdge_Sourceport();

	/**
	 * Returns the meta object for the attribute '{@link forsyde.io.eclipse.systemgraph.Edge#getTargetport <em>Targetport</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Targetport</em>'.
	 * @see forsyde.io.eclipse.systemgraph.Edge#getTargetport()
	 * @see #getEdge()
	 * @generated
	 */
	EAttribute getEdge_Targetport();

	/**
	 * Returns the meta object for the attribute list '{@link forsyde.io.eclipse.systemgraph.Edge#getTraits <em>Traits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Traits</em>'.
	 * @see forsyde.io.eclipse.systemgraph.Edge#getTraits()
	 * @see #getEdge()
	 * @generated
	 */
	EAttribute getEdge_Traits();

	/**
	 * Returns the meta object for the reference '{@link forsyde.io.eclipse.systemgraph.Edge#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see forsyde.io.eclipse.systemgraph.Edge#getSource()
	 * @see #getEdge()
	 * @generated
	 */
	EReference getEdge_Source();

	/**
	 * Returns the meta object for the reference '{@link forsyde.io.eclipse.systemgraph.Edge#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see forsyde.io.eclipse.systemgraph.Edge#getTarget()
	 * @see #getEdge()
	 * @generated
	 */
	EReference getEdge_Target();

	/**
	 * Returns the meta object for class '{@link forsyde.io.eclipse.systemgraph.ForSyDeSystemGraph <em>For Sy De System Graph</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>For Sy De System Graph</em>'.
	 * @see forsyde.io.eclipse.systemgraph.ForSyDeSystemGraph
	 * @generated
	 */
	EClass getForSyDeSystemGraph();

	/**
	 * Returns the meta object for the containment reference list '{@link forsyde.io.eclipse.systemgraph.ForSyDeSystemGraph#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see forsyde.io.eclipse.systemgraph.ForSyDeSystemGraph#getNodes()
	 * @see #getForSyDeSystemGraph()
	 * @generated
	 */
	EReference getForSyDeSystemGraph_Nodes();

	/**
	 * Returns the meta object for the containment reference list '{@link forsyde.io.eclipse.systemgraph.ForSyDeSystemGraph#getEdges <em>Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Edges</em>'.
	 * @see forsyde.io.eclipse.systemgraph.ForSyDeSystemGraph#getEdges()
	 * @see #getForSyDeSystemGraph()
	 * @generated
	 */
	EReference getForSyDeSystemGraph_Edges();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SystemGraphFactory getSystemGraphFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link forsyde.io.eclipse.systemgraph.VertexProperty <em>Vertex Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see forsyde.io.eclipse.systemgraph.VertexProperty
		 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getVertexProperty()
		 * @generated
		 */
		EClass VERTEX_PROPERTY = eINSTANCE.getVertexProperty();

		/**
		 * The meta object literal for the '{@link forsyde.io.eclipse.systemgraph.impl.StringVertexPropertyImpl <em>String Vertex Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see forsyde.io.eclipse.systemgraph.impl.StringVertexPropertyImpl
		 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getStringVertexProperty()
		 * @generated
		 */
		EClass STRING_VERTEX_PROPERTY = eINSTANCE.getStringVertexProperty();

		/**
		 * The meta object literal for the '<em><b>String</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_VERTEX_PROPERTY__STRING = eINSTANCE.getStringVertexProperty_String();

		/**
		 * The meta object literal for the '{@link forsyde.io.eclipse.systemgraph.impl.IntVertexPropertyImpl <em>Int Vertex Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see forsyde.io.eclipse.systemgraph.impl.IntVertexPropertyImpl
		 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getIntVertexProperty()
		 * @generated
		 */
		EClass INT_VERTEX_PROPERTY = eINSTANCE.getIntVertexProperty();

		/**
		 * The meta object literal for the '<em><b>Int Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INT_VERTEX_PROPERTY__INT_VALUE = eINSTANCE.getIntVertexProperty_IntValue();

		/**
		 * The meta object literal for the '{@link forsyde.io.eclipse.systemgraph.impl.BooleanVertexPropertyImpl <em>Boolean Vertex Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see forsyde.io.eclipse.systemgraph.impl.BooleanVertexPropertyImpl
		 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getBooleanVertexProperty()
		 * @generated
		 */
		EClass BOOLEAN_VERTEX_PROPERTY = eINSTANCE.getBooleanVertexProperty();

		/**
		 * The meta object literal for the '<em><b>Boolean Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_VERTEX_PROPERTY__BOOLEAN_VALUE = eINSTANCE.getBooleanVertexProperty_BooleanValue();

		/**
		 * The meta object literal for the '{@link forsyde.io.eclipse.systemgraph.impl.FloatVertexPropertyImpl <em>Float Vertex Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see forsyde.io.eclipse.systemgraph.impl.FloatVertexPropertyImpl
		 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getFloatVertexProperty()
		 * @generated
		 */
		EClass FLOAT_VERTEX_PROPERTY = eINSTANCE.getFloatVertexProperty();

		/**
		 * The meta object literal for the '<em><b>Float Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOAT_VERTEX_PROPERTY__FLOAT_VALUE = eINSTANCE.getFloatVertexProperty_FloatValue();

		/**
		 * The meta object literal for the '{@link forsyde.io.eclipse.systemgraph.impl.DoubleVertexPropertyImpl <em>Double Vertex Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see forsyde.io.eclipse.systemgraph.impl.DoubleVertexPropertyImpl
		 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getDoubleVertexProperty()
		 * @generated
		 */
		EClass DOUBLE_VERTEX_PROPERTY = eINSTANCE.getDoubleVertexProperty();

		/**
		 * The meta object literal for the '<em><b>Double Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOUBLE_VERTEX_PROPERTY__DOUBLE_VALUE = eINSTANCE.getDoubleVertexProperty_DoubleValue();

		/**
		 * The meta object literal for the '{@link forsyde.io.eclipse.systemgraph.impl.LongVertexPropertyImpl <em>Long Vertex Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see forsyde.io.eclipse.systemgraph.impl.LongVertexPropertyImpl
		 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getLongVertexProperty()
		 * @generated
		 */
		EClass LONG_VERTEX_PROPERTY = eINSTANCE.getLongVertexProperty();

		/**
		 * The meta object literal for the '<em><b>Long Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LONG_VERTEX_PROPERTY__LONG_VALUE = eINSTANCE.getLongVertexProperty_LongValue();

		/**
		 * The meta object literal for the '{@link forsyde.io.eclipse.systemgraph.impl.ArrayVertexPropertyImpl <em>Array Vertex Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see forsyde.io.eclipse.systemgraph.impl.ArrayVertexPropertyImpl
		 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getArrayVertexProperty()
		 * @generated
		 */
		EClass ARRAY_VERTEX_PROPERTY = eINSTANCE.getArrayVertexProperty();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_VERTEX_PROPERTY__VALUES = eINSTANCE.getArrayVertexProperty_Values();

		/**
		 * The meta object literal for the '{@link forsyde.io.eclipse.systemgraph.impl.IntMapVertexPropertyImpl <em>Int Map Vertex Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see forsyde.io.eclipse.systemgraph.impl.IntMapVertexPropertyImpl
		 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getIntMapVertexProperty()
		 * @generated
		 */
		EClass INT_MAP_VERTEX_PROPERTY = eINSTANCE.getIntMapVertexProperty();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INT_MAP_VERTEX_PROPERTY__VALUES = eINSTANCE.getIntMapVertexProperty_Values();

		/**
		 * The meta object literal for the '<em><b>Indexes</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INT_MAP_VERTEX_PROPERTY__INDEXES = eINSTANCE.getIntMapVertexProperty_Indexes();

		/**
		 * The meta object literal for the '{@link forsyde.io.eclipse.systemgraph.impl.StringMapVertexPropertyImpl <em>String Map Vertex Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see forsyde.io.eclipse.systemgraph.impl.StringMapVertexPropertyImpl
		 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getStringMapVertexProperty()
		 * @generated
		 */
		EClass STRING_MAP_VERTEX_PROPERTY = eINSTANCE.getStringMapVertexProperty();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRING_MAP_VERTEX_PROPERTY__VALUES = eINSTANCE.getStringMapVertexProperty_Values();

		/**
		 * The meta object literal for the '<em><b>Indexes</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_MAP_VERTEX_PROPERTY__INDEXES = eINSTANCE.getStringMapVertexProperty_Indexes();

		/**
		 * The meta object literal for the '{@link forsyde.io.eclipse.systemgraph.impl.VertexImpl <em>Vertex</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see forsyde.io.eclipse.systemgraph.impl.VertexImpl
		 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getVertex()
		 * @generated
		 */
		EClass VERTEX = eINSTANCE.getVertex();

		/**
		 * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERTEX__IDENTIFIER = eINSTANCE.getVertex_Identifier();

		/**
		 * The meta object literal for the '<em><b>Traits</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERTEX__TRAITS = eINSTANCE.getVertex_Traits();

		/**
		 * The meta object literal for the '<em><b>Ports</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERTEX__PORTS = eINSTANCE.getVertex_Ports();

		/**
		 * The meta object literal for the '<em><b>Properties Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VERTEX__PROPERTIES_VALUES = eINSTANCE.getVertex_PropertiesValues();

		/**
		 * The meta object literal for the '<em><b>Properties Names</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VERTEX__PROPERTIES_NAMES = eINSTANCE.getVertex_PropertiesNames();

		/**
		 * The meta object literal for the '{@link forsyde.io.eclipse.systemgraph.impl.EdgeImpl <em>Edge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see forsyde.io.eclipse.systemgraph.impl.EdgeImpl
		 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getEdge()
		 * @generated
		 */
		EClass EDGE = eINSTANCE.getEdge();

		/**
		 * The meta object literal for the '<em><b>Sourceport</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EDGE__SOURCEPORT = eINSTANCE.getEdge_Sourceport();

		/**
		 * The meta object literal for the '<em><b>Targetport</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EDGE__TARGETPORT = eINSTANCE.getEdge_Targetport();

		/**
		 * The meta object literal for the '<em><b>Traits</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EDGE__TRAITS = eINSTANCE.getEdge_Traits();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDGE__SOURCE = eINSTANCE.getEdge_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDGE__TARGET = eINSTANCE.getEdge_Target();

		/**
		 * The meta object literal for the '{@link forsyde.io.eclipse.systemgraph.impl.ForSyDeSystemGraphImpl <em>For Sy De System Graph</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see forsyde.io.eclipse.systemgraph.impl.ForSyDeSystemGraphImpl
		 * @see forsyde.io.eclipse.systemgraph.impl.SystemGraphPackageImpl#getForSyDeSystemGraph()
		 * @generated
		 */
		EClass FOR_SY_DE_SYSTEM_GRAPH = eINSTANCE.getForSyDeSystemGraph();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOR_SY_DE_SYSTEM_GRAPH__NODES = eINSTANCE.getForSyDeSystemGraph_Nodes();

		/**
		 * The meta object literal for the '<em><b>Edges</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOR_SY_DE_SYSTEM_GRAPH__EDGES = eINSTANCE.getForSyDeSystemGraph_Edges();

	}

} //SystemGraphPackage
