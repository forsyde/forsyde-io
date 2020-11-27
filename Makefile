SQL_DIR := sql
PYTHON_DIR := python
JAVA_DIR := java
HASKELL_DIR := haskell
PROLOG_DIR := prolog

LIBS := PYTHON JAVA HASKELL

all: generate-code

generate-code:\
	inject-sql
	@./gradlew run

inject-sql: $(addprefix inject-sql-,$(LIBS))

inject-sql-%:
	@mkdir -p $($*_DIR)/sql
	@cp -r $(SQL_DIR)/* $($*_DIR)/sql
	@$(MAKE) -C $($*_DIR) inject-sql

clean-code-%:
	@$(MAKE) -C $($*_DIR) clean-code

publish-local: $(addprefix publish-local-,$(LIBS))

publish-local-%: generate-code
	$(MAKE) -C $($*_DIR) publish-local

publish-online: $(addprefix publish-online-,$(LIBS))
	
publish-online-%: generate-code
	$(MAKE) -C $($*_DIR) publish-online
