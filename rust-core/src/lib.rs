use core::str;
use std::{collections::HashMap, fmt::Display, sync::Arc};

// pub mod avro;
// pub mod fbs;
pub mod fiodl;

pub trait Trait: Send + Sync {
    fn get_name(&self) -> &str;

    fn refines(&self, other: &dyn Trait) -> bool {
        self.get_name() == other.get_name()
    }
}

impl PartialEq<dyn Trait> for dyn Trait {
    fn eq(&self, other: &Self) -> bool {
        self.get_name() == other.get_name()
    }
}

impl std::fmt::Debug for dyn Trait {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        write!(f, "{}", self.get_name())
    }
}

impl Display for dyn Trait {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        write!(f, "{}", self.get_name())
    }
}

pub trait Mergeable<T = Self, O = Self> {
    fn merge(&self, other: &T) -> O;
}

pub trait VertexViewer {
    fn get_vertex(&self) -> &Vertex;
    fn get_system_graph(&self) -> &SystemGraph;
    fn get_identifier(&self) -> &str {
        &self.get_vertex().identifier
    }
    fn get_ports(&self) -> &[String] {
        &self.get_vertex().ports
    }
    fn get_traits(&self) -> &[Arc<dyn Trait>] {
        &self.get_vertex().traits
    }
    fn get_vertex_properties(&self) -> &HashMap<String, VertexProperty> {
        &self.get_vertex().properties
    }
}

#[derive(Clone, Debug, PartialEq)]
pub enum VertexProperty {
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

impl TryFrom<VertexProperty> for bool {
    type Error = String;

    fn try_from(value: VertexProperty) -> Result<Self, Self::Error> {
        match value {
            VertexProperty::BooleanProperty(v) => Ok(v),
            _ => Err("VertexProperty is not a boolean".to_string()),
        }
    }
}

impl TryFrom<VertexProperty> for i32 {
    type Error = String;

    fn try_from(value: VertexProperty) -> Result<Self, Self::Error> {
        match value {
            VertexProperty::IntProperty(v) => Ok(v),
            _ => Err("VertexProperty is not an integer".to_string()),
        }
    }
}

impl TryFrom<VertexProperty> for u32 {
    type Error = String;

    fn try_from(value: VertexProperty) -> Result<Self, Self::Error> {
        match value {
            VertexProperty::UIntProperty(v) => Ok(v),
            _ => Err("VertexProperty is not an unsigned integer".to_string()),
        }
    }
}

impl TryFrom<VertexProperty> for i64 {
    type Error = String;

    fn try_from(value: VertexProperty) -> Result<Self, Self::Error> {
        match value {
            VertexProperty::LongProperty(v) => Ok(v),
            _ => Err("VertexProperty is not a long".to_string()),
        }
    }
}

impl TryFrom<VertexProperty> for u64 {
    type Error = String;

    fn try_from(value: VertexProperty) -> Result<Self, Self::Error> {
        match value {
            VertexProperty::ULongProperty(v) => Ok(v),
            _ => Err("VertexProperty is not an unsigned long".to_string()),
        }
    }
}

impl TryFrom<VertexProperty> for f32 {
    type Error = String;

    fn try_from(value: VertexProperty) -> Result<Self, Self::Error> {
        match value {
            VertexProperty::FloatProperty(v) => Ok(v),
            _ => Err("VertexProperty is not a float".to_string()),
        }
    }
}

impl TryFrom<VertexProperty> for f64 {
    type Error = String;

    fn try_from(value: VertexProperty) -> Result<Self, Self::Error> {
        match value {
            VertexProperty::DoubleProperty(v) => Ok(v),
            _ => Err("VertexProperty is not a double".to_string()),
        }
    }
}

impl TryFrom<VertexProperty> for String {
    type Error = String;

