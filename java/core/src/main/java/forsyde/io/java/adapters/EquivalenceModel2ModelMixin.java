package forsyde.io.java.adapters;

import java.util.Optional;
import java.util.stream.Stream;

public interface EquivalenceModel2ModelMixin<S, T> {

    Stream<T> equivalents(S source);

    default Optional<T> equivalent(S source) {
        return equivalents(source).findFirst();
    };

    void addEquivalence(S source, T elem);

    boolean contains(T elem);

    boolean hasEquivalent(S elem);

}
