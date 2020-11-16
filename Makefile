SQL_DIR := sql
PYTHON_DIR := python
JAVA_DIR := java
HASKELL_DIR := haskell
PROLOG_DIR := prolog

all: generate-code.task

generate-code.task: \
	generate-code-types.task

generate-code-types.task:
	./gradlew run

publish-local.task: publish-local-all.task

publish-local-all.task: \
	publish-local-python.task \
	publish-local-java.task \
	publish-local-haskell.task

publish-local-python.task: generate-code.task
	$(MAKE) -C $(PYTHON_DIR) publish-local.task

publish-local-java.task: generate-code.task
	$(MAKE) -C $(JAVA_DIR) publish-local.task

publish-local-haskell.task: generate-code.task
	$(MAKE) -C $(HASKELL_DIR) publish-local.task

publish-online.task: publish-online-all.task

publish-online-all.task: generate-code.task
	$(MAKE) -C $(PYTHON_DIR) publish-online.task
	$(MAKE) -C $(JAVA_DIR) publish-online.task
