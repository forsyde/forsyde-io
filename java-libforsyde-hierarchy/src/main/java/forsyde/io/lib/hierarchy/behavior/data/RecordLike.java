package forsyde.io.lib.hierarchy.behavior.data;

import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.RegisterTrait;
import forsyde.io.core.annotations.WithEdgeTrait;
import forsyde.io.lib.hierarchy.IForSyDeHierarchy;
import forsyde.io.lib.hierarchy.behavior.DataLikeCompositionEdge;
import forsyde.io.lib.hierarchy.behavior.DataTypeLike;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This trait captures record datum.
 * A record is an aggregate of data with labels where the data can be heterogeneous.
 * An illustrative example of a record are structs in ANSI C.
 */
@RegisterTrait(IForSyDeHierarchy.class)
public interface RecordLike extends DataTypeLike {

    @OutPort
    @WithEdgeTrait(DataLikeCompositionEdge.class)
    Set<DataTypeLike> fields();


    /**
     * This utility method return the fields of the record associated with any ports they might have.
     * For example, suppose that an integer field is connected to this entity in the port "range" via
     * the "fields" port.
     * This methods then returns a mapping {"range" -> integer connected vertex via "ports"}.
     *
     * If the field is _not_ connected to a port in the vertex, it will _not_ appear in this mapping.
     */
    default Map<String, DataTypeLike> namedFields() {
        return fields().stream().flatMap(
                f -> getViewedSystemGraph().getAllEdges(this, f).stream().flatMap(
                        e -> e.getSourcePort().stream()).map(srcPort -> Map.entry(srcPort, f))
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
