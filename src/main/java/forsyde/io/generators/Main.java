package forsyde.io.generators;

public class Main {

	public static void main(String[] args) {
		try {
			PythonGenerator pythonGenerator = new PythonGenerator();
			HaskellGenerator haskellGenerator = new HaskellGenerator("model/types.ecore", "haskell/src/ForSyDe/IO/Haskell");
			JavaGenerator javaGenerator = new JavaGenerator();
			PrologGenerator prologGenerator = new PrologGenerator();
			SQLGenerator sqlGenerator = new SQLGenerator();
			
			pythonGenerator.generate("model/types.ecore", "python/forsyde/io/python");
			haskellGenerator.generate();
			javaGenerator.generate("model/types.ecore", "java/src/main/java/forsyde/io/java");
			prologGenerator.generate();
			sqlGenerator.generate("model/types.ecore", "python/forsyde/io/python/sql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
