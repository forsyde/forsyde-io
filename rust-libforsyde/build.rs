fn main() {
    let schema =
        std::fs::read_to_string("../java-libforsyde-hierarchy/build/generated/sources/annotationProcessor/java/main/forsyde.io.lib.hierarchy.ForSyDeHierarchy.json").unwrap();
    let hierarchy_code = forsyde_io_generator::from_json_trait_hierarchy_to_code(&schema).unwrap();
    let out_dir = std::path::PathBuf::from(std::env::var("OUT_DIR").unwrap()).join(std::path::PathBuf::from("libforsyde_gen.rs"));
    std::fs::write(out_dir, hierarchy_code).unwrap();
    // println!("{:?}", hierarchy);
    // let code = TokenStream::from(&hierarchy);
}