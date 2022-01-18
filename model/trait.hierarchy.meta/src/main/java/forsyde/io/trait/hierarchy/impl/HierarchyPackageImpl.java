/**
 */
package forsyde.io.trait.hierarchy.impl;

import forsyde.io.trait.hierarchy.ArrayVertexPropertyDefault;
import forsyde.io.trait.hierarchy.ArrayVertexPropertyType;
import forsyde.io.trait.hierarchy.BooleanVertexPropertyDefault;
import forsyde.io.trait.hierarchy.BooleanVertexPropertyType;
import forsyde.io.trait.hierarchy.DoubleVertexPropertyDefault;
import forsyde.io.trait.hierarchy.DoubleVertexPropertyType;
import forsyde.io.trait.hierarchy.EdgeTraitSpec;
import forsyde.io.trait.hierarchy.FloatVertexPropertyDefault;
import forsyde.io.trait.hierarchy.FloatVertexPropertyType;
import forsyde.io.trait.hierarchy.HierarchyFactory;
import forsyde.io.trait.hierarchy.IntMapVertexPropertyDefault;
import forsyde.io.trait.hierarchy.IntMapVertexPropertyType;
import forsyde.io.trait.hierarchy.IntVertexPropertyDefault;
import forsyde.io.trait.hierarchy.IntVertexPropertyType;
import forsyde.io.trait.hierarchy.LongVertexPropertyDefault;
import forsyde.io.trait.hierarchy.LongVertexPropertyType;
import forsyde.io.trait.hierarchy.PortDirectionEnum;
import forsyde.io.trait.hierarchy.PortSpec;
import forsyde.io.trait.hierarchy.StringMapVertexPropertyDefault;
import forsyde.io.trait.hierarchy.StringMapVertexPropertyType;
import forsyde.io.trait.hierarchy.StringVertexPropertyDefault;
import forsyde.io.trait.hierarchy.StringVertexPropertyType;
import forsyde.io.trait.hierarchy.TraitHierarchy;
import forsyde.io.trait.hierarchy.VertexPropertyDefault;
import forsyde.io.trait.hierarchy.VertexPropertySpec;
import forsyde.io.trait.hierarchy.VertexPropertyType;
import forsyde.io.trait.hierarchy.VertexTraitSpec;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see HierarchyFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/GenModel generateSchema='true' minimalReflectiveMethods='false' literalsInterface='false' operationReflection='false' resource='XML' suppressGenModelAnnotations='false' adapterFactory='false' containmentProxies='false' suppressEMFMetaData='true' suppressNotification='true' basePackage='forsyde.io.trait'"
 * @generated
 */
public class HierarchyPackageImpl extends EPackageImpl {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "hierarchy";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "forsyde.io.trait.hierarchy";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "hierarchy";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final HierarchyPackageImpl eINSTANCE = HierarchyPackageImpl.init();

	/**
	 * The meta object id for the '{@link PortSpecImpl <em>Port Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see PortSpecImpl
	 * @see HierarchyPackageImpl#getPortSpec()
	 * @generated
	 */
	public static final int PORT_SPEC = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PORT_SPEC__NAME = 0;

	/**
	 * The feature id for the '<em><b>Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PORT_SPEC__MULTIPLE = 1;

	/**
	 * The feature id for the '<em><b>Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PORT_SPEC__ORDERED = 2;

	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PORT_SPEC__DIRECTION = 3;

	/**
	 * The feature id for the '<em><b>Connected Vertex Trait</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PORT_SPEC__CONNECTED_VERTEX_TRAIT = 4;

	/**
	 * The feature id for the '<em><b>Connected Edge Trait</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PORT_SPEC__CONNECTED_EDGE_TRAIT = 5;

	/**
	 * The number of structural features of the '<em>Port Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PORT_SPEC_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link VertexPropertyType <em>Vertex Property Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see VertexPropertyType
	 * @see HierarchyPackageImpl#getVertexPropertyType()
	 * @generated
	 */
	public static final int VERTEX_PROPERTY_TYPE = 1;

	/**
	 * The number of structural features of the '<em>Vertex Property Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_PROPERTY_TYPE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link StringVertexPropertyTypeImpl <em>String Vertex Property Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see StringVertexPropertyTypeImpl
	 * @see HierarchyPackageImpl#getStringVertexPropertyType()
	 * @generated
	 */
	public static final int STRING_VERTEX_PROPERTY_TYPE = 2;

	/**
	 * The number of structural features of the '<em>String Vertex Property Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_VERTEX_PROPERTY_TYPE_FEATURE_COUNT = VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link IntVertexPropertyTypeImpl <em>Int Vertex Property Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see IntVertexPropertyTypeImpl
	 * @see HierarchyPackageImpl#getIntVertexPropertyType()
	 * @generated
	 */
	public static final int INT_VERTEX_PROPERTY_TYPE = 3;

	/**
	 * The number of structural features of the '<em>Int Vertex Property Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INT_VERTEX_PROPERTY_TYPE_FEATURE_COUNT = VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link BooleanVertexPropertyTypeImpl <em>Boolean Vertex Property Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BooleanVertexPropertyTypeImpl
	 * @see HierarchyPackageImpl#getBooleanVertexPropertyType()
	 * @generated
	 */
	public static final int BOOLEAN_VERTEX_PROPERTY_TYPE = 4;

	/**
	 * The number of structural features of the '<em>Boolean Vertex Property Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BOOLEAN_VERTEX_PROPERTY_TYPE_FEATURE_COUNT = VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link FloatVertexPropertyTypeImpl <em>Float Vertex Property Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FloatVertexPropertyTypeImpl
	 * @see HierarchyPackageImpl#getFloatVertexPropertyType()
	 * @generated
	 */
	public static final int FLOAT_VERTEX_PROPERTY_TYPE = 5;

	/**
	 * The number of structural features of the '<em>Float Vertex Property Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FLOAT_VERTEX_PROPERTY_TYPE_FEATURE_COUNT = VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link DoubleVertexPropertyTypeImpl <em>Double Vertex Property Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DoubleVertexPropertyTypeImpl
	 * @see HierarchyPackageImpl#getDoubleVertexPropertyType()
	 * @generated
	 */
	public static final int DOUBLE_VERTEX_PROPERTY_TYPE = 6;

	/**
	 * The number of structural features of the '<em>Double Vertex Property Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DOUBLE_VERTEX_PROPERTY_TYPE_FEATURE_COUNT = VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link LongVertexPropertyTypeImpl <em>Long Vertex Property Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see LongVertexPropertyTypeImpl
	 * @see HierarchyPackageImpl#getLongVertexPropertyType()
	 * @generated
	 */
	public static final int LONG_VERTEX_PROPERTY_TYPE = 7;

