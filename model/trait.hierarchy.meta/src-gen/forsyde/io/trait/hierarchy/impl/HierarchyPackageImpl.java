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
 * &lt;!-- begin-user-doc --&gt;
 * The <b>Package&lt;/b&gt; for the model.
 * It contains accessors for the meta objects to represent
 * &lt; &gt;
 *   <li>each class,&lt;/li&gt;
 *   <li>each feature of each class,&lt;/li&gt;
 *   <li>each enum,&lt;/li&gt;
 *   <li>and each data type&lt;/li&gt;
 * &lt;/ul&gt;
 * &lt;!-- end-user-doc --&gt;
 * @see forsyde.io.trait.hierarchy.HierarchyFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/GenModel generateSchema='true' minimalReflectiveMethods='false' literalsInterface='false' operationReflection='false' resource='XML' suppressGenModelAnnotations='false' adapterFactory='false' containmentProxies='false' suppressEMFMetaData='true' suppressNotification='true' basePackage='forsyde.io.trait'"
 * @generated
 */
public class HierarchyPackageImpl extends EPackageImpl {
	/**
	 * The package name.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public static final String eNAME = "hierarchy";

	/**
	 * The package namespace URI.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public static final String eNS_URI = "forsyde.io.trait.hierarchy";

	/**
	 * The package namespace name.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public static final String eNS_PREFIX = "hierarchy";

	/**
	 * The singleton instance of the package.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public static final HierarchyPackageImpl eINSTANCE = forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl.init();

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.PortSpecImpl <em>Port Spec&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.PortSpecImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getPortSpec()
	 * @generated
	 */
	public static final int PORT_SPEC = 0;

	/**
	 * The feature id for the '<em><b>Name</b>&lt;/em&gt;' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int PORT_SPEC__NAME = 0;

	/**
	 * The feature id for the '<em><b>Direction</b>&lt;/em&gt;' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int PORT_SPEC__DIRECTION = 1;

	/**
	 * The feature id for the '<em><b>Connected Vertex Trait</b>&lt;/em&gt;' reference.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int PORT_SPEC__CONNECTED_VERTEX_TRAIT = 2;

	/**
	 * The feature id for the '<em><b>Connected Edge Trait</b>&lt;/em&gt;' reference.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int PORT_SPEC__CONNECTED_EDGE_TRAIT = 3;

	/**
	 * The number of structural features of the '<em>Port Spec&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int PORT_SPEC_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.VertexPropertyType <em>Vertex Property Type&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.VertexPropertyType
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getVertexPropertyType()
	 * @generated
	 */
	public static final int VERTEX_PROPERTY_TYPE = 1;

	/**
	 * The number of structural features of the '<em>Vertex Property Type&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_PROPERTY_TYPE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.StringVertexPropertyTypeImpl <em>String Vertex Property Type&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.StringVertexPropertyTypeImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getStringVertexPropertyType()
	 * @generated
	 */
	public static final int STRING_VERTEX_PROPERTY_TYPE = 2;

	/**
	 * The number of structural features of the '<em>String Vertex Property Type&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int STRING_VERTEX_PROPERTY_TYPE_FEATURE_COUNT = VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.IntVertexPropertyTypeImpl <em>Int Vertex Property Type&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.IntVertexPropertyTypeImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getIntVertexPropertyType()
	 * @generated
	 */
	public static final int INT_VERTEX_PROPERTY_TYPE = 3;

	/**
	 * The number of structural features of the '<em>Int Vertex Property Type&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int INT_VERTEX_PROPERTY_TYPE_FEATURE_COUNT = VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.BooleanVertexPropertyTypeImpl <em>Boolean Vertex Property Type&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.BooleanVertexPropertyTypeImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getBooleanVertexPropertyType()
	 * @generated
	 */
	public static final int BOOLEAN_VERTEX_PROPERTY_TYPE = 4;

	/**
	 * The number of structural features of the '<em>Boolean Vertex Property Type&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int BOOLEAN_VERTEX_PROPERTY_TYPE_FEATURE_COUNT = VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.FloatVertexPropertyTypeImpl <em>Float Vertex Property Type&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.FloatVertexPropertyTypeImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getFloatVertexPropertyType()
	 * @generated
	 */
	public static final int FLOAT_VERTEX_PROPERTY_TYPE = 5;

	/**
	 * The number of structural features of the '<em>Float Vertex Property Type&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int FLOAT_VERTEX_PROPERTY_TYPE_FEATURE_COUNT = VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.DoubleVertexPropertyTypeImpl <em>Double Vertex Property Type&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.DoubleVertexPropertyTypeImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getDoubleVertexPropertyType()
	 * @generated
	 */
	public static final int DOUBLE_VERTEX_PROPERTY_TYPE = 6;

