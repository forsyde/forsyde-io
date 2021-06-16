package forsyde.io.java.core;

import java.lang.String;

public enum VertexTrait implements Trait {
  AbstractGroupingTrait,

  AbstractOrderingTrait,

  TriggeredTaskTrait,

  SporadicTaskTrait,

  ExtraFunctionalTrait,

  WCETTrait,

  WCCTTrait,

  RequirementTrait,

  HardRequirementTrait,

  LocationRequirementTrait,

  GoalTrait,

  MinimumThroughputTrait,

  TimeTriggeredSchedulerTrait,

  FixedPrioritySchedulerTrait,

  CustomSchedulerTrait,

  RoundRobinSchedulerTrait,

  ForSyDeFunctionTrait,

  InstrumentedFunctionTrait,

  SignalTrait,

  BufferSignalTrait,

  LabelSignalTrait,

  InstrumentedTrait,

  InstrumentedSignalTrait,

  SYCombTrait,

  SYPrefixTrait,

  SDFCombTrait,

  SDFPrefixTrait,

  ReactorElementTrait,

  ReactorTimerTrait,

  ReactorActorTrait,

  AbstractPhysicalComponentTrait,

  AbstractProcessingComponentTrait,

  InstrumentedProcessorTileTrait,

  AbstractStorageComponentTrait,

  AbsractInterfaceComponentTrait,

  AbstractCommunicationComponentTrait,

  InstrumentedCommunicationInterconnectTrait,

  TimeDivisionMultiplexerTrait;

