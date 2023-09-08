# LibForSyDe Catalog

In this page you can find the generated documentation of the LibForSyDe trait hierarchy, so that it is easier
to make sense of on-disk files, e.g. fiodl files, but also to manipulate them manually if the need arises.
The last part is quite important, as any LibForSyDe-based tool consuming on-disk files might reject a model that
is not consistent with the trait hierarchy presented here.

## forsyde::io::lib::hierarchy::IForSyDeHierarchy

### forsyde::io::lib::hierarchy::behavior::moc::sdf::SDFChannel

No description exists.

Refines: `forsyde::io::lib::hierarchy::behavior::moc::MoCEntity`

Required ports:

- **tokenDataType**: `forsyde::io::lib::hierarchy::behavior::DataTypeLike` vertices connected by `forsyde::io::lib::hierarchy::behavior::BehaviourCompositionEdge` edges.
- **producer**: `forsyde::io::lib::hierarchy::behavior::moc::sdf::SDFActor` vertices connected by `forsyde::io::lib::hierarchy::behavior::moc::sdf::SDFNetworkEdge` edges.
- **consumer**: `forsyde::io::lib::hierarchy::behavior::moc::sdf::SDFActor` vertices connected by `forsyde::io::lib::hierarchy::behavior::moc::sdf::SDFNetworkEdge` edges.
  Required properties:

- **numInitialTokens:** .

### forsyde::io::lib::hierarchy::implementation::functional::BoundedBufferLike

A BoundedBuffer is a refinement of the potentially infinite BufferLike queue.
It adds a maximum number of elements for this buffer which must be respects at all times.
It also enables nice implementations in software and hardware, e.g. using circular buffers.


Refines: `forsyde::io::lib::hierarchy::implementation::functional::BufferLike`

Required ports:

Required properties:

- **maxElements:** .

### forsyde::io::lib::hierarchy::behavior::FunctionLikeEntity

No description exists.

Refines:

Required ports:

Required properties:

- **outputPorts:** .
- **inputPorts:** .

### forsyde::io::lib::hierarchy::implementation::code::HasANSICImplementations

This trait enforces the vertex to have at least one inlined C-base implementation.

There can be more than one, discriminted by a label for each inlined source code.
For example, a vertex can have a chunck of code with the label "riscv" for RISC-V
based processors and one with label "cude" to be implemented directly in a CUDA
based flow, whenever possible.



Refines:

Required ports:

Required properties:

- **outputArgumentPorts:** .
- **returnPort:** .
- **inlinedCodes:** .
- **inputArgumentPorts:** .

### forsyde::io::lib::hierarchy::visualization::Visualizable

No description exists.

Refines:

Required ports:

Required properties:


### forsyde::io::lib::hierarchy::constraints::UtilizationBound

This trait describe an utilization constraint for a GenericProcessingModule,
where the sum of all things using it, including any runtime, must not exceed a certain
percentage.


Refines: `forsyde::io::lib::hierarchy::platform::hardware::GenericProcessingModule`

Required ports:

Required properties:

- **maxUtilization:** .

### forsyde::io::lib::hierarchy::behavior::parallel::InterleaveV

This parallel skeleton describes the creation of overlapping areas from a Vectorizable.
As an example, consider the vector of integers [1, 4, 2];
An interleaving skeleton with stride of 1 and radius of 2 would generator the vector of vector [[1, 4], [4, 2]].
An interleaving skeleton with stride of 2 and radius of 2 would generator the vector of vector [[1, 4], [2]].


Refines: `forsyde::io::lib::hierarchy::behavior::parallel::ParallelSkeleton`

Required ports:

- **inputArray**: `forsyde::io::lib::hierarchy::behavior::parallel::Vectorizable` vertices connected by `forsyde::io::lib::hierarchy::behavior::parallel::ParallelComputationEdge` edges.
- **outputArrayOfArray**: `forsyde::io::lib::hierarchy::behavior::parallel::Vectorizable` vertices connected by `forsyde::io::lib::hierarchy::behavior::parallel::ParallelComputationEdge` edges.
  Required properties:

- **stride:** .
- **radius:** .

### forsyde::io::lib::hierarchy::decision::sdf::AnalyzedActor

