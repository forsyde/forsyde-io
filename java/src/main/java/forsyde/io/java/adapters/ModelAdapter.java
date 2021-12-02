package forsyde.io.java.adapters;

import forsyde.io.java.core.ForSyDeSystemGraph;

public interface ModelAdapter<ModelT> {

    ForSyDeSystemGraph convert(ModelT inputModel);

    ModelT convert(ForSyDeSystemGraph inputModel);
}
