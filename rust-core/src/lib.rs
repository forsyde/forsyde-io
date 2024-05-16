use std::{collections::HashMap, sync::Arc};

pub mod avro;
pub mod fbs;

trait Trait: Send + Sync {
    fn get_name(&self) -> &str;
}

impl std::fmt::Debug for dyn Trait {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        write!(f, "Trait: {}", self.get_name())
    }
}

#[derive(Clone, Debug, PartialEq)]
enum VertexProperty {
    BooleanProperty(bool),
    IntProperty(i32),
    UIntProperty(u32),
    LongProperty(i64),
    ULongProperty(u64),
    FloatProperty(f32),
    DoubleProperty(f64),
    StringProperty(String),
    ArrayProperty(Vec<VertexProperty>),
    MapProperty(HashMap<String, VertexProperty>),
}

#[derive(Clone, Debug, PartialEq, Eq)]
pub struct OpaqueTrait {
    pub name: String,
}

impl Trait for OpaqueTrait {
    fn get_name(&self) -> &str {
        &self.name
    }
}

impl<T: Into<String> + ?Sized> From<T> for OpaqueTrait {
    fn from(name: T) -> Self {
        OpaqueTrait { name: name.into() }
    }
}

#[derive(Clone, Debug)]
pub struct EdgeInfo {
    pub source: String,
    pub target: String,
    pub source_port: Option<String>,
    pub target_port: Option<String>,
    pub traits: Vec<Arc<dyn Trait>>,
}

#[derive(Clone, Debug)]
pub struct Vertex {
    pub identifier: String,
    pub ports: Vec<String>,
    pub traits: Vec<Arc<dyn Trait>>,
    pub properties: HashMap<String, VertexProperty>,
}

impl Vertex {
    pub fn new(identifier: &str) -> Vertex {
        Vertex {
            identifier: identifier.to_string(),
            ports: Vec::new(),
            traits: Vec::new(),
            properties: HashMap::new(),
        }
    }
}

pub type SystemGraph = petgraph::graph::DiGraph<Vertex, EdgeInfo, petgraph::graph::DefaultIx>;

trait ModelDriver {
    fn load_model(&self, model_path: &str) -> Result<SystemGraph, String>;
    fn read_model(&self, model: &[u8], format: &str) -> Result<SystemGraph, String>;
    fn write_model(&self, model: &SystemGraph, model_path: &str) -> Result<(), String>;
    fn print_model(&self, model: &SystemGraph, format: &str) -> Result<Vec<u8>, String>;
}
