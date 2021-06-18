package forsyde.io.java.typed.interfaces;

import forsyde.io.java.core.EdgeInterface;
import forsyde.io.java.core.ForSyDeModel;
import forsyde.io.java.core.VertexInterface;
import forsyde.io.java.core.VertexTrait;
import java.lang.Boolean;
import java.lang.String;
import java.util.HashSet;
import java.util.Optional;

public interface WCCT extends VertexInterface, ExtraFunctional {
  default String getTime() {
    return (String) getProperties().get("time").unwrap();
  }

  default HashSet<Process> getSignalPort(ForSyDeModel model) {
    HashSet<Process> outList = new HashSet<Process>();
    for (EdgeInterface e: model.outgoingEdgesOf(this)) {
      if (e.getSourcePort().orElse("").equals("signal") && e.getTarget() instanceof Process) {
        outList.add((Process)  e.getTarget());
      }
    }
    return outList;
  }

  default HashSet<AbstractCommunicationComponent> getPlatformElementPort(ForSyDeModel model) {
    HashSet<AbstractCommunicationComponent> outList = new HashSet<AbstractCommunicationComponent>();
    for (EdgeInterface e: model.outgoingEdgesOf(this)) {
      if (e.getSourcePort().orElse("").equals("platform_element") && e.getTarget() instanceof AbstractCommunicationComponent) {
        outList.add((AbstractCommunicationComponent)  e.getTarget());
      }
    }
    return outList;
  }

  static Boolean conforms(VertexInterface vertex) {
    return vertex.getTraits().contains(VertexTrait.WCCT);
  }

  static Optional<WCCT> safeCast(VertexInterface vertex) {
    return conforms(vertex) ? Optional.of((WCCT) vertex) : Optional.empty();
  }
}
