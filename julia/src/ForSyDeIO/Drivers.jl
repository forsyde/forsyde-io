module Drivers

using ForSyDeIO.Models
using ForSyDeIO.Models.Traits
using EzXML

export load_model

load_model_forxml(name::String) = load_model_forxml(open(name, "r"))

load_model_forxml(instream::IOStream) = load_model_forxml(readxml(instream))

function load_model_forxml(doc::EzXML.Document)::Models.ForSyDeModel
    new_model = Models.ForSyDeModel()
    root_node = root(doc)
    model_ns = [Pair("forsyde", namespace(root_node))]
    for vnode in findall("/forsyde:graphml/forsyde:graph//forsyde:node", root_node, model_ns)
        ports = Set(pnode["name"] for pnode in findall("forsyde:port", vnode, model_ns))
		traits = filter(!isnothing, Set(Traits.make_trait(tname) for tname in split(vnode["traits"], ";")))
        props = Dict{String, Any}()
        for propnode in findall("forsyde:data", vnode, model_ns)
            props[propnode["attr.name"]] = load_model_property_forxml(propnode)
        end
        new_vertex = Models.Vertex(
            vnode["id"],
            ports,
			props,
			traits
        )
        push!(new_model, new_vertex)
    end
    for enode in findall("/forsyde:graphml/forsyde:graph//forsyde:edge", root_node, model_ns)
        source = Ref(getindex(new_model, enode["source"]))
        target = Ref(getindex(new_model, enode["target"]))
        sourceport = haskey(enode, "sourceport") ? enode["sourceport"] : nothing
        targetport = haskey(enode, "targetport") ? enode["targetport"] : nothing
        traits = filter(!isnothing, Set(Traits.make_trait(tname) for tname in split(enode["traits"], ";")))
        push!(new_model, Models.Edge(source, target, sourceport, targetport, traits))
    end
    return new_model
end # function

function load_model_property_forxml(node)
    if node["attr.type"] == "object"
        new_dict = Dict{String, Any}()
        for e in eachelement(node)
            new_dict[e["attr.name"]] = load_model_property_forxml(e)
        end
        return new_dict
    elseif node["attr.type"] == "array"
        new_array = Vector{Any}()
        for e in eachelement(node)
            push!(new_array, load_model_property_forxml(e))
        end
        return new_array
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

function load_model(name::String)::Models.ForSyDeModel
    if endswith(name, ".forxml")
        return load_model_forxml(name)
    else
        error("File '$name' is not in a supported readable format.")
    end
end

end # module
