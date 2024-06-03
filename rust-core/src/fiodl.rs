use std::{collections::HashMap, sync::Arc};

use nom::{
    branch::alt, bytes::complete::{is_not, tag, take_until, take_while}, character::complete::{alphanumeric1, char, digit1, multispace0}, combinator::{fail, opt, recognize}, error::ParseError, multi::{many0, separated_list0}, number::complete::{double, float}, sequence::{delimited, preceded, terminated, tuple}, IResult, Parser
};
use petgraph::visit::NodeIndexable;

use crate::{EdgeInfo, ModelDriver, OpaqueTrait, SystemGraph, Trait, Vertex, VertexProperty};

/// A combinator that takes a parser `inner` and produces a parser that also consumes both leading and 
/// trailing whitespace, returning the output of `inner`.
fn ws<'a, F, O, E: ParseError<&'a str>>(inner: F) -> impl Parser<&'a str, O, E>
  where
  F: Parser<&'a str, O, E>,
{
  delimited(
    multispace0,
    inner,
    multispace0
  )
}

/// A combinator that takes a parser `inner` and produces a parser that also consumes both leading and 
/// trailing quotes if they exist, returning the output of `inner`.
fn opt_quoted<'a, F, O, E: ParseError<&'a str>>(inner: F) -> impl Parser<&'a str, O, E>
  where
  F: Parser<&'a str, O, E>,
{
  delimited(
    opt(char('"')),
    inner,
    opt(char('"')),
  )
}

/// A combinator that takes a parser `inner` and produces a parser that also requires both leading and 
/// trailing quotes, returning the output of `inner`.
fn quoted<'a, F, O, E: ParseError<&'a str>>(inner: F) -> impl Parser<&'a str, O, E>
  where
  F: Parser<&'a str, O, E>,
{
  delimited(
    char('"'),
    inner,
    char('"'),
  )
}


enum ElementsPermutated {
    EdgeInfo(EdgeInfo),
    Vertex(Vertex),
}


pub fn parse_system_graph(input: &str) -> IResult<&str, SystemGraph> {
    let mut sg = SystemGraph::new();
    let (txt, insides) = preceded(
        tag("systemgraph"),
        delimited(
                ws(char('{')),
                ws(many0(alt((parse_edge_info, parse_vertex)))),
                ws(char('}')),
            )
        )(input)?;
    for elem in insides {
        match elem {
            ElementsPermutated::EdgeInfo(ei) => {
                let srci = sg.node_weights_mut().position(|x| x.identifier == ei.source).map(|i| sg.from_index(i)).unwrap_or(sg.add_node(Vertex::new(ei.source.as_str())));
                let dsti = sg.node_weights_mut().position(|x| x.identifier == ei.target).map(|i| sg.from_index(i)).unwrap_or(sg.add_node(Vertex::new(ei.target.as_str())));
                sg.add_edge(srci, dsti, ei);
            }
            ElementsPermutated::Vertex(v) => {
                if let Some(existing) = sg.node_weights_mut().find(|x| x.identifier == v.identifier) {
                    for port in v.ports {
                        if !existing.ports.contains(&port) {
                            existing.ports.push(port);
                        }
                    }
                    for t in v.traits {
                        if !existing.traits.iter().any(|vt| vt.refines(t.as_ref())) {
                            existing.traits.push(t);
                        }
                    }
                } else {
                    sg.add_node(v);
                }
            }
        }
    }
    Ok((txt, sg))
}

fn parse_edge_info(input: &str) -> IResult<&str, ElementsPermutated> {
    let (without_edge, _) = tag("edge")(input)?;
    let (without_traits, traits_str) = delimited(
                ws(char('[')),
                ws(separated_list0(ws(char(',')), recognize(take_while(|c| c != ',' && c != ']')))),
                ws(char(']')),
    )(without_edge)?;
    let (without_src, src) = preceded(ws(tag("from")),ws( opt_quoted(alphanumeric1)))(without_traits)?;
    let (without_src_port, src_port) = opt(preceded(
        ws(tag("port")), ws( opt_quoted(alphanumeric1))))(without_src)?;
    let (without_dst, dst) = preceded(
        ws(tag("to")), ws( opt_quoted(alphanumeric1)))(without_src_port)?;
    let (last_parsed, dst_port) = opt(preceded(ws(tag("port")), ws( opt_quoted(alphanumeric1))))(without_dst)?;
    Ok((
        last_parsed,
        ElementsPermutated::EdgeInfo(EdgeInfo {
            source: src.to_string(),
            target: dst.to_string(),
            source_port: src_port.map(|s| s.to_string()),
            target_port: dst_port.map(|s| s.to_string()),
            traits: traits_str
                .into_iter()
                .map(|s| Arc::new(OpaqueTrait::from(s)) as Arc<dyn Trait>)
                .collect(),
        }),
    ))
}

