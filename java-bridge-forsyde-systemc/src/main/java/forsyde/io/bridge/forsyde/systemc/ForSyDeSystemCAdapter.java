package forsyde.io.bridge.forsyde.systemc;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.VertexViewer;
import forsyde.io.lib.hierarchy.ForSyDeHierarchy;
import forsyde.io.lib.hierarchy.behavior.FunctionLikeEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
        var regionCode = sourceCode.substring(parenStart + 1, parenEnd);
        collectContents(container, regionCode, sourceCode, clsName, instName);
        return container;
    }

    default FunctionLikeEntity inspectLeafFunction(VertexViewer container, String sourceCode, String funcName, String instName) {
        var mainStart = sourceCode.indexOf(funcName);
        var parametersStart = sourceCode.indexOf("(", mainStart + 1);
        var parametersEnd = sourceCode.indexOf(")",  parametersStart + 1);
        var parenStart = sourceCode.indexOf('{', mainStart + 1);
        var parenEnd = getRegionEnd(sourceCode, parenStart + 1);
        var systemGraph = container.getViewedSystemGraph();
        var withCVertex = ForSyDeHierarchy.HasANSICImplementations.enforce(systemGraph, systemGraph.newVertex(funcName));
        var funcVertex = ForSyDeHierarchy.FunctionLikeEntity.enforce(withCVertex);
        withCVertex.inlinedCodes().put("generic", sourceCode.substring(parenStart + 1, parenEnd).trim());
        for (var param : sourceCode.substring(parametersStart + 1, parametersEnd).split(",")) {
            var tokens = param.trim().split(" ");
            if (tokens[0].startsWith("const")) {
                var port = tokens[tokens.length - 1].replace("&", "");
                withCVertex.inputArgumentPorts().add(port);
                withCVertex.addPorts(port);
                funcVertex.inputPorts().add(port);
            } else {
                var port = tokens[tokens.length - 1].replace("&", "");
                withCVertex.outputArgumentPorts().add(port);
                withCVertex.addPorts(port);
                funcVertex.outputPorts().add(port);
            }
        }

//        var visu = ForSyDeHierarchy.VisualizableWithProperties.enforce(funcVertex);
//        visu.visualizedPropertiesNames(List.of("inlinedCodes"));
        return funcVertex;
    }

    default VertexViewer collectProcessConstructor(VertexViewer container, String sourceCode, String instName, String constructorName, List<String> parameters) {
        var systemGraph = container.getViewedSystemGraph();
        if (constructorName.startsWith("scomb")) {
            var syProc = ForSyDeHierarchy.SYMap.enforce(systemGraph, systemGraph.newVertex(instName));
            var func = ForSyDeHierarchy.FunctionLikeEntity.enforce(inspectLeafFunction(container, sourceCode, parameters.get(0).trim(), instName));
            syProc.addCombFunctions(func);
            ForSyDeHierarchy.GreyBox.enforce(container).addContained(ForSyDeHierarchy.Visualizable.enforce(syProc));
            ForSyDeHierarchy.GreyBox.enforce(syProc).addContained(ForSyDeHierarchy.Visualizable.enforce(func));
            // add output port and connect to signal
            syProc.addPorts("oport1");
            syProc.outputPorts(List.of("oport1"));
            systemGraph.queryVertex(container.getIdentifier() + "_" + parameters.get(1).trim())
                    .flatMap(outSig -> ForSyDeHierarchy.SYSignal.tryView(systemGraph, outSig))
                    .ifPresentOrElse(outSig -> outSig.producer("oport1", syProc, ForSyDeHierarchy.EdgeTraits.VisualConnection), () ->
                            systemGraph.connect(syProc, container, "oport1", parameters.get(1).trim(), ForSyDeHierarchy.EdgeTraits.VisualConnection, ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge)
                    );
            // now for the inputs
            for (int i = 2; i < parameters.size(); i++) {
                syProc.addPorts("iport" + (i - 1));
                syProc.inputPorts().add("iport" + (i - 1));
                var finalI = i;
                systemGraph.queryVertex(container.getIdentifier() + "_" + parameters.get(i).trim())
                        .flatMap(outSig -> ForSyDeHierarchy.SYSignal.tryView(systemGraph, outSig))
                        .ifPresentOrElse(inSig -> inSig.addConsumers("iport" + (finalI - 1), syProc, ForSyDeHierarchy.EdgeTraits.VisualConnection), () ->
                                systemGraph.connect(container, syProc, parameters.get(finalI).trim(), "iport" + (finalI - 1), ForSyDeHierarchy.EdgeTraits.VisualConnection, ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge)
                        );
            }
            // connect child inputs and output
            for (int i = 0; i < func.inputPorts().size(); i++) {
                systemGraph.connect(syProc, func, syProc.inputPorts().get(i), func.inputPorts().get(i), ForSyDeHierarchy.EdgeTraits.VisualConnection, ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge);
            }
            for (int i = 0; i < func.outputPorts().size(); i++) {
                systemGraph.connect(func, syProc, func.outputPorts().get(i), syProc.outputPorts().get(i), ForSyDeHierarchy.EdgeTraits.VisualConnection, ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge);
            }
            return syProc;
        } else if (constructorName.startsWith("sdelay")) {
            var syDelay = ForSyDeHierarchy.SYDelay.enforce(systemGraph, systemGraph.newVertex(instName));
            ForSyDeHierarchy.GreyBox.enforce(container).addContained(ForSyDeHierarchy.Visualizable.enforce(syDelay));
            // add output port and connect to signal
            systemGraph.queryVertex(container.getIdentifier() + "_" + parameters.get(1).trim())
                    .flatMap(outSig -> ForSyDeHierarchy.SYSignal.tryView(systemGraph, outSig))
                    .ifPresentOrElse(outSig -> syDelay.delayed("producer", outSig, ForSyDeHierarchy.EdgeTraits.VisualConnection), () ->
                            systemGraph.connect(syDelay, container, "oport1", parameters.get(1).trim(), ForSyDeHierarchy.EdgeTraits.VisualConnection, ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge)
                    );
            // now for the inputs
            systemGraph.queryVertex(container.getIdentifier() + "_" + parameters.get(2).trim())
                    .flatMap(outSig -> ForSyDeHierarchy.SYSignal.tryView(systemGraph, outSig))
                    .ifPresentOrElse(inSig -> syDelay.input("consumers", inSig, ForSyDeHierarchy.EdgeTraits.VisualConnection), () ->
                            systemGraph.connect(container, syDelay, parameters.get(2).trim(), "iport1", ForSyDeHierarchy.EdgeTraits.VisualConnection, ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge)
                    );
            return syDelay;
        } else if (constructorName.startsWith("ssource")) {
            var syProc = ForSyDeHierarchy.SYMap.enforce(systemGraph, systemGraph.newVertex(instName));
            var func = ForSyDeHierarchy.FunctionLikeEntity.enforce(inspectLeafFunction(container, sourceCode, parameters.get(0).trim(), instName));
            syProc.addCombFunctions(func);
            ForSyDeHierarchy.GreyBox.enforce(container).addContained(ForSyDeHierarchy.Visualizable.enforce(syProc));
            ForSyDeHierarchy.GreyBox.enforce(syProc).addContained(ForSyDeHierarchy.Visualizable.enforce(func));
            // add output port and connect to signal
            syProc.addPorts("oport1");
            syProc.outputPorts(List.of("oport1"));
            systemGraph.queryVertex(container.getIdentifier() + "_" + parameters.get(3).trim())
                    .flatMap(outSig -> ForSyDeHierarchy.SYSignal.tryView(systemGraph, outSig))
                    .ifPresentOrElse(outSig -> outSig.producer("oport1", syProc, ForSyDeHierarchy.EdgeTraits.VisualConnection), () ->
                            systemGraph.connect(syProc, container, "oport1", parameters.get(3).trim(), ForSyDeHierarchy.EdgeTraits.VisualConnection, ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge)
                    );
            // connect child inputs and output
            for (int i = 0; i < func.outputPorts().size(); i++) {
                systemGraph.connect(func, syProc, func.outputPorts().get(i), syProc.outputPorts().get(i), ForSyDeHierarchy.EdgeTraits.VisualConnection, ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge);
            }
            return syProc;
        } else if (constructorName.startsWith("ssink")) {
            var syProc = ForSyDeHierarchy.SYMap.enforce(systemGraph, systemGraph.newVertex(instName));
            var func = ForSyDeHierarchy.FunctionLikeEntity.enforce(inspectLeafFunction(container, sourceCode, parameters.get(0).trim(), instName));
            syProc.addCombFunctions(func);
            ForSyDeHierarchy.GreyBox.enforce(container).addContained(ForSyDeHierarchy.Visualizable.enforce(syProc));
            ForSyDeHierarchy.GreyBox.enforce(syProc).addContained(ForSyDeHierarchy.Visualizable.enforce(func));
            // add output port and connect to signal
            syProc.addPorts("iport1");
            syProc.inputPorts(List.of("iport1"));
            systemGraph.queryVertex(container.getIdentifier() + "_" + parameters.get(1).trim())
                    .flatMap(inSig -> ForSyDeHierarchy.SYSignal.tryView(systemGraph, inSig))
                    .ifPresentOrElse(inSig -> inSig.addConsumers("iport1", syProc, ForSyDeHierarchy.EdgeTraits.VisualConnection), () ->
                            systemGraph.connect(container, syProc, parameters.get(1).trim(), "iport1", ForSyDeHierarchy.EdgeTraits.VisualConnection, ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge)
                    );
            // connect child inputs and output
            for (int i = 0; i < func.inputPorts().size(); i++) {
                systemGraph.connect(syProc, func, syProc.inputPorts().get(i), func.inputPorts().get(i), ForSyDeHierarchy.EdgeTraits.VisualConnection, ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge);
            }
            return syProc;
        } else if (constructorName.startsWith("sconstant")) {
            var syProc = ForSyDeHierarchy.SYMap.enforce(systemGraph, systemGraph.newVertex(instName));
            var func = ForSyDeHierarchy.FunctionLikeEntity.enforce(systemGraph, systemGraph.newVertex(instName + "_func"));
            func.addPorts("res");
            func.outputPorts().add("res");
            var impls = ForSyDeHierarchy.HasANSICImplementations.enforce(func);
            impls.returnPort("res");
            impls.inlinedCodes().put("generic", "res = %s;".formatted(parameters.get(0)));
            syProc.addCombFunctions(func);
            ForSyDeHierarchy.GreyBox.enforce(container).addContained(ForSyDeHierarchy.Visualizable.enforce(syProc));
            ForSyDeHierarchy.GreyBox.enforce(syProc).addContained(ForSyDeHierarchy.Visualizable.enforce(func));
            // add output port and connect to signal
            syProc.addPorts("res");
            syProc.outputPorts().add("res");
            systemGraph.queryVertex(container.getIdentifier() + "_" + parameters.get(2).trim())
                    .flatMap(outSig -> ForSyDeHierarchy.SYSignal.tryView(systemGraph, outSig))
                    .ifPresentOrElse(outSig -> outSig.producer("res", syProc, ForSyDeHierarchy.EdgeTraits.VisualConnection), () ->
                            systemGraph.connect(syProc, container, "res", parameters.get(2).trim(), ForSyDeHierarchy.EdgeTraits.VisualConnection, ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge)
                    );
            // connect child inputs and output
            for (int i = 0; i < func.outputPorts().size(); i++) {
                systemGraph.connect(func, syProc, func.outputPorts().get(i), syProc.outputPorts().get(i), ForSyDeHierarchy.EdgeTraits.VisualConnection, ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge);
            }
            return syProc;
        }
        return null;
    }

    default VertexViewer collectContents(VertexViewer container, String regionSource, String sourceCode, String clsName, String instName) {
        var systemGraph = container.getViewedSystemGraph();
        // enforce a certain container or another
        if (regionSource.contains("SY") && !regionSource.contains("SDF")) {
            ForSyDeHierarchy.SYProcess.enforce(container);
        }
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
                ForSyDeHierarchy.SYProcess.tryView(container).ifPresent(syProcessViewer -> syProcessViewer.inputPorts().add(name.trim()));
            }
        });
        // get all output ports
        outPortPattern.matcher(regionSource).results().forEach(matchResult -> {
            for (var name : matchResult.group(3).split(",")) {
                container.addPorts(name.trim());
                ForSyDeHierarchy.SYProcess.tryView(container).ifPresent(syProcessViewer -> syProcessViewer.outputPorts().add(name.trim()));
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
            var proc = collectProcessConstructor(container, sourceCode, subInstName, matchResult.group(2).trim(), Arrays.stream(matchResult.group(4).split(", ")).toList());
        });
        // get the port connections
        connectPortAndSignals.matcher(regionSource).results().forEach(matchResult -> {
            var procName = instName.isBlank() ? matchResult.group(1).trim() : instName + "_" + matchResult.group(1).trim();
            var sigName = instName.isBlank() ? matchResult.group(3).trim() : instName + "_" + matchResult.group(3).trim();
            systemGraph.queryVertex(procName).ifPresent(procVertex -> {
                systemGraph.queryVertex(sigName).ifPresentOrElse(sigVertex -> {
                    ForSyDeHierarchy.SYProcess.tryView(systemGraph, procVertex).ifPresent(syProcVeiwer -> {
                        ForSyDeHierarchy.SYSignal.tryView(systemGraph, sigVertex).ifPresent(sySignalViewer -> {
                            if (syProcVeiwer.outputPorts().contains(matchResult.group(2).trim())) {
                                sySignalViewer.producer(matchResult.group(2).trim(), syProcVeiwer, ForSyDeHierarchy.EdgeTraits.VisualConnection);
                            } else if (syProcVeiwer.inputPorts().contains(matchResult.group(2).trim())) {
                                sySignalViewer.addConsumers(matchResult.group(2).trim(), syProcVeiwer, ForSyDeHierarchy.EdgeTraits.VisualConnection);
                            }
                        });
                    });
                }, () -> { // it is a port in the end
                    ForSyDeHierarchy.SYProcess.tryView(systemGraph, procVertex).ifPresent(syProcessViewer -> {
                        if (syProcessViewer.outputPorts().contains(matchResult.group(2).trim())) {
                            systemGraph.connect(syProcessViewer, container, matchResult.group(2).trim(), matchResult.group(3).trim(), ForSyDeHierarchy.EdgeTraits.VisualConnection, ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge);
                        } else if (syProcessViewer.inputPorts().contains(matchResult.group(2).trim())) {
                            systemGraph.connect(container, syProcessViewer, matchResult.group(3).trim(), matchResult.group(2).trim(), ForSyDeHierarchy.EdgeTraits.VisualConnection, ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge);
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
    static Pattern instantiateNewProcessWithMake = Pattern.compile("(?:[<>:\\w]+ (?<varname>\\w+) = )?[<>\\w]+::make_(?<clsName>\\w+)\\(\"(?<instName>\\w+)\"(?:, (?<parameters>[\\w, ]+))?\\);");

    static Pattern connectPortAndSignals = Pattern.compile("(?<procName>\\w+)->(?<portName>\\w+)\\((?<sigName>\\w+)\\);");
}
