Certainly! Here’s how your updated `README.md` would look, based on your code and instructions, **without the License section**. This version includes a dedicated note about how Spring determines the transaction manager, as well as a **step-by-step debugging guide to check which transaction manager is picked at runtime**.

# Spring Boot Transactions Demo

This project demonstrates advanced **transaction management** concepts in Spring Boot, including:

- **Declarative Transactions** using the `@Transactional` annotation
- **Programmatic Transaction Management** (`PlatformTransactionManager`, `TransactionTemplate`)
- **Transaction Propagation Behaviors**
- **Transaction Isolation Levels**
- **Debugging and Identifying Active Transaction Manager**

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Key Concepts Illustrated](#key-concepts-illustrated)
  - [Declarative Transaction Management](#declarative-transaction-management)
  - [Programmatic Transaction Management](#programmatic-transaction-management)
  - [Transaction Propagation](#transaction-propagation)
  - [Transaction Isolation](#transaction-isolation)
- [How Spring Picks the Transaction Manager](#how-spring-picks-the-transaction-manager)
- [Debugging: Which Transaction Manager is Picked?](#debugging-which-transaction-manager-is-picked)
- [Example Usage](#example-usage)

## Overview

This repository provides practical code examples and explanations for Spring’s transaction management system using a simple `Book` entity and CRUD operations. The project’s aim is to help you understand **how different transaction strategies and configurations affect data consistency and application behavior**.

## Features

- Declarative (annotation-based) transactions
- Programmatic transaction management (two different approaches)
- Customizable transaction propagation and isolation levels
- Simple REST endpoint to create books
- Step-by-step debugging instructions for transaction inspection

## Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 in-memory database

## Project Structure

```
src/
└── main/
    └── java/
        └── com/
            └── demo/
                └── transactiondemo/
                    ├── config/
                    ├── controller/
                    ├── entity/
                    ├── repository/
                    ├── service/
                    └── TransactionDemoApplication.java
```

## Getting Started

1. Clone the repository:
    ```sh
    git clone https://github.com/AgeOfUltra/transactionDemo.git
    cd transactionDemo
    ```
2. Build and run the project:
    ```sh
    ./mvnw spring-boot:run
    ```
3. Test the main endpoint using curl or Postman:
    ```sh
    curl -X POST http://localhost:8080/book/save \
      -H "Content-Type: application/json" \
      -d '{"title":"Spring In Action","author":"Craig Walls"}'
    ```

## Key Concepts Illustrated

### Declarative Transaction Management

- Annotate service methods with `@Transactional`, e.g.:
    ```java
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Book saveBook(Book book) { ... }
    ```
- Benefits: Clean separation of transaction boundaries from business logic.

### Programmatic Transaction Management

Two approaches:
- **Using `PlatformTransactionManager`**
- **Using `TransactionTemplate`**

Both styles let you manage transactions manually if deeper control is needed.

### Transaction Propagation

Defines how nested method transactions relate. The default `REQUIRED` joins existing transactions or starts new ones as needed.

### Transaction Isolation

Controls data visibility between concurrent transactions. Using `Isolation.SERIALIZABLE` offers maximum safety by preventing dirty/non-repeatable/phantom reads.

## How Spring Picks the Transaction Manager

When you use `@Transactional`, **Spring tries to pick a suitable transaction manager bean at runtime**:

- If you have only one `PlatformTransactionManager` bean, Spring will use that automatically for all transactional operations.
- By default (with Spring Boot + JPA), Spring creates a `JpaTransactionManager`.
- In your code, by manually creating a `DataSourceTransactionManager` bean, you instruct Spring to use this manager instead of the default JPA manager (unless you specify a `transactionManager` qualifier in `@Transactional`).

**Summary:**  
> Spring will auto-detect the available transaction manager bean at runtime and use it for all transactional annotations. You can override this behavior by defining your own transaction manager bean, as shown in your `AppConfig.java`.

## Debugging: Which Transaction Manager is Picked?

You can easily debug and identify which transaction manager is handling your transaction in Spring Boot using IntelliJ IDEA’s debug tools:

### Steps to Debug

1. **Set Breakpoints:**
   - Place a breakpoint at the start of the `saveBook` method in your `BookController`.
   - Place another breakpoint in the `saveBook` method in your `BookService`.

2. **Run Application in Debug Mode:**
   - Start your Spring Boot app in debug mode via your IDE.

3. **Trigger the Endpoint:**
   - Make a POST request to `/book/save` to hit your breakpoints.

4. **Open the Debug Console:**
   - When execution stops at a breakpoint, open the Debug panel in IntelliJ.

5. **Click "More":**
   - In the Debug panel, click on the "More" (three dots or three lines) button.
<img width="1866" height="477" alt="image" src="https://github.com/user-attachments/assets/1c00c31d-4155-4680-a81f-c2b3c63dbb3b" />


6. **Choose "Evaluate Expression":**
   - Select the "Evaluate Expression" option.
    <img width="695" height="548" alt="image" src="https://github.com/user-attachments/assets/0f9d32b3-5b59-45e0-88bd-b437f5a18929" />


7. **Type the expression** *(don't copy-paste to ensure accuracy)*:
   ```
   TransactionAspectSupport.currentTransactionStatus()
   ```
   - Hit "Evaluate".

8. **Check the Result:**
   - In the result panel, look for the details under `result`. You’ll see objects like `JpaTransactionManager` or `DataSourceTransactionManager` being referenced.  
   - For example:  
     - A JPA transaction: `JpaTransactionManager`
     - A data source transaction: `DataSourceTransactionManager`

   - Additional details such as transaction origin, name, and flags (newTransaction, nested, etc.) will also be visible.
  
   <img width="1153" height="672" alt="image" src="https://github.com/user-attachments/assets/5e290a0f-b51b-4cf1-b611-8f08aa3392f7" />


**This allows you to visually confirm which transaction manager is orchestrating the current transaction at runtime.**

## Example Usage

- **POST** to `/book/save` with a Book payload to trigger transaction behavior.
- **Experiment** by altering isolation/propagation levels, or uncommenting programmatic approaches in the controller/service and observe differences in behavior and transaction management.
- **Follow the debug instructions to see which transaction manager is in use.**

Feel free to update and expand this documentation as you add more transactional scenarios or further explore Spring transaction management!
