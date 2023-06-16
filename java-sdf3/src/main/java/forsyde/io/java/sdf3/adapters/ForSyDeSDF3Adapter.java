package forsyde.io.java.sdf3.adapters;

import forsyde.io.java.adapters.ModelAdapter;
import forsyde.io.core.SystemGraph;
import forsyde.io.java.sdf3.adapters.mixins.elems.Sdf3;

public class ForSyDeSDF3Adapter implements ModelAdapter<Sdf3> {

    @Override
    public SystemGraph convert(Sdf3 inputModel) {
        final SDF3ToForSyDeAdapter sdf3ToForSyDeAdapter = new SDF3ToForSyDeAdapter();
        final SystemGraph systemGraph = new SystemGraph();
        sdf3ToForSyDeAdapter.fromActorsToVertexes(inputModel, systemGraph);
        sdf3ToForSyDeAdapter.fromChannelstoSignalsAndPrefix(inputModel, systemGraph);
        sdf3ToForSyDeAdapter.fromChannelsToEdges(inputModel, systemGraph);
        sdf3ToForSyDeAdapter.fromChannelPropertiesToSDFChannels(inputModel, systemGraph);
        sdf3ToForSyDeAdapter.fromActorPropertiesToSDFActor(inputModel, systemGraph);
        return systemGraph;
    }

    @Override
    public Sdf3 convert(SystemGraph inputModel) {
        final ForSyDeToSDF3Adapter forSyDeToSDF3Adapter = new ForSyDeToSDF3Adapter();
        final Sdf3 sdf3 = new Sdf3();
        sdf3.setType("sdf");
        sdf3.setVersion("1.0");
        forSyDeToSDF3Adapter.convertApplications(sdf3, inputModel);
        forSyDeToSDF3Adapter.convertArchitecture(sdf3, inputModel);
        forSyDeToSDF3Adapter.convertParameters(sdf3, inputModel);
        return sdf3;
    }
}
