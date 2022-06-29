package forsyde.io.java.generator.specs;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
public class EdgeTraitSpec implements SourceTraceableSpec {

    public transient int declaredLine = 0;
    public transient int declaredColumn = 0;
    public String name;
    public List<EdgeTraitSpec> refinedTraits = new ArrayList<>();
    public transient List<String> absoluteRefinedTraitNames = new ArrayList<>();
    public transient List<String> relativeRefinedTraitNames = new ArrayList<>();
    public List<VertexTraitSpec> sourceTraits = new ArrayList<>();
    public List<VertexTraitSpec> targetTraits = new ArrayList<>();
    public transient List<String> sourceTraitNames = new ArrayList<>();
    public transient List<String> targetTraitNames = new ArrayList<>();


    @Override
    public int getLine() {
        return declaredLine;
    }

    @Override
    public int getColumn() {
        return declaredColumn;
    }


}
