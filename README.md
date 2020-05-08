# online-bookstore
online store that has three types of users (ordinary user , manager , publisher).

# procedures
### getBooksByPublisher
take id of publisher as parameter and return the books that this publisher publish.
### getBooksByCategory
take id of Category as parameter and return the books that belongs to that category.
### getBooksByAuthor
take id of Author as parameter and return the books that belongs to that author.
### getBookBytitle
take title of book as parameter and return the book that has that title.
### getBookByISBN
take isbn of book as parameter and return the book that has that isbn.
### confirm_order
that function take an id of order and remove it from order table as it is confirmed by publisher.
### new_book
create new book for a known publisher.
##### parameters 
ISBN INTEGER 
title VARCHAR(30)
publisher_id INTEGER
publication_year year
selling_price double
category_id integer
minimum_quantity INTEGER
quantity integer

### new_publisher
create new publisher .

##### parameters
id INTEGER 
name VARCHAR(30)
address VARCHAR(30)
phone_number VARCHAR(15)

### update_copies
take the id of the book and quantity to be sold and update all the required tables.
