package forsyde.io.frontsyde.sdf


/**
 * @author     Joakim Savegren and Joar Edling, 2022
 *             Rodolfo Jordao, 2022-
 *
 *              A formal system modeling workbench in the Java ecosystem
 *              in the form of a groovy DSL.
 *
 *              The SDF class contains the relevant methods for describing SDF actors in ForSyDe
 *              and creating the systemgraph using the ForSyDe IO Java core. 
 *
 */

class SDF {

    String namedState
    String identifier
    int[] state_array
    Set<String> ports = new HashSet<>()
    Set<Trait> vertexTraits = new HashSet<>()
    Map<String, VertexProperty> properties = new HashMap<>()
    HashMap<String, Integer> production = new HashMap<>()
    HashMap<String, Integer> consumption = new HashMap<>()
    ForSyDeSystemGraph model = new ForSyDeSystemGraph()
    

    // Closure for instantiating new SDF actors
    def static actor(closure) {
        SDF actorSDF = new SDF()
        closure.delegate = actorSDF
        closure()
        return actorSDF.createVertex(actorSDF)
    }

    def static Actor(closure) {
        SDFActorDef actorSDF = new SDFActorDef()
        closure.delegate = actorSDF
        closure()
        return actorSDF
    }

    // Closure for creating the model (systemgraph)
    def static model(closure) {
        SDF modelSDF = new SDF()
        closure.delegate = modelSDF
        closure()
        return modelSDF.model 
    }
    
    // Set Unique Vertex identifier
    def id = {String identifier ->
        this.identifier = identifier
    }
    
    // Set Vertex properties
    def property = {String name, def s ->
        this.properties.put(name, VertexProperty.create(s))
    }
    
    // Set Vertex traits
    def traits = {Trait t -> 
        this.vertexTraits.add(t)
    }

    // Adds the vertices to the graph
    // The method also checks if there is a specified state port. In that case a connection is established between the state_in and state_out 
    def add = {Vertex... v ->
        v.each {it -> 
            this.model.addVertex(it)
            it.ports.each {p ->
                if(p.endsWith("_state_in")) {
                    it.ports.each {o ->
                        if(o.endsWith("_state_out")) {
                            channelConnect(it, it, p, o, 1)
                        }
                    }
                }
            }
        }
    }

    // Name a state for a Vertex
    def state = {String name ->
        this.namedState = name
    }

    // State array
    def states = {int number ->
        this.state_array = new int[number]
    }

    // Defining ports and their respective consumption/production rates
    def port(String... s) {
        [consumes: {int... tokens ->
            for(int i = 0; i < s.length; i++) {
                this.ports.add(s[i])
                this.consumption.put(s[i], tokens[i])
            } 
        }, 
        produces: {int... tokens ->
            for(int i = 0; i < s.length; i++) {
                this.ports.add(s[i])
                this.production.put(s[i], tokens[i])
            }
        }]
    }

    // Creates a channel and defining the number of initial tokens
    public static Vertex createSDFChannel(String id, int initialTokens) {
        Vertex v = new Vertex(id)
        SDFChannel sdfChannel = SDFChannel.enforce(v)
        if(initialTokens > 0)
            sdfChannel.setNumOfInitialTokens(initialTokens)
        return sdfChannel.getViewedVertex()
    }

    // Connecting two vertices with a delay channel. For instance: actor_a to actor_b delayed 2
    // would create 2 initial tokens on the channel.
    def connectDelay(def... src) {
        [to: {def... dest ->
            [delayed: {int delay ->
                
                // with src and dest port
                if(src.length == 2 && dest.length == 2) {
                    channelConnect(src[0], dest[0], src[1], dest[1], delay)
                }
                // with src port only
                else if(src.length == 2 && dest.length == 1) {
                    channelConnect(src[0], dest[0], src[1], delay)
                }
                // with no ports
                else if(src.length == 1 && dest.length == 1) {
                    channelConnect(src[0], dest[0], delay)
                }
            }]
        }]
    }

    // Connecting two vertices without delay
    def connect(def... src) {
        [to: {def... dest ->

            // with src and dest port
            if(src.length == 2 && dest.length == 2) {
                channelConnect(src[0], dest[0], src[1], dest[1], 0)
            }
            // with src port only
            else if(src.length == 2 && dest.length == 1) {
                channelConnect(src[0], dest[0], src[1], 0)
            }
            // with no ports
            else if(src.length == 1 && dest.length == 1) {
                channelConnect(src[0], dest[0], 0)
            }
        }]
    }

    // Enables connection of two vertices with no specific ports 
    // For instance: actor_a to actor_b delayed a, b
    private void channelConnect(Vertex src, Vertex dest, int initialTokens) {
    
        Vertex channel = SDF.createSDFChannel("$src.identifier"+"_"+"$dest.identifier"+"_channel_" + initialTokens + "_tokens", initialTokens)
        this.model.addVertex(channel)
        this.model.connect(src, channel, EdgeTrait.MOC_SDF_SDFDATAEDGE)
        this.model.connect(channel, dest, "consumer", EdgeTrait.MOC_SDF_SDFDATAEDGE)
    }
    
    // Enables connection of two vertices with specific src port but no dest port 
    // For instance: actor_a "out1" to actor_b delayed a, b
    private void channelConnect(Vertex src, Vertex dest, String s, int initialTokens) {
        
        Vertex channel = SDF.createSDFChannel("$src.identifier"+"_$s"+"_"+"$dest.identifier"+"_channel_" + initialTokens + "_tokens", initialTokens)
        this.model.addVertex(channel)
        this.model.connect(src, channel, s, "producer", EdgeTrait.MOC_SDF_SDFDATAEDGE)
        this.model.connect(channel, dest, "consumer", EdgeTrait.MOC_SDF_SDFDATAEDGE)
    }

    // Enables connection of two vertices with specific src and dest port
    // For instance: actor_a "out1" to actor_b "in2" delayed a, b
    private void channelConnect(Vertex src, Vertex dest, String s, String d, int initialTokens) {
        
        Vertex channel = SDF.createSDFChannel("$src.identifier"+"_$s"+"_"+"$dest.identifier"+"_$d"+"_channel_" + initialTokens + "_tokens", initialTokens)
        this.model.addVertex(channel)
        this.model.connect(src, channel, s, "producer", EdgeTrait.MOC_SDF_SDFDATAEDGE)
        this.model.connect(channel, dest, "consumer", d, EdgeTrait.MOC_SDF_SDFDATAEDGE)
    }

    // Creates a new SDF actor
    private Vertex createVertex(SDF actor) {
        final Vertex newSDFactor = new Vertex(actor.identifier)
        actor.ports.each {p ->
            newSDFactor.ports.add(p)
        }

        if(namedState)
            newSDFactor.ports.addAll(namedState + "_state_in", namedState + "_state_out")
        final SDFActor sdf = SDFActor.enforce(newSDFactor)
        sdf.setConsumption(actor.consumption)
        sdf.setProduction(actor.production)
        return sdf.getViewedVertex()
    }

}

