using ForSyDeIO
using Documenter

DocMeta.setdocmeta!(ForSyDeIO, :DocTestSetup, :(using ForSyDeIO); recursive=true)

makedocs(;
    modules=[ForSyDeIO],
    authors="Rodolfo Jordão <jordao@kth.se> and contributors",
    repo="https://github.com/Rojods/ForSyDeIO.jl/blob/{commit}{path}#{line}",
    sitename="ForSyDeIO.jl",
    format=Documenter.HTML(;
        prettyurls=get(ENV, "CI", "false") == "true",
        canonical="https://Rojods.github.io/ForSyDeIO.jl",
        assets=String[],
    ),
    pages=[
        "Home" => "index.md",
    ],
)

deploydocs(;
    repo="github.com/Rojods/ForSyDeIO.jl",
)
