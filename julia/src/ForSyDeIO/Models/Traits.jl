# This file has been generated automatically by 'generate.jl'

module Traits

import ForSyDeIO.Models as Models

struct CustomScheduler <: Models.VertexTrait end
struct InstrumentedCommunicationInterconnect <: Models.VertexTrait end
struct Goal <: Models.VertexTrait end
struct TimeDivisionMultiplexer <: Models.VertexTrait end
struct AbstractOrdering <: Models.VertexTrait end
struct FixedPriorityScheduler <: Models.VertexTrait end
struct BufferSignal <: Models.VertexTrait end
struct ExtraFunctional <: Models.VertexTrait end
struct SporadicTask <: Models.VertexTrait end
struct TimeTriggeredScheduler <: Models.VertexTrait end
struct SYComb <: Models.VertexTrait end
struct ReactorTimer <: Models.VertexTrait end
struct WCET <: Models.VertexTrait end
struct LabelSignal <: Models.VertexTrait end
struct HardRequirement <: Models.VertexTrait end
struct Signal <: Models.VertexTrait end
struct RoundRobinScheduler <: Models.VertexTrait end
struct InstrumentedSignal <: Models.VertexTrait end
struct AbstractPhysicalComponent <: Models.VertexTrait end
struct AbstractProcessingComponent <: Models.VertexTrait end
struct ForSyDeFunction <: Models.VertexTrait end
struct ReactorElement <: Models.VertexTrait end
struct WCCT <: Models.VertexTrait end
struct AbstractGrouping <: Models.VertexTrait end
struct TriggeredTask <: Models.VertexTrait end
struct Requirement <: Models.VertexTrait end
struct InstrumentedFunction <: Models.VertexTrait end
struct SDFComb <: Models.VertexTrait end
struct AbsractInterfaceComponent <: Models.VertexTrait end
struct ReactorActor <: Models.VertexTrait end
struct SYPrefix <: Models.VertexTrait end
struct InstrumentedProcessorTile <: Models.VertexTrait end
struct LocationRequirement <: Models.VertexTrait end
struct MinimumThroughput <: Models.VertexTrait end
struct SDFPrefix <: Models.VertexTrait end
struct AbstractStorageComponent <: Models.VertexTrait end
struct Instrumented <: Models.VertexTrait end
struct AbstractCommunicationComponent <: Models.VertexTrait end

refines(t1::CustomScheduler, t2::CustomScheduler) = true

refines(t1::InstrumentedCommunicationInterconnect, t2::InstrumentedCommunicationInterconnect) = true
refines(t1::InstrumentedCommunicationInterconnect, t2::AbstractPhysicalComponent) = true
refines(t1::InstrumentedCommunicationInterconnect, t2::AbstractCommunicationComponent) = true

refines(t1::Goal, t2::Goal) = true

refines(t1::TimeDivisionMultiplexer, t2::TimeDivisionMultiplexer) = true
refines(t1::TimeDivisionMultiplexer, t2::AbstractPhysicalComponent) = true
refines(t1::TimeDivisionMultiplexer, t2::AbstractCommunicationComponent) = true

refines(t1::AbstractOrdering, t2::AbstractOrdering) = true
refines(t1::AbstractOrdering, t2::AbstractGrouping) = true

refines(t1::FixedPriorityScheduler, t2::FixedPriorityScheduler) = true

refines(t1::BufferSignal, t2::BufferSignal) = true
refines(t1::BufferSignal, t2::Signal) = true

refines(t1::ExtraFunctional, t2::ExtraFunctional) = true

refines(t1::SporadicTask, t2::AbstractOrdering) = true
refines(t1::SporadicTask, t2::SporadicTask) = true
refines(t1::SporadicTask, t2::AbstractGrouping) = true

refines(t1::TimeTriggeredScheduler, t2::TimeTriggeredScheduler) = true
refines(t1::TimeTriggeredScheduler, t2::AbstractGrouping) = true

