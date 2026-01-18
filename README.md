# Rule Engine

A Spring Boot application that evaluates transactions against configurable rules using JavaScript expressions.

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Setup

1. Clone or download the project
2. Navigate to the project directory:
```
cd rule-engine-api
```

## Running the Application

### Using Maven (Windows)
```
mvnw.cmd spring-boot:run
```

### Using Maven (Linux/Mac)
```
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### 1. Add Rules
Add one or more rules to the engine. Rules are JavaScript expressions that evaluate transactions.

**Endpoint:** `POST /rules`

**Request Body:**
```json
[
  "amount > 1000 && type == 'credit'",
  "category == 'electronics' && amount > 500"
]
```


**Response:**
```
Rules added!
```

### 2. Get All Rules
Retrieve all configured rules.

**Endpoint:** `GET /rules`

**Example (curl):**
```bash
curl http://localhost:8080/rules
```

**Response:**
```json
[
  "amount > 1000 && type == 'credit'",
  "category == 'electronics' && amount > 500"
]
```

### 3. Evaluate Transactions
Evaluate a list of transactions against all configured rules. Returns transactions that match at least one rule.

**Endpoint:** `POST /rules/evaluate`

**Request Body:**
```json
[
  {
    "id": 1,
    "amount": 1500,
    "type": "credit",
    "category": "electronics"
  },
  {
    "id": 2,
    "amount": 300,
    "type": "debit",
    "category": "groceries"
  }
]
```



**Response:**
```json
[
  {
    "id": 1,
    "amount": 1500,
    "type": "credit",
    "category": "electronics"
  }
]
```




## Stopping the Application

Press `Ctrl+C` in the terminal where the application is running.
