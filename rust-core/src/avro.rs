use std::{collections::HashMap, fs::File, io::BufReader, sync::Arc};

use avro_rs::{types::Value, Schema};

use crate::{EdgeInfo, OpaqueTrait, SystemGraph, Trait, Vertex};

const SYSTEM_GRAPH_SCHEMA_STR: &str = r#"
[
    {
        "type": "record",
        "namespace": "forsyde.io.core.avro",
        "name": "EdgeInfo",
        "fields": [
            {
                "name": "source",
                "type": "string"
            },
            {
                "name": "target",
                "type": "string"
            },
            {
                "name": "sourcePort",
                "type": ["null", "string"],
                "default": null
            },
            {
                "name": "targetPort",
                "type": ["null", "string"],
                "default": null
            },
            {
                "name": "traits",
                "type": {
                    "type": "array",
                    "items": "string",
                    "default": []
                }
            }
        ]
    },
    {
        "name": "VertexProperty",
        "type": "record",
        "namespace": "forsyde.io.core.avro",
        "fields": [{
            "name": "value",
            "type": [
                "int",
                "long",
                "float",
                "double",
                "string",
                {
                    "type": "array",
                    "items": "VertexProperty"
                },
                {
                    "type": "map",
                    "values": "VertexProperty"
                }
            ]
        }]
    },
    {
        "name": "Vertex",
        "type": "record",
        "namespace": "forsyde.io.core.avro",
        "fields": [
            {
                "name": "identifier",
                "type": "string"
            },
            {
                "name": "ports",
                "type": {
                    "type": "array",
                    "items": "string",
                    "default": []
                }
            },
            {
                "name": "traits",
                "type": {
                    "type": "array",
                    "items": "string",
                    "default": []
                }
            },
            {
                "name": "properties",
                "type": {
                    "type": "map",
                    "values": "VertexProperty",
                    "default": {}
                }
            }
        ]
    },
    {
        "type": "record",
        "namespace": "forsyde.io.core.avro",
        "name": "SystemGraph",
        "fields": [
            {
                "name": "vertices",
                "type": {
                    "type": "array",
                    "items": "Vertex",
                    "default": []
                }
            },
            {
                "name": "edges",
                "type": {
                    "type": "array",
                    "items": "EdgeInfo",
                    "default": []
                }
            }
        ]
    }
]
"#;

pub fn load_model_avro(model_path: &str) -> Result<SystemGraph, String> {
    println!("Loading model from: {}", model_path);
    if !model_path.ends_with("fio.avro") && !model_path.ends_with("favro") {
        return Err(
            "Invalid model file extension. Must end with 'fio.avro' or 'favro'.".to_string(),
        );
    }
    let file = File::open(model_path).map_err(|e| e.to_string())?;
    let reader = BufReader::new(file);
    let schema: Schema = avro_rs::Schema::parse_str(SYSTEM_GRAPH_SCHEMA_STR)
        .expect("Failed to parse constant system graph schema. Should never happen.");
    let mut decoder = avro_rs::Reader::with_schema(&schema, reader).map_err(|e| e.to_string())?;
    let mut system_graph = SystemGraph::default();
    while let Some(value) = decoder.next() {
        let value = value.map_err(|e| e.to_string())?;
        match value {
            Value::Record(record) => {
                let (_, vertices) = record
                    .iter()
                    .find(|(k, _)| k == "vertices")
                    .ok_or("Missing 'vertices' field in system graph record.")?;
                let (_, edges) = record
                    .iter()
                    .find(|(k, _)| k == "edges")
                    .ok_or("Missing 'edges' field in system graph record.")?;

                // let vertices: Vec<Vertex> = serde_json::from_value(vertices).map_err(|e| e.to_string())?;
                // let edges: Vec<EdgeInfo> = serde_json::from_value(edges).map_err(|e| e.to_string())?;
                // system_graph.vertices = vertices;
                // system_graph.edges = edges;
            }
            _ => return Err("Invalid system graph record.".to_string()),
        }
        // let vertex: Vertex = serde_json::from_value(value).map_err(|e| e.to_string())?;
        // system_graph.vertices.push(vertex);
    }
    Ok(system_graph)
}

