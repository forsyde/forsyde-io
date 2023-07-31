# This file has been generated automatically by 'generate.jl'

__precompile__()

module Traits

export VertexTrait, EdgeTrait, refines

@enum VertexTrait begin
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
  ReactorTimer
  WCET
  LabelSignal
  HardRequirement
  Signal
  RoundRobinScheduler
  InstrumentedSignal
  AbstractPhysicalComponent
  AbstractProcessingComponent
  ForSyDeFunction
  ReactorElement
  WCCT
  AbstractGrouping
  TriggeredTask
  Requirement
  InstrumentedFunction
  SDFComb
  AbsractInterfaceComponent
  ReactorActor
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
  if t1 == CustomScheduler
    if t2 == CustomScheduler
      return true
    end
  end
  if t1 == InstrumentedCommunicationInterconnect
    if t2 == InstrumentedCommunicationInterconnect
      return true
    end
    if t2 == AbstractPhysicalComponent
      return true
    end
    if t2 == AbstractCommunicationComponent
      return true
    end
  end
  if t1 == Goal
    if t2 == Goal
      return true
    end
  end
  if t1 == TimeDivisionMultiplexer
    if t2 == TimeDivisionMultiplexer
      return true
    end
    if t2 == AbstractPhysicalComponent
      return true
    end
    if t2 == AbstractCommunicationComponent
      return true
    end
  end
  if t1 == AbstractOrdering
    if t2 == AbstractOrdering
      return true
    end
    if t2 == AbstractGrouping
      return true
    end
  end
  if t1 == FixedPriorityScheduler
    if t2 == FixedPriorityScheduler
      return true
    end
  end
  if t1 == BufferSignal
    if t2 == BufferSignal
      return true
    end
    if t2 == Signal
      return true
    end
  end
  if t1 == ExtraFunctional
    if t2 == ExtraFunctional
      return true
    end
  end
  if t1 == SporadicTask
    if t2 == AbstractOrdering
      return true
    end
    if t2 == SporadicTask
      return true
    end
    if t2 == AbstractGrouping
      return true
    end
  end
  if t1 == TimeTriggeredScheduler
    if t2 == TimeTriggeredScheduler
      return true
    end
    if t2 == AbstractGrouping
      return true
    end
  end
  if t1 == SYComb
    if t2 == SYComb
      return true
    end
    if t2 == ForSyDeFunction
      return true
    end
  end
  if t1 == ReactorTimer
    if t2 == ReactorTimer
      return true
    end
    if t2 == ReactorElement
      return true
    end
  end
  if t1 == WCET
    if t2 == ExtraFunctional
      return true
    end
    if t2 == WCET
      return true
    end
  end
  if t1 == LabelSignal
    if t2 == LabelSignal
      return true
    end
    if t2 == Signal
      return true
    end
  end
  if t1 == HardRequirement
    if t2 == HardRequirement
      return true
    end
    if t2 == Requirement
      return true
    end
  end
  if t1 == Signal
    if t2 == Signal
      return true
    end
  end
  if t1 == RoundRobinScheduler
    if t2 == RoundRobinScheduler
      return true
    end
  end
  if t1 == InstrumentedSignal
    if t2 == Signal
      return true
    end
    if t2 == InstrumentedSignal
      return true
    end
    if t2 == Instrumented
      return true
    end
  end
  if t1 == AbstractPhysicalComponent
    if t2 == AbstractPhysicalComponent
      return true
    end
  end
  if t1 == AbstractProcessingComponent
    if t2 == AbstractPhysicalComponent
      return true
    end
    if t2 == AbstractProcessingComponent
      return true
    end
  end
  if t1 == ForSyDeFunction
    if t2 == ForSyDeFunction
      return true
    end
  end
  if t1 == ReactorElement
    if t2 == ReactorElement
      return true
    end
  end
  if t1 == WCCT
    if t2 == ExtraFunctional
      return true
    end
    if t2 == WCCT
      return true
    end
  end
  if t1 == AbstractGrouping
    if t2 == AbstractGrouping
      return true
    end
  end
  if t1 == TriggeredTask
    if t2 == AbstractOrdering
      return true
    end
    if t2 == AbstractGrouping
      return true
    end
    if t2 == TriggeredTask
      return true
    end
  end
  if t1 == Requirement
    if t2 == Requirement
      return true
    end
  end
  if t1 == InstrumentedFunction
    if t2 == ForSyDeFunction
      return true
    end
    if t2 == InstrumentedFunction
      return true
    end
  end
  if t1 == SDFComb
    if t2 == ForSyDeFunction
      return true
    end
    if t2 == SDFComb
      return true
    end
  end
  if t1 == AbsractInterfaceComponent
    if t2 == AbstractPhysicalComponent
      return true
    end
    if t2 == AbsractInterfaceComponent
      return true
    end
  end
  if t1 == ReactorActor
    if t2 == ForSyDeFunction
      return true
    end
    if t2 == ReactorElement
      return true
    end
    if t2 == ReactorActor
      return true
    end
  end
  if t1 == SYPrefix
    if t2 == ForSyDeFunction
      return true
    end
    if t2 == SYPrefix
      return true
    end
  end
  if t1 == InstrumentedProcessorTile
    if t2 == AbstractPhysicalComponent
      return true
    end
    if t2 == AbstractProcessingComponent
      return true
    end
    if t2 == InstrumentedProcessorTile
      return true
    end
    if t2 == Instrumented
      return true
    end
  end
  if t1 == LocationRequirement
    if t2 == Requirement
      return true
    end
    if t2 == LocationRequirement
      return true
    end
  end
  if t1 == MinimumThroughput
    if t2 == Goal
      return true
    end
    if t2 == MinimumThroughput
      return true
    end
  end
  if t1 == SDFPrefix
    if t2 == ForSyDeFunction
      return true
    end
    if t2 == SDFPrefix
      return true
    end
  end
  if t1 == AbstractStorageComponent
    if t2 == AbstractPhysicalComponent
      return true
    end
    if t2 == AbstractStorageComponent
      return true
    end
  end
  if t1 == Instrumented
    if t2 == Instrumented
      return true
    end
  end
  if t1 == AbstractCommunicationComponent
    if t2 == AbstractPhysicalComponent
      return true
    end
    if t2 == AbstractCommunicationComponent
      return true
    end
  end
  return false
