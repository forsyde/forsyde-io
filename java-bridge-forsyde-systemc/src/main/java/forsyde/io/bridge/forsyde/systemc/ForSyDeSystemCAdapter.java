package forsyde.io.bridge.forsyde.systemc;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.VertexViewer;
import forsyde.io.lib.ForSyDeHierarchy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

interface ForSyDeSystemCAdapter {

    default SystemGraph inspectRegion(String sourceCode, long startIdx, int endIdx, Map<String, String> symbolTable) {
        // first, look for module
        return new SystemGraph();
    }

    default SystemGraph inspectSourceCode(String sourceCode) {
        var mainStart = sourceCode.indexOf("int sc_main");
        var parenStart = sourceCode.indexOf('{', mainStart + 1);
        var parenEnd = getRegionEnd(sourceCode, parenStart + 1);
        var systemGraph = new SystemGraph();
        processInstantiationPattern.matcher(sourceCode.substring(parenStart, parenEnd)).results().forEach(matchResult -> {
            var clsName = matchResult.group(1);
            var varName = matchResult.group(2);
            var instName = matchResult.group(3);
            var entity = ForSyDeHierarchy.MoCEntity.enforce(systemGraph, systemGraph.newVertex(instName));
            inspectModuleDeclaration(entity, sourceCode, clsName, instName);
        });
        return systemGraph;
    }

    default VertexViewer inspectModuleDeclaration(VertexViewer container, String sourceCode, String clsName, String instName) {
        var mainStart = sourceCode.indexOf("SC_MODULE(" + clsName + ")");
        var parenStart = sourceCode.indexOf('{', mainStart + 1);
        var parenEnd = getRegionEnd(sourceCode, parenStart + 1);
        collectContents(container, sourceCode.substring(parenStart + 1, parenEnd), sourceCode, clsName, instName);
        return container;
    }

    default VertexViewer inspectLeafFunction(VertexViewer container, String sourceCode, String funcName, String instName) {
        var mainStart = sourceCode.indexOf(funcName);
        var parenStart = sourceCode.indexOf('{', mainStart + 1);
        var parenEnd = getRegionEnd(sourceCode, parenStart + 1);
        var systemGraph = container.getViewedSystemGraph();
        var funcVertex = ForSyDeHierarchy.HasANSICImplementation.enforce(systemGraph, systemGraph.newVertex(funcName));
        funcVertex.inlinedCode(sourceCode.substring(parenStart + 1, parenEnd).trim());
        var visu = ForSyDeHierarchy.VisualizableWithProperties.enforce(funcVertex);
        visu.visualizedPropertiesNames(List.of("inlinedCode"));
        return funcVertex;
    }

    default VertexViewer collectProcessConstructor(VertexViewer container, String sourceCode, String instName, String funcName, List<String> parameters) {
        var systemGraph = container.getViewedSystemGraph();
        if (funcName.startsWith("scomb")) {
            var syProc = ForSyDeHierarchy.SYMap.enforce(systemGraph, systemGraph.newVertex(instName));
            var func = ForSyDeHierarchy.BehaviourEntity.enforce(inspectLeafFunction(container, sourceCode, parameters.get(0).trim(), instName));
            syProc.addCombFunctions(func);
            ForSyDeHierarchy.GreyBox.enforce(container).addContained(ForSyDeHierarchy.Visualizable.enforce(syProc));
            ForSyDeHierarchy.GreyBox.enforce(syProc).addContained(ForSyDeHierarchy.Visualizable.enforce(func));
            // add output port and connect to signal
            syProc.addPorts("oport1");
            syProc.outputPorts(List.of("oport1"));
            systemGraph.queryVertex(instName.isBlank() ? parameters.get(1).trim() : instName + "_" + parameters.get(1).trim())
                    .flatMap(outSig -> ForSyDeHierarchy.SYSignal.tryView(systemGraph, outSig))
                    .ifPresentOrElse(outSig -> outSig.producer("oport1", syProc, ForSyDeHierarchy.EdgeTraits.VisualConnection), () ->
                            systemGraph.connect(syProc, container, "oport1", parameters.get(1).trim(), ForSyDeHierarchy.EdgeTraits.VisualConnection, ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge)
                    );
            // now for the inputs
            for (int i = 2; i < parameters.size(); i++) {
                syProc.addPorts("iport" + (i - 1));
                syProc.inputPorts().add("iport" + (i - 1));
                var finalI = i;
                systemGraph.queryVertex(instName.isBlank() ? parameters.get(i - 2).trim() : instName + "_" + parameters.get(1).trim())
                        .flatMap(outSig -> ForSyDeHierarchy.SYSignal.tryView(systemGraph, outSig))
                        .ifPresentOrElse(outSig -> outSig.addConsumers("iport" + (finalI - 1), syProc, ForSyDeHierarchy.EdgeTraits.VisualConnection), () ->
                                systemGraph.connect(container, syProc, parameters[1].trim(), "iport" + (finalI - 1), ForSyDeHierarchy.EdgeTraits.VisualConnection, ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge)
                        );
            }
            return syProc;
        }
        return null;
    }

