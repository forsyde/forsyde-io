package forsyde.io.visual.kgt.adapter;


import forsyde.io.java.adapters.ModelAdapter;
import forsyde.io.core.SystemGraph;
import forsyde.io.lib.hierarchy.ForSyDeHierarchy;
import forsyde.io.lib.hierarchy.visualization.GreyBox;
import forsyde.io.lib.hierarchy.visualization.Visualizable;

import java.util.*;
import java.util.stream.Collectors;

public interface ForSyDe2KGTNode extends ModelAdapter<KlighDContainer> {
    @Override
    default SystemGraph convert(KlighDContainer inputModel) {
        return null;
    }

    @Override
    default KlighDContainer convert(SystemGraph inputModel) {
        // first, figure out how many copies are necessary for each visualizable, since there is the "merge" pattern
        // a    b
        //  \  /
        //   c
        // where c needs to be duplicated (or equivalently triplicated etc).
//        final Map<Visualizable, Integer> counted = inputModel.vertexSet().stream().flatMap(v -> ForSyDeHierarchy.Visualizable.tryView(inputModel, v).stream()).collect(Collectors.toMap(v -> v, v -> 0));
        final Set<Visualizable> noParents = inputModel.vertexSet().stream().flatMap(v -> ForSyDeHierarchy.Visualizable.tryView(inputModel, v).stream()).filter(v -> inputModel.incomingEdgesOf(v).stream().noneMatch(e -> e.hasTrait(ForSyDeHierarchy.EdgeTraits.VisualContainment))).collect(Collectors.toSet());
        final Set<KlighDNodeView> roots = noParents.stream().map(v -> new KlighDNodeView(v, "v_" + v.getIdentifier())).collect(Collectors.toSet());
        final Set<KlighDNodeView> allNodes = new HashSet<>();
        final KlighDContainer root = new KlighDContainer(roots);
        final Queue<KlighDNodeView> kqueue = new ArrayDeque<>(roots);
        // the repetition of edges in the following lines is ABSOLUTELY NECESSARY.
        // This is because some parents might share the children visually, which breaks the id handling in KIELER.
        // Therefore, we need to handle here how to generate "copies" of the same vertex so that KIELER is happy
        // and wont silently break because it connects to the same vertex. So, the logic looks a bit complicated
        // for no reason, but it complies to how KIELER expects its input.
        while (!kqueue.isEmpty()) {
            var kcur = kqueue.poll();
            allNodes.add(kcur);
            ForSyDeHierarchy.GreyBox.tryView(kcur.getViewed()).ifPresent(greyBox -> {
                // add them as children
                for (Visualizable visualizable : greyBox.contained()) {
                    final KlighDNodeView knode = new KlighDNodeView(visualizable, kcur.getId() + "_v_" + visualizable.getIdentifier());
                    kcur.addChild(knode);
                    kqueue.add(knode);
                }
                // between children
                for (var src : kcur.getChildren()) {
                    for (var dst: kcur.getChildren()) {
                        if (src != dst) {
                            // edges
                            inputModel.getAllEdges(src.getViewed(), dst.getViewed()).stream().filter(edgeInfo -> edgeInfo.hasTrait(ForSyDeHierarchy.EdgeTraits.VisualConnection)).forEach(edgeInfo -> {
                                var kedge = src.addEdge(dst);
                                edgeInfo.getSourcePort().ifPresent(kedge::setSrcPort);
                                edgeInfo.getTargetPort().ifPresent(kedge::setDstPort);
                            });
                        }
                    }
                }
                // between children and parent
                for (var dst: kcur.getChildren()) {
                    // edges
                    inputModel.getAllEdges(kcur.getViewed(), dst.getViewed()).stream().filter(edgeInfo -> edgeInfo.hasTrait(ForSyDeHierarchy.EdgeTraits.VisualConnection)).forEach(edgeInfo -> {
                        var kedge = kcur.addEdge(dst);
                        edgeInfo.getSourcePort().ifPresent(kedge::setSrcPort);
                        edgeInfo.getTargetPort().ifPresent(kedge::setDstPort);
                    });
                    inputModel.getAllEdges(dst.getViewed(), kcur.getViewed()).stream().filter(edgeInfo -> edgeInfo.hasTrait(ForSyDeHierarchy.EdgeTraits.VisualConnection)).forEach(edgeInfo -> {
                        var kedge = dst.addEdge(kcur);
                        edgeInfo.getSourcePort().ifPresent(kedge::setSrcPort);
                        edgeInfo.getTargetPort().ifPresent(kedge::setDstPort);
                    });
                }
            });
        }
        // finish by making connections
        for (var src : roots) {
            for (var dst: roots) {
                if (src != dst) {
                    // edges
                    inputModel.getAllEdges(src.getViewed(), dst.getViewed()).stream().filter(edgeInfo -> edgeInfo.hasTrait(ForSyDeHierarchy.EdgeTraits.VisualConnection)).forEach(edgeInfo -> {
                        var kedge = src.addEdge(dst);
                        edgeInfo.getSourcePort().ifPresent(kedge::setSrcPort);
                        edgeInfo.getTargetPort().ifPresent(kedge::setDstPort);
                    });
                }
            }
        }
        return root;
    }

}
