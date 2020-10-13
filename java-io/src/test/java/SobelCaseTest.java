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
import forsyde.io.java.types.application.SDFComb;

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
		
//		Signal gx = new Signal("GxInput");
//		Signal gy = new Signal("GyInput");
//		Signal absx = new Signal("AbsXInput");
//		Signal absy = new Signal("AbsYInput");
//		
//		Writes getPxTogx = new Writes("px-gxsig");
//		getPxTogx.fromVertex = getPx;
//		getPxTogx.toVertex = gx;
//		getPxTogx.fromPort = "gx";
//		getPxTogx.toPort = "fifo-in";
//		Writes getPxTogy = new Writes("px-gysig");
//		getPxTogy.fromVertex = getPx;
//		getPxTogy.toVertex = gy;
//		getPxTogy.fromPort = "gy";
//		getPxTogy.toPort = "fifo-in";
//		Reads gxtoGx = new Reads("gxsig-gx");
//		gxtoGx.fromVertex = gx;
//		gxtoGx.toVertex = Gx;
//		gxtoGx.fromPort = "fifo-out";
//		gxtoGx.toPort = "gx";
//		Reads gyToGy = new Reads("gysig-gy");
//		gyToGy.fromVertex = gy;
//		gyToGy.toVertex = Gy;
//		gyToGy.fromPort = "fifo-out";
//		gyToGy.toPort = "gy";
//		Writes gxToabsx = new Writes("gx-absx");
//		gxToabsx.fromVertex = Gx;
//		gxToabsx.toVertex = absx;
//		gxToabsx.fromPort = "resx";
//		gxToabsx.toPort = "fifo-in";
//		Writes gyToabsy = new Writes("gy-absy");
//		gyToabsy.fromVertex = Gy;
//		gyToabsy.toVertex = absy;
//		gyToabsy.fromPort = "resy";
//		gyToabsy.toPort = "fifo-in";
//		Reads absxToAbs = new Reads("absx-abs");
//		absxToAbs.fromVertex = absx;
//		absxToAbs.toVertex = Abs;
//		absxToAbs.fromPort = "fifo-out";
//		absxToAbs.toPort = "resx";
//		Reads absyToAbs = new Reads("absy-abs");
//		absyToAbs.fromVertex = absy;
//		absyToAbs.toVertex = Abs;
//		absyToAbs.fromPort = "fifo-out";
//		absyToAbs.toPort = "resy";
//		
//		
//		CompoundProcess sobel = new CompoundProcess("sobel");
//		sobel.vertexes = List.of(
//				getPxCons, 
//				GxCons,
//				GyCons,
//				AbsCons,
//				getPx,
//				Gx,
//				Gy,
//				Abs,
//				gx,
//				gy,
//				absx,
//				absy
//				);
//		sobel.edges = List.of(
//				getPxEdge,
//				GxEdge,
//				GyEdge,
//				AbsEdge,
//				getPxTogx,
//				getPxTogy,
//				gxtoGx,
//				gyToGy,
//				gxToabsx,
//				gyToabsy,
//				absxToAbs,
//				absyToAbs
//				);
//		
//		ForSyDeIO model = new ForSyDeIO();
//		model.vertexes = List.of(
//				sobel
//				);
//		
//		XmlMapper xmlMapper = XmlMapper.builder()
//				.defaultUseWrapper(false)
//				.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
//				.configure(SerializationFeature.INDENT_OUTPUT, true)
//				.addModule(new JaxbAnnotationModule())
//				.build();
//		xmlMapper.writeValue(new File("sobelCaseTest.xml"), model);
		
		ForSyDeModelWriter writer = new ForSyDeModelWriter();
		writer.write(model, "sobelTestCase.pro");
	}

}
