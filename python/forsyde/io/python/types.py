
from enum import Enum
from enum import auto
from typing import Optional
from typing import Mapping
from typing import Sequence

import forsyde.io.python.core as core

class VertexTrait(core.Trait, enum):
    SpecificationLayer = auto()
    AlgorithmicSkeleton = auto()
    FunctionLayer = auto()
    ModelOfComputation = auto()
    PlatformLayer = auto()
    PlatformAbstraction = auto()
    ProfiledFunction = auto()
    InlineFunction = auto()
    LinguaFrancaElement = auto()
    LinguaFrancaReactor = auto()
    LinguaFrancaReaction = auto()
    LinguaFrancaTimer = auto()
    LinguaFrancaSignal = auto()
    SDFElement = auto()
    SDFComb = auto()
    SDFPrefix = auto()
    SDFSignal = auto()
    AbstractDigitalModule = auto()
    GenericProcessingModule = auto()
    ProfiledProcessingModule = auto()
    GenericDigitalInterconnect = auto()
    RoundRobinInterconnect = auto()
    AbstractStructure = auto()
    GenericDigitalStorage = auto()
    GenericCacheModule = auto()
    GenericMemoryModule = auto()
    AbstractOrdering = auto()
    TriggeredTask = auto()
    SporadicTask = auto()
    ExtraFunctional = auto()
    WCET = auto()
    WCCT = auto()
    Requirement = auto()
    HardRequirement = auto()
    LocationRequirement = auto()
    TimeTriggeredScheduler = auto()
    FixedPriorityScheduler = auto()
    RoundRobinScheduler = auto()
    MapVector = auto()
    ReduceVector = auto()

    @classmethod
    def refines_static(cls, one, other):
        if one is cls.AlgorithmicSkeleton and other is cls.SpecificationLayer:
            return True
        if one is cls.FunctionLayer and other is cls.SpecificationLayer:
            return True
        if one is cls.ModelOfComputation and other is cls.SpecificationLayer:
            return True
        if one is cls.PlatformAbstraction and other is cls.PlatformLayer:
            return True
        if one is cls.ProfiledFunction and other is cls.FunctionLayer:
            return True
        if one is cls.InlineFunction and other is cls.FunctionLayer:
            return True
        if one is cls.LinguaFrancaElement and other is cls.ModelOfComputation:
            return True
        if one is cls.LinguaFrancaReactor and other is cls.LinguaFrancaElement:
            return True
        if one is cls.LinguaFrancaReaction and other is cls.LinguaFrancaElement:
            return True
        if one is cls.LinguaFrancaTimer and other is cls.LinguaFrancaElement:
            return True
        if one is cls.LinguaFrancaSignal and other is cls.LinguaFrancaElement:
            return True
        if one is cls.SDFElement and other is cls.ModelOfComputation:
            return True
        if one is cls.SDFComb and other is cls.SDFElement:
            return True
        if one is cls.SDFPrefix and other is cls.SDFElement:
            return True
        if one is cls.SDFSignal and other is cls.SDFElement:
            return True
        if one is cls.AbstractDigitalModule and other is cls.PlatformLayer:
            return True
        if one is cls.GenericProcessingModule and other is cls.AbstractDigitalModule:
            return True
        if one is cls.ProfiledProcessingModule and other is cls.AbstractDigitalModule:
            return True
        if one is cls.GenericDigitalInterconnect and other is cls.AbstractDigitalModule:
            return True
        if one is cls.RoundRobinInterconnect and other is cls.GenericDigitalInterconnect:
            return True
        if one is cls.AbstractStructure and other is cls.PlatformLayer:
            return True
        if one is cls.GenericDigitalStorage and other is cls.AbstractDigitalModule:
            return True
        if one is cls.GenericCacheModule and other is cls.GenericDigitalStorage:
            return True
        if one is cls.GenericMemoryModule and other is cls.GenericDigitalStorage:
            return True
        if one is cls.AbstractOrdering and other is cls.PlatformAbstraction:
            return True
        if one is cls.TriggeredTask and other is cls.AbstractOrdering:
            return True
        if one is cls.SporadicTask and other is cls.AbstractOrdering:
            return True
        if one is cls.WCET and other is cls.ExtraFunctional:
            return True
        if one is cls.WCCT and other is cls.ExtraFunctional:
            return True
        if one is cls.HardRequirement and other is cls.Requirement:
            return True
        if one is cls.LocationRequirement and other is cls.Requirement:
            return True
        if one is cls.TimeTriggeredScheduler and other is cls.PlatformAbstraction:
            return True
        if one is cls.FixedPriorityScheduler and other is cls.PlatformAbstraction:
            return True
        if one is cls.RoundRobinScheduler and other is cls.PlatformAbstraction:
            return True
        if one is cls.MapVector and other is cls.AlgorithmicSkeleton:
            return True
        if one is cls.ReduceVector and other is cls.AlgorithmicSkeleton:
            return True
        return one == other

    def refines(self, other):
        return VertexTrait.refines_static(self, other)

