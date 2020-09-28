package forsyde.io.generators;

import java.io.File;

import org.eclipse.epsilon.egl.EglFileGeneratingTemplateFactory;
import org.eclipse.epsilon.egl.EgxModule;
import org.eclipse.epsilon.emc.emf.EmfModel;

public class PythonGenerator {

	public void generate() throws Exception {
        // Parse main.egx
        EgxModule module = new EgxModule(new EglFileGeneratingTemplateFactory());
        module.parse(new File("epsilon/forsyde2py.egx").getAbsoluteFile());

        if (!module.getParseProblems().isEmpty()) {
          System.out.println("Syntax errors found.");
        }

        // Load the XML document
		EmfModel model = new EmfModel();
        model.setModelFile("ecore/forsyde.ecore");
        model.setName("ForSyDe");
        model.load();

        // Make the document visible to the EGX program
        module.getContext().getModelRepository().addModel(model);
        // ... and execute
        module.execute();
	}
	
}
