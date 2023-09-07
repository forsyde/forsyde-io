package forsyde.io.core;

public interface Trait {

	boolean refines(Trait other);

	String getName();

}
