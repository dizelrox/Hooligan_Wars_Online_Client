SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `hooligan_wars_schema` ;
USE `hooligan_wars_schema` ;

-- -----------------------------------------------------
-- Table `hooligan_wars_schema`.`Players`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hooligan_wars_schema`.`Players` (
  `playerId` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(15) NOT NULL ,
  `playerObject` BLOB NOT NULL ,
  PRIMARY KEY (`playerId`) ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hooligan_wars_schema`.`WonMatches`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hooligan_wars_schema`.`WonMatches` (
  `matchId` INT NOT NULL AUTO_INCREMENT ,
  `Players_playerId` INT NOT NULL ,
  PRIMARY KEY (`matchId`) ,
  INDEX `fk_WonMatches_Players_idx` (`Players_playerId` ASC) ,
  CONSTRAINT `fk_WonMatches_Players`
    FOREIGN KEY (`Players_playerId` )
    REFERENCES `hooligan_wars_schema`.`Players` (`playerId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hooligan_wars_schema`.`LostMatches`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hooligan_wars_schema`.`LostMatches` (
  `matchId` INT NOT NULL ,
  `Players_playerId` INT NOT NULL ,
  PRIMARY KEY (`matchId`) ,
  INDEX `fk_LostMatches_Players1_idx` (`Players_playerId` ASC) ,
  CONSTRAINT `fk_LostMatches_Players1`
    FOREIGN KEY (`Players_playerId` )
    REFERENCES `hooligan_wars_schema`.`Players` (`playerId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `hooligan_wars_schema` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
