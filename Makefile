GITHUB_RUN_ID=make
PROJECTS=example bank

.PHONY: all docker

all: docker

docker:
	echo "${PROJECTS}"
	for project in ${PROJECTS}; do \
		docker build $${project} --tag mukkak/$${project}:latest; \
	done
