/*
* ADD NEW BOOK
*/

USE `bookstore`;
DROP procedure IF EXISTS `addNewBook`;

DELIMITER $$
USE `bookstore`$$
CREATE PROCEDURE `addNewBook`(userName varchar(15), password varchar(20),
							ISBN INTEGER ,title VARCHAR(30) ,
							publisher_id INTEGER,publication_year year,
                            selling_price double,category_id integer,
                            minimum_quantity INTEGER , quantity integer)
BEGIN
	IF ( SELECT isManager(userName, password) from user where user_name = userName ) = TRUE THEN
			INSERT INTO BOOK 
            VALUES( ISBN,title,publisher_id,publication_year,
					selling_price ,category_id,minimum_quantity ,quantity);
	END IF;
END$$

DELIMITER ;



/*
* ADD NEW Publisher
*/

USE `bookstore`;
DROP procedure IF EXISTS `addNewPublisher`;

DELIMITER $$
USE `bookstore`$$
CREATE PROCEDURE addNewPublisher (userName varchar(15), password varchar(20),
								  id INTEGER ,name VARCHAR(30) ,address VARCHAR(30),
                                  phone_number VARCHAR(15))
BEGIN
	IF ( SELECT isManager(userName, password) from user where user_name = userName ) = TRUE THEN
		INSERT INTO publisher VALUES(id ,name ,address ,phone_number );
	END IF;
END$$

DELIMITER ;

/*
* Modify existing book
*/
USE `bookstore`;
DROP procedure IF EXISTS `modifyBook`;

DELIMITER $$
USE `bookstore`$$
CREATE PROCEDURE modifyBook (userName varchar(15), password varchar(20),
							book_ISBN INTEGER ,new_title VARCHAR(30) ,
							new_publisher_id INTEGER,new_publication_year year,
                            new_selling_price double,new_category_id integer,
                            new_minimum_quantity INTEGER , new_quantity integer)
BEGIN
	IF ( SELECT isManager(userName, password) from user where user_name = userName ) = TRUE THEN
		IF(book_ISBN in (SELECT ISBN from book))THEN
		   UPDATE book SET title =  new_title,
							publisher_id =new_publisher_id,publication_year = new_publication_year,
                            selling_price = new_selling_price,category_id =new_category_id,
                            minimum_quantity =new_minimum_quantity , current_quantity =new_quantity
						WHERE isbn = book_ISBN;
        END IF;
		   
	END IF;
END$$

DELIMITER ;

/*
* Add new Category
*/

USE `bookstore`;
DROP procedure IF EXISTS `addNewCategory`;

DELIMITER $$
USE `bookstore`$$
CREATE PROCEDURE addNewCategory (userName varchar(15), password varchar(20),
								c_name VARCHAR(20),description TEXT)
BEGIN
	IF ( SELECT isManager(userName, password) from user where user_name = userName ) = TRUE THEN
		INSERT INTO category (name,description) VALUES(c_name, description);
	END IF;
END$$

DELIMITER ;


/*
* Place orders for books
*/

USE `bookstore`;
DROP procedure IF EXISTS `orderBooks`;

DELIMITER $$
USE `bookstore`$$
CREATE PROCEDURE orderBooks (userName varchar(15), password varchar(20),
							book_ISBN INTEGER, order_quantity INTEGER)
BEGIN
	IF ( SELECT isManager(userName, password) from user where user_name = userName ) = TRUE THEN
		IF(book_ISBN in (SELECT ISBN from book))THEN
		   INSERT INTO book_orders (book_num,quantity) VALUES(book_ISBN, order_quantity);
        END IF;
	END IF;
END$$

DELIMITER ;


/*
*  Confirm orders 
*/


USE `bookstore`;
DROP procedure IF EXISTS `confirm_order`;

DELIMITER $$
USE `bookstore`$$
CREATE PROCEDURE confirm_order (userName varchar(15), password varchar(20),
								order_id INTEGER)
BEGIN
	IF ( SELECT isManager(userName, password) from user where user_name = userName ) = TRUE THEN
		   DELETE FROM book_orders WHERE id = order_id;
	END IF;
END$$

DELIMITER ;

/*
* Promote registered customers to have managers credentials
*/

USE `bookstore`;
DROP procedure IF EXISTS `makeManager`;

DELIMITER $$
USE `bookstore`$$
CREATE PROCEDURE makeManager (manager_user_name varchar(15), manager_password varchar(20),
							new_manager_user_name varchar(15))
BEGIN
	IF ( SELECT isManager(manager_user_name, manager_password) from user where user_name = manager_user_name ) = TRUE THEN
		IF new_manager_user_name IN (SELECT user_name FROM user) THEN
			update user set is_manager = true where user_name = new_manager_user_name;
        END IF;
	END IF;
END$$

DELIMITER ;


/*
****************************** Reports *************************************************
*/

/*
* The total sales for books in the previous month
*/

USE `bookstore`;
DROP procedure IF EXISTS `totalBooksSales`;

DELIMITER $$
USE `bookstore`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `totalBooksSales`(userName varchar(15), password varchar(20))
BEGIN
	IF ( SELECT isManager(userName, password) from user where user_name = userName ) = TRUE THEN
			SELECT book_isbn, sum(num_copies) number_sales 
                FROM sales 
                WHERE sale_time 
						BETWEEN DATE_FORMAT(NOW() - INTERVAL 1 MONTH, '%Y-%m-01 00:00:00')
						AND DATE_FORMAT(LAST_DAY(NOW() - INTERVAL 1 MONTH), '%Y-%m-%d 23:59:59')
				GROUP BY book_isbn;
	END IF;
END$$

DELIMITER ;

/*
* The top 5 customers who purchase the most purchase amount in descending order for the last three months
*/

USE `bookstore`;
DROP procedure IF EXISTS `topPurchaceCustomers`;

DELIMITER $$
USE `bookstore`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `topPurchaceCustomers`(userName varchar(15), password varchar(20))
BEGIN
	IF ( SELECT isManager(userName, password) from user where user_name = userName ) = TRUE THEN
		SELECT customer_user_name, sum(sale_price) purchace_amount
        FROM sales 
        WHERE sale_time 
						BETWEEN DATE_FORMAT(NOW() - INTERVAL 3 MONTH, '%Y-%m-01 00:00:00')
						AND DATE_FORMAT(LAST_DAY(NOW() - INTERVAL 3 MONTH), '%Y-%m-%d 23:59:59')
		GROUP BY customer_user_name 
        order by purchace_amount
		limit 5;
    end if;
END$$

DELIMITER ;

/*
* The top 10 selling books for the last three months
*/

USE `bookstore`;
DROP procedure IF EXISTS `topSellingBooks`;

DELIMITER $$
USE `bookstore`$$
CREATE PROCEDURE topSellingBooks (userName varchar(15), password varchar(20))
BEGIN
	IF ( SELECT isManager(userName, password) from user where user_name = userName ) = TRUE THEN
			SELECT book_isbn, sum(num_copies) number_sales 
			FROM sales 
			WHERE sale_time 
						BETWEEN DATE_FORMAT(NOW() - INTERVAL 3 MONTH, '%Y-%m-01 00:00:00')
						AND DATE_FORMAT(LAST_DAY(NOW() - INTERVAL 3 MONTH), '%Y-%m-%d 23:59:59')
			GROUP BY book_isbn 
            ORDER BY number_sales 
            LIMIT 10;
                
	END IF;
END$$

DELIMITER ;