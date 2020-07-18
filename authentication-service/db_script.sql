-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema fms
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema fms
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fms` DEFAULT CHARACTER SET utf8 ;
USE `fms` ;

-- -----------------------------------------------------
-- Table `fms`.`questions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fms`.`questions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(1000) NULL DEFAULT NULL,
  `fb_type` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fms`.`answers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fms`.`answers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `answers` VARCHAR(400) NULL DEFAULT NULL,
  `questions_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_answers_questions1_idx` (`questions_id` ASC),
  CONSTRAINT `fk_answers_questions1`
    FOREIGN KEY (`questions_id`)
    REFERENCES `fms`.`questions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fms`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fms`.`event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `event_id` VARCHAR(45) NOT NULL,
  `month` VARCHAR(45) NULL DEFAULT NULL,
  `base_location` VARCHAR(45) NULL DEFAULT NULL,
  `beneficiary_name` VARCHAR(80) NULL DEFAULT NULL,
  `venture_address` VARCHAR(500) NULL DEFAULT NULL,
  `council_name` VARCHAR(45) NULL DEFAULT NULL,
  `project` VARCHAR(45) NULL DEFAULT NULL,
  `category` VARCHAR(45) NULL DEFAULT NULL,
  `event_name` VARCHAR(500) NULL DEFAULT NULL,
  `event_description` TEXT NULL DEFAULT NULL,
  `event_date` DATE NULL DEFAULT NULL,
  `total_no_of_volunteers` INT NULL DEFAULT NULL,
  `total_volunteer_hours` INT NULL DEFAULT NULL,
  `total_travel_hours` INT NULL DEFAULT NULL,
  `overall_volunteer_hours` INT NULL DEFAULT NULL,
  `lives_impacted` INT NULL DEFAULT NULL,
  `activity_type` VARCHAR(45) NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  `poc_id` INT NULL DEFAULT NULL,
  `poc_name` VARCHAR(80) NULL DEFAULT NULL,
  `poc_contact` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`event_id`),
   UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `event_id_UNIQUE` (`event_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `fms`.`event_report`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fms`.`event_report` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `event_id` VARCHAR(45) NULL DEFAULT NULL,
  `base_location` VARCHAR(45) NULL DEFAULT NULL,
  `beneficiary_name` VARCHAR(80) NULL DEFAULT NULL,
  `council_name` VARCHAR(45) NULL DEFAULT NULL,
  `event_name` VARCHAR(500) NULL DEFAULT NULL,
  `event_description` TEXT NULL DEFAULT NULL,
  `event_date` DATE NULL DEFAULT NULL,
  `employee_id` INT NOT NULL,
  `employee_name` VARCHAR(60) NULL DEFAULT NULL,
  `volunteer_hours` INT NULL DEFAULT NULL,
  `travel_hours` INT NULL DEFAULT NULL,
  `lives_impacted` INT NULL DEFAULT NULL,
  `buisness_unit` VARCHAR(45) NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  `iiep_category` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 55
DEFAULT CHARACTER SET = latin1;


-- -- -----------------------------------------------------
-- -- Table `fms`.`role`
-- -- -----------------------------------------------------
-- CREATE TABLE IF NOT EXISTS `fms`.`role` (
--   `ro_id` INT NOT NULL AUTO_INCREMENT,
--   `ro_name` VARCHAR(60) NULL DEFAULT NULL,
--   PRIMARY KEY (`ro_id`))
-- ENGINE = InnoDB
-- AUTO_INCREMENT = 3
-- DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fms`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fms`.`user` (
  `us_id` INT NOT NULL AUTO_INCREMENT,
  `us_emp_id` INT NOT NULL,
  `us_fs_name` VARCHAR(45) NULL DEFAULT NULL,
  `us_ls_name` VARCHAR(45) NULL DEFAULT NULL,
  `us_password` VARCHAR(60) NULL DEFAULT NULL,
  `role` VARCHAR(60) NULL DEFAULT NULL,
  PRIMARY KEY (`us_id`),
  UNIQUE INDEX `us_emp_id_UNIQUE` (`us_emp_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -- -----------------------------------------------------
-- -- Table `fms`.`user_role`
-- -- -----------------------------------------------------
-- CREATE TABLE IF NOT EXISTS `fms`.`user_role` (
--   `ur_id` INT NOT NULL AUTO_INCREMENT,
--   `role_ro_id` INT NOT NULL,
--   `user_us_id` INT NOT NULL,
--   PRIMARY KEY (`ur_id`),
--   INDEX `fk_user_role_role1_idx` (`role_ro_id` ASC),
--   INDEX `fk_user_role_user1_idx` (`user_us_id` ASC),
--   CONSTRAINT `fk_user_role_role1`
--     FOREIGN KEY (`role_ro_id`)
--     REFERENCES `fms`.`role` (`ro_id`),
--   CONSTRAINT `fk_user_role_user1`
--     FOREIGN KEY (`user_us_id`)
--     REFERENCES `fms`.`user` (`us_id`)
--     ON DELETE NO ACTION
--     ON UPDATE NO ACTION)
-- ENGINE = InnoDB
-- AUTO_INCREMENT = 5
-- DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `fms`.`volunteer_reg`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fms`.`volunteer_reg` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `event_id` VARCHAR(45) NULL DEFAULT NULL,
  `event_name` VARCHAR(60) NULL DEFAULT NULL,
  `beneficiary_name` VARCHAR(80) NULL DEFAULT NULL,
  `base_location` VARCHAR(45) NULL DEFAULT NULL,
  `event_date` DATE NULL DEFAULT NULL,
  `employee_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `employee_id_UNIQUE` (`employee_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 57
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `fms`.`volunteer_unreg`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fms`.`volunteer_unreg` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `event_id` VARCHAR(45) NULL DEFAULT NULL,
  `event_name` VARCHAR(60) NULL DEFAULT NULL,
  `beneficiary_name` VARCHAR(80) NULL DEFAULT NULL,
  `base_location` VARCHAR(45) NULL DEFAULT NULL,
  `event_date` DATE NULL DEFAULT NULL,
  `employee_id` INT NOT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL, 
  PRIMARY KEY (`id`),
  UNIQUE INDEX `employee_id_UNIQUE` (`employee_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 57
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `fms`.`participated`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fms`.`participated` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rating` VARCHAR(45) NULL,
  `likes` VARCHAR(200) NULL,
  `dislike` VARCHAR(200) NULL,
  `event_event_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_participated_event1_idx` (`event_event_id` ASC),
  CONSTRAINT `fk_participated_event1`
    FOREIGN KEY (`event_event_id`)
    REFERENCES `fms`.`event` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `fms`.`non_participated`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fms`.`non_participated` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` VARCHAR(100) NULL,
  `event_event_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_non_participated_event1_idx` (`event_event_id` ASC),
  CONSTRAINT `fk_non_participated_event1`
    FOREIGN KEY (`event_event_id`)
    REFERENCES `fms`.`event` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `fms`.`un_reg`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fms`.`un_reg` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` VARCHAR(100) NULL,
  `event_event_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_un_reg_event1_idx` (`event_event_id` ASC),
  CONSTRAINT `fk_un_reg_event1`
    FOREIGN KEY (`event_event_id`)
    REFERENCES `fms`.`event` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO user VALUES (1,'799460','Admin','Admin','$2a$10$R/lZJuT9skteNmAku9Y7aeutxbOKstD5xE5bHOf74M2PHZipyt3yK','Admin');
INSERT INTO user VALUES (2,'799461','Pmo','Pmo','$2a$10$R/lZJuT9skteNmAku9Y7aeutxbOKstD5xE5bHOf74M2PHZipyt3yK','Pmo');

INSERT INTO user VALUES (3,'799462','Pmo','Pmo','$2a$10$R/lZJuT9skteNmAku9Y7aeutxbOKstD5xE5bHOf74M2PHZipyt3yK','Pmo');
INSERT INTO user VALUES (4,'799463','Pmo','Pmo','$2a$10$R/lZJuT9skteNmAku9Y7aeutxbOKstD5xE5bHOf74M2PHZipyt3yK','Pmo');
