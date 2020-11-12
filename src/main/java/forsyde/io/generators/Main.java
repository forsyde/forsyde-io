package forsyde.io.generators;

import java.io.File;

import org.eclipse.epsilon.egl.EglFileGeneratingTemplateFactory;
import org.eclipse.epsilon.egl.EgxModule;
import org.eclipse.epsilon.emc.emf.EmfModel;

public class Main {

	public static void main(String[] args) {
		try {
			PythonGenerator pythonGenerator = new PythonGenerator("ecore/types.ecore", "python/forsyde/io/python");
			HaskellGenerator haskellGenerator = new HaskellGenerator("ecore/types.ecore", "haskell/src/ForSyDe/IO/Haskell");
			JavaGenerator javaGenerator = new JavaGenerator();
			PrologGenerator prologGenerator = new PrologGenerator();
			SQLGenerator sqlGenerator = new SQLGenerator();
			
			pythonGenerator.generate();
			haskellGenerator.generate();
			javaGenerator.generate("ecore/types.ecore", "java/src/main/java/forsyde/io/java");
			prologGenerator.generate();
			sqlGenerator.generate("ecore/types.ecore", "sql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
