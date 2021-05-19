package forsyde.io.java.core;

import java.lang.String;

public enum EdgeTrait implements Trait {
  Input,

  Output,

  Annotation,

  Expansion,

  AbstractPhysicalConnection,

  AbstractDecision,

  AbstractScheduling,

  AbstractMapping;

  public static boolean refines(EdgeTrait one, EdgeTrait other) {
    switch (one) {
      case Input:
      switch (other) {
        case Input: return true;
        default: return false;
      }
      case Output:
      switch (other) {
        case Output: return true;
        default: return false;
      }
      case Annotation:
      switch (other) {
        case Annotation: return true;
        default: return false;
      }
      case Expansion:
      switch (other) {
        case Expansion: return true;
        default: return false;
      }
      case AbstractPhysicalConnection:
      switch (other) {
        case AbstractPhysicalConnection: return true;
        default: return false;
      }
      case AbstractDecision:
      switch (other) {
        case AbstractDecision: return true;
        default: return false;
      }
      case AbstractScheduling:
      switch (other) {
        case AbstractScheduling: return true;
        case AbstractDecision: return true;
        default: return false;
      }
      case AbstractMapping:
      switch (other) {
        case AbstractMapping: return true;
        case AbstractDecision: return true;
        default: return false;
      }
      default: return false;
    }
  }

  public boolean refines(Trait other) {
    return other instanceof EdgeTrait ? EdgeTrait.refines(this, (EdgeTrait) other) : false;
  }

  public String getName() {
    return this.toString();
  }
}
