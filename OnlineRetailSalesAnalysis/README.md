# Shopeasy Database Project

## Overview

This project represents a structured relational database system for an e-commerce platform named Shopeasy. It is designed to manage customers, products, orders, and order details efficiently.

The project demonstrates understanding of database design, relationships, and SQL querying for business insights.

---

## Database Design

The database consists of four main tables:

### Customers
Stores customer details such as name, city, email, and registration date.

### Products
Contains product information including category and price.

### Orders
Stores order-level details and links each order to a customer.

### Order_Items
Acts as a junction table connecting orders and products, storing quantity for each item in an order.

---

## Relationships

- One customer can place multiple orders  
- One order can contain multiple items  
- One product can appear in multiple orders  
- Orders and products have a many-to-many relationship handled using the Order_Items table  

---

## Schema Diagram

Add your exported schema diagram image here:

schema.png

---

## SQL Features and Queries

The project includes multiple analytical queries such as:

- Top selling products based on quantity and revenue  
- Top customers based on total spending  
- Monthly revenue and order trends  
- Category-wise sales performance  
- Identification of inactive customers  
- Customers who never placed orders  
- City-wise revenue analysis  
- Average order value calculation  
- Customer segmentation based on purchase frequency  
- Best performing month by revenue  
- Products that have never been ordered  
- Overall business summary including revenue and top performers  

---

## Technologies Used

- MySQL  
- SQL (DDL, DML, Joins, Aggregations, Subqueries)  

---

## How to Run

1. Open MySQL Workbench or any MySQL client  
2. Run the SQL script file to create the database and tables  
3. Insert the sample data  
4. Execute the analytical queries to generate insights  

---

## Project Highlights

- Well-structured relational schema  
- Proper use of primary and foreign keys  
- Use of normalization and junction table design  
- Realistic sample dataset  
- Business-oriented SQL queries  

