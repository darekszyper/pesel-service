# Pesel Service REST API

The Pesel Service REST API is a Java application that provides functionality related to PESEL (Personal Identification Number) validation, parsing, and decoding. It allows you to extract information such as gender, birth date, and the day of the week from a given PESEL number.

## Table of Contents

- [Introduction](#introduction)
- [Technologies](#technologies)
- [Usage](#usage)
  - [Getting Started](#getting-started)
  - [Example](#example)
- [Logging and Exception Handling](#logging-and-exception-handling)

## Introduction

PESEL is a national identification number used in Poland. The Pesel Service REST API allows you to work with PESEL numbers by validating their format, parsing the PESEL number into a structured object, and extracting useful information like gender, birth date, and birth day of the week.

## Technologies

The Pesel Service REST API is developed using the following technologies:

- Java 20
- RESTful API
- Spring Framework: The application utilizes the Spring framework for dependency injection
- Aspect-Oriented Programming (AOP)
- Lombok: Used for reducing boilerplate code with annotations like `@Getter`, `@Setter`, and `@ToString`
- MapStruct: Used for mapping between DTOs and domain objects
- Swagger: OpenAPI Documentation

## Usage

### Getting Started

To run the Pesel Service REST API locally, follow these steps:

1. **Clone the Repository**

2. **Run the application**

## Example
Once the application is running, you can access the RESTful API by sending POST request using Postman to http://localhost:8080/api/v1/pesel. The API endpoint expects a JSON payload containing the pesel field with the PESEL number to be validated and decoded. The response will include information about the gender, birth date, and birth day of the week.

| Example Request: | Example Response: |
| :---- | :---- |
| {<br>    "pesel" : "82041932416"<br>}<br> | {<br>    "gender": "MALE",<br>    "birthDate": "1982-04-19",<br>    "birthDayOfWeek":"MONDAY"<br>} |

## Logging and Exception Handling
The Pesel Service REST API utilizes logging and exception handling to provide useful information about method invocations, responses, and encountered exceptions. The application uses AOP (Aspect-Oriented Programming) to achieve this. The LoggingAdvice aspect logs method invocations and responses, while the GlobalExceptionHandler handles and logs exceptions of type InvalidPeselException with an appropriate error message and a BAD_REQUEST HTTP status.
