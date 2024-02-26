# LibForSyDe quick start

LibForSyDe is the trait hierarchy developed in the timeframe of this thesis to support the MDE activities of the ForSyDe project.

> As of 2024, the most developed and well-maintained set of traits in this hierarchy are those relating to DSE, given its use in IDeSyDe.
However, substantial effort was put into capturing ForSyDe's synchronous MoC and SDF MoC on a granularity that is useful not only for DSE but also for code generation.

Currently, LibForSyDe contains over 70 traits.
This list is continuously updated as LibForSyDe is extended to cover more design activities of the ForSyDe project.
The LibForSyDe trait hierarchy is available in the ForSyDe IO Java support library,
and its self-generated documentation can be found in [the LibForSyDe catalog webpage](https://forsyde.github.io/forsyde-io/usage/catalog).

here we discuss a subset of subhierarchies that have non-trivial assumptions on how they expect
the system graph to be constructed, as well as giving a starting point for ForSyDe IO LibForSyDe.
From now on, it is assumed you have read and gone through the [general ForSyDe IO quick start](https://forsyde.github.io/forsyde-io/usage).

## Registering LibForSyDe

As presented in [the general ForSyDe IO quick start](https://forsyde.github.io/forsyde-io/usage), the model handler
is one of the API points that the user must interact with for most model facilities.
Therefore, to register the traits, validators, migrations etc of LibForSyDe in the common model framework, we must register
whichever such elements are there.
Luckily, LibForSyDe already provides a shortcut to make life easier in this regard:

    // ... all the imports, initialisation etc.
    ModelHandler modelHandler = new ModelHandler();
    LibForSyDeModelHandler.registerLibForSyDe(modelHandler);
    // ... now you can automatically benefit from all validators, migrations etc provided by LibForSyDe
    SystemGraph graph = modelHandler.loadModel("path/to/toy_sdf_tiny.fiodl");
    // ...

Under the hood, the `LibForSyDeModelHandler.registerLibForSyDe` static call is simply (as of February 2024):

    modelHandler.registerTraitHierarchy(new ForSyDeHierarchy());
    modelHandler.registerSystemGraphMigrator(new TraitNamesFrom0_6To0_7());
    //...
    modelHandler.registerValidation(new SDFValidator());
    //...
    modelHandler.registerInference(new ComputationalRequirementsPropagator());
    //...

## The execution subhierarchy

The execution subhierarchy captures periodic and communication workloads .
This execution model separates communication and stimulus, but provides constructs to express many-to-many stimulation relationships.
These constructs are heavily inspired by [the App4mc meta-model AMALTHEA](https://eclipse.dev/app4mc/), and the [rubus component model](https://www.arcticus-systems.com/).
We discuss this subhierarchy with an illustrate example, shown below.

<img src="{{ site.baseurl }}/assets/images/svg/execution-hierarchy.svg" />

Three vertexes from the example are shown in their system graph explicit visualisation format: the 15 milliseconds periodic clock, Task 4 and Buffer 3.
The relationship between the compact and explicit visualisations is drawn as color-coded concentric circles.

Note that tasks do not have periods themselves.
Instead, the tasks must be part of a stimulus chain that has a periodic stimulus at its source, drawn as alarm clocks.
This stimulus chain allows for upsampling and downsampling operations, as exemplified by the downsample arrow,
and direct connections between tasks, where the target task simply inherits the stimulus of the source task.

Second, and most importantly, two constructs for many-to-many stimulus relationships exist, as seen in Task 4:
stimulatable elements have either **AND** or **OR** semantics; these names are taken directly from App4mc.
Task 4 has OR semantics, meaning that the all incoming stimuli are maintained as they are and propagated in a fan-out manner to Tasks 5 and 6;
that is, Tasks 5 and 6 receive the stimulis chains coming from Tasks 3 and 2 without any modification.
If Task 4 had AND semantics, then its behaviour is the best periodic fit so that all incoming stimuli are synchronized.
For instance, Task 4 has incoming stimuli of 15 milliseconds and 20 milliseconds, then, the best periodic fit is a period of 20 milliseconds with a relative deadline of 5 milliseconds.
This best fit is then propagated to Tasks 5 and 6.
Observe that **AND** semantics might cause data to be lost: the best fit shown causes the data produced by an invocation of Task 2 to be lost.

## The functional implementation subhierarchy

The functional implementation subhierarchy has the key role of capturing instrumentation data required for mapping and scheduling analysis within DSE solutions in a separation-of-concerns manner.
More precisely, the traits in this subhierarchy capture instrumentation data that has no**a priori** dependencies on the potential target platforms.
In general, these requirements is captured in a two-level dictionary: a set of possible implementations is associated with each process, and a set of numerical requirements is associated with each implementation.
For example, suppose that a process has three possible software implementations: an implementation in CUDA and an implementation in ANSI-C.

    vertex process [...InstrumentedSoftwareBehaviour...] (...) {
        ...
        "computationalRequirements": {
            "ANSI-C": {
                "intadd": 4,
                "floatadd": 10,
                "branch": 200,
                ...
            },
            "CUDA": {
                "kerneladd": 5,
                ...
            }
        },
        "maxSizeInBits": {
            "ANSI-C": 8196,
            "CUDA": 16372
        }
        ...
    }

Semantically, the higher the computational requirement for an element of an implementation, the more demanding this implementation will be.
This can be understood intuitively through the example:
if there is a second \texttt{ANSI-C-2} implementation that requires 200 integer additions instead of \texttt{ANSI-C}'s 4 integer additions,
we naturally expect \texttt{ANSI-C-2} to be comparatively slower than \texttt{ANSI-C} regardless of the target platform.

In terms of hardware implementations, a similar trait exists that better captures the characteristics of hardware process implementations.
These characteristics are the performance numbers attained by the implementation and the resource requirements of the implementation.
For example, suppose that a process has two possible hardware implementations: an FPGA implementation and an SiLago implementation.
A possible set of requirements and performance numbers for this process is shown in \Cref{fig:libforsyde-functional-implementation-reqs-hw}.

    vertex process [...InstrumentedHardwareBehaviour...] (...) {
        ...
        resourceRequirements: {
            "FPGA": {
                "LUTS": 10,
                "gates" 100,
                ...
            },
            "SiLago": {
                "FFTFIMP": 1,
                ...
            }
        },
        latencyInSecsNumerators: {
            "FPGA": 12,
            ...
        },
        latencyInSecsDenominators: {
            "FPGA": 1000000000000,
            ...
        }
        ...
    }

Like its software implementation counterpart, the higher the resource requirement for an element of an implementation, the more demanding this implementation will be.
However, this applies only to resources: the performance is free-form and a more resource-intensive implementation can be dominated in terms of performance numbers, if they are comparable.

## The hardware and runtime subhierarchies

The platform model captured in the hardware and runtime subhierarchies within LibForSyDe does not have any mandatory **semantical hierarchy**.
For example, the trait `Structure` contains a set of other structures and hardware modules, but it does not entail any
mandatory physical or logical constraint at the moment. Naturally, it does imply that its contained elements are physically related,
but no DSE solution developed in this thesis requires or can benefit from this implication.

The traits that capture runtime elements must be connected with their host hardware processing elements and with their controlled processing elements.
This separation between hardware and runtime elements enables the same hardware model to be re-used with different types of runtimes.
Another possible way to capture runtime information, i.e., the software components of a platform, is to create runtimes as refinements of hardware processing elements.
This alternative is used for the communication elements traits, as experience with the case studies involving IDeSyDe have shown that the interconnect cannot
be easily configured; thus, it is practical that the *runtime* aspects of a communication element are represented through refinements of hardware elements.
Processing elements, on the other hand, typically enjoy much easier configurability by virtue of their programmability.
Moreover, most runtime elements captured in these subhierarchies do represent subsystems built with software, like RTOS, so
it is practical that they are represented as an explicit vertex.

<img src="{{ site.baseurl }}/assets/images/svg/shared_mem_platform_with_runtime.svg" />

Consider the example shown with added explicit runtime elements.
There are three types of runtimes in this example.
First, the GPU is scheduled with a configurable TDM schedule.
Second, the Core A CPU are globally scheduled by a single fixed-priority scheduler that is hosted by the first Core A element.
Hosted in this sense is to be understood as execution.
Third and last, each core B has its own separated fixed-priority scheduler, that is, they follow a partitioned scheduling policy.
Observe that the solutions within IDeSyDe do not admit global schedulers;
this is currently enforced through the identification rules.
