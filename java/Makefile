GRADLE := ./gradlew

publish-local.task:
	$(GRADLE) publishToMavenLocal

publish-online.task: upload-online.task

upload-online.task: upload-bintray.task

upload-bintray.task:
	$(GRADLE) bintrayUpload

