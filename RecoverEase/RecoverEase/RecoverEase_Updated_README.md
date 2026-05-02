# RecoverEase — Lost and Found Item Reporting System

---

# Project Title

## RecoverEase: Lost and Found Item Reporting System

RecoverEase is a Java-based terminal application developed to help users report, search, and recover lost and found items within an institution such as a college campus or office environment.

---

# Team Members and Roles

| Roll Number | Student Name | Role / Contribution |
|---|---|---|
| AM.SC.U4CSE25209 | BYN. Siddharth Sai | Authentication Module, User Management |
| AM.SC.U4CSE25258 | V. Gowtham Adithya | ReportManager, Search & Match Features |
| AM.SC.U4CSE25263 | K. Rama Sri Surya | UML Design, Documentation, System Architecture |
| AM.SC.U4CSE25264 | K. Sai Santhosh | File Handling, ID Generator, Testing & Integration |

---

# Problem Description

RecoverEase is designed to simplify the process of reporting and retrieving lost items within a community.

The system allows users to:

- Register and log in securely
- Report lost items
- Report found items
- Search items using keywords
- View matching lost/found items
- Edit or delete their own posts
- Store records permanently using text files

An admin account called the Institute Head has additional privileges such as:

- Viewing all reports
- Editing or deleting any report
- Managing users
- Posting reports on behalf of the institution

The application follows Object-Oriented Programming principles and is organized into multiple packages including:

- `model`
- `service`
- `util`
- `ui`

---

# Tools and Technologies Used

| Technology | Purpose |
|---|---|
| Java | Core Programming Language |
| OOP Concepts | Software Design |
| UML | System Modelling |
| File Handling | Data Persistence |
| VS Code / IntelliJ IDEA | Development Environment |
| Git & GitHub | Version Control |

---

# Project Structure

```text
src/
│
├── model/
│   ├── Item.java
│   ├── LostItem.java
│   ├── FoundItem.java
│   ├── User.java
│   └── Role.java
│
├── service/
│   ├── AuthService.java
│   └── ReportManager.java
│
├── util/
│   ├── FileHandler.java
│   └── IDGenerator.java
│
├── ui/
│   ├── UserMenu.java
│   ├── AdminMenu.java
│   └── Main.java
```

---

# How to Run the Code

## Step 1: Clone or Download the Project

```bash
git clone <repository-link>
```

OR download the ZIP file and extract it.

---

## Step 2: Open the Project

Open the project in:

- VS Code
- IntelliJ IDEA
- Eclipse

---

## Step 3: Compile the Java Files

```bash
javac Main.java
```

---

## Step 4: Run the Application

```bash
java Main
```

---

# Sample Input / Output

## Sample Input

```text
1. Register
2. Login

Enter Choice: 1

Enter Name: Surya
Enter Email: surya@gmail.com
Enter Password: 1234
```

---

## Sample Output

```text
Registration Successful!

1. Report Lost Item
2. Report Found Item
3. Search Items
4. Logout

Enter Choice: 1

Enter Item Title: Wallet
Enter Description: Black leather wallet
Enter Location: Library
Enter Date: 05-05-2026

Lost Item Posted Successfully!
```

---

# Features

## User Features
- Register/Login
- Report Lost Items
- Report Found Items
- Search Items
- Match Suggestions
- Edit/Delete Own Posts

## Admin Features
- Manage Users
- View All Reports
- Edit/Delete Any Item
- Institutional Posting

---

# OOP Concepts Used

- Abstraction
- Encapsulation
- Inheritance
- Polymorphism
- Composition
- Aggregation

---

# UML Class Diagram

The project includes a complete UML Class Diagram containing:

- Classes
- Attributes
- Methods
- Relationships
- Inheritance
- Composition
- Aggregation
- Dependencies

---

# Future Enhancements

- GUI Version using JavaFX
- Database Integration
- Email Notifications
- AI-Based Matching
- Mobile Application

---

# Conclusion

RecoverEase provides an efficient and user-friendly platform for managing lost and found items in an institution. The project demonstrates strong Object-Oriented Programming concepts, modular design, and practical implementation of Java application development.
