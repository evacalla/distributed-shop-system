# Product API - Distributed Shop System

Distributed product and inventory management system built with Spring Boot using hexagonal architecture. Handles product information and stock operations with optimistic concurrency control for distributed environments.

## 🚀 Technologies Used

- **Java 17** - Primary programming language
- **Spring Boot 3.5.6** - Application framework
- **Spring Data JPA** - Data persistence
- **Spring Validation** - Request validation
- **H2 Database** - In-memory database for development
- **Maven 3.9.2** - Dependency management and build tool
- **Docker & Docker Compose** - Containerization
- **JUnit 5** - Testing framework
- **OpenAPI/Swagger** - API documentation
- **Spring Boot Actuator** - Application monitoring and management

## 🏗️ System Architecture

This project follows **Hexagonal Architecture (Clean Architecture)** principles with clear separation of concerns:

### Project Structure
```
src/main/java/com/distributed/product/
├── ProductApplication.java          # Main application class
├── application/                     # Application layer
│   ├── ports/                       # Port interfaces
│   │   └── in/                      # Incoming ports (use cases)
│   └── usercase/                    # Use case implementations
├── domain/                          # Domain layer
│   ├── exception/                   # Domain exceptions
│   └── model/                       # Domain entities
└── infrastructure/                  # Infrastructure layer
    ├── adapter/                     # Adapters implementation
    │   ├── in/                      # Incoming adapters (controllers)
    │   └── out/                     # Outgoing adapters (repositories)
    └── configuration/               # Spring configuration
```

### Data Model

The system is based on two main entities:

#### Product
- `productId`: Unique product identifier
- `name`: Product name
- `retailId`: Retailer identifier
- `description`: Product description
- `price`: Product price (BigDecimal)
- `createdAt`: Creation timestamp
- `updatedAt`: Last update timestamp

#### Stock (with Optimistic Locking)
- `stockId`: Unique stock identifier
- `productId`: Product reference
- `retailId`: Retailer identifier
- `warehouseId`: Warehouse identifier
- `availableQuantity`: Available quantity for sale
- `committedQuantity`: Reserved/committed quantity
- `onHandQuantity`: Physical quantity in warehouse
- `version`: Version field for optimistic concurrency control
- `createdAt`: Creation timestamp
- `updatedAt`: Last update timestamp

### Concurrency Control with Optimistic Locking

The system implements **Optimistic Locking** using version control on the Stock entity:

**How it works:**
1. When reading a stock record, the system stores the version number
2. When updating, it verifies that the version hasn't changed
3. If another transaction modified the record, a version conflict occurs
4. The system handles conflicts gracefully with proper error responses
5. Clients can retry the operation with updated data

**Advantages:**
- No record locking during long reads
- Better performance in systems with few collisions
- Automatically detects concurrent modifications
- Prevents lost update problems

## 🎯 Main Use Cases

### GetProductUseCase
- **Function**: Product management and information queries
- **Features**:
  - Get products by retailer
  - Get specific product with stock information
  - Product information retrieval

### GetStockUseCase
- **Function**: Stock information queries
- **Features**:
  - Stock queries by product, retailer, and warehouse
  - Real-time stock availability checking

### ReserveStockUseCase
- **Function**: Stock reservation for orders
- **Operation**: `availableQuantity -= quantity`, `committedQuantity += quantity`
- **Validations**: Verifies sufficient available stock
- **Concurrency**: Handles conflicts with optimistic locking

### ReleaseStockUseCase
- **Function**: Release previously reserved stock
- **Operation**: `committedQuantity -= quantity`, `availableQuantity += quantity`
- **Validations**: Verifies committed stock exists
- **Concurrency**: Optimistic control to prevent duplicate releases

## 📚 API Documentation with Swagger

#### Swagger UI (Interactive Documentation)
- **URL**: http://localhost:8081/api/swagger-ui
- **Features**:
  - Interactive API testing
  - Request/response examples
  - Schema definitions
  - Try-it-out functionality
  - Real-time API exploration

#### OpenAPI JSON Specification
- **URL**: http://localhost:8081/api/v3/api-docs
- **Format**: JSON specification compatible with OpenAPI 3.0
- **Usage**: Can be imported into other tools (Postman, Insomnia, etc.)

### API Endpoints Documentation

#### Product Endpoints
```
GET /api/products/{retailId}
- Description: Retrieve all products for a specific retailer
- Parameters: retailId (path parameter)
- Response: List of products with stock information

GET /api/products/{productId}/{retailId}
- Description: Retrieve specific product with stock details
- Parameters: productId, retailId (path parameters)
- Response: Product details with stock information
- Exceptions: 404 Not Found if product doesn't exist
```

