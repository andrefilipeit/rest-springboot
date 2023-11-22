# rest-springboot
REST API's RESTFul - Spring Boot 3 Java and Docker

## Requirements

| Technologies Used                       | Version |
| ---                                     | ---       |
| `Java 20`                               | [download](https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html)  |
| `MAVEN 3.6.0`                           | [download](https://maven.apache.org/download.cgi) |
| `Spring Tools Suite 4.19.0`             | [download](https://spring.io/tools) |
| `MySQL Server Installer (MSI) 8.0.34`   | [download](https://dev.mysql.com/downloads/installer) |
| `MySQL Workbench `                      | [download](https://dev.mysql.com/downloads/workbench/)|
| `HeidiSQL 12.5.0`                       | [download](https://www.heidisql.com/download.php?download=installer#google_vignette)|
| `Postman`   | [download](https://www.postman.com/downloads/) |
| `Docker`   | [download](https://www.docker.com/products/docker-desktop/) |



:bulb: Download Spring Tools Suite and extract using: ```> java -jar pathJarDowloadedName.jar```


:exclamation: [Possible problem 1](https://www.youtube.com/watch?v=zvnFtNlD1eo)  
:exclamation: [Possible problem 2 '-docker  info-'
](https://stackoverflow.com/questions/57460393/the-docker-client-must-be-run-elevated-to-connect#:~:text=This%20error%20may%20also%20indicate,Anything%20else%20I%20can%20do%20%3F&text=Go%20to%20the%20directory%20where,if%20it%20solves%20the%20problem)  


## Database Config

user: root/docker <br /> 
pass: admin123 <br />
[see more](https://github.com/andrefilipeit/rest-springboot/blob/develop/src/main/resources/application.yml)

Helpful Docker Commands:

  * on the file docker-compose.yml path ```> docker compose up -d --build``` 
  * ```docker compose ls```  for list execution apps
  * ```docker ps```  for list containers in execution
  * ```docker logs id```, example: docker logs e23bvd - for full log from creating container app
  * ```docker login/logout```
  * ```docker images```
  * ```docker push [id image]```


[![Docker Hub Repo](https://img.shields.io/docker/pulls/DOCKER_HUB_USERNAME/RESPOSITORY_NAME.svg)](https://hub.docker.com/repository/docker/andrefilipeit/rest-springboot)

## Starting from zero

Project created with [Spring Initializr](https://start.spring.io),
but for create local project on SpringToolsSuite use:
Spring Starter Project (name equals git repo folder name) ```> Artifact > equals (repeat it)```

Initial Dependencies: <br />
[Spring Web](https://mvnrepository.com/artifact/org.springframework/spring-web) <br />
[Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-devtools.html)
