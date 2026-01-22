# App dependencies download stage
# https://vsupalov.com/5-tips-to-speed-up-docker-build/
FROM maven:3-amazoncorretto-21 AS deps
ENV WORKDIR=/api
ENV MAVEN_REPO=$WORKDIR/.m2
WORKDIR $WORKDIR
COPY pom.xml .
RUN mvn -Dmaven.repo.local=$MAVEN_REPO dependency:go-offline

# App build stage
FROM deps as build
WORKDIR $WORKDIR
COPY . .
COPY --from=deps $MAVEN_REPO .

# Ignora os testes pois eles já são executados no workflow de build do GitHub Actions
RUN mvn -Dmaven.repo.local=$MAVEN_REPO package -DskipTests

# App copy stage
FROM amazoncorretto:21-alpine
WORKDIR $WORKDIR
RUN apk add curl
COPY --from=build /api/target/vendas-api-*.jar "./vendas-api.jar"
EXPOSE 8080
CMD [ "java", "-jar", "vendas-api.jar" ]
