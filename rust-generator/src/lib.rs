use petgraph::visit::Dfs;
use petgraph::Directed;
use petgraph::Graph;
use proc_macro2::{Ident, TokenStream};
use quote::format_ident;
use quote::quote;
use std::collections::HashMap;

use syn::File as SynFile;

use serde::{Deserialize, Serialize};

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct PortSpec {
    pub name: String,
    #[serde(alias = "htmlDescription")]
    pub html_description: Option<String>,
    #[serde(skip)]
    pub vertex_trait: Option<VertexTraitSpec>,
    #[serde(skip)]
    pub edge_trait: Option<EdgeTraitSpec>,
    #[serde(alias = "vertexTrait")]
    pub vertex_trait_name: String,
    #[serde(alias = "edgeTrait")]
    pub edge_trait_name: Option<String>,
    pub multiple: bool,
    pub optional: bool,
    pub incoming: bool,
    pub outgoing: bool,
}

#[derive(Debug, Clone, Serialize, Deserialize)]
#[serde(tag = "category")]
pub enum PropertySpecType {
    IntegerPropertyType {
        bits: u32,
        unsigned: bool,
    },
    RealPropertyType {
        bits: u32,
    },
    BooleanPropertyType,
    StringPropertyType,
    ArrayPropertyType {
        #[serde(alias = "valueType")]
        value_type: Box<PropertySpecType>,
    },
    MapPropertyType {
        #[serde(alias = "keyType")]
        key_type: Box<PropertySpecType>,
        #[serde(alias = "valueType")]
        value_type: Box<PropertySpecType>,
    },
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct PropertySpec {
    pub name: String,
    #[serde(alias = "htmlDescription")]
    pub html_description: Option<String>,
    #[serde(alias = "initializationCode")]
    pub initialization_code: HashMap<String, String>,
    #[serde(alias = "propertyType")]
    pub property_type: PropertySpecType,
    #[serde(alias = "defaultValue")]
    pub default_value: serde_json::Value,
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct VertexTraitSpec {
    #[serde(alias = "canonicalName")]
    pub canonical_name: String,
    #[serde(alias = "htmlDescription")]
    pub html_description: Option<String>,
    #[serde(skip)]
    pub refined_traits: Vec<VertexTraitSpec>,
    #[serde(alias = "refinedTraits")]
    pub refined_traits_names: Vec<String>,
    #[serde(alias = "requiredPorts")]
    pub required_ports: HashMap<String, PortSpec>,
    #[serde(alias = "requiredProperties")]
    pub required_properties: HashMap<String, PropertySpec>,
}

impl PartialEq<VertexTraitSpec> for VertexTraitSpec {
    fn eq(&self, other: &Self) -> bool {
        self.canonical_name == other.canonical_name
    }
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct EdgeTraitSpec {
    #[serde(alias = "canonicalName")]
    pub canonical_name: String,
    #[serde(alias = "htmlDescription")]
    pub html_description: Option<String>,
    #[serde(skip)]
    pub refined_traits: Vec<EdgeTraitSpec>,
    #[serde(alias = "refinedTraits")]
    pub refined_traits_names: Vec<String>,
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct TraitHierarchySpec {
    #[serde(alias = "canonicalName")]
    pub canonical_name: String,
    #[serde(alias = "vertexTraits")]
    pub vertex_traits: HashMap<String, VertexTraitSpec>,
    #[serde(alias = "edgeTraits")]
    pub edge_traits: HashMap<String, EdgeTraitSpec>,
}

pub type VertexTraitHierachy = Graph<VertexTraitSpec, (), Directed>;

pub type EdgeTraitHierachy = Graph<EdgeTraitSpec, (), Directed>;

impl From<&TraitHierarchySpec> for VertexTraitHierachy {
    fn from(value: &TraitHierarchySpec) -> Self {
        let mut idx_to_node = HashMap::new();
        let mut graph = Graph::new();
        for (name, vt) in &value.vertex_traits {
            let node = graph.add_node(vt.clone());
            idx_to_node.insert(name, node);
        }
        for (name, vt) in &value.vertex_traits {
            for rt in &vt.refined_traits_names {
                let node = idx_to_node.get(name).unwrap();
                let refined_node = idx_to_node.get(rt).unwrap();
                graph.add_edge(*node, *refined_node, ());
            }
        }
        graph
    }
}

impl From<&TraitHierarchySpec> for EdgeTraitHierachy {
    fn from(value: &TraitHierarchySpec) -> Self {
        let mut idx_to_node = HashMap::new();
        let mut graph = Graph::new();
        for (name, vt) in &value.edge_traits {
            let node = graph.add_node(vt.clone());
            idx_to_node.insert(name, node);
        }
        for (name, vt) in &value.edge_traits {
            for rt in &vt.refined_traits_names {
                let node = idx_to_node.get(name).unwrap();
                let refined_node = idx_to_node.get(rt).unwrap();
                graph.add_edge(*node, *refined_node, ());
            }
        }
        graph
    }
}

fn from_canonical_name(canonical_name: &str) -> TokenStream {
    let splitted = canonical_name.split("::").map(|x| format_ident!("{}", x));
    quote! {
        #(#splitted)::*
    }
}

impl From<&PropertySpecType> for TokenStream {
    fn from(value: &PropertySpecType) -> Self {
        match value {
            PropertySpecType::IntegerPropertyType { bits, unsigned } => {
                if bits <= &8 {
                    if *unsigned {
                        quote! { u8 }
                    } else {
                        quote! { i8 }
                    }
                } else if bits <= &16 {
                    if *unsigned {
                        quote! { u16 }
                    } else {
                        quote! { i16 }
                    }
                } else if bits <= &32 {
                    if *unsigned {
                        quote! { u32 }
                    } else {
                        quote! { i32 }
                    }
                } else if bits <= &64 {
                    if *unsigned {
                        quote! { u64 }
                    } else {
                        quote! { i64 }
                    }
                } else {
                    if *unsigned {
                        quote! { u128 }
                    } else {
                        quote! { i128 }
                    }
                }
            }
            PropertySpecType::RealPropertyType { bits } => {
                if bits <= &32 {
                    quote! { f32 }
                } else {
                    quote! { f64 }
                }
            }
            PropertySpecType::BooleanPropertyType => {
                quote! {
                    bool
                }
            }
            PropertySpecType::StringPropertyType => {
                quote! {
                    String
                }
            }
            PropertySpecType::ArrayPropertyType { value_type } => {
                let value_type = TokenStream::from(value_type.as_ref());
                quote! {
                    Vec<#value_type>
                }
            }
            PropertySpecType::MapPropertyType {
                key_type,
                value_type,
            } => {
                let key_type = TokenStream::from(key_type.as_ref());
                let value_type = TokenStream::from(value_type.as_ref());
                quote! {
                    std::collections::HashMap<#key_type, #value_type>
                }
            }
        }
    }
}

impl From<&PropertySpec> for TokenStream {
    fn from(value: &PropertySpec) -> Self {
        let simple_name = &value.name;
        let func_name = format_ident!("get_{}", &value.name);
        let return_type = TokenStream::from(&value.property_type);
        quote! {
            fn #func_name(&self) -> #return_type {
                <#return_type>::try_from(
                    self.get_vertex_properties().get(#simple_name).expect(&format!("Property {} for vertex {} should exist but does not.", #simple_name, self.get_identifier()))
                ).expect(&format!("Property {} for vertex {} is of a wrong type.", #simple_name, self.get_identifier()))
            }
        }
    }
}

impl From<&PortSpec> for TokenStream {
    fn from(value: &PortSpec) -> Self {
        let simple_name = value
            .vertex_trait_name
            .split("::")
            .last()
            .unwrap_or(&value.vertex_trait_name);
        let returned_viewer = format_ident!("{}Viewer", simple_name);
        let port_name = &value.name;
        let func_name = format_ident!("get_{}", &value.name);
        let direction_ident = match (value.incoming, value.outgoing) {
            (true, false) => from_canonical_name("petgraph::Direction::Incoming"),
            (false, true) => from_canonical_name("petgraph::Direction::Outgoing"),
            _ => from_canonical_name("petgraph::Undirected"),
        };
        let skip_if_wrong_edge = value
            .edge_trait_name
            .as_ref()
            .map(|e| {
                quote! {
                    if eref.weight().traits.iter().all(|t| t.get_name() != #e) {
                        continue;
                    }
                }
            })
            .unwrap_or(quote! {});
        let skip_if_wrong_port = match (value.incoming, value.outgoing) {
            (true, false) => quote! {
                if eref.weight().target_port.as_ref().map(|x| x != #port_name).unwrap_or(false)  {
                    continue;
                }
            },
            (false, true) => quote! {
                if eref.weight().source_port.as_ref().map(|x| x != #port_name).unwrap_or(false)  {
                    continue;
                }
            },
            (true, true) => quote! {
                if eref.weight().source_port.as_ref().map(|x| x != #port_name).unwrap_or(false) || eref.weight().target_port.as_ref().map(|x| x != #port_name).unwrap_or(false)  {
                    continue;
                }
            },
            _ => quote! {},
        };
        let edge_node_based_on_spec = if value.incoming {
            quote! { _src_idx }
        } else {
            quote! { _tgt_idx }
        };
        match value.multiple {
            false => {
                quote! {
                    fn #func_name(&self) -> Option<#returned_viewer> {
                        let sg = self.get_system_graph();
                        let this = self.get_vertex();
                        let self_idx = sg.node_weights().position(|x| x.identifier == this.identifier)?;
                        let self_vertex = petgraph::visit::NodeIndexable::from_index(sg, self_idx);
                        for eref in sg.edges_directed(self_vertex, #direction_ident) {
                            #skip_if_wrong_edge
                            #skip_if_wrong_port
                            let (_src_idx, _tgt_idx) = sg.edge_endpoints(eref.id())?;
                            let idx = #edge_node_based_on_spec;
                            let v = sg.node_weight(idx)?;
                            let viewer_opt = #returned_viewer::try_view(v, sg);
                            if viewer_opt.is_some() {
                                return viewer_opt;
                            }
                        }
                        None
                    }
                }
            }
            true => {
                quote! {
                    fn #func_name(&self) -> Vec<#returned_viewer> {
                        let mut viewers = Vec::new();
                        let sg = self.get_system_graph();
                        let this = self.get_vertex();
                        if let Some(self_idx) = sg.node_weights().position(|x| x.identifier == this.identifier) {
                            let self_vertex = petgraph::visit::NodeIndexable::from_index(sg, self_idx);
                            for eref in sg.edges_directed(self_vertex, #direction_ident) {
                                #skip_if_wrong_edge
                                #skip_if_wrong_port
                                if let Some((_src_idx, _tgt_idx)) = sg.edge_endpoints(eref.id()) {
                                    let idx = #edge_node_based_on_spec;
                                    if let Some(v) = sg.node_weight(idx) {
                                        if let Some(viewer) = #returned_viewer::try_view(v, sg) {
                                            viewers.push(viewer);
                                        }
                                    }
                                }

                            }
                        }
                        viewers
                    }
                }
            }
        }
    }
}

fn vertex_code_from_trait_hierarchy(
    value: &VertexTraitSpec,
    hierarchy: &VertexTraitHierachy,
) -> TokenStream {
    let canonical_name = &value.canonical_name;
    let simple_name = value
        .canonical_name
        .split("::")
        .last()
        .unwrap_or(&value.canonical_name);
    let viewer_ident = format_ident!("{}Viewer", simple_name);
    let trait_ident = format_ident!("Is{}", simple_name);
    let mut all_refined: Vec<&VertexTraitSpec> = vec![];
    let value_idx = hierarchy
        .node_indices()
        .find(|idx| hierarchy[*idx].canonical_name == value.canonical_name)
        .unwrap();
    let mut dfs = Dfs::new(hierarchy, value_idx);
    while let Some(nx) = dfs.next(&hierarchy) {
        let value = &hierarchy[nx];
        if !all_refined.contains(&value) {
            all_refined.push(value);
        }
    }
    all_refined.remove(0);
    let refinements = all_refined.iter().map(|rt| {
        let simple_name = rt
            .canonical_name
            .split("::")
            .last()
            .unwrap_or(&rt.canonical_name);
        format_ident!("Is{}", simple_name)
    });
    let refinements_implementations = all_refined
        .iter()
        .map(|rt| {
            let simple_name = rt
                .canonical_name
                .split("::")
                .last()
                .unwrap_or(&rt.canonical_name);
            let i = format_ident!("Is{}", simple_name);
            quote! { impl<'view> #i for #viewer_ident<'view> {} }
        })
        .chain(std::iter::once(
            quote! { impl<'view> #trait_ident for #viewer_ident<'view> {} },
        ));
    let ports_methods = value
        .required_ports
        .iter()
        .map(|(_, port_spec)| TokenStream::from(port_spec));
    let properties_methods = value
        .required_properties
        .iter()
        .map(|(_, property_spec)| TokenStream::from(property_spec));
    let html_documentation = value
        .html_description
        .as_ref()
        .map(String::as_str)
        .unwrap_or("")
        .replace("@deprecated", "deprecated:")
        .replace("<p>", "")
        .replace("</p>", "");
    let doc_lines = html_documentation
        .split("\n")
        .map(|s| format!(" {}", s.trim()));
    let trait_msg = format!(
        " This struct is the generated vertex viewer for the trait `{}`.",
        value.canonical_name
    );
    quote! {

        pub trait #trait_ident: forsyde_io_core::VertexViewer #(+ #refinements) *  {
            #(#ports_methods)*

            #(#properties_methods)*
        }

        #(#[doc = #doc_lines])*
        #[doc = #trait_msg]
        /// Use the function `try_view` to create a viewer for a vertex if it has the trait."
        pub struct #viewer_ident<'view> {
            pub vertex: &'view forsyde_io_core::Vertex,
            pub system_graph: &'view forsyde_io_core::SystemGraph
        }

        impl<'view, 'sg: 'view> #viewer_ident<'view> {
            pub fn try_view(vertex: &'sg forsyde_io_core::Vertex, system_graph: &'sg forsyde_io_core::SystemGraph) -> Option<Self> {
                if vertex.traits.iter().any(|t| t.get_name() == #canonical_name) {
                    let new_vertex_ref = vertex;
                    let new_system_graph_ref = system_graph;
                    Some(Self { vertex: new_vertex_ref, system_graph: new_system_graph_ref })
                } else {
                    None
                }
            }

        }

        impl<'view> forsyde_io_core::VertexViewer for #viewer_ident<'view> {
            fn get_vertex(&self) -> &forsyde_io_core::Vertex {
                self.vertex
            }
            fn get_system_graph(&self) -> &forsyde_io_core::SystemGraph {
                self.system_graph
            }
        }

        #(#refinements_implementations)*

    }
}

