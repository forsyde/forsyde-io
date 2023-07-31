import forsyde.io.python.api as forsyde_api


def read_and_write():
    model = forsyde_api.load_model('../examples/sobel2mpsoc.forxml')
    forsyde_api.write_model(model, "sobel2mpsoc_vis.graphml")
