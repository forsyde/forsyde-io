package meta;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

public class ExtractorAndGenerator {

    public static void main(String args[]) {

        String pkg = "meta";
        try (ScanResult scanResult = new ClassGraph().verbose() // Log to stderr
                .enableAllInfo() // Scan classes, methods, fields, annotations
                .acceptPackages(pkg) // Scan com.xyz and subpackages (omit to scan all packages)
                .scan()) { // Start the scan
            // for (ClassInfo routeClassInfo :
            // scanResult.getClassesWithAnnotation(routeAnnotation)) {
            // AnnotationInfo routeAnnotationInfo =
            // routeClassInfo.getAnnotationInfo(routeAnnotation);
            // List<AnnotationParameterValue> routeParamVals =
            // routeAnnotationInfo.getParameterValues();
            // @com.xyz.Route has one required parameter
            // String route = (String) routeParamVals.get(0).getValue();
            // System.out.println(routeClassInfo.getName() + " is annotated with route " +
            // route);
            // }
            System.out.println(scanResult.getClassesImplementing("meta.VertexTraitSpec"));
        }
    }
}
