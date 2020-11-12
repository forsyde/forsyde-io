package forsyde.io.generators;

import java.io.IOException;

public interface TypesGenerator {
	
	public void generate (
		String typeSec,
		String typeDst
	) throws IOException;

}
