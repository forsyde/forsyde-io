import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import javax.xml.bind.JAXBException;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import forsyde.io.java.Edge;
import forsyde.io.java.ForSyDeModel;
import forsyde.io.java.ForSyDeModelReader;
import forsyde.io.java.ForSyDeModelWriter;
import forsyde.io.java.Port;
import forsyde.io.java.Vertex;
import forsyde.io.java.types.application.Constructs;
import forsyde.io.java.types.application.Description;
import forsyde.io.java.types.application.Input;
import forsyde.io.java.types.application.Output;
import forsyde.io.java.types.application.Process;
import forsyde.io.java.types.application.Reads;
import forsyde.io.java.types.application.SDFComb;
import forsyde.io.java.types.application.Signal;
import forsyde.io.java.types.application.Writes;

public class SobelCaseTest {
	
	@Test
	public void createModel() throws JsonGenerationException, JsonMappingException, IOException, JAXBException {
		
		ForSyDeModel model = new ForSyDeModel();
		
		// create all the constructors
		Vertex getPxCons = Vertex.builder()
				.identifier("getPxCons")
				.type(new SDFComb())
				.ports(Set.of(
						Port.builder().identifier("mapped").type(new Input()).build(),
						Port.builder().identifier("constructed").type(new Output()).build()
						))
				.build();
		model.addVertex(getPxCons);
		Vertex GxCons = Vertex.builder()
				.identifier("GxCons")
				.type(new SDFComb())
				.ports(Set.of(
						Port.builder().identifier("mapped").type(new Input()).build(),
						Port.builder().identifier("constructed").type(new Output()).build()
						))
				.build();
		model.addVertex(GxCons);
		Vertex GyCons = Vertex.builder()
				.identifier("GyCons")
				.type(new SDFComb())
				.ports(Set.of(
						Port.builder().identifier("mapped").type(new Input()).build(),
						Port.builder().identifier("constructed").type(new Output()).build()
						))
				.build();
		model.addVertex(GyCons);
		Vertex AbsCons = Vertex.builder()
				.identifier("AbsCons")
				.type(new SDFComb())
				.ports(Set.of(
						Port.builder().identifier("mapped").type(new Input()).build(),
						Port.builder().identifier("constructed").type(new Output()).build()
						))
				.build();
		model.addVertex(AbsCons);
		
		Vertex getPx = Vertex.builder()
				.identifier("getPx")
				.type(new Process())
				.ports(Set.of(
						Port.builder().identifier("constructor").type(new Description()).build()
						))
				.build();
		model.addVertex(getPx);
		Vertex Gx = Vertex.builder()
				.identifier("Gx")
				.type(new Process())
				.ports(Set.of(
						Port.builder().identifier("constructor").type(new Description()).build()
						))
				.build();
		model.addVertex(Gx);
		Vertex Gy = Vertex.builder()
				.identifier("Gy")
				.type(new Process())
				.ports(Set.of(
						Port.builder().identifier("constructor").type(new Description()).build()
						))
				.build();
		model.addVertex(Gy);
		Vertex Abs = Vertex.builder()
				.identifier("Abs")
				.type(new Process())
				.ports(Set.of(
						Port.builder().identifier("constructor").type(new Description()).build()
						))
				.build();
		model.addVertex(Abs);
		
		Edge getPxEdge = Edge.builder()
				.sourceNodeId(getPxCons.identifier)
				.targetNodeId(getPx.identifier)
				.sourceNodePortId(Optional.of("constructed"))
				.targetNodePortId(Optional.of("constructor"))
				.type(new Constructs())
				.build();
		model.addEdge(getPxCons, getPx, getPxEdge);
		Edge GxEdge = Edge.builder()
				.sourceNodeId(GxCons.identifier)
				.targetNodeId(Gx.identifier)
				.sourceNodePortId(Optional.of("constructed"))
				.targetNodePortId(Optional.of("constructor"))
				.type(new Constructs())
				.build();
		model.addEdge(GxCons, Gx, GxEdge);
		Edge GyEdge = Edge.builder()
				.sourceNodeId(GyCons.identifier)
				.targetNodeId(Gy.identifier)
				.sourceNodePortId(Optional.of("constructed"))
				.targetNodePortId(Optional.of("constructor"))
				.type(new Constructs())
				.build();
		model.addEdge(GyCons, Gy, GyEdge);
		Edge AbsEdge = Edge.builder()
				.sourceNodeId(AbsCons.identifier)
				.targetNodeId(Abs.identifier)
				.sourceNodePortId(Optional.of("constructed"))
				.targetNodePortId(Optional.of("constructor"))
				.type(new Constructs())
				.build();
		model.addEdge(AbsCons, Abs, AbsEdge);

		Vertex gxSig = Vertex.builder()
				.identifier("gxsig")
				.type(new Signal())
				.ports(Set.of(
						Port.builder().identifier("fifoIn").type(new Description()).build(),
						Port.builder().identifier("fifoOut").type(new Description()).build()
						))
				.build();
		model.addVertex(gxSig);
		Vertex gySig = Vertex.builder()
				.identifier("gysig")
				.type(new Signal())
				.ports(Set.of(
						Port.builder().identifier("fifoIn").type(new Description()).build(),
						Port.builder().identifier("fifoOut").type(new Description()).build()
						))
				.build();
		model.addVertex(gySig);
		Vertex absxSig = Vertex.builder()
				.identifier("absxsig")
				.type(new Signal())
				.ports(Set.of(
						Port.builder().identifier("fifoIn").type(new Description()).build(),
						Port.builder().identifier("fifoOut").type(new Description()).build()
						))
				.build();
		model.addVertex(absxSig);
		Vertex absySig = Vertex.builder()
				.identifier("absysig")
				.type(new Signal())
				.ports(Set.of(
						Port.builder().identifier("fifoIn").type(new Description()).build(),
						Port.builder().identifier("fifoOut").type(new Description()).build()
						))
				.build();
		model.addVertex(absySig);
		
		Edge getPxTogx = Edge.builder()
				.sourceNodeId(getPx.identifier)
				.targetNodeId(gxSig.identifier)
				.sourceNodePortId(Optional.of("gx"))
				.targetNodePortId(Optional.of("fifoIn"))
				.type(new Writes())
				.build();
		model.addEdge(getPx, gxSig, getPxTogx);
		Edge getPxTogy = Edge.builder()
				.sourceNodeId(getPx.identifier)
				.targetNodeId(gySig.identifier)
				.sourceNodePortId(Optional.of("gx"))
				.targetNodePortId(Optional.of("fifoIn"))
				.type(new Writes())
				.build();
		model.addEdge(getPx, gySig, getPxTogy);
		Edge gxtoGx = Edge.builder()
				.sourceNodeId(gxSig.identifier)
				.targetNodeId(Gx.identifier)
				.sourceNodePortId(Optional.of("fifoOut"))
				.targetNodePortId(Optional.of("gx"))
				.type(new Reads())
				.build();
		model.addEdge(gxSig, Gx, gxtoGx);
		Edge gyToGy = Edge.builder()
				.sourceNodeId(gySig.identifier)
				.targetNodeId(Gy.identifier)
				.sourceNodePortId(Optional.of("fifoOut"))
				.targetNodePortId(Optional.of("gy"))
				.type(new Reads())
				.build();
		model.addEdge(gySig, Gy, gyToGy);
		Edge gxToabsx = Edge.builder()
				.sourceNodeId(Gx.identifier)
				.targetNodeId(absxSig.identifier)
				.sourceNodePortId(Optional.of("resx"))
				.targetNodePortId(Optional.of("fifoIn"))
				.type(new Writes())
				.build();
		model.addEdge(Gx, absxSig, gxToabsx);
		Edge gyToabsy = Edge.builder()
				.sourceNodeId(Gy.identifier)
				.targetNodeId(absySig.identifier)
				.sourceNodePortId(Optional.of("resy"))
				.targetNodePortId(Optional.of("fifoIn"))
				.type(new Writes())
				.build();
		model.addEdge(Gy, absySig, gyToabsy);
		Edge absxToAbs = Edge.builder()
				.sourceNodeId(absxSig.identifier)
				.targetNodeId(Abs.identifier)
				.sourceNodePortId(Optional.of("fifoOut"))
				.targetNodePortId(Optional.of("resx"))
				.type(new Reads())
				.build();
		model.addEdge(absxSig, Abs, absxToAbs);
		Edge absyToAbs = Edge.builder()
				.sourceNodeId(absySig.identifier)
				.targetNodeId(Abs.identifier)
				.sourceNodePortId(Optional.of("fifoOut"))
				.targetNodePortId(Optional.of("resy"))
				.type(new Reads())
				.build();
		model.addEdge(absySig, Abs, absyToAbs);

		Vertex sobel = Vertex.builder()
				.identifier("sobel")
				.type(new Process())
				.ports(Set.of(
						
						))
				.build();
		model.addVertex(sobel);

		
		ForSyDeModelWriter writer = new ForSyDeModelWriter();
		writer.write(model, "sobelTestCase.pro");
	}
	
	@Test
	public void readModel() throws IOException {
		ForSyDeModelReader reader = new ForSyDeModelReader();
		ForSyDeModel model = reader.read("sobelTestCase.pro");
		System.out.println(model.toString());
	}

}
