module Types

import ForSyDeIO.Models

@enum VertexTrait <: ForSyDeIO.Model.Trait begin
  CustomScheduler
  InstrumentedCommunicationInterconnect
  Goal
  TimeDivisionMultiplexer
  AbstractOrdering
  FixedPriorityScheduler
  BufferSignal
  ExtraFunctional
  SporadicTask
  TimeTriggeredScheduler
  SYComb
  WCET
  LabelSignal
  HardRequirement
  Signal
  RoundRobinScheduler
  InstrumentedSignal
  AbstractPhysicalComponent
  AbstractProcessingComponent
  WCCT
  Function
  AbstractGrouping
  TriggeredTask
  Requirement
  InstrumentedFunction
  SDFComb
  AbsractInterfaceComponent
  SYPrefix
  InstrumentedProcessorTile
  LocationRequirement
  MinimumThroughput
  SDFPrefix
  AbstractStorageComponent
  Instrumented
  AbstractCommunicationComponent
end

function refines(t1::VertexTrait, t2::VertexTrait)
  if t1 == t2
    return true
  elseif t1 == CustomScheduler && t2 == CustomScheduler
    return true
  elseif t1 == InstrumentedCommunicationInterconnect && t2 == InstrumentedCommunicationInterconnect
    return true
  elseif t1 == InstrumentedCommunicationInterconnect && t2 == AbstractPhysicalComponent
    return true
  elseif t1 == InstrumentedCommunicationInterconnect && t2 == AbstractCommunicationComponent
    return true
  elseif t1 == Goal && t2 == Goal
    return true
  elseif t1 == TimeDivisionMultiplexer && t2 == TimeDivisionMultiplexer
    return true
  elseif t1 == TimeDivisionMultiplexer && t2 == AbstractPhysicalComponent
    return true
  elseif t1 == TimeDivisionMultiplexer && t2 == AbstractCommunicationComponent
    return true
  elseif t1 == AbstractOrdering && t2 == AbstractOrdering
    return true
  elseif t1 == AbstractOrdering && t2 == AbstractGrouping
    return true
  elseif t1 == FixedPriorityScheduler && t2 == FixedPriorityScheduler
    return true
  elseif t1 == BufferSignal && t2 == BufferSignal
    return true
  elseif t1 == BufferSignal && t2 == Signal
    return true
  elseif t1 == ExtraFunctional && t2 == ExtraFunctional
    return true
  elseif t1 == SporadicTask && t2 == AbstractOrdering
    return true
  elseif t1 == SporadicTask && t2 == SporadicTask
    return true
  elseif t1 == SporadicTask && t2 == AbstractGrouping
    return true
  elseif t1 == TimeTriggeredScheduler && t2 == TimeTriggeredScheduler
    return true
  elseif t1 == TimeTriggeredScheduler && t2 == AbstractGrouping
    return true
  elseif t1 == SYComb && t2 == SYComb
    return true
  elseif t1 == SYComb && t2 == Function
    return true
  elseif t1 == WCET && t2 == ExtraFunctional
    return true
  elseif t1 == WCET && t2 == WCET
    return true
  elseif t1 == LabelSignal && t2 == LabelSignal
    return true
  elseif t1 == LabelSignal && t2 == Signal
    return true
  elseif t1 == HardRequirement && t2 == HardRequirement
    return true
  elseif t1 == HardRequirement && t2 == Requirement
    return true
  elseif t1 == Signal && t2 == Signal
    return true
  elseif t1 == RoundRobinScheduler && t2 == RoundRobinScheduler
    return true
  elseif t1 == InstrumentedSignal && t2 == Signal
    return true
  elseif t1 == InstrumentedSignal && t2 == InstrumentedSignal
    return true
  elseif t1 == InstrumentedSignal && t2 == Instrumented
    return true
  elseif t1 == AbstractPhysicalComponent && t2 == AbstractPhysicalComponent
    return true
  elseif t1 == AbstractProcessingComponent && t2 == AbstractPhysicalComponent
    return true
  elseif t1 == AbstractProcessingComponent && t2 == AbstractProcessingComponent
    return true
  elseif t1 == WCCT && t2 == ExtraFunctional
    return true
  elseif t1 == WCCT && t2 == WCCT
    return true
  elseif t1 == Function && t2 == Function
    return true
  elseif t1 == AbstractGrouping && t2 == AbstractGrouping
    return true
  elseif t1 == TriggeredTask && t2 == AbstractOrdering
    return true
  elseif t1 == TriggeredTask && t2 == AbstractGrouping
    return true
  elseif t1 == TriggeredTask && t2 == TriggeredTask
    return true
  elseif t1 == Requirement && t2 == Requirement
    return true
  elseif t1 == InstrumentedFunction && t2 == Function
    return true
  elseif t1 == InstrumentedFunction && t2 == InstrumentedFunction
    return true
  elseif t1 == SDFComb && t2 == Function
    return true
  elseif t1 == SDFComb && t2 == SDFComb
    return true
  elseif t1 == AbsractInterfaceComponent && t2 == AbstractPhysicalComponent
    return true
  elseif t1 == AbsractInterfaceComponent && t2 == AbsractInterfaceComponent
    return true
  elseif t1 == SYPrefix && t2 == Function
    return true
  elseif t1 == SYPrefix && t2 == SYPrefix
    return true
  elseif t1 == InstrumentedProcessorTile && t2 == AbstractPhysicalComponent
    return true
  elseif t1 == InstrumentedProcessorTile && t2 == AbstractProcessingComponent
    return true
  elseif t1 == InstrumentedProcessorTile && t2 == InstrumentedProcessorTile
    return true
  elseif t1 == InstrumentedProcessorTile && t2 == Instrumented
    return true
  elseif t1 == LocationRequirement && t2 == Requirement
    return true
  elseif t1 == LocationRequirement && t2 == LocationRequirement
    return true
  elseif t1 == MinimumThroughput && t2 == Goal
    return true
  elseif t1 == MinimumThroughput && t2 == MinimumThroughput
    return true
  elseif t1 == SDFPrefix && t2 == Function
    return true
  elseif t1 == SDFPrefix && t2 == SDFPrefix
    return true
  elseif t1 == AbstractStorageComponent && t2 == AbstractPhysicalComponent
    return true
  elseif t1 == AbstractStorageComponent && t2 == AbstractStorageComponent
    return true
  elseif t1 == Instrumented && t2 == Instrumented
    return true
  elseif t1 == AbstractCommunicationComponent && t2 == AbstractPhysicalComponent
    return true
  elseif t1 == AbstractCommunicationComponent && t2 == AbstractCommunicationComponent
    return true
  else
    return false
  end
end

@enum EdgeTrait <: ForSyDeIO.Model.Trait begin
  Output
  AbstractScheduling
  AbstractMapping
  Expansion
  AbstractPhysicalConnection
  AbstractDecision
  Annotation
  Input
end

function refines(t1::EdgeTrait, t2::EdgeTrait)
  if t1 == t2
    return true
  elseif t1 == Output && t2 == Output
    return true
  elseif t1 == AbstractScheduling && t2 == AbstractScheduling
    return true
  elseif t1 == AbstractScheduling && t2 == AbstractDecision
    return true
  elseif t1 == AbstractMapping && t2 == AbstractMapping
    return true
  elseif t1 == AbstractMapping && t2 == AbstractDecision
    return true
  elseif t1 == Expansion && t2 == Expansion
    return true
  elseif t1 == AbstractPhysicalConnection && t2 == AbstractPhysicalConnection
    return true
  elseif t1 == AbstractDecision && t2 == AbstractDecision
    return true
  elseif t1 == Annotation && t2 == Annotation
    return true
  elseif t1 == Input && t2 == Input
    return true
  else
    return false
  end
end

end