"""
This package has been generated automatically from the ForSyDe IO types model.
Any code in this file may be overwritten without notice, so it's best not to spend
time modifying anything here, but on the true source which is a model file.
"""


import forsyde.io.python.core as core


class SporadicTaskType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'SporadicTask'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield TriggeredTaskType.get_instance()
        yield from TriggeredTaskType.get_instance().get_super_types()
        yield AbstractOrderingType.get_instance()
        yield from AbstractOrderingType.get_instance().get_super_types()

    # the type has at least one default attribute 
    # and therefore the function override is generated
    def get_required_properties(self):
        yield ('minArrival', 0)
        yield from TriggeredTaskType.get_instance().get_required_properties()
        yield from AbstractOrderingType.get_instance().get_required_properties()


class TriggeredTaskType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'TriggeredTask'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield AbstractOrderingType.get_instance()
        yield from AbstractOrderingType.get_instance().get_super_types()


class AbstractOrderingType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'AbstractOrdering'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield AbstractGroupingType.get_instance()
        yield from AbstractGroupingType.get_instance().get_super_types()


class AbstractGroupingType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'AbstractGrouping'


class WCETType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'WCET'

    # the type has at least one refence 
    # and therefore the function override is generated
    def get_required_ports(self):
        yield ('process', ProcessType.get_instance())
        yield from ProcessType.get_instance().get_required_ports()
        yield ('processor', AbstractProcessingComponentType.get_instance())
        yield from AbstractProcessingComponentType.get_instance().get_required_ports()

    # the type has at least one default attribute 
    # and therefore the function override is generated
    def get_required_properties(self):
        yield ('time', 0)


class WCCTType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'WCCT'

    # the type has at least one refence 
    # and therefore the function override is generated
    def get_required_ports(self):
        yield ('sender', AbstractProcessingComponentType.get_instance())
        yield from AbstractProcessingComponentType.get_instance().get_required_ports()
        yield ('reciever', AbstractProcessingComponentType.get_instance())
        yield from AbstractProcessingComponentType.get_instance().get_required_ports()
        yield ('signal', SignalType.get_instance())
        yield from SignalType.get_instance().get_required_ports()

    # the type has at least one default attribute 
    # and therefore the function override is generated
    def get_required_properties(self):
        yield ('time', 0)


class RequirementType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'Requirement'


class HardRequirementType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'HardRequirement'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield RequirementType.get_instance()
        yield from RequirementType.get_instance().get_super_types()

    # the type has at least one default attribute 
    # and therefore the function override is generated
    def get_required_properties(self):
        yield ('limit', None)
        yield from RequirementType.get_instance().get_required_properties()


class MinimumThroughputType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'MinimumThroughput'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield GoalType.get_instance()
        yield from GoalType.get_instance().get_super_types()

    # the type has at least one default attribute 
    # and therefore the function override is generated
    def get_required_properties(self):
        yield ('apriori_importance', 1)
        yield from GoalType.get_instance().get_required_properties()


class GoalType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'Goal'


class StaticCyclicSchedulerType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'StaticCyclicScheduler'


class FixedPrioritySchedulerType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'FixedPriorityScheduler'

    # the type has at least one default attribute 
    # and therefore the function override is generated
    def get_required_properties(self):
        yield ('preemptive', True)


class CustomSchedulerType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'CustomScheduler'


class RoundRobinSchedulerType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'RoundRobinScheduler'


class AbstractPhysicalComponentType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'AbstractPhysicalComponent'


class AbstractProcessingComponentType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'AbstractProcessingComponent'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield AbstractPhysicalComponentType.get_instance()
        yield from AbstractPhysicalComponentType.get_instance().get_super_types()


class AbstractCommunicationComponentType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'AbstractCommunicationComponent'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield AbstractPhysicalComponentType.get_instance()
        yield from AbstractPhysicalComponentType.get_instance().get_super_types()


