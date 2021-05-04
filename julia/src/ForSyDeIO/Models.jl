module Models

using LightGraphs

VertexIdxType = UInt

abstract type AbstractVertexTrait end
abstract type AbstractVertexType end
abstract type AbstractEdgeType end

struct Vertex{Ts <: Set{<:AbstractVertexTrait}}
    id::String
    ports::Set{String}
    properties::Dict{String,Any}
    vertex_traits::Ts
end

struct OpaqueVertexType <: AbstractVertexType end

Vertex(id::String) = Vertex(id, Set{String}(), Dict{String,Any}(), Set{AbstractVertexTrait}())

Vertex(id::String, ports::Set{String}) = Vertex(id, ports, Dict{String,Any}(), Set{AbstractVertexTrait}())

==(v1::Vertex, v2::Vertex) = v1.id == v2.id

hash(v::Vertex) = hash(v.id)

struct Edge{V <: Vertex,P <: Union{<:String,Nothing}}
    source::V
    target::V
    source_port::P
    target_port::P
    edge_type::AbstractEdgeType
end

==(e1::Edge, e2::Edge) = (e1.source == e2.source && 
                          e1.target == e2.target && 
                          e1.source_port == e2.source_port && 
                          e1.target_port == e2.target_port && 
                          e1.edge_type == e2.edge_type)

dst(e::Edge) = e.target

src(e::Edge) = e.source

mutable struct ForSyDeModel{V <: Vertex,VArray <: AbstractArray{V},EArray <: AbstractArray{<:Edge}} <: AbstractGraph{V}
    vertexes::VArray
    edges::EArray
    vertexes_inv::Dict{Vertex,Int}
    edges_inv::Dict{Tuple{Vertex,Vertex},EArray}
    # edges_idx::AbstractDict{Tuple{VertexIdxType,VertexIdxType},AbstractArray{UInt}}
    # inv_edges_idx::AbstractDict{UInt,Tuple{VertexIdxType,VertexIdxType}}
    inedges_idx::Dict{Vertex,EArray}
    outedges_idx::Dict{Vertex,EArray}
end # struct

# Minimum interface required by LightGraphs

ForSyDeModel() = ForSyDeModel(Vertex[], Edge[], Dict{Vertex,Int}(), Dict{Tuple{Vertex,Vertex},Vector{Edge}}(), Dict{Vertex,Vector{Edge}}(), Dict{Vertex,Vector{Edge}}())

zero(::Type{<:ForSyDeModel}) = ForSyDeModel()

zero(model::ForSyDeModel) = ForSyDeModel()

edges(model::ForSyDeModel) = model.edges

vertices(model::ForSyDeModel) = model.vertexes

edgetype(model::ForSyDeModel) = Edge

has_edge(model::ForSyDeModel, s::Vertex, t::Vertex) = any(src(e) == s && dst(e) == t for e in model.edges) 

has_vertex(model::ForSyDeModel, v::Vertex) = haskey(model.vertexes_inv, v)

inneighbors(model::ForSyDeModel, v::Vertex) = [src(e) for e in model.inedges_idx[v]]

is_directed(::Type{<:ForSyDeModel}) = true

is_directed(model::ForSyDeModel) = true

outneighbors(model::ForSyDeModel, v::Vertex) = [dst(e) for e in model.outedges_idx[v]]

ne(model::ForSyDeModel) = length(model.edges)

nv(model::ForSyDeModel) = length(model.vertexes)

# Additional functions for model CRUD

function add_vertex!(model::ForSyDeModel, v::Vertex)
    if haskey(model.vertexes_inv, v)
        return false
    end
    push!(model.vertexes, v)
    model.vertexes_inv[v] = length(model.vertexes)
    return true
end # function

function add_edge!(model::ForSyDeModel, s::Vertex, t::Vertex, e::Edge)
    if haskey(model.edges_inv, (s, t)) && any(e == st for st in model.edges_inv[(s, t)])
        return false
    end
    push!(model.edges, e)
    if model.edges_inv[(s, t)] === nothing
        model.edges_inv[(s, t)] = Edge[]
    end
    if model.inedges_idx[t] === nothing
        model.inedges_idx[t] = Edge[]
    end
    if model.outedges_idx[s] === nothing
        model.outedges_idx[s] = Edge[]
    end
    push!(model.edges_inv[(s, t)], e)
    push!(model.outedges_idx[s], e)
    push!(model.inedges_idx[t], e)
    return true
end # function

end # module
