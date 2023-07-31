
from enum import Enum
from enum import auto
from typing import Optional
from typing import Mapping
from typing import Sequence

import forsyde.io.python.core as core

class VertexTrait(core.Trait, Enum):
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

    def __str__(self):
        return self.name

    def refines(self, other):
        return VertexTrait.refines_static(self, other)

class EdgeTrait(core.Trait, Enum):
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

    def __str__(self):
        return self.name

    def refines(self, other):
        return EdgeTrait.refines_static(self, other)

class SpecificationLayer(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.SpecificationLayer) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "SpecificationLayer" + self.viewed_vertex.identifier


class AlgorithmicSkeleton(SpecificationLayer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.AlgorithmicSkeleton) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "AlgorithmicSkeleton" + self.viewed_vertex.identifier


class FunctionLayer(SpecificationLayer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.FunctionLayer) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "FunctionLayer" + self.viewed_vertex.identifier


class ModelOfComputation(SpecificationLayer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.ModelOfComputation) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "ModelOfComputation" + self.viewed_vertex.identifier


class PlatformLayer(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.PlatformLayer) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "PlatformLayer" + self.viewed_vertex.identifier


class PlatformAbstraction(PlatformLayer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.PlatformAbstraction) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "PlatformAbstraction" + self.viewed_vertex.identifier


class ProfiledFunction(FunctionLayer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.ProfiledFunction) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "ProfiledFunction" + self.viewed_vertex.identifier

    @property
    def requirements(self) -> Mapping[str, Mapping[str, int]]:
        return self.viewed_vertex.properties["requirements"]


class InlineFunction(FunctionLayer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.InlineFunction) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "InlineFunction" + self.viewed_vertex.identifier

    @property
    def source_code(self) -> str:
        return self.viewed_vertex.properties["source_code"]


class LinguaFrancaElement(ModelOfComputation):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.LinguaFrancaElement) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "LinguaFrancaElement" + self.viewed_vertex.identifier


class LinguaFrancaReactor(LinguaFrancaElement):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.LinguaFrancaReactor) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "LinguaFrancaReactor" + self.viewed_vertex.identifier

    @property
    def state_sizes_in_bits(self) -> Sequence[int]:
        return self.viewed_vertex.properties["state_sizes_in_bits"]

    @property
    def state_names(self) -> Sequence[str]:
        return self.viewed_vertex.properties["state_names"]

    def get_children_reactors(self, model: core.ForSyDeModel) -> Sequence["LinguaFrancaReactor"]:
        return sorted(
            [LinguaFrancaReactor.safe_cast(n) for n in model[self.viewed_vertex] if LinguaFrancaReactor.conforms(n)],
            key = lambda v: int(self.viewed_vertex.properties["__children_reactors_ordering__"][v.viewed_vertex.identifier])
        )

    def get_timers(self, model: core.ForSyDeModel) -> Sequence["LinguaFrancaTimer"]:
        return [LinguaFrancaTimer.safe_cast(n) for n in model[self.viewed_vertex] if LinguaFrancaTimer.conforms(n)]

    def get_reactions(self, model: core.ForSyDeModel) -> Sequence["LinguaFrancaReaction"]:
        return sorted(
            [LinguaFrancaReaction.safe_cast(n) for n in model[self.viewed_vertex] if LinguaFrancaReaction.conforms(n)],
            key = lambda v: int(self.viewed_vertex.properties["__reactions_ordering__"][v.viewed_vertex.identifier])
        )


