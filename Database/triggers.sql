/*
*
*/
USE `bookstore`;
DROP TRIGGER IF EXISTS `check_book_sell`;

DELIMITER $$
USE `bookstore`$$
CREATE TRIGGER check_book_sell AFTER UPDATE ON book FOR EACH ROW BEGIN 
    IF NEW.current_quantity<=OLD.minimum_quantity then 
    insert into book_orders(book_num,quantity) values(NEW.isbn,2*OLD.minimum_quantity);
 END if;
 END$$

DELIMITER ;
 
 
 /*
*
*/
USE `bookstore`;
DROP TRIGGER IF EXISTS `check_book`;

DELIMITER $$
USE `bookstore`$$
CREATE  TRIGGER check_book BEFORE UPDATE ON book FOR EACH ROW BEGIN 
    IF NEW.minimum_quantity<0 then SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "COPIES CAN NOT BE LESS THAN 0" ; 
 END if;
 END $$
DELIMITER ;

 /*
*  
*/
USE `bookstore`;
DROP TRIGGER IF EXISTS `confirm_order`;

DELIMITER $$
USE `bookstore`$$
 CREATE TRIGGER confirm_order BEFORE DELETE ON  book_orders FOR EACH ROW BEGIN 
    update book set current_quantity = current_quantity + old.quantity where isbn = old.book_num;
 END $$ 
 DELIMITER ;