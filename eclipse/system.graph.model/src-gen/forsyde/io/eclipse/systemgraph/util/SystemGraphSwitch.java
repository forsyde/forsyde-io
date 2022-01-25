/**
 */
package forsyde.io.eclipse.systemgraph.util;

import forsyde.io.eclipse.systemgraph.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage
 * @generated
 */
public class SystemGraphSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SystemGraphPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemGraphSwitch() {
		if (modelPackage == null) {
			modelPackage = SystemGraphPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case SystemGraphPackage.VERTEX_PROPERTY: {
				VertexProperty vertexProperty = (VertexProperty)theEObject;
				T result = caseVertexProperty(vertexProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SystemGraphPackage.STRING_VERTEX_PROPERTY: {
				StringVertexProperty stringVertexProperty = (StringVertexProperty)theEObject;
				T result = caseStringVertexProperty(stringVertexProperty);
				if (result == null) result = caseVertexProperty(stringVertexProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SystemGraphPackage.INT_VERTEX_PROPERTY: {
				IntVertexProperty intVertexProperty = (IntVertexProperty)theEObject;
				T result = caseIntVertexProperty(intVertexProperty);
				if (result == null) result = caseVertexProperty(intVertexProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SystemGraphPackage.BOOLEAN_VERTEX_PROPERTY: {
				BooleanVertexProperty booleanVertexProperty = (BooleanVertexProperty)theEObject;
				T result = caseBooleanVertexProperty(booleanVertexProperty);
				if (result == null) result = caseVertexProperty(booleanVertexProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SystemGraphPackage.FLOAT_VERTEX_PROPERTY: {
				FloatVertexProperty floatVertexProperty = (FloatVertexProperty)theEObject;
				T result = caseFloatVertexProperty(floatVertexProperty);
				if (result == null) result = caseVertexProperty(floatVertexProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SystemGraphPackage.DOUBLE_VERTEX_PROPERTY: {
				DoubleVertexProperty doubleVertexProperty = (DoubleVertexProperty)theEObject;
				T result = caseDoubleVertexProperty(doubleVertexProperty);
				if (result == null) result = caseVertexProperty(doubleVertexProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SystemGraphPackage.LONG_VERTEX_PROPERTY: {
				LongVertexProperty longVertexProperty = (LongVertexProperty)theEObject;
				T result = caseLongVertexProperty(longVertexProperty);
				if (result == null) result = caseVertexProperty(longVertexProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SystemGraphPackage.ARRAY_VERTEX_PROPERTY: {
				ArrayVertexProperty arrayVertexProperty = (ArrayVertexProperty)theEObject;
				T result = caseArrayVertexProperty(arrayVertexProperty);
				if (result == null) result = caseVertexProperty(arrayVertexProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SystemGraphPackage.INT_MAP_VERTEX_PROPERTY: {
				IntMapVertexProperty intMapVertexProperty = (IntMapVertexProperty)theEObject;
				T result = caseIntMapVertexProperty(intMapVertexProperty);
				if (result == null) result = caseVertexProperty(intMapVertexProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SystemGraphPackage.STRING_MAP_VERTEX_PROPERTY: {
				StringMapVertexProperty stringMapVertexProperty = (StringMapVertexProperty)theEObject;
				T result = caseStringMapVertexProperty(stringMapVertexProperty);
				if (result == null) result = caseIntVertexProperty(stringMapVertexProperty);
				if (result == null) result = caseVertexProperty(stringMapVertexProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SystemGraphPackage.VERTEX: {
				Vertex vertex = (Vertex)theEObject;
				T result = caseVertex(vertex);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SystemGraphPackage.EDGE: {
				Edge edge = (Edge)theEObject;
				T result = caseEdge(edge);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SystemGraphPackage.FOR_SY_DE_SYSTEM_GRAPH: {
				ForSyDeSystemGraph forSyDeSystemGraph = (ForSyDeSystemGraph)theEObject;
				T result = caseForSyDeSystemGraph(forSyDeSystemGraph);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Vertex Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Vertex Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVertexProperty(VertexProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Vertex Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Vertex Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringVertexProperty(StringVertexProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Int Vertex Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Int Vertex Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntVertexProperty(IntVertexProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean Vertex Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean Vertex Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBooleanVertexProperty(BooleanVertexProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Float Vertex Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Float Vertex Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFloatVertexProperty(FloatVertexProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Double Vertex Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Double Vertex Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDoubleVertexProperty(DoubleVertexProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Long Vertex Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Long Vertex Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLongVertexProperty(LongVertexProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Array Vertex Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Array Vertex Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArrayVertexProperty(ArrayVertexProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Int Map Vertex Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Int Map Vertex Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntMapVertexProperty(IntMapVertexProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Map Vertex Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Map Vertex Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringMapVertexProperty(StringMapVertexProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Vertex</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Vertex</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVertex(Vertex object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Edge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Edge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEdge(Edge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>For Sy De System Graph</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>For Sy De System Graph</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseForSyDeSystemGraph(ForSyDeSystemGraph object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //SystemGraphSwitch
