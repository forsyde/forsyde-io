package generator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import javax.lang.model.element.Modifier;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.apache.commons.text.WordUtils;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.*;
import org.gradle.work.Incremental;
import specs.*;

public class JavaMetaGenerator extends DefaultTask {

	@Incremental
	@InputFile
	File inputModelJson = getProject().file("meta.json");

	@InputDirectory
	File rootOutDir = getProject().getProjectDir();

	@OutputFiles
	List<File> outFiles = new ArrayList<>();

	@TaskAction
	public void execute() {
		ObjectMapper objectMapper = new ObjectMapper()
				.registerModule(new Jdk8Module());
		try {
			MetaModel metaModel = objectMapper.readValue(inputModelJson, MetaModel.class);
			generateFiles(metaModel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void generateFiles(MetaModel model) throws IOException {
		Path root = rootOutDir.toPath();
		Path generatedDir = root.resolve(Paths.get("src-gen/main/java"));
		Path enumsPath = root.resolve(Paths.get("src-gen/main/java/forsyde/io/java/core/"));
		Path viewersPath = root.resolve("src-gen/main/java/forsyde/io/java/typed/viewers/");
		Files.createDirectories(enumsPath) ;
		Files.createDirectories(viewersPath);

		outFiles.add(JavaFile.builder("forsyde.io.java.core", generateEdgeTraits(model)).build().writeToFile(generatedDir.toFile()));
		outFiles.add(JavaFile.builder("forsyde.io.java.core", generateVertexTraitEnum(model)).build().writeToFile(generatedDir.toFile()));

//		Files.writeString(edgePath, edgeStr, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
//		Files.writeString(vertexPath, vertexStr, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

		for (TypeSpec interfaceSpec : generateVertexViewers(model)) {
//			Path interfacePath = root
//					.resolve("src-gen/main/java/forsyde/io/java/typed/viewers/" + interfaceSpec.name + ".java");
			outFiles.add(JavaFile.builder("forsyde.io.java.typed.viewers", interfaceSpec).build().writeToFile(generatedDir.toFile()));
			// Files.writeString(interfacePath, interfaceStr, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		}

		for (TypeSpec viewerSpec : generateVertexInterfaces(model)) {
//			Path interfacePath = root
//					.resolve("src-gen/main/java/forsyde/io/java/typed/viewers/" + viewerSpec.name + ".java");
			outFiles.add(JavaFile.builder("forsyde.io.java.typed.viewers", viewerSpec).build().writeToFile(generatedDir.toFile()));
			// Files.writeString(interfacePath, interfaceStr, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		}
	}

	protected String toCamelCase(String word) {
		return WordUtils.capitalizeFully(word, '_').replace("_", "");
	}

	public TypeName metaToJavaType(PropertyTypeSpec prop) {
		switch (prop.name) {
			case INTMAP:
				return ParameterizedTypeName.get(ClassName.get(HashMap.class), ClassName.get(Integer.class),
						metaToJavaType(prop.valueType.get()));
			case STRINGMAP:
				return ParameterizedTypeName.get(ClassName.get(HashMap.class), ClassName.get(String.class),
						metaToJavaType(prop.valueType.get()));
			case ARRAY:
				return ParameterizedTypeName.get(ClassName.get(ArrayList.class), metaToJavaType(prop.valueType.get()));
			case BOOLEAN:
				return ClassName.get(Boolean.class);
			case INTEGER:
				return ClassName.get(Integer.class);
			case FLOAT:
				return ClassName.get(Float.class);
			case DOUBLE:
				return ClassName.get(Double.class); 
			case LONG:
				return ClassName.get(Long.class);
			default:
				return ClassName.get(String.class);
		}
	}

	public MethodSpec generatePropertyGetter(PropertySpec prop) {
		TypeName typeOut = metaToJavaType(prop.type);
		ParameterizedTypeName optionalOut = ParameterizedTypeName.get(ClassName.get(Optional.class), typeOut);
		MethodSpec.Builder getPropMethod = MethodSpec.methodBuilder("get" + toCamelCase(prop.name))
				.addAnnotation(
						AnnotationSpec.builder(SuppressWarnings.class).addMember("value", "$S", "unchecked").build())
				.addJavadoc("getter for required property \"$L\".\n", prop.name)
				.addJavadoc("@return \"$L\".\n\" property", prop.name)
				.addModifiers(Modifier.PUBLIC, Modifier.DEFAULT).returns(typeOut);
		prop.comment.ifPresent(comment -> getPropMethod.addJavadoc("$L", comment));
		// if (propInfo['default'] != null) {
		// getPropMethod.beginControlFlow("if
		// (getViewedVertex().getProperties().containsKey(\"${propName}\"))")
		// getPropMethod.addStatement("return (\$T)
		// getViewedVertex().getProperties().get(\"${propName}\").unwrap()", typeOut)
		// getPropMethod.nextControlFlow("else")
		// getPropMethod.addStatement("return new \$T(\$L)", typeOut,
		// generatePropertyDefault(propInfo).build())
		// getPropMethod.endControlFlow()
		// } else {
		getPropMethod.addStatement("return ($T) getViewedVertex().getProperties().get(\"$L\").unwrap()", typeOut,
				prop.name);
		// }
		return getPropMethod.build();
	}

	public MethodSpec generatePropertySetter(PropertySpec prop) {
		TypeName typeIn = metaToJavaType(prop.type);
		// ParameterizedTypeName optionalOut =
		// ParameterizedTypeName.get(ClassName.get(Optional.class), typeOut);
		;
		MethodSpec.Builder getPropMethod = MethodSpec.methodBuilder("set" + toCamelCase(prop.name))
				.addParameter(typeIn, WordUtils.uncapitalize(toCamelCase(prop.name)))
				.addJavadoc("setter for named required property \"$L\".\n", prop.name)
				.addJavadoc("@param $L value for required property \"$L\".\n", WordUtils.uncapitalize(toCamelCase(prop.name)), prop.name)
				.addModifiers(Modifier.PUBLIC, Modifier.DEFAULT);
		// if (propInfo['default'] != null) {
		// getPropMethod.beginControlFlow("if
		// (getViewedVertex().getProperties().containsKey(\"${propName}\"))")
		// getPropMethod.addStatement("return (\$T)
		// getViewedVertex().getProperties().get(\"${propName}\").unwrap()", typeOut)
		// getPropMethod.nextControlFlow("else")
		// getPropMethod.addStatement("return new \$T(\$L)", typeOut,
		// generatePropertyDefault(propInfo).build())
		// getPropMethod.endControlFlow()
		// } else {
		TypeName propClass = ClassName.get("forsyde.io.java.core", "VertexProperty");
//		TypeName mapClass = ClassName.get("forsyde.io.java.core", "MapVertexProperty");
//		if (prop.getType() instanceof VertexTraitObjectProperty) {
//			getPropMethod.addStatement("getViewedVertex().getProperties().put(\"$L\", $T.fromConformingMapObject($L))",
//					prop.getName(), mapClass, toCamelCase(prop.getName()));
//		} else if (prop.getType() instanceof VertexTraitArrayProperty) {
//			getPropMethod.addStatement("getViewedVertex().getProperties().put(\"$L\", $T.fromConformingList($L))",
//					prop.getName(), arrayClass, toCamelCase(prop.getName()));
//		} else if (prop.getType() instanceof VertexTraitIntProperty) {
//			getPropMethod.addStatement("getViewedVertex().getProperties().put(\"$L\", new $T($L))", prop.getName(),
//					ClassName.get("forsyde.io.java.core", "IntegerVertexProperty"), toCamelCase(prop.getName()));
//		} else if (prop.getType() instanceof VertexTraitRealProperty) {
//			getPropMethod.addStatement("getViewedVertex().getProperties().put(\"$L\", new $T($L))", prop.getName(),
//					ClassName.get("forsyde.io.java.core", "DoubleVertexProperty"), toCamelCase(prop.getName()));
//		} else if (prop.getType() instanceof VertexTraitBoolProperty) {
//			getPropMethod.addStatement("getViewedVertex().getProperties().put(\"$L\", new $T($L))", prop.getName(),
//					ClassName.get("forsyde.io.java.core", "BooleanVertexProperty"), toCamelCase(prop.getName()));
//		} else {
		getPropMethod.addStatement("getViewedVertex().getProperties().put(\"$L\", $T.create($L))", prop.name,
				propClass, WordUtils.uncapitalize(toCamelCase(prop.name)));
//		}
		return getPropMethod.build();
	}

	public MethodSpec generatePortGetter(PortSpec port) {
		TypeName vertexEnumClass = ClassName.get("forsyde.io.java.core", "VertexTrait");
		TypeName vertexClass = ClassName.get("forsyde.io.java.typed.viewers", port.vertexTrait.name);
		ParameterizedTypeName optionalOut = ParameterizedTypeName.get(ClassName.get(Optional.class), vertexClass);
		ParameterizedTypeName listOut = ParameterizedTypeName.get(ClassName.get(List.class), vertexClass);
		ParameterizedTypeName setOut = ParameterizedTypeName.get(ClassName.get(Set.class), vertexClass);
		ParameterizedTypeName arrayType = ParameterizedTypeName.get(ClassName.get(ArrayList.class), vertexClass);
		ParameterizedTypeName setType = ParameterizedTypeName.get(ClassName.get(HashSet.class), vertexClass);
		MethodSpec.Builder getPortMethod = MethodSpec.methodBuilder("get" + toCamelCase(port.name) + "Port")
				.addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
				.addParameter(ClassName.get("forsyde.io.java.core", "ForSyDeModel"), "model");
		// .addParameter(vertexClass, "vertex");
		if (port.multiple.orElse(true)) {
			if (port.ordered.orElse(false)) {
				getPortMethod.returns(listOut);
				String statement = "return getMultipleNamedPort(model, \"$L\", \"$L\", $L).stream().map(v -> $T.safeCast(v).get()).collect($T.toList())";
				getPortMethod.addStatement(statement, 
						port.name, 
						port.vertexTrait.name, 
						directionToInt(port.direction),
						vertexClass,
						Collectors.class);
//				getPortMethod.addStatement("$T outList = new $T()", arrayType, arrayType);
			} else {
				getPortMethod.returns(setOut);
				String statement = "return getMultipleNamedPort(model, \"$L\", \"$L\", $L).stream().map(v -> $T.safeCast(v).get()).collect($T.toSet())";
				getPortMethod.addStatement(statement, 
						port.name, 
						port.vertexTrait.name, 
						directionToInt(port.direction),
						vertexClass,
						Collectors.class);
//				getPortMethod.addStatement("$T outList = new $T()", setType, setType);
			}
		} else {
			getPortMethod.returns(optionalOut);
			getPortMethod.addStatement("return getNamedPort(model, \"$L\", \"$L\", $L).map(v -> $T.safeCast(v).get())", 
					port.name, 
					port.vertexTrait.name, 
					directionToInt(port.direction),
					vertexClass);
		}
		// // decide if a collection needs to be generated
		// // generate the iteration to collect or find vertexes
//		if (port.direction == PortDirection.OUTGOING
//				|| port.direction == PortDirection.BIDIRECTIONAL) {
//			getPortMethod.beginControlFlow("for ($T e: model.outgoingEdgesOf(getViewedVertex()))",
//					ClassName.get("forsyde.io.java.core", "Edge"));
//			getPortMethod.beginControlFlow("if (e.getSourcePort().orElse(\"\").equals(\"" + port.name
//					+ "\") && $T.conforms(e.getTarget()))", vertexClass);
//			// this decides for every iteration if first found is good or if a list is being
//			// built.
//			if (port.multiple.orElse(true))
//				getPortMethod.addStatement("outList.add($T.safeCast(e.getTarget()).get())", vertexClass);
//			else
//				getPortMethod.addStatement("return $T.safeCast(e.getTarget())", vertexClass);
//			getPortMethod.endControlFlow();
//			getPortMethod.endControlFlow();
//		}
//		if (port.direction == PortDirection.INCOMING
//				|| port.direction == PortDirection.BIDIRECTIONAL) {
//			getPortMethod.beginControlFlow("for ($T e: model.incomingEdgesOf(getViewedVertex()))",
//					ClassName.get("forsyde.io.java.core", "Edge"));
//			getPortMethod.beginControlFlow("if (e.getTargetPort().orElse(\"\").equals(\"" + port.name
//					+ "\") && $T.conforms(e.getSource()))", vertexClass);
//			// this decides for every iteration if first found is good or if a list is being
//			// built.
//			if (port.multiple.orElse(true))
//				getPortMethod.addStatement("outList.add($T.safeCast(e.getSource()).get())", vertexClass);
//			else
//				getPortMethod.addStatement("return $T.safeCast(e.getSource())", vertexClass);
//			getPortMethod.endControlFlow();
//			getPortMethod.endControlFlow();
//		}
//		if (port.multiple.orElse(true))
//			getPortMethod.addStatement("return outList");
//		else
//			getPortMethod.addStatement("return Optional.empty()");
		return getPortMethod.build();
	}

	public TypeSpec generateVertexTraitEnum(MetaModel model) {
		ClassName generalTraitClass = ClassName.get("forsyde.io.java.core", "Trait");
		TypeSpec.Builder vertexTraitEnumBuilder = TypeSpec.enumBuilder("VertexTrait")
				.addSuperinterface(generalTraitClass).addModifiers(Modifier.PUBLIC);
		// // refinement method
		MethodSpec.Builder refinesMethod = MethodSpec.methodBuilder("refines").returns(TypeName.BOOLEAN)
				.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
				.addParameter(ClassName.get("forsyde.io.java.core", "VertexTrait"), "one")
				.addParameter(ClassName.get("forsyde.io.java.core", "VertexTrait"), "other")
				.beginControlFlow("switch (one)");
		List<VertexTraitSpec> traits = model.vertexTraits.stream().collect(Collectors.toList());
		for (VertexTraitSpec trait : traits) {
			vertexTraitEnumBuilder.addEnumConstant(trait.name);
			refinesMethod.addCode("case " + trait.name + ":\n");
			refinesMethod.beginControlFlow("switch (other)");
			// def superIterator = new DepthFirstIterator<String, DefaultEdge>(traitGraph,
			// vertexTrait.key)
			Queue<VertexTraitSpec> refinedTraits = new LinkedList<>(List.of(trait));
			while (!refinedTraits.isEmpty()) {
				VertexTraitSpec refinedTrait = refinedTraits.poll();
				refinesMethod.addCode("case " + refinedTrait.name + ": ");
				refinesMethod.addStatement("return true");
				refinedTraits.addAll(refinedTrait.refinedTraits);
			}
			refinesMethod.addStatement("default: return false");
			refinesMethod.endControlFlow();
		}
		refinesMethod.addStatement("default: return false");
		refinesMethod.endControlFlow();
		vertexTraitEnumBuilder.addMethod(refinesMethod.build());
		vertexTraitEnumBuilder.addMethod(MethodSpec.methodBuilder("refines").returns(TypeName.BOOLEAN)
				.addModifiers(Modifier.PUBLIC).addParameter(ClassName.get("forsyde.io.java.core", "Trait"), "other")
				.addStatement(
						"return other instanceof VertexTrait ? VertexTrait.refines(this, (VertexTrait) other) : false")
				.build());
		vertexTraitEnumBuilder
				.addMethod(MethodSpec.methodBuilder("getName").returns(ClassName.bestGuess("java.lang.String"))
						.addModifiers(Modifier.PUBLIC).addStatement("return this.toString()").build());
		return vertexTraitEnumBuilder.build();
	}

	public TypeSpec generateEdgeTraits(MetaModel model) {
		ClassName traitClass = ClassName.get("forsyde.io.java.core", "Trait");
		TypeSpec.Builder edgeEnumBuilder = TypeSpec.enumBuilder("EdgeTrait").addSuperinterface(traitClass)
				.addModifiers(Modifier.PUBLIC);
		MethodSpec.Builder refinesMethod = MethodSpec.methodBuilder("refines").returns(TypeName.BOOLEAN)
				.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
				.addParameter(ClassName.get("forsyde.io.java.core", "EdgeTrait"), "one")
				.addParameter(ClassName.get("forsyde.io.java.core", "EdgeTrait"), "other")
				.beginControlFlow("switch (one)");
		List<EdgeTraitSpec> traits = model.edgeTraits.stream().collect(Collectors.toList());
		for (EdgeTraitSpec trait : traits) {
			edgeEnumBuilder.addEnumConstant(trait.name);
			refinesMethod.addCode("case " + trait.name + ":\n");
			refinesMethod.beginControlFlow("switch (other)");
			Queue<EdgeTraitSpec> refinedTraits = new LinkedList<>(List.of(trait));
			while (!refinedTraits.isEmpty()) {
				EdgeTraitSpec refinedTrait = refinedTraits.poll();
				refinesMethod.addCode("case " + refinedTrait.name + ": ");
				refinesMethod.addStatement("return true");
				refinedTraits.addAll(refinedTrait.refinedTraits);
			}
			refinesMethod.addStatement("default: return false");
			refinesMethod.endControlFlow();
		}
		refinesMethod.addStatement("default: return false");
		refinesMethod.endControlFlow();
		edgeEnumBuilder.addMethod(refinesMethod.build());
		edgeEnumBuilder.addMethod(MethodSpec.methodBuilder("refines").returns(TypeName.BOOLEAN)
				.addModifiers(Modifier.PUBLIC).addParameter(ClassName.get("forsyde.io.java.core", "Trait"), "other")
				.addStatement("return other instanceof EdgeTrait ? EdgeTrait.refines(this, (EdgeTrait) other) : false")
				.build());
		edgeEnumBuilder.addMethod(MethodSpec.methodBuilder("getName").returns(ClassName.bestGuess("java.lang.String"))
				.addModifiers(Modifier.PUBLIC).addStatement("return this.toString()").build());
		return edgeEnumBuilder.build();
	}

	public List<TypeSpec> generateVertexInterfaces(MetaModel model) {
		List<VertexTraitSpec> traits = model.vertexTraits.stream().collect(Collectors.toList());
		List<TypeSpec> traitList = new ArrayList<>(traits.size());
		for (VertexTraitSpec trait : traits) {
			TypeSpec.Builder traitInterface = TypeSpec.interfaceBuilder(trait.name).addModifiers(Modifier.PUBLIC);
			if (trait.refinedTraits.isEmpty()) {
				traitInterface.addSuperinterface(ClassName.get("forsyde.io.java.core", "VertexViewer"));
			} else {
				for (VertexTraitSpec refinedTrait : trait.refinedTraits) {
					traitInterface
							.addSuperinterface(ClassName.get("forsyde.io.java.typed.viewers", refinedTrait.name));
				}
			}
			for (PropertySpec prop : trait.requiredProperties) {
				traitInterface.addMethod(generatePropertyGetter(prop));
				traitInterface.addMethod(generatePropertySetter(prop));
			}
			for (PortSpec port : trait.requiredPorts) {
				traitInterface.addMethod(generatePortGetter(port));
			}
			MethodSpec.Builder conformsMethod = MethodSpec.methodBuilder("conforms")
					.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
					.addParameter(ClassName.get("forsyde.io.java.core", "Vertex"), "vertex")
					.returns(ClassName.get(Boolean.class));
			conformsMethod.beginControlFlow("for ($T t : vertex.getTraits())",
					ClassName.get("forsyde.io.java.core", "Trait"));
			conformsMethod.addStatement("if(t.refines($T.$L)) return true",
					ClassName.get("forsyde.io.java.core", "VertexTrait"), trait.name);
			conformsMethod.endControlFlow();
			conformsMethod.addStatement("return false");
			traitInterface.addMethod(conformsMethod.build());
			MethodSpec.Builder conformsMethodViewer = MethodSpec.methodBuilder("conforms")
					.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
					.addParameter(ClassName.get("forsyde.io.java.core", "VertexViewer"), "viewer")
					.returns(ClassName.get(Boolean.class));
			conformsMethodViewer.beginControlFlow("for ($T t : viewer.getViewedVertex().getTraits())",
					ClassName.get("forsyde.io.java.core", "Trait"));
			conformsMethodViewer.addStatement("if(t.refines($T.$L)) return true",
					ClassName.get("forsyde.io.java.core", "VertexTrait"), trait.name);
			conformsMethodViewer.endControlFlow();
			conformsMethodViewer.addStatement("return false");
			traitInterface.addMethod(conformsMethodViewer.build());
			MethodSpec.Builder safeCast = MethodSpec.methodBuilder("safeCast")
					.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
					.addParameter(ClassName.get("forsyde.io.java.core", "Vertex"), "vertex")
					.returns(ParameterizedTypeName.get(ClassName.get(Optional.class),
							ClassName.get("forsyde.io.java.typed.viewers", trait.name)));
			safeCast.addStatement("return conforms(vertex) ? $T.of(new $T(vertex)) : Optional.empty()",
					ClassName.get(Optional.class),
					ClassName.get("forsyde.io.java.typed.viewers", trait.name + "Viewer"));
			traitInterface.addMethod(safeCast.build());
			MethodSpec.Builder safeCastViewer = MethodSpec.methodBuilder("safeCast")
					.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
					.addParameter(ClassName.get("forsyde.io.java.core", "VertexViewer"), "viewer")
					.returns(ParameterizedTypeName.get(ClassName.get(Optional.class),
							ClassName.get("forsyde.io.java.typed.viewers", trait.name)));
			safeCastViewer.addStatement(
					"return conforms(viewer.getViewedVertex()) ? $T.of(new $T(viewer.getViewedVertex())) : Optional.empty()",
					ClassName.get(Optional.class),
					ClassName.get("forsyde.io.java.typed.viewers", trait.name + "Viewer"));
			traitInterface.addMethod(safeCastViewer.build());
			traitList.add(traitInterface.build());
		}
		return traitList;
	}

	public List<TypeSpec> generateVertexViewers(MetaModel model) {
		List<VertexTraitSpec> traits = model.vertexTraits.stream().collect(Collectors.toList());
		List<TypeSpec> traitList = new ArrayList<>(traits.size());
		for (VertexTraitSpec trait : traits) {
			TypeSpec.Builder traitInterface = TypeSpec.classBuilder(trait.name + "Viewer")
					.addModifiers(Modifier.PUBLIC, Modifier.FINAL)
					.addSuperinterface(ClassName.get("forsyde.io.java.typed.viewers", trait.name))
					.addField(ClassName.get("forsyde.io.java.core", "Vertex"), "viewedVertex", Modifier.PUBLIC,
							Modifier.FINAL);
			MethodSpec.Builder constructorMethod = MethodSpec.constructorBuilder()
					.addParameter(ClassName.get("forsyde.io.java.core", "Vertex"), "vertex")
					.addModifiers(Modifier.PUBLIC);
			constructorMethod.addStatement("viewedVertex = vertex");
			traitInterface.addMethod(constructorMethod.build());
			traitInterface.addMethod(MethodSpec.methodBuilder("getViewedVertex").addModifiers(Modifier.PUBLIC)
					.returns(ClassName.get("forsyde.io.java.core", "Vertex")).addStatement("return viewedVertex")
					.build());
			traitInterface.addMethod(
					MethodSpec.methodBuilder("hashCode")
					.addAnnotation(Override.class)
					.addModifiers(Modifier.PUBLIC)
					.addStatement("return (getIdentifier() + \"$L\").hashCode()", trait.name)
					.returns(int.class)
					.build());
			traitInterface.addMethod(
					MethodSpec.methodBuilder("equals")
					.addAnnotation(Override.class)
					.addModifiers(Modifier.PUBLIC)
					.addParameter(Object.class, "other")
					.addStatement("return other instanceof $L ? (($L) other).getIdentifier().equals(getIdentifier()) : false", 
							trait.name + "Viewer", trait.name + "Viewer")
					.returns(boolean.class)
					.build());
			traitList.add(traitInterface.build());
		}
		return traitList;
	}
	
	private Integer directionToInt(PortDirection dir) {
		switch (dir) {
			case INCOMING:
				return -1;
			case OUTGOING:
				return 1;
			default:
				return 0;
		}
	}


}
