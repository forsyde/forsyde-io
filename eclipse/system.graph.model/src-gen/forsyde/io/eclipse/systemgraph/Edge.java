/**
 */
package forsyde.io.eclipse.systemgraph;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link forsyde.io.eclipse.systemgraph.Edge#getSourceport <em>Sourceport</em>}</li>
 *   <li>{@link forsyde.io.eclipse.systemgraph.Edge#getTargetport <em>Targetport</em>}</li>
 *   <li>{@link forsyde.io.eclipse.systemgraph.Edge#getTraits <em>Traits</em>}</li>
 *   <li>{@link forsyde.io.eclipse.systemgraph.Edge#getSource <em>Source</em>}</li>
 *   <li>{@link forsyde.io.eclipse.systemgraph.Edge#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getEdge()
 * @model
 * @generated
 */
public interface Edge extends EObject {
	/**
	 * Returns the value of the '<em><b>Sourceport</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sourceport</em>' attribute.
	 * @see #setSourceport(String)
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getEdge_Sourceport()
	 * @model unique="false"
	 * @generated
	 */
	String getSourceport();

	/**
	 * Sets the value of the '{@link forsyde.io.eclipse.systemgraph.Edge#getSourceport <em>Sourceport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sourceport</em>' attribute.
	 * @see #getSourceport()
	 * @generated
	 */
	void setSourceport(String value);

	/**
	 * Returns the value of the '<em><b>Targetport</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Targetport</em>' attribute.
	 * @see #setTargetport(String)
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getEdge_Targetport()
	 * @model unique="false"
	 * @generated
	 */
	String getTargetport();

	/**
	 * Sets the value of the '{@link forsyde.io.eclipse.systemgraph.Edge#getTargetport <em>Targetport</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Targetport</em>' attribute.
	 * @see #getTargetport()
	 * @generated
	 */
	void setTargetport(String value);

	/**
	 * Returns the value of the '<em><b>Traits</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Traits</em>' attribute list.
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getEdge_Traits()
	 * @model unique="false"
	 * @generated
	 */
	EList<String> getTraits();

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(Vertex)
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getEdge_Source()
	 * @model
	 * @generated
	 */
	Vertex getSource();

	/**
	 * Sets the value of the '{@link forsyde.io.eclipse.systemgraph.Edge#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Vertex value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Vertex)
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getEdge_Target()
	 * @model
	 * @generated
	 */
	Vertex getTarget();

	/**
	 * Sets the value of the '{@link forsyde.io.eclipse.systemgraph.Edge#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Vertex value);

} // Edge
