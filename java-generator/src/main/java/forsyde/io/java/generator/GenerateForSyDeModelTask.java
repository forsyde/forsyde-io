package forsyde.io.java.generator;

import com.squareup.javapoet.*;
import forsyde.io.java.generator.dsl.ForSyDeTraitDSLLexer;
import forsyde.io.java.generator.dsl.ForSyDeTraitDSLParser;
import forsyde.io.java.generator.exceptions.InconsistentTraitHierarchyException;
import forsyde.io.java.generator.specs.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.text.WordUtils;
import org.gradle.api.DefaultTask;
import org.gradle.api.Task;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.tasks.*;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class GenerateForSyDeModelTask extends DefaultTask implements Task {

    @InputFile
    abstract RegularFileProperty getInputModelDSL();// = getProject().file("traithierarchy.traitdsl");

    @OutputDirectory
    abstract DirectoryProperty getOutputDir();

    @TaskAction
    public void execute() {
        /*
        final ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new Jdk8Module());
         */
        try {
            final ForSyDeTraitDSLLexer forSyDeTraitDSLLexer = new ForSyDeTraitDSLLexer(CharStreams.fromPath(getInputModelDSL().get().getAsFile().toPath()));
            final CommonTokenStream commonTokenStream = new CommonTokenStream(forSyDeTraitDSLLexer);
            final ForSyDeTraitDSLParser forSyDeTraitDSLParser = new ForSyDeTraitDSLParser(commonTokenStream);
            final ForSyDeIOTraitDSLVisitor forSyDeIOTraitDSLVisitor = new ForSyDeIOTraitDSLVisitor();
            //final ForSyDeIOTraitDSLListener forSyDeTraitDSLListener = new ForSyDeIOTraitDSLListener();
            //final ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
            //parseTreeWalker.walk(forSyDeTraitDSLListener, forSyDeTraitDSLParser.rootTraitHierarchy());
            final TraitHierarchy traitHierarchy = forSyDeIOTraitDSLVisitor.getRootTraitHierarchy(forSyDeTraitDSLParser.rootTraitHierarchy());//objectMapper.readValue(inputModelJson, TraitHierarchy.class);
            generateFiles(traitHierarchy);
        } catch (IOException | InconsistentTraitHierarchyException e) {
            e.printStackTrace();
        }
    }


    public void generateFiles(TraitHierarchy model) throws IOException {
//        final File rootOutDir = getProject().getBuildDir().toPath(); //.resolve(Paths.get(outputRootDirName)).toFile();
        Path root = getOutputDir().get().getAsFile().toPath();
        Path enumsPath = root.resolve(Paths.get("forsyde/io/java/core/"));
        Path viewersPath = root.resolve("forsyde/io/java/typed/viewers/");
        Files.createDirectories(enumsPath) ;
        Files.createDirectories(viewersPath);

        JavaFile.builder("forsyde.io.java.core", generateEdgeTraitsEnum(model)).build().writeToFile(root.toFile());
        JavaFile.builder("forsyde.io.java.core", generateVertexTraitEnum(model)).build().writeToFile(root.toFile());

//		Files.writeString(edgePath, edgeStr, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
//		Files.writeString(vertexPath, vertexStr, StandardOpenOption.WRITE, StandardOpenOption.CREATE);


        for (VertexTraitSpec trait : model.vertexTraits) {
//			Path interfacePath = root
//					.resolve("src-gen/main/java/forsyde/io/java/typed/viewers/" + interfaceSpec.name + ".java");
            final TypeSpec interfaceSpec = generateVertexInterface(trait);
            final String extraPackages = trait.getNamespaces().isEmpty() ?
                    "" : "." + String.join(".", trait.getNamespaces());
            JavaFile.builder("forsyde.io.java.typed.viewers" + extraPackages, interfaceSpec)
                    .build().writeToFile(root.toFile());
            // Files.writeString(interfacePath, interfaceStr, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        }

        for (VertexTraitSpec trait : model.vertexTraits) {
            final TypeSpec viewerSpec = generateVertexViewer(trait);
            final String extraPackages = trait.getNamespaces().isEmpty() ?
                    "" : "." + String.join(".", trait.getNamespaces());
            JavaFile.builder("forsyde.io.java.typed.viewers" + extraPackages, viewerSpec)
                    .build().writeToFile(root.toFile());
            // Files.writeString(interfacePath, interfaceStr, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        }

        for (EdgeTraitSpec trait : model.edgeTraits) {
            final TypeSpec viewerSpec = generateEdgeAcessors(trait);
            final String extraPackages = trait.getNamespaces().isEmpty() ?
                    "" : "." + String.join(".", trait.getNamespaces());
            JavaFile.builder("forsyde.io.java.typed.edges" + extraPackages, viewerSpec)
                    .build().writeToFile(root.toFile());
        }

//        final Set<Path> outPaths = outFiles.stream().map(File::toPath).collect(Collectors.toSet());
//        Files.walk(root).forEach(s -> {
//            if (!outPaths.contains(s) && s.toFile().isFile()) {
//                try {
//                    Files.deleteIfExists(s);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

    }

    protected String toCamelCase(String word) {
        return WordUtils.capitalize(word);
        //return WordUtils.capitalizeFully(word, '_').replace("_", "");
    }

    public TypeName metaToJavaType(PropertyTypeSpec prop) {
        return PropertyTypeSpecs.cases()
                .StringVertexProperty(() -> (TypeName) ClassName.get(String.class))
                .IntVertexProperty(() -> ClassName.get(Integer.class))
                .BooleanVertexProperty(() -> ClassName.get(Boolean.class))
                .FloatVertexProperty(() -> ClassName.get(Float.class))
                .DoubleVertexProperty(() -> ClassName.get(Double.class))
                .LongVertexProperty(() -> ClassName.get(Long.class))
                .ArrayVertexProperty((p) -> ParameterizedTypeName.get(ClassName.get(List.class), metaToJavaType(p)))
                .IntMapVertexProperty((p) -> ParameterizedTypeName.get(ClassName.get(Map.class), ClassName.get(Integer.class),
                        metaToJavaType(p)))
                .StringMapVertexProperty((p) -> ParameterizedTypeName.get(ClassName.get(Map.class), ClassName.get(String.class),
                        metaToJavaType(p)))
                .apply(prop);
    }

    public TypeName concreteMetaToJavaType(PropertyTypeSpec prop) {
        return PropertyTypeSpecs.cases()
                .StringVertexProperty(() -> (TypeName) ClassName.get(String.class))
                .IntVertexProperty(() -> ClassName.get(Integer.class))
                .BooleanVertexProperty(() -> ClassName.get(Boolean.class))
                .FloatVertexProperty(() -> ClassName.get(Float.class))
                .DoubleVertexProperty(() -> ClassName.get(Double.class))
                .LongVertexProperty(() -> ClassName.get(Long.class))
                .ArrayVertexProperty((p) -> ParameterizedTypeName.get(ClassName.get(ArrayList.class), metaToJavaType(p)))
                .IntMapVertexProperty((p) -> ParameterizedTypeName.get(ClassName.get(HashMap.class), ClassName.get(Integer.class),
                        metaToJavaType(p)))
                .StringMapVertexProperty((p) -> ParameterizedTypeName.get(ClassName.get(HashMap.class), ClassName.get(String.class),
                        metaToJavaType(p)))
                .apply(prop);
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
        getPropMethod.addStatement("return getViewedVertex().hasProperty(\"$L\") ?\n" +
                        "            ($T) getViewedVertex().getProperty(\"$L\") :\n" +
                        "            null",
                prop.name,
                typeOut,
                prop.name);
        return getPropMethod.build();
    }

    public MethodSpec generateRequiredPortGetter(VertexTraitSpec vertexTraitSpec) {
        final String portsString = vertexTraitSpec.requiredPorts.stream()
                .map(p -> "\"" + p.name + "\"")
                .collect(Collectors.joining(", "));
        final String refinedPortsString = vertexTraitSpec.refinedTraits.stream().map(t -> ".addAll(" + t.getTraitLocalName() + ".getRequiredPorts())")
                .collect(Collectors.joining());
        final MethodSpec.Builder getRequiredPortGetter = MethodSpec.methodBuilder("getRequiredPorts")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(ParameterizedTypeName.get(Set.class, String.class))
                .addStatement("final $T<$T> required = new $T<$T>($L)",
                        Set.class, String.class, HashSet.class, String.class, vertexTraitSpec.requiredPorts.size());
        for (PortSpec port: vertexTraitSpec.requiredPorts) {
            getRequiredPortGetter.addStatement("required.add($S)", port.name);
        }
        for (VertexTraitSpec refined: vertexTraitSpec.refinedTraits) {
            getRequiredPortGetter.addStatement("required.addAll($L.getRequiredPorts())", refined.getTraitLocalName());
        }
        return getRequiredPortGetter.addStatement("return required").build();
    }

    public MethodSpec generateRequiredPropertyGetter(VertexTraitSpec vertexTraitSpec) {
        final MethodSpec.Builder getRequiredPropGetter = MethodSpec.methodBuilder("getRequiredProperties")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(ParameterizedTypeName.get(ClassName.get(Map.class), ClassName.get(String.class), ClassName.get(Object.class)))
                .addStatement("final $T<$T, $T> required = new $T<$T, $T>()",
                        HashMap.class, String.class, Object.class,
                        HashMap.class, String.class, Object.class)
                .addCode("required.putAll($T.of(\n", Map.class);
        for (int i = 0; i < vertexTraitSpec.requiredProperties.size(); i++) {
            final PropertySpec propertySpec = vertexTraitSpec.requiredProperties.get(i);
            if (propertySpec.defaultValue == null) {
                PropertyTypeSpecs.cases()
                        .StringVertexProperty(() -> getRequiredPropGetter.addCode("\t$S, $S", propertySpec.name, ""))
                        .IntVertexProperty(() -> getRequiredPropGetter.addCode("\t$S, $L", propertySpec.name, 0))
                        .BooleanVertexProperty(() -> getRequiredPropGetter.addCode("\t$S, $L", propertySpec.name, false))
                        .FloatVertexProperty(() -> getRequiredPropGetter.addCode("\t$S, $L", propertySpec.name, 0.0F))
                        .DoubleVertexProperty(() -> getRequiredPropGetter.addCode("\t$S, $L", propertySpec.name, 0.0))
                        .LongVertexProperty(() -> getRequiredPropGetter.addCode("\t$S, $LL", propertySpec.name, 0L))
                        .ArrayVertexProperty((p) -> getRequiredPropGetter.addCode("\t$S, new $T()", propertySpec.name, concreteMetaToJavaType(propertySpec.type)))
                        .IntMapVertexProperty((p) -> getRequiredPropGetter.addCode("\t$S, new $T()", propertySpec.name, concreteMetaToJavaType(propertySpec.type)))
                        .StringMapVertexProperty((p) -> getRequiredPropGetter.addCode("\t$S, new $T()", propertySpec.name, concreteMetaToJavaType(propertySpec.type)))
                        .apply(propertySpec.type);
            } else {
                // TODO: support also definition of mappings and arrays as default values
                VertexProperties.caseOf(propertySpec.defaultValue)
                        .StringVertexProperty(s -> getRequiredPropGetter.addCode("\t$S, $S", propertySpec.name, s))
                        .IntVertexProperty(in -> getRequiredPropGetter.addCode("\t$S, $L", propertySpec.name, in))
                        .BooleanVertexProperty(b -> getRequiredPropGetter.addCode("\t$S, $L", propertySpec.name, b))
                        .FloatVertexProperty(f -> getRequiredPropGetter.addCode("\t$S, $LF", propertySpec.name, f))
                        .DoubleVertexProperty(f -> getRequiredPropGetter.addCode("\t$S, $L", propertySpec.name, f))
                        .LongVertexProperty(l -> getRequiredPropGetter.addCode("\t$S, $LL", propertySpec.name, l))
                        .otherwiseEmpty();
            }
            if (i < vertexTraitSpec.requiredProperties.size() -1)
                getRequiredPropGetter.addCode(",\n");

        }
        getRequiredPropGetter.addStatement("))");
        for (VertexTraitSpec refinedTrait : vertexTraitSpec.refinedTraits) {
            getRequiredPropGetter.addStatement("required.putAll($L.getRequiredProperties())", refinedTrait.getTraitLocalName());
        }
        return getRequiredPropGetter.addStatement("return required").build();
    }

    public MethodSpec generateEnforce(VertexTraitSpec vertexTraitSpec) {
        final TypeName traitEnum = ClassName.get("forsyde.io.java.core", "VertexTrait");
        final TypeName vertexClass = ClassName.get("forsyde.io.java.core", "Vertex");
        final String enumTraitName = vertexTraitSpec.name.replace("::", "_").toUpperCase();
        final String extraPackages = vertexTraitSpec.getNamespaces().isEmpty() ?
                "" : "." + String.join(".", vertexTraitSpec.getNamespaces());
        final TypeName traitInterface = ClassName.get("forsyde.io.java.typed.viewers" + extraPackages, vertexTraitSpec.getTraitLocalName());
        final TypeName viewerClass = ClassName.get("forsyde.io.java.typed.viewers" + extraPackages, vertexTraitSpec.getTraitLocalName()  + "Viewer");
        final CodeBlock propertyBlock = CodeBlock.builder()
                .beginControlFlow("for(String key : $T.getRequiredProperties().keySet())", traitInterface)
                .addStatement("if (!vertex.hasProperty(key)) vertex.putProperty(key, $T.getRequiredProperties().get(key))", traitInterface)
                .endControlFlow()
                .build();
        return MethodSpec.methodBuilder("enforce")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(vertexClass, "vertex")
                .addStatement("vertex.addTraits($T.$L)", traitEnum, enumTraitName)
                .addStatement("vertex.addPorts($T.getRequiredPorts())", traitInterface)
                .addCode(propertyBlock)
                .addStatement("return new $T(vertex)", viewerClass)
                .returns(traitInterface)
                .build();
    }

    public MethodSpec generateEnforceFromViewer(VertexTraitSpec vertexTraitSpec) {
        final TypeName vertexViewerClass = ClassName.get("forsyde.io.java.core", "VertexViewer");
        final String extraPackages = vertexTraitSpec.getNamespaces().isEmpty() ?
                "" : "." + String.join(".", vertexTraitSpec.getNamespaces());
        final TypeName traitInterface = ClassName.get("forsyde.io.java.typed.viewers" + extraPackages, vertexTraitSpec.getTraitLocalName());
        return MethodSpec.methodBuilder("enforce")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(vertexViewerClass, "viewer")
                .addStatement("return $T.enforce(viewer.getViewedVertex())", traitInterface)
                .returns(traitInterface)
                .build();
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
        getPropMethod.addStatement("getViewedVertex().putProperty(\"$L\", $L)", prop.name,
                WordUtils.uncapitalize(toCamelCase(prop.name)));
//		}
        return getPropMethod.build();
    }

    public MethodSpec generatePortGetter(PortSpec port) {
        final String traitName = port.vertexTrait.name.replace("::", "_").toUpperCase();
        final String extraPackages = port.vertexTrait.getNamespaces().isEmpty() ?
                "" : "." + String.join(".", port.vertexTrait.getNamespaces());
        TypeName vertexClass = ClassName.get("forsyde.io.java.typed.viewers" + extraPackages, port.vertexTrait.getTraitLocalName());
        ParameterizedTypeName optionalOut = ParameterizedTypeName.get(ClassName.get(Optional.class), vertexClass);
        ParameterizedTypeName listOut = ParameterizedTypeName.get(ClassName.get(List.class), vertexClass);
        ParameterizedTypeName setOut = ParameterizedTypeName.get(ClassName.get(Set.class), vertexClass);
        ParameterizedTypeName arrayType = ParameterizedTypeName.get(ClassName.get(ArrayList.class), vertexClass);
        ParameterizedTypeName setType = ParameterizedTypeName.get(ClassName.get(HashSet.class), vertexClass);
        MethodSpec.Builder getPortMethod = MethodSpec.methodBuilder("get" + toCamelCase(port.name) + "Port")
                .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
                .addComment("Returns the vertex(es) that declares $L, connected at port $L in the underlying model, in accordance to the trait hierarchy specification.", traitName,  port.name)
                .addParameter(ClassName.get("forsyde.io.java.core", "ForSyDeSystemGraph"), "model");
        // .addParameter(vertexClass, "vertex");
        if (port.multiple.orElse(true)) {
            if (port.ordered.orElse(false)) {
                getPortMethod.returns(listOut);
                if (port.edgeTraitSpec != null) {
                    String statement = "return $T.getMultipleNamedPort(model, getViewedVertex(), $S, $T.$L, $T.$L, $T.$L).stream().flatMap(v -> $T.safeCast(v).stream()).collect($T.toList())";
                    getPortMethod.addStatement(statement,
                            ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                            port.name,
                            ClassName.get("forsyde.io.java.core", "VertexTrait"),
                            traitName,
                            ClassName.get("forsyde.io.java.core.VertexAcessor", "VertexPortDirection"),
                            port.direction.toString().toUpperCase(),
                            ClassName.get("forsyde.io.java.core", "EdgeTrait"),
                            port.edgeTraitSpec.name.replace("::", "_").toUpperCase(),
                            vertexClass,
                            Collectors.class);
                } else {
                    String statement = "return $T.getMultipleNamedPort(model, getViewedVertex(), $S, $T.$L, $T.$L).stream().flatMap(v -> $T.safeCast(v).stream()).collect($T.toList())";
                    getPortMethod.addStatement(statement,
                            ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                            port.name,
                            ClassName.get("forsyde.io.java.core", "VertexTrait"),
                            traitName,
                            ClassName.get("forsyde.io.java.core.VertexAcessor", "VertexPortDirection"),
                            port.direction.toString().toUpperCase(),
                            vertexClass,
                            Collectors.class);
                }
//				getPortMethod.addStatement("$T outList = new $T()", arrayType, arrayType);
            } else {
                getPortMethod.returns(setOut);
                if (port.edgeTraitSpec != null) {
                    String statement = "return $T.getMultipleNamedPort(model, getViewedVertex(), $S, $T.$L, $T.$L, $T.$L).stream().flatMap(v -> $T.safeCast(v).stream()).collect($T.toSet())";
                    getPortMethod.addStatement(statement,
                            ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                            port.name,
                            ClassName.get("forsyde.io.java.core", "VertexTrait"),
                            traitName,
                            ClassName.get("forsyde.io.java.core.VertexAcessor", "VertexPortDirection"),
                            port.direction.toString().toUpperCase(),
                            ClassName.get("forsyde.io.java.core", "EdgeTrait"),
                            port.edgeTraitSpec.name.replace("::", "_").toUpperCase(),
                            vertexClass,
                            Collectors.class);
                } else {
                    String statement = "return $T.getMultipleNamedPort(model, getViewedVertex(), $S, $T.$L, $T.$L).stream().flatMap(v -> $T.safeCast(v).stream()).collect($T.toSet())";
                    getPortMethod.addStatement(statement,
                            ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                            port.name,
                            ClassName.get("forsyde.io.java.core", "VertexTrait"),
                            traitName,
                            ClassName.get("forsyde.io.java.core.VertexAcessor", "VertexPortDirection"),
                            port.direction.toString().toUpperCase(),
                            vertexClass,
                            Collectors.class);
                }
//				getPortMethod.addStatement("$T outList = new $T()", setType, setType);
            }
        } else {
            getPortMethod.returns(optionalOut);
            if (port.edgeTraitSpec != null) {
                getPortMethod.addStatement("return $T.getNamedPort(model, getViewedVertex(), $S, $T.$L, $T.$L, $T.$L).flatMap($T::safeCast)",
                        ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                        port.name,
                        ClassName.get("forsyde.io.java.core", "VertexTrait"),
                        traitName,
                        ClassName.get("forsyde.io.java.core.VertexAcessor", "VertexPortDirection"),
                        port.direction.toString().toUpperCase(),
                        ClassName.get("forsyde.io.java.core", "EdgeTrait"),
                        port.edgeTraitSpec.name.replace("::", "_").toUpperCase(),
                        vertexClass);
            } else {
                getPortMethod.addStatement("return $T.getNamedPort(model, getViewedVertex(), $S, $T.$L, $T.$L).flatMap($T::safeCast)",
                        ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                        port.name,
                        ClassName.get("forsyde.io.java.core", "VertexTrait"),
                        traitName,
                        ClassName.get("forsyde.io.java.core.VertexAcessor", "VertexPortDirection"),
                        port.direction.toString().toUpperCase(),
                        vertexClass);
            }
        }
        return getPortMethod.build();
    }

    public Set<MethodSpec> generatePortSetters(PortSpec port) {
        final String traitName = port.vertexTrait.name.replace("::", "_").toUpperCase();
        final String extraPackages = port.vertexTrait.getNamespaces().isEmpty() ?
                "" : "." + String.join(".", port.vertexTrait.getNamespaces());
        final TypeName vertexClass = ClassName.get("forsyde.io.java.typed.viewers" + extraPackages, port.vertexTrait.getTraitLocalName());
        final ParameterizedTypeName listOut = ParameterizedTypeName.get(ClassName.get(List.class), vertexClass);
        final ParameterizedTypeName setOut = ParameterizedTypeName.get(ClassName.get(Set.class), vertexClass);
        final Set<MethodSpec.Builder> methodsBuilders = new HashSet<>();
        MethodSpec.Builder getPortMethod = MethodSpec.methodBuilder("set" + toCamelCase(port.name) + "Port")
                .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
                .addComment("Connects vertex(es) that declare(s) $L at port $L in the underlying model, in accordance to the trait hierarchy specification.", traitName,  port.name)
                .addParameter(ClassName.get("forsyde.io.java.core", "ForSyDeSystemGraph"), "model")
                .returns(TypeName.BOOLEAN);
        methodsBuilders.add(getPortMethod);
        if (port.multiple.orElse(true)) {
            if (port.ordered.orElse(false)) {
                getPortMethod.addParameter(listOut, "vertexes");
                if (port.edgeTraitSpec != null) {
                    String statement;
                    switch (port.direction) {
                        case BIDIRECTIONAL:
                        case OUTGOING:
                            statement = "return $T.insertOrderedMultipleNamedPort(model, this, vertexes, $S, null, $T.$L)";
                            getPortMethod.addStatement(statement,
                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                    port.name,
                                    ClassName.get("forsyde.io.java.core", "EdgeTrait"),
                                    port.edgeTraitSpec.name.replace("::", "_").toUpperCase()
                            );
                            methodsBuilders.add(
                                MethodSpec.methodBuilder("insert" + toCamelCase(port.name) + "Port")
                                    .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
                                    .addParameter(ClassName.get("forsyde.io.java.core", "ForSyDeSystemGraph"), "model")
                                    .addParameter(vertexClass, "vertex")
                                    .addParameter(Integer.class, "index")
                                    .addStatement("return $T.insertOrderedMultipleNamedPort(model, this, vertex, $S, null, index, $T.$L)",
                                            ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                            port.name,
                                            ClassName.get("forsyde.io.java.core", "EdgeTrait"),
                                            port.edgeTraitSpec.name.replace("::", "_").toUpperCase())
                                    .returns(TypeName.BOOLEAN));
                            methodsBuilders.add(
                                    MethodSpec.methodBuilder("insert" + toCamelCase(port.name) + "Port")
                                            .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
                                            .addParameter(ClassName.get("forsyde.io.java.core", "ForSyDeSystemGraph"), "model")
                                            .addParameter(vertexClass, "vertex")
                                            .addStatement("return $T.insertOrderedMultipleNamedPort(model, this, vertex, $S, null, $T.$L)",
                                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                                    port.name,
                                                    ClassName.get("forsyde.io.java.core", "EdgeTrait"),
                                                    port.edgeTraitSpec.name.replace("::", "_").toUpperCase())
                                            .returns(TypeName.BOOLEAN));
                            break;
                        case INCOMING:
                            statement = "return $T.insertOrderedMultipleNamedPort(model, vertexes, this, null, $S, $T.$L)";
                            getPortMethod.addStatement(statement,
                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                    port.name,
                                    ClassName.get("forsyde.io.java.core", "EdgeTrait"),
                                    port.edgeTraitSpec.name.replace("::", "_").toUpperCase()
                            );
                            methodsBuilders.add(
                                    MethodSpec.methodBuilder("insert" + toCamelCase(port.name) + "Port")
                                            .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
                                            .addParameter(ClassName.get("forsyde.io.java.core", "ForSyDeSystemGraph"), "model")
                                            .addParameter(vertexClass, "vertex")
                                            .addParameter(Integer.class, "index")
                                            .addStatement("return $T.insertOrderedMultipleNamedPort(model, this, vertex, null, $S, index, $T.$L)",
                                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                                    port.name,
                                                    ClassName.get("forsyde.io.java.core", "EdgeTrait"),
                                                    port.edgeTraitSpec.name.replace("::", "_").toUpperCase())
                                            .returns(TypeName.BOOLEAN));
                            methodsBuilders.add(
                                    MethodSpec.methodBuilder("insert" + toCamelCase(port.name) + "Port")
                                            .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
                                            .addParameter(ClassName.get("forsyde.io.java.core", "ForSyDeSystemGraph"), "model")
                                            .addParameter(vertexClass, "vertex")
                                            .addStatement("return $T.insertOrderedMultipleNamedPort(model, this, vertex, null, $S, $T.$L)",
                                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                                    port.name,
                                                    ClassName.get("forsyde.io.java.core", "EdgeTrait"),
                                                    port.edgeTraitSpec.name.replace("::", "_").toUpperCase())
                                            .returns(TypeName.BOOLEAN));
                            break;
                    }
                } else {
                    String statement;
                    switch (port.direction) {
                        case BIDIRECTIONAL:
                        case OUTGOING:
                            statement = "return $T.insertOrderedMultipleNamedPort(model, this, vertexes, $S, null)";
                            getPortMethod.addStatement(statement,
                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                    port.name);
                            methodsBuilders.add(
                                    MethodSpec.methodBuilder("insert" + toCamelCase(port.name) + "Port")
                                            .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
                                            .addParameter(ClassName.get("forsyde.io.java.core", "ForSyDeSystemGraph"), "model")
                                            .addParameter(vertexClass, "vertex")
                                            .addParameter(Integer.class, "index")
                                            .addStatement("return $T.insertOrderedMultipleNamedPort(model, this, vertex, $S, null, index)",
                                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                                    port.name)
                                            .returns(TypeName.BOOLEAN));
                            methodsBuilders.add(
                                    MethodSpec.methodBuilder("insert" + toCamelCase(port.name) + "Port")
                                            .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
                                            .addParameter(ClassName.get("forsyde.io.java.core", "ForSyDeSystemGraph"), "model")
                                            .addParameter(vertexClass, "vertex")
                                            .addStatement("return $T.insertOrderedMultipleNamedPort(model, this, vertex, $S, null)",
                                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                                    port.name)
                                            .returns(TypeName.BOOLEAN));
                            break;
                        case INCOMING:
                            statement = "return $T.insertOrderedMultipleNamedPort(model, vertexes, this, null, $S)";
                            getPortMethod.addStatement(statement,
                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                    port.name);
                            methodsBuilders.add(
                                    MethodSpec.methodBuilder("insert" + toCamelCase(port.name) + "Port")
                                            .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
                                            .addParameter(ClassName.get("forsyde.io.java.core", "ForSyDeSystemGraph"), "model")
                                            .addParameter(vertexClass, "vertex")
                                            .addParameter(Integer.class, "index")
                                            .addStatement("return $T.insertOrderedMultipleNamedPort(model, this, vertex, null, $S, index)",
                                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                                    port.name)
                                            .returns(TypeName.BOOLEAN));
                            methodsBuilders.add(
                                    MethodSpec.methodBuilder("insert" + toCamelCase(port.name) + "Port")
                                            .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
                                            .addParameter(ClassName.get("forsyde.io.java.core", "ForSyDeSystemGraph"), "model")
                                            .addParameter(vertexClass, "vertex")
                                            .addStatement("return $T.insertOrderedMultipleNamedPort(model, this, vertex, null, $S)",
                                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                                    port.name)
                                            .returns(TypeName.BOOLEAN));
                            break;
                    }
                }
//				getPortMethod.addStatement("$T outList = new $T()", arrayType, arrayType);
            } else {
                getPortMethod.addParameter(setOut, "vertexes");
                String statement;
                if (port.edgeTraitSpec != null) {
                    switch (port.direction) {
                        case BIDIRECTIONAL:
                        case OUTGOING:
                            statement = "return $T.addMultipleNamedPort(model, this, vertexes, $S, null, $T.$L)";
                            getPortMethod.addStatement(statement,
                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                    port.name,
                                    ClassName.get("forsyde.io.java.core", "EdgeTrait"),
                                    port.edgeTraitSpec.name.replace("::", "_").toUpperCase()
                            );
                            methodsBuilders.add(
                                    MethodSpec.methodBuilder("insert" + toCamelCase(port.name) + "Port")
                                            .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
                                            .addParameter(ClassName.get("forsyde.io.java.core", "ForSyDeSystemGraph"), "model")
                                            .addParameter(vertexClass, "vertex")
                                            .addStatement("return $T.addMultipleNamedPort(model, this, vertex, $S, null, $T.$L)",
                                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                                    port.name,
                                                    ClassName.get("forsyde.io.java.core", "EdgeTrait"),
                                                    port.edgeTraitSpec.name.replace("::", "_").toUpperCase())
                                            .returns(TypeName.BOOLEAN));
                            break;
                        case INCOMING:
                            statement = "return $T.addMultipleNamedPort(model, vertexes, this, null, $S, $T.$L)";
                            getPortMethod.addStatement(statement,
                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                    port.name,
                                    ClassName.get("forsyde.io.java.core", "EdgeTrait"),
                                    port.edgeTraitSpec.name.replace("::", "_").toUpperCase()
                            );
                            methodsBuilders.add(
                                    MethodSpec.methodBuilder("insert" + toCamelCase(port.name) + "Port")
                                            .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
                                            .addParameter(ClassName.get("forsyde.io.java.core", "ForSyDeSystemGraph"), "model")
                                            .addParameter(vertexClass, "vertex")
                                            .addStatement("return $T.addMultipleNamedPort(model, this, vertex, null, $S, $T.$L)",
                                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                                    port.name,
                                                    ClassName.get("forsyde.io.java.core", "EdgeTrait"),
                                                    port.edgeTraitSpec.name.replace("::", "_").toUpperCase())
                                            .returns(TypeName.BOOLEAN));
                            break;
                    }
                } else {
                    switch (port.direction) {
                        case BIDIRECTIONAL:
                        case OUTGOING:
                            statement = "return $T.addMultipleNamedPort(model, this, vertexes, $S, null)";
                            getPortMethod.addStatement(statement,
                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                    port.name);
                            methodsBuilders.add(
                                    MethodSpec.methodBuilder("insert" + toCamelCase(port.name) + "Port")
                                            .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
                                            .addParameter(ClassName.get("forsyde.io.java.core", "ForSyDeSystemGraph"), "model")
                                            .addParameter(vertexClass, "vertex")
                                            .addStatement("return $T.addMultipleNamedPort(model, this, vertex, $S, null)",
                                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                                    port.name)
                                            .returns(TypeName.BOOLEAN));
                            break;
                        case INCOMING:
                            statement = "return $T.addMultipleNamedPort(model, vertexes, this, null, $S)";
                            getPortMethod.addStatement(statement,
                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                    port.name);
                            methodsBuilders.add(
                                    MethodSpec.methodBuilder("insert" + toCamelCase(port.name) + "Port")
                                            .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
                                            .addParameter(ClassName.get("forsyde.io.java.core", "ForSyDeSystemGraph"), "model")
                                            .addParameter(vertexClass, "vertex")
                                            .addStatement("return $T.addMultipleNamedPort(model, this, vertex, null, $S)",
                                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                                    port.name)
                                            .returns(TypeName.BOOLEAN));
                            break;
                    }
                }
//				getPortMethod.addStatement("$T outList = new $T()", setType, setType);
            }
        } else {
            getPortMethod.addParameter(vertexClass, "vertex");
            String statement;
            if (port.edgeTraitSpec != null) {
                switch (port.direction) {
                    case BIDIRECTIONAL:
                    case OUTGOING:
                        statement = "return $T.setNamedPort(model, this, vertex, $S, null, $T.$L)";
                        getPortMethod.addStatement(statement,
                                ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                port.name,
                                ClassName.get("forsyde.io.java.core", "EdgeTrait"),
                                port.edgeTraitSpec.name.replace("::", "_").toUpperCase()
                        );
                        break;
                    case INCOMING:
                        statement = "return $T.setNamedPort(model, vertex, this, null, $S, $T.$L)";
                        getPortMethod.addStatement(statement,
                                ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                port.name,
                                ClassName.get("forsyde.io.java.core", "EdgeTrait"),
                                port.edgeTraitSpec.name.replace("::", "_").toUpperCase()
                        );
                        break;
                }
            } else {
                switch (port.direction) {
                    case BIDIRECTIONAL:
                    case OUTGOING:
                        statement = "return $T.setNamedPort(model, this, vertex, $S, null)";
                        getPortMethod.addStatement(statement,
                                ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                port.name);
                        break;
                    case INCOMING:
                        statement = "return $T.setNamedPort(model, vertex, this, null, $S)";
                        getPortMethod.addStatement(statement,
                                ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                port.name);
                        break;
                }
            }
        }
        return methodsBuilders.stream().map(MethodSpec.Builder::build).collect(Collectors.toSet());
    }

    public MethodSpec generatePortSetterToOtherPort(PortSpec port) {
        final String traitName = port.vertexTrait.name.replace("::", "_").toUpperCase();
        final String extraPackages = port.vertexTrait.getNamespaces().isEmpty() ?
                "" : "." + String.join(".", port.vertexTrait.getNamespaces());
        final TypeName vertexClass = ClassName.get("forsyde.io.java.typed.viewers" + extraPackages, port.vertexTrait.getTraitLocalName());
        final ParameterizedTypeName listOut = ParameterizedTypeName.get(ClassName.get(List.class), vertexClass);
        final ParameterizedTypeName setOut = ParameterizedTypeName.get(ClassName.get(Set.class), vertexClass);
        MethodSpec.Builder getPortMethodWithOtherPort = MethodSpec.methodBuilder("set" + toCamelCase(port.name) + "Port")
                .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
                .addParameter(ClassName.get("forsyde.io.java.core", "ForSyDeSystemGraph"), "model")
                .returns(TypeName.BOOLEAN);
        if (port.multiple.orElse(true)) {
            if (port.ordered.orElse(false)) {
                if (port.edgeTraitSpec != null) {
                    String statement;
                    switch (port.direction) {
                        case BIDIRECTIONAL:
                        case OUTGOING:
                            getPortMethodWithOtherPort.addParameter(listOut, "vertexes");
                            getPortMethodWithOtherPort.addParameter(String.class, "portDst");
                            statement = "return $T.insertOrderedMultipleNamedPort(model, this, vertexes, $S, portDst, $T.$L)";
                            getPortMethodWithOtherPort.addStatement(statement,
                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                    port.name,
                                    ClassName.get("forsyde.io.java.core", "EdgeTrait"),
                                    port.edgeTraitSpec.name.replace("::", "_").toUpperCase()
                            );
                            break;
                        case INCOMING:
                            getPortMethodWithOtherPort.addParameter(String.class, "portSrc");
                            getPortMethodWithOtherPort.addParameter(listOut, "vertexes");
                            statement = "return $T.insertOrderedMultipleNamedPort(model, vertexes, this, portSrc, $S, $T.$L)";
                            getPortMethodWithOtherPort.addStatement(statement,
                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                    port.name,
                                    ClassName.get("forsyde.io.java.core", "EdgeTrait"),
                                    port.edgeTraitSpec.name.replace("::", "_").toUpperCase()
                            );
                            break;
                    }
                } else {
                    String statement;
                    switch (port.direction) {
                        case BIDIRECTIONAL:
                        case OUTGOING:
                            getPortMethodWithOtherPort.addParameter(listOut, "vertexes");
                            getPortMethodWithOtherPort.addParameter(String.class, "portDst");
                            statement = "return $T.insertOrderedMultipleNamedPort(model, this, vertexes, $S, portDst)";
                            getPortMethodWithOtherPort.addStatement(statement,
                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                    port.name);
                            break;
                        case INCOMING:
                            getPortMethodWithOtherPort.addParameter(String.class, "portSrc");
                            getPortMethodWithOtherPort.addParameter(listOut, "vertexes");
                            statement = "return $T.insertOrderedMultipleNamedPort(model, vertexes, this, portSrc, $S)";
                            getPortMethodWithOtherPort.addStatement(statement,
                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                    port.name);
                            break;
                    }
                }
//				getPortMethod.addStatement("$T outList = new $T()", arrayType, arrayType);
            } else {
                String statement;
                if (port.edgeTraitSpec != null) {
                    switch (port.direction) {
                        case BIDIRECTIONAL:
                        case OUTGOING:
                            getPortMethodWithOtherPort.addParameter(setOut, "vertexes");
                            getPortMethodWithOtherPort.addParameter(String.class, "portDst");
                            statement = "return $T.addMultipleNamedPort(model, this, vertexes, $S, portDst, $T.$L)";
                            getPortMethodWithOtherPort.addStatement(statement,
                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                    port.name,
                                    ClassName.get("forsyde.io.java.core", "EdgeTrait"),
                                    port.edgeTraitSpec.name.replace("::", "_").toUpperCase()
                            );
                            break;
                        case INCOMING:
                            getPortMethodWithOtherPort.addParameter(String.class, "portSrc");
                            getPortMethodWithOtherPort.addParameter(listOut, "vertexes");
                            statement = "return $T.addMultipleNamedPort(model, vertexes, this, portSrc, $S, $T.$L)";
                            getPortMethodWithOtherPort.addStatement(statement,
                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                    port.name,
                                    ClassName.get("forsyde.io.java.core", "EdgeTrait"),
                                    port.edgeTraitSpec.name.replace("::", "_").toUpperCase()
                            );
                            break;
                    }
                } else {
                    switch (port.direction) {
                        case BIDIRECTIONAL:
                        case OUTGOING:
                            getPortMethodWithOtherPort.addParameter(setOut, "vertexes");
                            getPortMethodWithOtherPort.addParameter(String.class, "portDst");
                            statement = "return $T.addMultipleNamedPort(model, this, vertexes, $S, portDst)";
                            getPortMethodWithOtherPort.addStatement(statement,
                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                    port.name);
                            break;
                        case INCOMING:
                            getPortMethodWithOtherPort.addParameter(String.class, "portSrc");
                            getPortMethodWithOtherPort.addParameter(listOut, "vertexes");
                            statement = "return $T.addMultipleNamedPort(model, vertexes, this, portSrc, $S)";
                            getPortMethodWithOtherPort.addStatement(statement,
                                    ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                    port.name);
                            break;
                    }
                }
//				getPortMethod.addStatement("$T outList = new $T()", setType, setType);
            }
        } else {
            String statement;
            if (port.edgeTraitSpec != null) {
                switch (port.direction) {
                    case BIDIRECTIONAL:
                    case OUTGOING:
                        getPortMethodWithOtherPort.addParameter(vertexClass, "vertex");
                        getPortMethodWithOtherPort.addParameter(String.class, "portDst");
                        statement = "return $T.setNamedPort(model, this, vertex, $S, portDst, $T.$L)";
                        getPortMethodWithOtherPort.addStatement(statement,
                                ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                port.name,
                                ClassName.get("forsyde.io.java.core", "EdgeTrait"),
                                port.edgeTraitSpec.name.replace("::", "_").toUpperCase()
                        );
                        break;
                    case INCOMING:
                        getPortMethodWithOtherPort.addParameter(String.class, "portSrc");
                        getPortMethodWithOtherPort.addParameter(vertexClass, "vertex");
                        statement = "return $T.setNamedPort(model, vertex, this, portSrc, $S, $T.$L)";
                        getPortMethodWithOtherPort.addStatement(statement,
                                ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                port.name,
                                ClassName.get("forsyde.io.java.core", "EdgeTrait"),
                                port.edgeTraitSpec.name.replace("::", "_").toUpperCase()
                        );
                        break;
                }
            } else {
                switch (port.direction) {
                    case BIDIRECTIONAL:
                    case OUTGOING:
                        getPortMethodWithOtherPort.addParameter(vertexClass, "vertex");
                        getPortMethodWithOtherPort.addParameter(String.class, "portDst");
                        statement = "return $T.setNamedPort(model, this, vertex, $S, portDst)";
                        getPortMethodWithOtherPort.addStatement(statement,
                                ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                port.name);
                        break;
                    case INCOMING:
                        getPortMethodWithOtherPort.addParameter(String.class, "portSrc");
                        getPortMethodWithOtherPort.addParameter(vertexClass, "vertex");
                        statement = "return $T.setNamedPort(model, vertex, this, portSrc, $S)";
                        getPortMethodWithOtherPort.addStatement(statement,
                                ClassName.get("forsyde.io.java.core", "VertexAcessor"),
                                port.name);
                        break;
                }
            }
        }
        return getPortMethodWithOtherPort.build();
    }

    public TypeSpec generateVertexTraitEnum(TraitHierarchy model) {
        ClassName generalTraitClass = ClassName.get("forsyde.io.java.core", "Trait");
        final TypeSpec.Builder vertexTraitEnumBuilder = TypeSpec.enumBuilder("VertexTrait")
                .addSuperinterface(generalTraitClass).addModifiers(Modifier.PUBLIC)
                .addField(String.class, "canonicalName", Modifier.PRIVATE, Modifier.FINAL)
                .addMethod(MethodSpec.constructorBuilder()
                        .addParameter(String.class, "canonicalName")
                        .addStatement("this.$N = $N", "canonicalName", "canonicalName")
                        .build());

        // // refinement method
        final MethodSpec.Builder refinesMethod = MethodSpec.methodBuilder("refines")
                .returns(TypeName.BOOLEAN)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(ClassName.get("forsyde.io.java.core", "VertexTrait"), "one")
                .addParameter(ClassName.get("forsyde.io.java.core", "VertexTrait"), "other")
                .beginControlFlow("switch (one)");
        final MethodSpec.Builder fromNameMethod = MethodSpec.methodBuilder("fromName")
                .returns(ClassName.get("forsyde.io.java.core", "Trait"))
                .addParameter(String.class, "traitName")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .beginControlFlow("switch (traitName)");
        final MethodSpec.Builder getNameMethod = MethodSpec.methodBuilder("getName")
                .returns(String.class)
                .addModifiers(Modifier.PUBLIC)
                .addStatement("return canonicalName");
        List<VertexTraitSpec> traits = model.vertexTraits.stream().collect(Collectors.toList());
        final Set<String> visitedtraits = new HashSet<>();
        for (VertexTraitSpec trait : traits) {
            final String enumTraitName = trait.name.replace("::", "_").toUpperCase();
            if (!visitedtraits.contains(enumTraitName)) {
                visitedtraits.add(enumTraitName);
                vertexTraitEnumBuilder.addEnumConstant(enumTraitName,
                        TypeSpec.anonymousClassBuilder("$S", trait.name).build());


                refinesMethod.addCode("case " + enumTraitName + ":\n");
                refinesMethod.beginControlFlow("switch (other)");
                // def superIterator = new DepthFirstIterator<String, DefaultEdge>(traitGraph,
                // vertexTrait.key)
                Queue<VertexTraitSpec> refinedTraits = new LinkedList<>(List.of(trait));
                while (!refinedTraits.isEmpty()) {
                    VertexTraitSpec refinedTrait = refinedTraits.poll();
                    final String refinedEnumTraitName = refinedTrait.name.replace("::", "_").toUpperCase();
                    refinesMethod.addCode("case " + refinedEnumTraitName + ": ");
                    refinesMethod.addStatement("return true");
                    refinedTraits.addAll(refinedTrait.refinedTraits);
                }
                refinesMethod.addStatement("default: return false");
                refinesMethod.endControlFlow();

                //getNameMethod.addStatement("case $L: return $S", enumTraitName, trait.name);
                //getNameMethod.addStatement("case $S: return \"" + trait.name + "\"");

                fromNameMethod.addCode("case $S:\n", trait.name);
                fromNameMethod.addStatement("case $S: return VertexTrait.$L", enumTraitName, enumTraitName);
            }
        }
        refinesMethod.addStatement("default: return false");
        refinesMethod.endControlFlow();

        //getNameMethod.addStatement("default: return \"\"");
        //getNameMethod.endControlFlow();

        fromNameMethod.addStatement("default: return new $T($L)", ClassName.get("forsyde.io.java.core", "OpaqueTrait"), "traitName");
        fromNameMethod.endControlFlow();

        vertexTraitEnumBuilder.addMethod(refinesMethod.build());
        vertexTraitEnumBuilder.addMethod(MethodSpec.methodBuilder("refines").returns(TypeName.BOOLEAN)
                .addModifiers(Modifier.PUBLIC).addParameter(ClassName.get("forsyde.io.java.core", "Trait"), "other")
                .addStatement(
                        "return other instanceof VertexTrait ? VertexTrait.refines(this, (VertexTrait) other) : false")
                .build());
        vertexTraitEnumBuilder.addMethod(fromNameMethod.build());
        vertexTraitEnumBuilder.addMethod(getNameMethod.build());
        return vertexTraitEnumBuilder.build();
    }

    public TypeSpec generateEdgeTraitsEnum(TraitHierarchy model) {
        ClassName traitClass = ClassName.get("forsyde.io.java.core", "Trait");
        TypeSpec.Builder edgeEnumBuilder = TypeSpec.enumBuilder("EdgeTrait").addSuperinterface(traitClass)
                .addModifiers(Modifier.PUBLIC)
                .addField(String.class, "canonicalName", Modifier.PRIVATE, Modifier.FINAL)
                .addMethod(MethodSpec.constructorBuilder()
                        .addParameter(String.class, "canonicalName")
                        .addStatement("this.$N = $N", "canonicalName", "canonicalName")
                        .build());
        MethodSpec.Builder refinesMethod = MethodSpec.methodBuilder("refines").returns(TypeName.BOOLEAN)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(ClassName.get("forsyde.io.java.core", "EdgeTrait"), "one")
                .addParameter(ClassName.get("forsyde.io.java.core", "EdgeTrait"), "other")
                .beginControlFlow("switch (one)");
        final MethodSpec.Builder fromNameMethod = MethodSpec.methodBuilder("fromName")
                .returns(ClassName.get("forsyde.io.java.core", "Trait"))
                .addParameter(String.class, "traitName")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .beginControlFlow("switch (traitName)");
        final MethodSpec.Builder getNameMethod = MethodSpec.methodBuilder("getName")
                .returns(String.class)
                .addModifiers(Modifier.PUBLIC)
                .addStatement("return canonicalName");
        List<EdgeTraitSpec> traits = model.edgeTraits.stream().collect(Collectors.toList());
        for (EdgeTraitSpec trait : traits) {
            final String enumTraitName = trait.name.replace("::", "_").toUpperCase();
            edgeEnumBuilder.addEnumConstant(enumTraitName, TypeSpec.anonymousClassBuilder("$S", trait.name).build());

            refinesMethod.addCode("case " + enumTraitName + ":\n");
            refinesMethod.beginControlFlow("switch (other)");
            Queue<EdgeTraitSpec> refinedTraits = new LinkedList<>(List.of(trait));
            while (!refinedTraits.isEmpty()) {
                EdgeTraitSpec refinedTrait = refinedTraits.poll();
                final String refinedEnumTraitName = refinedTrait.name.replace("::", "_").toUpperCase();
                refinesMethod.addCode("case " + refinedEnumTraitName + ": ");
                refinesMethod.addStatement("return true");
                refinedTraits.addAll(refinedTrait.refinedTraits);
            }
            refinesMethod.addStatement("default: return false");
            refinesMethod.endControlFlow();

            //getNameMethod.addStatement("case " + enumTraitName + ": return \"" + trait.name + "\"");

            fromNameMethod.addCode("case $S:\n", trait.name);
            fromNameMethod.addStatement("case $S: return EdgeTrait.$L", enumTraitName, enumTraitName);
        }
        refinesMethod.addStatement("default: return false");
        refinesMethod.endControlFlow();

        //getNameMethod.addStatement("default: return \"\"");
        //getNameMethod.endControlFlow();

        fromNameMethod.addStatement("default: return new $T($L)", ClassName.get("forsyde.io.java.core", "OpaqueTrait"), "traitName");
        fromNameMethod.endControlFlow();

        edgeEnumBuilder.addMethod(refinesMethod.build());
        edgeEnumBuilder.addMethod(MethodSpec.methodBuilder("refines").returns(TypeName.BOOLEAN)
                .addModifiers(Modifier.PUBLIC).addParameter(ClassName.get("forsyde.io.java.core", "Trait"), "other")
                .addStatement("return other instanceof EdgeTrait ? EdgeTrait.refines(this, (EdgeTrait) other) : false")
                .build());
        edgeEnumBuilder.addMethod(fromNameMethod.build());
        edgeEnumBuilder.addMethod(getNameMethod.build());
        return edgeEnumBuilder.build();
    }

    public TypeSpec generateVertexInterface(VertexTraitSpec trait) {
        /*
        List<VertexTraitSpec> traits = model.vertexTraits.stream().collect(Collectors.toList());
        List<TypeSpec> traitList = new ArrayList<>(traits.size());
        for (VertexTraitSpec trait : traits) {
        */
        final String enumTraitName = trait.name.replace("::", "_").toUpperCase();
        final String extraPackages = trait.getNamespaces().isEmpty() ?
                "" : "." + String.join(".", trait.getNamespaces());
        TypeSpec.Builder traitInterface = TypeSpec.interfaceBuilder(ClassName.get("forsyde.io.java.typed.viewers" + extraPackages, trait.getTraitLocalName()))
                .addModifiers(Modifier.PUBLIC)
                .addJavadoc(trait.comment);
        for (VertexTraitSpec refinedTrait : trait.refinedTraits) {
            final String refinedExtraPackages = refinedTrait.getNamespaces().isEmpty() ?
                    "" : "." + String.join(".", refinedTrait.getNamespaces());
            final ClassName superClass = ClassName.get("forsyde.io.java.typed.viewers" + refinedExtraPackages, refinedTrait.getTraitLocalName());
            traitInterface
                    .addSuperinterface(superClass);
//            conformsMethod.addStatement("if ($T.conforms(vertex)) return true", superClass);
        }
        traitInterface.addSuperinterface(ClassName.get("forsyde.io.java.core", "VertexViewer"));

        for (PropertySpec prop : trait.requiredProperties) {
            traitInterface.addMethod(generatePropertyGetter(prop));
            traitInterface.addMethod(generatePropertySetter(prop));
        }
        for (PortSpec port : trait.requiredPorts) {
            traitInterface.addMethod(generatePortGetter(port));
            for (MethodSpec portSetter: generatePortSetters(port)) {
                traitInterface.addMethod(portSetter);
            }
            traitInterface.addMethod(generatePortSetterToOtherPort(port));
        }

        MethodSpec.Builder conformsMethod = MethodSpec.methodBuilder("conforms")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(ClassName.get("forsyde.io.java.core", "Vertex"), "vertex")
                .returns(ClassName.get(Boolean.class));
        conformsMethod.beginControlFlow("for ($T t : vertex.getTraits())",
                ClassName.get("forsyde.io.java.core", "Trait"));
        conformsMethod.addStatement("if(t.refines($T.$L)) return true",
                ClassName.get("forsyde.io.java.core", "VertexTrait"), enumTraitName);
        conformsMethod.endControlFlow();
        conformsMethod.addStatement("return false");
        traitInterface.addMethod(conformsMethod.build());

        MethodSpec.Builder conformsMethodViewer = MethodSpec.methodBuilder("conforms")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(ClassName.get("forsyde.io.java.core", "VertexViewer"), "viewer")
                .returns(ClassName.get(Boolean.class));
//        conformsMethodViewer.beginControlFlow("for ($T t : viewer.getViewedVertex().getTraits())",
//                ClassName.get("forsyde.io.java.core", "Trait"));
//        conformsMethodViewer.addStatement("if(t.refines($T.$L)) return true",
//                ClassName.get("forsyde.io.java.core", "VertexTrait"), enumTraitName);
//        conformsMethodViewer.endControlFlow();
        conformsMethodViewer.addStatement("return conforms(viewer.getViewedVertex())");
        traitInterface.addMethod(conformsMethodViewer.build());

        MethodSpec.Builder safeCast = MethodSpec.methodBuilder("safeCast")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(ClassName.get("forsyde.io.java.core", "Vertex"), "vertex")
                .returns(ParameterizedTypeName.get(ClassName.get(Optional.class),
                        ClassName.get("forsyde.io.java.typed.viewers" + extraPackages, trait.getTraitLocalName())));
        safeCast.addStatement("return conforms(vertex) ? $T.of(new $T(vertex)) : Optional.empty()",
                ClassName.get(Optional.class),
                ClassName.get("forsyde.io.java.typed.viewers" + extraPackages, trait.getTraitLocalName() + "Viewer"));
        traitInterface.addMethod(safeCast.build());

        MethodSpec.Builder safeCastViewer = MethodSpec.methodBuilder("safeCast")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(ClassName.get("forsyde.io.java.core", "VertexViewer"), "viewer")
                .returns(ParameterizedTypeName.get(ClassName.get(Optional.class),
                        ClassName.get("forsyde.io.java.typed.viewers" + extraPackages, trait.getTraitLocalName())));
        safeCastViewer.addStatement(
                "return conforms(viewer.getViewedVertex()) ? $T.of(new $T(viewer.getViewedVertex())) : Optional.empty()",
                ClassName.get(Optional.class),
                ClassName.get("forsyde.io.java.typed.viewers" + extraPackages, trait.getTraitLocalName() + "Viewer"));
        traitInterface.addMethod(safeCastViewer.build());

        traitInterface.addMethod(generateRequiredPortGetter(trait));

        traitInterface.addMethod(generateRequiredPropertyGetter(trait));

        traitInterface.addMethod(generateEnforce(trait));

        traitInterface.addMethod(generateEnforceFromViewer(trait));

        return traitInterface.build();
        /*
            traitList.add(traitInterface.build());
        }
        return traitList;
         */
    }

    public TypeSpec generateVertexViewer(VertexTraitSpec trait) {
        /*
        List<VertexTraitSpec> traits = model.vertexTraits.stream().collect(Collectors.toList());
        List<TypeSpec> viewersList = new ArrayList<>(traits.size());
        for (VertexTraitSpec trait : traits) {
         */
        final String enumTraitName = trait.name.replace("::", "_").toUpperCase();
        final String extraPackages = trait.getNamespaces().isEmpty() ?
                "" : "." + String.join(".", trait.getNamespaces());

        final TypeSpec.Builder viewerClass = TypeSpec.classBuilder(ClassName.get("forsyde.io.java.typed.viewers" + extraPackages, trait.getTraitLocalName()  + "Viewer"))
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addSuperinterface(ClassName.get("forsyde.io.java.typed.viewers" + extraPackages, trait.getTraitLocalName()))
                //.superclass(ClassName.get("forsyde.io.java.core", "VertexViewer"))
                .addJavadoc(trait.comment)
                .addField(ClassName.get("forsyde.io.java.core", "Vertex"), "viewedVertex", Modifier.PUBLIC,
                        Modifier.FINAL);

        final MethodSpec.Builder constructorMethod = MethodSpec.constructorBuilder()
                .addParameter(ClassName.get("forsyde.io.java.core", "Vertex"), "vertex")
                .addModifiers(Modifier.PUBLIC);
        constructorMethod.addStatement("viewedVertex = vertex");
        viewerClass.addMethod(constructorMethod.build());
        viewerClass.addMethod(MethodSpec.methodBuilder("getViewedVertex").addModifiers(Modifier.PUBLIC)
                .returns(ClassName.get("forsyde.io.java.core", "Vertex")).addStatement("return viewedVertex")
                .build());

        viewerClass.addMethod(
                MethodSpec.methodBuilder("hashCode")
                        .addAnnotation(Override.class)
                        .addModifiers(Modifier.PUBLIC)
                        .addStatement("return getIdentifier().hashCode() + \"$L\".hashCode()", trait.getTraitLocalName())
                        .returns(int.class)
                        .build());

        viewerClass.addMethod(
                MethodSpec.methodBuilder("equals")
                        .addAnnotation(Override.class)
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(Object.class, "other")
                        .addStatement("return other instanceof $L ? (($L) other).getIdentifier().equals(getIdentifier()) : false",
                                trait.getTraitLocalName() + "Viewer", trait.getTraitLocalName() + "Viewer")
                        .returns(boolean.class)
                        .build());

        viewerClass.addMethod(
                MethodSpec.methodBuilder("toString")
                        .addAnnotation(Override.class)
                        .addModifiers(Modifier.PUBLIC)
                        .addStatement("return \"$L{\" + getViewedVertex().toString() + \"}\"", trait.getTraitLocalName() + "Viewer")
                        .returns(String.class)
                        .build());
        return viewerClass.build();
        /*
            viewersList.add(viewerClass.build());
        }
        return viewersList;

         */
    }

    public TypeSpec generateEdgeAcessors(EdgeTraitSpec trait) {
        /*
        List<VertexTraitSpec> traits = model.vertexTraits.stream().collect(Collectors.toList());
        List<TypeSpec> viewersList = new ArrayList<>(traits.size());
        for (VertexTraitSpec trait : traits) {
         */
        final String enumTraitName = trait.name.replace("::", "_").toUpperCase();
        final String extraPackages = trait.getNamespaces().isEmpty() ?
                "" : "." + String.join(".", trait.getNamespaces());
        final TypeName edgeTraitsType = ClassName.get("forsyde.io.java.core", "EdgeTrait");
        final TypeName edgeAcessorInterface = ClassName.get("forsyde.io.java.core", "EdgeAcessor");
        final ClassName edgeAcessorType = ClassName.get("forsyde.io.java.typed.edges" + extraPackages, trait.getTraitLocalName());
        final ClassName systemGraphType = ClassName.get("forsyde.io.java.core", "ForSyDeSystemGraph");
        final ClassName vertexType = ClassName.get("forsyde.io.java.core", "Vertex");
        final ClassName vertexViewerType = ClassName.get("forsyde.io.java.core", "VertexViewer");

        final TypeSpec.Builder edgeAcessorBuilder = TypeSpec.classBuilder(edgeAcessorType)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addSuperinterface(edgeAcessorInterface)
                //.superclass(ClassName.get("forsyde.io.java.core", "VertexViewer"))
                .addJavadoc(trait.comment)
                .addField(edgeAcessorType, "instance", Modifier.PRIVATE,
                        Modifier.STATIC);

        final MethodSpec.Builder constructorMethod = MethodSpec.constructorBuilder()
//                .addParameter(ClassName.get("forsyde.io.java.core", "Vertex"), "vertex")
                .addModifiers(Modifier.PRIVATE);
//        constructorMethod.addStatement("viewedVertex = vertex");
        edgeAcessorBuilder.addMethod(constructorMethod.build());
        edgeAcessorBuilder.addMethod(MethodSpec.methodBuilder("getInstance")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                .returns(edgeAcessorType)
                .addStatement("if (instance == null) instance = new $T()", edgeAcessorType)
                .addStatement("return instance")
                .build());

        edgeAcessorBuilder.addField(FieldSpec.builder(ArrayTypeName.of(edgeTraitsType), "edgeTraits")
                        .addModifiers(Modifier.FINAL, Modifier.STATIC)
                        .initializer(" {$T.$L}", edgeTraitsType, enumTraitName)
                .build());
        edgeAcessorBuilder.addMethod(MethodSpec.methodBuilder("getEdgeTraits")
                        .addModifiers(Modifier.PUBLIC)
                        .returns(ArrayTypeName.of(edgeTraitsType))
                .addStatement("return edgeTraits")
                .build());

        edgeAcessorBuilder.addMethod(
                MethodSpec.methodBuilder("create")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(systemGraphType, "model")
                        .addParameter(vertexType, "src")
                        .addParameter(vertexType, "dst")
                        .addStatement("$T.createStatic(getInstance(), model, src, dst)", edgeAcessorInterface)
                        .build());

        edgeAcessorBuilder.addMethod(
                MethodSpec.methodBuilder("create")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(systemGraphType, "model")
                        .addParameter(vertexType, "src")
                        .addParameter(vertexType, "dst")
                        .addParameter(String.class, "srcPort")
                        .addStatement("$T.createStatic(getInstance(), model, src, dst, srcPort)", edgeAcessorInterface)
                        .build());

        edgeAcessorBuilder.addMethod(
                MethodSpec.methodBuilder("create")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(systemGraphType, "model")
                        .addParameter(vertexType, "src")
                        .addParameter(vertexType, "dst")
                        .addParameter(String.class, "srcPort")
                        .addParameter(String.class, "dstPort")
                        .addStatement("$T.createStatic(getInstance(), model, src, dst, srcPort, dstPort)", edgeAcessorInterface)
                        .build());

        edgeAcessorBuilder.addMethod(
                MethodSpec.methodBuilder("create")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(systemGraphType, "model")
                        .addParameter(vertexViewerType, "src")
                        .addParameter(vertexViewerType, "dst")
                        .addStatement("$T.createStatic(getInstance(), model, src, dst)", edgeAcessorInterface)
                        .build());

        edgeAcessorBuilder.addMethod(
                MethodSpec.methodBuilder("create")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(systemGraphType, "model")
                        .addParameter(vertexViewerType, "src")
                        .addParameter(vertexViewerType, "dst")
                        .addParameter(String.class, "srcPort")
                        .addStatement("$T.createStatic(getInstance(), model, src, dst, srcPort)", edgeAcessorInterface)
                        .build());

        edgeAcessorBuilder.addMethod(
                MethodSpec.methodBuilder("create")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(systemGraphType, "model")
                        .addParameter(vertexViewerType, "src")
                        .addParameter(vertexViewerType, "dst")
                        .addParameter(String.class, "srcPort")
                        .addParameter(String.class, "dstPort")
                        .addStatement("$T.createStatic(getInstance(), model, src, dst, srcPort, dstPort)", edgeAcessorInterface)
                        .build());

        return edgeAcessorBuilder.build();
        /*
            viewersList.add(viewerClass.build());
        }
        return viewersList;

         */
    }

    private Integer directionToInt(PortDirection dir) {
        switch (dir) {
            case INCOMING:
                return 0;
            case OUTGOING:
                return 1;
            default:
                return 2;
        }
    }


}
