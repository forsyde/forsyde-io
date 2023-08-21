package forsyde.io.bridge.forsyde.systemc;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.VertexViewer;
import forsyde.io.lib.ForSyDeHierarchy;

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
        collectContents(container, sourceCode.substring(parenStart + 1, parenEnd), sourceCode, clsName, instName);
        return container;
    }

    default VertexViewer collectContents(VertexViewer container, String resgionSource, String sourceCode, String clsName, String instName) {
        var systemGraph = container.getViewedSystemGraph();
        // get all signals
        signalDefPattern.matcher(resgionSource).results().forEach(matchResult -> {
            for (var name: matchResult.group(3).split(",")) {
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
        inPortPattern.matcher(resgionSource).results().forEach(matchResult -> {
            for (var name: matchResult.group(3).split(",")) {
                var portName = instName.isBlank() ? name.trim() : instName + "_" + name.trim();
                container.addPorts(portName);
            }
        });
        // get all output ports
        outPortPattern.matcher(resgionSource).results().forEach(matchResult -> {
            for (var name: matchResult.group(3).split(",")) {
                var portName = instName.isBlank() ? name.trim() : instName + "_" + name.trim();
                container.addPorts(portName);
            }
        });
        // get children instantiated processes
        instantiateNewProcess.matcher(resgionSource).results().forEach(matchResult -> {
            var subInstName = instName.isBlank() ? matchResult.group(3).trim() : instName + "_" + matchResult.group(3).trim();
            var subContainer = ForSyDeHierarchy.MoCEntity.enforce(systemGraph, systemGraph.newVertex(subInstName));
            var created = inspectModuleDeclaration(subContainer, sourceCode, matchResult.group(2), subInstName);
            ForSyDeHierarchy.GreyBox.enforce(container).addContained(ForSyDeHierarchy.Visualizable.enforce(created));
        });
        // get process constructors
        makeNewProcess.matcher(resgionSource).results().forEach(matchResult -> {
            var subInstName = instName.isBlank() ? matchResult.group(3).trim() : instName + "_" + matchResult.group(3).trim();
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
    static Pattern makeNewProcess = Pattern.compile("[<>:\\w]+ (?<varname>\\w+) = [<>\\w]+::make_(?<clsName>\\w+)\\(\"(?<instName>\\w+)\"(?:, (?<parameters>[\\w, ]+))?\\);");
}
