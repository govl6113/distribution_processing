FROM openjdk:17
ARG JAR_FILE=build/libs/*-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "-Dmetadata.service-id=${SERVICE_ID}","-Dmetadata.replica-id=${REPLICA_ID}","-Dspring.redis.host=${REDIS_HOST}", "/app.jar"]