refines(t1::SYComb, t2::SYComb) = true
refines(t1::SYComb, t2::ForSyDeFunction) = true

refines(t1::ReactorTimer, t2::ReactorTimer) = true
refines(t1::ReactorTimer, t2::ReactorElement) = true

refines(t1::WCET, t2::ExtraFunctional) = true
refines(t1::WCET, t2::WCET) = true

refines(t1::LabelSignal, t2::LabelSignal) = true
refines(t1::LabelSignal, t2::Signal) = true

refines(t1::HardRequirement, t2::HardRequirement) = true
refines(t1::HardRequirement, t2::Requirement) = true

refines(t1::Signal, t2::Signal) = true

refines(t1::RoundRobinScheduler, t2::RoundRobinScheduler) = true

refines(t1::InstrumentedSignal, t2::Signal) = true
refines(t1::InstrumentedSignal, t2::InstrumentedSignal) = true
refines(t1::InstrumentedSignal, t2::Instrumented) = true

refines(t1::AbstractPhysicalComponent, t2::AbstractPhysicalComponent) = true

refines(t1::AbstractProcessingComponent, t2::AbstractPhysicalComponent) = true
refines(t1::AbstractProcessingComponent, t2::AbstractProcessingComponent) = true

refines(t1::ForSyDeFunction, t2::ForSyDeFunction) = true

refines(t1::ReactorElement, t2::ReactorElement) = true

refines(t1::WCCT, t2::ExtraFunctional) = true
refines(t1::WCCT, t2::WCCT) = true

refines(t1::AbstractGrouping, t2::AbstractGrouping) = true

refines(t1::TriggeredTask, t2::AbstractOrdering) = true
refines(t1::TriggeredTask, t2::AbstractGrouping) = true
refines(t1::TriggeredTask, t2::TriggeredTask) = true

refines(t1::Requirement, t2::Requirement) = true

refines(t1::InstrumentedFunction, t2::ForSyDeFunction) = true
refines(t1::InstrumentedFunction, t2::InstrumentedFunction) = true

refines(t1::SDFComb, t2::ForSyDeFunction) = true
refines(t1::SDFComb, t2::SDFComb) = true

refines(t1::AbsractInterfaceComponent, t2::AbstractPhysicalComponent) = true
refines(t1::AbsractInterfaceComponent, t2::AbsractInterfaceComponent) = true

refines(t1::ReactorActor, t2::ReactorElement) = true
refines(t1::ReactorActor, t2::ReactorActor) = true

refines(t1::SYPrefix, t2::ForSyDeFunction) = true
refines(t1::SYPrefix, t2::SYPrefix) = true

refines(t1::InstrumentedProcessorTile, t2::AbstractPhysicalComponent) = true
refines(t1::InstrumentedProcessorTile, t2::AbstractProcessingComponent) = true
refines(t1::InstrumentedProcessorTile, t2::InstrumentedProcessorTile) = true
refines(t1::InstrumentedProcessorTile, t2::Instrumented) = true

refines(t1::LocationRequirement, t2::Requirement) = true
refines(t1::LocationRequirement, t2::LocationRequirement) = true

refines(t1::MinimumThroughput, t2::Goal) = true
refines(t1::MinimumThroughput, t2::MinimumThroughput) = true

refines(t1::SDFPrefix, t2::ForSyDeFunction) = true
refines(t1::SDFPrefix, t2::SDFPrefix) = true

refines(t1::AbstractStorageComponent, t2::AbstractPhysicalComponent) = true
refines(t1::AbstractStorageComponent, t2::AbstractStorageComponent) = true

refines(t1::Instrumented, t2::Instrumented) = true

refines(t1::AbstractCommunicationComponent, t2::AbstractPhysicalComponent) = true
refines(t1::AbstractCommunicationComponent, t2::AbstractCommunicationComponent) = true


