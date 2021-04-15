from typing import Dict
from typing import List
from typing import Sequence

import forsyde.io.python.core as core



class AbstractGrouping(core.Vertex):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "AbstractGrouping"
            
    


class AbstractOrdering(AbstractGrouping):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "AbstractOrdering"
            
    


class TriggeredTask(AbstractOrdering):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "TriggeredTask"
            
    


class SporadicTask(AbstractOrdering):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "SporadicTask"
            
    


class ExtraFunctional(core.Vertex):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "ExtraFunctional"
            
    


class WCET(ExtraFunctional):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "WCET"
            
    
    
    def get_port_application(self) -> core.Port:
        return self.get_port("application")

    
    def get_application(self, model) -> Sequence["Process"]:
        return self.get_neighs("application", model)
    

    
    def get_port_platform(self) -> core.Port:
        return self.get_port("platform")

    
    def get_platform(self, model) -> Sequence["AbstractProcessingComponent"]:
        return self.get_neighs("platform", model)
    

    
    
    def get_time(self) -> int:
        return self.properties["time"] if 'time' in self.properties else 0

    
    


class WCCT(ExtraFunctional):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "WCCT"
            
    
    
    def get_port_application(self) -> core.Port:
        return self.get_port("application")

    
    def get_application(self, model) -> Sequence["Process"]:
        return self.get_neighs("application", model)
    

    
    def get_port_platform(self) -> core.Port:
        return self.get_port("platform")

    
    def get_platform(self, model) -> Sequence["AbstractCommunicationComponent"]:
        return self.get_neighs("platform", model)
    

    
    
    def get_time(self) -> int:
        return self.properties["time"] if 'time' in self.properties else 0

    
    


class Requirement(core.Vertex):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "Requirement"
            
    


class HardRequirement(Requirement):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "HardRequirement"
            
    


class LocationRequirement(Requirement):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "LocationRequirement"
            
    
    
    def get_port_process(self) -> core.Port:
        return self.get_port("process")

    
    def get_process(self, model) -> Sequence["Process"]:
        return self.get_neighs("process", model)
    

    
    def get_port_processing_unit(self) -> core.Port:
        return self.get_port("processing_unit")

    
    def get_processing_unit(self, model) -> Sequence["AbstractProcessingComponent"]:
        return self.get_neighs("processing_unit", model)
    

    
    


class Goal(core.Vertex):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "Goal"
            
    


class MinimumThroughput(Goal):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "MinimumThroughput"
            
    
    
    def get_port_application(self) -> core.Port:
        return self.get_port("application")

    
    def get_application(self, model) -> Sequence["Process"]:
        return self.get_neighs("application", model)
    

    
    
    def get_apriori_importance(self) -> int:
        return self.properties["apriori_importance"] if 'apriori_importance' in self.properties else 1

    
    


class StaticCyclicScheduler(core.Vertex):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "StaticCyclicScheduler"
            
    


class TimeTriggeredScheduler(AbstractGrouping):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "TimeTriggeredScheduler"
            
    
    def get_trigger_time(self) -> Dict[int, str]:
        return self.properties["trigger_time"] if 'trigger_time' in self.properties else {}

    
    


class FixedPriorityScheduler(core.Vertex):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "FixedPriorityScheduler"
            
    


class CustomScheduler(core.Vertex):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "CustomScheduler"
            
    


class RoundRobinScheduler(core.Vertex):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "RoundRobinScheduler"
            
    


class Function(core.Vertex):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "Function"
            
    


class InstrumentedFunction(Function):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "InstrumentedFunction"
            
    
    def get_max_float_operations(self) -> int:
        return self.properties["max_float_operations"] if 'max_float_operations' in self.properties else 0

    def get_max_int_operations(self) -> int:
        return self.properties["max_int_operations"] if 'max_int_operations' in self.properties else 0

    def get_max_boolean_operations(self) -> int:
        return self.properties["max_boolean_operations"] if 'max_boolean_operations' in self.properties else 0

    def get_max_memory_size_in_bytes(self) -> int:
        return self.properties["max_memory_size_in_bytes"] if 'max_memory_size_in_bytes' in self.properties else 1

    
    


class Process(Function):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "Process"
            
    


class Signal(core.Vertex):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "Signal"
            
    


class BufferSignal(Signal):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "BufferSignal"
            
    


class LabelSignal(Signal):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "LabelSignal"
            
    


class InstrumentedSignal(Signal):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "InstrumentedSignal"
            
    
    def get_max_elem_size_bytes(self) -> int:
        try:
            return self.properties["max_elem_size_bytes"]
        except KeyError:
            raise AttributeError(f"Vertex {self.identifier} has no required 'max_elem_size_bytes' property.")

    def get_max_elem_count(self) -> int:
        return self.properties["max_elem_count"] if 'max_elem_count' in self.properties else 1

    
    


class SYComb(Function):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "SYComb"
            
    


class SYPrefix(Function):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "SYPrefix"
            
    
    
    def get_port_prefixer(self) -> core.Port:
        return self.get_port("prefixer")

    
    def get_prefixer(self, model) -> "Function":
        return self.get_neigh("prefixer", model)
    

    
    def get_port_output(self) -> core.Port:
        return self.get_port("output")

    
    def get_output(self, model) -> "Function":
        return self.get_neigh("output", model)
    

    
    