class EdgeTrait(core.Trait, enum):
    ModelOfComputationEdge = auto()
    PlatformLayerEdge = auto()
    ModelOfImplementationEdge = auto()
    Input = auto()
    Output = auto()
    Annotation = auto()
    AbstractPhysicalConnection = auto()
    AbstractStructuralConnection = auto()
    AbstractDecision = auto()
    AbstractScheduling = auto()
    AbstractMapping = auto()
    AbstractAllocation = auto()
    LinguaFrancaConnection = auto()
    LinguaFrancaTrigger = auto()
    LinguaFrancaContainment = auto()

    @classmethod
    def refines_static(cls, one, other):
        return one == other

    def refines(self, other):
        return EdgeTrait.refines_static(self, other)

class SpecificationLayer(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.SpecificationLayer) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None


class AlgorithmicSkeleton(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.AlgorithmicSkeleton) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None


class FunctionLayer(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.FunctionLayer) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None


class ModelOfComputation(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.ModelOfComputation) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None


class PlatformLayer(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.PlatformLayer) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None


class PlatformAbstraction(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.PlatformAbstraction) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None


class ProfiledFunction(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.ProfiledFunction) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def requirements(self) -> str:
        return self.properties["requirements"]


class InlineFunction(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.InlineFunction) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def source_code(self) -> str:
        return self.properties["source_code"]


class LinguaFrancaElement(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.LinguaFrancaElement) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None


class LinguaFrancaReactor(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.LinguaFrancaReactor) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def state_names(self) -> Sequence[str]:
        return self.properties["state_names"]

    def state_sizes_in_bits(self) -> Sequence[int]:
        return self.properties["state_sizes_in_bits"]

    def get_timers(self, model: core.ForSyDeModel) -> Optional["LinguaFrancaTimer"]:
        return next((LinguaFrancaTimer.safe_cast(n) for n in model[self] if LinguaFrancaTimer.conforms(n)), None)

    def get_reactions(self, model: core.ForSyDeModel) -> Optional["LinguaFrancaReaction"]:
        return next((LinguaFrancaReaction.safe_cast(n) for n in model[self] if LinguaFrancaReaction.conforms(n)), None)

    def get_children_reactors(self, model: core.ForSyDeModel) -> Optional["LinguaFrancaReactor"]:
        return next((LinguaFrancaReactor.safe_cast(n) for n in model[self] if LinguaFrancaReactor.conforms(n)), None)


class LinguaFrancaReaction(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.LinguaFrancaReaction) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def size_in_bits(self) -> int:
        return self.properties["size_in_bits"]

    def get_implementation(self, model: core.ForSyDeModel) -> Optional["InlineFunction"]:
        return next((InlineFunction.safe_cast(n) for n in model[self] if InlineFunction.conforms(n)), None)

    def get_triggers(self, model: core.ForSyDeModel) -> List["LinguaFrancaElement"]:
        return [LinguaFrancaElement.safe_cast(n) for n in model[self] if LinguaFrancaElement.conforms(n)]

    def get_effects(self, model: core.ForSyDeModel) -> List["LinguaFrancaElement"]:
        return [LinguaFrancaElement.safe_cast(n) for n in model[self] if LinguaFrancaElement.conforms(n)]


