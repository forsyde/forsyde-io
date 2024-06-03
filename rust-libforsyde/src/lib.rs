use forsyde_io_core::ModelHandler;

include!(concat!(env!("OUT_DIR"), "/libforsyde_gen.rs"));

pub fn register_libforsyde(model_handler: &mut ModelHandler) {
    model_handler.trait_converters.push(Box::new(ForSyDeHierarchy::trait_from_str));
}


#[cfg(test)]
mod tests {
    use forsyde_io_core::VertexViewer;

    use crate::{register_libforsyde, ForSyDeHierarchy};


    #[test]
    fn read() {
        let mut model_handler = forsyde_io_core::ModelHandler::default();
        register_libforsyde(&mut model_handler);
        let system_graph = model_handler.load_model("../examples/sdf/toy_sdf_tiny2.fiodl").unwrap();
        for v in system_graph.node_weights() {
            if let Some(viewer) = ForSyDeHierarchy::SDFActorViewer::try_view(v, &system_graph) {
                println!("Actor {} is an SDF actor", viewer.get_identifier());
            }
        }
        // println!("{:?}", system_graph);
        println!("{}", String::from_utf8(model_handler.print_model(&system_graph, "fiodl").unwrap()).unwrap());
    }
}
