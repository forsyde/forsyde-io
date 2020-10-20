from setuptools import setup, find_packages

setup(
    name='forsyde-io-python',
    version='0.5.1',
    url='gitr',
    author='Rodolfo Jordao',
    author_email='jordao@kth.se',
    description='Python bindings for reading, writing and manipulating ForSyDe-IO models',
    packages=find_packages(),    
    install_requires=['networkx'],
)