class LinguaFrancaTimer(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.LinguaFrancaTimer) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def period_numerator_per_sec(self) -> int:
        return self.properties["period_numerator_per_sec"]

    def offset_numerator_per_sec(self) -> int:
        return self.properties["offset_numerator_per_sec"]

    def period_denominator_per_sec(self) -> int:
        return self.properties["period_denominator_per_sec"]

    def offset_denominator_per_sec(self) -> int:
        return self.properties["offset_denominator_per_sec"]


class LinguaFrancaSignal(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.LinguaFrancaSignal) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def size_in_bits(self) -> int:
        return self.properties["size_in_bits"]

    def get_target_reaction(self, model: core.ForSyDeModel) -> Optional["LinguaFrancaReaction"]:
        return next((LinguaFrancaReaction.safe_cast(n) for n in model[self] if LinguaFrancaReaction.conforms(n)), None)

    def get_source_reaction(self, model: core.ForSyDeModel) -> Optional["LinguaFrancaReaction"]:
        return next((LinguaFrancaReaction.safe_cast(n) for n in model[self] if LinguaFrancaReaction.conforms(n)), None)


class SDFElement(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.SDFElement) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None


class SDFComb(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.SDFComb) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def production(self) -> str:
        return self.properties["production"]

    def consumption(self) -> str:
        return self.properties["consumption"]

    def get_combinator(self, model: core.ForSyDeModel) -> Optional["ModelOfComputation"]:
        return next((ModelOfComputation.safe_cast(n) for n in model[self] if ModelOfComputation.conforms(n)), None)


class SDFPrefix(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.SDFPrefix) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def get_prefixer(self, model: core.ForSyDeModel) -> Optional["ModelOfComputation"]:
        return next((ModelOfComputation.safe_cast(n) for n in model[self] if ModelOfComputation.conforms(n)), None)


class SDFSignal(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.SDFSignal) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def token_size_in_bits(self) -> int:
        return self.properties["token_size_in_bits"]


class AbstractDigitalModule(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.AbstractDigitalModule) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def nominal_frequency_in_hertz(self) -> int:
        return self.properties["nominal_frequency_in_hertz"]


class GenericProcessingModule(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.GenericProcessingModule) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def can_be_explored(self) -> bool:
        return self.properties["can_be_explored"]


class ProfiledProcessingModule(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.ProfiledProcessingModule) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def provisions(self) -> str:
        return self.properties["provisions"]


class GenericDigitalInterconnect(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.GenericDigitalInterconnect) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def max_concurrent_flits(self) -> int:
        return self.properties["max_concurrent_flits"]

    def max_flit_size_in_bits(self) -> int:
        return self.properties["max_flit_size_in_bits"]


class RoundRobinInterconnect(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.RoundRobinInterconnect) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def total_weights(self) -> int:
        return self.properties["total_weights"]

    def allocated_weights(self) -> str:
        return self.properties["allocated_weights"]


class AbstractStructure(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.AbstractStructure) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def get_submodules(self, model: core.ForSyDeModel) -> Optional["PlatformLayer"]:
        return next((PlatformLayer.safe_cast(n) for n in model[self] if PlatformLayer.conforms(n)), None)


class GenericDigitalStorage(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.GenericDigitalStorage) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None


class GenericCacheModule(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.GenericCacheModule) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None


class GenericMemoryModule(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.GenericMemoryModule) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def max_memory_in_bits(self) -> int:
        return self.properties["max_memory_in_bits"]


class AbstractOrdering(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.AbstractOrdering) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None


class TriggeredTask(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.TriggeredTask) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None


class SporadicTask(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.SporadicTask) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None


class ExtraFunctional(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.ExtraFunctional) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None


class WCET(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.WCET) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def time(self) -> int:
        return self.properties["time"]

    def get_functionality(self, model: core.ForSyDeModel) -> List["ModelOfComputation"]:
        return [ModelOfComputation.safe_cast(n) for n in model[self] if ModelOfComputation.conforms(n)]

    def get_module(self, model: core.ForSyDeModel) -> List["PlatformLayer"]:
        return [PlatformLayer.safe_cast(n) for n in model[self] if PlatformLayer.conforms(n)]