	/**
	 * The number of structural features of the '<em>Double Vertex Property Type&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int DOUBLE_VERTEX_PROPERTY_TYPE_FEATURE_COUNT = VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.LongVertexPropertyTypeImpl <em>Long Vertex Property Type&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.LongVertexPropertyTypeImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getLongVertexPropertyType()
	 * @generated
	 */
	public static final int LONG_VERTEX_PROPERTY_TYPE = 7;

	/**
	 * The number of structural features of the '<em>Long Vertex Property Type&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int LONG_VERTEX_PROPERTY_TYPE_FEATURE_COUNT = VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.ArrayVertexPropertyTypeImpl <em>Array Vertex Property Type&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.ArrayVertexPropertyTypeImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getArrayVertexPropertyType()
	 * @generated
	 */
	public static final int ARRAY_VERTEX_PROPERTY_TYPE = 8;

	/**
	 * The feature id for the '<em><b>Value Types</b>&lt;/em&gt;' containment reference.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int ARRAY_VERTEX_PROPERTY_TYPE__VALUE_TYPES = VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Array Vertex Property Type&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int ARRAY_VERTEX_PROPERTY_TYPE_FEATURE_COUNT = VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.IntMapVertexPropertyTypeImpl <em>Int Map Vertex Property Type&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.IntMapVertexPropertyTypeImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getIntMapVertexPropertyType()
	 * @generated
	 */
	public static final int INT_MAP_VERTEX_PROPERTY_TYPE = 9;

	/**
	 * The feature id for the '<em><b>Value Type</b>&lt;/em&gt;' containment reference.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int INT_MAP_VERTEX_PROPERTY_TYPE__VALUE_TYPE = VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Int Map Vertex Property Type&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int INT_MAP_VERTEX_PROPERTY_TYPE_FEATURE_COUNT = VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.StringMapVertexPropertyTypeImpl <em>String Map Vertex Property Type&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.StringMapVertexPropertyTypeImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getStringMapVertexPropertyType()
	 * @generated
	 */
	public static final int STRING_MAP_VERTEX_PROPERTY_TYPE = 10;

	/**
	 * The feature id for the '<em><b>Value Type</b>&lt;/em&gt;' containment reference.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int STRING_MAP_VERTEX_PROPERTY_TYPE__VALUE_TYPE = INT_VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Map Vertex Property Type&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int STRING_MAP_VERTEX_PROPERTY_TYPE_FEATURE_COUNT = INT_VERTEX_PROPERTY_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.VertexPropertyDefault <em>Vertex Property Default&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.VertexPropertyDefault
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getVertexPropertyDefault()
	 * @generated
	 */
	public static final int VERTEX_PROPERTY_DEFAULT = 11;

	/**
	 * The number of structural features of the '<em>Vertex Property Default&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.StringVertexPropertyDefaultImpl <em>String Vertex Property Default&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.StringVertexPropertyDefaultImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getStringVertexPropertyDefault()
	 * @generated
	 */
	public static final int STRING_VERTEX_PROPERTY_DEFAULT = 12;

	/**
	 * The feature id for the '<em><b>String</b>&lt;/em&gt;' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int STRING_VERTEX_PROPERTY_DEFAULT__STRING = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Vertex Property Default&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int STRING_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.IntVertexPropertyDefaultImpl <em>Int Vertex Property Default&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.IntVertexPropertyDefaultImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getIntVertexPropertyDefault()
	 * @generated
	 */
	public static final int INT_VERTEX_PROPERTY_DEFAULT = 13;

	/**
	 * The feature id for the '<em><b>Int Value</b>&lt;/em&gt;' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int INT_VERTEX_PROPERTY_DEFAULT__INT_VALUE = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Int Vertex Property Default&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int INT_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.BooleanVertexPropertyDefaultImpl <em>Boolean Vertex Property Default&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.BooleanVertexPropertyDefaultImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getBooleanVertexPropertyDefault()
	 * @generated
	 */
	public static final int BOOLEAN_VERTEX_PROPERTY_DEFAULT = 14;

	/**
	 * The feature id for the '<em><b>Boolean Value</b>&lt;/em&gt;' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int BOOLEAN_VERTEX_PROPERTY_DEFAULT__BOOLEAN_VALUE = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Vertex Property Default&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int BOOLEAN_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.FloatVertexPropertyDefaultImpl <em>Float Vertex Property Default&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.FloatVertexPropertyDefaultImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getFloatVertexPropertyDefault()
	 * @generated
	 */
	public static final int FLOAT_VERTEX_PROPERTY_DEFAULT = 15;

	/**
	 * The feature id for the '<em><b>Float Value</b>&lt;/em&gt;' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int FLOAT_VERTEX_PROPERTY_DEFAULT__FLOAT_VALUE = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Float Vertex Property Default&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int FLOAT_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.DoubleVertexPropertyDefaultImpl <em>Double Vertex Property Default&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.DoubleVertexPropertyDefaultImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getDoubleVertexPropertyDefault()
	 * @generated
	 */
	public static final int DOUBLE_VERTEX_PROPERTY_DEFAULT = 16;

