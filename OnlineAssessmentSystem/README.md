# Online Quiz and Assessment System

## Overview

This project is a console-based quiz application developed in Java. It allows users to take quizzes, evaluate their performance, and manage questions through an admin panel. The system simulates a simple online assessment environment with features such as time limits, scoring, and result analysis.

---

## Features

- Take quiz with multiple-choice questions  
- Timer-based quiz execution  
- Automatic score calculation  
- Performance summary with percentage and grade  
- Review of incorrect answers  
- Admin panel for managing questions  
- Add and view questions dynamically  
- Questions stored and loaded from a file  
- Adjustable quiz time limit  

---

## System Design

The application is structured into multiple components:

- Question: Represents a quiz question with options and correct answer  
- QuestionBank: Manages storage and retrieval of questions  
- QuizTimer: Tracks time taken during the quiz  
- Result: Handles score calculation, grading, and result display  

---

## Functionality

Users can:

- Start a quiz and answer questions one by one  
- View remaining time while attempting the quiz  
- Receive feedback for each answer  
- Get a detailed result summary at the end  

Administrators can:

- Add new questions  
- View all existing questions  
- Set the quiz time limit  
- Access the system using a password  

---

## How to Run

1. Navigate to the project directory  
2. Compile the Java file:
javac QuizSystem.java  

3. Run the program:
java QuizSystem  

---

## Data Storage

- Questions are stored in a text file  
- Data is saved in a structured format using delimiters  
- The system automatically loads existing questions at startup  

---

## Project Highlights

- Object-oriented design with multiple classes  
- File handling for persistent data storage  
- Input validation for user responses  
- Real-time timer implementation  
- Clear separation of admin and user functionalities  

