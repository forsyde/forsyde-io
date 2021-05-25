using LightGraphs
import JSON

model = JSON.parsefile("meta.json")
code = "# This file has been generated automatically by 'generate.jl'\n\n"
code = code * "module Traits\n\n"
code = code * "import ForSyDeIO.Models as Models\n\n"
# open("package_template.py", 'r') do template_file
#     template = jinja2.Template(template_file.read())
# end

# build the trait vertex graph
vertex_traits_idx = [m for m in keys(model["vertexTraits"])]
vertexTraitGraph = SimpleDiGraph(length(model["vertexTraits"]))
# for (vidx, (vname, _)) in enumerate(model["vertexTraits"])
#     vertexTraitGraph.add_node(vname)
# end
for (vidx, (vname, vdata)) in enumerate(model["vertexTraits"])
    if !isnothing(vdata) && "superTraits" in keys(vdata)
        for superTrait in vdata["superTraits"]
            super_idx = findfirst(superTrait .== vertex_traits_idx)
            add_edge!(vertexTraitGraph, vidx, super_idx)
        end
    end
end

# code = code * "struct VertexTrait <: ForSyDeIO.Model.Trait begin\n"
for (vidx, trait_name) in enumerate(vertex_traits_idx)
    global code
    code = code * "struct $trait_name <: Models.VertexTrait end\n"
end
# code = code * "end\n\nfunction refines(t1::VertexTrait, t2::VertexTrait)\n"
# code = code * "  if t1 == t2\n"
# code = code * "\n"
for (vidx, trait_name) in enumerate(vertex_traits_idx)
    global code
    code = code * "\n"
    for (oidx, trait_name_other) in enumerate(vertex_traits_idx)
        if has_path(vertexTraitGraph, vidx, oidx)
            global code
            code = code * "refines(t1::$trait_name, t2::$trait_name_other) = true\n" 
            #  elseif t1 == $trait_name && t2 == $trait_name_other\n"
            # code = code * "    return true\n"
        end
    end
end
# code = code * "  else\n    return false\n  end\n"
code = code * "\n\n"

# build the trait edge graph
edge_traits_idx = [m for m in keys(model["edgeTraits"])]
edgeTraitGraph = SimpleDiGraph(length(edge_traits_idx))
for (vidx, (vname, vdata)) in enumerate(model["edgeTraits"])
    if !isnothing(vdata) && "superTraits" in keys(vdata)
        for superTrait in vdata["superTraits"]
            super_idx = findfirst(superTrait .== edge_traits_idx)
            add_edge!(edgeTraitGraph, vidx, super_idx)
        end
    end
end

#code = code * "@enum EdgeTrait <: ForSyDeIO.Model.Trait begin\n"
for (vidx, trait_name) in enumerate(edge_traits_idx)
    global code
    code = code * "struct $trait_name <: Models.EdgeTrait end\n"
end
# code = code * "end\n\nfunction refines(t1::EdgeTrait, t2::EdgeTrait)\n"
# code = code * "  if t1 == t2\n"
for (vidx, trait_name) in enumerate(edge_traits_idx)
    global code
    code = code * "\n"
    for (oidx, trait_name_other) in enumerate(edge_traits_idx)
        if has_path(edgeTraitGraph, vidx, oidx)
            global code
            code = code * "refines(t1::$trait_name, t2::$trait_name_other) = true\n" 
            #code = code * "  elseif t1 == $trait_name && t2 == $trait_name_other\n"
            #code = code * "    return true\n"
        end
    end
end
code = code * "\n"
code = code * "vertex_trait_lut = Dict(\n"
code = code * Base.join(map((t) -> "  \"$t\" => $t()", vertex_traits_idx), ",\n")
code = code * ")\n\n"

code = code * "\n"
code = code * "edge_trait_lut = Dict(\n"
code = code * Base.join(map((t) -> "  \"$t\" => $t()", edge_traits_idx), ",\n")
code = code * ")\n\n"


#code = code * "@enum EdgeTrait <: ForSyDeIO.Model.Trait begin\n"
code = code * "function make_trait(label::AbstractString)::Union{Nothing, <:Models.Trait}\n"
code = code * "  global vertex_trait_lut\n"
code = code * "  global edge_trait_lut\n"
code = code * "  if haskey(vertex_trait_lut, label)\n"
code = code * "    return vertex_trait_lut[label]\n"
code = code * "  elseif haskey(edge_trait_lut, label)\n"
code = code * "    return edge_trait_lut[label]\n"
code = code * "  else\n"
code = code * "    return nothing\n"
code = code * "  end\n"
code = code * "end\n\n"

code = code * "end # module"

open("src/ForSyDeIO/Models/Traits.jl", "w") do out_file
    write(out_file, code)
end