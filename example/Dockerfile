FROM maven:3-eclipse-temurin-21 as builder

WORKDIR /usr/src/project

COPY pom.xml ./pom.xml
COPY src ./src

RUN --mount=type=cache,target=/root/.m2 mvn --batch-mode --update-snapshots verify
RUN mkdir dist && \
    cp target/*.jar dist/application.jar && \
    cd dist && \
    java -Djarmode=layertools -jar application.jar extract

CMD ["/bin/bash"]

FROM eclipse-temurin:21-jre

ENV APP_USER="mukkak"
ENV APP_DIR="/home/${APP_USER}"
RUN /usr/sbin/groupadd -r -g 1000 ${APP_USER} && \
    /usr/sbin/useradd -m -g 1000 -u 1000 -s /bin/bash -d ${APP_DIR} ${APP_USER}

WORKDIR ${APP_DIR}
COPY --from=builder /usr/src/project/dist/dependencies ./
COPY --from=builder /usr/src/project/dist/spring-boot-loader ./
COPY --from=builder /usr/src/project/dist/snapshot-dependencies ./
COPY --from=builder /usr/src/project/dist/application ./
COPY entrypoint.sh ./

RUN ls -lh ${APP_DIR} && \
    chown -R ${APP_USER}:${APP_USER} ${APP_DIR} && \
    chmod 500 ${APP_DIR}/entrypoint.sh

USER ${APP_USER}
EXPOSE 8080
ENTRYPOINT ${APP_DIR}/entrypoint.sh
