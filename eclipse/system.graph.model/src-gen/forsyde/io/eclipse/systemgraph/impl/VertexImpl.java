/**
 */
package forsyde.io.eclipse.systemgraph.impl;

import forsyde.io.eclipse.systemgraph.SystemGraphPackage;
import forsyde.io.eclipse.systemgraph.Vertex;
import forsyde.io.eclipse.systemgraph.VertexProperty;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Vertex</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link forsyde.io.eclipse.systemgraph.impl.VertexImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link forsyde.io.eclipse.systemgraph.impl.VertexImpl#getTraits <em>Traits</em>}</li>
 *   <li>{@link forsyde.io.eclipse.systemgraph.impl.VertexImpl#getPorts <em>Ports</em>}</li>
 *   <li>{@link forsyde.io.eclipse.systemgraph.impl.VertexImpl#getPropertiesValues <em>Properties Values</em>}</li>
 *   <li>{@link forsyde.io.eclipse.systemgraph.impl.VertexImpl#getPropertiesNames <em>Properties Names</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VertexImpl extends MinimalEObjectImpl.Container implements Vertex {
	/**
	 * The default value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentifier()
	 * @generated
	 * @ordered
	 */
	protected static final String IDENTIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentifier()
	 * @generated
	 * @ordered
	 */
	protected String identifier = IDENTIFIER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTraits() <em>Traits</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTraits()
	 * @generated
	 * @ordered
	 */
	protected EList<String> traits;

	/**
	 * The cached value of the '{@link #getPorts() <em>Ports</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPorts()
	 * @generated
	 * @ordered
	 */
	protected EList<String> ports;

	/**
	 * The cached value of the '{@link #getPropertiesValues() <em>Properties Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertiesValues()
	 * @generated
	 * @ordered
	 */
	protected EList<VertexProperty> propertiesValues;

	/**
	 * The cached value of the '{@link #getPropertiesNames() <em>Properties Names</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertiesNames()
	 * @generated
	 * @ordered
	 */
	protected EList<String> propertiesNames;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VertexImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SystemGraphPackage.Literals.VERTEX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdentifier(String newIdentifier) {
		String oldIdentifier = identifier;
		identifier = newIdentifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SystemGraphPackage.VERTEX__IDENTIFIER, oldIdentifier, identifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getTraits() {
		if (traits == null) {
			traits = new EDataTypeEList<String>(String.class, this, SystemGraphPackage.VERTEX__TRAITS);
		}
		return traits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getPorts() {
		if (ports == null) {
			ports = new EDataTypeEList<String>(String.class, this, SystemGraphPackage.VERTEX__PORTS);
		}
		return ports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VertexProperty> getPropertiesValues() {
		if (propertiesValues == null) {
			propertiesValues = new EObjectContainmentEList<VertexProperty>(VertexProperty.class, this, SystemGraphPackage.VERTEX__PROPERTIES_VALUES);
		}
		return propertiesValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getPropertiesNames() {
		if (propertiesNames == null) {
			propertiesNames = new EDataTypeEList<String>(String.class, this, SystemGraphPackage.VERTEX__PROPERTIES_NAMES);
		}
		return propertiesNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SystemGraphPackage.VERTEX__PROPERTIES_VALUES:
				return ((InternalEList<?>)getPropertiesValues()).basicRemove(otherEnd, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SystemGraphPackage.VERTEX__IDENTIFIER:
				return getIdentifier();
			case SystemGraphPackage.VERTEX__TRAITS:
				return getTraits();
			case SystemGraphPackage.VERTEX__PORTS:
				return getPorts();
			case SystemGraphPackage.VERTEX__PROPERTIES_VALUES:
				return getPropertiesValues();
			case SystemGraphPackage.VERTEX__PROPERTIES_NAMES:
				return getPropertiesNames();
		}
		return eDynamicGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SystemGraphPackage.VERTEX__IDENTIFIER:
				setIdentifier((String)newValue);
				return;
			case SystemGraphPackage.VERTEX__TRAITS:
				getTraits().clear();
				getTraits().addAll((Collection<? extends String>)newValue);
				return;
			case SystemGraphPackage.VERTEX__PORTS:
				getPorts().clear();
				getPorts().addAll((Collection<? extends String>)newValue);
				return;
			case SystemGraphPackage.VERTEX__PROPERTIES_VALUES:
				getPropertiesValues().clear();
				getPropertiesValues().addAll((Collection<? extends VertexProperty>)newValue);
				return;
			case SystemGraphPackage.VERTEX__PROPERTIES_NAMES:
				getPropertiesNames().clear();
				getPropertiesNames().addAll((Collection<? extends String>)newValue);
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
			case SystemGraphPackage.VERTEX__IDENTIFIER:
				setIdentifier(IDENTIFIER_EDEFAULT);
				return;
			case SystemGraphPackage.VERTEX__TRAITS:
				getTraits().clear();
				return;
			case SystemGraphPackage.VERTEX__PORTS:
				getPorts().clear();
				return;
			case SystemGraphPackage.VERTEX__PROPERTIES_VALUES:
				getPropertiesValues().clear();
				return;
			case SystemGraphPackage.VERTEX__PROPERTIES_NAMES:
				getPropertiesNames().clear();
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
			case SystemGraphPackage.VERTEX__IDENTIFIER:
				return IDENTIFIER_EDEFAULT == null ? identifier != null : !IDENTIFIER_EDEFAULT.equals(identifier);
			case SystemGraphPackage.VERTEX__TRAITS:
				return traits != null && !traits.isEmpty();
			case SystemGraphPackage.VERTEX__PORTS:
				return ports != null && !ports.isEmpty();
			case SystemGraphPackage.VERTEX__PROPERTIES_VALUES:
				return propertiesValues != null && !propertiesValues.isEmpty();
			case SystemGraphPackage.VERTEX__PROPERTIES_NAMES:
				return propertiesNames != null && !propertiesNames.isEmpty();
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
		result.append(" (identifier: ");
		result.append(identifier);
		result.append(", traits: ");
		result.append(traits);
		result.append(", ports: ");
		result.append(ports);
		result.append(", propertiesNames: ");
		result.append(propertiesNames);
		result.append(')');
		return result.toString();
	}

} //VertexImpl
