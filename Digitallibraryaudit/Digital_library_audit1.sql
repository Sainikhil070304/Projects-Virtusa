CREATE DATABASE IF NOT EXISTS library_db;
USE library_db;
SET SQL_SAFE_UPDATES = 0;
DROP TABLE IF EXISTS IssuedBooks;
DROP TABLE IF EXISTS Books;
DROP TABLE IF EXISTS Students;
CREATE TABLE Students (
    StudentID INT PRIMARY KEY AUTO_INCREMENT,
    FullName VARCHAR(100) NOT NULL,
    Email VARCHAR(100),
    JoinDate DATE NOT NULL
);
CREATE TABLE Books (
    BookID INT PRIMARY KEY AUTO_INCREMENT,
    Title VARCHAR(200) NOT NULL,
    Author VARCHAR(100),
    Category VARCHAR(50),
    TotalCopies INT DEFAULT 5
);
CREATE TABLE IssuedBooks (
    IssueID INT PRIMARY KEY AUTO_INCREMENT,
    StudentID INT,
    BookID INT,
    IssueDate DATE,
    ReturnDate DATE,
    FOREIGN KEY (StudentID) REFERENCES Students(StudentID),
    FOREIGN KEY (BookID) REFERENCES Books(BookID)
);
INSERT INTO Students (FullName, Email, JoinDate)
SELECT 
    CONCAT('Student_', n),
    CONCAT('student', n, '@college.edu'),
    DATE_SUB(CURDATE(), INTERVAL FLOOR(RAND()*1500) DAY)
FROM (
    SELECT a.N + b.N*10 + c.N*100 + 1 AS n
    FROM 
    (SELECT 0 N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 
     UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a,
    (SELECT 0 N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 
     UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b,
    (SELECT 0 N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 
     UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) c
) numbers
LIMIT 1000;
INSERT INTO Books (Title, Author, Category, TotalCopies) VALUES
('The Great Gatsby','F. Scott Fitzgerald','Fiction',5),
('A Brief History of Time','Stephen Hawking','Science',5),
('Sapiens','Yuval Noah Harari','History',5),
('Clean Code','Robert C. Martin','Technology',5),
('Dune','Frank Herbert','Fiction',5);
INSERT INTO IssuedBooks (StudentID, BookID, IssueDate, ReturnDate)
SELECT 
    FLOOR(1 + RAND()*1000),
    FLOOR(1 + RAND()*5),
    DATE_SUB(CURDATE(), INTERVAL FLOOR(RAND()*60) DAY),
    CASE 
        WHEN RAND() > 0.6 THEN NULL
        ELSE DATE_SUB(CURDATE(), INTERVAL FLOOR(RAND()*30) DAY)
    END
FROM (
    SELECT 1 FROM Students LIMIT 1000
) t;
SELECT 
    s.StudentID,
    s.FullName,
    b.Title,
    DATEDIFF(CURDATE(), ib.IssueDate) AS TotalDays,
    DATEDIFF(CURDATE(), ib.IssueDate) - 14 AS OverdueDays,
    (DATEDIFF(CURDATE(), ib.IssueDate) - 14) * 2 AS FineAmount
FROM IssuedBooks ib
JOIN Students s ON s.StudentID = ib.StudentID
JOIN Books b ON b.BookID = ib.BookID
WHERE ib.ReturnDate IS NULL
AND DATEDIFF(CURDATE(), ib.IssueDate) > 14
ORDER BY FineAmount DESC;
SELECT 
    b.Category,
    COUNT(*) AS BorrowCount,
    RANK() OVER (ORDER BY COUNT(*) DESC) AS RankPosition
FROM IssuedBooks ib
JOIN Books b ON b.BookID = ib.BookID
GROUP BY b.Category;
SELECT 
    s.FullName,
    COUNT(*) AS BooksBorrowed
FROM IssuedBooks ib
JOIN Students s ON s.StudentID = ib.StudentID
GROUP BY s.FullName
ORDER BY BooksBorrowed DESC
LIMIT 5;
SELECT 
    s.StudentID,
    s.FullName,
    MAX(ib.IssueDate) AS LastBorrow
FROM Students s
LEFT JOIN IssuedBooks ib ON s.StudentID = ib.StudentID
GROUP BY s.StudentID
HAVING LastBorrow < DATE_SUB(CURDATE(), INTERVAL 3 YEAR)
OR LastBorrow IS NULL;
DELETE FROM Students
WHERE StudentID IN (
    SELECT StudentID FROM (
        SELECT s.StudentID
        FROM Students s
        LEFT JOIN IssuedBooks ib ON s.StudentID = ib.StudentID
        GROUP BY s.StudentID
        HAVING MAX(ib.IssueDate) < DATE_SUB(CURDATE(), INTERVAL 3 YEAR)
        OR MAX(ib.IssueDate) IS NULL
    ) x
);
SELECT 
    COUNT(DISTINCT StudentID) AS TotalStudents,
    COUNT(DISTINCT BookID) AS TotalBooks,
    COUNT(*) AS TotalTransactions,
    SUM(CASE WHEN ReturnDate IS NULL THEN 1 ELSE 0 END) AS ActiveLoans,
    SUM(CASE 
        WHEN ReturnDate IS NULL AND DATEDIFF(CURDATE(), IssueDate) > 14 
        THEN 1 ELSE 0 END) AS OverdueBooks
FROM IssuedBooks;
SELECT 
    s.FullName,
    COUNT(*) AS BorrowCount,
    DENSE_RANK() OVER (ORDER BY COUNT(*) DESC) AS RankPosition
FROM IssuedBooks ib
JOIN Students s ON s.StudentID = ib.StudentID
GROUP BY s.FullName;