    fn try_from(value: VertexProperty) -> Result<Self, Self::Error> {
        match value {
            VertexProperty::StringProperty(v) => Ok(v),
            _ => Err("VertexProperty is not a string".to_string()),
        }
    }
}

impl<T: TryFrom<VertexProperty>> TryFrom<VertexProperty> for Vec<T> 
where 
    String: From<<T as TryFrom<VertexProperty>>::Error> 
{
    type Error = String;

    fn try_from(value: VertexProperty) -> Result<Self, Self::Error> {
        match value {
            VertexProperty::ArrayProperty(v) => {
                let mut result = Vec::new();
                for item in v {
                    result.push(T::try_from(item)?);
                }
                Ok(result)
            },
            _ => Err("VertexProperty is not an array".to_string()),
        }
    }
}

impl<T: TryFrom<VertexProperty>> TryFrom<VertexProperty> for HashMap<String, T>
where
    String: From<<T as TryFrom<VertexProperty>>::Error>
{
    type Error = String;

    fn try_from(value: VertexProperty) -> Result<Self, Self::Error> {
        match value {
            VertexProperty::MapProperty(v) => {
                let mut mapping = HashMap::new();
                for (key, item) in v {
                    mapping.insert(key, T::try_from(item)?);
                }
                Ok(mapping)
            },
            _ => Err("VertexProperty is not a map".to_string()),
        }
    }
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

    pub fn complies(&self, t: &dyn Trait) -> bool {
        self.traits.iter().any(|x| x.refines(t))
    }
}

pub type SystemGraph = petgraph::graph::DiGraph<Vertex, EdgeInfo, petgraph::graph::DefaultIx>;


pub trait ModelDriver {
    fn load_model(&self, model_path: &str) -> Result<SystemGraph, String>;
    fn read_model(&self, model: &[u8], format: &str) -> Result<SystemGraph, String>;
    fn write_model(&self, model: &SystemGraph, model_path: &str) -> Result<(), String>;
    fn print_model(&self, model: &SystemGraph, format: &str) -> Result<Vec<u8>, String>;
}

pub struct ModelHandler {
    pub trait_converters: Vec<Box<dyn Fn(&str) -> Option<Arc<dyn Trait>>>>,
    pub drivers: Vec<Box<dyn ModelDriver>>,
}

impl Default for ModelHandler {
    fn default() -> Self {
        ModelHandler {
            trait_converters: Vec::new(),
            drivers: vec![Box::new(fiodl::FiodlDriver)],
        }
    }
}

impl ModelHandler {
    pub fn load_model(&self, model_path: &str) -> Result<SystemGraph, String> {
        let mut last_error = Err("No driver registered".to_string());
        for driver in &self.drivers {
            match driver.load_model(model_path) {
                Ok(mut model) => {
                    for node in model.node_weights_mut() {
                        for trait_converter in &self.trait_converters {
                            for i in 0..node.traits.len() {
                                let t = &node.traits[i];
                                if let Some(trait_obj) = trait_converter(t.get_name()) {
                                    node.traits.insert(i, trait_obj);
                                    node.traits.remove(i + 1);
                                }
                            }
                        }
                    }
                    return Ok(model)
                },
                e => {
                    last_error = e;
                },
            }
        }
        last_error
    }
    pub fn read_model(&self, model: &[u8], format: &str) -> Result<SystemGraph, String> {
        let mut last_error = Err("No driver registered".to_string());
        for driver in &self.drivers {
            match driver.read_model(model, format) {
                Ok(mut model) => {
                    for node in model.node_weights_mut() {
                        for trait_converter in &self.trait_converters {
                            for i in 0..node.traits.len() {
                                let t = &node.traits[i];
                                if let Some(trait_obj) = trait_converter(t.get_name()) {
                                    node.traits.insert(i, trait_obj);
                                    node.traits.remove(i + 1);
                                }
                            }
                        }
                    }
                    return Ok(model)
                },
                e => {
                    last_error = e;
                },
            }
        }
        last_error
    }
    pub fn write_model(&self, model: &SystemGraph, model_path: &str) -> Result<(), String> {
        let mut last_error = Err("No driver registered".to_string());
        for driver in &self.drivers {
            match driver.write_model(model, model_path) {
                Ok(_) => return Ok(()),
                e => {
                    last_error = e;
                },
            }
        }
        last_error
    }
    pub fn print_model(&self, model: &SystemGraph, format: &str) -> Result<Vec<u8>, String> {
        let mut last_error = Err("No driver registered".to_string());
        for driver in &self.drivers {
            match driver.print_model(model, format) {
                Ok(model) => return Ok(model),
                e => {
                    last_error = e;
                },
            }
        }
        last_error
    }
}