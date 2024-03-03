# EcommerceApplication

**1st microservice** 
**User Authentication Service**
The User Authentication Service is a microservice that provides user registration, login, and token generation functionalities for a simple e-commerce application.

**Dependencies**
Spring Boot
Spring Security
JWT (JSON Web Tokens)
PostgreSQL (or other database of your choice)
**Setup**
Clone the repository:


git clone (https://github.com/AtrijSharma/EcommerceApplication.git)
cd user-authentication-service
Configure database settings in application.properties:

properties

spring.datasource.url=jdbc:postgresql://localhost:5432/{db_name}
spring.datasource.username={your_username}
spring.datasource.password={your_password}
Build and run the application:


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

{
  "username": "Ram",
  "password": "password123"
}
Login and generate a JWT token:


Endpoint: /api/auth/login
Method: POST
Request Body:
json

{
  "username": "Ram",
  "password": "password123"
}
Response:
json

{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
}
Use the JWT token for accessing protected endpoints in other microservices.

**Contributing**
Contributions are welcome! Please open an issue or submit a pull request.

2nd microservice

**Product Management Service**
The Product Management Service is a microservice that handles CRUD operations for products in an e-commerce application.

**Features**
Create Product: Allows users to create new products.
Read Product: Allows users to view existing products.
Update Product: Allows users to update existing products.
Delete Product: Allows users to delete existing products.
**Setup**

Clone the Repository: Clone the repository to your local machine using the following command:


git clone (https://github.com/AtrijSharma/EcommerceApplication.git)
Install Dependencies: Navigate to the project directory and install the required dependencies using Maven:



cd product-management-service
mvn clean install
Run the Application: Run the application using the following command:

arduino

mvn spring-boot:run
Access the API: The API can be accessed at http://localhost:8080/api/products.

**Dependencies**
Spring Boot: The application is built using Spring Boot, a popular Java framework for building microservices.
Spring Data JPA: Spring Data JPA is used for interacting with the database.
H2 Database: An in-memory database is used for development and testing purposes.
Spring Security: Spring Security is used for authentication and authorization.
Endpoints
GET /api/products: Get all products.
GET /api/products/{id}: Get a product by ID.
POST /api/products: Create a new product.
PUT /api/products/{id}: Update an existing product.
DELETE /api/products/{id}: Delete a product by ID.
Security
The Product Management Service uses Spring Security for authentication and authorization. By default, the application uses basic authentication with a hardcoded username and password (admin/admin). You can customize the authentication mechanism by modifying the SecurityConfig class.

**Testing**
The application includes unit tests for the ProductService class using JUnit and Mockito. You can run the tests using the following command:


mvn test
Deployment
The application can be deployed to any cloud platform that supports Java applications, such as AWS, Azure, or Google Cloud. You can build a Docker image using the provided Dockerfile and deploy it to a container orchestration platform like Kubernetes.

**Contributing**
Contributions are welcome! If you have any suggestions or improvements, feel free to open an issue or create a pull request.


**3 rd microservice** --------


**Order Processing Service**
The Order Processing Service is a microservice that handles the creation and processing of orders in an e-commerce application.

**Features**
Create Order: Allows users to create new orders.
Read Order: Allows users to view existing orders.
Update Order: Allows users to update existing orders.
Delete Order: Allows users to delete existing orders.
**Setup**
Clone the Repository: Clone the repository to your local machine:


git clone (https://github.com/AtrijSharma/EcommerceApplication.git)
Install Dependencies: Navigate to the project directory and install the required dependencies:


cd order-processing-service
mvn clean install
Run the Application: Run the application:



mvn spring-boot:run
Access the API: The API can be accessed at http://localhost:8080/api/orders.

**Dependencies**
Spring Boot: The application is built using Spring Boot, a popular Java framework for building microservices.
Spring Data JPA: Spring Data JPA is used for interacting with the database.
H2 Database: An in-memory database is used for development and testing purposes.
Spring Security: Spring Security is used for authentication and authorization.
**Endpoints**
GET /api/orders: Get all orders.
GET /api/orders/{id}: Get an order by ID.
POST /api/orders: Create a new order.
PUT /api/orders/{id}: Update an existing order.
DELETE /api/orders/{id}: Delete an order by ID.
**Security**
The Order Processing Service uses Spring Security for authentication and authorization. By default, the application uses basic authentication with a hardcoded username and password (admin/admin). You can customize the authentication mechanism by modifying the SecurityConfig class.

**Testing**
The application includes unit tests for the OrderService class using JUnit and Mockito. You can run the tests using the following command:

mvn test
Deployment
The application can be deployed to any cloud platform that supports Java applications, such as AWS, Azure, or Google Cloud. You can build a Docker image using the provided Dockerfile and deploy it to a container orchestration platform like Kubernetes.

**Contributing**
Contributions are welcome! If you have any suggestions or improvements, feel free to open an issue or create a pull request.





