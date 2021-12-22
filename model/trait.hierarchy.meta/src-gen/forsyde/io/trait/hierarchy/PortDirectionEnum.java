/**
 */
package forsyde.io.trait.hierarchy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * &lt;!-- begin-user-doc --&gt;
 * A representation of the literals of the enumeration '<em><b>Port Direction Enum</b>&lt;/em&gt;',
 * and utility methods for working with them.
 * &lt;!-- end-user-doc --&gt;
 * @see forsyde.io.trait.hierarchy.impl.HierarchyPackageImpl#getPortDirectionEnum()
 * @model
 * @generated
 */
public enum PortDirectionEnum implements InternalPortDirectionEnum {
	/**
	 * The '<em><b>FORWARD</b>&lt;/em&gt;' literal object.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #FORWARD_VALUE
	 * @generated
	 * @ordered
	 */
	FORWARD(0, "FORWARD", "FORWARD"),

	/**
	 * The '<em><b>BACKWARD</b>&lt;/em&gt;' literal object.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #BACKWARD_VALUE
	 * @generated
	 * @ordered
	 */
	BACKWARD(0, "BACKWARD", "BACKWARD"),

	/**
	 * The '<em><b>BIDIRECTIONAL</b>&lt;/em&gt;' literal object.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #BIDIRECTIONAL_VALUE
	 * @generated
	 * @ordered
	 */
	BIDIRECTIONAL(0, "BIDIRECTIONAL", "BIDIRECTIONAL");

	/**
	 * The '<em><b>FORWARD</b>&lt;/em&gt;' literal value.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #FORWARD
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FORWARD_VALUE = 0;

	/**
	 * The '<em><b>BACKWARD</b>&lt;/em&gt;' literal value.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #BACKWARD
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BACKWARD_VALUE = 0;

	/**
	 * The '<em><b>BIDIRECTIONAL</b>&lt;/em&gt;' literal value.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @see #BIDIRECTIONAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BIDIRECTIONAL_VALUE = 0;

	/**
	 * An array of all the '<em><b>Port Direction Enum</b>&lt;/em&gt;' enumerators.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private static final PortDirectionEnum[] VALUES_ARRAY =
		new PortDirectionEnum[] {
			FORWARD,
			BACKWARD,
			BIDIRECTIONAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Port Direction Enum</b>&lt;/em&gt;' enumerators.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public static final List<PortDirectionEnum> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Port Direction Enum</b>&lt;/em&gt;' literal with the specified literal value.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @param literal the literal.
	 * @return the matching enumerator or null&lt;/code&gt;.
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
	 * Returns the '<em><b>Port Direction Enum</b>&lt;/em&gt;' literal with the specified name.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @param name the name.
	 * @return the matching enumerator or null&lt;/code&gt;.
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
	 * Returns the '<em><b>Port Direction Enum</b>&lt;/em&gt;' literal with the specified integer value.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @param value the integer value.
	 * @return the matching enumerator or null&lt;/code&gt;.
	 * @generated
	 */
	public static PortDirectionEnum get(int value) {
		switch (value) {
			case FORWARD_VALUE: return FORWARD;
		}
		return null;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private final int value;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private final String name;

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	private PortDirectionEnum(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * &lt;!-- begin-user-doc --&gt;
	 * &lt;!-- end-user-doc --&gt;
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //PortDirectionEnum

/**
 * A private implementation interface used to hide the inheritance from Enumerator.
 * &lt;!-- begin-user-doc --&gt;
 * &lt;!-- end-user-doc --&gt;
 * @generated
 */
interface InternalPortDirectionEnum extends org.eclipse.emf.common.util.Enumerator {
	// Empty 
}
