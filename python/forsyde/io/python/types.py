from enum import Enum
from enum import auto
from typing import Optional
from typing import Mapping
from typing import Sequence

import forsyde.io.python.core as core

class VertexTrait(core.Trait, Enum):
    AbstractGrouping = auto()
    AbstractOrdering = auto()
    TriggeredTask = auto()
    SporadicTask = auto()
    ExtraFunctional = auto()
    WCET = auto()
    WCCT = auto()
    Requirement = auto()
    HardRequirement = auto()
    LocationRequirement = auto()
    Goal = auto()
    MinimumThroughput = auto()
    TimeTriggeredScheduler = auto()
    FixedPriorityScheduler = auto()
    CustomScheduler = auto()
    RoundRobinScheduler = auto()
    ForSyDeFunction = auto()
    InstrumentedFunction = auto()
    Signal = auto()
    BufferSignal = auto()
    LabelSignal = auto()
    Instrumented = auto()
    InstrumentedSignal = auto()
    SYComb = auto()
    SYPrefix = auto()
    SDFComb = auto()
    SDFPrefix = auto()
    ReactorElement = auto()
    ReactorTimer = auto()
    ReactorActor = auto()
    AbstractPhysicalComponent = auto()
    AbstractProcessingComponent = auto()
    InstrumentedProcessorTile = auto()
    AbstractStorageComponent = auto()
    AbsractInterfaceComponent = auto()
    AbstractCommunicationComponent = auto()
    InstrumentedCommunicationInterconnect = auto()
    TimeDivisionMultiplexer = auto()

    @classmethod
    def refines_static(cls, one, other):
        if one is cls.AbstractOrdering and other is cls.AbstractGrouping:
            return True
        if one is cls.TriggeredTask and other is cls.AbstractGrouping:
            return True
        if one is cls.TriggeredTask and other is cls.AbstractOrdering:
            return True
        if one is cls.SporadicTask and other is cls.AbstractGrouping:
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
        if one is cls.MinimumThroughput and other is cls.Goal:
            return True
        if one is cls.TimeTriggeredScheduler and other is cls.AbstractGrouping:
            return True
        if one is cls.InstrumentedFunction and other is cls.ForSyDeFunction:
            return True
        if one is cls.BufferSignal and other is cls.Signal:
            return True
        if one is cls.LabelSignal and other is cls.Signal:
            return True
        if one is cls.InstrumentedSignal and other is cls.Instrumented:
            return True
        if one is cls.InstrumentedSignal and other is cls.Signal:
            return True
        if one is cls.SYComb and other is cls.ForSyDeFunction:
            return True
        if one is cls.SYPrefix and other is cls.ForSyDeFunction:
            return True
        if one is cls.SDFComb and other is cls.ForSyDeFunction:
            return True
        if one is cls.SDFPrefix and other is cls.ForSyDeFunction:
            return True
        if one is cls.ReactorTimer and other is cls.ReactorElement:
            return True
        if one is cls.ReactorActor and other is cls.ForSyDeFunction:
            return True
        if one is cls.ReactorActor and other is cls.ReactorElement:
            return True
        if one is cls.AbstractProcessingComponent and other is cls.AbstractPhysicalComponent:
            return True
        if one is cls.InstrumentedProcessorTile and other is cls.Instrumented:
            return True
        if one is cls.InstrumentedProcessorTile and other is cls.AbstractProcessingComponent:
            return True
        if one is cls.InstrumentedProcessorTile and other is cls.AbstractPhysicalComponent:
            return True
        if one is cls.AbstractStorageComponent and other is cls.AbstractPhysicalComponent:
            return True
        if one is cls.AbsractInterfaceComponent and other is cls.AbstractPhysicalComponent:
            return True
        if one is cls.AbstractCommunicationComponent and other is cls.AbstractPhysicalComponent:
            return True
        if one is cls.InstrumentedCommunicationInterconnect and other is cls.AbstractCommunicationComponent:
            return True
        if one is cls.InstrumentedCommunicationInterconnect and other is cls.AbstractPhysicalComponent:
            return True
        if one is cls.TimeDivisionMultiplexer and other is cls.AbstractCommunicationComponent:
            return True
        if one is cls.TimeDivisionMultiplexer and other is cls.AbstractPhysicalComponent:
            return True
        return False

    def refines(self, o):
        return VertexTrait.refines_static(self, o)

class EdgeTrait(core.Trait, Enum):
    Input = auto()
    Output = auto()
    Annotation = auto()
    Composition = auto()
    AbstractPhysicalConnection = auto()
    AbstractDecision = auto()
    AbstractScheduling = auto()
    AbstractMapping = auto()
    AbstractAllocation = auto()

    @classmethod
    def refines_static(cls, one, other):
        if one is cls.AbstractScheduling and other is cls.AbstractDecision:
            return True
        if one is cls.AbstractMapping and other is cls.AbstractDecision:
            return True
        if one is cls.AbstractAllocation and other is cls.AbstractDecision:
            return True
        return False

    def refines(self, o):
        return EdgeTrait.refines_static(self, o)


