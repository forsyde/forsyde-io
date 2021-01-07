SQL_DIR := sql
PYTHON_DIR := python
JAVA_DIR := java
HASKELL_DIR := haskell
PROLOG_DIR := prolog

PYTHON_SQL_DIR := $(PYTHON_DIR)/forsyde/io/python/sql
JAVA_SQL_DIR := $(JAVA_DIR)/src/main/resources/sql
HASKELL_SQL_DIR := $(HASKELL_DIR)/sql

LIBS := PYTHON JAVA HASKELL

all: generate-code

generate-code:\
	inject-sql
	@./gradlew run

inject-sql: $(addprefix inject-sql-,$(LIBS))

inject-sql-%:
	@mkdir -p $($*_SQL_DIR)
	@cp -r $(SQL_DIR)/* $($*_SQL_DIR)

clean-code-%:
	@$(MAKE) -C $($*_DIR) clean-code

publish-local: $(addprefix publish-local-,$(LIBS))

publish-local-%: generate-code
	$(MAKE) -C $($*_DIR) publish-local

publish-online: $(addprefix publish-online-,$(LIBS))
	
publish-online-%: generate-code
	$(MAKE) -C $($*_DIR) publish-online
