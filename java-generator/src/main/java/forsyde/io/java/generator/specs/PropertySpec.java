package forsyde.io.java.generator.specs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Optional;

public class PropertySpec {

    public String name;
//    @JsonProperty("default")
//    public VertexProperty defaultValue;
    public PropertyTypeSpec type;
    public Optional<String> comment = Optional.empty();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertySpec that = (PropertySpec) o;
        return Objects.equals(name, that.name) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }
}