class AbstractGrouping(core.Vertex):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class AbstractOrdering(AbstractGrouping):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class TriggeredTask(AbstractOrdering):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class SporadicTask(AbstractOrdering):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class ExtraFunctional(core.Vertex):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class WCET(ExtraFunctional):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""
    @property
    def time(self) -> int:
        return self.properties["time"]
        
        


class WCCT(ExtraFunctional):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""
    @property
    def time(self) -> int:
        return self.properties["time"]
        
        


class Requirement(core.Vertex):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class HardRequirement(Requirement):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class LocationRequirement(Requirement):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class Goal(core.Vertex):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class MinimumThroughput(Goal):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""
    @property
    def apriori_importance(self) -> int:
        return self.properties["apriori_importance"]
        
        


class TimeTriggeredScheduler(AbstractGrouping):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""
    @property
    def trigger_time(self) -> Mapping[int, str]:
        return self.properties["trigger_time"]
        
        


class FixedPriorityScheduler(core.Vertex):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class CustomScheduler(core.Vertex):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class RoundRobinScheduler(core.Vertex):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class ForSyDeFunction(core.Vertex):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class InstrumentedFunction(ForSyDeFunction):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""
    @property
    def max_operations(self) -> Mapping[str, int]:
        return self.properties["max_operations"]
        
        

    @property
    def max_memory_size_in_bytes(self) -> int:
        return self.properties["max_memory_size_in_bytes"]
        
        


class Signal(core.Vertex):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class BufferSignal(Signal):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class LabelSignal(Signal):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class Instrumented(core.Vertex):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""
    @property
    def requires(self) -> Mapping[str, Mapping[str, int]]:
        return self.properties["requires"]
        
        

    @property
    def provides(self) -> Mapping[str, Mapping[str, int]]:
        return self.properties["provides"]
        
        

    @property
    def configurations(self) -> Sequence[str]:
        return self.properties["configurations"]
        
        


class InstrumentedSignal(Signal, Instrumented):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""
    @property
    def max_elem_size_bytes(self) -> int:
        return self.properties["max_elem_size_bytes"]
        
        

    @property
    def max_elem_count(self) -> int:
        return self.properties["max_elem_count"]
        
        


class SYComb(ForSyDeFunction):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class SYPrefix(ForSyDeFunction):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class SDFComb(ForSyDeFunction):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""
    @property
    def consumption(self) -> Mapping[str, int]:
        return self.properties["consumption"]
        
        

    @property
    def production(self) -> Mapping[str, int]:
        return self.properties["production"]
        
        


class SDFPrefix(ForSyDeFunction):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class ReactorElement(core.Vertex):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class ReactorTimer(ReactorElement):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""
    @property
    def period_numerator_per_sec(self) -> int:
        return self.properties["period_numerator_per_sec"]
        
        

    @property
    def period_denominator_per_sec(self) -> int:
        return self.properties["period_denominator_per_sec"]
        
        

    @property
    def offset_numerator_per_sec(self) -> int:
        return self.properties["offset_numerator_per_sec"]
        
        

    @property
    def offset_denominator_per_sec(self) -> int:
        return self.properties["offset_denominator_per_sec"]
        
        


class ReactorActor(ReactorElement, ForSyDeFunction):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class AbstractPhysicalComponent(core.Vertex):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class AbstractProcessingComponent(AbstractPhysicalComponent):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""
    @property
    def can_be_explored(self) -> bool:
        return self.properties["can_be_explored"]
        
        


class InstrumentedProcessorTile(AbstractProcessingComponent, Instrumented):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""
    @property
    def min_frequency_hz(self) -> int:
        return self.properties["min_frequency_hz"]
        
        

    @property
    def max_frequency_hz(self) -> int:
        return self.properties["max_frequency_hz"]
        
        

    @property
    def max_clock_cycles_per_op(self) -> Mapping[str, int]:
        return self.properties["max_clock_cycles_per_op"]
        
        

    @property
    def max_memory_internal_bytes(self) -> int:
        return self.properties["max_memory_internal_bytes"]
        
        


class AbstractStorageComponent(AbstractPhysicalComponent):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class AbsractInterfaceComponent(AbstractPhysicalComponent):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class AbstractCommunicationComponent(AbstractPhysicalComponent):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""

class InstrumentedCommunicationInterconnect(AbstractCommunicationComponent):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""
    @property
    def max_bandwith_bytes_per_sec(self) -> int:
        return self.properties["max_bandwith_bytes_per_sec"]
        
        


class TimeDivisionMultiplexer(AbstractCommunicationComponent):

    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""
    @property
    def slots(self) -> int:
        return self.properties["slots"]
        
        

