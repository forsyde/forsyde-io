"""
This package has been generated automatically from the ForSyDe IO types model.
Any code in this file may be overwritten without notice, so it's best not to spend
time modifying anything here, but on the true source which is a model file.
"""

import forsyde.io.python.core as core


class AbstractGrouping(core.Vertex):

    def get_type_name(self) -> str:
        return "AbstractGrouping"
        

class AbstractOrdering(AbstractGrouping):

    def get_type_name(self) -> str:
        return "AbstractOrdering"
        

class TriggeredTask(AbstractOrdering):

    def get_type_name(self) -> str:
        return "TriggeredTask"
        

class SporadicTask(TriggeredTask, AbstractOrdering):

    def get_type_name(self) -> str:
        return "SporadicTask"
        

class WCET(core.Vertex):

    def get_type_name(self) -> str:
        return "WCET"
        

class WCCT(core.Vertex):

    def get_type_name(self) -> str:
        return "WCCT"
        

class Requirement(core.Vertex):

    def get_type_name(self) -> str:
        return "Requirement"
        

class HardRequirement(Requirement):

    def get_type_name(self) -> str:
        return "HardRequirement"
        

class Goal(core.Vertex):

    def get_type_name(self) -> str:
        return "Goal"
        

class MinimumThroughput(Goal):

    def get_type_name(self) -> str:
        return "MinimumThroughput"
        

class StaticCyclicScheduler(core.Vertex):

    def get_type_name(self) -> str:
        return "StaticCyclicScheduler"
        

class FixedPriorityScheduler(core.Vertex):

    def get_type_name(self) -> str:
        return "FixedPriorityScheduler"
        

class CustomScheduler(core.Vertex):

    def get_type_name(self) -> str:
        return "CustomScheduler"
        

class RoundRobinScheduler(core.Vertex):

    def get_type_name(self) -> str:
        return "RoundRobinScheduler"
        

class Function(core.Vertex):

    def get_type_name(self) -> str:
        return "Function"
        

class Process(Function):

    def get_type_name(self) -> str:
        return "Process"
        

class Signal(core.Vertex):

    def get_type_name(self) -> str:
        return "Signal"
        

class FIFOSignal(Signal):

    def get_type_name(self) -> str:
        return "FIFOSignal"
        

class LabelSignal(Signal):

    def get_type_name(self) -> str:
        return "LabelSignal"
        

class SYComb(Function):

    def get_type_name(self) -> str:
        return "SYComb"
        
    def get_port_combinator(self) -> core.Port:
        return self.get_port("combinator")

    def get_port_output(self) -> core.Port:
        return self.get_port("output")

    def get_combinator(self, model) -> Function:
        out_port = self.get_port_combinator()
        for n in model.adj[self]:
            for (_, edata) in model.edges[self][n]:
                edge = edata["object"]
                if edge.source_vertex_port == out_port:
                    return edge.target_vertex
        raise AttributeError(f"Port combinator of {self.identifier} does not exist.")

    def get_output(self, model) -> Function:
        out_port = self.get_port_output()
        for n in model.adj[self]:
            for (_, edata) in model.edges[self][n]:
                edge = edata["object"]
                if edge.source_vertex_port == out_port:
                    return edge.target_vertex
        raise AttributeError(f"Port output of {self.identifier} does not exist.")


class SYPrefix(Function):

    def get_type_name(self) -> str:
        return "SYPrefix"
        
    def get_port_prefixer(self) -> core.Port:
        return self.get_port("prefixer")

    def get_port_output(self) -> core.Port:
        return self.get_port("output")

    def get_prefixer(self, model) -> Function:
        out_port = self.get_port_prefixer()
        for n in model.adj[self]:
            for (_, edata) in model.edges[self][n]:
                edge = edata["object"]
                if edge.source_vertex_port == out_port:
                    return edge.target_vertex
        raise AttributeError(f"Port prefixer of {self.identifier} does not exist.")

    def get_output(self, model) -> Function:
        out_port = self.get_port_output()
        for n in model.adj[self]:
            for (_, edata) in model.edges[self][n]:
                edge = edata["object"]
                if edge.source_vertex_port == out_port:
                    return edge.target_vertex
        raise AttributeError(f"Port output of {self.identifier} does not exist.")