class TimeDivisionMultiplexerType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'TimeDivisionMultiplexer'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield AbstractCommunicationComponentType.get_instance()
        yield from AbstractCommunicationComponentType.get_instance().get_super_types()

    # the type has at least one default attribute 
    # and therefore the function override is generated
    def get_required_properties(self):
        yield ('slots', 1)
        yield from AbstractCommunicationComponentType.get_instance().get_required_properties()


class AbstractStorageComponentType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'AbstractStorageComponent'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield AbstractPhysicalComponentType.get_instance()
        yield from AbstractPhysicalComponentType.get_instance().get_super_types()


class AbsractInterfaceComponentType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'AbsractInterfaceComponent'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield AbstractPhysicalComponentType.get_instance()
        yield from AbstractPhysicalComponentType.get_instance().get_super_types()


class ProcessType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'Process'


class ConstructedProcessType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'ConstructedProcess'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield ProcessType.get_instance()
        yield from ProcessType.get_instance().get_super_types()


class SignalType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'Signal'


class FIFOSignalType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'FIFOSignal'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield SignalType.get_instance()
        yield from SignalType.get_instance().get_super_types()


class FunctionType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'Function'


class LabelSignalType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'LabelSignal'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield SignalType.get_instance()
        yield from SignalType.get_instance().get_super_types()


class SYCombType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'SYComb'

    # the type has at least one refence 
    # and therefore the function override is generated
    def get_required_ports(self):
        yield ('combinator', FunctionType.get_instance())
        yield from FunctionType.get_instance().get_required_ports()
        yield ('result', ProcessType.get_instance())
        yield from ProcessType.get_instance().get_required_ports()


class SYPrefixType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'SYPrefix'

    # the type has at least one refence 
    # and therefore the function override is generated
    def get_required_ports(self):
        yield ('result', ProcessType.get_instance())
        yield from ProcessType.get_instance().get_required_ports()
        yield ('initializer', FunctionType.get_instance())
        yield from FunctionType.get_instance().get_required_ports()


class SDFCombType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'SDFComb'

    # the type has at least one refence 
    # and therefore the function override is generated
    def get_required_ports(self):
        yield ('combinator', FunctionType.get_instance())
        yield from FunctionType.get_instance().get_required_ports()
        yield ('result', ProcessType.get_instance())
        yield from ProcessType.get_instance().get_required_ports()

    # the type has at least one default attribute 
    # and therefore the function override is generated
    def get_required_properties(self):
        yield ('production', None)
        yield ('consumption', None)


class SDFPrefixType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'SDFPrefix'

    # the type has at least one refence 
    # and therefore the function override is generated
    def get_required_ports(self):
        yield ('result', ProcessType.get_instance())
        yield from ProcessType.get_instance().get_required_ports()
        yield ('initializer', FunctionType.get_instance())
        yield from FunctionType.get_instance().get_required_ports()


class DEPrefixType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'DEPrefix'


class DEValueCombType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'DEValueComb'


class DETagCombType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'DETagComb'


class InputType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'Input'


class OutputType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'Output'


class AnnotationType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'Annotation'


class ExpansionType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'Expansion'


class AbstractPhysicalConnectionType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'AbstractPhysicalConnection'


class DecisionType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'Decision'


class SchedulingType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'Scheduling'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield DecisionType.get_instance()
        yield from DecisionType.get_instance().get_super_types()


class MappingType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'Mapping'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield DecisionType.get_instance()
        yield from DecisionType.get_instance().get_super_types()


class AbstractCyberConnectionType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'AbstractCyberConnection'


class ConsumptionType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'Consumption'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield AbstractCyberConnectionType.get_instance()
        yield from AbstractCyberConnectionType.get_instance().get_super_types()


class ProductionType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'Production'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield AbstractCyberConnectionType.get_instance()
        yield from AbstractCyberConnectionType.get_instance().get_super_types()


class ConstructionType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'Construction'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield AbstractCyberConnectionType.get_instance()
        yield from AbstractCyberConnectionType.get_instance().get_super_types()


class AbstractHardwarePortType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'AbstractHardwarePort'


