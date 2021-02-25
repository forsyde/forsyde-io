import forsyde.io.python.core as core



class AbstractGrouping(core.Vertex):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "AbstractGrouping"
            
    
    


class AbstractOrdering(AbstractGrouping):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "AbstractOrdering"
            
    
    


class TriggeredTask(AbstractOrdering):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "TriggeredTask"
            
    
    


class SporadicTask(AbstractOrdering):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "SporadicTask"
            
    
    


class ExtraFunctional(core.Vertex):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "ExtraFunctional"
            
    
    


class WCET(ExtraFunctional):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "WCET"
            
    
    


class WCCT(ExtraFunctional):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "WCCT"
            
    
    


class Requirement(core.Vertex):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "Requirement"
            
    
    


class HardRequirement(Requirement):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "HardRequirement"
            
    
    


class Goal(core.Vertex):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "Goal"
            
    
    


class MinimumThroughput(Goal):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "MinimumThroughput"
            
    
    


class StaticCyclicScheduler(core.Vertex):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "StaticCyclicScheduler"
            
    
    


class FixedPriorityScheduler(core.Vertex):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "FixedPriorityScheduler"
            
    
    


class CustomScheduler(core.Vertex):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "CustomScheduler"
            
    
    


class RoundRobinScheduler(core.Vertex):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "RoundRobinScheduler"
            
    
    


class Function(core.Vertex):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "Function"
            
    
    


class Process(Function):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "Process"
            
    
    


class Signal(core.Vertex):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "Signal"
            
    
    


class BufferSignal(Signal):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "BufferSignal"
            
    
    


class LabelSignal(Signal):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "LabelSignal"
            
    
    


class SYComb(Function):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "SYComb"
            
    
    


class SYPrefix(Function):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "SYPrefix"
            
    
    
    def get_port_prefixer(self) -> core.Port:
        return self.get_port("prefixer")

    def get_prefixer(self, model) -> Function:
        out_port = self.get_port_prefixer()
        for n in model.adj[self]:
            for (_, edata) in model.edges[self][n]:
                edge = edata["object"]
                if edge.source_vertex_port == out_port:
                    return edge.target_vertex
        raise AttributeError(f"Required port prefixer of {self.identifier} does not exist.")

    
    def get_port_output(self) -> core.Port:
        return self.get_port("output")

    def get_output(self, model) -> Function:
        out_port = self.get_port_output()
        for n in model.adj[self]:
            for (_, edata) in model.edges[self][n]:
                edge = edata["object"]
                if edge.source_vertex_port == out_port:
                    return edge.target_vertex
        raise AttributeError(f"Required port output of {self.identifier} does not exist.")

    
    
    


class SDFComb(Function):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "SDFComb"
            
    
    
    def get_port_combinator(self) -> core.Port:
        return self.get_port("combinator")

    def get_combinator(self, model) -> Function:
        out_port = self.get_port_combinator()
        for n in model.adj[self]:
            for (_, edata) in model.edges[self][n]:
                edge = edata["object"]
                if edge.source_vertex_port == out_port:
                    return edge.target_vertex
        raise AttributeError(f"Required port combinator of {self.identifier} does not exist.")

    
    def get_port_output(self) -> core.Port:
        return self.get_port("output")

    def get_output(self, model) -> Function:
        out_port = self.get_port_output()
        for n in model.adj[self]:
            for (_, edata) in model.edges[self][n]:
                edge = edata["object"]
                if edge.source_vertex_port == out_port:
                    return edge.target_vertex
        raise AttributeError(f"Required port output of {self.identifier} does not exist.")

    
    
    
    
    
    def get_consumption(self) -> dict:
    
        try:
            return self.properties["consumption"]
        except KeyError:
            raise AttributeError(f"Vertex {self.identifier} has no required 'consumption' property.")

    
    
    def get_production(self) -> dict:
    
        try:
            return self.properties["production"]
        except KeyError:
            raise AttributeError(f"Vertex {self.identifier} has no required 'production' property.")

    
    


