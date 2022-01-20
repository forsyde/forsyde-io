package forsyde.io.java.generator.specs;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
public class EdgeTraitSpec {

    public String name;
    public List<EdgeTraitSpec> refinedTraits = new ArrayList<>();
    public transient List<String> refinedTraitNames = new ArrayList<>();
}
