FROM openjdk:11.0.1-jdk-slim

# setting up env with memory and security
ENV JAVA_OPTS " -Xms512m -Xmx512m -Djava.security.egd=file:/dev/./urandom"

WORKDIR application
# copy from target to work directory
COPY target/student-data-ui-app-0.0.1-SNAPSHOT.jar ./

ENTRYPOINT ["java", "-jar", "student-data-ui-app-0.0.1-SNAPSHOT.jar"]

# docker build -f ./Dockerfile -t student-data-ui-app .

EXPOSE 8081