This enables the "annotation" of analyzed SDF actors with throughput results
from mapping and scheduling techniques.


Refines: `forsyde::io::lib::hierarchy::behavior::moc::sdf::SDFActor`

Required ports:

Required properties:

- **setThroughputInSecsDenominator:** .
- **setThroughputInSecsNumerator:** .

### forsyde::io::lib::hierarchy::decision::ConcurrentSlotsReserved

This trait is intended to describe communication reservation for communication modules
that allow slot reservations. A key example would be a TDM bus, where any data incoming from the
same processing element can share the same TDM slot.


Refines: `forsyde::io::lib::hierarchy::platform::hardware::GenericCommunicationModule`

Required ports:

Required properties:

- **slotReservations:** .

### forsyde::io::lib::hierarchy::behavior::execution::CommunicatingTask

No description exists.

Refines: `forsyde::io::lib::hierarchy::behavior::execution::Task`

Required ports:

Required properties:

- **portDataReadSize:** .
- **portDataWrittenSize:** .

### forsyde::io::lib::hierarchy::platform::hardware::HardwareModule

This trait represents any hardware element in the platform.
It does not distinguish between analog or digital.
The main purpose of this trait is to have a common "parent" trait
for all traits relevant to the hardware parts of a platform.


Refines:

Required ports:

Required properties:


### forsyde::io::lib::hierarchy::implementation::functional::RegisterArrayLike

This trait refines "RegisterLike" by ensuring that the register-like component now holds
chunkable data. In software this could mean that we have a random-access array where its entries
can be read and overwritten at will; in hardware this could mean a literal array of registers that can
be read and written at will. Be sure to also read the "RegisterLike" documentation to understand what this
is refining.



Refines: `forsyde::io::lib::hierarchy::implementation::functional::RegisterLike`

Required ports:

Required properties:

- **elementSizeInBits:** .

### forsyde::io::lib::hierarchy::behavior::moc::sy::SYSignal

An SY signal expresses a link between different SY processes in a SY network.
To ensure that the SY model is well-defined, all signals can only have one producer,
and multiple producers. This means that the consumers get a copy of whatever data is produced
onto this signal at every discrete step.


Refines: `forsyde::io::lib::hierarchy::behavior::moc::MoCEntity`

Required ports:

- **dataType**: `forsyde::io::lib::hierarchy::behavior::DataTypeLike` vertices connected by `forsyde::io::lib::hierarchy::behavior::BehaviourCompositionEdge` edges.
- **producer**: `forsyde::io::lib::hierarchy::behavior::moc::sy::SYProcess` vertices connected by `forsyde::io::lib::hierarchy::behavior::moc::sy::SYNetworkEdge` edges.
- **consumers**:  multiple `forsyde::io::lib::hierarchy::behavior::moc::sy::SYProcess` vertices connected by `forsyde::io::lib::hierarchy::behavior::moc::sy::SYNetworkEdge` edges.
  Required properties:


### forsyde::io::lib::hierarchy::decision::MemoryMapped

A MemoryMapped trait describes the mapping of any kind of a process or buffer/channel to a memory.
This is intended to be used as the result of scheduling and mapping techniques,
by "annotating" the memory mapped process.


Refines:

Required ports:

- **mappingHost**: `forsyde::io::lib::hierarchy::platform::hardware::GenericMemoryModule` vertices.
  Required properties:


### forsyde::io::lib::hierarchy::implementation::functional::RegisterLike

A RegisterLike element is one that holds a piece of data until it is overwritten.
That is, this represents a register in hardware or a memory location/variable in software.
Keep in mind that this trait assumes that all reads and writes to the register-like element
are done as a single element. For example, if this represents a NxM matrix, reading and writing
to this register-like element entails reading or writing _the entire matrix_.

For a more array-like behavior, check the "RegisterArrayLike" trait.


Refines:

Required ports:

Required properties:

- **sizeInBits:** .

### forsyde::io::lib::hierarchy::behavior::moc::sdf::SDFActor

No description exists.

Refines: `forsyde::io::lib::hierarchy::behavior::moc::MoCEntity`

Required ports:

- **combFunctions**:  multiple `forsyde::io::lib::hierarchy::behavior::BehaviourEntity` vertices connected by `forsyde::io::lib::hierarchy::behavior::BehaviourCompositionEdge` edges.
  Required properties:

- **production:** .
- **consumption:** .

### forsyde::io::lib::hierarchy::behavior::DataTypeLike

This trait is categorical and simply implies that the vertex is likely representative
of a data type, whether an array, a record, a composition of both etc.


Refines:

Required ports:

Required properties:


### forsyde::io::lib::hierarchy::behavior::parallel::Vectorizable

A Vectorizable represents an array of vector of certain data.
Sometimes we might find in the literature (or functional programming languages) a vector
of functions or routines.
This is not what this trait is about.
This trait is concerned only with encapsulating parallelizable data; whatever they might be.
The underlying model with system graphs should already be enough to have many parallel processes
side-by-side without expressing them as a parametrizable range.

If you DO NEED such parametrization, create a script in your favorite support library that will
generate the appropriate number of parallel processes.


Refines: `forsyde::io::lib::hierarchy::behavior::DataTypeLike`

Required ports:

- **producer**: `forsyde::io::lib::hierarchy::behavior::parallel::ParallelSkeleton` vertices connected by `forsyde::io::lib::hierarchy::behavior::parallel::ParallelComputationEdge` edges.
- **arrayItemType**: `forsyde::io::lib::hierarchy::behavior::DataTypeLike` vertices connected by `forsyde::io::lib::hierarchy::behavior::BehaviourCompositionEdge` edges.
- **consumers**:  multiple `forsyde::io::lib::hierarchy::behavior::parallel::ParallelSkeleton` vertices connected by `forsyde::io::lib::hierarchy::behavior::parallel::ParallelComputationEdge` edges.
  Required properties:

- **dimensions:** .

### forsyde::io::lib::hierarchy::platform::hardware::DigitalModule

This trait represents a digital hardware element in the platform.
As such, it requires the presence of an operating frequency property.


Refines: `forsyde::io::lib::hierarchy::platform::hardware::HardwareModule`

Required ports:

Required properties:

- **operatingFrequencyInHertz:** .

### forsyde::io::lib::hierarchy::platform::runtime::TimeDivisionMultiplexingRuntime

A TimeDivisionMultiplexingRuntime is capable of giving "slices" of time to the processes running on it.
The size of this slices are constant and are assumed to be contained within a certain range.
Moreover, all such runtimes also have a maximum "frame" in which it can schedule time slices for its processes.

A frame size of 0 and a maximum time slice of 0 indicate that both are unbounded.


Refines: `forsyde::io::lib::hierarchy::platform::runtime::AbstractRuntime`

Required ports:

Required properties:

- **frameSizeInClockCycles:** .
- **maximumTimeSliceInClockCycles:** .
- **timeSliceProcess:** .
- **timeSlicesSizesInClockCycles:** .
- **minimumTimeSliceInClockCycles:** .

### forsyde::io::lib::hierarchy::behavior::parallel::ReduceV

A ReduceV skeleton specifies that the output vector can be generated by aggregating
the first dimension of the input vector. Therefore, this skeleton is not really parallel,
unless the aggregation function is known to be associative.

Like this: let v = [v[1], v[2], v[3], ..., v[n]] be the input vector and o be one "scalar";
then a ReduceV with kernels f1, f2, ..., fm guarantess that:

f = (fm ... f3 . f2 . f1)
o = f(v[n], f(v[n-1], ...f(v[2], v[1])...))



Refines: `forsyde::io::lib::hierarchy::behavior::parallel::ParallelSkeleton`

Required ports:

- **kernels**:  multiple `forsyde::io::lib::hierarchy::behavior::FunctionLikeEntity` vertices connected by `forsyde::io::lib::hierarchy::behavior::BehaviourCompositionEdge` edges.
  Required properties:

- **inputArray:** .
- **outputScalar:** .

### forsyde::io::lib::hierarchy::decision::Scheduled

A Scheduled trait describes the scheduling of any kind of a process by a scheduler.
This is intended to be used as the result of scheduling and mapping techniques,
by "annotating" the scheduled process.


Refines:

Required ports:

- **runtimeHost**: `forsyde::io::lib::hierarchy::platform::runtime::AbstractRuntime` vertices.
  Required properties:


### forsyde::io::lib::hierarchy::behavior::BehaviourEntity

No description exists.

Refines:

Required ports:

Required properties:


### forsyde::io::lib::hierarchy::implementation::functional::BufferLike

A BufferLike element is one that is capable of queuing an unbounded amount of data.
This then represents a queue of "dynamic" size.


Refines:

Required ports:

Required properties:

- **elementSizeInBits:** .

### forsyde::io::lib::hierarchy::behavior::execution::ContinousStimulator

Once this trait is connected to a stimulatable element,
this element becomes perpetually read to be executed after "initialLatency" seconds.


Refines: `forsyde::io::lib::hierarchy::behavior::execution::Stimulator`

Required ports:

Required properties:

- **initialLatencyNumerator:** .
- **initialLatencyDenominator:** .

### forsyde::io::lib::hierarchy::platform::hardware::GenericProcessingModule

A GenericProcessingModule represents any digital hardware module capable of doing "compute".
Typical real things fitting to this trait abstraction are CPU Cores, GPUs and ASICs.
If the element can be abstracted into a black-box that "runs computation", this trait captures it.

This trait can also be used to represent compound processing elements, like multi-core CPUs _without_ their memories.
But beware: analysis and synthesis algorithms might expect these elements to always be "single-core-like",
so it is always good to be in sync with the assumptions of the tool using this trait.

This black-boxed level of _true_ parallelism is captured through the property `maximumComputationParallelism`,
which is 1 by default.


Refines: `forsyde::io::lib::hierarchy::platform::hardware::DigitalModule`

Required ports:

Required properties:

- **maximumComputationParallelism:** .

### forsyde::io::lib::hierarchy::behavior::parallel::ParallelSkeleton

An algorithmic parallel skeleton is a specification that explicitly
enables a computation to be done in parallel. That is, if a computation is a
certain parallel skeleton, we know that it can be implemented with the parallelization
of that skeleton. For example, for the MapV skeleton, we know that all the entries of the
output vector can be computed independently from each entry in the input vector,
inpendently if the implementation is made in sequential software, parallel software or even hardware.


Refines: `forsyde::io::lib::hierarchy::behavior::FunctionLikeEntity`

Required ports:

Required properties:


### forsyde::io::lib::hierarchy::behavior::execution::PeriodicStimulator

No description exists.

Refines: `forsyde::io::lib::hierarchy::behavior::execution::Stimulator`

Required ports:

Required properties:

- **offsetDenominator:** .
- **periodDenominator:** .
- **offsetNumerator:** .
- **periodNumerator:** .

### forsyde::io::lib::hierarchy::behavior::parallel::MapV

A MapV skeleton specifies that all the entries of the output vector can be computed
independently from each other, using each of the input vector entries.

Like this: let v = [v[1], v[2], v[3], ..., v[n]] be the input vector and o be an equivalent output vector;
then a MapV with kernels f1, f2, ..., fm guarantess that:

o[i] = fm(...f3(f2(f1(v[i])))...)

or,

o[i] = (fm ... f3 . f2 . f1) (v[i])



Refines: `forsyde::io::lib::hierarchy::behavior::parallel::ParallelSkeleton`

Required ports:

- **kernels**:  multiple `forsyde::io::lib::hierarchy::behavior::FunctionLikeEntity` vertices connected by `forsyde::io::lib::hierarchy::behavior::BehaviourCompositionEdge` edges.
  Required properties:

- **outputPorts:** .
- **inputPorts:** .

### forsyde::io::lib::hierarchy::behavior::execution::Task

No description exists.

Refines: `forsyde::io::lib::hierarchy::behavior::execution::Stimulatable`, `forsyde::io::lib::hierarchy::behavior::execution::Stimulator`

Required ports:

Required properties:


### forsyde::io::lib::hierarchy::behavior::moc::sy::SYMap

An SY Combinator is an SY process that transforms an input signals into output signals.
Or better saying, for every discrete step, the SY Comb uses its defining functions to produce
output values from the input values in each step.

