package forsyde.io.generators;

import java.io.File;

import org.eclipse.epsilon.egl.EglFileGeneratingTemplateFactory;
import org.eclipse.epsilon.egl.EgxModule;
import org.eclipse.epsilon.emc.emf.EmfModel;

public class Main {

	public static void main(String[] args) {
		try {
			PythonGenerator pythonGenerator = new PythonGenerator();
			HaskellGenerator haskellGenerator = new HaskellGenerator();
			JavaGenerator javaGenerator = new JavaGenerator();
			
			pythonGenerator.generate();
			haskellGenerator.generate();
			javaGenerator.generate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
