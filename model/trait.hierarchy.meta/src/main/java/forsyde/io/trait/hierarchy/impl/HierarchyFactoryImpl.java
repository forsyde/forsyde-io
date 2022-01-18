/**
 */
package forsyde.io.trait.hierarchy.impl;

import forsyde.io.trait.hierarchy.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
public class HierarchyFactoryImpl extends EFactoryImpl implements HierarchyFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final HierarchyFactoryImpl eINSTANCE = init();

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static HierarchyFactoryImpl init() {
		try {
			HierarchyFactoryImpl theHierarchyFactory = (HierarchyFactoryImpl)EPackage.Registry.INSTANCE.getEFactory(HierarchyPackageImpl.eNS_URI);
			if (theHierarchyFactory != null) {
				return theHierarchyFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new HierarchyFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HierarchyFactoryImpl() {
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
			case HierarchyPackageImpl.PORT_SPEC: return createPortSpec();
			case HierarchyPackageImpl.STRING_VERTEX_PROPERTY_TYPE: return createStringVertexPropertyType();
			case HierarchyPackageImpl.INT_VERTEX_PROPERTY_TYPE: return createIntVertexPropertyType();
			case HierarchyPackageImpl.BOOLEAN_VERTEX_PROPERTY_TYPE: return createBooleanVertexPropertyType();
			case HierarchyPackageImpl.FLOAT_VERTEX_PROPERTY_TYPE: return createFloatVertexPropertyType();
			case HierarchyPackageImpl.DOUBLE_VERTEX_PROPERTY_TYPE: return createDoubleVertexPropertyType();
			case HierarchyPackageImpl.LONG_VERTEX_PROPERTY_TYPE: return createLongVertexPropertyType();
			case HierarchyPackageImpl.ARRAY_VERTEX_PROPERTY_TYPE: return createArrayVertexPropertyType();
			case HierarchyPackageImpl.INT_MAP_VERTEX_PROPERTY_TYPE: return createIntMapVertexPropertyType();
			case HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_TYPE: return createStringMapVertexPropertyType();
			case HierarchyPackageImpl.STRING_VERTEX_PROPERTY_DEFAULT: return createStringVertexPropertyDefault();
			case HierarchyPackageImpl.INT_VERTEX_PROPERTY_DEFAULT: return createIntVertexPropertyDefault();
			case HierarchyPackageImpl.BOOLEAN_VERTEX_PROPERTY_DEFAULT: return createBooleanVertexPropertyDefault();
			case HierarchyPackageImpl.FLOAT_VERTEX_PROPERTY_DEFAULT: return createFloatVertexPropertyDefault();
			case HierarchyPackageImpl.DOUBLE_VERTEX_PROPERTY_DEFAULT: return createDoubleVertexPropertyDefault();
			case HierarchyPackageImpl.LONG_VERTEX_PROPERTY_DEFAULT: return createLongVertexPropertyDefault();
			case HierarchyPackageImpl.ARRAY_VERTEX_PROPERTY_DEFAULT: return createArrayVertexPropertyDefault();
			case HierarchyPackageImpl.INT_MAP_VERTEX_PROPERTY_DEFAULT: return createIntMapVertexPropertyDefault();
			case HierarchyPackageImpl.STRING_MAP_VERTEX_PROPERTY_DEFAULT: return createStringMapVertexPropertyDefault();
			case HierarchyPackageImpl.VERTEX_PROPERTY_SPEC: return createVertexPropertySpec();
			case HierarchyPackageImpl.VERTEX_TRAIT_SPEC: return createVertexTraitSpec();
			case HierarchyPackageImpl.EDGE_TRAIT_SPEC: return createEdgeTraitSpec();
			case HierarchyPackageImpl.TRAIT_HIERARCHY: return createTraitHierarchy();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case HierarchyPackageImpl.PORT_DIRECTION_ENUM:
				return createPortDirectionEnumFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case HierarchyPackageImpl.PORT_DIRECTION_ENUM:
				return convertPortDirectionEnumToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortSpec createPortSpec() {
		PortSpecImpl portSpec = new PortSpecImpl();
		return portSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringVertexPropertyType createStringVertexPropertyType() {
		StringVertexPropertyTypeImpl stringVertexPropertyType = new StringVertexPropertyTypeImpl();
		return stringVertexPropertyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntVertexPropertyType createIntVertexPropertyType() {
		IntVertexPropertyTypeImpl intVertexPropertyType = new IntVertexPropertyTypeImpl();
		return intVertexPropertyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanVertexPropertyType createBooleanVertexPropertyType() {
		BooleanVertexPropertyTypeImpl booleanVertexPropertyType = new BooleanVertexPropertyTypeImpl();
		return booleanVertexPropertyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FloatVertexPropertyType createFloatVertexPropertyType() {
		FloatVertexPropertyTypeImpl floatVertexPropertyType = new FloatVertexPropertyTypeImpl();
		return floatVertexPropertyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DoubleVertexPropertyType createDoubleVertexPropertyType() {
		DoubleVertexPropertyTypeImpl doubleVertexPropertyType = new DoubleVertexPropertyTypeImpl();
		return doubleVertexPropertyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LongVertexPropertyType createLongVertexPropertyType() {
		LongVertexPropertyTypeImpl longVertexPropertyType = new LongVertexPropertyTypeImpl();
		return longVertexPropertyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayVertexPropertyType createArrayVertexPropertyType() {
		ArrayVertexPropertyTypeImpl arrayVertexPropertyType = new ArrayVertexPropertyTypeImpl();
		return arrayVertexPropertyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntMapVertexPropertyType createIntMapVertexPropertyType() {
		IntMapVertexPropertyTypeImpl intMapVertexPropertyType = new IntMapVertexPropertyTypeImpl();
		return intMapVertexPropertyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringMapVertexPropertyType createStringMapVertexPropertyType() {
		StringMapVertexPropertyTypeImpl stringMapVertexPropertyType = new StringMapVertexPropertyTypeImpl();
		return stringMapVertexPropertyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringVertexPropertyDefault createStringVertexPropertyDefault() {
		StringVertexPropertyDefaultImpl stringVertexPropertyDefault = new StringVertexPropertyDefaultImpl();
		return stringVertexPropertyDefault;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntVertexPropertyDefault createIntVertexPropertyDefault() {
		IntVertexPropertyDefaultImpl intVertexPropertyDefault = new IntVertexPropertyDefaultImpl();
		return intVertexPropertyDefault;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanVertexPropertyDefault createBooleanVertexPropertyDefault() {
		BooleanVertexPropertyDefaultImpl booleanVertexPropertyDefault = new BooleanVertexPropertyDefaultImpl();
		return booleanVertexPropertyDefault;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FloatVertexPropertyDefault createFloatVertexPropertyDefault() {
		FloatVertexPropertyDefaultImpl floatVertexPropertyDefault = new FloatVertexPropertyDefaultImpl();
		return floatVertexPropertyDefault;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DoubleVertexPropertyDefault createDoubleVertexPropertyDefault() {
		DoubleVertexPropertyDefaultImpl doubleVertexPropertyDefault = new DoubleVertexPropertyDefaultImpl();
		return doubleVertexPropertyDefault;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LongVertexPropertyDefault createLongVertexPropertyDefault() {
		LongVertexPropertyDefaultImpl longVertexPropertyDefault = new LongVertexPropertyDefaultImpl();
		return longVertexPropertyDefault;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayVertexPropertyDefault createArrayVertexPropertyDefault() {
		ArrayVertexPropertyDefaultImpl arrayVertexPropertyDefault = new ArrayVertexPropertyDefaultImpl();
		return arrayVertexPropertyDefault;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntMapVertexPropertyDefault createIntMapVertexPropertyDefault() {
		IntMapVertexPropertyDefaultImpl intMapVertexPropertyDefault = new IntMapVertexPropertyDefaultImpl();
		return intMapVertexPropertyDefault;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringMapVertexPropertyDefault createStringMapVertexPropertyDefault() {
		StringMapVertexPropertyDefaultImpl stringMapVertexPropertyDefault = new StringMapVertexPropertyDefaultImpl();
		return stringMapVertexPropertyDefault;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VertexPropertySpec createVertexPropertySpec() {
		VertexPropertySpecImpl vertexPropertySpec = new VertexPropertySpecImpl();
		return vertexPropertySpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VertexTraitSpec createVertexTraitSpec() {
		VertexTraitSpecImpl vertexTraitSpec = new VertexTraitSpecImpl();
		return vertexTraitSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeTraitSpec createEdgeTraitSpec() {
		EdgeTraitSpecImpl edgeTraitSpec = new EdgeTraitSpecImpl();
		return edgeTraitSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TraitHierarchy createTraitHierarchy() {
		TraitHierarchyImpl traitHierarchy = new TraitHierarchyImpl();
		return traitHierarchy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortDirectionEnum createPortDirectionEnumFromString(EDataType eDataType, String initialValue) {
		PortDirectionEnum result = PortDirectionEnum.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPortDirectionEnumToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HierarchyPackageImpl getHierarchyPackage() {
		return (HierarchyPackageImpl)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static HierarchyPackageImpl getPackage() {
		return HierarchyPackageImpl.eINSTANCE;
	}

} //HierarchyFactoryImpl