    default VertexViewer collectContents(VertexViewer container, String regionSource, String sourceCode, String clsName, String instName) {
        var systemGraph = container.getViewedSystemGraph();
        // get all signals
        signalDefPattern.matcher(regionSource).results().forEach(matchResult -> {
            for (var name : matchResult.group(3).split(",")) {
                var sigName = instName.isBlank() ? name.trim() : instName + "_" + name.trim();
                if (matchResult.group(1).equalsIgnoreCase("SY")) {
                    var sig = ForSyDeHierarchy.SYSignal.enforce(systemGraph, systemGraph.newVertex(sigName));
                    ForSyDeHierarchy.GreyBox.enforce(container).addContained(ForSyDeHierarchy.Visualizable.enforce(sig));
                } else if (matchResult.group(1).equalsIgnoreCase("SDF")) {
                    var sig = ForSyDeHierarchy.SDFChannel.enforce(systemGraph, systemGraph.newVertex(sigName));
                    ForSyDeHierarchy.GreyBox.enforce(container).addContained(ForSyDeHierarchy.Visualizable.enforce(sig));
                }
            }
        });
        // get all input ports
        inPortPattern.matcher(regionSource).results().forEach(matchResult -> {
            for (var name : matchResult.group(3).split(",")) {
                container.addPorts(name.trim());
            }
        });
        // get all output ports
        outPortPattern.matcher(regionSource).results().forEach(matchResult -> {
            for (var name : matchResult.group(3).split(",")) {
                container.addPorts(name.trim());
            }
        });
        // get children instantiated processes
        instantiateNewProcess.matcher(regionSource).results().forEach(matchResult -> {
            var subInstName = instName.isBlank() ? matchResult.group(3).trim() : instName + "_" + matchResult.group(3).trim();
            var subContainer = ForSyDeHierarchy.MoCEntity.enforce(systemGraph, systemGraph.newVertex(subInstName));
            var created = inspectModuleDeclaration(subContainer, sourceCode, matchResult.group(2), subInstName);
            ForSyDeHierarchy.GreyBox.enforce(container).addContained(ForSyDeHierarchy.Visualizable.enforce(created));
        });
        // get process constructors
        instantiateNewProcessWithMake.matcher(regionSource).results().forEach(matchResult -> {
            var subInstName = instName.isBlank() ? matchResult.group(3).trim() : instName + "_" + matchResult.group(3).trim();
            if (matchResult.group(2).trim().startsWith("scomb")) {
                var syProc = collectProcessConstructor(container, sourceCode, subInstName, matchResult.group(2).trim(), Arrays);
            }
        });
        // and other written in other formats
        newProcessWithMake.matcher(regionSource).results().forEach(matchResult -> {
            var subInstName = instName.isBlank() ? matchResult.group(2).trim() : instName + "_" + matchResult.group(2).trim();
            if (matchResult.group(1).trim().startsWith("scomb")) {
                var syProc = collectProcessConstructor(container, sourceCode, subInstName, matchResult);
            }
        });
        // get the port connections
        connectPortAndSignals.matcher(regionSource).results().forEach(matchResult -> {
            var group1 = instName.isBlank() ? matchResult.group(1).trim() : instName + "_" + matchResult.group(1).trim();
            var group3 = instName.isBlank() ? matchResult.group(3).trim() : instName + "_" + matchResult.group(3).trim();
            systemGraph.queryVertex(group1).ifPresent(procVertex -> {
                systemGraph.queryVertex(group3).ifPresentOrElse(sigVertex -> {
                    ForSyDeHierarchy.SYMap.tryView(systemGraph, procVertex).ifPresent(syMapViewer -> {
                        ForSyDeHierarchy.SYSignal.tryView(systemGraph, sigVertex).ifPresent(sySignalViewer -> {
                            if (syMapViewer.outputPorts().contains(matchResult.group(2).trim())) {
                                sySignalViewer.producer(matchResult.group(2).trim(), syMapViewer, ForSyDeHierarchy.EdgeTraits.VisualConnection);
                            } else if (syMapViewer.inputPorts().contains(matchResult.group(2).trim())) {
                                sySignalViewer.addConsumers(matchResult.group(2).trim(), syMapViewer, ForSyDeHierarchy.EdgeTraits.VisualConnection);
                            }
                        });
                    });
                }, () -> { // it is a port in the end
                    ForSyDeHierarchy.SYMap.tryView(systemGraph, procVertex).ifPresent(syMapViewer -> {
                        if (syMapViewer.outputPorts().contains(matchResult.group(2).trim())) {
                            systemGraph.connect(syMapViewer, container, matchResult.group(2).trim(), matchResult.group(3).trim(), ForSyDeHierarchy.EdgeTraits.VisualConnection, ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge);
                        } else if (syMapViewer.inputPorts().contains(matchResult.group(2).trim())) {
                            systemGraph.connect(container, syMapViewer, matchResult.group(3).trim(), matchResult.group(2).trim(), ForSyDeHierarchy.EdgeTraits.VisualConnection, ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge);
                        }
                    });
                });
            });
        });
        return container;
    }

