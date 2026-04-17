# Banking Transaction System

A Java Spring Boot application to manage bank accounts and transactions.  
The system supports account creation, deposits, withdrawals, fund transfers, transaction history, and admin monitoring. Built with Spring Boot, Hibernate, and MySQL.

---

## 🚀 Features
- Create and manage bank accounts
- Deposit and withdraw money
- Transfer funds between accounts
- View transaction history
- Admin monitoring of accounts and transactions
- Input validation and error handling

---

## 🛠️ Tech Stack
- **Backend:** Java, Spring Boot, Hibernate
- **Database:** MySQL
- **API:** RESTful Web Services
- **Build Tool:** Maven
- **Version Control:** Git, GitHub
- **IDE:** IntelliJ IDEA / Eclipse

---

## 📂 Project Structure
```
Banking-Transaction-System/
│
├─ src/
│  ├─ main/
│  │  ├─ java/com/bank/          # Controllers, Services, Entities, Repositories, Exceptions
│  │  └─ resources/              # application.properties, templates
│  └─ test/java/com/bank/        # Test classes
├─ .gitignore
├─ LICENSE
├─ README.md
├─ pom.xml
├─ mvnw
├─ mvnw.cmd
└─ MJ HELP.md
```

---

## ⚡ Installation & Usage
1. Clone the repository:
```bash
git clone https://github.com/Kddeshmukh/banking-transaction-system.git
```
2. Open the project in **IntelliJ IDEA** or **Eclipse**.
3. Update `src/main/resources/application.properties` with your MySQL database credentials.
4. Build the project using Maven:
```bash
mvn clean install
```
5. Run the Spring Boot application:
```bash
mvn spring-boot:run
```
6. Open your browser at:
```
http://localhost:8080/
```

---

## 📌 License
This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.

---

## 🔗 GitHub Repository
[https://github.com/Kddeshmukh/banking-transaction-system](https://github.com/Kddeshmukh/banking-transaction-system)
