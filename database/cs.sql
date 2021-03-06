-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: chargesystem
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bill` (
  `property` varchar(10) DEFAULT NULL,
  `clean` varchar(10) DEFAULT NULL,
  `water` varchar(10) DEFAULT NULL,
  `electricity` varchar(10) DEFAULT NULL,
  `time_stamp` date DEFAULT NULL,
  `house_id` varchar(20) DEFAULT NULL,
  `state` enum('已缴','未缴') DEFAULT NULL,
  `staff_id` varchar(20) DEFAULT NULL,
  KEY `house_id` (`house_id`),
  CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`house_id`) REFERENCES `house` (`house_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES ('100','40','4.2','81.6','2019-11-01','A0101','未缴','001'),('100','40','4.9','80','2019-10-01','A0101','已缴','001'),('100','40','2.1','87.6','2019-09-01','A0101','已缴','001'),('100','40','5.6','71.8','2019-08-01','A0101','已缴','001'),('100','40','6.3','100','2019-07-01','A0101','已缴','001'),('100','40','3.5','68.5','2019-06-01','A0101','已缴','001');
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `department` (
  `department_id` varchar(3) NOT NULL,
  `department_name` varchar(20) DEFAULT NULL,
  `manager` varchar(20) DEFAULT NULL,
  `phone_number` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('001','部门1','李狗蛋','222222'),('002','部门2','赵铁柱','222222'),('003','部门3','李翠花','222222'),('004','部门4','王二狗','222222');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `house`
--

DROP TABLE IF EXISTS `house`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `house` (
  `house_id` varchar(5) NOT NULL,
  `area` double DEFAULT NULL,
  `room` double DEFAULT NULL,
  `owner_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`house_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house`
--

LOCK TABLES `house` WRITE;
/*!40000 ALTER TABLE `house` DISABLE KEYS */;
INSERT INTO `house` VALUES ('A0101',100,4,'00001'),('A0102',100,4,'00002'),('A0201',78,4,'00003'),('A0301',94,4,'00004'),('A0503',120,4,'00005'),('A1608',98.6,1,'00006'),('A2200',102,4,'00007'),('A2201',102,4,'00007');
/*!40000 ALTER TABLE `house` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `house_owner`
--

DROP TABLE IF EXISTS `house_owner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `house_owner` (
  `owner_id` varchar(5) NOT NULL,
  `owner_name` varchar(20) DEFAULT NULL,
  `work_place` varchar(100) DEFAULT NULL,
  `phone_number` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`owner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house_owner`
--

LOCK TABLES `house_owner` WRITE;
/*!40000 ALTER TABLE `house_owner` DISABLE KEYS */;
INSERT INTO `house_owner` VALUES ('00001','张全蛋','富士康流水线1','13811110000'),('00002','田琒','富士康流水线1','13811110001'),('00003','吴龄','富士康流水线2','13811110002'),('00004','姜超','富士康流水线3','13811110003'),('00005','潘濮','富士康流水线4','13811110004'),('00006','李斌','富士康流水线5','13811110005'),('00007','文晷','富士康流水线6','13811110007');
/*!40000 ALTER TABLE `house_owner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `staff` (
  `staff_id` varchar(3) NOT NULL,
  `staff_name` varchar(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `gender` enum('M','F') DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `department_id` varchar(3) DEFAULT NULL,
  `job` enum('M','S') DEFAULT NULL,
  `pwd` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`staff_id`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `fk_dp_id` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`),
  CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES ('001','张伟','1998-09-01','M','海棠10#I528','3333333','001','S','zhangwei'),('002','胡杰夫','1998-09-01','M','海棠10#I528','1234567','001','S','hujiefu'),('003','孙天宇','1998-09-01','M','海棠10#I528','1234567','001','S','suntianyu'),('004','王继科','1998-09-01','M','海棠10#I528','1234567','002','S','wangjike'),('005','杨秦辉','1998-09-01','M','海棠10#I528','1234567','002','S','yangqinhui'),('006','郑慧明','1998-09-01','M','海棠10#I528','1234567','003','S','zhenghuiming'),('008','夏浩文','1998-09-01','M','海棠10#I528','1234567','003','S','xiahaowen'),('009','江萧','1998-03-23','M','海棠10#I528','1234567','001','M','jiangxiao'),('010','徐云飞','1998-10-07','M','海棠10#I528','1234567','002','M','xuyunfei'),('011','牛年','1997-09-10','M','海棠10#I528','1234567','003','M','niunian');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-08 18:46:37
