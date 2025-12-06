FROM maven:3.9.11-amazoncorretto-21-al2023 AS build
COPY . .
RUN --mount=type=cache,target=/root/.m2 mvn clean package

FROM gcr.io/distroless/java21-debian12:nonroot
COPY --from=build target/periodic-table-microservice-0.0.1-SNAPSHOT.jar /target
CMD ["target/periodic-table-microservice-0.0.1-SNAPSHOT.jar"]