# 📚 Library Management API

A simple Java-based RESTful API for managing a library's book catalog, built with Spring Boot. This project demonstrates core Java concepts, OOP principles, and the development of a backend system with RESTful endpoints and database integration.

## 🎯 Objective

The primary goal of this project is to showcase an understanding of Java, Object-Oriented Programming (OOP) principles, and the ability to design and implement a simple backend system for managing books in a library.

## 📝 Problem Statement

This application provides a RESTful API to manage a library's book catalog, enabling the following operations:

* **Adding a new book:** Register new books into the system.
* **Retrieving the list of all books:** Get a complete list of all books in the catalog.
* **Getting details of a book by its ID:** Fetch specific details for a single book using its unique identifier.
* **Deleting a book:** Remove a book from the catalog.
* **Updating availability of a book:** Modify the availability status of a book.

### Book Fields

Each book in the catalog has the following attributes:

* `id` (Integer)
* `title` (String)
* `author` (String)
* `isbn` (String)
* `available` (Boolean)

## 🛠️ Technical Requirements

### Mandatory

* **Java 17:** The project is developed using Java 17.
* **Object-Oriented Design & Encapsulation:** The codebase adheres to OOP principles, ensuring a modular and maintainable structure.
* **REST-style Endpoints:** All functionalities are exposed via well-defined RESTful endpoints.

### Optional (Bonus)

* **Spring Boot:** Used to simplify the development of REST endpoints and manage the application context.
* **MySQL Database:** Books are persisted in a MySQL database, configured via `application.properties`.
* **Postman Collection:** A Postman collection is provided for easy testing of all API endpoints.
* **Input Validation:** Simple validation is implemented to ensure data integrity (e.g., `title`, `author`, `isbn` are mandatory).

## 🚀 How to Run the Project

To get this project up and running on your local machine, follow these steps:

1.  **Prerequisites:**
    * Java Development Kit (JDK) 17 or later
    * Maven
    * MySQL Database (running on `localhost:3306` with credentials `root`/`admin`)

2.  **Database Setup:**
    * Create a database named `library_db` in your MySQL instance. The application will automatically create the `book` table based on the JPA configuration.
    * You can change the database configuration in `src/main/resources/application.properties`.

3.  **Clone the Repository:**

    ```bash
    git clone <repository_url>
    cd library
    ```

4.  **Build the Project:**
    Navigate to the project root directory (`library`) and build the project using Maven:

    ```bash
    mvn clean install
    ```

5.  **Run the Application:**
    You can run the Spring Boot application using the Maven Spring Boot plugin:

    ```bash
    mvn spring-boot:run
    ```
    The application will start on `http://localhost:8080`.

## 📂 Project Structure

The project follows a standard Spring Boot application structure:

```
library/
├── .vscode/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── library/
│   │   │               ├── controller/
│   │   │               │   └── BookController.java
│   │   │               ├── model/
│   │   │               │   └── Book.java
│   │   │               ├── Repository/
│   │   │               │   └── BookRepository.java
│   │   │               ├── service/
│   │   │               │   └── BookService.java
│   │   │               └── LibraryApplication.java
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       └── application.properties
│   └── test/
├── .gitignore
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── Library.postman_collection.json
```

* `controller/BookController.java`: Handles incoming HTTP requests and delegates to the service layer.
* `model/Book.java`: Defines the `Book` entity, mapping it to the database table.
* `Repository/BookRepository.java`: Provides data access operations for the `Book` entity using Spring Data JPA.
* `service/BookService.java`: Contains the business logic for book operations.
* `LibraryApplication.java`: The main entry point for the Spring Boot application.
* `application.properties`: Configuration file for the Spring Boot application, including database settings and server port.
* `pom.xml`: Maven project object model, defining dependencies and build configurations.
* `Library.postman_collection.json`: Postman collection for testing the API endpoints.

## 🧪 Sample Requests and Expected Responses

You can use the provided `Library.postman_collection.json` to test the API endpoints. Import this file into Postman to get started quickly.

Here are some sample requests and their expected responses:

### 1. Add a New Book (POST)

