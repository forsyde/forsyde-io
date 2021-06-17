package forsyde.io.java.typed.prototypes;

import forsyde.io.java.core.Edge;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.interfaces.ReactorTimerPrototype;
import java.lang.Boolean;
import java.lang.Integer;
import java.util.HashSet;

public final class ReactorTimer extends Vertex implements ReactorTimerPrototype {
  public static Integer staticGetPeriodNumeratorPerSec(Vertex vertex) {
    return (java.lang.Integer) vertex.properties.get("period_numerator_per_sec").unwrap();
  }

  public Integer getPeriodNumeratorPerSec() {
    return staticGetPeriodNumeratorPerSec(this);
  }

  public static Integer staticGetPeriodDenominatorPerSec(Vertex vertex) {
    return (java.lang.Integer) vertex.properties.get("period_denominator_per_sec").unwrap();
  }

  public Integer getPeriodDenominatorPerSec() {
    return staticGetPeriodDenominatorPerSec(this);
  }

  public static Integer staticGetOffsetNumeratorPerSec(Vertex vertex) {
    return (java.lang.Integer) vertex.properties.get("offset_numerator_per_sec").unwrap();
  }

  public Integer getOffsetNumeratorPerSec() {
    return staticGetOffsetNumeratorPerSec(this);
  }

  public static Integer staticGetOffsetDenominatorPerSec(Vertex vertex) {
    return (java.lang.Integer) vertex.properties.get("offset_denominator_per_sec").unwrap();
  }

  public Integer getOffsetDenominatorPerSec() {
    return staticGetOffsetDenominatorPerSec(this);
  }

  public static HashSet<ReactorElement> staticGetOutputPort(ForSyDeModel model, Vertex vertex) {
    HashSet<ReactorElement> outList = new HashSet<ReactorElement>();
    for (Edge e: model.outgoingEdgesOf(vertex)) {
      if (e.sourcePort.orElse("").equals("output") && ReactorElement.conforms(vertex)) {
        outList.add((ReactorElement) e.target);
      }
    }
    return outList;
  }

  public HashSet<ReactorElement> getOutputPort(ForSyDeModel model) {
    return staticGetOutputPort(model, this);
  }

  public static Boolean conforms(Vertex vertex) {
    return vertex.vertexTraits.contains(VertexTrait.ReactorTimerTrait);
  }
}
