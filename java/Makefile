TYPES_DIR := src/main/java/forsyde/io/java/types/
GRADLE := ./gradlew

publish-local.task:
	$(GRADLE) publishToMavenLocal

clean-code-types.task:
	$(RM) -rf $(TYPES_DIR)

publish-online.task: upload-online.task

upload-online.task: upload-bintray.task

upload-bintray.task:
	$(GRADLE) bintrayUpload

