-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: abiliteclean
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
-- Table structure for table `activityobjective`
--

DROP TABLE IF EXISTS `activityobjective`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activityobjective` (
  `objectiveId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `objectiveName` varchar(1000) NOT NULL DEFAULT '',
  `subProcessId` int(10) unsigned NOT NULL DEFAULT '0',
  `referenceNo` varchar(45) NOT NULL DEFAULT '',
  `checked` int(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`objectiveId`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activityobjective`
--

LOCK TABLES `activityobjective` WRITE;
/*!40000 ALTER TABLE `activityobjective` DISABLE KEYS */;
INSERT INTO `activityobjective` VALUES (1,'All contracts, memorandums or understanding and letters of intent that financially obligate the company are approved & authorized as per the Authority Matrix.',2,'O-P2P-C-1',0),(2,'All contracts are managed in accordance with the contract.',2,'O-P2P-C-2',0),(3,'All orders are authorised  -  Orders are placed only when they satisfy management authorisation requirements',3,'O-P2P-C-3',0),(4,'All orders are accurately reported - Details of each order are accurately captured and allocated',3,'O-P2P-C-4',0),(5,'All obligations to creditors for goods and services received are recognised on a timely basis',4,'O-P2P-C-5',0),(6,'Obligations recognised are for goods and services received in accordance with required specifications',4,'O-P2P-C-6',0),(7,'Obligations for goods and services received are in accordance with management authorisations',4,'O-P2P-C-7',0),(8,'Payments are made only for goods/services authorized to be procured at a specified price.',5,'O-P2P-C-8',0),(9,'Payments are made only after being approved & authorized as per the company policy',5,'O-P2P-C-9',0),(10,'Payments are accurate and complete',5,'O-P2P-C-10',0),(11,'The vendor master file is a record of all authorised suppliers.',30,'O-P2P-C-11',0),(12,'All master file data elements are complete and accurate.',30,'O-P2P-C-12',0),(13,'All positions are authorised',14,'O-H2T-PP-1',0),(14,'All position placement data is accurately reflected in system (details of each employee is accurately captured and allocated)',14,'O-H2T-PP-2',0),(15,'Only authorised payroll payments are made',16,'O-H2T-PD-1',0),(16,'Payments are accurate, timely and complete.',16,'O-H2T-PD-2',0),(17,'Employment taxes are calculated in accordance with applicable legislation',16,'O-H2T-PD-3',0),(18,'Employee taxes and benefit payments are accurate and complete',16,'O-H2T-PD-4',0),(19,'Services performed are authorised and accurately reflected in the system.',31,'O-H2T-PT-1',0),(20,'Pay rates are authorised and accurately reflected in the system.',31,'O-H2T-PT-2',0),(21,'All fixed asset additions are properly authorised - Commitments to acquire fixed assets are only entered into based on appropriate management authorisation',32,'O-FA-A-1',0),(22,'Expenditure is correctly classified.',32,'O-FA-A-2',0),(23,'Capitalised expenditure is correctly classified - Valid capital expenditure is coded to the correct asset category in the fixed asset register',32,'O-FA-A-3',0),(24,'The fixed asset register is complete and accurate',32,'O-FA-A-4',0),(25,'All assets in use are depreciated',33,'O-FA-D-1',0),(26,'All assets withdrawn from use have their depreciation appropriately adjusted',33,'O-FA-D-2',0),(27,'Assets are depreciated over the best estimate of their useful lives',33,'O-FA-D-3',0),(28,'Depreciation is calculated and recorded on a timely basis',33,'O-FA-D-4',0),(29,'Depreciable amount and depreciation charge for each asset is accurately determined ',33,'O-FA-D-5',0),(30,'Depreciation charge components are properly identified and disclosed',33,'O-FA-D-6',0),(31,'Year-end asset processes are accurately performed on a timely basis.',33,'O-FA-D-7',0),(32,'All disposals are accounted for correctly and timely',34,'O-FA-DR-1',0),(33,'Disposals are properly authorised.',34,'O-FA-DR-2',0),(34,'Assets are disposed of economically.',34,'O-FA-DR-3',0),(35,'All disposals are valid.',34,'O-FA-DR-4',0),(36,'All reserves are accounted for correctly and timely',35,'O-FA-R-1',0);
/*!40000 ALTER TABLE `activityobjective` ENABLE KEYS */;
UNLOCK TABLES;

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
  `auditNotification` varchar(2200) DEFAULT NULL,
  `sendto` varchar(45) DEFAULT NULL,
  `cc` varchar(45) DEFAULT NULL,
  `year` int(10) unsigned DEFAULT NULL,
  `companyId` int(10) unsigned DEFAULT NULL,
  `status` varchar(45) NOT NULL DEFAULT '0',
  `initiatedBy` int(10) unsigned DEFAULT NULL,
  `approvedBy` int(10) unsigned DEFAULT NULL,
  `referenceNo` varchar(45) DEFAULT NULL,
  `fromPerson` varchar(45) DEFAULT NULL,
  `subject` varchar(45) DEFAULT NULL,
  `notificationSentDate` datetime DEFAULT NULL,
  PRIMARY KEY (`auditEngId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit_engagement`
--

LOCK TABLES `audit_engagement` WRITE;
/*!40000 ALTER TABLE `audit_engagement` DISABLE KEYS */;
INSERT INTO `audit_engagement` VALUES (129,74,'In Progress','','','','We hereby would like to inform you thot company Nome Internal Audit will be conducting o review of Engagement Nome from 14 December, 2017. This is port of the Internal Audit Pion for 2017, discussed with Top Monogement, Group Internal Audit, ond approved by member(s) of BoD/Audit Committee\nThe oudit will be performed by Mr DEF ond Mr GHI , members of Locol Internal Audit.\n The scope of the oudit includes review of odequocy, effectiveness & efficiency of the controls ond processes around Engagement Nome, with specific focus on xxxx. \n Please note thot IA teom moy toke contact with relevant members of your teom in the coming doys for o better process understanding relating to this oudit ond information required. Any resultant scope changes, if required, will be duly communicated. \n The completion ond finolisotion will depend on the ovoilobility of information ond interviewees, yet we oim ot delivering o droft report for your review ond comments by 8 FebruorY, 2018. \n You ore requested to forward the information in ti-is notification to those who will be involved in the oudit. If you hove ony questions concerning the oudit, please feel free to contact myself or e-moil ot xxxxx. \n We look forward to your continued support. ','junaidp@gmail.com','Hello ',2018,29,'0',116,0,'Eho','junaidp@gmail.com','Subject hee',NULL),(130,75,'In Progress','','','','','','',2018,29,'0',116,0,'','','',NULL),(131,76,'In Progress','','','','We hereby would like to inform you thot company Nome Internal Audit will be conducting o review of Engagement Nome from 14 December, 2017. This is port of the Internal Audit Pion for 2017, discussed with Top Monogement, Group Internal Audit, ond approved by member(s) of BoD/Audit Committee\nThe oudit will be performed by Mr DEF ond Mr GHI , members of Locol Internal Audit.\n The scope of the oudit includes review of odequocy, effectiveness & efficiency of the controls ond processes around Engagement Nome, with specific focus on xxxx. \n Please note thot IA teom moy toke contact with relevant members of your teom in the coming doys for o better process understanding relating to this oudit ond information required. Any resultant scope changes, if required, will be duly communicated. \n The completion ond finolisotion will depend on the ovoilobility of information ond interviewees, yet we oim ot delivering o droft report for your review ond comments by 8 FebruorY, 2018. \n You ore requested to forward the information in ti-is notification to those who will be involved in the oudit. If you hove ony questions concerning the oudit, please feel free to contact myself or e-moil ot xxxxx. \n We look forward to your continued support. ','MR XYZ,Director XXX','CXO Detail',2018,29,'0',116,0,'Korgent','Head of Internal Audit korgent','Inventory Management',NULL),(142,77,'In Progress','','','','','','',2018,29,'0',116,0,'','','',NULL),(145,78,'In Progress','','','','We hereby would like to inform you thot company Nome Internal Audit will be conducting o review of Engagement Nome from 14 December, 2017. This is port of the Internal Audit Pion for 2017, discussed with Top Monogement, Group Internal Audit, ond approved by member(s) of BoD/Audit Committee\nThe oudit will be performed by Mr DEF ond Mr GHI , members of Locol Internal Audit.\n The scope of the oudit includes review of odequocy, effectiveness & efficiency of the controls ond processes around Engagement Nome, with specific focus on xxxx. \n Please note thot IA teom moy toke contact with relevant members of your teom in the coming doys for o better process understanding relating to this oudit ond information required. Any resultant scope changes, if required, will be duly communicated. \n The completion ond finolisotion will depend on the ovoilobility of information ond interviewees, yet we oim ot delivering o droft report for your review ond comments by 8 FebruorY, 2018. \n You ore requested to forward the information in ti-is notification to those who will be involved in the oudit. If you hove ony questions concerning the oudit, please feel free to contact myself or e-moil ot xxxxx. \n We look forward to your continued support. ','MR XYZ,Director XXX','CXO Detail',2018,29,'0',116,0,'Company name Internal Audit','MR XYZ,Head of internal Audit','Engegement Name',NULL),(152,79,'In Progress','','','','We hereby would like to inform you thot company Nome Internal Audit will be conducting o review of Engagement Nome from 14 December, 2017. This is port of the Internal Audit Pion for 2017, discussed with Top Monogement, Group Internal Audit, ond approved by member(s) of BoD/Audit Committee\nThe oudit will be performed by Mr DEF ond Mr GHI , members of Locol Internal Audit.\n The scope of the oudit includes review of odequocy, effectiveness & efficiency of the controls ond processes around Engagement Nome, with specific focus on xxxx. \n Please note thot IA teom moy toke contact with relevant members of your teom in the coming doys for o better process understanding relating to this oudit ond information required. Any resultant scope changes, if required, will be duly communicated. \n The completion ond finolisotion will depend on the ovoilobility of information ond interviewees, yet we oim ot delivering o droft report for your review ond comments by 8 FebruorY, 2018. \n You ore requested to forward the information in ti-is notification to those who will be involved in the oudit. If you hove ony questions concerning the oudit, please feel free to contact myself or e-moil ot xxxxx. \n We look forward to your continued support. ','MR XYZ,Director XXX','CXO Detail',2018,29,'0',116,0,'Company name Internal Audit','MR XYZ,Head of internal Audit','Engegement Name',NULL),(153,80,'In Progress','','','','We hereby would like to inform you thot company Nome Internal Audit will be conducting o review of Engagement Nome from 14 December, 2017. This is port of the Internal Audit Pion for 2017, discussed with Top Monogement, Group Internal Audit, ond approved by member(s) of BoD/Audit Committee\nThe oudit will be performed by Mr DEF ond Mr GHI , members of Locol Internal Audit.\n The scope of the oudit includes review of odequocy, effectiveness & efficiency of the controls ond processes around Engagement Nome, with specific focus on xxxx. \n Please note thot IA teom moy toke contact with relevant members of your teom in the coming doys for o better process understanding relating to this oudit ond information required. Any resultant scope changes, if required, will be duly communicated. \n The completion ond finolisotion will depend on the ovoilobility of information ond interviewees, yet we oim ot delivering o droft report for your review ond comments by 8 FebruorY, 2018. \n You ore requested to forward the information in ti-is notification to those who will be involved in the oudit. If you hove ony questions concerning the oudit, please feel free to contact myself or e-moil ot xxxxx. \n We look forward to your continued support. ','MR XYZ,Director XXX','CXO Detail',2018,29,'0',116,0,'Company name Internal Audit','MR XYZ,Head of internal Audit','Engegement Name',NULL),(154,81,'In Progress','','','','','','',2018,29,'0',116,0,'','','',NULL),(155,82,'In Progress','','','','','','',2018,29,'0',116,0,'','','',NULL),(156,83,'In Progress','','','','We hereby would like to inform you thot company Nome Internal Audit will be conducting o review of Engagement Nome from 14 December, 2017. This is port of the Internal Audit Pion for 2017, discussed with Top Monogement, Group Internal Audit, ond approved by member(s) of BoD/Audit Committee\nThe oudit will be performed by Mr DEF ond Mr GHI , members of Locol Internal Audit.\n The scope of the oudit includes review of odequocy, effectiveness & efficiency of the controls ond processes around Engagement Nome, with specific focus on xxxx. \n Please note thot IA teom moy toke contact with relevant members of your teom in the coming doys for o better process understanding relating to this oudit ond information required. Any resultant scope changes, if required, will be duly communicated. \n The completion ond finolisotion will depend on the ovoilobility of information ond interviewees, yet we oim ot delivering o droft report for your review ond comments by 8 FebruorY, 2018. \n You ore requested to forward the information in ti-is notification to those who will be involved in the oudit. If you hove ony questions concerning the oudit, please feel free to contact myself or e-moil ot xxxxx. \n We look forward to your continued support. ','MR XYZ,Director XXX','CXO Detail',2018,29,'0',116,0,'Company name Internal Audit','MR XYZ,Head of internal Audit','Engegement Name',NULL),(157,84,'In Progress','','','','We hereby would like to inform you thot company Nome Internal Audit will be conducting o review of Engagement Nome from 14 December, 2017. This is port of the Internal Audit Pion for 2017, discussed with Top Monogement, Group Internal Audit, ond approved by member(s) of BoD/Audit Committee\nThe oudit will be performed by Mr DEF ond Mr GHI , members of Locol Internal Audit.\n The scope of the oudit includes review of odequocy, effectiveness & efficiency of the controls ond processes around Engagement Nome, with specific focus on xxxx. \n Please note thot IA teom moy toke contact with relevant members of your teom in the coming doys for o better process understanding relating to this oudit ond information required. Any resultant scope changes, if required, will be duly communicated. \n The completion ond finolisotion will depend on the ovoilobility of information ond interviewees, yet we oim ot delivering o droft report for your review ond comments by 8 FebruorY, 2018. \n You ore requested to forward the information in ti-is notification to those who will be involved in the oudit. If you hove ony questions concerning the oudit, please feel free to contact myself or e-moil ot xxxxx. \n We look forward to your continued support. ','MR XYZ,Director XXX','CXO Detail',2018,29,'0',116,0,'Company name Internal Audit','MR XYZ,Head of internal Audit','Engegement Name',NULL),(158,85,'In Progress','','','','','','',2018,29,'0',116,0,'','','',NULL),(159,86,'In Progress','','','','','','',2018,29,'0',116,0,'','','',NULL),(160,87,'In Progress','','','','We hereby would like to inform you thot company Nome Internal Audit will be conducting o review of Engagement Nome from 14 December, 2017. This is port of the Internal Audit Pion for 2017, discussed with Top Monogement, Group Internal Audit, ond approved by member(s) of BoD/Audit Committee\nThe oudit will be performed by Mr DEF ond Mr GHI , members of Locol Internal Audit.\n The scope of the oudit includes review of odequocy, effectiveness & efficiency of the controls ond processes around Engagement Nome, with specific focus on xxxx. \n Please note thot IA teom moy toke contact with relevant members of your teom in the coming doys for o better process understanding relating to this oudit ond information required. Any resultant scope changes, if required, will be duly communicated. \n The completion ond finolisotion will depend on the ovoilobility of information ond interviewees, yet we oim ot delivering o droft report for your review ond comments by 8 FebruorY, 2018. \n You ore requested to forward the information in ti-is notification to those who will be involved in the oudit. If you hove ony questions concerning the oudit, please feel free to contact myself or e-moil ot xxxxx. \n We look forward to your continued support. ','MR XYZ,Director XXX','CXO Detail',2018,29,'0',116,0,'Company name Internal Audit','MR XYZ,Head of internal Audit','Engegement Name',NULL),(161,88,'In Progress','','','','','','',2018,29,'0',116,0,'','','',NULL),(162,89,'In Progress','','','','We hereby would like to inform you thot company Nome Internal Audit will be conducting o review of Engagement Nome from 14 December, 2017. This is port of the Internal Audit Pion for 2017, discussed with Top Monogement, Group Internal Audit, ond approved by member(s) of BoD/Audit Committee\nThe oudit will be performed by Mr DEF ond Mr GHI , members of Locol Internal Audit.\n The scope of the oudit includes review of odequocy, effectiveness & efficiency of the controls ond processes around Engagement Nome, with specific focus on xxxx. \n Please note thot IA teom moy toke contact with relevant members of your teom in the coming doys for o better process understanding relating to this oudit ond information required. Any resultant scope changes, if required, will be duly communicated. \n The completion ond finolisotion will depend on the ovoilobility of information ond interviewees, yet we oim ot delivering o droft report for your review ond comments by 8 FebruorY, 2018. \n You ore requested to forward the information in ti-is notification to those who will be involved in the oudit. If you hove ony questions concerning the oudit, please feel free to contact myself or e-moil ot xxxxx. \n We look forward to your continued support. ','MR XYZ,Director XXX','CXO Detail',2018,29,'0',116,0,'Company name Internal Audit','MR XYZ,Head of internal Audit','Engegement Name',NULL);
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
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`audit_step_id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit_step`
--

LOCK TABLES `audit_step` WRITE;
/*!40000 ALTER TABLE `audit_step` DISABLE KEYS */;
INSERT INTO `audit_step` VALUES (68,'','','','','Non-Satisfactory',74,88,1,2018,29,116,116,'',NULL,'2018-10-18 00:29:50'),(71,'','','','','Non-Satisfactory',76,89,1,2018,29,116,116,'',NULL,'2018-10-23 09:47:02'),(72,'','','','','Satisfactory',78,90,1,2018,29,116,116,'',NULL,'2018-10-24 10:52:02'),(73,'','','','','Satisfactory',78,90,4,2018,29,0,116,'',NULL,'2018-10-24 10:51:33'),(74,'','','','','Satisfactory',78,91,1,2018,29,116,116,'',NULL,'2018-10-24 10:52:38'),(77,'','','','','Satisfactory',79,92,1,2018,29,116,116,'',NULL,'2018-10-25 12:41:01'),(78,'','','','','Satisfactory',79,92,4,2018,29,0,116,'',NULL,'2018-10-25 12:40:59'),(82,'','','','','Satisfactory',80,93,1,2018,29,116,116,'',NULL,'2018-11-06 10:08:15'),(83,'','','','','Satisfactory',81,94,1,2018,29,116,116,'',NULL,'2018-11-06 10:10:42'),(84,'','','','','Satisfactory',82,95,1,2018,29,116,116,'',NULL,'2018-11-08 12:10:28'),(85,'','','','','Non-Satisfactory',83,96,1,2018,29,116,116,'',NULL,'2018-11-11 13:10:41'),(86,'','','','','Non-Satisfactory',84,97,1,2018,29,116,116,'',NULL,'2018-11-11 15:38:23'),(87,'','','','','Satisfactory',77,98,1,2018,29,116,116,'',NULL,'2018-11-12 12:20:35'),(88,'','','','','Satisfactory',85,99,1,2018,29,116,116,'',NULL,'2018-11-12 14:48:47'),(89,'','','','','Satisfactory',86,100,1,2018,29,116,116,'',NULL,'2018-11-13 13:24:00'),(90,'','','','','Satisfactory',87,101,1,2018,29,116,116,'',NULL,'2018-11-13 14:50:40'),(91,'','','','','Satisfactory',87,101,4,2018,29,0,116,'',NULL,'2018-11-13 14:50:32'),(92,'','','','','Satisfactory',88,102,1,2018,29,116,116,'',NULL,'2018-11-13 16:29:17'),(93,'','','','','Satisfactory',89,103,1,2018,29,116,116,'',NULL,'2018-11-13 16:55:05');
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
  `step_no` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `responsible_control` int(11) DEFAULT NULL,
  `jobcreationid` int(11) DEFAULT NULL,
  `status` int(1) unsigned DEFAULT '0',
  `year` int(10) unsigned DEFAULT NULL,
  `companyId` int(10) unsigned NOT NULL DEFAULT '0',
  `initiatedBy` int(10) unsigned DEFAULT NULL,
  `approvedBy` int(10) unsigned DEFAULT NULL,
  `feedback` varchar(200) DEFAULT NULL,
  `suggestedControlsId` int(10) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`audit_work_id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit_work`
--

LOCK TABLES `audit_work` WRITE;
/*!40000 ALTER TABLE `audit_work` DISABLE KEYS */;
INSERT INTO `audit_work` VALUES (88,'104100','Test this application control by trying to issue a Purchase Order in the system without vendor being selected in the system by the autorized.',116,74,1,2018,29,116,116,'',1,NULL),(89,'118115','abc',116,76,1,2018,29,116,116,'',31,NULL),(90,'119112','abc',116,78,1,2018,29,116,116,'',1,NULL),(91,'112103','Test this application control by trying to issue a Purchase Order in the system without vendor being selected in the system by the autorized.',116,78,1,2018,29,116,116,'',1,NULL),(92,'99118','123',116,79,1,2018,29,116,116,'',32,NULL),(93,'99112','456',116,80,1,2018,29,117,116,'',1,NULL),(94,'110104','prg',116,81,1,2018,29,116,116,'',33,NULL),(95,'119112','some audit program ',116,82,1,2018,29,116,116,'',34,NULL),(96,'102104','Audit Program of RcUnit',116,83,1,2018,29,116,116,'',35,NULL),(97,'105100','Test this application control by trying to issue a Purchase Order in the system without vendor being selected in the system by the autorized.',116,84,1,2018,29,116,116,'',1,NULL),(98,'107118','secondunit audit prg',116,77,1,2018,29,116,116,'',36,NULL),(99,'122101','resource auditprogram',116,85,1,2018,29,116,116,'',37,NULL),(100,'102101','reportingcheckfinal',116,86,1,2018,29,116,116,'',38,NULL),(101,'97118','abc',116,87,1,2018,29,116,116,'',39,NULL),(102,'119102','z unit r audit work',116,88,1,2018,29,116,116,'',40,NULL),(103,'110104','abc',116,89,1,2018,29,116,116,'',41,NULL);
/*!40000 ALTER TABLE `audit_work` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auditprogramme`
--

DROP TABLE IF EXISTS `auditprogramme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auditprogramme` (
  `auditProgrammeId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `auditProgrammeName` varchar(500) NOT NULL DEFAULT '',
  `suggestedControlId` int(10) unsigned NOT NULL DEFAULT '0',
  `reviewer` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`auditProgrammeId`)
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditprogramme`
--

LOCK TABLES `auditprogramme` WRITE;
/*!40000 ALTER TABLE `auditprogramme` DISABLE KEYS */;
INSERT INTO `auditprogramme` VALUES (1,'Test this application control by trying to issue a Purchase Order in the system without vendor being selected in the system by the autorized.',1,58),(2,'Take a sample of POs\' as per the sampling methodology and trace with the documentary evidence of bidding process being undertaken as per the procurement policy..',2,58),(3,'Take a sample of POs\' as per the sampling methodology and trace with the documentary evidence of bidding process being undertaken as per the procurement policy.',3,0),(4,'Review the Authority Matrix for the segregation of responsibilities.Check a sample of contracts as per the sampling methodology to ensure that contracts are approved as per the Authority Matrix',4,0),(5,'Test this application control by trying to process an invoice in the system without PO being created',0,0),(6,'Test this application control by trying to issue a Purchase Order in the system without contract being signed off in the system',5,0),(7,'Take a sample of contracts as per the sampling methodology and trace with the documentary evidence of approvals on the contract control sheet',6,0),(8,'Take a sample of contracts as per the sampling methodology and ensure that contracts are approved as per the authority matrix',7,0),(9,'Take a sample of contracts as per the sampling methodology and trace with the documentary evidence of approvals on the contract control sheet',8,0),(10,'Review the criteria for identification of embedded derivatives and ensure compliance with the accounting standards.Through targeted sampling of contracts check if the criteria of embedded derivatives is correctly applied',9,0),(11,'Review the criteria of vendor performance evaluation and check its appropriatenessTake a sample of completed contracts as per the sampling methodology and trace with the vendor performance evaluations being performed. Review the resonableness of evaluation, actions being taken and input into the vendor data base ',10,0),(12,'1-Check expenditures/payments being categorized as exempt from issuance of PO and conclude on the reasonableness/justification                    2-Obtain a system generated list showing payments without Pos\' and ensure no payments from categories being non exempt      3- Check whether certain categories are marked as PO exempt in the system i.e tax payments, utility bill payments etc.Other than these marked categories system restricts the processing of invoice without a PO      ',11,0),(13,'1.Based on the sample selected review the monitoring being performed by the contract owner with respect to planned vs. actual hours.       2. Take a sample of contracts and validate that the hours in excess of planned/ordered are supported by authorized \"change work orders\"',12,0),(14,'Check a system based application control that no PO can be issued without approved and authorized Purchase Requisition',13,0),(15,'Based on a system based sample check from a population of Pos\' that these are approved as per the Authority Matrix',14,0),(16,'Generate a list of Pos\' with variations.From a system based sample test that all the variations are approved as per the authority matrix',15,0),(17,'1- Review the job descriptions of the relevant employees to ensure segregation of duties         2. Take a sample of payments and ensure that all these tasks are being perfomred by separate individuals                  3- Review the Access Control List of ERP and ensure that segregation of duties exist in the system',16,0),(18,'Test this application control by attempting to process a PO without filling all the mandatory fields',17,0),(19,'Test this application control by attempting to issue a PO without a valid PR',18,0),(20,'Test this application control by processing a PO and observe if vendor related information is directly extracted from the vendor module',19,0),(21,'Take a sample of Pos\' issued during the peroid and review the approvals as per the autority matrix',20,0),(22,'From a system based sample check if correct account coding is performed by the responisble',22,0),(23,'Review the item catalogue and select a sample to ensure its accuracy.Process one PO and observe whether correct gl code is automatically embedded.',23,0),(24,'Test the integration of the two systems i.e \"Vendor selection\" module and Ordering Module',24,0),(25,'Take a sample of Pos\' issued during the peroid and review the approvals as per the autority matrix',25,0),(26,'Take a sample of Pos\' issued during the peroid and validate the incorporation of correct vendor ',26,0),(27,'Test the effectiveness of GR/IR account on a system based sample. Review the appropriateness of action steps being taken on the reconciling and long outstanding items.',42,0),(28,'Test the effectiveness of review being performed on long outstanding PO on a system based sample. Review the appropriateness of action steps being taken on long outstanding Pos\'',43,0),(29,'From a system based sample match the physical inspection form being signed off by a person in a warehouse being separate from the person responsible for GRN in the system with the Pos\'. ',44,0),(30,'1.Check a system based control whereby PO is not processed until the GRN field is filled.      2. Check the application control of reminders being sent. ',45,0),(31,'Review the terms of the contract/PO with vendors with respect to instructions of invoice despatch. Review the invoice log being maintained and check the appropriateness of the same.Check the procedure of dealing with any Non Compliance by the vendor.',46,0),(32,'Check the application control of a 3 way match',47,0),(33,'Review warehouse procedures for written and practical compliance with goods inspection requirements.',48,0),(34,'Review of system ordering and receipting access to ensure SOD.Test on a sample basis to ensure unaithorized person can not enter GRN etc ',49,0),(35,'Check the application control of a 3 way match',50,0),(36,'Check the application control of a 3 way match',51,0),(37,'Review accounts payable personnel system access, ensuring access to purchasing and receiving activities is restricted.',53,0),(38,'Reperform system match observing accounting entries created at each match.',54,0),(39,'Review Authorization Matrix specifically for the payment approval authorization',55,0),(40,'Check system based control on a selected sample',56,0),(41,'Select appropriate sample and ensure that Accounts Payable  Manager has correclty valided the approval by the authorized personnel',57,0),(42,'Observe and assess adequacy system controls in place to prevent the changing of bank details during EFT run.',58,0),(43,'Select a sample and assess the appropriateness of review by the Management',59,0),(44,'Select a sample and assess the appropriateness of review by the Accounts Payable Team',60,0),(45,'Reperform system match observing accounting entries created at each match.',61,0),(47,'Reperform system match observing accounting entries created at each match.',62,0),(48,'Review accounts payable personnel physical access, ensuring access to purchasing and receiving activities is restricted.',52,0),(49,'Obtain report detailing users with system access to change vendor master data.  Review for reasonableness.',63,0),(50,'From a sample of newly selected vendors created during the year, trace to approval by the Procurement Head and Weighted Decision Analysis, Vendor Risk Analysis;if applicable',64,0),(51,'Obtain report detailing users with system access to change vendor master data.  Review for reasonableness.',65,0),(52,'Review vendor confirmation of change request.',66,0),(53,'Review vendor masterfile change report against original vendor request form.',67,0),(54,'Review inactive vendor reports and inquire into vendors not active but not yet removed.',68,0),(55,'From a sample of newly selected vendors created during the year, trace to approval by the Procurement Head ',69,0),(56,'On sample basis add test data to ensure that after the vendor slection, odering module accurately extract the price against the selected vendor in PO',21,0),(57,'Review the organization structure and roles and responsibilites for appropriateness of segregation of duties',70,0),(58,'From a sample of transfers / recruits, trace details from pre-hire activity form to system.',71,0),(59,'Review the Authority Matrix for appropriateness. Further, trace the approval of new hires against the authority matrix',72,0),(60,'Understand the system functioning and integration and test on a sample of one.',73,0),(61,'Review master data checking process for a sample of new or transferred employees.  Where evidence of review not available reperform check.',74,0),(62,'Review system access profiles around adding / changing / deleting high risk employee data.',75,0),(63,'Review master data checking process for a sample of new or transferred employees.  Where evidence of review not available reperform ',76,0),(64,'From a sample of  pay runs, obtain evidence of review of the corresponding master file change report.',77,0),(65,'Review system access profiles within the on-line banking system for reasonableness.',78,0),(66,'Observe security and access controls to payroll banking information.',79,0),(67,'Review payroll clearing account reconciliation for timely clearing of unreconciled differences.',80,0),(68,'Review system configuration to detect duplicate payroll data and assess for reasonableness.',81,0),(69,'Review duplicate reports and inquire into follow-up up actions by Payroll Supervisors.',82,0),(70,'From a sample of pay run  discrepancy reports, trace a sample of 25 discrepancies to appropriate follow-up actions by Payroll Supervisors.',83,0),(71,'Observe system payroll calculation and pay run configuration.  Observe procedures around configuration reviews and changes.',84,0),(72,'From a sample of pay run  discrepancy reports, trace a sample of 25 discrepancies to appropriate follow-up actions by Payroll Supervisors.',85,0),(73,'Review a sample of intra-period variance analysis reviews performed by the Payroll Supervisor.  Inquire into significant intra-period differences.',86,0),(74,'From a sample of 25 pay runs, trace payroll reports to evidence of review and approval.',87,0),(75,'Review cost report variance analysis and inquire into follow-up actions where large discrepancies are detected.',88,0),(76,'From a sample of pay run  discrepancy reports, trace a sample of 25 discrepancies to appropriate follow-up actions by Payroll Supervisors.',89,0),(77,'From a sample of 25 pay runs, trace payroll reports to evidence of review and approval.',90,0),(78,'Observe system configuration to calculate employee deductions during pay runs.',91,0),(79,'Employees authorise all withholdings / deductions from payroll',92,0),(80,'Review employee complaint log for timely resolution of employee deduction queries.',93,0),(81,'From a sample of pay run  discrepancy reports, trace a sample of 25 discrepancies to appropriate follow-up actions by Payroll Supervisors.',94,0),(82,'From a sample of 25 pay runs, trace payroll reports to evidence of review and approval.',95,0),(83,'Review documented payroll disaster recovery plan for adequacy and appropriateness.  Determine when it was last reviewed / updated.',96,0),(84,'Observe procedures for recording due dates for payroll disbursements / submissions.  Determine compliance with these dates.',97,0),(85,'Review system access profiles within the on-line banking system for reasonableness.',98,0),(86,'Observe security and access controls to payroll banking information.',99,0),(87,'Review payroll clearing account reconciliation for timely clearing of unreconciled differences.',100,0),(88,'Observe system payroll calculation and pay run configuration.  Observe procedures around configuration reviews and changes.',101,0),(89,'Review a sample of intra-period variance analysis reviews performed by the Payroll Supervisor.  Inquire into significant intra-period differences.',102,0),(90,'Trace current tax rates to existing system tax rates.',103,0),(91,'Review a sample of monthly ',104,0),(92,'Review cost report variance analysis and inquire into follow-up actions where large discrepancies are detected.',105,0),(93,'Review a sample of intra-period variance analysis reviews performed by the Payroll Supervisor.  Inquire into significant intra-period differences.',106,0),(94,'Review a sample of monthly ',107,0),(95,'Observe procedures for recording due dates for payroll disbursements / submissions.  Determine compliance with these dates.',108,0),(96,'Review employment tax submission process including review of regular submission forms for completeness and compliance with tax authority requirements.',109,0),(97,'Observe procedures for recording due dates for payroll disbursements / submissions.  Determine compliance with these dates.',110,0),(98,'Inquiry into process to determine workers compensation payments amounts and to ensure compliance with relevant legislation.',111,0),(99,'From a sample of 25 workers compensation payments made, trace to employee weekly / fortnightly / monthly wages for consistency.  Inquire where inconsistencies exist.',112,0),(100,'Observe and assess the system control to allocate names to rosters.',113,0),(101,'From a selected sample of  hourly payroll reports during the year, trace to appropriate shift supervisor review and authorisation.',114,0),(102,'Assess system control using test data',115,0),(103,'Perform surprise inspection of work areas and examine the reported hours on that day',116,0),(104,'From a sample of 25 hourly payroll reports during the year, trace to appropriate shift supervisor review and authorisation.',117,0),(105,'System configured to report all duplicate entries. All exceptions followed up by payroll supervisor',118,0),(106,'Review payroll complaint log for timeliness and adequacy of payroll complaint resolution.',119,0),(107,'Check any Audit of employee time records  need to understand that employees worked for all time they have worked whether it was authorized or not.',120,0),(108,'Make sure that the management is aware of company policy regarding change in payrates  with proper approval and authorization.',121,0),(109,'From a sample of 25 master file change requests, trace to appropriate authorisation and review for completeness. ',122,0),(110,'Observe review procedures around verifying system master file change requests to master file changes.  ',123,0),(111,'Review master data checking process for a sample of 25 master file change requests.  Where evidence of review not available reperform check for a sample of 25 master file changes.',123,0),(112,'From a sample of 25 pay runs, obtain evidence of review of the corresponding master file change report.',123,0),(113,'Check that to hold each employee was accountable or not ',124,0),(114,'Make sure everyone completes their start and end shift responsibilities. And each employee should be held responsible for their specific duties.',125,0),(115,'From a sample of 25 hourly payroll reports during the year, trace to appropriate shift supervisor review and authorisation.',126,0),(116,'From a sample of 25 absence reports, trace to evidence of review and authorisation.',126,0),(117,'Review payroll complaint log for timeliness and adequacy of payroll complaint resolution.',126,0),(118,'Make sure that the pay rate for each of your employees is accurate. ',127,0),(119,'Check that  every pay rate increase needs to have some type of documentation and paperwork to simplify your auditing.',128,0),(120,'Track all the pay increases you gave to employees due to a promotion, and make sure that the pay rate matches that increase. This is also important if your employees work on a bonus or commission system',129,0),(121,'Observe review procedures around verifying system master file change requests to master file changes.  ',130,0),(122,'Review master data checking process for a sample of 25 master file change requests.  Where evidence of review not available reperform check for a sample of 25 master file changes.',130,0),(123,'From a sample of 25 pay runs, obtain evidence of review of the corresponding master file change report.',130,0),(124,'1)Observe who is responsible for authorising, recording and holding assets, identifying areas for possible segregation improvements.\r\n',131,0),(125,'Obtain samples of asset additions from the additions report and trace to approved set-up forms and to unique system allocated asset numbers.\r\n\r\nReview allocated budgets against actuals and inquire into larger cost variances',132,0),(126,'Review of pre-determined asset classification scheme for inappropriate asset classifications.',133,0),(127,'Obtain samples of capital expenditure requisitions and review classification of expenditure',134,0),(128,'Review purchase and sales agreements',135,0),(129,'Review the accounting policy manual and observe it is followed',136,0),(130,'Financial Accountant to review purchase orders raised throughout the month for correct capital / operating classification.',137,0),(131,'Obtain sample of asset additions from the additions report and trace classification to approved set-up forms.',138,0),(132,'Obtain samples of purchase order and work orders and verify that it is appropriately classified',139,0),(133,'Review the accounting policy manual and observe it is followed',140,0),(134,'Obtain samples and check approval for selection of classification code for each asset selected.',141,0),(135,'Review edit report and follow-up on amounts breaching approved limits.',142,0),(136,'Review reconciliation between the asset register and general ledger ensuring discrepancies have been resolved.',143,0),(137,'Perform system testing',144,0),(138,'Observe and review monthly edit reports.',145,0),(139,'Review edit reports and observe follow-up actions for assets appearing on this report.',146,0),(140,'Observe and review monthly edit reports.',147,0),(141,'Inspect Accountant\'s review of assets not depreciated report.  Confirm that assets not depreciated are not in use.',148,0),(142,'Management review of events occurring throughout the year which may impact on assets termination value.',148,0),(143,'Obtain sample of acquisitions and test compliance against  Accounting policy depreciation requirements.',149,0),(144,'Attempt to post a retrospective depreciation charge to previous financial year and observe system controls to prevent.',150,0),(145,'Obtain report detailing users who have access to amend depreciation rates and assess for reasonableness.',151,0),(146,'Inquire with Management and observe outcomes of periodic reviews.',152,0),(147,'Review depreciation reasonableness tests performed and inquire into significant variances.',153,0),(148,'Obtain sample and review the accuracy of system data against the acquired asset information checklist.',154,0),(149,'Inquire with Management and observe outcomes of  reviews.',155,0),(150,'Observe the system depreciation run and the procedures followed.  Reperform a sample of depreciation calculations.',156,0),(151,'Obtain sample of acquisitions and test compliance against  Accounting policy depreciation requirements.',157,0),(152,'Observe depreciation charges and accumulated depreciation regularly reconciled to general ledger',158,0),(153,'Observe the month-end depreciation run for compliance with month-end procedures documentation.',159,0),(154,'Observe the recording of an asset disposal in the system to ensure system controls are in place.',160,0),(155,'Obtain sample of asset disposals recorded in the system and trace to authorised disposal forms.',161,0),(156,'Obtain sample of disposal and test compliance against  Accounting policy  requirements.',162,0),(157,'Review reconciliation of general ledger and fixed asset register',163,0),(158,'Observe system calculation of gain / loss on disposal.  Reperform a sample of disposal gain / loss calculations for accuracy.',164,0),(159,'Accountant to prepare and review asset disposal report and confirm that disposals at less than fair value are reported.',164,0),(160,'Observe the recording of an asset disposal in the system to ensure system controls are in place.',164,0),(161,'Obtain report detailing those users who have access to dispose of assets in the system and observe for appropriateness.',165,0),(162,'Observe segregations in both the system and in practice.',166,0),(163,'Obtain report detailing those users who have access to dispose of assets in the system and observe for appropriateness.',167,0),(164,'Review third party documentation / reports detailing testing undertaken, reported findings and actions taken.',168,0),(165,'Obtain report detailing those users who have access to dispose of assets in the system and observe for appropriateness.',169,0),(166,'Test Accounting Policy requirements against a sample of asset disposals.',170,0),(167,'Review profit and loss on disposals account reconciliation ensuring discrepancies have been resolved.',171,0),(168,'Obtain list of changes/modifications to reserve tracking data\r\n\r\n',172,0),(169,'For a sample of reserve agree and confirm the third party reserve with internally determined reserves to ensure accuracy and completeness.\r\nFor a sample of reserve agree and confirm the third party reserve with internally determined reserves to ensure accuracy and completeness.\r\n',173,0),(170,'Ensure accuracy of the economic factors used in estimating values.\r\n\r\nReview checklist to ensure compliance.',174,0),(171,'Review reservoirs analysis report and ensure that there are no unusual trends/changes and investigate if there are any.\r\n',175,0),(172,'Obtain reconciliation for approval and authorisation.',176,0),(173,'Review reserve models and ensure that modified terms of leases or contractual obligations are completely considered in model.',177,0),(174,'Agree applicable regulations with the estimated procedure to ensure compliance and accuracy.',178,0);
/*!40000 ALTER TABLE `auditprogramme` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (0,'none','none','none@none.com'),(1,'hyphen','faheem','faheem@hyphenconsult.com'),(29,'korgent','k','k@email.com');
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companyskillrelation`
--

LOCK TABLES `companyskillrelation` WRITE;
/*!40000 ALTER TABLE `companyskillrelation` DISABLE KEYS */;
INSERT INTO `companyskillrelation` VALUES (25,1,2,2018,'4160'),(34,29,2,2018,'5600');
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
INSERT INTO `department` VALUES (0,'none',20000),(1,'IT',1950),(2,'Finance',2500),(3,'Business',750),(4,'Strategy',2000),(5,'Regulatory',2000),(6,'Commercial',2000),(7,'Human Resource',2000),(8,'Procurement and Logistics',2000);
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
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (0,'no user','nouser@user.com',1,1,1,'no design',0,NULL,NULL,0,0,'yes',0,0,0,0),(1,'Muhammad Faheem Piracha','faheem@email.com',1,1,1,'des',1,NULL,'kj',1,1,'yes',1,1,1,1),(2,'hy1','hy1@email.com',1,NULL,1,'hj',NULL,'2014-12-25 00:00:00',NULL,2,1,'yes',0,2,1,3),(3,'hyad','hyad',1,NULL,1,'admin',NULL,'2014-12-29 00:00:00',NULL,3,3,'yes',0,2,1,4),(5,'hy2','hy2@email.com',1,1,1,'lead',NULL,'2014-12-30 00:00:00',NULL,5,2,'yes',0,2,1,2),(8,'hy3','hy3',1,NULL,1,'hy3',NULL,'2014-12-30 00:00:00',NULL,8,2,'yes',0,2,1,3),(9,'mgm','mgm',1,NULL,1,'mgm',NULL,'2014-12-31 00:00:00',NULL,9,9,'no',0,1,4,5),(115,'korgentadmin','korgentadmin@email.com',1,NULL,1,'Admin',NULL,'2018-10-17 00:00:00',NULL,114,115,'yes',0,2,29,4),(116,'korgenthead','korgenthead@email.com',1,NULL,1,'Head',NULL,'2018-10-17 00:00:00',NULL,115,116,'yes',0,2,29,1),(117,'korgentLead','korgentlead@email.com',1,NULL,1,'lead',NULL,'2018-10-17 00:00:00',NULL,116,116,'yes',0,2,29,2),(118,'korgentauditor','korgentauditor@email.com',1,NULL,1,'auditor',NULL,'2018-10-17 00:00:00',NULL,117,117,'yes',0,2,29,3),(119,'korgentMgm','korgentmgm@email.com',1,NULL,1,'mgm',NULL,'2018-10-17 00:00:00',NULL,118,119,'no',0,2,29,5);
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
  `implication` varchar(305) DEFAULT NULL,
  `implicationRating` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`exception_id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exception`
--

LOCK TABLES `exception` WRITE;
/*!40000 ALTER TABLE `exception` DISABLE KEYS */;
INSERT INTO `exception` VALUES (80,'Exception from 104100',74,119,119,'2018-10-20 00:00:00','First Unit','2018-10-20 00:00:00','Here are management comments',116,'Approved','1','Here are the final comments','1','Approved','Approved',68,2018,29,'Approved','Here are some recommendeations ','1','','0'),(81,'abc',76,119,119,'2018-10-23 00:00:00','Inventory',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',69,2018,29,'','abc','0','','1'),(82,'abc',76,119,119,NULL,'Inventory',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'',70,2018,29,NULL,'abc','0','','2'),(83,'abc',78,119,119,'2018-10-25 00:00:00','Third Unit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',72,2018,29,'','abc','0','','0'),(84,'abc',78,119,119,'2018-10-25 00:00:00','Third Unit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',73,2018,29,'','abc','0','','1'),(85,'abc',78,119,119,'2018-10-25 00:00:00','Third Unit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',74,2018,29,'','abc','0','','2'),(86,'abc',78,119,119,'2018-10-25 00:00:00','Third Unit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',75,2018,29,'','abc','0','','0'),(87,'abc',78,119,119,'2018-10-25 00:00:00','Third Unit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',76,2018,29,'','abc','0','','2'),(88,'123',79,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,77,2018,29,NULL,NULL,'0','','2'),(89,'123',79,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,78,2018,29,NULL,NULL,'0','','1'),(90,'456',80,119,119,'2018-10-26 00:00:00','Fifth Unit','2018-10-03 00:00:00','456',116,'Approved','0','456','1','Approved','Approved',79,2018,29,'','456','0','','1'),(91,'check exception',81,119,119,'2018-11-15 00:00:00','engViewCorrec',NULL,NULL,116,'Approved','0',NULL,'0','Approved','Approved',80,2018,29,'cmnt','any reccomendation','0','anhy implications to check ','1'),(92,'check exception',81,119,119,NULL,'engViewCorrec',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'',81,2018,29,NULL,'recc','0','hello','2'),(93,'fifth exception',80,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,82,2018,29,NULL,NULL,'0',NULL,'1'),(94,'engagement excweption',81,119,119,NULL,'engViewCorrec',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'',83,2018,29,NULL,'qwwe','0','','1'),(95,'reportingExceptionCheck',82,119,119,NULL,'reportingcheck',NULL,'',116,NULL,'0',NULL,'0',NULL,'',84,2018,29,NULL,NULL,'0',NULL,'1'),(96,'exception of RcUnit',83,119,119,NULL,'RcUnit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',85,2018,29,'ok','','0','','0'),(97,'exception2 of RcUnit',83,119,119,NULL,'RcUnit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',85,2018,29,'ok for 2','','0','','0'),(98,'rc1ex1',84,119,119,NULL,'RC1Unit','2018-11-26 00:00:00','coments',116,'Approved','1','final','1','Approved','Approved',86,2018,29,'ok','action steps','1','imp','0'),(99,'rc1ex2',84,119,119,NULL,'RC1Unit','2018-11-29 00:00:00','comm',116,'Approved','0','final2 ','1','Approved','Approved',86,2018,29,'ok','actions tep2 ','1','imp','0'),(100,'secndunit e1',77,119,119,'2018-11-29 00:00:00','Second Unit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',87,2018,29,'','sec act actn stp','0','sec unt  e1','0'),(101,'secndunit e2',77,119,119,'2018-11-29 00:00:00','Second Unit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',87,2018,29,'n','','0','jjj','1'),(102,'sec unit e3',77,119,119,'2018-11-26 00:00:00','Second Unit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',87,2018,29,'nb','','0','','2'),(103,'resource exc1',85,119,119,'2018-11-14 00:00:00','resource auditableunit','2018-11-21 00:00:00','management comments on resource exc1',116,'Sent','1',NULL,'0',NULL,'Approved',88,2018,29,'','resource recommendations','1','implications','1'),(104,'resource ex2',85,119,119,'2018-11-22 00:00:00','resource auditableunit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',88,2018,29,'','','0','qq','0'),(105,'resource ec3',85,119,119,'2018-11-22 00:00:00','resource auditableunit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',88,2018,29,NULL,'asdad','0','asd','2'),(106,'rc1',86,119,119,'2018-11-29 00:00:00','reportingfinalunit','2018-11-21 00:00:00','management comments to be add hete',116,'Approved','1','final cmnts','1','Sent','Approved',89,2018,29,'','rc1 actions','1','implication of rc1','1'),(107,'rc2',86,119,119,'2018-11-22 00:00:00','reportingfinalunit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',89,2018,29,'','rc2 actions','0','implication of rc2','2'),(108,'rc3',86,119,119,'2018-11-14 00:00:00','reportingfinalunit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',89,2018,29,'','rc3 act','0','imp rc3','0'),(109,'rc4',86,119,119,'2018-11-29 00:00:00','reportingfinalunit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',89,2018,29,'','rc4 act','0','imp rc4','2'),(110,'abc',87,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,90,2018,29,NULL,NULL,'0',NULL,NULL),(111,'abc',87,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,91,2018,29,NULL,NULL,'0',NULL,NULL),(112,'z unit r1',88,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,92,2018,29,NULL,NULL,'0',NULL,NULL),(113,'z unit r2',88,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,92,2018,29,NULL,NULL,'0',NULL,NULL),(114,'abc',89,119,119,'2018-11-13 00:00:00','new2','2018-11-13 00:00:00','f',116,'Approved','0','g','1','Approved','Approved',93,2018,29,'','e','1','d','0');
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
-- Table structure for table `informationrequest`
--

DROP TABLE IF EXISTS `informationrequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `informationrequest` (
  `informationRequestId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `requestItem` varchar(45) NOT NULL DEFAULT '',
  `contactResponsible` int(10) unsigned NOT NULL DEFAULT '0',
  `contactEmail` varchar(45) DEFAULT NULL,
  `dueDate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` int(10) unsigned NOT NULL DEFAULT '0',
  `sendNotication` tinyint(1) NOT NULL DEFAULT '0',
  `sendReminder` tinyint(1) NOT NULL DEFAULT '0',
  `assignedFrom` int(10) unsigned NOT NULL DEFAULT '0',
  `companyId` int(10) unsigned NOT NULL DEFAULT '0',
  `job` int(10) unsigned DEFAULT NULL,
  `respond` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`informationRequestId`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informationrequest`
--

LOCK TABLES `informationrequest` WRITE;
/*!40000 ALTER TABLE `informationrequest` DISABLE KEYS */;
INSERT INTO `informationrequest` VALUES (25,'todo Desciption check',115,'hamza@todo.com','2018-10-24 00:00:00',1,0,1,116,29,74,''),(26,'second inforequest',115,'','2018-10-19 00:00:00',0,0,1,116,29,74,''),(27,'c',116,'a','2018-10-19 00:00:00',0,0,0,116,29,74,'ffff'),(28,'Contracts required ',118,'zohaib1112@hotmail.com','2018-10-19 00:00:00',1,1,1,116,29,74,''),(29,'aaa',116,'s','2018-10-19 00:00:00',0,1,1,116,29,74,'rep to a'),(30,'aaa',116,'s','2018-10-19 00:00:00',0,1,1,116,29,74,'hello aaa'),(31,'Test Zohaib',117,'','2018-10-19 00:00:00',1,1,0,116,29,74,''),(32,'abc tedt',115,'','2018-10-19 00:00:00',1,1,0,116,29,74,''),(33,'job chck',116,'ttt','2018-11-28 00:00:00',1,1,1,116,29,81,'rep to jobcheck'),(34,'H work to receivr',116,'h@email','2018-11-22 00:00:00',0,0,1,116,29,74,NULL),(35,'checking job id saved or note',116,'@email','2018-11-14 00:00:00',1,0,1,116,29,83,NULL);
/*!40000 ALTER TABLE `informationrequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informationrequestlogs`
--

DROP TABLE IF EXISTS `informationrequestlogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `informationrequestlogs` (
  `informationrequestlogsId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL DEFAULT '',
  `assignedBy` varchar(45) NOT NULL DEFAULT '',
  `assignedTo` varchar(45) NOT NULL DEFAULT '',
  `date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`informationrequestlogsId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informationrequestlogs`
--

LOCK TABLES `informationrequestlogs` WRITE;
/*!40000 ALTER TABLE `informationrequestlogs` DISABLE KEYS */;
INSERT INTO `informationrequestlogs` VALUES (1,'aaa','116','116','2018-10-19 00:00:00'),(2,'aaa','116','116','2018-10-19 00:00:00'),(3,'aaa','116','116','2018-10-19 00:00:00'),(4,'aaa','116','116','2018-10-19 00:00:00'),(5,'aaa','116','116','2018-10-19 00:00:00'),(6,'job chck','116','116','2018-11-28 00:00:00'),(7,'aaa','116','116','2018-10-19 00:00:00');
/*!40000 ALTER TABLE `informationrequestlogs` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=312 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_employee_relation`
--

LOCK TABLES `job_employee_relation` WRITE;
/*!40000 ALTER TABLE `job_employee_relation` DISABLE KEYS */;
INSERT INTO `job_employee_relation` VALUES (257,117,74),(258,118,74),(259,116,74),(260,119,74),(261,118,75),(262,116,75),(263,117,76),(264,118,76),(265,116,76),(266,118,77),(267,116,77),(268,116,77),(269,118,78),(270,116,78),(271,118,79),(272,116,79),(273,117,80),(274,118,80),(275,116,80),(276,116,81),(277,116,82),(278,117,83),(279,118,83),(280,116,83),(281,117,84),(282,118,84),(283,116,84),(284,117,84),(285,118,84),(286,119,84),(287,116,84),(288,117,85),(289,118,85),(290,119,85),(291,116,85),(292,117,86),(293,118,86),(294,119,86),(295,116,86),(296,117,87),(297,118,87),(298,119,87),(299,116,87),(300,117,87),(301,118,87),(302,119,87),(303,116,87),(304,117,88),(305,118,88),(306,119,88),(307,116,88),(308,117,89),(309,118,89),(310,119,89),(311,116,89);
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
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_skill_relation`
--

LOCK TABLES `job_skill_relation` WRITE;
/*!40000 ALTER TABLE `job_skill_relation` DISABLE KEYS */;
INSERT INTO `job_skill_relation` VALUES (183,2,269),(184,1,270),(185,3,270),(186,3,271),(187,3,272),(188,1,272),(189,3,272),(190,3,273),(191,3,275),(192,3,276),(193,3,277),(194,2,278),(195,1,280),(196,1,281),(197,1,281),(198,1,282),(199,1,283),(200,2,285),(201,3,285),(202,1,285),(203,2,285),(204,3,285),(205,1,287),(206,2,288),(207,3,288);
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobandareaofexpertise`
--

LOCK TABLES `jobandareaofexpertise` WRITE;
/*!40000 ALTER TABLE `jobandareaofexpertise` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobcreation`
--

LOCK TABLES `jobcreation` WRITE;
/*!40000 ALTER TABLE `jobcreation` DISABLE KEYS */;
INSERT INTO `jobcreation` VALUES (74,'Strategic','Finance','Medium',1,'',269,'First Unit','19-10-18','26-10-18',5,116,2018,29,'1'),(75,'Strategic','Finance','High',1,'',270,'Sales','01-07-18','08-07-18',0,116,2018,29,'1'),(76,'Strategic','Finance','Medium',1,'',271,'Inventory','01-05-18','08-05-18',1,116,2018,29,'1'),(77,'Strategic','Finance','Medium',1,'',272,'Second Unit','01-09-18','08-09-18',2,116,2018,29,'1'),(78,'Strategic','Finance','High',1,'',273,'Third Unit','01-06-18','08-06-18',2,116,2018,29,'1'),(79,'Strategic','Finance','High',1,'',275,'Fourth Unit','01-10-18','08-10-18',1,116,2018,29,'1'),(80,'Strategic','Finance','Medium',1,'',276,'Fifth Unit','01-12-18','08-12-18',1,116,2018,29,'1'),(81,'Strategic','none','Medium',1,'yes',277,'engViewCorrec','31-10-18','07-11-18',1,116,2018,29,'1'),(82,'Strategic','Finance','Low',0,'skill',278,'reportingcheck','21-11-18','21-11-18',1,116,2018,29,'1'),(83,'Strategic','Finance','High',1,'',280,'RcUnit','24-11-18','01-12-18',2,116,2018,29,'1'),(84,'Strategic','Finance','Low',1,'',281,'RC1Unit','17-11-18','24-11-18',5,116,2018,29,'1'),(85,'Strategic','Finance','Medium',1,'',282,'resource auditableunit','12-11-18','19-11-18',2,116,2018,29,'1'),(86,'Strategic','IT','High',1,'',283,'reportingfinalunit','22-11-18','29-11-18',2,116,2018,29,'1'),(87,'Strategic','Finance','High',1,'',285,'new1','22-11-18','29-11-18',0,116,2018,29,'1'),(88,'Strategic','IT','High',1,'yes',287,'z unitR','13-11-18','20-11-18',1,116,2018,29,'1'),(89,'Strategic','Finance','High',1,'',288,'new2','01-02-18','08-02-18',5,116,2018,29,'1');
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
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobtimeestimation`
--

LOCK TABLES `jobtimeestimation` WRITE;
/*!40000 ALTER TABLE `jobtimeestimation` DISABLE KEYS */;
INSERT INTO `jobtimeestimation` VALUES (62,'Scope Level',1,40,1,41,'Outstation',1,49,269,2018,29,'1'),(63,'High level scope of work due to market conditions',1,40,5,45,'Outstation',0,45,270,2018,29,'1'),(64,'high',1,80,5,85,'Inhouse and Outstation',4,117,271,2018,29,'1'),(65,'abc',1,40,2,42,'Inhouse and Outstation',3,66,272,2018,29,'1'),(66,'abc',1,40,2,42,'Inhouse and Outstation',2,58,273,2018,29,'1'),(67,'123',1,40,3,43,'Inhouse',0,43,275,2018,29,'1'),(68,'456',1,40,2,42,'Inhouse',0,42,276,2018,29,'1'),(69,'scope',1,0,23,23,'Outstation',0,23,277,2018,29,'1'),(70,'scope',1,0,23,23,'Outstation',0,23,277,2018,29,'1'),(71,'scope',1,0,23,23,'Outstation',0,23,277,2018,29,'1'),(72,'scope',1,0,23,23,'Outstation',0,23,277,2018,29,'1'),(73,'level',1,120,1,121,'Outstation',0,121,280,2018,29,'1'),(74,'hie l',1,80,0,80,'Outstation',0,80,281,2018,29,'1'),(75,'high',1,40,1,41,'Outstation',0,41,282,2018,29,'1'),(76,'scope',1,40,1,41,'Outstation',0,41,283,2018,29,'1'),(77,'high',1,40,2,42,'Outstation',2,58,285,2018,29,'1'),(78,'level Z',1,40,1,41,'Inhouse and Outstation',0,41,287,2018,29,'1'),(79,'abc',1,40,2,42,'Outstation',2,58,288,2018,29,'1');
/*!40000 ALTER TABLE `jobtimeestimation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobtype`
--

DROP TABLE IF EXISTS `jobtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobtype` (
  `jobtypeId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `jobtypeName` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`jobtypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobtype`
--

LOCK TABLES `jobtype` WRITE;
/*!40000 ALTER TABLE `jobtype` DISABLE KEYS */;
INSERT INTO `jobtype` VALUES (1,'Assurance & Compilance'),(2,'Advisory & Consulting'),(3,'Fraud & investigation');
/*!40000 ALTER TABLE `jobtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `objectivejobrelation`
--

DROP TABLE IF EXISTS `objectivejobrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `objectivejobrelation` (
  `activityjobid` int(11) NOT NULL AUTO_INCREMENT,
  `jobId` int(10) DEFAULT NULL,
  `objectiveId` int(10) DEFAULT NULL,
  `status` int(10) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`activityjobid`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `objectivejobrelation`
--

LOCK TABLES `objectivejobrelation` WRITE;
/*!40000 ALTER TABLE `objectivejobrelation` DISABLE KEYS */;
INSERT INTO `objectivejobrelation` VALUES (31,74,1,4,'2018-10-17 23:36:03'),(32,76,17,4,'2018-10-22 12:48:35'),(33,78,1,4,'2018-10-24 10:48:07'),(34,78,2,4,'2018-10-24 10:48:07'),(35,79,1,4,'2018-10-25 12:37:39'),(36,80,1,4,'2018-10-25 12:59:33'),(37,81,18,3,'2018-10-31 14:54:45'),(38,81,19,3,'2018-10-31 14:54:45'),(39,81,20,4,'2018-10-31 14:55:18'),(40,81,21,4,'2018-10-31 14:55:19'),(41,81,22,4,'2018-10-31 14:55:19'),(42,81,23,4,'2018-10-31 14:55:22'),(43,81,24,4,'2018-10-31 14:55:23'),(44,81,25,4,'2018-10-31 14:55:23'),(45,81,26,4,'2018-10-31 14:55:24'),(46,81,27,4,'2018-10-31 14:55:24'),(47,81,28,4,'2018-10-31 14:55:24'),(48,81,29,3,'2018-10-31 14:55:25'),(49,81,30,3,'2018-10-31 14:55:25'),(50,81,31,3,'2018-10-31 14:55:25'),(51,82,17,3,'2018-11-08 12:03:06'),(52,82,32,4,'2018-11-08 12:03:01'),(53,82,33,3,'2018-11-08 12:03:06'),(54,83,34,3,'2018-11-11 12:59:02'),(55,83,35,4,'2018-11-11 12:59:17'),(56,83,36,4,'2018-11-11 12:59:30'),(57,83,37,3,'2018-11-11 12:59:33'),(58,84,1,3,'2018-11-11 15:35:42'),(59,77,32,4,'2018-11-12 12:15:47'),(60,85,2,4,'2018-11-12 14:41:24'),(61,86,32,4,'2018-11-13 13:20:25'),(62,87,1,4,'2018-11-13 14:47:38'),(63,88,17,4,'2018-11-13 16:26:37'),(64,89,1,4,'2018-11-13 16:51:15');
/*!40000 ALTER TABLE `objectivejobrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `process`
--

DROP TABLE IF EXISTS `process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `process` (
  `processId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `processName` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`processId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `process`
--

LOCK TABLES `process` WRITE;
/*!40000 ALTER TABLE `process` DISABLE KEYS */;
INSERT INTO `process` VALUES (1,'Procure to Pay'),(2,'Order to Cash'),(3,'Acquire to retire'),(4,'Hire to retire'),(5,'Record to Report'),(6,'Tax'),(7,'Treasury'),(8,'Fixed Assets');
/*!40000 ALTER TABLE `process` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resourceuse`
--

LOCK TABLES `resourceuse` WRITE;
/*!40000 ALTER TABLE `resourceuse` DISABLE KEYS */;
INSERT INTO `resourceuse` VALUES (172,1,0,269,2018,29),(173,2,1,269,2018,29),(174,3,0,269,2018,29),(175,1,0,270,2018,29),(176,2,1,270,2018,29),(177,3,0,270,2018,29),(178,1,1,271,2018,29),(179,2,1,271,2018,29),(180,3,0,271,2018,29),(181,1,0,272,2018,29),(182,2,1,272,2018,29),(183,3,0,272,2018,29),(184,1,0,273,2018,29),(185,2,1,273,2018,29),(186,3,0,273,2018,29),(187,1,0,275,2018,29),(188,2,1,275,2018,29),(189,3,0,275,2018,29),(190,1,0,276,2018,29),(191,2,1,276,2018,29),(192,3,0,276,2018,29),(193,1,0,277,2018,29),(194,2,0,277,2018,29),(195,3,0,277,2018,29),(196,1,1,280,2018,29),(197,2,1,280,2018,29),(198,3,1,280,2018,29),(199,1,1,281,2018,29),(200,2,1,281,2018,29),(201,3,0,281,2018,29),(202,1,0,282,2018,29),(203,2,1,282,2018,29),(204,3,0,282,2018,29),(205,1,0,283,2018,29),(206,2,1,283,2018,29),(207,3,0,283,2018,29),(208,1,0,285,2018,29),(209,2,1,285,2018,29),(210,3,0,285,2018,29),(211,1,0,287,2018,29),(212,2,1,287,2018,29),(213,3,0,287,2018,29),(214,1,0,288,2018,29),(215,2,1,288,2018,29),(216,3,0,288,2018,29);
/*!40000 ALTER TABLE `resourceuse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `riskcontrolmatrix`
--

DROP TABLE IF EXISTS `riskcontrolmatrix`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `riskcontrolmatrix` (
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
  `suggestedControlsId` int(10) NOT NULL DEFAULT '0',
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`risk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `riskcontrolmatrix`
--

LOCK TABLES `riskcontrolmatrix` WRITE;
/*!40000 ALTER TABLE `riskcontrolmatrix` DISABLE KEYS */;
INSERT INTO `riskcontrolmatrix` VALUES (76,NULL,NULL,129,2018,29,1,116,116,'',1,'2018-10-18 00:27:45'),(77,NULL,NULL,131,2018,29,1,116,116,'',31,'2018-10-23 09:28:53'),(78,NULL,NULL,145,2018,29,1,116,116,'',1,'2018-10-24 10:49:42'),(79,NULL,NULL,145,2018,29,1,116,116,'',2,'2018-10-24 10:49:42'),(80,NULL,NULL,152,2018,29,1,116,116,'',32,'2018-10-25 12:39:07'),(81,NULL,NULL,153,2018,29,1,117,116,'',1,'2018-10-25 13:01:14'),(82,NULL,NULL,154,2018,29,1,116,116,'',33,'2018-10-31 15:07:34'),(83,NULL,NULL,155,2018,29,1,116,116,'',34,'2018-11-08 12:06:03'),(84,NULL,NULL,156,2018,29,4,116,0,NULL,35,'2018-11-11 13:03:18'),(85,NULL,NULL,157,2018,29,4,116,0,NULL,1,'2018-11-11 15:36:31'),(86,NULL,NULL,142,2018,29,4,116,0,NULL,36,'2018-11-12 12:17:20'),(87,NULL,NULL,158,2018,29,4,116,0,NULL,37,'2018-11-12 14:46:14'),(88,NULL,NULL,159,2018,29,4,116,0,NULL,38,'2018-11-13 13:21:36'),(89,NULL,NULL,160,2018,29,1,116,116,'',39,'2018-11-13 14:49:15'),(90,NULL,NULL,161,2018,29,4,116,0,NULL,40,'2018-11-13 16:27:31'),(91,NULL,NULL,162,2018,29,1,116,116,'',41,'2018-11-13 16:53:01');
/*!40000 ALTER TABLE `riskcontrolmatrix` ENABLE KEYS */;
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
-- Table structure for table `riskjobrelation`
--

DROP TABLE IF EXISTS `riskjobrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `riskjobrelation` (
  `riskjobrelationId` int(11) NOT NULL AUTO_INCREMENT,
  `jobCreation` int(10) DEFAULT NULL,
  `riskObjective` int(10) DEFAULT NULL,
  `inherintRisk` varchar(45) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`riskjobrelationId`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `riskjobrelation`
--

LOCK TABLES `riskjobrelation` WRITE;
/*!40000 ALTER TABLE `riskjobrelation` DISABLE KEYS */;
INSERT INTO `riskjobrelation` VALUES (12,74,1,'1',4,'2018-10-17 23:39:00'),(14,76,25,'1',4,'2018-10-22 12:48:23'),(15,76,26,'1',4,'2018-10-22 12:48:41'),(16,78,1,'1',4,'2018-10-24 10:48:50'),(17,79,1,'1',4,'2018-10-25 12:38:06'),(18,80,1,'1',4,'2018-10-25 12:59:59'),(20,81,28,'0',4,'2018-10-31 15:02:33'),(22,82,24,'2',3,'2018-11-08 12:04:37'),(23,83,30,'1',3,'2018-11-11 13:00:05'),(24,83,31,'1',4,'2018-11-11 13:00:10'),(25,83,33,'1',4,'2018-11-11 13:00:10'),(26,83,32,'1',4,'2018-11-11 13:00:10'),(27,83,34,'1',3,'2018-11-11 13:01:16'),(28,83,35,'1',4,'2018-11-11 13:01:18'),(29,84,1,'1',4,'2018-11-11 15:36:06'),(30,77,36,'0',4,'2018-11-12 12:16:38'),(31,77,37,'0',4,'2018-11-12 12:16:42'),(32,85,4,'2',4,'2018-11-12 14:41:55'),(33,86,38,'0',4,'2018-11-13 13:21:05'),(34,87,1,'1',4,'2018-11-13 14:48:09'),(35,88,39,'0',4,'2018-11-13 16:27:05'),(36,89,1,'1',4,'2018-11-13 16:51:37');
/*!40000 ALTER TABLE `riskjobrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `riskobjective`
--

DROP TABLE IF EXISTS `riskobjective`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `riskobjective` (
  `riskID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `riskName` varchar(1000) NOT NULL DEFAULT '',
  `objectiveId` int(10) unsigned NOT NULL DEFAULT '0',
  `checked` int(1) unsigned NOT NULL DEFAULT '0',
  `riskReferenceNo` varchar(45) NOT NULL DEFAULT '',
  `riskRating` varchar(45) NOT NULL DEFAULT '',
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`riskID`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `riskobjective`
--

LOCK TABLES `riskobjective` WRITE;
/*!40000 ALTER TABLE `riskobjective` DISABLE KEYS */;
INSERT INTO `riskobjective` VALUES (1,'Competitive bidding process not undertaken resulting in : receiving substandard or incorrect supplies and / or services, paying too much for those supplies or services, fraud, delivery and / or production delaysss.',1,0,'R-P2P-C-1','1',NULL),(2,'Functional review (Finance & Legal review) not undertaken resulting in: terms and conditions not understood by either party to the agreement; no dispute resolution process; additional liabilities to company ; foreign currency exposure not considered, unreasonable payment terms.',1,0,'R-P2P-C-2','2',NULL),(3,'Embedded derivatives are not identified and reported when new contracts are entered into.',1,1,'R-P2P-C-3','0',NULL),(4,'(Services / goods received are not in accordance with the contract, resulting in: terms and conditions  not being met, contract scope not met / completed, disputes arise, variations not approved, additional liabilities aris',2,0,'R-P2P-C-4','2',NULL),(5,'(A) Orders are placed outside the system without an approved purchase requisition being raised   (eg. Direct orders, phone orders), resulting in loss of company assets',3,0,'R-P2P-C-5','',NULL),(6,'(B) Actual maintenance work performed exceeds the planned authorised work order amount, resulting in loss of company assets.',3,0,'R-P2P-C-6','',NULL),(7,'(c)  Duplicate, fictitious, or non-business related purchase orders are executed (either within or outside the system) resulting in loss of company assets and / or non-recording of creditors, commitments, operating costs.',3,0,'R-P2P-C-7','',NULL),(8,'D)Pricing, terms and conditions agreed to are not authorised and are onerous or detrimental to the business.',3,0,'R-P2P-C-8','',NULL),(9,'(F) Information on valid orders is incomplete or inaccurate in the system resulting in incorrect accounting allocations or valuations, and / or incorrect payments.',4,0,'R-P2P-C-9','',NULL),(10,'(G) Expenditures not classified (coded) in accordance with company policy resulting in incorrect expenditure allocations.',4,0,'R-P2P-C-10','',NULL),(11,'(H) Allocation of wrong vendor to purchase order, resulting in possible purchase from wrong vendor and a potential loss of company assets.',4,0,'R-P2P-C-11','',NULL),(12,'Goods received are not recorded on a timely basis (e.g. goods are delivered directly to requestor, goods remain in warehouse for long periods before being receipted in system).',5,0,'R-P2P-C-12','',NULL),(13,'Invoices received are not recorded on a timely basis resulting in misstatement of liabilities at period-end.',5,0,'R-P2P-C-13','',NULL),(14,'Goods received do not match price or quantity on purchase order but go undetected or unrecorded.',6,0,'R-P2P-C-14','',NULL),(15,'Goods are damaged when received but go undetected.',6,0,'R-P2P-C-15','',NULL),(16,'Goods and services are receipted into the system by unauthorised personnel',7,0,'R-P2P-C-16','',NULL),(17,'Unordered goods are receipted.',7,0,'R-P2P-C-17','',NULL),(18,'Ordered goods are receipted to an incorrect order',7,0,'R-P2P-C-18','',NULL),(47,'Fraudulent  or inappropriate payments made as a result of: payment for goods or services never / not yet received; payment of incorrect value, payment for something that is not a valid expense of the company)',8,0,'R-P2P-C-19','',NULL),(48,'Payments may not be appropriately authorized due to lack of awareness,errors or due to mal intetntions',9,0,'R-P2P-C-20','',NULL),(49,'Payment is made to incorrect bank account arising from changes made during the EFT process /manual process',10,0,'R-P2P-C-21','',NULL),(50,'Duplicate payments are made to the same supplier',10,0,'R-P2P-C-22','',NULL),(51,'Suppliers are over / under paid ',10,0,'R-P2P-C-23','',NULL),(52,'Unauthorised / fictitious vendors are included in the masterfile for fraudulent purposes.',11,0,'R-P2P-C-24','',NULL),(53,'Master file data records are incorrectly deleted/amended',12,0,'R-P2P-C-25','',NULL),(54,'Records are not entered correctly due to error/intentionally',12,0,'R-P2P-C-26','',NULL),(55,'Positions are filled without an approved authority for own self interest/expediting the hiring process resulting in loss in company assets.',13,0,'R-H2T-PP-1','',NULL),(56,'Information on employees is incomplete or inaccurate in the system (I.e. letter of offer does not reflect system data, bank details not accurate, leave allowances incorrect) due to errors or mal intentions',14,0,'R-H2T-PP-2','',NULL),(57,'Changes to standing data (I.e. bank account, etc) result in payment to the wrong person.',14,0,'R-H2T-PP-3','',NULL),(58,'(A) Access to payment system (I.e. online banking) is not restricted resulting in unauthorised payments.',15,0,'R-H2T-PD-1','',NULL),(59,'(B) Duplicate payments are made to the same employee resulting in loss of company assets',16,0,'R-H2T-PD-2','',NULL),(60,'(C) Payroll system incorrectly calculates pay run resulting in under / over payment.',16,0,'R-H2T-PD-3','',NULL),(61,'(D) Rejected items not identified resulting in under payment.',16,0,'R-H2T-PD-4','',NULL),(62,'(E) Employee deductions (e.g. health insurance, child support) are not appropriately authorised, recorded and executed.',16,0,'R-H2T-PD-5','',NULL),(63,'(F) Employees are not paid on a timely basis resulting in industrial disputes.',16,0,'R-H2T-PD-6','',NULL),(64,'(G) Payroll disbursement file is corrupted or interfered with prior to loading into the banking system.',16,0,'R-H2T-PD-7','',NULL),(65,'(H) Incorrect calculation of taxes (I.e. PAYG, payroll tax, superannuation) resulting in breach of legislation and / or penalties.',17,0,'R-H2T-PD-8','',NULL),(66,'(I) Authorities (third parties) are under / over paid, or not paid at all.',18,0,'R-H2T-PD-9','',NULL),(67,'(J) Authorities (third parties) are not paid on a timely basis resulting in penalties.',18,0,'R-H2T-PD-10','',NULL),(68,'(K) Workers compensation claim payments do not reflect approved claim liability.',18,0,'R-H2T-PD-11','',NULL),(69,'Fictitious time and attendance information is entered into the system.',19,0,'R-H2T-PT-1','',NULL),(70,'(B) Inaccurate time and attendance information is entered into the system.',19,0,'R-H2T-PT-2','',NULL),(71,'(C) Change in pay rates are unauthorised resulting in loss of company assets',20,0,'R-H2T-PT-3','',NULL),(72,'(D) Roster / shift changes are not accurately processed in the system resulting in under / over payment.',20,0,'R-H2T-PT-4','',NULL),(73,'(E) Pay rate changes (I.e. salary review, EBA changes, ad hoc changes) are not accurately processed in the system resulting in under / over payment.',20,0,'R-H2T-PT-5','',NULL),(74,'Commitments to acquire fixed assets are entered into prior to, or without, appropriate approval resulting in loss of company assets (i.e. cash)\r\n\r\n',21,0,'R-FA-A-1','',NULL),(75,'Asset can be acquired for more then its allocated budget',21,0,'R-FA-A-2','',NULL),(76,'Expenditure is allocated to the incorrect capital item.',22,0,'R-FA-A-3','',NULL),(77,'Expenditure incurred no longer qualifies as an asset (e.g. asset abandoned, net realisable value concerns)',22,0,'R-FA-A-4','',NULL),(78,'Expenditure is classified as capital instead of operating.',22,0,'R-FA-A-5','',NULL),(79,'Asset additions have been classified into the incorrect asset categories (I.e. land & buildings, PPE, EE&D)',23,0,'R-FA-A-6','',NULL),(80,'Fixed asset costs are not reflected in the fixed asset register (including costs supplementary to the purchase price, e.g. costs of installation and preparatory costs)',24,0,'R-FA-A-7','',NULL),(81,'Assets classified as held for sale (and not in use) are not recognised as such and incorrectly continue to be depreciated',26,0,'R-FA-D-2','',NULL),(82,'Assets temporarily withdrawn from use cease to have depreciation charged ',26,0,'R-FA-D-3','',NULL),(83,'Estimated useful lives are not properly determined on inception or reviewed in subsequent financial periods.',27,0,'R-FA-D-4','',NULL),(84,'Changes in estimated economic life incorrectly give rise to a retrospective depreciation charge (I.e. back-dated depreciation), rather than being included in depreciation charges on a prospective basis',27,0,'R-FA-D-5','',NULL),(85,'Material components of depreciable assets with different lives are not separately identified ',27,0,'R-FA-D-6','',NULL),(86,'Depreciation calculation run is initiated prior to asset register reaching the required state of readiness ',28,0,'R-FA-D-7','',NULL),(87,'Depreciation run is not completed and/or result is not booked into the general ledger',28,0,'R-FA-D-8','',NULL),(88,'Estimated residual values of depreciable assets are not properly determined or reviewed in subsequent financial periods ',29,0,'R-FA-D-9','',NULL),(89,'Depreciation calculation logic in FAR is flawed ',29,0,'R-FA-D-10','',NULL),(90,'Impairment charges are not separately reported from regular depreciation charges ',30,0,'R-FA-D-11','',NULL),(91,'Incorrect general ledger account assignment',30,0,'R-FA-D-12','',NULL),(92,'The asset register is not closed out on a timely basis resulting in incorrect asset reporting at year-end.',31,0,'R-FA-D-13','',NULL),(93,'Assets in use are not being depreciated ',25,0,'R-FA-D-1','',NULL),(94,'Depreciation rates are incorrectly modified or modified without authorisation',27,0,'R-FA-D-14','',NULL),(95,'Assets remain on fixed asset register subsequent to disposal and continue to be depreciated.',32,0,'R-FA-DR-1','',NULL),(96,'Proceeds from sale are not recorded in fixed asset register at total consideration received / receivable ',32,0,'R-FA-DR-2','',NULL),(97,'Assets are physically disposed  (from the asset register and / or physical disposal) without proper authorisation resulting in loss of company assets.',33,0,'R-FA-DR-3','',NULL),(98,'Assets are disposed for less than fair value resulting in a loss of company assets.',34,0,'R-FA-DR-4','',NULL),(99,'Proceeds on disposal are not received.',34,0,'R-FA-DR-5','',NULL),(100,' Inadequate information captured in the asset register to enable adequate matching of sale proceeds to assets.',35,0,'R-FA-DR-6','',NULL),(101,'Gains / losses on disposal are not separately identified for disclosure',32,0,'R-FA-DR-7','',NULL),(102,'Modification to reserve tracking data may be wrong',36,0,'R-FA-R-1','',NULL),(103,'Lack of completeness of disclosure being made.',36,0,'R-FA-R-2','',NULL),(104,'Lack of accuracy and reasonableness of estimates used to drive reserve values.',36,0,'R-FA-R-3','',NULL);
/*!40000 ALTER TABLE `riskobjective` ENABLE KEYS */;
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
  `userDefinedRating` varchar(45) DEFAULT NULL,
  `process` int(10) unsigned NOT NULL DEFAULT '1',
  `subProcess` int(10) unsigned NOT NULL DEFAULT '5',
  `jobType` int(10) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=289 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strategic`
--

LOCK TABLES `strategic` WRITE;
/*!40000 ALTER TABLE `strategic` DISABLE KEYS */;
INSERT INTO `strategic` VALUES (269,'First Objective',0,NULL,1,'Medium','First Unit',116,116,'initiated','OK',116,'2018-11-13 02:29:55',1,5,0,2018,1,29,'Low',1,5,1),(270,'Market Diversification',0,NULL,1,'High','Sales',116,116,'initiated','',116,'2018-11-11 09:02:46',1,5,0,2018,1,29,'High',1,5,1),(271,'Inventory Management',0,NULL,1,'Medium','Inventory',116,116,'initiated','',116,'2018-11-13 02:38:39',1,5,0,2018,1,29,'Medium',1,5,1),(272,'Second Objective',0,NULL,1,'Medium','Second Unit',116,116,'initiated','',116,'2018-11-13 02:38:39',1,5,0,2018,1,29,'Medium',1,5,1),(273,'Third Objective',0,NULL,1,'High','Third Unit',116,116,'initiated','',116,'2018-11-13 02:38:39',1,5,0,2018,1,29,'High',1,5,1),(274,'Fourth Objective',0,NULL,1,'Low',NULL,116,116,'deleted',NULL,0,'2018-10-25 07:33:12',0,1,0,2018,0,29,NULL,1,5,1),(275,'Fourth Objective',0,NULL,1,'High','Fourth Unit',116,116,'initiated','',116,'2018-11-11 09:02:47',1,5,0,2018,1,29,'High',1,5,1),(276,'Fifth Objective',0,NULL,1,'Medium','Fifth Unit',117,117,'initiated','',116,'2018-11-13 02:38:39',1,5,0,2018,1,29,'High',1,5,1),(277,'engagement View corrrecton',0,NULL,1,'Medium','engViewCorrec',116,116,'initiated','c',116,'2018-11-13 02:38:40',1,5,0,2018,1,29,'N/A',1,5,1),(278,'ReportingCheck',0,NULL,1,'Low','reportingcheck',116,116,'initiated','check reporting',116,'2018-11-13 02:38:40',1,5,0,2018,1,29,'N/A',1,5,1),(279,'Rc1',0,NULL,1,'Low',NULL,116,116,'deleted',NULL,0,'2018-11-11 07:10:56',0,1,0,2018,0,29,NULL,1,5,1),(280,'Rc',0,NULL,1,'High','RcUnit',116,116,'initiated','comments',116,'2018-11-13 02:38:40',1,5,0,2018,1,29,'Medium',1,5,1),(281,'Rc1',0,NULL,1,'Low','RC1Unit',116,116,'initiated','OK',116,'2018-11-13 02:38:40',1,5,0,2018,1,29,'N/A',1,5,1),(282,'reosurce objective',0,NULL,1,'Medium','resource auditableunit',116,116,'initiated','resource comment',116,'2018-11-12 09:30:42',1,5,0,2018,1,29,'High',1,2,1),(283,'reportingcheckfinal',0,NULL,1,'High','reportingfinalunit',116,116,'initiated','',116,'2018-11-13 08:18:40',1,5,0,2018,1,29,'High',1,5,1),(284,'abilite new1',0,NULL,1,'Low',NULL,116,116,'deleted',NULL,0,'2018-11-13 09:43:11',0,1,0,2018,0,29,NULL,1,5,1),(285,'abilite new1',0,NULL,1,'High','new1',116,116,'initiated','',116,'2018-11-13 09:44:56',1,5,0,2018,1,29,'High',1,2,1),(286,'Z Report',0,NULL,1,'Low',NULL,116,116,'deleted',NULL,0,'2018-11-13 11:23:11',0,1,0,2018,0,29,NULL,1,5,1),(287,'Z Report',0,NULL,1,'High','z unitR',116,116,'initiated','',116,'2018-11-13 11:24:22',1,5,0,2018,1,29,'Medium',1,5,1),(288,'abilite new2',0,NULL,1,'High','new2',116,116,'initiated','',116,'2018-11-13 11:48:56',1,5,0,2018,1,29,'High',1,2,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=367 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strategicdepartments`
--

LOCK TABLES `strategicdepartments` WRITE;
/*!40000 ALTER TABLE `strategicdepartments` DISABLE KEYS */;
INSERT INTO `strategicdepartments` VALUES (269,'2',342),(270,'2',343),(271,'2',344),(271,'1',345),(272,'2',346),(273,'2',347),(275,'2',348),(274,'2',349),(276,'2',350),(277,'0',351),(278,'2',352),(279,'0',353),(280,'2',354),(281,'2',355),(282,'2',356),(283,'1',357),(283,'2',358),(283,'3',359),(284,'0',360),(285,'2',361),(286,'1',362),(286,'2',363),(287,'1',364),(287,'2',365),(288,'2',366);
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
) ENGINE=InnoDB AUTO_INCREMENT=763 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strategicrisk`
--

LOCK TABLES `strategicrisk` WRITE;
/*!40000 ALTER TABLE `strategicrisk` DISABLE KEYS */;
INSERT INTO `strategicrisk` VALUES (667,269,1,'Low','a','2','1'),(668,269,2,'High','b','3','2'),(669,269,3,'Low','c','2','1'),(670,269,4,'High','d','3','2'),(671,269,5,'Low','e','2','1'),(672,269,6,'High','f','3','2'),(673,270,1,'Medium','High complexity due to market conditions','1','3'),(674,270,2,'High','','3','2'),(675,270,3,'Medium','','3','1'),(676,270,4,'Medium','','2','2'),(677,270,5,'High','','3','3'),(678,270,6,'Low','','1','1'),(679,271,1,'Low','a','2','1'),(680,271,2,'Low','b','1','2'),(681,271,3,'High','c','3','2'),(682,271,4,'Medium','d','1','3'),(683,271,5,'Medium','e','2','2'),(684,271,6,'High','f','3','3'),(685,272,1,'Low','abc','2','1'),(686,272,2,'Medium','','1','3'),(687,272,3,'Medium','','2','2'),(688,272,4,'Low','','1','1'),(689,272,5,'High','','3','3'),(690,272,6,'High','','3','2'),(691,273,1,'Low','abc','2','1'),(692,273,2,'High','','3','2'),(693,273,3,'N/A','','3','0'),(694,273,4,'High','','3','2'),(695,273,5,'Low','','1','2'),(696,273,6,'High','','3','2'),(697,275,1,'Low','123','2','1'),(698,275,2,'High','','3','2'),(699,275,3,'Low','','1','2'),(700,275,4,'High','','3','2'),(701,275,5,'Medium','','1','3'),(702,275,6,'Medium','','3','1'),(703,276,1,'Low','456','1','2'),(704,276,2,'High','','3','2'),(705,276,3,'Low','','2','1'),(706,276,4,'High','','2','3'),(707,276,5,'Low','','2','1'),(708,276,6,'High','','2','3'),(709,277,1,'N/A','','0','0'),(710,277,2,'N/A','','0','0'),(711,277,3,'N/A','','0','0'),(712,277,4,'N/A','','3','0'),(713,277,5,'N/A','','0','3'),(714,277,6,'N/A','','2','0'),(715,278,1,'N/A','','2','0'),(716,278,2,'N/A','','0','0'),(717,278,3,'N/A','','0','2'),(718,278,4,'N/A','','0','0'),(719,278,5,'N/A','','1','0'),(720,278,6,'N/A','','0','0'),(721,280,1,'N/A','','0','0'),(722,280,2,'N/A','','0','0'),(723,280,3,'N/A','','0','0'),(724,280,4,'N/A','','0','0'),(725,280,5,'N/A','','0','0'),(726,280,6,'N/A','','0','0'),(727,281,1,'N/A','','0','0'),(728,281,2,'N/A','','0','1'),(729,281,3,'N/A','','0','0'),(730,281,4,'N/A','','2','0'),(731,281,5,'N/A','','0','0'),(732,281,6,'N/A','','2','0'),(733,282,1,'N/A','','0','0'),(734,282,2,'N/A','','0','0'),(735,282,3,'N/A','','0','0'),(736,282,4,'N/A','','0','0'),(737,282,5,'N/A','','0','0'),(738,282,6,'N/A','','0','0'),(739,283,1,'N/A','','0','0'),(740,283,2,'N/A','','0','0'),(741,283,3,'N/A','','0','0'),(742,283,4,'N/A','','0','0'),(743,283,5,'N/A','','0','0'),(744,283,6,'N/A','','1','0'),(745,285,1,'Low','','2','1'),(746,285,2,'High','','2','3'),(747,285,3,'Medium','','3','1'),(748,285,4,'High','','3','3'),(749,285,5,'Low','','2','1'),(750,285,6,'Medium','','1','3'),(751,287,1,'N/A','','0','0'),(752,287,2,'N/A','','0','0'),(753,287,3,'N/A','','0','0'),(754,287,4,'N/A','','0','0'),(755,287,5,'N/A','','0','0'),(756,287,6,'N/A','','1','0'),(757,288,1,'Low','abc','2','1'),(758,288,2,'High','','3','2'),(759,288,3,'Medium','','3','1'),(760,288,4,'Medium','','2','2'),(761,288,5,'N/A','','0','2'),(762,288,6,'N/A','','0','0');
/*!40000 ALTER TABLE `strategicrisk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subprocess`
--

DROP TABLE IF EXISTS `subprocess`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subprocess` (
  `subprocessId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `subprocessName` varchar(45) NOT NULL DEFAULT '',
  `processId` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`subprocessId`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subprocess`
--

LOCK TABLES `subprocess` WRITE;
/*!40000 ALTER TABLE `subprocess` DISABLE KEYS */;
INSERT INTO `subprocess` VALUES (1,'Requisition',1),(2,'Contract',1),(3,'Order',1),(4,'Receipt',1),(5,'Disbursement',1),(6,'Prepaid Expenditure',1),(7,'Sales Agreement',2),(8,'Deliver products/services',2),(9,'Invoicing',2),(10,'Collections',2),(11,'Acquisition',3),(12,'Disposal ',3),(13,'Depreciation',3),(14,'Position placement',4),(15,'Payroll processing',4),(16,'Payroll disbursement',4),(17,'Terminations',4),(18,'Employee benefits',4),(19,'Accruals & provisions',5),(20,'Sub ledger clsoing and reconciliations',5),(21,'Financial Statements review',5),(22,'Consolidation',5),(23,'Internal & External Reporting',5),(24,'Income tax accounting',6),(25,'Income tax compliance',6),(26,'Cash Management',7),(27,'Funding',7),(28,'Hedging',7),(30,'Vendor Master Data',1),(31,'Pareoll Transactions',4),(32,'Acquisitions',8),(33,'Depriciation',8),(34,'Disposals',8),(35,'Reserves',8);
/*!40000 ALTER TABLE `subprocess` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suggestedcontrols`
--

DROP TABLE IF EXISTS `suggestedcontrols`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suggestedcontrols` (
  `suggestedControlsId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sugestedControlsName` varchar(1000) NOT NULL DEFAULT '',
  `riskId` int(10) unsigned DEFAULT NULL,
  `checked` int(1) unsigned NOT NULL DEFAULT '0',
  `suggestedReferenceNo` varchar(45) NOT NULL DEFAULT '',
  `controlRisk` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`suggestedControlsId`)
) ENGINE=InnoDB AUTO_INCREMENT=179 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suggestedcontrols`
--

LOCK TABLES `suggestedcontrols` WRITE;
/*!40000 ALTER TABLE `suggestedcontrols` DISABLE KEYS */;
INSERT INTO `suggestedcontrols` VALUES (1,'Automated : Purchase Order can not be issued until and unless vendor is selected by the \"authorized\" as per the company\'s approved policy within the procurment module',1,0,'C-P2P-C-1',''),(2,'Manual : Before the issuance of Purchase Order the authorized person ensures that the bidding process has been undertaken as per the company\'s policy. Documentary evidence of the same is attached with the purchase order for internal records',1,0,'C-P2P-C-2','2'),(3,'All contract authorisation responsibilities are segregated from disbursement, contract initiation and contract management...',1,0,'C-P2P-C-3','1'),(4,'Automated : System does not allow payment of any invoice wihtout Purchase Order/Change Order',1,0,'C-P2P-C-4','0'),(5,'Contract Management System is integrated with the PO Module such that PO can be issued once the contract is signed off by all the authorized in the system',2,0,'C-P2P-C-5','0'),(6,'Contract sign-off process is controlled by contract owner / manager via the contract signoff document (document execution checklist - docex), ',2,0,'C-P2P-C-6','0'),(7,'All contracts developed and executed in compliance with approvals framework.  The approvals framework is maintained by appropriate personnel and changes to it are appropriately authorised by management.',2,0,'C-P2P-C-7','1'),(8,'Contract sign-off process is controlled by contract owner / manager via the contract signoff document (document execution checklist - docex), ',3,0,'C-P2P-C-8','0'),(9,'Criteria exists to enable identification of embedded derivatives.  All new contracts are reviewed against company\'s developed embedded derivative criteria for identification of potential and actual embedded derivatives.  ',3,0,'C-P2P-C-9','1'),(10,'Vendor performance criteira is established at the time of contract signing for all contracts above a certain value. Contract owners manage contract in accordance with that criteria. Results of the evaluation are input into the vendor registration database......',4,0,'101112','2'),(11,'For expenses other than petty cash/specfically identified; no invoice can be processed without a valid PO.',5,0,'C-P2P-C-11',''),(12,'Planned vs. Actual hours are regularly monitored by the Contract Owner. Any variations are investigated for appropriate action.Change work orders are issued when it is expected tha the actual will exceed the planned hours.',6,0,'C-P2P-C-12',''),(13,'Purchase Orders are only issued against the unique authorized purchase requisition.',7,0,'C-P2P-C-13',''),(14,'All purchase orders are based on authorisation in accordance with approved limits (system workflow).  ',8,0,'C-P2P-C-14',''),(15,'Where an order is varied, final order value is authorised in accordance with approval limits (system workflow).  ',8,0,'C-P2P-C-15',''),(16,'Requisitioning,   ordering, goods receipting and payment functions are physically segregated. ',8,0,'C-P2P-C-16',''),(17,'System requires that all mandatory fields (please list) be completed prior processing a purchase order.  If all mandatory fields are not complete, the order cannot be placed with the supplier.',9,0,'C-P2P-C-17',''),(18,'PO is automatically generated from approved PR in the system',9,0,'C-P2P-C-18',''),(19,'Vendor related information is direcly extracted from the vendor Module and is uneditable in the PO.',9,0,'C-P2P-C-19',''),(20,'All Pos\' are reviewed at one level above the preparer',9,0,'C-P2P-C-20',''),(21,'\"Vendor selection\" module is integrated with the Ordering module such that the final price of the selected vendor is automatically extracted into the PO.',9,0,'C-P2P-C-21',''),(22,'A standard chart of accounts exists and all expenditures are classified consistently in accordance with this chart of accounts.  Knowledgeable and trained people perform coding.',10,0,'C-P2P-C-22',''),(23,'Item catalogue is developed and embedded into the system.GL Account codes are already mapped against the goods/services in the item catalogue',10,0,'C-P2P-C-23',''),(24,'\"Vendor selection\" module is integrated with the Ordering module such that the selected vendor is automatically extracted into the PO.',11,0,'C-P2P-C-24',''),(25,'All Pos\' are reviewed at one level above the preparer',11,0,'C-P2P-C-25',''),(26,'Purchasing and order placement function is restricted to trained users / purchasing officers.',11,0,'C-P2P-C-26',''),(42,'GR/IR account is regularly reviewed and cleared.',12,0,'C-P2P-C-27',''),(43,'Outstanding order report run on a monthly basis to detect long outstanding purchase orders which have not been matched to invoices and issues resolved before monthly closing.',12,0,'C-P2P-C-28',''),(44,'Centralised goods receipting function to record and review goods received (I.e. warehouse), where practical.',12,0,'C-P2P-C-29',''),(45,'For items where centralized warehousing is not practical, the person to enter the \"Receipt\" is identified at the time of issuance of PO.Reminders are automatically sent through system at the due date. ',12,0,'C-P2P-C-30',''),(46,'Centralised invoice receipt function to record and distribute invoices received enabling  invoices to be recorded and tracked by the system.',13,0,'C-P2P-C-31',''),(47,'The system is an integrated procurement system which requires the matching of purchase orders to goods receipt or service entry sheet.',14,0,'C-P2P-C-32',''),(48,' Centralised goods receipting function to record and review goods received (I.e. warehouse).',15,0,'C-P2P-C-33',''),(49,' Systems access profiles documented and matched to segregation of duties',16,0,'C-P2P-C-34',''),(50,'The system is an integrated procurement system which requires the matching of purchase orders to goods receipt or service entry sheet.',17,0,'C-P2P-C-35',''),(51,'The system is an integrated procurement system which requires the matching of purchase orders to goods receipt or service entry sheet.',18,0,'C-P2P-C-36',''),(52,'Accounts payable function is segregated from purchasing and receiving and general ledger recording activities.  These functions are segregated to prevent inappropriate disbursement activity.',47,0,'C-P2P-C-37',''),(53,'Systems access profiles for accounts payable personnel are documented and reviewed against segregation of duties requirements.',47,0,'C-P2P-C-38',''),(54,' System matches purchase order, receipt, and invoice (3way match) and automatically generates an accounting entry based on match.  Payment is not made until 3-way match occurs.  ',47,0,'C-P2P-C-39',''),(55,'Comprehensive Authority Matrix is prepared and circulated to all the concerned',48,0,'C-P2P-C-40',''),(56,'Payment authorization limits are defined in the system so that they authomatically go into the work flow of the concerend',48,0,'C-P2P-C-41',''),(57,'In a manual set up Accounts Payable Manager specifically reviews and validate that the invoice is approved by the authorized for payment processing',48,0,'C-P2P-C-42',''),(58,'System control to prevent changing bank accounts during EFT run.',49,0,'C-P2P-C-43',''),(59,'Cheque runs are reviewed by management prior to processing. Unusual or missing payments are investigated and resolved on a timely basis.',49,0,'C-P2P-C-44',''),(60,'Manual comparison of payee\'s name/address to the approved vendor master file. Differences are investigated and resolved on a timely basis.',49,0,'C-P2P-C-45',''),(61,' System matches purchase order, receipt, and invoice (3way match) and automatically generates an accounting entry based on match.  Payment is not made until 3-way match occurs.  ',50,0,'C-P2P-C-46',''),(62,' System matches purchase order, receipt, and invoice (3way match) and automatically generates an accounting entry based on match.  Payment is not made until 3-way match occurs.  ',51,0,'C-P2P-C-47',''),(63,'Restricted access to the vendor masterfile.  Additional restrictions around access to change vendor bank details.',52,0,'C-P2P-C-48',''),(64,'Prequalification questionnaire must be reviewed and approved by appropriate supply management.  Selection of vendors for major contracts is based  on results of completion of a Weighted Decision Analysis and a Vendor Risk Analysis .Vendor is registered into the system only after vendor registration form is approved by the Procurement Head',52,0,'C-P2P-C-49',''),(65,'Restricted access to the vendor masterfile.  Additional restrictions around access to change vendor bank details.',53,0,'C-P2P-C-50',''),(66,'Independent confirmation from vendor of vendor details.',53,0,'C-P2P-C-51',''),(67,'Masterfile change report is  verified against original information.',53,0,'C-P2P-C-52',''),(68,'Identify inactive suppliers and periodically purge  from the system where not used.',53,0,'C-P2P-C-53',''),(69,'Vendor is registered into the system only after vendor registration form is approved by the Procurement Head',54,0,'C-P2P-C-54',''),(70,'Appropriate segregation is maintained between position requirement generator,Hiring funtion & Payroll processing fucntion such that activity in each function is not trigerred unless previous one is appropriately approved and authorized as per the company policy',55,0,'C-H2T-PP-1',''),(71,'HR prepare a pre-hire activity form to update system, organisation structure and costing allocations.',55,0,'C-H2T-PP-2',''),(72,'Authority Matirx is in place detailing the authorities for various hiring positions',55,0,'C-H2T-PP-3',''),(73,'An online portal is created where employees themselves enter the their personal details (Bank Account etc) integrated with the payroll application.Offer letter is also created through the system by compensation team such that when the offer is accepted it is automatically updated in payroll application.',56,0,'C-H2T-PP-4',''),(74,' An independent person (I.e. a person different to the employee master data processor) checks inputs of employee master data against  source documentation.  Evidence of this check is maintained.',56,0,'C-H2T-PP-5',''),(75,'System access to change high risk employee data (eg. Bank details) is appropriately restricted.',57,0,'C-H2T-PP-6',''),(76,'An independent person (I.e. a person different to the employee master data processor) checks inputs of employee master data against  source documentation.  Evidence of this check is maintained.',57,0,'C-H2T-PP-7',''),(77,' Payroll supervisor and / or HR review a master file data change report prior to weekly, fortnightly, monthly pay runs, for reasonableness.  ',57,0,'C-H2T-PP-8',''),(78,' Restricted access to initiate and authorise payroll payment via banking systems.  Two authorities required for each payment.',58,0,'C-H2T-PD-1',''),(79,'Payroll banking information is securely stored from unauthorised users.',58,0,'C-H2T-PD-2',''),(80,'Payroll clearing account is reconciled on a monthly basis to detect differences between payroll journals and payments made by the bank.',58,0,'C-H2T-PD-3',''),(81,'System configured to report all duplicate entries. ',59,0,'C-H2T-PD-4',''),(82,'All duplicate entries are reviewed and resolved by the payroll supervisor.',59,0,'C-H2T-PD-5',''),(83,'Exception reports (eg. Hours worked, net pay variances exceeding reasonable tolerance, duplicate bank accounts, etc) generated by system and all items followed up by payroll supervisor prior to pay run.',59,0,'C-H2T-PD-6',''),(84,'Payroll system is configured to automatically generate payroll based on inputs for hourly employees and data contained in master file',60,0,'C-H2T-PD-7',''),(85,'Exception reports (eg. Hours worked, net pay variances exceeding reasonable tolerance, duplicate bank accounts, etc) generated by system and all items followed up by payroll supervisor prior to pay run.',60,0,' C-H2T-PD-8',''),(86,'Payroll supervisor performs intra-period variance analysis ',60,0,'C-H2T-PD-9',''),(87,' Completed payroll reports are reviewed and approved prior to disbursement',60,0,'C-H2T-PD-10',''),(88,'Monthly cost reports reviewed for significant variances to budget and variances investigated where appropriate',60,0,'C-H2T-PD-11',''),(89,'Exception reports (eg. Hours worked, net pay variances exceeding reasonable tolerance, duplicate bank accounts, etc) generated by system and all items followed up by payroll supervisor prior to pay run.',61,0,'C-H2T-PD-12',''),(90,' Completed payroll reports are reviewed and approved prior to disbursement',61,0,'C-H2T-PD-13',''),(91,'System automatically calculates deductions based on information contained within the Payroll Masterfile.',62,0,'C-H2T-PD-14',''),(92,'From a sample of 25 employee payroll deduction requests, trace to employee authorisation.',62,0,'C-H2T-PD-15',''),(93,'Salary and wage details submitted to employees (payslips) detail all withholdings / deductions made.  Employees contact payroll where incorrect withholdings / deductions are detected.',62,0,'C-H2T-PD-16',''),(94,'Exception reports (eg. Hours worked, net pay variances exceeding reasonable tolerance, duplicate bank accounts, etc) generated by system and all items followed up by payroll supervisor prior to pay run.',63,0,'C-H2T-PD-17',''),(95,'Completed payroll reports are reviewed and approved prior to disbursement',63,0,'C-H2T-PD-18',''),(96,'A payroll disaster recovery plan is in place.',63,0,'C-H2T-PD-19',''),(97,'Use of monthly check list or calendar to  document due dates for payroll disbursement submissions and payments.',63,0,'C-H2T-PD-20',''),(98,'Restricted access to initiate and authorise payroll payment via banking systems.  Two authorities required for each payment.',64,0,'C-H2T-PD-21',''),(99,'Payroll banking information is securely stored from unauthorised users.',64,0,'C-H2T-PD-22',''),(100,' Payroll clearing account is reconciled on a monthly basis to detect differences between payroll journals and payments made by the bank.',64,0,'C-H2T-PD-23',''),(101,'Payroll system is configured to automatically generate payroll based on inputs for hourly employees and data contained in master file',65,0,'C-H2T-PD-24',''),(102,'Payroll supervisor performs intra-period variance analysis ',65,0,'C-H2T-PD-25',''),(103,'Payroll Masterfile reflects current taxation rates for relevant taxes payable.  Payroll Management reviews Masterfile tax rates regularly for accuracy against current legislation.',65,0,'C-H2T-PD-26',''),(104,'Employment taxes reconciled to payroll reports and to the general ledger on a monthly basis',65,0,'C-H2T-PD-27',''),(105,'Monthly cost reports reviewed for significant variances to budget and variances investigated where appropriate',65,0,'C-H2T-PD-28',''),(106,'Payroll supervisor performs intra-period variance analysis ',66,0,'C-H2T-PD-29',''),(107,' Employment taxes reconciled to payroll reports and to the general ledger on a monthly basis',66,0,'C-H2T-PD-30',''),(108,'Use of monthly check list or calendar to  document due dates for payroll disbursement submissions and payments.',66,0,'C-H2T-PD-31',''),(109,' Employment tax submissions are required to be lodged on a prescribed form to prevent inconsistent and / untimely submissions.',67,0,'C-H2T-PD-32',''),(110,'Use of monthly check list or calendar to  document due dates for payroll disbursement submissions and payments.',67,0,'C-H2T-PD-33',''),(111,'BHP B workers compensation experts determine claim amounts in accordance with relevant legislation.',68,0,'C-H2T-PD-34',''),(112,'Existing system payroll information drawn from to determine compensation amount. No manual input necessary to weekly / fortnightly / monthly payment amounts. ',68,0,'C-H2T-PD-35',''),(113,'Timesheets are pre-named based on system roster information.',69,0,'C-H2T-PT-1',''),(114,'Shift supervisors authorise all hourly payroll reports prior to submitting for processing',69,0,'C-H2T-PT-2',''),(115,'System configured to report all duplicate entries. All exceptions followed up by payroll supervisor',69,0,'C-H2T-PT-3',''),(116,'Shift supervisors authorise all hourly payroll reports prior to submitting for processing',70,0,'C-H2T-PT-4',''),(117,'Absence reports generated by the system are reviewed and actioned.',70,0,'C-H2T-PT-5',''),(118,'Observe and assess system reporting capabilities over duplicate transaction detection and reporting.',70,0,'C-H2T-PT-6',''),(119,'Payroll complaints are logged, verified and followed up on in a timely fashion.',70,0,'C-H2T-PT-7',''),(120,'Requests for employee master file changes are recorded on a change request form.  These forms are reviewed for completeness and appropriate authorisation prior to input by the change processor.  ',71,0,'C-H2T-PT-8',''),(121,'Master file changes recorded which are processed into payroll systems via automated uploads (eg. from Rewards on Line)  are validated and reviewed (by appropriate payroll personnel) for completeness and accuracy.  ',71,0,'C-H2T-PT-9',''),(122,'An independent person (I.e. a person different to the employee master data processor) checks inputs of employee master data against source documentation.  Evidence of this check is maintained.',71,0,'C-H2T-PT-10',''),(123,'Payroll supervisor and / or HR review a master file data change report prior to weekly, fortnightly, monthly pay runs, for reasonableness.  ',71,0,'C-H2T-PT-11',''),(124,'Shift supervisors authorise all hourly payroll reports prior to submitting for processing',72,0,'C-H2T-PT-12',''),(125,'Absence reports generated by the system are reviewed and actioned.',72,0,'C-H2T-PT-13',''),(126,'Review payroll complaint log for timeliness and adequacy of payroll complaint resolution.',72,0,'C-H2T-PT-14',''),(127,'Review payroll complaint log for timeliness and adequacy of payroll complaint resolution.',73,0,'C-H2T-PT-15',''),(128,'Master file changes recorded which are processed into payroll systems via automated uploads (eg. from Rewards on Line)  are validated and reviewed (by appropriate payroll personnel) for completeness and accuracy.  ',73,0,'C-H2T-PT-16',''),(129,'An independent person (I.e. a person different to the employee master data processor) checks inputs of employee master data against source documentation.  Evidence of this check is maintained.',73,0,'C-H2T-PT-17',''),(130,'Payroll supervisor and / or HR review a master file data change report prior to weekly, fortnightly, monthly pay runs, for reasonableness.  ',73,0,'C-H2T-PT-18',''),(131,'Acquisition reports are reviewed, with comparisons to budgets or other data for reasonableness of acquisitions by category of asset, location or segment of business. Variances from expectations and unusual items are investigated and resolved on a timely basis.\r\n',74,0,'C-FA-A-1',''),(132,'Mandatory fields must be completed in fixed asset addition requests, including: asset description, asset identifier, location, cost, addition date, tax data, depreciation information. System will not allow upload until mandatory fields have been completed, thus preventing the incomplete processing of asset details in the register.\r\n',75,0,'C-FA-A-2',''),(133,'System has pre-set classifications to enable automated  fixed asset disclosures at reporting date, preventing classification errors from manual determination.',76,0,'C-FA-A-3',''),(134,'All capital expenditures requisitions (including subsequent changes) are reviewed and approved by an appropriate level of management. Unusual items are investigated and resolved prior to approval.',76,0,'C-FA-A-4',''),(135,'Management reviews purchase & sale agreements, with other data to ensure acquisitions are accurately recorded (e.g. investment property, PP&E, investment in venture).',76,0,'C-FA-A-5',''),(136,'Accounting policy manual is followed for capitalisation of assets',77,0,'C-FA-A-6',''),(137,'Review Operating costs by department/cost centre/authorisation/project for expenditure against budgets, prior periods or expectations.  Irregularities are reviewed and resolved in a timely manner.',78,0,'C-FA-A-7',''),(138,'Management reviews and approves the classification of work orders as capital versus expense in nature based on the type of project and company policy. Discrepancies are followed up and resolved.',78,0,'C-FA-A-8',''),(139,'Review of purchase orders and work orders on a regular basis to determine appropriate capital / operating classification.',78,0,'C-FA-A-9',''),(140,'Fixed assets are capitalized as per the requirements of capitalization policy',78,0,'C-FA-A-10',''),(141,'Selection of classification code for each asset must be approved.  The approval process involves reviewing the coding for accuracy.',79,0,'C-FA-A-11',''),(142,'Review capital expenditures outside approved amounts. All exceptions followed up in a timely manner.\r\n',79,0,'C-FA-A-12',''),(143,'The asset register and the general ledger is reconciled regularly (at least quarterly).  Unreconciled differences are followed-up on a timely basis.',80,0,'C-FA-A-13',''),(144,'System access to create final asset in the fixed asset register  is restricted.  ',80,0,'C-FA-A-14',''),(145,'Management reviews periodic reports, with comparisons to budgets or other data for reasonableness of depreciation charges by category of asset, location or segment of business. Variances from expectation outside a predetermined threshold and unusual items are investigated and resolved on a timely basis.',93,0,'C-FA-D-1',''),(146,'Annual review of reports detailing assets not depreciated.  Appropriate accounting consideration given to all assets appearing on the assets not depreciated report. ',93,0,'C-FA-D-2',''),(147,'Edit report run to ensure that all assets classified as held for sale and/or taken out of service do not have depreciation taken against them',81,0,'C-FA-D-3',''),(148,'Periodic review of capital asset register with operational staff to identify assets held for sale and/or taken out of service.  This is done to prevent incorrect charging to depreciation expense.',82,0,'C-FA-D-4',''),(149,'Accounting policy on depreciation and depreciable assets is understood and  applied by staff responsible for calculating depreciation. ',83,0,'C-FA-D-5',''),(150,'System will not allow retrospective depreciation charges once financial year is rolled- over.',84,0,'C-FA-D-6',''),(151,'Access to modify depreciation rates is restricted by user profiles',94,0,'C-FA-D-7',''),(152,'Information used for the calculation of depreciation for new acquisitions is validated against \"acquired assets information check list\" (asset class, date asset put into use, expected useful life or UOP asset, depreciation rate, estimated residual value etc.) for accuracy.',85,0,'C-FA-D-8',''),(153,'Depreciation calculations are performed by the system and are only run after all periodic amendments, adjustments and additions to fixed asset register has been completed',86,0,'C-FA-D-9',''),(154,'Monthly depreciation run is included in schedule of month end procedures resulting in timely and consistent booking of asset values to the general ledger at month-end.',87,0,'C-FA-D-10',''),(155,'Annual review of asset recoverable amount.  Recoverable amount adjustments are posted on a timely basis.',88,0,'C-FA-D-11',''),(156,'Information used for the calculation of depreciation for new acquisitions is validated against \"acquired assets information check list\" (asset class, date asset put into use, expected useful life or UOP asset, depreciation rate, estimated residual value etc.) for accuracy.',89,0,'C-FA-D-12',''),(157,'Accounting policy on depreciation and depreciable assets is understood and  applied by staff responsible for calculating depreciation. ',90,0,'C-FA-D-13',''),(158,'Depreciation charges and accumulated depreciation by asset class are regularly reconciled to the general ledger',91,0,'C-FA-D-14',''),(159,'Year-end asset roll-over is included in schedule of year-end procedures resulting in a timely and consistent roll-forward of asset values in the asset register.',92,0,'C-FA-D-15',''),(160,'Mandatory fields must be completed in fixed asset disposal requests, including asset description, asset identifier, location, cost, date of disposal, tax data, depreciation information.  System will not allow disposal to be processed unless mandatory fields are complete thus reducing the risk of incomplete or inaccurate disposals in the system.',95,0,'C-FA-DR-1',''),(161,'All disposals require review and approval from appropriate level of management prior to processing.\r\nAn authorised asset disposal form is required to authorise disposal and remove asset from site.',95,0,'C-FA-DR-2',''),(162,'Accounting policy manual is followed for disposal of assets.',95,0,'C-FA-DR-3',''),(163,'Reconcile fixed asset register with general ledger after receiving disposal proceeds',96,0,'C-FA-DR-4',''),(164,'Management reviews and approves the calculation and classification of gains and losses on significant disposals/retirements based on disposal/retirement information (e.g. nature of the disposal/retirement, proceeds of disposition etc.) to ensure posted against accumulated depreciation or to the P/L as applicable. Discrepancies are investigated and resolved.',101,0,'C-FA-DR-5',''),(165,'An authorised asset disposal form is required to authorise disposal and remove asset from site.',97,0,'C-FA-DR-6',''),(166,'Initiation of the disposal transaction is segregated from authorisation, recording, receipt of proceeds and physical disposal.',97,0,'C-FA-DR-7',''),(167,'An authorised asset disposal form is required to authorise disposal and remove asset from site.',98,0,'C-FA-DR-8',''),(168,'A comparison is performed between disposals processed to disposal source documents (e.g. approved disposal form, cash proceeds, removal costs, etc). Differences are investigated and resolved on a timely basis.',99,0,'C-FA-DR-9',''),(169,'All disposals require review and approval from appropriate level of management prior to processing.',100,0,'C-FA-DR-10',''),(170,'Accounting policy on disposal of assets and applied by staff responsible for calculating depreciation. ',100,0,'C-FA-DR-11',''),(171,'Reconciliation of the profit / loss on disposals account and proceeds on disposal accounts to the asset register.  Discrepancies are investigated on a timely basis.',100,0,'C-FA-DR-12',''),(172,'Changes/modifications to reserve tracking data are reviewed',102,0,'C-FA-R-1',''),(173,'Comparison of third party reserve engineer determined reserves to internally determined reserves',102,0,'C-FA-R-2',''),(174,'Economic factors used in estimating values are compared with applicable disclosure requirements.\r\nEconomic factors used in estimating values are compared with applicable disclosure requirements.\r\nEconomic factors used in estimating values are compared with applicable disclosure requirements.\r\n',103,0,'C-FA-R-3',''),(175,'Management review of year over year analysis of changes to estimates or classifications in reservoirs.\r\n',104,0,'C-FA-R-4',''),(176,'Manual review and approval of model/reserve tracking outputs reconciliation.',104,0,'C-FA-R-5',''),(177,'Modified terms of lease or contractual obligations considered in preparing or updating reserve models.\r\nModified terms of lease or contractual obligations considered in preparing or updating reserve models.\r\nModified terms of lease or contractual obligations considered in preparing or updating reserve models.\r\n',104,0,'C-FA-R-6',''),(178,'Review compliance of review estimation procedures with applicable regulations.\r\nReview compliance of review estimation procedures with applicable regulations.\r\nReview compliance of review estimation procedures with applicable regulations.\r\n',104,0,'C-FA-R-7','');
/*!40000 ALTER TABLE `suggestedcontrols` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `todo`
--

DROP TABLE IF EXISTS `todo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `todo` (
  `toDoId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(300) NOT NULL DEFAULT '',
  `assignedTo` int(10) unsigned NOT NULL DEFAULT '0',
  `dueDate` date NOT NULL DEFAULT '0000-00-00',
  `assignedFrom` int(10) unsigned NOT NULL DEFAULT '0',
  `companyId` int(10) unsigned NOT NULL DEFAULT '0',
  `respond` varchar(300) DEFAULT NULL,
  `job` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`toDoId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `todo`
--

LOCK TABLES `todo` WRITE;
/*!40000 ALTER TABLE `todo` DISABLE KEYS */;
INSERT INTO `todo` VALUES (7,'desciption to TodOs ',117,'2018-10-24',116,29,NULL,74),(8,'second todo',115,'2018-10-19',116,29,NULL,74),(9,'hi',116,'2018-10-19',116,29,'hello1',74),(10,'hi',116,'2018-10-19',116,29,'huiyyyy',74),(11,'qq',116,'2018-10-19',116,29,'hello qq ',74),(12,'abczohaib',117,'2018-10-19',117,29,NULL,74),(13,'desc',117,'2018-10-17',116,29,NULL,78),(14,'desc',117,'2018-10-17',116,29,NULL,78),(15,'jpobnme chck',116,'2018-11-23',116,29,'hello jobname check',74);
/*!40000 ALTER TABLE `todo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `todologs`
--

DROP TABLE IF EXISTS `todologs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `todologs` (
  `todologsId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL DEFAULT '',
  `assignedBy` varchar(45) NOT NULL DEFAULT '',
  `assignedTo` varchar(45) NOT NULL DEFAULT '',
  `date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`todologsId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `todologs`
--

LOCK TABLES `todologs` WRITE;
/*!40000 ALTER TABLE `todologs` DISABLE KEYS */;
INSERT INTO `todologs` VALUES (1,'hi','116','116','2018-10-19 00:00:00'),(2,'qq','116','116','2018-10-19 00:00:00'),(3,'jpobnme chck','116','116','2018-11-23 00:00:00');
/*!40000 ALTER TABLE `todologs` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (0,'no user','123','123',0),(1,'faheem','faheem','test@email.com',1),(2,'test','test','hy1@email.com',2),(3,'hyadmin','hyadmin','hyad',3),(5,'hy2','hy2','hy2@emai.com',5),(8,'hy3','hy3','hy3',8),(9,'mgm','mgm','mgm',9),(114,'korgentadmin@email.com','korgentadmin','korgentadmin@email.com',115),(115,'korgenthead@gmail.com','korgenthead','korgenthead@email.com',116),(116,'korgentlead@email.com','korgentlead','korgentlead@email.com',117),(117,'korgentauditor@email.com','korgentauditor','korgentauditor@email.com',118),(118,'korgentmgm@email.com','korgentmgm','korgentmgm@email.com',119);
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

-- Dump completed on 2018-11-21  7:56:32
