package forsyde.io.java.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.squareup.javapoet.*;
import forsyde.io.core.VertexTrait;
import forsyde.io.core.*;
import forsyde.io.core.annotations.*;
import forsyde.io.java.generator.specs.EdgeTraitSpec;
import forsyde.io.java.generator.specs.TraitHierarchySpec;
import forsyde.io.java.generator.specs.VertexTraitSpec;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.util.ElementFilter;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SupportedAnnotationTypes({ "forsyde.io.core.annotations.RegisterTrait" })
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class TraitHierarchyProcessor extends AbstractProcessor implements HasSpecExtractors, HasSpecCatalogGeneration {

    protected Optional<? extends TypeElement> getValueOfAnnotationMirror(AnnotationMirror annotationMirror) {
        return annotationMirror.getElementValues().entrySet().stream()
                .filter(e -> e.getKey().getSimpleName().contentEquals("value"))
                .flatMap(e -> processingEnv.getElementUtils().getAllTypeElements(e.getValue().getValue().toString())
                        .stream())
                .findAny();
    }

    protected List<? extends TypeElement> topoSortAscendingTypeElements(final Set<? extends TypeElement> types) {
        var sortedList = new ArrayList<TypeElement>(types.size());
        for (var t : types) {
            var minIdx = IntStream.range(0, sortedList.size())
                    .filter(i -> processingEnv.getTypeUtils().isSubtype(t.asType(), sortedList.get(i).asType())).max()
                    .orElse(-1);
            var maxIdx = IntStream.range(0, sortedList.size())
                    .filter(i -> processingEnv.getTypeUtils().isSubtype(sortedList.get(i).asType(), t.asType())).min()
                    .orElse(-1);
            if (maxIdx > -1) {
                sortedList.add(maxIdx, t);
            } else if (minIdx > -1 && maxIdx == -1) {
                sortedList.add(minIdx + 1, t);
            } else {
                sortedList.add(t);
            }
        }
        return sortedList;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        var objectMapper = new ObjectMapper().registerModule(new Jdk8Module());
        var elementsToHierarchies = new HashMap<TypeElement, TraitHierarchySpec>();
        var elementsToVertexSpecs = new HashMap<TypeElement, VertexTraitSpec>();
        var elementsToEdgeSpecs = new HashMap<TypeElement, EdgeTraitSpec>();
        final TypeElement hierarchyT = processingEnv.getElementUtils()
                .getTypeElement(TraitHierarchy.class.getCanonicalName());
        final TypeElement vertexViewerT = processingEnv.getElementUtils()
                .getTypeElement(VertexViewer.class.getCanonicalName());
        final TypeElement edgeT = processingEnv.getElementUtils().getTypeElement(EdgeTrait.class.getCanonicalName());
        final Map<TypeElement, TypeElement> traitsToHierarchy = new HashMap<>();
        final Map<TypeElement, TypeSpec> traitToSpec = new HashMap<>();
        // final Map<TypeElement, TypeSpec> hierarchyToSpec = new HashMap<>();
        try {
            var toGenerate = roundEnv.getElementsAnnotatedWith(RegisterTrait.class);
            var typesToGenerate = ElementFilter.typesIn(toGenerate);
            var sortedTypesToGenerate = topoSortAscendingTypeElements(typesToGenerate);
            for (var typeElement : sortedTypesToGenerate) {
                processingEnv.getElementUtils().getAllAnnotationMirrors(typeElement).stream()
                        .flatMap(t -> getValueOfAnnotationMirror(t).stream())
                        .filter(t -> processingEnv.getTypeUtils().isSubtype(t.asType(), hierarchyT.asType())).findAny()
                        .ifPresent(t -> {
                            if (!elementsToHierarchies.containsKey(t)) {
                                var traitHierarchySpec = new TraitHierarchySpec();
                                traitHierarchySpec.canonicalName = t.getQualifiedName().toString().replace(".", "::");
                                elementsToHierarchies.put(t, traitHierarchySpec);
                            }
                            var traitHierarchySpec = elementsToHierarchies.get(t);
                            if (processingEnv.getTypeUtils().isSubtype(typeElement.asType(), vertexViewerT.asType())) {
                                var vertexSpec = extractVertexSpecFromTypeElement(processingEnv, typeElement,
                                        traitHierarchySpec);
                                elementsToVertexSpecs.put(typeElement, vertexSpec);
                                traitHierarchySpec.vertexTraits.put(vertexSpec.canonicalName, vertexSpec);
                            } else if (processingEnv.getTypeUtils().isSubtype(typeElement.asType(), edgeT.asType())) {
                                var edgeSpec = extractEdgeSpecFromTypeElement(processingEnv, typeElement,
                                        traitHierarchySpec, elementsToEdgeSpecs);
                                elementsToEdgeSpecs.put(typeElement, edgeSpec);
                                traitHierarchySpec.edgeTraits.put(edgeSpec.canonicalName, edgeSpec);
                            }
                        });
                var hierarchy = getRegisteredHierarchy(typeElement.getAnnotation(RegisterTrait.class));
                // hierarchyToSpec.putIfAbsent(hierarchy, makeHierarchy(hierarchy));
                if (processingEnv.getTypeUtils().isSubtype(typeElement.asType(), vertexViewerT.asType())) {
                    var spec = makeViewer(hierarchy, typeElement);
                    traitToSpec.put(typeElement, spec);
                    var javaFile = JavaFile
                            .builder(processingEnv.getElementUtils().getPackageOf(typeElement).toString(), spec)
                            .build();
                    javaFile.writeTo(processingEnv.getFiler());
                }
                traitsToHierarchy.put(typeElement, hierarchy);
            }
            for (var entry : elementsToVertexSpecs.entrySet()) {
                linkVertexSpecFromTypeElement(processingEnv, entry.getKey(), entry.getValue(), elementsToVertexSpecs,
                        elementsToEdgeSpecs);
            }
            var hierarchies = new HashSet<>(traitsToHierarchy.values());
            for (var typeHierarchy : hierarchies) {
                var containedTraits = traitsToHierarchy.entrySet().stream()
                        .filter(e -> e.getValue().equals(typeHierarchy)).map(Map.Entry::getKey)
                        .collect(Collectors.toSet());
                // hierarchyToSpec.putIfAbsent(hierarchy, makeHierarchy(hierarchy));
                var genTH = makeHierarchy(typeHierarchy, containedTraits);
                var javaFile = JavaFile
                        .builder(processingEnv.getElementUtils().getPackageOf(typeHierarchy).toString(), genTH).build();
                javaFile.writeTo(processingEnv.getFiler());
            }
            try {
                var res = processingEnv.getFiler().createResource(StandardLocation.SOURCE_OUTPUT, "", "java-generated-hierarchies.md");
                try (var oWriter = res.openWriter()) {
                    oWriter.write(getMarkdownDocumentation(elementsToHierarchies.values(), 1));
                }
            } catch (FilerException ignored) {
//
            }
            for (var hierarchy : elementsToHierarchies.values()) {
                try {
                    var res = processingEnv.getFiler().createResource(StandardLocation.SOURCE_OUTPUT, "", hierarchy.getJavaCanonicalName() + ".json");
                    try (var oWriter = res.openWriter()) {
                        oWriter.write(objectMapper.writeValueAsString(hierarchy));
                    }
                } catch (FilerException ignored) {
//
                }
            }

//            try {
//                var res = processingEnv.getFiler().getResource(StandardLocation.ANNOTATION_PROCESSOR_PATH, "", "java-generated-hierarchies.md");
//                res.delete();
//
//
//            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    protected TypeSpec makeViewer(TypeElement hierarchyInterface, TypeElement traitInterface) {
        var viewerClassSimpleName = traitInterface.getSimpleName().toString() + "Viewer";
        var generatedHierarchy = ClassName.get(
                processingEnv.getElementUtils().getPackageOf(hierarchyInterface).toString(),
                hierarchyElemToName(hierarchyInterface));
        final TypeSpec.Builder viewerClassBuilder = TypeSpec
                .classBuilder(ClassName.get(processingEnv.getElementUtils().getPackageOf(traitInterface).toString(),
                        viewerClassSimpleName))
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ClassName.get(traitInterface))
                .addSuperinterface(VertexViewer.class)
                .addJavadoc("Generated vertex viewer class for trait $L.\n {@inheritDoc}",
                        traitInterface.getQualifiedName().toString().replace(".", "::"))
                // .superclass(ClassName.get("forsyde.io.java.core", "VertexViewer"))
                .addField(Vertex.class, "vertex", Modifier.PRIVATE, Modifier.FINAL)
                .addField(SystemGraph.class, "systemGraph", Modifier.PRIVATE, Modifier.FINAL);
        var constructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PROTECTED)
                .addParameter(SystemGraph.class, "systemGraph")
                .addParameter(Vertex.class, "vertex")
                .addStatement("this.vertex = vertex")
                .addStatement("this.systemGraph = systemGraph")
                .build();
        viewerClassBuilder.addMethod(constructor);
        var getVertexMethod = MethodSpec.methodBuilder("getViewedVertex")
                .addModifiers(Modifier.PUBLIC)
                .addStatement("return vertex")
                .returns(Vertex.class)
                .build();
        viewerClassBuilder.addMethod(getVertexMethod);
        var getSystemGraphMethod = MethodSpec.methodBuilder("getViewedSystemGraph")
                .addModifiers(Modifier.PUBLIC)
                .addStatement("return systemGraph")
                .returns(SystemGraph.class)
                .build();
        viewerClassBuilder.addMethod(getSystemGraphMethod);
        var getTraitNameMethod = MethodSpec.methodBuilder("getTraitName")
                .addModifiers(Modifier.PUBLIC)
                .addStatement("return $S", traitInterface.getQualifiedName().toString().replace(".", "::"))
                .returns(String.class)
                .build();
        viewerClassBuilder.addMethod(getTraitNameMethod);

        var tryViewMethod = MethodSpec.methodBuilder("tryView")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(SystemGraph.class, "systemGraph")
                .addParameter(Vertex.class, "vertex")
                .addCode(CodeBlock.builder()
                        .beginControlFlow("if (vertex.hasTrait($T.VertexTraits.$L))", generatedHierarchy,
                                traitInterface.getSimpleName().toString())
                        .addStatement("return Optional.of(new $L(systemGraph, vertex))", viewerClassSimpleName)
                        .nextControlFlow("else")
                        .addStatement("return Optional.empty()")
                        .endControlFlow()
                        .build())
                .returns(ParameterizedTypeName.get(ClassName.get(Optional.class),
                        ClassName.get(processingEnv.getElementUtils().getPackageOf(traitInterface).toString(),
                                traitInterface.getSimpleName().toString() + "Viewer")))
                .build();
        viewerClassBuilder.addMethod(makeTryViewMethod(traitInterface, generatedHierarchy));
        var tryViewMethodFromViewer = MethodSpec.methodBuilder("tryView")
                .addTypeVariable(TypeVariableName.get("T").withBounds(ClassName.get(VertexViewer.class)))
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(TypeVariableName.get("T"), "otherViewer")
                .addStatement("return tryView(otherViewer.getViewedSystemGraph(), otherViewer.getViewedVertex())",
                        viewerClassSimpleName)
                .returns(ParameterizedTypeName.get(ClassName.get(Optional.class),
                        ClassName.get(processingEnv.getElementUtils().getPackageOf(traitInterface).toString(),
                                traitInterface.getSimpleName().toString() + "Viewer")))
                .build();
        viewerClassBuilder.addMethod(tryViewMethodFromViewer);

        var enforceMethod = makeEnforceMethod(traitInterface, generatedHierarchy);
        viewerClassBuilder.addMethod(enforceMethod);
        var enforceFromViewer = MethodSpec.methodBuilder("enforce")
                .addTypeVariable(TypeVariableName.get("T").withBounds(ClassName.get(VertexViewer.class)))
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(TypeVariableName.get("T"), "otherViewer")
                .addStatement("return enforce(otherViewer.getViewedSystemGraph() , otherViewer.getViewedVertex())")
                .returns(TypeName.get(traitInterface.asType()))
                .build();
        viewerClassBuilder.addMethod(enforceFromViewer);

        for (var portGetter : generatePortGetters(hierarchyInterface, traitInterface)) {
            viewerClassBuilder.addMethod(portGetter);
        }
        for (var portSetter : generatePortSetters(hierarchyInterface, traitInterface)) {
            viewerClassBuilder.addMethod(portSetter);
        }
        for (var propGetter : generatePropertyGetters(traitInterface)) {
            viewerClassBuilder.addMethod(propGetter);
        }
        for (var propSetter : generatePropertySetters(traitInterface)) {
            viewerClassBuilder.addMethod(propSetter);
        }

        viewerClassBuilder.addMethod(MethodSpec.methodBuilder("equals")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(Object.class, "other")
                .returns(TypeName.BOOLEAN)
                .addStatement("if (other instanceof $T vw) return vw.getIdentifier().equals(getIdentifier())",
                        VertexViewer.class)
                .addStatement("return false")
                .build());

        viewerClassBuilder.addMethod(MethodSpec.methodBuilder("hashCode")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .returns(TypeName.INT)
                .addStatement("return $T.hash(getIdentifier())", Objects.class)
                .build());

        return viewerClassBuilder.build();
    }

    protected Set<MethodSpec> generatePortSetters(TypeElement hierarchy, TypeElement viewerInterface) {
        var methods = new HashSet<MethodSpec>();
        var members = processingEnv.getElementUtils().getAllMembers(viewerInterface);
        var viewerClass = ClassName.get(processingEnv.getElementUtils().getPackageOf(viewerInterface).toString(),
                viewerInterface.getSimpleName().toString() + "Viewer");
        for (var member : members) {
            if (member instanceof ExecutableElement execMember
                    && (member.getAnnotation(InPort.class) != null || member.getAnnotation(OutPort.class) != null)) {
                var name = execMember.getSimpleName().toString();
                if (execMember.getReturnType() instanceof DeclaredType declaredType
                        && (declaredType.asElement().getSimpleName().contentEquals("Set"))) {
                    var capitalizedName = name.substring(0, 1).toUpperCase() + name.substring(1);
                    var getMethodBuilderWithoutPort = MethodSpec.methodBuilder("add" + capitalizedName)
                            .addModifiers(Modifier.PUBLIC).returns(viewerClass);
                    var getMethodBuilder = MethodSpec.methodBuilder("add" + capitalizedName)
                            .addParameter(String.class, "otherPort").addModifiers(Modifier.PUBLIC).returns(viewerClass);
                    if (execMember.getAnnotation(InPort.class) != null) {
                        var portInputType = declaredType.getTypeArguments().get(0).toString().contains("VertexViewer")
                                ? ClassName.get(OpaqueVertexViewer.class)
                                : ClassName.bestGuess(declaredType.getTypeArguments().get(0).toString());
                        getMethodBuilderWithoutPort.addParameter(portInputType, name)
                                .addParameter(ArrayTypeName.of(ClassName.get(EdgeTrait.class)), "extraEdgeTraits")
                                .varargs();
                        getMethodBuilder.addParameter(portInputType, name)
                                .addParameter(ArrayTypeName.of(ClassName.get(EdgeTrait.class)), "extraEdgeTraits")
                                .varargs();
                        if (execMember.getAnnotation(WithEdgeTrait.class) != null) {
                            getMethodBuilderWithoutPort
                                    .addStatement("$T[] edgeTraits = new $T[1 + extraEdgeTraits.length]",
                                            EdgeTrait.class, EdgeTrait.class)
                                    .addStatement(
                                            "$T.arraycopy(extraEdgeTraits, 0, edgeTraits, 0, extraEdgeTraits.length)",
                                            System.class)
                                    .addStatement("edgeTraits[extraEdgeTraits.length] = $T.EdgeTraits.$L",
                                            ClassName.get(
                                                    processingEnv.getElementUtils().getPackageOf(hierarchy).toString(),
                                                    hierarchyElemToName(hierarchy)),
                                            getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class))
                                                    .getSimpleName())
                                    .addStatement(
                                            "getViewedSystemGraph().connect($L, this, null, $S, edgeTraits)",
                                            name,
                                            name);
                            getMethodBuilder
                                    .addStatement("$T[] edgeTraits = new $T[1 + extraEdgeTraits.length]",
                                            EdgeTrait.class, EdgeTrait.class)
                                    .addStatement(
                                            "$T.arraycopy(extraEdgeTraits, 0, edgeTraits, 0, extraEdgeTraits.length)",
                                            System.class)
                                    .addStatement("edgeTraits[extraEdgeTraits.length] = $T.EdgeTraits.$L",
                                            ClassName.get(
                                                    processingEnv.getElementUtils().getPackageOf(hierarchy).toString(),
                                                    hierarchyElemToName(hierarchy)),
                                            getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class))
                                                    .getSimpleName())
                                    .addStatement(
                                            "getViewedSystemGraph().connect($L, this, otherPort, $S, edgeTraits)",
                                            name,
                                            name);
                        } else {
                            getMethodBuilderWithoutPort.addStatement(
                                    "getViewedSystemGraph().connect($L, this, null, $S, extraEdgeTraits)",
                                    name,
                                    name

                            );
                            getMethodBuilder.addStatement(
                                    "getViewedSystemGraph().connect($L, this, otherPort, $S, extraEdgeTraits)",
                                    name,
                                    name

                            );
                        }
                    }
                    if (execMember.getAnnotation(OutPort.class) != null) {
                        // checking for what type of return type it is
                        var portOutputType = declaredType.getTypeArguments().get(0).toString().contains("VertexViewer")
                                ? ClassName.get(OpaqueVertexViewer.class)
                                : ClassName.bestGuess(declaredType.getTypeArguments().get(0).toString());
                        getMethodBuilderWithoutPort.addParameter(portOutputType, name)
                                .addParameter(ArrayTypeName.of(ClassName.get(EdgeTrait.class)), "extraEdgeTraits")
                                .varargs();
                        getMethodBuilder.addParameter(portOutputType, name)
                                .addParameter(ArrayTypeName.of(ClassName.get(EdgeTrait.class)), "extraEdgeTraits")
                                .varargs();
                        if (execMember.getAnnotation(WithEdgeTrait.class) != null) {
                            var edgeTrait = getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class));
                            getMethodBuilderWithoutPort
                                    .addStatement("$T[] edgeTraits = new $T[1 + extraEdgeTraits.length]",
                                            EdgeTrait.class, EdgeTrait.class)
                                    .addStatement(
                                            "$T.arraycopy(extraEdgeTraits, 0, edgeTraits, 0, extraEdgeTraits.length)",
                                            System.class)
                                    .addStatement("edgeTraits[extraEdgeTraits.length] = $T.EdgeTraits.$L",
                                            ClassName.get(
                                                    processingEnv.getElementUtils().getPackageOf(hierarchy).toString(),
                                                    hierarchyElemToName(hierarchy)),
                                            getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class))
                                                    .getSimpleName())
                                    .addStatement(
                                            "getViewedSystemGraph().connect(this, $L, $S, edgeTraits)",
                                            name,
                                            name);
                            getMethodBuilder
                                    .addStatement("$T[] edgeTraits = new $T[1 + extraEdgeTraits.length]",
                                            EdgeTrait.class, EdgeTrait.class)
                                    .addStatement(
                                            "$T.arraycopy(extraEdgeTraits, 0, edgeTraits, 0, extraEdgeTraits.length)",
                                            System.class)
                                    .addStatement("edgeTraits[extraEdgeTraits.length] = $T.EdgeTraits.$L",
                                            ClassName.get(
                                                    processingEnv.getElementUtils().getPackageOf(hierarchy).toString(),
                                                    hierarchyElemToName(hierarchy)),
                                            getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class))
                                                    .getSimpleName())
                                    .addStatement(
                                            "getViewedSystemGraph().connect(this, $L, $S, otherPort, edgeTraits)",
                                            name,
                                            name);
                        } else {
                            getMethodBuilderWithoutPort.addStatement(
                                    "getViewedSystemGraph().connect(this, $L, $S, extraEdgeTraits)",
                                    name,
                                    name);
                            getMethodBuilder.addStatement(
                                    "getViewedSystemGraph().connect(this, $L, otherPort, $S, extraEdgeTraits)",
                                    name,
                                    name);
                        }
                    }
                    getMethodBuilderWithoutPort.addStatement("return this");
                    getMethodBuilder.addStatement("return this");
                    methods.add(getMethodBuilderWithoutPort.build());
                    methods.add(getMethodBuilder.build());
                } else if (execMember.getReturnType() instanceof DeclaredType declaredType
                        && (declaredType.asElement().getSimpleName().contentEquals("List"))) {
                    throw new IllegalArgumentException("Exception at " + viewerInterface.getQualifiedName().toString()
                            + " on " + execMember.getSimpleName()
                            + ": Lists are not supported. Use Sets and properties with your ordering scheme to achieve proper listing");
                } else if (execMember.getReturnType() instanceof DeclaredType declaredType
                        && (declaredType.asElement().getSimpleName().contentEquals("Optional"))) {
                    var getMethodBuilderWithoutPort = MethodSpec.methodBuilder(name).addModifiers(Modifier.PUBLIC)
                            .returns(viewerClass);
                    var getMethodBuilder = MethodSpec.methodBuilder(name).addParameter(String.class, "otherPort")
                            .addModifiers(Modifier.PUBLIC).returns(viewerClass);
                    if (execMember.getAnnotation(InPort.class) != null) {
                        var portInputType = declaredType.getTypeArguments().get(0).toString().contains("VertexViewer")
                                ? ClassName.get(OpaqueVertexViewer.class)
                                : ClassName.bestGuess(declaredType.getTypeArguments().get(0).toString());
                        getMethodBuilderWithoutPort.addParameter(portInputType, name)
                                .addParameter(ArrayTypeName.of(ClassName.get(EdgeTrait.class)), "extraEdgeTraits")
                                .varargs();
                        getMethodBuilder.addParameter(portInputType, name)
                                .addParameter(ArrayTypeName.of(ClassName.get(EdgeTrait.class)), "extraEdgeTraits")
                                .varargs();
                        if (execMember.getAnnotation(WithEdgeTrait.class) != null) {
                            getMethodBuilderWithoutPort
                                    .addStatement("$T[] edgeTraits = new $T[1 + extraEdgeTraits.length]",
                                            EdgeTrait.class, EdgeTrait.class)
                                    .addStatement(
                                            "$T.arraycopy(extraEdgeTraits, 0, edgeTraits, 0, extraEdgeTraits.length)",
                                            System.class)
                                    .addStatement("edgeTraits[extraEdgeTraits.length] = $T.EdgeTraits.$L",
                                            ClassName.get(
                                                    processingEnv.getElementUtils().getPackageOf(hierarchy).toString(),
                                                    hierarchyElemToName(hierarchy)),
                                            getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class))
                                                    .getSimpleName())
                                    .addStatement(
                                            "getViewedSystemGraph().connect($L, this, null, $S, edgeTraits)",
                                            name,
                                            name);
                            getMethodBuilder
                                    .addStatement("$T[] edgeTraits = new $T[1 + extraEdgeTraits.length]",
                                            EdgeTrait.class, EdgeTrait.class)
                                    .addStatement(
                                            "$T.arraycopy(extraEdgeTraits, 0, edgeTraits, 0, extraEdgeTraits.length)",
                                            System.class)
                                    .addStatement("edgeTraits[extraEdgeTraits.length] = $T.EdgeTraits.$L",
                                            ClassName.get(
                                                    processingEnv.getElementUtils().getPackageOf(hierarchy).toString(),
                                                    hierarchyElemToName(hierarchy)),
                                            getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class))
                                                    .getSimpleName())
                                    .addStatement(
                                            "getViewedSystemGraph().connect($L, this, otherPort, $S, edgeTraits)",
                                            name,
                                            name);
                        } else {
                            getMethodBuilderWithoutPort.addStatement(
                                    "getViewedSystemGraph().connect($L, this, null, $S, extraEdgeTraits)",
                                    name,
                                    name

                            );
                            getMethodBuilder.addStatement(
                                    "getViewedSystemGraph().connect($L, this, otherPort, $S, extraEdgeTraits)",
                                    name,
                                    name

                            );
                        }
                    }
                    if (execMember.getAnnotation(OutPort.class) != null) {
                        // checking for what type of return type it is
                        var portOutputType = declaredType.getTypeArguments().get(0).toString().contains("VertexViewer")
                                ? ClassName.get(OpaqueVertexViewer.class)
                                : ClassName.bestGuess(declaredType.getTypeArguments().get(0).toString());
                        getMethodBuilderWithoutPort.addParameter(portOutputType, name)
                                .addParameter(ArrayTypeName.of(ClassName.get(EdgeTrait.class)), "extraEdgeTraits")
                                .varargs();
                        getMethodBuilder.addParameter(portOutputType, name)
                                .addParameter(ArrayTypeName.of(ClassName.get(EdgeTrait.class)), "extraEdgeTraits")
                                .varargs();
                        if (execMember.getAnnotation(WithEdgeTrait.class) != null) {
                            getMethodBuilderWithoutPort
                                    .addStatement("$T[] edgeTraits = new $T[1 + extraEdgeTraits.length]",
                                            EdgeTrait.class, EdgeTrait.class)
                                    .addStatement(
                                            "$T.arraycopy(extraEdgeTraits, 0, edgeTraits, 0, extraEdgeTraits.length)",
                                            System.class)
                                    .addStatement("edgeTraits[extraEdgeTraits.length] = $T.EdgeTraits.$L",
                                            ClassName.get(
                                                    processingEnv.getElementUtils().getPackageOf(hierarchy).toString(),
                                                    hierarchyElemToName(hierarchy)),
                                            getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class))
                                                    .getSimpleName())
                                    .addStatement(
                                            "getViewedSystemGraph().connect(this, $L, $S, edgeTraits)",
                                            name,
                                            name);
                            getMethodBuilder
                                    .addStatement("$T[] edgeTraits = new $T[1 + extraEdgeTraits.length]",
                                            EdgeTrait.class, EdgeTrait.class)
                                    .addStatement(
                                            "$T.arraycopy(extraEdgeTraits, 0, edgeTraits, 0, extraEdgeTraits.length)",
                                            System.class)
                                    .addStatement("edgeTraits[extraEdgeTraits.length] = $T.EdgeTraits.$L",
                                            ClassName.get(
                                                    processingEnv.getElementUtils().getPackageOf(hierarchy).toString(),
                                                    hierarchyElemToName(hierarchy)),
                                            getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class))
                                                    .getSimpleName())
                                    .addStatement(
                                            "getViewedSystemGraph().connect(this, $L, $S, otherPort, edgeTraits)",
                                            name,
                                            name);
                        } else {
                            getMethodBuilderWithoutPort.addStatement(
                                    "getViewedSystemGraph().connect(this, $L, $S, extraEdgeTraits)",
                                    name,
                                    name);
                            getMethodBuilder.addStatement(
                                    "getViewedSystemGraph().connect(this, $L, otherPort, $S, extraEdgeTraits)",
                                    name,
                                    name);
                        }
                    }
                    getMethodBuilderWithoutPort.addStatement("return this");
                    getMethodBuilder.addStatement("return this");
                    methods.add(getMethodBuilderWithoutPort.build());
                    methods.add(getMethodBuilder.build());
                } else {
                    var getMethodBuilderWithoutPort = MethodSpec.methodBuilder(name).addModifiers(Modifier.PUBLIC)
                            .returns(viewerClass);
                    var getMethodBuilder = MethodSpec.methodBuilder(name).addParameter(String.class, "otherPort")
                            .addModifiers(Modifier.PUBLIC).returns(viewerClass);
                    if (execMember.getAnnotation(InPort.class) != null) {
                        var portInputType = execMember.getReturnType().toString().contains("VertexViewer")
                                ? ClassName.get(OpaqueVertexViewer.class)
                                : ClassName.bestGuess(execMember.getReturnType().toString());
                        getMethodBuilderWithoutPort.addParameter(portInputType, name)
                                .addParameter(ArrayTypeName.of(ClassName.get(EdgeTrait.class)), "extraEdgeTraits")
                                .varargs();
                        getMethodBuilder.addParameter(portInputType, name)
                                .addParameter(ArrayTypeName.of(ClassName.get(EdgeTrait.class)), "extraEdgeTraits")
                                .varargs();
                        if (execMember.getAnnotation(WithEdgeTrait.class) != null) {
                            getMethodBuilderWithoutPort
                                    .addStatement("$T[] edgeTraits = new $T[1 + extraEdgeTraits.length]",
                                            EdgeTrait.class, EdgeTrait.class)
                                    .addStatement(
                                            "$T.arraycopy(extraEdgeTraits, 0, edgeTraits, 0, extraEdgeTraits.length)",
                                            System.class)
                                    .addStatement("edgeTraits[extraEdgeTraits.length] = $T.EdgeTraits.$L",
                                            ClassName.get(
                                                    processingEnv.getElementUtils().getPackageOf(hierarchy).toString(),
                                                    hierarchyElemToName(hierarchy)),
                                            getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class))
                                                    .getSimpleName())
                                    .addStatement(
                                            "getViewedSystemGraph().connect($L, this, null, $S, edgeTraits)",
                                            name,
                                            name);
                            getMethodBuilder
                                    .addStatement("$T[] edgeTraits = new $T[1 + extraEdgeTraits.length]",
                                            EdgeTrait.class, EdgeTrait.class)
                                    .addStatement(
                                            "$T.arraycopy(extraEdgeTraits, 0, edgeTraits, 0, extraEdgeTraits.length)",
                                            System.class)
                                    .addStatement("edgeTraits[extraEdgeTraits.length] = $T.EdgeTraits.$L",
                                            ClassName.get(
                                                    processingEnv.getElementUtils().getPackageOf(hierarchy).toString(),
                                                    hierarchyElemToName(hierarchy)),
                                            getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class))
                                                    .getSimpleName())
                                    .addStatement(
                                            "getViewedSystemGraph().connect($L, this, otherPort, $S, edgeTraits)",
                                            name,
                                            name);
                        } else {
                            getMethodBuilderWithoutPort.addStatement(
                                    "getViewedSystemGraph().connect($L, this, null, $S, extraEdgeTraits)",
                                    name,
                                    name

                            );
                            getMethodBuilder.addStatement(
                                    "getViewedSystemGraph().connect($L, this, otherPort, $S, extraEdgeTraits)",
                                    name,
                                    name

                            );
                        }
                    }
                    if (execMember.getAnnotation(OutPort.class) != null) {
                        // checking for what type of return type it is
                        var portOutputType = execMember.getReturnType().toString().contains("VertexViewer")
                                ? ClassName.get(OpaqueVertexViewer.class)
                                : ClassName.bestGuess(execMember.getReturnType().toString());
                        getMethodBuilderWithoutPort.addParameter(portOutputType, name)
                                .addParameter(ArrayTypeName.of(ClassName.get(EdgeTrait.class)), "extraEdgeTraits")
                                .varargs();
                        getMethodBuilder.addParameter(portOutputType, name)
                                .addParameter(ArrayTypeName.of(ClassName.get(EdgeTrait.class)), "extraEdgeTraits")
                                .varargs();
                        if (execMember.getAnnotation(WithEdgeTrait.class) != null) {
                            var edgeTrait = getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class));
                            getMethodBuilderWithoutPort
                                    .addStatement("$T[] edgeTraits = new $T[1 + extraEdgeTraits.length]",
                                            EdgeTrait.class, EdgeTrait.class)
                                    .addStatement(
                                            "$T.arraycopy(extraEdgeTraits, 0, edgeTraits, 0, extraEdgeTraits.length)",
                                            System.class)
                                    .addStatement("edgeTraits[extraEdgeTraits.length] = $T.EdgeTraits.$L",
                                            ClassName.get(
                                                    processingEnv.getElementUtils().getPackageOf(hierarchy).toString(),
                                                    hierarchyElemToName(hierarchy)),
                                            getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class))
                                                    .getSimpleName())
                                    .addStatement(
                                            "getViewedSystemGraph().connect(this, $L, $S, edgeTraits)",
                                            name,
                                            name);
                            getMethodBuilder
                                    .addStatement("$T[] edgeTraits = new $T[1 + extraEdgeTraits.length]",
                                            EdgeTrait.class, EdgeTrait.class)
                                    .addStatement(
                                            "$T.arraycopy(extraEdgeTraits, 0, edgeTraits, 0, extraEdgeTraits.length)",
                                            System.class)
                                    .addStatement("edgeTraits[extraEdgeTraits.length] = $T.EdgeTraits.$L",
                                            ClassName.get(
                                                    processingEnv.getElementUtils().getPackageOf(hierarchy).toString(),
                                                    hierarchyElemToName(hierarchy)),
                                            getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class))
                                                    .getSimpleName())
                                    .addStatement(
                                            "getViewedSystemGraph().connect(this, $L, $S, otherPort, edgeTraits)",
                                            name,
                                            name);
                        } else {
                            getMethodBuilderWithoutPort.addStatement(
                                    "getViewedSystemGraph().connect(this, $L, $S, extraEdgeTraits)",
                                    name,
                                    name);
                            getMethodBuilder.addStatement(
                                    "getViewedSystemGraph().connect(this, $L, otherPort, $S, extraEdgeTraits)",
                                    name,
                                    name);
                        }
                    }
                    getMethodBuilderWithoutPort.addStatement("return this");
                    getMethodBuilder.addStatement("return this");
                    methods.add(getMethodBuilderWithoutPort.build());
                    methods.add(getMethodBuilder.build());
                }
            }
        }
        return methods;
    }

    protected Set<MethodSpec> generatePortGetters(TypeElement hierarchy, TypeElement viewerInterface) {
        var methods = new HashSet<MethodSpec>();
        var members = processingEnv.getElementUtils().getAllMembers(viewerInterface);
        for (var member : members) {
            if (member instanceof ExecutableElement execMember
                    && (member.getAnnotation(InPort.class) != null || member.getAnnotation(OutPort.class) != null)) {
                var name = execMember.getSimpleName().toString();
                var getMethodBuilder = MethodSpec.methodBuilder(name).addModifiers(Modifier.PUBLIC)
                        .addAnnotation(Override.class).returns(TypeName.get(execMember.getReturnType()));
                if (execMember.getReturnType() instanceof DeclaredType declaredType
                        && (declaredType.asElement().getSimpleName().contentEquals("Set"))) {
                    getMethodBuilder.addStatement("$T<$T> collected = new $T<>()", ArrayList.class,
                            declaredType.getTypeArguments().get(0), ArrayList.class);
                    if (execMember.getAnnotation(InPort.class) != null) {
                        var portInputType = declaredType.getTypeArguments().get(0).toString().contains("VertexViewer")
                                ? ClassName.get(OpaqueVertexViewer.class)
                                : ClassName.bestGuess(declaredType.getTypeArguments().get(0).toString() + "Viewer");
                        if (execMember.getAnnotation(WithEdgeTrait.class) != null) {
                            getMethodBuilder.addStatement(
                                    "collected.addAll(systemGraph.incomingEdgesOf(vertex).stream().filter(e -> e.connectsTargetPort($S)).filter(e -> e.hasTrait($T.EdgeTraits.$L)).map(systemGraph::getEdgeSource).flatMap(v -> $T.tryView(systemGraph, v).stream()).collect($T.toList()))",
                                    name,
                                    ClassName.get(processingEnv.getElementUtils().getPackageOf(hierarchy).toString(),
                                            hierarchyElemToName(hierarchy)),
                                    getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class)).getSimpleName(),
                                    portInputType,
                                    Collectors.class);
                        } else {
                            getMethodBuilder.addStatement(
                                    "collected.addAll(systemGraph.incomingEdgesOf(vertex).stream().filter(e -> e.connectsTargetPort($S)).map(systemGraph::getEdgeSource).flatMap(v -> $T.tryView(systemGraph, v).stream()).collect($T.toList()))",
                                    name,
                                    portInputType,
                                    Collectors.class);
                        }
                    }
                    if (execMember.getAnnotation(OutPort.class) != null) {
                        // checking for what type of return type it is
                        var portOutputType = declaredType.getTypeArguments().get(0).toString().contains("VertexViewer")
                                ? ClassName.get(OpaqueVertexViewer.class)
                                : ClassName.bestGuess(declaredType.getTypeArguments().get(0).toString() + "Viewer");
                        getMethodBuilder.addStatement(
                                "collected.addAll(systemGraph.outgoingEdgesOf(vertex).stream().filter(e -> e.connectsSourcePort($S)).map(systemGraph::getEdgeTarget).flatMap(v -> $T.tryView(systemGraph, v).stream()).collect($T.toSet()))",
                                name,
                                portOutputType,
                                Collectors.class);
                    }
                    if (declaredType.asElement().getSimpleName().contentEquals("Set")) {
                        getMethodBuilder.addStatement("return new $T(collected)", HashSet.class);
                    } else {
                        getMethodBuilder.addStatement("return collected");
                    }
                    methods.add(getMethodBuilder.build());
                } else if (execMember.getReturnType() instanceof DeclaredType declaredType
                        && (declaredType.asElement().getSimpleName().contentEquals("List"))) {
                    throw new IllegalArgumentException("Exception at " + viewerInterface.getQualifiedName().toString()
                            + " on " + execMember.getSimpleName()
                            + ": Lists are not supported. Use Sets and properties with your ordering scheme to achieve proper listing");
                } else if (execMember.getReturnType() instanceof DeclaredType declaredType
                        && (declaredType.asElement().getSimpleName().contentEquals("Optional"))) {
                    if (execMember.getAnnotation(InPort.class) != null) {
                        var portInputType = declaredType.getTypeArguments().get(0).toString().contains("VertexViewer")
                                ? ClassName.get(OpaqueVertexViewer.class)
                                : ClassName.bestGuess(declaredType.getTypeArguments().get(0).toString() + "Viewer");
                        getMethodBuilder.addStatement(
                                "final $T in = systemGraph.incomingEdgesOf(vertex).stream().filter(e -> e.connectsTargetPort($S)).map(systemGraph::getEdgeSource).flatMap(v -> $T.tryView(systemGraph, v).stream())",
                                ParameterizedTypeName.get(ClassName.get(Stream.class),
                                        TypeName.get(declaredType.getTypeArguments().get(0))),
                                name,
                                portInputType);
                        // getMethodBuilder.addStatement("if (in != null) return in");
                    }
                    if (execMember.getAnnotation(OutPort.class) != null) {
                        // checking for what type of return type it is
                        var portOutputType = declaredType.getTypeArguments().get(0).toString().contains("VertexViewer")
                                ? ClassName.get(OpaqueVertexViewer.class)
                                : ClassName.bestGuess(declaredType.getTypeArguments().get(0).toString() + "Viewer");
                        getMethodBuilder.addStatement(
                                "final $T out = systemGraph.outgoingEdgesOf(vertex).stream().filter(e -> e.connectsSourcePort($S)).map(systemGraph::getEdgeTarget).flatMap(v -> $T.tryView(systemGraph, v).stream())",
                                ParameterizedTypeName.get(ClassName.get(Stream.class),
                                        TypeName.get(declaredType.getTypeArguments().get(0))),
                                name,
                                portOutputType);
                        // getMethodBuilder.addStatement("if (out != null) return out");
                    }
                    if (execMember.getAnnotation(InPort.class) != null
                            && execMember.getAnnotation(OutPort.class) != null) {
                        getMethodBuilder.addStatement("return $T.concat(in, out).findAny()", Stream.class);
                    } else if (execMember.getAnnotation(InPort.class) == null
                            && execMember.getAnnotation(OutPort.class) != null) {
                        getMethodBuilder.addStatement("return out.findAny()");
                    } else if (execMember.getAnnotation(InPort.class) != null
                            && execMember.getAnnotation(OutPort.class) == null) {
                        getMethodBuilder.addStatement("return in.findAny()");
                    } else {
                        getMethodBuilder.addStatement("return $T.empty()", Optional.class);
                    }
                    methods.add(getMethodBuilder.build());
                } else {
                    if (execMember.getAnnotation(InPort.class) != null) {
                        var portInputType = execMember.getReturnType().toString().contains("VertexViewer")
                                ? ClassName.get(OpaqueVertexViewer.class)
                                : ClassName.bestGuess(execMember.getReturnType().toString() + "Viewer");
                        getMethodBuilder.addStatement(
                                "final $T in = systemGraph.incomingEdgesOf(vertex).stream().filter(e -> e.connectsTargetPort($S)).map(systemGraph::getEdgeSource).flatMap(v -> $T.tryView(systemGraph, v).stream()).findAny().orElse(null)",
                                execMember.getReturnType(),
                                name,
                                portInputType);
                        getMethodBuilder.addStatement("if (in != null) return in");
                    }
                    if (execMember.getAnnotation(OutPort.class) != null) {
                        // checking for what type of return type it is
                        var portOutputType = execMember.getReturnType().toString().contains("VertexViewer")
                                ? ClassName.get(OpaqueVertexViewer.class)
                                : ClassName.bestGuess(execMember.getReturnType().toString() + "Viewer");
                        getMethodBuilder.addStatement(
                                "final $T out = systemGraph.outgoingEdgesOf(vertex).stream().filter(e -> e.connectsSourcePort($S)).map(systemGraph::getEdgeTarget).flatMap(v -> $T.tryView(systemGraph, v).stream()).findAny().orElse(null)",
                                execMember.getReturnType(),
                                name,
                                portOutputType);
                        getMethodBuilder.addStatement("if (out != null) return out");
                    }
                    getMethodBuilder.addStatement("return null");
                    methods.add(getMethodBuilder.build());
                }
            }
        }
        return methods;
    }

    protected Set<MethodSpec> generatePropertyGetters(TypeElement viewerInterface) {
        var methods = new HashSet<MethodSpec>();
        var methodMembers = new HashSet<ExecutableElement>();
        var open = new ArrayDeque<TypeElement>();
        open.add(viewerInterface);
        while (!open.isEmpty()) {
            var current = open.poll();
            current.getInterfaces().stream().map(t -> processingEnv.getTypeUtils().asElement(t))
                    .filter(t -> !t.getSimpleName().contentEquals("VertexViewer"))
                    .forEach(t -> {
                        if (t instanceof TypeElement tt) {
                            open.add(tt);
                        }
                    });
            current.getEnclosedElements().stream()
                    .filter(e -> e.getKind().equals(ElementKind.METHOD))
                    .map(e -> (ExecutableElement) e)
                    .filter(e -> e.getAnnotation(Property.class) != null)
                    .filter(e -> methodMembers.stream().noneMatch(ee -> ee.getSimpleName().contentEquals(e.getSimpleName()) && ee.getAnnotation(Override.class) != null)) // there is no methods with the same name and @Override
                    .forEach(methodMembers::add);
        }
        // make resolution to keep only the most refined overridden property

        for (var execMember : methodMembers) {
            var name = execMember.getSimpleName().toString();
            var getMethodBuilder = MethodSpec.methodBuilder(name).addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class).returns(TypeName.get(execMember.getReturnType()));
            if (execMember.isDefault()) {
                getMethodBuilder.addStatement(
                        "if (!getViewedVertex().hasProperty($S)) getViewedVertex().putProperty($S, $T.super.$L())",
                        name, name, viewerInterface, name);
            }
            if (execMember.getReturnType().toString().contains("Optional")) {
                var innerType = execMember.getReturnType();
                if (execMember.getReturnType() instanceof DeclaredType declaredType) {
                    innerType = declaredType.getTypeArguments().get(0);
                }
                getMethodBuilder.addStatement("return ($T) $T.ofNullable(($T) vertex.getProperty($S))",
                        execMember.getReturnType(), Optional.class, innerType, name);
            } else {
                getMethodBuilder.addStatement("return ($T) vertex.getProperty($S)", execMember.getReturnType(), name);
            }
            methods.add(getMethodBuilder.build());
        }
        return methods;
    }

    protected Set<MethodSpec> generatePropertySetters(TypeElement viewerInterface) {
        var methods = new HashSet<MethodSpec>();
        var methodMembers = new HashSet<ExecutableElement>();
        var open = new ArrayDeque<TypeElement>();
        open.add(viewerInterface);
        while (!open.isEmpty()) {
            var current = open.poll();
            current.getInterfaces().stream().map(t -> processingEnv.getTypeUtils().asElement(t))
                    .filter(t -> !t.getSimpleName().contentEquals("VertexViewer"))
                    .forEach(t -> {
                        if (t instanceof TypeElement tt) {
                            open.add(tt);
                        }
                    });
            current.getEnclosedElements().stream()
                    .filter(e -> e.getKind().equals(ElementKind.METHOD))
                    .map(e -> (ExecutableElement) e)
                    .filter(e -> e.getAnnotation(Property.class) != null)
                    .forEach(methodMembers::add);
        }
        for (var member : methodMembers) {
            var name = member.getSimpleName().toString();
            if (member.getReturnType() instanceof DeclaredType declaredType
                    && declaredType.asElement().getSimpleName().toString().contains("Optional")) {
                var inner = declaredType.getTypeArguments().get(0);
                var getMethodBuilder = MethodSpec.methodBuilder(name).addModifiers(Modifier.PUBLIC)
                        .addParameter(TypeName.get(inner), "value");
                getMethodBuilder.addStatement("vertex.putProperty($S, value)", name);
                methods.add(getMethodBuilder.build());
            } else {
                var getMethodBuilder = MethodSpec.methodBuilder(name).addModifiers(Modifier.PUBLIC)
                        .addParameter(TypeName.get(member.getReturnType()), "value");
                getMethodBuilder.addStatement("vertex.putProperty($S, value)", name);
                methods.add(getMethodBuilder.build());
            }
        }
        return methods;
    }

    // taken from
    // https://stackoverflow.com/questions/7687829/java-6-annotation-processing-getting-a-class-from-an-annotation
    protected TypeElement getRegisteredHierarchy(RegisterTrait annotation) {
        try {
            annotation.value(); // this should throw
        } catch (MirroredTypeException mte) {
            var hierarchy = mte.getTypeMirror();
            var t = processingEnv.getTypeUtils().asElement(hierarchy);
            if (t instanceof TypeElement typeElement) {
                return typeElement;
            } else {
                return null;
            }
        }
        return null;
    }

    protected TypeElement getRegisteredEdge(WithEdgeTrait annotation) {
        try {
            annotation.value(); // this should throw
        } catch (MirroredTypeException mte) {
            var hierarchy = mte.getTypeMirror();
            var t = processingEnv.getTypeUtils().asElement(hierarchy);
            if (t instanceof TypeElement typeElement) {
                return typeElement;
            } else {
                return null;
            }
        }
        return null;
    }

    protected TypeSpec makeHierarchy(TypeElement hierarchy, Set<TypeElement> containedTraits) {
        final TypeElement viewerT = processingEnv.getElementUtils()
                .getTypeElement(VertexViewer.class.getCanonicalName());
        final TypeElement edgeT = processingEnv.getElementUtils().getTypeElement(EdgeTrait.class.getCanonicalName());
        var hierarchyElemName = hierarchyElemToName(hierarchy);
        var hierarchySpecBuilder = TypeSpec.classBuilder(hierarchyElemName).addModifiers(Modifier.PUBLIC)
                .addSuperinterface(hierarchy.asType())
                .addJavadoc(
                        "This class provides easy access to the generated viewers and elements of trait hierarchy %s.\n\n{@inheritDoc}"
                                .formatted(hierarchy.getSimpleName()));
        var containedVertexTraits = containedTraits.stream()
                .filter(t -> processingEnv.getTypeUtils().isSubtype(t.asType(), viewerT.asType()))
                .collect(Collectors.toSet());
        var containedEdgeTraits = Stream.concat(
                containedTraits.stream()
                        .filter(t -> processingEnv.getTypeUtils().isSubtype(t.asType(), edgeT.asType())),
                containedTraits.stream().flatMap(t -> t.getEnclosedElements().stream()).filter(
                        t -> t.getKind().equals(ElementKind.METHOD) && t.getAnnotation(WithEdgeTrait.class) != null)
                        .map(t -> getRegisteredEdge(t.getAnnotation(WithEdgeTrait.class))))
                .collect(Collectors.toSet());
        var vertexTraitEnumBuilder = TypeSpec.enumBuilder("VertexTraits").addSuperinterface(VertexTrait.class)
                .addModifiers(Modifier.PUBLIC).addField(String.class, "name")
                .addMethod(MethodSpec.constructorBuilder().addParameter(String.class, "name")
                        .addStatement("this.name = name").build());
        var vertexTraitEnumRefinesBuilder = CodeBlock.builder().beginControlFlow("switch (this)");
        for (var trait : containedVertexTraits) {
            vertexTraitEnumBuilder.addEnumConstant(
                    trait.getSimpleName().toString(),
                    TypeSpec.anonymousClassBuilder("$S", trait.getQualifiedName().toString().replace(".", "::"))
                            .build());
            vertexTraitEnumRefinesBuilder.beginControlFlow("case $L: switch(other.getName())",
                    trait.getSimpleName().toString());
            var open = new ArrayDeque<TypeElement>();
            open.add(trait);
            while (!open.isEmpty()) {
                var cur = open.poll();
                vertexTraitEnumRefinesBuilder.addStatement("case $S: return true",
                        cur.getQualifiedName().toString().replace(".", "::"));
                cur.getInterfaces().stream().map(t -> processingEnv.getTypeUtils().asElement(t)).forEach(elem -> {
                    if (elem instanceof TypeElement typeElement
                            && !typeElement.getSimpleName().contentEquals("VertexViewer")) {
                        open.add(typeElement);
                    }
                });
            }
            vertexTraitEnumRefinesBuilder.addStatement("default: return false");
            vertexTraitEnumRefinesBuilder.endControlFlow();
            var traitInnerClassBuilder = TypeSpec.classBuilder(trait.getSimpleName().toString())
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC);
            if (processingEnv.getElementUtils().getDocComment(trait) == null) {
                traitInnerClassBuilder.addJavadoc("Access methods for vertex trait $L.",
                        trait.getQualifiedName().toString().replace(".", "::"));
            } else {
                traitInnerClassBuilder.addJavadoc(
                        "Access methods for vertex trait $L.\n\n"
                                + processingEnv.getElementUtils().getDocComment(trait),
                        trait.getQualifiedName().toString().replace(".", "::"));
            }
            // add the viewing method
            var tryViewMethod = MethodSpec.methodBuilder("tryView")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .addParameter(SystemGraph.class, "systemGraph")
                    .addParameter(Vertex.class, "vertex")
                    .addStatement("return $T.tryView(systemGraph, vertex)",
                            ClassName.get(processingEnv.getElementUtils().getPackageOf(trait).toString(),
                                    trait.getSimpleName() + "Viewer"))
                    .returns(ParameterizedTypeName.get(ClassName.get(Optional.class),
                            ClassName.get(processingEnv.getElementUtils().getPackageOf(trait).toString(),
                                    trait.getSimpleName().toString() + "Viewer")))
                    .build();
            traitInnerClassBuilder.addMethod(tryViewMethod);
            var tryViewMethodFromViewer = MethodSpec.methodBuilder("tryView")
                    .addTypeVariable(TypeVariableName.get("T").withBounds(ClassName.get(VertexViewer.class)))
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .addParameter(TypeVariableName.get("T"), "otherViewer")
                    .addStatement("return $T.tryView(otherViewer)",
                            ClassName.get(processingEnv.getElementUtils().getPackageOf(trait).toString(),
                                    trait.getSimpleName() + "Viewer"))
                    .returns(ParameterizedTypeName.get(ClassName.get(Optional.class),
                            ClassName.get(processingEnv.getElementUtils().getPackageOf(trait).toString(),
                                    trait.getSimpleName().toString() + "Viewer")))
                    .build();
            traitInnerClassBuilder.addMethod(tryViewMethodFromViewer);
            var enforceMethod = MethodSpec.methodBuilder("enforce")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .addParameter(SystemGraph.class, "systemGraph")
                    .addParameter(Vertex.class, "vertex")
                    .addStatement("return $T.enforce(systemGraph, vertex)",
                            ClassName.get(processingEnv.getElementUtils().getPackageOf(trait).toString(),
                                    trait.getSimpleName() + "Viewer"))
                    .returns(ClassName.get(processingEnv.getElementUtils().getPackageOf(trait).toString(),
                            trait.getSimpleName().toString() + "Viewer"))
                    .build();
            traitInnerClassBuilder.addMethod(enforceMethod);
            var enforceFromViewer = MethodSpec.methodBuilder("enforce")
                    .addTypeVariable(TypeVariableName.get("T").withBounds(ClassName.get(VertexViewer.class)))
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .addParameter(TypeVariableName.get("T"), "otherViewer")
                    .addStatement(
                            "return $T.enforce(otherViewer.getViewedSystemGraph(), otherViewer.getViewedVertex())",
                            ClassName.get(processingEnv.getElementUtils().getPackageOf(trait).toString(),
                                    trait.getSimpleName() + "Viewer"))
                    .returns(ClassName.get(processingEnv.getElementUtils().getPackageOf(trait).toString(),
                            trait.getSimpleName().toString() + "Viewer"))
                    .build();
            traitInnerClassBuilder.addMethod(enforceFromViewer);
            // traitInnerClassBuilder.addField(FieldSpec.builder(ClassName.get(trait.getSimpleName().toString()),
            // "instance").addModifiers(Modifier.FINAL, Modifier.PUBLIC,
            // Modifier.STATIC).initializer("new $T()",
            // ClassName.get(trait.getSimpleName().toString())).build());
            hierarchySpecBuilder.addType(traitInnerClassBuilder.build());
        }
        vertexTraitEnumRefinesBuilder.addStatement("default: return false");
        vertexTraitEnumRefinesBuilder.endControlFlow();
        vertexTraitEnumBuilder.addMethod(
                MethodSpec.methodBuilder("refines").addParameter(Trait.class, "other").addModifiers(Modifier.PUBLIC)
                        .returns(TypeName.BOOLEAN).addCode(vertexTraitEnumRefinesBuilder.build()).build());
        vertexTraitEnumBuilder.addMethod(MethodSpec.methodBuilder("getName").addModifiers(Modifier.PUBLIC)
                .returns(String.class).addStatement("return name").build());
        // make the required properties requirement for each vertex trait
        // edges now
        var edgesTraitEnumBuilder = TypeSpec.enumBuilder("EdgeTraits").addSuperinterface(EdgeTrait.class)
                .addModifiers(Modifier.PUBLIC).addField(String.class, "name")
                .addMethod(MethodSpec.constructorBuilder().addParameter(String.class, "name")
                        .addStatement("this.name = name").build());
        var edgesTraitEnumRefinesBuilder = CodeBlock.builder().beginControlFlow("switch (this)");
        for (var trait : containedEdgeTraits) {
            edgesTraitEnumBuilder.addEnumConstant(trait.getSimpleName().toString(), TypeSpec
                    .anonymousClassBuilder("$S", trait.getQualifiedName().toString().replace(".", "::")).build());
            // var traitInnerClassBuilder =
            // TypeSpec.classBuilder(trait.getSimpleName().toString()).addModifiers(Modifier.PUBLIC);
            // traitInnerClassBuilder.addField(FieldSpec.builder(String.class,
            // "name").initializer("$S", trait.getQualifiedName().toString().replace(".",
            // "::")).addModifiers(Modifier.FINAL, Modifier.STATIC,
            // Modifier.PUBLIC).build());
            // traitInnerClassBuilder.addMethod(MethodSpec.methodBuilder("getName").returns(String.class).addModifiers(Modifier.PUBLIC).addStatement("return
            // name").build());
            // var refinesSpec =
            // MethodSpec.methodBuilder("refines").addParameter(Trait.class,
            // "other").returns(TypeName.BOOLEAN).addModifiers(Modifier.PUBLIC);
            edgesTraitEnumRefinesBuilder.beginControlFlow("case $L: switch (other.getName())",
                    trait.getSimpleName().toString());
            var open = new ArrayDeque<TypeElement>();
            open.add(trait);
            while (!open.isEmpty()) {
                var cur = open.poll();
                edgesTraitEnumRefinesBuilder.addStatement("case $S: return true",
                        cur.getQualifiedName().toString().replace(".", "::"));
                cur.getInterfaces().stream().map(t -> processingEnv.getTypeUtils().asElement(t)).forEach(elem -> {
                    if (elem instanceof TypeElement typeElement
                            && !typeElement.getSimpleName().contentEquals("EdgeTrait")) {
                        open.add(typeElement);
                    }
                });
            }
            edgesTraitEnumRefinesBuilder.addStatement("default: return false");
            edgesTraitEnumRefinesBuilder.endControlFlow();
            // hierarchySpecBuilder.addType(traitInnerClassBuilder.build());
        }
        edgesTraitEnumRefinesBuilder.addStatement("default: return false");
        edgesTraitEnumRefinesBuilder.endControlFlow();
        edgesTraitEnumBuilder.addMethod(
                MethodSpec.methodBuilder("refines").addParameter(Trait.class, "other").addModifiers(Modifier.PUBLIC)
                        .returns(TypeName.BOOLEAN).addCode(edgesTraitEnumRefinesBuilder.build()).build());
        edgesTraitEnumBuilder.addMethod(MethodSpec.methodBuilder("getName").addModifiers(Modifier.PUBLIC)
                .returns(String.class).addStatement("return name").build());
        // finishing
        hierarchySpecBuilder.addField(FieldSpec
                .builder(ParameterizedTypeName.get(Set.class, Trait.class), "containedTraits")
                .addModifiers(Modifier.STATIC, Modifier.PUBLIC)
                .initializer("Set.of($L)",
                        containedVertexTraits.stream().map(
                                t -> "VertexTraits." + t.getSimpleName().toString()).collect(Collectors.joining(",\n"))
                                +
                                (!containedEdgeTraits.isEmpty() ? ",\n" + containedEdgeTraits.stream()
                                        .map(t -> "EdgeTraits." + t.getSimpleName().toString())
                                        .collect(Collectors.joining(",\n")) : ""))
                .build());
        var fromNameCodeBlockBuilder = CodeBlock.builder()
                .beginControlFlow("switch (traitName)");
        for (var trait : containedVertexTraits) {
            fromNameCodeBlockBuilder.addStatement("case $S: return VertexTraits.$L",
                    trait.getQualifiedName().toString().replace(".", "::"), trait.getSimpleName().toString());
        }
        for (var trait : containedEdgeTraits) {
            fromNameCodeBlockBuilder.addStatement("case $S: return EdgeTraits.$L",
                    trait.getQualifiedName().toString().replace(".", "::"), trait.getSimpleName().toString());
        }
        fromNameCodeBlockBuilder.addStatement("default: return new $T(traitName)", OpaqueTrait.class);
        fromNameCodeBlockBuilder.endControlFlow();
        hierarchySpecBuilder.addMethod(MethodSpec.methodBuilder("fromName").addParameter(String.class, "traitName")
                .addModifiers(Modifier.PUBLIC).returns(Trait.class)
                .addCode(fromNameCodeBlockBuilder.build()).build());
        hierarchySpecBuilder.addMethod(MethodSpec.methodBuilder("traits").addModifiers(Modifier.PUBLIC)
                .returns(ParameterizedTypeName.get(Set.class, Trait.class)).addStatement("return containedTraits")
                .build());
        if (!containedVertexTraits.isEmpty())
            hierarchySpecBuilder.addType(vertexTraitEnumBuilder.build());
        if (!containedEdgeTraits.isEmpty())
            hierarchySpecBuilder.addType(edgesTraitEnumBuilder.build());
        return hierarchySpecBuilder.build();
    }

    ;

    protected MethodSpec makeEnforceMethod(TypeElement traitInterface, ClassName generatedHierarchy) {
        var viewerClassSimpleName = traitInterface.getSimpleName().toString() + "Viewer";
        var enforceMethodBuilder = MethodSpec.methodBuilder("enforce")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(SystemGraph.class, "systemGraph")
                .addParameter(Vertex.class, "vertex")
                .returns(ClassName.get(processingEnv.getElementUtils().getPackageOf(traitInterface).toString(),
                        traitInterface.getSimpleName().toString() + "Viewer"))
                .addStatement("vertex.addTrait($T.VertexTraits.$L)", generatedHierarchy, traitInterface.getSimpleName().toString());
        enforceMethodBuilder.addStatement("final $L viewer = new $L(systemGraph, vertex)", viewerClassSimpleName,
                viewerClassSimpleName);
        // properties
        for (var member : traitInterface.getEnclosedElements()) {
            if (member instanceof ExecutableElement execMember && (member.getAnnotation(Property.class) != null)) {
                if (execMember.getReturnType().toString().contains("Map")) {
                    enforceMethodBuilder.addStatement("if (!vertex.hasProperty($S)) vertex.putProperty($S, new $T())",
                            execMember.getSimpleName().toString(), execMember.getSimpleName().toString(),
                            HashMap.class);
                } else if (execMember.getReturnType().toString().contains("Set")) {
                    enforceMethodBuilder.addStatement("if (!vertex.hasProperty($S)) vertex.putProperty($S, new $T())",
                            execMember.getSimpleName().toString(), execMember.getSimpleName().toString(),
                            HashSet.class);
                } else if (execMember.getReturnType().toString().contains("List")) {
                    enforceMethodBuilder.addStatement("if (!vertex.hasProperty($S)) vertex.putProperty($S, new $T())",
                            execMember.getSimpleName().toString(), execMember.getSimpleName().toString(),
                            ArrayList.class);
                } else if (execMember.isDefault()) {
                    enforceMethodBuilder.addStatement(
                            "if (!vertex.hasProperty($S)) vertex.putProperty($S, viewer.$L())",
                            execMember.getSimpleName().toString(), execMember.getSimpleName().toString(),
                            execMember.getSimpleName().toString());
                }
                // else {
                // enforceMethodBuilder.addStatement("vertex.putProperty($S, new $T())",
                // execMember.getSimpleName().toString(), execMember.getReturnType());
                // }
            }
        }
        // ports
        for (var member : traitInterface.getEnclosedElements()) {
            if (member instanceof ExecutableElement execMember
                    && (member.getAnnotation(InPort.class) != null || member.getAnnotation(OutPort.class) != null)) {
                enforceMethodBuilder.addStatement("vertex.addPort($S)", execMember.getSimpleName().toString());
            }
        }
        for (var sup : traitInterface.getInterfaces()) {
            if (!sup.toString().contains("VertexViewer")) {
                var elem = processingEnv.getTypeUtils().asElement(sup);
                var viewerClass = ClassName.get(processingEnv.getElementUtils().getPackageOf(elem).toString(),
                        elem.getSimpleName().toString() + "Viewer");
                enforceMethodBuilder.addStatement("$T.enforce(systemGraph, vertex)", viewerClass);
            }
        }
        enforceMethodBuilder.addStatement("return viewer", viewerClassSimpleName);

        return enforceMethodBuilder.build();
    }

    protected MethodSpec makeTryViewMethod(TypeElement traitInterface, ClassName generatedHierarchy) {
        var viewerClassSimpleName = traitInterface.getSimpleName().toString() + "Viewer";
        var tryViewMethodBuilder = MethodSpec.methodBuilder("tryView")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(SystemGraph.class, "systemGraph")
                .addParameter(Vertex.class, "vertex")
                .returns(ParameterizedTypeName.get(ClassName.get(Optional.class),
                        ClassName.get(processingEnv.getElementUtils().getPackageOf(traitInterface).toString(),
                                traitInterface.getSimpleName().toString() + "Viewer")));
        var bodyBlockBuilder = CodeBlock.builder()
                .beginControlFlow("if (vertex.hasTrait($T.VertexTraits.$L))", generatedHierarchy,
                        traitInterface.getSimpleName().toString())
                .addStatement("final $L viewer = new $L(systemGraph, vertex);", viewerClassSimpleName, viewerClassSimpleName);
        // properties
        for (var member : traitInterface.getEnclosedElements()) {
            if (member instanceof ExecutableElement execMember && (member.getAnnotation(Property.class) != null)) {
                if (execMember.getReturnType().toString().contains("Map")) {
                    bodyBlockBuilder.addStatement("if (!vertex.hasProperty($S)) vertex.putProperty($S, new $T())",
                            execMember.getSimpleName().toString(), execMember.getSimpleName().toString(),
                            HashMap.class);
                } else if (execMember.getReturnType().toString().contains("Set")) {
                    bodyBlockBuilder.addStatement("if (!vertex.hasProperty($S)) vertex.putProperty($S, new $T())",
                            execMember.getSimpleName().toString(), execMember.getSimpleName().toString(),
                            HashSet.class);
                } else if (execMember.getReturnType().toString().contains("List")) {
                    bodyBlockBuilder.addStatement("if (!vertex.hasProperty($S)) vertex.putProperty($S, new $T())",
                            execMember.getSimpleName().toString(), execMember.getSimpleName().toString(),
                            ArrayList.class);
                } else if (execMember.isDefault()) {
                    bodyBlockBuilder.addStatement(
                            "if (!vertex.hasProperty($S)) vertex.putProperty($S, viewer.$L())",
                            execMember.getSimpleName().toString(), execMember.getSimpleName().toString(),
                            execMember.getSimpleName().toString());
                }
                // else {
                // enforceMethodBuilder.addStatement("vertex.putProperty($S, new $T())",
                // execMember.getSimpleName().toString(), execMember.getReturnType());
                // }
            }
        }
        // ports
        for (var member : traitInterface.getEnclosedElements()) {
            if (member instanceof ExecutableElement execMember
                    && (member.getAnnotation(InPort.class) != null || member.getAnnotation(OutPort.class) != null)) {
                bodyBlockBuilder.addStatement("vertex.addPort($S)", execMember.getSimpleName().toString());
            }
        }
        for (var sup : traitInterface.getInterfaces()) {
            if (!sup.toString().contains("VertexViewer")) {
                var elem = processingEnv.getTypeUtils().asElement(sup);
                var viewerClass = ClassName.get(processingEnv.getElementUtils().getPackageOf(elem).toString(),
                        elem.getSimpleName().toString() + "Viewer");
                bodyBlockBuilder.addStatement("$T.enforce(systemGraph, vertex)", viewerClass);
            }
        }
        bodyBlockBuilder.addStatement("return $T.of(viewer)", Optional.class)
                .nextControlFlow("else")
                .addStatement("return $T.empty()", Optional.class)
                .endControlFlow();
        tryViewMethodBuilder.addCode(bodyBlockBuilder.build());

        return tryViewMethodBuilder.build();
    }

    protected String hierarchyElemToName(TypeElement hierarchy) {
        return hierarchy.getSimpleName().toString().startsWith("I") ? hierarchy.getSimpleName().toString().substring(1)
                : hierarchy.getSimpleName().toString() + "Gen";
    }

}
