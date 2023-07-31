package shallow;

import forsyde.io.java.bridge.forsyde.shallow.drivers.ForSyDeShallowDriver;
import forsyde.io.java.drivers.ModelHandler;
import forsyde.io.java.graphviz.drivers.GraphVizDriver;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class ForSyDeShallowTests {

    static String haskellText ="""
module SDF_Intro where

import ForSyDe.Shallow

-- Netlist
system s_in = s_out
  where
    (s_1, s_2) = a_1 s_in s_3
    s_3 = d_1 s_2
    s_out = a_2 s_1

-- Process specification
a_1 s1 s2 = actor22SDF (1, 1) (1, 1) add s1 s2

d_1 s = delaySDF [0] s

a_2 s = actor11SDF 3 1 average s

-- Function definition
add [x] [y] = ([x + y], [x + y])

average [x1, x2, x3] = [(x1 + x2 + x3) / 3.0]
""";
    @Test
    public void testParsing() throws Exception {
        var driver = new ForSyDeShallowDriver();
        var systemGraph = driver.loadModel(new ByteArrayInputStream(haskellText.getBytes()));
        System.out.println(systemGraph);

    }
}
