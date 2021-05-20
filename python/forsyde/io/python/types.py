from enum import Enum
from enum import auto

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
        if one is cls.WCET and other is cls.WCET:
            return True
        if one is cls.WCET and other is cls.ExtraFunctional:
            return True
        if one is cls.WCCT and other is cls.WCCT:
            return True
        if one is cls.WCCT and other is cls.ExtraFunctional:
            return True
        if one is cls.Requirement and other is cls.Requirement:
            return True
        if one is cls.HardRequirement and other is cls.HardRequirement:
            return True
        if one is cls.HardRequirement and other is cls.Requirement:
            return True
        if one is cls.LocationRequirement and other is cls.LocationRequirement:
            return True
        if one is cls.LocationRequirement and other is cls.Requirement:
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
        if one is cls.InstrumentedSignal and other is cls.Signal:
            return True
        if one is cls.InstrumentedSignal and other is cls.InstrumentedSignal:
            return True
        if one is cls.SYComb and other is cls.Function:
            return True
        if one is cls.SYComb and other is cls.SYComb:
            return True
        if one is cls.SYPrefix and other is cls.Function:
            return True
        if one is cls.SYPrefix and other is cls.SYPrefix:
            return True
        if one is cls.SDFComb and other is cls.Function:
            return True
        if one is cls.SDFComb and other is cls.SDFComb:
            return True
        if one is cls.SDFPrefix and other is cls.Function:
            return True
        if one is cls.SDFPrefix and other is cls.SDFPrefix:
            return True
        if one is cls.ReactorElement and other is cls.ReactorElement:
            return True
        if one is cls.ReactorTimer and other is cls.ReactorElement:
            return True
        if one is cls.ReactorTimer and other is cls.ReactorTimer:
            return True
        if one is cls.ReactorActor and other is cls.ReactorElement:
            return True
        if one is cls.ReactorActor and other is cls.ReactorActor:
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
        if one is cls.InstrumentedCommunicationInterconnect and other is cls.AbstractPhysicalComponent:
            return True
        if one is cls.InstrumentedCommunicationInterconnect and other is cls.InstrumentedCommunicationInterconnect:
            return True
        if one is cls.TimeDivisionMultiplexer and other is cls.TimeDivisionMultiplexer:
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
        if one is cls.AbstractScheduling and other is cls.AbstractDecision:
            return True
        if one is cls.AbstractScheduling and other is cls.AbstractScheduling:
            return True
        if one is cls.AbstractMapping and other is cls.AbstractMapping:
            return True
        if one is cls.AbstractMapping and other is cls.AbstractDecision:
            return True
        return False

    def refines(self, o):
        return EdgeTrait.refines_static(self, o)