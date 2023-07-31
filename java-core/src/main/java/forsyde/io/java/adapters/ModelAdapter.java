package forsyde.io.java.adapters;

import forsyde.io.core.SystemGraph;

public interface ModelAdapter<ModelT> {

    SystemGraph convert(ModelT inputModel);

    ModelT convert(SystemGraph inputModel);
}