	/**
	 * The feature id for the '<em><b>Double Value</b>&lt;/em&gt;' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int DOUBLE_VERTEX_PROPERTY_DEFAULT__DOUBLE_VALUE = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Double Vertex Property Default&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int DOUBLE_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.LongVertexPropertyDefaultImpl <em>Long Vertex Property Default&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.LongVertexPropertyDefaultImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getLongVertexPropertyDefault()
	 * @generated
	 */
	public static final int LONG_VERTEX_PROPERTY_DEFAULT = 17;

	/**
	 * The feature id for the '<em><b>Long Value</b>&lt;/em&gt;' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int LONG_VERTEX_PROPERTY_DEFAULT__LONG_VALUE = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Long Vertex Property Default&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int LONG_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.ArrayVertexPropertyDefaultImpl <em>Array Vertex Property Default&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.ArrayVertexPropertyDefaultImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getArrayVertexPropertyDefault()
	 * @generated
	 */
	public static final int ARRAY_VERTEX_PROPERTY_DEFAULT = 18;

	/**
	 * The feature id for the '<em><b>Values</b>&lt;/em&gt;' containment reference list.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int ARRAY_VERTEX_PROPERTY_DEFAULT__VALUES = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Array Vertex Property Default&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int ARRAY_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.IntMapVertexPropertyDefaultImpl <em>Int Map Vertex Property Default&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.IntMapVertexPropertyDefaultImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getIntMapVertexPropertyDefault()
	 * @generated
	 */
	public static final int INT_MAP_VERTEX_PROPERTY_DEFAULT = 19;

	/**
	 * The feature id for the '<em><b>Values</b>&lt;/em&gt;' containment reference list.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int INT_MAP_VERTEX_PROPERTY_DEFAULT__VALUES = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Indexes</b>&lt;/em&gt;' attribute list.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int INT_MAP_VERTEX_PROPERTY_DEFAULT__INDEXES = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Int Map Vertex Property Default&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int INT_MAP_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT = VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.StringMapVertexPropertyDefaultImpl <em>String Map Vertex Property Default&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.StringMapVertexPropertyDefaultImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getStringMapVertexPropertyDefault()
	 * @generated
	 */
	public static final int STRING_MAP_VERTEX_PROPERTY_DEFAULT = 20;

	/**
	 * The feature id for the '<em><b>Int Value</b>&lt;/em&gt;' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int STRING_MAP_VERTEX_PROPERTY_DEFAULT__INT_VALUE = INT_VERTEX_PROPERTY_DEFAULT__INT_VALUE;

	/**
	 * The feature id for the '<em><b>Values</b>&lt;/em&gt;' containment reference list.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int STRING_MAP_VERTEX_PROPERTY_DEFAULT__VALUES = INT_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Indexes</b>&lt;/em&gt;' attribute list.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int STRING_MAP_VERTEX_PROPERTY_DEFAULT__INDEXES = INT_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>String Map Vertex Property Default&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int STRING_MAP_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT = INT_VERTEX_PROPERTY_DEFAULT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.VertexPropertySpecImpl <em>Vertex Property Spec&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.VertexPropertySpecImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getVertexPropertySpec()
	 * @generated
	 */
	public static final int VERTEX_PROPERTY_SPEC = 21;

	/**
	 * The feature id for the '<em><b>Name</b>&lt;/em&gt;' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_PROPERTY_SPEC__NAME = 0;

	/**
	 * The feature id for the '<em><b>Property Type</b>&lt;/em&gt;' containment reference.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_PROPERTY_SPEC__PROPERTY_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Property Default</b>&lt;/em&gt;' containment reference.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_PROPERTY_SPEC__PROPERTY_DEFAULT = 2;

	/**
	 * The number of structural features of the '<em>Vertex Property Spec&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_PROPERTY_SPEC_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.VertexTraitSpecImpl <em>Vertex Trait Spec&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.VertexTraitSpecImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getVertexTraitSpec()
	 * @generated
	 */
	public static final int VERTEX_TRAIT_SPEC = 22;

