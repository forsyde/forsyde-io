/**
 */
package forsyde.io.eclipse.systemgraph;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>For Sy De System Graph</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link forsyde.io.eclipse.systemgraph.ForSyDeSystemGraph#getNodes <em>Nodes</em>}</li>
 *   <li>{@link forsyde.io.eclipse.systemgraph.ForSyDeSystemGraph#getEdges <em>Edges</em>}</li>
 * </ul>
 *
 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getForSyDeSystemGraph()
 * @model
 * @generated
 */
public interface ForSyDeSystemGraph extends EObject {
	/**
	 * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
	 * The list contents are of type {@link forsyde.io.eclipse.systemgraph.Vertex}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodes</em>' containment reference list.
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getForSyDeSystemGraph_Nodes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Vertex> getNodes();

	/**
	 * Returns the value of the '<em><b>Edges</b></em>' containment reference list.
	 * The list contents are of type {@link forsyde.io.eclipse.systemgraph.Edge}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edges</em>' containment reference list.
	 * @see forsyde.io.eclipse.systemgraph.SystemGraphPackage#getForSyDeSystemGraph_Edges()
	 * @model containment="true"
	 * @generated
	 */
	EList<Edge> getEdges();

} // ForSyDeSystemGraph