	/**
	 * The number of structural features of the '<em>Long Vertex Property Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LONG_VERTEX_PROPERTY_TYPE_FEATURE_COUNT = VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link ArrayVertexPropertyTypeImpl <em>Array Vertex Property Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ArrayVertexPropertyTypeImpl
	 * @see HierarchyPackageImpl#getArrayVertexPropertyType()
	 * @generated
	 */
	public static final int ARRAY_VERTEX_PROPERTY_TYPE = 8;

	/**
	 * The feature id for the '<em><b>Value Types</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ARRAY_VERTEX_PROPERTY_TYPE__VALUE_TYPES = VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Array Vertex Property Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ARRAY_VERTEX_PROPERTY_TYPE_FEATURE_COUNT = VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link IntMapVertexPropertyTypeImpl <em>Int Map Vertex Property Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see IntMapVertexPropertyTypeImpl
	 * @see HierarchyPackageImpl#getIntMapVertexPropertyType()
	 * @generated
	 */
	public static final int INT_MAP_VERTEX_PROPERTY_TYPE = 9;

	/**
	 * The feature id for the '<em><b>Value Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INT_MAP_VERTEX_PROPERTY_TYPE__VALUE_TYPE = VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Int Map Vertex Property Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INT_MAP_VERTEX_PROPERTY_TYPE_FEATURE_COUNT = VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link StringMapVertexPropertyTypeImpl <em>String Map Vertex Property Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see StringMapVertexPropertyTypeImpl
	 * @see HierarchyPackageImpl#getStringMapVertexPropertyType()
	 * @generated
	 */
	public static final int STRING_MAP_VERTEX_PROPERTY_TYPE = 10;

	/**
	 * The feature id for the '<em><b>Value Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_MAP_VERTEX_PROPERTY_TYPE__VALUE_TYPE = INT_VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Map Vertex Property Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_MAP_VERTEX_PROPERTY_TYPE_FEATURE_COUNT = INT_VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link VertexPropertyDefault <em>Vertex Property Default</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see VertexPropertyDefault
	 * @see HierarchyPackageImpl#getVertexPropertyDefault()
	 * @generated
	 */
	public static final int VERTEX_PROPERTY_DEFAULT = 11;

	/**
	 * The number of structural features of the '<em>Vertex Property Default</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link StringVertexPropertyDefaultImpl <em>String Vertex Property Default</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see StringVertexPropertyDefaultImpl
	 * @see HierarchyPackageImpl#getStringVertexPropertyDefault()
	 * @generated
	 */
	public static final int STRING_VERTEX_PROPERTY_DEFAULT = 12;

	/**
	 * The feature id for the '<em><b>String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_VERTEX_PROPERTY_DEFAULT__STRING = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Vertex Property Default</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link IntVertexPropertyDefaultImpl <em>Int Vertex Property Default</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see IntVertexPropertyDefaultImpl
	 * @see HierarchyPackageImpl#getIntVertexPropertyDefault()
	 * @generated
	 */
	public static final int INT_VERTEX_PROPERTY_DEFAULT = 13;

	/**
	 * The feature id for the '<em><b>Int Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INT_VERTEX_PROPERTY_DEFAULT__INT_VALUE = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Int Vertex Property Default</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INT_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link BooleanVertexPropertyDefaultImpl <em>Boolean Vertex Property Default</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BooleanVertexPropertyDefaultImpl
	 * @see HierarchyPackageImpl#getBooleanVertexPropertyDefault()
	 * @generated
	 */
	public static final int BOOLEAN_VERTEX_PROPERTY_DEFAULT = 14;

	/**
	 * The feature id for the '<em><b>Boolean Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BOOLEAN_VERTEX_PROPERTY_DEFAULT__BOOLEAN_VALUE = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Vertex Property Default</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BOOLEAN_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link FloatVertexPropertyDefaultImpl <em>Float Vertex Property Default</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see FloatVertexPropertyDefaultImpl
	 * @see HierarchyPackageImpl#getFloatVertexPropertyDefault()
	 * @generated
	 */
	public static final int FLOAT_VERTEX_PROPERTY_DEFAULT = 15;

	/**
	 * The feature id for the '<em><b>Float Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FLOAT_VERTEX_PROPERTY_DEFAULT__FLOAT_VALUE = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Float Vertex Property Default</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FLOAT_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link DoubleVertexPropertyDefaultImpl <em>Double Vertex Property Default</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DoubleVertexPropertyDefaultImpl
	 * @see HierarchyPackageImpl#getDoubleVertexPropertyDefault()
	 * @generated
	 */
	public static final int DOUBLE_VERTEX_PROPERTY_DEFAULT = 16;

	/**
	 * The feature id for the '<em><b>Double Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DOUBLE_VERTEX_PROPERTY_DEFAULT__DOUBLE_VALUE = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Double Vertex Property Default</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DOUBLE_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link LongVertexPropertyDefaultImpl <em>Long Vertex Property Default</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see LongVertexPropertyDefaultImpl
	 * @see HierarchyPackageImpl#getLongVertexPropertyDefault()
	 * @generated
	 */
	public static final int LONG_VERTEX_PROPERTY_DEFAULT = 17;

	/**
	 * The feature id for the '<em><b>Long Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LONG_VERTEX_PROPERTY_DEFAULT__LONG_VALUE = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Long Vertex Property Default</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LONG_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link ArrayVertexPropertyDefaultImpl <em>Array Vertex Property Default</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ArrayVertexPropertyDefaultImpl
	 * @see HierarchyPackageImpl#getArrayVertexPropertyDefault()
	 * @generated
	 */
	public static final int ARRAY_VERTEX_PROPERTY_DEFAULT = 18;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ARRAY_VERTEX_PROPERTY_DEFAULT__VALUES = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Array Vertex Property Default</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ARRAY_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link IntMapVertexPropertyDefaultImpl <em>Int Map Vertex Property Default</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see IntMapVertexPropertyDefaultImpl
	 * @see HierarchyPackageImpl#getIntMapVertexPropertyDefault()
	 * @generated
	 */
	public static final int INT_MAP_VERTEX_PROPERTY_DEFAULT = 19;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INT_MAP_VERTEX_PROPERTY_DEFAULT__VALUES = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Indexes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INT_MAP_VERTEX_PROPERTY_DEFAULT__INDEXES = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Int Map Vertex Property Default</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INT_MAP_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link StringMapVertexPropertyDefaultImpl <em>String Map Vertex Property Default</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see StringMapVertexPropertyDefaultImpl
	 * @see HierarchyPackageImpl#getStringMapVertexPropertyDefault()
	 * @generated
	 */
	public static final int STRING_MAP_VERTEX_PROPERTY_DEFAULT = 20;

