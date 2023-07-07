package forsyde.io.visual.kgt.adapter;


import forsyde.io.java.adapters.ModelAdapter;
import forsyde.io.core.SystemGraph;
import forsyde.io.lib.ForSyDeHierarchy;
import forsyde.io.lib.visualization.GreyBox;
import forsyde.io.lib.visualization.Visualizable;

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
        final Map<Visualizable, Integer> counted = inputModel.vertexSet().stream().flatMap(v -> ForSyDeHierarchy.Visualizable.tryView(inputModel, v).stream()).collect(Collectors.toMap(v -> v, v -> 0));
        final Set<Visualizable> noParents = inputModel.vertexSet().stream().flatMap(v -> ForSyDeHierarchy.Visualizable.tryView(inputModel, v).stream()).filter(v -> inputModel.incomingEdgesOf(v).stream().noneMatch(e -> e.hasTrait(ForSyDeHierarchy.EdgeTraits.VisualContainment))).collect(Collectors.toSet());
        final Set<KlighDNodeView> roots = noParents.stream().map(v -> new KlighDNodeView(v, "v_" + v.getIdentifier())).collect(Collectors.toSet());
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
//        final Queue<Visualizable> queue = new ArrayDeque<>(noParents);
        final Queue<KlighDNodeView> kqueue = new ArrayDeque<>(roots);
        while (!kqueue.isEmpty()) {
//            final Visualizable cur = queue.poll();
            var kcur = kqueue.poll();
//            nodeToVisu.put(kcur, cur);
            ForSyDeHierarchy.GreyBox.tryView(kcur.getViewed()).ifPresent(greyBox -> {
                // add them as children
                for (Visualizable visualizable : greyBox.contained()) {
                    final int count = counted.get(visualizable);
                    final KlighDNodeView knode = new KlighDNodeView(visualizable, "v_" + visualizable.getIdentifier());
//                    var klabel = KGraphFactory.eINSTANCE.createKLabel();
//                    klabel.setParent(knode);
//                    klabel.setText(visualizable.getIdentifier());
                    kcur.addChild(knode);
//                    knode.setParent(kcur);
                    kqueue.add(knode);
//                    queue.add(visualizable);
                    counted.put(visualizable, count + 1);
                    // edges
                    inputModel.getAllEdges(kcur.getViewed().getViewedVertex(), visualizable.getViewedVertex()).stream().filter(edgeInfo -> edgeInfo.hasTrait(ForSyDeHierarchy.EdgeTraits.VisualConnection)).forEach(edgeInfo -> {
                        var kedge = kcur.addEdge(knode);
//                        kedge.setSource(kcur);
//                        kedge.setTarget(knode);
                        edgeInfo.getSourcePort().ifPresent(srcPort -> {
                            kedge.setSrcPort(srcPort);
                            edgeInfo.getTargetPort().ifPresent(dstPort -> {
                                kedge.setDstPort(dstPort);
                            });
                        });
                    });
                    //other direction
                    inputModel.getAllEdges(visualizable.getViewedVertex(), kcur.getViewed().getViewedVertex()).stream().filter(edgeInfo -> edgeInfo.hasTrait(ForSyDeHierarchy.EdgeTraits.VisualConnection)).forEach(edgeInfo -> {
                        var kedge = knode.addEdge(kcur);
                        edgeInfo.getSourcePort().ifPresent(srcPort -> {
                            kedge.setSrcPort(srcPort);
                            edgeInfo.getTargetPort().ifPresent(dstPort -> {
                                kedge.setDstPort(dstPort);
                            });
                        });
                    });
                }
            });
        }
        // finish by making same level connections
        kqueue.addAll(roots);
        while (!kqueue.isEmpty()) {
            var kcur = kqueue.poll();
            for (var src : kcur.getChildren()) {
                for (var dst: kcur.getChildren()) {
                    if (src != dst) {
                        // edges
                        inputModel.getAllEdges(src.getViewed(), dst.getViewed()).stream().filter(edgeInfo -> edgeInfo.hasTrait(ForSyDeHierarchy.EdgeTraits.VisualConnection)).forEach(edgeInfo -> {
                            var kedge = src.addEdge(dst);
                            edgeInfo.getSourcePort().ifPresent(srcPort -> {
                                kedge.setSrcPort(srcPort);
                                edgeInfo.getTargetPort().ifPresent(kedge::setDstPort);
                            });
                        });
                        //other direction
                        inputModel.getAllEdges(dst.getViewed(), src.getViewed()).stream().filter(edgeInfo -> edgeInfo.hasTrait(ForSyDeHierarchy.EdgeTraits.VisualConnection)).forEach(edgeInfo -> {
                            var kedge = dst.addEdge(src);
                            edgeInfo.getSourcePort().ifPresent(srcPort -> {
                                kedge.setSrcPort(srcPort);
                                edgeInfo.getTargetPort().ifPresent(kedge::setDstPort);
                            });
                        });
                    }
                }
            }
        }
        return root;
    }

}
