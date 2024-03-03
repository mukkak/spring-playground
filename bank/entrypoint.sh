#!/bin/bash

SPRING_PROFILE="${SPRING_PROFILE_NAME:-prod}"

echo "Starting service with profile ${SPRING_PROFILE}"
cd ${APP_DIR}
java \
  -Xms${JVM_MIN_HEAP:-512M} -Xmx${JVM_MAX_HEAP:-1G} \
  -Dspring.profiles.active=${SPRING_PROFILE} \
  org.springframework.boot.loader.launch.JarLauncher
