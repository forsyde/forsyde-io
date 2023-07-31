/**
 */
package forsyde.io.eclipse.systemgraph.util;

import forsyde.io.eclipse.systemgraph.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage
 * @generated
 */
public class SystemGraphAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SystemGraphPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemGraphAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = SystemGraphPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SystemGraphSwitch<Adapter> modelSwitch =
		new SystemGraphSwitch<Adapter>() {
			@Override
			public Adapter caseVertexProperty(VertexProperty object) {
				return createVertexPropertyAdapter();
			}
			@Override
			public Adapter caseStringVertexProperty(StringVertexProperty object) {
				return createStringVertexPropertyAdapter();
			}
			@Override
			public Adapter caseIntVertexProperty(IntVertexProperty object) {
				return createIntVertexPropertyAdapter();
			}
			@Override
			public Adapter caseBooleanVertexProperty(BooleanVertexProperty object) {
				return createBooleanVertexPropertyAdapter();
			}
			@Override
			public Adapter caseFloatVertexProperty(FloatVertexProperty object) {
				return createFloatVertexPropertyAdapter();
			}
			@Override
			public Adapter caseDoubleVertexProperty(DoubleVertexProperty object) {
				return createDoubleVertexPropertyAdapter();
			}
			@Override
			public Adapter caseLongVertexProperty(LongVertexProperty object) {
				return createLongVertexPropertyAdapter();
			}
			@Override
			public Adapter caseArrayVertexProperty(ArrayVertexProperty object) {
				return createArrayVertexPropertyAdapter();
			}
			@Override
			public Adapter caseIntMapVertexProperty(IntMapVertexProperty object) {
				return createIntMapVertexPropertyAdapter();
			}
			@Override
			public Adapter caseStringMapVertexProperty(StringMapVertexProperty object) {
				return createStringMapVertexPropertyAdapter();
			}
			@Override
			public Adapter caseVertex(Vertex object) {
				return createVertexAdapter();
			}
			@Override
			public Adapter caseEdge(Edge object) {
				return createEdgeAdapter();
			}
			@Override
			public Adapter caseForSyDeSystemGraph(ForSyDeSystemGraph object) {
				return createForSyDeSystemGraphAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link forsyde.io.eclipse.systemgraph.VertexProperty <em>Vertex Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see forsyde.io.eclipse.systemgraph.VertexProperty
	 * @generated
	 */
	public Adapter createVertexPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link forsyde.io.eclipse.systemgraph.StringVertexProperty <em>String Vertex Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see forsyde.io.eclipse.systemgraph.StringVertexProperty
	 * @generated
	 */
	public Adapter createStringVertexPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link forsyde.io.eclipse.systemgraph.IntVertexProperty <em>Int Vertex Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see forsyde.io.eclipse.systemgraph.IntVertexProperty
	 * @generated
	 */
	public Adapter createIntVertexPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link forsyde.io.eclipse.systemgraph.BooleanVertexProperty <em>Boolean Vertex Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see forsyde.io.eclipse.systemgraph.BooleanVertexProperty
	 * @generated
	 */
	public Adapter createBooleanVertexPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link forsyde.io.eclipse.systemgraph.FloatVertexProperty <em>Float Vertex Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see forsyde.io.eclipse.systemgraph.FloatVertexProperty
	 * @generated
	 */
	public Adapter createFloatVertexPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link forsyde.io.eclipse.systemgraph.DoubleVertexProperty <em>Double Vertex Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see forsyde.io.eclipse.systemgraph.DoubleVertexProperty
	 * @generated
	 */
	public Adapter createDoubleVertexPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link forsyde.io.eclipse.systemgraph.LongVertexProperty <em>Long Vertex Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see forsyde.io.eclipse.systemgraph.LongVertexProperty
	 * @generated
	 */
	public Adapter createLongVertexPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link forsyde.io.eclipse.systemgraph.ArrayVertexProperty <em>Array Vertex Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see forsyde.io.eclipse.systemgraph.ArrayVertexProperty
	 * @generated
	 */
	public Adapter createArrayVertexPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link forsyde.io.eclipse.systemgraph.IntMapVertexProperty <em>Int Map Vertex Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see forsyde.io.eclipse.systemgraph.IntMapVertexProperty
	 * @generated
	 */
	public Adapter createIntMapVertexPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link forsyde.io.eclipse.systemgraph.StringMapVertexProperty <em>String Map Vertex Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see forsyde.io.eclipse.systemgraph.StringMapVertexProperty
	 * @generated
	 */
	public Adapter createStringMapVertexPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link forsyde.io.eclipse.systemgraph.Vertex <em>Vertex</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see forsyde.io.eclipse.systemgraph.Vertex
	 * @generated
	 */
	public Adapter createVertexAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link forsyde.io.eclipse.systemgraph.Edge <em>Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see forsyde.io.eclipse.systemgraph.Edge
	 * @generated
	 */
	public Adapter createEdgeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link forsyde.io.eclipse.systemgraph.ForSyDeSystemGraph <em>For Sy De System Graph</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see forsyde.io.eclipse.systemgraph.ForSyDeSystemGraph
	 * @generated
	 */
	public Adapter createForSyDeSystemGraphAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //SystemGraphAdapterFactory
