FROM openjdk:8
ADD target/product-database.jar product-database.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "product-database.jar"]
