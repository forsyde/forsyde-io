package forsyde.io.java.generator;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.file.FileTree;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.internal.DefaultJavaPluginConvention;
import org.gradle.api.tasks.SourceSetContainer;

import java.util.List;
import java.util.Objects;

public class ForSyDeIOJavaMetaGenerator implements Plugin<Project> {

	//final GenerateForSyDeModelTask generateForSyDeModelTask = new GenerateForSyDeModelTask();

	@Override
	public void apply(Project project) {
		project.getTasks().register("generateForSyDeModel", GenerateForSyDeModelTask.class, generateForSyDeModelTask -> {
			generateForSyDeModelTask.getOutputDir().convention(project.getLayout().getBuildDirectory().dir("generated/forsyde-io/main/java"));
			Objects.requireNonNull(project.getTasks().findByName("compileJava")).dependsOn(generateForSyDeModelTask);
			Objects.requireNonNull(project.getTasks().findByName("build")).dependsOn(generateForSyDeModelTask);
			Objects.requireNonNull(project.getTasks().findByName("sourcesJar")).dependsOn(generateForSyDeModelTask);

			final SourceSetContainer sourceSetContainer = project.getExtensions().getByType(SourceSetContainer.class);
			sourceSetContainer.forEach(sourceSet -> {
				if(sourceSet.getName().equals("main")) {
					sourceSet.java((scs) -> {
						scs.srcDirs(generateForSyDeModelTask.getOutputDir());
					});
				}
			});
		});
	}
}
