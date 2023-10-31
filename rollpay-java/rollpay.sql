-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: rollpay
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','123456');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buyorder`
--

DROP TABLE IF EXISTS `buyorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buyorder` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `amount` double NOT NULL,
  `billingaddress` varchar(64) NOT NULL,
  `pointcontact` varchar(64) NOT NULL,
  `employeeid` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyorder`
--

LOCK TABLES `buyorder` WRITE;
/*!40000 ALTER TABLE `buyorder` DISABLE KEYS */;
INSERT INTO `buyorder` VALUES (2,'2023-09-26',200222,'吉林省长春市','111112',1),(6,'2023-10-01',1111111,'233333333333','2222',1),(7,'2023-10-03',123,'3333333333333333','123123',1),(8,'2023-10-03',222,'222222222','3333',2),(9,'2023-10-02',1111,'GGGGG','GGG',1);
/*!40000 ALTER TABLE `buyorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `password` varchar(64) NOT NULL,
  `name` varchar(32) NOT NULL,
  `email` varchar(64) DEFAULT NULL,
  `SSN` varchar(64) DEFAULT NULL,
  `tax` double DEFAULT NULL,
  `detection` double DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `kind` tinyint NOT NULL DEFAULT '0',
  `hoursal` double DEFAULT NULL,
  `sal` double DEFAULT NULL,
  `com` double DEFAULT NULL,
  `hourlimit` double DEFAULT NULL,
  `way` tinyint NOT NULL DEFAULT '0',
  `address` varchar(64) DEFAULT NULL,
  `bankname` varchar(64) DEFAULT NULL,
  `banknumber` varchar(64) DEFAULT NULL,
  `isDelete` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'test','123','ghn','111@qq.com','11',0,0,'12345678',2,300,3000,0.1,0,1,'11111','','',1),(3,'test1','123456','张四','222@qq.com','11',20,401,'12345678',0,400,3000,0.1,0,0,NULL,NULL,NULL,0),(4,'test2','123456','张武','333@qq.com','11',10,401,'1234567890',0,500,3000,0.1,10,0,NULL,NULL,NULL,0),(5,'test3','123456','张武','333@qq.com','111',10,401,'1234567890',0,500,3000,0.1,10,0,NULL,NULL,NULL,0),(6,'test5','123456','张武','333@qq.com',NULL,10,401,'1234567890',0,500,3000,0.1,10,0,NULL,NULL,NULL,0),(8,'test50','123456','张武','333@qq.com',NULL,10,401,'1234567890',0,500,3000,0.1,10,0,NULL,NULL,NULL,0),(9,'test511','123456','张武','333@qq.com',NULL,10,401,'1234567890',2,500,3000,0.1,10,2,NULL,NULL,NULL,0),(10,'1234','1234','update','111',NULL,333,333,'33333333333',1,111,3000,0.1,1,1,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timecard`
--

DROP TABLE IF EXISTS `timecard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timecard` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `time` double NOT NULL,
  `projectid` bigint NOT NULL,
  `employeeid` bigint NOT NULL,
  `isSubmit` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timecard`
--

LOCK TABLES `timecard` WRITE;
/*!40000 ALTER TABLE `timecard` DISABLE KEYS */;
INSERT INTO `timecard` VALUES (1,'2023-09-26',5,1,1,1),(2,'2023-09-26',4,2,1,1),(3,'2023-09-26',3,3,3,1),(4,'2023-09-26',3,2,3,1),(5,'2023-09-26',4,3,1,1),(6,'2023-09-26',4,4,3,1),(7,'2023-09-26',3,5,1,1),(8,'2023-09-26',3,6,3,0),(9,'2023-09-26',4,3,1,1),(10,'2023-09-26',10,2,3,0),(11,'2023-09-30',20,10,2,0),(15,'2023-09-30',20,10,1,1),(16,'2023-10-01',15,2,1,0),(18,'2023-10-06',10,2,1,0);
/*!40000 ALTER TABLE `timecard` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-31 14:43:56
