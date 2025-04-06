# Ignite Server

This is a Spring Boot application with Apache Ignite integration, providing REST API endpoints and built-in SQLline support.

## Features

- Spring Boot integration
- Apache Ignite server node
- REST API endpoints
- Built-in SQLline support
- Docker support
- Jenkins CI/CD pipeline
- Ignite REST API support

## Prerequisites

- Java 17
- Maven 3.6+
- Docker (optional)
- Jenkins (optional)

## Building the Application

```bash
mvn clean install
```

## Running the Application

### Using Maven

```bash
mvn spring-boot:run
```

The application will be available at http://localhost:8082

### Using Docker

```bash
mvn clean package dockerfile:build
docker run -p 8082:8082 ignite-server
```

## Stopping the Service

### On Linux/MacOS

```bash
# Find the process ID
ps aux | grep ignite-server

# Kill the process
kill <PID>

# Or use the port number to find and kill the process
lsof -i :8082 | grep LISTEN
kill $(lsof -t -i:8082)
```

### On Windows

```bash
# Find the process ID
netstat -ano | findstr :8082

# Kill the process
taskkill /PID <PID> /F
```

## REST API Endpoints

### Ignite REST API

- `GET /ignite?cmd=version` - Get Ignite version
- `GET /ignite?cmd=top` - Get topology
- `GET /ignite?cmd=node` - Get node information

### Custom REST API

- `GET /api/hello` - Hello endpoint
- `GET /api/cache/{cacheName}` - Get cache information
- `POST /api/cache/{cacheName}` - Create cache
- `DELETE /api/cache/{cacheName}` - Remove cache

## SQL Functionality

The application supports SQL queries through Ignite's SQL engine. You can use any JDBC-compliant tool to connect to the database.

## Configuration

The application uses the following configuration files:

- `application.properties` - Spring Boot configuration
- `ignite-config.xml` - Ignite configuration

## License

This project is licensed under the MIT License.

### Using Built-in SQLLine

The application automatically starts SQLLine when running. You can use it to execute SQL commands:

```sql
-- Create a table
CREATE TABLE IF NOT EXISTS Person (
    id INT PRIMARY KEY,
    name VARCHAR,
    age INT
) WITH "template=partitioned,CACHE_NAME=PersonCache";

-- Insert data
INSERT INTO Person (id, name, age) VALUES (1, 'John', 30);

-- Query data
SELECT * FROM Person;
```

Default connection details:
- URL: jdbc:ignite:thin://localhost
- Username: ignite
- Password: ignite

## Jenkins Integration

The project includes a Jenkinsfile for CI/CD pipeline. The pipeline includes:
- Build
- Test
- Build Docker image
- Push Docker image