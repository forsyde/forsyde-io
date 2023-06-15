package forsyde.io.java.generator;

import com.squareup.javapoet.*;
import forsyde.io.java.core.*;
import forsyde.io.java.core.annotations.*;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
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

@SupportedAnnotationTypes({"forsyde.io.java.core.annotations.RegisterTrait"})
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class TraitViewerGenerator extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        final Map<TypeElement, TypeElement> traitsToHierarchy = new HashMap<>();
        final Map<TypeElement, TypeSpec> traitToSpec = new HashMap<>();
//        final Map<TypeElement, TypeSpec> hierarchyToSpec = new HashMap<>();
        try {
            var toGenerate = roundEnv.getElementsAnnotatedWith(RegisterTrait.class);
            var typesToGenerate = ElementFilter.typesIn(toGenerate);
            for (var typeElement : typesToGenerate) {
                var hierarchy = getRegisteredHierarchy(typeElement.getAnnotation(RegisterTrait.class));
//                hierarchyToSpec.putIfAbsent(hierarchy, makeHierarchy(hierarchy));
                var spec = makeViewer(typeElement);
                traitsToHierarchy.put(typeElement, hierarchy);
                traitToSpec.put(typeElement, spec);
                var javaFile = JavaFile.builder(processingEnv.getElementUtils().getPackageOf(typeElement).toString(), spec).build();
                javaFile.writeTo(processingEnv.getFiler());
            }
            var hierarchies = new HashSet<>(traitsToHierarchy.values());
            for (var typeHierarchy: hierarchies) {
                var containedTraits = traitsToHierarchy.entrySet().stream().filter(e -> e.getValue().equals(typeHierarchy)).map(Map.Entry::getKey).collect(Collectors.toSet());
//                hierarchyToSpec.putIfAbsent(hierarchy, makeHierarchy(hierarchy));
                var genTH = makeHierarchy(typeHierarchy, containedTraits, traitToSpec);
                var javaFile = JavaFile.builder(processingEnv.getElementUtils().getPackageOf(typeHierarchy).toString(), genTH).build();
                javaFile.writeTo(processingEnv.getFiler());
            }
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return false;
    }

    protected TypeSpec makeViewer(TypeElement traitInterface) {
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
                .returns(ParameterizedTypeName.get(ClassName.get(Optional.class), TypeName.get(traitInterface.asType())))
                .build();
        viewerClassBuilder.addMethod(tryViewMethod);
        var tryViewMethodFromViewer = MethodSpec.methodBuilder("tryView")
                .addTypeVariable(TypeVariableName.get("T").withBounds(ClassName.get(VertexViewer.class)))
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(TypeVariableName.get("T"), "otherViewer")
                .addStatement("return Optional.of(new $L(otherViewer.getViewedSystemGraph(), otherViewer.getViewedVertex()))", viewerClassSimpleName)
                .returns(ParameterizedTypeName.get(ClassName.get(Optional.class), TypeName.get(traitInterface.asType())))
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

        for (var portGetter : generatePortGetters(traitInterface)) {
            viewerClassBuilder.addMethod(portGetter);
        }
        for (var portSetter : generatePortSetters(traitInterface)) {
            viewerClassBuilder.addMethod(portSetter);
        }
        for (var propGetter: generatePropertyGetters(traitInterface)) {
            viewerClassBuilder.addMethod(propGetter);
        }
        return viewerClassBuilder.build();
    }

    protected Set<MethodSpec> generatePortSetters(TypeElement viewerInterface) {
        var methods = new HashSet<MethodSpec>();
        var members = processingEnv.getElementUtils().getAllMembers(viewerInterface);
        for (var member : members) {
            if (member instanceof ExecutableElement execMember && (member.getAnnotation(InPort.class) != null || member.getAnnotation(OutPort.class) != null)) {
                var name = execMember.getSimpleName().toString();
                var getMethodBuilderWithoutPort = MethodSpec.methodBuilder(name).addModifiers(Modifier.PUBLIC).returns(TypeName.get(viewerInterface.asType()));
                var getMethodBuilder = MethodSpec.methodBuilder(name).addParameter(String.class, "otherPort").addModifiers(Modifier.PUBLIC).returns(TypeName.get(viewerInterface.asType()));
                if (execMember.getReturnType() instanceof DeclaredType declaredType && (declaredType.asElement().getSimpleName().contentEquals("Set") || declaredType.asElement().getSimpleName().contentEquals("List"))) {
//                    getMethodBuilder.addStatement("$T<$T> collected = new $T<>()", ArrayList.class, declaredType.getTypeArguments().get(0), ArrayList.class);
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
//                    methods.add(getMethodBuilder.build());
                } else {
                    if (execMember.getAnnotation(InPort.class) != null) {
                        var portInputType = execMember.getReturnType().toString().contains("VertexViewer") ? ClassName.get(OpaqueVertexViewer.class) : ClassName.bestGuess(execMember.getReturnType().toString() + "Viewer");
                        getMethodBuilderWithoutPort.addParameter(portInputType, name);
                        getMethodBuilder.addParameter(portInputType, name);
                        if (execMember.getAnnotation(WithEdgeTrait.class) != null) {
                            getMethodBuilderWithoutPort.addStatement(
                                    "getViewedSystemGraph().connect($L, this, null, $S, $S)",
                                    name,
                                    name,
                                    execMember.getAnnotation(WithEdgeTrait.class).value().getCanonicalName().replace(".", "::")
                            );
                            getMethodBuilder.addStatement(
                                    "getViewedSystemGraph().connect($L, this, otherPort, $S, $S)",
                                    name,
                                    name,
                                    execMember.getAnnotation(WithEdgeTrait.class).value().getCanonicalName().replace(".", "::")
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
                            getMethodBuilderWithoutPort.addStatement(
                                    "getViewedSystemGraph().connect(this, $L, $S, $S)",
                                    name,
                                    name,
                                    execMember.getAnnotation(WithEdgeTrait.class).value().getCanonicalName().replace(".", "::")
                            );
                            getMethodBuilder.addStatement(
                                    "getViewedSystemGraph().connect(this, $L, $S, otherPort, $S)",
                                    name,
                                    name,
                                    execMember.getAnnotation(WithEdgeTrait.class).value().getCanonicalName().replace(".", "::")
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

    protected Set<MethodSpec> generatePortGetters(TypeElement viewerInterface) {
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
                                    "collected.addAll(systemGraph.incomingEdgesOf(vertex).stream().filter(e -> e.connectsTargetPort($S)).filter(e -> e.hasTrait($S)).map(systemGraph::getEdgeSource).flatMap(v -> $T.tryView(systemGraph, v).stream()).collect($T.toList()))",
                                    name,
                                    viewerInterface.getAnnotation(WithEdgeTrait.class).value().getCanonicalName().replace(".", "::"),
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

    protected TypeSpec makeHierarchy(TypeElement hierarchy, Set<TypeElement> containedTraits, Map<TypeElement, TypeSpec> traitToSpec) {
        var hierarchyElemName = hierarchy.getSimpleName().toString().startsWith("I") ? hierarchy.getSimpleName().toString().substring(1) : hierarchy.getSimpleName().toString() + "Gen";
        var hierarchySpecBuilder = TypeSpec.classBuilder(hierarchyElemName).addSuperinterface(hierarchy.asType());
        for (var trait : containedTraits) {
            var traitInnerClassBuilder = TypeSpec.classBuilder(trait.getSimpleName().toString()).addModifiers(Modifier.PUBLIC).addSuperinterface(Trait.class);
            traitInnerClassBuilder.addField(FieldSpec.builder(String.class, "name").initializer("$S", trait.getQualifiedName().toString().replace(".", "::")).addModifiers(Modifier.FINAL, Modifier.STATIC, Modifier.PUBLIC).build());
            traitInnerClassBuilder.addMethod(MethodSpec.methodBuilder("getName").returns(String.class).addModifiers(Modifier.PUBLIC).addStatement("return name").build());
            var refinesSpec = MethodSpec.methodBuilder("refines").addParameter(Trait.class, "other").returns(TypeName.BOOLEAN).addModifiers(Modifier.PUBLIC);
            var switchCodeBlock = CodeBlock.builder().beginControlFlow("switch (other.getName())");
            for (var refinedTrait : trait.getInterfaces()) {
                var elem = processingEnv.getTypeUtils().asElement(refinedTrait);
                if (elem instanceof TypeElement typeElement && !typeElement.getSimpleName().contentEquals("VertexViewer")) {
                    switchCodeBlock.addStatement("case $S: return true", typeElement.getQualifiedName().toString().replace(".", "::"));
                }
            }
            switchCodeBlock.addStatement("default: return false");
            switchCodeBlock.endControlFlow();
            traitInnerClassBuilder.addMethod(refinesSpec.addCode(switchCodeBlock.build()).build());
            // add the viewing method
            var tryViewMethod = MethodSpec.methodBuilder("tryView")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .addParameter(SystemGraph.class, "systemGraph")
                    .addParameter(Vertex.class, "vertex")
                    .addStatement("return $T.tryView(systemGraph, vertex)", ClassName.get(processingEnv.getElementUtils().getPackageOf(trait).toString(), trait.getSimpleName() + "Viewer"))
                    .returns(ParameterizedTypeName.get(ClassName.get(Optional.class), TypeName.get(trait.asType())))
                    .build();
            traitInnerClassBuilder.addMethod(tryViewMethod);
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
            hierarchySpecBuilder.addType(traitInnerClassBuilder.build());
        }
        hierarchySpecBuilder.addMethod(MethodSpec.methodBuilder("fromName").addParameter(String.class, "traitName").addModifiers(Modifier.PUBLIC).returns(Trait.class).addStatement("return new $T($L)", OpaqueTrait.class, "traitName").build());
        hierarchySpecBuilder.addMethod(MethodSpec.methodBuilder("traits").addModifiers(Modifier.PUBLIC).returns(ParameterizedTypeName.get(Set.class, Trait.class)).addStatement("return $T.of()", Set.class).build());
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

}