fn parse_vertex(input: &str) -> IResult<&str, ElementsPermutated> {
    let (without_id, identifier) = preceded(ws(tag("vertex")), ws(opt_quoted(alphanumeric1)))(input)?;
    let (without_traits, traits_str) = 
        delimited(
            ws(char('[')),
            ws(separated_list0(ws(char(',')), recognize(take_while(|c| c != ',' && c != ']')))),
            ws(char(']')),
    )(without_id)?;
    let (without_ports, ports_str) = delimited(
            ws(char('(')),
            ws(separated_list0(ws(char(',')), recognize(take_while(|c| c != ',' && c != ')')))),
            ws(char(')')),
    )(without_traits)?;
    let (without_properties, properties_vec) = delimited(
        ws(char('{')),
        ws(separated_list0(ws(char(',')), tuple((opt_quoted(alphanumeric1), ws(char(':')), parse_vertex_property)))),
        ws(char('}'))
    )(without_ports)?;
    let mut properties = HashMap::new();
    for (key, _, value) in properties_vec {
        properties.insert(key.to_string(), value);
    }
    Ok((
        without_properties,
        ElementsPermutated::Vertex(Vertex {
            identifier: identifier.to_string(),
            ports: ports_str.into_iter().map(|s| s.to_string()).collect(),
            traits: traits_str
                .into_iter()
                .map(|s| Arc::new(OpaqueTrait::from(s)) as Arc<dyn Trait>)
                .collect(),
            properties
        }),
    ))
}

fn parse_vertex_property(input: &str) -> IResult<&str, VertexProperty> {
    if let Ok((txt, v)) = terminated(digit1::<&str, ()>, tag("_b"))(input) {
        return Ok((txt, VertexProperty::BooleanProperty(v == "true" || v == "1")));
    } else if let Ok((txt, v)) = terminated(digit1::<&str, ()>, tag("_l"))(input) {
        return Ok((txt, VertexProperty::LongProperty(v.parse().expect("Failed to parse long property"))));
    } else if let Ok((txt, v)) = terminated(digit1::<&str, ()>, tag("_i"))(input) {
        return Ok((txt, VertexProperty::IntProperty(v.parse().expect("Failed to parse int property"))));
    } else if let Ok((txt, v)) = terminated(float::<&str, ()>, opt(tag("_f")))(input) {
        return Ok((txt, VertexProperty::FloatProperty(v)));
    } else if let Ok((txt, v)) = terminated(double::<&str, ()>, opt(tag("_d")))(input) {
        return Ok((txt, VertexProperty::DoubleProperty(v)));
    } else if let Ok((txt, v)) = preceded(char::<&str, ()>('"'), recognize(take_until("\"")))(input) {
        return Ok((txt, VertexProperty::StringProperty(v.to_string())));
    } else if let Ok((txt, v)) = delimited(ws(char('[')), separated_list0(ws(char(',')), parse_vertex_property), ws(char(']')))(input) {
        return Ok((txt, VertexProperty::ArrayProperty(v)));
    } else if let Ok((txt, v)) = delimited(ws(char('{')), separated_list0(ws(char(',')), tuple((quoted(is_not("\"")), ws(char(':')), parse_vertex_property))), ws(char('}')))(input) {
        let mut map = HashMap::new();
        for (key, _, value) in v {
            map.insert(key.to_string(), value);
        }
        return Ok((txt, VertexProperty::MapProperty(map)));
    }
    else {
        return fail("invalid property");
    }
}

