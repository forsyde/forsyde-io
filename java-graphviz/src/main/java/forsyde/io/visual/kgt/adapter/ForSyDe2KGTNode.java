package forsyde.io.visual.kgt.adapter;


import de.cau.cs.kieler.klighd.kgraph.KGraphFactory;
import de.cau.cs.kieler.klighd.kgraph.KNode;
import de.cau.cs.kieler.klighd.kgraph.impl.KNodeImpl;
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory;
import forsyde.io.java.adapters.ModelAdapter;
import forsyde.io.core.SystemGraph;
import forsyde.io.lib.ForSyDeHierarchy;
import forsyde.io.lib.visualization.GreyBox;
import forsyde.io.lib.visualization.Visualizable;

import java.util.*;
import java.util.stream.Collectors;

public interface ForSyDe2KGTNode extends ModelAdapter<KNode> {
    @Override
    default SystemGraph convert(KNode inputModel) {
        return null;
    }

    @Override
    default KNode convert(SystemGraph inputModel) {
        // first, figure out how many copies are necessary for each visualizable, since there is the "merge" pattern
        // a    b
        //  \  /
        //   c
        // where c needs to be duplicated (or equivalently triplicated etc).
        final KNode root = KGraphFactory.eINSTANCE.createKNode();
        final Map<Visualizable, Integer> counted = inputModel.vertexSet().stream().flatMap(v -> ForSyDeHierarchy.Visualizable.tryView(inputModel, v).stream()).collect(Collectors.toMap(v -> v, v -> 0));
        final Set<Visualizable> noParents = inputModel.vertexSet().stream().flatMap(v -> ForSyDeHierarchy.Visualizable.tryView(inputModel, v).stream()).filter(v -> inputModel.incomingEdgesOf(v).stream().noneMatch(e -> e.hasTrait(ForSyDeHierarchy.EdgeTraits.VisualContainment))).collect(Collectors.toSet());
        final Map<KNode, Visualizable> nodeToVisu = new HashMap<>();
        var listParents = noParents.stream().collect(Collectors.toList());
        var listKNodes = new ArrayList<KNode>();
        for (var parent: listParents) {
            var knode = KGraphFactory.eINSTANCE.createKNode();
            var klabel = KGraphFactory.eINSTANCE.createKLabel();
            klabel.setParent(knode);
            klabel.setText(parent.getIdentifier());
            knode.setParent(root);
            listKNodes.add(knode);
        }
        final Queue<Visualizable> queue = new ArrayDeque<>(listParents);
        final Queue<KNode> kqueue = new ArrayDeque<>(listKNodes);
        while (!queue.isEmpty()) {
            final Visualizable cur = queue.poll();
            var kcur = kqueue.poll();
            nodeToVisu.put(kcur, cur);
            ForSyDeHierarchy.GreyBox.tryView(cur).ifPresent(greyBox -> {
                // add them as children
                for (Visualizable visualizable : greyBox.contained()) {
                    final int count = counted.get(visualizable);
                    final KNode knode = KGraphFactory.eINSTANCE.createKNode();
                    var klabel = KGraphFactory.eINSTANCE.createKLabel();
                    klabel.setParent(knode);
                    klabel.setText(visualizable.getIdentifier());
                    knode.setParent(kcur);
                    kqueue.add(knode);
                    queue.add(visualizable);
                    counted.put(visualizable, count + 1);
                    // edges
                    inputModel.getAllEdges(cur.getViewedVertex(), visualizable.getViewedVertex()).stream().filter(edgeInfo -> edgeInfo.hasTrait(ForSyDeHierarchy.EdgeTraits.VisualConnection)).forEach(edgeInfo -> {
                        var kedge = KGraphFactory.eINSTANCE.createKEdge();
                        kedge.setSource(kcur);
                        kedge.setTarget(knode);
                        edgeInfo.getSourcePort().ifPresent(srcPort -> {
                            var ksrcport = KGraphFactory.eINSTANCE.createKPort();
                            ksrcport.setNode(kcur);
                            kedge.setSourcePort(ksrcport);
                            edgeInfo.getTargetPort().ifPresent(dstPort -> {
                                var kdstport = KGraphFactory.eINSTANCE.createKPort();
                                kdstport.setNode(knode);
                                kedge.setTargetPort(kdstport);
                            });
                        });
                    });
                    //other direction
                    inputModel.getAllEdges(visualizable.getViewedVertex(), cur.getViewedVertex()).stream().filter(edgeInfo -> edgeInfo.hasTrait(ForSyDeHierarchy.EdgeTraits.VisualConnection)).forEach(edgeInfo -> {
                        var kedge = KGraphFactory.eINSTANCE.createKEdge();
                        kedge.setSource(knode);
                        kedge.setTarget(kcur);
                        edgeInfo.getSourcePort().ifPresent(srcPort -> {
                            var ksrcport = KGraphFactory.eINSTANCE.createKPort();
                            ksrcport.setNode(knode);
                            kedge.setSourcePort(ksrcport);
                            edgeInfo.getTargetPort().ifPresent(dstPort -> {
                                var kdstport = KGraphFactory.eINSTANCE.createKPort();
                                kdstport.setNode(kcur);
                                kedge.setTargetPort(kdstport);
                            });
                        });
                    });
                }
            });
        }
        // finish by making same level connections
        assert kqueue.isEmpty();
        kqueue.add(root);
        while (!kqueue.isEmpty()) {
            var kcur = kqueue.poll();
            for (var src : kcur.getChildren()) {
                for (var dst: kcur.getChildren()) {
                    if (src != dst) {
                        // edges
                        inputModel.getAllEdges(nodeToVisu.get(src), nodeToVisu.get(dst)).stream().filter(edgeInfo -> edgeInfo.hasTrait(ForSyDeHierarchy.EdgeTraits.VisualConnection)).forEach(edgeInfo -> {
                            var kedge = KGraphFactory.eINSTANCE.createKEdge();
                            kedge.setSource(src);
                            kedge.setTarget(dst);
                            edgeInfo.getSourcePort().ifPresent(srcPort -> {
                                var ksrcport = KGraphFactory.eINSTANCE.createKPort();
                                ksrcport.setNode(src);
                                kedge.setSourcePort(ksrcport);
                                edgeInfo.getTargetPort().ifPresent(dstPort -> {
                                    var kdstport = KGraphFactory.eINSTANCE.createKPort();
                                    kdstport.setNode(dst);
                                    kedge.setTargetPort(kdstport);
                                });
                            });
                        });
                        //other direction
                        inputModel.getAllEdges(nodeToVisu.get(dst), nodeToVisu.get(src)).stream().filter(edgeInfo -> edgeInfo.hasTrait(ForSyDeHierarchy.EdgeTraits.VisualConnection)).forEach(edgeInfo -> {
                            var kedge = KGraphFactory.eINSTANCE.createKEdge();
                            kedge.setSource(dst);
                            kedge.setTarget(src);
                            edgeInfo.getSourcePort().ifPresent(srcPort -> {
                                var ksrcport = KGraphFactory.eINSTANCE.createKPort();
                                ksrcport.setNode(dst);
                                kedge.setSourcePort(ksrcport);
                                edgeInfo.getTargetPort().ifPresent(dstPort -> {
                                    var kdstport = KGraphFactory.eINSTANCE.createKPort();
                                    kdstport.setNode(src);
                                    kedge.setTargetPort(kdstport);
                                });
                            });
                        });
                    }
                }
            }
        }
        return root;
    }

}
