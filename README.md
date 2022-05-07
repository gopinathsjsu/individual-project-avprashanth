# Information to know - 
1) This is a java application built using Gradle 
2) Install gradle 8.0 in IDE of your choice (IntellIJ is preferred)


# Steps to run the project -
1) Open project in IDE and let the IDE resolve gradle dependencies
2) Build the project to ensure dependencies are resolved
3) If you encounter any errors with related to dependencies, install dependencies that are suggested to be downloaded by the IDE
4) Repeat Step #2
5) Your Main class is Billing.java and run the class
6) You might receive a prompt to setup JDK version for this project. Recommend setting JDK version 1.8 or higher.
7) Repeat Step #5
8) Enter Input file name containing order details (Sample test files have been located in project directory. Please ensure to locate any additional test files in same directory or enter the complete path for Input file to avoid exceptions)
9) On 'Enter', the application validates input and generates response accordingly.
10) Output.csv and Error.txt files will be generated for positive and failed cases respectively. These files will be located in project directory.


# Design Patterns Used - 
1) Builder : 
I have used Builder Design Pattern to convert data from CSV and map to Java Objects. This creational design pattern helped me to construct complex objects step by step. Additionally, Builder pattern allowed me to produce different types and representations of an object using the same construction code. I have used the same object construction code to structure both Input and Output data.

2) Singleton : 
The constraints on this project is to limit users from purchasing items belonging to multiple categories. Singleton creationaly design pattern helped me to construct instance of the category limit only at the start of the application. This limit set on different categories will not be reset but always the updated limit is fetched to user on request of the object instance. 

3) Factory : 
The final creational design pattern I have used is Factory. I have noticed that the categories - Essential/Luxury/Misc all have the same construction code and instead of duplicating the construction code in each of these classes, I have used Factory design pattern to redirect the object creation based on the category type on demand. 

4) Chain of Responsibility : 
Upon validation of input order, I have to either print receipt or generate an error text file. I have identified these responsibilities to be handled by the COR Design Pattern. The responsibility has been handed over to print receipt on a valid input order and to generate a text file if an invalid input order is encountered.  

# Class Diagram - 
<img width="1221" alt="ClassDiagram" src="https://user-images.githubusercontent.com/75163512/167275752-0b8d0638-db31-4db4-b09d-6f48d863d47c.png">
