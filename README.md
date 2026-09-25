# Athlete Performance Analytics Dashboard

## 1. Project Description

The Athlete Performance Analytics Dashboard is a Java and Oracle
DBMS-based application designed to help coaches and athletes
record, monitor and analyze sports performance.

The system stores training sessions, workload, heart rate,
performance metrics and athlete information.

It converts raw training data into useful analytics such as:

- Training workload
- Acute:Chronic Workload Ratio (ACWR)
- Performance trends
- Personal bests
- Squad comparison
- Athlete workload status

## 2. Problem Statement

Coaches often maintain athlete training information manually
using notebooks or spreadsheets.

This makes it difficult to:

- Track athlete performance over time
- Identify workload changes
- Compare athletes
- Identify performance trends
- Maintain centralized records

The proposed system provides a centralized database and Java
application for storing and analyzing athlete performance data.

## 3. Existing System

The existing system commonly relies on:

- Paper records
- Excel spreadsheets
- Separate applications
- Manual calculations

Limitations:

- Data duplication
- Manual calculations
- Difficult comparison
- Limited security
- No centralized database
- Difficult historical analysis

## 4. Proposed System

The proposed system combines:

- Java
- JDBC
- Oracle Database
- SQL
- PL/SQL

The Java application communicates with Oracle through JDBC.

## 5. Main Features

### Admin
- Manage athletes
- Manage squads
- Manage metrics
- Manage users

### Coach
- Add training sessions
- View athlete performance
- Calculate workload
- View ACWR
- Compare athletes

### Athlete
- View personal performance
- View personal bests
- View workload
- View performance trends

## 6. Technologies Used

- Java
- JDBC
- Oracle Database
- SQL
- PL/SQL
- VS Code
- GitHub

## 7. Database Features

The project demonstrates:

- Primary keys
- Foreign keys
- Constraints
- Normalization
- Views
- Functions
- Stored procedures
- Triggers
- Transactions
- ACID properties

## 8. Analytics

Workload:

Workload = Duration × RPE

ACWR:

ACWR = Acute Workload / Chronic Workload

Acute workload is calculated from recent training data,
while chronic workload is based on a longer reference period.

## 9. Project Architecture

User
 ↓
Java Application
 ↓
JDBC
 ↓
Oracle Database
 ↓
SQL / PL-SQL
 ↓
Analytics
 ↓
Java Dashboard

## 10. How to Run

1. Create the Oracle database/schema.
2. Execute the SQL files in the database folder.
3. Add ojdbc11.jar to java-app/lib.
4. Configure database credentials.
5. Compile the Java program.
6. Run Main.java.