#### Stock Management Endpoints
```
GET /api/stock/{productId}/{retailId}
- Description: Get stock information for a product
- Parameters: productId, retailId (path parameters)
- Response: Stock details

POST /api/stock/reserve
- Description: Reserve stock for an order
- Request Body: Stock reservation details
- Response: Updated stock information
- Exceptions: 400 Insufficient Stock, 409 Version Conflict

POST /api/stock/release
- Description: Release previously reserved stock
- Request Body: Stock release details
- Response: Updated stock information
- Exceptions: 400 No Committed Stock, 409 Version Conflict
```

### Request/Response Examples

#### Stock Reserve Request
```json
{
  "productId": "product-123",
  "retailId": "retailer-456",
  "warehouseId": "warehouse-789",
  "quantity": 10
}
```

#### Product Response
```json
{
  "productId": "product-123",
  "name": "Sample Product",
  "retailId": "retailer-456",
  "description": "Product description",
  "price": 29.99,
  "createdAt": "2025-09-21T10:00:00Z",
  "updatedAt": "2025-09-21T11:30:00Z",
  "stocks": [
    {
      "stockId": "stock-abc",
      "productId": "product-123",
      "retailId": "retailer-456",
      "warehouseId": "warehouse-789",
      "availableQuantity": 90,
      "committedQuantity": 30,
      "onHandQuantity": 120,
      "createdAt": "2025-09-21T10:00:00Z",
      "updatedAt": "2025-09-21T11:30:00Z"
    }
  ]
}
```

#### Error Response
```json
{
  "timestamp": "2025-09-21T11:30:00Z",
  "status": 409,
  "error": "Version Conflict",
  "message": "Stock version conflict, please retry",
  "path": "/api/stock/reserve"
}
```

## 🐳 Docker Execution

### Prerequisites
- Docker
- Docker Compose

### Start the application
```bash
# Build and start the service
docker-compose up --build

# Detached mode (background)
docker-compose up -d --build
```

### Available Services
- **Product API**: http://localhost:8081/api
- **Swagger UI**: http://localhost:8081/api/swagger-ui
- **OpenAPI Docs**: http://localhost:8081/api/v3/api-docs
- **H2 Console**: http://localhost:8081/api/h2-console

### Useful Commands
```bash
# View logs
docker-compose logs -f product-api

# Stop services
docker-compose down

# Rebuild only the application
docker-compose up --build product-api
```

## 💾 Database Configuration

### H2 Database (Development)
- **Console**: http://localhost:8081/api/h2-console
- **JDBC URL**: `jdbc:h2:mem:productDb`
- **Username**: `sa`
- **Password**: (empty)
- **Features**:
  - In-memory database for fast development
  - Automatic schema creation via schema.sql
  - Sample data loading via data.sql
  - Web console for database inspection

### Database Schema
The application uses two main tables:
- **products**: Store product information
- **stock**: Store inventory information with optimistic locking

## 📊 Monitoring and Health

### Actuator Endpoints
- **Health Check**: http://localhost:8081/api/actuator/health
- **Application Info**: http://localhost:8081/api/actuator/info
- **Metrics**: http://localhost:8081/api/actuator/metrics

### API Documentation
- **OpenAPI JSON**: http://localhost:8081/api/v3/api-docs
- **Swagger UI**: http://localhost:8081/api/swagger-ui

## 🧪 Testing

### Run Tests
```bash
# All tests
./mvnw test

# Specific tests
./mvnw -Dtest=GetProductControllerTest test
./mvnw -Dtest=ReserveStockControllerTest test
```

### Test Coverage
The project includes comprehensive tests:
- **Unit Tests**: Use case implementations with complete mocking
- **Integration Tests**: Controllers with Spring context
- **Concurrency Tests**: Concurrent access simulation for stock operations
- **Validation Tests**: Request validation testing

### Available Test Classes
- `GetProductControllerTest`: Product retrieval endpoint tests
- `GetStockControllerTest`: Stock information endpoint tests
- `ReserveStockControllerTest`: Stock reservation endpoint tests
- `ReleaseStockControllerTest`: Stock release endpoint tests

## 📈 Scalability and Performance

### Architecture Features
- **Hexagonal Architecture**: Clean separation of concerns for maintainability
- **Stateless Design**: Stateless application for horizontal scaling
- **Optimistic Locking**: Minimizes database locks for better performance
- **Connection Pooling**: Configured for high concurrency
- **Health Checks**: Automatic dependency monitoring

### Key Performance Considerations
- Optimistic concurrency control for stock operations
- Efficient JPA queries with proper indexing
- Stateless design enabling horizontal scaling
- In-memory database for fast development cycles


