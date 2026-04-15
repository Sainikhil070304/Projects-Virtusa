# 📚 Digital Library Audit System (MySQL)

## 📌 Project Overview

The **Digital Library Audit System** is a SQL-based project designed to manage and analyze book borrowing activities in a college library.
It helps track issued books, identify overdue returns, calculate penalties, and analyze borrowing trends.

---

## 🎯 Business Case

A local community college needed a system to:

* Track book loans efficiently
* Identify overdue books
* Analyze most popular categories
* Manage inactive student accounts

---

## 🧩 Problem Statement

Design a relational database system that:

* Stores student and book data
* Tracks book issue and return details
* Generates penalty reports for overdue books (>14 days)
* Provides analytical insights

---

## 🛠️ Technologies Used

* **Database:** MySQL
* **Tool:** MySQL Workbench
* **Language:** SQL

---

## 🗂️ Database Schema

### 📘 Tables:

1. **Students**

   * StudentID (Primary Key)
   * FullName
   * Email
   * JoinDate

2. **Books**

   * BookID (Primary Key)
   * Title
   * Author
   * Category
   * TotalCopies

3. **IssuedBooks**

   * IssueID (Primary Key)
   * StudentID (Foreign Key)
   * BookID (Foreign Key)
   * IssueDate
   * ReturnDate

---

## ⚙️ Features Implemented

### ✅ 1. Overdue Penalty Report

* Identifies books not returned after 14 days
* Calculates overdue days and fine

![Overdue Report](images/overdue_books_penalty_report.png)

---

### ✅ 2. Category Popularity Analysis

* Finds most borrowed book categories using `GROUP BY`

![Category Analysis](images/category_popularity_ranking.png)

---

### ✅ 3. Top Students

* Identifies most active students based on borrowing frequency

![Top Students](images/top_students_borrowing_frequency.png)

---

### ✅ 4. Inactive Students Detection

* Detects students with no borrowing activity for 3+ years

![Inactive Students](images/inactive_students_no_borrow_history.png)

---

### ✅ 5. Dashboard Summary

* Displays key metrics:

  * Total Students
  * Total Books
  * Total Transactions
  * Active Loans
  * Overdue Books

![Dashboard](images/library_dashboard_summary.png)

---

### ✅ 6. Advanced Ranking Analysis

* Uses `DENSE_RANK()` to rank students by borrowing activity

![Ranking](images/student_borrowing_rank_analysis.png)

---

## 📊 Dataset

* 1000+ Students
* 1000+ Book Transactions
* Randomized realistic data generation

---

## 🔥 Advanced SQL Concepts Used

* JOIN operations
* GROUP BY & Aggregation
* Subqueries
* Window Functions (`RANK`, `DENSE_RANK`)
* CASE statements
* Date functions (`DATEDIFF`, `INTERVAL`)

---

## 🧹 Data Cleanup

* Removed inactive students using subquery logic
* Ensured data integrity using foreign keys

---

## 🚀 How to Run

1. Open **MySQL Workbench**
2. Open the file:

   ```
   Digital_Library_Audit.sql
   ```
3. Execute the script
4. View results in result grid

---

## 📁 Project Structure

```
Digital_Library_Audit/
│
├── Digital_Library_Audit.sql
├── README.md
├── overdue_books_penalty_report.png
├── category_popularity_ranking.png
├── top_students_borrowing_frequency.png
├── inactive_students_no_borrow_history.png
├── library_dashboard_summary.png
└── student_borrowing_rank_analysis.png
```

---

## 💡 Key Insights

* Fiction is the most popular category
* Significant number of overdue books detected
* System helps in decision-making for library purchases

---

## 👨‍💻 Author

**N Sai Nikhil Tej**

---

## 📌 Conclusion

This project demonstrates how SQL can be used to build a **real-world data analysis system**.
It provides both operational and analytical insights, making it suitable for academic and practical applications.

---
