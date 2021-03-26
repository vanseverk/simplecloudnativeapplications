#Simple Cloud Native Applications
Two Spring Boot applications ready to be put on a Cloud Native platform.

##journalapp-web
* Simple web application, based on Spring MVC with Thymeleaf
* Requires to be exposed to the outside world
* Requires to be able to reach the *journalapp-backend*

##journalapp-backend
* Simple backend application, offering an API through Spring REST
* Requires to be exposed to the journalapp-web through HTTP
* Requires to be able to read and write to a file


##building the images
```
mvn spring-boot:build-image
docker tag <existing-image> <hub-user>/<repo-name>[:<tag>]
docker push <hub-user>/<repo-name>:<tag>
```