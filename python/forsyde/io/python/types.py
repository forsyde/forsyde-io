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
    Function = auto()
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
        if one is cls.AbstractGrouping and other is cls.AbstractGrouping:
            return True
        if one is cls.AbstractOrdering and other is cls.AbstractOrdering:
            return True
        if one is cls.AbstractOrdering and other is cls.AbstractGrouping:
            return True
        if one is cls.TriggeredTask and other is cls.TriggeredTask:
            return True
        if one is cls.TriggeredTask and other is cls.AbstractOrdering:
            return True
        if one is cls.TriggeredTask and other is cls.AbstractGrouping:
            return True
        if one is cls.SporadicTask and other is cls.AbstractOrdering:
            return True
        if one is cls.SporadicTask and other is cls.SporadicTask:
            return True
        if one is cls.SporadicTask and other is cls.AbstractGrouping:
            return True
        if one is cls.ExtraFunctional and other is cls.ExtraFunctional:
            return True
        if one is cls.WCET and other is cls.ExtraFunctional:
            return True
        if one is cls.WCET and other is cls.WCET:
            return True
        if one is cls.WCCT and other is cls.ExtraFunctional:
            return True
        if one is cls.WCCT and other is cls.WCCT:
            return True
        if one is cls.Requirement and other is cls.Requirement:
            return True
        if one is cls.HardRequirement and other is cls.Requirement:
            return True
        if one is cls.HardRequirement and other is cls.HardRequirement:
            return True
        if one is cls.LocationRequirement and other is cls.Requirement:
            return True
        if one is cls.LocationRequirement and other is cls.LocationRequirement:
            return True
        if one is cls.Goal and other is cls.Goal:
            return True
        if one is cls.MinimumThroughput and other is cls.MinimumThroughput:
            return True
        if one is cls.MinimumThroughput and other is cls.Goal:
            return True
        if one is cls.TimeTriggeredScheduler and other is cls.TimeTriggeredScheduler:
            return True
        if one is cls.TimeTriggeredScheduler and other is cls.AbstractGrouping:
            return True
        if one is cls.FixedPriorityScheduler and other is cls.FixedPriorityScheduler:
            return True
        if one is cls.CustomScheduler and other is cls.CustomScheduler:
            return True
        if one is cls.RoundRobinScheduler and other is cls.RoundRobinScheduler:
            return True
        if one is cls.Function and other is cls.Function:
            return True
        if one is cls.InstrumentedFunction and other is cls.InstrumentedFunction:
            return True
        if one is cls.InstrumentedFunction and other is cls.Function:
            return True
        if one is cls.Signal and other is cls.Signal:
            return True
        if one is cls.BufferSignal and other is cls.BufferSignal:
            return True
        if one is cls.BufferSignal and other is cls.Signal:
            return True
        if one is cls.LabelSignal and other is cls.LabelSignal:
            return True
        if one is cls.LabelSignal and other is cls.Signal:
            return True
        if one is cls.Instrumented and other is cls.Instrumented:
            return True
        if one is cls.InstrumentedSignal and other is cls.Instrumented:
            return True
        if one is cls.InstrumentedSignal and other is cls.InstrumentedSignal:
            return True
        if one is cls.InstrumentedSignal and other is cls.Signal:
            return True
        if one is cls.SYComb and other is cls.SYComb:
            return True
        if one is cls.SYComb and other is cls.Function:
            return True
        if one is cls.SYPrefix and other is cls.Function:
            return True
        if one is cls.SYPrefix and other is cls.SYPrefix:
            return True
        if one is cls.SDFComb and other is cls.SDFComb:
            return True
        if one is cls.SDFComb and other is cls.Function:
            return True
        if one is cls.SDFPrefix and other is cls.SDFPrefix:
            return True
        if one is cls.SDFPrefix and other is cls.Function:
            return True
        if one is cls.ReactorElement and other is cls.ReactorElement:
            return True
        if one is cls.ReactorTimer and other is cls.ReactorTimer:
            return True
        if one is cls.ReactorTimer and other is cls.ReactorElement:
            return True
        if one is cls.ReactorActor and other is cls.ReactorActor:
            return True
        if one is cls.ReactorActor and other is cls.ReactorElement:
            return True
        if one is cls.AbstractPhysicalComponent and other is cls.AbstractPhysicalComponent:
            return True
        if one is cls.AbstractProcessingComponent and other is cls.AbstractProcessingComponent:
            return True
        if one is cls.AbstractProcessingComponent and other is cls.AbstractPhysicalComponent:
            return True
        if one is cls.InstrumentedProcessorTile and other is cls.Instrumented:
            return True
        if one is cls.InstrumentedProcessorTile and other is cls.AbstractProcessingComponent:
            return True
        if one is cls.InstrumentedProcessorTile and other is cls.InstrumentedProcessorTile:
            return True
        if one is cls.InstrumentedProcessorTile and other is cls.AbstractPhysicalComponent:
            return True
        if one is cls.AbstractStorageComponent and other is cls.AbstractStorageComponent:
            return True
        if one is cls.AbstractStorageComponent and other is cls.AbstractPhysicalComponent:
            return True
        if one is cls.AbsractInterfaceComponent and other is cls.AbsractInterfaceComponent:
            return True
        if one is cls.AbsractInterfaceComponent and other is cls.AbstractPhysicalComponent:
            return True
        if one is cls.AbstractCommunicationComponent and other is cls.AbstractCommunicationComponent:
            return True
        if one is cls.AbstractCommunicationComponent and other is cls.AbstractPhysicalComponent:
            return True
        if one is cls.InstrumentedCommunicationInterconnect and other is cls.AbstractCommunicationComponent:
            return True
        if one is cls.InstrumentedCommunicationInterconnect and other is cls.InstrumentedCommunicationInterconnect:
            return True
        if one is cls.InstrumentedCommunicationInterconnect and other is cls.AbstractPhysicalComponent:
            return True
        if one is cls.TimeDivisionMultiplexer and other is cls.AbstractCommunicationComponent:
            return True
        if one is cls.TimeDivisionMultiplexer and other is cls.TimeDivisionMultiplexer:
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

    @classmethod
    def refines_static(cls, one, other):
        if one is cls.Input and other is cls.Input:
            return True
        if one is cls.Output and other is cls.Output:
            return True
        if one is cls.Annotation and other is cls.Annotation:
            return True
        if one is cls.Composition and other is cls.Composition:
            return True
        if one is cls.AbstractPhysicalConnection and other is cls.AbstractPhysicalConnection:
            return True
        if one is cls.AbstractDecision and other is cls.AbstractDecision:
            return True
        if one is cls.AbstractScheduling and other is cls.AbstractScheduling:
            return True
        if one is cls.AbstractScheduling and other is cls.AbstractDecision:
            return True
        if one is cls.AbstractMapping and other is cls.AbstractMapping:
            return True
        if one is cls.AbstractMapping and other is cls.AbstractDecision:
            return True
        return False

    def refines(self, o):
        return EdgeTrait.refines_static(self, o)