class WCCT(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.WCCT) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def time(self) -> int:
        return self.properties["time"]

    def get_signal(self, model: core.ForSyDeModel) -> List["ModelOfComputation"]:
        return [ModelOfComputation.safe_cast(n) for n in model[self] if ModelOfComputation.conforms(n)]

    def get_module(self, model: core.ForSyDeModel) -> List["PlatformLayer"]:
        return [PlatformLayer.safe_cast(n) for n in model[self] if PlatformLayer.conforms(n)]


class Requirement(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.Requirement) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None


class HardRequirement(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.HardRequirement) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None


class LocationRequirement(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.LocationRequirement) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def get_functionality(self, model: core.ForSyDeModel) -> List["ModelOfComputation"]:
        return [ModelOfComputation.safe_cast(n) for n in model[self] if ModelOfComputation.conforms(n)]

    def get_module(self, model: core.ForSyDeModel) -> List["PlatformLayer"]:
        return [PlatformLayer.safe_cast(n) for n in model[self] if PlatformLayer.conforms(n)]


class TimeTriggeredScheduler(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.TimeTriggeredScheduler) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def trigger_time(self) -> str:
        return self.properties["trigger_time"]


class FixedPriorityScheduler(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.FixedPriorityScheduler) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def priorities_assignment(self) -> str:
        return self.properties["priorities_assignment"]

    def maximum_number_of_priorities(self) -> int:
        return self.properties["maximum_number_of_priorities"]

    def preemptive(self) -> bool:
        return self.properties["preemptive"]


class RoundRobinScheduler(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.RoundRobinScheduler) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def allocated_cycles(self) -> str:
        return self.properties["allocated_cycles"]

    def maximum_time_slice_in_cycles(self) -> int:
        return self.properties["maximum_time_slice_in_cycles"]

    def hyper_round_cycles(self) -> int:
        return self.properties["hyper_round_cycles"]

    def maximum_overhead_in_cycles(self) -> int:
        return self.properties["maximum_overhead_in_cycles"]

    def minimum_time_slice_in_cycles(self) -> int:
        return self.properties["minimum_time_slice_in_cycles"]


class MapVector(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.MapVector) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def maximum_invocations(self) -> int:
        return self.properties["maximum_invocations"]

    def get_mapped(self, model: core.ForSyDeModel) -> Optional["SpecificationLayer"]:
        return next((SpecificationLayer.safe_cast(n) for n in model[self] if SpecificationLayer.conforms(n)), None)


class ReduceVector(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.ReduceVector) for t in vertex.vertex_traits)

    @classmethod
    def safe_cast(cls, vertex):
        return cls(viewed_vertex=vertex) if cls.conforms(vertex) else None

    def get_reduced(self, model: core.ForSyDeModel) -> Optional["SpecificationLayer"]:
        return next((SpecificationLayer.safe_cast(n) for n in model[self] if SpecificationLayer.conforms(n)), None)

class ModelOfComputationEdge(core.EdgeViewer):
    @classmethod
    def conforms(cls, edge):
        return any(t.refines(EdgeTrait.ModelOfComputationEdge) for t in edge.edge_traits)

    @classmethod
    def safe_cast(cls, edge):
        return cls(viewed_edge=edge) if cls.conforms(edge) else None


class PlatformLayerEdge(core.EdgeViewer):
    @classmethod
    def conforms(cls, edge):
        return any(t.refines(EdgeTrait.PlatformLayerEdge) for t in edge.edge_traits)

    @classmethod
    def safe_cast(cls, edge):
        return cls(viewed_edge=edge) if cls.conforms(edge) else None


class ModelOfImplementationEdge(core.EdgeViewer):
    @classmethod
    def conforms(cls, edge):
        return any(t.refines(EdgeTrait.ModelOfImplementationEdge) for t in edge.edge_traits)

    @classmethod
    def safe_cast(cls, edge):
        return cls(viewed_edge=edge) if cls.conforms(edge) else None


