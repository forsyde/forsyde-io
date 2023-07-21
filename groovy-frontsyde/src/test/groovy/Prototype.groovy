

import forsyde.io.frontsyde.sdf.SDF
import forsyde.io.java.core.*

class Prototype {
    
    static void main(String[] args) {

        final Vertex a = SDF.actor {
            id "a"
            state "convert"
            port "in1" consumes 2
            port "out1", "out2" produces 2, 2
        }

        final Vertex b = SDF.actor {
            id "b"
            port "in1", "in2", "in3" consumes 1, 2, 3
            port "out2", "Out3" produces 6, 3
        }

        final Vertex c = SDF.actor {
            id "actor82SDF"
            port "in1", "in2", "in3" consumes 1, 2, 3
            port "out1" produces 6
        }

        final Vertex d = SDF.actor {
            id "actorSDF"
            state "ForSyDe"
            port "state_in" consumes 4
            port "state_out" produces 6
        }

        ForSyDeSystemGraph model = SDF.model {
            add a, b, c, d
            connectDelay b, "out2" to c, "in2" delayed 1
            connectDelay a, "out2" to a, "in3" delayed 4
            connect c to d
            connect a, "out2" to c
        }
        println "model: " + model
       
        final Vertex getPx = SDF.actor {
            id "getPx"
            port "out1", "out2" produces 6, 6
        }

        final Vertex gx = SDF.actor {
            id "gx"
            port "in" consumes 6
            port "out" produces 1
        }

        final Vertex gy = SDF.actor {
            id "gy"
            port "in" consumes 6
            port "out" produces 1
        }

        final Vertex abs = SDF.actor {
            id "abs"
            port "in1", "in2" consumes 1, 1
        }

        ForSyDeSystemGraph sobelapplication = SDF.model {
            add getPx, gx, gy, abs
            connect getPx, "out1" to gx, "in"
            connect getPx, "out2" to gy, "in"
            connect gx, "out" to abs, "in1"
            connect gy, "out" to abs, "in2"
        }
        println "Sobel Application " + sobelapplication

        def aa = SDF.Actor {
            state "s" type Integer value 1
            port "o1" produces 2
            port "in1" consumes 3
            body "s = in1 + 1; o = s;"
        }

        def bb = SDF.Actor {
            id "second"
            port "in1" consumes 1
        }

        aa.o1 >> bb.in1


        println(aa.connected)
    }
}