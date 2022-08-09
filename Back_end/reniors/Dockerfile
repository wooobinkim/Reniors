FROM openjdk:8

RUN chmod =x ./gradlew
RUN ./gradlew bootJar

ADD ./build/libs/reniors-0.0.1-SNAPSHOT.jar reniors.jar

EXPOSE 8080
CMD ["java","-jar","reniors.jar"]