fn print_vertex_property(prop: &VertexProperty, ident_level: u16) -> String {
    let ident_str = " ".repeat(2 + ident_level as usize);
    match prop {
        VertexProperty::BooleanProperty(b) => format!("{}_b", b),
        VertexProperty::LongProperty(l) => format!("{}_l", l),
        VertexProperty::IntProperty(i) => format!("{}_i", i),
        VertexProperty::FloatProperty(f) => format!("{}_f", f),
        VertexProperty::DoubleProperty(d) => format!("{}_d", d),
        VertexProperty::StringProperty(s) => format!("\"{}\"", s),
        VertexProperty::ArrayProperty(a) => {
            let mut out = String::new();
            out.push_str("[\n");
            for (i, p) in a.iter().enumerate() {
                out.push_str(&ident_str);
                out.push_str(&print_vertex_property(p, ident_level + 2));
                if i < a.len() - 1 {
                    out.push_str(",\n");
                }
            }
            out.push_str("\n");
            out.push_str(&" ".repeat(ident_level as usize));
            out.push_str("]");
            out
        }
        VertexProperty::MapProperty(m) => {
            let mut out = String::new();
            out.push_str("{\n");
            for (i, (k, v)) in m.iter().enumerate() {
                out.push_str(&ident_str);
                out.push_str(&format!("\"{}\": {}", k, print_vertex_property(v, ident_level + 2)));
                if i < m.len() - 1 {
                    out.push_str(",\n");
                }
            }
            out.push_str("\n");
            out.push_str(&" ".repeat(ident_level as usize));
            out.push_str("}");
            out
        }
        VertexProperty::UIntProperty(x) => format!("{}_i", x),
        VertexProperty::ULongProperty(x) => format!("{}_l", x),
    }

}

fn print_system_grap(sg: &SystemGraph) -> String {
    let mut out = String::new();
    out.push_str("systemgraph {\n");
    for node in sg.node_weights() {
        out.push_str(&format!("  vertex \"{}\"\n  [", node.identifier));
        if !node.traits.is_empty() {
            out.push_str(&node.traits.iter().map(|t| t.to_string()).collect::<Vec<String>>().join(", "));
        }
        out.push_str("]\n  (");
        if !node.ports.is_empty() {
            out.push_str(&node.ports.join(", "));
        }
        out.push_str(")\n  {\n");
        let properties: Vec<String> = node.properties.iter().map(|(key, value)| format!("    \"{}\": {}", key, print_vertex_property(value, 4)))
            .collect();
        out.push_str(&properties.join(",\n"));
        out.push_str("\n  }\n");
    }
    for edge in sg.edge_weights() {
        out.push_str(&format!("  edge from \"{}\" to \"{}\" [", edge.source, edge.target));
        if !edge.traits.is_empty() {
            out.push_str(&edge.traits.iter().map(|t| t.to_string()).collect::<Vec<String>>().join(", "));
        }
        out.push_str("]\n");
    }
    out.push_str("}\n");
    out
}
pub struct FiodlDriver;

impl ModelDriver for FiodlDriver {
    fn load_model(&self, model_path: &str) -> Result<SystemGraph, String> {
        let system_graph = std::fs::read_to_string(model_path).map_err(|e| e.to_string())?;
        parse_system_graph(&system_graph).map(|(_, sg)| sg).map_err(|e| e.to_string())
    }

    fn read_model(&self, model: &[u8], format: &str) -> Result<SystemGraph, String> {
        if format != "fiodl" {
            return Err("Invalid format. Only `fiodl` is supported by the FiodlDriver.".to_string());
        }
        let system_graph = String::from_utf8(model.to_vec()).map_err(|e| e.to_string())?;
        parse_system_graph(&system_graph).map(|(_, sg)| sg).map_err(|e| e.to_string())
    }

    fn write_model(&self, model: &SystemGraph, model_path: &str) -> Result<(), String> {
        let system_graph = print_system_grap(model);
        std::fs::write(model_path, system_graph).map_err(|e| e.to_string())
    }

    fn print_model(&self, model: &SystemGraph, format: &str) -> Result<Vec<u8>, String> {
        if format == "fiodl" {
            Ok(print_system_grap(model).into_bytes())
        } else {
            Err("Invalid format. Only `fiodl` is supported by the FiodlDriver.".to_string())
        }
    }
}