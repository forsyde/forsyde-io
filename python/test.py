import sample

forsyde = sample.parse('../examples/sobel.sdf.example.xml')

print(forsyde.system.Process)


nodes = []
nodes.extend(forsyde.system.Process)
nodes.extend(forsyde.system.Signal)
nodes.extend(forsyde.system.Function)
nodes.extend(forsyde.system.Constructor)
for node in nodes:
    for node2 in nodes:
        if node != node2 and node.id == node2.id:
            print("Duplicate Ids!")
