/**
 */
package forsyde.io.eclipse.systemgraph;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage
 * @generated
 */
public interface SystemGraphFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SystemGraphFactory eINSTANCE = forsyde.io.eclipse.systemgraph.impl.SystemGraphFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>String Vertex Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Vertex Property</em>'.
	 * @generated
	 */
	StringVertexProperty createStringVertexProperty();

	/**
	 * Returns a new object of class '<em>Int Vertex Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Int Vertex Property</em>'.
	 * @generated
	 */
	IntVertexProperty createIntVertexProperty();

	/**
	 * Returns a new object of class '<em>Boolean Vertex Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Vertex Property</em>'.
	 * @generated
	 */
	BooleanVertexProperty createBooleanVertexProperty();

	/**
	 * Returns a new object of class '<em>Float Vertex Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Float Vertex Property</em>'.
	 * @generated
	 */
	FloatVertexProperty createFloatVertexProperty();

	/**
	 * Returns a new object of class '<em>Double Vertex Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Double Vertex Property</em>'.
	 * @generated
	 */
	DoubleVertexProperty createDoubleVertexProperty();

	/**
	 * Returns a new object of class '<em>Long Vertex Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Long Vertex Property</em>'.
	 * @generated
	 */
	LongVertexProperty createLongVertexProperty();

	/**
	 * Returns a new object of class '<em>Array Vertex Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Array Vertex Property</em>'.
	 * @generated
	 */
	ArrayVertexProperty createArrayVertexProperty();

	/**
	 * Returns a new object of class '<em>Int Map Vertex Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Int Map Vertex Property</em>'.
	 * @generated
	 */
	IntMapVertexProperty createIntMapVertexProperty();

	/**
	 * Returns a new object of class '<em>String Map Vertex Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Map Vertex Property</em>'.
	 * @generated
	 */
	StringMapVertexProperty createStringMapVertexProperty();

	/**
	 * Returns a new object of class '<em>Vertex</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Vertex</em>'.
	 * @generated
	 */
	Vertex createVertex();

	/**
	 * Returns a new object of class '<em>Edge</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Edge</em>'.
	 * @generated
	 */
	Edge createEdge();

	/**
	 * Returns a new object of class '<em>For Sy De System Graph</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>For Sy De System Graph</em>'.
	 * @generated
	 */
	ForSyDeSystemGraph createForSyDeSystemGraph();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SystemGraphPackage getSystemGraphPackage();

} //SystemGraphFactory
