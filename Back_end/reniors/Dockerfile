FROM openjdk:8

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod =x ./gradlew
RUN ./gradlew bootJar

ADD ./build/libs/reniors-0.0.1-SNAPSHOT.jar reniors.jar

EXPOSE 8080
CMD ["java","-jar","reniors.jar"]