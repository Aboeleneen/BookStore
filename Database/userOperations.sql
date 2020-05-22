/*
	Edit user information
*/

USE `bookstore`;
DROP procedure IF EXISTS `editUserInfo`;

DELIMITER $$
USE `bookstore`$$
CREATE PROCEDURE `editUserInfo`(userName varchar(15), password varchar(20),
					 new_user_name varchar(15), new_password varchar(20),
					 new_last_name varchar(15), new_first_name varchar(15), new_email varchar(30), 
					 new_phone varchar(15),new_shipping_address varchar(30))
BEGIN
	IF (SELECT login(userName, password) from user where user_name = userName) = TRUE THEN
			UPDATE user
			SET user_name = new_user_name,
				password = new_password, 
				last_name = new_last_name,
				first_name = new_first_name,
				email_address = new_email,
                phone_number = new_phone,
				shipping_address = new_shipping_address
			WHERE user_name = userName;
	END IF;
END$$

DELIMITER ;


/*
* get all books in specific category
*/

USE `bookstore`;
DROP procedure IF EXISTS `getBooksByCategory`;

DELIMITER $$
USE `bookstore`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getBooksByCategory`(userName varchar(15), password varchar(20), cat_name VARCHAR(20))
BEGIN
	IF ( SELECT login(userName, password) from user where user_name = userName ) = TRUE THEN
		 select *from book where category_id = (select id from category where name=cat_name);
    END IF;
END$$

DELIMITER ;

/*
* get Book By ISBN
*/

USE `bookstore`;
DROP procedure IF EXISTS `getBookByISBN`;

DELIMITER $$
USE `bookstore`$$
CREATE PROCEDURE getBookByISBN (userName varchar(15), password varchar(20), isb int)
BEGIN
	IF ( SELECT login(userName, password) from user where user_name = userName ) = TRUE THEN
		 select *from book where ISBN=isb;
    END IF;
END$$

DELIMITER ;

/*
* search for books by title
*/
USE `bookstore`;
DROP procedure IF EXISTS `searchForbooks`;

DELIMITER $$
USE `bookstore`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `searchForbooks`(userName varchar(15), password varchar(20), search_term varchar(30))
BEGIN
	IF ( SELECT login(userName, password) from user where user_name = userName ) = TRUE THEN
		 select *from book where title like concat('%' ,search_term ,'%');
    END IF;
END$$

DELIMITER ;


/*
*********************************************  user shopping cart *****************************************************
*/

/*
* user can add books to his shopping cart
*/
USE `bookstore`;
DROP procedure IF EXISTS `addBookToShoppingCart`;

DELIMITER $$
USE `bookstore`$$
CREATE PROCEDURE addBookToShoppingCart (user_name varchar(15), password varchar(20),bookNumber INTEGER)
BEGIN
	IF (SELECT login(userName, password) from user where user_name = userName) = TRUE THEN
		IF bookNumber in (select ISBN from book) THEN
			INSERT INTO shopping_cart_books (book_num ,customer_user_name) VALUES (bookNumber, user_name);
		END IF;
	END IF;
END$$

DELIMITER ;



/*
* Registered user can view the items in the cart
*/
USE `bookstore`;
DROP procedure IF EXISTS `viewCartItems`;

DELIMITER $$
USE `bookstore`$$
CREATE PROCEDURE `viewCartItems`(userName varchar(15), password varchar(20))
BEGIN
	IF ( SELECT login(userName, password) from user where user_name = userName ) = TRUE THEN
		SELECT b.* FROM book b 
        JOIN ( 
			SELECT book_num 
            FROM shopping_cart_books  
            WHERE customer_user_name = userName
            ) sc 
		ON B.ISBN = sc.book_num;
	END IF;
END$$

DELIMITER ;

/*
* Remove items from the cart
*/

USE `bookstore`;
DROP procedure IF EXISTS `removeItemFromCart`;

DELIMITER $$
USE `bookstore`$$
CREATE PROCEDURE removeItemFromCart (userName varchar(15), password varchar(20), bookNumber INTEGER)
BEGIN
	IF ( SELECT login(userName, password) from user where user_name = userName ) = TRUE THEN
		IF( bookNumber in (
				SELECT book_num FROM shopping_cart_books 
                WHERE customer_user_name = userName)
			) THEN
				DELETE FROM shopping_cart_books 
                WHERE customer_user_name = userName AND book_num = bookNumber;
			END IF;
	END IF;
END$$

DELIMITER ;


/*
* view Book Cart Price
*/
USE `bookstore`;
DROP procedure IF EXISTS `viewBookCartPrice`;

DELIMITER $$
USE `bookstore`$$
CREATE PROCEDURE viewBookCartPrice (userName varchar(15), password varchar(20), bookNumber INTEGER)
BEGIN
	IF ( SELECT login(userName, password) from user where user_name = userName ) = TRUE THEN
		IF( bookNumber in (
				SELECT book_num FROM shopping_cart_books 
                WHERE customer_user_name = userName)
			) THEN
				SELECT selling_price 
                FROM book 
                WHERE ISBN = bookNumber;
			END IF;
	END IF;
END$$

DELIMITER ;

/*
* view Cart Price
*/

USE `bookstore`;
DROP procedure IF EXISTS `viewCartPrice`;

DELIMITER $$
USE `bookstore`$$
CREATE PROCEDURE viewCartPrice (userName varchar(15), password varchar(20))
BEGIN
	IF ( SELECT login(userName, password) from user where user_name = userName ) = TRUE THEN
			SELECT SUM(b.selling_price) total_price 
            FROM book b 
            JOIN (
				SELECT book_num FROM shopping_cart_books 
                WHERE customer_user_name = userName
            ) s 
            ON s.book_num = b.ISBN 
            GROUP BY b.ISBN;
	END IF;
END$$

DELIMITER ;


/*
* add Credit Card
*/
USE `bookstore`;
DROP procedure IF EXISTS `addCreditCard`;

DELIMITER $$
USE `bookstore`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addCreditCard`(userName varchar(15), password varchar(20),
								credit_num CHAR(16),owner_name VARCHAR(30),
                                CSV CHAR(3),expiry_date DATE)
BEGIN
	IF ( SELECT login(userName, password) from user where user_name = userName ) = TRUE THEN
		IF length(credit_num) = 16 AND length(CSV) = 3 THEN
			INSERT INTO credit_card VALUES(credit_num,owner_name,CSV,expiry_date,userName);
		END IF;
	END IF;
END$$

DELIMITER ;


/*
* customer purchase
*/










