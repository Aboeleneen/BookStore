/*
* signup procedure: used to register new user into the database
*/
USE `bookstore`;
DROP procedure IF EXISTS `signUp`;

DELIMITER $$
USE `bookstore`$$
CREATE PROCEDURE signUp (user_name varchar(15), password varchar(20),
					 last_name varchar(15), first_name varchar(15), email varchar(30), 
					 phone varchar(15),shipping_address varchar(30))
BEGIN
	INSERT INTO user values(user_name, password, last_name, first_name, email, phone,shipping_address,false);
END$$

DELIMITER ;



/*
* login function: used to login existing user into the database
*/
USE `bookstore`;
DROP function IF EXISTS `login`;

DELIMITER $$
USE `bookstore`$$
CREATE FUNCTION login (userName varchar(15), user_password varchar(20))
RETURNS boolean
BEGIN
	IF userName in (select user_name from user) THEN
		IF user_password in (select password from user where user_name = userName) THEN
			RETURN TRUE;
		ELSE 
			RETURN FALSE;
        END IF;
	ELSE 
		RETURN FALSE;
	END IF;
END$$

DELIMITER ;





/*
* Logout of the system
*/

USE `bookstore`;
DROP procedure IF EXISTS `logout`;

DELIMITER $$
USE `bookstore`$$
CREATE PROCEDURE logout (userName varchar(15), password varchar(20))
BEGIN
	IF ( SELECT login(userName, password) from user where user_name = userName ) = TRUE THEN
		DELETE FROM shopping_cart_books 
		WHERE customer_user_name = userName;
	END IF;
END$$

DELIMITER ;


/*
* CHECK IF THE USER HAS MANAGER credentials
*/
USE `bookstore`;
DROP function IF EXISTS `isManager`;

DELIMITER $$
USE `bookstore`$$
CREATE FUNCTION isManager (userName varchar(15), password varchar(20))
RETURNS boolean
BEGIN
	IF ( SELECT login(userName, password) from user where user_name = userName ) = TRUE THEN
		IF((SELECT is_manager from user where user_name = userName) = true) THEN
			return true;
		END IF;
	END IF;
	RETURN FALSE;
END$$

DELIMITER ;




