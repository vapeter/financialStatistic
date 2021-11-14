-- -----------------------------------------------------
-- Table `financialstatistic`.`banks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `financialstatistic`.`banks` (
  `bank_id` INT NOT NULL AUTO_INCREMENT,
  `bank_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`bank_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `financialstatistic`.`bank_accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `financialstatistic`.`bank_accounts` (
  `bank_account_id` INT NOT NULL AUTO_INCREMENT,
  `bank_account_number` VARCHAR(26) NOT NULL,
  `amount` BIGINT NOT NULL,
  `bank_id` INT NOT NULL,
  `bank_account_type` INT NOT NULL,
  PRIMARY KEY (`bank_account_id`),
  INDEX `fk_bank_accounts_banks_idx` (`bank_id` ASC),
  CONSTRAINT `fk_bank_accounts_banks`
    FOREIGN KEY (`bank_id`)
    REFERENCES `financialstatistic`.`banks` (`bank_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `financialstatistic`.`transaction_categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `financialstatistic`.`transaction_categories` (
  `transaction_category_id` INT NOT NULL AUTO_INCREMENT,
  `transaction_category` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`transaction_category_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `financialstatistic`.`transactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `financialstatistic`.`transactions` (
  `transaction_id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `amount` BIGINT NOT NULL,
  `currency` VARCHAR(5) NULL DEFAULT NULL,
  `announcement` VARCHAR(250) NULL DEFAULT NULL,
  `partner_name` VARCHAR(150) NULL DEFAULT NULL,
  `transaction_category_id` INT NOT NULL,
  `bank_account_id` INT NOT NULL,
  PRIMARY KEY (`transaction_id`),
  INDEX `fk_transactions_transaction_categories1_idx` (`transaction_category_id` ASC),
  INDEX `fk_transactions_bank_accounts1_idx` (`bank_account_id` ASC),
  CONSTRAINT `fk_transactions_bank_accounts1`
    FOREIGN KEY (`bank_account_id`)
    REFERENCES `financialstatistic`.`bank_accounts` (`bank_account_id`),
  CONSTRAINT `fk_transactions_transaction_categories1`
    FOREIGN KEY (`transaction_category_id`)
    REFERENCES `financialstatistic`.`transaction_categories` (`transaction_category_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 310
DEFAULT CHARACTER SET = utf8;
