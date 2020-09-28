import java.io.File;
import java.io.IOException;
import java.util.List;

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

import ForSyDe.Model.Application.CompoundProcess;
import ForSyDe.Model.Application.ConstructedProcess;
import ForSyDe.Model.Application.Constructs;
import ForSyDe.Model.Application.Reads;
import ForSyDe.Model.Application.SDFComb;
import ForSyDe.Model.Application.Signal;
import ForSyDe.Model.Application.Writes;
import ForSyDe.Model.IO.ForSyDeIO;

public class SobelCaseTest {
	
	@Test
	public void createModel() throws JsonGenerationException, JsonMappingException, IOException, JAXBException {
		
		// create all the constructors
		SDFComb getPxCons = new SDFComb("getPxCons");
		SDFComb GxCons = new SDFComb("GxCons");
		SDFComb GyCons = new SDFComb("GyCons");
		SDFComb AbsCons = new SDFComb("AbsCons");
		
		ConstructedProcess getPx = new ConstructedProcess("getPxProc");
		ConstructedProcess Gx = new ConstructedProcess("GxProc");
		ConstructedProcess Gy = new ConstructedProcess("GyProc");
		ConstructedProcess Abs = new ConstructedProcess("AbsProc");
		
		Constructs getPxEdge = new Constructs("px-cons");
		getPxEdge.fromVertex = getPxCons;
		getPxEdge.fromPort = "combinator";
		getPxEdge.toVertex = getPx;
		getPxEdge.toPort = "constructor";
		Constructs GxEdge = new Constructs("gx-cons");
		GxEdge.fromVertex = GxCons;
		GxEdge.fromPort = "combinator";
		GxEdge.toVertex = Gx;
		GxEdge.toPort = "constructor";
		Constructs GyEdge = new Constructs("gy-cons");
		GyEdge.fromVertex = GyCons;
		GyEdge.fromPort = "combinator";
		GyEdge.toVertex = Gy;
		GyEdge.toPort = "constructor";
		Constructs AbsEdge = new Constructs("abs-cons");
		AbsEdge.fromVertex = AbsCons;
		AbsEdge.fromPort = "combinator";
		AbsEdge.toVertex = Abs;
		AbsEdge.toPort = "constructor";	
		
		Signal gx = new Signal("GxInput");
		Signal gy = new Signal("GyInput");
		Signal absx = new Signal("AbsXInput");
		Signal absy = new Signal("AbsYInput");
		
		Writes getPxTogx = new Writes("px-gxsig");
		getPxTogx.fromVertex = getPx;
		getPxTogx.toVertex = gx;
		getPxTogx.fromPort = "gx";
		getPxTogx.toPort = "fifo-in";
		Writes getPxTogy = new Writes("px-gysig");
		getPxTogy.fromVertex = getPx;
		getPxTogy.toVertex = gy;
		getPxTogy.fromPort = "gy";
		getPxTogy.toPort = "fifo-in";
		Reads gxtoGx = new Reads("gxsig-gx");
		gxtoGx.fromVertex = gx;
		gxtoGx.toVertex = Gx;
		gxtoGx.fromPort = "fifo-out";
		gxtoGx.toPort = "gx";
		Reads gyToGy = new Reads("gysig-gy");
		gyToGy.fromVertex = gy;
		gyToGy.toVertex = Gy;
		gyToGy.fromPort = "fifo-out";
		gyToGy.toPort = "gy";
		Writes gxToabsx = new Writes("gx-absx");
		gxToabsx.fromVertex = Gx;
		gxToabsx.toVertex = absx;
		gxToabsx.fromPort = "resx";
		gxToabsx.toPort = "fifo-in";
		Writes gyToabsy = new Writes("gy-absy");
		gyToabsy.fromVertex = Gy;
		gyToabsy.toVertex = absy;
		gyToabsy.fromPort = "resy";
		gyToabsy.toPort = "fifo-in";
		Reads absxToAbs = new Reads("absx-abs");
		absxToAbs.fromVertex = absx;
		absxToAbs.toVertex = Abs;
		absxToAbs.fromPort = "fifo-out";
		absxToAbs.toPort = "resx";
		Reads absyToAbs = new Reads("absy-abs");
		absyToAbs.fromVertex = absy;
		absyToAbs.toVertex = Abs;
		absyToAbs.fromPort = "fifo-out";
		absyToAbs.toPort = "resy";
		
		
		CompoundProcess sobel = new CompoundProcess("sobel");
		sobel.vertexes = List.of(
				getPxCons, 
				GxCons,
				GyCons,
				AbsCons,
				getPx,
				Gx,
				Gy,
				Abs,
				gx,
				gy,
				absx,
				absy
				);
		sobel.edges = List.of(
				getPxEdge,
				GxEdge,
				GyEdge,
				AbsEdge,
				getPxTogx,
				getPxTogy,
				gxtoGx,
				gyToGy,
				gxToabsx,
				gyToabsy,
				absxToAbs,
				absyToAbs
				);
		
		ForSyDeIO model = new ForSyDeIO();
		model.vertexes = List.of(
				sobel
				);
		
		XmlMapper xmlMapper = XmlMapper.builder()
				.defaultUseWrapper(false)
				.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
				.configure(SerializationFeature.INDENT_OUTPUT, true)
				.addModule(new JaxbAnnotationModule())
				.build();
		xmlMapper.writeValue(new File("sobelCaseTest.xml"), model);
	}

}
