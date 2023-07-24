package forsyde.io.frontsyde.sdf

import forsyde.io.core.SystemGraph
import forsyde.io.core.Vertex
import forsyde.io.lib.ForSyDeHierarchy
import forsyde.io.lib.behavior.moc.sdf.SDFActor
import forsyde.io.lib.behavior.moc.sdf.SDFActorViewer
import forsyde.io.lib.behavior.moc.sdf.SDFChannelViewer

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

    def port(String s) {
        [consumes: {int tokens ->
            this.portNames_.add(s)
            this.consumption.put(s, tokens)
        },
         produces: {int tokens ->
             this.portNames_.add(s)
             this.production.put(s, tokens)
         }]
    }

    def build(SystemGraph model = new SystemGraph()) {
        // make the highest random identifier possible
        if (identifier_ == null) {
            def counted = model.vertexSet().stream().map(v -> v.getIdentifier()).filter(s -> s.contains("sdfActor"))
            .count()
            identifier_ = "sdfActor${counted}"
        }
        final Vertex newSDFactor = model.newVertex(identifier_)
        final SDFActorViewer sdfActor = ForSyDeHierarchy.SDFActor.enforce(model, newSDFactor)
        ForSyDeHierarchy.Visualizable.enforce(sdfActor)
        ForSyDeHierarchy.VisualizableWithProperties.enforce(sdfActor).visualizedPropertiesNames(List.of("consumption", "production"))
        newSDFactor.addPorts(portNames_)
        for (int i = 0; i < states_.size(); i++) {
            def (stateName, stateType, stateVal) = states_[i]
            newSDFactor.addPort(stateName + "_in")
            newSDFactor.addPort(stateName + "_out")

            // create the channels with one token of delay
            final Vertex stateChannel = new Vertex("${identifier_}_${stateName}")
            final SDFChannelViewer sdfChannel = ForSyDeHierarchy.SDFChannel.enforce(model, stateChannel)
            ForSyDeHierarchy.Visualizable.enforce(sdfChannel)
            ForSyDeHierarchy.VisualizableWithProperties.enforce(sdfChannel).visualizedPropertiesNames(List.of("numInitialTokens"))
            model.addVertex(stateChannel)
            sdfChannel.numInitialTokens(1)
            sdfChannel.producer(stateName + "_out", sdfActor, ForSyDeHierarchy.EdgeTraits.VisualConnection)
            sdfChannel.consumer(stateName + "_in", sdfActor, ForSyDeHierarchy.EdgeTraits.VisualConnection)
            production.put(stateName + "_out", 1)
            consumption.put(stateName + "_in", 1)
        }

        sdfActor.consumption(consumption)
        sdfActor.production(production)

        if (body_ != null) {
            def bodyVertex = model.newVertex("${identifier_}_body")
            def executable = ForSyDeHierarchy.HasANSICImplementation.enforce(model, bodyVertex)
            executable.inlinedCode(body_)
            ForSyDeHierarchy.VisualizableWithProperties.enforce(executable).visualizedPropertiesNames(List.of("inlinedCode", "inputArgumentPorts", "outputArgumentPorts", "returnPort"))
            sdfActor.addCombFunctions(ForSyDeHierarchy.BehaviourEntity.enforce(executable))
            ForSyDeHierarchy.GreyBox.enforce(sdfActor).addContained(ForSyDeHierarchy.Visualizable.enforce(executable))
            for (String p : consumption.keySet()) {
                executable.addPorts(p)
                model.connect(sdfActor, executable, p, p, ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge, ForSyDeHierarchy.EdgeTraits.VisualConnection)
            }
            executable.inputArgumentPorts(consumption.keySet().toList())
            for (String p : production.keySet()) {
                executable.addPorts(p)
                model.connect(executable, sdfActor, p, p, ForSyDeHierarchy.EdgeTraits.BehaviourCompositionEdge, ForSyDeHierarchy.EdgeTraits.VisualConnection)
            }
            executable.outputArgumentPorts(production.keySet().toList())
            executable.addPorts("res")
            executable.returnPort("res")
        }

        for (def con : connected) {
            def (srcPort, oActorDef, dstPort, tok) = con
            sdfActor.addPorts(srcPort)
            def oActor = oActorDef.build(model)
            oActor.addPorts(dstPort)
            // create middle sdfChannel
            final Vertex channel = model.newVertex("${identifier_}_${oActor.getIdentifier()}")
            final SDFChannelViewer sdfChannel = ForSyDeHierarchy.SDFChannel.enforce(model, channel)
            ForSyDeHierarchy.Visualizable.enforce(sdfChannel)
            ForSyDeHierarchy.VisualizableWithProperties.enforce(sdfChannel).visualizedPropertiesNames(List.of("numInitialTokens"))
            sdfChannel.numInitialTokens(tok)
            sdfChannel.producer(srcPort, sdfActor, ForSyDeHierarchy.EdgeTraits.VisualConnection)
            sdfChannel.consumer(dstPort, oActor, ForSyDeHierarchy.EdgeTraits.VisualConnection)
        }

        return sdfActor
    }

    def inlinedBody(String b) {
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
            return new SDFActorDefPort(this, name)
        }
    }

}