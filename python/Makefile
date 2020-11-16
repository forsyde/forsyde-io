TYPES_DIR := forsyde/io/python/types
SQL_DIR := forsyde/io/python/sql
PYTHON := python3
PIP := $(PYTHON) -m pip 

publish-local:
	$(PIP) install .

clean-code-types:
	$(RM) -rf $(TYPES_DIR)

publish-online: publish-pypi

update-tools: 
	$(PIP) install --user --upgrade setuptools wheel twine

gen-dist-archives: update-tools
	$(PYTHON) setup.py sdist bdist_wheel

publish-testpypi: gen-dist-archives
	$(PYTHON) -m twine upload --repository testpypi dist/*

publish-pypi: gen-dist-archives
	$(PYTHON) -m twine upload dist/*

inject-sql:
	@mkdir -p $(SQL_DIR)
	@cp -r sql/* $(SQL_DIR)