	/**
	 * The feature id for the '<em><b>Name</b>&lt;/em&gt;' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_TRAIT_SPEC__NAME = 0;

	/**
	 * The feature id for the '<em><b>Refined Traits</b>&lt;/em&gt;' reference list.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_TRAIT_SPEC__REFINED_TRAITS = 1;

	/**
	 * The feature id for the '<em><b>Required Ports</b>&lt;/em&gt;' containment reference list.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_TRAIT_SPEC__REQUIRED_PORTS = 2;

	/**
	 * The feature id for the '<em><b>Required Properties</b>&lt;/em&gt;' containment reference list.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_TRAIT_SPEC__REQUIRED_PROPERTIES = 3;

	/**
	 * The number of structural features of the '<em>Vertex Trait Spec&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int VERTEX_TRAIT_SPEC_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.EdgeTraitSpecImpl <em>Edge Trait Spec&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.EdgeTraitSpecImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getEdgeTraitSpec()
	 * @generated
	 */
	public static final int EDGE_TRAIT_SPEC = 23;

	/**
	 * The feature id for the '<em><b>Name</b>&lt;/em&gt;' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int EDGE_TRAIT_SPEC__NAME = 0;

	/**
	 * The feature id for the '<em><b>Refined Traits</b>&lt;/em&gt;' reference list.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int EDGE_TRAIT_SPEC__REFINED_TRAITS = 1;

	/**
	 * The number of structural features of the '<em>Edge Trait Spec&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int EDGE_TRAIT_SPEC_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.impl.TraitHierarchyImpl <em>Trait Hierarchy&lt;/em&gt;}' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.impl.TraitHierarchyImpl
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getTraitHierarchy()
	 * @generated
	 */
	public static final int TRAIT_HIERARCHY = 24;

