package forsyde.io.java.generator;

import com.squareup.javapoet.*;
import forsyde.io.java.core.OpaqueVertexViewer;
import forsyde.io.java.core.SystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexViewer;
import forsyde.io.java.core.annotations.GenerateViewer;
import forsyde.io.java.core.annotations.InPort;
import forsyde.io.java.core.annotations.OutPort;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedAnnotationTypes({"forsyde.io.java.core.annotations.GenerateViewer"})
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class TraitViewerGenerator extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        try {
            var toGenerate = roundEnv.getElementsAnnotatedWith(GenerateViewer.class);
            var typesToGenerate = ElementFilter.typesIn(toGenerate);
            for (var typeElement : typesToGenerate) {
                var spec = makeViewer(typeElement);
                var javaFile = JavaFile.builder(processingEnv.getElementUtils().getPackageOf(typeElement).toString(), spec).build();
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
        for (var portGetter : generatePortGetters(traitInterface)) {
            viewerClassBuilder.addMethod(portGetter);
        }
        return viewerClassBuilder.build();
    }

    protected Set<MethodSpec> generatePortGetters(TypeElement viewerInterface) {
        var methods = new HashSet<MethodSpec>();
        var members = processingEnv.getElementUtils().getAllMembers(viewerInterface);
        for (var member : members) {
            if (member instanceof ExecutableElement && (member.getAnnotation(InPort.class) != null || member.getAnnotation(OutPort.class) != null)) {
                var execMember = (ExecutableElement) member;
                var name = execMember.getSimpleName().toString();
                var getMethodBuilder = MethodSpec.methodBuilder(name).addModifiers(Modifier.PUBLIC).addAnnotation(Override.class).returns(TypeName.get(execMember.getReturnType()));
                if (execMember.getReturnType() instanceof DeclaredType && execMember.getReturnType().toString().contains("Set")) {
                    var declaredType = (DeclaredType) execMember.getReturnType();
                    getMethodBuilder.addStatement("$T<$T> collected = new $T<>()", HashSet.class, declaredType.getTypeArguments().get(0), HashSet.class);
                    if (execMember.getAnnotation(InPort.class) != null) {
                        var portInputType = declaredType.getTypeArguments().get(0).toString().contains("VertexViewer") ? ClassName.get(OpaqueVertexViewer.class) : ClassName.bestGuess(declaredType.getTypeArguments().get(0).toString() + "Viewer");
                        getMethodBuilder.addStatement(
                                "collected.addAll(systemGraph.incomingEdgesOf(vertex).stream().filter(e -> e.connectsTargetPort($S)).map(systemGraph::getEdgeSource).flatMap(v -> $T.tryView(systemGraph, v).stream()).collect($T.toSet()))",
                                name,
                                portInputType,
                                Collectors.class
                        );
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
                    getMethodBuilder.addStatement("return collected");
                    methods.add(getMethodBuilder.build());
                }
            }
        }
        return methods;
    }

}

