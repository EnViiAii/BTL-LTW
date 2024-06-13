FROM maven:3.9.3-eclipse-temurin-17-alpine AS build
COPY . /build/
WORKDIR /build
RUN --mount=type=cache,id=s/0d714b28-5f8b-410a-9ecf-2f725bad1960,target=/root/.m2 mvn clean package

FROM tomcat:8.5.99-jdk17-corretto
COPY --from=build /build/target/btl.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]