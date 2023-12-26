```markdown
# Employwise API

This is a Spring Boot project for the Employwise API.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Configuration](#configuration)
- [Usage](#usage)
  - [API Endpoints](#api-endpoints)
  - [Error Handling](#error-handling)
- [Contributing](#contributing)
- [License](#license)

## Introduction

This is the assesment project and I have completed the tasks of all the three levels including Begineer, Intermediate, Advanced.

## Features

- CRUD operations for employees
  - Getting Details of all employees
  - Creating employees with their id(generated through uuid), name, mobile no, email, manager id, image url.
  - Updating user based on their id.
  - Deleting user based on their id.
- API for getting the nth level manager of an employee
- Pagination and sorting options for getting all employees
- Send email notification to level 1 manager on new employee addition

## Getting Started

### Prerequisites

dependencies that need to be installed before running the project.

- Java 17
- Spring Boot Maven
- MongoDB

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/employwise-api.git
   ```

2. Build the project:

   ```bash
   cd employwise-api
   mvn clean install
   ```

### Configuration

Add Value of MongoDB Uri and Your email address and passkey.

### API Endpoints

API endpoints, including request and response examples.

#### Add Employee

1. **Create Employee:**
   - **Endpoint:** `POST /employee`
   - **Request Body:**
     ```json
     {
       "name": "John Doe",
       "mobileNum": 1234567890,
       "mail": "john.doe@example.com",
       "reportId": "manager123",
       "image": "https://example.com/johndoe.jpg"
     }
     ```
   - **Response:**
     ```json
     {
       "employeeId": "generatedId",
       "name": "John Doe",
       "mobileNum": 1234567890,
       "mail": "john.doe@example.com",
       "reportId": "manager123",
       "image": "https://example.com/johndoe.jpg"
     }
     ```
   - **HTTP Status:** `201 Created`

2. **Get All Employees:**
   - **Endpoint:** `GET /employee`
   - **Response:**
     ```json
     [
       {
         "employeeId": "1",
         "name": "Employee 1",
         "mobileNum": 1234567890,
         "mail": "employee1@example.com",
         "reportId": "manager123",
         "image": "https://example.com/employee1.jpg"
       },
       {
         "employeeId": "2",
         "name": "Employee 2",
         "mobileNum": 9876543210,
         "mail": "employee2@example.com",
         "reportId": "manager123",
         "image": "https://example.com/employee2.jpg"
       },
       // ... other employees
     ]
     ```
   - **HTTP Status:** `200 OK`

3. **Get Employees with Pagination and Sorting:**
   - **Endpoint:** `GET /employee/pagination?page=0&size=10&sortBy=name`
   - **Response:**
     ```json
     [
       {
         "employeeId": "1",
         "name": "Employee 1",
         "mobileNum": 1234567890,
         "mail": "employee1@example.com",
         "reportId": "manager123",
         "image": "https://example.com/employee1.jpg"
       },
       {
         "employeeId": "2",
         "name": "Employee 2",
         "mobileNum": 9876543210,
         "mail": "employee2@example.com",
         "reportId": "manager123",
         "image": "https://example.com/employee2.jpg"
       },
       // ... other employees
     ]
     ```
   - **HTTP Status:** `200 OK` or `404 Not Found` if no employees found

4. **Get nth Level Manager of an Employee:**
   - **Endpoint:** `GET /employee/{employeeId}/manager/{level}`
   - **Response:**
     ```json
     {
       "employeeId": "managerId",
       "name": "Manager Name",
       "mobileNum": 9876543210,
       "mail": "manager@example.com",
       "reportId": "topManagerId",
       "image": "https://example.com/manager.jpg"
     }
     ```
   - **HTTP Status:** `200 OK` or `404 Not Found` if no manager found

5. **Update Employee:**
   - **Endpoint:** `PUT /employee/{employeeId}`
   - **Request Body:**
     ```json
     {
       "name": "Updated Employee Name",
       "mobileNum": 9876543210,
       "mail": "updated.employee@example.com",
       "reportId": "updatedManagerId",
       "image": "https://example.com/updated_employee.jpg"
     }
     ```
   - **Response:**
     ```json
     {
       "employeeId": "updatedEmployeeId",
       "name": "Updated Employee Name",
       "mobileNum": 9876543210,
       "mail": "updated.employee@example.com",
       "reportId": "updatedManagerId",
       "image": "https://example.com/updated_employee.jpg"
     }
     ```
   - **HTTP Status:** `200 OK` or `404 Not Found` if no employee found

6. **Delete Employee:**
   - **Endpoint:** `DELETE /employee/{employeeId}`
   - **Response:**
     ```json
     "Employee with ID {employeeId} deleted Successfully"
     ```
   - **HTTP Status:** `200 OK` or `404 Not Found` if no employee found

These are the RESTful API endpoints used here.


## Contributing

Explain how others can contribute to your project.

1. Fork the project.
2. Create a new branch: `git checkout -b feature-name`.
3. Commit your changes: `git commit -m 'Add feature'`.
4. Push to the branch: `git push origin feature-name`.
5. Open a pull request.

## License

This project is licensed under the [MIT License](LICENSE).
```
