import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class MetaGenerator extends DefaultTask {

    @TaskAction
    public void doGenerate() {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new Jdk8Module());
        try {
            MetaModel metaModel = objectMapper.readValue(new File("meta.json"), MetaModel.class);
            for (VertexTraitSpec v : metaModel.vertexTraits) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