class LinguaFrancaReaction(LinguaFrancaElement):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.LinguaFrancaReaction) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "LinguaFrancaReaction" + self.viewed_vertex.identifier

    @property
    def size_in_bits(self) -> int:
        return self.viewed_vertex.properties["size_in_bits"]

    def get_implementation(self, model: core.ForSyDeModel) -> Optional["InlineFunction"]:
        return next((InlineFunction.safe_cast(n) for n in model[self.viewed_vertex] if InlineFunction.conforms(n)), None)

    def get_triggers(self, model: core.ForSyDeModel) -> Sequence["LinguaFrancaElement"]:
        return [LinguaFrancaElement.safe_cast(n) for n in model[self.viewed_vertex] if LinguaFrancaElement.conforms(n)]

    def get_effects(self, model: core.ForSyDeModel) -> Sequence["LinguaFrancaElement"]:
        return [LinguaFrancaElement.safe_cast(n) for n in model[self.viewed_vertex] if LinguaFrancaElement.conforms(n)]


class LinguaFrancaTimer(LinguaFrancaElement):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.LinguaFrancaTimer) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "LinguaFrancaTimer" + self.viewed_vertex.identifier

    @property
    def offset_numerator_per_sec(self) -> int:
        return self.viewed_vertex.properties["offset_numerator_per_sec"]

    @property
    def period_numerator_per_sec(self) -> int:
        return self.viewed_vertex.properties["period_numerator_per_sec"]

    @property
    def offset_denominator_per_sec(self) -> int:
        return self.viewed_vertex.properties["offset_denominator_per_sec"]

    @property
    def period_denominator_per_sec(self) -> int:
        return self.viewed_vertex.properties["period_denominator_per_sec"]


class LinguaFrancaSignal(LinguaFrancaElement):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.LinguaFrancaSignal) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "LinguaFrancaSignal" + self.viewed_vertex.identifier

    @property
    def propagation_delay_in_secs_numerator(self) -> int:
        return self.viewed_vertex.properties["propagation_delay_in_secs_numerator"]

    @property
    def size_in_bits(self) -> int:
        return self.viewed_vertex.properties["size_in_bits"]

    @property
    def propagation_delay_in_secs_denominator(self) -> int:
        return self.viewed_vertex.properties["propagation_delay_in_secs_denominator"]

    def get_source_reaction(self, model: core.ForSyDeModel) -> Optional["LinguaFrancaReaction"]:
        return next((LinguaFrancaReaction.safe_cast(n) for n in model[self.viewed_vertex] if LinguaFrancaReaction.conforms(n)), None)

    def get_target_reaction(self, model: core.ForSyDeModel) -> Optional["LinguaFrancaReaction"]:
        return next((LinguaFrancaReaction.safe_cast(n) for n in model[self.viewed_vertex] if LinguaFrancaReaction.conforms(n)), None)


class SDFElement(ModelOfComputation):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.SDFElement) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "SDFElement" + self.viewed_vertex.identifier


class SDFComb(SDFElement):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.SDFComb) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "SDFComb" + self.viewed_vertex.identifier

    @property
    def consumption(self) -> Mapping[str, int]:
        return self.viewed_vertex.properties["consumption"]

    @property
    def production(self) -> Mapping[str, int]:
        return self.viewed_vertex.properties["production"]

    def get_combinator(self, model: core.ForSyDeModel) -> Optional["ModelOfComputation"]:
        return next((ModelOfComputation.safe_cast(n) for n in model[self.viewed_vertex] if ModelOfComputation.conforms(n)), None)


class SDFPrefix(SDFElement):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.SDFPrefix) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "SDFPrefix" + self.viewed_vertex.identifier

    def get_prefixer(self, model: core.ForSyDeModel) -> Optional["ModelOfComputation"]:
        return next((ModelOfComputation.safe_cast(n) for n in model[self.viewed_vertex] if ModelOfComputation.conforms(n)), None)


class SDFSignal(SDFElement):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.SDFSignal) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "SDFSignal" + self.viewed_vertex.identifier

    @property
    def token_size_in_bits(self) -> int:
        return self.viewed_vertex.properties["token_size_in_bits"]


class AbstractDigitalModule(PlatformLayer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.AbstractDigitalModule) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "AbstractDigitalModule" + self.viewed_vertex.identifier

    @property
    def nominal_frequency_in_hertz(self) -> int:
        return self.viewed_vertex.properties["nominal_frequency_in_hertz"]


