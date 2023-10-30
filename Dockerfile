FROM eclipse-temurin:17-jdk
ARG REPO_URL=https://github.com/StephanyCS1/taxislibres-prueba/target/pruebaTecnica-0.0.1-SNAPSHOT.jar
RUN wget -O pruebaTecnica-0.0.1-SNAPSHOT.jar ${REPO_URL}
ENTRYPOINT ["java", "-jar", "/pruebaTecnica-0.0.1-SNAPSHOT.jar"]
LABEL authors="StephanyCastroSalas"
