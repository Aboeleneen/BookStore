# Book Store System 
  Design a relational database using MYSQL for a book store and implement a desktop application using Java to simulate it.

## Main requirements :
  - Analyze, design and implement a database system to support the operations of a simplified online bookstore. 
  - Create the necessary forms to perform these operations. The data and functional requirements of the system are as follows.

## Code Structure :
  - There are two main modules:
     - Database Modul:
        - Using Mysql DBMS to create the database.
        - Creates the models of the system.
        - Contains the procedures that provide interaction with the bookstore database.
    - FrontEnd Modul:
       - Follow the MVC pattern and contains three folders:
            - View: represent the user interface of the system.
            - Model: provides interaction with the Mysql Server.
            - Controller: manages the data flow between the view and the model

## Features :
  - **Customer :**
    - signUp
    - login
    - Logout
    - Edit User Info
    - get Books By Category
    - search For books
    - add Book to shoppingCart
    - view cart items
    - remove item from cart
  - **Manager :**
    - addNewBook
    - modifyBook
    - Place orders for books
    - confirm orders for books
    - Promote registered customers to have managers credentials.
    - View The total sales for books in the previous month
    - View The top 5 customers who purchase the most purchase amount in descending order for the last three months.
    - View The top 10 selling books for the last three months.
