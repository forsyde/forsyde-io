module Drivers

import ForSyDeIO.Models as Models
using EzXML

export load_model

load_model(name::String) = load_model(open(name, "r"))

load_model(instream::IOStream) = load_model(readxml(instream))

function load_model(doc::EzXML.Document)
    new_model = Models.ForSyDeModel()
    root_node = root(doc)
    model_ns = [Pair("forsyde", namespace(root_node))]
    for vnode in findall("/forsyde:graphml/forsyde:graph//forsyde:node", root_node, model_ns)
        ports = Set(pnode["name"] for pnode in findall("/forsyde:port", vnode, model_ns))
        new_vertex = Models.Vertex(
            vnode["id"],
            ports
        )
        Models.add_vertex!(new_model, new_vertex)
    end
    return new_model
end # function
    
end # module