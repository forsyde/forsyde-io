package forsyde.io.java.core;

import java.lang.String;

public enum EdgeTrait implements Trait {
  InputTrait,

  OutputTrait,

  AnnotationTrait,

  CompositionTrait,

  AbstractPhysicalConnectionTrait,

  AbstractDecisionTrait,

  AbstractSchedulingTrait,

  AbstractMappingTrait,

  AbstractAllocationTrait;

  public static boolean refines(EdgeTrait one, EdgeTrait other) {
    switch (one) {
      case InputTrait:
      switch (other) {
        case InputTrait: return true;
        default: return false;
      }
      case OutputTrait:
      switch (other) {
        case OutputTrait: return true;
        default: return false;
      }
      case AnnotationTrait:
      switch (other) {
        case AnnotationTrait: return true;
        default: return false;
      }
      case CompositionTrait:
      switch (other) {
        case CompositionTrait: return true;
        default: return false;
      }
      case AbstractPhysicalConnectionTrait:
      switch (other) {
        case AbstractPhysicalConnectionTrait: return true;
        default: return false;
      }
      case AbstractDecisionTrait:
      switch (other) {
        case AbstractDecisionTrait: return true;
        default: return false;
      }
      case AbstractSchedulingTrait:
      switch (other) {
        case AbstractSchedulingTrait: return true;
        case AbstractDecisionTrait: return true;
        default: return false;
      }
      case AbstractMappingTrait:
      switch (other) {
        case AbstractMappingTrait: return true;
        case AbstractDecisionTrait: return true;
        default: return false;
      }
      case AbstractAllocationTrait:
      switch (other) {
        case AbstractAllocationTrait: return true;
        case AbstractDecisionTrait: return true;
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
