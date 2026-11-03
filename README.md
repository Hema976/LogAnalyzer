Log Analyzer – Java Project

Project Overview

Log Analyzer is a Java-based application that reads server log files and detects suspicious login activities. It identifies high-risk IP addresses and generates security alerts.

The application stores login records and security alerts in a MySQL database.

Features

- Reads server log files.
- Extracts username, IP address, date and login status.
- Detects suspicious login activities.
- Identifies high-risk IP addresses.
- Generates security alerts.
- Stores login records in MySQL.
- Stores security alerts in MySQL.

Technologies Used

- Java
- JDBC
- MySQL
- Maven
- Git & GitHub

Project Structure

LogAnalyzer/
│
├── src/
│   └── main/
│       └── java/
│           ├── Main.java
│           ├── LogReader.java
│           ├── LogDatabase.java
│           ├── DatabaseConnection.java
│           ├── AlertDatabase.java
│           └── SecurityAnalyzer.java
│
├── pom.xml
├── schema.sql
├── server.log
├── README.md
└── .gitignore

Database Tables

1. login_logs

Stores login information such as username, IP address, event type and event time.

2. security_alerts

Stores suspicious login activities and security alert details.

How to Run

1. Install Java JDK.
2. Install MySQL.
3. Clone the repository.
4. Create the database using "schema.sql".
5. Configure database connection details.
6. Build the project using Maven.
7. Run the Main.java class.

Maven Commands

mvn clean compile
mvn exec:java -Dexec.mainClass=Main

Project Objective

The main objective of this project is to analyze server logs, identify suspicious login activities and improve basic server security monitoring.

Author

Java Developer | B.Tech CSE