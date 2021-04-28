module Core 

using LightGraphs

VertexIdType = UInt

abstract type Vertex end

abstract type Edge{V <: Vertex} end

mutable struct ForSyDeModel{V <: Vertex,E <: Edge} <: AbstractGraph
    vertexes::AbstractArray{V}
    edges::AbstractArray{E}
    vertexes_idx::Dict{VertexIdType,UInt}
    edges_idx::Dict{Tuple{VertexIdType,VertexIdType},AbstractArray{UInt}}
    inv_edges_idx::Dict{UInt,Tuple{VertexIdType,VertexIdType}}
    inedges_idx::Dict{VertexIdType,AbstractArray{UInt}}
    outedges_idx::Dict{VertexIdType,AbstractArray{UInt}}
end # struct

# Minimum interface required by LightGraphs

ForSyDeModel() = ForSyDeModel(List(), List(), Dict(), Dict(), Dict(), Dict(), Dict())

zero(::Type{<:ForSyDeModel}) = ForSyDeModel()

zero(model::ForSyDeModel) = ForSyDeModel()

edges(model::ForSyDeModel) = model.vertexes

vertices(model::ForSyDeModel) = model.vertexes

edgetype(model::ForSyDeModel) = Edge

has_edge(model::ForSyDeModel, s::Vertex, t::Vertex) = length(model.edges_idx[(hash(s), hash(t))]) > 0

has_vertex(model::ForSyDeModel, v::Vertex) = haskey(model.vertexes_idx, hash(v))

inneighbors(model::ForSyDeModel, v::Vertex) = {edges[i] for i in model.inedges_idx[hash(v)]}

is_directed(::Type{<:ForSyDeModel}) = true

is_directed(model::ForSyDeModel) = true

outneighbors(model::ForSyDeModel, v::Vertex) = {edges[i] for i in model.outedges_idx[hash(v)]}

ne(model::ForSyDeModel) = length(model.edges)

nv(model::ForSyDeModel) = length(model.vertexes)

# Additional functions for model CRUD

function add_vertex!(model::ForSyDeModel, v::V <: Vertex)
    if haskey(model.vertexes_idx, hash(v))
        return false
    end
    push!(model.vertexes, v)
    model.vertexes_idx[hash(v)] = length(model.vertexes)
    return true
end # function

function add_edge!(model::ForSyDeModel, s::Vertex, t::Vertex, e::Edge)
    if haskey(model.edges_idx, (hash(s), hash(t))) && 
        any(e == st for st in model.edges[idx] for idx in model.edges_idx[(hash(s), hash(t))])
        return false
    end
    push!(model.edges, e)
    push!(model.edges_idx[(hash(s), hash(t))], length(e))
    model.inv_edges_idx[length(e)] = (hash(s), hash(t))
    push!(model.inneighbors[hash(t)], hash(s))
    push!(model.outneighbors[hash(s), hash(t)])
    return true
end # function

end # module
