package forsyde.io.java.core;

public interface Trait {

	public boolean refines(Trait other);

	public String getName();

}
