# BMW Test Task
## Task
_Call the following endpoint, and handle all the data you will get back in the response_

## Technologies
* Java 11
* PostgreSQL
* Log4J
* Maven
* Spring Boot
* JUnit
* Docker

## How to run a project
1. **Make sure that the local postgres server installed on your computer is inactive**
2. **Clone the project and navigate to the main project folder**
3. **Execute the command** `docker-compose up` **to deploy a postgresql database using a docker, to which the application will connect**
4. **Now you can run the application server or run tests**

## Functional description
* When the application starts, it accesses the provided endpoint to get all the data from the response, mapping them into models which are then inserted into the database that you started with the docker
* After starting the application, you can call a new internal endpoint ([GET] http://localhost:8080/api/v1/users), which in the response will contain all users and their related data from the postgres database
* After each request to an external or internal endpoint, the system will automatically record logs in the logs/log.log file, which will contain information about the request URI, response status code, http headers and response body
* You can also run tests that check the status codes and the number of users that return external and internal endpoints (to run the tests, run the test class `src/test/java/com/knubisoft/bmwtesttask/BmwTestTaskApplicationTests.java`)
