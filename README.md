Overview
The Hospital Management System (HMS) is a Java-based application designed to streamline and manage the operations of a hospital. This system uses JDBC for database connectivity and features three primary classes: Hospital, Doctor, and Patient. The HMS allows users to manage hospital records, doctor information, and patient details efficiently.

Technology Stack
Java: The programming language used to build the application.
JDBC: Java Database Connectivity, used for connecting to and interacting with the database.//ORACLE is used here
Prerequisites
To run this application, ensure you have the following installed:

Java Development Kit (JDK): Version 8 or later.
Database Management System (DBMS): Any DBMS compatible with JDBC (e.g., MySQL, PostgreSQL).
JDBC Driver: Corresponding to your DBMS, for example, mysql-connector-java for MySQL.
Project Structure
The project contains the following classes:
Hospital
Doctor
Patient

_Class Descriptions_
**Hospital**
Key Methods:
connectToDatabase(): Establishes a connection to the database.
bookAppointment()
checkDoctorAvailability()
**Doctor**
Key Methods:
addDoctors() {name,ID,Specialization}
viewDoctors() {ID,Name,Specializatuon}
**Patient**
Key Methods:
addPatient() {name,age,gender}
viewPatient(){patientID,name}
