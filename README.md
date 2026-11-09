# RestaurantOS

RestaurantOS is a Java backend learning project.

The application uses Java's built-in HttpServer to handle
HTTP requests without Spring Boot.

Currently, the server supports GET and POST requests
for ingredient information.

POST requests are validated before the server
returns an HTTP response.

## HTTP Endpoints

### GET /ingredients

Returns a simple message confirming that the server
received a GET request.

**HTTP Status:** 200 OK

**Response:**
Get Request Received

### POST /ingredients

Accepts ingredient information in the HTTP request body.

**Content-Type:** application/x-www-form-urlencoded

**Example Request Body:**
ingredient=cheese&price=10

**Successful Response:**
HTTP Status: 200
Request is valid

**Invalid Request Example:**
ingredient=cheese&price=-10

**Error Response:**
HTTP Status: 400
price cannot be negative or zero or infinite

### Unsupported HTTP Methods

Returns HTTP Status 405 Method Not Allowed.

Allowed methods: GET, POST

## Technologies Used

- Java
- Java HttpServer API
- HTTP/1.1
- Git and GitHub
- IntelliJ IDEA

## How to Run

1. Open the project in IntelliJ IDEA.
2. Run RestaurantServer.java.
3. The server starts on port 8080.
4. Open http://localhost:8080/ingredients in your browser.

## Current Limitations

- Data is validated but not stored in a database.
- POST requests use a fixed ingredient and price format.
- No HTML user interface yet.

## Future Development

- HTML form integration.
- MySQL database using JDBC.
- Hibernate/JPA integration.
- Spring Boot REST API.