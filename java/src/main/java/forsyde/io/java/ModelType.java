package forsyde.io.java;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author rjordao
 *
 */
public abstract class ModelType {
	
	public abstract String getName();
	
	public abstract Stream<ModelType> getSuperTypes();
	
	public int hashCode() {
		return getName().hashCode();
	}
	
	public boolean equals(ModelType other) {
		return getName().equals(other.getName());
	}
	
	public boolean isRefinement(ModelType other) {
		// if this type equals the other
		if (this.equals(other))
			return true;
		// if any of the super types, recursively, equals the other
		else if (getSuperTypes().anyMatch(s -> s.isRefinement(other))) {
			return true;
		}
		// or no, no equality at all
		else
			return false;
	};

}
