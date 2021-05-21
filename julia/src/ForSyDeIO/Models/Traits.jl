module Types

import ForSyDeIO.Models

struct CustomScheduler <: ForSyDeIO.Model.VertexTrait end
struct InstrumentedCommunicationInterconnect <: ForSyDeIO.Model.VertexTrait end
struct Goal <: ForSyDeIO.Model.VertexTrait end
struct TimeDivisionMultiplexer <: ForSyDeIO.Model.VertexTrait end
struct AbstractOrdering <: ForSyDeIO.Model.VertexTrait end
struct FixedPriorityScheduler <: ForSyDeIO.Model.VertexTrait end
struct BufferSignal <: ForSyDeIO.Model.VertexTrait end
struct ExtraFunctional <: ForSyDeIO.Model.VertexTrait end
struct SporadicTask <: ForSyDeIO.Model.VertexTrait end
struct TimeTriggeredScheduler <: ForSyDeIO.Model.VertexTrait end
struct SYComb <: ForSyDeIO.Model.VertexTrait end
struct ReactorTimer <: ForSyDeIO.Model.VertexTrait end
struct WCET <: ForSyDeIO.Model.VertexTrait end
struct LabelSignal <: ForSyDeIO.Model.VertexTrait end
struct HardRequirement <: ForSyDeIO.Model.VertexTrait end
struct Signal <: ForSyDeIO.Model.VertexTrait end
struct RoundRobinScheduler <: ForSyDeIO.Model.VertexTrait end
struct InstrumentedSignal <: ForSyDeIO.Model.VertexTrait end
struct AbstractPhysicalComponent <: ForSyDeIO.Model.VertexTrait end
struct AbstractProcessingComponent <: ForSyDeIO.Model.VertexTrait end
struct ReactorElement <: ForSyDeIO.Model.VertexTrait end
struct WCCT <: ForSyDeIO.Model.VertexTrait end
struct Function <: ForSyDeIO.Model.VertexTrait end
struct AbstractGrouping <: ForSyDeIO.Model.VertexTrait end
struct TriggeredTask <: ForSyDeIO.Model.VertexTrait end
struct Requirement <: ForSyDeIO.Model.VertexTrait end
struct InstrumentedFunction <: ForSyDeIO.Model.VertexTrait end
struct SDFComb <: ForSyDeIO.Model.VertexTrait end
struct AbsractInterfaceComponent <: ForSyDeIO.Model.VertexTrait end
struct ReactorActor <: ForSyDeIO.Model.VertexTrait end
struct SYPrefix <: ForSyDeIO.Model.VertexTrait end
struct InstrumentedProcessorTile <: ForSyDeIO.Model.VertexTrait end
struct LocationRequirement <: ForSyDeIO.Model.VertexTrait end
struct MinimumThroughput <: ForSyDeIO.Model.VertexTrait end
struct SDFPrefix <: ForSyDeIO.Model.VertexTrait end
struct AbstractStorageComponent <: ForSyDeIO.Model.VertexTrait end
struct Instrumented <: ForSyDeIO.Model.VertexTrait end
struct AbstractCommunicationComponent <: ForSyDeIO.Model.VertexTrait end

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
refines(t1::SYComb, t2::Function) = true

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

refines(t1::ReactorElement, t2::ReactorElement) = true

refines(t1::WCCT, t2::ExtraFunctional) = true
refines(t1::WCCT, t2::WCCT) = true

refines(t1::Function, t2::Function) = true

refines(t1::AbstractGrouping, t2::AbstractGrouping) = true

refines(t1::TriggeredTask, t2::AbstractOrdering) = true
refines(t1::TriggeredTask, t2::AbstractGrouping) = true
refines(t1::TriggeredTask, t2::TriggeredTask) = true

refines(t1::Requirement, t2::Requirement) = true

refines(t1::InstrumentedFunction, t2::Function) = true
refines(t1::InstrumentedFunction, t2::InstrumentedFunction) = true

refines(t1::SDFComb, t2::Function) = true
refines(t1::SDFComb, t2::SDFComb) = true

refines(t1::AbsractInterfaceComponent, t2::AbstractPhysicalComponent) = true
refines(t1::AbsractInterfaceComponent, t2::AbsractInterfaceComponent) = true

refines(t1::ReactorActor, t2::ReactorElement) = true
refines(t1::ReactorActor, t2::ReactorActor) = true

refines(t1::SYPrefix, t2::Function) = true
refines(t1::SYPrefix, t2::SYPrefix) = true

refines(t1::InstrumentedProcessorTile, t2::AbstractPhysicalComponent) = true
refines(t1::InstrumentedProcessorTile, t2::AbstractProcessingComponent) = true
refines(t1::InstrumentedProcessorTile, t2::InstrumentedProcessorTile) = true
refines(t1::InstrumentedProcessorTile, t2::Instrumented) = true

refines(t1::LocationRequirement, t2::Requirement) = true
refines(t1::LocationRequirement, t2::LocationRequirement) = true

refines(t1::MinimumThroughput, t2::Goal) = true
refines(t1::MinimumThroughput, t2::MinimumThroughput) = true

refines(t1::SDFPrefix, t2::Function) = true
refines(t1::SDFPrefix, t2::SDFPrefix) = true

refines(t1::AbstractStorageComponent, t2::AbstractPhysicalComponent) = true
refines(t1::AbstractStorageComponent, t2::AbstractStorageComponent) = true

refines(t1::Instrumented, t2::Instrumented) = true

refines(t1::AbstractCommunicationComponent, t2::AbstractPhysicalComponent) = true
refines(t1::AbstractCommunicationComponent, t2::AbstractCommunicationComponent) = true


struct Output <: ForSyDeIO.Model.EdgeTrait end
struct Composition <: ForSyDeIO.Model.EdgeTrait end
struct AbstractScheduling <: ForSyDeIO.Model.EdgeTrait end
struct AbstractMapping <: ForSyDeIO.Model.EdgeTrait end
struct AbstractPhysicalConnection <: ForSyDeIO.Model.EdgeTrait end
struct AbstractDecision <: ForSyDeIO.Model.EdgeTrait end
struct Annotation <: ForSyDeIO.Model.EdgeTrait end
struct Input <: ForSyDeIO.Model.EdgeTrait end

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