class GenericProcessingModule(AbstractDigitalModule):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.GenericProcessingModule) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "GenericProcessingModule" + self.viewed_vertex.identifier

    @property
    def can_be_explored(self) -> bool:
        return self.viewed_vertex.properties["can_be_explored"]


class ProfiledProcessingModule(AbstractDigitalModule):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.ProfiledProcessingModule) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "ProfiledProcessingModule" + self.viewed_vertex.identifier

    @property
    def provisions(self) -> Mapping[str, Mapping[str, int]]:
        return self.viewed_vertex.properties["provisions"]


class GenericDigitalInterconnect(AbstractDigitalModule):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.GenericDigitalInterconnect) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "GenericDigitalInterconnect" + self.viewed_vertex.identifier

    @property
    def max_flit_size_in_bits(self) -> int:
        return self.viewed_vertex.properties["max_flit_size_in_bits"]

    @property
    def max_concurrent_flits(self) -> int:
        return self.viewed_vertex.properties["max_concurrent_flits"]


class RoundRobinInterconnect(GenericDigitalInterconnect):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.RoundRobinInterconnect) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "RoundRobinInterconnect" + self.viewed_vertex.identifier

    @property
    def total_weights(self) -> int:
        return self.viewed_vertex.properties["total_weights"]

    @property
    def allocated_weights(self) -> Mapping[str, int]:
        return self.viewed_vertex.properties["allocated_weights"]


class AbstractStructure(PlatformLayer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.AbstractStructure) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "AbstractStructure" + self.viewed_vertex.identifier

    def get_submodules(self, model: core.ForSyDeModel) -> Sequence["PlatformLayer"]:
        return [PlatformLayer.safe_cast(n) for n in model[self.viewed_vertex] if PlatformLayer.conforms(n)]


class GenericDigitalStorage(AbstractDigitalModule):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.GenericDigitalStorage) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "GenericDigitalStorage" + self.viewed_vertex.identifier


class GenericCacheModule(GenericDigitalStorage):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.GenericCacheModule) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "GenericCacheModule" + self.viewed_vertex.identifier


class GenericMemoryModule(GenericDigitalStorage):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.GenericMemoryModule) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "GenericMemoryModule" + self.viewed_vertex.identifier

    @property
    def max_memory_in_bits(self) -> int:
        return self.viewed_vertex.properties["max_memory_in_bits"]


class AbstractOrdering(PlatformAbstraction):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.AbstractOrdering) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "AbstractOrdering" + self.viewed_vertex.identifier


class TriggeredTask(AbstractOrdering):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.TriggeredTask) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "TriggeredTask" + self.viewed_vertex.identifier


class SporadicTask(AbstractOrdering):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.SporadicTask) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "SporadicTask" + self.viewed_vertex.identifier


class ExtraFunctional(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.ExtraFunctional) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "ExtraFunctional" + self.viewed_vertex.identifier


class WCET(ExtraFunctional):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.WCET) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "WCET" + self.viewed_vertex.identifier

    @property
    def time(self) -> int:
        return self.viewed_vertex.properties["time"]

    def get_functionality(self, model: core.ForSyDeModel) -> Sequence["ModelOfComputation"]:
        return [ModelOfComputation.safe_cast(n) for n in model[self.viewed_vertex] if ModelOfComputation.conforms(n)]

    def get_module(self, model: core.ForSyDeModel) -> Sequence["PlatformLayer"]:
        return [PlatformLayer.safe_cast(n) for n in model[self.viewed_vertex] if PlatformLayer.conforms(n)]


