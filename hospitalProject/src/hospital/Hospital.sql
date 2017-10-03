-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: Hospital
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `docschedule1`
--

DROP TABLE IF EXISTS `docschedule1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `docschedule1` (
  `name` text,
  `duty1` text,
  `duty2` text,
  `duty3` text,
  `duty4` text,
  `duty5` text,
  `duty6` text,
  `duty7` text,
  `duty8` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docschedule1`
--

LOCK TABLES `docschedule1` WRITE;
/*!40000 ALTER TABLE `docschedule1` DISABLE KEYS */;
INSERT INTO `docschedule1` VALUES ('U Ba','a','b','c','d','e','f','g','h'),('Daw Mya','A3','Free','B4','C5','D2','Free','F2','A3'),('Daw Hla','C3','C4','C5','C6','C7','C8','Free','Free');
/*!40000 ALTER TABLE `docschedule1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctorrecord`
--

DROP TABLE IF EXISTS `doctorrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctorrecord` (
  `Name` text,
  `Speciality` text,
  `ConsFee` text,
  `Contact` text,
  `Address` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctorrecord`
--

LOCK TABLES `doctorrecord` WRITE;
/*!40000 ALTER TABLE `doctorrecord` DISABLE KEYS */;
INSERT INTO `doctorrecord` VALUES ('Daw Hla','Allergist','100000','093893484','Yangon'),('U Ba','Physiologist','100000','0967876689','Hledann'),('Daw Mya','Physiologist','100000','0967889789','Mandalay');
/*!40000 ALTER TABLE `doctorrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `Name` text,
  `Disease` text,
  `FirstDate` Date,
  `RoomORBed` text,
  `PrescribedDoctor` text,
  `Address` text,
  `TotalBill` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES ('Mya Mya','Cold','2016/1/1','3A','U Ba','Yangon','10000.0'),('Mg Mg','Alleges','2016/2/1','3B','Daw Hla','Mandalay','30000.0'),('Khin Khin','Cough','2016/4/5','3C','Daw Mya','Mandalay','20000.0');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-15 23:55:03
