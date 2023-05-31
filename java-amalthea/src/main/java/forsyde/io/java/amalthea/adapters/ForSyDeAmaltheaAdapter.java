package forsyde.io.java.amalthea.adapters;

import forsyde.io.java.adapters.ModelAdapter;
import forsyde.io.java.amalthea.adapters.mixins.AmaltheaToForSyDeAdapter;
import forsyde.io.java.amalthea.adapters.mixins.ForSyDeToAmaltheaAdapter;
import forsyde.io.java.core.SystemGraph;
import org.eclipse.app4mc.amalthea.model.Amalthea;
import org.eclipse.app4mc.amalthea.model.AmaltheaFactory;

public class ForSyDeAmaltheaAdapter implements ModelAdapter<Amalthea> {

	protected AmaltheaToForSyDeAdapter amaltheaToForSyDeAdapter = new AmaltheaToForSyDeAdapter();
	protected ForSyDeToAmaltheaAdapter forSyDeToAmaltheaAdapter = new ForSyDeToAmaltheaAdapter();

	@Override
	public SystemGraph convert(Amalthea inputModel) {
		final SystemGraph systemGraph = new SystemGraph();
		amaltheaToForSyDeAdapter.convert(inputModel, systemGraph);
		return systemGraph;
	}

	@Override
	public Amalthea convert(SystemGraph inputModel) {
		Amalthea target = AmaltheaFactory.eINSTANCE.createAmalthea();
		forSyDeToAmaltheaAdapter.convert(inputModel, target);
		return target;
	}

}