**Endpoint:** `POST http://localhost:8080/books`
**Headers:** `Content-Type: application/json`
**Body:**
```json
{
  "title": "Effective Java",
  "author": "Joshua Bloch",
  "isbn": "978-0134685991",
  "available": true
}
````

**Expected Response (Status: 200 OK):**

```json
{
  "id": 1,
  "title": "Effective Java",
  "author": "Joshua Bloch",
  "isbn": "978-0134685991",
  "available": true
}
```

**Expected Response (Status: 404 Not Found) if ID does not exist:**

```
Failed to add book: error occured!
```

(ID will be auto-generated by the database)

### 2\. Get All Books (GET)

**Endpoint:** `GET http://localhost:8080/books`

**Expected Response (Status: 200 OK):**

```json
[
  {
    "id": 1,
    "title": "Effective Java",
    "author": "Joshua Bloch",
    "isbn": "978-0134685991",
    "available": true
  },
  {
    "id": 2,
    "title": "Clean Code",
    "author": "Robert C. Martin",
    "isbn": "978-0132350884",
    "available": false
  }
]
```
**Expected Response (Status: 404 Not Found) if ID does not exist:**

```
Failed to retrieve books: error occured!
```

(Response will vary based on added books)

### 3\. Get Book by ID (GET)

**Endpoint:** `GET http://localhost:8080/books/1`

**Expected Response (Status: 200 OK):**

```json
{
  "id": 1,
  "title": "Effective Java",
  "author": "Joshua Bloch",
  "isbn": "978-0134685991",
  "available": true
}
```
**Expected Response (Status: 404 Not Found) if Book does not exist:**

```
Book with ID 1 not found.
```

**Expected Response (Status: 404 Not Found) if ID does not exist:**

```
Error fetching book by ID 1: error occured while fetching
```

### 4\. Update Book Availability (PATCH)

**Endpoint:** `PATCH http://localhost:8080/books/1/availability?available=false`

**Expected Response (Status: 200 OK):**

```json
{
  "id": 1,
  "title": "Effective Java",
  "author": "Joshua Bloch",
  "isbn": "978-0134685991",
  "available": false
}
```
**Expected Response (Status: 404 Not Found) if Book does not exist:**

```
Cannot update availability. Book with ID 1 not found.
```
**Expected Response (Status: 404 Not Found) if ID does not exist:**

```
Failed to update availability for book ID: 1 error occured
```

### 5\. Delete Book by ID (DELETE)

**Endpoint:** `DELETE http://localhost:8080/books/1`

**Expected Response (Status: 200 OK):**

```
Book with ID 1 has been deleted successfully.
```
**Expected Response (Status: 404 Not Found) if Book does not exist:**

```
Cannot delete. Book with ID 1 not found.
```

**Expected Response (Status: 404 Not Found) if ID does not exist:**

```
Failed to delete book with ID 1: error occured!
```

## 🔗 Postman Collection

You can access and import the Postman collection for this API using the following JSON format or by importing the `Library.postman_collection.json` file directly into Postman:

```
{
	"info": {
		"_postman_id": "1271060c-856f-40d0-9eb5-9d63bef66f7a",
		"name": "Library",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "39133193"
	},
	"item": [
		{
			"name": "Post book",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n  \"title\": \"Effective Java\",\r\n  \"author\": \"Joshua Bloch\",\r\n  \"isbn\": \"978-0134685991\",\r\n  \"available\": true\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/books",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"books"
					]
				}
			},
			"response": []
		},
		{
			"name": "Get All Books",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/books/",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"books",
						""
					]
				}
			},
			"response": []
		},
		{
			"name": "Get Book By Id",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/books/1",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"books",
						"1"
					]
				}
			},
			"response": []
		},
		{
			"name": "Delete Book By Id",
			"request": {
				"method": "DELETE",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/books/1",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"books",
						"1"
					]
				}
			},
			"response": []
		},
		{
			"name": "Patch Book",
			"request": {
				"method": "PATCH",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/books/1/availability?available=false",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"books",
						"1",
						"availability"
					],
					"query": [
						{
							"key": "available",
							"value": "false"
						}
					]
				}
			},
			"response": []
		}
	]
}
```
