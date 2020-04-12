package forsyde.schema.generators;

import java.io.File;

import org.eclipse.epsilon.egl.EglFileGeneratingTemplateFactory;
import org.eclipse.epsilon.egl.EgxModule;
import org.eclipse.epsilon.emc.emf.EmfModel;

public class Main {

	public static void main(String[] args) {
		try {

	        // Parse main.egx
	        EgxModule module = new EgxModule(new EglFileGeneratingTemplateFactory());
	        module.parse(new File("epsilon/forsyde2py.egx").getAbsoluteFile());

	        if (!module.getParseProblems().isEmpty()) {
	          System.out.println("Syntax errors found. Exiting.");
	          return;
	        }

	        // Load the XML document
			EmfModel model = new EmfModel();
	        model.setModelFile("model/forsyde.ecore");
	        model.setName("ForSyDe");
	        model.load();

	        // Make the document visible to the EGX program
	        module.getContext().getModelRepository().addModel(model);
	        // ... and execute
	        module.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}