end

@enum EdgeTrait begin
  Output
  Composition
  AbstractScheduling
  AbstractMapping
  AbstractPhysicalConnection
  AbstractDecision
  Annotation
  Input
end

function refines(t1::EdgeTrait, t2::EdgeTrait)
  if t1 == Output
    if t2 == Output
      return true
    end
  end
  if t1 == Composition
    if t2 == Composition
      return true
    end
  end
  if t1 == AbstractScheduling
    if t2 == AbstractScheduling
      return true
    end
    if t2 == AbstractDecision
      return true
    end
  end
  if t1 == AbstractMapping
    if t2 == AbstractMapping
      return true
    end
    if t2 == AbstractDecision
      return true
    end
  end
  if t1 == AbstractPhysicalConnection
    if t2 == AbstractPhysicalConnection
      return true
    end
  end
  if t1 == AbstractDecision
    if t2 == AbstractDecision
      return true
    end
  end
  if t1 == Annotation
    if t2 == Annotation
      return true
    end
  end
  if t1 == Input
    if t2 == Input
      return true
    end
  end
  return false
end


const vertex_trait_lut = Dict(
  "CustomScheduler" => CustomScheduler,
  "InstrumentedCommunicationInterconnect" => InstrumentedCommunicationInterconnect,
  "Goal" => Goal,
  "TimeDivisionMultiplexer" => TimeDivisionMultiplexer,
  "AbstractOrdering" => AbstractOrdering,
  "FixedPriorityScheduler" => FixedPriorityScheduler,
  "BufferSignal" => BufferSignal,
  "ExtraFunctional" => ExtraFunctional,
  "SporadicTask" => SporadicTask,
  "TimeTriggeredScheduler" => TimeTriggeredScheduler,
  "SYComb" => SYComb,
  "ReactorTimer" => ReactorTimer,
  "WCET" => WCET,
  "LabelSignal" => LabelSignal,
  "HardRequirement" => HardRequirement,
  "Signal" => Signal,
  "RoundRobinScheduler" => RoundRobinScheduler,
  "InstrumentedSignal" => InstrumentedSignal,
  "AbstractPhysicalComponent" => AbstractPhysicalComponent,
  "AbstractProcessingComponent" => AbstractProcessingComponent,
  "ForSyDeFunction" => ForSyDeFunction,
  "ReactorElement" => ReactorElement,
  "WCCT" => WCCT,
  "AbstractGrouping" => AbstractGrouping,
  "TriggeredTask" => TriggeredTask,
  "Requirement" => Requirement,
  "InstrumentedFunction" => InstrumentedFunction,
  "SDFComb" => SDFComb,
  "AbsractInterfaceComponent" => AbsractInterfaceComponent,
  "ReactorActor" => ReactorActor,
  "SYPrefix" => SYPrefix,
  "InstrumentedProcessorTile" => InstrumentedProcessorTile,
  "LocationRequirement" => LocationRequirement,
  "MinimumThroughput" => MinimumThroughput,
  "SDFPrefix" => SDFPrefix,
  "AbstractStorageComponent" => AbstractStorageComponent,
  "Instrumented" => Instrumented,
  "AbstractCommunicationComponent" => AbstractCommunicationComponent)


const edge_trait_lut = Dict(
  "Output" => Output,
  "Composition" => Composition,
  "AbstractScheduling" => AbstractScheduling,
  "AbstractMapping" => AbstractMapping,
  "AbstractPhysicalConnection" => AbstractPhysicalConnection,
  "AbstractDecision" => AbstractDecision,
  "Annotation" => Annotation,
  "Input" => Input)

make_trait_vertex(label::AbstractString) = vertex_trait_lut[label]
make_trait_edge(label::AbstractString) = edge_trait_lut[label]
is_trait(label::AbstractString)::Bool = haskey(vertex_trait_lut, label) || haskey(edge_trait_lut, label)
end # module