/**
 */
package forsyde.io.eclipse.systemgraph;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vertex</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link forsyde.io.eclipse.systemgraph.Vertex#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link forsyde.io.eclipse.systemgraph.Vertex#getTraits <em>Traits</em>}</li>
 *   <li>{@link forsyde.io.eclipse.systemgraph.Vertex#getPorts <em>Ports</em>}</li>
 *   <li>{@link forsyde.io.eclipse.systemgraph.Vertex#getPropertiesValues <em>Properties Values</em>}</li>
 *   <li>{@link forsyde.io.eclipse.systemgraph.Vertex#getPropertiesNames <em>Properties Names</em>}</li>
 * </ul>
 *
 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getVertex()
 * @model
 * @generated
 */
public interface Vertex extends EObject {
	/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see #setIdentifier(String)
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getVertex_Identifier()
	 * @model unique="false" id="true"
	 * @generated
	 */
	String getIdentifier();

	/**
	 * Sets the value of the '{@link forsyde.io.eclipse.systemgraph.Vertex#getIdentifier <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identifier</em>' attribute.
	 * @see #getIdentifier()
	 * @generated
	 */
	void setIdentifier(String value);

	/**
	 * Returns the value of the '<em><b>Traits</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Traits</em>' attribute list.
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getVertex_Traits()
	 * @model unique="false"
	 * @generated
	 */
	EList<String> getTraits();

	/**
	 * Returns the value of the '<em><b>Ports</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ports</em>' attribute list.
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getVertex_Ports()
	 * @model unique="false"
	 * @generated
	 */
	EList<String> getPorts();

	/**
	 * Returns the value of the '<em><b>Properties Values</b></em>' containment reference list.
	 * The list contents are of type {@link forsyde.io.eclipse.systemgraph.VertexProperty}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties Values</em>' containment reference list.
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getVertex_PropertiesValues()
	 * @model containment="true"
	 * @generated
	 */
	EList<VertexProperty> getPropertiesValues();

	/**
	 * Returns the value of the '<em><b>Properties Names</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties Names</em>' attribute list.
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getVertex_PropertiesNames()
	 * @model unique="false"
	 * @generated
	 */
	EList<String> getPropertiesNames();

} // Vertex
