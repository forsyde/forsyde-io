package forsyde.io.java.generator;

import com.squareup.javapoet.*;
import forsyde.io.core.*;
import forsyde.io.core.VertexTrait;
import forsyde.io.core.annotations.*;
import forsyde.io.core.*;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SupportedAnnotationTypes({"forsyde.io.core.annotations.RegisterTrait"})
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class TraitViewerGenerator extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        final TypeElement viewerT = processingEnv.getElementUtils().getTypeElement(VertexViewer.class.getCanonicalName());
        final TypeElement edgeT = processingEnv.getElementUtils().getTypeElement(EdgeTrait.class.getCanonicalName());
        final Map<TypeElement, TypeElement> traitsToHierarchy = new HashMap<>();
        final Map<TypeElement, TypeSpec> traitToSpec = new HashMap<>();
//        final Map<TypeElement, TypeSpec> hierarchyToSpec = new HashMap<>();
        try {
            var toGenerate = roundEnv.getElementsAnnotatedWith(RegisterTrait.class);
            var typesToGenerate = ElementFilter.typesIn(toGenerate);
            for (var typeElement : typesToGenerate) {
                var hierarchy = getRegisteredHierarchy(typeElement.getAnnotation(RegisterTrait.class));
//                hierarchyToSpec.putIfAbsent(hierarchy, makeHierarchy(hierarchy));
                if (processingEnv.getTypeUtils().isSubtype(typeElement.asType(), viewerT.asType())) {
                    var spec = makeViewer(hierarchy, typeElement);
                    traitToSpec.put(typeElement, spec);
                    var javaFile = JavaFile.builder(processingEnv.getElementUtils().getPackageOf(typeElement).toString(), spec).build();
                    javaFile.writeTo(processingEnv.getFiler());
                }
                traitsToHierarchy.put(typeElement, hierarchy);
            }
            var hierarchies = new HashSet<>(traitsToHierarchy.values());
            for (var typeHierarchy: hierarchies) {
                var containedTraits = traitsToHierarchy.entrySet().stream().filter(e -> e.getValue().equals(typeHierarchy)).map(Map.Entry::getKey).collect(Collectors.toSet());
//                hierarchyToSpec.putIfAbsent(hierarchy, makeHierarchy(hierarchy));
                var genTH = makeHierarchy(typeHierarchy, containedTraits);
                var javaFile = JavaFile.builder(processingEnv.getElementUtils().getPackageOf(typeHierarchy).toString(), genTH).build();
                javaFile.writeTo(processingEnv.getFiler());
            }
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return false;
    }

    protected TypeSpec makeViewer(TypeElement hierarchy, TypeElement traitInterface) {
        var viewerClassSimpleName = traitInterface.getSimpleName().toString() + "Viewer";
        final TypeSpec.Builder viewerClassBuilder = TypeSpec.classBuilder(ClassName.get(processingEnv.getElementUtils().getPackageOf(traitInterface).toString(), viewerClassSimpleName))
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addSuperinterface(ClassName.get(traitInterface))
                .addSuperinterface(VertexViewer.class)
                //.superclass(ClassName.get("forsyde.io.java.core", "VertexViewer"))
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
                .addStatement("return Optional.of(new $L(systemGraph, vertex))", viewerClassSimpleName)
                .returns(ParameterizedTypeName.get(ClassName.get(Optional.class), ClassName.get(processingEnv.getElementUtils().getPackageOf(traitInterface).toString(), traitInterface.getSimpleName().toString() + "Viewer")))
                .build();
        viewerClassBuilder.addMethod(tryViewMethod);
        var tryViewMethodFromViewer = MethodSpec.methodBuilder("tryView")
                .addTypeVariable(TypeVariableName.get("T").withBounds(ClassName.get(VertexViewer.class)))
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(TypeVariableName.get("T"), "otherViewer")
                .addStatement("return Optional.of(new $L(otherViewer.getViewedSystemGraph(), otherViewer.getViewedVertex()))", viewerClassSimpleName)
                .returns(ParameterizedTypeName.get(ClassName.get(Optional.class), ClassName.get(processingEnv.getElementUtils().getPackageOf(traitInterface).toString(), traitInterface.getSimpleName().toString() + "Viewer")))
                .build();
        viewerClassBuilder.addMethod(tryViewMethodFromViewer);

        var enforceMethod = makeEnforceMethod(traitInterface);
        viewerClassBuilder.addMethod(enforceMethod);
        var enforceFromViewer = MethodSpec.methodBuilder("enforce")
                .addTypeVariable(TypeVariableName.get("T").withBounds(ClassName.get(VertexViewer.class)))
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(TypeVariableName.get("T"), "otherViewer")
                .addStatement("return enforce(otherViewer.getViewedSystemGraph() , otherViewer.getViewedVertex())")
                .returns(TypeName.get(traitInterface.asType()))
                .build();
        viewerClassBuilder.addMethod(enforceFromViewer);

        for (var portGetter : generatePortGetters(hierarchy, traitInterface)) {
            viewerClassBuilder.addMethod(portGetter);
        }
        for (var portSetter : generatePortSetters(hierarchy, traitInterface)) {
            viewerClassBuilder.addMethod(portSetter);
        }
        for (var propGetter: generatePropertyGetters(traitInterface)) {
            viewerClassBuilder.addMethod(propGetter);
        }
        return viewerClassBuilder.build();
    }

    protected Set<MethodSpec> generatePortSetters(TypeElement hierarchy, TypeElement viewerInterface) {
        var methods = new HashSet<MethodSpec>();
        var members = processingEnv.getElementUtils().getAllMembers(viewerInterface);
        for (var member : members) {
            if (member instanceof ExecutableElement execMember && (member.getAnnotation(InPort.class) != null || member.getAnnotation(OutPort.class) != null)) {
                var name = execMember.getSimpleName().toString();
                if (execMember.getReturnType() instanceof DeclaredType declaredType && (declaredType.asElement().getSimpleName().contentEquals("Set"))) {
                    var capitalizedName = name.substring(0, 1).toUpperCase() + name.substring(1);
                    var getMethodBuilderWithoutPort = MethodSpec.methodBuilder("add" + capitalizedName).addModifiers(Modifier.PUBLIC).returns(TypeName.get(viewerInterface.asType()));
                    var getMethodBuilder = MethodSpec.methodBuilder("add" + capitalizedName).addParameter(String.class, "otherPort").addModifiers(Modifier.PUBLIC).returns(TypeName.get(viewerInterface.asType()));
                    if (execMember.getAnnotation(InPort.class) != null) {
                        var portInputType = declaredType.getTypeArguments().get(0).toString().contains("VertexViewer") ? ClassName.get(OpaqueVertexViewer.class) : ClassName.bestGuess(declaredType.getTypeArguments().get(0).toString() + "Viewer");
                        getMethodBuilderWithoutPort.addParameter(portInputType, name);
                        getMethodBuilder.addParameter(portInputType, name);
                        if (execMember.getAnnotation(WithEdgeTrait.class) != null) {
                            getMethodBuilderWithoutPort.addStatement(
                                    "getViewedSystemGraph().connect($L, this, null, $S, $T.EdgeTraits.$L)",
                                    name,
                                    name,
                                    ClassName.get(processingEnv.getElementUtils().getPackageOf(hierarchy).toString(), hierarchyElemToName(hierarchy)),
                                    getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class)).getSimpleName()
                            );
                            getMethodBuilder.addStatement(
                                    "getViewedSystemGraph().connect($L, this, otherPort, $S, $T.EdgeTraits.$L)",
                                    name,
                                    name,
                                    ClassName.get(processingEnv.getElementUtils().getPackageOf(hierarchy).toString(), hierarchyElemToName(hierarchy)),
                                    getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class)).getSimpleName()
                            );
                        } else {
                            getMethodBuilderWithoutPort.addStatement(
                                    "getViewedSystemGraph().connect($L, this, null, $S)",
                                    name,
                                    name

                            );
                            getMethodBuilder.addStatement(
                                    "getViewedSystemGraph().connect($L, this, otherPort, $S)",
                                    name,
                                    name

                            );
                        }
                    }
                    if (execMember.getAnnotation(OutPort.class) != null) {
                        // checking for what type of return type it is
                        var portOutputType = declaredType.getTypeArguments().get(0).toString().contains("VertexViewer") ? ClassName.get(OpaqueVertexViewer.class) : ClassName.bestGuess(declaredType.getTypeArguments().get(0).toString() + "Viewer");
                        getMethodBuilderWithoutPort.addParameter(portOutputType, name);
                        getMethodBuilder.addParameter(portOutputType, name);
                        if (execMember.getAnnotation(WithEdgeTrait.class) != null) {
                            var edgeTrait = getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class));
                            getMethodBuilderWithoutPort.addStatement(
                                    "getViewedSystemGraph().connect(this, $L, $S, $T.EdgeTraits.$L)",
                                    name,
                                    name,
                                    ClassName.get(processingEnv.getElementUtils().getPackageOf(hierarchy).toString(), hierarchyElemToName(hierarchy)),
                                    getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class)).getSimpleName()
                            );
                            getMethodBuilder.addStatement(
                                    "getViewedSystemGraph().connect(this, $L, $S, otherPort, $T.EdgeTraits.$L)",
                                    name,
                                    name,
                                    ClassName.get(processingEnv.getElementUtils().getPackageOf(hierarchy).toString(), hierarchyElemToName(hierarchy)),
                                    getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class)).getSimpleName()
                            );
                        } else {
                            getMethodBuilderWithoutPort.addStatement(
                                    "getViewedSystemGraph().connect(this, $L, $S)",
                                    name,
                                    name
                            );
                            getMethodBuilder.addStatement(
                                    "getViewedSystemGraph().connect(this, $L, otherPort, $S)",
                                    name,
                                    name
                            );
                        }
                    }
