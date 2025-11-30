
# Inventory Service (Java 20 + Maven + Spring Boot)

A clean REST API for managing inventory items (SKU, name, quantity, price). Built with **Java 20**, **Maven**, and **Spring Boot**.

## Features
- CRUD endpoints for items
- Validation (non-negative quantity/price, required fields)
- In-memory H2 database for local dev
- OpenAPI/Swagger UI at `/swagger-ui.html`
- Tests with JUnit 5
- GitHub Actions CI (JDK 20)

## Requirements
- JDK **20** installed
- Maven 3.9+

## Run locally
```bash
./mvnw spring-boot:run
# or, if mvnw is not bundled, use:
mvn spring-boot:run
```

## API Endpoints
- `GET /api/items` — list items
- `GET /api/items/{id}` — get item by id
- `POST /api/items` — create item
- `PUT /api/items/{id}` — full update
- `PATCH /api/items/{id}/adjust?delta=5` — adjust quantity (+/-)
- `DELETE /api/items/{id}` — delete

### Example requests
```bash
# Create
curl -X POST http://localhost:8080/api/items   -H 'Content-Type: application/json'   -d '{"sku":"SKU-1001","name":"Keyboard","quantity":10,"price":29.99}'

# List
curl http://localhost:8080/api/items

# Adjust quantity
curl -X PATCH 'http://localhost:8080/api/items/1/adjust?delta=-2'
```

## Build & Test
```bash
mvn -B -e -DskipTests=false test package
```

## Swagger/OpenAPI
Once running, visit:
- JSON: `http://localhost:8080/v3/api-docs`
- UI: `http://localhost:8080/swagger-ui.html`

## License
MIT
