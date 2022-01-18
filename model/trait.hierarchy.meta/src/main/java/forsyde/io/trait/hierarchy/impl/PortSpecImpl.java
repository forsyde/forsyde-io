/**
 */
package forsyde.io.trait.hierarchy.impl;

import forsyde.io.trait.hierarchy.EdgeTraitSpec;
import forsyde.io.trait.hierarchy.PortDirectionEnum;
import forsyde.io.trait.hierarchy.PortSpec;
import forsyde.io.trait.hierarchy.VertexTraitSpec;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link PortSpecImpl#getName <em>Name</em>}</li>
 *   <li>{@link PortSpecImpl#isMultiple <em>Multiple</em>}</li>
 *   <li>{@link PortSpecImpl#isOrdered <em>Ordered</em>}</li>
 *   <li>{@link PortSpecImpl#getDirection <em>Direction</em>}</li>
 *   <li>{@link PortSpecImpl#getConnectedVertexTrait <em>Connected Vertex Trait</em>}</li>
 *   <li>{@link PortSpecImpl#getConnectedEdgeTrait <em>Connected Edge Trait</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PortSpecImpl extends MinimalEObjectImpl.Container implements PortSpec {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isMultiple() <em>Multiple</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMultiple()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MULTIPLE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isMultiple() <em>Multiple</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMultiple()
	 * @generated
	 * @ordered
	 */
	protected boolean multiple = MULTIPLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isOrdered() <em>Ordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOrdered()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ORDERED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOrdered() <em>Ordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOrdered()
	 * @generated
	 * @ordered
	 */
	protected boolean ordered = ORDERED_EDEFAULT;

	/**
	 * The default value of the '{@link #getDirection() <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected static final PortDirectionEnum DIRECTION_EDEFAULT = PortDirectionEnum.BIDIRECTIONAL;

	/**
	 * The cached value of the '{@link #getDirection() <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected PortDirectionEnum direction = DIRECTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getConnectedVertexTrait() <em>Connected Vertex Trait</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectedVertexTrait()
	 * @generated
	 * @ordered
	 */
	protected VertexTraitSpec connectedVertexTrait;

	/**
	 * The cached value of the '{@link #getConnectedEdgeTrait() <em>Connected Edge Trait</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectedEdgeTrait()
	 * @generated
	 * @ordered
	 */
	protected EdgeTraitSpec connectedEdgeTrait;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PortSpecImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HierarchyPackageImpl.eINSTANCE.getPortSpec();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMultiple() {
		return multiple;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMultiple(boolean newMultiple) {
		multiple = newMultiple;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOrdered() {
		return ordered;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrdered(boolean newOrdered) {
		ordered = newOrdered;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortDirectionEnum getDirection() {
		return direction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDirection(PortDirectionEnum newDirection) {
		direction = newDirection == null ? DIRECTION_EDEFAULT : newDirection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VertexTraitSpec getConnectedVertexTrait() {
		if (connectedVertexTrait != null && connectedVertexTrait.eIsProxy()) {
			InternalEObject oldConnectedVertexTrait = (InternalEObject)connectedVertexTrait;
			connectedVertexTrait = (VertexTraitSpec)eResolveProxy(oldConnectedVertexTrait);
			if (connectedVertexTrait != oldConnectedVertexTrait) {
			}
		}
		return connectedVertexTrait;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VertexTraitSpec basicGetConnectedVertexTrait() {
		return connectedVertexTrait;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectedVertexTrait(VertexTraitSpec newConnectedVertexTrait) {
		connectedVertexTrait = newConnectedVertexTrait;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeTraitSpec getConnectedEdgeTrait() {
		if (connectedEdgeTrait != null && connectedEdgeTrait.eIsProxy()) {
			InternalEObject oldConnectedEdgeTrait = (InternalEObject)connectedEdgeTrait;
			connectedEdgeTrait = (EdgeTraitSpec)eResolveProxy(oldConnectedEdgeTrait);
			if (connectedEdgeTrait != oldConnectedEdgeTrait) {
			}
		}
		return connectedEdgeTrait;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeTraitSpec basicGetConnectedEdgeTrait() {
		return connectedEdgeTrait;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectedEdgeTrait(EdgeTraitSpec newConnectedEdgeTrait) {
		connectedEdgeTrait = newConnectedEdgeTrait;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case HierarchyPackageImpl.PORT_SPEC__NAME:
				return getName();
			case HierarchyPackageImpl.PORT_SPEC__MULTIPLE:
				return isMultiple();
			case HierarchyPackageImpl.PORT_SPEC__ORDERED:
				return isOrdered();
			case HierarchyPackageImpl.PORT_SPEC__DIRECTION:
				return getDirection();
			case HierarchyPackageImpl.PORT_SPEC__CONNECTED_VERTEX_TRAIT:
				if (resolve) return getConnectedVertexTrait();
				return basicGetConnectedVertexTrait();
			case HierarchyPackageImpl.PORT_SPEC__CONNECTED_EDGE_TRAIT:
				if (resolve) return getConnectedEdgeTrait();
				return basicGetConnectedEdgeTrait();
		}
		return eDynamicGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case HierarchyPackageImpl.PORT_SPEC__NAME:
				setName((String)newValue);
				return;
			case HierarchyPackageImpl.PORT_SPEC__MULTIPLE:
				setMultiple((Boolean)newValue);
				return;
			case HierarchyPackageImpl.PORT_SPEC__ORDERED:
				setOrdered((Boolean)newValue);
				return;
			case HierarchyPackageImpl.PORT_SPEC__DIRECTION:
				setDirection((PortDirectionEnum)newValue);
				return;
			case HierarchyPackageImpl.PORT_SPEC__CONNECTED_VERTEX_TRAIT:
				setConnectedVertexTrait((VertexTraitSpec)newValue);
				return;
			case HierarchyPackageImpl.PORT_SPEC__CONNECTED_EDGE_TRAIT:
				setConnectedEdgeTrait((EdgeTraitSpec)newValue);
				return;
		}
		eDynamicSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case HierarchyPackageImpl.PORT_SPEC__NAME:
				setName(NAME_EDEFAULT);
				return;
			case HierarchyPackageImpl.PORT_SPEC__MULTIPLE:
				setMultiple(MULTIPLE_EDEFAULT);
				return;
			case HierarchyPackageImpl.PORT_SPEC__ORDERED:
				setOrdered(ORDERED_EDEFAULT);
				return;
			case HierarchyPackageImpl.PORT_SPEC__DIRECTION:
				setDirection(DIRECTION_EDEFAULT);
				return;
			case HierarchyPackageImpl.PORT_SPEC__CONNECTED_VERTEX_TRAIT:
				setConnectedVertexTrait((VertexTraitSpec)null);
				return;
			case HierarchyPackageImpl.PORT_SPEC__CONNECTED_EDGE_TRAIT:
				setConnectedEdgeTrait((EdgeTraitSpec)null);
				return;
		}
		eDynamicUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case HierarchyPackageImpl.PORT_SPEC__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case HierarchyPackageImpl.PORT_SPEC__MULTIPLE:
				return multiple != MULTIPLE_EDEFAULT;
			case HierarchyPackageImpl.PORT_SPEC__ORDERED:
				return ordered != ORDERED_EDEFAULT;
			case HierarchyPackageImpl.PORT_SPEC__DIRECTION:
				return direction != DIRECTION_EDEFAULT;
			case HierarchyPackageImpl.PORT_SPEC__CONNECTED_VERTEX_TRAIT:
				return connectedVertexTrait != null;
			case HierarchyPackageImpl.PORT_SPEC__CONNECTED_EDGE_TRAIT:
				return connectedEdgeTrait != null;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", multiple: ");
		result.append(multiple);
		result.append(", ordered: ");
		result.append(ordered);
		result.append(", direction: ");
		result.append(direction);
		result.append(')');
		return result.toString();
	}

} //PortSpecImpl