//                    if (execMember.getAnnotation(InPort.class) != null) {
//                        var portInputType = declaredType.getTypeArguments().get(0).toString().contains("VertexViewer") ? ClassName.get(OpaqueVertexViewer.class) : ClassName.bestGuess(declaredType.getTypeArguments().get(0).toString() + "Viewer");
//                        if (execMember.getAnnotation(WithEdgeTrait.class) != null) {
//                            getMethodBuilder.addStatement(
//                                    "collected.addAll(systemGraph.incomingEdgesOf(vertex).stream().filter(e -> e.connectsTargetPort($S)).filter(e -> e.hasTrait($S)).map(systemGraph::getEdgeSource).flatMap(v -> $T.tryView(systemGraph, v).stream()).collect($T.toList()))",
//                                    name,
//                                    viewerInterface.getAnnotation(WithEdgeTrait.class).value().getCanonicalName().replace(".", "::"),
//                                    portInputType,
//                                    Collectors.class
//                            );
//                        } else {
//                            getMethodBuilder.addStatement(
//                                    "collected.addAll(systemGraph.incomingEdgesOf(vertex).stream().filter(e -> e.connectsTargetPort($S)).map(systemGraph::getEdgeSource).flatMap(v -> $T.tryView(systemGraph, v).stream()).collect($T.toList()))",
//                                    name,
//                                    portInputType,
//                                    Collectors.class
//                            );
//                        }
//                    }
//                    if (execMember.getAnnotation(OutPort.class) != null) {
//                        // checking for what type of return type it is
//                        var portOutputType = declaredType.getTypeArguments().get(0).toString().contains("VertexViewer") ? ClassName.get(OpaqueVertexViewer.class) : ClassName.bestGuess(declaredType.getTypeArguments().get(0).toString() + "Viewer");
//                        getMethodBuilder.addStatement(
//                                "collected.addAll(systemGraph.outgoingEdgesOf(vertex).stream().filter(e -> e.connectsSourcePort($S)).map(systemGraph::getEdgeTarget).flatMap(v -> $T.tryView(systemGraph, v).stream()).collect($T.toSet()))",
//                                name,
//                                portOutputType,
//                                Collectors.class
//                        );
//                    }
//                    if (declaredType.asElement().getSimpleName().contentEquals("Set")) {
//                        getMethodBuilder.addStatement("return new $T(collected)", HashSet.class);
//                    } else {
//                        getMethodBuilder.addStatement("return collected");
//                    }
                    getMethodBuilderWithoutPort.addStatement("return this");
                    getMethodBuilder.addStatement("return this");
                    methods.add(getMethodBuilderWithoutPort.build());
                    methods.add(getMethodBuilder.build());
                } else if (execMember.getReturnType() instanceof DeclaredType declaredType && (declaredType.asElement().getSimpleName().contentEquals("List"))) {
                } else if (execMember.getReturnType() instanceof DeclaredType declaredType && (declaredType.asElement().getSimpleName().contentEquals("Optional"))) {
                    var getMethodBuilderWithoutPort = MethodSpec.methodBuilder(name).addModifiers(Modifier.PUBLIC).returns(TypeName.get(viewerInterface.asType()));
                    var getMethodBuilder = MethodSpec.methodBuilder(name).addParameter(String.class, "otherPort").addModifiers(Modifier.PUBLIC).returns(TypeName.get(viewerInterface.asType()));
                    if (execMember.getAnnotation(InPort.class) != null) {
                        var portInputType = declaredType.getTypeArguments().get(0).toString().contains("VertexViewer") ? ClassName.get(OpaqueVertexViewer.class) : ClassName.bestGuess(declaredType.getTypeArguments().get(0).toString() + "Viewer");
                        getMethodBuilderWithoutPort.addParameter(portInputType, name);
                        getMethodBuilder.addParameter(portInputType, name);
                        if (execMember.getAnnotation(WithEdgeTrait.class) != null) {
                            var edgeTrait = getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class));
                            getMethodBuilderWithoutPort.addStatement(
                                    "getViewedSystemGraph().connect($L, this, null, $S, $T.instance)",
                                    name,
                                    name,
                                    edgeTrait
                            );
                            getMethodBuilder.addStatement(
                                    "getViewedSystemGraph().connect($L, this, otherPort, $S, $T.instance)",
                                    name,
                                    name,
                                    edgeTrait
                            );
                        } else {
                            getMethodBuilderWithoutPort.addStatement(
                                    "getViewedSystemGraph().connect($L, this, null, $S)",
                                    name,
                                    name

                            );
                            getMethodBuilder.addStatement(
                                    "getViewedSystemGraph().connect($L, this, otherPort, $S)",
                                    name,
                                    name

                            );
                        }
                    }
                    if (execMember.getAnnotation(OutPort.class) != null) {
                        // checking for what type of return type it is
                        var portOutputType = declaredType.getTypeArguments().get(0).toString().contains("VertexViewer") ? ClassName.get(OpaqueVertexViewer.class) : ClassName.bestGuess(declaredType.getTypeArguments().get(0).toString() + "Viewer");
                        getMethodBuilderWithoutPort.addParameter(portOutputType, name);
                        getMethodBuilder.addParameter(portOutputType, name);
                        if (execMember.getAnnotation(WithEdgeTrait.class) != null) {
                            var edgeTrait = getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class));
                            getMethodBuilderWithoutPort.addStatement(
                                    "getViewedSystemGraph().connect(this, $L, $S, $T)",
                                    name,
                                    name,
                                    edgeTrait
                            );
                            getMethodBuilder.addStatement(
                                    "getViewedSystemGraph().connect(this, $L, $S, otherPort, $T)",
                                    name,
                                    name,
                                    edgeTrait
                            );
                        } else {
                            getMethodBuilderWithoutPort.addStatement(
                                    "getViewedSystemGraph().connect(this, $L, $S)",
                                    name,
                                    name
                            );
                            getMethodBuilder.addStatement(
                                    "getViewedSystemGraph().connect(this, $L, otherPort, $S)",
                                    name,
                                    name
                            );
                        }
                    }
                    getMethodBuilderWithoutPort.addStatement("return this");
                    getMethodBuilder.addStatement("return this");
                    methods.add(getMethodBuilderWithoutPort.build());
                    methods.add(getMethodBuilder.build());
                } else {
                    var getMethodBuilderWithoutPort = MethodSpec.methodBuilder(name).addModifiers(Modifier.PUBLIC).returns(TypeName.get(viewerInterface.asType()));
                    var getMethodBuilder = MethodSpec.methodBuilder(name).addParameter(String.class, "otherPort").addModifiers(Modifier.PUBLIC).returns(TypeName.get(viewerInterface.asType()));
                    if (execMember.getAnnotation(InPort.class) != null) {
                        var portInputType = execMember.getReturnType().toString().contains("VertexViewer") ? ClassName.get(OpaqueVertexViewer.class) : ClassName.bestGuess(execMember.getReturnType().toString() + "Viewer");
                        getMethodBuilderWithoutPort.addParameter(portInputType, name);
                        getMethodBuilder.addParameter(portInputType, name);
                        if (execMember.getAnnotation(WithEdgeTrait.class) != null) {
                            var edgeTrait = getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class));
                            getMethodBuilderWithoutPort.addStatement(
                                    "getViewedSystemGraph().connect($L, this, null, $S, $T.instance)",
                                    name,
                                    name,
                                    edgeTrait
                            );
                            getMethodBuilder.addStatement(
                                    "getViewedSystemGraph().connect($L, this, otherPort, $S, $T.instance)",
                                    name,
                                    name,
                                    edgeTrait
                            );
                        } else {
                            getMethodBuilderWithoutPort.addStatement(
                                    "getViewedSystemGraph().connect($L, this, null, $S)",
                                    name,
                                    name

                            );
                            getMethodBuilder.addStatement(
                                    "getViewedSystemGraph().connect($L, this, otherPort, $S)",
                                    name,
                                    name

                            );
                        }
                    }
                    if (execMember.getAnnotation(OutPort.class) != null) {
                        // checking for what type of return type it is
                        var portOutputType = execMember.getReturnType().toString().contains("VertexViewer") ? ClassName.get(OpaqueVertexViewer.class) : ClassName.bestGuess(execMember.getReturnType().toString() + "Viewer");
                        getMethodBuilderWithoutPort.addParameter(portOutputType, name);
                        getMethodBuilder.addParameter(portOutputType, name);
                        if (execMember.getAnnotation(WithEdgeTrait.class) != null) {
                            var edgeTrait = getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class));
                            getMethodBuilderWithoutPort.addStatement(
                                    "getViewedSystemGraph().connect(this, $L, $S, $T)",
                                    name,
                                    name,
                                    edgeTrait
                            );
                            getMethodBuilder.addStatement(
                                    "getViewedSystemGraph().connect(this, $L, $S, otherPort, $T)",
                                    name,
                                    name,
                                    edgeTrait
                            );
                        } else {
                            getMethodBuilderWithoutPort.addStatement(
                                    "getViewedSystemGraph().connect(this, $L, $S)",
                                    name,
                                    name
                            );
                            getMethodBuilder.addStatement(
                                    "getViewedSystemGraph().connect(this, $L, otherPort, $S)",
                                    name,
                                    name
                            );
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
            if (member instanceof ExecutableElement execMember && (member.getAnnotation(InPort.class) != null || member.getAnnotation(OutPort.class) != null)) {
                var name = execMember.getSimpleName().toString();
                var getMethodBuilder = MethodSpec.methodBuilder(name).addModifiers(Modifier.PUBLIC).addAnnotation(Override.class).returns(TypeName.get(execMember.getReturnType()));
                if (execMember.getReturnType() instanceof DeclaredType declaredType && (declaredType.asElement().getSimpleName().contentEquals("Set") || declaredType.asElement().getSimpleName().contentEquals("List"))) {
                    getMethodBuilder.addStatement("$T<$T> collected = new $T<>()", ArrayList.class, declaredType.getTypeArguments().get(0), ArrayList.class);
                    if (execMember.getAnnotation(InPort.class) != null) {
                        var portInputType = declaredType.getTypeArguments().get(0).toString().contains("VertexViewer") ? ClassName.get(OpaqueVertexViewer.class) : ClassName.bestGuess(declaredType.getTypeArguments().get(0).toString() + "Viewer");
                        if (execMember.getAnnotation(WithEdgeTrait.class) != null) {
                            getMethodBuilder.addStatement(
                                    "collected.addAll(systemGraph.incomingEdgesOf(vertex).stream().filter(e -> e.connectsTargetPort($S)).filter(e -> e.hasTrait($T.EdgeTraits.$L)).map(systemGraph::getEdgeSource).flatMap(v -> $T.tryView(systemGraph, v).stream()).collect($T.toList()))",
                                    name,
                                    ClassName.get(processingEnv.getElementUtils().getPackageOf(hierarchy).toString(), hierarchyElemToName(hierarchy)),
                                    getRegisteredEdge(execMember.getAnnotation(WithEdgeTrait.class)).getSimpleName(),
                                    portInputType,
                                    Collectors.class
                            );
                        } else {
                            getMethodBuilder.addStatement(
                                    "collected.addAll(systemGraph.incomingEdgesOf(vertex).stream().filter(e -> e.connectsTargetPort($S)).map(systemGraph::getEdgeSource).flatMap(v -> $T.tryView(systemGraph, v).stream()).collect($T.toList()))",
                                    name,
                                    portInputType,
                                    Collectors.class
                            );
                        }
                    }
                    if (execMember.getAnnotation(OutPort.class) != null) {
                        // checking for what type of return type it is
                        var portOutputType = declaredType.getTypeArguments().get(0).toString().contains("VertexViewer") ? ClassName.get(OpaqueVertexViewer.class) : ClassName.bestGuess(declaredType.getTypeArguments().get(0).toString() + "Viewer");
                        getMethodBuilder.addStatement(
                                "collected.addAll(systemGraph.outgoingEdgesOf(vertex).stream().filter(e -> e.connectsSourcePort($S)).map(systemGraph::getEdgeTarget).flatMap(v -> $T.tryView(systemGraph, v).stream()).collect($T.toSet()))",
                                name,
                                portOutputType,
                                Collectors.class
                        );
                    }
                    if (declaredType.asElement().getSimpleName().contentEquals("Set")) {
                        getMethodBuilder.addStatement("return new $T(collected)", HashSet.class);
                    } else {
                        getMethodBuilder.addStatement("return collected");
                    }
                    methods.add(getMethodBuilder.build());
                } else if (execMember.getReturnType() instanceof DeclaredType declaredType && (declaredType.asElement().getSimpleName().contentEquals("Optional"))) {
                    if (execMember.getAnnotation(InPort.class) != null) {
                        var portInputType = declaredType.getTypeArguments().get(0).toString().contains("VertexViewer") ? ClassName.get(OpaqueVertexViewer.class) : ClassName.bestGuess(declaredType.getTypeArguments().get(0).toString() + "Viewer");
                        getMethodBuilder.addStatement(
                                "final $T in = systemGraph.incomingEdgesOf(vertex).stream().filter(e -> e.connectsTargetPort($S)).map(systemGraph::getEdgeSource).flatMap(v -> $T.tryView(systemGraph, v).stream())",
                                ParameterizedTypeName.get(ClassName.get(Stream.class), TypeName.get(declaredType.getTypeArguments().get(0))),
                                name,
                                portInputType
                        );
//                        getMethodBuilder.addStatement("if (in != null) return in");
                    }
                    if (execMember.getAnnotation(OutPort.class) != null) {
                        // checking for what type of return type it is
                        var portOutputType = declaredType.getTypeArguments().get(0).toString().contains("VertexViewer") ? ClassName.get(OpaqueVertexViewer.class) : ClassName.bestGuess(declaredType.getTypeArguments().get(0).toString() + "Viewer");
                        getMethodBuilder.addStatement(
                                "final $T out = systemGraph.outgoingEdgesOf(vertex).stream().filter(e -> e.connectsSourcePort($S)).map(systemGraph::getEdgeTarget).flatMap(v -> $T.tryView(systemGraph, v).stream())",
                                ParameterizedTypeName.get(ClassName.get(Stream.class), TypeName.get(declaredType.getTypeArguments().get(0))),
                                name,
                                portOutputType
                        );
//                        getMethodBuilder.addStatement("if (out != null) return out");
                    }
                    if (execMember.getAnnotation(InPort.class) != null && execMember.getAnnotation(OutPort.class) != null) {
                        getMethodBuilder.addStatement("return $T.concat(in, out).findAny()", Stream.class);
                    } else if (execMember.getAnnotation(InPort.class) == null && execMember.getAnnotation(OutPort.class) != null) {
                        getMethodBuilder.addStatement("return out.findAny()");
                    } else if (execMember.getAnnotation(InPort.class) != null && execMember.getAnnotation(OutPort.class) == null) {
                        getMethodBuilder.addStatement("return in.findAny()");
                    } else {
                        getMethodBuilder.addStatement("return $T.empty()", Optional.class);
                    }
                    methods.add(getMethodBuilder.build());
                } else {
                    if (execMember.getAnnotation(InPort.class) != null) {
                        var portInputType = execMember.getReturnType().toString().contains("VertexViewer") ? ClassName.get(OpaqueVertexViewer.class) : ClassName.bestGuess(execMember.getReturnType().toString() + "Viewer");
                        getMethodBuilder.addStatement(
                                "final $T in = systemGraph.incomingEdgesOf(vertex).stream().filter(e -> e.connectsTargetPort($S)).map(systemGraph::getEdgeSource).flatMap(v -> $T.tryView(systemGraph, v).stream()).findAny().orElse(null)",
                                execMember.getReturnType(),
                                name,
                                portInputType
                        );
                        getMethodBuilder.addStatement("if (in != null) return in");
                    }
                    if (execMember.getAnnotation(OutPort.class) != null) {
                        // checking for what type of return type it is
                        var portOutputType = execMember.getReturnType().toString().contains("VertexViewer") ? ClassName.get(OpaqueVertexViewer.class) : ClassName.bestGuess(execMember.getReturnType().toString() + "Viewer");
                        getMethodBuilder.addStatement(
                                "final $T out = systemGraph.outgoingEdgesOf(vertex).stream().filter(e -> e.connectsSourcePort($S)).map(systemGraph::getEdgeTarget).flatMap(v -> $T.tryView(systemGraph, v).stream()).findAny().orElse(null)",
                                execMember.getReturnType(),
                                name,
                                portOutputType
                        );
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
        var members = viewerInterface.getEnclosedElements();
        for (var member : members) {
            if (member instanceof ExecutableElement execMember && (member.getAnnotation(Property.class) != null)) {
                var name = execMember.getSimpleName().toString();
                var getMethodBuilder = MethodSpec.methodBuilder(name).addModifiers(Modifier.PUBLIC).addAnnotation(Override.class).returns(TypeName.get(execMember.getReturnType()));
                if (execMember.isDefault()) {
                    getMethodBuilder.addStatement("if (!getViewedVertex().hasProperty($S)) getViewedVertex().putProperty($S, $T.super.$L())", name, name, viewerInterface, name);
                }
                getMethodBuilder.addStatement("return ($T) vertex.getProperty($S)", execMember.getReturnType(), name);
                methods.add(getMethodBuilder.build());
            }
        }
        return methods;
    }

    // taken from https://stackoverflow.com/questions/7687829/java-6-annotation-processing-getting-a-class-from-an-annotation
    protected TypeElement getRegisteredHierarchy(RegisterTrait annotation) {
        try
        {
            annotation.value(); // this should throw
        }
        catch( MirroredTypeException mte )
        {
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
        try
        {
            annotation.value(); // this should throw
        }
        catch( MirroredTypeException mte )
        {
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
        final TypeElement viewerT = processingEnv.getElementUtils().getTypeElement(VertexViewer.class.getCanonicalName());
        final TypeElement edgeT = processingEnv.getElementUtils().getTypeElement(EdgeTrait.class.getCanonicalName());
        var hierarchyElemName = hierarchyElemToName(hierarchy);
        var hierarchySpecBuilder = TypeSpec.classBuilder(hierarchyElemName).addModifiers(Modifier.PUBLIC).addSuperinterface(hierarchy.asType());
        var containedVertexTraits = containedTraits.stream().filter(t -> processingEnv.getTypeUtils().isSubtype(t.asType(), viewerT.asType())).collect(Collectors.toSet());
        var containedEdgeTraits = Stream.concat(
                containedTraits.stream().filter(t -> processingEnv.getTypeUtils().isSubtype(t.asType(), edgeT.asType())),
                containedTraits.stream().flatMap(t -> t.getEnclosedElements().stream()).filter(t -> t.getKind().equals(ElementKind.METHOD) && t.getAnnotation(WithEdgeTrait.class) != null).map(t -> getRegisteredEdge(t.getAnnotation(WithEdgeTrait.class)))).collect(Collectors.toSet());
        var vertexTraitEnumBuilder = TypeSpec.enumBuilder("VertexTraits").addSuperinterface(VertexTrait.class).addModifiers(Modifier.PUBLIC).addField(String.class, "name")
                .addMethod(MethodSpec.constructorBuilder().addParameter(String.class, "name").addStatement("this.name = name").build());
        var vertexTraitEnumRefinesBuilder = CodeBlock.builder().beginControlFlow("switch (this)");
        for (var trait : containedVertexTraits) {
            vertexTraitEnumBuilder.addEnumConstant(trait.getSimpleName().toString(), TypeSpec.anonymousClassBuilder("$S", trait.getQualifiedName().toString().replace(".", "::")).build());
            vertexTraitEnumRefinesBuilder.beginControlFlow("case $L: switch(other.getName())", trait.getSimpleName().toString());
            var traitInnerClassBuilder = TypeSpec.classBuilder(trait.getSimpleName().toString()).addModifiers(Modifier.PUBLIC);
            for (var refinedTrait : trait.getInterfaces()) {
                var elem = processingEnv.getTypeUtils().asElement(refinedTrait);
                if (elem instanceof TypeElement typeElement && !typeElement.getSimpleName().contentEquals("VertexViewer")) {
                    vertexTraitEnumRefinesBuilder.addStatement("case $S: return true", typeElement.getQualifiedName().toString().replace(".", "::"));
                }
            }
            vertexTraitEnumRefinesBuilder.addStatement("default: return false");
            vertexTraitEnumRefinesBuilder.endControlFlow();
            // add the viewing method
            var tryViewMethod = MethodSpec.methodBuilder("tryView")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .addParameter(SystemGraph.class, "systemGraph")
                    .addParameter(Vertex.class, "vertex")
                    .addStatement("return $T.tryView(systemGraph, vertex)", ClassName.get(processingEnv.getElementUtils().getPackageOf(trait).toString(), trait.getSimpleName() + "Viewer"))
                    .returns(ParameterizedTypeName.get(ClassName.get(Optional.class), ClassName.get(processingEnv.getElementUtils().getPackageOf(trait).toString(), trait.getSimpleName().toString() + "Viewer")))
                    .build();
            traitInnerClassBuilder.addMethod(tryViewMethod);
            var tryViewMethodFromViewer = MethodSpec.methodBuilder("tryView")
                    .addTypeVariable(TypeVariableName.get("T").withBounds(ClassName.get(VertexViewer.class)))
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .addParameter(TypeVariableName.get("T"), "otherViewer")
                    .addStatement("return $T.tryView(otherViewer)", ClassName.get(processingEnv.getElementUtils().getPackageOf(trait).toString(), trait.getSimpleName() + "Viewer"))
                    .returns(ParameterizedTypeName.get(ClassName.get(Optional.class), ClassName.get(processingEnv.getElementUtils().getPackageOf(trait).toString(), trait.getSimpleName().toString() + "Viewer")))
                    .build();
            traitInnerClassBuilder.addMethod(tryViewMethodFromViewer);
            var enforceMethod = MethodSpec.methodBuilder("enforce")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .addParameter(SystemGraph.class, "systemGraph")
                    .addParameter(Vertex.class, "vertex")
                    .addStatement("return $T.enforce(systemGraph, vertex)", ClassName.get(processingEnv.getElementUtils().getPackageOf(trait).toString(), trait.getSimpleName() + "Viewer"))
                    .returns(ClassName.get(processingEnv.getElementUtils().getPackageOf(trait).toString(), trait.getSimpleName().toString() + "Viewer"))
                    .build();
            traitInnerClassBuilder.addMethod(enforceMethod);
            var enforceFromViewer = MethodSpec.methodBuilder("enforce")
                    .addTypeVariable(TypeVariableName.get("T").withBounds(ClassName.get(VertexViewer.class)))
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .addParameter(TypeVariableName.get("T"), "otherViewer")
                    .addStatement("return $T.enforce(otherViewer.getViewedSystemGraph() , otherViewer.getViewedVertex())", ClassName.get(processingEnv.getElementUtils().getPackageOf(trait).toString(), trait.getSimpleName() + "Viewer"))
                    .returns(TypeName.get(trait.asType()))
                    .build();
            traitInnerClassBuilder.addMethod(enforceFromViewer);
//            traitInnerClassBuilder.addField(FieldSpec.builder(ClassName.get(trait.getSimpleName().toString()), "instance").addModifiers(Modifier.FINAL, Modifier.PUBLIC, Modifier.STATIC).initializer("new $T()", ClassName.get(trait.getSimpleName().toString())).build());
            hierarchySpecBuilder.addType(traitInnerClassBuilder.build());
        }
        vertexTraitEnumRefinesBuilder.addStatement("default: return false");
        vertexTraitEnumRefinesBuilder.endControlFlow();
        vertexTraitEnumBuilder.addMethod(MethodSpec.methodBuilder("refines").addParameter(Trait.class, "other").addModifiers(Modifier.PUBLIC).returns(TypeName.BOOLEAN).addCode(vertexTraitEnumRefinesBuilder.build()).build());
        vertexTraitEnumBuilder.addMethod(MethodSpec.methodBuilder("getName").addModifiers(Modifier.PUBLIC).returns(String.class).addStatement("return name").build());
        // edges now
        var edgesTraitEnumBuilder = TypeSpec.enumBuilder("EdgeTraits").addSuperinterface(EdgeTrait.class).addModifiers(Modifier.PUBLIC).addField(String.class, "name")
                .addMethod(MethodSpec.constructorBuilder().addParameter(String.class, "name").addStatement("this.name = name").build());
        var edgesTraitEnumRefinesBuilder = CodeBlock.builder().beginControlFlow("switch (this)");
        for (var trait : containedEdgeTraits) {
            edgesTraitEnumBuilder.addEnumConstant(trait.getSimpleName().toString(), TypeSpec.anonymousClassBuilder("$S", trait.getQualifiedName().toString().replace(".", "::")).build());
//            var traitInnerClassBuilder = TypeSpec.classBuilder(trait.getSimpleName().toString()).addModifiers(Modifier.PUBLIC);
//            traitInnerClassBuilder.addField(FieldSpec.builder(String.class, "name").initializer("$S", trait.getQualifiedName().toString().replace(".", "::")).addModifiers(Modifier.FINAL, Modifier.STATIC, Modifier.PUBLIC).build());
//            traitInnerClassBuilder.addMethod(MethodSpec.methodBuilder("getName").returns(String.class).addModifiers(Modifier.PUBLIC).addStatement("return name").build());
//            var refinesSpec = MethodSpec.methodBuilder("refines").addParameter(Trait.class, "other").returns(TypeName.BOOLEAN).addModifiers(Modifier.PUBLIC);
            edgesTraitEnumRefinesBuilder.beginControlFlow("case $L: switch (other.getName())", trait.getSimpleName().toString());
            for (var refinedTrait : trait.getInterfaces()) {
                var elem = processingEnv.getTypeUtils().asElement(refinedTrait);
                if (elem instanceof TypeElement typeElement && !typeElement.getSimpleName().contentEquals("EdgeTrait")) {
                    edgesTraitEnumRefinesBuilder.addStatement("case $S: return true", typeElement.getQualifiedName().toString().replace(".", "::"));
                }
            }
            edgesTraitEnumRefinesBuilder.addStatement("default: return false");
            edgesTraitEnumRefinesBuilder.endControlFlow();
//            hierarchySpecBuilder.addType(traitInnerClassBuilder.build());
        }
        edgesTraitEnumRefinesBuilder.addStatement("default: return false");
        edgesTraitEnumRefinesBuilder.endControlFlow();
        edgesTraitEnumBuilder.addMethod(MethodSpec.methodBuilder("refines").addParameter(Trait.class, "other").addModifiers(Modifier.PUBLIC).returns(TypeName.BOOLEAN).addCode(edgesTraitEnumRefinesBuilder.build()).build());
        edgesTraitEnumBuilder.addMethod(MethodSpec.methodBuilder("getName").addModifiers(Modifier.PUBLIC).returns(String.class).addStatement("return name").build());
        // finishing
        hierarchySpecBuilder.addMethod(MethodSpec.methodBuilder("fromName").addParameter(String.class, "traitName").addModifiers(Modifier.PUBLIC).returns(Trait.class).addStatement("return new $T($L)", OpaqueTrait.class, "traitName").build());
        hierarchySpecBuilder.addMethod(MethodSpec.methodBuilder("traits").addModifiers(Modifier.PUBLIC).returns(ParameterizedTypeName.get(Set.class, Trait.class)).addStatement("return $T.of()", Set.class).build());
        if (!containedVertexTraits.isEmpty()) hierarchySpecBuilder.addType(vertexTraitEnumBuilder.build());
        if(!containedEdgeTraits.isEmpty()) hierarchySpecBuilder.addType(edgesTraitEnumBuilder.build());
        return hierarchySpecBuilder.build();
    };

    protected MethodSpec makeEnforceMethod(TypeElement traitInterface) {
        var viewerClassSimpleName = traitInterface.getSimpleName().toString() + "Viewer";
        var enforceMethodBuilder = MethodSpec.methodBuilder("enforce")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(SystemGraph.class, "systemGraph")
                .addParameter(Vertex.class, "vertex")
                .returns(ClassName.get(processingEnv.getElementUtils().getPackageOf(traitInterface).toString(), traitInterface.getSimpleName().toString() + "Viewer"))
                .addStatement("vertex.addTrait($S)", traitInterface.getQualifiedName().toString().replace(".", "::"));
        enforceMethodBuilder.addStatement("final $L viewer = new $L(systemGraph, vertex)", viewerClassSimpleName, viewerClassSimpleName);
        // properties
        for (var member : traitInterface.getEnclosedElements()) {
            if (member instanceof ExecutableElement execMember && (member.getAnnotation(Property.class) != null)) {
                if (execMember.getReturnType().toString().contains("Map")) {
                    enforceMethodBuilder.addStatement("vertex.putProperty($S, new $T())", execMember.getSimpleName().toString(), HashMap.class);
                } else if (execMember.getReturnType().toString().contains("Set")) {
                    enforceMethodBuilder.addStatement("vertex.putProperty($S, new $T())", execMember.getSimpleName().toString(), HashSet.class);
                } else if (execMember.getReturnType().toString().contains("List")) {
                    enforceMethodBuilder.addStatement("vertex.putProperty($S, new $T())", execMember.getSimpleName().toString(), ArrayList.class);
                } else if (execMember.isDefault()) {
                    enforceMethodBuilder.addStatement("vertex.putProperty($S, viewer.$L())", execMember.getSimpleName().toString(), execMember.getSimpleName().toString());
                }
//                else {
//                    enforceMethodBuilder.addStatement("vertex.putProperty($S, new $T())", execMember.getSimpleName().toString(), execMember.getReturnType());
//                }
            }
        }
        // ports
        for (var member : traitInterface.getEnclosedElements()) {
            if (member instanceof ExecutableElement execMember && (member.getAnnotation(InPort.class) != null || member.getAnnotation(OutPort.class) != null)) {
                enforceMethodBuilder.addStatement("vertex.addPort($S)", execMember.getSimpleName().toString());
            }
        }
        enforceMethodBuilder.addStatement("return viewer", viewerClassSimpleName);

        return enforceMethodBuilder.build();
    }

    protected String hierarchyElemToName(TypeElement hierarchy) {
        return hierarchy.getSimpleName().toString().startsWith("I") ? hierarchy.getSimpleName().toString().substring(1) : hierarchy.getSimpleName().toString() + "Gen";
    }

}