In equational form, consider that we have a SYComb F with combinators f1, f2, f3, ..., fn
The output at synchronous every step k is:

output(k) = fn(...f3(f2(f1(input(k)))...)

or,

output(k) = fn . . . . . f3 . f2 . f1 (input(k))


Refines: `forsyde::io::lib::hierarchy::behavior::moc::sy::SYProcess`

Required ports:

- **combFunctions**:  multiple `forsyde::io::lib::hierarchy::behavior::FunctionLikeEntity` vertices connected by `forsyde::io::lib::hierarchy::behavior::BehaviourCompositionEdge` edges.
  Required properties:


### forsyde::io::lib::hierarchy::behavior::moc::sy::SYDelay

An SY Delay is an SY process creates a signal with one discrete step phaing.
Or better saying, for every discrete step, the created signal by SY Delay outputs the value of its inputs
signal from the previous discrete step.

In equational form, consider that we have a SYDelay F connecting `input` to `output`.
The output at synchronous every step k is:

output(k) = input(k-1)


Refines: `forsyde::io::lib::hierarchy::behavior::moc::sy::SYProcess`

Required ports:

- **input**: `forsyde::io::lib::hierarchy::behavior::moc::sy::SYSignal` vertices connected by `forsyde::io::lib::hierarchy::behavior::moc::sy::SYNetworkEdge` edges.
- **delayed**: `forsyde::io::lib::hierarchy::behavior::moc::sy::SYSignal` vertices connected by `forsyde::io::lib::hierarchy::behavior::moc::sy::SYNetworkEdge` edges.
  Required properties:


### forsyde::io::lib::hierarchy::platform::hardware::GenericCommunicationModule

A GenericCommunicationModule represents any digital hardware module capable of doing "communication".
Typical real things fitting to this trait abstraction are buses, crossbar switches, routers.
If the element can be abstracted into a black-box that "transmits data", this trait captures it.

Currently, this trait does not tell anything about the arbitration that the communication element performs.
If no information is avaiable to specialize the arbitration scheme used at a hardware level, any tool working
with this trait can assume that a fair round-robin is used.



Refines: `forsyde::io::lib::hierarchy::platform::hardware::DigitalModule`

Required ports:

Required properties:


### forsyde::io::lib::hierarchy::behavior::execution::Downsample

No description exists.

Refines: `forsyde::io::lib::hierarchy::behavior::execution::Stimulatable`, `forsyde::io::lib::hierarchy::behavior::execution::Stimulator`

Required ports:

Required properties:

- **repetitivePredecessorSkips:** .
- **initialPredecessorSkips:** .

### forsyde::io::lib::hierarchy::implementation::functional::InstrumentedBehaviour

No description exists.

Refines: `forsyde::io::lib::hierarchy::behavior::BehaviourEntity`

Required ports:

Required properties:

- **computationalRequirements:** .
- **maxSizeInBits:** .

### forsyde::io::lib::hierarchy::implementation::synthetizable::PeriodicTask

This simple refinement stores the amount of simply periodic
sub tasks that a task can generate given its incoming stimulators.


Refines: `forsyde::io::lib::hierarchy::behavior::execution::Task`

Required ports:

- **incomingStimulators**:  multiple `forsyde::io::lib::hierarchy::behavior::execution::PeriodicStimulator` vertices.
  Required properties:

- **offsetNumerators:** .
- **offsetDenominators:** .
- **periodDenominators:** .
- **periodNumerators:** .

### forsyde::io::lib::hierarchy::platform::runtime::SuperLoopRuntime

A super loop runtime captures the entry-point for programmable devices that have no runtime at all, i.e. almost or completely bare-metal.
These runtimes will generally represent "superloop" approaches, where the processes being scheduled are inside
a big while loop that runs forever, executing the processes unconditionally.
This does not exclude the fact that a process might stall its processing element while waiting for data or any
other activation condition.


Refines: `forsyde::io::lib::hierarchy::platform::runtime::AbstractRuntime`

Required ports:

Required properties:

- **superLoopEntries:** .

### forsyde::io::lib::hierarchy::behavior::execution::Stimulatable

No description exists.

Refines:

Required ports:

- **activators**:  multiple `forsyde::io::lib::hierarchy::behavior::execution::Stimulator` vertices connected by `forsyde::io::lib::hierarchy::behavior::execution::EventEdge` edges.
  Required properties:

- **hasORSemantics:** .

### forsyde::io::lib::hierarchy::platform::hardware::Structure

A structure is simply a collection of platform elements without
any specific meaning. It is helpful to create hierarchies in the platform
for "categorization" but it does not imply any semantic hierarchy by itself.

Structures have outgoing edges to its children, or contained, hardware modules.


Refines: `forsyde::io::lib::hierarchy::platform::hardware::HardwareModule`

Required ports:

- **containedModules**:  multiple `forsyde::io::lib::hierarchy::platform::hardware::HardwareModule` vertices connected by `forsyde::io::lib::hierarchy::platform::hardware::StructuralContainment` edges.
  Required properties:


### forsyde::io::lib::hierarchy::platform::hardware::InstrumentedCommunicationModule

A InstrumentedGenericCOmmunicationModule enriches `GenericCommunicationModule` with provision numbers so that
an analysis and synthesis tool is able to estimate the total amount of traversal time is required to transmit
a bunch of data through this communication element.

You can assume that analysis and synthesis tools will compute the traversal time by:

traversalTime(dataSize) = initialLatency
+ ceil(dataSize / flitSizeInBits) * (maxCyclesPerFlit / maxConcurrentFlits) / elementFrequency



Refines: `forsyde::io::lib::hierarchy::platform::hardware::GenericCommunicationModule`

Required ports:

Required properties:

- **maxCyclesPerFlit:** .
- **flitSizeInBits:** .
- **initialLatency:** .
- **maxConcurrentFlits:** .

### forsyde::io::lib::hierarchy::platform::hardware::GenericMemoryModule

A GenericMemoryModule represents any digital hardware module capable of "storing data".
Typical real things fitting to this trait abstraction are on-chip memories, DRAMs, SDRAMs etc.
If the element can be abstracted into a black-box that "stores data", this trait captures it.

This trait _does not_ imply any arbitration scheme for parallel accesses to it.
Therefore, if there are two elements connecting to this memory module, one should put the memory module
behind a `GenericCommunicationModule` (or refined) module.
For the sake of avoiding ambiguity, tools consuming this trait can raise an "illegal" status if there are
multiple modules connecting to a memory module without going through a communication module first.

If a `GenericMemoryModule` is connected directly to a `GenericProcessingModule` (or refined), it is implied
that this memory element is tightly-coupled to the processing element.



Refines: `forsyde::io::lib::hierarchy::platform::hardware::DigitalModule`

Required ports:

Required properties:

- **spaceInBits:** .

### forsyde::io::lib::hierarchy::platform::runtime::AbstractRuntime

An AbstractRuntime element can be anything that is acting as a "middleware" for the platform and therefore
should be refined to be better useful.

In any case, a runtime must have a "host" which executes its instructions and also processing elements
which it manages, which means that they are part of the runtime's controlled processing elements.


Refines:

Required ports:

- **managed**:  multiple `forsyde::io::lib::hierarchy::platform::hardware::GenericProcessingModule` vertices.
- **host**: `forsyde::io::lib::hierarchy::platform::hardware::GenericProcessingModule` vertices.
  Required properties:


### forsyde::io::lib::hierarchy::behavior::execution::LoopingTask

No description exists.

Refines: `forsyde::io::lib::hierarchy::behavior::execution::Task`

Required ports:

- **loopSequence**:  multiple `forsyde::io::lib::hierarchy::behavior::BehaviourEntity` vertices.
- **initSequence**:  multiple `forsyde::io::lib::hierarchy::behavior::BehaviourEntity` vertices.
  Required properties:


### forsyde::io::lib::hierarchy::behavior::basic::BasicOperation

No description exists.

Refines: `forsyde::io::lib::hierarchy::behavior::BehaviourEntity`

Required ports:

Required properties:


### forsyde::io::lib::hierarchy::visualization::VisualizableWithProperties

No description exists.

Refines: `forsyde::io::lib::hierarchy::visualization::Visualizable`

Required ports:

Required properties:

- **visualizedPropertiesNames:** .

### forsyde::io::lib::hierarchy::behavior::moc::MoCEntity

No description exists.

Refines:

Required ports:

Required properties:


### forsyde::io::lib::hierarchy::platform::hardware::InstrumentedProcessingModule

A InstrumentedProcessingModule enriches `GenericProcessingModule` with provision numbers so that
an analysis and synthesis tool is able to estimate the total amount of execution time is required to execute
a bunch of instructions in this processing element.

If this processing element exhibits higher level of parallelism (see `GenericProcessingModule`), then the provisions
should always be provided _per parallel "thread"_.
For example, if the processing element is a typical dual-core, the model instructions per cycle property
should be as the intructions per cycle _per core_, not their summed total.


Refines: `forsyde::io::lib::hierarchy::platform::hardware::GenericProcessingModule`

Required ports:

Required properties:

- **modalInstructionsPerCycle:** .

### forsyde::io::lib::hierarchy::behavior::execution::Stimulator

No description exists.

Refines:

Required ports:

- **activated**:  multiple `forsyde::io::lib::hierarchy::behavior::execution::Stimulatable` vertices connected by `forsyde::io::lib::hierarchy::behavior::execution::EventEdge` edges.
  Required properties:


### forsyde::io::lib::hierarchy::visualization::GreyBox

No description exists.

Refines: `forsyde::io::lib::hierarchy::visualization::Visualizable`

Required ports:

- **contained**:  multiple `forsyde::io::lib::hierarchy::visualization::Visualizable` vertices connected by `forsyde::io::lib::hierarchy::visualization::VisualContainment` edges.
  Required properties:


### forsyde::io::lib::hierarchy::behavior::moc::sy::SYProcess

A SY process is either a SY combination or a SY delay.
This is enough to build _any_ single-rate synchronous system, no matter how unwieldy.


Refines: `forsyde::io::lib::hierarchy::behavior::moc::MoCEntity`

Required ports:

Required properties:

- **outputPorts:** .
- **inputPorts:** .

### forsyde::io::lib::hierarchy::implementation::code::HasLLVMIRImplementations

This trait enforces the vertex to have at least one inlined LLVM implementation.

There can be more than one, discriminted by a label for each inlined source code.
For example, a vertex can have a chunck of code with the label "riscv" for RISC-V
based processors and one with label "cude" to be implemented directly in a CUDA
based flow, whenever possible.



Refines:

Required ports:

Required properties:

- **outputArgumentPorts:** .
- **returnPort:** .
- **inlinedLLVMIR:** .
- **inputArgumentPorts:** .

### forsyde::io::lib::hierarchy::behavior::basic::Plus

No description exists.

Refines: `forsyde::io::lib::hierarchy::behavior::basic::BasicOperation`

Required ports:

- **outputs**:  multiple `forsyde::io::lib::hierarchy::behavior::DataTypeLike` vertices.
- **inputs**:  multiple `forsyde::io::lib::hierarchy::behavior::DataTypeLike` vertices.
  Required properties:


### forsyde::io::lib::hierarchy::behavior::execution::Upsample

No description exists.

Refines: `forsyde::io::lib::hierarchy::behavior::execution::Stimulatable`, `forsyde::io::lib::hierarchy::behavior::execution::Stimulator`

Required ports:

Required properties:

- **initialPredecessorHolds:** .
- **repetitivePredecessorHolds:** .

### forsyde::io::lib::hierarchy::platform::runtime::FixedPriorityScheduledRuntime

As the name implies, runtimes with the "FixedPriorityScheduledRuntime" trait can schedule its processes
following a fixed priority policy. Whether the runtime allows for preemption or not, is a property of components
satisfying this trait.

If you want to represent the presence of overheads for runtimes satisfying this trait, you should add
the "InstrumentedBehaviour" to a component that has "FixedPriorityScheduledRuntime".



Refines: `forsyde::io::lib::hierarchy::platform::runtime::AbstractRuntime`

Required ports:

Required properties:

- **minimumActivationInSecsNumerator:** .
- **minimumActivationInSecsDenominator:** .
- **allowsInterCoreMigration:** .
- **priorityAssignments:** .
- **supportsPreemption:** .


