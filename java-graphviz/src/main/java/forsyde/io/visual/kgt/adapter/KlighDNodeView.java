package forsyde.io.visual.kgt.adapter;

import forsyde.io.core.Trait;
import forsyde.io.lib.hierarchy.ForSyDeHierarchy;
import forsyde.io.lib.hierarchy.visualization.Visualizable;
import forsyde.io.lib.hierarchy.visualization.VisualizableWithPropertiesViewer;
import org.ainslec.picocog.PicoWriter;

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
        return id.replace(".", "_");
    }

    public String getLabel() {
        return viewed.getIdentifier() + " [" + viewed.getVertexTraitNames().stream().filter(t -> !t.getName().contains("visualization")).map(Trait::getName).map(x -> {
            var spl = x.split("::");
            return spl[spl.length - 1];
        }).collect(Collectors.joining(", ")) + "]";
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

    public Set<String> getActiveKports() {
        var m = getViewed().getViewedSystemGraph();
        return viewed.getPorts().stream().filter(p ->
                m.incomingEdgesOf(viewed).stream().filter(e -> e.hasTrait(ForSyDeHierarchy.EdgeTraits.VisualConnection) && !e.hasTrait(ForSyDeHierarchy.EdgeTraits.VisualContainment))
                        .anyMatch(e -> e.getTargetPort().map(p::equals).orElse(false)) || m.outgoingEdgesOf(viewed).stream().filter(e -> e.hasTrait(ForSyDeHierarchy.EdgeTraits.VisualConnection) && !e.hasTrait(ForSyDeHierarchy.EdgeTraits.VisualContainment))
                        .anyMatch(e -> e.getSourcePort().map(p::equals).orElse(false)))
                .collect(Collectors.toSet());
    }

    public Set<KlighDEdge> getKedges() {
        return kedges;
    }

    public KlighDEdge addEdge(KlighDNodeView target) {
        var e = new KlighDEdge(target);
        kedges.add(e);
        return e;
    }

    public KlighDEdge addEdge(String srcPort, KlighDNodeView target) {
        var e = new KlighDEdge(srcPort, target);
        kedges.add(e);
        return e;
    }

    public KlighDEdge addEdge(String srcPort, KlighDNodeView target, String targetPort) {
        var e = new KlighDEdge(srcPort, target, targetPort);
        kedges.add(e);
        return e;
    }

    public boolean hasVisualizableProperties() {
        return ForSyDeHierarchy.VisualizableWithProperties.tryView(viewed).isPresent();
    }

    public Map<String, String> getVisualizedProperties() {
        return ForSyDeHierarchy.VisualizableWithProperties.tryView(viewed).map(VisualizableWithPropertiesViewer::visualizedPropertiesNames).map(strings -> strings.stream().collect(Collectors.toMap(
                s -> s,
                s -> viewed.getViewedVertex().getProperty(s).toString()
        ))).orElse(Map.of());
    }

    public void write(PicoWriter picoWriter) {
        picoWriter.writeln_r("knode " + getId() + " {");
        picoWriter.writeln("klabel \"" + getLabel() + "\"");
        for (String port : getActiveKports()) {
            final String portString = port.replace(" ", "_").replace(".", "_");
            picoWriter.writeln_r("kport " + portString + " {");
            picoWriter.writeln("klabel \"" + port + "\"");
            picoWriter.writeln_l("}");
        }
        if (hasVisualizableProperties()) {
            picoWriter.writeln_r("knode " + id + "vProperties {");
            // TODO: until I figure out how to make the size of the node label be respected,
            // this small sizing hack with width and height stays.
            final int width = 5 * getVisualizedProperties().entrySet().stream().mapToInt(e -> ("\\n " + e.getKey() + ": " + e.getValue()).length()).sum();
            final int height = 35 * getVisualizedProperties().size();
            picoWriter.writeln("size: width=" + width + " height=" + height);
            picoWriter.write("klabel \"properties:");
            getVisualizedProperties().forEach((k, v) -> picoWriter.write("\\n " + k + ": " + v));
            picoWriter.writeln("\"");
            picoWriter.writeln_l("}");
        }
        for (KlighDNodeView child : getChildren()) {
            child.write(picoWriter.createDeferredWriter());
//            queue.add(child);
        }
        for (KlighDEdge edge : getKedges()) {
            var dstId = edge.getTarget().getId().replace(" ", "_").replace(".", "_");
            edge.getSrcPort().filter(src -> getActiveKports().contains(src)).ifPresentOrElse(srcPort -> {
                edge.getDstPort().filter(p -> edge.getTarget().getActiveKports().contains(p)).ifPresentOrElse(dstPort -> {
                    picoWriter.writeln("kedge ( " + ":" + srcPort + " -> " + dstId + ":" + dstPort + ")");
                }, () -> {
                    picoWriter.writeln("kedge ( " + ":" + srcPort + " -> " + dstId + ")");
                });
            }, () -> {
                edge.getDstPort().filter(p -> edge.getTarget().getActiveKports().contains(p)).ifPresentOrElse(dstPort -> {
                    picoWriter.writeln("kedge ( -> " + dstId + ":" + dstPort + ")");
                }, () -> {
                    picoWriter.writeln("kedge ( " + " -> " + dstId + ")");
                });
            });
        }
        picoWriter.writeln_l("}");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KlighDNodeView that = (KlighDNodeView) o;
        return Objects.equals(viewed, that.viewed) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(viewed, id);
    }
}
