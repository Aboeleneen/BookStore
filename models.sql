CREATE DATABASE bookstore;

use bookstore;

/*  Category Model */
CREATE TABLE category(
	id INTEGER PRIMARY KEY,
    name VARCHAR(20) UNIQUE NOT NULL,
    description TEXT
);

/* Publisher Model */
CREATE TABLE publisher(
	id INTEGER PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    address VARCHAR(30),
    phone_number VARCHAR(15)
);
/* author model*/
CREATE TABLE author(
	id INTEGER PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    address VARCHAR(30),
    phone_number VARCHAR(15),
    bio TEXT
);

/* Book Model */
CREATE TABLE book(
	ISBN INTEGER PRIMARY KEY,
    title VARCHAR(30) UNIQUE NOT NULL,
    publisher_id INTEGER,
    publication_year year,
    selling_price double,
    category_id integer,
    minimum_quantity INTEGER,
    FOREIGN KEY(category_id) REFERENCES category(id) 
				ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY(publisher_id) REFERENCES publisher(id) 
				ON UPDATE CASCADE ON DELETE SET NULL
);

/* book_author model (association table)*/
CREATE TABLE book_author(
	book_num INTEGER,
    author_id INTEGER,
    PRIMARY KEY(book_num,author_id),
    FOREIGN KEY(book_num) REFERENCES book(ISBN) 
				ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(author_id) REFERENCES author(id) 
				ON UPDATE CASCADE ON DELETE CASCADE
);

/* book_orders Model*/
CREATE TABLE book_orders(
	id INTEGER PRIMARY KEY,
    book_num INTEGER,
    quantity INTEGER,
    FOREIGN KEY(book_num) REFERENCES book(ISBN) 
				ON UPDATE CASCADE ON DELETE CASCADE
);


/*  Customer Model*/
CREATE TABLE user(
	user_name VARCHAR(15) PRIMARY KEY,
    password VARCHAR(20) NOT NULL,
    last_name VARCHAR(15) NOT NULL,
    first_name VARCHAR(15) NOT NULL,
    email_address VARCHAR(30) UNIQUE NOT NULL,
    phone_number VARCHAR(15),
    shipping_address VARCHAR(30),
    is_manager BOOLEAN NOT NULL
);

 
 /* shopping cart items Model */
 CREATE TABLE shopping_cart_books(
	book_num INTEGER NOT NULL,
    customer_user_name VARCHAR(15) NOT NULL,
    PRIMARY KEY(book_num, customer_user_name),
    FOREIGN KEY(book_num) REFERENCES book(ISBN) 
				ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY(customer_user_name) REFERENCES user(user_name) 
				ON UPDATE CASCADE ON DELETE CASCADE
 );
 
 /* credit card model*/
 CREATE TABLE credit_card(
	number INTEGER PRIMARY KEY,
    expiry_date DATE NOT NULL,
    customer_user_name VARCHAR(15) NOT NULL,
    FOREIGN KEY(customer_user_name) REFERENCES user(user_name) 
				ON UPDATE CASCADE ON DELETE CASCADE
 );



