# Bank Application (Spring Boot REST API)

A Bank Management System REST API built with **Spring Boot, Spring Security, Spring Data JPA and MySQL**.
Create accounts, deposit, withdraw, and keep a transaction history.

## Features
- Create / view / delete bank accounts (with customer details)
- Deposit and withdraw money with validation (amount > 0, sufficient balance)
- Automatic transaction history for every deposit / withdrawal
- HTTP Basic authentication with Spring Security

## Tech Stack
| Layer | Technology |
|-------|-----------|
| Language | Java 17 |
| Framework | Spring Boot 3.5.0 |
| Security | Spring Security (Basic Auth) |
| Database | MySQL + Spring Data JPA / Hibernate |
| Build | Maven |

## How to Run
1. Install **Java 17+**, **Maven** and **MySQL**.
2. Set your MySQL password:
   - Windows CMD: `set DB_PASSWORD=your_password`
   - Linux/macOS: `export DB_PASSWORD=your_password`
   - or edit `spring.datasource.password` in `src/main/resources/application.properties`
3. Start the app:
   ```bash
   mvn spring-boot:run
   ```
4. Open Postman and use **Basic Auth**: `admin` / `admin123` (demo credentials).

The `bank` database and its tables are created automatically. Full guide: [docs/RUN_AND_OUTPUT.md](docs/RUN_AND_OUTPUT.md)

## API Endpoints
| Method | URL | Description |
|--------|-----|-------------|
| POST | `/accounts/addaccount` | Create account |
| GET | `/accounts/selectall` | List all accounts |
| GET | `/accounts/part/{id}` | Get account by ID |
| PUT | `/accounts/deposit/{id}/{amount}` | Deposit money |
| PUT | `/accounts/withdraw/{id}/{amount}` | Withdraw money |
| DELETE | `/accounts/delete/{id}` | Delete account |

Sample body for `POST /accounts/addaccount`:
```json
{
  "accountNumber": "1001",
  "accountType": "Savings",
  "balance": 5000,
  "cust": { "name": "Baskar", "email": "baskar@example.com", "phone": "9876543210" }
}
```

## Expected Output
> Illustrative sample outputs (not live screenshots). IDs and timestamps will differ.

**1. Application startup**

![Console startup](docs/images/01-console-startup.png)

**2. Create account**

![Create account](docs/images/02-create-account.png)

**3. Deposit**

![Deposit](docs/images/03-deposit.png)

**4. Withdraw**

![Withdraw](docs/images/04-withdraw.png)

**5. Get all accounts**

![Get all accounts](docs/images/05-get-all-accounts.png)

**6. Wrong / missing login**

![Unauthorized](docs/images/06-unauthorized.png)

**7. Data in MySQL**

![MySQL tables](docs/images/07-mysql-tables.png)

## Project Structure
```
bank
├── pom.xml
├── README.md
├── docs
│   ├── RUN_AND_OUTPUT.md
│   ├── GITHUB_UPLOAD_GUIDE.md
│   └── images/
└── src/main
    ├── java/com/example/bank
    │   ├── BankApplication.java
    │   ├── controller/AccountController.java
    │   ├── model/{Account,Customer,Transaction}.java
    │   ├── repo/{AccountRepo,CustomerRepo,TransactionRepo}.java
    │   ├── security/SecurityConfig.java
    │   └── service/AccountService.java
    └── resources/application.properties
```

## Author
Baskar

## License
MIT - see [LICENSE](LICENSE)