class WCCT(ExtraFunctional):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.WCCT) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "WCCT" + self.viewed_vertex.identifier

    @property
    def time(self) -> int:
        return self.viewed_vertex.properties["time"]

    def get_module(self, model: core.ForSyDeModel) -> Sequence["PlatformLayer"]:
        return [PlatformLayer.safe_cast(n) for n in model[self.viewed_vertex] if PlatformLayer.conforms(n)]

    def get_signal(self, model: core.ForSyDeModel) -> Sequence["ModelOfComputation"]:
        return [ModelOfComputation.safe_cast(n) for n in model[self.viewed_vertex] if ModelOfComputation.conforms(n)]


class Requirement(core.VertexViewer):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.Requirement) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "Requirement" + self.viewed_vertex.identifier


class HardRequirement(Requirement):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.HardRequirement) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "HardRequirement" + self.viewed_vertex.identifier


class LocationRequirement(Requirement):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.LocationRequirement) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "LocationRequirement" + self.viewed_vertex.identifier

    def get_functionality(self, model: core.ForSyDeModel) -> Sequence["ModelOfComputation"]:
        return [ModelOfComputation.safe_cast(n) for n in model[self.viewed_vertex] if ModelOfComputation.conforms(n)]

    def get_module(self, model: core.ForSyDeModel) -> Sequence["PlatformLayer"]:
        return [PlatformLayer.safe_cast(n) for n in model[self.viewed_vertex] if PlatformLayer.conforms(n)]


class TimeTriggeredScheduler(PlatformAbstraction):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.TimeTriggeredScheduler) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "TimeTriggeredScheduler" + self.viewed_vertex.identifier

    @property
    def trigger_time(self) -> Mapping[int, str]:
        return self.viewed_vertex.properties["trigger_time"]


class FixedPriorityScheduler(PlatformAbstraction):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.FixedPriorityScheduler) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "FixedPriorityScheduler" + self.viewed_vertex.identifier

    @property
    def maximum_number_of_priorities(self) -> int:
        return self.viewed_vertex.properties["maximum_number_of_priorities"]

    @property
    def priorities_assignment(self) -> Mapping[int, str]:
        return self.viewed_vertex.properties["priorities_assignment"]

    @property
    def preemptive(self) -> bool:
        return self.viewed_vertex.properties["preemptive"]


class RoundRobinScheduler(PlatformAbstraction):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.RoundRobinScheduler) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "RoundRobinScheduler" + self.viewed_vertex.identifier

    @property
    def hyper_round_cycles(self) -> int:
        return self.viewed_vertex.properties["hyper_round_cycles"]

    @property
    def maximum_time_slice_in_cycles(self) -> int:
        return self.viewed_vertex.properties["maximum_time_slice_in_cycles"]

    @property
    def minimum_time_slice_in_cycles(self) -> int:
        return self.viewed_vertex.properties["minimum_time_slice_in_cycles"]

    @property
    def allocated_cycles(self) -> Mapping[str, int]:
        return self.viewed_vertex.properties["allocated_cycles"]

    @property
    def maximum_overhead_in_cycles(self) -> int:
        return self.viewed_vertex.properties["maximum_overhead_in_cycles"]


class MapVector(AlgorithmicSkeleton):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.MapVector) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "MapVector" + self.viewed_vertex.identifier

    @property
    def maximum_invocations(self) -> int:
        return self.viewed_vertex.properties["maximum_invocations"]

    def get_mapped(self, model: core.ForSyDeModel) -> Optional["SpecificationLayer"]:
        return next((SpecificationLayer.safe_cast(n) for n in model[self.viewed_vertex] if SpecificationLayer.conforms(n)), None)


class ReduceVector(AlgorithmicSkeleton):
    @classmethod
    def conforms(cls, vertex):
        return any(t.refines(VertexTrait.ReduceVector) for t in vertex.vertex_traits)

    @property
    def identifier(self) -> str:
        return "ReduceVector" + self.viewed_vertex.identifier

    def get_reduced(self, model: core.ForSyDeModel) -> Optional["SpecificationLayer"]:
        return next((SpecificationLayer.safe_cast(n) for n in model[self.viewed_vertex] if SpecificationLayer.conforms(n)), None)

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