class Input(core.EdgeViewer):
    @classmethod
    def conforms(cls, edge):
        return any(t.refines(EdgeTrait.Input) for t in edge.edge_traits)

    @classmethod
    def safe_cast(cls, edge):
        return cls(viewed_edge=edge) if cls.conforms(edge) else None


class Output(core.EdgeViewer):
    @classmethod
    def conforms(cls, edge):
        return any(t.refines(EdgeTrait.Output) for t in edge.edge_traits)

    @classmethod
    def safe_cast(cls, edge):
        return cls(viewed_edge=edge) if cls.conforms(edge) else None


class Annotation(core.EdgeViewer):
    @classmethod
    def conforms(cls, edge):
        return any(t.refines(EdgeTrait.Annotation) for t in edge.edge_traits)

    @classmethod
    def safe_cast(cls, edge):
        return cls(viewed_edge=edge) if cls.conforms(edge) else None


class AbstractPhysicalConnection(core.EdgeViewer):
    @classmethod
    def conforms(cls, edge):
        return any(t.refines(EdgeTrait.AbstractPhysicalConnection) for t in edge.edge_traits)

    @classmethod
    def safe_cast(cls, edge):
        return cls(viewed_edge=edge) if cls.conforms(edge) else None


class AbstractStructuralConnection(core.EdgeViewer):
    @classmethod
    def conforms(cls, edge):
        return any(t.refines(EdgeTrait.AbstractStructuralConnection) for t in edge.edge_traits)

    @classmethod
    def safe_cast(cls, edge):
        return cls(viewed_edge=edge) if cls.conforms(edge) else None


class AbstractDecision(core.EdgeViewer):
    @classmethod
    def conforms(cls, edge):
        return any(t.refines(EdgeTrait.AbstractDecision) for t in edge.edge_traits)

    @classmethod
    def safe_cast(cls, edge):
        return cls(viewed_edge=edge) if cls.conforms(edge) else None


class AbstractScheduling(core.EdgeViewer):
    @classmethod
    def conforms(cls, edge):
        return any(t.refines(EdgeTrait.AbstractScheduling) for t in edge.edge_traits)

    @classmethod
    def safe_cast(cls, edge):
        return cls(viewed_edge=edge) if cls.conforms(edge) else None


class AbstractMapping(core.EdgeViewer):
    @classmethod
    def conforms(cls, edge):
        return any(t.refines(EdgeTrait.AbstractMapping) for t in edge.edge_traits)

    @classmethod
    def safe_cast(cls, edge):
        return cls(viewed_edge=edge) if cls.conforms(edge) else None


class AbstractAllocation(core.EdgeViewer):
    @classmethod
    def conforms(cls, edge):
        return any(t.refines(EdgeTrait.AbstractAllocation) for t in edge.edge_traits)

    @classmethod
    def safe_cast(cls, edge):
        return cls(viewed_edge=edge) if cls.conforms(edge) else None


class LinguaFrancaConnection(core.EdgeViewer):
    @classmethod
    def conforms(cls, edge):
        return any(t.refines(EdgeTrait.LinguaFrancaConnection) for t in edge.edge_traits)

    @classmethod
    def safe_cast(cls, edge):
        return cls(viewed_edge=edge) if cls.conforms(edge) else None


class LinguaFrancaTrigger(core.EdgeViewer):
    @classmethod
    def conforms(cls, edge):
        return any(t.refines(EdgeTrait.LinguaFrancaTrigger) for t in edge.edge_traits)

    @classmethod
    def safe_cast(cls, edge):
        return cls(viewed_edge=edge) if cls.conforms(edge) else None


class LinguaFrancaContainment(core.EdgeViewer):
    @classmethod
    def conforms(cls, edge):
        return any(t.refines(EdgeTrait.LinguaFrancaContainment) for t in edge.edge_traits)

    @classmethod
    def safe_cast(cls, edge):
        return cls(viewed_edge=edge) if cls.conforms(edge) else None

