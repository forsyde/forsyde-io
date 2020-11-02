PYTHON := python3
PIP := $(PYTHON) -m pip 

publish-local.task:
	$(PIP) install .

update-tools.task: 
	$(PIP) install --user --upgrade setuptools wheel twine

gen-dist-archives.task: update-tools.task
	$(PYTHON) setup.py sdist bdist_wheel

publish-testpypi.task: gen-dist-archives.task
	$(PYTHON) -m twine upload --repository testpypi dist/*
