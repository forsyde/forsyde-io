package forsyde.io.java.kgt.adapter;

import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Trait;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.viewers.visualization.Visualizable;
import forsyde.io.java.typed.viewers.visualization.VisualizableWithProperties;

import java.util.*;
import java.util.stream.Collectors;

public class KlighDNodeView {

    private final Visualizable viewed;
    final private String id;
    private final List<KlighDNodeView> children;
    private final Set<KlighDEdge> kedges;

    public KlighDNodeView(Visualizable viewed, String id) {
        this.viewed = viewed;
        this.id = id;
        children = new ArrayList<>();
        kedges = new HashSet<>();
    }

    public Visualizable getViewed() {
        return viewed;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return viewed.getIdentifier() + " [" + viewed.getTraits().stream().filter(t -> !t.refines(VertexTrait.VISUALIZATION_VISUALIZABLE)).map(Trait::getName).collect(Collectors.joining(", ")) + "]";
    }

    public List<KlighDNodeView> getChildren() {
        return children;
    }

    public void addChild(KlighDNodeView node) {
        children.add(node);
    }

    public Set<String> getKports() {
        return viewed.getPorts();
    }

    public Set<String> getActiveKports(ForSyDeSystemGraph m) {
        return viewed.getPorts().stream()
                .filter(p ->
                    m.incomingEdgesOf(viewed).stream()
                        .filter(e -> e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONNECTION) && !e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONTAINMENT))
                        .anyMatch(e -> e.getTargetPort().map(p::equals).orElse(false))
                        ||
                        m.outgoingEdgesOf(viewed).stream()
                                .filter(e -> e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONNECTION) && !e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONTAINMENT))
                                .anyMatch(e -> e.getSourcePort().map(p::equals).orElse(false)))
                .collect(Collectors.toSet());
    }

    public Set<KlighDEdge> getKedges() {
        return kedges;
    }

    public void addEdge(KlighDNodeView target) {
        kedges.add(new KlighDEdge(target));
    }

    public void addEdge(String srcPort, KlighDNodeView target) {
        kedges.add(new KlighDEdge(srcPort, target));
    }

    public void addEdge(String srcPort, KlighDNodeView target, String targetPort) {
        kedges.add(new KlighDEdge(srcPort, target, targetPort));
    }

    public boolean hasVisualizableProperties() {
        return VisualizableWithProperties.conforms(viewed);
    }

    public Map<String, String> getVisualizedProperties() {
        return VisualizableWithProperties.safeCast(viewed).map(VisualizableWithProperties::getVisualizedPropertiesNames).map(strings -> strings.stream().collect(Collectors.toMap(
                s -> s,
                s -> viewed.getViewedVertex().getProperty(s).toString()
        ))).orElse(Map.of());
    }
}
