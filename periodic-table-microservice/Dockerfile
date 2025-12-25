FROM maven:3.9.11-amazoncorretto-21-al2023 AS build
COPY . .
RUN --mount=type=cache,target=/root/.m2 mvn clean package

FROM gcr.io/distroless/java21-debian12:nonroot
ARG VERSION=0.0.1-SNAPSHOT
COPY --from=build target/periodic-table-microservice-${VERSION}.jar .
CMD ["periodic-table-microservice-${VERSION}.jar"]