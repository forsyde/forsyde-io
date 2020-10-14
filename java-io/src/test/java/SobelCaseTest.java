import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper.Builder;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import forsyde.io.java.Edge;
import forsyde.io.java.ForSyDeModel;
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
				.withIdentifier("getPxCons")
				.withType(new SDFComb())
				.withPorts(Set.of(
						Port.builder().withIdentifier("mapped").withType(new Input()).build(),
						Port.builder().withIdentifier("constructed").withType(new Output()).build()
						))
				.build();
		model.addVertex(getPxCons);
		Vertex GxCons = Vertex.builder()
				.withIdentifier("GxCons")
				.withType(new SDFComb())
				.withPorts(Set.of(
						Port.builder().withIdentifier("mapped").withType(new Input()).build(),
						Port.builder().withIdentifier("constructed").withType(new Output()).build()
						))
				.build();
		model.addVertex(GxCons);
		Vertex GyCons = Vertex.builder()
				.withIdentifier("GyCons")
				.withType(new SDFComb())
				.withPorts(Set.of(
						Port.builder().withIdentifier("mapped").withType(new Input()).build(),
						Port.builder().withIdentifier("constructed").withType(new Output()).build()
						))
				.build();
		model.addVertex(GyCons);
		Vertex AbsCons = Vertex.builder()
				.withIdentifier("AbsCons")
				.withType(new SDFComb())
				.withPorts(Set.of(
						Port.builder().withIdentifier("mapped").withType(new Input()).build(),
						Port.builder().withIdentifier("constructed").withType(new Output()).build()
						))
				.build();
		model.addVertex(AbsCons);
		
		Vertex getPx = Vertex.builder()
				.withIdentifier("getPx")
				.withType(new Process())
				.withPorts(Set.of(
						Port.builder().withIdentifier("constructor").withType(new Description()).build()
						))
				.build();
		model.addVertex(getPx);
		Vertex Gx = Vertex.builder()
				.withIdentifier("Gx")
				.withType(new Process())
				.withPorts(Set.of(
						Port.builder().withIdentifier("constructor").withType(new Description()).build()
						))
				.build();
		model.addVertex(Gx);
		Vertex Gy = Vertex.builder()
				.withIdentifier("Gy")
				.withType(new Process())
				.withPorts(Set.of(
						Port.builder().withIdentifier("constructor").withType(new Description()).build()
						))
				.build();
		model.addVertex(Gy);
		Vertex Abs = Vertex.builder()
				.withIdentifier("Abs")
				.withType(new Process())
				.withPorts(Set.of(
						Port.builder().withIdentifier("constructor").withType(new Description()).build()
						))
				.build();
		model.addVertex(Abs);
		
		Edge getPxEdge = Edge.builder()
				.withSourceNodeId(getPxCons.identifier)
				.withTargetNodeId(getPx.identifier)
				.withSourceNodePortId(Optional.of("constructed"))
				.withTargetNodePortId(Optional.of("constructor"))
				.withType(new Constructs())
				.build();
		model.addEdge(getPxCons, getPx, getPxEdge);
		Edge GxEdge = Edge.builder()
				.withSourceNodeId(GxCons.identifier)
				.withTargetNodeId(Gx.identifier)
				.withSourceNodePortId(Optional.of("constructed"))
				.withTargetNodePortId(Optional.of("constructor"))
				.withType(new Constructs())
				.build();
		model.addEdge(GxCons, Gx, GxEdge);
		Edge GyEdge = Edge.builder()
				.withSourceNodeId(GyCons.identifier)
				.withTargetNodeId(Gy.identifier)
				.withSourceNodePortId(Optional.of("constructed"))
				.withTargetNodePortId(Optional.of("constructor"))
				.withType(new Constructs())
				.build();
		model.addEdge(GyCons, Gy, GyEdge);
		Edge AbsEdge = Edge.builder()
				.withSourceNodeId(AbsCons.identifier)
				.withTargetNodeId(Abs.identifier)
				.withSourceNodePortId(Optional.of("constructed"))
				.withTargetNodePortId(Optional.of("constructor"))
				.withType(new Constructs())
				.build();
		model.addEdge(AbsCons, Abs, AbsEdge);

		Vertex gxSig = Vertex.builder()
				.withIdentifier("gxsig")
				.withType(new Signal())
				.withPorts(Set.of(
						Port.builder().withIdentifier("fifoIn").withType(new Description()).build(),
						Port.builder().withIdentifier("fifoOut").withType(new Description()).build()
						))
				.build();
		model.addVertex(gxSig);
		Vertex gySig = Vertex.builder()
				.withIdentifier("gysig")
				.withType(new Signal())
				.withPorts(Set.of(
						Port.builder().withIdentifier("fifoIn").withType(new Description()).build(),
						Port.builder().withIdentifier("fifoOut").withType(new Description()).build()
						))
				.build();
		model.addVertex(gySig);
		Vertex absxSig = Vertex.builder()
				.withIdentifier("absxsig")
				.withType(new Signal())
				.withPorts(Set.of(
						Port.builder().withIdentifier("fifoIn").withType(new Description()).build(),
						Port.builder().withIdentifier("fifoOut").withType(new Description()).build()
						))
				.build();
		model.addVertex(absxSig);
		Vertex absySig = Vertex.builder()
				.withIdentifier("absysig")
				.withType(new Signal())
				.withPorts(Set.of(
						Port.builder().withIdentifier("fifoIn").withType(new Description()).build(),
						Port.builder().withIdentifier("fifoOut").withType(new Description()).build()
						))
				.build();
		model.addVertex(absySig);
		
		Edge getPxTogx = Edge.builder()
				.withSourceNodeId(getPx.identifier)
				.withTargetNodeId(gxSig.identifier)
				.withSourceNodePortId(Optional.of("gx"))
				.withTargetNodePortId(Optional.of("fifoIn"))
				.withType(new Writes())
				.build();
		model.addEdge(getPx, gxSig, getPxTogx);
		Edge getPxTogy = Edge.builder()
				.withSourceNodeId(getPx.identifier)
				.withTargetNodeId(gySig.identifier)
				.withSourceNodePortId(Optional.of("gx"))
				.withTargetNodePortId(Optional.of("fifoIn"))
				.withType(new Writes())
				.build();
		model.addEdge(getPx, gySig, getPxTogy);
		Edge gxtoGx = Edge.builder()
				.withSourceNodeId(gxSig.identifier)
				.withTargetNodeId(Gx.identifier)
				.withSourceNodePortId(Optional.of("fifoOut"))
				.withTargetNodePortId(Optional.of("gx"))
				.withType(new Reads())
				.build();
		model.addEdge(gxSig, Gx, gxtoGx);
		Edge gyToGy = Edge.builder()
				.withSourceNodeId(gySig.identifier)
				.withTargetNodeId(Gy.identifier)
				.withSourceNodePortId(Optional.of("fifoOut"))
				.withTargetNodePortId(Optional.of("gy"))
				.withType(new Reads())
				.build();
		model.addEdge(gySig, Gy, gyToGy);
		Edge gxToabsx = Edge.builder()
				.withSourceNodeId(Gx.identifier)
				.withTargetNodeId(absxSig.identifier)
				.withSourceNodePortId(Optional.of("resx"))
				.withTargetNodePortId(Optional.of("fifoIn"))
				.withType(new Writes())
				.build();
		model.addEdge(Gx, absxSig, gxToabsx);
		Edge gyToabsy = Edge.builder()
				.withSourceNodeId(Gy.identifier)
				.withTargetNodeId(absySig.identifier)
				.withSourceNodePortId(Optional.of("resy"))
				.withTargetNodePortId(Optional.of("fifoIn"))
				.withType(new Writes())
				.build();
		model.addEdge(Gy, absySig, gyToabsy);
		Edge absxToAbs = Edge.builder()
				.withSourceNodeId(absxSig.identifier)
				.withTargetNodeId(Abs.identifier)
				.withSourceNodePortId(Optional.of("fifoOut"))
				.withTargetNodePortId(Optional.of("resx"))
				.withType(new Reads())
				.build();
		model.addEdge(absxSig, Abs, absxToAbs);
		Edge absyToAbs = Edge.builder()
				.withSourceNodeId(absySig.identifier)
				.withTargetNodeId(Abs.identifier)
				.withSourceNodePortId(Optional.of("fifoOut"))
				.withTargetNodePortId(Optional.of("resy"))
				.withType(new Reads())
				.build();
		model.addEdge(absySig, Abs, absyToAbs);

		Vertex sobel = Vertex.builder()
				.withIdentifier("sobel")
				.withType(new Process())
				.withPorts(Set.of(
						
						))
				.build();
		model.addVertex(sobel);

		
		ForSyDeModelWriter writer = new ForSyDeModelWriter();
		writer.write(model, "sobelTestCase.pro");
	}

}