class VertexAcessor(object):
    """This class is a method holder for all the possible type-safe acesses
    for the vertexes properties."""
    @classmethod
    def get_time(cls, v: core.Vertex) -> Optional[int]:
        if "time" in v.properties:
            return v.properties["time"]
        else:
            if v.refines(VertexTrait.WCET):
                raise ValueError("Property time should exist in vertex with trait WCET, but does not.")
            if v.refines(VertexTrait.WCCT):
                raise ValueError("Property time should exist in vertex with trait WCCT, but does not.")
            return None

    @classmethod
    def get_apriori_importance(cls, v: core.Vertex) -> Optional[int]:
        if "apriori_importance" in v.properties:
            return v.properties["apriori_importance"]
        else:
            if v.refines(VertexTrait.MinimumThroughput):
                raise ValueError("Property apriori_importance should exist in vertex with trait MinimumThroughput, but does not.")
            return None

    @classmethod
    def get_trigger_time(cls, v: core.Vertex) -> Optional[Mapping[int, str]]:
        if "trigger_time" in v.properties:
            return v.properties["trigger_time"]
        else:
            if v.refines(VertexTrait.TimeTriggeredScheduler):
                raise ValueError("Property trigger_time should exist in vertex with trait TimeTriggeredScheduler, but does not.")
            return None

    @classmethod
    def get_max_operations(cls, v: core.Vertex) -> Optional[Mapping[str, int]]:
        if "max_operations" in v.properties:
            return v.properties["max_operations"]
        else:
            if v.refines(VertexTrait.InstrumentedFunction):
                raise ValueError("Property max_operations should exist in vertex with trait InstrumentedFunction, but does not.")
            return None

    @classmethod
    def get_max_float_operations(cls, v: core.Vertex) -> Optional[int]:
        if "max_float_operations" in v.properties:
            return v.properties["max_float_operations"]
        else:
            if v.refines(VertexTrait.InstrumentedFunction):
                raise ValueError("Property max_float_operations should exist in vertex with trait InstrumentedFunction, but does not.")
            return None

    @classmethod
    def get_max_int_operations(cls, v: core.Vertex) -> Optional[int]:
        if "max_int_operations" in v.properties:
            return v.properties["max_int_operations"]
        else:
            if v.refines(VertexTrait.InstrumentedFunction):
                raise ValueError("Property max_int_operations should exist in vertex with trait InstrumentedFunction, but does not.")
            return None

    @classmethod
    def get_max_boolean_operations(cls, v: core.Vertex) -> Optional[int]:
        if "max_boolean_operations" in v.properties:
            return v.properties["max_boolean_operations"]
        else:
            if v.refines(VertexTrait.InstrumentedFunction):
                raise ValueError("Property max_boolean_operations should exist in vertex with trait InstrumentedFunction, but does not.")
            return None

    @classmethod
    def get_max_memory_size_in_bytes(cls, v: core.Vertex) -> Optional[int]:
        if "max_memory_size_in_bytes" in v.properties:
            return v.properties["max_memory_size_in_bytes"]
        else:
            if v.refines(VertexTrait.InstrumentedFunction):
                raise ValueError("Property max_memory_size_in_bytes should exist in vertex with trait InstrumentedFunction, but does not.")
            return None

    @classmethod
    def get_requires(cls, v: core.Vertex) -> Optional[Mapping[str, Mapping[str, int]]]:
        if "requires" in v.properties:
            return v.properties["requires"]
        else:
            if v.refines(VertexTrait.Instrumented):
                raise ValueError("Property requires should exist in vertex with trait Instrumented, but does not.")
            return None

    @classmethod
    def get_provides(cls, v: core.Vertex) -> Optional[Mapping[str, Mapping[str, int]]]:
        if "provides" in v.properties:
            return v.properties["provides"]
        else:
            if v.refines(VertexTrait.Instrumented):
                raise ValueError("Property provides should exist in vertex with trait Instrumented, but does not.")
            return None

    @classmethod
    def get_configurations(cls, v: core.Vertex) -> Optional[Sequence[str]]:
        if "configurations" in v.properties:
            return v.properties["configurations"]
        else:
            if v.refines(VertexTrait.Instrumented):
                raise ValueError("Property configurations should exist in vertex with trait Instrumented, but does not.")
            return None

    @classmethod
    def get_max_elem_size_bytes(cls, v: core.Vertex) -> Optional[int]:
        if "max_elem_size_bytes" in v.properties:
            return v.properties["max_elem_size_bytes"]
        else:
            if v.refines(VertexTrait.InstrumentedSignal):
                raise ValueError("Property max_elem_size_bytes should exist in vertex with trait InstrumentedSignal, but does not.")
            return None

    @classmethod
    def get_max_elem_count(cls, v: core.Vertex) -> Optional[int]:
        if "max_elem_count" in v.properties:
            return v.properties["max_elem_count"]
        else:
            if v.refines(VertexTrait.InstrumentedSignal):
                raise ValueError("Property max_elem_count should exist in vertex with trait InstrumentedSignal, but does not.")
            return None

    @classmethod
    def get_consumption(cls, v: core.Vertex) -> Optional[Mapping[str, int]]:
        if "consumption" in v.properties:
            return v.properties["consumption"]
        else:
            if v.refines(VertexTrait.SDFComb):
                raise ValueError("Property consumption should exist in vertex with trait SDFComb, but does not.")
            return None

    @classmethod
    def get_production(cls, v: core.Vertex) -> Optional[Mapping[str, int]]:
        if "production" in v.properties:
            return v.properties["production"]
        else:
            if v.refines(VertexTrait.SDFComb):
                raise ValueError("Property production should exist in vertex with trait SDFComb, but does not.")
            return None

    @classmethod
    def get_period_numerator_per_sec(cls, v: core.Vertex) -> Optional[int]:
        if "period_numerator_per_sec" in v.properties:
            return v.properties["period_numerator_per_sec"]
        else:
            if v.refines(VertexTrait.ReactorTimer):
                raise ValueError("Property period_numerator_per_sec should exist in vertex with trait ReactorTimer, but does not.")
            return None

    @classmethod
    def get_period_denominator_per_sec(cls, v: core.Vertex) -> Optional[int]:
        if "period_denominator_per_sec" in v.properties:
            return v.properties["period_denominator_per_sec"]
        else:
            if v.refines(VertexTrait.ReactorTimer):
                raise ValueError("Property period_denominator_per_sec should exist in vertex with trait ReactorTimer, but does not.")
            return None

    @classmethod
    def get_offset_numerator_per_sec(cls, v: core.Vertex) -> Optional[int]:
        if "offset_numerator_per_sec" in v.properties:
            return v.properties["offset_numerator_per_sec"]
        else:
            if v.refines(VertexTrait.ReactorTimer):
                raise ValueError("Property offset_numerator_per_sec should exist in vertex with trait ReactorTimer, but does not.")
            return None

    @classmethod
    def get_offset_denominator_per_sec(cls, v: core.Vertex) -> Optional[int]:
        if "offset_denominator_per_sec" in v.properties:
            return v.properties["offset_denominator_per_sec"]
        else:
            if v.refines(VertexTrait.ReactorTimer):
                raise ValueError("Property offset_denominator_per_sec should exist in vertex with trait ReactorTimer, but does not.")
            return None

    @classmethod
    def get_can_be_explored(cls, v: core.Vertex) -> Optional[bool]:
        if "can_be_explored" in v.properties:
            return v.properties["can_be_explored"]
        else:
            if v.refines(VertexTrait.AbstractProcessingComponent):
                raise ValueError("Property can_be_explored should exist in vertex with trait AbstractProcessingComponent, but does not.")
            return None

    @classmethod
    def get_min_frequency_hz(cls, v: core.Vertex) -> Optional[int]:
        if "min_frequency_hz" in v.properties:
            return v.properties["min_frequency_hz"]
        else:
            if v.refines(VertexTrait.InstrumentedProcessorTile):
                raise ValueError("Property min_frequency_hz should exist in vertex with trait InstrumentedProcessorTile, but does not.")
            return None

    @classmethod
    def get_max_frequency_hz(cls, v: core.Vertex) -> Optional[int]:
        if "max_frequency_hz" in v.properties:
            return v.properties["max_frequency_hz"]
        else:
            if v.refines(VertexTrait.InstrumentedProcessorTile):
                raise ValueError("Property max_frequency_hz should exist in vertex with trait InstrumentedProcessorTile, but does not.")
            return None

    @classmethod
    def get_max_clock_cycles_per_op(cls, v: core.Vertex) -> Optional[Mapping[str, Mapping[str, int]]]:
        if "max_clock_cycles_per_op" in v.properties:
            return v.properties["max_clock_cycles_per_op"]
        else:
            if v.refines(VertexTrait.InstrumentedProcessorTile):
                raise ValueError("Property max_clock_cycles_per_op should exist in vertex with trait InstrumentedProcessorTile, but does not.")
            return None

    @classmethod
    def get_max_memory_internal_bytes(cls, v: core.Vertex) -> Optional[int]:
        if "max_memory_internal_bytes" in v.properties:
            return v.properties["max_memory_internal_bytes"]
        else:
            if v.refines(VertexTrait.InstrumentedProcessorTile):
                raise ValueError("Property max_memory_internal_bytes should exist in vertex with trait InstrumentedProcessorTile, but does not.")
            return None

    @classmethod
    def get_clock_cycles_per_float_op(cls, v: core.Vertex) -> Optional[int]:
        if "clock_cycles_per_float_op" in v.properties:
            return v.properties["clock_cycles_per_float_op"]
        else:
            if v.refines(VertexTrait.InstrumentedProcessorTile):
                raise ValueError("Property clock_cycles_per_float_op should exist in vertex with trait InstrumentedProcessorTile, but does not.")
            return None

    @classmethod
    def get_clock_cycles_per_integer_op(cls, v: core.Vertex) -> Optional[int]:
        if "clock_cycles_per_integer_op" in v.properties:
            return v.properties["clock_cycles_per_integer_op"]
        else:
            if v.refines(VertexTrait.InstrumentedProcessorTile):
                raise ValueError("Property clock_cycles_per_integer_op should exist in vertex with trait InstrumentedProcessorTile, but does not.")
            return None

    @classmethod
    def get_clock_cycles_per_boolean_op(cls, v: core.Vertex) -> Optional[int]:
        if "clock_cycles_per_boolean_op" in v.properties:
            return v.properties["clock_cycles_per_boolean_op"]
        else:
            if v.refines(VertexTrait.InstrumentedProcessorTile):
                raise ValueError("Property clock_cycles_per_boolean_op should exist in vertex with trait InstrumentedProcessorTile, but does not.")
            return None

    @classmethod
    def get_max_bandwith_bytes_per_sec(cls, v: core.Vertex) -> Optional[int]:
        if "max_bandwith_bytes_per_sec" in v.properties:
            return v.properties["max_bandwith_bytes_per_sec"]
        else:
            if v.refines(VertexTrait.InstrumentedCommunicationInterconnect):
                raise ValueError("Property max_bandwith_bytes_per_sec should exist in vertex with trait InstrumentedCommunicationInterconnect, but does not.")
            return None

    @classmethod
    def get_slots(cls, v: core.Vertex) -> Optional[int]:
        if "slots" in v.properties:
            return v.properties["slots"]
        else:
            if v.refines(VertexTrait.TimeDivisionMultiplexer):
                raise ValueError("Property slots should exist in vertex with trait TimeDivisionMultiplexer, but does not.")
            return None