  public static boolean refines(VertexTrait one, VertexTrait other) {
    switch (one) {
      case AbstractGroupingTrait:
      switch (other) {
        case AbstractGroupingTrait: return true;
        default: return false;
      }
      case AbstractOrderingTrait:
      switch (other) {
        case AbstractOrderingTrait: return true;
        case AbstractGroupingTrait: return true;
        default: return false;
      }
      case TriggeredTaskTrait:
      switch (other) {
        case TriggeredTaskTrait: return true;
        case AbstractOrderingTrait: return true;
        case AbstractGroupingTrait: return true;
        default: return false;
      }
      case SporadicTaskTrait:
      switch (other) {
        case SporadicTaskTrait: return true;
        case AbstractOrderingTrait: return true;
        case AbstractGroupingTrait: return true;
        default: return false;
      }
      case ExtraFunctionalTrait:
      switch (other) {
        case ExtraFunctionalTrait: return true;
        default: return false;
      }
      case WCETTrait:
      switch (other) {
        case WCETTrait: return true;
        case ExtraFunctionalTrait: return true;
        default: return false;
      }
      case WCCTTrait:
      switch (other) {
        case WCCTTrait: return true;
        case ExtraFunctionalTrait: return true;
        default: return false;
      }
      case RequirementTrait:
      switch (other) {
        case RequirementTrait: return true;
        default: return false;
      }
      case HardRequirementTrait:
      switch (other) {
        case HardRequirementTrait: return true;
        case RequirementTrait: return true;
        default: return false;
      }
      case LocationRequirementTrait:
      switch (other) {
        case LocationRequirementTrait: return true;
        case RequirementTrait: return true;
        default: return false;
      }
      case GoalTrait:
      switch (other) {
        case GoalTrait: return true;
        default: return false;
      }
      case MinimumThroughputTrait:
      switch (other) {
        case MinimumThroughputTrait: return true;
        case GoalTrait: return true;
        default: return false;
      }
      case TimeTriggeredSchedulerTrait:
      switch (other) {
        case TimeTriggeredSchedulerTrait: return true;
        case AbstractGroupingTrait: return true;
        default: return false;
      }
      case FixedPrioritySchedulerTrait:
      switch (other) {
        case FixedPrioritySchedulerTrait: return true;
        default: return false;
      }
      case CustomSchedulerTrait:
      switch (other) {
        case CustomSchedulerTrait: return true;
        default: return false;
      }
      case RoundRobinSchedulerTrait:
      switch (other) {
        case RoundRobinSchedulerTrait: return true;
        default: return false;
      }
      case ForSyDeFunctionTrait:
      switch (other) {
        case ForSyDeFunctionTrait: return true;
        default: return false;
      }
      case InstrumentedFunctionTrait:
      switch (other) {
        case InstrumentedFunctionTrait: return true;
        case ForSyDeFunctionTrait: return true;
        default: return false;
      }
      case SignalTrait:
      switch (other) {
        case SignalTrait: return true;
        default: return false;
      }
      case BufferSignalTrait:
      switch (other) {
        case BufferSignalTrait: return true;
        case SignalTrait: return true;
        default: return false;
      }
      case LabelSignalTrait:
      switch (other) {
        case LabelSignalTrait: return true;
        case SignalTrait: return true;
        default: return false;
      }
      case InstrumentedTrait:
      switch (other) {
        case InstrumentedTrait: return true;
        default: return false;
      }
      case InstrumentedSignalTrait:
      switch (other) {
        case InstrumentedSignalTrait: return true;
        case InstrumentedTrait: return true;
        case SignalTrait: return true;
        default: return false;
      }
      case SYCombTrait:
      switch (other) {
        case SYCombTrait: return true;
        case ForSyDeFunctionTrait: return true;
        default: return false;
      }
      case SYPrefixTrait:
      switch (other) {
        case SYPrefixTrait: return true;
        case ForSyDeFunctionTrait: return true;
        default: return false;
      }
      case SDFCombTrait:
      switch (other) {
        case SDFCombTrait: return true;
        case ForSyDeFunctionTrait: return true;
        default: return false;
      }
      case SDFPrefixTrait:
      switch (other) {
        case SDFPrefixTrait: return true;
        case ForSyDeFunctionTrait: return true;
        default: return false;
      }
      case ReactorElementTrait:
      switch (other) {
        case ReactorElementTrait: return true;
        default: return false;
      }
      case ReactorTimerTrait:
      switch (other) {
        case ReactorTimerTrait: return true;
        case ReactorElementTrait: return true;
        default: return false;
      }
      case ReactorActorTrait:
      switch (other) {
        case ReactorActorTrait: return true;
        case ForSyDeFunctionTrait: return true;
        case ReactorElementTrait: return true;
        default: return false;
      }
      case AbstractPhysicalComponentTrait:
      switch (other) {
        case AbstractPhysicalComponentTrait: return true;
        default: return false;
      }
      case AbstractProcessingComponentTrait:
      switch (other) {
        case AbstractProcessingComponentTrait: return true;
        case AbstractPhysicalComponentTrait: return true;
        default: return false;
      }
      case InstrumentedProcessorTileTrait:
      switch (other) {
        case InstrumentedProcessorTileTrait: return true;
        case InstrumentedTrait: return true;
        case AbstractProcessingComponentTrait: return true;
        case AbstractPhysicalComponentTrait: return true;
        default: return false;
      }
      case AbstractStorageComponentTrait:
      switch (other) {
        case AbstractStorageComponentTrait: return true;
        case AbstractPhysicalComponentTrait: return true;
        default: return false;
      }
      case AbsractInterfaceComponentTrait:
      switch (other) {
        case AbsractInterfaceComponentTrait: return true;
        case AbstractPhysicalComponentTrait: return true;
        default: return false;
      }
      case AbstractCommunicationComponentTrait:
      switch (other) {
        case AbstractCommunicationComponentTrait: return true;
        case AbstractPhysicalComponentTrait: return true;
        default: return false;
      }
      case InstrumentedCommunicationInterconnectTrait:
      switch (other) {
        case InstrumentedCommunicationInterconnectTrait: return true;
        case AbstractCommunicationComponentTrait: return true;
        case AbstractPhysicalComponentTrait: return true;
        default: return false;
      }
      case TimeDivisionMultiplexerTrait:
      switch (other) {
        case TimeDivisionMultiplexerTrait: return true;
        case AbstractCommunicationComponentTrait: return true;
        case AbstractPhysicalComponentTrait: return true;
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
