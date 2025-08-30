## builder
FROM amazoncorretto:21.0.8-alpine AS builder
WORKDIR /builder

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENV TZ=Asia/Seoul

RUN java -Djarmode=tools -jar app.jar extract --layers --destination extracted

## container
FROM amazoncorretto:21.0.8
WORKDIR /app
COPY --from=builder /builder/extracted/dependencies/ ./
COPY --from=builder /builder/extracted/spring-boot-loader/ ./
COPY --from=builder /builder/extracted/snapshot-dependencies/ ./
COPY --from=builder /builder/extracted/application/ ./

EXPOSE 8080
HEALTHCHECK --interval=10s --timeout=10s --retries=5 CMD curl --fail http://localhost:8080/actuator/health || exit 1

CMD ["java", "-jar", "app.jar"]
