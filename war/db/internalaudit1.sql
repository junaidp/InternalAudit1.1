-- MySQL dump 10.13  Distrib 5.7.10, for Win32 (AMD64)
--
-- Host: localhost    Database: internalaudit1
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `audit_engagement`
--

DROP TABLE IF EXISTS `audit_engagement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audit_engagement` (
  `auditEngId` int(11) NOT NULL AUTO_INCREMENT,
  `jobcreationid` int(11) DEFAULT NULL,
  `jobstatus` varchar(50) DEFAULT NULL,
  `assignmentObj` text,
  `activityObj` text,
  `processName` text,
  `auditNotification` varchar(200) DEFAULT NULL,
  `sendto` varchar(45) DEFAULT NULL,
  `cc` varchar(45) DEFAULT NULL,
  `year` int(10) unsigned DEFAULT NULL,
  `companyId` int(10) unsigned DEFAULT NULL,
  `status` varchar(45) NOT NULL DEFAULT '0',
  `initiatedBy` int(10) unsigned DEFAULT NULL,
  `approvedBy` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`auditEngId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit_engagement`
--

LOCK TABLES `audit_engagement` WRITE;
/*!40000 ALTER TABLE `audit_engagement` DISABLE KEYS */;
INSERT INTO `audit_engagement` VALUES (9,1,'Not Started','','','','','','',2014,1,'',NULL,NULL),(10,2,'In Progress','obb','bob','jh','ddd','junaidp@gmail.com','',2014,1,'',NULL,NULL),(11,3,'In Progress','jmmm','iuui..','ui (','testing email','junaidp@gmail.com','junaidp2@hotmail.com',2016,1,'0',1,0),(24,4,'In Progress','kkklkkl','lkjhhh','uhuhih','Notification for audit...','junaidp@gmail.com','jo@jot.com',2015,3,'1',10,0),(25,5,'In Progress','obbbbbbbbbbbbbbbbbbbbbbbbb','activityyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy','undrlayinggggggggggggggggggggg','','junaidp@gmail.com','',2015,3,'4',10,0),(26,6,'In Progress','','','','','','',2015,8,'0',37,0),(27,7,'Not Started','','','','','','',2015,8,'0',0,0),(28,8,'Not Started','','','','','','',2015,8,'0',0,0),(29,9,'Not Started','','','','','','',2015,8,'0',0,0),(30,10,'In Progress','aaa','nnnnnn v,cmsnv,msnfvsldkjfgnlsdkfvndslmncz,mcnzc','lsjvnczlknz,cxm z lzcknzlkcnmlzdckmnlknmm,','Get set ready','mfaheempiracha@gmail.com','',2015,8,'1',37,0),(31,11,'In Progress',' n','m,n',',kn','','mfaheempiracha@gmail.com','',2015,8,'4',37,0),(32,12,'In Progress','','','','','','',2018,8,'0',37,0),(33,13,'In Progress','lkjadjfldskjflsknbknb','kbkjnbakdsn','ksadncfbsdakbas','kick off','faheempiracha@gmail.com','sohaibnouman@gmail.com',2015,11,'1',60,0),(34,14,'In Progress','mknbmn','knbkmnbm','mnb mn','kasdfbsdakfas','faheempiracha@gmail.com','',2015,11,'1',60,0),(35,15,'In Progress','','','','','','',2015,11,'0',58,0),(36,16,'In Progress','','','','','mfaheempiracha@gmail.com','',2015,11,'4',58,0),(37,17,'In Progress','ob','obbb','identt','Test email','junaidp@gmail.com','',2015,4,'4',18,0),(38,18,'In Progress','h','h','h','dd','junaidp@gmail.com','',2015,4,'4',16,0),(39,19,'In Progress','','','','Dear Faheem\n\nAudit kicked off','mfaheempiracha@gmail.com','',2015,11,'0',60,0),(40,20,'In Progress','','','','Dear Rafey\n\nAudit shall be kicked off on 23rd June','rafeyqidwai@gmail.com','',2015,11,'0',59,0),(41,21,'In Progress','','','','','','',2015,11,'0',60,0),(42,22,'In Progress','','','','Dear Rafey\n\nAudit will commence on 23rd July','rafeyqidwai@gmail.com','',2015,11,'0',60,0),(43,23,'In Progress','','','','','','',2015,11,'0',58,0),(44,24,'In Progress','','','','The required resources have been allocated.','korgentteamlead@gmail.com','resource allocation',2015,11,'0',60,0),(45,25,'In Progress','','','','dear rafey\n\nAudit has been kicked off','rafeyqidwai@gmail.com','',2015,11,'0',58,0),(46,26,'In Progress','','','','Dear sir\n\nAudit shall be kicked off on 18th aug.','hyphen @gmail.com','hyphen @gmail.com',2015,11,'0',59,0),(47,28,'In Progress','','','','','junaidp@gmail.com','',2017,11,'0',58,0),(48,29,'In Progress','Objective2','ctivity object','Underl','Test','junaidp@gmail.com','',2018,15,'1',83,0),(49,30,'In Progress','abc','abc','abc','','teamleadblack@email.com','auditorblack@email.com',2018,17,'1',86,0),(50,31,'In Progress','sdbsbdjfsd','sfsfgsd','ssdsdfs','ababababababababvavaaba','xyz@email.com','abc@email.com',2018,17,'1',86,0),(51,32,'In Progress','xvxcv','xcvxv','zzxcv','','dfsd@email.com','sdsd@email.com',2018,17,'1',86,0),(52,33,'In Progress','Market Diversification','Searching for newer markets with lucrative business opportunities','Identification of risks by applying various forecast techiniques','The requires resources have been allocated.','abc@email.com','',2018,18,'1',92,0),(53,34,'In Progress','Fashion Trends','Searching for newer markets','Identifications of risks.','','abc@email.com','',2018,18,'1',94,0),(54,35,'In Progress','Market Diversification','abc','abc','','abc@email.com','',2018,18,'1',94,0),(56,36,'In Progress','abc','abc','abc','abc','abc@email.com','',2018,18,'1',93,0),(57,37,'In Progress','abc','abc','abc','abc','abc@email.com','',2018,18,'1',92,0),(58,38,'In Progress','srf','sfs','sf','','abc@email.com','',2018,18,'1',92,0);
/*!40000 ALTER TABLE `audit_engagement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `audit_step`
--

DROP TABLE IF EXISTS `audit_step`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audit_step` (
  `audit_step_id` int(11) NOT NULL AUTO_INCREMENT,
  `population` varchar(255) NOT NULL,
  `sample_selected` varchar(255) NOT NULL,
  `selection_basis` varchar(255) NOT NULL,
  `proceduce_performance` text NOT NULL,
  `conclusion` varchar(255) NOT NULL,
  `job_id` int(11) NOT NULL,
  `auditWork` int(11) DEFAULT NULL,
  `status` int(1) unsigned DEFAULT NULL,
  `year` int(10) unsigned DEFAULT NULL,
  `companyId` int(10) unsigned NOT NULL DEFAULT '0',
  `approvedBy` int(10) unsigned DEFAULT NULL,
  `initiatedBy` int(10) unsigned DEFAULT NULL,
  `feedback` varchar(200) DEFAULT NULL,
  `uploadedFile` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`audit_step_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit_step`
--

LOCK TABLES `audit_step` WRITE;
/*!40000 ALTER TABLE `audit_step` DISABLE KEYS */;
INSERT INTO `audit_step` VALUES (1,'p1','sa1','bas1','procedure perfo, exc','Non-Satisfactory',3,16,1,2015,1,1,5,NULL,NULL),(2,'ppp11','wss1','bbbb1kk','jjjjk  okk','Non-Satisfactory',3,17,1,2015,1,1,5,NULL,NULL),(3,'p1','s1','b1','kjhkhljh;kjn','Non-Satisfactory',4,18,1,2015,3,10,13,NULL,NULL),(4,'p111222','s112222','b111222','hgfjgjhgvckhg','Non-Satisfactory',4,19,1,2015,3,10,13,NULL,NULL),(5,'ppppppppppppppppppppppp','ssssssssssssssssssssssssss','bbbbbbbbbbbbbbbbbbbbbbbbbbb','kkkkkkkkmkmmmmmmmmmmmmmmmmmmmmmmmmm','Non-Satisfactory',5,20,1,2015,3,10,13,NULL,NULL),(6,'p11111111111111111111','s1111111111111111111111','b111111111111111111','kkkkkkkkkkkkkkkkkkkkkkkkkkkk','Non-Satisfactory',5,21,1,2015,3,10,13,NULL,NULL),(7,'1','1','1','DID SOMEHTHING','Satisfactory',10,22,1,2015,8,37,39,NULL,NULL),(8,'','','','','Non-Satisfactory',10,23,1,2015,8,37,39,NULL,NULL),(9,'','','','','Satisfactory',10,24,2,2015,8,37,39,NULL,NULL),(10,'','','','','Non-Satisfactory',10,25,1,2015,8,37,39,NULL,NULL),(11,'','','','','Non-Satisfactory',11,26,1,2015,8,37,39,NULL,NULL),(12,'12','10','random','lasdnaslkdnalmkn.,m.amds.,mda','Non-Satisfactory',14,28,1,2015,11,58,60,NULL,NULL),(13,'','','',',n,','Non-Satisfactory',14,34,2,2015,11,58,60,NULL,NULL),(14,'','','','','Non-Satisfactory',14,35,1,2015,11,58,60,'',NULL),(15,'ppp11','ssss1','bbbb1','jjjljl','Non-Satisfactory',18,38,1,2015,4,16,17,'',NULL),(16,'','','','1. Did this\n2. Also did that\n3.Checked everything','Non-Satisfactory',13,27,1,2015,11,58,60,'','src.zip'),(17,'','','','1. Check the two systems\n2. Check again','Non-Satisfactory',13,41,1,2015,11,58,60,'','Geographical list of Firms of Chartered Accountants.docx'),(18,'','','','1. Stay focused\n2. Stay calm','Non-Satisfactory',13,42,1,2015,11,58,60,'',NULL),(19,'','','','a. sada\nv.afdlask','Satisfactory',13,43,1,2015,11,58,60,'',''),(20,'','','','','Satisfactory',13,44,1,2015,11,58,60,'',NULL),(21,'','','','','Satisfactory',14,36,1,2015,11,58,60,'',NULL),(22,'','','','','Satisfactory',14,37,4,2015,11,0,58,'',NULL),(23,'','','','','Non-Satisfactory',15,45,1,2015,11,58,62,'',NULL),(24,'','','','','Non-Satisfactory',15,46,1,2015,11,58,62,'',NULL),(25,'','','','','Satisfactory',15,47,1,2015,11,58,62,'',NULL),(26,'j','j','j','j','Satisfactory',17,32,1,2015,4,16,18,'',NULL),(27,'99','99','99','99','Non-Satisfactory',17,33,1,2015,4,16,18,'',NULL),(28,'8','8','8','8','Satisfactory',17,48,1,2015,4,16,18,'',NULL),(29,'7','7','7','7','Satisfactory',17,49,1,2015,4,16,18,'',NULL),(30,'6','6','6','6','Non-Satisfactory',17,50,1,2015,4,16,18,'',NULL),(31,'','','','','Non-Satisfactory',16,51,1,2015,11,58,60,'',NULL),(32,'','','','','Non-Satisfactory',16,52,1,2015,11,58,60,'',NULL),(33,'','','','','Non-Satisfactory',16,53,1,2015,11,58,60,'',NULL),(34,'','','','','Non-Satisfactory',19,54,1,2015,11,58,60,'',NULL),(35,'','','','','Non-Satisfactory',19,55,1,2015,11,58,60,'',NULL),(36,'','','','','Satisfactory',19,56,1,2015,11,58,60,'',NULL),(37,'','','','','Satisfactory',22,61,1,2015,11,58,60,'',NULL),(38,'','','','jasdhskjfhnsdmfns,mcnzxc,m\nabkdjnaskdnasdm\nkjbadkasndkmasnd','Satisfactory',21,62,1,2015,11,58,60,'',NULL),(39,'20','10','cost leadership','','Satisfactory',24,63,1,2015,11,58,60,'',NULL),(40,'10','5','cost leadership','','Satisfactory',25,64,1,2015,11,58,58,'',NULL),(41,'','','','','Satisfactory',20,60,3,2015,11,0,59,'',NULL),(42,'10','5','Cost leadership','','Satisfactory',26,65,1,2015,11,58,59,'',NULL),(43,'','','','proce','Satisfactory',29,66,1,2018,15,81,83,'',NULL),(44,'abc','abc','abc','abcabcabac','Satisfactory',30,67,1,2018,17,86,88,'',NULL),(45,'','','','dfssdfsdf','Satisfactory',31,68,1,2018,17,86,87,'',NULL),(46,'','','','sfsdfsdf','Non-Satisfactory',31,69,1,2018,17,86,87,'',NULL),(47,'xvxvx','dgdf','dgds','dfsdf','Satisfactory',32,70,1,2018,17,86,86,'',NULL),(48,'20','10','Cost Leadership','','Satisfactory',33,71,1,2018,18,92,94,'',NULL),(49,'20','10','Cost Leadership','abc','Satisfactory',34,72,1,2018,18,92,94,'',NULL),(50,'abc','abc','abc','abc','Satisfactory',35,73,1,2018,18,92,92,'',NULL),(51,'abc','abc','abc','abc','Satisfactory',35,73,1,2018,18,92,92,'',NULL),(52,'10','10','10','abc','Satisfactory',36,74,1,2018,18,92,94,'',NULL),(53,'1','1','1','av','Satisfactory',37,75,1,2018,18,92,93,'',NULL);
/*!40000 ALTER TABLE `audit_step` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `audit_work`
--

DROP TABLE IF EXISTS `audit_work`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audit_work` (
  `audit_work_id` int(11) NOT NULL AUTO_INCREMENT,
  `step_no` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `responsible_control` int(11) NOT NULL,
  `jobcreationid` int(11) NOT NULL,
  `status` int(1) unsigned DEFAULT '0',
  `year` int(10) unsigned DEFAULT NULL,
  `companyId` int(10) unsigned NOT NULL DEFAULT '0',
  `initiatedBy` int(10) unsigned DEFAULT NULL,
  `approvedBy` int(10) unsigned DEFAULT NULL,
  `feedback` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`audit_work_id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit_work`
--

LOCK TABLES `audit_work` WRITE;
/*!40000 ALTER TABLE `audit_work` DISABLE KEYS */;
INSERT INTO `audit_work` VALUES (16,'1','one ok',1,3,1,2015,1,5,1,NULL),(17,'2','twi  feedback from head11',1,3,1,2015,1,5,1,NULL),(18,'1','dessss',10,4,1,2015,3,13,10,NULL),(19,'2','dess22',10,4,1,2015,3,13,10,NULL),(20,'1','dddddddd',10,5,1,2015,3,13,10,NULL),(21,'2','ccccccccccccccccc',10,5,1,2015,3,13,10,NULL),(22,'1','do whatever',37,10,1,2015,8,39,37,NULL),(23,'2','do what i like',37,10,1,2015,8,39,37,NULL),(24,'3.','something needs to be done',37,10,1,2015,8,39,37,NULL),(25,'4.','section 248',37,10,1,2015,8,39,37,NULL),(26,'','mn ',37,11,1,2015,8,39,37,NULL),(27,'1','ksdnnbfkdsnfdsbkm',58,13,1,2015,11,60,58,''),(28,'1','ddd,mn,mn',58,14,1,2015,11,60,58,NULL),(29,'22','ddd',16,17,5,2015,4,18,0,NULL),(30,'8','u',16,17,5,2015,4,18,0,NULL),(31,'22','ddd',16,17,5,2015,4,18,0,NULL),(32,'221','sd3',16,17,1,2015,4,18,16,NULL),(33,'2','dd',16,17,1,2015,4,18,16,NULL),(34,'2','adasd',58,14,1,2015,11,60,58,NULL),(35,'3','Do something',58,14,1,2015,11,60,58,NULL),(36,'4','Finance',58,14,1,2015,11,60,58,NULL),(37,'','',58,14,1,2015,11,60,58,NULL),(38,'1','1111',16,18,1,2015,4,17,16,''),(39,'2','222',16,18,1,2015,4,17,16,''),(40,'3','222!..',16,18,1,2015,4,17,16,''),(41,'2','Ensure that data is reconciled and matched',58,13,1,2015,11,60,58,''),(42,'3','Please do the work diligently',58,13,1,2015,11,60,58,''),(43,'4','Ensure that every thing is completed',58,13,1,2015,11,60,58,''),(44,'5','Ensure that inventory is ordered on timely basis. completed',58,13,1,2015,11,60,58,''),(45,'1.','Environement',58,15,1,2015,11,62,58,''),(46,'2.','Pollution',58,15,1,2015,11,62,58,''),(47,'3. ','Waste management',58,15,1,2015,11,62,58,''),(48,'222','desc222',16,17,1,2015,4,18,16,NULL),(49,'223','desc333',16,17,1,2015,4,18,16,NULL),(50,'224','desc444',16,17,1,2015,4,18,16,NULL),(51,'1.','see waste management',58,16,1,2015,11,60,58,''),(52,'2.','See water',58,16,1,2015,11,60,58,''),(53,'3.','See plant',58,16,1,2015,11,60,58,''),(54,'1.','abc',58,19,1,2015,11,58,58,''),(55,'2.','123',58,19,1,2015,11,58,58,''),(56,'2.','123',58,19,1,2015,11,58,58,''),(57,'1','Physical verification of assets',58,20,1,2015,11,60,58,''),(58,'2','Verify valuation',58,20,1,2015,11,60,58,''),(59,'3','Asset count',58,20,1,2015,11,60,58,''),(60,'4','Allocation to appropriate heads',58,20,1,2015,11,60,58,''),(61,'1','Verify Tax rates',58,22,1,2015,11,58,58,''),(62,'','Reveneue',58,21,1,2015,11,60,58,''),(63,'1','Cost competition analysis',58,24,1,2015,11,60,58,''),(64,'1','cost completion analysis',58,25,1,2015,11,58,58,''),(65,'1','Cost completion analysis',58,26,1,2015,11,59,58,''),(66,'1','srep1 desc',81,29,1,2018,15,83,81,''),(67,'1','abc',86,30,1,2018,17,88,86,''),(68,'1','dsf',86,31,1,2018,17,87,86,''),(69,'2','dsfsdf',86,31,1,2018,17,87,86,''),(70,'1','afaf',86,32,1,2018,17,86,86,''),(71,'1','Cost Competition Analysis',92,33,1,2018,18,94,92,''),(72,'1','Cost Completion Analysis',92,34,1,2018,18,94,92,''),(73,'1','abc',92,35,1,2018,18,94,92,''),(74,'1','abc',92,36,1,2018,18,94,92,''),(75,'1','er',92,37,1,2018,18,93,92,''),(76,'1','rfc',92,38,1,2018,18,92,92,'');
/*!40000 ALTER TABLE `audit_work` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `cityId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `city` varchar(45) NOT NULL,
  `countryId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`cityId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'islamabad',1);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `companyId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '',
  `contactPerson` varchar(45) DEFAULT NULL,
  `contactPersonEmail` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`companyId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (0,'none','none','none@none.com'),(1,'hyphen','faheem','faheem@hyphenconsult.com'),(2,'test','test','test'),(3,'jons','junaid','junaidp@gmail.com'),(4,'test','test','test'),(5,'Telenor','Ahsan','faheempiracha@gmail.com'),(6,'pol','poladmin','poladmin@gmail.com'),(7,'pol','poladmin','poladmin@gmail.com'),(8,'arl','arladmin','arladmin@gmail.com'),(9,'demotest','demotestadmin','demotestadmin@gmail.com'),(10,'demo','demoadmin','demoadmin@gmail.com'),(11,'korgent','korgentadmin','korgentadmin@gmail.com'),(12,'Zong','zongadmin','zongadmin@email.com'),(13,'jubileelife','Adeel','Adeel.Khan@jubileelife.com'),(14,'bestway','amjad','amjad@bestway.com.pk'),(15,'Bug','bugger','bug@d.com'),(16,'Zohaib (Private) Limited','Zohaib Abbasi','zohaib1112@hotmail.com'),(17,'Black Panther','Zohaib Abbasi','zohaib1112@hotmail.com'),(18,'Honda ','Zack','honda@email.com'),(19,'ALSUHAIMI','Nauman Sohail','n.sohail@alsuhaimi.net');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companyskillrelation`
--

DROP TABLE IF EXISTS `companyskillrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `companyskillrelation` (
  `companySkillId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `companyId` int(10) unsigned NOT NULL DEFAULT '0',
  `skillId` int(10) unsigned NOT NULL DEFAULT '0',
  `year` int(10) unsigned NOT NULL DEFAULT '0',
  `availablehours` varchar(45) NOT NULL DEFAULT '0',
  PRIMARY KEY (`companySkillId`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companyskillrelation`
--

LOCK TABLES `companyskillrelation` WRITE;
/*!40000 ALTER TABLE `companyskillrelation` DISABLE KEYS */;
INSERT INTO `companyskillrelation` VALUES (1,3,2,2015,'1840'),(2,4,1,2015,'12440'),(3,4,2,2015,'3320'),(4,3,1,2015,'2080'),(5,5,2,2015,'4160'),(6,5,3,2015,'2080'),(7,1,1,2015,'2080'),(8,1,2,2015,'6240'),(9,8,2,2015,'1344'),(10,8,1,2015,'11864'),(11,8,3,2015,'304'),(12,8,1,2018,'9040'),(13,8,2,2018,'9040'),(14,8,3,2018,'8960'),(15,9,2,2015,'9056'),(16,9,1,2015,'1744'),(17,9,3,2015,'1744'),(18,11,2,2016,'3384'),(19,11,1,2016,'904'),(20,11,3,2016,'784'),(21,12,2,2016,'4160'),(22,13,2,2015,'7680'),(23,14,2,2015,'4160'),(24,15,2,2018,'6200'),(25,1,2,2018,'2080'),(26,17,2,2018,'5320'),(27,18,3,2018,'2080'),(28,18,2,2018,'3600'),(29,19,2,2018,'4160');
/*!40000 ALTER TABLE `companyskillrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `countryId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `country` varchar(45) NOT NULL,
  PRIMARY KEY (`countryId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'pakistan');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `departmentId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(45) NOT NULL,
  `availableHours` int(9) NOT NULL,
  PRIMARY KEY (`departmentId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'IT',1950),(2,'Finance',2500),(3,'Business',750),(4,'Strategy',2000),(5,'Regulatory',2000),(6,'Commercial',2000),(7,'Human Resource',2000),(8,'Procurement and Logistics',2000);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `employeeId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `employeeName` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `cityId` int(10) unsigned DEFAULT NULL,
  `province` int(10) unsigned DEFAULT NULL,
  `countryId` int(10) unsigned DEFAULT NULL,
  `designation` varchar(45) DEFAULT NULL,
  `chargeOutRate` int(10) unsigned DEFAULT NULL,
  `dateOfJoining` datetime DEFAULT NULL,
  `latestPerformamceRating` varchar(45) DEFAULT NULL,
  `userId` int(10) unsigned DEFAULT NULL,
  `reportingTo` int(10) unsigned DEFAULT NULL,
  `fromInternalAuditDept` varchar(45) DEFAULT 'no',
  `auditHead` tinyint(1) unsigned DEFAULT NULL,
  `skillId` int(10) unsigned DEFAULT NULL,
  `companyId` int(10) unsigned DEFAULT NULL,
  `rollId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`employeeId`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (0,'no user','nouser@user.com',1,1,1,'no design',0,NULL,NULL,0,0,'yes',0,0,0,0),(1,'Muhammad Faheem Piracha','faheem@email.com',1,1,1,'des',1,NULL,'kj',1,1,'yes',1,1,1,1),(2,'hy1','hy1@email.com',1,NULL,1,'hj',NULL,'2014-12-25 00:00:00',NULL,2,1,'yes',0,2,1,3),(3,'hyad','hyad',1,NULL,1,'admin',NULL,'2014-12-29 00:00:00',NULL,3,3,'yes',0,2,1,4),(4,'jk','jk',1,NULL,1,'',NULL,'2014-12-29 00:00:00',NULL,4,4,'yes',0,2,2,2),(5,'hy2','hy2@email.com',1,1,1,'lead',NULL,'2014-12-30 00:00:00',NULL,5,2,'yes',0,2,1,2),(6,'test1','test1',1,NULL,1,'cd',NULL,'2014-12-30 00:00:00',NULL,6,6,'yes',0,1,2,4),(8,'hy3','hy3',1,NULL,1,'hy3',NULL,'2014-12-30 00:00:00',NULL,8,2,'yes',0,2,1,3),(9,'mgm','mgm',1,NULL,1,'mgm',NULL,'2014-12-31 00:00:00',NULL,9,9,'no',0,1,4,5),(10,'jj','jj',1,NULL,1,'jj',NULL,'2015-01-07 00:00:00',NULL,10,10,'yes',0,1,3,1),(11,'jadmin','jadmin',1,NULL,1,'jadmin',NULL,'2015-01-07 00:00:00',NULL,11,11,'yes',0,1,3,4),(12,'j1','j1',1,1,1,'j1',NULL,'2015-01-07 00:00:00',NULL,12,10,'yes',0,2,3,2),(13,'j2','j2',1,NULL,1,'j2',NULL,'2015-01-07 00:00:00',NULL,13,12,'yes',0,2,3,3),(14,'t1','t1',1,NULL,1,'t1',NULL,'2015-01-08 00:00:00',NULL,14,12,'yes',0,2,3,2),(15,'tadmin','tadmin',1,NULL,1,'tadmin',NULL,'2015-01-08 00:00:00',NULL,15,15,'yes',0,1,4,4),(16,'thead','t2',1,NULL,1,'t2',NULL,'2015-01-08 00:00:00',NULL,16,16,'yes',0,1,4,1),(17,'t3','t3',1,NULL,1,'t3',NULL,'2015-01-08 00:00:00',NULL,17,16,'yes',0,2,4,2),(18,'t4','t4',1,NULL,1,'t4',NULL,'2015-01-08 00:00:00',NULL,18,17,'yes',0,1,4,3),(19,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,0,NULL),(23,'jmgm','jmgm',1,NULL,1,'jmgm',NULL,'2015-01-09 00:00:00',NULL,22,23,'no',0,1,3,5),(24,'telenor1','telenor1@gmail.com',1,NULL,1,'auditor',NULL,'2015-01-10 00:00:00',NULL,23,24,'yes',0,1,5,4),(25,'telenor2','telenor2@gmail.com',1,NULL,1,'auditor',NULL,'2015-01-10 00:00:00',NULL,24,25,'yes',0,2,5,1),(26,'tttt','tt@tt.com',1,NULL,1,'tttt',NULL,'2015-01-11 00:00:00',NULL,25,26,'yes',0,1,4,4),(27,'tt','tt1@tt.com',1,NULL,1,'tt',NULL,'2015-01-11 00:00:00',NULL,26,27,'yes',0,1,4,1),(28,'tt','tt2@tt.com',1,NULL,1,'ju',NULL,'2015-01-11 00:00:00',NULL,27,28,'yes',0,1,4,1),(29,'tt','tt3@tt.com',1,NULL,1,'hh',NULL,'2015-01-11 00:00:00',NULL,28,29,'yes',0,1,4,1),(30,'tt','tt111@tt.com',1,NULL,1,'tt',NULL,'2015-01-11 00:00:00',NULL,29,30,'yes',0,1,4,1),(31,'rr','tt33@tt.com',1,NULL,1,'y',NULL,'2015-01-11 00:00:00',NULL,30,31,'yes',0,1,4,1),(32,'ptelenor3','ptelenor3@gmail.com',1,NULL,1,'team lead',NULL,'2015-01-19 00:00:00',NULL,31,24,'yes',0,2,5,2),(33,'ptelenor4','ptelenor4@gmail.com',1,NULL,1,'auditor',NULL,'2015-01-19 00:00:00',NULL,32,24,'yes',0,3,5,3),(34,'poladmin','poladmin@gmail.com',1,NULL,1,'admin',NULL,'2015-01-19 00:00:00',NULL,33,34,'yes',0,1,6,1),(35,'arladmin','arladmin@gmail.com',1,NULL,1,'auditor',NULL,'2015-01-19 00:00:00',NULL,34,35,'yes',0,2,8,4),(36,'arlauditor','arlauditor@gmail.com',1,NULL,1,'auditor',NULL,'2015-01-19 00:00:00',NULL,35,35,'yes',0,2,8,3),(37,'arlhead','arlhead@gmail.com',1,NULL,1,'head',NULL,'2015-01-19 00:00:00',NULL,36,37,'yes',0,2,8,1),(38,'arlteamlead','arlteamlead@gmail.com',1,NULL,1,'teamlead',NULL,'2015-01-19 00:00:00',NULL,37,37,'yes',0,2,8,2),(39,'arlauditor1','arlauditor1@gmail.com',1,NULL,1,'arlauditor1',NULL,'2015-01-19 00:00:00',NULL,38,38,'yes',0,2,8,3),(40,'arlauditor2','arlauditor2@gmail.com',1,NULL,1,'auditor',NULL,'2015-01-19 00:00:00',NULL,39,38,'yes',0,1,8,3),(41,'arlauditor3','arlauditor3@gmail.com',1,NULL,1,'auditor',NULL,'2015-01-19 00:00:00',NULL,40,38,'yes',0,2,8,3),(42,'arlauditor4','arlauditor4@gmail.com',1,NULL,1,'auditor',NULL,'2015-01-19 00:00:00',NULL,41,38,'yes',0,2,8,3),(43,'arlauditor5','arlauditor5@gmail.com',1,NULL,1,'auditor',NULL,'2015-01-19 00:00:00',NULL,42,38,'yes',0,3,8,3),(44,'arlmgm','arlmgm@gmail.com',1,NULL,1,'CFO',NULL,'2015-02-19 00:00:00',NULL,43,44,'no',0,1,8,5),(45,'arlmgm1','arlmgm1@gmail.com',1,NULL,1,'CTO',NULL,'2015-02-19 00:00:00',NULL,44,45,'no',0,1,8,5),(46,'demotestadmin','demotestadmin@gmail.com',1,NULL,1,'admin',NULL,'2015-03-19 00:00:00',NULL,45,46,'yes',0,1,9,4),(47,'demofin1','demofin1@gmail.com',1,NULL,1,'Finance',NULL,'2015-03-19 00:00:00',NULL,46,46,'yes',0,2,9,3),(48,'demofin2','demofin2@gmail.com',1,NULL,1,'Finance',NULL,'2015-03-19 00:00:00',NULL,47,46,'yes',0,2,9,3),(49,'demoit1','demoit1@gmail.com',1,NULL,1,'IT',NULL,'2015-03-19 00:00:00',NULL,48,46,'yes',0,1,9,3),(50,'demobus1','demobus1@gmail.com',1,NULL,1,'Business',NULL,'2015-03-19 00:00:00',NULL,49,46,'yes',0,3,9,3),(51,'demoteamlead','demoteamlead@gmail.com',1,NULL,1,'Team lead',NULL,'2015-03-19 00:00:00',NULL,50,46,'yes',0,2,9,2),(52,'demoteamlead','demoteamlead@gmail.com',1,NULL,1,'Team lead',NULL,'2015-03-19 00:00:00',NULL,51,46,'yes',0,2,9,2),(53,'demohead','demohead@gmail.com',1,NULL,1,'Head',NULL,'2015-03-19 00:00:00',NULL,52,53,'yes',0,2,9,1),(54,'demohead1','demohead1@gmail.com',1,NULL,1,'head',NULL,'2015-03-19 00:00:00',NULL,53,54,'yes',0,2,10,1),(55,'demoadmin','demoadmin@gmail.com',1,NULL,1,'admin',NULL,'2015-03-19 00:00:00',NULL,54,55,'yes',0,1,10,4),(56,'korgentadmin','korgentadmin1@gmail.com',1,NULL,1,'admin',NULL,'2015-03-19 00:00:00',NULL,55,56,'yes',0,1,11,4),(57,'korgentadmin','korgentadmin@gmail.com',1,NULL,1,'admin',NULL,'2015-03-19 00:00:00',NULL,56,57,'yes',0,1,11,4),(58,'korgenthead','korgenthead@gmail.com',1,NULL,1,'head',NULL,'2015-03-19 00:00:00',NULL,57,58,'yes',0,2,11,1),(59,'korgentteamlead','korgentteamlead@gmail.com',1,NULL,1,'team lead',NULL,'2015-03-19 00:00:00',NULL,58,58,'yes',0,2,11,2),(60,'korgentfin1','korgentfin1@gmail.com',1,NULL,1,'finance',NULL,'2015-03-19 00:00:00',NULL,59,59,'yes',0,2,11,3),(61,'korgentfin2','korgentfin2@gmail.com',1,NULL,1,'Finance',NULL,'2015-03-19 00:00:00',NULL,60,59,'yes',0,2,11,3),(62,'korgentit1','korgentit1@gmail.com',1,NULL,1,'it',NULL,'2015-03-19 00:00:00',NULL,61,59,'yes',0,1,11,3),(63,'korgentbus1','korgentbus1@gmail.com',1,NULL,1,'business',NULL,'2015-03-19 00:00:00',NULL,62,59,'yes',0,3,11,3),(64,'wc','korgentwc@gmail.com',1,NULL,1,'wc manager',NULL,'2015-04-06 00:00:00',NULL,63,64,'no',0,1,11,5),(65,'env','korgentenv@gmail.com',1,NULL,1,'env',NULL,'2015-04-06 00:00:00',NULL,64,65,'no',0,1,11,5),(66,'zongAdmin','junaidp@gmail.com',1,NULL,1,'admin',NULL,'2015-05-27 00:00:00',NULL,65,66,'yes',0,1,12,4),(67,'Sulman','Sulman.javed@zong.com.pk',1,NULL,1,'Audit Head',NULL,'2015-05-27 00:00:00',NULL,66,67,'yes',0,2,12,1),(68,'Adnan','Adnan.hashmi@zong.com.pk',1,NULL,1,'Team lead',NULL,'2015-05-27 00:00:00',NULL,67,67,'yes',0,2,12,2),(69,'umar','Umar.hafeez@zong.com.pk',1,NULL,1,'Management',NULL,'2015-05-27 00:00:00',NULL,68,69,'no',0,2,12,5),(71,'jubileeAdmin','jubileeadmin@email.com',1,NULL,1,'admin',NULL,'2015-06-05 00:00:00',NULL,70,71,'yes',0,1,13,4),(72,'Adeel','Adeel.Khan@jubileelife.com',1,NULL,1,'Head',NULL,'2015-06-05 00:00:00',NULL,71,72,'yes',0,2,13,1),(73,'waseem','waseem.hassan@jubileelife.com',1,NULL,1,'Team lead',NULL,'2015-06-05 00:00:00',NULL,72,72,'yes',0,2,13,2),(74,'faizan','Faizan.Ahmed_ia@jubileelife.com',1,NULL,1,'Team lead',NULL,'2015-06-05 00:00:00',NULL,73,72,'yes',0,2,13,2),(75,'asif','asif.akbar@jubileelife.com',1,NULL,1,'management',NULL,'2015-06-10 00:00:00',NULL,74,75,'no',0,2,13,5),(76,'admin','admin@bestway.com',1,NULL,1,'admin',NULL,'2015-08-24 00:00:00',NULL,75,76,'yes',0,1,14,4),(77,'Amjad Ghaffar','amjad@bestway.com.pk',1,NULL,1,'Head IA',NULL,'2015-08-24 00:00:00',NULL,76,77,'yes',0,2,14,1),(78,'Adnan Waqar','audit3@bestway.com.pk',1,NULL,1,'Team lead',NULL,'2015-08-24 00:00:00',NULL,77,77,'yes',0,2,14,2),(79,'Saqib Ghafoor','audit2@bestway.com.pk',1,NULL,1,'auditee',NULL,'2015-08-24 00:00:00',NULL,78,79,'no',0,2,14,5),(80,'bugadmin','buga@emai.com',1,NULL,1,'Admin',NULL,'2018-02-22 00:00:00',NULL,79,80,'yes',0,1,15,4),(81,'bHead','bHead@email.com',1,NULL,1,'head',NULL,'2018-02-22 00:00:00',NULL,80,81,'yes',0,2,15,1),(82,'bteamlead','bteamlead@emai.com',1,NULL,1,'team lead',NULL,'2018-02-22 00:00:00',NULL,81,81,'yes',0,2,15,2),(83,'bauditor','bauditor@email.com',1,NULL,1,'auditor',NULL,'2018-02-22 00:00:00',NULL,82,82,'yes',0,2,15,3),(84,'Head zohaib','zohaib1112@hotmail.com',1,NULL,1,'',NULL,'2018-02-26 00:00:00',NULL,83,84,'yes',0,2,16,1),(85,'Admin Black Panther','adminblack@email.com',1,NULL,1,'Admin',NULL,'2018-02-26 00:00:00',NULL,84,85,'yes',0,1,17,4),(86,'Head Black Panther','Headblack@email.com',1,NULL,1,'Head',NULL,'2018-02-26 00:00:00',NULL,85,86,'yes',0,2,17,1),(87,'Team Lead Black Panther','teamleadblack@email.com',1,NULL,1,'teamlead',NULL,'2018-02-26 00:00:00',NULL,86,86,'yes',0,2,17,2),(88,'Auditor Black Panther','auditorblack@email.com',1,NULL,1,'auditor',NULL,'2018-02-26 00:00:00',NULL,87,87,'yes',0,2,17,3),(89,'Management Black Panther','managementblack@email.com',1,NULL,1,'management',NULL,'2018-02-26 00:00:00',NULL,88,89,'no',0,2,17,5),(90,'Honda Admin','adminhonda@email.com',1,NULL,1,'admin',NULL,'2018-03-01 00:00:00',NULL,89,90,'yes',0,1,18,4),(91,'Honda ','honda@email.com',1,NULL,1,'admin',NULL,'2018-03-01 00:00:00',NULL,90,91,'yes',0,1,18,4),(92,'Head on internal Audit','headhonda@email.com',1,NULL,1,'Head on internal Audit',NULL,'2018-03-01 00:00:00',NULL,91,92,'yes',0,3,18,1),(93,'Team Lead','teamleadhonda@email.com',1,NULL,1,'teamlead',NULL,'2018-03-01 00:00:00',NULL,92,92,'yes',0,2,18,2),(94,'Auditor','auditorhonda@email.com',1,NULL,1,'auditor',NULL,'2018-03-01 00:00:00',NULL,93,93,'yes',0,2,18,3),(95,'Management','managementhonda@email.com',1,NULL,1,'management',NULL,'2018-03-01 00:00:00',NULL,94,95,'no',0,3,18,5),(96,'Zohaib Abbasi','zohaib.abbasi@hyphenconsultancy.com',1,NULL,1,'Senior Business Analyst',NULL,'2018-03-07 00:00:00',NULL,95,96,'yes',0,1,19,4),(97,'Nauman Sohail','n.sohail@alsuhaimi.net',1,NULL,1,'Group Chief Audit Executive',NULL,'2018-03-07 00:00:00',NULL,96,97,'yes',0,2,19,1),(98,'Saeed','saeed@alsuhaimi.net',1,NULL,1,'Team Lead',NULL,'2018-03-07 00:00:00',NULL,97,97,'yes',0,2,19,2);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exception`
--

DROP TABLE IF EXISTS `exception`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exception` (
  `exception_id` int(11) NOT NULL AUTO_INCREMENT,
  `detail` varchar(255) NOT NULL,
  `jobcreation_id` int(11) NOT NULL,
  `responsiblePerson` int(10) unsigned DEFAULT '0',
  `divisionHead` int(10) unsigned DEFAULT '0',
  `dueDate` datetime DEFAULT NULL,
  `jobName` varchar(45) DEFAULT NULL,
  `implementationDate` datetime DEFAULT NULL,
  `managementComments` varchar(200) DEFAULT NULL,
  `auditHead` int(10) unsigned DEFAULT '0',
  `status` varchar(45) DEFAULT NULL,
  `emailSent` char(1) DEFAULT '0',
  `implementaionComments` varchar(200) DEFAULT NULL,
  `isImplemented` char(1) DEFAULT '0',
  `finalStatus` varchar(45) DEFAULT NULL,
  `initialStatus` varchar(45) DEFAULT NULL COMMENT 'if exception approved by internal audit head then it will sent to Management..',
  `auditStep` int(10) DEFAULT NULL,
  `year` int(10) unsigned DEFAULT NULL,
  `companyId` int(10) unsigned NOT NULL DEFAULT '0',
  `comments` varchar(45) DEFAULT NULL,
  `recommendations` varchar(200) DEFAULT NULL,
  `isAgreed` char(1) DEFAULT NULL,
  PRIMARY KEY (`exception_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exception`
--

LOCK TABLES `exception` WRITE;
/*!40000 ALTER TABLE `exception` DISABLE KEYS */;
INSERT INTO `exception` VALUES (4,'xx',2,9,9,'2015-01-09 00:00:00','t4unit','2015-01-08 00:00:00','Mgm Comments heree',1,'Approved','0','Finally Implemented','1','Approved','Approved',3,2014,1,NULL,'r','1'),(5,'jbhhbk',3,9,9,'2015-01-06 00:00:00','u67','2015-01-30 00:00:00','ok extendtion',1,'Approved','0','Implemented','1','Approved','Approved',1,2015,1,'Not okk','r','1'),(6,'2nd ecx',3,9,9,'2015-01-15 00:00:00','u67','2015-01-22 00:00:00','will',1,'Approved','0','not imemented','0','Approved','Approved',1,2015,1,'ok','r','1'),(7,'11',3,9,9,'2015-01-15 00:00:00','u67','2015-01-28 00:00:00','kmlk',1,'Approved','0','final comments hre','0','Approved','Approved',2,2015,1,'ok','r','1'),(8,'12',3,9,9,'2015-01-08 00:00:00','u67','2015-01-21 00:00:00','JJKll',1,'Approved','0','ok jiinow','0','Approved','Approved',2,2015,1,'Enter Comments','r','1'),(9,'exxx111',4,23,23,'2015-01-16 00:00:00','unitj','2015-01-24 00:00:00','mg1come',10,'Approved','0','finally','0','Approved','Approved',3,2015,3,'ok','r','1'),(10,'lopppppppppppppp',4,23,23,'2015-01-17 00:00:00','unitj','2015-01-23 00:00:00','loppcomme..',10,'Approved','0','kkk','0','Approved','Approved',4,2015,3,'ok','r','1'),(11,'elast1',5,23,23,'2015-01-16 00:00:00','last Unit','2015-01-30 00:00:00','com1',10,'Approved','0','finallyyyy','1','Approved','Approved',5,2015,3,'ok','r','0'),(12,'elastt22',5,23,23,'2015-01-30 00:00:00','last Unit','2015-02-19 00:00:00','com2',10,'Approved','0','finallyy','1','Approved','Approved',6,2015,3,'ok','r','0'),(13,'nOT APPROVED',10,44,44,'2015-02-19 00:00:00','Compliance with Companies Ordinance','2015-02-19 00:00:00','Agreed and will be implemented',37,'Approved','0','It will take another day to implement','0','Approved','Approved',10,2015,8,'Enter Comments','  ','0'),(14,'Not reconciled',10,44,44,'2015-02-19 00:00:00','Compliance with Companies Ordinance',NULL,'We do  not agree',37,'Approved','0',NULL,'0',NULL,'Approved',8,2015,8,'Enter Comments',' ','0'),(15,'Reporting not accurate',11,44,44,'2015-02-19 00:00:00','Reporting','2015-02-21 00:00:00','',37,'Sent','0','','0',NULL,'Approved',11,2015,8,'ok',' ','0'),(16,'Reconciliation not reviewed',14,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,12,2015,11,NULL,'r','0'),(17,'Items not performed',14,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,13,2015,11,NULL,'r','0'),(18,'Nothing performed',14,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,14,2015,11,NULL,'r','0'),(19,'e1!1',18,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,15,2015,4,NULL,'r','1'),(21,'77',18,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,15,2015,4,NULL,'r','1'),(22,'Data not reconciled properly',13,64,64,'2015-04-06 00:00:00','Working Capital Management','2015-04-10 00:00:00','Agreed',58,'Approved','0',NULL,'0',NULL,'Approved',16,2015,11,'Enter Comments','r','1'),(23,'Reconcling report is not approved by authorized',13,64,64,'2015-04-06 00:00:00','Working Capital Management','2015-04-17 00:00:00','Agreed',58,'Approved','0',NULL,'0',NULL,'Approved',17,2015,11,'Enter Comments','r','1'),(24,'Doing multitasking more than required',13,64,64,'2015-04-06 00:00:00','Working Capital Management',NULL,'Do not agree and can not give implementation data',58,'Rejected','0',NULL,'0',NULL,'Approved',18,2015,11,'Need to discuss','r','1'),(25,'No software exists for inventory management',13,64,64,'2015-04-06 00:00:00','Working Capital Management','2015-04-24 00:00:00','Agreed',58,'Approved','0',NULL,'0',NULL,'Approved',20,2015,11,'Enter Comments','r','1'),(26,'Compliance with laws',15,65,65,'2015-04-06 00:00:00','Information Technology','2015-04-06 00:00:00','Agreed',58,'Approved','0','Implemented','1','Approved','Approved',23,2015,11,'Enter Comments','r','1'),(27,'Dashboard reporting',15,65,65,'2015-04-06 00:00:00','Information Technology','2015-04-07 00:00:00','Agree',58,'Approved','0','We have implemented this','1','Approved','Approved',23,2015,11,'Enter Comments','r','1'),(28,'Carbon credits',15,65,65,'2015-04-06 00:00:00','Information Technology','2015-04-06 00:00:00','Agreed',58,'Approved','0','Implemented. Ok done','1','Approved','Approved',24,2015,11,'You also need to do some othe stuff','r','1'),(29,'Plant monitoring',15,65,65,'2015-04-06 00:00:00','Information Technology','2015-04-07 00:00:00','Agreed',58,'Approved','0','Implemented','1','Approved','Approved',25,2015,11,'Enter Comments','r','1'),(30,'kjjjpp',17,9,9,'2015-04-16 00:00:00','ui','2015-04-22 00:00:00','comm',16,'Approved','0','my final 1','1','Approved','Approved',26,2015,4,'put date  hh','rec1','0'),(31,'99',17,9,9,'2015-04-17 00:00:00','ui','2015-04-30 00:00:00','kk',16,'Approved','0','Help','0','Sent','Approved',27,2015,4,'ok','r22','0'),(32,'88',17,9,9,'2015-04-18 00:00:00','ui','2015-04-16 00:00:00','gg',16,'Approved','0','my final comm','1','Approved','Approved',28,2015,4,'oksssssssssssssssssssssssssssssssssssssssssss','rec333','0'),(33,'66',17,9,9,'2015-04-19 00:00:00','ui','2015-04-22 00:00:00','dd',16,'Approved','0','my finalmy fna','0','Approved','Approved',30,2015,4,'initall appapp',' My Recommendation','0'),(34,'Waste not disposed off properly',16,64,64,'2015-04-15 00:00:00','Environmental regulations','2015-05-06 00:00:00','x',58,'Approved','0',NULL,'0',NULL,'Approved',31,2015,11,'Not agreed','Please do appropriately','1'),(35,'Waste diposal not approved',16,65,65,'2015-04-15 00:00:00','Environmental regulations','2015-04-15 00:00:00','We have agreed and we will do this or whatever',58,'Approved','0','We have implemented','1','Approved','Approved',31,2015,11,'Enter Comments','Please do appropriately','0'),(36,'Water not disposed off propoerly',16,65,65,'2015-04-15 00:00:00','Environmental regulations','2015-04-17 00:00:00','We have agreed and we will do this or whatever',58,'Approved','0','ddd','0','Approved','Approved',32,2015,11,'Enter Comments','Please do appropriately','0'),(37,'Water disposal not approved',16,65,65,'2015-04-15 00:00:00','Environmental regulations','2015-04-16 00:00:00','We have agreed and we will do this or whatever',58,'Approved','0','Implemented','1','Approved','Approved',32,2015,11,'Change implementation date','Please do appropriately','0'),(38,'Plant maintenenace not appropraite',16,65,65,'2015-04-15 00:00:00','Environmental regulations','2015-04-15 00:00:00','We have agreed and we will do this or whatever',58,'Approved','0','Implemented','1','Approved','Approved',33,2015,11,'We need to discuss','Please do appropriately','0'),(39,'Plant main not scheduled',16,65,65,'2015-04-15 00:00:00','Environmental regulations','2015-05-04 00:00:00','We have agreed and we will do this or whatever. Updated',58,'Approved','0','im','1','Approved','Approved',33,2015,11,'We need to discuss','Please do appropriately','0'),(40,'No documenary evidence of approval',19,64,64,'2015-05-03 00:00:00','Risk assessment','2015-05-05 00:00:00','Agreed',58,'Approved','0','Implemented','1','Approved','Approved',34,2015,11,'Enter Comments','We recommend that all the related documents are appropriately stored.\n\nEvidence should be kept in hard as well as soft files','1'),(41,'Bank account can be opened by anyone',19,64,64,'2015-05-03 00:00:00','Risk assessment','2015-05-05 00:00:00','Agreed',58,'Approved','0','Implemented','1','Approved','Approved',35,2015,11,'Enter Comments','Only through directors approval bank account can be opened','1'),(42,'Automated controls not effective',19,64,64,'2015-05-03 00:00:00','Risk assessment','2015-05-05 00:00:00','Agreed',58,'Approved','0','Implemented','1','Approved','Approved',35,2015,11,'Enter Comments','All the controls should be very effective','1'),(43,'Data not stored at pre defined frequency',19,64,64,'2015-05-03 00:00:00','Risk assessment','2015-05-21 00:00:00','Agreed',58,'Approved','0','Implemented','1','Approved','Approved',35,2015,11,'cant be implemented in this time frame','All the documents should be stored.','1'),(44,'Analytical review not performed',19,64,64,'2015-05-03 00:00:00','Risk assessment','2015-05-05 00:00:00','Ok we can implement',58,'Approved','0','Implemented','1','Approved','Approved',36,2015,11,'Need to discuss','Analysis to be performed','1'),(45,'tax rates not approved',22,64,64,'2015-05-05 00:00:00','Taxation',NULL,NULL,58,NULL,'0',NULL,'0',NULL,'Approved',37,2015,11,'Enter Comments','Tax rates to be approved by senior manager','0'),(46,'calculation not checked',22,64,64,'2015-05-06 00:00:00','Taxation','2015-08-24 00:00:00','It can be implemented',58,'Approved','0','Implemented','1','Approved','Approved',37,2015,11,'Enter Comments','To be checked','0'),(47,'It assets not propoerly tagged',21,65,65,'2015-06-11 00:00:00','arsie',NULL,NULL,58,NULL,'0',NULL,'0',NULL,'',38,2015,11,NULL,'Pleadse tag','0'),(48,'LKJASLDFHNASLKDF',21,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,38,2015,11,NULL,NULL,'0'),(49,'No documentary evidence was available.',25,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,40,2015,11,NULL,NULL,'0'),(50,'Cost controls were not effective.',25,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,40,2015,11,NULL,NULL,'0'),(51,'No documentary evidence was available',26,64,64,'2015-08-27 00:00:00','portfolio management','2015-08-27 00:00:00','Agreed',58,'Approved','0','Implemented.','0','Approved','Approved',42,2015,11,'Agreed','We recommend that all documents are properly stored.','1'),(52,'ex1',29,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,43,2018,15,NULL,NULL,'0'),(53,'ex2',29,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,43,2018,15,NULL,NULL,'0'),(54,'abc',30,89,89,'2018-02-28 00:00:00','Sales','2018-02-26 00:00:00','Agreed',86,'Approved','0','abc','1','Approved','Approved',44,2018,17,'Enter Comments','abc','1'),(55,'sddsfdfsdff',31,89,89,'2018-02-28 00:00:00','Purchases','2018-02-28 00:00:00','dgdfgdgdfgdfg',86,'Approved','0','cdsfdsafadffsdz','1','Approved','Approved',45,2018,17,'dvxvxvvvc','vxvcxvxv','1'),(56,'sdfsfsfd',31,89,89,'2018-02-28 00:00:00','Purchases','2018-02-28 00:00:00','sdsgdsgdgdg',86,'Approved','0','dssfsfcsfc','0','Approved','Approved',46,2018,17,'cxvxcvxcvcvxxcv','cxvvxvxvx','0'),(57,'sdfsf',32,89,89,'2018-04-04 00:00:00','Expenses',NULL,NULL,86,NULL,'0',NULL,'0',NULL,'',47,2018,17,NULL,'fgdsg','0'),(58,'abc',34,95,95,'2018-04-19 00:00:00','Purchases','2018-04-05 00:00:00','Agreed',92,'Approved','0','Implemented','1','Approved','Approved',49,2018,18,'Enter Comments','No vendor selection mechanism found. ','1'),(59,'abc',35,95,95,'2018-04-06 00:00:00','Expenses','2018-04-06 00:00:00','abc',92,'Approved','0','implemented.','1','Approved','Approved',50,2018,18,'Enter Comments','abc','1'),(60,'abc',35,95,95,'2018-04-06 00:00:00','Expenses','2018-04-06 00:00:00','abc',92,'Approved','0','Implemented.','1','Approved','Approved',51,2018,18,'yess','','1'),(61,'abc',36,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,52,2018,18,NULL,NULL,'0'),(62,'q',37,95,95,'2018-07-06 00:00:00','Material cost','2018-07-06 00:00:00','sada',92,'Approved','0','sddgv','1','Approved','Approved',53,2018,18,'Enter Comments','sddf','1');
/*!40000 ALTER TABLE `exception` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `heading` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `employee` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,'Test feedback','testing desc',92);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_employee_relation`
--

DROP TABLE IF EXISTS `job_employee_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_employee_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employeeid` int(11) NOT NULL,
  `jobcreationid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=189 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_employee_relation`
--

LOCK TABLES `job_employee_relation` WRITE;
/*!40000 ALTER TABLE `job_employee_relation` DISABLE KEYS */;
INSERT INTO `job_employee_relation` VALUES (1,2,1),(2,1,1),(3,2,2),(4,5,2),(5,1,2),(6,2,3),(7,5,3),(8,1,3),(9,12,4),(11,12,4),(12,13,4),(13,10,4),(14,12,5),(15,13,5),(16,10,5),(17,37,6),(18,37,7),(19,37,8),(20,41,9),(21,37,9),(22,39,10),(23,37,10),(24,39,11),(25,37,11),(26,37,12),(27,40,12),(28,37,12),(29,40,12),(30,37,12),(31,40,12),(32,37,12),(95,60,14),(96,58,14),(104,60,16),(105,61,16),(106,58,16),(107,62,15),(108,58,15),(109,18,17),(110,16,17),(111,17,18),(112,16,18),(113,60,13),(114,61,13),(115,58,13),(116,59,19),(117,60,19),(118,61,19),(119,58,19),(120,59,20),(121,60,20),(122,61,20),(123,58,20),(124,59,21),(125,60,21),(126,61,21),(127,62,21),(128,63,21),(129,58,21),(130,60,22),(131,61,22),(132,58,22),(133,61,23),(134,58,23),(139,60,24),(140,58,24),(144,58,25),(145,59,26),(146,58,26),(155,58,27),(156,58,28),(157,82,29),(158,83,29),(159,81,29),(160,87,30),(161,88,30),(162,86,30),(166,87,31),(167,88,31),(168,86,31),(169,87,32),(170,88,32),(171,86,32),(172,94,33),(173,93,33),(174,92,33),(175,94,34),(176,93,34),(177,92,34),(178,94,35),(179,93,35),(180,92,35),(181,94,36),(182,93,36),(183,92,36),(184,94,37),(185,93,37),(186,92,37),(187,93,38),(188,92,38);
/*!40000 ALTER TABLE `job_employee_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_skill_relation`
--

DROP TABLE IF EXISTS `job_skill_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_skill_relation` (
  `jobskillrelation` int(11) NOT NULL AUTO_INCREMENT,
  `softskillid` int(11) NOT NULL,
  `jobid` int(11) NOT NULL,
  PRIMARY KEY (`jobskillrelation`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_skill_relation`
--

LOCK TABLES `job_skill_relation` WRITE;
/*!40000 ALTER TABLE `job_skill_relation` DISABLE KEYS */;
INSERT INTO `job_skill_relation` VALUES (1,1,7),(2,1,17),(3,1,12),(4,1,25),(5,1,25),(6,1,26),(7,1,28),(8,1,30),(9,1,31),(10,1,33),(11,1,34),(12,1,37),(13,3,107),(14,2,107),(15,2,107),(16,2,107),(17,1,134),(18,1,135),(19,1,135),(20,1,135),(21,1,134),(22,1,134),(23,1,135),(24,1,134),(25,1,135),(26,1,134),(27,1,134),(28,1,134),(29,1,134),(30,1,134),(31,1,134),(32,1,134),(33,1,134),(34,1,134),(35,1,134),(36,1,135),(37,1,134),(38,1,135),(39,1,134),(40,1,135),(41,1,134),(42,1,134),(43,1,137),(44,1,140),(45,1,137),(46,1,22),(47,1,143),(48,1,134),(49,1,144),(50,2,144),(51,1,146),(52,1,147),(53,1,153),(54,1,158),(55,1,159),(56,1,159),(57,1,159),(58,2,188),(59,2,188),(60,2,188),(61,1,188),(62,2,188),(63,2,192),(64,1,198),(65,2,198),(66,3,198),(67,1,198),(68,2,198),(69,3,198),(70,1,198),(71,2,198),(72,3,198),(73,2,198),(74,1,198),(75,2,198),(76,3,198),(77,1,198),(78,2,198),(79,3,198),(80,1,198),(81,2,198),(82,3,198),(83,1,136),(84,1,204),(85,2,205),(86,3,205),(87,1,208),(88,2,208),(89,3,208),(90,1,208),(91,2,208),(92,3,208),(93,2,209),(94,3,209),(95,1,210),(96,2,210),(97,3,210),(98,2,212),(99,3,212),(100,2,213),(101,3,213),(102,2,214),(103,3,214),(104,1,215),(105,2,215),(106,3,215),(107,2,216),(108,3,216);
/*!40000 ALTER TABLE `job_skill_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobandareaofexpertise`
--

DROP TABLE IF EXISTS `jobandareaofexpertise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobandareaofexpertise` (
  `jobandareaid` int(11) NOT NULL AUTO_INCREMENT,
  `jobid` int(11) NOT NULL,
  `departmentid` int(11) NOT NULL,
  `ischecked` tinyint(4) NOT NULL,
  PRIMARY KEY (`jobandareaid`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobandareaofexpertise`
--

LOCK TABLES `jobandareaofexpertise` WRITE;
/*!40000 ALTER TABLE `jobandareaofexpertise` DISABLE KEYS */;
INSERT INTO `jobandareaofexpertise` VALUES (1,7,1,0),(2,7,2,1),(3,7,3,1),(4,17,1,0),(5,17,2,1),(6,17,3,0),(7,12,1,0),(8,12,2,1),(9,12,3,1),(10,25,1,0),(11,25,2,1),(12,25,3,0),(13,26,1,1),(14,26,2,1),(15,26,3,0),(16,28,1,0),(17,28,2,0),(18,28,3,1),(19,30,1,0),(20,30,2,1),(21,30,3,0),(22,31,1,0),(23,31,2,1),(24,31,3,0),(25,33,1,1),(26,33,2,0),(27,33,3,0),(28,34,1,1),(29,34,2,0),(30,34,3,0),(31,37,1,0),(32,37,2,1),(33,37,3,0),(34,107,1,0),(35,107,2,1),(36,107,3,0),(37,108,1,0),(38,108,2,1),(39,108,3,0),(40,110,1,0),(41,110,2,1),(42,110,3,0);
/*!40000 ALTER TABLE `jobandareaofexpertise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobcreation`
--

DROP TABLE IF EXISTS `jobcreation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobcreation` (
  `jobcreationid` int(11) NOT NULL AUTO_INCREMENT,
  `domain` varchar(255) NOT NULL,
  `relevantdept` varchar(255) NOT NULL,
  `riskrating` varchar(255) NOT NULL,
  `weeks` int(11) NOT NULL,
  `technical` varchar(255) NOT NULL,
  `jobid` int(11) NOT NULL,
  `jobname` varchar(255) DEFAULT NULL,
  `startdate` varchar(100) DEFAULT NULL,
  `enddate` varchar(100) DEFAULT NULL,
  `reportStatus` int(10) unsigned DEFAULT '1',
  `auditHead` int(10) unsigned DEFAULT '0',
  `year` int(10) unsigned DEFAULT NULL,
  `companyId` int(10) unsigned NOT NULL DEFAULT '0',
  `approved` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`jobcreationid`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobcreation`
--

LOCK TABLES `jobcreation` WRITE;
/*!40000 ALTER TABLE `jobcreation` DISABLE KEYS */;
INSERT INTO `jobcreation` VALUES (1,'Strategic','Finance','Low',1,'',7,'un11','01/15/15','01/22/15',0,1,2014,1,'0'),(2,'Strategic','Finance','Medium',1,'tec',17,'t4unit','02/12/15','02/19/15',3,1,2014,1,'0'),(3,'Strategic','Finance','High',1,'e',12,'u67','01/02/15','01/09/15',3,1,2015,1,'0'),(4,'Strategic','Finance','Medium',1,'tech',25,'unitj','02/06/15','02/13/15',3,10,2015,3,'0'),(5,'Strategic','Finance','Medium',1,'tec',26,'last Unit','03/20/15','03/27/15',3,10,2015,3,'0'),(6,'Operations','Business','Low',1,'',28,'Appropriate inventory levels are to be maintained','02/01/15','02/08/15',0,37,2015,8,'0'),(7,'Compliance','Regulatory','High',2,'',30,'Compiance with OGRA regulations specific to inventory','02/01/15','02/15/15',0,37,2015,8,'0'),(8,'Strategic','Business','Medium',3,'',31,'Vertical acquisition','02/01/15','02/22/15',0,37,2015,8,'0'),(9,'Reporting','Finance','High',6,'',33,'Developement of ICFR framewrok','02/01/15','03/15/15',0,37,2015,8,'0'),(10,'Compliance','Finance','Medium',10,'',34,'Compliance with Companies Ordinance','02/09/15','04/20/15',3,37,2015,8,'0'),(11,'Strategic','Finance','Low',1,'',37,'Reporting','02/19/15','02/26/15',2,37,2015,8,'0'),(12,'Strategic','IT','High',1,'',107,'Auditable Unit 1',NULL,NULL,0,37,2018,8,'0'),(13,'Operations','Procurement and Logistics','High',3,'',134,'Working Capital Management','17-07-15','07-08-15',4,58,2015,11,'1'),(14,'Reporting','Finance','High',1,'',135,'Financial Reporting','05-05-15','12-05-15',1,58,2015,11,'1'),(15,'Strategic','IT','Low',1,'',137,'Information Technology','01-03-15','08-03-15',5,58,2015,11,'1'),(16,'Compliance','Regulatory','Medium',3,'',140,'Environmental regulations','01-05-15','22-05-15',5,58,2015,11,'1'),(17,'Strategic','IT','Medium',1,'',22,'ui','01-04-15','08-04-15',3,16,2015,4,'0'),(18,'Strategic','IT','Low',1,'',143,'NewAudit','22-05-15','29-05-15',1,16,2015,4,'0'),(19,'Strategic','IT','Medium',10,'',144,'Risk assessment','07-09-15','16-11-15',5,58,2015,11,'1'),(20,'Strategic','IT','High',7,'',146,'Fixed Assets','01-01-15','19-02-15',0,58,2015,11,'1'),(21,'Strategic','IT','Low',9,'',147,'arsie','01-06-15','03-08-15',1,58,2015,11,'1'),(22,'Compliance','Finance','Medium',4,'',153,'Taxation','01-03-15','29-03-15',5,58,2015,11,'1'),(23,'Strategic','IT','Low',2,'',158,'stock','09-06-15','23-06-15',0,58,2015,11,'1'),(24,'Strategic','IT','Low',2,'',159,'Sales','16-06-15','30-06-15',0,58,2015,11,'1'),(25,'Strategic','IT','Low',4,'',188,'Market diversification','25-06-15','23-07-15',1,58,2015,11,'1'),(26,'Strategic','IT','Low',2,'',192,'portfolio management','18-08-15','01-09-15',5,58,2015,11,'1'),(27,'Strategic','IT','Medium',0,'2',198,'Market share',NULL,NULL,0,58,2016,11,'0'),(28,'Compliance','Regulatory','High',2,'',136,'Regulatory','11-04-17','25-04-17',0,58,2017,11,'1'),(29,'Strategic','Finance','High',1,'',204,'Bunit1','24-02-18','03-03-18',1,81,2018,15,'1'),(30,'Strategic','Strategy','High',4,'',205,'Sales','26-02-18','26-03-18',5,86,2018,17,'1'),(31,'Strategic','Business','Medium',1,'',208,'Purchases','26-03-18','02-04-18',5,86,2018,17,'1'),(32,'Strategic','Strategy','Low',1,'',209,'Expenses','26-04-18','03-05-18',1,86,2018,17,'1'),(33,'Strategic','Strategy','High',2,'',210,'Sales','01-03-18','15-03-18',0,92,2018,18,'1'),(34,'Strategic','Strategy','High',1,'',212,'Purchases','01-04-18','08-04-18',5,92,2018,18,'1'),(35,'Strategic','Strategy','High',1,'',213,'Expenses','01-05-18','08-05-18',5,92,2018,18,'1'),(36,'Strategic','Business','High',1,'',214,'Assets','01-06-18','08-06-18',1,92,2018,18,'1'),(37,'Strategic','Business','High',1,'',215,'Material cost','06-07-18','13-07-18',5,92,2018,18,'1'),(38,'Strategic','Strategy','Low',1,'',216,'sales1','06-08-18','13-08-18',0,92,2018,18,'1');
/*!40000 ALTER TABLE `jobcreation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobtimeestimation`
--

DROP TABLE IF EXISTS `jobtimeestimation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobtimeestimation` (
  `jobTimeEstimationId` int(5) NOT NULL AUTO_INCREMENT,
  `scopeOfWork` varchar(255) NOT NULL,
  `estimatedWeeks` int(3) NOT NULL,
  `fieldWorkManHours` int(5) DEFAULT NULL,
  `managementHours` int(11) DEFAULT NULL,
  `totalWorkingManHours` int(11) DEFAULT NULL,
  `placeOfWork` varchar(100) DEFAULT NULL,
  `travelDays` int(11) DEFAULT NULL,
  `hoursInclTravel` int(11) DEFAULT NULL,
  `jobid` int(11) DEFAULT NULL,
  `year` int(10) unsigned DEFAULT NULL,
  `companyId` int(10) unsigned NOT NULL DEFAULT '0',
  `approved` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`jobTimeEstimationId`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobtimeestimation`
--

LOCK TABLES `jobtimeestimation` WRITE;
/*!40000 ALTER TABLE `jobtimeestimation` DISABLE KEYS */;
INSERT INTO `jobtimeestimation` VALUES (1,'scopeee',1,40,1,121,'Outstation',0,121,7,2014,1,'0'),(2,'scopiii',1,40,1,41,'Outstation',0,41,17,2014,1,'0'),(3,'sc',1,40,1,41,'Inhouse',0,41,12,2015,1,'0'),(4,'scopeee',1,80,1,121,'Outstation',0,121,25,2015,3,'0'),(5,'scop',1,40,1,41,'Outstation',1,49,26,2015,3,'0'),(6,'OGRA',2,160,30,190,'Inhouse',0,190,30,2015,8,'0'),(7,'Inventory',1,80,20,100,'Outstation',1,108,28,2015,8,'0'),(8,'Vertical',1,80,60,140,'Outstation',0,140,31,2015,8,'0'),(9,'ICFR',6,240,50,290,'Outstation',0,290,33,2015,8,'0'),(10,'',10,400,100,500,'Outstation',0,500,34,2015,8,'0'),(11,'nmn',1,40,5,45,'Inhouse',0,45,37,2015,8,'0'),(12,'Focus should be on etc etc etc',1,40,1,121,'Outstation',1,129,107,2018,8,'0'),(13,'scope of work',1,40,1,41,'Outstation',0,41,108,2018,8,'0'),(14,'Cash flow managegement',3,240,20,260,'Inhouse',0,260,134,2015,11,'1'),(15,'',1,80,20,100,'Outstation',0,100,135,2015,11,'1'),(16,'test',1,80,1,81,'Outstation',0,81,137,2015,11,'1'),(17,'',3,240,20,260,'Outstation',0,260,140,2015,11,'1'),(18,'sc',1,40,1,41,'Outstation',0,41,22,2015,4,'0'),(19,'scc',1,40,1,41,'Outstation',0,41,143,2015,4,'0'),(20,'kqndwjkjdkjq',10,1200,100,1300,'Inhouse',0,1300,144,2015,11,'1'),(21,'dnadsamdn',7,1120,50,1170,'Inhouse',0,1170,146,2015,11,'1'),(22,'mnm',5,1000,200,1200,'Outstation',0,1200,147,2015,11,'1'),(23,'Taxatrion laws',4,320,25,345,'Outstation',2,361,153,2015,11,'1'),(24,'Fixed assets',2,80,50,130,'Outstation',0,130,149,2017,11,'1'),(25,'Fixed assets',2,80,50,130,'Outstation',0,130,136,2017,11,'1'),(26,'Current assets',1,40,50,90,'Outstation',0,90,148,2016,11,'1'),(27,'pricing slow moving inventory',2,160,20,180,'Outstation',0,180,158,2015,11,'1'),(28,'',1,80,10,90,'Outstation',0,90,160,2015,11,'1'),(29,'Managements skill and expertise is required to predict different market conditions',2,320,250,570,'Outstation',0,570,159,2015,11,'1'),(30,'Carrying out risk assessment for the new market.',4,320,120,440,'Outstation',3,464,188,2015,11,'1'),(31,'carrying out risk assessment',2,240,50,290,'Outstation',0,290,192,2015,11,'1'),(32,'test',1,40,1,41,'Outstation',0,41,198,2016,11,'1'),(33,'test',1,40,1,41,'Outstation',0,41,198,2016,11,'1'),(34,'This is high level of scope',1,40,2,42,'Outstation',1,50,204,2018,15,'1'),(35,'Management skills and expertise is required to predict market conditions',4,320,5,325,'Inhouse',0,325,205,2018,17,'1'),(36,'Management skills required to identify fashion trends',1,80,50,130,'Outstation',1,138,208,2018,17,'1'),(37,'Management skill required to identify expenses',1,120,10,130,'Outstation',0,130,209,2018,17,'1'),(38,'Management skills & expertise is required  to predict different market conditions.',2,160,100,260,'Outstation',2,276,210,2018,18,'1'),(39,'Management skill required',1,80,10,90,'Inhouse',0,90,212,2018,18,'1'),(40,'High',1,80,10,90,'Inhouse',0,90,213,2018,18,'1'),(41,'high',1,80,10,90,'Inhouse',0,90,214,2018,18,'1'),(42,'high',1,80,10,90,'Inhouse',0,90,215,2018,18,'1'),(43,'sf',1,80,10,90,'Inhouse',0,90,216,2018,18,'1');
/*!40000 ALTER TABLE `jobtimeestimation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resourceuse`
--

DROP TABLE IF EXISTS `resourceuse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resourceuse` (
  `resourceUseId` int(11) NOT NULL AUTO_INCREMENT,
  `skillId` int(11) NOT NULL,
  `noOfResources` int(11) NOT NULL,
  `jobId` int(10) unsigned NOT NULL DEFAULT '0',
  `year` int(10) unsigned DEFAULT NULL,
  `companyId` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`resourceUseId`),
  KEY `FK_Skills` (`skillId`),
  CONSTRAINT `FK_Skills` FOREIGN KEY (`skillId`) REFERENCES `skills` (`skillId`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resourceuse`
--

LOCK TABLES `resourceuse` WRITE;
/*!40000 ALTER TABLE `resourceuse` DISABLE KEYS */;
INSERT INTO `resourceuse` VALUES (1,1,1,7,2014,1),(2,2,0,7,2014,1),(3,3,0,7,2014,1),(4,1,0,17,0,1),(5,2,1,17,0,1),(6,3,0,17,0,1),(7,1,0,12,0,1),(8,2,1,12,0,1),(9,3,0,12,0,1),(10,1,0,25,2015,3),(11,2,2,25,2015,3),(12,3,0,25,2015,3),(13,1,0,26,2015,3),(14,2,1,26,2015,3),(15,3,0,26,2015,3),(16,1,1,30,2015,8),(17,2,1,30,2015,8),(18,3,0,30,2015,8),(19,1,1,28,2015,8),(20,2,1,28,2015,8),(21,3,0,28,2015,8),(22,1,1,31,2015,8),(23,2,1,31,2015,8),(24,3,0,31,2015,8),(25,1,1,33,2015,8),(26,2,0,33,2015,8),(27,3,0,33,2015,8),(28,1,1,34,2015,8),(29,2,0,34,2015,8),(30,3,0,34,2015,8),(31,1,0,37,2015,8),(32,2,1,37,2015,8),(33,3,0,37,2015,8),(34,1,0,107,2018,8),(35,2,1,107,2018,8),(36,3,0,107,2018,8),(37,1,1,108,2018,8),(38,2,0,108,2018,8),(39,3,0,108,2018,8),(40,1,0,134,2015,11),(41,2,2,134,2015,11),(42,3,0,134,2015,11),(43,1,1,135,2015,11),(44,2,1,135,2015,11),(45,3,0,135,2015,11),(46,1,2,137,2015,11),(47,2,0,137,2015,11),(48,3,0,137,2015,11),(49,1,0,140,2015,11),(50,2,2,140,2015,11),(51,3,0,140,2015,11),(52,1,1,22,2015,4),(53,2,0,22,2015,4),(54,3,0,22,2015,4),(55,1,0,143,2015,4),(56,2,1,143,2015,4),(57,3,0,143,2015,4),(58,1,0,144,2015,11),(59,2,3,144,2015,11),(60,3,0,144,2015,11),(61,1,0,146,2015,11),(62,2,3,146,2015,11),(63,3,1,146,2015,11),(64,1,1,147,2015,11),(65,2,4,147,2015,11),(66,3,0,147,2015,11),(67,1,0,153,2015,11),(68,2,2,153,2015,11),(69,3,0,153,2015,11),(70,1,1,149,2017,11),(71,2,0,149,2017,11),(72,3,0,149,2017,11),(73,1,1,136,2017,11),(74,2,0,136,2017,11),(75,3,0,136,2017,11),(76,1,1,148,2016,11),(77,2,0,148,2016,11),(78,3,0,148,2016,11),(79,1,1,158,2015,11),(80,2,1,158,2015,11),(81,3,0,158,2015,11),(82,1,0,160,2015,11),(83,2,2,160,2015,11),(84,3,0,160,2015,11),(85,1,1,159,2015,11),(86,2,2,159,2015,11),(87,3,0,159,2015,11),(88,1,1,188,2015,11),(89,2,0,188,2015,11),(90,3,0,188,2015,11),(91,1,1,192,2015,11),(92,2,1,192,2015,11),(93,3,1,192,2015,11),(94,1,1,198,2016,11),(95,2,0,198,2016,11),(96,3,0,198,2016,11),(97,1,0,204,2018,15),(98,2,1,204,2018,15),(99,3,0,204,2018,15),(100,1,0,205,2018,17),(101,2,2,205,2018,17),(102,3,0,205,2018,17),(103,1,0,208,2018,17),(104,2,2,208,2018,17),(105,3,0,208,2018,17),(106,1,0,209,2018,17),(107,2,2,209,2018,17),(108,3,0,209,2018,17),(109,1,0,210,2018,18),(110,2,2,210,2018,18),(111,3,0,210,2018,18),(112,1,0,212,2018,18),(113,2,2,212,2018,18),(114,3,0,212,2018,18),(115,1,0,213,2018,18),(116,2,2,213,2018,18),(117,3,0,213,2018,18),(118,1,0,214,2018,18),(119,2,2,214,2018,18),(120,3,0,214,2018,18),(121,1,0,215,2018,18),(122,2,2,215,2018,18),(123,3,0,215,2018,18),(124,1,0,216,2018,18),(125,2,2,216,2018,18),(126,3,0,216,2018,18);
/*!40000 ALTER TABLE `resourceuse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `risk`
--

DROP TABLE IF EXISTS `risk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `risk` (
  `risk_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text,
  `existing_control` text,
  `audit_engage_id` int(11) NOT NULL,
  `year` int(10) unsigned DEFAULT NULL,
  `companyId` int(10) unsigned NOT NULL DEFAULT '0',
  `status` int(10) unsigned NOT NULL DEFAULT '0',
  `initiatedBy` int(10) unsigned DEFAULT NULL,
  `approvedBy` int(10) unsigned DEFAULT NULL,
  `feedback` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`risk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `risk`
--

LOCK TABLES `risk` WRITE;
/*!40000 ALTER TABLE `risk` DISABLE KEYS */;
INSERT INTO `risk` VALUES (1,'des','con',10,2014,1,0,NULL,NULL,NULL),(2,'des2','vi3',10,2014,1,0,NULL,NULL,NULL),(3,'se','hj',11,2015,1,5,1,0,NULL),(4,'s2','h2',11,2015,1,5,1,0,NULL),(5,'s3()j','jk',11,2015,1,5,1,0,NULL),(6,'s3II','jk',11,2015,1,5,1,0,NULL),(7,'a','s',11,2015,1,5,1,0,NULL),(8,'d1 feed','con1',11,2015,1,1,5,2,NULL),(9,'d1','con1 feed',11,2015,1,1,5,2,NULL),(10,'d2','c2lkl',11,2015,1,1,5,2,NULL),(11,'sa','new',11,2015,1,1,2,2,NULL),(12,'d1','c1',24,2015,3,5,13,0,NULL),(13,'d2','c2',24,2015,3,5,13,0,NULL),(14,'d1','c1',24,2015,3,5,13,0,NULL),(15,'d1','c1',24,2015,3,5,13,0,NULL),(16,'d2','c2',24,2015,3,5,13,0,NULL),(17,'d1','c1',24,2015,3,5,13,0,NULL),(18,'r1','r111',24,2015,3,1,13,12,NULL),(19,'r2 ','r222',24,2015,3,1,13,12,NULL),(20,'r3','r333',24,2015,3,1,13,12,NULL),(21,'d1','c1',25,2015,3,1,13,10,NULL),(22,'non compliance','checking',30,2015,8,1,39,37,NULL),(23,'complexity','expert advice',30,2015,8,1,39,37,NULL),(24,'frequent changes','outsource',30,2015,8,1,39,37,NULL),(25,'mn ','mn',31,2015,8,4,39,0,NULL),(26,'knsdbvksdnf','kajsbasdkjfbadskn',33,2015,11,1,60,58,NULL),(27,',nmnnsd,msn','sm,ncdsa,mc',34,2015,11,1,60,58,NULL),(28,'zs,cnsad,mcan','zm,xcns,mcnasd,m',34,2015,11,1,60,58,NULL),(29,'lkajcnasmlkccma','mz,c nads,mcn as,m',34,2015,11,1,60,58,NULL),(30,',andcas,mcan','mnsacb asdm,bcnqa,m',34,2015,11,1,60,58,NULL),(31,'d12','e12',37,2015,4,1,18,16,NULL),(32,'d223a','e2223',37,2015,4,1,18,16,NULL),(36,'tt','tt',38,2015,4,1,17,16,''),(37,'ttddd..!','tcdd',38,2015,4,1,17,16,''),(38,'Waste management','123',36,2015,11,4,60,0,NULL),(39,'Water disposal','456',36,2015,11,4,60,0,NULL),(40,'Plant treatment','789',36,2015,11,4,60,0,NULL),(41,'Economic uncertainty','Economic forecasting',44,2015,11,1,60,58,''),(42,'Uncertain forecasts','Use tools and models to make forecasts reliable',45,2015,11,1,58,58,''),(43,'Uncertain forecasts','use tools to make reliable forecasts.',46,2015,11,4,58,0,NULL),(44,'d1','ex',48,2018,15,1,83,81,''),(45,'abc','abc',49,2018,17,1,88,87,''),(46,'xdvsdv','sdfsdfdfs',50,2018,17,1,87,86,''),(47,'sdfsdfdsf','dsfsfsdf',50,2018,17,1,87,86,''),(48,'asdasd','dasdas',51,2018,17,1,86,86,''),(49,'Economic uncertantity','Economic Forecasting',52,2018,18,1,94,93,''),(50,'Economic uncertinity','Economic forecasting',53,2018,18,1,94,93,''),(51,'abc','abc',54,2018,18,1,94,93,''),(52,'abc','abc',56,2018,18,1,94,93,''),(53,'ed','er',57,2018,18,1,93,92,''),(54,'wr','r',58,2018,18,1,92,92,'');
/*!40000 ALTER TABLE `risk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `riskfactor`
--

DROP TABLE IF EXISTS `riskfactor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `riskfactor` (
  `riskid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `riskName` varchar(45) NOT NULL,
  PRIMARY KEY (`riskid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `riskfactor`
--

LOCK TABLES `riskfactor` WRITE;
/*!40000 ALTER TABLE `riskfactor` DISABLE KEYS */;
INSERT INTO `riskfactor` VALUES (1,'Complexity'),(2,'Reputational sensitivity'),(3,'Interdepartmental dependencies'),(4,'Process maturity'),(5,'Influence of external factors'),(6,'Relevance to the overall entity objectives');
/*!40000 ALTER TABLE `riskfactor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rolls`
--

DROP TABLE IF EXISTS `rolls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rolls` (
  `rollId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `rollName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`rollId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rolls`
--

LOCK TABLES `rolls` WRITE;
/*!40000 ALTER TABLE `rolls` DISABLE KEYS */;
INSERT INTO `rolls` VALUES (0,'none'),(1,'Head of Internal Audit'),(2,'Team Lead'),(3,'Auditor '),(4,'Admin'),(5,'Management');
/*!40000 ALTER TABLE `rolls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skilljobrelation`
--

DROP TABLE IF EXISTS `skilljobrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skilljobrelation` (
  `relationid` int(11) NOT NULL AUTO_INCREMENT,
  `softskillid` int(11) NOT NULL,
  `jobcreationid` int(11) NOT NULL,
  PRIMARY KEY (`relationid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skilljobrelation`
--

LOCK TABLES `skilljobrelation` WRITE;
/*!40000 ALTER TABLE `skilljobrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `skilljobrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skills` (
  `skillId` int(5) NOT NULL AUTO_INCREMENT,
  `skillName` varchar(255) NOT NULL,
  `availablehours` int(9) NOT NULL,
  PRIMARY KEY (`skillId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skills`
--

LOCK TABLES `skills` WRITE;
/*!40000 ALTER TABLE `skills` DISABLE KEYS */;
INSERT INTO `skills` VALUES (0,'none',0),(1,'IT',5000),(2,'Finance',5000),(3,'Business',5000);
/*!40000 ALTER TABLE `skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `softskills`
--

DROP TABLE IF EXISTS `softskills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `softskills` (
  `softskillid` int(11) NOT NULL AUTO_INCREMENT,
  `softskillname` varchar(255) NOT NULL,
  PRIMARY KEY (`softskillid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `softskills`
--

LOCK TABLES `softskills` WRITE;
/*!40000 ALTER TABLE `softskills` DISABLE KEYS */;
INSERT INTO `softskills` VALUES (1,'Communication'),(2,'Analytical'),(3,'Attention to Detail');
/*!40000 ALTER TABLE `softskills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `strategic`
--

DROP TABLE IF EXISTS `strategic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `strategic` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `strategicObjective` varchar(500) NOT NULL,
  `relevantDepartment` int(10) unsigned DEFAULT NULL,
  `acheivementDate` datetime DEFAULT NULL,
  `riskFactor` int(10) unsigned DEFAULT NULL,
  `rating` varchar(45) DEFAULT NULL,
  `auditableUnit` varchar(500) DEFAULT NULL,
  `initiatedBy` int(10) unsigned DEFAULT NULL,
  `assignedTo` int(10) unsigned DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `comments` varchar(500) DEFAULT NULL,
  `approvedBy` int(10) unsigned DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `audit` tinyint(1) DEFAULT '0',
  `phase` int(10) unsigned DEFAULT NULL,
  `tab` int(10) unsigned DEFAULT NULL,
  `year` int(10) unsigned NOT NULL,
  `approvedByAuditHead` tinyint(3) unsigned DEFAULT NULL,
  `companyId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strategic`
--

LOCK TABLES `strategic` WRITE;
/*!40000 ALTER TABLE `strategic` DISABLE KEYS */;
INSERT INTO `strategic` VALUES (3,'j',0,NULL,1,'Low',NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-24 19:51:07',0,1,0,2014,0,1),(4,'k',0,NULL,1,'Low',NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-24 19:51:25',0,1,0,2014,0,1),(5,'nl',0,NULL,1,'Low',NULL,NULL,NULL,NULL,NULL,NULL,'2014-12-24 19:51:38',0,1,0,2014,0,1),(6,'ml',0,NULL,1,'Low',NULL,1,1,'initiated',NULL,1,'2014-12-24 19:56:39',0,2,0,2014,0,1),(7,'from hy1',0,NULL,1,'Low','un11',2,2,'initiated',NULL,1,'2014-12-24 20:05:02',1,5,0,2014,1,1),(10,'lk',0,NULL,1,'Low',NULL,2,1,'deleted',NULL,0,'2014-12-25 07:20:34',0,1,0,2014,0,1),(11,'nlkk',0,NULL,1,'Low',NULL,2,2,'deleted',NULL,0,'2014-12-25 07:27:33',0,1,0,2014,0,1),(12,'oppi',0,NULL,0,'High','u67',2,2,'initiated',NULL,1,'2014-12-29 05:01:12',1,5,0,2015,1,1),(13,'test1',0,NULL,1,'Medium','untest',2,2,'initiated',NULL,1,'2014-12-29 05:05:28',1,5,0,2016,1,1),(14,'t2',0,NULL,1,'Low',NULL,2,2,'deleted',NULL,1,'2014-12-29 05:55:35',0,2,0,2014,0,1),(15,'t3',0,NULL,1,'High','t3unit',2,2,'initiated','Approved for 2015',1,'2014-12-29 06:01:38',1,5,0,2015,1,1),(16,'test3',0,NULL,1,'Low',NULL,2,1,'submitted',NULL,0,'2014-12-30 02:06:54',0,1,0,2014,0,1),(17,'t4',0,NULL,1,'Medium','t4unit',8,8,'initiated','yup',2,'2014-12-30 02:39:34',1,5,0,2014,1,1),(18,'first..',0,NULL,1,'Low',NULL,13,12,'submitted',NULL,0,'2015-01-07 11:01:26',0,2,0,2015,0,3),(19,'2nd',0,NULL,1,'Medium',NULL,13,12,'submitted',NULL,0,'2015-01-08 05:13:34',0,2,0,2015,0,3),(20,'new',0,NULL,1,'Medium',NULL,5,5,'initiated',NULL,2,'2015-01-08 05:02:43',0,3,0,2015,0,1),(21,'jj',0,NULL,1,'Low',NULL,5,5,'initiated',NULL,2,'2015-01-08 05:10:35',0,3,0,2015,0,1),(22,'tob1',0,NULL,1,'Medium','ui',17,17,'initiated','Comments',16,'2015-03-31 12:54:35',1,5,0,2015,1,4),(23,'tt',0,NULL,1,'Medium',NULL,18,18,'initiated',NULL,17,'2015-01-08 17:21:08',0,0,0,2015,0,4),(24,'3rd',0,NULL,1,'Low',NULL,13,13,'deleted',NULL,0,'2015-01-08 17:45:48',0,1,0,2015,0,3),(25,'Jobj',0,NULL,1,'Medium','unitj',13,13,'initiated','done',12,'2015-01-08 18:09:10',1,5,0,2015,1,3),(26,'last test',0,NULL,1,'Medium','last Unit',13,13,'initiated','ok',12,'2015-01-10 04:03:19',1,5,0,2015,1,3),(27,'Investment in new technology',0,NULL,1,'High','Investment in new technology',13,39,'initiated',NULL,39,'2015-03-26 03:38:56',0,4,0,2015,0,8),(28,'Appropriate inventory levels are to be maintained',0,NULL,1,'Low','Appropriate inventory levels are to be maintained',39,39,'initiated','Comments',38,'2015-02-12 12:41:04',1,5,1,2015,1,8),(29,'Stong controls over revenue recognition',0,NULL,1,'Low','Stong controls over revenue recognition',39,39,'initiated',NULL,38,'2015-02-12 12:40:33',1,5,2,2016,0,8),(30,'Compiance with OGRA regulations specific to inventory',0,NULL,1,'High','Compiance with OGRA regulations specific to inventory',39,39,'initiated','Comments',38,'2015-02-12 12:40:33',1,5,3,2015,1,8),(31,'Vertical acquisition',0,NULL,1,'Medium','Vertical acquisition',39,39,'initiated','Comments',38,'2015-02-12 12:40:33',1,5,0,2015,1,8),(32,'Enahance IT security of the systems',0,NULL,1,'Medium','Enahance IT security of the systems',39,38,'submitted',NULL,0,'2015-02-12 12:40:33',1,2,1,2015,0,8),(33,'Developement of ICFR framewrok',0,NULL,1,'High','Developement of ICFR framewrok',39,39,'initiated','Comments',38,'2015-02-12 12:40:33',1,5,2,2015,1,8),(34,'Compliance with Companies Ordinance',0,NULL,1,'Medium','Compliance with Companies Ordinance',39,39,'initiated','Comments',38,'2015-02-12 12:20:35',1,5,3,2015,1,8),(35,'Horizantal integration',0,NULL,1,'Low','Horizantal integration',38,37,'submitted',NULL,0,'2015-02-12 12:40:33',1,1,0,2015,0,8),(36,'Compliance with Companies Ordinance',0,NULL,1,'Low','Compliance with Companies Ordinance',38,37,'submitted',NULL,0,'2015-02-12 12:40:34',1,1,3,2015,0,8),(37,'Recheck reporting',0,NULL,1,'Low','Reporting',39,39,'initiated','Comments',38,'2015-02-19 05:08:17',1,5,0,2015,1,8),(38,'Check 2016',0,NULL,1,'Low','2016',39,38,'submitted',NULL,0,'2015-02-19 07:35:33',0,4,0,2016,0,8),(39,'khurram',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-19 07:45:21',0,2,0,2016,0,8),(40,'khurrram head',0,NULL,1,'Low',NULL,37,37,'initiated',NULL,37,'2015-02-19 07:47:29',0,2,0,2016,0,8),(41,'Strategic 1',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 05:14:05',0,2,0,2017,0,8),(42,'Operations 1',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 05:17:08',0,2,1,2017,0,8),(43,'Reporting 1',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 05:22:14',0,2,2,2017,0,8),(44,'Compliance 1',0,NULL,1,'Low',NULL,39,38,'deleted',NULL,0,'2015-02-20 05:23:00',0,1,3,2017,0,8),(45,'Strategic 2 ',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 05:39:13',0,2,0,2017,0,8),(46,'Strategic 3 ',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 05:39:06',0,2,0,2017,0,8),(47,'Strategic 4',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 05:38:59',0,2,0,2017,0,8),(48,'Strategic 5',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 05:38:53',0,2,0,2017,0,8),(49,'Strategic 6',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 05:38:44',0,2,0,2017,0,8),(50,'Strategic 7',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 05:38:41',0,2,0,2017,0,8),(51,'Strategic 8',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 05:38:39',0,2,0,2017,0,8),(52,'Operations 2',0,NULL,1,'Low',NULL,39,39,'deleted',NULL,0,'2015-02-20 05:39:49',0,1,1,2017,0,8),(53,'Operations 2',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 05:43:29',0,2,1,2017,0,8),(54,'Operations 3',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 06:23:06',0,2,1,2017,0,8),(55,'Operations 4',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 06:23:00',0,2,1,2017,0,8),(56,'Operations 5',0,NULL,1,'Low',NULL,39,38,'deleted',NULL,0,'2015-02-20 05:41:35',0,1,1,2017,0,8),(57,'Operations 6',0,NULL,1,'Low',NULL,39,38,'deleted',NULL,0,'2015-02-20 05:41:54',0,1,1,2017,0,8),(58,'Operations 7',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 07:24:43',0,2,1,2017,0,8),(59,'Operations 8',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 07:24:41',0,2,1,2017,0,8),(60,'Reporting 2',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 06:30:26',0,2,2,2017,0,8),(61,'Reporting 3',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 06:30:20',0,2,2,2017,0,8),(62,'Reporting 4',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 06:30:15',0,2,2,2017,0,8),(63,'Reporting 5',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 06:30:10',0,2,2,2017,0,8),(64,'Reporting 6',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 06:30:06',0,2,2,2017,0,8),(65,'Reporting 7',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 06:30:04',0,2,2,2017,0,8),(66,'Reporting 8',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 06:30:02',0,2,2,2017,0,8),(67,'Compliance 2',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 06:43:51',0,2,3,2017,0,8),(68,'Compliance 3',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 06:43:43',0,2,3,2017,0,8),(69,'Compliance 4',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 06:43:37',0,2,3,2017,0,8),(70,'Compliance 5',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 06:43:28',0,2,3,2017,0,8),(71,'Compliance 6',0,NULL,1,'Low',NULL,39,38,'submitted',NULL,0,'2015-02-20 06:44:22',0,1,3,2017,0,8),(72,'Compliance 7',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 06:43:08',0,2,3,2017,0,8),(73,'Compliance 8',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-02-20 06:43:06',0,2,3,2017,0,8),(74,'ATS 1',0,NULL,1,'Low',NULL,38,38,'initiated',NULL,37,'2015-02-20 07:10:39',0,2,0,2017,0,8),(75,'ATS 2',0,NULL,1,'Low',NULL,38,38,'initiated',NULL,37,'2015-02-20 07:10:54',0,2,0,2017,0,8),(76,'ATS 3',0,NULL,1,'Low',NULL,38,38,'initiated',NULL,37,'2015-02-20 07:24:07',0,2,0,2017,0,8),(77,'ATS 4',0,NULL,1,'Low',NULL,38,38,'initiated',NULL,37,'2015-02-20 07:22:05',0,2,0,2017,0,8),(78,'ATS 5',0,NULL,1,'Low',NULL,38,38,'initiated',NULL,37,'2015-02-20 07:13:02',0,2,0,2017,0,8),(79,'ATS 6',0,NULL,1,'Low',NULL,38,38,'initiated',NULL,37,'2015-02-20 07:12:53',0,2,0,2017,0,8),(80,'ATS 7',0,NULL,1,'Low',NULL,38,38,'initiated',NULL,37,'2015-02-20 07:12:47',0,2,0,2017,0,8),(81,'ATS 8',0,NULL,1,'Low',NULL,38,38,'initiated',NULL,37,'2015-02-20 07:12:33',0,2,0,2017,0,8),(82,'ATS 9',0,NULL,1,'Low',NULL,38,38,'initiated',NULL,37,'2015-02-20 07:12:35',0,2,0,2017,0,8),(83,'Obj1',0,NULL,1,'Low',NULL,38,37,'submitted',NULL,0,'2015-02-20 07:40:19',0,1,1,2017,0,8),(84,'Obj 2',0,NULL,1,'Low',NULL,38,38,'initiated',NULL,37,'2015-02-20 07:42:51',0,2,1,2017,0,8),(85,'Obj 3',0,NULL,1,'Low',NULL,38,37,'submitted',NULL,0,'2015-02-20 07:40:45',0,1,1,2017,0,8),(86,'Obj 4',0,NULL,1,'Low',NULL,38,37,'submitted',NULL,0,'2015-02-20 07:40:57',0,1,1,2017,0,8),(87,'Obj 5',0,NULL,1,'Low',NULL,38,38,'initiated',NULL,37,'2015-02-20 07:43:09',0,2,1,2017,0,8),(88,'Obj 6',0,NULL,1,'Low',NULL,38,38,'initiated',NULL,37,'2015-02-20 07:43:02',0,2,1,2017,0,8),(89,'Obj 7',0,NULL,1,'Low',NULL,38,38,'initiated',NULL,37,'2015-02-20 07:42:56',0,2,1,2017,0,8),(90,'Obj 8',0,NULL,1,'Low',NULL,38,37,'submitted',NULL,0,'2015-02-20 07:42:04',0,1,1,2017,0,8),(91,'RTL 1',0,NULL,1,'Low',NULL,38,37,'submitted',NULL,0,'2015-02-20 10:09:12',0,1,2,2017,0,8),(92,'RTL 2',0,NULL,1,'Low',NULL,38,37,'submitted',NULL,0,'2015-02-20 10:09:08',0,1,2,2017,0,8),(93,'RTL 3',0,NULL,1,'Low',NULL,38,37,'submitted',NULL,0,'2015-02-20 10:09:51',0,1,2,2017,0,8),(94,'RTL 4',0,NULL,1,'Low',NULL,38,37,'submitted',NULL,0,'2015-02-20 10:10:13',0,1,2,2017,0,8),(95,'RTL 5',0,NULL,1,'Low',NULL,38,37,'submitted',NULL,0,'2015-02-20 10:10:44',0,1,2,2017,0,8),(96,'RTL 6',0,NULL,1,'Low',NULL,38,37,'submitted',NULL,0,'2015-02-20 10:11:04',0,1,2,2017,0,8),(97,'26-01-S',0,NULL,1,'Low',NULL,38,37,'deleted',NULL,0,'2015-02-26 05:50:56',0,1,0,2018,0,8),(98,'26-02-O',0,NULL,1,'Low',NULL,38,38,'amend','Remove',0,'2015-02-26 05:55:12',0,1,1,2018,0,8),(99,'26-03-R',0,NULL,1,'Medium',NULL,38,37,'submitted',NULL,0,'2015-02-26 06:20:37',0,2,2,2018,0,8),(100,'26-04-C',0,NULL,1,'Low',NULL,38,37,'deleted',NULL,0,'2015-02-26 05:53:00',0,1,3,2018,0,8),(101,'26-04-C',0,NULL,1,'Low',NULL,38,38,'initiated',NULL,37,'2015-02-26 05:56:12',0,2,3,2018,0,8),(102,'26-05-IT',0,NULL,1,'Low',NULL,37,37,'submitted',NULL,0,'2015-02-26 05:59:50',0,1,0,2018,0,8),(103,'26-06-F',0,NULL,1,'Low',NULL,37,37,'submitted',NULL,0,'2015-02-26 06:00:14',0,1,1,2018,0,8),(104,'26-07-B',0,NULL,1,'Low',NULL,37,37,'deleted',NULL,0,'2015-02-26 06:03:18',0,1,2,2018,0,8),(105,'26-08-HR',0,NULL,1,'Low',NULL,37,37,'deleted',NULL,0,'2015-02-26 06:01:28',0,1,3,2018,0,8),(106,'26-5-IT',0,NULL,1,'Low',NULL,39,38,'submitted',NULL,0,'2015-02-26 06:35:46',0,1,0,2018,0,8),(107,'Auditor 1',0,NULL,1,'High','Auditable Unit 1',39,39,'initiated','Comments',38,'2015-02-26 08:12:31',1,5,0,2018,1,8),(108,'Auditor 2 ',0,NULL,1,'Medium','Auditable Unit 2',39,39,'initiated','Comments',38,'2015-02-26 08:12:29',1,5,0,2018,1,8),(109,'Auditor Objective 3',0,NULL,1,'High','',39,39,'initiated',NULL,38,'2015-02-26 11:32:06',0,5,1,2018,0,8),(110,'Auditor Objective 4',0,NULL,1,'Low','',39,39,'initiated','Comments',38,'2015-02-26 12:28:06',1,5,1,2018,1,8),(111,'OJ1',0,NULL,1,'Medium','',38,38,'initiated',NULL,37,'2015-02-26 13:11:21',1,5,0,2018,0,8),(112,'OJ2',0,NULL,1,'Low','',38,38,'initiated',NULL,37,'2015-02-26 13:11:23',1,5,0,2018,0,8),(113,'MO1',0,NULL,1,'High','Auditable Unit 3',37,37,'initiated',NULL,37,'2015-02-26 13:34:36',0,5,0,2018,0,8),(114,'300',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-03-10 08:34:34',0,2,0,2018,0,8),(115,'400',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-03-10 08:35:17',0,2,0,2018,0,8),(116,'500',0,NULL,1,'Low',NULL,38,38,'initiated',NULL,37,'2015-03-10 08:38:56',0,2,0,2018,0,8),(117,'600',0,NULL,1,'Low',NULL,37,37,'initiated',NULL,37,'2015-03-10 08:44:29',0,2,0,2018,0,8),(118,'rafey',0,NULL,1,'Medium',NULL,39,39,'deleted',NULL,38,'2015-03-12 05:39:09',0,3,0,2015,0,8),(119,'Improve ARPU ',0,NULL,1,'Low',NULL,39,38,'submitted',NULL,0,'2015-03-12 07:03:33',0,1,0,2020,0,8),(120,'Increase market share by 2% ',0,NULL,1,'Low',NULL,39,38,'submitted',NULL,0,'2015-03-12 07:03:55',0,1,0,2020,0,8),(121,'Increase market share percentage by 2 percent',0,NULL,1,'Low',NULL,39,38,'submitted',NULL,0,'2015-03-12 12:30:21',0,1,0,2019,0,8),(122,'rafey',0,NULL,1,'Low',NULL,36,35,'submitted',NULL,0,'2015-03-19 06:51:40',0,1,0,2015,0,8),(123,'zain',0,NULL,1,'Low',NULL,36,35,'submitted',NULL,0,'2015-03-19 06:52:17',0,1,1,2015,0,8),(124,'fassd',0,NULL,1,'Low',NULL,36,35,'submitted',NULL,0,'2015-03-19 06:52:32',0,1,2,2015,0,8),(125,'dsadasd',0,NULL,1,'Low',NULL,36,35,'submitted',NULL,0,'2015-03-19 06:52:51',0,1,3,2015,0,8),(126,'EXPLORE INTERNATIONAL MARKET',0,NULL,1,'Low',NULL,36,35,'submitted',NULL,0,'2015-03-19 08:06:58',0,1,0,2015,0,8),(127,'Understanding the entity',0,NULL,1,'Low',NULL,36,35,'submitted',NULL,0,'2015-03-19 08:03:10',0,1,0,2015,0,8),(128,'Understand Industry',0,NULL,1,'Low',NULL,36,36,'saved',NULL,0,'2015-03-19 08:09:53',0,1,0,2015,0,8),(129,'rafay',0,NULL,1,'Low',NULL,36,35,'submitted',NULL,0,'2015-03-19 08:13:17',0,1,0,2015,0,8),(130,'rafey',0,NULL,1,'Medium','rafey',39,39,'initiated',NULL,38,'2015-03-19 08:30:52',1,5,0,2015,0,8),(131,'Understanding business Environment',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-03-25 10:14:44',0,2,0,2015,0,8),(132,'Understanding the business objectives',0,NULL,1,'Low','Inventories',39,38,'submitted',NULL,0,'2015-03-19 09:53:10',1,4,0,2015,0,8),(133,'Introduce the product into International Market',0,NULL,1,'High',NULL,58,58,'submitted',NULL,0,'2015-03-19 10:20:15',0,2,0,2015,0,11),(134,'Maintain appropriate inventory levels',0,NULL,1,'High','Working Capital Management',60,60,'initiated','Comments',59,'2015-03-26 04:39:30',1,5,1,2015,1,11),(135,'Comliance with SOX requirements',0,NULL,1,'High','Financial Reporting',60,60,'initiated','Comments',59,'2015-03-26 04:39:27',1,5,2,2015,1,11),(136,'Compliance with regulatory authorities',0,NULL,1,'High','Regulatory',60,60,'initiated','Comments',59,'2015-03-26 04:39:21',1,5,3,2017,1,11),(137,'Making IT a strategic business partner',0,NULL,1,'Low','Information Technology',60,60,'initiated','Comments',59,'2015-03-26 04:39:13',1,5,0,2015,1,11),(138,'Enhance the recoverablity of receivables',0,NULL,1,'Medium','Working Capital Management',60,60,'initiated',NULL,59,'2015-03-26 04:39:11',1,5,1,2015,0,11),(139,'Reduce the number of days to close monthly accounts',0,NULL,1,'High','Financial Reporting',60,60,'initiated',NULL,59,'2015-04-22 07:49:57',1,5,2,2015,0,11),(140,'Compliance with environment rules',0,NULL,1,'Medium','Environmental regulations',60,60,'initiated','Comments',59,'2015-03-26 04:39:07',1,5,3,2015,1,11),(141,'understanding the business objectives',0,NULL,1,'Low',NULL,39,39,'initiated',NULL,38,'2015-03-25 10:23:32',0,3,0,2015,0,8),(142,'horizintal integration',0,NULL,1,'Low',NULL,39,38,'submitted',NULL,0,'2015-03-25 10:18:18',0,1,0,2015,0,8),(143,'new1',0,NULL,1,'Low','NewAudit',18,18,'initiated','ok',17,'2015-04-02 05:19:50',1,5,0,2015,1,4),(144,'Check risk assessment',0,NULL,1,'Medium','Risk assessment',60,60,'initiated','Comments',59,'2015-05-03 11:31:00',1,5,0,2015,1,11),(145,'xcveefdf',0,NULL,1,'Low',NULL,60,59,'submitted',NULL,0,'2015-04-28 12:39:22',0,1,0,2015,0,11),(146,'physical verification of assets',0,NULL,1,'High','Fixed Assets',60,60,'initiated','Comments',59,'2015-05-03 11:30:58',1,5,0,2015,1,11),(147,'recalculation of assumptions',0,NULL,1,'Low','arsie',60,60,'initiated','Comments',59,'2015-04-30 06:16:22',1,5,0,2015,1,11),(148,'verify inventory values',0,NULL,1,'Low','inventory',60,60,'initiated','Comments',59,'2015-05-02 16:10:34',1,5,0,2016,1,11),(149,'physical verification of fixed assets',0,NULL,1,'Low','fixed assets',60,60,'initiated','Comments',59,'2015-05-02 16:18:47',1,5,0,2017,1,11),(150,'Enahance the effective of sales operational staff',0,NULL,1,'Medium','',60,60,'initiated','Comments',59,'2015-05-03 11:30:56',1,5,1,2015,1,11),(151,'HR efficiency',0,NULL,1,'Low','HR',60,60,'initiated','Comments',59,'2015-08-27 04:39:33',1,5,1,2015,1,11),(152,'Fixed assets recognition',0,NULL,1,'Medium','Fixed Assets',60,60,'initiated',NULL,59,'2015-05-03 11:30:53',1,5,2,2015,0,11),(153,'Compliance with tax laws',0,NULL,1,'Medium','Taxation',60,60,'initiated','Comments',59,'2015-05-03 11:30:51',1,5,3,2015,1,11),(154,'Product diversification',0,NULL,1,'High','Product diversification',61,61,'initiated',NULL,61,'2015-05-03 11:24:42',0,4,0,2015,0,11),(155,'Product diversification to enhance market share',0,NULL,1,'Low',NULL,60,59,'submitted',NULL,0,'2015-05-04 06:16:05',0,1,0,2015,0,11),(156,'Product diversification to enhance market share',0,NULL,1,'Low',NULL,60,59,'submitted',NULL,0,'2015-05-04 06:23:12',0,2,0,2015,0,11),(157,'Product diversification to enhance market share',0,NULL,1,'Low',NULL,60,59,'submitted',NULL,0,'2015-05-04 06:22:22',0,2,0,2015,0,11),(158,'pricing according to market segmentation',0,NULL,1,'Low','stock',60,60,'initiated','Comments',59,'2015-05-04 10:16:11',1,5,0,2015,1,11),(159,'Geographical expansion in middle east markets',0,NULL,1,'Low','Sales',58,58,'initiated','Comments',58,'2015-05-04 11:26:23',1,5,0,2015,1,11),(160,'Revenue recognition',0,NULL,1,'High','Revenue',60,60,'initiated','Comments',59,'2015-05-04 11:54:45',1,5,2,2015,1,11),(161,'Cost optmization',0,NULL,1,'Medium','Business Process Excellence',60,60,'initiated','Comments',59,'2015-05-31 12:17:40',1,5,0,2016,1,11),(162,'bf fvb f',0,NULL,1,'Low',NULL,60,59,'submitted',NULL,0,'2015-06-01 05:53:51',0,1,0,2015,0,11),(163,'Object No. 1',0,NULL,1,'Medium',NULL,68,68,'saved',NULL,0,'2015-06-02 06:17:52',0,2,0,2015,0,12),(164,'umar hafeez',0,NULL,1,'Low',NULL,68,68,'initiated',NULL,67,'2015-06-02 06:13:42',0,2,0,2015,0,12),(165,'umar hafeez',0,NULL,1,'Low',NULL,68,68,'initiated',NULL,67,'2015-06-02 06:13:39',0,2,0,2015,0,12),(166,'Insurance.....',0,NULL,1,'High',NULL,60,59,'submitted',NULL,0,'2015-06-09 11:15:36',0,2,0,2015,0,11),(175,'123',0,NULL,1,'Low',NULL,60,59,'submitted',NULL,0,'2015-06-08 12:34:43',0,1,0,2015,0,11),(176,'Fashion trends ....',0,NULL,1,'Low',NULL,60,59,'submitted',NULL,0,'2015-06-09 11:13:49',0,1,0,2015,0,11),(177,'Market diversification',0,NULL,1,'Low',NULL,58,58,'deleted',NULL,0,'2015-06-16 05:36:45',0,1,0,2015,0,11),(178,'Market Diversification',0,NULL,1,'Low',NULL,60,60,'initiated',NULL,59,'2015-06-16 06:58:58',0,2,0,2015,0,11),(179,'market diversification',0,NULL,1,'Low','Sales',58,58,'initiated',NULL,58,'2015-06-16 07:27:53',1,5,0,2015,0,11),(180,'product diversification',0,NULL,1,'Low',NULL,58,58,'deleted',NULL,0,'2015-06-16 06:14:30',0,1,0,2015,0,11),(181,'market diversification',0,NULL,1,'High','Sales',59,59,'initiated',NULL,58,'2015-06-16 09:23:38',1,5,0,2015,0,11),(182,'market diversification',0,NULL,1,'Low','Portfolio management',60,60,'initiated','Comments',59,'2016-02-22 10:18:46',1,5,0,2016,1,11),(183,'market diversification',0,NULL,1,'Low','Sales',58,58,'submitted',NULL,0,'2015-06-18 10:30:46',1,5,0,2015,0,11),(184,'Market diversification',0,NULL,1,'Low','Sales',58,58,'initiated',NULL,58,'2015-06-22 06:06:37',1,5,0,2015,0,11),(185,'Market expansion',0,NULL,1,'Low','Revenue',58,58,'initiated',NULL,58,'2015-06-22 07:47:40',1,5,0,2015,0,11),(186,'market diversification',0,NULL,1,'Low',NULL,58,58,'deleted',NULL,0,'2015-06-22 08:10:01',0,1,0,2016,0,11),(187,'market diversification',0,NULL,1,'Low',NULL,58,58,'initiated',NULL,59,'2015-06-22 08:11:34',0,2,0,2016,0,11),(188,'Market diversification',0,NULL,1,'Low','Market diversification',58,58,'initiated','Comments',58,'2015-06-23 06:59:17',1,5,0,2015,1,11),(189,'Market diversification',0,NULL,1,'Low',NULL,60,60,'deleted',NULL,0,'2015-06-24 07:55:15',0,1,0,2017,0,11),(190,'Market diversification',0,NULL,1,'Low',NULL,60,60,'initiated',NULL,59,'2015-06-24 10:32:00',0,3,0,2017,0,11),(191,'best way',0,NULL,1,'Low',NULL,60,60,'initiated',NULL,59,'2015-08-26 05:01:23',0,2,0,2015,0,11),(192,'market diversification',0,NULL,1,'Low','portfolio management',60,60,'initiated','Comments',59,'2015-08-27 04:58:09',1,5,0,2015,1,11),(193,'test2',0,NULL,1,'Low',NULL,2,2,'deleted',NULL,0,'2016-02-06 13:43:44',0,1,0,2016,0,1),(194,'Expansion into overseas market',0,NULL,1,'Low',NULL,60,60,'initiated',NULL,59,'2016-02-12 01:58:46',0,2,1,2015,0,11),(195,'Enhance standards with respect to environmental regulations',0,NULL,1,'Low',NULL,60,60,'initiated',NULL,59,'2016-02-12 02:06:15',0,2,3,2015,0,11),(196,'Supply chain efficiency',0,NULL,1,'Medium',NULL,60,59,'submitted',NULL,0,'2016-02-12 07:04:14',0,2,1,2016,0,11),(197,'Inventory management',0,NULL,1,'Low','Stock',60,60,'initiated','Comments',59,'2016-02-22 08:16:04',1,5,0,2016,1,11),(198,'Sales growth',0,NULL,1,'Medium','Market share',60,60,'initiated','Comments',59,'2016-02-22 08:18:07',1,5,0,2016,1,11),(199,'Sales monitoring',0,NULL,1,'Low',NULL,59,58,'submitted',NULL,0,'2016-02-22 08:10:47',0,1,1,2016,0,11),(200,'Compliance with the environemental regulations',0,NULL,1,'Medium','Environmental compliance assessment',60,60,'initiated','Comments',59,'2016-02-23 07:21:53',1,5,3,2016,1,11),(201,'Compliance with companies ordinance',0,NULL,1,'Low',NULL,60,60,'initiated',NULL,59,'2016-02-23 07:15:47',0,2,3,2016,0,11),(202,'test',0,NULL,1,'Medium',NULL,78,78,'initiated',NULL,77,'2018-02-22 09:42:27',0,3,0,2018,0,14),(203,'test2',0,NULL,1,'Low',NULL,77,77,'initiated',NULL,77,'2018-02-22 09:38:46',0,3,0,2018,0,14),(204,'b1',0,NULL,1,'High','Bunit1',83,83,'initiated','OK',82,'2018-02-22 19:03:03',1,5,0,2018,1,15),(205,'Market Diversification',0,NULL,1,'High','Sales',88,88,'initiated','abc',87,'2018-02-26 07:20:32',1,5,0,2018,1,17),(206,'abc',0,NULL,1,'Low',NULL,88,87,'deleted',NULL,0,'2018-02-26 06:58:31',0,1,0,2018,0,17),(207,'def',0,NULL,1,'Low',NULL,88,88,'deleted',NULL,0,'2018-02-26 06:58:46',0,1,0,2018,0,17),(208,'Fashion Trends',0,NULL,1,'Medium','Purchases',87,87,'initiated','Comments',86,'2018-02-27 05:22:02',1,5,0,2018,1,17),(209,'Sales Growth',0,NULL,1,'Low','Expenses',86,86,'initiated','done',86,'2018-02-27 06:28:33',1,5,0,2018,1,17),(210,'Market Diversification',0,NULL,1,'High','Sales',94,94,'initiated','Comments',93,'2018-03-01 11:55:30',1,5,0,2018,1,18),(211,'Market Diversification',0,NULL,1,'High','Sales',88,88,'initiated',NULL,87,'2018-03-04 14:50:50',1,5,0,2018,0,17),(212,'Fashion Trends',0,NULL,1,'High','Purchases',94,94,'initiated','Comments',93,'2018-03-05 06:42:44',1,5,0,2018,1,18),(213,'Market Diversification 1',0,NULL,1,'High','Expenses',94,94,'initiated','Comments',93,'2018-03-06 08:09:36',1,5,0,2018,1,18),(214,'New Product',0,NULL,1,'High','Assets',94,94,'initiated','done',93,'2018-03-07 11:11:04',1,5,0,2018,1,18),(215,'New trends',0,NULL,1,'High','Material cost',93,93,'initiated','Comments',92,'2018-03-07 12:43:06',1,5,0,2018,1,18),(216,'newest trend',0,NULL,1,'Low','sales1',92,92,'initiated','Comments',92,'2018-03-07 13:01:33',1,5,0,2018,1,18);
/*!40000 ALTER TABLE `strategic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `strategicaudit`
--

DROP TABLE IF EXISTS `strategicaudit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `strategicaudit` (
  `auditId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `strategicObjective` varchar(500) NOT NULL DEFAULT '',
  `objectiveOwner` int(10) unsigned DEFAULT NULL,
  `relevantDepartment` int(10) unsigned DEFAULT NULL,
  `acheivementDate` datetime DEFAULT NULL,
  `riskFactor` int(10) unsigned DEFAULT NULL,
  `rating` varchar(45) DEFAULT NULL,
  `auditableUnit` varchar(45) DEFAULT NULL,
  `initiatedBy` int(10) unsigned DEFAULT NULL,
  `assignedTo` int(10) unsigned DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `comments` varchar(100) DEFAULT NULL,
  `phase` varchar(45) DEFAULT NULL,
  `approvedBy` int(10) unsigned DEFAULT NULL,
  `delete` varchar(45) DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`auditId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strategicaudit`
--

LOCK TABLES `strategicaudit` WRITE;
/*!40000 ALTER TABLE `strategicaudit` DISABLE KEYS */;
/*!40000 ALTER TABLE `strategicaudit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `strategicdepartments`
--

DROP TABLE IF EXISTS `strategicdepartments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `strategicdepartments` (
  `strategic` int(10) unsigned NOT NULL,
  `department` varchar(45) DEFAULT NULL,
  `strategicDepartmentId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`strategicDepartmentId`)
) ENGINE=InnoDB AUTO_INCREMENT=264 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strategicdepartments`
--

LOCK TABLES `strategicdepartments` WRITE;
/*!40000 ALTER TABLE `strategicdepartments` DISABLE KEYS */;
INSERT INTO `strategicdepartments` VALUES (118,'1',41),(3,'1',42),(4,'1',43),(5,'1',44),(6,'1',45),(7,'2',46),(10,'1',47),(11,'1',48),(12,'2',49),(12,'3',50),(13,'2',51),(14,'1',52),(15,'2',53),(16,'2',54),(17,'2',55),(18,'3',56),(19,'2',57),(20,'1',58),(21,'1',59),(22,'1',60),(23,'1',61),(24,'1',62),(24,'2',63),(25,'2',64),(26,'2',65),(26,'3',66),(27,'4',67),(28,'3',68),(29,'2',69),(30,'5',70),(31,'3',71),(32,'1',72),(33,'2',73),(34,'2',74),(35,'3',75),(36,'2',76),(37,'2',77),(38,'1',78),(39,'2',79),(40,'1',80),(41,'1',81),(42,'1',82),(44,'1',83),(45,'2',84),(46,'3',85),(47,'4',86),(48,'5',87),(49,'6',88),(50,'7',89),(51,'8',90),(52,'2',91),(53,'2',92),(54,'3',93),(55,'4',94),(56,'5',95),(57,'6',96),(58,'7',97),(59,'8',98),(60,'2',99),(61,'3',100),(62,'4',101),(63,'5',102),(64,'6',103),(65,'7',104),(66,'8',105),(67,'2',106),(68,'3',107),(69,'4',108),(70,'5',109),(71,'6',110),(72,'7',111),(73,'8',112),(74,'1',113),(75,'2',114),(76,'3',115),(77,'5',116),(78,'4',117),(79,'6',118),(80,'7',119),(81,'8',120),(82,'7',121),(83,'1',122),(84,'2',123),(85,'3',124),(86,'4',125),(87,'5',126),(88,'6',127),(89,'7',128),(90,'8',129),(91,'1',130),(92,'2',131),(93,'3',132),(94,'4',133),(95,'5',134),(96,'6',135),(97,'1',136),(98,'2',137),(99,'3',138),(100,'4',139),(101,'4',140),(102,'1',141),(103,'1',142),(104,'3',143),(105,'7',144),(106,'1',145),(107,'1',146),(108,'2',147),(109,'3',148),(110,'5',149),(111,'8',150),(112,'6',151),(113,'1',152),(114,'1',153),(115,'1',154),(116,'1',155),(117,'1',156),(119,'1',157),(120,'1',158),(121,'4',159),(122,'1',160),(123,'1',161),(124,'1',162),(125,'1',163),(126,'3',164),(127,'1',165),(128,'1',166),(129,'1',167),(130,'1',168),(131,'1',169),(132,'1',170),(133,'4',171),(134,'8',172),(135,'2',173),(136,'5',174),(137,'1',175),(138,'3',176),(139,'2',177),(140,'5',178),(141,'1',179),(142,'2',180),(143,'1',181),(144,'1',182),(145,'1',183),(146,'1',184),(147,'1',185),(148,'1',186),(149,'1',187),(150,'6',188),(151,'7',189),(152,'2',190),(153,'2',191),(154,'3',192),(155,'1',193),(156,'1',194),(157,'2',195),(158,'1',196),(159,'1',197),(160,'1',198),(161,'1',199),(161,'3',200),(162,'2',201),(163,'1',202),(164,'1',203),(165,'1',204),(164,'2',205),(166,'2',206),(177,'2',219),(177,'4',220),(178,'4',221),(179,'1',222),(179,'4',223),(180,'4',224),(181,'4',225),(182,'1',226),(183,'1',227),(184,'1',228),(185,'1',229),(186,'1',230),(187,'1',231),(188,'1',232),(189,'1',233),(190,'4',234),(191,'2',235),(192,'1',236),(194,'4',237),(195,'5',238),(196,'8',239),(197,'1',240),(198,'1',241),(199,'1',242),(200,'5',243),(201,'1',244),(202,'2',245),(203,'2',246),(204,'2',247),(205,'4',248),(206,'1',249),(207,'1',250),(208,'3',251),(208,'4',252),(209,'4',253),(209,'2',254),(210,'4',255),(211,'4',256),(212,'4',257),(213,'4',258),(214,'3',259),(214,'4',260),(215,'3',261),(215,'4',262),(216,'4',263);
/*!40000 ALTER TABLE `strategicdepartments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `strategicrisk`
--

DROP TABLE IF EXISTS `strategicrisk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `strategicrisk` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `strategicId` int(10) unsigned NOT NULL,
  `riskFactorId` int(10) unsigned NOT NULL,
  `rating` varchar(45) NOT NULL DEFAULT '',
  `comments` varchar(100) DEFAULT NULL,
  `probabality` varchar(45) NOT NULL DEFAULT '',
  `impact` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=505 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strategicrisk`
--

LOCK TABLES `strategicrisk` WRITE;
/*!40000 ALTER TABLE `strategicrisk` DISABLE KEYS */;
INSERT INTO `strategicrisk` VALUES (1,7,1,'Low','k;','',''),(2,7,2,'Medium','kl','',''),(3,7,3,'High','kl','',''),(4,7,4,'Low','hj','',''),(5,7,5,'Medium','gy','',''),(6,7,6,'High','bn','',''),(7,12,1,'Low','c','',''),(8,12,2,'Low','d','',''),(9,12,3,'Medium','f','',''),(10,12,4,'High','f','',''),(11,12,5,'High','d','',''),(12,12,6,'High','s','',''),(13,6,1,'N/A','','',''),(14,6,2,'N/A','','',''),(15,6,3,'N/A','','',''),(16,6,4,'N/A','','',''),(17,6,5,'N/A','','',''),(18,6,6,'N/A','','',''),(19,13,1,'Low','jk','',''),(20,13,2,'Low','','',''),(21,13,3,'Medium','','',''),(22,13,4,'High','','',''),(23,13,5,'High','','',''),(24,13,6,'Medium','','',''),(25,15,1,'Low','','',''),(26,15,2,'Low','','',''),(27,15,3,'Medium','','',''),(28,15,4,'High','','',''),(29,15,5,'Medium','','',''),(30,15,6,'Medium','','',''),(31,17,1,'N/A','','',''),(32,17,2,'N/A','','',''),(33,17,3,'N/A','','',''),(34,17,4,'N/A','','',''),(35,17,5,'N/A','','',''),(36,17,6,'N/A','','',''),(37,18,1,'Low','ok','',''),(38,18,2,'Medium','1','',''),(39,18,3,'High','2','',''),(40,18,4,'Low','3','',''),(41,18,5,'Medium','4','',''),(42,18,6,'High','57','',''),(43,19,1,'Low','j','',''),(44,19,2,'Low','j','',''),(45,19,3,'Low','j','',''),(46,19,4,'Medium','j','',''),(47,19,5,'Medium','m','',''),(48,19,6,'Medium','n','',''),(49,20,1,'Low','d','',''),(50,20,2,'Low','d','',''),(51,20,3,'Medium','d','',''),(52,20,4,'High','f','',''),(53,20,5,'Medium','s','',''),(54,20,6,'Low','s','',''),(55,21,1,'Low','jhttp://127.0.0.1:8888/internalaudit/clear.cache.gif','',''),(56,21,2,'Low','jj','',''),(57,21,3,'Low','jj','',''),(58,21,4,'Medium','jj','',''),(59,21,5,'Low','jj','',''),(60,21,6,'Medium','jj','',''),(61,22,1,'Low','j','',''),(62,22,2,'Low','j','',''),(63,22,3,'Medium','jj','',''),(64,22,4,'High','jj','',''),(65,22,5,'Medium','jj','',''),(66,22,6,'Low','j','',''),(67,23,1,'Low','yu','',''),(68,23,2,'Medium','yu','',''),(69,23,3,'High','yu','',''),(70,23,4,'Low','yu','',''),(71,23,5,'Low','yu','',''),(72,23,6,'Low','yu','',''),(73,25,1,'Low','ok','',''),(74,25,2,'Medium','okk','',''),(75,25,3,'Medium','kk','',''),(76,25,4,'Medium','jj','',''),(77,25,5,'High','hh','',''),(78,25,6,'Medium','ff','',''),(79,26,1,'Medium','j','',''),(80,26,2,'N/A','j','',''),(81,26,3,'N/A','j','',''),(82,26,4,'Medium','j','',''),(83,26,5,'N/A','j','',''),(84,26,6,'Medium','j','',''),(85,27,1,'Medium','Hellooooooooooooooooooooooooooooooooooooo','',''),(86,27,2,'High','','',''),(87,27,3,'High','','',''),(88,27,4,'High','','',''),(89,27,5,'High','','',''),(90,27,6,'Low','','',''),(91,31,1,'Medium','','',''),(92,31,2,'Medium','','',''),(93,31,3,'Medium','','',''),(94,31,4,'High','','',''),(95,31,5,'High','','',''),(96,31,6,'High','','',''),(97,28,1,'Low','','',''),(98,28,2,'Low','','',''),(99,28,3,'N/A','','',''),(100,28,4,'N/A','','',''),(101,28,5,'N/A','','',''),(102,28,6,'N/A','','',''),(103,32,1,'N/A','','',''),(104,32,2,'N/A','','',''),(105,32,3,'Medium','','',''),(106,32,4,'N/A','','',''),(107,32,5,'N/A','','',''),(108,32,6,'N/A','','',''),(109,29,1,'N/A','','',''),(110,29,2,'N/A','','',''),(111,29,3,'N/A','','',''),(112,29,4,'N/A','','',''),(113,29,5,'N/A','','',''),(114,29,6,'N/A','','',''),(115,33,1,'N/A','','',''),(116,33,2,'N/A','','',''),(117,33,3,'N/A','','',''),(118,33,4,'N/A','','',''),(119,33,5,'N/A','','',''),(120,33,6,'N/A','','',''),(121,30,1,'N/A','','',''),(122,30,2,'N/A','','',''),(123,30,3,'N/A','','',''),(124,30,4,'N/A','','',''),(125,30,5,'N/A','','',''),(126,30,6,'N/A','','',''),(127,34,1,'N/A','','',''),(128,34,2,'N/A','','',''),(129,34,3,'N/A','','',''),(130,34,4,'N/A','','',''),(131,34,5,'N/A','','',''),(132,34,6,'N/A','','',''),(133,37,1,'N/A','','',''),(134,37,2,'N/A','','',''),(135,37,3,'N/A','','',''),(136,37,4,'N/A','','',''),(137,37,5,'N/A','','',''),(138,37,6,'N/A','','',''),(139,38,1,'N/A','','',''),(140,38,2,'N/A','','',''),(141,38,3,'N/A','','',''),(142,38,4,'N/A','','',''),(143,38,5,'N/A','','',''),(144,38,6,'N/A','','',''),(145,99,1,'Medium','This is very complex','',''),(146,99,2,'Medium','Reputational sensitivity','',''),(147,99,3,'Medium','Interdepartmental dependencies','',''),(148,99,4,'Medium','Process Maturity','',''),(149,99,5,'Medium','Influence of external factors','',''),(150,99,6,'Medium','Relevance to the overall entity objectives','',''),(151,107,1,'Low','','',''),(152,107,2,'Low','','',''),(153,107,3,'Medium','','',''),(154,107,4,'Medium','','',''),(155,107,5,'High','','',''),(156,107,6,'High','','',''),(157,108,1,'Medium','check complexity','',''),(158,108,2,'Medium','reputational sensitivity','',''),(159,108,3,'Medium','interdepartmental dependencies','',''),(160,108,4,'Medium','process maturity','',''),(161,108,5,'Medium','influence of external factors','',''),(162,108,6,'Medium','relevance to the overall entity objectives 2','',''),(163,109,1,'High','','',''),(164,109,2,'High','','',''),(165,109,3,'High','','',''),(166,109,4,'High','','',''),(167,109,5,'High','','',''),(168,109,6,'High','','',''),(169,110,1,'Low','','',''),(170,110,2,'Low','','',''),(171,110,3,'Low','','',''),(172,110,4,'Low','','',''),(173,110,5,'Low','','',''),(174,110,6,'Low','','',''),(175,111,1,'Medium','','',''),(176,111,2,'Medium','','',''),(177,111,3,'Medium','','',''),(178,111,4,'Medium','','',''),(179,111,5,'Medium','','',''),(180,111,6,'Medium','','',''),(181,112,1,'N/A','','',''),(182,112,2,'N/A','','',''),(183,112,3,'N/A','','',''),(184,112,4,'N/A','','',''),(185,112,5,'N/A','','',''),(186,112,6,'N/A','','',''),(187,113,1,'High','','',''),(188,113,2,'High','','',''),(189,113,3,'High','','',''),(190,113,4,'High','','',''),(191,113,5,'High','','',''),(192,113,6,'High','','',''),(193,118,1,'Medium','','',''),(194,118,2,'Medium','','',''),(195,118,3,'Low','','',''),(196,118,4,'High','','',''),(197,118,5,'Low','','',''),(198,118,6,'Medium','','',''),(199,130,1,'Low','rafey','',''),(200,130,2,'Medium','khurram','',''),(201,130,3,'Low','fsdasd','',''),(202,130,4,'Medium','fsdfasf','',''),(203,130,5,'Medium','sdsd','',''),(204,130,6,'Medium','dssdsad','',''),(205,132,1,'Medium','not to complex','',''),(206,132,2,'Low','reputation not too good','',''),(207,132,3,'Low','','',''),(208,132,4,'Medium','','',''),(209,132,5,'Medium','','',''),(210,132,6,'High','','',''),(211,133,1,'N/A','','',''),(212,133,2,'N/A','','',''),(213,133,3,'High','','',''),(214,133,4,'N/A','','',''),(215,133,5,'High','','',''),(216,133,6,'High','','',''),(217,137,1,'N/A','','',''),(218,137,2,'N/A','','',''),(219,137,3,'N/A','','',''),(220,137,4,'N/A','','',''),(221,137,5,'N/A','','',''),(222,137,6,'N/A','','',''),(223,134,1,'N/A','','',''),(224,134,2,'N/A','','',''),(225,134,3,'N/A','','',''),(226,134,4,'N/A','','',''),(227,134,5,'N/A','','',''),(228,134,6,'N/A','','',''),(229,138,1,'N/A','','',''),(230,138,2,'N/A','','',''),(231,138,3,'N/A','','',''),(232,138,4,'N/A','','',''),(233,138,5,'N/A','','',''),(234,138,6,'N/A','','',''),(235,135,1,'N/A','','',''),(236,135,2,'N/A','','',''),(237,135,3,'N/A','','',''),(238,135,4,'N/A','','',''),(239,135,5,'N/A','','',''),(240,135,6,'N/A','','',''),(241,139,1,'N/A','','',''),(242,139,2,'N/A','','',''),(243,139,3,'N/A','','',''),(244,139,4,'N/A','','',''),(245,139,5,'N/A','','',''),(246,139,6,'N/A','','',''),(247,136,1,'N/A','','',''),(248,136,2,'N/A','','',''),(249,136,3,'N/A','','',''),(250,136,4,'N/A','','',''),(251,136,5,'N/A','','',''),(252,136,6,'N/A','','',''),(253,140,1,'N/A','','',''),(254,140,2,'N/A','','',''),(255,140,3,'N/A','','',''),(256,140,4,'N/A','','',''),(257,140,5,'N/A','','',''),(258,140,6,'N/A','','',''),(259,141,1,'Low','kugkjgh','',''),(260,141,2,'Medium','poor controls','',''),(261,141,3,'N/A','','',''),(262,141,4,'N/A','','',''),(263,141,5,'N/A','','',''),(264,141,6,'N/A','','',''),(265,143,1,'Low','kk','',''),(266,143,2,'Medium','dd','',''),(267,143,3,'High','2','',''),(268,143,4,'Low','d','',''),(269,143,5,'Low','4','',''),(270,143,6,'Low','f','',''),(271,144,1,'Medium','k','',''),(272,144,2,'Medium','s','',''),(273,144,3,'Low','s','',''),(274,144,4,'High','a','',''),(275,144,5,'Medium','s','',''),(276,144,6,'Medium','d','',''),(277,147,1,'High','its very complex','',''),(278,147,2,'N/A','','',''),(279,147,3,'N/A','','',''),(280,147,4,'N/A','','',''),(281,147,5,'N/A','','',''),(282,147,6,'N/A','','',''),(283,148,1,'High','risky area due to high value items','',''),(284,148,2,'N/A','','',''),(285,148,3,'N/A','','',''),(286,148,4,'N/A','','',''),(287,148,5,'N/A','','',''),(288,148,6,'N/A','','',''),(289,149,1,'Medium','a large number of high value items','',''),(290,149,2,'N/A','','',''),(291,149,3,'N/A','','',''),(292,149,4,'N/A','','',''),(293,149,5,'N/A','','',''),(294,149,6,'N/A','','',''),(295,150,1,'Medium','','',''),(296,150,2,'Medium','','',''),(297,150,3,'N/A','','',''),(298,150,4,'N/A','','',''),(299,150,5,'N/A','','',''),(300,150,6,'N/A','','',''),(301,146,1,'N/A','','',''),(302,146,2,'N/A','','',''),(303,146,3,'N/A','','',''),(304,146,4,'N/A','','',''),(305,146,5,'N/A','','',''),(306,146,6,'N/A','','',''),(307,152,1,'N/A','','',''),(308,152,2,'N/A','','',''),(309,152,3,'N/A','','',''),(310,152,4,'N/A','','',''),(311,152,5,'N/A','','',''),(312,152,6,'N/A','','',''),(313,153,1,'N/A','','',''),(314,153,2,'N/A','','',''),(315,153,3,'N/A','','',''),(316,153,4,'N/A','','',''),(317,153,5,'N/A','','',''),(318,153,6,'N/A','','',''),(319,154,1,'N/A','','',''),(320,154,2,'N/A','','',''),(321,154,3,'N/A','','',''),(322,154,4,'N/A','','',''),(323,154,5,'N/A','','',''),(324,154,6,'N/A','','',''),(325,157,1,'High','','',''),(326,157,2,'N/A','','',''),(327,157,3,'N/A','','',''),(328,157,4,'N/A','','',''),(329,157,5,'N/A','','',''),(330,157,6,'N/A','','',''),(331,156,1,'High','','',''),(332,156,2,'N/A','','',''),(333,156,3,'N/A','','',''),(334,156,4,'N/A','','',''),(335,156,5,'N/A','','',''),(336,156,6,'N/A','','',''),(337,158,1,'Low','','',''),(338,158,2,'N/A','','',''),(339,158,3,'N/A','','',''),(340,158,4,'N/A','','',''),(341,158,5,'N/A','','',''),(342,158,6,'N/A','','',''),(343,159,1,'Medium','','',''),(344,159,2,'N/A','','',''),(345,159,3,'N/A','','',''),(346,159,4,'N/A','','',''),(347,159,5,'N/A','','',''),(348,159,6,'N/A','','',''),(349,160,1,'High','','',''),(350,160,2,'N/A','','',''),(351,160,3,'N/A','','',''),(352,160,4,'N/A','','',''),(353,160,5,'N/A','','',''),(354,160,6,'N/A','','',''),(355,151,1,'N/A','','',''),(356,151,2,'N/A','','',''),(357,151,3,'N/A','','',''),(358,151,4,'N/A','','',''),(359,151,5,'N/A','','',''),(360,151,6,'N/A','','',''),(361,161,1,'Medium','','',''),(362,161,2,'Medium','','',''),(363,161,3,'High','','',''),(364,161,4,'Low','','',''),(365,161,5,'Medium','','',''),(366,161,6,'Medium','','',''),(367,163,1,'Low','','',''),(368,163,2,'Medium','','',''),(369,163,3,'High','','',''),(370,163,4,'Medium','','',''),(371,163,5,'High','','',''),(372,163,6,'High','','',''),(373,179,1,'Low','High complexity due to uncertain market conditions','',''),(374,179,2,'Medium','','',''),(375,179,3,'Low','','',''),(376,179,4,'Low','','',''),(377,179,5,'Medium','','',''),(378,179,6,'N/A','','',''),(379,181,1,'High','High complexity due to uncertain market conditions','',''),(380,181,2,'High','','',''),(381,181,3,'High','','',''),(382,181,4,'High','','',''),(383,181,5,'High','','',''),(384,181,6,'Medium','','',''),(385,182,1,'High','High complexity due to uncertain market conditions','',''),(386,182,2,'High','','',''),(387,182,3,'Medium','','',''),(388,182,4,'High','','',''),(389,182,5,'High','','',''),(390,182,6,'High','','',''),(391,183,1,'Medium','high risk due to uncertainty','',''),(392,183,2,'Medium','','',''),(393,183,3,'Medium','','',''),(394,183,4,'Medium','','',''),(395,183,5,'Medium','','',''),(396,183,6,'N/A','','',''),(397,184,1,'Low','High complexity due to market risk','',''),(398,184,2,'High','','',''),(399,184,3,'Low','','',''),(400,184,4,'Medium','','',''),(401,184,5,'Medium','','',''),(402,184,6,'Low','','',''),(403,185,1,'N/A','','',''),(404,185,2,'N/A','','',''),(405,185,3,'N/A','','',''),(406,185,4,'N/A','','',''),(407,185,5,'N/A','','',''),(408,185,6,'N/A','','',''),(409,188,1,'N/A','','',''),(410,188,2,'N/A','','',''),(411,188,3,'N/A','','',''),(412,188,4,'N/A','','',''),(413,188,5,'N/A','','',''),(414,188,6,'N/A','','',''),(415,190,1,'N/A','High complexity due to uncertain market conditions','',''),(416,190,2,'Medium','','',''),(417,190,3,'Low','','',''),(418,190,4,'Low','','',''),(419,190,5,'Medium','','',''),(420,190,6,'Low','','',''),(421,192,1,'N/A','complexity is high','',''),(422,192,2,'N/A','','',''),(423,192,3,'N/A','','',''),(424,192,4,'N/A','','',''),(425,192,5,'N/A','','',''),(426,192,6,'N/A','','',''),(427,202,1,'N/A','t1','',''),(428,202,2,'High','t2','',''),(429,202,3,'Medium','t4','',''),(430,202,4,'Medium','t5','',''),(431,202,5,'Medium','t6','',''),(432,202,6,'N/A','t7','',''),(433,203,1,'Low','','',''),(434,203,2,'Low','','',''),(435,203,3,'N/A','','',''),(436,203,4,'Medium','','',''),(437,203,5,'N/A','','',''),(438,203,6,'N/A','','',''),(439,204,1,'Low','c1','2','1'),(440,204,2,'Medium','c2','2','2'),(441,204,3,'Low','c3','2','1'),(442,204,4,'N/A','c4','2','0'),(443,204,5,'Low','c5','0','0'),(444,204,6,'Low','c6','0','0'),(445,205,1,'Low','abc','2','1'),(446,205,2,'Medium','abc','1','3'),(447,205,3,'High','abc','2','3'),(448,205,4,'High','abc','3','2'),(449,205,5,'N/A','abc','1','0'),(450,205,6,'N/A','abc','0','1'),(451,208,1,'Low','abc','2','1'),(452,208,2,'Medium','abc','1','3'),(453,208,3,'High','abc','3','2'),(454,208,4,'Medium','abc','3','1'),(455,208,5,'Low','abc','1','1'),(456,208,6,'High','abc','3','3'),(457,209,1,'Low','zdvczdv','1','1'),(458,209,2,'High','zdvzdv','3','3'),(459,209,3,'High','zvdzv','3','2'),(460,209,4,'Medium','sdfsdf','3','1'),(461,209,5,'High','sdfsdf','2','3'),(462,209,6,'Low','sdfsdf','1','2'),(463,210,1,'High','High Complexity due to market conditions','2','3'),(464,210,2,'High','','3','2'),(465,210,3,'High','','3','2'),(466,210,4,'High','','2','3'),(467,210,5,'High','','2','3'),(468,210,6,'Medium','','3','1'),(469,211,1,'High','High complexity due to market conditions','2','3'),(470,211,2,'High','','3','2'),(471,211,3,'High','','3','2'),(472,211,4,'High','','2','3'),(473,211,5,'High','','2','3'),(474,211,6,'Medium','','3','1'),(475,212,1,'High','High complexity due to uncertain market conditions','3','2'),(476,212,2,'High','','3','2'),(477,212,3,'Low','','2','1'),(478,212,4,'Medium','','2','2'),(479,212,5,'Low','','1','1'),(480,212,6,'High','','3','2'),(481,213,1,'Low','High complexity','2','1'),(482,213,2,'Medium','','1','3'),(483,213,3,'Medium','','2','2'),(484,213,4,'Medium','','1','3'),(485,213,5,'Low','','2','1'),(486,213,6,'Low','','1','1'),(487,214,1,'Low','abc','2','1'),(488,214,2,'Low','','2','1'),(489,214,3,'N/A','','0','2'),(490,214,4,'Low','','1','1'),(491,214,5,'High','','3','2'),(492,214,6,'Low','','2','1'),(493,215,1,'Low','abc','2','1'),(494,215,2,'Low','abc','1','2'),(495,215,3,'High','','2','3'),(496,215,4,'Low','','1','1'),(497,215,5,'Low','','1','1'),(498,215,6,'High','','3','2'),(499,216,1,'Medium','abc','3','1'),(500,216,2,'Low','','1','1'),(501,216,3,'High','','3','2'),(502,216,4,'High','','2','3'),(503,216,5,'Medium','','2','2'),(504,216,6,'Medium','','2','2');
/*!40000 ALTER TABLE `strategicrisk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `employeeId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (0,'no user','123','123',0),(1,'faheem','faheem','test@email.com',1),(2,'test','test','hy1@email.com',2),(3,'hyadmin','hyadmin','hyad',3),(4,'jk','jk','jk',4),(5,'hy2','hy2','hy2@emai.com',5),(6,'testadmin','testadmin','test1',6),(7,'test1','test1','test1',7),(8,'hy3','hy3','hy3',8),(9,'mgm','mgm','mgm',9),(10,'jj','jj','jj',10),(11,'jadmin','jadmin','jadmin',11),(12,'j1','j1','j1',12),(13,'j2','j2','j2',13),(14,'t1','t1','t1',14),(15,'tadmin','tadmin','tadmin',15),(16,'thead','thead','t2',16),(17,'t3','t3','t3',17),(18,'t4','t4','t4',18),(22,'jgmg','jmgm','jmgm',23),(23,'ptelenor','ptelenor','telenor1@gmail.com',24),(24,'ptelenor2','ptelenor2','telenor2@gmail.com',25),(25,'tt@tt.com','tttt','tt@tt.com',26),(26,'tt1@tt.com','tt','tt1@tt.com',27),(27,'tt2@tt.com','jj','tt2@tt.com',28),(28,'tt3@tt.com','jh','tt3@tt.com',29),(29,'tt111@tt.com','tt','tt111@tt.com',30),(30,'tt33@tt.com','y2','tt33@tt.com',31),(31,'ptelenor3@gmail.com','ptelenor3','ptelenor3@gmail.com',32),(32,'ptelenor4@gmail.com','ptelenor4','ptelenor4@gmail.com',33),(33,'poladmin@gmail.com','poladmin','poladmin@gmail.com',34),(34,'arladmin@gmail.com','arladmin','mfaheempiracha@gmail.com',35),(35,'arlauditor@gmail.com','arlauditor','mfaheempiracha@gmail.com',36),(36,'arlhead@gmail.com','arlhead','mfaheempiracha@gmail.com',37),(37,'arlteamlead@gmail.com','arlteamlead','mfaheempiracha@gmail.com',38),(38,'arlauditor1@gmail.com','arlauditor1','mfaheempiracha@gmail.com',39),(39,'arlauditor2@gmail.com','arlauditor2','mfaheempiracha@gmail.com',40),(40,'arlauditor3@gmail.com','arlauditor3','mfaheempiracha@gmail.com',41),(41,'arlauditor4@gmail.com','arlauditor','mfaheempiracha@gmail.com',42),(42,'arlauditor5@gmail.com','arlauditor5','mfaheempiracha@gmail.com',43),(43,'arlmgm@gmail.com','arlmgm','arlmgm@gmail.com',44),(44,'arlmgm1@gmail.com','arlmgm1','arlmgm1@gmail.com',45),(45,'demotestadmin@gmail.com','demotestadmin','demotestadmin@gmail.com',46),(46,'demofin1@gmail.com','demofin1','demofin1@gmail.com',47),(47,'demofin2@gmail.com','demofin2','demofin2@gmail.com',48),(48,'demoit1@gmail.com','demoit1','demoit1@gmail.com',49),(49,'demobus1@gmail.com','demobus1','demobus1@gmail.com',50),(50,'demoteamlead@gmail.com','demoteamlead','demoteamlead@gmail.com',51),(51,'demoteamlead@gmail.com','demoteamlead','demoteamlead@gmail.com',52),(52,'demohead@gmail.com','demohead','demohead@gmail.com',53),(53,'demohead1@gmail.com','demohead','demohead1@gmail.com',54),(54,'demoadmin@gmail.com','demoadmin','demoadmin@gmail.com',55),(55,'korgentadmin1@gmail.com','korgentadmin1','korgentadmin1@gmail.com',56),(56,'korgentadmin@gmail.com','korgentadmin','korgentadmin@gmail.com',57),(57,'korgenthead@gmail.com','korgenthead','korgenthead@gmail.com',58),(58,'korgentteamlead@gmail.com','korgentteamlead','korgentteamlead@gmail.com',59),(59,'korgentfin1@gmail.com','korgentfin1','korgentfin1@gmail.com',60),(60,'korgentfin2@gmail.com','korgentfin2','korgentfin2@gmail.com',61),(61,'korgentit1@gmail.com','korgentit1','korgentit1@gmail.com',62),(62,'korgentbus1@gmail.com','korgentbus1','korgentbus1@gmail.com',63),(63,'korgentwc@gmail.com','korgentwc','korgentwc@gmail.com',64),(64,'korgentenv@gmail.com','korgentenv','korgentenv@gmail.com',65),(65,'junaidp@gmail.com','test','junaidp@gmail.com',66),(66,'Sulman.javed@zong.com.pk','expired','Sulman.javed@zong.com.pk',67),(67,'Adnan.hashmi@zong.com.pk','expired','Adnan.hashmi@zong.com.pk',68),(68,'Umar.hafeez@zong.com.pk','expiredd','Umar.hafeez@zong.com.pk',69),(70,'jubileeadmin@email.com','admin','jubileeadmin@email.com',71),(71,'Adeel.Khan@jubileelife.com','adeel','Adeel.Khan@jubileelife.com',72),(72,'waseem.hassan@jubileelife.com','waseem','waseem.hassan@jubileelife.com',73),(73,'Faizan.Ahmed_ia@jubileelife.com','faizan','faisal.qasim@jubileelife.com',74),(74,'asif.akbar@jubileelife.com','asif','asif.akbar@jubileelife.com	',75),(75,'admin@bestway.com','admin','admin@bestway.com',76),(76,'amjad@bestway.com.pk','amjad','amjad@bestway.com.pk',77),(77,'audit3@bestway.com.pk','adnan','audit3@bestway.com.pk',78),(78,'audit2@bestway.com.pk','saqib','audit2@bestway.com.pk',79),(79,'buga@emai.com','bugadmin','buga@emai.com',80),(80,'bHead@email.com','bhead','bHead@email.com',81),(81,'bteamlead@emai.com','bteamlead','bteamlead@emai.com',82),(82,'bauditor@email.com','bauditor','bauditor@email.com',83),(83,'zohaib1112@hotmail.com','zohaib','zohaib1112@hotmail.com',84),(84,'adminblack@email.com','admin','adminblack@email.com',85),(85,'Headblack@email.com','head','Headblack@email.com',86),(86,'teamleadblack@email.com','teamlead','teamleadblack@email.com',87),(87,'auditorblack@email.com','auditor','auditorblack@email.com',88),(88,'managementblack@email.com','management','managementblack@email.com',89),(89,'adminhonda@email.com','admin','adminhonda@email.com',90),(90,'honda@email.com','honda','honda@email.com',91),(91,'headhonda@email.com','head','headhonda@email.com',92),(92,'teamleadhonda@email.com','teamlead','teamleadhonda@email.com',93),(93,'auditorhonda@email.com','auditor','auditorhonda@email.com',94),(94,'managementhonda@email.com','management','managementhonda@email.com',95),(95,'zohaib.abbasi@hyphenconsultancy.com','zohaib','zohaib.abbasi@hyphenconsultancy.com',96),(96,'n.sohail@alsuhaimi.net','naumansohail','n.sohail@alsuhaimi.net',97),(97,'saeed@alsuhaimi.net','saeed','saeed@alsuhaimi.net',98);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-12 10:10:58