	/**
	 * The feature id for the '<em><b>Int Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_MAP_VERTEX_PROPERTY_DEFAULT__INT_VALUE = INT_VERTEX_PROPERTY_DEFAULT__INT_VALUE;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_MAP_VERTEX_PROPERTY_DEFAULT__VALUES = INT_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Indexes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_MAP_VERTEX_PROPERTY_DEFAULT__INDEXES = INT_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>String Map Vertex Property Default</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_MAP_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT = INT_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link VertexPropertySpecImpl <em>Vertex Property Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see VertexPropertySpecImpl
	 * @see HierarchyPackageImpl#getVertexPropertySpec()
	 * @generated
	 */
	public static final int VERTEX_PROPERTY_SPEC = 21;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_PROPERTY_SPEC__NAME = 0;

	/**
	 * The feature id for the '<em><b>Property Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_PROPERTY_SPEC__PROPERTY_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Property Default</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_PROPERTY_SPEC__PROPERTY_DEFAULT = 2;

	/**
	 * The number of structural features of the '<em>Vertex Property Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_PROPERTY_SPEC_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link VertexTraitSpecImpl <em>Vertex Trait Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see VertexTraitSpecImpl
	 * @see HierarchyPackageImpl#getVertexTraitSpec()
	 * @generated
	 */
	public static final int VERTEX_TRAIT_SPEC = 22;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_TRAIT_SPEC__NAME = 0;

	/**
	 * The feature id for the '<em><b>Refined Traits</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_TRAIT_SPEC__REFINED_TRAITS = 1;

	/**
	 * The feature id for the '<em><b>Required Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_TRAIT_SPEC__REQUIRED_PORTS = 2;

	/**
	 * The feature id for the '<em><b>Required Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_TRAIT_SPEC__REQUIRED_PROPERTIES = 3;

	/**
	 * The number of structural features of the '<em>Vertex Trait Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_TRAIT_SPEC_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link EdgeTraitSpecImpl <em>Edge Trait Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see EdgeTraitSpecImpl
	 * @see HierarchyPackageImpl#getEdgeTraitSpec()
	 * @generated
	 */
	public static final int EDGE_TRAIT_SPEC = 23;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EDGE_TRAIT_SPEC__NAME = 0;

	/**
	 * The feature id for the '<em><b>Refined Traits</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EDGE_TRAIT_SPEC__REFINED_TRAITS = 1;

	/**
	 * The number of structural features of the '<em>Edge Trait Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EDGE_TRAIT_SPEC_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link TraitHierarchyImpl <em>Trait Hierarchy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see TraitHierarchyImpl
	 * @see HierarchyPackageImpl#getTraitHierarchy()
	 * @generated
	 */
	public static final int TRAIT_HIERARCHY = 24;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRAIT_HIERARCHY__NAMESPACE = 0;

	/**
	 * The feature id for the '<em><b>Scoped Hierarchy</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRAIT_HIERARCHY__SCOPED_HIERARCHY = 1;

	/**
	 * The feature id for the '<em><b>Vertex Traits</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRAIT_HIERARCHY__VERTEX_TRAITS = 2;

	/**
	 * The feature id for the '<em><b>Edge Traits</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRAIT_HIERARCHY__EDGE_TRAITS = 3;

	/**
	 * The number of structural features of the '<em>Trait Hierarchy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRAIT_HIERARCHY_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link PortDirectionEnum <em>Port Direction Enum</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see PortDirectionEnum
	 * @see HierarchyPackageImpl#getPortDirectionEnum()
	 * @generated
	 */
	public static final int PORT_DIRECTION_ENUM = 25;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portSpecEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vertexPropertyTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringVertexPropertyTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intVertexPropertyTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanVertexPropertyTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatVertexPropertyTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass doubleVertexPropertyTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass longVertexPropertyTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrayVertexPropertyTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intMapVertexPropertyTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringMapVertexPropertyTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vertexPropertyDefaultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringVertexPropertyDefaultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intVertexPropertyDefaultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanVertexPropertyDefaultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatVertexPropertyDefaultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass doubleVertexPropertyDefaultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass longVertexPropertyDefaultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrayVertexPropertyDefaultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intMapVertexPropertyDefaultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringMapVertexPropertyDefaultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vertexPropertySpecEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vertexTraitSpecEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass edgeTraitSpecEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass traitHierarchyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum portDirectionEnumEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Registry
	 * @see HierarchyPackageImpl#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private HierarchyPackageImpl() {
		super(eNS_URI, ((EFactory)HierarchyFactory.INSTANCE));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link HierarchyPackageImpl#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static HierarchyPackageImpl init() {
		if (isInited) return (HierarchyPackageImpl) Registry.INSTANCE.getEPackage(HierarchyPackageImpl.eNS_URI);

		// Obtain or create and register package
		Object registeredHierarchyPackage = Registry.INSTANCE.get(eNS_URI);
		HierarchyPackageImpl theHierarchyPackage = registeredHierarchyPackage instanceof HierarchyPackageImpl ? (HierarchyPackageImpl)registeredHierarchyPackage : new HierarchyPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theHierarchyPackage.createPackageContents();

		// Initialize created meta-data
		theHierarchyPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theHierarchyPackage.freeze();

