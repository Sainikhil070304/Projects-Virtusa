CREATE DATABASE IF NOT EXISTS shopeasy;
USE shopeasy;

CREATE TABLE Customers (
    customer_id   INT PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    city          VARCHAR(50)  NOT NULL,
    email         VARCHAR(100),
    joined_date   DATE
);

CREATE TABLE Products (
    product_id   INT PRIMARY KEY,
    name         VARCHAR(150) NOT NULL,
    category     VARCHAR(50)  NOT NULL,
    price        DECIMAL(10, 2) NOT NULL
);

CREATE TABLE Orders (
    order_id     INT PRIMARY KEY,
    customer_id  INT  NOT NULL,
    order_date   DATE NOT NULL,
    status       VARCHAR(20) DEFAULT 'Delivered',
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);

CREATE TABLE Order_Items (
    item_id      INT PRIMARY KEY,
    order_id     INT NOT NULL,
    product_id   INT NOT NULL,
    quantity     INT NOT NULL,
    FOREIGN KEY (order_id)   REFERENCES Orders(order_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);
INSERT INTO Customers VALUES
(1,  'Aarav Sharma',    'Mumbai',    'aarav@gmail.com',    '2022-03-15'),
(2,  'Priya Mehta',     'Delhi',     'priya@gmail.com',    '2022-06-20'),
(3,  'Rohan Verma',     'Bangalore', 'rohan@gmail.com',    '2022-08-10'),
(4,  'Sneha Iyer',      'Chennai',   'sneha@gmail.com',    '2023-01-05'),
(5,  'Karan Patel',     'Ahmedabad', 'karan@gmail.com',    '2023-02-18'),
(6,  'Divya Nair',      'Kochi',     'divya@gmail.com',    '2023-04-22'),
(7,  'Arjun Singh',     'Jaipur',    'arjun@gmail.com',    '2023-05-30'),
(8,  'Meera Pillai',    'Hyderabad', 'meera@gmail.com',    '2023-07-14'),
(9,  'Vikram Rao',      'Pune',      'vikram@gmail.com',   '2023-09-01'),
(10, 'Ananya Das',      'Kolkata',   'ananya@gmail.com',   '2021-11-25'),
(11, 'Ravi Kumar',      'Mumbai',    'ravi@gmail.com',     '2021-05-10'),
(12, 'Pooja Joshi',     'Delhi',     'pooja@gmail.com',    '2022-12-01'),
(13, 'Suresh Babu',     'Bangalore', 'suresh@gmail.com',   '2020-03-08'),
(14, 'Lakshmi Reddy',   'Chennai',   'lakshmi@gmail.com',  '2020-07-19'),
(15, 'Nikhil Gupta',    'Bhopal',    'nikhil@gmail.com',   '2021-10-30');

INSERT INTO Products VALUES
(1,  'iPhone 15',              'Electronics',  79999.00),
(2,  'Samsung Galaxy S23',     'Electronics',  69999.00),
(3,  'Sony WH-1000XM5',        'Electronics',  29999.00),
(4,  'Nike Air Max 270',       'Footwear',     12999.00),
(5,  'Adidas Ultraboost 22',   'Footwear',     14999.00),
(6,  'Levi 511 Slim Jeans',    'Clothing',      3499.00),
(7,  'Allen Solly Formal Shirt','Clothing',     1999.00),
(8,  'Prestige Induction Cooktop','Appliances', 4999.00),
(9,  'Philips Air Fryer',      'Appliances',    8499.00),
(10, 'Kindle Paperwhite',      'Electronics',   9999.00),
(11, 'HP Pavilion Laptop',     'Electronics',  55999.00),
(12, 'Wildcraft Backpack',     'Bags',          2999.00),
(13, 'Bombay Shaving Grooming Kit','Personal Care', 799.00),
(14, 'Yoga Mat Premium',       'Fitness',       1499.00),
(15, 'Protein Whey 1kg',       'Fitness',       1899.00),
(16, 'boAt Airdopes 141',      'Electronics',   1299.00),
(17, 'Saree Silk Banarasi',    'Clothing',      5999.00),
(18, 'IKEA Study Table',       'Furniture',    12999.00),
(19, 'Milton Water Bottle Set','Kitchen',        699.00),
(20, 'Dettol Hand Sanitiser Pack','Personal Care', 399.00);

INSERT INTO Orders VALUES
(1001, 1,  '2024-01-05', 'Delivered'),
(1002, 2,  '2024-01-12', 'Delivered'),
(1003, 3,  '2024-01-20', 'Delivered'),
(1004, 4,  '2024-02-03', 'Delivered'),
(1005, 5,  '2024-02-14', 'Delivered'),
(1006, 6,  '2024-02-25', 'Delivered'),
(1007, 1,  '2024-03-08', 'Delivered'),
(1008, 7,  '2024-03-15', 'Delivered'),
(1009, 8,  '2024-03-22', 'Delivered'),
(1010, 9,  '2024-04-01', 'Delivered'),
(1011, 10, '2024-04-10', 'Delivered'),
(1012, 2,  '2024-04-18', 'Delivered'),
(1013, 3,  '2024-05-02', 'Delivered'),
(1014, 11, '2024-05-15', 'Delivered'),
(1015, 12, '2024-05-28', 'Delivered'),
(1016, 4,  '2024-06-06', 'Delivered'),
(1017, 5,  '2024-06-20', 'Delivered'),
(1018, 13, '2024-07-03', 'Delivered'),
(1019, 6,  '2024-07-17', 'Delivered'),
(1020, 1,  '2024-07-30', 'Delivered'),
(1021, 14, '2024-08-08', 'Delivered'),
(1022, 2,  '2024-08-19', 'Delivered'),
(1023, 7,  '2024-08-27', 'Delivered'),
(1024, 8,  '2024-09-05', 'Delivered'),
(1025, 9,  '2024-09-14', 'Delivered'),
(1026, 15, '2024-09-23', 'Delivered'),
(1027, 10, '2024-10-01', 'Delivered'),
(1028, 3,  '2024-10-12', 'Delivered'),
(1029, 4,  '2024-10-25', 'Delivered'),
(1030, 1,  '2024-11-05', 'Delivered'),
(1031, 2,  '2024-11-11', 'Delivered'),
(1032, 5,  '2024-11-20', 'Delivered'),
(1033, 6,  '2024-12-02', 'Delivered'),
(1034, 7,  '2024-12-15', 'Delivered'),
(1035, 8,  '2024-12-26', 'Delivered');

INSERT INTO Order_Items VALUES
(1,  1001, 1,  1),
(2,  1001, 16, 2),
(3,  1002, 2,  1),
(4,  1002, 12, 1),
(5,  1003, 11, 1),
(6,  1003, 14, 2),
(7,  1004, 4,  1),
(8,  1004, 6,  2),
(9,  1005, 9,  1),
(10, 1005, 19, 3),
(11, 1006, 3,  1),
(12, 1006, 13, 2),
(13, 1007, 10, 1),
(14, 1007, 20, 4),
(15, 1008, 5,  1),
(16, 1008, 7,  2),
(17, 1009, 8,  1),
(18, 1009, 15, 2),
(19, 1010, 1,  1),
(20, 1010, 16, 1),
(21, 1011, 2,  1),
(22, 1011, 14, 3),
(23, 1012, 11, 1),
(24, 1012, 6,  1),
(25, 1013, 3,  1),
(26, 1013, 17, 1),
(27, 1014, 4,  2),
(28, 1014, 20, 5),
(29, 1015, 9,  1),
(30, 1015, 19, 2),
(31, 1016, 1,  1),
(32, 1016, 12, 2),
(33, 1017, 5,  1),
(34, 1017, 13, 3),
(35, 1018, 18, 1),
(36, 1018, 14, 1),
(37, 1019, 10, 2),
(38, 1019, 7,  3),
(39, 1020, 2,  1),
(40, 1020, 15, 2),
(41, 1021, 11, 1),
(42, 1021, 16, 3),
(43, 1022, 1,  1),
(44, 1022, 19, 2),
(45, 1023, 3,  1),
(46, 1023, 6,  2),
(47, 1024, 9,  1),
(48, 1024, 13, 4),
(49, 1025, 4,  1),
(50, 1025, 20, 3),
(51, 1026, 8,  1),
(52, 1026, 17, 1),
(53, 1027, 2,  1),
(54, 1027, 12, 2),
(55, 1028, 10, 1),
(56, 1028, 15, 3),
(57, 1029, 11, 1),
(58, 1029, 7,  2),
(59, 1030, 1,  1),
(60, 1030, 16, 2),
(61, 1031, 5,  1),
(62, 1031, 14, 2),
(63, 1032, 3,  1),
(64, 1032, 18, 1),
(65, 1033, 9,  1),
(66, 1033, 6,  3),
(67, 1034, 4,  2),
(68, 1034, 20, 6),
(69, 1035, 2,  1),
(70, 1035, 13, 2);
SELECT
    p.product_id,
    p.name                        AS product_name,
    p.category,
    p.price,
    SUM(oi.quantity)              AS total_units_sold,
    SUM(oi.quantity * p.price)    AS total_revenue
FROM Order_Items oi
JOIN Products p ON oi.product_id = p.product_id
GROUP BY p.product_id, p.name, p.category, p.price
ORDER BY total_units_sold DESC
LIMIT 10;

SELECT
    c.customer_id,
    c.name                            AS customer_name,
    c.city,
    COUNT(DISTINCT o.order_id)        AS total_orders,
    SUM(oi.quantity * p.price)        AS total_spent
FROM Customers c
JOIN Orders      o  ON c.customer_id = o.customer_id
JOIN Order_Items oi ON o.order_id    = oi.order_id
JOIN Products    p  ON oi.product_id = p.product_id
GROUP BY c.customer_id, c.name, c.city
ORDER BY total_spent DESC
LIMIT 10;

SELECT
    DATE_FORMAT(o.order_date, '%Y-%m')   AS month,
    COUNT(DISTINCT o.order_id)           AS total_orders,
    SUM(oi.quantity * p.price)           AS monthly_revenue
FROM Orders o
JOIN Order_Items oi ON o.order_id    = oi.order_id
JOIN Products    p  ON oi.product_id = p.product_id
GROUP BY DATE_FORMAT(o.order_date, '%Y-%m')
ORDER BY month;

SELECT
    p.category,
    COUNT(DISTINCT oi.order_id)       AS total_orders,
    SUM(oi.quantity)                  AS units_sold,
    SUM(oi.quantity * p.price)        AS category_revenue,
    ROUND(
        SUM(oi.quantity * p.price) * 100.0 /
        (SELECT SUM(oi2.quantity * p2.price)
         FROM Order_Items oi2
         JOIN Products p2 ON oi2.product_id = p2.product_id), 2
    )                                 AS revenue_percentage
FROM Order_Items oi
JOIN Products p ON oi.product_id = p.product_id
GROUP BY p.category
ORDER BY category_revenue DESC;

SELECT
    c.customer_id,
    c.name,
    c.city,
    c.email,
    MAX(o.order_date)                AS last_order_date,
    DATEDIFF(CURRENT_DATE, MAX(o.order_date)) AS days_since_last_order
FROM Customers c
JOIN Orders o ON c.customer_id = o.customer_id
GROUP BY c.customer_id, c.name, c.city, c.email
HAVING MAX(o.order_date) < DATE_SUB(CURRENT_DATE, INTERVAL 6 MONTH)
ORDER BY last_order_date ASC;

SELECT
    c.customer_id,
    c.name,
    c.city,
    c.email,
    c.joined_date,
    'Never ordered' AS status
FROM Customers c
LEFT JOIN Orders o ON c.customer_id = o.customer_id
WHERE o.order_id IS NULL;

SELECT
    c.city,
    COUNT(DISTINCT c.customer_id)    AS total_customers,
    COUNT(DISTINCT o.order_id)       AS total_orders,
    SUM(oi.quantity * p.price)       AS city_revenue
FROM Customers c
JOIN Orders      o  ON c.customer_id = o.customer_id
JOIN Order_Items oi ON o.order_id    = oi.order_id
JOIN Products    p  ON oi.product_id = p.product_id
GROUP BY c.city
ORDER BY city_revenue DESC;

SELECT
    ROUND(
        SUM(oi.quantity * p.price) / COUNT(DISTINCT o.order_id), 2
    ) AS avg_order_value
FROM Orders o
JOIN Order_Items oi ON o.order_id    = oi.order_id
JOIN Products    p  ON oi.product_id = p.product_id;

SELECT
    CASE
        WHEN order_count = 1 THEN 'One-Time Buyer'
        WHEN order_count BETWEEN 2 AND 4 THEN 'Occasional Buyer'
        ELSE 'Loyal Customer'
    END                          AS customer_type,
    COUNT(*)                     AS total_customers
FROM (
    SELECT customer_id, COUNT(order_id) AS order_count
    FROM Orders
    GROUP BY customer_id
) AS order_summary
GROUP BY customer_type
ORDER BY total_customers DESC;

SELECT
    DATE_FORMAT(o.order_date, '%Y-%m')  AS month,
    SUM(oi.quantity * p.price)          AS revenue
FROM Orders o
JOIN Order_Items oi ON o.order_id    = oi.order_id
JOIN Products    p  ON oi.product_id = p.product_id
GROUP BY DATE_FORMAT(o.order_date, '%Y-%m')
ORDER BY revenue DESC
LIMIT 1;

SELECT
    p.product_id,
    p.name,
    p.category,
    p.price
FROM Products p
LEFT JOIN Order_Items oi ON p.product_id = oi.product_id
WHERE oi.order_id IS NULL;

SELECT
    (SELECT COUNT(*)                    FROM Customers)               AS total_customers,
    (SELECT COUNT(*)                    FROM Products)                AS total_products,
    (SELECT COUNT(*)                    FROM Orders)                  AS total_orders,
    (SELECT SUM(oi.quantity * p.price)
     FROM Order_Items oi
     JOIN Products p ON oi.product_id = p.product_id)                AS total_revenue,
    (SELECT p.name
     FROM Order_Items oi
     JOIN Products p ON oi.product_id = p.product_id
     GROUP BY p.product_id, p.name
     ORDER BY SUM(oi.quantity) DESC LIMIT 1)                         AS best_selling_product,
    (SELECT c.name
     FROM Customers c
     JOIN Orders o      ON c.customer_id = o.customer_id
     JOIN Order_Items oi ON o.order_id   = oi.order_id
     JOIN Products p    ON oi.product_id = p.product_id
     GROUP BY c.customer_id, c.name
     ORDER BY SUM(oi.quantity * p.price) DESC LIMIT 1)               AS top_customer,
    (SELECT p.category
     FROM Order_Items oi
     JOIN Products p ON oi.product_id = p.product_id
     GROUP BY p.category
     ORDER BY SUM(oi.quantity * p.price) DESC LIMIT 1)               AS top_category;