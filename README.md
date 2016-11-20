# Stock-Application

Super Simple Stocks

Requirements

1. Provide working source code that will :-

a. For a given stock,

i. calculate the dividend yield

ii. calculate the P/E Ratio

iii. record a trade, with timestamp, quantity of shares, buy or sell indicator

and price

iv. Calculate Stock Price based on trades recorded in past 15 minutes

b. Calculate the GBCE All Share Index using the geometric mean of prices for all stocks

Constraints &amp; Notes

1. Written in one of these languages:

 Java, C#, C++, Python

2. No database or GUI is required, all data need only be held in memory

3. Formulas and data provided are simplified representations for the purpose of this exercise



Implementation:
This is a Spring Boot Simple Stock Application. This can be a simple microservice from a big platform :)
Used technologies: 
1. Java 8
2. Spring
3. JUnit 4
4. Mockito Framework

The Application use Spring stereotypes as a mechanism for encapsulating storage, retrieval, and search behavior which emulates a collection of objects
There is StockDB.java which is used to simulate Key->Value Database. It stores into memory Stock and Trade objects. The class is annotated with @Component, so when we start the app there is data already
There is @Repository(StockRepository.java) which is used for queries to the database and @Service(StockService.java) annotated class which is used for the implementation of the stock business logic.

To start the application are:
1. Clone the repository
2. Import existing Maven project into Eclipse
3. Maven Update
4. Maven clean install
5. Run java application - StockApplication.java