class SDFComb(Function):

    def get_type_name(self) -> str:
        return "SDFComb"
        
    def get_port_combinator(self) -> core.Port:
        return self.get_port("combinator")

    def get_port_output(self) -> core.Port:
        return self.get_port("output")

    def get_combinator(self, model) -> Function:
        out_port = self.get_port_combinator()
        for n in model.adj[self]:
            for (_, edata) in model.edges[self][n]:
                edge = edata["object"]
                if edge.source_vertex_port == out_port:
                    return edge.target_vertex
        raise AttributeError(f"Port combinator of {self.identifier} does not exist.")

    def get_output(self, model) -> Function:
        out_port = self.get_port_output()
        for n in model.adj[self]:
            for (_, edata) in model.edges[self][n]:
                edge = edata["object"]
                if edge.source_vertex_port == out_port:
                    return edge.target_vertex
        raise AttributeError(f"Port output of {self.identifier} does not exist.")

    def get_production(self):
        try:
            return self.properties["production"]
        except KeyError:
            raise AttributeError(f"Vertex {self.identifier} has no 'production' property.")

    def get_consumption(self):
        try:
            return self.properties["consumption"]
        except KeyError:
            raise AttributeError(f"Vertex {self.identifier} has no 'consumption' property.")


class SDFPrefix(Function):

    def get_type_name(self) -> str:
        return "SDFPrefix"
        
    def get_port_output(self) -> core.Port:
        return self.get_port("output")

    def get_port_prefixer(self) -> core.Port:
        return self.get_port("prefixer")

    def get_output(self, model) -> Function:
        out_port = self.get_port_output()
        for n in model.adj[self]:
            for (_, edata) in model.edges[self][n]:
                edge = edata["object"]
                if edge.source_vertex_port == out_port:
                    return edge.target_vertex
        raise AttributeError(f"Port output of {self.identifier} does not exist.")

    def get_prefixer(self, model) -> Function:
        out_port = self.get_port_prefixer()
        for n in model.adj[self]:
            for (_, edata) in model.edges[self][n]:
                edge = edata["object"]
                if edge.source_vertex_port == out_port:
                    return edge.target_vertex
        raise AttributeError(f"Port prefixer of {self.identifier} does not exist.")


class AbstractPhysicalComponent(core.Vertex):

    def get_type_name(self) -> str:
        return "AbstractPhysicalComponent"
        

class AbstractProcessingComponent(AbstractPhysicalComponent):

    def get_type_name(self) -> str:
        return "AbstractProcessingComponent"
        

class AbstractCommunicationComponent(AbstractPhysicalComponent):

    def get_type_name(self) -> str:
        return "AbstractCommunicationComponent"
        

class TimeDivisionMultiplexer(AbstractCommunicationComponent):

    def get_type_name(self) -> str:
        return "TimeDivisionMultiplexer"
        
    def get_slots(self):
        try:
            return self.properties["slots"]
        except KeyError:
            raise AttributeError(f"Vertex {self.identifier} has no 'slots' property.")


class AbstractStorageComponent(AbstractPhysicalComponent):

    def get_type_name(self) -> str:
        return "AbstractStorageComponent"
        

class AbsractInterfaceComponent(AbstractPhysicalComponent):

    def get_type_name(self) -> str:
        return "AbsractInterfaceComponent"
        

class Input(core.Edge):

    def get_type_name(self) -> str:
        return "Input"

class Output(core.Edge):

    def get_type_name(self) -> str:
        return "Output"

class Annotation(core.Edge):

    def get_type_name(self) -> str:
        return "Annotation"

class Expansion(core.Edge):

    def get_type_name(self) -> str:
        return "Expansion"