class AbstractCyberPortType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'AbstractCyberPort'


class NumericType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'Numeric'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield AbstractCyberPortType.get_instance()
        yield from AbstractCyberPortType.get_instance().get_super_types()

    # the type has at least one default attribute 
    # and therefore the function override is generated
    def get_required_properties(self):
        yield ('bits', 1)
        yield from AbstractCyberPortType.get_instance().get_required_properties()


class IntegerType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'Integer'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield NumericType.get_instance()
        yield from NumericType.get_instance().get_super_types()


class UnsignedIntegerType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'UnsignedInteger'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield NumericType.get_instance()
        yield from NumericType.get_instance().get_super_types()


class RealType(core.ModelType):
    """
    This class has been automatically generated from forsyde-io.
    Any modifications therein are likely going to be overwritten.
    """

    def get_type_name(self):
        return 'Real'

    # the type has at least one super type 
    # and therefore the function override is generated.
    def get_super_types(self):
        yield NumericType.get_instance()
        yield from NumericType.get_instance().get_super_types()


class TypesFactory:
    """
    This class is auto generated.
    It enables import and export of ForSyDe-IO type models by stringification.
    """

    @classmethod
    def build_type(cls,
                   type_name: str,
                   strict: bool = True
                   ) -> core.ModelType:
        str_to_classes = {
                "SporadicTask": SporadicTaskType,
                "TriggeredTask": TriggeredTaskType,
                "AbstractOrdering": AbstractOrderingType,
                "AbstractGrouping": AbstractGroupingType,
                "WCET": WCETType,
                "WCCT": WCCTType,
                "Requirement": RequirementType,
                "HardRequirement": HardRequirementType,
                "MinimumThroughput": MinimumThroughputType,
                "Goal": GoalType,
                "StaticCyclicScheduler": StaticCyclicSchedulerType,
                "FixedPriorityScheduler": FixedPrioritySchedulerType,
                "CustomScheduler": CustomSchedulerType,
                "RoundRobinScheduler": RoundRobinSchedulerType,
                "AbstractPhysicalComponent": AbstractPhysicalComponentType,
                "AbstractProcessingComponent": AbstractProcessingComponentType,
                "AbstractCommunicationComponent": AbstractCommunicationComponentType,
                "TimeDivisionMultiplexer": TimeDivisionMultiplexerType,
                "AbstractStorageComponent": AbstractStorageComponentType,
                "AbsractInterfaceComponent": AbsractInterfaceComponentType,
                "Process": ProcessType,
                "ConstructedProcess": ConstructedProcessType,
                "Signal": SignalType,
                "FIFOSignal": FIFOSignalType,
                "Function": FunctionType,
                "LabelSignal": LabelSignalType,
                "SYComb": SYCombType,
                "SYPrefix": SYPrefixType,
                "SDFComb": SDFCombType,
                "SDFPrefix": SDFPrefixType,
                "DEPrefix": DEPrefixType,
                "DEValueComb": DEValueCombType,
                "DETagComb": DETagCombType,
                "Input": InputType,
                "Output": OutputType,
                "Annotation": AnnotationType,
                "Expansion": ExpansionType,
                "AbstractPhysicalConnection": AbstractPhysicalConnectionType,
                "Decision": DecisionType,
                "Scheduling": SchedulingType,
                "Mapping": MappingType,
                "AbstractCyberConnection": AbstractCyberConnectionType,
                "Consumption": ConsumptionType,
                "Production": ProductionType,
                "Construction": ConstructionType,
                "AbstractHardwarePort": AbstractHardwarePortType,
                "AbstractCyberPort": AbstractCyberPortType,
                "Numeric": NumericType,
                "Integer": IntegerType,
                "UnsignedInteger": UnsignedIntegerType,
                "Real": RealType
        }
        if type_name in str_to_classes:
            return str_to_classes[type_name].get_instance()
        if strict:
            raise NotImplementedError(
              f"The type '{type_name}' is not recognized."
            )
        else:
            return None
