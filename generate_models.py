import re
import os
import subprocess

schema_src = os.path.join('origin', 'description.schema.json')
quicktype_pipe = subprocess.run(['quicktype', '-s', 'schema', 
                '-t', 'ForSyDeDescription',
                '-l', 'java', 
                '--array-type', 'list',
                '--package', 'forsyde.model',
                schema_src],
                capture_output = True)

# read the output of quicktype and separate it into the corresponding classes to be
# dumped later
java_header_re = re.compile('//\s+(\w+.java)')
java_classes = {}
current_class = None
for line in str(quicktype_pipe.stdout, 'utf-8').splitlines():
    if line.startswith('//') and '.java' in line:
        current_class = java_header_re.match(line).groups()[0]
    if current_class != None:
        if not current_class in java_classes:
            java_classes[current_class] = ''
        #generate id stuff for serlization
        if 'public class' in line and current_class != 'Converter.java':
            line = '@JsonIdentityInfo(generator = ' +\
                'ObjectIdGenerators.PropertyGenerator.class, property = "id")\n' +\
                line
        java_classes[current_class] += line + os.linesep

# dump the rescued code into the right files for the java project
java_src_dest = os.path.join('java', os.path.join('src', os.path.join('main', 'java')))
java_models_src_dest = os.path.join(java_src_dest, os.path.join('forsyde', 'model'))
os.makedirs(java_models_src_dest, exist_ok=True)
for java_class in java_classes:
    code = java_classes[java_class]
    with open(os.path.join(java_models_src_dest, java_class), 'w') as src_file:
        src_file.write(code)
