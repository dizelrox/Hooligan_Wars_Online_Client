CREATE DATABASE  IF NOT EXISTS `hooligan_wars_schema` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hooligan_wars_schema`;
-- MySQL dump 10.13  Distrib 5.6.10, for Win64 (x86_64)
--
-- Host: localhost    Database: hooligan_wars_schema
-- ------------------------------------------------------
-- Server version	5.6.10

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `lostmatches`
--

DROP TABLE IF EXISTS `lostmatches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lostmatches` (
  `matchId` int(11) NOT NULL AUTO_INCREMENT,
  `Players_playerId` int(11) NOT NULL,
  `matchEndTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`matchId`),
  KEY `fk_LostMatches_Players1_idx` (`Players_playerId`),
  CONSTRAINT `fk_LostMatches_Players1` FOREIGN KEY (`Players_playerId`) REFERENCES `players` (`playerId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lostmatches`
--

LOCK TABLES `lostmatches` WRITE;
/*!40000 ALTER TABLE `lostmatches` DISABLE KEYS */;
INSERT INTO `lostmatches` VALUES (1,12,'2014-05-10 12:31:42'),(2,12,'2014-05-10 13:03:03'),(3,12,'2014-05-10 13:37:34'),(4,13,'2014-05-10 16:39:28'),(5,12,'2014-05-11 12:22:17'),(6,12,'2014-05-11 12:32:02'),(7,12,'2014-05-11 12:38:35'),(8,12,'2014-05-11 12:53:33'),(9,12,'2014-05-11 12:56:59'),(10,12,'2014-05-11 15:17:06'),(11,12,'2014-05-11 15:21:17'),(12,12,'2014-05-11 15:21:52');
/*!40000 ALTER TABLE `lostmatches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `players` (
  `playerId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(15) NOT NULL,
  `playerObject` mediumblob NOT NULL,
  PRIMARY KEY (`playerId`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players`
--

LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
/*!40000 ALTER TABLE `players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wonmatches`
--

DROP TABLE IF EXISTS `wonmatches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wonmatches` (
  `matchId` int(11) NOT NULL AUTO_INCREMENT,
  `Players_playerId` int(11) NOT NULL,
  `matchEndTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`matchId`),
  KEY `fk_WonMatches_Players_idx` (`Players_playerId`),
  CONSTRAINT `fk_WonMatches_Players` FOREIGN KEY (`Players_playerId`) REFERENCES `players` (`playerId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wonmatches`
--

LOCK TABLES `wonmatches` WRITE;
/*!40000 ALTER TABLE `wonmatches` DISABLE KEYS */;
INSERT INTO `wonmatches` VALUES (1,13,'2014-05-10 09:57:21'),(2,13,'2014-05-10 12:31:36'),(3,13,'2014-05-10 12:44:16'),(4,13,'2014-05-10 13:03:03'),(5,13,'2014-05-10 13:37:34'),(6,12,'2014-05-10 16:39:36'),(7,13,'2014-05-11 12:22:15'),(8,13,'2014-05-11 12:32:28'),(9,13,'2014-05-11 12:35:19'),(10,13,'2014-05-11 12:56:59'),(11,12,'2014-05-11 15:17:06'),(12,13,'2014-05-11 15:21:17'),(13,13,'2014-05-11 15:21:52');
/*!40000 ALTER TABLE `wonmatches` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-05-11 21:50:18