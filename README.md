# HR Management System

## Project Overview

The HR Management System is a Spring Boot REST API application designed to manage employee information and HR operations. It demonstrates RESTful API development, database integration, input validation, exception handling, security, and automated testing.

## Technologies Used

* Java 21
* Spring Boot 4.1.1
* Spring Data JPA and Hibernate
* MySQL 8
* Spring Security
* Maven
* Swagger / OpenAPI
* Spring Boot Actuator
* JUnit and Mockito

## Features

* Employee CRUD operations
* Input validation and global exception handling
* Pagination and sorting
* Leave request management
* Transaction management
* Audit logging
* Role-based access control
* JPQL and native SQL queries
* API documentation using Swagger
* Application health monitoring using Actuator
* Caching and scheduled tasks
* Unit and integration tests

## Prerequisites

* Java 261 or a compatible configured Java version
* Maven
* MySQL Server and MySQL Workbench
* Spring Tool Suite (STS) or another Java IDE

## Database Setup

1. Start MySQL Server.

2. Create the database if it does not already exist:

   ```sql
   CREATE DATABASE hr_management;
   ```

3. Import `database/hr_management.sql` using MySQL Workbench.

4. Configure the database credentials using environment variables.

   Windows Command Prompt:

   ```cmd
   set DB_USERNAME=root
   set "DB_PASSWORD=YOUR_MYSQL_PASSWORD"
   ```

   Replace `YOUR_MYSQL_PASSWORD` with your local MySQL password. Do not publish your actual password.

## Run the Application

1. Open the project in STS.
2. Ensure MySQL is running and the database credentials are configured.
3. Run the main Spring Boot application class.

Alternatively, build and run the executable JAR:

```bash
mvn clean package
java -jar target/hrmanagement-0.0.1-SNAPSHOT.jar
```

The application uses port `8080` by default.

## API Documentation

Swagger UI:

http://localhost:8080/swagger-ui/index.html

## Health Check

http://localhost:8080/actuator/health

A successful health check should return a response containing `"status":"UP"`.

## Demo Login Accounts

| Role     | Username   | Password       |
| -------- | ---------- | -------------- |
| Admin    | `admin`    | `Admin@123`    |
| HR       | `hr`       | `Hr@123`       |
| Employee | `employee` | `Employee@123` |

These are demonstration accounts only and should not be used in production.

## Testing

The project includes Spring Boot tests, controller integration tests, and service unit tests using JUnit and Mockito.

Run the tests:

```bash
mvn test
```

The latest successful test run completed with 17 tests, 0 failures, 0 errors, and 0 skipped.

## Build Executable JAR

Run:

```bash
mvn clean package
```

The executable JAR is generated in the `target` directory.

## Project Structure

```text
HRManagementSystem/
├── src/
├── database/
│   └── hr_management.sql
├── screenshots/
├── README.md
├── pom.xml
└── .gitignore
```

## Author

Developed as a Spring Boot HR Management System project.