class SDFPrefix(Function):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "SDFPrefix"
            
    
    
    def get_port_prefixer(self) -> core.Port:
        return self.get_port("prefixer")

    def get_prefixer(self, model) -> Function:
        out_port = self.get_port_prefixer()
        for n in model.adj[self]:
            for (_, edata) in model.edges[self][n]:
                edge = edata["object"]
                if edge.source_vertex_port == out_port:
                    return edge.target_vertex
        raise AttributeError(f"Required port prefixer of {self.identifier} does not exist.")

    
    def get_port_output(self) -> core.Port:
        return self.get_port("output")

    def get_output(self, model) -> Function:
        out_port = self.get_port_output()
        for n in model.adj[self]:
            for (_, edata) in model.edges[self][n]:
                edge = edata["object"]
                if edge.source_vertex_port == out_port:
                    return edge.target_vertex
        raise AttributeError(f"Required port output of {self.identifier} does not exist.")

    
    
    


class AbstractPhysicalComponent(core.Vertex):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "AbstractPhysicalComponent"
            
    
    


class AbstractProcessingComponent(AbstractPhysicalComponent):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "AbstractProcessingComponent"
            
    
    


class AbstractStorageComponent(AbstractPhysicalComponent):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "AbstractStorageComponent"
            
    
    


class AbsractInterfaceComponent(AbstractPhysicalComponent):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "AbsractInterfaceComponent"
            
    
    


class AbstractCommunicationComponent(AbstractPhysicalComponent):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "AbstractCommunicationComponent"
            
    
    


class TimeDivisionMultiplexer(AbstractCommunicationComponent):

    """
	This class has been generated automatically from the ForSyDe IO types model.
	Any code in this file may be overwritten without notice, so it's best not to spend
	time modifying anything here, but on the true source which is a model file.
    
    
	"""

    def get_type_name(self) -> str:
	        return "TimeDivisionMultiplexer"
            
    
    
    
    
    def get_slots(self) -> int:
    
        try:
            return self.properties["slots"]
        except KeyError:
            raise AttributeError(f"Vertex {self.identifier} has no required 'slots' property.")

    
    




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
        
        "ExtraFunctional": ExtraFunctional,
        
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
        
        "BufferSignal": BufferSignal,
        
        "LabelSignal": LabelSignal,
        
        "SYComb": SYComb,
        
        "SYPrefix": SYPrefix,
        
        "SDFComb": SDFComb,
        
        "SDFPrefix": SDFPrefix,
        
        "AbstractPhysicalComponent": AbstractPhysicalComponent,
        
        "AbstractProcessingComponent": AbstractProcessingComponent,
        
        "AbstractStorageComponent": AbstractStorageComponent,
        
        "AbsractInterfaceComponent": AbsractInterfaceComponent,
        
        "AbstractCommunicationComponent": AbstractCommunicationComponent,
        
        "TimeDivisionMultiplexer": TimeDivisionMultiplexer
        
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
        try:
            vtype = cls.get_type_from_name(type_name)
            return vtype(
                identifier=identifier,
                ports=ports if ports else set(),
                properties=properties if properties else dict()
            )
        except KeyError: 
            raise NotImplementedError(
                f"The Vertex type '{type_name}' is not recognized."
            )

class EdgeFactory:
    """
    This class is auto generated.
    It enables import and export of ForSyDe-IO type models by stringification.
    """

    str_to_classes = {
        
        "Input": Input,
        
        "Output": Output,
        
        "Annotation": Annotation,
        
        "Expansion": Expansion,
        
        "AbstractPhysicalConnection": AbstractPhysicalConnection,
        
        "AbstractDecision": AbstractDecision,
        
        "AbstractScheduling": AbstractScheduling,
        
        "AbstractMapping": AbstractMapping
        
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
        source: core.Vertex,
        target: core.Vertex,
        type_name: str
    ) -> core.Edge:
        try:
            etype = cls.get_type_from_name(type_name)
            return etype(
                source_vertex = source,
                target_vertex = target
            )
        except KeyError:
            raise NotImplementedError(
                f"The Edge type '{type_name}' is not recognized."
            )