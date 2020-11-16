TYPES_DIR := forsyde/io/python/types
PYTHON := python3
PIP := $(PYTHON) -m pip 

publish-local.task:
	$(PIP) install .

clean-code-types.task:
	$(RM) -rf $(TYPES_DIR)

publish-online.task: publish-pypi.task

update-tools.task: 
	$(PIP) install --user --upgrade setuptools wheel twine

gen-dist-archives.task: update-tools.task
	$(PYTHON) setup.py sdist bdist_wheel

publish-testpypi.task: gen-dist-archives.task
	$(PYTHON) -m twine upload --repository testpypi dist/*

publish-pypi.task: gen-dist-archives.task
	$(PYTHON) -m twine upload dist/*
