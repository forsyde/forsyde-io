use std::{
    fs::File,
    io::{BufReader, Read},
};

pub mod auto;

fn load_model_flatbuffer(model_path: &str) -> Result<auto::SystemGraph, String> {
    let file = File::open(model_path).map_err(|e| format!("Failed to open file: {}", e))?;
    let mut buf_reader = BufReader::new(file);
    let mut buf = Vec::new();
    buf_reader
        .read_to_end(&mut buf)
        .map_err(|e| format!("Failed to read file: {}", e))?;
    let model = auto::root_as_system_graph(&buf);
    Err("Not implemented".to_string())
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn read() {
        let system_graph = load_model_flatbuffer("../test_fb.fiofb").unwrap();
        println!("{:?}", system_graph);
    }
}