    private int getRegionEnd(String sourceCode, int start) {
        var idx = start + 1;
        var parenLeft = 1;
        while (parenLeft > 0 && idx < sourceCode.length()) {
            idx += 1;
            if (sourceCode.charAt(idx) == '}') parenLeft -= 1;
            else if (sourceCode.charAt(idx) == '{') parenLeft += 1;
        }
        return idx;
    }

    static Pattern processInstantiationPattern = Pattern.compile("(?<cls>\\w+) (?<varName>\\w+)\\(\"(?<instName>\\w+)\"\\);");
    static Pattern inPortPattern = Pattern.compile("(?<moc>\\w+)::in_port<(?<sigType>\\w+)> ([ ,\\w]+);");
    static Pattern outPortPattern = Pattern.compile("(?<moc>\\w+)::out_port<(?<sigType>\\w+)> ([ ,\\w]+);");
    static Pattern signalDefPattern = Pattern.compile("(?<moc>\\w+)::signal<(?<sigType>\\w+)> ([ ,\\w]+);");
    static Pattern instantiateNewProcess = Pattern.compile("[<>:\\w]+ (?<varname>\\w+) = new (?<clsName>\\w+)\\(\"(?<instName>\\w+)\"\\);");
    static Pattern instantiateNewProcessWithMake = Pattern.compile("[<>:\\w]+ (?<varname>\\w+) = [<>\\w]+::make_(?<clsName>\\w+)\\(\"(?<instName>\\w+)\"(?:, (?<parameters>[\\w, ]+))?\\);");

    static Pattern newProcessWithMake = Pattern.compile("[<>\\w]+::make_(?<clsName>\\w+)\\(\"(?<instName>\\w+)\"(?:, (?<parameters>[\\w, ]+))?\\);");

    static Pattern connectPortAndSignals = Pattern.compile("(?<procName>\\w+)->(?<portName>\\w+)\\((?<sigName>\\w+)\\);");
}
