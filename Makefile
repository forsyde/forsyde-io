PYTHON_DIR := python-io
JAVA_DIR := java-io
HASKELL_DIR := haskell-io

all: generate-code.task

generate-code.task:
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
