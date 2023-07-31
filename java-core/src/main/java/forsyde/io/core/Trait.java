package forsyde.io.core;

public interface Trait {

	public boolean refines(Trait other);

	public String getName();

}
