import forsyde.io.core.ModelHandler
import forsyde.io.frontsyde.sdf.SDF
import forsyde.io.visual.kgt.drivers.KGTDriver

class Prototype {
    
    static void main(String[] args) {

        def system = SDF.create {

            def aa = actor {
                state "s" type Integer value 1
                port "o1" produces 1
                port "o2" produces 1
                port "in1" consumes 2
                inlinedBody """
                    s = in1[0] + 1; 
                    o1[0] = s + in[1];
                    o2[0] = in[1] - s; 
                    """
            }

            def bb = actor("second") {
                port "in1" consumes 1
            }

            def cc = actor("third") {
                port "in1" consumes 3
            }

            aa.o1 >> bb.in1
            aa.o2 >> 2 >> cc.in1
        }


        def handler = new ModelHandler().registerDriver(new KGTDriver())
        handler.writeModel(system.build(), "visual.kgt")
        handler.writeModel(system.build(), "processed.fiodl")

    }
}