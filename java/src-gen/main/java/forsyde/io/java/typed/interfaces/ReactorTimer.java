package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.EdgeInterface;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.Integer;
import java.util.HashSet;

public interface ReactorTimer extends VertexInterface, ReactorElement {
  default Integer getPeriodNumeratorPerSec() {
    return (java.lang.Integer) getProperties().get("period_numerator_per_sec").unwrap();
  }

  default Integer getPeriodDenominatorPerSec() {
    return (java.lang.Integer) getProperties().get("period_denominator_per_sec").unwrap();
  }

  default Integer getOffsetNumeratorPerSec() {
    return (java.lang.Integer) getProperties().get("offset_numerator_per_sec").unwrap();
  }

  default Integer getOffsetDenominatorPerSec() {
    return (java.lang.Integer) getProperties().get("offset_denominator_per_sec").unwrap();
  }

  default HashSet<VertexInterface> getOutputPort(ForSyDeModel model) {
    HashSet<VertexInterface> outList = new HashSet<VertexInterface>();
    for (EdgeInterface e: model.outgoingEdgesOf(this)) {
      if (e.getSourcePort().orElse("").equals("output")) {
        outList.add(e.getTarget());
      }
    }
    return outList;
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.ReactorTimer);
  }
}
