package forsyde.io.frontsyde.sdf

class SDFActorDefPort {

    SDFActorDef ref
    String portName
    int delay = 0

    SDFActorDefPort(SDFActorDef ref, String portName) {
        this.ref = ref
        this.portName = portName
    }

    def to(SDFActorDefPort other) {
        ref.connected.add(Tuple.tuple(portName, other.ref, other.portName, delay))
        delay = 0
        return this
    }

    def to(int tok) {
        delay = tok
        return this
    }

    def rightShift(SDFActorDefPort other) {
        to(other)
    }

    def rightShift(int other) {
        to(other)
    }

}