class AbstractPhysicalConnection(core.Edge):

    def get_type_name(self) -> str:
        return "AbstractPhysicalConnection"

class AbstractDecision(core.Edge):

    def get_type_name(self) -> str:
        return "AbstractDecision"

class AbstractScheduling(AbstractDecision):

    def get_type_name(self) -> str:
        return "AbstractScheduling"

class AbstractMapping(AbstractDecision):

    def get_type_name(self) -> str:
        return "AbstractMapping"

class VertexFactory:
    """
    This class is auto generated.
    It enables import and export of ForSyDe-IO type models by stringification.
    """

    str_to_classes = {
            "AbstractGrouping": AbstractGrouping,
            "AbstractOrdering": AbstractOrdering,
            "TriggeredTask": TriggeredTask,
            "SporadicTask": SporadicTask,
            "WCET": WCET,
            "WCCT": WCCT,
            "Requirement": Requirement,
            "HardRequirement": HardRequirement,
            "Goal": Goal,
            "MinimumThroughput": MinimumThroughput,
            "StaticCyclicScheduler": StaticCyclicScheduler,
            "FixedPriorityScheduler": FixedPriorityScheduler,
            "CustomScheduler": CustomScheduler,
            "RoundRobinScheduler": RoundRobinScheduler,
            "Function": Function,
            "Process": Process,
            "Signal": Signal,
            "FIFOSignal": FIFOSignal,
            "LabelSignal": LabelSignal,
            "SYComb": SYComb,
            "SYPrefix": SYPrefix,
            "SDFComb": SDFComb,
            "SDFPrefix": SDFPrefix,
            "AbstractPhysicalComponent": AbstractPhysicalComponent,
            "AbstractProcessingComponent": AbstractProcessingComponent,
            "AbstractCommunicationComponent": AbstractCommunicationComponent,
            "TimeDivisionMultiplexer": TimeDivisionMultiplexer,
            "AbstractStorageComponent": AbstractStorageComponent,
            "AbsractInterfaceComponent": AbsractInterfaceComponent
    }
    
    @classmethod
    def get_type_from_name(cls,
                   type_name: str
                   ) -> type:
        if type_name in cls.str_to_classes:
            return cls.str_to_classes[type_name]
        raise NotImplementedError(
           f"The type '{type_name}' is not recognized."
        )


    @classmethod
    def build(
        cls,
        identifier: str,
        type_name: str,
        ports = None,
        properties = None
     ) -> core.Vertex:
        vtype = cls.get_type_from_name(type_name)
        return vtype(
            identifier=identifier,
            ports=ports if ports else set(),
            properties=properties if properties else dict()
        )
        raise NotImplementedError(
           f"The Vertex type '{type_name}' is not recognized."
        )

class EdgeFactory:
    """
    This class is auto generated.
    It enables import and export of ForSyDe-IO type models by stringification.
    """

    @classmethod
    def build(
        cls,
	    source: core.Vertex,
	    target: core.Vertex,
	    type_name: str
	) -> core.Edge:
        if type_name == "Input":
            return Input(
                source_vertex = source,
                target_vertex = target
            )
        if type_name == "Output":
            return Output(
                source_vertex = source,
                target_vertex = target
            )
        if type_name == "Annotation":
            return Annotation(
                source_vertex = source,
                target_vertex = target
            )
        if type_name == "Expansion":
            return Expansion(
                source_vertex = source,
                target_vertex = target
            )
        if type_name == "AbstractPhysicalConnection":
            return AbstractPhysicalConnection(
                source_vertex = source,
                target_vertex = target
            )
        if type_name == "AbstractDecision":
            return AbstractDecision(
                source_vertex = source,
                target_vertex = target
            )
        if type_name == "AbstractScheduling":
            return AbstractScheduling(
                source_vertex = source,
                target_vertex = target
            )
        if type_name == "AbstractMapping":
            return AbstractMapping(
                source_vertex = source,
                target_vertex = target
            )
        raise NotImplementedError(
            f"The Edge type '{type_name}' is not recognized."
        )

