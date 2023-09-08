package forsyde.io.java.generator;

import forsyde.io.core.VertexViewer;
import forsyde.io.core.annotations.InPort;
import forsyde.io.core.annotations.OutPort;
import forsyde.io.core.annotations.Property;
import forsyde.io.core.annotations.WithEdgeTrait;
import forsyde.io.java.generator.specs.EdgeTraitSpec;
import forsyde.io.java.generator.specs.PortSpec;
import forsyde.io.java.generator.specs.PropertySpec;
import forsyde.io.java.generator.specs.TraitHierarchySpec;
import forsyde.io.java.generator.specs.VertexTraitSpec;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface HasSpecExtractors {

    /**
     * This method assumes that the hierarchy ALREADY contains all the required
     * traits. Be careful to ensure this while calling this method.
     * 
     * @param processingEnv                the processing env from the annotation
     *                                     processor
     * @param typeElement                  the type element of the vertex
     * @param containingTraitHierarchySpec the trait hierarchy that will contain the
     *                                     spec
     * @return the created spec
     */
    default VertexTraitSpec extractVertexSpecFromTypeElement(final ProcessingEnvironment processingEnv,
            final TypeElement typeElement, final TraitHierarchySpec containingTraitHierarchySpec) {
        var vertexSpec = new VertexTraitSpec();
        var vertexPackage = processingEnv.getElementUtils().getPackageOf(typeElement);
        vertexSpec.canonicalName = typeElement.getQualifiedName().toString().replace(".", "::");
        vertexSpec.hierarchySpec = containingTraitHierarchySpec;
        vertexSpec.htmlDescription = processingEnv.getElementUtils().getDocComment(typeElement);
        return vertexSpec;
    }

    default VertexTraitSpec linkVertexSpecFromTypeElement(final ProcessingEnvironment processingEnv,
            final TypeElement typeElement, final VertexTraitSpec vertexSpec,
            final Map<TypeElement, VertexTraitSpec> elementsToSpecs,
            final Map<TypeElement, EdgeTraitSpec> elementsToEdgeSpecs) {
        typeElement.getInterfaces().stream().map(t -> processingEnv.getTypeUtils().asElement(t))
                .filter(elementsToSpecs::containsKey)
                .map(elementsToSpecs::get)
                .forEach(spec -> vertexSpec.refinedTraits.add(spec));
        for (var elem : typeElement.getEnclosedElements()) {
            if (elem instanceof ExecutableElement executableElement) {
                if ((elem.getAnnotation(InPort.class) != null || elem.getAnnotation(OutPort.class) != null)) {
                    var portSpec = extractPortSpecFromExecutableElement(processingEnv, executableElement,
                            elementsToSpecs,
                            elementsToEdgeSpecs);
                    vertexSpec.requiredPorts.put(portSpec.name, portSpec);
                } else if (elem.getAnnotation(Property.class) != null) {
                    var propSpec = extractPropertySpecFromExecutableElement(processingEnv, executableElement,
                            vertexSpec);
                    vertexSpec.requiredProperties.put(propSpec.name, propSpec);
                }
            }
        }
        return vertexSpec;
    }

    default EdgeTraitSpec extractEdgeSpecFromTypeElement(final ProcessingEnvironment processingEnv,
            final TypeElement typeElement, final TraitHierarchySpec containingTraitHierarchySpec,
            final Map<TypeElement, EdgeTraitSpec> elementsToSpecs) {
        var edgeSpec = new EdgeTraitSpec();
        var vertexPackage = processingEnv.getElementUtils().getPackageOf(typeElement);
        edgeSpec.canonicalName = typeElement.getQualifiedName().toString().replace(".", "::");
        edgeSpec.traitHierarchySpec = containingTraitHierarchySpec;
        edgeSpec.htmlDescription = processingEnv.getElementUtils().getDocComment(typeElement);
        typeElement.getInterfaces().stream().map(t -> processingEnv.getTypeUtils().asElement(t))
                .filter(elementsToSpecs::containsKey)
                .map(elementsToSpecs::get)
                .forEach(spec -> edgeSpec.refinedTraits.add(spec));
        return edgeSpec;
    }

    default PortSpec extractPortSpecFromExecutableElement(final ProcessingEnvironment processingEnv,
            final ExecutableElement executableElement, final Map<TypeElement, VertexTraitSpec> elementsToVertex,
            final Map<TypeElement, EdgeTraitSpec> elementsToEdges) {
        var viewerT = processingEnv.getElementUtils().getTypeElement(VertexViewer.class.getCanonicalName());
        var setT = processingEnv.getElementUtils().getTypeElement(Set.class.getCanonicalName());
        var optT = processingEnv.getElementUtils().getTypeElement(Optional.class.getCanonicalName());
        var inPorT = processingEnv.getElementUtils().getTypeElement(InPort.class.getCanonicalName());
        var outPortT = processingEnv.getElementUtils().getTypeElement(OutPort.class.getCanonicalName());
        var withEdgeT = processingEnv.getElementUtils().getTypeElement(WithEdgeTrait.class.getCanonicalName());
        var portSpec = new PortSpec();
        portSpec.name = executableElement.getSimpleName().toString();
        portSpec.incoming = processingEnv.getElementUtils().getAllAnnotationMirrors(executableElement).stream()
                .anyMatch(annotationMirror -> processingEnv.getTypeUtils()
                        .isSubtype(annotationMirror.getAnnotationType(), inPorT.asType()));
        portSpec.outgoing = processingEnv.getElementUtils().getAllAnnotationMirrors(executableElement).stream()
                .anyMatch(annotationMirror -> processingEnv.getTypeUtils()
                        .isSubtype(annotationMirror.getAnnotationType(), outPortT.asType()));
        processingEnv.getElementUtils().getAllAnnotationMirrors(executableElement).stream()
                .filter(annotationMirror -> processingEnv.getTypeUtils().isSubtype(annotationMirror.getAnnotationType(),
                        withEdgeT.asType()))
                .flatMap(annotationMirror -> annotationMirror.getElementValues().entrySet().stream())
                .filter(e -> e.getKey().getSimpleName().contentEquals("value"))
                .flatMap(e -> processingEnv.getElementUtils().getAllTypeElements(e.getValue().getValue().toString())
                        .stream())
                .findAny()
                .ifPresent(typeElement -> portSpec.edgeTrait = elementsToEdges.get(typeElement));
        if (executableElement.getReturnType() instanceof DeclaredType declaredType) {
            if (processingEnv.getTypeUtils().isAssignable(declaredType.asElement().asType(), setT.asType())) {
                portSpec.multiple = true;
                portSpec.optional = false;
                portSpec.vertexTrait = elementsToVertex
                        .get(processingEnv.getTypeUtils().asElement(declaredType.getTypeArguments().get(0)));
            } else if (processingEnv.getTypeUtils().isAssignable(declaredType.asElement().asType(), optT.asType())) {
                portSpec.multiple = false;
                portSpec.optional = true;
                portSpec.vertexTrait = elementsToVertex
                        .get(processingEnv.getTypeUtils().asElement(declaredType.getTypeArguments().get(0)));
            } else if (processingEnv.getTypeUtils().isSubtype(declaredType, viewerT.asType())) {
                portSpec.multiple = false;
                portSpec.optional = false;
                portSpec.vertexTrait = elementsToVertex.get(processingEnv.getTypeUtils().asElement(declaredType));
            }
        }
        return portSpec;
    }

    default PropertySpec extractPropertySpecFromExecutableElement(final ProcessingEnvironment processingEnv,
            final ExecutableElement executableElement,
            final VertexTraitSpec containingVertex) {
        var propSpec = new PropertySpec();
        propSpec.name = executableElement.getSimpleName().toString();
        if (executableElement.isDefault()) {
            var clsName = containingVertex.getJavaCanonicalName();
            propSpec.initializationCode.put("java",
                    "if (!getViewedVertex().hasProperty(\"%s\")) getViewedVertex().putProperty(\"%s\", %s.super.%s())"
                            .formatted(propSpec.name, propSpec.name, clsName, propSpec.name));
        }
        return propSpec;
    }
}
