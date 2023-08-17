package forsyde.io.bridge.forsyde.systemc;

import forsyde.io.core.SystemGraph;
import forsyde.io.lib.ForSyDeHierarchy;

import java.util.ArrayList;
import java.util.HashSet;
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
            ForSyDeHierarchy.MoCEntity.enforce(systemGraph, systemGraph.newVertex(instName));
            inspectModuleDeclaration(systemGraph, sourceCode, clsName, instName);
        });
        return systemGraph;
    }

    default SystemGraph inspectModuleDeclaration(SystemGraph systemGraph, String sourceCode, String clsName, String instName) {
        var mainStart = sourceCode.indexOf("SC_MODULE(" + clsName + ")");
        var parenStart = sourceCode.indexOf('{', mainStart + 1);
        var parenEnd = getRegionEnd(sourceCode, parenStart + 1);
        // get all signals
        signalDefPattern.matcher(sourceCode.substring(parenStart, parenEnd)).results().forEach(matchResult -> {
            for (var name: matchResult.group(3).split(",")) {
                var sigName = instName + "_" + name.trim();
                if (matchResult.group(1).equalsIgnoreCase("SY")) {
                    var sig = ForSyDeHierarchy.SYSignal.enforce(systemGraph, systemGraph.newVertex(sigName));
                } else if (matchResult.group(1).equalsIgnoreCase("SDF")) {
                    var sig = ForSyDeHierarchy.SDFChannel.enforce(systemGraph, systemGraph.newVertex(sigName));
                }
            }
        });
        return systemGraph;
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
}
