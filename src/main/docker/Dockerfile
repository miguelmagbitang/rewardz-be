FROM maven:3.8.5-openjdk-17
RUN pwd
COPY . .
RUN mvn clean install -DskipTests
CMD mvn spring-boot:run
# ARG JAR_FILE=*.jar
# COPY ${JAR_FILE} application.jar
# ENTRYPOINT ["java", "-jar", "application.jar"]