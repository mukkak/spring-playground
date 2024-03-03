MAVEN_GROUP_ID=$(shell mvn -q help:evaluate -DforceStdout -Dexpression=project.groupId)
MAVEN_ARTIFACT_ID=$(shell mvn help:evaluate -q -DforceStdout -Dexpression=project.artifactId)
MAVEN_ARTIFACT_VERSION=$(shell mvn help:evaluate -q -DforceStdout -Dexpression=project.version)

.PHONY: all docker

all: docker

docker:
	docker build -t ${MAVEN_GROUP_ID}/${MAVEN_ARTIFACT_ID}:latest .
