/**
 *
 */
package forsyde.io.core;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author rjordao
 *
 *         Class containing all information for an Edge.
 *
 *         The edge contains references to the source and target
 *         {@link Vertex}es as well as the {@link Port}s being connect on both
 *         ends, in case they exist. The edges also have types associated with
 *         them so that extra deductions can be made along the EDA flow.
 */
final public class EdgeInfo {

    public String sourceId;
    public String targetId;
    public Set<Trait> edgeTraits = new HashSet<Trait>();
    private String sourcePort = null;
    private String targetPort = null;

    /**
     * Utility constructor wrapping the source and target vertex into
     * their identifiers.
     *
     * @param target Target Vertex for this edge.
     * @param source Source vertex for this edge.
     */
    public EdgeInfo(Vertex source, Vertex target) {
        this.targetId = target.getIdentifier();
        this.sourceId = source.getIdentifier();
    }

    /**
     * Utility constructor wrapping the source and target ports into empty optionals
     * for constructor.
     *
     * @param targetId Target Vertex for this edge.
     * @param sourceId Source vertex for this edge.
     */
    public EdgeInfo(String sourceId, String targetId) {
        this.targetId = targetId;
        this.sourceId = sourceId;
    }

    /**
     * Utility constructor wrapping the source and target ports into optionals for
     * constructor.
     *
     * @param targetId   Target Vertex for this edge.
     * @param sourceId   Source vertex for this edge.
     * @param targetPort target vertex port for this edge.
     * @param sourcePort source vertex port for this edge.
     */
    public EdgeInfo(String sourceId, String targetId, String sourcePort, String targetPort) {
        this.sourceId = sourceId;
        this.targetId = targetId;
        this.targetPort = targetPort;
        this.sourcePort = sourcePort;
    }

    public EdgeInfo(String sourceId, String targetId, Optional<String> sourcePort, Optional<String> targetPort) {
        this.sourceId = sourceId;
        this.targetId = targetId;
        targetPort.ifPresent(t -> this.targetPort = t);
        sourcePort.ifPresent(s -> this.sourcePort = s);
    }

    public Set<Trait> getTraits() {
        return edgeTraits;
    }

    public boolean hasTrait(EdgeTrait trait) {
        return edgeTraits.stream().anyMatch(t -> t.refines(trait));
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetId, targetPort, sourceId, sourcePort);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EdgeInfo)) {
            return false;
        }
        EdgeInfo other = (EdgeInfo) obj;
        return Objects.equals(targetId, other.targetId) && Objects.equals(targetPort, other.targetPort)
                && Objects.equals(sourceId, other.sourceId) && Objects.equals(sourcePort, other.sourcePort);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Edge [traits=").append(edgeTraits).append(", source=").append(sourceId).append(", target=")
                .append(targetId)
                .append(", sourcePort=").append(sourcePort).append(", targetPort=").append(targetPort).append("]");
        return builder.toString();
    }

    public String toIDString() {
        StringBuilder builder = new StringBuilder();
        builder.append(sourceId);
        getSourcePort().ifPresent(s -> builder.append(".").append(s));
        builder.append("[").append(
                edgeTraits.stream().map(Trait::getName).collect(Collectors.joining(";"))).append("]");
        builder.append(targetId);
        getTargetPort().ifPresent(s -> builder.append(".").append(s));
        return builder.toString();
    }

    public String getSource() {
        return sourceId;
    }

    public String getTarget() {
        return targetId;
    }

    public Optional<String> getSourcePort() {
        return Optional.ofNullable(sourcePort);
    }

    public void setSourcePort(String sourcePort) {
        this.sourcePort = sourcePort;
    }

    public Optional<String> getTargetPort() {
        return Optional.ofNullable(targetPort);
    }

    public void setTargetPort(String targetPort) {
        this.targetPort = targetPort;
    }

    public boolean connectsTargetPort(String p) {
        return getTargetPort().stream().anyMatch(p::equals);
    }

    public boolean connectsSourcePort(String p) {
        return getSourcePort().stream().anyMatch(p::equals);
    }

    public void addTraits(Trait... traits) {
        edgeTraits.addAll(Arrays.asList(traits.clone()));
    }

}