	/**
	 * The feature id for the '<em><b>Namespace</b>&lt;/em&gt;' attribute.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int TRAIT_HIERARCHY__NAMESPACE = 0;

	/**
	 * The feature id for the '<em><b>Scoped Hierarchy</b>&lt;/em&gt;' containment reference list.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int TRAIT_HIERARCHY__SCOPED_HIERARCHY = 1;

	/**
	 * The feature id for the '<em><b>Vertex Traits</b>&lt;/em&gt;' containment reference list.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int TRAIT_HIERARCHY__VERTEX_TRAITS = 2;

	/**
	 * The feature id for the '<em><b>Edge Traits</b>&lt;/em&gt;' containment reference list.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int TRAIT_HIERARCHY__EDGE_TRAITS = 3;

	/**
	 * The number of structural features of the '<em>Trait Hierarchy&lt;/em&gt;' class.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 * @ordered
	 */
	public static final int TRAIT_HIERARCHY_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link forsyde.io.trait.hierarchy.PortDirectionEnum <em>Port Direction Enum&lt;/em&gt;}' enum.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see forsyde.io.trait.hierarchy.PortDirectionEnum
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getPortDirectionEnum()
	 * @generated
	 */
	public static final int PORT_DIRECTION_ENUM = 25;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass portSpecEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass vertexPropertyTypeEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass stringVertexPropertyTypeEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass intVertexPropertyTypeEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass booleanVertexPropertyTypeEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass floatVertexPropertyTypeEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass doubleVertexPropertyTypeEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass longVertexPropertyTypeEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass arrayVertexPropertyTypeEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass intMapVertexPropertyTypeEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass stringMapVertexPropertyTypeEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass vertexPropertyDefaultEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass stringVertexPropertyDefaultEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass intVertexPropertyDefaultEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass booleanVertexPropertyDefaultEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass floatVertexPropertyDefaultEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass doubleVertexPropertyDefaultEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass longVertexPropertyDefaultEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass arrayVertexPropertyDefaultEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass intMapVertexPropertyDefaultEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass stringMapVertexPropertyDefaultEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass vertexPropertySpecEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass vertexTraitSpecEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass edgeTraitSpecEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EClass traitHierarchyEClass = null;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private EEnum portDirectionEnumEEnum = null;

	/**
	 * Creates an instance of the model <b>Package&lt;/b&gt;, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * &lt; &gt;Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private HierarchyPackageImpl() {
		super(eNS_URI, ((EFactory)HierarchyFactory.INSTANCE));
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package&lt;/b&gt; for this model, and for any others upon which it depends.
	 *
	 * &lt; &gt;This method is used to initialize {@link HierarchyPackageImpl#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static HierarchyPackageImpl init() {
		if (isInited) return (HierarchyPackageImpl)EPackage.Registry.INSTANCE.getEPackage(HierarchyPackageImpl.eNS_URI);

		// Obtain or create and register package
		Object registeredHierarchyPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
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
		EPackage.Registry.INSTANCE.put(HierarchyPackageImpl.eNS_URI, theHierarchyPackage);
		return theHierarchyPackage;
	}


	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.PortSpec <em>Port Spec&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>Port Spec&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.PortSpec
	 * @generated
	 */
	public EClass getPortSpec() {
		return portSpecEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link forsyde.io.trait.hierarchy.PortSpec#getName <em>Name&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the attribute '<em>Name&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.PortSpec#getName()
	 * @see #getPortSpec()
	 * @generated
	 */
	public EAttribute getPortSpec_Name() {
		return (EAttribute)portSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the attribute '{@link forsyde.io.trait.hierarchy.PortSpec#getDirection <em>Direction&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the attribute '<em>Direction&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.PortSpec#getDirection()
	 * @see #getPortSpec()
	 * @generated
	 */
	public EAttribute getPortSpec_Direction() {
		return (EAttribute)portSpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the reference '{@link forsyde.io.trait.hierarchy.PortSpec#getConnectedVertexTrait <em>Connected Vertex Trait&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the reference '<em>Connected Vertex Trait&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.PortSpec#getConnectedVertexTrait()
	 * @see #getPortSpec()
	 * @generated
	 */
	public EReference getPortSpec_ConnectedVertexTrait() {
		return (EReference)portSpecEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for the reference '{@link forsyde.io.trait.hierarchy.PortSpec#getConnectedEdgeTrait <em>Connected Edge Trait&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the reference '<em>Connected Edge Trait&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.PortSpec#getConnectedEdgeTrait()
	 * @see #getPortSpec()
	 * @generated
	 */
	public EReference getPortSpec_ConnectedEdgeTrait() {
		return (EReference)portSpecEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.VertexPropertyType <em>Vertex Property Type&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>Vertex Property Type&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.VertexPropertyType
	 * @generated
	 */
	public EClass getVertexPropertyType() {
		return vertexPropertyTypeEClass;
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.StringVertexPropertyType <em>String Vertex Property Type&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>String Vertex Property Type&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.StringVertexPropertyType
	 * @generated
	 */
	public EClass getStringVertexPropertyType() {
		return stringVertexPropertyTypeEClass;
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.IntVertexPropertyType <em>Int Vertex Property Type&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>Int Vertex Property Type&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.IntVertexPropertyType
	 * @generated
	 */
	public EClass getIntVertexPropertyType() {
		return intVertexPropertyTypeEClass;
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.BooleanVertexPropertyType <em>Boolean Vertex Property Type&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>Boolean Vertex Property Type&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.BooleanVertexPropertyType
	 * @generated
	 */
	public EClass getBooleanVertexPropertyType() {
		return booleanVertexPropertyTypeEClass;
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.FloatVertexPropertyType <em>Float Vertex Property Type&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>Float Vertex Property Type&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.FloatVertexPropertyType
	 * @generated
	 */
	public EClass getFloatVertexPropertyType() {
		return floatVertexPropertyTypeEClass;
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.DoubleVertexPropertyType <em>Double Vertex Property Type&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>Double Vertex Property Type&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.DoubleVertexPropertyType
	 * @generated
	 */
	public EClass getDoubleVertexPropertyType() {
		return doubleVertexPropertyTypeEClass;
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.LongVertexPropertyType <em>Long Vertex Property Type&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>Long Vertex Property Type&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.LongVertexPropertyType
	 * @generated
	 */
	public EClass getLongVertexPropertyType() {
		return longVertexPropertyTypeEClass;
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.ArrayVertexPropertyType <em>Array Vertex Property Type&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>Array Vertex Property Type&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.ArrayVertexPropertyType
	 * @generated
	 */
	public EClass getArrayVertexPropertyType() {
		return arrayVertexPropertyTypeEClass;
	}

	/**
	 * Returns the meta object for the containment reference '{@link forsyde.io.trait.hierarchy.ArrayVertexPropertyType#getValueTypes <em>Value Types&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the containment reference '<em>Value Types&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.ArrayVertexPropertyType#getValueTypes()
	 * @see #getArrayVertexPropertyType()
	 * @generated
	 */
	public EReference getArrayVertexPropertyType_ValueTypes() {
		return (EReference)arrayVertexPropertyTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.IntMapVertexPropertyType <em>Int Map Vertex Property Type&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>Int Map Vertex Property Type&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.IntMapVertexPropertyType
	 * @generated
	 */
	public EClass getIntMapVertexPropertyType() {
		return intMapVertexPropertyTypeEClass;
	}

	/**
	 * Returns the meta object for the containment reference '{@link forsyde.io.trait.hierarchy.IntMapVertexPropertyType#getValueType <em>Value Type&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the containment reference '<em>Value Type&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.IntMapVertexPropertyType#getValueType()
	 * @see #getIntMapVertexPropertyType()
	 * @generated
	 */
	public EReference getIntMapVertexPropertyType_ValueType() {
		return (EReference)intMapVertexPropertyTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.StringMapVertexPropertyType <em>String Map Vertex Property Type&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>String Map Vertex Property Type&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.StringMapVertexPropertyType
	 * @generated
	 */
	public EClass getStringMapVertexPropertyType() {
		return stringMapVertexPropertyTypeEClass;
	}

	/**
	 * Returns the meta object for the containment reference '{@link forsyde.io.trait.hierarchy.StringMapVertexPropertyType#getValueType <em>Value Type&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the containment reference '<em>Value Type&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.StringMapVertexPropertyType#getValueType()
	 * @see #getStringMapVertexPropertyType()
	 * @generated
	 */
	public EReference getStringMapVertexPropertyType_ValueType() {
		return (EReference)stringMapVertexPropertyTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.VertexPropertyDefault <em>Vertex Property Default&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>Vertex Property Default&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.VertexPropertyDefault
	 * @generated
	 */
	public EClass getVertexPropertyDefault() {
		return vertexPropertyDefaultEClass;
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.StringVertexPropertyDefault <em>String Vertex Property Default&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>String Vertex Property Default&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.StringVertexPropertyDefault
	 * @generated
	 */
	public EClass getStringVertexPropertyDefault() {
		return stringVertexPropertyDefaultEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link forsyde.io.trait.hierarchy.StringVertexPropertyDefault#getString <em>String&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the attribute '<em>String&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.StringVertexPropertyDefault#getString()
	 * @see #getStringVertexPropertyDefault()
	 * @generated
	 */
	public EAttribute getStringVertexPropertyDefault_String() {
		return (EAttribute)stringVertexPropertyDefaultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.IntVertexPropertyDefault <em>Int Vertex Property Default&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>Int Vertex Property Default&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.IntVertexPropertyDefault
	 * @generated
	 */
	public EClass getIntVertexPropertyDefault() {
		return intVertexPropertyDefaultEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link forsyde.io.trait.hierarchy.IntVertexPropertyDefault#getIntValue <em>Int Value&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the attribute '<em>Int Value&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.IntVertexPropertyDefault#getIntValue()
	 * @see #getIntVertexPropertyDefault()
	 * @generated
	 */
	public EAttribute getIntVertexPropertyDefault_IntValue() {
		return (EAttribute)intVertexPropertyDefaultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.BooleanVertexPropertyDefault <em>Boolean Vertex Property Default&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>Boolean Vertex Property Default&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.BooleanVertexPropertyDefault
	 * @generated
	 */
	public EClass getBooleanVertexPropertyDefault() {
		return booleanVertexPropertyDefaultEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link forsyde.io.trait.hierarchy.BooleanVertexPropertyDefault#isBooleanValue <em>Boolean Value&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the attribute '<em>Boolean Value&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.BooleanVertexPropertyDefault#isBooleanValue()
	 * @see #getBooleanVertexPropertyDefault()
	 * @generated
	 */
	public EAttribute getBooleanVertexPropertyDefault_BooleanValue() {
		return (EAttribute)booleanVertexPropertyDefaultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.FloatVertexPropertyDefault <em>Float Vertex Property Default&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>Float Vertex Property Default&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.FloatVertexPropertyDefault
	 * @generated
	 */
	public EClass getFloatVertexPropertyDefault() {
		return floatVertexPropertyDefaultEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link forsyde.io.trait.hierarchy.FloatVertexPropertyDefault#getFloatValue <em>Float Value&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the attribute '<em>Float Value&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.FloatVertexPropertyDefault#getFloatValue()
	 * @see #getFloatVertexPropertyDefault()
	 * @generated
	 */
	public EAttribute getFloatVertexPropertyDefault_FloatValue() {
		return (EAttribute)floatVertexPropertyDefaultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.DoubleVertexPropertyDefault <em>Double Vertex Property Default&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>Double Vertex Property Default&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.DoubleVertexPropertyDefault
	 * @generated
	 */
	public EClass getDoubleVertexPropertyDefault() {
		return doubleVertexPropertyDefaultEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link forsyde.io.trait.hierarchy.DoubleVertexPropertyDefault#getDoubleValue <em>Double Value&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the attribute '<em>Double Value&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.DoubleVertexPropertyDefault#getDoubleValue()
	 * @see #getDoubleVertexPropertyDefault()
	 * @generated
	 */
	public EAttribute getDoubleVertexPropertyDefault_DoubleValue() {
		return (EAttribute)doubleVertexPropertyDefaultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.LongVertexPropertyDefault <em>Long Vertex Property Default&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>Long Vertex Property Default&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.LongVertexPropertyDefault
	 * @generated
	 */
	public EClass getLongVertexPropertyDefault() {
		return longVertexPropertyDefaultEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link forsyde.io.trait.hierarchy.LongVertexPropertyDefault#getLongValue <em>Long Value&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the attribute '<em>Long Value&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.LongVertexPropertyDefault#getLongValue()
	 * @see #getLongVertexPropertyDefault()
	 * @generated
	 */
	public EAttribute getLongVertexPropertyDefault_LongValue() {
		return (EAttribute)longVertexPropertyDefaultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.ArrayVertexPropertyDefault <em>Array Vertex Property Default&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>Array Vertex Property Default&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.ArrayVertexPropertyDefault
	 * @generated
	 */
	public EClass getArrayVertexPropertyDefault() {
		return arrayVertexPropertyDefaultEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link forsyde.io.trait.hierarchy.ArrayVertexPropertyDefault#getValues <em>Values&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the containment reference list '<em>Values&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.ArrayVertexPropertyDefault#getValues()
	 * @see #getArrayVertexPropertyDefault()
	 * @generated
	 */
	public EReference getArrayVertexPropertyDefault_Values() {
		return (EReference)arrayVertexPropertyDefaultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.IntMapVertexPropertyDefault <em>Int Map Vertex Property Default&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>Int Map Vertex Property Default&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.IntMapVertexPropertyDefault
	 * @generated
	 */
	public EClass getIntMapVertexPropertyDefault() {
		return intMapVertexPropertyDefaultEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link forsyde.io.trait.hierarchy.IntMapVertexPropertyDefault#getValues <em>Values&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the containment reference list '<em>Values&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.IntMapVertexPropertyDefault#getValues()
	 * @see #getIntMapVertexPropertyDefault()
	 * @generated
	 */
	public EReference getIntMapVertexPropertyDefault_Values() {
		return (EReference)intMapVertexPropertyDefaultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the attribute list '{@link forsyde.io.trait.hierarchy.IntMapVertexPropertyDefault#getIndexes <em>Indexes&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the attribute list '<em>Indexes&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.IntMapVertexPropertyDefault#getIndexes()
	 * @see #getIntMapVertexPropertyDefault()
	 * @generated
	 */
	public EAttribute getIntMapVertexPropertyDefault_Indexes() {
		return (EAttribute)intMapVertexPropertyDefaultEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.StringMapVertexPropertyDefault <em>String Map Vertex Property Default&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>String Map Vertex Property Default&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.StringMapVertexPropertyDefault
	 * @generated
	 */
	public EClass getStringMapVertexPropertyDefault() {
		return stringMapVertexPropertyDefaultEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link forsyde.io.trait.hierarchy.StringMapVertexPropertyDefault#getValues <em>Values&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the containment reference list '<em>Values&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.StringMapVertexPropertyDefault#getValues()
	 * @see #getStringMapVertexPropertyDefault()
	 * @generated
	 */
	public EReference getStringMapVertexPropertyDefault_Values() {
		return (EReference)stringMapVertexPropertyDefaultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the attribute list '{@link forsyde.io.trait.hierarchy.StringMapVertexPropertyDefault#getIndexes <em>Indexes&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the attribute list '<em>Indexes&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.StringMapVertexPropertyDefault#getIndexes()
	 * @see #getStringMapVertexPropertyDefault()
	 * @generated
	 */
	public EAttribute getStringMapVertexPropertyDefault_Indexes() {
		return (EAttribute)stringMapVertexPropertyDefaultEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.VertexPropertySpec <em>Vertex Property Spec&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>Vertex Property Spec&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.VertexPropertySpec
	 * @generated
	 */
	public EClass getVertexPropertySpec() {
		return vertexPropertySpecEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link forsyde.io.trait.hierarchy.VertexPropertySpec#getName <em>Name&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the attribute '<em>Name&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.VertexPropertySpec#getName()
	 * @see #getVertexPropertySpec()
	 * @generated
	 */
	public EAttribute getVertexPropertySpec_Name() {
		return (EAttribute)vertexPropertySpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference '{@link forsyde.io.trait.hierarchy.VertexPropertySpec#getPropertyType <em>Property Type&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the containment reference '<em>Property Type&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.VertexPropertySpec#getPropertyType()
	 * @see #getVertexPropertySpec()
	 * @generated
	 */
	public EReference getVertexPropertySpec_PropertyType() {
		return (EReference)vertexPropertySpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the containment reference '{@link forsyde.io.trait.hierarchy.VertexPropertySpec#getPropertyDefault <em>Property Default&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the containment reference '<em>Property Default&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.VertexPropertySpec#getPropertyDefault()
	 * @see #getVertexPropertySpec()
	 * @generated
	 */
	public EReference getVertexPropertySpec_PropertyDefault() {
		return (EReference)vertexPropertySpecEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.VertexTraitSpec <em>Vertex Trait Spec&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>Vertex Trait Spec&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.VertexTraitSpec
	 * @generated
	 */
	public EClass getVertexTraitSpec() {
		return vertexTraitSpecEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link forsyde.io.trait.hierarchy.VertexTraitSpec#getName <em>Name&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the attribute '<em>Name&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.VertexTraitSpec#getName()
	 * @see #getVertexTraitSpec()
	 * @generated
	 */
	public EAttribute getVertexTraitSpec_Name() {
		return (EAttribute)vertexTraitSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the reference list '{@link forsyde.io.trait.hierarchy.VertexTraitSpec#getRefinedTraits <em>Refined Traits&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the reference list '<em>Refined Traits&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.VertexTraitSpec#getRefinedTraits()
	 * @see #getVertexTraitSpec()
	 * @generated
	 */
	public EReference getVertexTraitSpec_RefinedTraits() {
		return (EReference)vertexTraitSpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link forsyde.io.trait.hierarchy.VertexTraitSpec#getRequiredPorts <em>Required Ports&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the containment reference list '<em>Required Ports&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.VertexTraitSpec#getRequiredPorts()
	 * @see #getVertexTraitSpec()
	 * @generated
	 */
	public EReference getVertexTraitSpec_RequiredPorts() {
		return (EReference)vertexTraitSpecEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link forsyde.io.trait.hierarchy.VertexTraitSpec#getRequiredProperties <em>Required Properties&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the containment reference list '<em>Required Properties&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.VertexTraitSpec#getRequiredProperties()
	 * @see #getVertexTraitSpec()
	 * @generated
	 */
	public EReference getVertexTraitSpec_RequiredProperties() {
		return (EReference)vertexTraitSpecEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.EdgeTraitSpec <em>Edge Trait Spec&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>Edge Trait Spec&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.EdgeTraitSpec
	 * @generated
	 */
	public EClass getEdgeTraitSpec() {
		return edgeTraitSpecEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link forsyde.io.trait.hierarchy.EdgeTraitSpec#getName <em>Name&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the attribute '<em>Name&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.EdgeTraitSpec#getName()
	 * @see #getEdgeTraitSpec()
	 * @generated
	 */
	public EAttribute getEdgeTraitSpec_Name() {
		return (EAttribute)edgeTraitSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the reference list '{@link forsyde.io.trait.hierarchy.EdgeTraitSpec#getRefinedTraits <em>Refined Traits&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the reference list '<em>Refined Traits&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.EdgeTraitSpec#getRefinedTraits()
	 * @see #getEdgeTraitSpec()
	 * @generated
	 */
	public EReference getEdgeTraitSpec_RefinedTraits() {
		return (EReference)edgeTraitSpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link forsyde.io.trait.hierarchy.TraitHierarchy <em>Trait Hierarchy&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for class '<em>Trait Hierarchy&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.TraitHierarchy
	 * @generated
	 */
	public EClass getTraitHierarchy() {
		return traitHierarchyEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link forsyde.io.trait.hierarchy.TraitHierarchy#getNamespace <em>Namespace&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the attribute '<em>Namespace&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.TraitHierarchy#getNamespace()
	 * @see #getTraitHierarchy()
	 * @generated
	 */
	public EAttribute getTraitHierarchy_Namespace() {
		return (EAttribute)traitHierarchyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link forsyde.io.trait.hierarchy.TraitHierarchy#getScopedHierarchy <em>Scoped Hierarchy&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the containment reference list '<em>Scoped Hierarchy&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.TraitHierarchy#getScopedHierarchy()
	 * @see #getTraitHierarchy()
	 * @generated
	 */
	public EReference getTraitHierarchy_ScopedHierarchy() {
		return (EReference)traitHierarchyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link forsyde.io.trait.hierarchy.TraitHierarchy#getVertexTraits <em>Vertex Traits&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the containment reference list '<em>Vertex Traits&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.TraitHierarchy#getVertexTraits()
	 * @see #getTraitHierarchy()
	 * @generated
	 */
	public EReference getTraitHierarchy_VertexTraits() {
		return (EReference)traitHierarchyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link forsyde.io.trait.hierarchy.TraitHierarchy#getEdgeTraits <em>Edge Traits&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for the containment reference list '<em>Edge Traits&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.TraitHierarchy#getEdgeTraits()
	 * @see #getTraitHierarchy()
	 * @generated
	 */
	public EReference getTraitHierarchy_EdgeTraits() {
		return (EReference)traitHierarchyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * Returns the meta object for enum '{@link forsyde.io.trait.hierarchy.PortDirectionEnum <em>Port Direction Enum&lt;/em&gt;}'.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the meta object for enum '<em>Port Direction Enum&lt;/em&gt;'.
	 * @see forsyde.io.trait.hierarchy.PortDirectionEnum
	 * @generated
	 */
	public EEnum getPortDirectionEnum() {
		return portDirectionEnumEEnum;
	}

	/**
	 * Returns the factory that creates the instances of the model.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public HierarchyFactory getHierarchyFactory() {
		return (HierarchyFactory)getEFactoryInstance();
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		portSpecEClass = createEClass(PORT_SPEC);
		createEAttribute(portSpecEClass, PORT_SPEC__NAME);
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
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

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
		initEAttribute(getPortSpec_Direction(), this.getPortDirectionEnum(), "direction", null, 0, 1, PortSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
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
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/GenModel&lt;/b&gt;.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
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
