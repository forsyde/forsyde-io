TYPES_DIR := forsyde/io/python/types
SQL_DIR := forsyde/io/python/sql
PYTHON := python3
PIP := $(PYTHON) -m pip 
POETRY := poetry

publish-local:
	$(PIP) install .

clean-code-types:
	$(RM) -rf $(TYPES_DIR)

publish-online: publish-pypi

update-tools: 
	$(PIP) install --user --upgrade setuptools wheel twine

build: update-tools
	$(POETRY) build

publish-testpypi: gen-dist-archives
	$(POETRY) publish --repository testpypi-token.pypi

publish-pypi: build
	$(POETRY) publish 

inject-sql:
	@mkdir -p $(SQL_DIR)
	@mv -r sql/* $(SQL_DIR)
