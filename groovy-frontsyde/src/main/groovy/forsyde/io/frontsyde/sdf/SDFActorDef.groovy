package forsyde.io.frontsyde.sdf

class SDFActorDef {

    String identifier_
    List<Tuple3<String, Class, Object>> states_ = []
    Set<String> portNames_ = new HashSet<>()
    HashMap<String, Integer> production = new HashMap<>()
    HashMap<String, Integer> consumption = new HashMap<>()
    String body_

    List<Tuple4<String, SDFActorDef, String, Integer>> connected = []


    def id(String identifier) {
        this.identifier_ = identifier
    }

    def state(String name) {
        def newState = new Tuple3(name, Void.TYPE, null)
        states_.add(newState)
        [type: {Class givenType ->
            states_.set(states_.size() - 1, new Tuple3(name, givenType, null))
            ["value": {Object v ->
                states_.set(states_.size() - 1, new Tuple3(name, givenType, v))
            }]
        }]
    }

    def port(String... s) {
        [consumes: {int... tokens ->
            for(int i = 0; i < s.length; i++) {
                this.portNames_.add(s[i])
                this.consumption.put(s[i], tokens[i])
            }
        },
         produces: {int... tokens ->
             for(int i = 0; i < s.length; i++) {
                 this.portNames_.add(s[i])
                 this.production.put(s[i], tokens[i])
             }
         }]
    }

    def build(ForSyDeSystemGraph model = new ForSyDeSystemGraph()) {
        // make the highest random identifier possible
        if (identifier_ == null) {
            def counted = model.vertexSet().stream().map(v -> v.getIdentifier()).filter(s -> s.contains("sdfActor"))
            .count()
            identifier_ = "sdfActor${counted}"
        }
        final Vertex newSDFactor = new Vertex(identifier_)
        final SDFActor sdfActor = SDFActor.enforce(newSDFactor)
        model.addVertex(newSDFactor)
        portNames_.each {p -> newSDFactor.ports.add(p)}
        for (int i = 0; i < states_.size(); i++) {
            def (stateName, stateType, stateVal) = states_[i]
            newSDFactor.ports.add(stateName + "_in")
            newSDFactor.ports.add(stateName + "_out")

            // create the channels with one token of delay
            final Vertex stateChannel = new Vertex("${identifier_}_${stateName}")
            final SDFChannel sdfChannel = SDFChannel.enforce(stateChannel)
            model.addVertex(stateChannel)
            sdfChannel.setNumOfInitialTokens(1)

            model.connect(newSDFactor, stateChannel, stateName + "_out", "producer", EdgeTrait.MOC_SDF_SDFDATAEDGE)
            model.connect(stateChannel, newSDFactor, "consumer", stateName + "_in", EdgeTrait.MOC_SDF_SDFDATAEDGE)

        }

        sdfActor.setConsumption(consumption)
        sdfActor.setProduction(production)

        if (body_ != null) {
            def bodyVertex = new Vertex("${identifier_}_body")
            def executable = ANSICBlackBoxExecutable.enforce(bodyVertex)
            model.addVertex(bodyVertex)
            executable.setInlinedCode(body_)
            sdfActor.setCombFunctionsPort(model, Set.of(executable))
        }

        return model
    }

    def body(String b) {
        body_ = b
    }

    def connect(String portName) {
        [to: { SDFActorDef actorDef ->
            [at: {String dstPort ->

            }]
        }]
    }

    def propertyMissing(String name) {
        if (portNames_.contains(name)) {
            return new SDFActorDef.SDFActorDefPort(this, name)
        }
    }

    class SDFActorDefPort {

        SDFActorDef ref
        String portName

        SDFActorDefPort(SDFActorDef ref, String portName) {
            this.ref = ref
            this.portName = portName
        }

        def to(SDFActorDef.SDFActorDefPort other) {
            ref.connected.add(Tuple.tuple(portName, other.ref, other.portName, 0))
            ["delayed": {Integer d ->
                ref.connected.set(ref.connected.size() -1, Tuple.tuple(portName, other.ref, other.portName, d))
            }]
        }

        def rightShift(SDFActorDef.SDFActorDefPort other) {
            to(other)
        }

    }
}