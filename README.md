# OverAlt

## Overview
This project is a Java-based telecommunication application that manages customers, their chosen plans, friends and family lists, and call details. The application ensures that customers can only add a specified number of family members and friends based on their selected plan, and allows calls only to those contacts.

## Table of Contents
- [Features](#features)
- [Database Structure](#database-structure)
- [Java Classes and Methods](#java-classes-and-methods)
  - [Customer Class](#customer-class)
  - [Plan Class](#plan-class)
  - [FriendOrFamily Class](#friendorfamily-class)
  - [CallDetails Class](#calldetails-class)
  - [DataGenerator Class](#datagenerator-class)
- [Usage](#usage)
- [Workflow](#workflow)
- [License](#license)

## Features
- **Customer Management**: Store and manage customer details, including their assigned plans.
- **Plan Management**: Define different plans with specific limits on family members and friends.
- **Friends and Family List**: Allow customers to add a certain number of friends and family members based on their plan.
- **Call Management**: Track and log call details, ensuring calls are only allowed to authorized contacts.
- **Data Generation**: Populate the database with random data for testing purposes.

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
- `customer_id` (Foreign Key referencing Customers table)
- `callerName`
- `recieverName`
- `call_end_time`
- `call_duration`
- `balance minutes`

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
- `customer_id` (Foreign Key referencing Customers table)
- `callerName`
- `recieverName`
- `call_end_time`
- `call_duration`
- `balance minutes`

#### Methods:
- `calculateCallDuration()`: Calculates the duration of the call.
- `checkBalanceMinutes()`: Calculates if the call is exceeding duration based on plan

### DataGenerator Class
The `DataGenerator` class provides utility methods to generate random data for testing.

#### Methods:
- `generateRandomCustomer()`: Generates a random customer.
- `generateRandomPlan()`: Generates a random plan.
- `generateRandomFriendOrFamily(String relationshipType)`: Generates a random friend or family member.
- `generateRandomCallDetails(Customer customer)`: Generates random call details for a customer.

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

## License
Licensed under the Apache License, Version 2.0.
