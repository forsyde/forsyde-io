use proc_macro2::{Ident, TokenStream};
use quote::format_ident;
use quote::quote;
use std::{collections::HashMap, sync::Arc};

use serde::{Deserialize, Serialize};

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct PortSpec {
    pub name: String,
    #[serde(alias = "htmlDescription")]
    pub html_description: Option<String>,
    #[serde(skip)]
    pub vertex_trait: Option<Arc<VertexTraitSpec>>,
    #[serde(skip)]
    pub edge_trait: Option<Arc<EdgeTraitSpec>>,
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
    IntegerPropertyType { bits: u32, unsigned: bool },
    RealPropertyType { bits: u32 },
    BooleanPropertyType,
    StringPropertyType,
    ArrayPropertyType { 
        #[serde(alias = "valueType")]
        value_type: Box<PropertySpecType> 
    },
    MapPropertyType { 
        #[serde(alias = "keyType")]
        key_type: Box<PropertySpecType>, 
        #[serde(alias = "valueType")]
        value_type: Box<PropertySpecType> 
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
    pub refined_traits: Vec<Arc<VertexTraitSpec>>,
    #[serde(alias = "refinedTraits")]
    pub refined_traits_names: Vec<String>,
    #[serde(alias = "requiredPorts")]
    pub required_ports: HashMap<String, PortSpec>,
    #[serde(alias = "requiredProperties")]
    pub required_properties: HashMap<String, PropertySpec>
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct EdgeTraitSpec {
    #[serde(alias = "canonicalName")]
    pub canonical_name: String,
    #[serde(alias = "htmlDescription")]
    pub html_description: Option<String>,
    #[serde(skip)] 
    pub refined_traits: Vec<Arc<EdgeTraitSpec>>,
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

fn vertex_refinement_closure(hierarchy: &TraitHierarchySpec) -> HashMap<String, Vec<String>> {
let mut closure = HashMap::new();
    let traits = hierarchy.vertex_traits.keys().collect::<Vec<&String>>();
    for (name, vt) in hierarchy.vertex_traits.iter() {
        closure.insert(name.clone(), vt.refined_traits_names.clone());
    }
    for k in &traits {
        for i in &traits {
            for j in &traits {
                let i_has_k = closure.get(*i).map(|vi| vi.contains(*k)).unwrap_or(false);
                let k_has_j = closure.get(*k).map(|vk| vk.contains(*j)).unwrap_or(false);
                let i_has_j = closure.get(*i).map(|vi| vi.contains(*j)).unwrap_or(false);
                if i_has_k && k_has_j && !i_has_j  {
                    if let Some(vi) = closure.get_mut(*i) {
                        vi.push(j.to_string());
                    };
                }
            }
        }
    }
    closure

}

fn edge_refinement_closure(hierarchy: &TraitHierarchySpec) -> HashMap<String, Vec<String>> {
    let mut closure = HashMap::new();
    let traits = hierarchy.edge_traits.keys().collect::<Vec<&String>>();
    for (name, et) in hierarchy.edge_traits.iter() {
        closure.insert(name.clone(), et.refined_traits_names.clone());
    }
    for k in &traits {
        for i in &traits {
            for j in &traits {
                let i_has_k = closure.get(*i).map(|vi| vi.contains(*k)).unwrap_or(false);
                let k_has_j = closure.get(*k).map(|vk| vk.contains(*j)).unwrap_or(false);
                let i_has_j = closure.get(*i).map(|vi| vi.contains(*j)).unwrap_or(false);
                if i_has_k && k_has_j && !i_has_j  {
                    if let Some(vi) = closure.get_mut(*i) {
                        vi.push(j.to_string());
                    };
                }
            }
        }
    }
    closure

}

fn from_canonical_name(canonical_name: &str) -> TokenStream {
    let splitted = canonical_name.split("::").map(|x| format_ident!("{}", x));
    quote! {
        #(#splitted)::*
    }
}

impl From<&PortSpec> for TokenStream {
    fn from(value: &PortSpec) -> Self {
        let simple_name = value.vertex_trait_name.split("::").last().unwrap_or(&value.vertex_trait_name);
        let returned_viewer = format_ident!("{}Viewer", simple_name);
        let port_name = &value.name;
        let func_name = format_ident!("get_{}", &value.name);
        let direction_ident = match (value.incoming, value.outgoing) {
            (true, false) => from_canonical_name("petgraph::Direction::Incoming"),
            (false, true) => from_canonical_name("petgraph::Direction::Outgoing"),
            _ => from_canonical_name("petgraph::Undirected"),
        };
        let skip_if_wrong_edge = value.edge_trait_name.as_ref().map(|e| {
            quote! {
                if eref.weight().traits.iter().all(|t| t.get_name() != #e) {
                    continue;
                }
            }
        }).unwrap_or(quote! {  });
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
            _ => quote! {  }
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
            },
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

impl From<&TraitHierarchySpec> for TokenStream {
    fn from(hierarchy: &TraitHierarchySpec) -> Self {
        let hierarchy_module_ident = format_ident!("{}", hierarchy.canonical_name.split("::").last().unwrap_or(&hierarchy.canonical_name.as_str()));
        let vertex_trait_idents: Vec<Ident> = hierarchy.vertex_traits.keys().flat_map(|s| s.split("::").last()).map(|s| format_ident!("{}Trait", s)).collect();
        let edge_trait_idents: Vec<Ident> = hierarchy.edge_traits.keys().flat_map(|s| s.split("::").last()).map(|s| format_ident!("{}Trait", s)).collect();
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
        let vertex_refines_closure = vertex_refinement_closure(hierarchy);
        let edge_refines_closure = edge_refinement_closure(hierarchy);
        let vtrait_refine_match = vertex_refines_closure.iter().map(|(name, refined)| {
            let this_match = if refined.len() > 0 { quote! {
                match other.get_name() {
                    #(#refined => true),*,
                    _ => false
                }
            }} else {
                quote! {
                    false
                }
            
            };
            quote! {
                #name => #this_match
            }
        });
        let etrait_refines_match = edge_refines_closure.iter().map(|(name, refined)| {
            let this_match = if refined.len() > 0 { quote! {
                match other.get_name() {
                    #(#refined => true),*,
                    _ => false
                }
            }} else {
                quote! {
                    false
                }
            
            };
            quote! {
                #name => #this_match
            }
        });
        let etrait_match = hierarchy.edge_traits.iter().map(|(canonical_name,_)| {
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
        let vtraits_methods = hierarchy.vertex_traits.iter().map(|(canonical_name,vtrait)| {
            let simple_name = canonical_name.split("::").last().unwrap_or(&canonical_name);
            let simple_ident = format_ident!("Is{}", simple_name);
            let refinements = vtrait.refined_traits_names.iter().map(|rt| {
                let simple_name = rt.split("::").last().unwrap_or(&rt);
                format_ident!("Is{}", simple_name)
            });
            let ports_methods = vtrait.required_ports.iter().map(|(port_name, port_spec)| {
                let port_code: TokenStream = port_spec.into();
                quote! {
                    #port_code
                }
            });
            quote! {
                pub trait #simple_ident: forsyde_io_core::VertexViewer #(+ #refinements) *  {
                    #(#ports_methods)*
                }
            }
        });
        let vtraits_viewers = hierarchy.vertex_traits.iter().map(|(canonical_name,vt)| {
            let simple_name = canonical_name.split("::").last().unwrap_or(&canonical_name);
            let simple_ident = format_ident!("{}Viewer", simple_name);
            let trait_ident = format_ident!("Is{}", simple_name);
            let refinements = vertex_refines_closure.get(canonical_name).unwrap().iter().map(|rt| {
                let simple_name = rt.split("::").last().unwrap_or(&rt);
                let i = format_ident!("Is{}", simple_name);
                quote! { impl<'view> #i for #simple_ident<'view> {} }
            }).chain(
                std::iter::once(quote! { impl<'view> #trait_ident for #simple_ident<'view> {} })
            );
            let html_documentation = vt.html_description.as_ref().map(String::as_str).unwrap_or("")
                .replace("@deprecated", "deprecated:")
                .replace("<p>", "")
                .replace("</p>", "");
            let doc_lines = html_documentation.split("\n")
                .map(|s| format!(" {}", s.trim()));
            let trait_msg = format!(" This struct is the generated vertex viewer for the trait `{}`.", canonical_name);
            quote! {

                #(#[doc = #doc_lines])*
                #[doc = #trait_msg]
                /// Use the function `try_view` to create a viewer for a vertex if it has the trait."
                pub struct #simple_ident<'view> {
                    pub vertex: &'view forsyde_io_core::Vertex,
                    pub system_graph: &'view forsyde_io_core::SystemGraph
                }

                impl<'view, 'sg: 'view> #simple_ident<'view> {
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

                impl<'view> forsyde_io_core::VertexViewer for #simple_ident<'view> {
                    fn get_vertex(&self) -> &forsyde_io_core::Vertex {
                        self.vertex
                    }
                    fn get_system_graph(&self) -> &forsyde_io_core::SystemGraph {
                        self.system_graph
                    }
                }

                #(#refinements)*

            }
        });
        let code = quote! {
            // Automatically generated code by forsyde-io-generator: DO NOT EDIT MANUALLY
            #[allow(non_camel_case_types)]
            #[allow(non_snake_case)]
            pub mod #hierarchy_module_ident {
                use petgraph;
                use petgraph::visit::EdgeRef;

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

                #(#vtraits_methods)*

                #(#vtraits_viewers)*
            }
        };
        code
    }
}

pub fn from_json_trait_hierarchy_to_code(hierarchy_json: &str) -> Result<String, String> {
    let hierarchy: TraitHierarchySpec = serde_json::from_str(hierarchy_json).map_err(|e| e.to_string())?;
    generate_trait_hierarchy_code(&hierarchy)
}

pub fn generate_trait_hierarchy_code(hierarchy: &TraitHierarchySpec) -> Result<String, String> {
    let code = TokenStream::from(hierarchy);
    // println!("{}", code);
    let code_file: syn::File = syn::parse2(code).map_err(|e| e.to_string())?;
    Ok(prettyplease::unparse(&code_file))
}
