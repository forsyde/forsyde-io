package forsyde.io.java.adapters;

import forsyde.io.java.adapters.amalthea.*;
import forsyde.io.java.core.ForSyDeSystemGraph;
import org.eclipse.app4mc.amalthea.model.Amalthea;
import org.eclipse.app4mc.amalthea.model.AmaltheaFactory;

public class ForSyDeAmaltheaAdapter implements ModelAdapter<Amalthea> {

	protected AmaltheaToForSyDeAdapter amaltheaToForSyDeAdapter = new AmaltheaToForSyDeAdapter();
	protected ForSyDeToAmaltheaAdapter forSyDeToAmaltheaAdapter = new ForSyDeToAmaltheaAdapter();

	@Override
	public ForSyDeSystemGraph convert(Amalthea inputModel) {
		final ForSyDeSystemGraph forSyDeSystemGraph = new ForSyDeSystemGraph();
		amaltheaToForSyDeAdapter.convert(inputModel, forSyDeSystemGraph);
		return forSyDeSystemGraph;
	}

	@Override
	public Amalthea convert(ForSyDeSystemGraph inputModel) {
		Amalthea target = AmaltheaFactory.eINSTANCE.createAmalthea();
		forSyDeToAmaltheaAdapter.convert(inputModel, target);
		return target;
	}

}
