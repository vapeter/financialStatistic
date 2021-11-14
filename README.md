# Financial statistic demo application

This demo application was developed for hobby purposes usingspring-boot, thymeleaf, mysql, keycloak, and docker.

## Prerequisites
* Java 11
* Spring Boot 2.4.4
* Maven 3.5.0+
* MySQL 8.0.23
* Docker Engine 19.03.0+
* Keycloak 11.0.2


# Development
## Building

Please set in the src/main/resources/application.properties -> keycloak.auth-server-url variable at your ip address

* From Command Line `mvn clean package`


## Running the Application
From command line:
```
docker network create financialstat-network
```
* Please navigate to the downloaded folder, then enter 

```
docker-compose up -d
```
* please, wait a few minute

Open a browser and visit [http://localhost:8080](http://localhost:8080) for Configuring Keycloak
* Click to the Administration Console
* >Username: admin
* >Password: KeycloakAdmin

#### 1. Create the following user
* On Users page, click Add user, choose a username, and the Email Verified switch should be ON -> save
* On the Credentials tab, set a password
* On the Role Mappings tab, highlight the `users` option in the **Available Roles** block and then click on the "Add selected" button.

### Enter the application
* >Open a browser and visit [http://localhost:8500](http://localhost:8500) for using the application
  * Click login, and enter the username and password


# Spring Boot Configuration Properties
Please see [here](http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)
for details.
