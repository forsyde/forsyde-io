include!(concat!(env!("OUT_DIR"), "/libforsyde_gen.rs"));

use forsyde_io_core::ModelHandler;
use forsyde_io_core_macro::make_trait_hierarchy;

pub fn register_libforsyde(model_handler: &mut ModelHandler) {
    model_handler
        .trait_converters
        .push(Box::new(ForSyDeHierarchy::trait_from_str));
}

make_trait_hierarchy! {
    mod TestingMod {
        trait TestingTrait {}

        trait TestingTrait2 : TestingTrait {}
    }
}

#[cfg(test)]
mod tests {
    use crate::ForSyDeHierarchy::IsSDFChannel;
    use forsyde_io_core::VertexViewer;

    use crate::{register_libforsyde, ForSyDeHierarchy};

    #[test]
    fn read() {
        let mut model_handler = forsyde_io_core::ModelHandler::default();
        register_libforsyde(&mut model_handler);
        let system_graph = model_handler
            .load_model("../examples/sdf/toy_sdf_tiny2.fiodl")
            .unwrap();
        for v in system_graph.node_weights() {
            if let Some(viewer) = ForSyDeHierarchy::SDFActorViewer::try_view(v, &system_graph) {
                println!("Actor {} is an SDF actor", viewer.get_identifier());
            }
            if let Some(viewer) = ForSyDeHierarchy::SDFChannelViewer::try_view(v, &system_graph) {
                println!(
                    "Channel {} is an SDF channel with {} initial tokens",
                    viewer.get_identifier(),
                    viewer.get_numInitialTokens()
                );
                if let Some(p) = viewer.get_producer() {
                    println!("Producer: {}", p.get_identifier());
                }
                if let Some(c) = viewer.get_consumer() {
                    println!("Consumer: {}", c.get_identifier());
                }
            }
        }
        // println!("{:?}", system_graph);
        // println!("{}", String::from_utf8(model_handler.print_model(&system_graph, "fiodl").unwrap()).unwrap());
    }
}
