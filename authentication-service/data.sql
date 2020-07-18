

-- -- -----------------------------------------------------
-- -- Data for table `fms`.`user`
-- -- -----------------------------------------------------
-- START TRANSACTION;
-- USE `fms`;
-- INSERT INTO user VALUES (1,'602365','Admin','Admin','$2a$10$R/lZJuT9skteNmAku9Y7aeutxbOKstD5xE5bHOf74M2PHZipyt3yK');
-- INSERT INTO user VALUES (2,'602366','Pmo','Pmo','$2a$10$R/lZJuT9skteNmAku9Y7aeutxbOKstD5xE5bHOf74M2PHZipyt3yK');
-- INSERT INTO user VALUES (3,'602367','Poc','Poc','$2a$10$R/lZJuT9skteNmAku9Y7aeutxbOKstD5xE5bHOf74M2PHZipyt3yK');
-- INSERT INTO user VALUES (4,'602368','User','User','$2a$10$R/lZJuT9skteNmAku9Y7aeutxbOKstD5xE5bHOf74M2PHZipyt3yK');

-- COMMIT;




-- -- -----------------------------------------------------
-- -- Data for table `fms`.`role`
-- -- -----------------------------------------------------
-- START TRANSACTION;
-- USE `fms`;
-- INSERT INTO role VALUES (1,'ADMIN');
-- INSERT INTO role VALUES (2,'PMO');
-- INSERT INTO role VALUES (3,'POC');
-- INSERT INTO role VALUES (4,'USER');
-- COMMIT;
-- -- -----------------------------------------------------
-- -- Data for table `fms`.`user_role`
-- -- -----------------------------------------------------
-- START TRANSACTION;
-- USE `fms`;
-- INSERT INTO user_role VALUES (1,1,1);
-- INSERT INTO user_role VALUES (2,2,2);
-- INSERT INTO user_role VALUES (3,3,3);
-- INSERT INTO user_role VALUES (4,4,4);

-- COMMIT;

-- -----------------------------------------------------
-- Data for table `fms`.`participated'
-- -----------------------------------------------------
START TRANSACTION;
USE `fms`;
INSERT INTO participated VALUES (1,'5','Good','boring games','EVNT00047261');
INSERT INTO participated VALUES (2,'3','Average','boring games','EVNT00047261');
INSERT INTO participated VALUES (3,'2','Excellent','Prizes are not good','EVNT00047261');

COMMIT;


-- -----------------------------------------------------
-- Data for table `fms`.`non_participated'
-- -----------------------------------------------------
START TRANSACTION;
USE `fms`;
INSERT INTO non_participated VALUES (1,'Incorrectly Registered','EVNT00047261');
INSERT INTO non_participated VALUES (2,'unexpected Official Work','EVNT00047261');

COMMIT;

-- -----------------------------------------------------
-- Data for table `fms`.`un-reg'
-- -----------------------------------------------------
START TRANSACTION;
USE `fms`;
INSERT INTO un_reg VALUES (1,'Incorrectly Registered','EVNT00047261');
INSERT INTO un_reg VALUES (2,'unexpected Official Work','EVNT00047261');

COMMIT;


-- -----------------------------------------------------
-- Data for table `fms`.`questions'
-- -----------------------------------------------------
START TRANSACTION;
USE `fms`;
INSERT INTO questions VALUES (1,'How do you rate the overall event?','participated');
INSERT INTO questions VALUES (2,'What did you like about the volunteering activity?','participated');
INSERT INTO questions VALUES (3,'What can be improved in this volunteering activity?','participated');
INSERT INTO questions VALUES (4,'Hey there, You had registered for the event on saturday. We would like to know the reason for not joing the event to understand if the team which created the event has some room for improvement in their process, so that we get 100% participation from the registered attendees','notparticipated');
INSERT INTO questions VALUES (5,'Hey there, Please share your feedback unregistering from the event ','unregistered');
COMMIT;

-- -----------------------------------------------------
-- Data for table `fms`.`answers'
-- -----------------------------------------------------
START TRANSACTION;
USE `fms`;
INSERT INTO answers VALUES (1,'1',1);
INSERT INTO answers VALUES (2,'2',1);
INSERT INTO answers VALUES (3,'3',1);
INSERT INTO answers VALUES (4,'4',1);
INSERT INTO answers VALUES (5,'5',1);
INSERT INTO answers VALUES (6,'Do Not Wish to Disclose',4);
INSERT INTO answers VALUES (7,'Unexpected Personal Committment',4);
INSERT INTO answers VALUES (8,'Unexpected Official Work',4);
INSERT INTO answers VALUES (9,'Even Not What I Expected',4);
INSERT INTO answers VALUES (10,'Did Not Receive Further Information About The Event',4);
INSERT INTO answers VALUES (11,'Incorrectly Registered',4);
INSERT INTO answers VALUES (12,'Do Not Wish to Disclose',5);
INSERT INTO answers VALUES (13,'Unexpected Personal Committment',5);
INSERT INTO answers VALUES (14,'Unexpected Official Work',5);
INSERT INTO answers VALUES (15,'Even Not What I Expected',5);
INSERT INTO answers VALUES (16,'Did Not Receive Further Information About The Event',5);
INSERT INTO answers VALUES (17,'Incorrectly Registered',5);
INSERT INTO answers VALUES (18,'',2);
INSERT INTO answers VALUES (19,'',3);

COMMIT;