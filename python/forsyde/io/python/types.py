from typing import Dict
from typing import List
from typing import Sequence
from enum import Enum
from enum import auto

import forsyde.io.python.core as core


class VertexTrait(Enum, core.VertexTrait):
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
    AbstractPhysicalComponent = auto()
    AbstractProcessingComponent = auto()
    InstrumentedProcessorTile = auto()
    AbstractStorageComponent = auto()
    AbsractInterfaceComponent = auto()
    AbstractCommunicationComponent = auto()
    InstrumentedCommunicationInterconnect = auto()
    TimeDivisionMultiplexer = auto()


_traits_vertex = [
    (VertexTrait.AbstractOrdering, VertexTrait.AbstractGrouping),
    (VertexTrait.TriggeredTask, VertexTrait.AbstractOrdering),
    (VertexTrait.SporadicTask, VertexTrait.AbstractOrdering),
    (VertexTrait.WCET, VertexTrait.ExtraFunctional),
    (VertexTrait.WCCT, VertexTrait.ExtraFunctional),
    (VertexTrait.HardRequirement, VertexTrait.Requirement),
    (VertexTrait.LocationRequirement, VertexTrait.Requirement),
    (VertexTrait.MinimumThroughput, VertexTrait.Goal),
    (VertexTrait.TimeTriggeredScheduler, VertexTrait.AbstractGrouping),
    (VertexTrait.InstrumentedFunction, VertexTrait.Function),
    (VertexTrait.BufferSignal, VertexTrait.Signal),
    (VertexTrait.LabelSignal, VertexTrait.Signal),
    (VertexTrait.InstrumentedSignal, VertexTrait.Signal),
    (VertexTrait.InstrumentedSignal, VertexTrait.Instrumented),
    (VertexTrait.SYComb, VertexTrait.Function),
    (VertexTrait.SYPrefix, VertexTrait.Function),
    (VertexTrait.SDFComb, VertexTrait.Function),
    (VertexTrait.SDFPrefix, VertexTrait.Function),
    (VertexTrait.AbstractProcessingComponent, VertexTrait.AbstractPhysicalComponent),
    (VertexTrait.InstrumentedProcessorTile, VertexTrait.AbstractProcessingComponent),
    (VertexTrait.InstrumentedProcessorTile, VertexTrait.Instrumented),
    (VertexTrait.AbstractStorageComponent, VertexTrait.AbstractPhysicalComponent),
    (VertexTrait.AbsractInterfaceComponent, VertexTrait.AbstractPhysicalComponent),
    (VertexTrait.AbstractCommunicationComponent, VertexTrait.AbstractPhysicalComponent),
    (VertexTrait.InstrumentedCommunicationInterconnect, VertexTrait.AbstractCommunicationComponent),
    (VertexTrait.TimeDivisionMultiplexer, VertexTrait.AbstractCommunicationComponent),
]

def issupertrait_vertex(t1: VertexTrait, t2: VertexTrait) -> bool:
    if t1 is t2:
        return True
    else:
        return any(issupertrait_vertex(parent, t2) for (t, parent) in _traits_vertex if t is t1)


class EdgeTrait(Enum):
    Input = auto()
    Output = auto()
    Annotation = auto()
    Expansion = auto()
    AbstractPhysicalConnection = auto()
    AbstractDecision = auto()
    AbstractScheduling = auto()
    AbstractMapping = auto()


_traits_edges = [
    (EdgeTrait.AbstractScheduling, EdgeTrait.AbstractDecision),
    (EdgeTrait.AbstractMapping, EdgeTrait.AbstractDecision),
]

def issupertrait_edge(t1: EdgeTrait, t2: EdgeTrait) -> bool:
    if t1 is t2:
        return True
    else:
        return any(issupertrait_edge(parent, t2) for (t, parent) in _traits_edges if t is t1)

