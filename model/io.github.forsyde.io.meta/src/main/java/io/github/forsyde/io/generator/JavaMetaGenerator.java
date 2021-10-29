package io.github.forsyde.io.generator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

import org.apache.commons.text.WordUtils;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import io.github.forsyde.io.meta.EdgeTrait;
import io.github.forsyde.io.meta.Model;
import io.github.forsyde.io.meta.VertexTrait;
import io.github.forsyde.io.meta.VertexTraitArrayProperty;
import io.github.forsyde.io.meta.VertexTraitBoolProperty;
import io.github.forsyde.io.meta.VertexTraitIntProperty;
import io.github.forsyde.io.meta.VertexTraitObjectProperty;
import io.github.forsyde.io.meta.VertexTraitPort;
import io.github.forsyde.io.meta.VertexTraitPortDirection;
import io.github.forsyde.io.meta.VertexTraitProperty;
import io.github.forsyde.io.meta.VertexTraitPropertyType;
import io.github.forsyde.io.meta.VertexTraitRealProperty;

public class JavaMetaGenerator {

	protected String toCamelCase(String word) {
		return WordUtils.capitalizeFully(word, '_').replace("_", "");
	}

	public TypeName metaToJavaType(VertexTraitPropertyType prop) {
		if (prop instanceof VertexTraitObjectProperty) {
			VertexTraitObjectProperty obj = (VertexTraitObjectProperty) prop;
			return ParameterizedTypeName.get(ClassName.get(HashMap.class), metaToJavaType(obj.getKeyType()),
					metaToJavaType(obj.getValueType()));
		} else if (prop instanceof VertexTraitArrayProperty) {
			VertexTraitArrayProperty array = (VertexTraitArrayProperty) prop;
			return ParameterizedTypeName.get(ClassName.get(ArrayList.class), metaToJavaType(array.getItemType()));
		}
		// else if (prop instanceof VertexTraitLongProperty) {
		// return ClassName.get(Long.class);
		// }
		else if (prop instanceof VertexTraitIntProperty) {
			return ClassName.get(Integer.class);
		} else if (prop instanceof VertexTraitRealProperty) {
			return ClassName.get(Double.class);
		}
		// else if (md['class'] == 'float') {
		// return ClassName.get(Float.class)
		// }
		else if (prop instanceof VertexTraitBoolProperty) {
			return ClassName.get(Boolean.class);
		} else
			return ClassName.get(String.class);
	}

