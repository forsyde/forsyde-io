package forsyde.io.frontsyde.sdf

import forsyde.io.core.SystemGraph
import forsyde.io.core.Trait


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

    List<SDFActorDef> actorDefs = new ArrayList<>()

    def static create(@DelegatesTo(SDF) closure) {
        SDF sdf = new SDF();
        def code = closure.rehydrate(sdf, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
        return sdf
    }

    // Closure for instantiating new SDF actors
    def actor(@DelegatesTo(SDFActorDef) Closure closure) {
        SDFActorDef actorSDF = new SDFActorDef()
        def code = closure.rehydrate(actorSDF, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
        actorDefs.add(actorSDF)
        return actorSDF
    }

    def actor(@DelegatesTo.Target String id, @DelegatesTo(SDFActorDef) Closure closure) {
        SDFActorDef actorSDF = new SDFActorDef()
        actorSDF.setIdentifier_(id)
        def code = closure.rehydrate(actorSDF, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
        actorDefs.add(actorSDF)
        return actorSDF
    }

//    def Actor(closure) {
//        SDFActorDef actorSDF = new SDFActorDef()
//        closure.delegate = actorSDF
//        closure()
//        return actorSDF
//    }

    // Closure for creating the model (systemgraph)
//    def model(closure) {
//        SDF modelSDF = new SDF()
//        closure.delegate = modelSDF
//        closure()
//        return modelSDF.model
//    }
    
    // Set Unique Vertex identifier
//    def id = {String identifier ->
//        this.identifier = identifier
//    }
    
    // Set Vertex properties
//    def property = {String name, def s ->
//        this.properties.put(name, VertexProperty.create(s))
//    }
    
    // Set Vertex traits
//    def traits = {Trait t ->
//        this.vertexTraits.add(t)
//    }

    SystemGraph build(SystemGraph model = new SystemGraph()) {
        for (SDFActorDef actorDef : actorDefs) {
            actorDef.build(model)
        }
        return model
    }


}

