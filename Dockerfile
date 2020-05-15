# TODO: Replace with jdk11 maven / postgres combo
FROM maven:3-jdk-14

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean install

FROM openjdk:14
WORKDIR /app
COPY --from=0 /app/target/*.jar ./target/
COPY run.sh ./

EXPOSE 8080

CMD ["/app/run.sh"]