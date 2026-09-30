# How to Run the Project + Expected Output

> The outputs below are **sample results** showing what you should see. IDs, timestamps and
> timing will differ on your machine.

## 1. Prerequisites
- Java 17+ (`java -version`)
- Maven 3.9+ (`mvn -version`)
- MySQL running on `localhost:3306`

## 2. Set the database password
Windows (CMD):
```cmd
set DB_PASSWORD=your_mysql_password
```
Windows (PowerShell):
```powershell
$env:DB_PASSWORD="your_mysql_password"
```
Linux / macOS:
```bash
export DB_PASSWORD=your_mysql_password
```
(or edit `spring.datasource.password` in `src/main/resources/application.properties`)

## 3. Run
```bash
cd bank
mvn spring-boot:run
```
Or run `BankApplication.java` as a Java application in Eclipse / STS / IntelliJ.

Alternative (jar):
```bash
mvn clean package
java -jar target/bank-0.0.1-SNAPSHOT.jar
```

## 4. Expected console output
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.5.0)

c.example.bank.BankApplication : Starting BankApplication using Java 17
c.example.bank.BankApplication : No active profile set, falling back to 1 default profile: "default"
.s.d.r.c.RepositoryConfigurationDelegate : Found 3 JPA repository interfaces.
o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
Hibernate: create table account (...)
Hibernate: create table customer (...)
Hibernate: create table transaction (...)
o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http)
c.example.bank.BankApplication           : Started BankApplication in 6.2 seconds
```
Three tables are created automatically in the `bank` database: `account`, `customer`, `transaction`.

## 5. Test in Postman
**Authorization tab -> Basic Auth -> Username: `admin`, Password: `admin123`**

### 5.1 Create account
`POST http://localhost:8080/accounts/addaccount`  (Body -> raw -> JSON)
```json
{
  "accountNumber": "1001",
  "accountType": "Savings",
  "balance": 5000,
  "cust": {
    "name": "Baskar",
    "email": "baskar@example.com",
    "phone": "9876543210"
  }
}
```
Expected response (200 OK):
```json
{
  "id": 1,
  "accountNumber": "1001",
  "accountType": "Savings",
  "balance": 5000.0,
  "cust": {
    "id": 1,
    "name": "Baskar",
    "email": "baskar@example.com",
    "phone": "9876543210"
  },
  "transactions": null
}
```

### 5.2 Get all accounts
`GET http://localhost:8080/accounts/selectall`
```json
[
  {
    "id": 1,
    "accountNumber": "1001",
    "accountType": "Savings",
    "balance": 5000.0,
    "cust": { "id": 1, "name": "Baskar", "email": "baskar@example.com", "phone": "9876543210" },
    "transactions": []
  }
]
```

### 5.3 Get account by ID
`GET http://localhost:8080/accounts/part/1` -> the single account object (same shape as above).

### 5.4 Deposit
`PUT http://localhost:8080/accounts/deposit/1/2000`
```json
{
  "id": 1,
  "accountNumber": "1001",
  "accountType": "Savings",
  "balance": 7000.0,
  "cust": { "id": 1, "name": "Baskar", "email": "baskar@example.com", "phone": "9876543210" },
  "transactions": [
    { "id": 1, "transactionType": "Deposit", "amount": 2000.0, "transactiondate": "2026-09-30T12:15:42.318" }
  ]
}
```

### 5.5 Withdraw
`PUT http://localhost:8080/accounts/withdraw/1/1500`
-> `balance` becomes `5500.0` and a `Withdraw` transaction of `1500.0` is added.

### 5.6 Delete
`DELETE http://localhost:8080/accounts/delete/1`
```
Account deleted successfully
```

## 6. Expected error results
| Situation | Result |
|-----------|--------|
| No / wrong login | `401 Unauthorized` |
| Account ID does not exist | `500 Internal Server Error` (service throws "Account not found with ID: n") |
| Withdraw more than balance | `500 Internal Server Error` ("Insufficient balance") |
| Amount `0` or negative | `500 Internal Server Error` |

Spring Boot hides the exception text in the response by default; the message appears in the
console log. To show it in the response, add this to `application.properties`:
```
server.error.include-message=always
```

## 7. Check the data in MySQL
```sql
USE bank;
SELECT * FROM customer;
SELECT * FROM account;
SELECT * FROM transaction;
```
Sample:
```
account:      id=1, account_number=1001, account_type=Savings, balance=5500, customer_id=1
transaction:  id=1, Deposit,  2000, 2026-09-30 12:15:42, account_id=1
              id=2, Withdraw, 1500, 2026-09-30 12:17:05, account_id=1
```

## 8. Common problems
- **Access denied for user 'root'** -> wrong `DB_PASSWORD`.
- **Communications link failure** -> MySQL is not running or port is not 3306.
- **Port 8080 already in use** -> change `server.port` in `application.properties`.
- **Wrong Java version** -> the project needs Java 17+.
