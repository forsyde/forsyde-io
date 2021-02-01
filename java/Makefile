TYPES_DIR := src/main/java/forsyde/io/java/types/
GRADLE := ./gradlew

publish-local:
	$(GRADLE) publishToMavenLocal

clean-code-types:
	$(RM) -rf $(TYPES_DIR)

publish-online: upload-online

upload-online: upload-bintray

upload-bintray:
	$(GRADLE) bintrayUpload

inject-sql:
	@echo "!!!JAVA inject sql not implemented yet!!!"
