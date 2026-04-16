# 🔐 SafeLog – Employee Password Validator

A **Java-based console application** designed to validate employee passwords using customizable security policies.
This project demonstrates strong **Object-Oriented Programming (OOP)** concepts and simulates a real-world cybersecurity system.

---

## 📌 Features

* 🔒 Password validation with multiple security rules
* 🧠 Strength evaluation (Very Weak → Very Strong)
* ⚙️ Multiple security policies:

  * Basic (3 rules)
  * Standard (5 rules)
  * Strict (6 rules)
* 🔁 Retry mechanism with maximum attempts
* 📊 Detailed rule-by-rule feedback
* 📝 Audit logging system
* 👤 User account tracking with attempt history

---

## 🧱 OOP Concepts Implemented

* **Abstraction** → Interface (`PasswordPolicy`) & Abstract Class (`ValidationRule`)
* **Encapsulation** → `ValidationResult`, `UserAccount`, `AuditLogger`
* **Inheritance** → Rule classes (`LengthRule`, `DigitRule`, etc.)
* **Polymorphism** → Dynamic rule execution using interface references
* **Singleton Pattern** → `AuditLogger`
* **Factory Pattern** → `PolicyFactory`
* **Enum** → `PasswordStrength`

---

## ⚙️ Technologies Used

* Java (JDK 8+)
* VS Code / IntelliJ IDEA
* Console-based UI

---

## 🚀 How to Run

### 1️⃣ Compile the program

```bash
javac PasswordValidator.java
```

### 2️⃣ Run the program

```bash
java PasswordValidator
```

---

## 📸 Output Screenshot

![SafeLog Output](images/safelog-full-execution-output.png)

---

## 📊 Sample Flow

1. Enter employee details
2. Select security policy
3. Enter password
4. Get validation results:

   * Strength score
   * Rule-by-rule feedback
5. Account setup summary & audit log

---

## 📁 Project Structure

```
SafeLogPasswordValidator/
 ├── PasswordValidator.java
 ├── README.md
 └── images/
      └── safelog-full-execution-output.png
```

---

## 💡 Example Rules

* Minimum length requirement
* At least one uppercase letter
* At least one digit
* Special character requirement
* No whitespace allowed
* Blacklisted password detection

---

## 🎯 Use Case

This project simulates a **real-world employee password system** used in:

* Corporate login portals
* Cybersecurity applications
* Authentication systems

---

## 🏆 Highlights

* Clean modular design
* Strong OOP implementation
* Real-world simulation
* Interview-ready project

---

## 👨‍💻 Author

**Sai Nikhil Tej**
Final Year Engineering Student

---

## ⭐ If you like this project

Give it a ⭐ on GitHub and feel free to fork!

---
