# OverAlt

## Overview

The OverAlt project is a comprehensive Java-based Spring Boot application designed to manage telecommunication services. It facilitates the management of customers, their selected plans, friends and family lists, and call details. The application ensures that customers can only add a specified number of family members and friends based on their chosen plan and allows calls only to those contacts. The application uses PostgreSQL as its database and provides RESTful APIs for various operations.

## Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [Database Structure](#database-structure)
- [Java Classes and Methods](#java-classes-and-methods)
- [Endpoints](#endpoints)
- [Usage](#usage)
- [Workflow](#workflow)
- [Testing the APIs](#testing-the-apis)
- [Directory Structure](#directory-structure)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Customer Management**: Create, retrieve, update, and delete customer details, including their assigned plans.
- **Plan Management**: Define different plans with specific limits on family members and friends.
- **Friends and Family List**: Allow customers to add, retrieve, and delete friends and family members based on their plan.
- **Call Management**: Track and log call details, ensuring calls are only allowed to authorized contacts.
- **Data Generation**: Populate the database with random data for testing purposes.

## Technology Stack

- **Backend**: Spring Boot, Java
- **Database**: PostgreSQL
- **Persistence**: JPA/Hibernate
- **API Testing**: Postman, cURL

## Prerequisites

- Java 17 or higher
- PostgreSQL 12 or higher
- Maven or Gradle (for building the project)
- IDE (e.g., IntelliJ IDEA, Eclipse)

## Setup Instructions

### 1. Install PostgreSQL

Follow the installation guide for PostgreSQL based on your operating system:
- [PostgreSQL Downloads](https://www.postgresql.org/download/)

### 2. Create a PostgreSQL Database

1. **Access PostgreSQL**: Use the PostgreSQL interactive terminal (psql) or a GUI tool like pgAdmin.

   ```bash
   sudo -u postgres psql
   ```

2. **Create Database and User**:

   ```sql
   CREATE DATABASE overalt_db;
   CREATE USER overalt_user WITH ENCRYPTED PASSWORD 'yourpassword';
   GRANT ALL PRIVILEGES ON DATABASE overalt_db TO overalt_user;
   ```

3. **Exit psql**:

   ```sql
   \q
   ```

### 3. Configure Spring Boot Application

1. **Add Dependencies**: Update `pom.xml` or `build.gradle` for PostgreSQL.

   **Maven (`pom.xml`):**

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-jpa</artifactId>
   </dependency>
   <dependency>
       <groupId>org.postgresql</groupId>
       <artifactId>postgresql</artifactId>
   </dependency>
   ```

   **Gradle (`build.gradle`):**

   ```gradle
   implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
   implementation 'org.postgresql:postgresql'
   ```

2. **Configure Database Connection**: Update `src/main/resources/application.properties`.

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/overalt_db
   spring.datasource.username=overalt_user
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```

### 4. Build and Run the Application

1. **Build the Project**:

   **Maven:**

   ```bash
   ./mvnw clean install
   ```

   **Gradle:**

   ```bash
   ./gradlew build
   ```

2. **Run the Application**:

   **Maven:**

   ```bash
   ./mvnw spring-boot:run
   ```

   **Gradle:**

   ```bash
   ./gradlew bootRun
   ```

## Database Structure

The application uses the following database tables:

### Customers Table
- `customer_id` (Primary Key)
- `first_name`
- `last_name`
- `phone_number` (Unique)
- `email`
- `address`
- `plan_id` (Foreign Key referencing Plans table)
- `current_family_count` (Tracks how many family members the customer has added)
- `current_friends_count` (Tracks how many friends the customer has added)

### Plans Table
- `plan_id` (Primary Key)
- `plan_name`
- `monthly_cost`
- `data_limit`
- `call_minutes`
- `max_family_members` (e.g., 5 for a family plan)
- `max_friends` (e.g., 7 for a friends plan)

### FriendsAndFamily Table
- `customer_id` (Foreign Key referencing Customers table)
- `contact_name`
- `contact_number`
- `relationship_type` (e.g., "Friend" or "Family")

### CallDetails Table
- `call_id` (Primary Key)
- `caller_id` (Foreign Key referencing Customers table)
- `receiver_id`
- `call_start_time`
- `call_end_time`
- `call_duration`

## Java Classes and Methods

### Customer Class
The `Customer` class manages customer details and their interactions with the plan, friends, and family lists.

#### Attributes:
- `customerId`
- `firstName`
- `lastName`
- `phoneNumber`
- `email`
- `address`
- `plan` (Object of type Plan)
- `friendsAndFamily` (List of `FriendOrFamily` objects)
- `currentFamilyCount`
- `currentFriendsCount`

#### Methods:
- `addFamilyMember(FriendOrFamily familyMember)`: Adds a family member if within the plan's limit.
- `addFriend(FriendOrFamily friend)`: Adds a friend if within the plan's limit.
- `makeCall(String calledNumber)`: Makes a call if the called number is in the customer's friends and family list and allowed by the plan.

### Plan Class
The `Plan` class defines the details of a telecommunication plan.

#### Attributes:
- `planId`
- `planName`
- `monthlyCost`
- `dataLimit`
- `callMinutes`
- `maxFamilyMembers`
- `maxFriends`

#### Methods:
- `changePlanDetails(String planName, double monthlyCost, int dataLimit, int callMinutes)`: Changes the details of the plan.
- `setMaxFamilyMembers(int maxFamilyMembers)`: Sets the maximum number of family members allowed.
- `setMaxFriends(int maxFriends)`: Sets the maximum number of friends allowed.

### FriendOrFamily Class
The `FriendOrFamily` class represents a contact in the customer's friends and family list.

#### Attributes:
- `contactName`
- `contactNumber`
- `relationshipType` (e.g., "Friend" or "Family")

#### Methods:
- `updateContactNumber(String newPhoneNumber)`: Updates the contact number.
- `updateRelationshipType(String newRelationshipType)`: Updates the relationship type.

### CallDetails Class
The `CallDetails` class manages the details of calls made by the customer.

#### Attributes:
- `call_id` (Primary Key)
- `caller_id` (Foreign Key referencing Customers table)
- `receiver_id`
- `call_start_time`
- `call_end_time`
- `call_duration`

#### Methods:
- `calculateCallDuration()`: Calculates the duration of the call.
- `getCallDetails()`: Retrieves call details by `callId` or `caller_id`.

### DataGenerator Class
The `DataGenerator` class provides utility methods to generate random data for testing.

#### Methods:
- `generateRandomCustomer()`: Generates a random customer.
- `generateRandomPlan()`: Generates a random plan.
- `generateRandomFriendOrFamily(String relationshipType)`: Generates a random friend or family member.
- `generateRandomCallDetails(Customer customer)`: Generates random call details for a customer.

## Endpoints

### CallDetails Endpoints
1. **Create a new call detail**
   - **Endpoint**: `POST /api/call-details`
   - **Request Body**: `CallDetails` object
   - **Response**: Created `CallDetails` object

2. **Get a call detail by ID**
   - **Endpoint**: `GET /api/call-details/{callId}`
   - **Path Variable**: `callId`
   - **Response**: `CallDetails` object

3. **Get all call details**
   - **Endpoint**: `GET /api/call-details`
   - **Response**: List of `CallDetails` objects

4. **Get call details by caller ID**
   - **Endpoint**: `GET /api/call-details/caller/{callerId}`
   - **Path Variable**: `callerId`
   - **Response**: List of `CallDetails` objects

5. **Get call details by receiver ID**
   - **Endpoint**: `GET /api/call-details/receiver/{receiverId}`
   - **Path Variable**: `receiverId

`
   - **Response**: List of `CallDetails` objects

### Customer Endpoints
1. **Create a new customer**
   - **Endpoint**: `POST /api/customers`
   - **Request Body**: `Customer` object
   - **Response**: Created `Customer` object

2. **Get a customer by ID**
   - **Endpoint**: `GET /api/customers/{id}`
   - **Path Variable**: `id`
   - **Response**: `Customer` object

3. **Get all customers**
   - **Endpoint**: `GET /api/customers`
   - **Response**: List of `Customer` objects

4. **Update a customer**
   - **Endpoint**: `PUT /api/customers/{id}`
   - **Path Variable**: `id`
   - **Request Body**: `Customer` object
   - **Response**: Updated `Customer` object

5. **Delete a customer**
   - **Endpoint**: `DELETE /api/customers/{id}`
   - **Path Variable**: `id`
   - **Response**: Status message

### Plan Endpoints
1. **Create a new plan**
   - **Endpoint**: `POST /api/plans`
   - **Request Body**: `Plan` object
   - **Response**: Created `Plan` object

2. **Get a plan by ID**
   - **Endpoint**: `GET /api/plans/{id}`
   - **Path Variable**: `id`
   - **Response**: `Plan` object

3. **Get all plans**
   - **Endpoint**: `GET /api/plans`
   - **Response**: List of `Plan` objects

4. **Update a plan**
   - **Endpoint**: `PUT /api/plans/{id}`
   - **Path Variable**: `id`
   - **Request Body**: `Plan` object
   - **Response**: Updated `Plan` object

5. **Delete a plan**
   - **Endpoint**: `DELETE /api/plans/{id}`
   - **Path Variable**: `id`
   - **Response**: Status message

### FriendsAndFamily Endpoints
1. **Add a friend or family member**
   - **Endpoint**: `POST /api/friends-family`
   - **Request Body**: `FriendOrFamily` object
   - **Response**: Created `FriendOrFamily` object

2. **Get all friends and family for a customer**
   - **Endpoint**: `GET /api/friends-family/{customerId}`
   - **Path Variable**: `customerId`
   - **Response**: List of `FriendOrFamily` objects

3. **Delete a friend or family member**
   - **Endpoint**: `DELETE /api/friends-family/{customerId}/{contactNumber}`
   - **Path Variables**: `customerId`, `contactNumber`
   - **Response**: Status message

## Usage

1. **Setup**: Configure your database and ensure the tables are set up according to the provided structure.
2. **Data Population**: Use the `DataGenerator` class to populate the database with random customers, plans, friends, and family members.
3. **Operations**: Use the `Customer` class methods to add friends, family members, and make calls according to the rules of the selected plan.
4. **Call Logging**: The application will automatically log call details and enforce plan rules.

## Workflow

1. **Customer Creation**: Generate or manually create customers and assign them plans.
2. **Adding Contacts**: Use the `addFamilyMember()` and `addFriend()` methods in the `Customer` class to add contacts.
3. **Making Calls**: Use the `makeCall()` method to initiate calls, which will be allowed or blocked based on the plan and contact list.
4. **Logging Calls**: Call details are logged automatically, ensuring compliance with the plan’s rules.

## Testing the APIs

Use tools like Postman or cURL to test the endpoints described above.

### Example cURL Command:

```bash
curl -X POST http://localhost:8080/api/customers -H "Content-Type: application/json" -d '{"firstName":"John","lastName":"Doe","phoneNumber":"1234567890","email":"johndoe@example.com","address":"123 Main St","planId":1}'
```

## Directory Structure

```plaintext
overalt/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── overalt/
│   │   │   │   │   ├── controller/
│   │   │   │   │   ├── model/
│   │   │   │   │   ├── repository/
│   │   │   │   │   ├── service/
│   │   │   │   │   ├── OverAltApplication.java
│   │   │   │   │   └── config/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   └── test/
│       ├── java/
│       └── resources/
│
└── README.md
```

## Troubleshooting

- **Database Connection Issues**: Ensure PostgreSQL is running, and the credentials in `application.properties` are correct.
- **Dependency Issues**: Ensure all necessary dependencies are correctly added to `pom.xml` or `build.gradle`.
- **API Errors**: Use Postman or cURL to debug API requests and verify the correctness of your payloads.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes. Ensure that your code is well-documented and follows the project's coding standards.

## License

This project is licensed under the Apache License Version 2.0 - see the [LICENSE](LICENSE) file for details.
