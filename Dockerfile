#FROM openjdk:17-jdk-alpine
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
#ARG DEPENDENCY=target/dependency
#COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY ${DEPENDENCY}/META-INF /app/META-INF
#COPY ${DEPENDENCY}/BOOT-INF/classes /app
#ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.fleaMarket"]

FROM amazoncorretto:21 AS build
WORKDIR /home/app
COPY ./ ./
RUN ./gradlew bootJar

FROM amazoncorretto:21-alpine
WORKDIR /app
COPY --from=build /home/app/build/libs/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]