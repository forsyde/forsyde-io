package forsyde.io.bridge.forsyde.shallow.drivers;

import forsyde.io.bridge.forsyde.shallow.haskell.ForSyDeShallowHaskellVisitor;
import forsyde.io.bridge.forsyde.shallow.haskell.HaskellLexer;
import forsyde.io.bridge.forsyde.shallow.haskell.HaskellParser;
import forsyde.io.core.SystemGraph;
import forsyde.io.core.drivers.ModelDriver;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class ForSyDeShallowDriver extends ForSyDeShallowHaskellVisitor implements ModelDriver {
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
        final CharStream charStream = CharStreams.fromStream(in);
        final HaskellLexer haskellLexer = new HaskellLexer(charStream);
        final CommonTokenStream tokens = new CommonTokenStream(haskellLexer);
        final HaskellParser parser = new HaskellParser(tokens);
        return visitModule(parser.module());
    }

    @Override
    public void writeModel(SystemGraph model, OutputStream out) throws Exception {

    }
}
