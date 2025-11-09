# Spring Boot Redis Caching Example

This project demonstrates the implementation of Redis caching in a Spring Boot application with MySQL as the primary database. It showcases a simple blog/article system with categories, articles, and authors.

## Technology Stack

- Java Spring Boot
- Redis (for caching)
- MySQL (primary database)
- Spring Data JPA
- Lombok
- Gradle

## Project Structure

The application follows a standard Spring Boot architecture:

- **Models**: Entity classes representing the domain
  - `Article`: Contains URL, title, content, and relationships to Category and Authors
  - `Category`: Represents article categories
  - `Author`: Represents article authors
- **Repositories**: Data access layer with Redis caching
- **Services**: Business logic layer
- **Controllers**: REST API endpoints
- **DTOs**: Data transfer objects for API requests/responses
- **Exception**: Custom exception handling

## Setup Requirements

1. Java 17 or higher
2. Redis server running on localhost:6379
3. MySQL server running on localhost
4. Gradle

## Database Configuration

MySQL configuration in `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost/spring_data_jpa
spring.datasource.username=root
spring.datasource.password=root@123
```

Redis configuration:
```properties
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

## Running the Application

1. Start Redis server
2. Start MySQL server
3. Run the application:
   ```bash
   ./gradlew bootRun
   ```

The application will automatically create sample data including:
- Categories (Entertainment, Sport)
- Authors (3 sample authors)
- Articles (2 sample articles)

## Features

1. Redis caching integration with Spring Boot
2. Many-to-Many relationship between Articles and Authors
3. One-to-Many relationship between Categories and Articles
4. Automatic database schema generation
5. Sample data generation for testing
6. Exception handling for Category not found scenarios

## Project Testing

The project includes controller tests demonstrating the API functionality. Run tests using:
```bash
./gradlew test
```

## API Endpoints

The application exposes the following REST endpoints:

1. **Get All Categories**
   - Endpoint: `GET /category`
   - Response: List of all categories
   - Status Codes:
     - 200: Success

2. **Get Articles by Category**
   - Endpoint: `GET /category/{id}/articles`
   - Parameters:
     - `id`: Category ID (path parameter)
   - Response: List of articles in the specified category
   - Status Codes:
     - 200: Success
     - 400: Category not found

## Notes

- The application uses Hibernate's `create-drop` strategy, which means the database is recreated each time the application starts
- Redis is used for caching to improve read performance
- Lombok is used to reduce boilerplate code