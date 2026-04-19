# Smart Expense Tracker

A simple and practical command-line based expense tracker built using Python.  
This application helps users track daily expenses, analyze spending habits, and visualize financial data through charts.

---

## Features

- Add daily expenses with category, amount, and description  
- View all recorded expenses in a structured tabular format  
- Generate monthly summaries with category-wise breakdown  
- Visualize expenses using pie charts  
- Get smart suggestions based on highest spending category  
- Delete the last entered record for quick corrections  
- Store data locally using a CSV file (no database required)

---

## Tech Stack

- Python  
- CSV (for data storage)  
- Matplotlib (for visualization)  
- Datetime and OS modules  

---

## Project Structure

SmartExpenseTracker/
│── ExpenseTracker.py
│── expenses.csv
│── expensetracker1.png
│── expensetracker2.png
│── expensetracker3.png
│── expensetracker4.png
│── expense_chart_*.png

---

## How to Run

1. Clone the repository:
git clone https://github.com/Sainikhil070304/Projects-Virtusa.git  
cd Projects-Virtusa/SmartExpenseTracker  

2. Install required dependency:
pip install matplotlib  

3. Run the program:
python ExpenseTracker.py  

---

## Sample Workflow

- Add an expense by entering date, category, amount, and description  
- View all stored expenses  
- Generate a monthly summary  
- View category-wise expense distribution through a chart  

---

## How It Works

- Expenses are stored in a CSV file  
- The program reads and processes data dynamically  
- Monthly summaries group expenses by category  
- A pie chart is generated to visualize spending distribution  
- Suggestions are provided based on the category with the highest spending  

---

## Categories

- Food  
- Travel  
- Bills  
- Shopping  
- Health  
- Entertainment  
- Other  

---

## Future Improvements

- Graphical User Interface (GUI) version  
- Web-based dashboard  
- Expense prediction using machine learning  
- Export reports in PDF or Excel format  
- User login and authentication system  