	public MethodSpec generatePropertyGetter(VertexTraitProperty prop) {
		TypeName typeOut = metaToJavaType(prop.getType());
		ParameterizedTypeName optionalOut = ParameterizedTypeName.get(ClassName.get(Optional.class), typeOut);
		MethodSpec.Builder getPropMethod = MethodSpec.methodBuilder("get" + toCamelCase(prop.getName()))
				.addAnnotation(
						AnnotationSpec.builder(SuppressWarnings.class).addMember("value", "$S", "unchecked").build())
				.addModifiers(Modifier.PUBLIC, Modifier.DEFAULT).returns(typeOut);
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
				prop.getName());
		// }
		return getPropMethod.build();
	}

	public MethodSpec generatePropertySetter(VertexTraitProperty prop) {
		TypeName typeIn = metaToJavaType(prop.getType());
		// ParameterizedTypeName optionalOut =
		// ParameterizedTypeName.get(ClassName.get(Optional.class), typeOut);
		MethodSpec.Builder getPropMethod = MethodSpec.methodBuilder("set" + toCamelCase(prop.getName()))
				.addParameter(typeIn, toCamelCase(prop.getName())).addModifiers(Modifier.PUBLIC, Modifier.DEFAULT);
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
		TypeName arrayClass = ClassName.get("forsyde.io.java.core", "ArrayVertexProperty");
		TypeName mapClass = ClassName.get("forsyde.io.java.core", "MapVertexProperty");
		System.out.println(prop.getName());
		System.out.println(prop);
		if (prop.getType() instanceof VertexTraitObjectProperty) {
			getPropMethod.addStatement("getViewedVertex().getProperties().put(\"$L\", $T.fromConformingMapObject($L))",
					prop.getName(), mapClass, toCamelCase(prop.getName()));
		} else if (prop.getType() instanceof VertexTraitArrayProperty) {
			getPropMethod.addStatement("getViewedVertex().getProperties().put(\"$L\", $T.fromConformingList($L))",
					prop.getName(), arrayClass, toCamelCase(prop.getName()));
		} else if (prop.getType() instanceof VertexTraitIntProperty) {
			getPropMethod.addStatement("getViewedVertex().getProperties().put(\"$L\", new $T($L))", prop.getName(),
					ClassName.get("forsyde.io.java.core", "IntegerVertexProperty"), toCamelCase(prop.getName()));
		} else if (prop.getType() instanceof VertexTraitRealProperty) {
			getPropMethod.addStatement("getViewedVertex().getProperties().put(\"$L\", new $T($L))", prop.getName(),
					ClassName.get("forsyde.io.java.core", "DoubleVertexProperty"), toCamelCase(prop.getName()));
		} else if (prop.getType() instanceof VertexTraitBoolProperty) {
			getPropMethod.addStatement("getViewedVertex().getProperties().put(\"$L\", new $T($L))", prop.getName(),
					ClassName.get("forsyde.io.java.core", "BooleanVertexProperty"), toCamelCase(prop.getName()));
		} else {
			getPropMethod.addStatement("getViewedVertex().getProperties().put(\"$L\", new $T($L))", prop.getName(),
					ClassName.get("forsyde.io.java.core", "StringVertexProperty"), toCamelCase(prop.getName()));
		}
		return getPropMethod.build();
	}

	public MethodSpec generatePortGetter(VertexTraitPort port) {
		TypeName vertexClass = ClassName.get("forsyde.io.java.typed.viewers", port.getTrait().getName());
		ParameterizedTypeName optionalOut = ParameterizedTypeName.get(ClassName.get(Optional.class), vertexClass);
		ParameterizedTypeName listOut = ParameterizedTypeName.get(ClassName.get(List.class), vertexClass);
		ParameterizedTypeName setOut = ParameterizedTypeName.get(ClassName.get(Set.class), vertexClass);
		ParameterizedTypeName arrayType = ParameterizedTypeName.get(ClassName.get(ArrayList.class), vertexClass);
		ParameterizedTypeName setType = ParameterizedTypeName.get(ClassName.get(HashSet.class), vertexClass);
		MethodSpec.Builder getPortMethod = MethodSpec.methodBuilder("get" + toCamelCase(port.getName()) + "Port")
				.addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
				.addParameter(ClassName.get("forsyde.io.java.core", "ForSyDeModel"), "model");
		// .addParameter(vertexClass, "vertex");
		if (port.isMultiple()) {
			if (port.isOrdered()) {
				getPortMethod.returns(arrayType);
				getPortMethod.addStatement("$T outList = new $T()", arrayType, arrayType);
			} else {
				getPortMethod.returns(setType);
				getPortMethod.addStatement("$T outList = new $T()", setType, setType);
			}
		} else {
			getPortMethod.returns(optionalOut);
		}
		// // decide if a collection needs to be generated
		// // generate the iteration to collect or find vertexes
		if (port.getDirection() == VertexTraitPortDirection.OUTGOING
				|| port.getDirection() == VertexTraitPortDirection.BIDIRECTIONAL) {
			getPortMethod.beginControlFlow("for ($T e: model.outgoingEdgesOf(getViewedVertex()))",
					ClassName.get("forsyde.io.java.core", "Edge"));
			getPortMethod.beginControlFlow("if (e.getSourcePort().orElse(\"\").equals(\"" + port.getName()
					+ "\") && $T.conforms(e.getTarget()))", vertexClass);
			// this decides for every iteration if first found is good or if a list is being
			// built.
			if (port.isMultiple())
				getPortMethod.addStatement("outList.add($T.safeCast(e.getTarget()).get())", vertexClass);
			else
				getPortMethod.addStatement("return $T.safeCast(e.getTarget())", vertexClass);
			getPortMethod.endControlFlow();
			getPortMethod.endControlFlow();
		}
		if (port.getDirection() == VertexTraitPortDirection.INCOMING
				|| port.getDirection() == VertexTraitPortDirection.BIDIRECTIONAL) {
			getPortMethod.beginControlFlow("for ($T e: model.incomingEdgesOf(getViewedVertex()))",
					ClassName.get("forsyde.io.java.core", "Edge"));
			getPortMethod.beginControlFlow("if (e.getTargetPort().orElse(\"\").equals(\"" + port.getName()
					+ "\") && $T.conforms(e.getSource()))", vertexClass);
			// this decides for every iteration if first found is good or if a list is being
			// built.
			if (port.isMultiple())
				getPortMethod.addStatement("outList.add($T.safeCast(e.getSource()).get())", vertexClass);
			else
				getPortMethod.addStatement("return $T.safeCast(e.getSource())", vertexClass);
			getPortMethod.endControlFlow();
			getPortMethod.endControlFlow();
		}
		if (port.isMultiple())
			getPortMethod.addStatement("return outList");
		else
			getPortMethod.addStatement("return Optional.empty()");
		return getPortMethod.build();
	}

	public TypeSpec generateVertexTraitEnum(Model model) {
		ClassName generalTraitClass = ClassName.get("forsyde.io.java.core", "Trait");
		TypeSpec.Builder vertexTraitEnumBuilder = TypeSpec.enumBuilder("VertexTrait")
				.addSuperinterface(generalTraitClass).addModifiers(Modifier.PUBLIC);
		// // refinement method
		MethodSpec.Builder refinesMethod = MethodSpec.methodBuilder("refines").returns(TypeName.BOOLEAN)
				.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
				.addParameter(ClassName.get("forsyde.io.java.core", "VertexTrait"), "one")
				.addParameter(ClassName.get("forsyde.io.java.core", "VertexTrait"), "other")
				.beginControlFlow("switch (one)");
		List<VertexTrait> traits = model.getTraits().stream().filter(t -> t instanceof VertexTrait)
				.map(t -> (VertexTrait) t).collect(Collectors.toList());
		for (VertexTrait trait : traits) {
			vertexTraitEnumBuilder.addEnumConstant(trait.getName());
			refinesMethod.addCode("case " + trait.getName() + ":\n");
			refinesMethod.beginControlFlow("switch (other)");
			// def superIterator = new DepthFirstIterator<String, DefaultEdge>(traitGraph,
			// vertexTrait.key)
			Queue<VertexTrait> refinedTraits = new LinkedList<>(trait.getRefines());
			while (!refinedTraits.isEmpty()) {
				VertexTrait refinedTrait = refinedTraits.poll();
				refinesMethod.addCode("case " + refinedTrait.getName() + ": ");
				refinesMethod.addStatement("return true");
				refinedTraits.addAll(refinedTrait.getRefines());
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

	public TypeSpec generateEdgeTraits(Model model) {
		ClassName traitClass = ClassName.get("forsyde.io.java.core", "Trait");
		TypeSpec.Builder edgeEnumBuilder = TypeSpec.enumBuilder("EdgeTrait").addSuperinterface(traitClass)
				.addModifiers(Modifier.PUBLIC);
		MethodSpec.Builder refinesMethod = MethodSpec.methodBuilder("refines").returns(TypeName.BOOLEAN)
				.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
				.addParameter(ClassName.get("forsyde.io.java.core", "EdgeTrait"), "one")
				.addParameter(ClassName.get("forsyde.io.java.core", "EdgeTrait"), "other")
				.beginControlFlow("switch (one)");
		List<EdgeTrait> traits = model.getTraits().stream().filter(t -> t instanceof EdgeTrait).map(t -> (EdgeTrait) t)
				.collect(Collectors.toList());
		for (EdgeTrait trait : traits) {
			edgeEnumBuilder.addEnumConstant(trait.getName());
			refinesMethod.addCode("case " + trait.getName() + ":\n");
			refinesMethod.beginControlFlow("switch (other)");
			Queue<EdgeTrait> refinedTraits = new LinkedList<EdgeTrait>(trait.getRefines());
			while (!refinedTraits.isEmpty()) {
				EdgeTrait refinedTrait = refinedTraits.poll();
				refinesMethod.addCode("case " + refinedTrait.getName() + ": ");
				refinesMethod.addStatement("return true");
				refinedTraits.addAll(refinedTrait.getRefines());
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

	public List<TypeSpec> generateVertexInterfaces(Model model) {
		List<VertexTrait> traits = model.getTraits().stream().filter(t -> t instanceof VertexTrait)
				.map(t -> (VertexTrait) t).collect(Collectors.toList());
		List<TypeSpec> traitList = new ArrayList<>(traits.size());
		for (VertexTrait trait : traits) {
			TypeSpec.Builder traitInterface = TypeSpec.interfaceBuilder(trait.getName()).addModifiers(Modifier.PUBLIC);
			if (trait.getRefines().isEmpty()) {
				traitInterface.addSuperinterface(ClassName.get("forsyde.io.java.core", "VertexViewer"));
			} else {
				for (VertexTrait refinedTrait : trait.getRefines()) {
					traitInterface
							.addSuperinterface(ClassName.get("forsyde.io.java.typed.viewers", refinedTrait.getName()));
				}
			}
			for (VertexTraitProperty prop : trait.getProperties()) {
				traitInterface.addMethod(generatePropertyGetter(prop));
				traitInterface.addMethod(generatePropertySetter(prop));
			}
			for (VertexTraitPort port : trait.getPorts()) {
				traitInterface.addMethod(generatePortGetter(port));
			}
			MethodSpec.Builder conformsMethod = MethodSpec.methodBuilder("conforms")
					.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
					.addParameter(ClassName.get("forsyde.io.java.core", "Vertex"), "vertex")
					.returns(ClassName.get(Boolean.class));
			conformsMethod.beginControlFlow("for ($T t : vertex.getTraits())",
					ClassName.get("forsyde.io.java.core", "Trait"));
			conformsMethod.addStatement("if(t.refines($T.$L)) return true",
					ClassName.get("forsyde.io.java.core", "VertexTrait"), trait.getName());
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
					ClassName.get("forsyde.io.java.core", "VertexTrait"), trait.getName());
			conformsMethodViewer.endControlFlow();
			conformsMethodViewer.addStatement("return false");
			traitInterface.addMethod(conformsMethodViewer.build());
			MethodSpec.Builder safeCast = MethodSpec.methodBuilder("safeCast")
					.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
					.addParameter(ClassName.get("forsyde.io.java.core", "Vertex"), "vertex")
					.returns(ParameterizedTypeName.get(ClassName.get(Optional.class),
							ClassName.get("forsyde.io.java.typed.viewers", trait.getName())));
			safeCast.addStatement("return conforms(vertex) ? $T.of(new $T(vertex)) : Optional.empty()",
					ClassName.get(Optional.class),
					ClassName.get("forsyde.io.java.typed.viewers", trait.getName() + "Viewer"));
			traitInterface.addMethod(safeCast.build());
			MethodSpec.Builder safeCastViewer = MethodSpec.methodBuilder("safeCast")
					.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
					.addParameter(ClassName.get("forsyde.io.java.core", "VertexViewer"), "viewer")
					.returns(ParameterizedTypeName.get(ClassName.get(Optional.class),
							ClassName.get("forsyde.io.java.typed.viewers", trait.getName())));
			safeCastViewer.addStatement(
					"return conforms(viewer.getViewedVertex()) ? $T.of(new $T(viewer.getViewedVertex())) : Optional.empty()",
					ClassName.get(Optional.class),
					ClassName.get("forsyde.io.java.typed.viewers", trait.getName() + "Viewer"));
			traitInterface.addMethod(safeCastViewer.build());
			traitList.add(traitInterface.build());
		}
		return traitList;
	}

	public List<TypeSpec> generateVertexViewers(Model model) {
		List<VertexTrait> traits = model.getTraits().stream().filter(t -> t instanceof VertexTrait)
				.map(t -> (VertexTrait) t).collect(Collectors.toList());
		List<TypeSpec> traitList = new ArrayList<>(traits.size());
		for (VertexTrait trait : traits) {
			TypeSpec.Builder traitInterface = TypeSpec.classBuilder(trait.getName() + "Viewer")
					.addModifiers(Modifier.PUBLIC, Modifier.FINAL)
					.addSuperinterface(ClassName.get("forsyde.io.java.typed.viewers", trait.getName()));
			MethodSpec.Builder constructorMethod = MethodSpec.constructorBuilder()
					.addParameter(ClassName.get("forsyde.io.java.core", "Vertex"), "vertex")
					.addModifiers(Modifier.PUBLIC);
			traitInterface.addField(ClassName.get("forsyde.io.java.core", "Vertex"), "viewedVertex", Modifier.PUBLIC,
					Modifier.FINAL);
			constructorMethod.addStatement("viewedVertex = vertex");
			// constructorMethod.addStatement("identifier = vertex.getIdentifier()")
			// constructorMethod.addStatement("properties = vertex.getProperties()")
			// constructorMethod.addStatement("ports = vertex.getPorts()")
			// constructorMethod.addStatement("vertexTraits = vertex.getTraits()")
			traitInterface.addMethod(constructorMethod.build());
			traitInterface.addMethod(MethodSpec.methodBuilder("getViewedVertex").addModifiers(Modifier.PUBLIC)
					.returns(ClassName.get("forsyde.io.java.core", "Vertex")).addStatement("return viewedVertex")
					.build());
			traitList.add(traitInterface.build());
		}
		return traitList;
	}

	public void doGenerate(Model model, Path root) throws IOException {
		String edgeStr = JavaFile.builder("forsyde.io.java.core", generateEdgeTraits(model)).build().toString();
		String vertexStr = JavaFile.builder("forsyde.io.java.core", generateVertexTraitEnum(model)).build().toString();
		Path edgePath = root.resolve("src-gen/main/java/forsyde/io/java/core/EdgeTrait.java");
		Path vertexPath = root.resolve("src-gen/main/java/forsyde/io/java/core/VertexTrait.java");

		Files.createDirectories(root.resolve(Paths.get("src-gen/main/java/forsyde/io/java/core")));
		Files.writeString(edgePath, edgeStr, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		Files.writeString(vertexPath, vertexStr, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

		Files.createDirectories(root.resolve(Paths.get("src-gen/main/java/forsyde/io/java/typed/viewers/")));
		for (TypeSpec interfaceSpec : generateVertexViewers(model)) {
			String interfaceStr = JavaFile.builder("forsyde.io.java.typed.viewers", interfaceSpec).build().toString();
			Path interfacePath = root
					.resolve("src-gen/main/java/forsyde/io/java/typed/viewers/" + interfaceSpec.name + ".java");
			Files.writeString(interfacePath, interfaceStr, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		}

		for (TypeSpec viewerSpec : generateVertexInterfaces(model)) {
			String interfaceStr = JavaFile.builder("forsyde.io.java.typed.viewers", viewerSpec).build().toString();
			Path interfacePath = root
					.resolve("src-gen/main/java/forsyde/io/java/typed/viewers/" + viewerSpec.name + ".java");
			Files.writeString(interfacePath, interfaceStr, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		}
	}

}