struct Output <: Models.EdgeTrait end
struct Composition <: Models.EdgeTrait end
struct AbstractScheduling <: Models.EdgeTrait end
struct AbstractMapping <: Models.EdgeTrait end
struct AbstractPhysicalConnection <: Models.EdgeTrait end
struct AbstractDecision <: Models.EdgeTrait end
struct Annotation <: Models.EdgeTrait end
struct Input <: Models.EdgeTrait end

refines(t1::Output, t2::Output) = true

refines(t1::Composition, t2::Composition) = true

refines(t1::AbstractScheduling, t2::AbstractScheduling) = true
refines(t1::AbstractScheduling, t2::AbstractDecision) = true

refines(t1::AbstractMapping, t2::AbstractMapping) = true
refines(t1::AbstractMapping, t2::AbstractDecision) = true

refines(t1::AbstractPhysicalConnection, t2::AbstractPhysicalConnection) = true

refines(t1::AbstractDecision, t2::AbstractDecision) = true

refines(t1::Annotation, t2::Annotation) = true

refines(t1::Input, t2::Input) = true

vertex_trait_lut = Dict(
  "CustomScheduler" => CustomScheduler(),
  "InstrumentedCommunicationInterconnect" => InstrumentedCommunicationInterconnect(),
  "Goal" => Goal(),
  "TimeDivisionMultiplexer" => TimeDivisionMultiplexer(),
  "AbstractOrdering" => AbstractOrdering(),
  "FixedPriorityScheduler" => FixedPriorityScheduler(),
  "BufferSignal" => BufferSignal(),
  "ExtraFunctional" => ExtraFunctional(),
  "SporadicTask" => SporadicTask(),
  "TimeTriggeredScheduler" => TimeTriggeredScheduler(),
  "SYComb" => SYComb(),
  "ReactorTimer" => ReactorTimer(),
  "WCET" => WCET(),
  "LabelSignal" => LabelSignal(),
  "HardRequirement" => HardRequirement(),
  "Signal" => Signal(),
  "RoundRobinScheduler" => RoundRobinScheduler(),
  "InstrumentedSignal" => InstrumentedSignal(),
  "AbstractPhysicalComponent" => AbstractPhysicalComponent(),
  "AbstractProcessingComponent" => AbstractProcessingComponent(),
  "ForSyDeFunction" => ForSyDeFunction(),
  "ReactorElement" => ReactorElement(),
  "WCCT" => WCCT(),
  "AbstractGrouping" => AbstractGrouping(),
  "TriggeredTask" => TriggeredTask(),
  "Requirement" => Requirement(),
  "InstrumentedFunction" => InstrumentedFunction(),
  "SDFComb" => SDFComb(),
  "AbsractInterfaceComponent" => AbsractInterfaceComponent(),
  "ReactorActor" => ReactorActor(),
  "SYPrefix" => SYPrefix(),
  "InstrumentedProcessorTile" => InstrumentedProcessorTile(),
  "LocationRequirement" => LocationRequirement(),
  "MinimumThroughput" => MinimumThroughput(),
  "SDFPrefix" => SDFPrefix(),
  "AbstractStorageComponent" => AbstractStorageComponent(),
  "Instrumented" => Instrumented(),
  "AbstractCommunicationComponent" => AbstractCommunicationComponent())


edge_trait_lut = Dict(
  "Output" => Output(),
  "Composition" => Composition(),
  "AbstractScheduling" => AbstractScheduling(),
  "AbstractMapping" => AbstractMapping(),
  "AbstractPhysicalConnection" => AbstractPhysicalConnection(),
  "AbstractDecision" => AbstractDecision(),
  "Annotation" => Annotation(),
  "Input" => Input())

function make_trait(label::AbstractString)::Union{Nothing, <:Models.Trait}
  global vertex_trait_lut
  global edge_trait_lut
  if haskey(vertex_trait_lut, label)
    return vertex_trait_lut[label]
  elseif haskey(edge_trait_lut, label)
    return edge_trait_lut[label]
  else
    return nothing
  end
end

end # module