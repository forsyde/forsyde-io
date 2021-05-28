module Models

import LightGraphs

VertexIdxType = UInt

abstract type Trait end
abstract type VertexTrait <: Trait end
abstract type EdgeTrait <: Trait end

refines(t1::Trait, t2::Trait) = false

struct Vertex
    id::String
    ports::Set{String}
    properties::Dict{String,Any}
    vertex_traits::Set{VertexTrait}
end

Vertex(id::String) = Vertex(id, Set{String}(), Dict{String,Any}(), Set{Trait}())

Vertex(id::String, ports::Set{String}) = Vertex(id, ports, Dict{String,Any}(), Set{Trait}())

==(v1::Vertex, v2::Vertex) = v1.id == v2.id

hash(v::Vertex) = hash(v.id)

struct Edge
    source::Base.RefValue{Vertex}
    target::Base.RefValue{Vertex}
    source_port::Union{Nothing,String}
    target_port::Union{Nothing,String}
    edge_traits::Set{EdgeTrait}
end

==(e1::Edge, e2::Edge) = (e1.source == e2.source && 
                          e1.target == e2.target && 
                          e1.source_port == e2.source_port && 
                          e1.target_port == e2.target_port && 
                          e1.edge_type == e2.edge_type)

mutable struct ForSyDeModel <: LightGraphs.AbstractGraph{Vertex}
    vertexes::Vector{Vertex}
    vertexes_lut::Dict{String,Vertex}
    edges::Vector{Edge}
    edges_lut::Dict{Tuple{String,String},Vector{Edge}}
    # edges_lut::AbstractDict{Tuple{VertexIdxType,VertexIdxType},AbstractArray{UInt}}
    # inv_edges_lut::AbstractDict{UInt,Tuple{VertexIdxType,VertexIdxType}}
    inedges_lut::Dict{String,Vector{Edge}}
    outedges_lut::Dict{String,Vector{Edge}}
end # struct

# Minimum interface required by LightGraphs

ForSyDeModel() = ForSyDeModel(Vertex[], Dict{String,Vertex}(), Edge[], Dict{Tuple{String,String},Vector{Edge}}(), Dict{String,Vector{Edge}}(), Dict{String,Vector{Edge}}())

# LightGraphs interfaces

LightGraphs.dst(e::Edge) = e.target[]

LightGraphs.src(e::Edge) = e.source[]

LightGraphs.zero(::Type{<:ForSyDeModel}) = ForSyDeModel()

LightGraphs.zero(model::ForSyDeModel) = ForSyDeModel()

LightGraphs.edges(model::ForSyDeModel) = model.edges

LightGraphs.vertices(model::ForSyDeModel) = model.vertexes

LightGraphs.edgetype(model::ForSyDeModel) = Edge

LightGraphs.has_edge(model::ForSyDeModel, s::Vertex, t::Vertex) = any(src(e) == s && dst(e) == t for e in model.edges) 

LightGraphs.has_vertex(model::ForSyDeModel, v::Vertex) = haskey(model.vertexes_lut, v)

LightGraphs.inneighbors(model::ForSyDeModel, v::Vertex) = [src(e) for e in model.inedges_lut[v.id]]

LightGraphs.is_directed(::Type{<:ForSyDeModel}) = true

LightGraphs.is_directed(model::ForSyDeModel) = true

LightGraphs.outneighbors(model::ForSyDeModel, v::Vertex) = [dst(e) for e in model.outedges_lut[v.id]]

LightGraphs.ne(model::ForSyDeModel) = length(model.edges)

LightGraphs.nv(model::ForSyDeModel) = length(model.vertexes)

# Additional functions for model CRUD

## Iterable interface
Base.iterate(model::ForSyDeModel) = iterate(model.vertexes)

Base.length(model::ForSyDeModel) = nv(model)

function Base.push!(model::ForSyDeModel, vs::Vararg{<:Vertex})
    for v in vs
        if haskey(model.vertexes_lut, v.id)
            return false
        end
        push!(model.vertexes, v)
        model.vertexes_lut[v.id] = v
    end
    return model
end # function

Base.getindex(model::ForSyDeModel, key::String) = model.vertexes_lut[key]


# Base.getindex(model::ForSyDeModel, keys::Vararg{String}) = (
#     model.vertexes_lut[k] for k in keys if haskey(model.vertexes_lut, k)
# )

Base.firstindex(model::ForSyDeModel) = first(keys(model.vertexes_lut))

Base.lastindex(model::ForSyDeModel) = last(keys(model.vertexes_lut))

Base.get(model::ForSyDeModel, key::String, default) = get(model.vertexes_lut, key, default)

function Base.push!(model::ForSyDeModel, es::Vararg{<:Edge})
    for e in es
        s = LightGraphs.src(e)
        t = LightGraphs.dst(e)
        push!(model.edges, e)
        if !haskey(model.edges_lut, (s.id, t.id))
            model.edges_lut[(s.id, t.id)] = Edge[]
        end
        if !haskey(model.inedges_lut, t.id)
            model.inedges_lut[t.id] = Edge[]
        end
        if !haskey(model.outedges_lut, s.id)
            model.outedges_lut[s.id] = Edge[]
        end
        push!(model.edges_lut[(s.id, t.id)], e)
        push!(model.outedges_lut[s.id], e)
        push!(model.inedges_lut[t.id], e)
    end
    return model
end


Base.getindex(model::ForSyDeModel, source::Vertex, target::Vertex) = getindex(model, source.id, target.id)

Base.getindex(model::ForSyDeModel, source::String, target::String) = get(model, (source, target), Edge[])

Base.getindex(model::ForSyDeModel, st::Tuple{Vertex,Vertex}) = get(model, st, Edge[])

Base.get(model::ForSyDeModel, key::Tuple{String,String}, default) = get(model.edges_lut, key, default)

include("Models/Traits.jl")

end # module
