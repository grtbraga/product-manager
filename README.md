# Product Manager

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.4-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)
![License](https://img.shields.io/badge/License-MIT-yellow)

## 📋 Description

**Product Manager** is a simple web application for managing products and their categories. Built with **Spring Boot**, **Thymeleaf**, and **MySQL**, the application allows users to create, edit, and delete products, organized in a category hierarchy (e.g., "Electronics > Smartphones"). It’s an ideal project for learning web development concepts with Java, including JPA, MVC, and database integration.

### Features

- **Product Management**  
  Create, edit, and delete products.  
  Each product has a name, description, price, category, and availability status.

- **Category Hierarchy**  
  Categories can have subcategories (e.g., "Electronics > Computers").  
  The category path (`category_path`) is stored for easy display.

- **Data Initialization**  
  Initial data (categories and products) is inserted automatically on the first run, without duplicates.

- **User Authentication**  
  The application requires login to access the product management features.  
  A default admin user is available for initial access.

## 🛠️ Technologies Used

- Java 17  
- Spring Boot 3.4.4 (with Spring MVC, Spring Data JPA, Thymeleaf, Spring Security)  
- MySQL 8.0  
- Thymeleaf  
- Maven

## 📦 Prerequisites

- Java 17 or higher  
- Maven  
- MySQL 8.0 (installed and running)  
- A web browser (e.g., Chrome, Firefox)

## 🚀 Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/grtbraga/product-manager.git
cd product-manager
```

### 2. Configure MySQL

Ensure MySQL is running on your machine.  
Verify the credentials in the `src/main/resources/application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/product_db?createDatabaseIfNotExist=true
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
```

Adjust the username and password to match your MySQL configuration.  
The database `product_db` will be created automatically when the application starts.

### 3. Build and Run

Build and run the application using Maven:

```bash
mvn clean spring-boot:run
```

The application will be available at:  
[http://localhost:8080](http://localhost:8080)

### 4. Access and Log In

Open your browser and navigate to:  
[http://localhost:8080/products](http://localhost:8080/products)

You will be redirected to the login page.

Use the default admin credentials:

```
Username: admin
Password: password
```

## 👨‍💼 Usage

After logging in, you will be able to access the `/products` page.

### Manage Products

- **Create a Product**  
  Fill out the form at the bottom of the page and click "Save".  
  Example: Name: "Smartwatch", Price: 199.99, Category: "Smartphones", Available: (checked)

- **Edit a Product**  
  Click "Edit" next to a product, update the fields, and click "Save".

- **Delete a Product**  
  Click "Delete" next to a product.

## 📂 Project Structure

```
product-manager/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/com/productmanager/
│   │   │       ├── controller/             # Controllers (ProductController)
│   │   │       ├── domain/                 # Entities (Product, Category)
│   │   │       ├── repository/             # JPA Repositories (ProductRepository, CategoryRepository)
│   │   │       └── ProductManagerApplication.java  # Main application class
│   │   └── resources/
│   │       ├── static/
│   │       │   └── css/styles.css          # CSS styles
│   │       ├── templates/
│   │       │   └── products.html           # Thymeleaf template
│   │       ├── application.properties      # Application configuration
│   │       ├── insert_categories.sql       # Script to insert initial categories
│   │       └── insert_products.sql         # Script to insert initial products
│   └── test/                               # Unit tests
├── pom.xml                                 # Maven configuration file
└── README.md                               # Project documentation
```