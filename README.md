# LoadBooking API

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-9.4-blue.svg)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/Maven-3.9.11-red.svg)](https://maven.apache.org/)

A backend system using Spring Boot and MySQL to manage Load & Booking operations efficiently. The system is optimized for performance, security, and scalability.

## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Running the Application](#-running-the-application)
- [API Documentation](#-api-documentation)
- [API Endpoints](#-api-endpoints)
- [Data Model](#-data-model)
- [Testing](#-testing)
- [Project Structure](#-project-structure)
- [Contributing](#-contributing)

## 🚀 Features

- **Load Management**: Create, read, update, and delete load bookings
- **RESTful API**: Well-structured REST endpoints with proper HTTP methods
- **Data Validation**: Input validation using Bean Validation annotations
- **Database Integration**: MySQL database with JPA/Hibernate ORM
- **API Documentation**: Interactive Swagger UI for API exploration
- **Error Handling**: Comprehensive error handling and validation
- **Status Management**: Track load status (POSTED, BOOKED, CANCELLED)
- **Auto-generated IDs**: UUID-based primary keys for better scalability

## 🛠 Tech Stack

- **Backend Framework**: Spring Boot 3.5.4
- **Language**: Java 21
- **Database**: MySQL 9.4.0
- **ORM**: Spring Data JPA with Hibernate
- **Build Tool**: Maven 3.9.11
- **Documentation**: SpringDoc OpenAPI 3 (Swagger)
- **Validation**: Bean Validation with Hibernate Validator
- **Development Tools**: Spring Boot DevTools, Lombok

## 📋 Prerequisites

Before running this application, make sure you have the following installed:

- **Java 21** or higher
- **Maven 3.6+** 
- **MySQL 8.0+**
- **Git** (for cloning the repository)

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone <repository-url>
cd LoadBooking
```

### 2. Setup MySQL Database

```sql
-- Create database
CREATE DATABASE acro1;

-- Create user (optional)
CREATE USER 'loaduser'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON acro1.* TO 'loaduser'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Configure Application Properties

Update `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/acro1
spring.datasource.username=root
spring.datasource.password=Mysql@123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Server Configuration
server.port=8080
```

## ⚙️ Configuration

The application uses the following configuration:

- **Database**: Configured to use MySQL with automatic schema generation
- **Port**: Runs on port 8080 by default
- **Logging**: SQL queries are logged for debugging
- **Validation**: Bean validation enabled for request validation

## 🏃‍♂️ Running the Application

### Option 1: Using Maven

```bash
# Run the application
mvn spring-boot:run
```

### Option 2: Using Java JAR

```bash
# Build the project
mvn clean package

# Run the JAR file
java -jar target/LoadBooking-0.0.1-SNAPSHOT.jar
```

### Option 3: Using IDE

Import the project into your favorite IDE (IntelliJ IDEA, Eclipse, VS Code) and run the `LoadBookingApplication.java` main class.

## 📚 API Documentation

### Swagger UI

Once the application is running, access the interactive API documentation at:

**🌐 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

### OpenAPI JSON

The OpenAPI specification is available at:

**📄 [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)**

## 🔗 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/load` | Create a new load |
| `GET` | `/load/{id}` | Get load by ID |
| `PUT` | `/load/{id}` | Update existing load |
| `DELETE` | `/load/{id}` | Delete load |

### Example API Calls

#### Create a New Load

```bash
curl -X POST http://localhost:8080/load \
  -H "Content-Type: application/json" \
  -d '{
    "shipperId": "SHIP001",
    "loadingPoint": "Mumbai",
    "unloadingPoint": "Delhi",
    "productType": "Electronics",
    "truckType": "Container",
    "noOfTrucks": 2,
    "weight": 1500.0,
    "comment": "Fragile items",
    "loadingDate": "2025-08-10T10:00:00",
    "unloadingDate": "2025-08-12T18:00:00"
  }'
```

#### Get Load by ID

```bash
curl -X GET http://localhost:8080/load/{load-id}
```

#### Update Load

```bash
curl -X PUT http://localhost:8080/load/{load-id} \
  -H "Content-Type: application/json" \
  -d '{
    "shipperId": "SHIP001",
    "loadingPoint": "Mumbai",
    "unloadingPoint": "Chennai",
    "status": "BOOKED"
  }'
```

#### Delete Load

```bash
curl -X DELETE http://localhost:8080/load/{load-id}
```

## 📊 Data Model

### Load Entity

```java
{
  "id": "uuid",                    // Auto-generated UUID
  "shipperId": "string",          // Shipper identifier
  "loadingPoint": "string",       // Loading location
  "unloadingPoint": "string",     // Unloading location
  "loadingDate": "datetime",      // Loading date and time
  "unloadingDate": "datetime",    // Unloading date and time
  "productType": "string",        // Type of product
  "truckType": "string",          // Type of truck required
  "noOfTrucks": "integer",        // Number of trucks needed
  "weight": "double",             // Weight in kg
  "comment": "string",            // Additional comments
  "datePosted": "datetime",       // Auto-generated posting date
  "status": "enum"                // POSTED, BOOKED, CANCELLED
}
```

### Status Enum Values

- `POSTED` - Load is posted and available for booking
- `BOOKED` - Load has been booked
- `CANCELLED` - Load booking has been cancelled

## 🧪 Testing

### Manual Testing with Swagger

1. Start the application
2. Open [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
3. Use the interactive interface to test all endpoints

### Testing with cURL

```bash
# Health check
curl http://localhost:8080/actuator/health

# Create test data
curl -X POST http://localhost:8080/load \
  -H "Content-Type: application/json" \
  -d '{"shipperId":"TEST001","loadingPoint":"Mumbai","unloadingPoint":"Delhi","productType":"Test","truckType":"Container","noOfTrucks":1,"weight":1000.0}'
```

### Database Verification

```sql
-- Check created loads
SELECT * FROM acro1.loads;

-- Check load count by status
SELECT status, COUNT(*) FROM acro1.loads GROUP BY status;
```

## 📁 Project Structure

```
LoadBooking/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── bs/
│   │   │           ├── LoadBookingApplication.java
│   │   │           ├── controller/
│   │   │           │   └── LoadController.java
│   │   │           ├── model/
│   │   │           │   └── Load.java
│   │   │           ├── repo/
│   │   │           │   └── LoadRepository.java
│   │   │           └── services/
│   │   │               ├── LoadService.java
│   │   │               └── LoadServiceImp.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/
│           └── com/
│               └── bs/
│                   └── LoadBookingApplicationTests.java
├── target/
├── pom.xml
├── README.md
└── HELP.md
```

## 🔧 Development

### Adding New Features

1. **Controller Layer**: Add new endpoints in `LoadController.java`
2. **Service Layer**: Implement business logic in `LoadService.java`
3. **Repository Layer**: Add custom queries in `LoadRepository.java`
4. **Model Layer**: Update `Load.java` entity as needed

### Code Style

- Follow Java naming conventions
- Use Lombok annotations to reduce boilerplate code
- Implement proper validation using Bean Validation
- Write meaningful commit messages

## 🚀 Deployment

### Production Configuration

Create `application-prod.properties`:

```properties
spring.datasource.url=jdbc:mysql://prod-db-host:3306/loadbooking
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
server.port=${PORT:8080}
```

### Docker Deployment

```dockerfile
FROM openjdk:21-jdk-slim
COPY target/LoadBooking-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

**Happy Coding! 🚀**
