# EcommerceApplication

**User Authentication Service**
The User Authentication Service is a microservice that provides user registration, login, and token generation functionalities for a simple e-commerce application.

**Dependencies**
Spring Boot
Spring Security
JWT (JSON Web Tokens)
PostgreSQL (or other database of your choice)
**Setup**
Clone the repository:

Copy code
git clone https://github.com/your-username/user-authentication-service.git
cd user-authentication-service
Configure database settings in application.properties:

properties
Copy code
spring.datasource.url=jdbc:postgresql://localhost:5432/{db_name}
spring.datasource.username={your_username}
spring.datasource.password={your_password}
Build and run the application:

Copy code
mvn clean install
mvn spring-boot:run

**Endpoints**
/api/auth/register: Register a new user.
/api/auth/login: Login and generate a JWT token.
**Usage**
Register a new user:

Endpoint: /api/auth/register
Method: POST
Request Body:
json
Copy code
{
  "username": "Ram",
  "password": "password123"
}
Login and generate a JWT token:

Endpoint: /api/auth/login
Method: POST
Request Body:
json
Copy code
{
  "username": "john.doe",
  "password": "password123"
}
Response:
json
Copy code
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
}
Use the JWT token for accessing protected endpoints in other microservices.

**Contributing**
Contributions are welcome! Please open an issue or submit a pull request.
