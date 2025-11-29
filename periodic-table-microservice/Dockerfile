FROM gcr.io/distroless/java21-debian12:nonroot
COPY target/periodic-table-microservice-0.0.1-SNAPSHOT.jar /app/target
WORKDIR /app
CMD ["target/periodic-table-microservice-0.0.1-SNAPSHOT.jar"]