package forsyde.io.java.kgt.adapter;

import forsyde.io.java.adapters.ModelAdapter;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.SystemGraph;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public interface ForSyDe2KGTNode extends ModelAdapter<KlighDContainer> {
    @Override
    default SystemGraph convert(KlighDContainer inputModel) {
        return null;
    }

    @Override
    default KlighDContainer convert(SystemGraph inputModel) {
        return null;
        // first, figure out how many copies are necessary for each visualizable, since there is the "merge" pattern
        // a    b
        //  \  /
        //   c
        // where c needs to be duplicated (or equivalently triplicated etc).
//        final Map<Visualizable, Integer> counted = inputModel.vertexSet().stream().flatMap(v -> Visualizable.safeCast(v).stream()).collect(Collectors.toMap(v -> v, v -> 0));
//        final Set<Visualizable> roots = inputModel.vertexSet().stream().flatMap(v -> Visualizable.safeCast(v).stream()).filter(v -> inputModel.incomingEdgesOf(v).stream().noneMatch(e -> e.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONTAINMENT))).collect(Collectors.toSet());
//        final Set<KlighDNodeView> rootNodes = roots.stream().map(visualizable -> new KlighDNodeView(visualizable, "v" + visualizable.getIdentifier())).collect(Collectors.toSet());
//        final Queue<KlighDNodeView> queue = new ArrayDeque<>(rootNodes);
//        while (!queue.isEmpty()) {
//            final KlighDNodeView cur = queue.poll();
//            GreyBox.safeCast(cur.getViewed()).ifPresent(greyBox -> {
//                // add them as children
//                for (Visualizable visualizable : greyBox.getContainedPort(inputModel)) {
//                    final int count = counted.get(visualizable);
//                    final KlighDNodeView node = new KlighDNodeView(visualizable, "v" + visualizable.getIdentifier() + count);
//                    cur.addChild(node);
//                    queue.add(node);
//                    counted.put(visualizable, count + 1);
//                }
//
//            });
//            // now create the edges between any two of them and the parent
//            for (KlighDNodeView src : cur.getChildren()) {
//                inputModel.getAllEdges(cur.getViewed(), src.getViewed()).stream().filter(edgeInfo -> edgeInfo.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONNECTION)).forEach(edgeInfo -> {
//                    edgeInfo.getSourcePort().ifPresentOrElse(srcPort -> {
//                        edgeInfo.getTargetPort().ifPresentOrElse(dstPort -> {
//                            cur.addEdge(srcPort, src, dstPort);
//                        }, () -> {
//                            cur.addEdge(srcPort, src);
//                        });
//                    }, () -> {
//                        cur.addEdge(src);
//                    });
//                });
//                for (KlighDNodeView dst : cur.getChildren()) {
//                    inputModel.getAllEdges(src.getViewed(), dst.getViewed()).stream().filter(edgeInfo -> edgeInfo.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONNECTION)).forEach(edgeInfo -> {
//                        edgeInfo.getSourcePort().ifPresentOrElse(srcPort -> {
//                            edgeInfo.getTargetPort().ifPresentOrElse(dstPort -> {
//                                src.addEdge(srcPort, dst, dstPort);
//                            }, () -> {
//                                src.addEdge(srcPort, dst);
//                            });
//                        }, () -> {
//                            src.addEdge(dst);
//                        });
//                    });
//                }
//                inputModel.getAllEdges(src.getViewed(), cur.getViewed()).stream().filter(edgeInfo -> edgeInfo.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONNECTION)).forEach(edgeInfo -> {
//                    edgeInfo.getSourcePort().ifPresentOrElse(srcPort -> {
//                        edgeInfo.getTargetPort().ifPresentOrElse(dstPort -> {
//                            src.addEdge(srcPort, cur, dstPort);
//                        }, () -> {
//                            src.addEdge(srcPort, cur);
//                        });
//                    }, () -> {
//                        src.addEdge(cur);
//                    });
//                });
//            }
//        }
//        // now make connections between roots if necessary
//        for (KlighDNodeView src : rootNodes) {
//            for (KlighDNodeView dst : rootNodes) {
//                inputModel.getAllEdges(src.getViewed(), dst.getViewed()).stream().filter(edgeInfo -> edgeInfo.hasTrait(EdgeTrait.VISUALIZATION_VISUALCONNECTION)).forEach(edgeInfo -> {
//                    edgeInfo.getSourcePort().ifPresentOrElse(srcPort -> {
//                        edgeInfo.getTargetPort().ifPresentOrElse(dstPort -> {
//                            src.addEdge(srcPort, dst, dstPort);
//                        }, () -> {
//                            src.addEdge(srcPort, dst);
//                        });
//                    }, () -> {
//                        src.addEdge(dst);
//                    });
//                });
//            }
//        }
//        return new KlighDContainer(rootNodes);
    }

}
