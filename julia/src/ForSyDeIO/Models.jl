__precompile__()
module Models

include("Models/Traits.jl")

import LightGraphs

using ForSyDeIO.Models.Traits

abstract type PropertyStruct end

const PropertyValues = Union{Int, Float32, Float64, String, Bool}

const PropertyElement = Union{PropertyStruct, PropertyValues}

struct PropertyArray <: PropertyStruct
    wrapped_vector::Vector{PropertyElement}
end

iterate(v::PropertyArray) = Base.iterate(v.wrapped_vector)

getindex(v::PropertyArray, idx...) = getindex(v.wrapped_vector, idx...)

setindex!(v::PropertyArray, X, idx...) = setindex!(v.wrapped_vector, X, idx...)
struct PropertyDict <: PropertyStruct
    wrapped_dict::Dict{String, PropertyElement}
end

getindex(d::PropertyDict, idx...) = getindex(d.wrapped_dict, idx...)

setindex!(d::PropertyDict, X, idx...) = setindex!(d.wrapped_dict, X, idx...)

get(d::PropertyDict, k, default) = get(d.wrapped_dict, k, default)

struct Vertex
    id::String
    ports::Set{String}
    properties::Dict{String,PropertyElement}
    vertex_traits::Set{VertexTrait}
end

Vertex(id::String) = Vertex(id, Set{String}(), Dict{String,Any}(), Set{Trait}())

Vertex(id::String, ports::Set{String}) = Vertex(id, ports, Dict{String,Any}(), Set{Trait}())

isequal(v1::Vertex, v2::Vertex) = v1.id == v2.id

hash(v::Vertex) = hash(v.id)

struct Edge
    source::Base.RefValue{Vertex}
    target::Base.RefValue{Vertex}
    source_port::Union{Nothing,String}
    target_port::Union{Nothing,String}
    edge_traits::Set{EdgeTrait}
end

isequal(e1::Edge, e2::Edge) = (e1.source == e2.source && 
                          e1.target == e2.target && 
                          e1.source_port == e2.source_port && 
                          e1.target_port == e2.target_port && 
                          e1.edge_type == e2.edge_type)

mutable struct ForSyDeModel <: LightGraphs.AbstractGraph{Vertex}
    vertexes::Vector{Vertex}
    edges::Vector{Edge}
    # properties::Vector{Dict{String, PropertyElement}}
    # edges_lut::Dict{Tuple{String,String},Vector{Edge}}
    # edges_lut::AbstractDict{Tuple{VertexIdxType,VertexIdxType},AbstractArray{UInt}}
    # inv_edges_lut::AbstractDict{UInt,Tuple{VertexIdxType,VertexIdxType}}
    # inedges_lut::Dict{String,Vector{Edge}}
    # outedges_lut::Dict{String,Vector{Edge}}
end # struct

# Minimum interface required by LightGraphs

ForSyDeModel() = ForSyDeModel(Vertex[], Edge[])#, Dict{String, PropertyElement}[])

# LightGraphs interfaces

dst(e::Edge) = LightGraphs.dst(e::Edge)

src(e::Edge) = LightGraphs.src(e::Edge)

LightGraphs.dst(e::Edge) = e.target[]

LightGraphs.src(e::Edge) = e.source[]

LightGraphs.zero(::Type{<:ForSyDeModel}) = ForSyDeModel()

LightGraphs.zero(model::ForSyDeModel) = ForSyDeModel()

LightGraphs.edges(model::ForSyDeModel) = model.edges

LightGraphs.vertices(model::ForSyDeModel) = model.vertexes

LightGraphs.edgetype(model::ForSyDeModel) = Edge

LightGraphs.has_edge(model::ForSyDeModel, s::Vertex, t::Vertex) = any(Base.isequal(src(e),s) && Base.isequal(dst(e),t) for e in model.edges) 

LightGraphs.has_vertex(model::ForSyDeModel, v::Vertex) = any(Base.isequal(vert, v) for vert in model.vertexes)

LightGraphs.inneighbors(model::ForSyDeModel, v::Vertex) = (src(e) for e in model.edges if Base.isequal(dst(e),v))

LightGraphs.is_directed(::Type{<:ForSyDeModel}) = true

LightGraphs.is_directed(model::ForSyDeModel) = true

LightGraphs.outneighbors(model::ForSyDeModel, v::Vertex) = (dst(e) for e in model.edges if Base.isequal(src(e),v))

LightGraphs.ne(model::ForSyDeModel) = length(model.edges)

LightGraphs.nv(model::ForSyDeModel) = length(model.vertexes)

# Additional functions for model CRUD

## Iterable interface
Base.iterate(model::ForSyDeModel) = Base.iterate(model.vertexes)
Base.iterate(model::ForSyDeModel, state) = Base.iterate(model.vertexes, state)

Base.length(model::ForSyDeModel) = nv(model)

function Base.push!(model::ForSyDeModel, vs::Vararg{V}) where V <: Vertex
    for v in vs
        Base.push!(model.vertexes, v)
    end
    return model
end # function

Base.getindex(model::ForSyDeModel, key::String) = first(v for v in model.vertexes if Base.isequal(v.id,key))


# Base.getindex(model::ForSyDeModel, keys::Vararg{String}) = (
#     model.vertexes_lut[k] for k in keys if haskey(model.vertexes_lut, k)
# )

Base.firstindex(model::ForSyDeModel) = firstindex(model.vertexes)

Base.lastindex(model::ForSyDeModel) = lastindex(model.vertexes)

function Base.push!(model::ForSyDeModel, es::Vararg{E}) where E <: Edge
    for e in es
        Base.push!(model.edges, e)
    end
    return model
end


Base.getindex(model::ForSyDeModel, source::Vertex, target::Vertex) = getindex(model, source.id, target.id)

Base.getindex(model::ForSyDeModel, source::String, target::String) = get(model, (source, target), Edge[])

Base.getindex(model::ForSyDeModel, st::Tuple{Vertex,Vertex}) = get(model, st, Edge[])

Base.get(model::ForSyDeModel, key::Tuple{String,String}, default) = get(model.edges, key, default)

end # module
