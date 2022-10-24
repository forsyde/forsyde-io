package forsyde.io.java.sdf3.adapters;

import forsyde.io.java.adapters.ModelAdapter;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.sdf3.adapters.mixins.elems.Sdf3;

public class ForSyDeSDF3Adapter implements ModelAdapter<Sdf3> {

    final ForSyDeToSDF3Adapter forSyDeToSDF3Adapter = new ForSyDeToSDF3Adapter();
    final SDF3ToForSyDeAdapter sdf3ToForSyDeAdapter = new SDF3ToForSyDeAdapter();

    @Override
    public ForSyDeSystemGraph convert(Sdf3 inputModel) {
        final ForSyDeSystemGraph forSyDeSystemGraph = new ForSyDeSystemGraph();
        sdf3ToForSyDeAdapter.fromActorsToVertexes(inputModel, forSyDeSystemGraph);
        sdf3ToForSyDeAdapter.fromChannelstoSignalsAndPrefix(inputModel, forSyDeSystemGraph);
        sdf3ToForSyDeAdapter.fromChannelsToEdges(inputModel, forSyDeSystemGraph);
        sdf3ToForSyDeAdapter.fromChannelPropertiesToSDFChannels(inputModel, forSyDeSystemGraph);
        sdf3ToForSyDeAdapter.fromActorPropertiesToSDFActor(inputModel, forSyDeSystemGraph);
        return forSyDeSystemGraph;
    }

    @Override
    public Sdf3 convert(ForSyDeSystemGraph inputModel) {
        final Sdf3 sdf3 = new Sdf3();
        forSyDeToSDF3Adapter.convertApplications(sdf3, inputModel);
        forSyDeToSDF3Adapter.convertArchitecture(sdf3, inputModel);
        forSyDeToSDF3Adapter.convertParameters(sdf3, inputModel);
        return sdf3;
    }
}
