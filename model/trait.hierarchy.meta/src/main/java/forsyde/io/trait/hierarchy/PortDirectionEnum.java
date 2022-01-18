/**
 */
package forsyde.io.trait.hierarchy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Port Direction Enum</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getPortDirectionEnum()
 * @model
 * @generated
 */
public enum PortDirectionEnum implements InternalPortDirectionEnum {
	/**
	 * The '<em><b>FORWARD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FORWARD_VALUE
	 * @generated
	 * @ordered
	 */
	FORWARD(0, "FORWARD", "FORWARD"),

	/**
	 * The '<em><b>BACKWARD</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BACKWARD_VALUE
	 * @generated
	 * @ordered
	 */
	BACKWARD(0, "BACKWARD", "BACKWARD"),

	/**
	 * The '<em><b>BIDIRECTIONAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BIDIRECTIONAL_VALUE
	 * @generated
	 * @ordered
	 */
	BIDIRECTIONAL(0, "BIDIRECTIONAL", "BIDIRECTIONAL");

	/**
	 * The '<em><b>FORWARD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FORWARD
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FORWARD_VALUE = 0;

	/**
	 * The '<em><b>BACKWARD</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BACKWARD
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BACKWARD_VALUE = 0;

	/**
	 * The '<em><b>BIDIRECTIONAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BIDIRECTIONAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BIDIRECTIONAL_VALUE = 0;

	/**
	 * An array of all the '<em><b>Port Direction Enum</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PortDirectionEnum[] VALUES_ARRAY =
		new PortDirectionEnum[] {
			FORWARD,
			BACKWARD,
			BIDIRECTIONAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Port Direction Enum</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<PortDirectionEnum> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Port Direction Enum</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PortDirectionEnum get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PortDirectionEnum result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Port Direction Enum</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PortDirectionEnum getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PortDirectionEnum result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Port Direction Enum</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PortDirectionEnum get(int value) {
		switch (value) {
			case FORWARD_VALUE: return FORWARD;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private PortDirectionEnum(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
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
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //PortDirectionEnum

/**
 * A private implementation interface used to hide the inheritance from Enumerator.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
interface InternalPortDirectionEnum extends org.eclipse.emf.common.util.Enumerator {
	// Empty 
}