fn decode_system_graph(value: &Value) -> Result<SystemGraph, String> {
    let mut system_graph = SystemGraph::default();
    match value {
        Value::Record(record) => {
            let (_, vertices) = record
                .iter()
                .find(|(k, _)| k == "vertices")
                .ok_or("Missing 'vertices' field in system graph record.")?;
            let mut identifiers_to_idx = HashMap::new();
            match vertices {
                Value::Array(vs) => {
                    for v in vs {
                        let vertex: Vertex = decode_vertex(v)?;
                        let id = vertex.identifier.clone();
                        identifiers_to_idx.insert(id, system_graph.add_node(vertex));
                    }
                }
                _ => return Err("Vertices field must be an array.".to_string()),
            }
            let (_, edges) = record
                .iter()
                .find(|(k, _)| k == "edges")
                .ok_or("Missing 'edges' field in system graph record.")?;
            match edges {
                Value::Array(es) => {
                    for e in es {
                        let edge_info: EdgeInfo = decode_edge_info(e)?;
                        let src_id = identifiers_to_idx
                            .get(&edge_info.source)
                            .ok_or("Edge source identifier not found in vertices.")?;
                        let dst_id = identifiers_to_idx
                            .get(&edge_info.target)
                            .ok_or("Edge target identifier not found in vertices.")?;
                        system_graph.add_edge(*src_id, *dst_id, edge_info);
                    }
                }
                _ => return Err("Edges field must be an array.".to_string()),
            }
            // let vertices: Vec<Vertex> = serde_json::from_value(vertices).map_err(|e| e.to_string())?;
            // let edges: Vec<EdgeInfo> = serde_json::from_value(edges).map_err(|e| e.to_string())?;
            // system_graph.vertices = vertices;
            // system_graph.edges = edges;
        }
        _ => return Err("Invalid system graph record.".to_string()),
    }
    Ok(system_graph)
}

fn decode_vertex(value: &Value) -> Result<Vertex, String> {
    return match value {
        Value::Record(record) => {
            let (_, id_value) = record
                .iter()
                .find(|(k, _)| k == "identifier")
                .ok_or("Missing 'identifier' field in vertex record.")?;
            let id = match id_value {
                Value::String(s) => s,
                _ => return Err("Vertex identifier must be a string.".to_string()),
            };
            let mut vertex = Vertex::new(id);
            Ok(vertex)
        }
        _ => Err("Top level value for vertex must be a record.".to_string()),
    };
}

fn decode_edge_info(value: &Value) -> Result<EdgeInfo, String> {
    return match value {
        Value::Record(record) => {
            let (_, source_value) = record
                .iter()
                .find(|(k, _)| k == "source")
                .ok_or("Missing 'source' field in edge info record.")?;
            let source = match source_value {
                Value::String(s) => s.to_string(),
                _ => return Err("Edge source must be a string.".to_string()),
            };
            let (_, target_value) = record
                .iter()
                .find(|(k, _)| k == "target")
                .ok_or("Missing 'target' field in edge info record.")?;
            let target = match target_value {
                Value::String(s) => s.to_string(),
                _ => return Err("Edge target must be a string.".to_string()),
            };
            let (_, source_port_value) = record
                .iter()
                .find(|(k, _)| k == "sourcePort")
                .ok_or("Missing 'sourcePort' field in edge info record.")?;
            let source_port = match source_port_value {
                Value::String(s) => Some(s.to_string()),
                Value::Null => None,
                _ => return Err("Edge source port must be a string or null.".to_string()),
            };
            let (_, target_port_value) = record
                .iter()
                .find(|(k, _)| k == "targetPort")
                .ok_or("Missing 'targetPort' field in edge info record.")?;
            let target_port = match target_port_value {
                Value::String(s) => Some(s.to_string()),
                Value::Null => None,
                _ => return Err("Edge target port must be a string or null.".to_string()),
            };
            let (_, traits_value) = record
                .iter()
                .find(|(k, _)| k == "traits")
                .ok_or("Missing 'traits' field in edge info record.")?;
            let traits = match traits_value {
                Value::Array(ts) => {
                    let mut traits = Vec::new();
                    for t in ts {
                        match t {
                            Value::String(s) => {
                                traits.push(Arc::new(OpaqueTrait::from(s)) as Arc<dyn Trait>)
                            }
                            _ => return Err("Edge trait must be a string.".to_string()),
                        }
                    }
                    traits
                }
                _ => return Err("Edge traits must be an array.".to_string()),
            };
            let edge_info = EdgeInfo {
                source,
                target,
                source_port,
                target_port,
                traits,
            };
            Ok(edge_info)
        }
        _ => Err("Top level value for edge info must be a record.".to_string()),
    };
}

// #[cfg(test)]
// mod tests {
//     use super::*;

//     #[test]
//     fn read() {
//         let system_graph = load_model_avro("../test_avro.fio.avro").unwrap();
//         println!("{:?}", system_graph);
//     }
// }
