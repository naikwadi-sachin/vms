CREATE DATABASE  IF NOT EXISTS `vms` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `vms`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: vms
-- ------------------------------------------------------
-- Server version	5.6.22-log

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
-- Table structure for table `vms_comments`
--

DROP TABLE IF EXISTS `vms_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vms_comments` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `comment` text,
  `comment_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` char(1) DEFAULT 'y',
  PRIMARY KEY (`comment_id`),
  KEY `fk_comments_event_eventid_idx` (`event_id`),
  KEY `fk_comments_user_userid_idx` (`user_id`),
  CONSTRAINT `fk_comments_event_eventid` FOREIGN KEY (`event_id`) REFERENCES `vms_event` (`event_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comments_user_userid` FOREIGN KEY (`user_id`) REFERENCES `vms_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vms_donation`
--

DROP TABLE IF EXISTS `vms_donation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vms_donation` (
  `donation_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `card_holder_name` varchar(45) NOT NULL,
  `billing_address` varchar(100) NOT NULL,
  `card_type` varchar(10) NOT NULL,
  `card_number` varchar(15) NOT NULL,
  `expiry_month` varchar(10) NOT NULL,
  `expiry_year` varchar(5) NOT NULL,
  `security_code` int(11) NOT NULL,
  `donation_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`donation_id`),
  KEY `fk_donation_event_eventid_idx` (`event_id`),
  KEY `fk_donation_user_userid_idx` (`user_id`),
  CONSTRAINT `fk_donation_event_eventid` FOREIGN KEY (`event_id`) REFERENCES `vms_event` (`event_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_donation_user_userid` FOREIGN KEY (`user_id`) REFERENCES `vms_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vms_donation_items`
--

DROP TABLE IF EXISTS `vms_donation_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vms_donation_items` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_category` varchar(45) DEFAULT NULL,
  `item_description` varchar(100) DEFAULT NULL,
  `event_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `item_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`item_id`),
  KEY `fk_donationitem_event_idx` (`event_id`),
  KEY `fk_donationitems_user_userid_idx` (`user_id`),
  CONSTRAINT `fk_donationitems_event_eventid` FOREIGN KEY (`event_id`) REFERENCES `vms_event` (`event_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_donationitems_user_userid` FOREIGN KEY (`user_id`) REFERENCES `vms_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vms_event`
--

DROP TABLE IF EXISTS `vms_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vms_event` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_name` varchar(45) NOT NULL,
  `event_description` text,
  `event_date` date NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` char(1) DEFAULT 'y',
  PRIMARY KEY (`event_id`),
  KEY `fk_event_user_userid_idx` (`user_id`),
  CONSTRAINT `fk_event_user_userid` FOREIGN KEY (`user_id`) REFERENCES `vms_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vms_user`
--

DROP TABLE IF EXISTS `vms_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vms_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `user_type` varchar(2) DEFAULT 'u',
  `active` varchar(2) DEFAULT 'y',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vms_user_events`
--

DROP TABLE IF EXISTS `vms_user_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vms_user_events` (
  `user_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `join_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status` char(1) DEFAULT 'y',
  PRIMARY KEY (`user_id`,`event_id`),
  KEY `fk_userevents_event_eventid_idx` (`event_id`),
  CONSTRAINT `fk_userevents_event_eventid` FOREIGN KEY (`event_id`) REFERENCES `vms_event` (`event_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_userevents_user_userid` FOREIGN KEY (`user_id`) REFERENCES `vms_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'vms'
--

--
-- Dumping routines for database 'vms'
--
/*!50003 DROP PROCEDURE IF EXISTS `check_user` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `check_user`(in in_email varchar(45), in in_password varchar(45),
out user_exists varchar(45)
)
BEGIN

select email as 'user_exists'
from vms.vms_user
where email = in_user_name and password=in_password;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-11 17:31:44
