package forsyde.io.java.generator.specs;

import java.util.Arrays;
import java.util.List;

public interface NamespacedMixin {
    
    String getName();

    default List<String> getNamespaces() {
        final List<String> names = getName().startsWith("::") ?
                Arrays.asList(getName().replaceFirst("::", "").split("::")) :
                Arrays.asList(getName().split("::"));
        return names.subList(0, names.size()-1);
    }

    default String getTraitLocalName() {
        final List<String> names = getName().startsWith("::") ?
                Arrays.asList(getName().replaceFirst("::", "").split("::")) :
                Arrays.asList(getName().split("::"));
        return names.get(names.size()-1);
    }
}