		// Update the registry and return the package
		Registry.INSTANCE.put(HierarchyPackageImpl.eNS_URI, theHierarchyPackage);
		return theHierarchyPackage;
	}


	/**
	 * Returns the meta object for class '{@link PortSpec <em>Port Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Spec</em>'.
	 * @see PortSpec
	 * @generated
	 */
	public EClass getPortSpec() {
		return portSpecEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link PortSpec#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see PortSpec#getName()
	 * @see #getPortSpec()
	 * @generated
	 */
	public EAttribute getPortSpec_Name() {
		return (EAttribute)portSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the attribute '{@link PortSpec#isMultiple <em>Multiple</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Multiple</em>'.
	 * @see PortSpec#isMultiple()
	 * @see #getPortSpec()
	 * @generated
	 */
	public EAttribute getPortSpec_Multiple() {
		return (EAttribute)portSpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the attribute '{@link PortSpec#isOrdered <em>Ordered</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ordered</em>'.
	 * @see PortSpec#isOrdered()
	 * @see #getPortSpec()
	 * @generated
	 */
	public EAttribute getPortSpec_Ordered() {
		return (EAttribute)portSpecEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for the attribute '{@link PortSpec#getDirection <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Direction</em>'.
	 * @see PortSpec#getDirection()
	 * @see #getPortSpec()
	 * @generated
	 */
	public EAttribute getPortSpec_Direction() {
		return (EAttribute)portSpecEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * Returns the meta object for the reference '{@link PortSpec#getConnectedVertexTrait <em>Connected Vertex Trait</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Connected Vertex Trait</em>'.
	 * @see PortSpec#getConnectedVertexTrait()
	 * @see #getPortSpec()
	 * @generated
	 */
	public EReference getPortSpec_ConnectedVertexTrait() {
		return (EReference)portSpecEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * Returns the meta object for the reference '{@link PortSpec#getConnectedEdgeTrait <em>Connected Edge Trait</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Connected Edge Trait</em>'.
	 * @see PortSpec#getConnectedEdgeTrait()
	 * @see #getPortSpec()
	 * @generated
	 */
	public EReference getPortSpec_ConnectedEdgeTrait() {
		return (EReference)portSpecEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * Returns the meta object for class '{@link VertexPropertyType <em>Vertex Property Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vertex Property Type</em>'.
	 * @see VertexPropertyType
	 * @generated
	 */
	public EClass getVertexPropertyType() {
		return vertexPropertyTypeEClass;
	}

	/**
	 * Returns the meta object for class '{@link StringVertexPropertyType <em>String Vertex Property Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Vertex Property Type</em>'.
	 * @see StringVertexPropertyType
	 * @generated
	 */
	public EClass getStringVertexPropertyType() {
		return stringVertexPropertyTypeEClass;
	}

	/**
	 * Returns the meta object for class '{@link IntVertexPropertyType <em>Int Vertex Property Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Int Vertex Property Type</em>'.
	 * @see IntVertexPropertyType
	 * @generated
	 */
	public EClass getIntVertexPropertyType() {
		return intVertexPropertyTypeEClass;
	}

	/**
	 * Returns the meta object for class '{@link BooleanVertexPropertyType <em>Boolean Vertex Property Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Vertex Property Type</em>'.
	 * @see BooleanVertexPropertyType
	 * @generated
	 */
	public EClass getBooleanVertexPropertyType() {
		return booleanVertexPropertyTypeEClass;
	}

	/**
	 * Returns the meta object for class '{@link FloatVertexPropertyType <em>Float Vertex Property Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Float Vertex Property Type</em>'.
	 * @see FloatVertexPropertyType
	 * @generated
	 */
	public EClass getFloatVertexPropertyType() {
		return floatVertexPropertyTypeEClass;
	}

	/**
	 * Returns the meta object for class '{@link DoubleVertexPropertyType <em>Double Vertex Property Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double Vertex Property Type</em>'.
	 * @see DoubleVertexPropertyType
	 * @generated
	 */
	public EClass getDoubleVertexPropertyType() {
		return doubleVertexPropertyTypeEClass;
	}

	/**
	 * Returns the meta object for class '{@link LongVertexPropertyType <em>Long Vertex Property Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Long Vertex Property Type</em>'.
	 * @see LongVertexPropertyType
	 * @generated
	 */
	public EClass getLongVertexPropertyType() {
		return longVertexPropertyTypeEClass;
	}

	/**
	 * Returns the meta object for class '{@link ArrayVertexPropertyType <em>Array Vertex Property Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array Vertex Property Type</em>'.
	 * @see ArrayVertexPropertyType
	 * @generated
	 */
	public EClass getArrayVertexPropertyType() {
		return arrayVertexPropertyTypeEClass;
	}

	/**
	 * Returns the meta object for the containment reference '{@link ArrayVertexPropertyType#getValueTypes <em>Value Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value Types</em>'.
	 * @see ArrayVertexPropertyType#getValueTypes()
	 * @see #getArrayVertexPropertyType()
	 * @generated
	 */
	public EReference getArrayVertexPropertyType_ValueTypes() {
		return (EReference)arrayVertexPropertyTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link IntMapVertexPropertyType <em>Int Map Vertex Property Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Int Map Vertex Property Type</em>'.
	 * @see IntMapVertexPropertyType
	 * @generated
	 */
	public EClass getIntMapVertexPropertyType() {
		return intMapVertexPropertyTypeEClass;
	}

	/**
	 * Returns the meta object for the containment reference '{@link IntMapVertexPropertyType#getValueType <em>Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value Type</em>'.
	 * @see IntMapVertexPropertyType#getValueType()
	 * @see #getIntMapVertexPropertyType()
	 * @generated
	 */
	public EReference getIntMapVertexPropertyType_ValueType() {
		return (EReference)intMapVertexPropertyTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link StringMapVertexPropertyType <em>String Map Vertex Property Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Map Vertex Property Type</em>'.
	 * @see StringMapVertexPropertyType
	 * @generated
	 */
	public EClass getStringMapVertexPropertyType() {
		return stringMapVertexPropertyTypeEClass;
	}

	/**
	 * Returns the meta object for the containment reference '{@link StringMapVertexPropertyType#getValueType <em>Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value Type</em>'.
	 * @see StringMapVertexPropertyType#getValueType()
	 * @see #getStringMapVertexPropertyType()
	 * @generated
	 */
	public EReference getStringMapVertexPropertyType_ValueType() {
		return (EReference)stringMapVertexPropertyTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link VertexPropertyDefault <em>Vertex Property Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vertex Property Default</em>'.
	 * @see VertexPropertyDefault
	 * @generated
	 */
	public EClass getVertexPropertyDefault() {
		return vertexPropertyDefaultEClass;
	}

	/**
	 * Returns the meta object for class '{@link StringVertexPropertyDefault <em>String Vertex Property Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Vertex Property Default</em>'.
	 * @see StringVertexPropertyDefault
	 * @generated
	 */
	public EClass getStringVertexPropertyDefault() {
		return stringVertexPropertyDefaultEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link StringVertexPropertyDefault#getString <em>String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String</em>'.
	 * @see StringVertexPropertyDefault#getString()
	 * @see #getStringVertexPropertyDefault()
	 * @generated
	 */
	public EAttribute getStringVertexPropertyDefault_String() {
		return (EAttribute)stringVertexPropertyDefaultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link IntVertexPropertyDefault <em>Int Vertex Property Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Int Vertex Property Default</em>'.
	 * @see IntVertexPropertyDefault
	 * @generated
	 */
	public EClass getIntVertexPropertyDefault() {
		return intVertexPropertyDefaultEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link IntVertexPropertyDefault#getIntValue <em>Int Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Int Value</em>'.
	 * @see IntVertexPropertyDefault#getIntValue()
	 * @see #getIntVertexPropertyDefault()
	 * @generated
	 */
	public EAttribute getIntVertexPropertyDefault_IntValue() {
		return (EAttribute)intVertexPropertyDefaultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link BooleanVertexPropertyDefault <em>Boolean Vertex Property Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Vertex Property Default</em>'.
	 * @see BooleanVertexPropertyDefault
	 * @generated
	 */
	public EClass getBooleanVertexPropertyDefault() {
		return booleanVertexPropertyDefaultEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link BooleanVertexPropertyDefault#isBooleanValue <em>Boolean Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Boolean Value</em>'.
	 * @see BooleanVertexPropertyDefault#isBooleanValue()
	 * @see #getBooleanVertexPropertyDefault()
	 * @generated
	 */
	public EAttribute getBooleanVertexPropertyDefault_BooleanValue() {
		return (EAttribute)booleanVertexPropertyDefaultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link FloatVertexPropertyDefault <em>Float Vertex Property Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Float Vertex Property Default</em>'.
	 * @see FloatVertexPropertyDefault
	 * @generated
	 */
	public EClass getFloatVertexPropertyDefault() {
		return floatVertexPropertyDefaultEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link FloatVertexPropertyDefault#getFloatValue <em>Float Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Float Value</em>'.
	 * @see FloatVertexPropertyDefault#getFloatValue()
	 * @see #getFloatVertexPropertyDefault()
	 * @generated
	 */
	public EAttribute getFloatVertexPropertyDefault_FloatValue() {
		return (EAttribute)floatVertexPropertyDefaultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link DoubleVertexPropertyDefault <em>Double Vertex Property Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double Vertex Property Default</em>'.
	 * @see DoubleVertexPropertyDefault
	 * @generated
	 */
	public EClass getDoubleVertexPropertyDefault() {
		return doubleVertexPropertyDefaultEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link DoubleVertexPropertyDefault#getDoubleValue <em>Double Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Double Value</em>'.
	 * @see DoubleVertexPropertyDefault#getDoubleValue()
	 * @see #getDoubleVertexPropertyDefault()
	 * @generated
	 */
	public EAttribute getDoubleVertexPropertyDefault_DoubleValue() {
		return (EAttribute)doubleVertexPropertyDefaultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link LongVertexPropertyDefault <em>Long Vertex Property Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Long Vertex Property Default</em>'.
	 * @see LongVertexPropertyDefault
	 * @generated
	 */
	public EClass getLongVertexPropertyDefault() {
		return longVertexPropertyDefaultEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link LongVertexPropertyDefault#getLongValue <em>Long Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Long Value</em>'.
	 * @see LongVertexPropertyDefault#getLongValue()
	 * @see #getLongVertexPropertyDefault()
	 * @generated
	 */
	public EAttribute getLongVertexPropertyDefault_LongValue() {
		return (EAttribute)longVertexPropertyDefaultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link ArrayVertexPropertyDefault <em>Array Vertex Property Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array Vertex Property Default</em>'.
	 * @see ArrayVertexPropertyDefault
	 * @generated
	 */
	public EClass getArrayVertexPropertyDefault() {
		return arrayVertexPropertyDefaultEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link ArrayVertexPropertyDefault#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Values</em>'.
	 * @see ArrayVertexPropertyDefault#getValues()
	 * @see #getArrayVertexPropertyDefault()
	 * @generated
	 */
	public EReference getArrayVertexPropertyDefault_Values() {
		return (EReference)arrayVertexPropertyDefaultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link IntMapVertexPropertyDefault <em>Int Map Vertex Property Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Int Map Vertex Property Default</em>'.
	 * @see IntMapVertexPropertyDefault
	 * @generated
	 */
	public EClass getIntMapVertexPropertyDefault() {
		return intMapVertexPropertyDefaultEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link IntMapVertexPropertyDefault#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Values</em>'.
	 * @see IntMapVertexPropertyDefault#getValues()
	 * @see #getIntMapVertexPropertyDefault()
	 * @generated
	 */
	public EReference getIntMapVertexPropertyDefault_Values() {
		return (EReference)intMapVertexPropertyDefaultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the attribute list '{@link IntMapVertexPropertyDefault#getIndexes <em>Indexes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Indexes</em>'.
	 * @see IntMapVertexPropertyDefault#getIndexes()
	 * @see #getIntMapVertexPropertyDefault()
	 * @generated
	 */
	public EAttribute getIntMapVertexPropertyDefault_Indexes() {
		return (EAttribute)intMapVertexPropertyDefaultEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link StringMapVertexPropertyDefault <em>String Map Vertex Property Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Map Vertex Property Default</em>'.
	 * @see StringMapVertexPropertyDefault
	 * @generated
	 */
	public EClass getStringMapVertexPropertyDefault() {
		return stringMapVertexPropertyDefaultEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link StringMapVertexPropertyDefault#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Values</em>'.
	 * @see StringMapVertexPropertyDefault#getValues()
	 * @see #getStringMapVertexPropertyDefault()
	 * @generated
	 */
	public EReference getStringMapVertexPropertyDefault_Values() {
		return (EReference)stringMapVertexPropertyDefaultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the attribute list '{@link StringMapVertexPropertyDefault#getIndexes <em>Indexes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Indexes</em>'.
	 * @see StringMapVertexPropertyDefault#getIndexes()
	 * @see #getStringMapVertexPropertyDefault()
	 * @generated
	 */
	public EAttribute getStringMapVertexPropertyDefault_Indexes() {
		return (EAttribute)stringMapVertexPropertyDefaultEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link VertexPropertySpec <em>Vertex Property Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vertex Property Spec</em>'.
	 * @see VertexPropertySpec
	 * @generated
	 */
	public EClass getVertexPropertySpec() {
		return vertexPropertySpecEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link VertexPropertySpec#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see VertexPropertySpec#getName()
	 * @see #getVertexPropertySpec()
	 * @generated
	 */
	public EAttribute getVertexPropertySpec_Name() {
		return (EAttribute)vertexPropertySpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference '{@link VertexPropertySpec#getPropertyType <em>Property Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Property Type</em>'.
	 * @see VertexPropertySpec#getPropertyType()
	 * @see #getVertexPropertySpec()
	 * @generated
	 */
	public EReference getVertexPropertySpec_PropertyType() {
		return (EReference)vertexPropertySpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the containment reference '{@link VertexPropertySpec#getPropertyDefault <em>Property Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Property Default</em>'.
	 * @see VertexPropertySpec#getPropertyDefault()
	 * @see #getVertexPropertySpec()
	 * @generated
	 */
	public EReference getVertexPropertySpec_PropertyDefault() {
		return (EReference)vertexPropertySpecEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for class '{@link VertexTraitSpec <em>Vertex Trait Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vertex Trait Spec</em>'.
	 * @see VertexTraitSpec
	 * @generated
	 */
	public EClass getVertexTraitSpec() {
		return vertexTraitSpecEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link VertexTraitSpec#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see VertexTraitSpec#getName()
	 * @see #getVertexTraitSpec()
	 * @generated
	 */
	public EAttribute getVertexTraitSpec_Name() {
		return (EAttribute)vertexTraitSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the reference list '{@link VertexTraitSpec#getRefinedTraits <em>Refined Traits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Refined Traits</em>'.
	 * @see VertexTraitSpec#getRefinedTraits()
	 * @see #getVertexTraitSpec()
	 * @generated
	 */
	public EReference getVertexTraitSpec_RefinedTraits() {
		return (EReference)vertexTraitSpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link VertexTraitSpec#getRequiredPorts <em>Required Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Required Ports</em>'.
	 * @see VertexTraitSpec#getRequiredPorts()
	 * @see #getVertexTraitSpec()
	 * @generated
	 */
	public EReference getVertexTraitSpec_RequiredPorts() {
		return (EReference)vertexTraitSpecEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link VertexTraitSpec#getRequiredProperties <em>Required Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Required Properties</em>'.
	 * @see VertexTraitSpec#getRequiredProperties()
	 * @see #getVertexTraitSpec()
	 * @generated
	 */
	public EReference getVertexTraitSpec_RequiredProperties() {
		return (EReference)vertexTraitSpecEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * Returns the meta object for class '{@link EdgeTraitSpec <em>Edge Trait Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Edge Trait Spec</em>'.
	 * @see EdgeTraitSpec
	 * @generated
	 */
	public EClass getEdgeTraitSpec() {
		return edgeTraitSpecEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link EdgeTraitSpec#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see EdgeTraitSpec#getName()
	 * @see #getEdgeTraitSpec()
	 * @generated
	 */
	public EAttribute getEdgeTraitSpec_Name() {
		return (EAttribute)edgeTraitSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the reference list '{@link EdgeTraitSpec#getRefinedTraits <em>Refined Traits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Refined Traits</em>'.
	 * @see EdgeTraitSpec#getRefinedTraits()
	 * @see #getEdgeTraitSpec()
	 * @generated
	 */
	public EReference getEdgeTraitSpec_RefinedTraits() {
		return (EReference)edgeTraitSpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link TraitHierarchy <em>Trait Hierarchy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trait Hierarchy</em>'.
	 * @see TraitHierarchy
	 * @generated
	 */
	public EClass getTraitHierarchy() {
		return traitHierarchyEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link TraitHierarchy#getNamespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Namespace</em>'.
	 * @see TraitHierarchy#getNamespace()
	 * @see #getTraitHierarchy()
	 * @generated
	 */
	public EAttribute getTraitHierarchy_Namespace() {
		return (EAttribute)traitHierarchyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link TraitHierarchy#getScopedHierarchy <em>Scoped Hierarchy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Scoped Hierarchy</em>'.
	 * @see TraitHierarchy#getScopedHierarchy()
	 * @see #getTraitHierarchy()
	 * @generated
	 */
	public EReference getTraitHierarchy_ScopedHierarchy() {
		return (EReference)traitHierarchyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link TraitHierarchy#getVertexTraits <em>Vertex Traits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Vertex Traits</em>'.
	 * @see TraitHierarchy#getVertexTraits()
	 * @see #getTraitHierarchy()
	 * @generated
	 */
	public EReference getTraitHierarchy_VertexTraits() {
		return (EReference)traitHierarchyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link TraitHierarchy#getEdgeTraits <em>Edge Traits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Edge Traits</em>'.
	 * @see TraitHierarchy#getEdgeTraits()
	 * @see #getTraitHierarchy()
	 * @generated
	 */
	public EReference getTraitHierarchy_EdgeTraits() {
		return (EReference)traitHierarchyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * Returns the meta object for enum '{@link PortDirectionEnum <em>Port Direction Enum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Port Direction Enum</em>'.
	 * @see PortDirectionEnum
	 * @generated
	 */
	public EEnum getPortDirectionEnum() {
		return portDirectionEnumEEnum;
	}

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public HierarchyFactory getHierarchyFactory() {
		return (HierarchyFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		portSpecEClass = createEClass(PORT_SPEC);
		createEAttribute(portSpecEClass, PORT_SPEC__NAME);
		createEAttribute(portSpecEClass, PORT_SPEC__MULTIPLE);
		createEAttribute(portSpecEClass, PORT_SPEC__ORDERED);
		createEAttribute(portSpecEClass, PORT_SPEC__DIRECTION);
		createEReference(portSpecEClass, PORT_SPEC__CONNECTED_VERTEX_TRAIT);
		createEReference(portSpecEClass, PORT_SPEC__CONNECTED_EDGE_TRAIT);

		vertexPropertyTypeEClass = createEClass(VERTEX_PROPERTY_TYPE);

		stringVertexPropertyTypeEClass = createEClass(STRING_VERTEX_PROPERTY_TYPE);

		intVertexPropertyTypeEClass = createEClass(INT_VERTEX_PROPERTY_TYPE);

		booleanVertexPropertyTypeEClass = createEClass(BOOLEAN_VERTEX_PROPERTY_TYPE);

		floatVertexPropertyTypeEClass = createEClass(FLOAT_VERTEX_PROPERTY_TYPE);

		doubleVertexPropertyTypeEClass = createEClass(DOUBLE_VERTEX_PROPERTY_TYPE);

		longVertexPropertyTypeEClass = createEClass(LONG_VERTEX_PROPERTY_TYPE);

		arrayVertexPropertyTypeEClass = createEClass(ARRAY_VERTEX_PROPERTY_TYPE);
		createEReference(arrayVertexPropertyTypeEClass, ARRAY_VERTEX_PROPERTY_TYPE__VALUE_TYPES);

		intMapVertexPropertyTypeEClass = createEClass(INT_MAP_VERTEX_PROPERTY_TYPE);
		createEReference(intMapVertexPropertyTypeEClass, INT_MAP_VERTEX_PROPERTY_TYPE__VALUE_TYPE);

		stringMapVertexPropertyTypeEClass = createEClass(STRING_MAP_VERTEX_PROPERTY_TYPE);
		createEReference(stringMapVertexPropertyTypeEClass, STRING_MAP_VERTEX_PROPERTY_TYPE__VALUE_TYPE);

		vertexPropertyDefaultEClass = createEClass(VERTEX_PROPERTY_DEFAULT);

		stringVertexPropertyDefaultEClass = createEClass(STRING_VERTEX_PROPERTY_DEFAULT);
		createEAttribute(stringVertexPropertyDefaultEClass, STRING_VERTEX_PROPERTY_DEFAULT__STRING);

		intVertexPropertyDefaultEClass = createEClass(INT_VERTEX_PROPERTY_DEFAULT);
		createEAttribute(intVertexPropertyDefaultEClass, INT_VERTEX_PROPERTY_DEFAULT__INT_VALUE);

		booleanVertexPropertyDefaultEClass = createEClass(BOOLEAN_VERTEX_PROPERTY_DEFAULT);
		createEAttribute(booleanVertexPropertyDefaultEClass, BOOLEAN_VERTEX_PROPERTY_DEFAULT__BOOLEAN_VALUE);

		floatVertexPropertyDefaultEClass = createEClass(FLOAT_VERTEX_PROPERTY_DEFAULT);
		createEAttribute(floatVertexPropertyDefaultEClass, FLOAT_VERTEX_PROPERTY_DEFAULT__FLOAT_VALUE);

		doubleVertexPropertyDefaultEClass = createEClass(DOUBLE_VERTEX_PROPERTY_DEFAULT);
		createEAttribute(doubleVertexPropertyDefaultEClass, DOUBLE_VERTEX_PROPERTY_DEFAULT__DOUBLE_VALUE);

		longVertexPropertyDefaultEClass = createEClass(LONG_VERTEX_PROPERTY_DEFAULT);
		createEAttribute(longVertexPropertyDefaultEClass, LONG_VERTEX_PROPERTY_DEFAULT__LONG_VALUE);

		arrayVertexPropertyDefaultEClass = createEClass(ARRAY_VERTEX_PROPERTY_DEFAULT);
		createEReference(arrayVertexPropertyDefaultEClass, ARRAY_VERTEX_PROPERTY_DEFAULT__VALUES);

		intMapVertexPropertyDefaultEClass = createEClass(INT_MAP_VERTEX_PROPERTY_DEFAULT);
		createEReference(intMapVertexPropertyDefaultEClass, INT_MAP_VERTEX_PROPERTY_DEFAULT__VALUES);
		createEAttribute(intMapVertexPropertyDefaultEClass, INT_MAP_VERTEX_PROPERTY_DEFAULT__INDEXES);

		stringMapVertexPropertyDefaultEClass = createEClass(STRING_MAP_VERTEX_PROPERTY_DEFAULT);
		createEReference(stringMapVertexPropertyDefaultEClass, STRING_MAP_VERTEX_PROPERTY_DEFAULT__VALUES);
		createEAttribute(stringMapVertexPropertyDefaultEClass, STRING_MAP_VERTEX_PROPERTY_DEFAULT__INDEXES);

		vertexPropertySpecEClass = createEClass(VERTEX_PROPERTY_SPEC);
		createEAttribute(vertexPropertySpecEClass, VERTEX_PROPERTY_SPEC__NAME);
		createEReference(vertexPropertySpecEClass, VERTEX_PROPERTY_SPEC__PROPERTY_TYPE);
		createEReference(vertexPropertySpecEClass, VERTEX_PROPERTY_SPEC__PROPERTY_DEFAULT);

		vertexTraitSpecEClass = createEClass(VERTEX_TRAIT_SPEC);
		createEAttribute(vertexTraitSpecEClass, VERTEX_TRAIT_SPEC__NAME);
		createEReference(vertexTraitSpecEClass, VERTEX_TRAIT_SPEC__REFINED_TRAITS);
		createEReference(vertexTraitSpecEClass, VERTEX_TRAIT_SPEC__REQUIRED_PORTS);
		createEReference(vertexTraitSpecEClass, VERTEX_TRAIT_SPEC__REQUIRED_PROPERTIES);

		edgeTraitSpecEClass = createEClass(EDGE_TRAIT_SPEC);
		createEAttribute(edgeTraitSpecEClass, EDGE_TRAIT_SPEC__NAME);
		createEReference(edgeTraitSpecEClass, EDGE_TRAIT_SPEC__REFINED_TRAITS);

		traitHierarchyEClass = createEClass(TRAIT_HIERARCHY);
		createEAttribute(traitHierarchyEClass, TRAIT_HIERARCHY__NAMESPACE);
		createEReference(traitHierarchyEClass, TRAIT_HIERARCHY__SCOPED_HIERARCHY);
		createEReference(traitHierarchyEClass, TRAIT_HIERARCHY__VERTEX_TRAITS);
		createEReference(traitHierarchyEClass, TRAIT_HIERARCHY__EDGE_TRAITS);

		// Create enums
		portDirectionEnumEEnum = createEEnum(PORT_DIRECTION_ENUM);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		EcorePackage theEcorePackage = (EcorePackage) Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		stringVertexPropertyTypeEClass.getESuperTypes().add(this.getVertexPropertyType());
		intVertexPropertyTypeEClass.getESuperTypes().add(this.getVertexPropertyType());
		booleanVertexPropertyTypeEClass.getESuperTypes().add(this.getVertexPropertyType());
		floatVertexPropertyTypeEClass.getESuperTypes().add(this.getVertexPropertyType());
		doubleVertexPropertyTypeEClass.getESuperTypes().add(this.getVertexPropertyType());
		longVertexPropertyTypeEClass.getESuperTypes().add(this.getVertexPropertyType());
		arrayVertexPropertyTypeEClass.getESuperTypes().add(this.getVertexPropertyType());
		intMapVertexPropertyTypeEClass.getESuperTypes().add(this.getVertexPropertyType());
		stringMapVertexPropertyTypeEClass.getESuperTypes().add(this.getIntVertexPropertyType());
		stringVertexPropertyDefaultEClass.getESuperTypes().add(this.getVertexPropertyDefault());
		intVertexPropertyDefaultEClass.getESuperTypes().add(this.getVertexPropertyDefault());
		booleanVertexPropertyDefaultEClass.getESuperTypes().add(this.getVertexPropertyDefault());
		floatVertexPropertyDefaultEClass.getESuperTypes().add(this.getVertexPropertyDefault());
		doubleVertexPropertyDefaultEClass.getESuperTypes().add(this.getVertexPropertyDefault());
		longVertexPropertyDefaultEClass.getESuperTypes().add(this.getVertexPropertyDefault());
		arrayVertexPropertyDefaultEClass.getESuperTypes().add(this.getVertexPropertyDefault());
		intMapVertexPropertyDefaultEClass.getESuperTypes().add(this.getVertexPropertyDefault());
		stringMapVertexPropertyDefaultEClass.getESuperTypes().add(this.getIntVertexPropertyDefault());

		// Initialize classes and features; add operations and parameters
		initEClass(portSpecEClass, PortSpec.class, "PortSpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPortSpec_Name(), theEcorePackage.getEString(), "name", null, 0, 1, PortSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPortSpec_Multiple(), theEcorePackage.getEBoolean(), "multiple", "true", 0, 1, PortSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPortSpec_Ordered(), theEcorePackage.getEBoolean(), "ordered", "false", 0, 1, PortSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPortSpec_Direction(), this.getPortDirectionEnum(), "direction", "BIDIRECTIONAL", 0, 1, PortSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPortSpec_ConnectedVertexTrait(), this.getVertexTraitSpec(), null, "connectedVertexTrait", null, 0, 1, PortSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPortSpec_ConnectedEdgeTrait(), this.getEdgeTraitSpec(), null, "connectedEdgeTrait", null, 0, 1, PortSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(vertexPropertyTypeEClass, VertexPropertyType.class, "VertexPropertyType", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stringVertexPropertyTypeEClass, StringVertexPropertyType.class, "StringVertexPropertyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(intVertexPropertyTypeEClass, IntVertexPropertyType.class, "IntVertexPropertyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(booleanVertexPropertyTypeEClass, BooleanVertexPropertyType.class, "BooleanVertexPropertyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(floatVertexPropertyTypeEClass, FloatVertexPropertyType.class, "FloatVertexPropertyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(doubleVertexPropertyTypeEClass, DoubleVertexPropertyType.class, "DoubleVertexPropertyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(longVertexPropertyTypeEClass, LongVertexPropertyType.class, "LongVertexPropertyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(arrayVertexPropertyTypeEClass, ArrayVertexPropertyType.class, "ArrayVertexPropertyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArrayVertexPropertyType_ValueTypes(), this.getVertexPropertyType(), null, "valueTypes", null, 0, 1, ArrayVertexPropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(intMapVertexPropertyTypeEClass, IntMapVertexPropertyType.class, "IntMapVertexPropertyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIntMapVertexPropertyType_ValueType(), this.getVertexPropertyType(), null, "valueType", null, 0, 1, IntMapVertexPropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringMapVertexPropertyTypeEClass, StringMapVertexPropertyType.class, "StringMapVertexPropertyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStringMapVertexPropertyType_ValueType(), this.getIntVertexPropertyType(), null, "valueType", null, 0, 1, StringMapVertexPropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(vertexPropertyDefaultEClass, VertexPropertyDefault.class, "VertexPropertyDefault", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stringVertexPropertyDefaultEClass, StringVertexPropertyDefault.class, "StringVertexPropertyDefault", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringVertexPropertyDefault_String(), theEcorePackage.getEString(), "string", null, 0, 1, StringVertexPropertyDefault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(intVertexPropertyDefaultEClass, IntVertexPropertyDefault.class, "IntVertexPropertyDefault", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntVertexPropertyDefault_IntValue(), theEcorePackage.getEInt(), "intValue", null, 0, 1, IntVertexPropertyDefault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(booleanVertexPropertyDefaultEClass, BooleanVertexPropertyDefault.class, "BooleanVertexPropertyDefault", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanVertexPropertyDefault_BooleanValue(), theEcorePackage.getEBoolean(), "booleanValue", null, 0, 1, BooleanVertexPropertyDefault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(floatVertexPropertyDefaultEClass, FloatVertexPropertyDefault.class, "FloatVertexPropertyDefault", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFloatVertexPropertyDefault_FloatValue(), theEcorePackage.getEFloat(), "floatValue", null, 0, 1, FloatVertexPropertyDefault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(doubleVertexPropertyDefaultEClass, DoubleVertexPropertyDefault.class, "DoubleVertexPropertyDefault", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDoubleVertexPropertyDefault_DoubleValue(), theEcorePackage.getEDouble(), "doubleValue", null, 0, 1, DoubleVertexPropertyDefault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(longVertexPropertyDefaultEClass, LongVertexPropertyDefault.class, "LongVertexPropertyDefault", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLongVertexPropertyDefault_LongValue(), theEcorePackage.getELong(), "longValue", null, 0, 1, LongVertexPropertyDefault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(arrayVertexPropertyDefaultEClass, ArrayVertexPropertyDefault.class, "ArrayVertexPropertyDefault", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArrayVertexPropertyDefault_Values(), this.getVertexPropertyDefault(), null, "values", null, 0, -1, ArrayVertexPropertyDefault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(intMapVertexPropertyDefaultEClass, IntMapVertexPropertyDefault.class, "IntMapVertexPropertyDefault", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIntMapVertexPropertyDefault_Values(), this.getVertexPropertyDefault(), null, "values", null, 0, -1, IntMapVertexPropertyDefault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntMapVertexPropertyDefault_Indexes(), theEcorePackage.getEInt(), "indexes", null, 0, -1, IntMapVertexPropertyDefault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringMapVertexPropertyDefaultEClass, StringMapVertexPropertyDefault.class, "StringMapVertexPropertyDefault", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStringMapVertexPropertyDefault_Values(), this.getIntVertexPropertyDefault(), null, "values", null, 0, -1, StringMapVertexPropertyDefault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStringMapVertexPropertyDefault_Indexes(), theEcorePackage.getEString(), "indexes", null, 0, -1, StringMapVertexPropertyDefault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(vertexPropertySpecEClass, VertexPropertySpec.class, "VertexPropertySpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVertexPropertySpec_Name(), theEcorePackage.getEString(), "name", null, 0, 1, VertexPropertySpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVertexPropertySpec_PropertyType(), this.getVertexPropertyType(), null, "propertyType", null, 0, 1, VertexPropertySpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVertexPropertySpec_PropertyDefault(), this.getVertexPropertyDefault(), null, "propertyDefault", null, 0, 1, VertexPropertySpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(vertexTraitSpecEClass, VertexTraitSpec.class, "VertexTraitSpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVertexTraitSpec_Name(), theEcorePackage.getEString(), "name", null, 0, 1, VertexTraitSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVertexTraitSpec_RefinedTraits(), this.getVertexTraitSpec(), null, "refinedTraits", null, 0, -1, VertexTraitSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVertexTraitSpec_RequiredPorts(), this.getPortSpec(), null, "requiredPorts", null, 0, -1, VertexTraitSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVertexTraitSpec_RequiredProperties(), this.getVertexPropertySpec(), null, "requiredProperties", null, 0, -1, VertexTraitSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(edgeTraitSpecEClass, EdgeTraitSpec.class, "EdgeTraitSpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEdgeTraitSpec_Name(), theEcorePackage.getEString(), "name", null, 0, 1, EdgeTraitSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEdgeTraitSpec_RefinedTraits(), this.getEdgeTraitSpec(), null, "refinedTraits", null, 0, -1, EdgeTraitSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(traitHierarchyEClass, TraitHierarchy.class, "TraitHierarchy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTraitHierarchy_Namespace(), theEcorePackage.getEString(), "namespace", "", 0, 1, TraitHierarchy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTraitHierarchy_ScopedHierarchy(), this.getTraitHierarchy(), null, "scopedHierarchy", null, 0, -1, TraitHierarchy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTraitHierarchy_VertexTraits(), this.getVertexTraitSpec(), null, "vertexTraits", null, 0, -1, TraitHierarchy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTraitHierarchy_EdgeTraits(), this.getEdgeTraitSpec(), null, "edgeTraits", null, 0, -1, TraitHierarchy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(portDirectionEnumEEnum, PortDirectionEnum.class, "PortDirectionEnum");
		addEEnumLiteral(portDirectionEnumEEnum, PortDirectionEnum.FORWARD);
		addEEnumLiteral(portDirectionEnumEEnum, PortDirectionEnum.BACKWARD);
		addEEnumLiteral(portDirectionEnumEEnum, PortDirectionEnum.BIDIRECTIONAL);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/GenModel
		createGenModelAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/GenModel</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createGenModelAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/GenModel";
		addAnnotation
		  (this,
		   source,
		   new String[] {
			   "generateSchema", "true",
			   "minimalReflectiveMethods", "false",
			   "literalsInterface", "false",
			   "operationReflection", "false",
			   "resource", "XML",
			   "suppressGenModelAnnotations", "false",
			   "adapterFactory", "false",
			   "containmentProxies", "false",
			   "suppressEMFMetaData", "true",
			   "suppressNotification", "true",
			   "basePackage", "forsyde.io.trait"
		   });
	}

} //HierarchyPackageImpl
