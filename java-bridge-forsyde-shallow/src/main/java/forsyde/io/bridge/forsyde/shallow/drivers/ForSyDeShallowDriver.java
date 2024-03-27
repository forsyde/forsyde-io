package forsyde.io.bridge.forsyde.shallow.drivers;

import forsyde.io.core.SystemGraph;
import forsyde.io.core.drivers.ModelDriver;
import forsyde.io.lib.hierarchy.ForSyDeHierarchy;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ForSyDeShallowDriver implements ModelDriver {

    @Override
    public List<String> inputExtensions() {
        return List.of("hs");
    }

    @Override
    public List<String> outputExtensions() {
        return List.of();
    }

    @Override
    public SystemGraph loadModel(InputStream in) throws Exception {
        final SystemGraph model = new SystemGraph();
        final BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(in));
        while (reader.ready()) {
            var line = reader.readLine();
            var equalIdx = line.indexOf('=');
            if (equalIdx > -1) {
                var lhs = line.substring(0, equalIdx).trim().replaceAll(",\\s*", ",");
                var rhs = line.substring(equalIdx + 1).trim().replaceAll(",\\s*", ",");
                var lhsElems = lhs.split(" ");
                var rhsElems = rhs.split(" ");
                if (Arrays.stream(rhsElems).anyMatch(e -> e.contains("actor") && e.contains("SDF"))) {
                    var sdfActor = ForSyDeHierarchy.SDFActor.enforce(model, model.newVertex(lhsElems[0]));
                    var inputRates = new HashMap<String, Integer>();
                    if (rhsElems[1].contains("(")) {
                        var noParens = rhsElems[1].substring(1, rhsElems[1].length() - 1);
                        var rates = noParens.split(",");
                        for (int i = 0; i < rates.length; i++) {
                            var inputRate = Integer.parseInt(rates[i]);
                            sdfActor.addPorts("in" + i);
                            inputRates.put("in" + i, inputRate);
                        }
                    } else {
                        var inputRate = Integer.parseInt(rhsElems[1]);
                        sdfActor.addPorts("in0");
                        inputRates.put("in0", inputRate);
                    }
                    sdfActor.consumption(inputRates);
                    var outputRates = new HashMap<String, Integer>();
                    if (rhsElems[2].contains("(")) {
                        var noParens = rhsElems[2].substring(1, rhsElems[1].length() - 1);
                        var rates = noParens.split(",");
                        for (int i = 0; i < rates.length; i++) {
                            var outputRate = Integer.parseInt(rates[i]);
                            sdfActor.addPorts("out" + i);
                            outputRates.put("out" + i, outputRate);
                        }
                    } else {
                        var outputRate = Integer.parseInt(rhsElems[2]);
                        sdfActor.addPorts("out0");
                        outputRates.put("out0", outputRate);
                    }
                    sdfActor.production(outputRates);
                } else if (rhsElems[rhsElems.length - 1].contains("where")) {
                    var container = ForSyDeHierarchy.GreyBox.enforce(model, model.newVertex(lhsElems[0]));
                } else {
                    // function
                }
            }
            // simplify tuples
        }
        // final CharStream charStream = CharStreams.fromStream(in);
        // final HaskellLexer haskellLexer = new HaskellLexer(charStream);
        // final CommonTokenStream tokens = new CommonTokenStream(haskellLexer);
        // final HaskellParser parser = new HaskellParser(tokens);
        return model;
    }

    @Override
    public void writeModel(SystemGraph model, OutputStream out) throws Exception {

    }

    @Override
    public String printModel(SystemGraph model) throws Exception {
        return null;
    }
}
