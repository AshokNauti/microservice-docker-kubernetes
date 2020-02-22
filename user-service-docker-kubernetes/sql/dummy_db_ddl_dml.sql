/* Ashok Nautiyal: Dummy DML and DDL script */

DROP TABLE IF EXISTS `user_detail`;

CREATE TABLE `user_detail` (
   `id` bigint(20) NOT NULL,
   `email` varchar(255) DEFAULT NULL,
   `first_name` varchar(255) DEFAULT NULL,
   `last_name` varchar(255) DEFAULT NULL,
   `type` varchar(255) DEFAULT NULL,
   `port` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8

INSERT INTO `user_detail` (`id`,`email`,`first_name`,`last_name`,`type`,`port`) VALUES (1,'borrower1@test.com','Borrower-dev','One','BORROWER',NULL);
INSERT INTO `user_detail` (`id`,`email`,`first_name`,`last_name`,`type`,`port`) VALUES (2,'borrower2@test.com','Borrower-dev','Two','BORROWER',NULL);
INSERT INTO `user_detail` (`id`,`email`,`first_name`,`last_name`,`type`,`port`) VALUES (3,'test3@test.com','Third-dev','Surname','FUNDER',NULL);
