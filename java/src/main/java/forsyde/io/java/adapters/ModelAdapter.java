package forsyde.io.java.adapters;

import forsyde.io.java.core.ForSyDeModel;

public interface ModelAdapter<ModelT> {

    ForSyDeModel convert(ModelT inputModel);

    ModelT convert(ForSyDeModel inputModel);
}
