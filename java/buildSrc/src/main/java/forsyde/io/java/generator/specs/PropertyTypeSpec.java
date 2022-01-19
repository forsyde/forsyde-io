package forsyde.io.java.generator.specs;

import org.derive4j.Data;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
public abstract class PropertyTypeSpec {
    //public PropertyTypeEnum name;
    //public Optional<PropertyTypeSpec> valueType = Optional.empty();

    interface Cases<R> {
        R StringVertexProperty ();
        R IntVertexProperty ();
        R BooleanVertexProperty ();
        R FloatVertexProperty ();
        R DoubleVertexProperty ();
        R LongVertexProperty ();
        R ArrayVertexProperty (PropertyTypeSpec arrayType);
        R IntMapVertexProperty (PropertyTypeSpec valueType);
        R StringMapVertexProperty (PropertyTypeSpec valueType);
    }

    public abstract <R> R match(Cases<R> cases);

}
