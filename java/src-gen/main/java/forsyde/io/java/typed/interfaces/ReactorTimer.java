package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.EdgeInterface;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.String;
import java.util.HashSet;
import java.util.Optional;

public interface ReactorTimer extends VertexInterface, ReactorElement {
  default String getPeriodNumeratorPerSec() {
    return (String) getProperties().get("period_numerator_per_sec").unwrap();
  }

  default String getPeriodDenominatorPerSec() {
    return (String) getProperties().get("period_denominator_per_sec").unwrap();
  }

  default String getOffsetNumeratorPerSec() {
    return (String) getProperties().get("offset_numerator_per_sec").unwrap();
  }

  default String getOffsetDenominatorPerSec() {
    return (String) getProperties().get("offset_denominator_per_sec").unwrap();
  }

  default HashSet<ReactorElement> getOutputPort(ForSyDeModel model) {
    HashSet<ReactorElement> outList = new HashSet<ReactorElement>();
    for (EdgeInterface e: model.outgoingEdgesOf(this)) {
      if (e.getSourcePort().orElse("").equals("output") && e.getTarget() instanceof ReactorElement) {
        outList.add((ReactorElement)  e.getTarget());
      }
    }
    return outList;
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.ReactorTimer);
  }

  static Optional<ReactorTimer> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((ReactorTimer) vertex) : Optional.empty();
  }
}
