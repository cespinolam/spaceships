Sure, here's the complete `README.md` content without any breaks:

```markdown
# Spaceship API

A Spring Boot application for CRUD maintenance of spaceships from series and movies.

## Requirements

- Java 17
- Maven 3.6+
- Docker (optional for containerization)

## Getting Started

### Project Setup

1. **Clone the repository:**

   ```bash
   git clone <repository-url>
   cd spaceship-api
   ```

2. **Build the project:**

   ```bash
   mvn clean install
   ```

3. **Run the application:**

   ```bash
   mvn spring-boot:run
   ```

### Configuration

The application uses an in-memory H2 database by default. You can access the H2 console at `http://localhost:8080/h2-console` with the following credentials:

- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** (leave blank)

### API Endpoints

- **Get all ships with pagination:**
  ```http
  GET /spaceships?page={page}&size={size}
  ```

- **Get a single ship by ID:**
  ```http
  GET /spaceships/{id}
  ```

- **Get ships by name containing a specific value:**
  ```http
  GET /spaceships/search?name={name}
  ```

- **Create a new ship:**
  ```http
  POST /spaceships
  Content-Type: application/json

  {
    "name": "Millennium Falcon",
    "series": "Star Wars"
  }
  ```

- **Update a ship:**
  ```http
  PUT /spaceships/{id}
  Content-Type: application/json

  {
    "name": "Updated Ship Name",
    "series": "Updated Series"
  }
  ```

- **Delete a ship:**
  ```http
  DELETE /spaceships/{id}
  ```

### Unit Testing

To run the unit tests, use the following command:

```bash
mvn test
```

### Logging Aspect

An aspect is implemented to log when a request is made for a ship with a negative ID. Check the logs to see these entries.

### Exception Management

The application has centralized exception management to handle various errors gracefully.

### Caching

The application uses caching to improve performance. You can customize the caching configuration in `application.properties`.

### Optional Improvements

- **Database Migrations:** Using Flyway for database migrations.
- **API Documentation:** Using Swagger for API documentation.
- **Security:** Implemented basic security using Spring Security.
- **Message Broker Integration:** Kafka producer/consumer integration.
- **Docker:** Dockerized application for easy deployment.

### Docker

To build and run the Docker container:

1. **Build the Docker image:**

   ```bash
   docker build -t spaceship-api .
   ```

2. **Run the Docker container:**

   ```bash
   docker run -p 8080:8080 spaceship-api
   ```

### API Documentation

Access the API documentation at `http://localhost:8080/swagger-ui/`.

## Contributing

TO DO

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any questions or support, please open an issue or contact the maintainer.
```