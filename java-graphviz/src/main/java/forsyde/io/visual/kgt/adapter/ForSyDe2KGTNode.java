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
//        final Map<KNode, Visualizable> nodeToVisu = new HashMap<>();
//        var listParents = new ArrayList<>(noParents);
//        var listKNodes = new ArrayList<KNode>();
//        for (var parent: listParents) {
//            var knode = KGraphFactory.eINSTANCE.createKNode();
//            var klabel = KGraphFactory.eINSTANCE.createKLabel();
//            klabel.setParent(knode);
//            klabel.setText(parent.getIdentifier());
//            knode.setParent(root);
//            listKNodes.add(knode);
//        }
        final Queue<KlighDNodeView> kqueue = new ArrayDeque<>(roots);
        while (!kqueue.isEmpty()) {
            var kcur = kqueue.poll();
            allNodes.add(kcur);
            ForSyDeHierarchy.GreyBox.tryView(kcur.getViewed()).ifPresent(greyBox -> {
                // add them as children
                for (Visualizable visualizable : greyBox.contained()) {
                    final KlighDNodeView knode = new KlighDNodeView(visualizable, "v_" + visualizable.getIdentifier());
                    kcur.addChild(knode);
                    kqueue.add(knode);
                }
            });
        }
        // finish by making connections
        for (var src : allNodes) {
            for (var dst: allNodes) {
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
