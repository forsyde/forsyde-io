/**
 */
package forsyde.io.eclipse.systemgraph.impl;

import forsyde.io.eclipse.systemgraph.ArrayVertexProperty;
import forsyde.io.eclipse.systemgraph.BooleanVertexProperty;
import forsyde.io.eclipse.systemgraph.DoubleVertexProperty;
import forsyde.io.eclipse.systemgraph.Edge;
import forsyde.io.eclipse.systemgraph.FloatVertexProperty;
import forsyde.io.eclipse.systemgraph.ForSyDeSystemGraph;
import forsyde.io.eclipse.systemgraph.IntMapVertexProperty;
import forsyde.io.eclipse.systemgraph.IntVertexProperty;
import forsyde.io.eclipse.systemgraph.LongVertexProperty;
import forsyde.io.eclipse.systemgraph.StringMapVertexProperty;
import forsyde.io.eclipse.systemgraph.StringVertexProperty;
import forsyde.io.eclipse.systemgraph.SystemGraphFactory;
import forsyde.io.eclipse.systemgraph.SystemGraphPackage;
import forsyde.io.eclipse.systemgraph.Vertex;
import forsyde.io.eclipse.systemgraph.VertexProperty;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SystemGraphPackageImpl extends EPackageImpl implements SystemGraphPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vertexPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringVertexPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intVertexPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanVertexPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatVertexPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass doubleVertexPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass longVertexPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrayVertexPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intMapVertexPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringMapVertexPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vertexEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass edgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass forSyDeSystemGraphEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SystemGraphPackageImpl() {
		super(eNS_URI, SystemGraphFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link SystemGraphPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SystemGraphPackage init() {
		if (isInited) return (SystemGraphPackage)EPackage.Registry.INSTANCE.getEPackage(SystemGraphPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredSystemGraphPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		SystemGraphPackageImpl theSystemGraphPackage = registeredSystemGraphPackage instanceof SystemGraphPackageImpl ? (SystemGraphPackageImpl)registeredSystemGraphPackage : new SystemGraphPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theSystemGraphPackage.createPackageContents();

		// Initialize created meta-data
		theSystemGraphPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSystemGraphPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SystemGraphPackage.eNS_URI, theSystemGraphPackage);
		return theSystemGraphPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVertexProperty() {
		return vertexPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringVertexProperty() {
		return stringVertexPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringVertexProperty_String() {
		return (EAttribute)stringVertexPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntVertexProperty() {
		return intVertexPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntVertexProperty_IntValue() {
		return (EAttribute)intVertexPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanVertexProperty() {
		return booleanVertexPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBooleanVertexProperty_BooleanValue() {
		return (EAttribute)booleanVertexPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFloatVertexProperty() {
		return floatVertexPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFloatVertexProperty_FloatValue() {
		return (EAttribute)floatVertexPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDoubleVertexProperty() {
		return doubleVertexPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDoubleVertexProperty_DoubleValue() {
		return (EAttribute)doubleVertexPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLongVertexProperty() {
		return longVertexPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLongVertexProperty_LongValue() {
		return (EAttribute)longVertexPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrayVertexProperty() {
		return arrayVertexPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayVertexProperty_Values() {
		return (EReference)arrayVertexPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntMapVertexProperty() {
		return intMapVertexPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntMapVertexProperty_Values() {
		return (EReference)intMapVertexPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntMapVertexProperty_Indexes() {
		return (EAttribute)intMapVertexPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringMapVertexProperty() {
		return stringMapVertexPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStringMapVertexProperty_Values() {
		return (EReference)stringMapVertexPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringMapVertexProperty_Indexes() {
		return (EAttribute)stringMapVertexPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVertex() {
		return vertexEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVertex_Identifier() {
		return (EAttribute)vertexEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVertex_Traits() {
		return (EAttribute)vertexEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVertex_Ports() {
		return (EAttribute)vertexEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVertex_PropertiesValues() {
		return (EReference)vertexEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVertex_PropertiesNames() {
		return (EAttribute)vertexEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEdge() {
		return edgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEdge_Sourceport() {
		return (EAttribute)edgeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEdge_Targetport() {
		return (EAttribute)edgeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEdge_Traits() {
		return (EAttribute)edgeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEdge_Source() {
		return (EReference)edgeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEdge_Target() {
		return (EReference)edgeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getForSyDeSystemGraph() {
		return forSyDeSystemGraphEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForSyDeSystemGraph_Nodes() {
		return (EReference)forSyDeSystemGraphEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForSyDeSystemGraph_Edges() {
		return (EReference)forSyDeSystemGraphEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemGraphFactory getSystemGraphFactory() {
		return (SystemGraphFactory)getEFactoryInstance();
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
		vertexPropertyEClass = createEClass(VERTEX_PROPERTY);

		stringVertexPropertyEClass = createEClass(STRING_VERTEX_PROPERTY);
		createEAttribute(stringVertexPropertyEClass, STRING_VERTEX_PROPERTY__STRING);

		intVertexPropertyEClass = createEClass(INT_VERTEX_PROPERTY);
		createEAttribute(intVertexPropertyEClass, INT_VERTEX_PROPERTY__INT_VALUE);

		booleanVertexPropertyEClass = createEClass(BOOLEAN_VERTEX_PROPERTY);
		createEAttribute(booleanVertexPropertyEClass, BOOLEAN_VERTEX_PROPERTY__BOOLEAN_VALUE);

		floatVertexPropertyEClass = createEClass(FLOAT_VERTEX_PROPERTY);
		createEAttribute(floatVertexPropertyEClass, FLOAT_VERTEX_PROPERTY__FLOAT_VALUE);

		doubleVertexPropertyEClass = createEClass(DOUBLE_VERTEX_PROPERTY);
		createEAttribute(doubleVertexPropertyEClass, DOUBLE_VERTEX_PROPERTY__DOUBLE_VALUE);

		longVertexPropertyEClass = createEClass(LONG_VERTEX_PROPERTY);
		createEAttribute(longVertexPropertyEClass, LONG_VERTEX_PROPERTY__LONG_VALUE);

		arrayVertexPropertyEClass = createEClass(ARRAY_VERTEX_PROPERTY);
		createEReference(arrayVertexPropertyEClass, ARRAY_VERTEX_PROPERTY__VALUES);

		intMapVertexPropertyEClass = createEClass(INT_MAP_VERTEX_PROPERTY);
		createEReference(intMapVertexPropertyEClass, INT_MAP_VERTEX_PROPERTY__VALUES);
		createEAttribute(intMapVertexPropertyEClass, INT_MAP_VERTEX_PROPERTY__INDEXES);

		stringMapVertexPropertyEClass = createEClass(STRING_MAP_VERTEX_PROPERTY);
		createEReference(stringMapVertexPropertyEClass, STRING_MAP_VERTEX_PROPERTY__VALUES);
		createEAttribute(stringMapVertexPropertyEClass, STRING_MAP_VERTEX_PROPERTY__INDEXES);

		vertexEClass = createEClass(VERTEX);
		createEAttribute(vertexEClass, VERTEX__IDENTIFIER);
		createEAttribute(vertexEClass, VERTEX__TRAITS);
		createEAttribute(vertexEClass, VERTEX__PORTS);
		createEReference(vertexEClass, VERTEX__PROPERTIES_VALUES);
		createEAttribute(vertexEClass, VERTEX__PROPERTIES_NAMES);

		edgeEClass = createEClass(EDGE);
		createEAttribute(edgeEClass, EDGE__SOURCEPORT);
		createEAttribute(edgeEClass, EDGE__TARGETPORT);
		createEAttribute(edgeEClass, EDGE__TRAITS);
		createEReference(edgeEClass, EDGE__SOURCE);
		createEReference(edgeEClass, EDGE__TARGET);

		forSyDeSystemGraphEClass = createEClass(FOR_SY_DE_SYSTEM_GRAPH);
		createEReference(forSyDeSystemGraphEClass, FOR_SY_DE_SYSTEM_GRAPH__NODES);
		createEReference(forSyDeSystemGraphEClass, FOR_SY_DE_SYSTEM_GRAPH__EDGES);
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
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		stringVertexPropertyEClass.getESuperTypes().add(this.getVertexProperty());
		intVertexPropertyEClass.getESuperTypes().add(this.getVertexProperty());
		booleanVertexPropertyEClass.getESuperTypes().add(this.getVertexProperty());
		floatVertexPropertyEClass.getESuperTypes().add(this.getVertexProperty());
		doubleVertexPropertyEClass.getESuperTypes().add(this.getVertexProperty());
		longVertexPropertyEClass.getESuperTypes().add(this.getVertexProperty());
		arrayVertexPropertyEClass.getESuperTypes().add(this.getVertexProperty());
		intMapVertexPropertyEClass.getESuperTypes().add(this.getVertexProperty());
		stringMapVertexPropertyEClass.getESuperTypes().add(this.getIntVertexProperty());

		// Initialize classes, features, and operations; add parameters
		initEClass(vertexPropertyEClass, VertexProperty.class, "VertexProperty", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stringVertexPropertyEClass, StringVertexProperty.class, "StringVertexProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringVertexProperty_String(), theEcorePackage.getEString(), "string", null, 0, 1, StringVertexProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(intVertexPropertyEClass, IntVertexProperty.class, "IntVertexProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntVertexProperty_IntValue(), theEcorePackage.getEInt(), "intValue", null, 0, 1, IntVertexProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(booleanVertexPropertyEClass, BooleanVertexProperty.class, "BooleanVertexProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanVertexProperty_BooleanValue(), theEcorePackage.getEBoolean(), "booleanValue", null, 0, 1, BooleanVertexProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(floatVertexPropertyEClass, FloatVertexProperty.class, "FloatVertexProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFloatVertexProperty_FloatValue(), theEcorePackage.getEFloat(), "floatValue", null, 0, 1, FloatVertexProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(doubleVertexPropertyEClass, DoubleVertexProperty.class, "DoubleVertexProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDoubleVertexProperty_DoubleValue(), theEcorePackage.getEDouble(), "doubleValue", null, 0, 1, DoubleVertexProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(longVertexPropertyEClass, LongVertexProperty.class, "LongVertexProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLongVertexProperty_LongValue(), theEcorePackage.getELong(), "longValue", null, 0, 1, LongVertexProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(arrayVertexPropertyEClass, ArrayVertexProperty.class, "ArrayVertexProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArrayVertexProperty_Values(), this.getVertexProperty(), null, "values", null, 0, -1, ArrayVertexProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(intMapVertexPropertyEClass, IntMapVertexProperty.class, "IntMapVertexProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIntMapVertexProperty_Values(), this.getVertexProperty(), null, "values", null, 0, -1, IntMapVertexProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntMapVertexProperty_Indexes(), theEcorePackage.getEInt(), "indexes", null, 0, -1, IntMapVertexProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringMapVertexPropertyEClass, StringMapVertexProperty.class, "StringMapVertexProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStringMapVertexProperty_Values(), this.getIntVertexProperty(), null, "values", null, 0, -1, StringMapVertexProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStringMapVertexProperty_Indexes(), theEcorePackage.getEString(), "indexes", null, 0, -1, StringMapVertexProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(vertexEClass, Vertex.class, "Vertex", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVertex_Identifier(), theEcorePackage.getEString(), "identifier", null, 0, 1, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVertex_Traits(), theEcorePackage.getEString(), "traits", null, 0, -1, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVertex_Ports(), theEcorePackage.getEString(), "ports", null, 0, -1, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVertex_PropertiesValues(), this.getVertexProperty(), null, "propertiesValues", null, 0, -1, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVertex_PropertiesNames(), theEcorePackage.getEString(), "propertiesNames", null, 0, -1, Vertex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(edgeEClass, Edge.class, "Edge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEdge_Sourceport(), theEcorePackage.getEString(), "sourceport", null, 0, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdge_Targetport(), theEcorePackage.getEString(), "targetport", null, 0, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEdge_Traits(), theEcorePackage.getEString(), "traits", null, 0, -1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEdge_Source(), this.getVertex(), null, "source", null, 0, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEdge_Target(), this.getVertex(), null, "target", null, 0, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(forSyDeSystemGraphEClass, ForSyDeSystemGraph.class, "ForSyDeSystemGraph", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getForSyDeSystemGraph_Nodes(), this.getVertex(), null, "nodes", null, 0, -1, ForSyDeSystemGraph.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getForSyDeSystemGraph_Edges(), this.getEdge(), null, "edges", null, 0, -1, ForSyDeSystemGraph.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //SystemGraphPackageImpl
