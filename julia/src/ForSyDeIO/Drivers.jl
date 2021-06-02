__precompile__()
module Drivers

using ForSyDeIO.Models
using ForSyDeIO.Models.Traits
using EzXML

export load_model, write_model

load_model_forxml(name::String) = load_model_forxml(open(name, "r"))

load_model_forxml(instream::IOStream) = load_model_forxml(readxml(instream))

function load_model_forxml(doc::EzXML.Document)::Models.ForSyDeModel
    new_model = Models.ForSyDeModel()
    root_node = root(doc)
    model_ns = [Pair("forsyde", namespace(root_node))]
    for vnode in findall("/forsyde:graphml/forsyde:graph//forsyde:node", root_node, model_ns)
        identifier = vnode["id"]
        traits = Set{Models.VertexTrait}()
        for tname in split(vnode["traits"], ";")
            if Traits.is_trait(tname)
                push!(traits, Traits.make_trait_vertex(tname))
            end
        end
        ports = Set{String}()
        for pnode in findall("forsyde:port", vnode, model_ns)
            push!(ports, pnode["name"])
        end
        # ports = Set(pnode["name"] for pnode in findall("forsyde:port", vnode, model_ns))
		# traits = filter(!isnothing, Set(Traits.make_trait(tname) for tname in split(vnode["traits"], ";")))
        props = Dict{String,Models.PropertyElement}()
        for propnode in findall("forsyde:data", vnode, model_ns)
            props[propnode["attr.name"]] = load_model_property_forxml(propnode)
        end
        vertex = Models.Vertex(
            identifier,
            ports,
		 	props,
		 	traits
        )
        push!(new_model, vertex)
    end
    for enode in findall("/forsyde:graphml/forsyde:graph//forsyde:edge", root_node, model_ns)
        traits = Set{Models.EdgeTrait}()
        source = Ref(getindex(new_model, enode["source"]))
        target = Ref(getindex(new_model, enode["target"]))
        sourceport = haskey(enode, "sourceport") ? enode["sourceport"] : nothing
        targetport = haskey(enode, "targetport") ? enode["targetport"] : nothing
        for tname in split(enode["traits"], ";")
            if Traits.is_trait(tname)
                push!(traits, Traits.make_trait_edge(tname))
            end
        end
        push!(new_model, Models.Edge(source, target, sourceport, targetport, traits))
    end
    return new_model
end # function

function load_model_property_forxml(node)::Models.PropertyElement
        if node["attr.type"] == "object"
        new_dict = Dict{String,Models.PropertyElement}()
        for e in eachelement(node)
            new_dict[e["attr.name"]] = load_model_property_forxml(e)
        end
    return Models.PropertyDict(new_dict)
    elseif node["attr.type"] == "array"
        new_array = Vector{Models.PropertyElement}()
        for e in eachelement(node)
            push!(new_array, load_model_property_forxml(e))
        end
        return Models.PropertyArray(new_array)
    elseif node["attr.type"] == "int" || node["attr.type"] == "integer" 
        return parse(Int, node.content)
    elseif node["attr.type"] == "bool" || node["attr.type"] == "boolean" 
        return parse(Bool, node.content)
    elseif node["attr.type"] == "float"
        return parse(Float32, node.content)
    elseif node["attr.type"] == "double"
        return parse(Float64, node.content)
    elseif node["attr.type"] == "str" || node["attr.type"] == "string" 
        return node.content
    else
    end
end

function load_model(name::AbstractString)::Models.ForSyDeModel
    if endswith(name, ".forxml")
        return load_model_forxml(name)
    else
        error("File '$name' is not in a supported readable format.")
    end
end

function write_model_property_forxml(nodeprop::Models.PropertyArray)
    newnode = ElementNode("data")
    link!(newnode, AttributeNode("attr.type", "array"))
    for (i, v) in enumerate(nodeprop)
        childnode = write_model_property_forxml(v)
        link!(childnode, AttributeNode("attr.name", string(i)))
        link!(newnode, childnode)
    end
    return newnode
end

function write_model_property_forxml(nodeprop::Models.PropertyDict)
    newnode = ElementNode("data")
    link!(newnode, AttributeNode("attr.type", "object"))
    for (k, v) in nodeprop
        childnode = write_model_property_forxml(v)
        link!(childnode, AttributeNode("attr.name", k))
        link!(newnode, childnode)
    end
    return newnode
end

function write_model_property_forxml(nodeprop::Int)
    newnode = ElementNode("data")
    link!(newnode, AttributeNode("attr.type", "integer"))
    link!(newnode, TextNode(string(nodeprop)))
    return newnode
end

function write_model_property_forxml(nodeprop::Float32)
    newnode = ElementNode("data")
    link!(newnode, AttributeNode("attr.type", "float"))
    link!(newnode, TextNode(string(nodeprop)))
    return newnode
end

function write_model_property_forxml(nodeprop::Float64)
    newnode = ElementNode("data")
    link!(newnode, AttributeNode("attr.type", "double"))
    link!(newnode, TextNode(string(nodeprop)))
    return newnode
end

function write_model_property_forxml(nodeprop::Bool)
    newnode = ElementNode("data")
    link!(newnode, AttributeNode("attr.type", "bool"))
    link!(newnode, TextNode(string(nodeprop)))
    return newnode
end

function write_model_property_forxml(nodeprop::String)
    newnode = ElementNode("data")
    link!(newnode, AttributeNode("attr.type", "string"))
    link!(newnode, TextNode(string(nodeprop)))
    return newnode
end


function build_model_forxml(model::Models.ForSyDeModel)::EzXML.Node
    graphmlnode = ElementNode("graphml")
    link!(graphmlnode, AttributeNode("xmlns", "http://graphml.graphdrawing.org/xmlns"))
    graphnode = ElementNode("graph")
    link!(graphnode, AttributeNode("id", "model"))
    link!(graphnode, AttributeNode("edgedefault", "directed"))
    link!(graphmlnode, graphnode)
    for v in model
        vnode = ElementNode("node")
        link!(vnode, AttributeNode("id", v.id))
        for port in v.ports
            portnode = ElementNode("port")
            link!(portnode, AttributeNode("name", port))
            link!(vnode, portnode)
        end
        for (propname, propelem) in v.properties
            datanode = write_model_property_forxml(propelem)
            link!(datanode, AttributeNode("attr.name", propname))
            link!(vnode, datanode)
        end
        link!(vnode, AttributeNode("traits", join([string(t) for t in v.vertex_traits], ";")))
        link!(graphnode, vnode)
    end
    for e in model.edges
        enode = ElementNode("edge")
        link!(enode, AttributeNode("source", Models.src(e).id))
        link!(enode, AttributeNode("target", Models.dst(e).id))
            if !isnothing(e.source_port)
            link!(enode, AttributeNode("sourceport", e.source_port))
        end
            if !isnothing(e.target_port)
            link!(enode, AttributeNode("targetport", e.target_port))
        end
        link!(enode, AttributeNode("traits", join([string(t) for t in e.edge_traits], ";")))
        link!(graphnode, enode)
    end
    return graphmlnode
end

function write_model_forxml(model::Models.ForSyDeModel, outstream::IO)
    doc = XMLDocument()
    setroot!(doc, build_model_forxml(model))
    print(outstream, doc)
end

function write_model_forxml(model, name::AbstractString)
    open(name, "w") do f
        write_model_forxml(model, f)    
    end
end

function write_model(model::Models.ForSyDeModel, name::AbstractString)
    if endswith(name, ".forxml")
        write_model_forxml(model, name)
    else
        error("File '$name' is not in a supported writable format.")
    end
end


end # module