impl From<&TraitHierarchySpec> for TokenStream {
    fn from(hierarchy: &TraitHierarchySpec) -> Self {
        let hierarchy_module_ident = format_ident!(
            "{}",
            hierarchy
                .canonical_name
                .split("::")
                .last()
                .unwrap_or(&hierarchy.canonical_name.as_str())
        );
        let vertex_trait_idents: Vec<Ident> = hierarchy
            .vertex_traits
            .keys()
            .flat_map(|s| s.split("::").last())
            .map(|s| format_ident!("{}Trait", s))
            .collect();
        let edge_trait_idents: Vec<Ident> = hierarchy
            .edge_traits
            .keys()
            .flat_map(|s| s.split("::").last())
            .map(|s| format_ident!("{}Trait", s))
            .collect();
        let vtrait_match = hierarchy.vertex_traits.iter().map(|(canonical_name, _)| {
            let simple_name = canonical_name.split("::").last().unwrap_or(&canonical_name);
            let simple_ident = format_ident!("{}Trait", simple_name);
            quote! {
                crate::#hierarchy_module_ident::VertexTraits::#simple_ident => #canonical_name
            }
        });
        let vtrait_from_str = hierarchy.vertex_traits.iter().map(|(canonical_name, _)| {
            let simple_name = canonical_name.split("::").last().unwrap_or(&canonical_name);
            let simple_ident = format_ident!("{}Trait", simple_name);
            quote! {
                #canonical_name => Some(std::sync::Arc::new(crate::#hierarchy_module_ident::VertexTraits::#simple_ident)  as std::sync::Arc<dyn forsyde_io_core::Trait>)
            }
        });
        let vtraits_hierarchy = VertexTraitHierachy::from(hierarchy);
        let etraits_hierarchy = EdgeTraitHierachy::from(hierarchy);
        let vtrait_refine_match = hierarchy.vertex_traits.iter().map(|(name, _)| {
            let vt_idx = vtraits_hierarchy
                .node_indices()
                .find(|idx| &vtraits_hierarchy[*idx].canonical_name == name)
                .unwrap();
            let mut dfs = Dfs::new(&vtraits_hierarchy, vt_idx);
            let mut refined = vec![];
            while let Some(nx) = dfs.next(&vtraits_hierarchy) {
                let value = &vtraits_hierarchy[nx];
                refined.push(&value.canonical_name);
            }
            let this_match = if refined.len() > 0 {
                quote! {
                    match other.get_name() {
                        #(#refined => true),*,
                        _ => false
                    }
                }
            } else {
                quote! {
                    false
                }
            };
            quote! {
                #name => #this_match
            }
        });
        let etrait_refines_match = hierarchy.edge_traits.iter().map(|(name, _)| {
            let et_idx = etraits_hierarchy
                .node_indices()
                .find(|idx| &etraits_hierarchy[*idx].canonical_name == name)
                .unwrap();
            let mut dfs = Dfs::new(&etraits_hierarchy, et_idx);
            let mut refined = vec![];
            while let Some(nx) = dfs.next(&etraits_hierarchy) {
                let value = &etraits_hierarchy[nx];
                refined.push(&value.canonical_name);
            }
            let this_match = if refined.len() > 0 {
                quote! {
                    match other.get_name() {
                        #(#refined => true),*,
                        _ => false
                    }
                }
            } else {
                quote! {
                    false
                }
            };
            quote! {
                #name => #this_match
            }
        });
        let etrait_match = hierarchy.edge_traits.iter().map(|(canonical_name, _)| {
            let simple_name = canonical_name.split("::").last().unwrap_or(&canonical_name);
            let simple_ident = format_ident!("{}Trait", simple_name);
            quote! {
                crate::#hierarchy_module_ident::EdgeTraits::#simple_ident => #canonical_name
            }
        });
        let etrait_from_str = hierarchy.edge_traits.iter().map(|(canonical_name,_)| {
            let simple_name = canonical_name.split("::").last().unwrap_or(&canonical_name);
            let simple_ident = format_ident!("{}Trait", simple_name);
            quote! {
                #canonical_name => Some(std::sync::Arc::new(crate::#hierarchy_module_ident::EdgeTraits::#simple_ident) as std::sync::Arc<dyn forsyde_io_core::Trait>)
            }
        });
        let vtraits_code = hierarchy
            .vertex_traits
            .iter()
            .map(|(_, vtrait)| vertex_code_from_trait_hierarchy(vtrait, &vtraits_hierarchy));
        let code = quote! {
            // Automatically generated code by forsyde-io-generator: DO NOT EDIT MANUALLY
            #[allow(non_camel_case_types)]
            #[allow(non_snake_case)]
            pub mod #hierarchy_module_ident {
                use petgraph;
                use petgraph::visit::EdgeRef;
                use forsyde_io_core;

                #[allow(dead_code)]
                enum VertexTraits {
                    #(#vertex_trait_idents),*
                }

                #[allow(dead_code)]
                enum EdgeTraits {
                    #(#edge_trait_idents),*
                }

                impl forsyde_io_core::Trait for VertexTraits {
                    fn get_name(&self) -> &str {
                        match self {
                            #(#vtrait_match),*
                        }
                    }

                    fn refines(&self, other: &dyn forsyde_io_core::Trait) -> bool {
                        if self.get_name() == other.get_name() {
                            true
                        } else {
                            match self.get_name() {
                                #(#vtrait_refine_match),*,
                                _ => false
                            }
                        }
                    }

                }

                impl forsyde_io_core::Trait for EdgeTraits {
                    fn get_name(&self) -> &str {
                        match self {
                            #(#etrait_match),*
                        }
                    }

                    fn refines(&self, other: &dyn forsyde_io_core::Trait) -> bool {
                        if self.get_name() == other.get_name() {
                            true
                        } else {
                            match self.get_name() {
                                #(#etrait_refines_match),*,
                                _ => false
                            }
                        }
                    }

                }

                pub fn trait_from_str(s: &str) -> Option<std::sync::Arc<dyn forsyde_io_core::Trait>> {
                    match s {
                        #(#vtrait_from_str),*,
                        #(#etrait_from_str),*,
                        _ => None
                    }
                }

                #(#vtraits_code)*
            }
        };
        code
    }
}

pub fn from_json_trait_hierarchy_to_code(hierarchy_json: &str) -> Result<String, String> {
    let hierarchy: TraitHierarchySpec =
        serde_json::from_str(hierarchy_json).map_err(|e| e.to_string())?;
    generate_trait_hierarchy_code(&hierarchy)
}

pub fn generate_trait_hierarchy_code(hierarchy: &TraitHierarchySpec) -> Result<String, String> {
    let code = TokenStream::from(hierarchy);
    // println!("{}", code);
    let code_file: SynFile = syn::parse2(code).map_err(|e| e.to_string())?;
    Ok(prettyplease::unparse(&code_file))
}