class SDFComb(Function):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "SDFComb"
            
    
    
    def get_port_combinator(self) -> core.Port:
        return self.get_port("combinator")

    
    def get_combinator(self, model) -> "Function":
        return self.get_neigh("combinator", model)
    

    
    def get_port_output(self) -> core.Port:
        return self.get_port("output")

    
    def get_output(self, model) -> "Function":
        return self.get_neigh("output", model)
    

    
    
    def get_consumption(self) -> Dict[str, int]:
        try:
            return self.properties["consumption"]
        except KeyError:
            raise AttributeError(f"Vertex {self.identifier} has no required 'consumption' property.")

    def get_production(self) -> Dict[str, int]:
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

    def get_type_tag(self) -> str:
	        return "SDFPrefix"
            
    
    
    def get_port_prefixer(self) -> core.Port:
        return self.get_port("prefixer")

    
    def get_prefixer(self, model) -> "Function":
        return self.get_neigh("prefixer", model)
    

    
    def get_port_output(self) -> core.Port:
        return self.get_port("output")

    
    def get_output(self, model) -> "Function":
        return self.get_neigh("output", model)
    

    
    


class AbstractPhysicalComponent(core.Vertex):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "AbstractPhysicalComponent"
            
    


class AbstractProcessingComponent(AbstractPhysicalComponent):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "AbstractProcessingComponent"
            
    
    def get_can_be_explored(self) -> bool:
        return self.properties["can_be_explored"] if 'can_be_explored' in self.properties else True

    
    


class InstrumentedProcessorTile(AbstractProcessingComponent):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "InstrumentedProcessorTile"
            
    
    def get_min_frequency_hz(self) -> int:
        return self.properties["min_frequency_hz"] if 'min_frequency_hz' in self.properties else 50000000

    def get_max_memory_internal_bytes(self) -> int:
        return self.properties["max_memory_internal_bytes"] if 'max_memory_internal_bytes' in self.properties else 32000

    def get_clock_cycles_per_float_op(self) -> int:
        return self.properties["clock_cycles_per_float_op"] if 'clock_cycles_per_float_op' in self.properties else 6

    def get_clock_cycles_per_integer_op(self) -> int:
        return self.properties["clock_cycles_per_integer_op"] if 'clock_cycles_per_integer_op' in self.properties else 2

    def get_clock_cycles_per_boolean_op(self) -> int:
        return self.properties["clock_cycles_per_boolean_op"] if 'clock_cycles_per_boolean_op' in self.properties else 1

    
    


class AbstractStorageComponent(AbstractPhysicalComponent):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "AbstractStorageComponent"
            
    


class AbsractInterfaceComponent(AbstractPhysicalComponent):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "AbsractInterfaceComponent"
            
    


class AbstractCommunicationComponent(AbstractPhysicalComponent):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "AbstractCommunicationComponent"
            
    


class InstrumentedCommunicationInterconnect(AbstractCommunicationComponent):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "InstrumentedCommunicationInterconnect"
            
    
    def get_max_bandwith_bytes_per_sec(self) -> int:
        return self.properties["max_bandwith_bytes_per_sec"] if 'max_bandwith_bytes_per_sec' in self.properties else 1000000

    
    


class TimeDivisionMultiplexer(AbstractCommunicationComponent):

    """
    This class has been generated automatically from the ForSyDe IO types model.
    Any code in this file may be overwritten without notice, so it's best not to spend
    time modifying anything here, but on the true source which is a model file.
    
    
    """

    def get_type_tag(self) -> str:
	        return "TimeDivisionMultiplexer"
            
    
    def get_slots(self) -> int:
        return self.properties["slots"] if 'slots' in self.properties else 1

    
    




class Input(core.Edge):



    def get_type_tag(self) -> str:
        return "Input"


class Output(core.Edge):



    def get_type_tag(self) -> str:
        return "Output"


class Annotation(core.Edge):



    def get_type_tag(self) -> str:
        return "Annotation"


class Expansion(core.Edge):



    def get_type_tag(self) -> str:
        return "Expansion"


class AbstractPhysicalConnection(core.Edge):



    def get_type_tag(self) -> str:
        return "AbstractPhysicalConnection"


class AbstractDecision(core.Edge):



    def get_type_tag(self) -> str:
        return "AbstractDecision"


class AbstractScheduling(AbstractDecision):



    def get_type_tag(self) -> str:
        return "AbstractScheduling"


class AbstractMapping(AbstractDecision):



    def get_type_tag(self) -> str:
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
        
        "LocationRequirement": LocationRequirement,
        
        "Goal": Goal,
        
        "MinimumThroughput": MinimumThroughput,
        
        "StaticCyclicScheduler": StaticCyclicScheduler,
        
        "TimeTriggeredScheduler": TimeTriggeredScheduler,
        
        "FixedPriorityScheduler": FixedPriorityScheduler,
        
        "CustomScheduler": CustomScheduler,
        
        "RoundRobinScheduler": RoundRobinScheduler,
        
        "Function": Function,
        
        "InstrumentedFunction": InstrumentedFunction,
        
        "Process": Process,
        
        "Signal": Signal,
        
        "BufferSignal": BufferSignal,
        
        "LabelSignal": LabelSignal,
        
        "InstrumentedSignal": InstrumentedSignal,
        
        "SYComb": SYComb,
        
        "SYPrefix": SYPrefix,
        
        "SDFComb": SDFComb,
        
        "SDFPrefix": SDFPrefix,
        
        "AbstractPhysicalComponent": AbstractPhysicalComponent,
        
        "AbstractProcessingComponent": AbstractProcessingComponent,
        
        "InstrumentedProcessorTile": InstrumentedProcessorTile,
        
        "AbstractStorageComponent": AbstractStorageComponent,
        
        "AbsractInterfaceComponent": AbsractInterfaceComponent,
        
        "AbstractCommunicationComponent": AbstractCommunicationComponent,
        
        "InstrumentedCommunicationInterconnect": InstrumentedCommunicationInterconnect,
        
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