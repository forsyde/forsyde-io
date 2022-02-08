package forsyde.io.java.generator;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.util.Objects;

public class ForSyDeIOJavaMetaGenerator implements Plugin<Project> {

	//final GenerateForSyDeModelTask generateForSyDeModelTask = new GenerateForSyDeModelTask();

	@Override
	public void apply(Project project) {
		project.getTasks().register("generateForSyDeModel", GenerateForSyDeModelTask.class);

		Objects.requireNonNull(project.getTasks().findByName("compileJava"))
				.dependsOn(project.getTasks().findByName("generateForSyDeModel"));

	}
}
