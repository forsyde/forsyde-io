pub mod traits;

use std::option::Option;
use std::collections::HashMap;
use std::collections::HashSet;

enum VertexProperty {
    IntProperty(i64),
    FloatProperty(f32),
    DoubleProperty(f64),
    StringProperty(String),
    BooleanProperty(bool),
    ArrayProperty(Vec<VertexProperty>),
    DictProperty(HashMap<String, VertexProperty>)
}

struct Vertex {
    id: String,
    ports: HashSet<String>,
    properties: HashMap<String, VertexProperty>
}

struct Edge<'a> {
    source: &'a Vertex,
    target: &'a Vertex,
    sourceport: Option<String>,
    targetport: Option<String>
}
