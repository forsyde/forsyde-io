package forsyde.io.java.core;

import java.lang.String;

public enum VertexTrait implements Trait {
  AbstractGrouping,

  AbstractOrdering,

  TriggeredTask,

  SporadicTask,

  ExtraFunctional,

  WCET,

  WCCT,

  Requirement,

  HardRequirement,

  LocationRequirement,

  Goal,

  MinimumThroughput,

  TimeTriggeredScheduler,

  FixedPriorityScheduler,

  CustomScheduler,

  RoundRobinScheduler,

  ForSyDeFunction,

  InstrumentedFunction,

  Signal,

  BufferSignal,

  LabelSignal,

  Instrumented,

  InstrumentedSignal,

  SYComb,

  SYPrefix,

  SDFComb,

  SDFPrefix,

  ReactorElement,

  ReactorTimer,

  ReactorActor,

  AbstractPhysicalComponent,

  AbstractProcessingComponent,

  InstrumentedProcessorTile,

  AbstractStorageComponent,

  AbsractInterfaceComponent,

  AbstractCommunicationComponent,

  InstrumentedCommunicationInterconnect,

  TimeDivisionMultiplexer;

  public static boolean refines(VertexTrait one, VertexTrait other) {
    switch (one) {
      case AbstractGrouping:
      switch (other) {
        case AbstractGrouping: return true;
        default: return false;
      }
      case AbstractOrdering:
      switch (other) {
        case AbstractOrdering: return true;
        case AbstractGrouping: return true;
        default: return false;
      }
      case TriggeredTask:
      switch (other) {
        case TriggeredTask: return true;
        case AbstractOrdering: return true;
        case AbstractGrouping: return true;
        default: return false;
      }
      case SporadicTask:
      switch (other) {
        case SporadicTask: return true;
        case AbstractOrdering: return true;
        case AbstractGrouping: return true;
        default: return false;
      }
      case ExtraFunctional:
      switch (other) {
        case ExtraFunctional: return true;
        default: return false;
      }
      case WCET:
      switch (other) {
        case WCET: return true;
        case ExtraFunctional: return true;
        default: return false;
      }
      case WCCT:
      switch (other) {
        case WCCT: return true;
        case ExtraFunctional: return true;
        default: return false;
      }
      case Requirement:
      switch (other) {
        case Requirement: return true;
        default: return false;
      }
      case HardRequirement:
      switch (other) {
        case HardRequirement: return true;
        case Requirement: return true;
        default: return false;
      }
      case LocationRequirement:
      switch (other) {
        case LocationRequirement: return true;
        case Requirement: return true;
        default: return false;
      }
      case Goal:
      switch (other) {
        case Goal: return true;
        default: return false;
      }
      case MinimumThroughput:
      switch (other) {
        case MinimumThroughput: return true;
        case Goal: return true;
        default: return false;
      }
      case TimeTriggeredScheduler:
      switch (other) {
        case TimeTriggeredScheduler: return true;
        case AbstractGrouping: return true;
        default: return false;
      }
      case FixedPriorityScheduler:
      switch (other) {
        case FixedPriorityScheduler: return true;
        default: return false;
      }
      case CustomScheduler:
      switch (other) {
        case CustomScheduler: return true;
        default: return false;
      }
      case RoundRobinScheduler:
      switch (other) {
        case RoundRobinScheduler: return true;
        default: return false;
      }
      case ForSyDeFunction:
      switch (other) {
        case ForSyDeFunction: return true;
        default: return false;
      }
      case InstrumentedFunction:
      switch (other) {
        case InstrumentedFunction: return true;
        case ForSyDeFunction: return true;
        default: return false;
      }
      case Signal:
      switch (other) {
        case Signal: return true;
        default: return false;
      }
      case BufferSignal:
      switch (other) {
        case BufferSignal: return true;
        case Signal: return true;
        default: return false;
      }
      case LabelSignal:
      switch (other) {
        case LabelSignal: return true;
        case Signal: return true;
        default: return false;
      }
      case Instrumented:
      switch (other) {
        case Instrumented: return true;
        default: return false;
      }
      case InstrumentedSignal:
      switch (other) {
        case InstrumentedSignal: return true;
        case Instrumented: return true;
        case Signal: return true;
        default: return false;
      }
      case SYComb:
      switch (other) {
        case SYComb: return true;
        case ForSyDeFunction: return true;
        default: return false;
      }
      case SYPrefix:
      switch (other) {
        case SYPrefix: return true;
        case ForSyDeFunction: return true;
        default: return false;
      }
      case SDFComb:
      switch (other) {
        case SDFComb: return true;
        case ForSyDeFunction: return true;
        default: return false;
      }
      case SDFPrefix:
      switch (other) {
        case SDFPrefix: return true;
        case ForSyDeFunction: return true;
        default: return false;
      }
      case ReactorElement:
      switch (other) {
        case ReactorElement: return true;
        default: return false;
      }
      case ReactorTimer:
      switch (other) {
        case ReactorTimer: return true;
        case ReactorElement: return true;
        default: return false;
      }
      case ReactorActor:
      switch (other) {
        case ReactorActor: return true;
        case ReactorElement: return true;
        default: return false;
      }
      case AbstractPhysicalComponent:
      switch (other) {
        case AbstractPhysicalComponent: return true;
        default: return false;
      }
      case AbstractProcessingComponent:
      switch (other) {
        case AbstractProcessingComponent: return true;
        case AbstractPhysicalComponent: return true;
        default: return false;
      }
      case InstrumentedProcessorTile:
      switch (other) {
        case InstrumentedProcessorTile: return true;
        case Instrumented: return true;
        case AbstractProcessingComponent: return true;
        case AbstractPhysicalComponent: return true;
        default: return false;
      }
      case AbstractStorageComponent:
      switch (other) {
        case AbstractStorageComponent: return true;
        case AbstractPhysicalComponent: return true;
        default: return false;
      }
      case AbsractInterfaceComponent:
      switch (other) {
        case AbsractInterfaceComponent: return true;
        case AbstractPhysicalComponent: return true;
        default: return false;
      }
      case AbstractCommunicationComponent:
      switch (other) {
        case AbstractCommunicationComponent: return true;
        case AbstractPhysicalComponent: return true;
        default: return false;
      }
      case InstrumentedCommunicationInterconnect:
      switch (other) {
        case InstrumentedCommunicationInterconnect: return true;
        case AbstractCommunicationComponent: return true;
        case AbstractPhysicalComponent: return true;
        default: return false;
      }
      case TimeDivisionMultiplexer:
      switch (other) {
        case TimeDivisionMultiplexer: return true;
        case AbstractCommunicationComponent: return true;
        case AbstractPhysicalComponent: return true;
        default: return false;
      }
      default: return false;
    }
  }

  public boolean refines(Trait other) {
    return other instanceof VertexTrait ? VertexTrait.refines(this, (VertexTrait) other) : false;
  }

  public String getName() {
    return this.toString();
  }
}
