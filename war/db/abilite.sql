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
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activityobjective`
--

LOCK TABLES `activityobjective` WRITE;
/*!40000 ALTER TABLE `activityobjective` DISABLE KEYS */;
INSERT INTO `activityobjective` VALUES (1,'All contracts, memorandums or understanding and letters of intent that financially obligate the company are approved & authorized as per the Authority Matrix.',2,'O-P2P-C-1',0),(2,'All contracts are managed in accordance with the contract.',2,'O-P2P-C-2',0),(3,'All orders are authorised  -  Orders are placed only when they satisfy management authorisation requirements',3,'O-P2P-C-3',0),(4,'All orders are accurately reported - Details of each order are accurately captured and allocated',3,'O-P2P-C-4',0),(5,'All obligations to creditors for goods and services received are recognised on a timely basis',4,'O-P2P-C-5',0),(6,'Obligations recognised are for goods and services received in accordance with required specifications',4,'O-P2P-C-6',0),(7,'Obligations for goods and services received are in accordance with management authorisations',4,'O-P2P-C-7',0),(8,'Payments are made only for goods/services authorized to be procured at a specified price.',5,'O-P2P-C-8',0),(9,'Payments are made only after being approved & authorized as per the company policy',5,'O-P2P-C-9',0),(10,'Payments are accurate and complete',5,'O-P2P-C-10',0),(11,'The vendor master file is a record of all authorised suppliers.',30,'O-P2P-C-11',0),(12,'All master file data elements are complete and accurate.',30,'O-P2P-C-12',0),(13,'All positions are authorised',14,'O-H2T-PP-1',0),(14,'All position placement data is accurately reflected in system (details of each employee is accurately captured and allocated)',14,'O-H2T-PP-2',0),(15,'Only authorised payroll payments are made',16,'O-H2T-PD-1',0),(16,'Payments are accurate, timely and complete.',16,'O-H2T-PD-2',0),(17,'Employment taxes are calculated in accordance with applicable legislation',16,'O-H2T-PD-3',0),(18,'Employee taxes and benefit payments are accurate and complete',16,'O-H2T-PD-4',0),(19,'Services performed are authorised and accurately reflected in the system.',31,'O-H2T-PT-1',0),(20,'Pay rates are authorised and accurately reflected in the system.',31,'O-H2T-PT-2',0),(21,'All fixed asset additions are properly authorised - Commitments to acquire fixed assets are only entered into based on appropriate management authorisation',32,'O-FA-A-1',0),(22,'Expenditure is correctly classified.',32,'O-FA-A-2',0),(23,'Capitalised expenditure is correctly classified - Valid capital expenditure is coded to the correct asset category in the fixed asset register',32,'O-FA-A-3',0),(24,'The fixed asset register is complete and accurate',32,'O-FA-A-4',0),(25,'All assets in use are depreciated',33,'O-FA-D-1',0),(26,'All assets withdrawn from use have their depreciation appropriately adjusted',33,'O-FA-D-2',0),(27,'Assets are depreciated over the best estimate of their useful lives',33,'O-FA-D-3',0),(28,'Depreciation is calculated and recorded on a timely basis',33,'O-FA-D-4',0),(29,'Depreciable amount and depreciation charge for each asset is accurately determined ',33,'O-FA-D-5',0),(30,'Depreciation charge components are properly identified and disclosed',33,'O-FA-D-6',0),(31,'Year-end asset processes are accurately performed on a timely basis.',33,'O-FA-D-7',0),(32,'All disposals are accounted for correctly and timely',34,'O-FA-DR-1',0),(33,'Disposals are properly authorised.',34,'O-FA-DR-2',0),(34,'Assets are disposed of economically.',34,'O-FA-DR-3',0),(35,'All disposals are valid.',34,'O-FA-DR-4',0),(36,'All reserves are accounted for correctly and timely',35,'O-FA-R-1',0),(37,'To ensure that ER and GL reconciliation is reviewed and approved by an authorised person',36,'O-R2P-YC-1',0),(38,'To ensure that the accuracy and completeness in the financial closing process',36,'O-R2P-YC-2',0),(39,'New accounts are completely and accurately created and appropriately authorised.',37,'O-R2P-GL-1',0),(40,'General ledger accounts are appropriately maintained on an ongoing basis',37,'O-R2P-GL-2',0),(41,'Unused accounts are appropriately removed from general ledger master records',37,'O-R2P-GL-3',0),(42,'Intercompany balances are completely and accurately eliminated upon consolidation.',38,'O-R2P-CMG-1',0),(43,'Other consolidation journal entries are complete and accurate.',38,'O-R2P-CMG-2',0),(44,'Hyperion data is accurate.',38,'O-R2P-CMG-3',0),(45,'Appropriate control exists over changes to consolidation data.',38,'O-R2P-CMG-4',0),(46,'To ensure completeness and accuracy of the reporting packages and source documents.',39,'O-R2P-BUP-1',0),(47,'To ensure that all the variances and the difference arises between the reconciliations and reports are addressed on timely basis and resolved',39,'O-R2P-BUP-2',0),(48,'Ensure that system is performing accurately and efficiently',39,'O-R2P-BUP-3',0),(49,'Consolidations system users have appropriate access rights.',22,'O-R2P-C-1',0),(50,'Changes to consolidations system account and logic are correct and accurately reflect reporting requirements.',22,'O-R2P-C-2',0),(51,'Changes to consolidation system entities are approved and accurate.',22,'O-R2P-C-3',0),(52,'To ensure that Financial statements are reviewed by management and any differences and variances about related party transactions has been addressed and communicated to appropriate authority for rectification.',42,'O-R2P-FSP-1',0),(53,'Ensure that management completely review financial statements, disclosure checklist and authorizes the financial statements before issuance.',42,'O-R2P-FSP-2',0),(54,'Ensure the authencity and accuracy of the information obtained from reporting units for inclusion in the financial statements and disclosures',42,'O-R2P-FSP-3',0),(55,'Ensure approval and authorisation and review of all topside entries prior to processing',42,'O-R2P-FSP-4',0),(56,'To ensure that bad debt provision is complete, accurate and is approved by appropriate authorities.',43,'O-O2C-APD-1',0),(57,'To ensure that provisions are complete, accurate and are approved by appropriate authorities.',43,'O-O2C-APD-2',0),(58,'To ensure that provisions are complete, accurate and are approved by appropriate authorities.',43,'O-O2C-APD-3',0),(59,'Ensure that there is a proper segregation of duties in cash handling process and the cash receipts are regularly tallied with the customer accounts/invoices.',44,'O-O2C-CR-1',0),(60,'All the cash receipts are timely deposited into company\'s account.',44,'O-O2C-CR-2',0),(61,'Ensure that discount and allowances are accurately calculated as per company policy and are reviewed and authorized by the relevant person',48,'O-O2C-CRD-1',0),(62,'Ensure that discount and allowances are accurately calculated and recorded.',48,'O-O2C-CRD-2',0),(63,'Ensure that rebates and allowances are as per company policy or as agreed in contract with customer.',48,'O-O2C-CRD-3',0),(64,'To minimize the sale return incurring due to excessive level of supply or due to the items approaching expiry',48,'O-O2C-CRD-4',0),(65,'To increase the sales revenue by achieving increase in sale volume',48,'O-O2C-CRD-5',0),(66,'To ensure that system is accurately processing the data.',48,'O-O2C-CRD-6',0),(67,'All the adjustment to the records are approved by authorized individuals.',48,'O-O2C-CRD-7',0),(68,'All period end adjustments in Revenue & Receivables are accurately recorded in the relevant period and are reviewed and approved by appropriate level of authority.',45,'O-O2C-APC-1',0),(69,'All adjustments made during the interim periods are accurately recorded and reviewed and approved by competent level of authority',45,'O-O2C-APC-2',0),(70,'Ensure accuracy of reporting by third parties',45,'O-O2C-APC-3',0),(71,'Ensure that period end adjustment are made only to valid and authentic customers.',45,'O-O2C-APC-4',0),(72,'External Data used in the analysis or comparison is authentic and relevant',45,'O-O2C-APC-5',0),(73,'Unusual trend and variances in revenue and its related accounts are identified and resolved on timely basis by appropriate level of authority.',45,'O-O2C-APC-6',0),(74,'Entity is complaint with laws and regulation related to customers data.',45,'O-O2C-APC-7',0),(75,'Revenue from long term contracts is recognized according to applicable financial reporting framework.',45,'O-O2C-APC-8',0),(76,'Comprehensive policies and procedures are in place and followed by relevant staff of the company. ',46,'O-O2C-PWC-1',0),(77,'Third party services are in line with companies objectives and are of operating effectively',46,'O-O2C-PWC-2',0),(78,'Avoid unauthorized changes to system or data.\r\n\r\nEnsure timely and right decision making by management of the company.\r\n',46,'O-O2C-PWC-3',0),(79,'Ensure that relevant record and documents are accurate and up to date. ',46,'O-O2C-PWC-4',0),(80,'Ensure that revenue and accounts receivables are authentic and reliable. ',46,'O-O2C-PWC-5',0),(81,'Proper and comprehensive policies and procedures exist in order to ensure maintain and uplift relationship with customers. ',46,'O-O2C-PWC-6',0),(82,'All orders are authorised  -  Orders are placed only when they satisfy management authorisation requirements',47,'O-O2C-OP-1',0),(83,'Ensure that sale orders made by customers do not exceeds the predetermined agreed threshold.',47,'O-O2C-OP-2',0),(84,'Ensure that contract variations are duly agreed, reviewed and approved for all non-standard contract terms',47,'O-O2C-OP-3',0),(85,'All prices set and agreed , should be reflected in the sales order and all sales orders are approved within system to ensure double check',47,'O-O2C-OP-4',0),(86,'To ensure sales order are checked with stock availability and keeping in view the production time',47,'O-O2C-OP-5',0),(87,'Ensure all sales order are sent to customers for acknowledgement and should include terms and conditions for them to acknowledge',47,'O-O2C-OP-6',0),(88,'Ensure all orders are timely processed without delay and proper monitoring is carried out for outstanding orders',47,'O-O2C-OP-7',0),(89,'Ensure segregation of duties in terms of person preparing sales orders and negotiating with customers',47,'O-O2C-OP-8',0),(90,'Ensure all orders are systemized and no sale of goods or services is carried out without preparation of sales order',47,'O-O2C-OP-9',0),(91,'Ensure that proper invoicing procedure has been followed and the Invoices has been duly stamped, sequentially numbered, duly authorized by the relevant person',9,'O-O2C-I-1',0),(92,'Ensure that the system performs invoicing function accurately and there are no system issues occurring in the process',9,'O-O2C-I-2',0),(93,'Ensure that changes to database of prices are made by the authorized person and appropriate prices are being used when invoicing customers',9,'O-O2C-I-3',0),(94,'Ensure that bill and hold arrangements are properly dealt as per IAS 2 and are excluded from inventories.',9,'O-O2C-I-4',0),(95,'hh',7,'111112',0),(96,'hh',7,'111112',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit_engagement`
--

LOCK TABLES `audit_engagement` WRITE;
/*!40000 ALTER TABLE `audit_engagement` DISABLE KEYS */;
INSERT INTO `audit_engagement` VALUES (129,74,'In Progress','','','','We hereby would like to inform you thot company Nome Internal Audit will be conducting o review of Engagement Nome from 14 December, 2017. This is port of the Internal Audit Pion for 2017, discussed with Top Monogement, Group Internal Audit, ond approved by member(s) of BoD/Audit Committee\nThe oudit will be performed by Mr DEF ond Mr GHI , members of Locol Internal Audit.\n The scope of the oudit includes review of odequocy, effectiveness & efficiency of the controls ond processes around Engagement Nome, with specific focus on xxxx. \n Please note thot IA teom moy toke contact with relevant members of your teom in the coming doys for o better process understanding relating to this oudit ond information required. Any resultant scope changes, if required, will be duly communicated. \n The completion ond finolisotion will depend on the ovoilobility of information ond interviewees, yet we oim ot delivering o droft report for your review ond comments by 8 FebruorY, 2018. \n You ore requested to forward the information in ti-is notification to those who will be involved in the oudit. If you hove ony questions concerning the oudit, please feel free to contact myself or e-moil ot xxxxx. \n We look forward to your continued support. ','junaidp@gmail.com','Hello ',2018,29,'0',116,0,'Eho','junaidp@gmail.com','Subject hee',NULL),(130,75,'In Progress','','','','','','',2018,29,'0',116,0,'','','',NULL),(131,76,'In Progress','','','','We hereby would like to inform you thot company Nome Internal Audit will be conducting o review of Engagement Nome from 14 December, 2017. This is port of the Internal Audit Pion for 2017, discussed with Top Monogement, Group Internal Audit, ond approved by member(s) of BoD/Audit Committee\nThe oudit will be performed by Mr DEF ond Mr GHI , members of Locol Internal Audit.\n The scope of the oudit includes review of odequocy, effectiveness & efficiency of the controls ond processes around Engagement Nome, with specific focus on xxxx. \n Please note thot IA teom moy toke contact with relevant members of your teom in the coming doys for o better process understanding relating to this oudit ond information required. Any resultant scope changes, if required, will be duly communicated. \n The completion ond finolisotion will depend on the ovoilobility of information ond interviewees, yet we oim ot delivering o droft report for your review ond comments by 8 FebruorY, 2018. \n You ore requested to forward the information in ti-is notification to those who will be involved in the oudit. If you hove ony questions concerning the oudit, please feel free to contact myself or e-moil ot xxxxx. \n We look forward to your continued support. ','MR XYZ,Director XXX','CXO Detail',2018,29,'0',116,0,'Korgent','Head of Internal Audit korgent','Inventory Management',NULL),(142,77,'In Progress','','','','','','',2018,29,'0',116,0,'','','',NULL),(145,78,'In Progress','','','','We hereby would like to inform you thot company Nome Internal Audit will be conducting o review of Engagement Nome from 14 December, 2017. This is port of the Internal Audit Pion for 2017, discussed with Top Monogement, Group Internal Audit, ond approved by member(s) of BoD/Audit Committee\nThe oudit will be performed by Mr DEF ond Mr GHI , members of Locol Internal Audit.\n The scope of the oudit includes review of odequocy, effectiveness & efficiency of the controls ond processes around Engagement Nome, with specific focus on xxxx. \n Please note thot IA teom moy toke contact with relevant members of your teom in the coming doys for o better process understanding relating to this oudit ond information required. Any resultant scope changes, if required, will be duly communicated. \n The completion ond finolisotion will depend on the ovoilobility of information ond interviewees, yet we oim ot delivering o droft report for your review ond comments by 8 FebruorY, 2018. \n You ore requested to forward the information in ti-is notification to those who will be involved in the oudit. If you hove ony questions concerning the oudit, please feel free to contact myself or e-moil ot xxxxx. \n We look forward to your continued support. ','MR XYZ,Director XXX','CXO Detail',2018,29,'0',116,0,'Company name Internal Audit','MR XYZ,Head of internal Audit','Engegement Name',NULL),(152,79,'In Progress','','','','We hereby would like to inform you thot company Nome Internal Audit will be conducting o review of Engagement Nome from 14 December, 2017. This is port of the Internal Audit Pion for 2017, discussed with Top Monogement, Group Internal Audit, ond approved by member(s) of BoD/Audit Committee\nThe oudit will be performed by Mr DEF ond Mr GHI , members of Locol Internal Audit.\n The scope of the oudit includes review of odequocy, effectiveness & efficiency of the controls ond processes around Engagement Nome, with specific focus on xxxx. \n Please note thot IA teom moy toke contact with relevant members of your teom in the coming doys for o better process understanding relating to this oudit ond information required. Any resultant scope changes, if required, will be duly communicated. \n The completion ond finolisotion will depend on the ovoilobility of information ond interviewees, yet we oim ot delivering o droft report for your review ond comments by 8 FebruorY, 2018. \n You ore requested to forward the information in ti-is notification to those who will be involved in the oudit. If you hove ony questions concerning the oudit, please feel free to contact myself or e-moil ot xxxxx. \n We look forward to your continued support. ','MR XYZ,Director XXX','CXO Detail',2018,29,'0',116,0,'Company name Internal Audit','MR XYZ,Head of internal Audit','Engegement Name',NULL),(153,80,'In Progress','','','','We hereby would like to inform you thot company Nome Internal Audit will be conducting o review of Engagement Nome from 14 December, 2017. This is port of the Internal Audit Pion for 2017, discussed with Top Monogement, Group Internal Audit, ond approved by member(s) of BoD/Audit Committee\nThe oudit will be performed by Mr DEF ond Mr GHI , members of Locol Internal Audit.\n The scope of the oudit includes review of odequocy, effectiveness & efficiency of the controls ond processes around Engagement Nome, with specific focus on xxxx. \n Please note thot IA teom moy toke contact with relevant members of your teom in the coming doys for o better process understanding relating to this oudit ond information required. Any resultant scope changes, if required, will be duly communicated. \n The completion ond finolisotion will depend on the ovoilobility of information ond interviewees, yet we oim ot delivering o droft report for your review ond comments by 8 FebruorY, 2018. \n You ore requested to forward the information in ti-is notification to those who will be involved in the oudit. If you hove ony questions concerning the oudit, please feel free to contact myself or e-moil ot xxxxx. \n We look forward to your continued support. ','MR XYZ,Director XXX','CXO Detail',2018,29,'0',116,0,'Company name Internal Audit','MR XYZ,Head of internal Audit','Engegement Name',NULL),(154,81,'In Progress','','','','','','',2018,29,'0',116,0,'','','',NULL),(155,82,'In Progress','','','','','','',2018,29,'0',116,0,'','','',NULL),(156,83,'In Progress','','','','We hereby would like to inform you thot company Nome Internal Audit will be conducting o review of Engagement Nome from 14 December, 2017. This is port of the Internal Audit Pion for 2017, discussed with Top Monogement, Group Internal Audit, ond approved by member(s) of BoD/Audit Committee\nThe oudit will be performed by Mr DEF ond Mr GHI , members of Locol Internal Audit.\n The scope of the oudit includes review of odequocy, effectiveness & efficiency of the controls ond processes around Engagement Nome, with specific focus on xxxx. \n Please note thot IA teom moy toke contact with relevant members of your teom in the coming doys for o better process understanding relating to this oudit ond information required. Any resultant scope changes, if required, will be duly communicated. \n The completion ond finolisotion will depend on the ovoilobility of information ond interviewees, yet we oim ot delivering o droft report for your review ond comments by 8 FebruorY, 2018. \n You ore requested to forward the information in ti-is notification to those who will be involved in the oudit. If you hove ony questions concerning the oudit, please feel free to contact myself or e-moil ot xxxxx. \n We look forward to your continued support. ','MR XYZ,Director XXX','CXO Detail',2018,29,'0',116,0,'Company name Internal Audit','MR XYZ,Head of internal Audit','Engegement Name',NULL),(157,84,'In Progress','','','','We hereby would like to inform you thot company Nome Internal Audit will be conducting o review of Engagement Nome from 14 December, 2017. This is port of the Internal Audit Pion for 2017, discussed with Top Monogement, Group Internal Audit, ond approved by member(s) of BoD/Audit Committee\nThe oudit will be performed by Mr DEF ond Mr GHI , members of Locol Internal Audit.\n The scope of the oudit includes review of odequocy, effectiveness & efficiency of the controls ond processes around Engagement Nome, with specific focus on xxxx. \n Please note thot IA teom moy toke contact with relevant members of your teom in the coming doys for o better process understanding relating to this oudit ond information required. Any resultant scope changes, if required, will be duly communicated. \n The completion ond finolisotion will depend on the ovoilobility of information ond interviewees, yet we oim ot delivering o droft report for your review ond comments by 8 FebruorY, 2018. \n You ore requested to forward the information in ti-is notification to those who will be involved in the oudit. If you hove ony questions concerning the oudit, please feel free to contact myself or e-moil ot xxxxx. \n We look forward to your continued support. ','MR XYZ,Director XXX','CXO Detail',2018,29,'0',116,0,'Company name Internal Audit','MR XYZ,Head of internal Audit','Engegement Name',NULL),(158,85,'In Progress','','','','','','',2018,29,'0',116,0,'','','',NULL),(159,86,'In Progress','','','','','','',2018,29,'0',116,0,'','','',NULL),(160,87,'In Progress','','','','We hereby would like to inform you thot company Nome Internal Audit will be conducting o review of Engagement Nome from 14 December, 2017. This is port of the Internal Audit Pion for 2017, discussed with Top Monogement, Group Internal Audit, ond approved by member(s) of BoD/Audit Committee\nThe oudit will be performed by Mr DEF ond Mr GHI , members of Locol Internal Audit.\n The scope of the oudit includes review of odequocy, effectiveness & efficiency of the controls ond processes around Engagement Nome, with specific focus on xxxx. \n Please note thot IA teom moy toke contact with relevant members of your teom in the coming doys for o better process understanding relating to this oudit ond information required. Any resultant scope changes, if required, will be duly communicated. \n The completion ond finolisotion will depend on the ovoilobility of information ond interviewees, yet we oim ot delivering o droft report for your review ond comments by 8 FebruorY, 2018. \n You ore requested to forward the information in ti-is notification to those who will be involved in the oudit. If you hove ony questions concerning the oudit, please feel free to contact myself or e-moil ot xxxxx. \n We look forward to your continued support. ','MR XYZ,Director XXX','CXO Detail',2018,29,'0',116,0,'Company name Internal Audit','MR XYZ,Head of internal Audit','Engegement Name',NULL),(161,88,'In Progress','','','','','','',2018,29,'0',116,0,'','','',NULL),(162,89,'In Progress','','','','We hereby would like to inform you thot company Nome Internal Audit will be conducting o review of Engagement Nome from 14 December, 2017. This is port of the Internal Audit Pion for 2017, discussed with Top Monogement, Group Internal Audit, ond approved by member(s) of BoD/Audit Committee\nThe oudit will be performed by Mr DEF ond Mr GHI , members of Locol Internal Audit.\n The scope of the oudit includes review of odequocy, effectiveness & efficiency of the controls ond processes around Engagement Nome, with specific focus on xxxx. \n Please note thot IA teom moy toke contact with relevant members of your teom in the coming doys for o better process understanding relating to this oudit ond information required. Any resultant scope changes, if required, will be duly communicated. \n The completion ond finolisotion will depend on the ovoilobility of information ond interviewees, yet we oim ot delivering o droft report for your review ond comments by 8 FebruorY, 2018. \n You ore requested to forward the information in ti-is notification to those who will be involved in the oudit. If you hove ony questions concerning the oudit, please feel free to contact myself or e-moil ot xxxxx. \n We look forward to your continued support. ','MR XYZ,Director XXX','CXO Detail',2018,29,'0',116,0,'Company name Internal Audit','MR XYZ,Head of internal Audit','Engegement Name',NULL),(163,90,'Not Started','','','','','','',2018,29,'0',0,0,'','','',NULL),(164,90,'In Progress','','','','','','',2018,29,'0',116,0,'','','',NULL),(165,91,'In Progress','','','','','','',2018,29,'0',116,0,'','','',NULL),(166,92,'In Progress','','','','','','',2018,29,'0',116,0,'','','',NULL),(167,93,'In Progress','','','','','','',2018,29,'0',116,0,'','','',NULL),(168,94,'In Progress','','','','','','',2018,29,'0',116,0,'','','',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit_step`
--

LOCK TABLES `audit_step` WRITE;
/*!40000 ALTER TABLE `audit_step` DISABLE KEYS */;
INSERT INTO `audit_step` VALUES (68,'','','','','Non-Satisfactory',74,88,1,2018,29,116,116,'',NULL,'2018-10-18 00:29:50'),(71,'','','','','Non-Satisfactory',76,89,1,2018,29,116,116,'',NULL,'2018-10-23 09:47:02'),(72,'','','','','Satisfactory',78,90,1,2018,29,116,116,'',NULL,'2018-10-24 10:52:02'),(73,'','','','','Satisfactory',78,90,4,2018,29,0,116,'',NULL,'2018-10-24 10:51:33'),(74,'','','','','Satisfactory',78,91,1,2018,29,116,116,'',NULL,'2018-10-24 10:52:38'),(77,'','','','','Satisfactory',79,92,1,2018,29,116,116,'',NULL,'2018-10-25 12:41:01'),(78,'','','','','Satisfactory',79,92,4,2018,29,0,116,'',NULL,'2018-10-25 12:40:59'),(82,'','','','','Satisfactory',80,93,1,2018,29,116,116,'',NULL,'2018-11-06 10:08:15'),(83,'','','','','Satisfactory',81,94,1,2018,29,116,116,'',NULL,'2018-11-06 10:10:42'),(84,'','','','','Satisfactory',82,95,1,2018,29,116,116,'',NULL,'2018-11-08 12:10:28'),(85,'','','','','Non-Satisfactory',83,96,1,2018,29,116,116,'',NULL,'2018-11-11 13:10:41'),(86,'','','','','Non-Satisfactory',84,97,1,2018,29,116,116,'',NULL,'2018-11-11 15:38:23'),(87,'','','','','Satisfactory',77,98,1,2018,29,116,116,'',NULL,'2018-11-12 12:20:35'),(88,'','','','','Satisfactory',85,99,1,2018,29,116,116,'',NULL,'2018-11-12 14:48:47'),(89,'','','','','Satisfactory',86,100,1,2018,29,116,116,'',NULL,'2018-11-13 13:24:00'),(90,'','','','','Satisfactory',87,101,1,2018,29,116,116,'',NULL,'2018-11-13 14:50:40'),(91,'','','','','Satisfactory',87,101,4,2018,29,0,116,'',NULL,'2018-11-13 14:50:32'),(92,'','','','','Satisfactory',88,102,1,2018,29,116,116,'',NULL,'2018-11-13 16:29:17'),(93,'','','','','Satisfactory',89,103,1,2018,29,116,116,'',NULL,'2018-11-13 16:55:05'),(94,'','','','','Satisfactory',90,104,1,2018,29,116,116,'',NULL,'2018-12-13 22:45:41'),(95,'','','','','Satisfactory',90,104,4,2018,29,0,116,'',NULL,'2018-12-13 22:45:24'),(96,'','','','','Satisfactory',91,105,1,2018,29,116,116,'',NULL,'2018-12-13 23:01:27'),(97,'','','','','Satisfactory',92,106,1,2018,29,116,116,'',NULL,'2018-12-14 22:45:56'),(98,'','','','','Satisfactory',93,107,1,2018,29,116,116,'',NULL,'2018-12-17 13:04:29'),(99,'','','','','Satisfactory',93,107,3,2018,29,0,116,'',NULL,'2018-12-17 13:04:05'),(100,'','','','','Satisfactory',93,107,4,2018,29,0,116,'',NULL,'2018-12-17 13:04:24'),(101,'','','','','Satisfactory',94,108,1,2018,29,116,116,'',NULL,'2018-12-17 13:16:55');
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
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit_work`
--

LOCK TABLES `audit_work` WRITE;
/*!40000 ALTER TABLE `audit_work` DISABLE KEYS */;
INSERT INTO `audit_work` VALUES (88,'104100','Test this application control by trying to issue a Purchase Order in the system without vendor being selected in the system by the autorized.',116,74,1,2018,29,116,116,'',1,NULL),(89,'118115','abc',116,76,1,2018,29,116,116,'',31,NULL),(90,'119112','abc',116,78,1,2018,29,116,116,'',1,NULL),(91,'112103','Test this application control by trying to issue a Purchase Order in the system without vendor being selected in the system by the autorized.',116,78,1,2018,29,116,116,'',1,NULL),(92,'99118','123',116,79,1,2018,29,116,116,'',32,NULL),(93,'99112','456',116,80,1,2018,29,117,116,'',1,NULL),(94,'110104','prg',116,81,1,2018,29,116,116,'',33,NULL),(95,'119112','some audit program ',116,82,1,2018,29,116,116,'',34,NULL),(96,'102104','Audit Program of RcUnit',116,83,1,2018,29,116,116,'',35,NULL),(97,'105100','Test this application control by trying to issue a Purchase Order in the system without vendor being selected in the system by the autorized.',116,84,1,2018,29,116,116,'',1,NULL),(98,'107118','secondunit audit prg',116,77,1,2018,29,116,116,'',36,NULL),(99,'122101','resource auditprogram',116,85,1,2018,29,116,116,'',37,NULL),(100,'102101','reportingcheckfinal',116,86,1,2018,29,116,116,'',38,NULL),(101,'97118','abc',116,87,1,2018,29,116,116,'',39,NULL),(102,'119102','z unit r audit work',116,88,1,2018,29,116,116,'',40,NULL),(103,'110104','abc',116,89,1,2018,29,116,116,'',41,NULL),(104,'121112','Review accounts payable personnel physical access, ensuring access to purchasing and receiving activities is restricted.',116,90,1,2018,29,116,116,'',52,NULL),(105,'9899','Review Authorization Matrix specifically for the payment approval authorization',116,91,1,2018,29,116,116,'',55,NULL),(106,'122101','auditwork',116,92,1,2018,29,116,116,'',319,NULL),(107,'109110','And on sample basis agree the system prices with the master file to ensure accuracy.',116,93,1,2018,29,116,116,'',308,NULL),(108,'97111','Check system based control on a selected sample',116,94,1,2018,29,116,116,'',56,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=314 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditprogramme`
--

LOCK TABLES `auditprogramme` WRITE;
/*!40000 ALTER TABLE `auditprogramme` DISABLE KEYS */;
INSERT INTO `auditprogramme` VALUES (1,'Test this application control by trying to issue a Purchase Order in the system without vendor being selected in the system by the autorized.',1,58),(2,'Take a sample of POs\' as per the sampling methodology and trace with the documentary evidence of bidding process being undertaken as per the procurement policy..',2,58),(3,'Take a sample of POs\' as per the sampling methodology and trace with the documentary evidence of bidding process being undertaken as per the procurement policy.',3,0),(4,'Review the Authority Matrix for the segregation of responsibilities.Check a sample of contracts as per the sampling methodology to ensure that contracts are approved as per the Authority Matrix',4,0),(5,'Test this application control by trying to process an invoice in the system without PO being created',0,0),(6,'Test this application control by trying to issue a Purchase Order in the system without contract being signed off in the system',5,0),(7,'Take a sample of contracts as per the sampling methodology and trace with the documentary evidence of approvals on the contract control sheet',6,0),(8,'Take a sample of contracts as per the sampling methodology and ensure that contracts are approved as per the authority matrix',7,0),(9,'Take a sample of contracts as per the sampling methodology and trace with the documentary evidence of approvals on the contract control sheet',8,0),(10,'Review the criteria for identification of embedded derivatives and ensure compliance with the accounting standards.Through targeted sampling of contracts check if the criteria of embedded derivatives is correctly applied',9,0),(11,'Review the criteria of vendor performance evaluation and check its appropriatenessTake a sample of completed contracts as per the sampling methodology and trace with the vendor performance evaluations being performed. Review the resonableness of evaluation, actions being taken and input into the vendor data base ',10,0),(12,'1-Check expenditures/payments being categorized as exempt from issuance of PO and conclude on the reasonableness/justification                    2-Obtain a system generated list showing payments without Pos\' and ensure no payments from categories being non exempt      3- Check whether certain categories are marked as PO exempt in the system i.e tax payments, utility bill payments etc.Other than these marked categories system restricts the processing of invoice without a PO      ',11,0),(13,'1.Based on the sample selected review the monitoring being performed by the contract owner with respect to planned vs. actual hours.       2. Take a sample of contracts and validate that the hours in excess of planned/ordered are supported by authorized \"change work orders\"',12,0),(14,'Check a system based application control that no PO can be issued without approved and authorized Purchase Requisition',13,0),(15,'Based on a system based sample check from a population of Pos\' that these are approved as per the Authority Matrix',14,0),(16,'Generate a list of Pos\' with variations.From a system based sample test that all the variations are approved as per the authority matrix',15,0),(17,'1- Review the job descriptions of the relevant employees to ensure segregation of duties         2. Take a sample of payments and ensure that all these tasks are being perfomred by separate individuals                  3- Review the Access Control List of ERP and ensure that segregation of duties exist in the system',16,0),(18,'Test this application control by attempting to process a PO without filling all the mandatory fields',17,0),(19,'Test this application control by attempting to issue a PO without a valid PR',18,0),(20,'Test this application control by processing a PO and observe if vendor related information is directly extracted from the vendor module',19,0),(21,'Take a sample of Pos\' issued during the peroid and review the approvals as per the autority matrix',20,0),(22,'From a system based sample check if correct account coding is performed by the responisble',22,0),(23,'Review the item catalogue and select a sample to ensure its accuracy.Process one PO and observe whether correct gl code is automatically embedded.',23,0),(24,'Test the integration of the two systems i.e \"Vendor selection\" module and Ordering Module',24,0),(25,'Take a sample of Pos\' issued during the peroid and review the approvals as per the autority matrix',25,0),(26,'Take a sample of Pos\' issued during the peroid and validate the incorporation of correct vendor ',26,0),(27,'Test the effectiveness of GR/IR account on a system based sample. Review the appropriateness of action steps being taken on the reconciling and long outstanding items.',42,0),(28,'Test the effectiveness of review being performed on long outstanding PO on a system based sample. Review the appropriateness of action steps being taken on long outstanding Pos\'',43,0),(29,'From a system based sample match the physical inspection form being signed off by a person in a warehouse being separate from the person responsible for GRN in the system with the Pos\'. ',44,0),(30,'1.Check a system based control whereby PO is not processed until the GRN field is filled.      2. Check the application control of reminders being sent. ',45,0),(31,'Review the terms of the contract/PO with vendors with respect to instructions of invoice despatch. Review the invoice log being maintained and check the appropriateness of the same.Check the procedure of dealing with any Non Compliance by the vendor.',46,0),(32,'Check the application control of a 3 way match',47,0),(33,'Review warehouse procedures for written and practical compliance with goods inspection requirements.',48,0),(34,'Review of system ordering and receipting access to ensure SOD.Test on a sample basis to ensure unaithorized person can not enter GRN etc ',49,0),(35,'Check the application control of a 3 way match',50,0),(36,'Check the application control of a 3 way match',51,0),(37,'Review accounts payable personnel system access, ensuring access to purchasing and receiving activities is restricted.',53,0),(38,'Reperform system match observing accounting entries created at each match.',54,0),(39,'Review Authorization Matrix specifically for the payment approval authorization',55,0),(40,'Check system based control on a selected sample',56,0),(41,'Select appropriate sample and ensure that Accounts Payable  Manager has correclty valided the approval by the authorized personnel',57,0),(42,'Observe and assess adequacy system controls in place to prevent the changing of bank details during EFT run.',58,0),(43,'Select a sample and assess the appropriateness of review by the Management',59,0),(44,'Select a sample and assess the appropriateness of review by the Accounts Payable Team',60,0),(45,'Reperform system match observing accounting entries created at each match.',61,0),(47,'Reperform system match observing accounting entries created at each match.',62,0),(48,'Review accounts payable personnel physical access, ensuring access to purchasing and receiving activities is restricted.',52,0),(49,'Obtain report detailing users with system access to change vendor master data.  Review for reasonableness.',63,0),(50,'From a sample of newly selected vendors created during the year, trace to approval by the Procurement Head and Weighted Decision Analysis, Vendor Risk Analysis;if applicable',64,0),(51,'Obtain report detailing users with system access to change vendor master data.  Review for reasonableness.',65,0),(52,'Review vendor confirmation of change request.',66,0),(53,'Review vendor masterfile change report against original vendor request form.',67,0),(54,'Review inactive vendor reports and inquire into vendors not active but not yet removed.',68,0),(55,'From a sample of newly selected vendors created during the year, trace to approval by the Procurement Head ',69,0),(56,'On sample basis add test data to ensure that after the vendor slection, odering module accurately extract the price against the selected vendor in PO',21,0),(57,'Review the organization structure and roles and responsibilites for appropriateness of segregation of duties',70,0),(58,'From a sample of transfers / recruits, trace details from pre-hire activity form to system.',71,0),(59,'Review the Authority Matrix for appropriateness. Further, trace the approval of new hires against the authority matrix',72,0),(60,'Understand the system functioning and integration and test on a sample of one.',73,0),(61,'Review master data checking process for a sample of new or transferred employees.  Where evidence of review not available reperform check.',74,0),(62,'Review system access profiles around adding / changing / deleting high risk employee data.',75,0),(63,'Review master data checking process for a sample of new or transferred employees.  Where evidence of review not available reperform ',76,0),(64,'From a sample of  pay runs, obtain evidence of review of the corresponding master file change report.',77,0),(65,'Review system access profiles within the on-line banking system for reasonableness.',78,0),(66,'Observe security and access controls to payroll banking information.',79,0),(67,'Review payroll clearing account reconciliation for timely clearing of unreconciled differences.',80,0),(68,'Review system configuration to detect duplicate payroll data and assess for reasonableness.',81,0),(69,'Review duplicate reports and inquire into follow-up up actions by Payroll Supervisors.',82,0),(70,'From a sample of pay run  discrepancy reports, trace a sample of 25 discrepancies to appropriate follow-up actions by Payroll Supervisors.',83,0),(71,'Observe system payroll calculation and pay run configuration.  Observe procedures around configuration reviews and changes.',84,0),(72,'From a sample of pay run  discrepancy reports, trace a sample of 25 discrepancies to appropriate follow-up actions by Payroll Supervisors.',85,0),(73,'Review a sample of intra-period variance analysis reviews performed by the Payroll Supervisor.  Inquire into significant intra-period differences.',86,0),(74,'From a sample of 25 pay runs, trace payroll reports to evidence of review and approval.',87,0),(75,'Review cost report variance analysis and inquire into follow-up actions where large discrepancies are detected.',88,0),(76,'From a sample of pay run  discrepancy reports, trace a sample of 25 discrepancies to appropriate follow-up actions by Payroll Supervisors.',89,0),(77,'From a sample of 25 pay runs, trace payroll reports to evidence of review and approval.',90,0),(78,'Observe system configuration to calculate employee deductions during pay runs.',91,0),(79,'Employees authorise all withholdings / deductions from payroll',92,0),(80,'Review employee complaint log for timely resolution of employee deduction queries.',93,0),(81,'From a sample of pay run  discrepancy reports, trace a sample of 25 discrepancies to appropriate follow-up actions by Payroll Supervisors.',94,0),(82,'From a sample of 25 pay runs, trace payroll reports to evidence of review and approval.',95,0),(83,'Review documented payroll disaster recovery plan for adequacy and appropriateness.  Determine when it was last reviewed / updated.',96,0),(84,'Observe procedures for recording due dates for payroll disbursements / submissions.  Determine compliance with these dates.',97,0),(85,'Review system access profiles within the on-line banking system for reasonableness.',98,0),(86,'Observe security and access controls to payroll banking information.',99,0),(87,'Review payroll clearing account reconciliation for timely clearing of unreconciled differences.',100,0),(88,'Observe system payroll calculation and pay run configuration.  Observe procedures around configuration reviews and changes.',101,0),(89,'Review a sample of intra-period variance analysis reviews performed by the Payroll Supervisor.  Inquire into significant intra-period differences.',102,0),(90,'Trace current tax rates to existing system tax rates.',103,0),(91,'Review a sample of monthly ',104,0),(92,'Review cost report variance analysis and inquire into follow-up actions where large discrepancies are detected.',105,0),(93,'Review a sample of intra-period variance analysis reviews performed by the Payroll Supervisor.  Inquire into significant intra-period differences.',106,0),(94,'Review a sample of monthly ',107,0),(95,'Observe procedures for recording due dates for payroll disbursements / submissions.  Determine compliance with these dates.',108,0),(96,'Review employment tax submission process including review of regular submission forms for completeness and compliance with tax authority requirements.',109,0),(97,'Observe procedures for recording due dates for payroll disbursements / submissions.  Determine compliance with these dates.',110,0),(98,'Inquiry into process to determine workers compensation payments amounts and to ensure compliance with relevant legislation.',111,0),(99,'From a sample of 25 workers compensation payments made, trace to employee weekly / fortnightly / monthly wages for consistency.  Inquire where inconsistencies exist.',112,0),(100,'Observe and assess the system control to allocate names to rosters.',113,0),(101,'From a selected sample of  hourly payroll reports during the year, trace to appropriate shift supervisor review and authorisation.',114,0),(102,'Assess system control using test data',115,0),(103,'Perform surprise inspection of work areas and examine the reported hours on that day',116,0),(104,'From a sample of 25 hourly payroll reports during the year, trace to appropriate shift supervisor review and authorisation.',117,0),(105,'System configured to report all duplicate entries. All exceptions followed up by payroll supervisor',118,0),(106,'Review payroll complaint log for timeliness and adequacy of payroll complaint resolution.',119,0),(107,'Check any Audit of employee time records  need to understand that employees worked for all time they have worked whether it was authorized or not.',120,0),(108,'Make sure that the management is aware of company policy regarding change in payrates  with proper approval and authorization.',121,0),(109,'From a sample of 25 master file change requests, trace to appropriate authorisation and review for completeness. ',122,0),(110,'Observe review procedures around verifying system master file change requests to master file changes.  ',123,0),(111,'Review master data checking process for a sample of 25 master file change requests.  Where evidence of review not available reperform check for a sample of 25 master file changes.',123,0),(112,'From a sample of 25 pay runs, obtain evidence of review of the corresponding master file change report.',123,0),(113,'Check that to hold each employee was accountable or not ',124,0),(114,'Make sure everyone completes their start and end shift responsibilities. And each employee should be held responsible for their specific duties.',125,0),(115,'From a sample of 25 hourly payroll reports during the year, trace to appropriate shift supervisor review and authorisation.',126,0),(116,'From a sample of 25 absence reports, trace to evidence of review and authorisation.',126,0),(117,'Review payroll complaint log for timeliness and adequacy of payroll complaint resolution.',126,0),(118,'Make sure that the pay rate for each of your employees is accurate. ',127,0),(119,'Check that  every pay rate increase needs to have some type of documentation and paperwork to simplify your auditing.',128,0),(120,'Track all the pay increases you gave to employees due to a promotion, and make sure that the pay rate matches that increase. This is also important if your employees work on a bonus or commission system',129,0),(121,'Observe review procedures around verifying system master file change requests to master file changes.  ',130,0),(122,'Review master data checking process for a sample of 25 master file change requests.  Where evidence of review not available reperform check for a sample of 25 master file changes.',130,0),(123,'From a sample of 25 pay runs, obtain evidence of review of the corresponding master file change report.',130,0),(124,'1)Observe who is responsible for authorising, recording and holding assets, identifying areas for possible segregation improvements.\r\n',131,0),(125,'Obtain samples of asset additions from the additions report and trace to approved set-up forms and to unique system allocated asset numbers.\r\n\r\nReview allocated budgets against actuals and inquire into larger cost variances',132,0),(126,'Review of pre-determined asset classification scheme for inappropriate asset classifications.',133,0),(127,'Obtain samples of capital expenditure requisitions and review classification of expenditure',134,0),(128,'Review purchase and sales agreements',135,0),(129,'Review the accounting policy manual and observe it is followed',136,0),(130,'Financial Accountant to review purchase orders raised throughout the month for correct capital / operating classification.',137,0),(131,'Obtain sample of asset additions from the additions report and trace classification to approved set-up forms.',138,0),(132,'Obtain samples of purchase order and work orders and verify that it is appropriately classified',139,0),(133,'Review the accounting policy manual and observe it is followed',140,0),(134,'Obtain samples and check approval for selection of classification code for each asset selected.',141,0),(135,'Review edit report and follow-up on amounts breaching approved limits.',142,0),(136,'Review reconciliation between the asset register and general ledger ensuring discrepancies have been resolved.',143,0),(137,'Perform system testing',144,0),(138,'Observe and review monthly edit reports.',145,0),(139,'Review edit reports and observe follow-up actions for assets appearing on this report.',146,0),(140,'Observe and review monthly edit reports.',147,0),(141,'Inspect Accountant\'s review of assets not depreciated report.  Confirm that assets not depreciated are not in use.',148,0),(142,'Management review of events occurring throughout the year which may impact on assets termination value.',148,0),(143,'Obtain sample of acquisitions and test compliance against  Accounting policy depreciation requirements.',149,0),(144,'Attempt to post a retrospective depreciation charge to previous financial year and observe system controls to prevent.',150,0),(145,'Obtain report detailing users who have access to amend depreciation rates and assess for reasonableness.',151,0),(146,'Inquire with Management and observe outcomes of periodic reviews.',152,0),(147,'Review depreciation reasonableness tests performed and inquire into significant variances.',153,0),(148,'Obtain sample and review the accuracy of system data against the acquired asset information checklist.',154,0),(149,'Inquire with Management and observe outcomes of  reviews.',155,0),(150,'Observe the system depreciation run and the procedures followed.  Reperform a sample of depreciation calculations.',156,0),(151,'Obtain sample of acquisitions and test compliance against  Accounting policy depreciation requirements.',157,0),(152,'Observe depreciation charges and accumulated depreciation regularly reconciled to general ledger',158,0),(153,'Observe the month-end depreciation run for compliance with month-end procedures documentation.',159,0),(154,'Observe the recording of an asset disposal in the system to ensure system controls are in place.',160,0),(155,'Obtain sample of asset disposals recorded in the system and trace to authorised disposal forms.',161,0),(156,'Obtain sample of disposal and test compliance against  Accounting policy  requirements.',162,0),(157,'Review reconciliation of general ledger and fixed asset register',163,0),(158,'Observe system calculation of gain / loss on disposal.  Reperform a sample of disposal gain / loss calculations for accuracy.',164,0),(159,'Accountant to prepare and review asset disposal report and confirm that disposals at less than fair value are reported.',164,0),(160,'Observe the recording of an asset disposal in the system to ensure system controls are in place.',164,0),(161,'Obtain report detailing those users who have access to dispose of assets in the system and observe for appropriateness.',165,0),(162,'Observe segregations in both the system and in practice.',166,0),(163,'Obtain report detailing those users who have access to dispose of assets in the system and observe for appropriateness.',167,0),(164,'Review third party documentation / reports detailing testing undertaken, reported findings and actions taken.',168,0),(165,'Obtain report detailing those users who have access to dispose of assets in the system and observe for appropriateness.',169,0),(166,'Test Accounting Policy requirements against a sample of asset disposals.',170,0),(167,'Review profit and loss on disposals account reconciliation ensuring discrepancies have been resolved.',171,0),(168,'Obtain list of changes/modifications to reserve tracking data\r\n\r\n',172,0),(169,'For a sample of reserve agree and confirm the third party reserve with internally determined reserves to ensure accuracy and completeness.\r\nFor a sample of reserve agree and confirm the third party reserve with internally determined reserves to ensure accuracy and completeness.\r\n',173,0),(170,'Ensure accuracy of the economic factors used in estimating values.\r\n\r\nReview checklist to ensure compliance.',174,0),(171,'Review reservoirs analysis report and ensure that there are no unusual trends/changes and investigate if there are any.\r\n',175,0),(172,'Obtain reconciliation for approval and authorisation.',176,0),(173,'Review reserve models and ensure that modified terms of leases or contractual obligations are completely considered in model.',177,0),(174,'Agree applicable regulations with the estimated procedure to ensure compliance and accuracy.',178,0),(175,'From a sample of monthly reports, trace to evidence of review by Close Coordinator.',179,0),(176,'Obtain close calendar schedule and on sample basis ensure the completeness and accuracy of the entries made in the ER.',180,0),(177,'Analytically review financial statements to ensure accuracy and completeness of reporting and disclosure',181,0),(178,'Obtain sample of prepayments and ensure that invoices has been received from the supplier and ensure proper cut-off',182,0),(179,'Requests for account changes / creations, sight evidence of Accountant review of account grouping.  Perform reasonableness test on account grouping assignment, assessing for reasonableness against the nature of the account and its purpose.\r\n',183,0),(180,'Observe evidence of Key User review of request.',184,0),(181,'Sight evidence of Financial Accountant review of system change against change form. Review system change and assess for reasonableness against change request form.',185,0),(182,'Review guidelines and assess for reasonableness.  Assess compliance with guidelines.',186,0),(183,'Review guidelines and assess for reasonableness.  Assess compliance with guidelines.',187,0),(184,'Inspect system access rights to change / create accounts.  Assess for reasonableness.',188,0),(185,'Observe Finance Specialist review of general ledger master records against company chart of accounts for reasonableness and consistency. ',189,0),(186,'obtain list of authorised unused accounts and review them in the system of sample basis to ensure that all the unused accounts are accurately  removed from the system.',190,0),(187,'Review reconciliations for timely follow-up of unreconciled intercompany mismatches.',192,0),(188,'Review reconciliations for timely follow-up of unreconciled intercompany mismatches.',193,0),(189,'Review bi-monthly reconciliations for appropriate and timeliness follow-up of unreconciled items.  Trace group company investment and equity information from reconciliations to information provided by source.',194,0),(190,'From shareholder funds reconciliations observe appropriate authorization.',195,0),(191,'From group reclassifications, trace to fit within standard template and check the accuracy of the journal.',196,0),(192,'From months, trace standard checklist journals to actual journals process for accuracy and completeness.',197,0),(193,'Review evidence of annual review and adjustments made.',198,0),(194,'Observe and assess system controls over copying consolidated data.',199,0),(195,'From changes to cut-over data, trace to authorised cut-over data request.',200,0),(196,'Observe data reconciliation ensuring appropriate and timely follow-up of discrepancies.',201,0),(197,'Observe appropriate authorisation and review of data reconciliation reports.',203,0),(198,'Review check file reports and investigate actions to resolve source data errors.',204,0),(199,'Observe evidence of Group Accounting review of checker files.',205,0),(200,'From individual intercompany balances, trace to intercompany declarations from counterparties.',191,0),(201,'Observe system control to prevent changes to prior-period balances.  Attempt to post change to prior-period figures.',206,0),(202,'From changes to prior-period balances, trace to authorization and review by Group Accounting.  Assess the rational for the change for appropriateness.',207,0),(203,'Observe spreadsheet integrity review and re-perform spreadsheet logic testing where necessary.',208,0),(204,'Inspect evidence of Senior Accountant review and assess for reasonableness.',209,0),(205,'Observe reporting package ensuring appropriate and timely follow-up of discrepancies.',210,0),(206,'Review list of standard reporting package to ensure completeness prior to issuance of reporting package',211,0),(207,'Review the result of the manual comparison performed between the information in the reporting package and the information in the supporting document and ensure discrepancies are resolved on timely basis',212,0),(208,'Review accuracy of standard recurring adjustments required to finalize the reporting package.',213,0),(209,'Review supporting documents to confirm the reasonableness of assumptions, mathematical accuracy, and completeness and accuracy of data inputs in accordance with documented policies and procedures. ',218,0),(210,'Review business performance review documents and supporting ensuring appropriate and timely follow-up of discrepancies.',214,0),(211,'Agree the accuracy of the reporting package spreadsheets and ensure that it is reviewed by the appropriate level of management',215,0),(212,'Review system to ensure its accuracy and effectiveness',216,0),(213,'Review balances transferred automatically by system with balance of the reporting package to ensure that system accurately transfers the data as per pre-defined mapping.',217,0),(214,'From a sample of users and entity changes, trace to Financial Controller authority.',219,0),(215,'From a sample of group view changes, trace to Manager of Group External Reporting authority.',220,0),(216,'From the system Masterfile change report, trace system changes to authorisation by system administrator.',221,0),(217,'From a sample of edit access users, trace to authorised edit access form.',222,0),(218,'Review security group access rights in Consolidations system for reasonableness against authorised access forms.',223,0),(219,'Observe documentation of consolidations system access review.  Inquire into the timeliness of anomalies resolution.',224,0),(220,'Review documented change control process and investigate compliance to policy and procedure requirements.',225,0),(221,'Review user testing documentation for appropriateness and completeness.',226,0),(222,'Trace a sample of system meta data changes to authorisation by system administrator.',227,0),(223,'From a sample of entity / cost centre creations, trace to asset / CSG key user requests.',228,0),(224,'Obtain list of key personnel who needs to submit related party transactions and ensure appropriate and timely follow-up of personnel who have not submitted key personnel transactions. ',229,0),(225,'Ensure that financial statements are accurately cross-referenced and all the differences has been rectified and investigated in a timely manner',230,0),(226,'Obtain and review supporting documents to ensure that business performance reviews are performed on regular basis. And ensure that variances are addressed on a timely basis and enquire if the variance exists',231,0),(227,'Review documentation to ensure that accounting personnel and senior management perform review of financial statements on periodic basis for accounting policies and GAAP',232,0),(228,'Review list of related party transactions and disclosures, and ensure its completeness in the statement of financial statements.',233,0),(229,'Review checklist to ensure accuracy and completeness in the period-end financial reporting process. ',234,0),(230,'Ensure that differences identified in the manual comparison are accurately resolved and investigated on a timely basis',235,0),(231,'Ensure that discrepancies between the financial statements / disclosure items and relevant supporting documentation are resolved on timely basis. ',236,0),(232,'Ensure approval and authorisation of financial statement and disclosure items. And ensure that unusual items are resolved during the period.',237,0),(233,'Review supporting document for the approval and authorisation of changes to be made in the financial statements and disclosure items.',238,0),(234,'Ensure completeness of the information obtained form the reporting units/departments for inclusion in the financial statements. Ensure that differences are investigated and addressed on timely basis.\r\nEnsure completeness of the information obtained form the reporting units/departments for inclusion in the financial statements. Ensure that differences are investigated and addressed on timely basis.\r\n',239,0),(235,'Ensure the completeness of the information required to prepare the financial statements and disclosure items and ensure that all missing and incomplete information is being investigated and resolved on a timely basis',240,0),(236,'Review financial statements for its accuracy in the calculations and disclosures',241,0),(237,'Review documentation to ensure that reported related party transactions are timely investigated and resolved',242,0),(238,'Review accuracy and approval of all topside entries made to the financial statements to ensure accuracy and completeness\r\n',243,0),(239,'Obtain and review aging report and enquire management about long outstanding balances due, if any.',244,0),(240,'Review policy for the provision of bad debts and on sample basis agree the provision for debts provided during the year with policy to ensure accuracy and compliance with policy.',245,0),(241,'Obtain and review documentation to confirm and agree the management approval.',246,0),(242,'Review documented policy for calculating provision and write off against bad debts and on sample basis agree the provisions and write off of bad debts with policy to ensure compliance accuracy.',247,0),(243,'Analytically assess the areas where provision could occur and investigate those  areas for provision along with the supporting documentation. For any missing items ',248,0),(244,'For a sample data perform procedure over Lockbox interface to ensure the accuracy of the system. \r\n\r\nEnsure that unmatched cash receipts placed into a suspense file are investigated and resolved. If, in case, there are any unmatched ',249,0),(245,'For a sample of cash receipts, review remittance slips as their supporting. Enquire from management for cash receipt with missing remittance slips.',250,0),(246,'For a sample , agree cash receipt with deposits to ensure accuracy and completeness. \r\nFor any differences investigate from management.',251,0),(247,'Observe the procedure of cash count and ensure that defined procedure is being followed by the designated person. \r\nReview signature of person reconciled and  signed the day end report to ensure proper segregation of duties.\r\n',252,0),(248,'Ensure that changes to sales are approved by the authorized personnel and only designated person has access to update sales price in the system',253,0),(249,'Obtain supporting document and ensure that the sale incentive reserve policy is in line with the company\'s sales incentive estimation method.',254,0),(250,'For a sample of data, perform three way match by comparing the quantities on the credit memo, the sales return receipt documents, and the sales return authorization document. Investigate and enquire from management for any differences.',255,0),(251,'For a sample, agree the price per credit card with the sale price on memo card to ensure accuracy and completeness.',256,0),(252,'Review documentation to ensure approval and authorisation ',257,0),(253,'obtain reports of product wise quarterly analysis and review documents to ensure that analysis has been performed and reviewed by the appropriate authorities',258,0),(254,'Review sales returns estimation policy and ensure that assumptions used in the estimation are justifiable.\r\n',259,0),(255,'On sample basis review return authorisation supporting documents and confirm the ',260,0),(256,'Obtain documentation to confirm whether the system log has been reviewed on monthly/quarter basis and ensure that any differences arising during the review has been investigated',261,0),(257,'On sample basis review adjustment to ensure authorize approval, accuracy and compliance with the policy. ',262,0),(258,'For a sample of Adjustments made during the year, review the accuracy and classification of the transactions made in GL. \r\n',263,0),(259,'For a sample, obtain supporting to ensure that approval and authorisations has been made by the authorized person.',264,0),(260,'Analytically review the number of routine and non-routine transactions based on nature and amount of transactions and investigate if there are any usual trends in amounts of transactions made.\r\n',265,0),(261,'For a sample of routine adjustments during interim periods, review  transactions to ensure accuracy and proper classification of transactions being made by the operational level and also agree the approval for transaction by managerial level personnel\r\n',266,0),(262,'Review non-routine transactions and review supporting for approval of transactions\r\n',267,0),(263,'For a sample of adjustments made to GL, Agree the credentials with the person authorized to make entries to the GL',268,0),(264,'Analytically review the sales recorded with the external data and ensure its accuracy',269,0),(265,'For adjustments made on sample basis ensure that the adjustment made during the year are linked to the customers accounts, which are linked to their contracts and agreement, to avoid misappropriation of receivables',270,0),(266,'Interview the person authorized to perform the accounts analysis to ensure that he/she understands the process.\r\n\r\nOn sample basis review adjustments made by the personnel to ensure accuracy and completeness',271,0),(267,'Review reports to ensure that the revenue and its related accounts are reconciled with the GL to ensure accuracy and completeness\r\n\r\n',272,0),(268,'Analytically review the trends and analysis and investigate variances above the pre-approved limits to ensure proper authorization\r\n\r\n',273,0),(269,'Obtain reports for unresolved substantial trends, if any, and ensure that they are being approved and resolved on timely basis by the higher level of authority',274,0),(270,'validates the access right to customer data to ensure that data is secure and is subject to access by only authorized personnel\r\n\r\n',275,0),(271,'Review authorisation letter for the approval of changes to be made to customer data and ensure that proper log has been maintained and reviewed periodically by appropriate level of authority.\r\n\r\n',276,0),(272,'Review disclosure to ensure the accuracy of the disclosure of customers data.',277,0),(273,'Review checklist to ensure the compliance with applicable laws and regulations and investigate any differences arising, if any, for any compliances with the legal department',278,0),(274,'Review the basis of recognition of revenue for long term contracts to ensure accuracy and compliance in recognition of revenue',279,0),(275,'Obtain documented policies and observe whether policies and procedures are in place by competent authorities.\r\n\r\n',280,0),(276,'Observe and ensure that there are proper segregation of duties for authorisation, recording and custody of cash.',281,0),(277,'Review supporting documents and arrangements in place to ensure that  third party\'s operations are evaluated and reviewed by the relevant authorized personnel.\r\nFurthermore, evaluate supporting documents to ensure that third party operations are in lined with the company\'s policy.\r\n',282,0),(278,'Validate Access right to system and other necessary data and ,if possible, observe that only authorized personnel has the access to the system authorized to him/her.\r\n\r\n',283,0),(279,'Obtain report of changes made to data and ensure that changes were approved by the relevant authority.\r\nObtain Log report to agree the changes made  in system was made by the relevant authorized person.\r\n ',284,0),(280,'On sample basis perform one on one interview with staff members to assess their current understanding of procedures and competency for recording and maintenance of relevant information.\r\n',285,0),(281,'And review their work on sample basis for accuracy, completeness and up-to-date of records.',286,0),(282,'On sample basis review supporting documents to validate the acknowledgment from customers.\r\n\r\n',287,0),(283,'For a sample of transactions recorded in the revenue account, obtain and review supporting documents to ensure the accuracy and approval of transactions.\r\n',288,0),(284,'Obtain and review sales and accounts receivable subsidiary ledgers to ensure that they are reconciled on periodic basis and difference identified are investigated and resolved on timely basis.\r\n',289,0),(285,'Obtain list of staff dedicated to facilitate and handle customers queries.\r\nObtain list of staff dedicated to facilitate and handle customers queries.\r\n',290,0),(286,'Obtain customer queries log and analytically review it assess the number of complaints not resolved on timely basis\r\n',291,0),(287,'Review signature on the log to ensure that it was reviewed by the competent authority on periodic basis.',292,0),(288,'For a sample of sales order, agree the item\'s prices on the sales order with the authorized price master file to ensure accuracy of the prices used in the sales order.',293,0),(289,'For sales order exceeding predetermined threshold, obtain supporting documents for approval by the relevant authorized personnel.',294,0),(290,'Ensure proper segregation of duties between the person making sales order and the person responsible for making adjustment to credit limits. \r\n\r\nFor orders made in excess of credit limits obtain their supporting documents for approval and authorisation by the relevant higher management authority',295,0),(291,'Observe approval process and ensure that all contracts and contract variations are reviewed prior approval. \r\n\r\nFor places where ERP are implemented, Ensure that system accurately tracks contract compliance and block orders in case validity is expired.\r\n',296,0),(292,'Check approval for the terms granted outside the approved price master file for the sales order.',297,0),(293,'Obtain system report and investigate the prices granted outside those specified in the master file. Ensure that approval and authorisation has been obtained for the price granted outside the master price list.',298,0),(294,'For a sample of sales order ensure that sales order has been signed and approved by the person authorized for approval.',299,0),(295,'On sample basis compare customer sales order total with the limits defined with customer contracts to ensure that terms and conditions has not been breached. ',300,0),(296,'Check approval for the  price granted outside the approved price master file for the sales order.',301,0),(297,'Ensure that system alerts when duplicate customer is added.',302,0),(298,'Obtain aging of outstanding orders and investigate, if there are any long outstanding orders for closure.\r\nPerform test of control to assess that ERP only processes an order if required quantity is appearing in inventory.',303,0),(299,'Perform test of control to assess that ERP only processes an order if required quantity is appearing in inventory.',304,0),(300,'Obtain picking list and on sample basis match shipments dispatched with the invoices generated to ensure accuracy, completeness and existence of invoices against shipment and vice versa.',305,0),(301,'For a sample of invoices agree their cut-off date with the date of recording of invoice in the system.',306,0),(302,'Obtain list of employees authorized to update and input prices to the system. ',307,0),(303,'And on sample basis agree the system prices with the master file to ensure accuracy.',308,0),(304,'Generate invoices using the test data to ensure that invoices are generated automatically on shipment.',309,0),(305,'For a sample of invoices check invoice numbers and compare it with the system generated invoices to ensure that invoices number agreed with the specific range generated during the period being tested. ',310,0),(306,'For a sample of services completed during the period, review their completion certificates to ensure that work has been completed and the completion certificate has been obtained against the work completed.\r\n\r\n',311,0),(307,'For a sample of completion certificates, ensure that cost of services completed has been accurately recorded in the relevant period.\r\n',312,0),(308,'For bill and hold arrangements, obtain stock listing and ensure that the inventory held under hill and hold arrangements has not been included in the Stock report.\r\n',313,0),(309,'Obtain report of outstanding orders report and investigate the reason and status for unbilled invoices.',314,0),(310,'Obtain exception report from system for invoices over a specified amount and invoices containing unusual prices, terms, and discounts and review their supporting document for the approval and authorisation.',315,0),(311,'Generate duplicate invoices on sample basis to ensure that system prevents duplicate invoices to be generated by the system.\r\n',316,0),(312,'For a sample of invoices agree invoice prices with the approved pricing source.',317,0),(313,'Obtain report of reversals and adjustments made during the year and ensure that reversal and adjustments has been approved by the head of department.\r\n',318,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (0,'none','none','none@none.com'),(1,'hyphen','faheem','faheem@hyphenconsult.com'),(29,'korgent','k','k@email.com'),(30,'Hyphen Solutions','hyphen','zohaib1112@hotmail.com'),(31,'abilite test','zohaib','zohaib1112@hotmail.com');
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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companyskillrelation`
--

LOCK TABLES `companyskillrelation` WRITE;
/*!40000 ALTER TABLE `companyskillrelation` DISABLE KEYS */;
INSERT INTO `companyskillrelation` VALUES (25,1,2,2018,'4160'),(34,29,2,2018,'5040'),(35,30,2,2018,'6240'),(36,31,2,2018,'5680');
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
  `password` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`employeeId`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (0,'Select User','nouser@user.com',1,1,1,'no design',0,NULL,NULL,0,0,'yes',0,0,0,0,'a'),(1,'Muhammad Faheem Piracha','faheem@email.com',1,1,1,'des',1,NULL,'kj',1,1,'yes',1,1,1,1,'faheem'),(2,'hy1','hy1@email.com',1,NULL,1,'hj',NULL,'2014-12-25 00:00:00',NULL,2,1,'yes',0,2,1,3,'hy1'),(3,'hyad','hyad',1,NULL,1,'admin',NULL,'2014-12-29 00:00:00',NULL,3,3,'yes',0,2,1,4,'hyad'),(5,'hy2','hy2@email.com',1,1,1,'lead',NULL,'2014-12-30 00:00:00',NULL,5,2,'yes',0,2,1,2,'hy2'),(8,'hy3','hy3',1,NULL,1,'hy3',NULL,'2014-12-30 00:00:00',NULL,8,2,'yes',0,2,1,3,'hy3'),(9,'mgm','mgm',1,NULL,1,'mgm',NULL,'2014-12-31 00:00:00',NULL,9,9,'no',0,1,4,5,'mgm'),(115,'korgentadmin','korgentadmin@email.com',1,NULL,1,'Admin',NULL,'2018-10-17 00:00:00',NULL,114,115,'yes',0,2,29,4,'korgentadmin'),(116,'korgenthead','korgenthead@email.com',1,NULL,1,'Head',NULL,'2018-10-17 00:00:00',NULL,115,116,'yes',0,2,29,1,'korgenthead'),(117,'korgentLead','korgentlead@email.com',1,NULL,1,'lead',NULL,'2018-10-17 00:00:00',NULL,116,116,'yes',0,2,29,2,'korgentlead'),(118,'korgentauditor','korgentauditor@email.com',1,NULL,1,'auditor',NULL,'2018-10-17 00:00:00',NULL,117,117,'yes',0,2,29,3,'korgentauditor'),(119,'korgentMgm','korgentmgm@email.com',1,NULL,1,'mgm',NULL,'2018-10-17 00:00:00',NULL,118,119,'no',0,2,29,5,'korgentmgm'),(120,'Admin','hyphensolutions@email.com',1,NULL,1,'Admin',NULL,'2018-12-17 00:00:00',NULL,119,120,'yes',0,1,30,4,'hyphen'),(121,'Internal Audit Head','hyphenhead@email.com.com',1,NULL,1,'Internal Audit Head',NULL,'2018-12-17 00:00:00',NULL,120,121,'yes',0,2,30,1,'hyphen'),(122,'Team Lead','hyphenteamlead@email.com',1,NULL,1,'Team Lead',NULL,'2018-12-17 00:00:00',NULL,121,121,'yes',0,2,30,2,'hyphen'),(123,'Auditor','hyphenauditor@email.com',1,NULL,1,'Auditor',NULL,'2018-12-17 00:00:00',NULL,122,122,'yes',0,2,30,3,'hyphen'),(124,'Management','hyphenmanagement@email.com',1,NULL,1,'Management',NULL,'2018-12-17 00:00:00',NULL,123,124,'no',0,3,30,5,'hyphen'),(125,'abilite test-ADMIN','abilitetestadmin@email.com',1,NULL,1,'abilite test-ADMIN',NULL,'2018-12-17 00:00:00',NULL,124,125,'yes',0,1,31,4,'abilite'),(126,'Head of Internal Audit','abilitetesthead@email.com',1,NULL,1,'Head of Internal Audit',NULL,'2018-12-17 00:00:00',NULL,125,126,'yes',0,2,31,1,'abilite'),(127,'Team Lead','abilitetestlead@email.com',1,NULL,1,'Team Lead',NULL,'2018-12-17 00:00:00',NULL,126,126,'yes',0,2,31,2,'abilite'),(128,'Auditor','abilitetestauditor@email.com',1,NULL,1,'Auditor',NULL,'2018-12-17 00:00:00',NULL,127,127,'yes',0,2,31,3,'abilite'),(129,'Management','abilitetestmanaganement@email.com',1,NULL,1,'Management',NULL,'2018-12-17 00:00:00',NULL,128,129,'no',0,3,31,5,'abilite');
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
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exception`
--

LOCK TABLES `exception` WRITE;
/*!40000 ALTER TABLE `exception` DISABLE KEYS */;
INSERT INTO `exception` VALUES (80,'Exception from 104100',74,119,119,'2018-10-20 00:00:00','First Unit','2018-10-20 00:00:00','Here are management comments',116,'Approved','1','Here are the final comments','1','Approved','Approved',68,2018,29,'Approved','Here are some recommendeations ','1','','0'),(81,'abc',76,119,119,'2018-10-23 00:00:00','Inventory',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',69,2018,29,'','abc','0','','1'),(82,'abc',76,119,119,NULL,'Inventory',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'',70,2018,29,NULL,'abc','0','','2'),(83,'abc',78,119,119,'2018-10-25 00:00:00','Third Unit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',72,2018,29,'','abc','0','','0'),(84,'abc',78,119,119,'2018-10-25 00:00:00','Third Unit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',73,2018,29,'','abc','0','','1'),(85,'abc',78,119,119,'2018-10-25 00:00:00','Third Unit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',74,2018,29,'','abc','0','','2'),(86,'abc',78,119,119,'2018-10-25 00:00:00','Third Unit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',75,2018,29,'','abc','0','','0'),(87,'abc',78,119,119,'2018-10-25 00:00:00','Third Unit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',76,2018,29,'','abc','0','','2'),(90,'456',80,119,119,'2018-10-26 00:00:00','Fifth Unit','2018-10-03 00:00:00','456',116,'Approved','0','456','1','Approved','Approved',79,2018,29,'','456','0','','1'),(91,'check exception',81,119,119,'2018-11-15 00:00:00','engViewCorrec',NULL,NULL,116,'Approved','0',NULL,'0','Approved','Approved',80,2018,29,'cmnt','any reccomendation','0','anhy implications to check ','1'),(92,'check exception',81,119,119,NULL,'engViewCorrec',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'',81,2018,29,NULL,'recc','0','hello','2'),(94,'engagement excweption',81,119,119,NULL,'engViewCorrec',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'',83,2018,29,NULL,'qwwe','0','','1'),(95,'reportingExceptionCheck',82,119,119,NULL,'reportingcheck',NULL,'',116,NULL,'0',NULL,'0',NULL,'',84,2018,29,NULL,NULL,'0',NULL,'1'),(96,'exception of RcUnit',83,119,119,NULL,'RcUnit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',85,2018,29,'ok','','0','','0'),(97,'exception2 of RcUnit',83,119,119,NULL,'RcUnit',NULL,NULL,116,NULL,'0',NULL,'0',NULL,'Approved',85,2018,29,'ok for 2','','0','','0'),(98,'rc1ex1',84,119,119,NULL,'RC1Unit','2018-11-26 00:00:00','coments',116,'Approved','1','final','1','Approved','Approved',86,2018,29,'ok','action steps','1','imp','0'),(99,'rc1ex2',84,119,119,NULL,'RC1Unit','2018-11-29 00:00:00','comm',116,'Approved','1','final2 ','1','Approved','Approved',86,2018,29,'ok','actions tep2 ','1','imp','0'),(115,'abiliteexception1',90,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,94,2018,29,NULL,NULL,'0',NULL,NULL),(116,'abiliteexcption2',90,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,94,2018,29,NULL,NULL,'0',NULL,NULL),(117,'abiliteexception1',90,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,95,2018,29,NULL,NULL,'0',NULL,NULL),(118,'abiliteexcption2',90,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,95,2018,29,NULL,NULL,'0',NULL,NULL),(119,'abilite1',91,119,119,'2018-12-21 00:00:00','abilitechck3','2018-12-13 00:00:00','mgm cmnts',116,'Approved','0','here are the final cmnts','1','Approved','Approved',96,2018,29,'cc','aact','0','imp','0'),(120,'abilite2',91,119,119,'2018-12-28 00:00:00','abilitechck3','2018-12-27 00:00:00','cccc',116,'Approved','1','here are th4e final cmnts','0','Approved','Approved',96,2018,29,'bbb','accc','1','impli','2'),(121,'reportcheck',92,119,119,'2018-12-21 00:00:00','reportcheckexc','2018-12-26 00:00:00','cmnts mgm',116,'Approved','1','final cmnts','1','Approved','Approved',97,2018,29,' vb','jj','1','imp','1'),(122,'exception auditee check1',93,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,98,2018,29,NULL,NULL,'0',NULL,NULL),(123,'exception auditee check1',93,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,99,2018,29,NULL,NULL,'0',NULL,NULL),(124,'exception auditee check2',93,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,98,2018,29,NULL,NULL,'0',NULL,NULL),(125,'exception auditee check2',93,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,99,2018,29,NULL,NULL,'0',NULL,NULL),(126,'exception auditee check1',93,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,100,2018,29,NULL,NULL,'0',NULL,NULL),(127,'exception auditee check2',93,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,100,2018,29,NULL,NULL,'0',NULL,NULL),(128,'xyz exception1',94,0,0,NULL,NULL,NULL,NULL,0,NULL,'0',NULL,'0',NULL,NULL,101,2018,29,NULL,NULL,'0',NULL,NULL);
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
  `messageread` tinyint(1) unsigned DEFAULT NULL,
  PRIMARY KEY (`informationRequestId`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informationrequest`
--

LOCK TABLES `informationrequest` WRITE;
/*!40000 ALTER TABLE `informationrequest` DISABLE KEYS */;
INSERT INTO `informationrequest` VALUES (55,'how are you auditor',118,'ee','2018-12-11 00:00:00',0,0,0,116,29,74,'i am great head',1),(56,'how are you head from lead',116,'qq','2018-12-13 00:00:00',0,0,0,117,29,74,'i am great lead from head',1),(57,'provide the webinar broucher',119,'','2018-12-18 00:00:00',0,0,0,118,29,74,'Sorry, I don\'t have any',1);
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
  `description` varchar(200) NOT NULL DEFAULT '',
  `assignedBy` varchar(45) NOT NULL DEFAULT '',
  `assignedTo` varchar(45) NOT NULL DEFAULT '',
  `date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `informationrequestId` int(10) unsigned DEFAULT NULL,
  `respond` varchar(205) DEFAULT NULL,
  PRIMARY KEY (`informationrequestlogsId`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informationrequestlogs`
--

LOCK TABLES `informationrequestlogs` WRITE;
/*!40000 ALTER TABLE `informationrequestlogs` DISABLE KEYS */;
INSERT INTO `informationrequestlogs` VALUES (38,'hi auditor from head','118','118','2018-12-11 00:00:00',55,'hello head from auditor'),(39,'hi auditor from head','116','118','2018-12-11 00:00:00',55,'how are you auditor'),(40,'how are you auditor','118','118','2018-12-11 00:00:00',55,'i am great head'),(41,'lead to head hello','116','116','2018-12-13 00:00:00',56,'head to lead hi'),(42,'lead to head hello','117','116','2018-12-13 00:00:00',56,'how are you head from lead'),(43,'how are you head from lead','116','116','2018-12-13 00:00:00',56,'i am great lead from head'),(44,'provide the webinar broucher','119','119','2018-12-18 00:00:00',57,'Sorry, I don\'t have any');
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
) ENGINE=InnoDB AUTO_INCREMENT=349 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_employee_relation`
--

LOCK TABLES `job_employee_relation` WRITE;
/*!40000 ALTER TABLE `job_employee_relation` DISABLE KEYS */;
INSERT INTO `job_employee_relation` VALUES (257,117,74),(258,118,74),(259,116,74),(260,119,74),(261,118,75),(262,116,75),(263,117,76),(264,118,76),(265,116,76),(266,118,77),(267,116,77),(268,116,77),(269,118,78),(270,116,78),(271,118,79),(272,116,79),(273,117,80),(274,118,80),(275,116,80),(276,116,81),(277,116,82),(278,117,83),(279,118,83),(280,116,83),(281,117,84),(282,118,84),(283,116,84),(284,117,84),(285,118,84),(286,119,84),(287,116,84),(288,117,85),(289,118,85),(290,119,85),(291,116,85),(292,117,86),(293,118,86),(294,119,86),(295,116,86),(296,117,87),(297,118,87),(298,119,87),(299,116,87),(300,117,87),(301,118,87),(302,119,87),(303,116,87),(304,117,88),(305,118,88),(306,119,88),(307,116,88),(308,117,89),(309,118,89),(310,119,89),(311,116,89),(312,116,90),(313,117,91),(314,118,91),(315,119,91),(316,116,91),(317,117,92),(318,118,92),(319,119,92),(320,116,92),(321,117,93),(322,118,93),(323,119,93),(324,116,93),(325,117,93),(326,118,93),(327,119,93),(328,116,93),(329,117,93),(330,118,93),(331,119,93),(332,116,93),(333,117,94),(334,118,94),(335,119,94),(336,116,94),(337,117,94),(338,118,94),(339,119,94),(340,116,94),(341,117,94),(342,118,94),(343,119,94),(344,116,94),(345,127,95),(346,128,95),(347,129,95),(348,126,95);
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
) ENGINE=InnoDB AUTO_INCREMENT=229 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_skill_relation`
--

LOCK TABLES `job_skill_relation` WRITE;
/*!40000 ALTER TABLE `job_skill_relation` DISABLE KEYS */;
INSERT INTO `job_skill_relation` VALUES (183,2,269),(184,1,270),(185,3,270),(186,3,271),(187,3,272),(188,1,272),(189,3,272),(190,3,273),(191,3,275),(192,3,276),(193,3,277),(194,2,278),(195,1,280),(196,1,281),(197,1,281),(198,1,282),(199,1,283),(200,2,285),(201,3,285),(202,1,285),(203,2,285),(204,3,285),(205,1,287),(206,2,288),(207,3,288),(208,1,290),(209,2,290),(210,3,290),(211,1,292),(212,2,292),(213,3,292),(214,1,293),(215,3,293),(216,3,296),(217,1,296),(218,3,296),(219,1,296),(220,3,296),(221,1,299),(222,3,299),(223,1,299),(224,3,299),(225,1,299),(226,3,299),(227,2,295),(228,3,295);
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
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobcreation`
--

LOCK TABLES `jobcreation` WRITE;
/*!40000 ALTER TABLE `jobcreation` DISABLE KEYS */;
INSERT INTO `jobcreation` VALUES (74,'Strategic','Finance','Medium',1,'',269,'First Unit','19-10-18','26-10-18',5,116,2018,29,'1'),(75,'Strategic','Finance','High',1,'',270,'Sales','01-07-18','08-07-18',0,116,2018,29,'1'),(76,'Strategic','Finance','Medium',1,'',271,'Inventory','01-05-18','08-05-18',1,116,2018,29,'1'),(77,'Strategic','Finance','Medium',1,'',272,'Second Unit','01-09-18','08-09-18',0,116,2018,29,'1'),(78,'Strategic','Finance','High',1,'',273,'Third Unit','01-06-18','08-06-18',2,116,2018,29,'1'),(79,'Strategic','Finance','High',1,'',275,'Fourth Unit','01-10-18','08-10-18',1,116,2018,29,'1'),(80,'Strategic','Finance','Medium',1,'',276,'Fifth Unit','01-12-18','08-12-18',5,116,2018,29,'1'),(81,'Strategic','none','Medium',1,'yes',277,'engViewCorrec','31-10-18','07-11-18',1,116,2018,29,'1'),(82,'Strategic','Finance','Low',0,'skill',278,'reportingcheck','21-11-18','21-11-18',1,116,2018,29,'1'),(83,'Strategic','Finance','High',1,'',280,'RcUnit','24-11-18','01-12-18',2,116,2018,29,'1'),(84,'Strategic','Finance','Low',1,'',281,'RC1Unit','17-11-18','24-11-18',5,116,2018,29,'1'),(85,'Strategic','Finance','Medium',1,'',282,'resource auditableunit','12-11-18','19-11-18',0,116,2018,29,'1'),(86,'Strategic','IT','High',1,'',283,'reportingfinalunit','22-11-18','29-11-18',0,116,2018,29,'1'),(87,'Strategic','Finance','High',1,'',285,'new1','22-11-18','29-11-18',0,116,2018,29,'1'),(88,'Strategic','IT','High',1,'yes',287,'z unitR','13-11-18','20-11-18',0,116,2018,29,'1'),(89,'Strategic','Finance','High',1,'',288,'new2','01-02-18','08-02-18',0,116,2018,29,'1'),(90,'Strategic','IT','Low',1,'',290,'abiluite check','13-12-18','20-12-18',0,116,2018,29,'1'),(91,'Strategic','IT','Low',2,'yes',292,'abilitechck3','13-12-18','27-12-18',5,116,2018,29,'1'),(92,'Strategic','IT','Low',1,'yes',293,'reportcheckexc','17-12-18','24-12-18',5,116,2018,29,'1'),(93,'Strategic','IT','Low',1,'',296,'resourcechecking','17-12-18','24-12-18',0,116,2018,29,'1'),(94,'Strategic','IT','Low',1,'',299,'xyz unit','18-12-18','25-12-18',1,116,2018,29,'1'),(95,'Strategic','Finance','Medium',1,'',295,'Sales','01-01-18','08-01-18',0,126,2018,31,'1');
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
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobtimeestimation`
--

LOCK TABLES `jobtimeestimation` WRITE;
/*!40000 ALTER TABLE `jobtimeestimation` DISABLE KEYS */;
INSERT INTO `jobtimeestimation` VALUES (62,'Scope Level',1,40,1,41,'Outstation',1,49,269,2018,29,'1'),(63,'High level scope of work due to market conditions',1,40,5,45,'Outstation',0,45,270,2018,29,'1'),(64,'high',1,80,5,85,'Inhouse and Outstation',4,117,271,2018,29,'1'),(65,'abc',1,40,2,42,'Inhouse and Outstation',3,66,272,2018,29,'1'),(66,'abc',1,40,2,42,'Inhouse and Outstation',2,58,273,2018,29,'1'),(67,'123',1,40,3,43,'Inhouse',0,43,275,2018,29,'1'),(68,'456',1,40,2,42,'Inhouse',0,42,276,2018,29,'1'),(69,'scope',1,0,23,23,'Outstation',0,23,277,2018,29,'1'),(70,'scope',1,0,23,23,'Outstation',0,23,277,2018,29,'1'),(71,'scope',1,0,23,23,'Outstation',0,23,277,2018,29,'1'),(72,'scope',1,0,23,23,'Outstation',0,23,277,2018,29,'1'),(73,'level',1,120,1,121,'Outstation',0,121,280,2018,29,'1'),(74,'hie l',1,80,0,80,'Outstation',0,80,281,2018,29,'1'),(75,'high',1,40,1,41,'Outstation',0,41,282,2018,29,'1'),(76,'scope',1,40,1,41,'Outstation',0,41,283,2018,29,'1'),(77,'high',1,40,2,42,'Outstation',2,58,285,2018,29,'1'),(78,'level Z',1,40,1,41,'Inhouse and Outstation',0,41,287,2018,29,'1'),(79,'abc',1,40,2,42,'Outstation',2,58,288,2018,29,'1'),(80,'sc',1,40,22,62,'Outstation',0,62,290,2018,29,'1'),(81,'1',1,0,1,1,'Outstation',0,1,291,2018,29,'1'),(82,'s',2,80,1,81,'Outstation',0,81,292,2018,29,'1'),(83,'as',1,40,1,41,'Outstation',0,41,293,2018,29,'1'),(84,'scope level',1,80,2,82,'Outstation',0,82,294,2018,29,'1'),(85,'',1,40,1,41,'Outstation',0,41,296,2018,29,'1'),(86,'',1,40,1,41,'Outstation',0,41,299,2018,29,'1'),(87,'Market skills and expertise are required to predict different market conditions.',1,120,2,122,'Outstation',2,138,295,2018,31,'0');
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
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `objectivejobrelation`
--

LOCK TABLES `objectivejobrelation` WRITE;
/*!40000 ALTER TABLE `objectivejobrelation` DISABLE KEYS */;
INSERT INTO `objectivejobrelation` VALUES (31,74,1,4,'2018-10-17 23:36:03'),(32,76,17,4,'2018-10-22 12:48:35'),(33,78,1,4,'2018-10-24 10:48:07'),(34,78,2,4,'2018-10-24 10:48:07'),(35,79,1,4,'2018-10-25 12:37:39'),(36,80,1,4,'2018-10-25 12:59:33'),(37,81,18,3,'2018-10-31 14:54:45'),(38,81,19,3,'2018-10-31 14:54:45'),(39,81,20,4,'2018-10-31 14:55:18'),(40,81,21,4,'2018-10-31 14:55:19'),(41,81,22,4,'2018-10-31 14:55:19'),(42,81,23,4,'2018-10-31 14:55:22'),(43,81,24,4,'2018-10-31 14:55:23'),(44,81,25,4,'2018-10-31 14:55:23'),(45,81,26,4,'2018-10-31 14:55:24'),(46,81,27,4,'2018-10-31 14:55:24'),(47,81,28,4,'2018-10-31 14:55:24'),(48,81,29,3,'2018-10-31 14:55:25'),(49,81,30,3,'2018-10-31 14:55:25'),(50,81,31,3,'2018-10-31 14:55:25'),(51,82,17,3,'2018-11-08 12:03:06'),(52,82,32,4,'2018-11-08 12:03:01'),(53,82,33,3,'2018-11-08 12:03:06'),(54,83,34,3,'2018-11-11 12:59:02'),(55,83,35,4,'2018-11-11 12:59:17'),(56,83,36,4,'2018-11-11 12:59:30'),(57,83,37,3,'2018-11-11 12:59:33'),(58,84,1,3,'2018-11-11 15:35:42'),(59,77,32,4,'2018-11-12 12:15:47'),(60,85,2,4,'2018-11-12 14:41:24'),(61,86,32,4,'2018-11-13 13:20:25'),(62,87,1,4,'2018-11-13 14:47:38'),(63,88,17,4,'2018-11-13 16:26:37'),(64,89,1,4,'2018-11-13 16:51:15'),(65,90,8,4,'2018-12-13 22:31:49'),(66,91,9,4,'2018-12-13 22:58:43'),(67,92,95,3,'2018-12-14 22:41:30'),(68,92,96,4,'2018-12-14 22:41:33'),(69,93,93,4,'2018-12-17 13:00:53'),(70,94,9,4,'2018-12-17 13:13:50');
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
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resourceuse`
--

LOCK TABLES `resourceuse` WRITE;
/*!40000 ALTER TABLE `resourceuse` DISABLE KEYS */;
INSERT INTO `resourceuse` VALUES (172,1,0,269,2018,29),(173,2,1,269,2018,29),(174,3,0,269,2018,29),(175,1,0,270,2018,29),(176,2,1,270,2018,29),(177,3,0,270,2018,29),(178,1,1,271,2018,29),(179,2,1,271,2018,29),(180,3,0,271,2018,29),(181,1,0,272,2018,29),(182,2,1,272,2018,29),(183,3,0,272,2018,29),(184,1,0,273,2018,29),(185,2,1,273,2018,29),(186,3,0,273,2018,29),(187,1,0,275,2018,29),(188,2,1,275,2018,29),(189,3,0,275,2018,29),(190,1,0,276,2018,29),(191,2,1,276,2018,29),(192,3,0,276,2018,29),(193,1,0,277,2018,29),(194,2,0,277,2018,29),(195,3,0,277,2018,29),(196,1,1,280,2018,29),(197,2,1,280,2018,29),(198,3,1,280,2018,29),(199,1,1,281,2018,29),(200,2,1,281,2018,29),(201,3,0,281,2018,29),(202,1,0,282,2018,29),(203,2,1,282,2018,29),(204,3,0,282,2018,29),(205,1,0,283,2018,29),(206,2,1,283,2018,29),(207,3,0,283,2018,29),(208,1,0,285,2018,29),(209,2,1,285,2018,29),(210,3,0,285,2018,29),(211,1,0,287,2018,29),(212,2,1,287,2018,29),(213,3,0,287,2018,29),(214,1,0,288,2018,29),(215,2,1,288,2018,29),(216,3,0,288,2018,29),(217,1,1,290,2018,29),(218,2,0,290,2018,29),(219,3,0,290,2018,29),(220,1,0,291,2018,29),(221,2,0,291,2018,29),(222,3,0,291,2018,29),(223,1,0,292,2018,29),(224,2,1,292,2018,29),(225,3,0,292,2018,29),(226,1,0,293,2018,29),(227,2,1,293,2018,29),(228,3,0,293,2018,29),(229,1,1,294,2018,29),(230,2,1,294,2018,29),(231,3,1,294,2018,29),(232,1,0,296,2018,29),(233,2,1,296,2018,29),(234,3,0,296,2018,29),(235,1,0,299,2018,29),(236,2,1,299,2018,29),(237,3,0,299,2018,29),(238,1,0,295,2018,31),(239,2,3,295,2018,31),(240,3,1,295,2018,31);
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
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `riskcontrolmatrix`
--

LOCK TABLES `riskcontrolmatrix` WRITE;
/*!40000 ALTER TABLE `riskcontrolmatrix` DISABLE KEYS */;
INSERT INTO `riskcontrolmatrix` VALUES (76,NULL,NULL,129,2018,29,1,116,116,'',1,'2018-10-18 00:27:45'),(77,NULL,NULL,131,2018,29,1,116,116,'',31,'2018-10-23 09:28:53'),(78,NULL,NULL,145,2018,29,1,116,116,'',1,'2018-10-24 10:49:42'),(79,NULL,NULL,145,2018,29,1,116,116,'',2,'2018-10-24 10:49:42'),(80,NULL,NULL,152,2018,29,1,116,116,'',32,'2018-10-25 12:39:07'),(81,NULL,NULL,153,2018,29,1,117,116,'',1,'2018-10-25 13:01:14'),(82,NULL,NULL,154,2018,29,1,116,116,'',33,'2018-10-31 15:07:34'),(83,NULL,NULL,155,2018,29,1,116,116,'',34,'2018-11-08 12:06:03'),(84,NULL,NULL,156,2018,29,4,116,0,NULL,35,'2018-11-11 13:03:18'),(85,NULL,NULL,157,2018,29,4,116,0,NULL,1,'2018-11-11 15:36:31'),(86,NULL,NULL,142,2018,29,4,116,0,NULL,36,'2018-11-12 12:17:20'),(87,NULL,NULL,158,2018,29,4,116,0,NULL,37,'2018-11-12 14:46:14'),(88,NULL,NULL,159,2018,29,4,116,0,NULL,38,'2018-11-13 13:21:36'),(89,NULL,NULL,160,2018,29,1,116,116,'',39,'2018-11-13 14:49:15'),(90,NULL,NULL,161,2018,29,4,116,0,NULL,40,'2018-11-13 16:27:31'),(91,NULL,NULL,162,2018,29,1,116,116,'',41,'2018-11-13 16:53:01'),(92,NULL,NULL,164,2018,29,4,116,0,NULL,52,'2018-12-13 22:43:44'),(93,NULL,NULL,165,2018,29,4,116,0,NULL,55,'2018-12-13 22:59:43'),(94,NULL,NULL,166,2018,29,4,116,0,NULL,319,'2018-12-14 22:43:25'),(95,NULL,NULL,167,2018,29,4,116,0,NULL,308,'2018-12-17 13:02:12'),(96,NULL,NULL,168,2018,29,4,116,0,NULL,56,'2018-12-17 13:15:10');
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
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `riskjobrelation`
--

LOCK TABLES `riskjobrelation` WRITE;
/*!40000 ALTER TABLE `riskjobrelation` DISABLE KEYS */;
INSERT INTO `riskjobrelation` VALUES (12,74,1,'1',4,'2018-10-17 23:39:00'),(14,76,25,'1',4,'2018-10-22 12:48:23'),(15,76,26,'1',4,'2018-10-22 12:48:41'),(16,78,1,'1',4,'2018-10-24 10:48:50'),(17,79,1,'1',4,'2018-10-25 12:38:06'),(18,80,1,'1',4,'2018-10-25 12:59:59'),(20,81,28,'0',4,'2018-10-31 15:02:33'),(22,82,24,'2',3,'2018-11-08 12:04:37'),(23,83,30,'1',3,'2018-11-11 13:00:05'),(24,83,31,'1',4,'2018-11-11 13:00:10'),(25,83,33,'1',4,'2018-11-11 13:00:10'),(26,83,32,'1',4,'2018-11-11 13:00:10'),(27,83,34,'1',3,'2018-11-11 13:01:16'),(28,83,35,'1',4,'2018-11-11 13:01:18'),(29,84,1,'1',4,'2018-11-11 15:36:06'),(30,77,36,'0',4,'2018-11-12 12:16:38'),(31,77,37,'0',4,'2018-11-12 12:16:42'),(32,85,4,'2',4,'2018-11-12 14:41:55'),(33,86,38,'0',4,'2018-11-13 13:21:05'),(34,87,1,'1',4,'2018-11-13 14:48:09'),(35,88,39,'0',4,'2018-11-13 16:27:05'),(36,89,1,'1',4,'2018-11-13 16:51:37'),(37,90,47,'0',4,'2018-12-13 22:39:53'),(38,91,48,'0',4,'2018-12-13 22:59:13'),(39,92,225,'0',3,'2018-12-14 22:42:16'),(40,92,226,'0',3,'2018-12-14 22:42:35'),(41,92,227,'0',4,'2018-12-14 22:42:37'),(42,93,215,'0',4,'2018-12-17 13:01:34'),(43,94,48,'0',4,'2018-12-17 13:14:17');
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
) ENGINE=InnoDB AUTO_INCREMENT=228 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `riskobjective`
--

LOCK TABLES `riskobjective` WRITE;
/*!40000 ALTER TABLE `riskobjective` DISABLE KEYS */;
INSERT INTO `riskobjective` VALUES (1,'Competitive bidding process not undertaken resulting in : receiving substandard or incorrect supplies and / or services, paying too much for those supplies or services, fraud, delivery and / or production delaysss.',1,0,'R-P2P-C-1','1',NULL),(2,'Functional review (Finance & Legal review) not undertaken resulting in: terms and conditions not understood by either party to the agreement; no dispute resolution process; additional liabilities to company ; foreign currency exposure not considered, unreasonable payment terms.',1,0,'R-P2P-C-2','2',NULL),(3,'Embedded derivatives are not identified and reported when new contracts are entered into.',1,1,'R-P2P-C-3','0',NULL),(4,'(Services / goods received are not in accordance with the contract, resulting in: terms and conditions  not being met, contract scope not met / completed, disputes arise, variations not approved, additional liabilities aris',2,0,'R-P2P-C-4','2',NULL),(5,'(A) Orders are placed outside the system without an approved purchase requisition being raised   (eg. Direct orders, phone orders), resulting in loss of company assets',3,0,'R-P2P-C-5','',NULL),(6,'(B) Actual maintenance work performed exceeds the planned authorised work order amount, resulting in loss of company assets.',3,0,'R-P2P-C-6','',NULL),(7,'(c)  Duplicate, fictitious, or non-business related purchase orders are executed (either within or outside the system) resulting in loss of company assets and / or non-recording of creditors, commitments, operating costs.',3,0,'R-P2P-C-7','',NULL),(8,'D)Pricing, terms and conditions agreed to are not authorised and are onerous or detrimental to the business.',3,0,'R-P2P-C-8','',NULL),(9,'(F) Information on valid orders is incomplete or inaccurate in the system resulting in incorrect accounting allocations or valuations, and / or incorrect payments.',4,0,'R-P2P-C-9','',NULL),(10,'(G) Expenditures not classified (coded) in accordance with company policy resulting in incorrect expenditure allocations.',4,0,'R-P2P-C-10','',NULL),(11,'(H) Allocation of wrong vendor to purchase order, resulting in possible purchase from wrong vendor and a potential loss of company assets.',4,0,'R-P2P-C-11','',NULL),(12,'Goods received are not recorded on a timely basis (e.g. goods are delivered directly to requestor, goods remain in warehouse for long periods before being receipted in system).',5,0,'R-P2P-C-12','',NULL),(13,'Invoices received are not recorded on a timely basis resulting in misstatement of liabilities at period-end.',5,0,'R-P2P-C-13','',NULL),(14,'Goods received do not match price or quantity on purchase order but go undetected or unrecorded.',6,0,'R-P2P-C-14','',NULL),(15,'Goods are damaged when received but go undetected.',6,0,'R-P2P-C-15','',NULL),(16,'Goods and services are receipted into the system by unauthorised personnel',7,0,'R-P2P-C-16','',NULL),(17,'Unordered goods are receipted.',7,0,'R-P2P-C-17','',NULL),(18,'Ordered goods are receipted to an incorrect order',7,0,'R-P2P-C-18','',NULL),(47,'Fraudulent  or inappropriate payments made as a result of: payment for goods or services never / not yet received; payment of incorrect value, payment for something that is not a valid expense of the company)',8,0,'R-P2P-C-19','0',NULL),(48,'Payments may not be appropriately authorized due to lack of awareness,errors or due to mal intetntions',9,0,'R-P2P-C-20','0',NULL),(49,'Payment is made to incorrect bank account arising from changes made during the EFT process /manual process',10,0,'R-P2P-C-21','',NULL),(50,'Duplicate payments are made to the same supplier',10,0,'R-P2P-C-22','',NULL),(51,'Suppliers are over / under paid ',10,0,'R-P2P-C-23','',NULL),(52,'Unauthorised / fictitious vendors are included in the masterfile for fraudulent purposes.',11,0,'R-P2P-C-24','',NULL),(53,'Master file data records are incorrectly deleted/amended',12,0,'R-P2P-C-25','',NULL),(54,'Records are not entered correctly due to error/intentionally',12,0,'R-P2P-C-26','',NULL),(55,'Positions are filled without an approved authority for own self interest/expediting the hiring process resulting in loss in company assets.',13,0,'R-H2T-PP-1','',NULL),(56,'Information on employees is incomplete or inaccurate in the system (I.e. letter of offer does not reflect system data, bank details not accurate, leave allowances incorrect) due to errors or mal intentions',14,0,'R-H2T-PP-2','',NULL),(57,'Changes to standing data (I.e. bank account, etc) result in payment to the wrong person.',14,0,'R-H2T-PP-3','',NULL),(58,'(A) Access to payment system (I.e. online banking) is not restricted resulting in unauthorised payments.',15,0,'R-H2T-PD-1','',NULL),(59,'(B) Duplicate payments are made to the same employee resulting in loss of company assets',16,0,'R-H2T-PD-2','',NULL),(60,'(C) Payroll system incorrectly calculates pay run resulting in under / over payment.',16,0,'R-H2T-PD-3','',NULL),(61,'(D) Rejected items not identified resulting in under payment.',16,0,'R-H2T-PD-4','',NULL),(62,'(E) Employee deductions (e.g. health insurance, child support) are not appropriately authorised, recorded and executed.',16,0,'R-H2T-PD-5','',NULL),(63,'(F) Employees are not paid on a timely basis resulting in industrial disputes.',16,0,'R-H2T-PD-6','',NULL),(64,'(G) Payroll disbursement file is corrupted or interfered with prior to loading into the banking system.',16,0,'R-H2T-PD-7','',NULL),(65,'(H) Incorrect calculation of taxes (I.e. PAYG, payroll tax, superannuation) resulting in breach of legislation and / or penalties.',17,0,'R-H2T-PD-8','',NULL),(66,'(I) Authorities (third parties) are under / over paid, or not paid at all.',18,0,'R-H2T-PD-9','',NULL),(67,'(J) Authorities (third parties) are not paid on a timely basis resulting in penalties.',18,0,'R-H2T-PD-10','',NULL),(68,'(K) Workers compensation claim payments do not reflect approved claim liability.',18,0,'R-H2T-PD-11','',NULL),(69,'Fictitious time and attendance information is entered into the system.',19,0,'R-H2T-PT-1','',NULL),(70,'(B) Inaccurate time and attendance information is entered into the system.',19,0,'R-H2T-PT-2','',NULL),(71,'(C) Change in pay rates are unauthorised resulting in loss of company assets',20,0,'R-H2T-PT-3','',NULL),(72,'(D) Roster / shift changes are not accurately processed in the system resulting in under / over payment.',20,0,'R-H2T-PT-4','',NULL),(73,'(E) Pay rate changes (I.e. salary review, EBA changes, ad hoc changes) are not accurately processed in the system resulting in under / over payment.',20,0,'R-H2T-PT-5','',NULL),(74,'Commitments to acquire fixed assets are entered into prior to, or without, appropriate approval resulting in loss of company assets (i.e. cash)\r\n\r\n',21,0,'R-FA-A-1','',NULL),(75,'Asset can be acquired for more then its allocated budget',21,0,'R-FA-A-2','',NULL),(76,'Expenditure is allocated to the incorrect capital item.',22,0,'R-FA-A-3','',NULL),(77,'Expenditure incurred no longer qualifies as an asset (e.g. asset abandoned, net realisable value concerns)',22,0,'R-FA-A-4','',NULL),(78,'Expenditure is classified as capital instead of operating.',22,0,'R-FA-A-5','',NULL),(79,'Asset additions have been classified into the incorrect asset categories (I.e. land & buildings, PPE, EE&D)',23,0,'R-FA-A-6','',NULL),(80,'Fixed asset costs are not reflected in the fixed asset register (including costs supplementary to the purchase price, e.g. costs of installation and preparatory costs)',24,0,'R-FA-A-7','',NULL),(81,'Assets classified as held for sale (and not in use) are not recognised as such and incorrectly continue to be depreciated',26,0,'R-FA-D-2','',NULL),(82,'Assets temporarily withdrawn from use cease to have depreciation charged ',26,0,'R-FA-D-3','',NULL),(83,'Estimated useful lives are not properly determined on inception or reviewed in subsequent financial periods.',27,0,'R-FA-D-4','',NULL),(84,'Changes in estimated economic life incorrectly give rise to a retrospective depreciation charge (I.e. back-dated depreciation), rather than being included in depreciation charges on a prospective basis',27,0,'R-FA-D-5','',NULL),(85,'Material components of depreciable assets with different lives are not separately identified ',27,0,'R-FA-D-6','',NULL),(86,'Depreciation calculation run is initiated prior to asset register reaching the required state of readiness ',28,0,'R-FA-D-7','',NULL),(87,'Depreciation run is not completed and/or result is not booked into the general ledger',28,0,'R-FA-D-8','',NULL),(88,'Estimated residual values of depreciable assets are not properly determined or reviewed in subsequent financial periods ',29,0,'R-FA-D-9','',NULL),(89,'Depreciation calculation logic in FAR is flawed ',29,0,'R-FA-D-10','',NULL),(90,'Impairment charges are not separately reported from regular depreciation charges ',30,0,'R-FA-D-11','',NULL),(91,'Incorrect general ledger account assignment',30,0,'R-FA-D-12','',NULL),(92,'The asset register is not closed out on a timely basis resulting in incorrect asset reporting at year-end.',31,0,'R-FA-D-13','',NULL),(93,'Assets in use are not being depreciated ',25,0,'R-FA-D-1','',NULL),(94,'Depreciation rates are incorrectly modified or modified without authorisation',27,0,'R-FA-D-14','',NULL),(95,'Assets remain on fixed asset register subsequent to disposal and continue to be depreciated.',32,0,'R-FA-DR-1','',NULL),(96,'Proceeds from sale are not recorded in fixed asset register at total consideration received / receivable ',32,0,'R-FA-DR-2','',NULL),(97,'Assets are physically disposed  (from the asset register and / or physical disposal) without proper authorisation resulting in loss of company assets.',33,0,'R-FA-DR-3','',NULL),(98,'Assets are disposed for less than fair value resulting in a loss of company assets.',34,0,'R-FA-DR-4','',NULL),(99,'Proceeds on disposal are not received.',34,0,'R-FA-DR-5','',NULL),(100,' Inadequate information captured in the asset register to enable adequate matching of sale proceeds to assets.',35,0,'R-FA-DR-6','',NULL),(101,'Gains / losses on disposal are not separately identified for disclosure',32,0,'R-FA-DR-7','',NULL),(102,'Modification to reserve tracking data may be wrong',36,0,'R-FA-R-1','',NULL),(103,'Lack of completeness of disclosure being made.',36,0,'R-FA-R-2','',NULL),(104,'Lack of accuracy and reasonableness of estimates used to drive reserve values.',36,0,'R-FA-R-3','',NULL),(105,'Formal approval of reconciliation between ERP and ER',37,0,'R-R2P-YC-1','',NULL),(106,'Close calendar is prepared, finalized, schedules finalized and reviewed.\r\n',38,0,'R-R2P-YC-2','',NULL),(107,'CFO  formally approves the annual financial statements prepared as a result of the financial closing process \r\n',38,0,'R-R2P-YC-3','',NULL),(108,'Prepayments are charged by AP on receipt of invoice from the supplier',38,0,'R-R2P-YC-4','',NULL),(109,'Unauthorised changes to the use of existing general ledger accounts results in inappropriate use of general ledger accounts.',39,0,'R-R2P-GL-1','',NULL),(110,'New accounts are set-up incorrectly (I.e. requests not completely / accurately processed in system master data) and go undetected.',39,0,'R-R2P-GL-2','',NULL),(111,'New accounts are assigned to incorrect account groupings resulting in incorrect account allocations.',39,0,'R-R2P-GL-3','',NULL),(112,'Unauthorised changes to the use of existing general ledger accounts results in inappropriate use of general ledger accounts.',40,0,'R-R2P-GL-4','',NULL),(113,'General ledger master records are incomplete resulting in incomplete or inaccurate transaction processing',40,0,'R-R2P-GL-5','',NULL),(114,'General ledger master records don\'t reflect company chart of accounts resulting in inaccurate transaction postings.',40,0,'R-R2P-GL-6','',NULL),(115,'Unauthorised removal of general ledger account from general ledger master data results in inaccurate or incomplete account postings',41,0,'R-R2P-GL-7','',NULL),(116,'Authorised unused accounts are incorrectly removed from general ledger master data.',41,0,'R-R2P-GL-8','',NULL),(117,'Intercompany debtor and creditor balances not fully eliminated upon consolidation',42,0,'R-R2P-CMR-1','',NULL),(118,'Relevant changes to consolidation standing journals are not captured (e.g. failure to correctly account for changes to investment holdings, and newly acquired or disposed entities) resulting in Group Financial misstatements. ',42,0,'R-R2P-CMR-2','',NULL),(119,'Other group reclassifications are not performed upon consolidation resulting in incorrect disclosure',43,0,'R-R2P-CMR-3','',NULL),(120,'Reversing consolidation journals carry forward incorrectly.',43,0,'R-R2P-CMR-4','',NULL),(121,'Manual transfers of equity balances between entities does not eliminate upon consolidation.',43,0,'R-R2P-CMR-5','',NULL),(122,'Insufficient or incomplete documentation to support consolidation entries which may result in redundant or incorrect entries remaining in the system',43,0,'R-R2P-CMR-6','',NULL),(123,'Data uploaded into Hyperion contains errors (e.g. negative CAPEX balances)',44,0,'R-R2P-CMR-7','',NULL),(124,'Unauthorised (deliberate) changes to final consolidation data resulting in inaccurate information in group accounts.',45,0,'R-R2P-CMR-8','',NULL),(125,'All changes required to consolidated data are not or incorrectly captured by the ATOD process.',45,0,'R-R2P-CMR-9','',NULL),(126,'Unauthorised changes to prior period data upon consolidation.',45,0,'R-R2P-CMR-10','',NULL),(127,'Use of manual spreadsheets for financial reporting resulting in inaccurate / incomplete reporting.',45,0,'R-R2P-CMR-11','',NULL),(128,'Lack of review of group reconciliations of group accounts on daily basis by senior consolidation accountant\r\n\r\n',45,0,'R-R2P-CMR-12','',NULL),(129,'Lack of review and understanding of \'checker file\' which detects errors in source system data.',45,0,'R-R2P-CMR-13','',NULL),(130,'Lack of attention being paid to exceptions identified in the review of \'checker file\'',45,0,'R-R2P-CMR-14','',NULL),(131,'Risk of changes can be made to the system after the after the system is being locked after publishing of prior period accounts.',45,0,'R-R2P-CMR-15','',NULL),(132,'Lack of approval for changes to be made to prior period data',45,0,'R-R2P-CMR-16','',NULL),(133,'Risk of lack of integrity, completeness, accuracy of source data presented and maintained in spreadsheet',45,0,'R-R2P-CMR-17','',NULL),(134,'Lack of bi-annual review and sign-off by senior accountant and Group Accounting Management of supporting spreadsheets / workpapers used in the preparation of financial statements.  ',45,0,'R-R2P-CMR-18','',NULL),(135,'Risk of amounts in the reporting package are not cross-referenced.',46,0,'R-R2P-BUP-1','',NULL),(136,'Risk of incompleteness and quality of  list of standard reporting package activities',46,0,'R-R2P-BUP-2','',NULL),(137,'Risk of lack of manual comparison between the information in the reporting package and the source documents.',46,0,'R-R2P-BUP-3','',NULL),(138,'Risk of lack of understanding and identification of errors in manual comparison of items on a list of recurring reporting package adjustments to adjustments recorded.\r\nRisk of error and omission  in manual review of reporting package adjustments to ensure completeness and accuracy',46,0,'R-R2P-BUP-4','',NULL),(139,'Risk of error and omission  in manual review of reporting package adjustments to ensure completeness and accuracy',46,0,'R-R2P-BUP-5','',NULL),(140,'Risk of lack of business performance reviews are performed and risk of lack of unusual trends and variances being investigated and resolved on timely basis.',47,0,'R-R2P-BUP-6','',NULL),(141,'Risk of inaccuracy in reporting packages in spreadsheet \r\n\r\nRisk of lack of review of reporting packages prior to submission to the parent company\r\nRisk of inaccuracy in reporting packages in spreadsheet \r\n\r\nRisk of lack of review of reporting packages prior to submission to the parent company\r\n',47,0,'R-R2P-BUP-7','',NULL),(142,'Risk of system not being performing mathematical calculations accurately',48,0,'R-R2P-BUP-8','',NULL),(143,'Risk of inaccuracy in amounts transferred to the reporting package based on pre-defined account mapping\r\nRisk of inaccuracy in amounts transferred to the reporting package based on pre-defined account mapping\r\nRisk of inaccuracy in amounts transferred to the reporting package based on pre-defined account mapping\r\nRisk of inaccuracy in amounts transferred to the reporting package based on pre-defined account mapping\r\nRisk of inaccuracy in amounts transferred to the reporting package based on pre-defined account mapping\r\nRisk of inaccuracy in amounts transferred to the reporting package based on pre-defined account mapping\r\n',48,0,'R-R2P-BUP-9','',NULL),(144,'Unauthorised access to consolidations systems resulting in misuse of company information. ',49,0,'R-R2P-C-1','',NULL),(145,'Unauthorised changes to financial information (e.g. unauthorised changes to accounts).',49,0,'R-R2P-C-2','',NULL),(146,'Incorrect account logic results in misstatement of financial results.',50,0,'R-R2P-C-3','',NULL),(147,'Unauthorised changes to entity structures resulting in segment reporting misstatements.',51,0,'R-R2P-C-4','',NULL),(148,'Group entities are not consolidated.',51,0,'R-R2P-C-5','',NULL),(149,'Mismatch between the list of expected submissions and actual information received about related party transactions.\r\n',52,0,'R-R2P-FSP-1','',NULL),(150,'Amounts not being cross-referenced accurately in the financial statements\r\n\r\nRisk of duplication during cross referencing between amounts',52,0,'R-R2P-FSP-2','',NULL),(151,'Lack of business performance reviews being performed for financial statements',52,0,'R-R2P-FSP-3','',NULL),(152,'Financial statements are not being reviewed by management \r\n\r\nLack of understanding of management to review financial statements\r\n\r\nLack of Knowledge of updated reporting standard requirements by management.\r\n',52,0,'R-R2P-FSP-4','',NULL),(153,'Lack of comparison between the list of related party transactions that require reporting and the transactions received from key personnel',52,0,'R-R2P-FSP-5','',NULL),(154,'Lack of management review of  completed financial statements and disclosure checklist',53,0,'R-R2P-FSP-6','',NULL),(155,'Lack of manual comparison between disclosure checklists and disclosures',53,0,'R-R2P-FSP-7','',NULL),(156,'Lack of manual comparison between the financial statements / disclosures and relevant supporting documentation',53,0,'R-R2P-FSP-8','',NULL),(157,'Lack of understanding  and unauthorize approval of financial statements by non-finance department individuals ',53,0,'R-R2P-FSP-9','',NULL),(158,'Lack of review of financial statements and disclosures to ensure completeness and accuracy',53,0,'R-R2P-FSP-10','',NULL),(159,'Lack of review and accuracy of information obtained from reporting units for inclusion in the financial statements and disclosures',54,0,'R-R2P-FSP-11','',NULL),(160,'Lack of review of information obtained from reporting units to ensure completeness',54,0,'R-R2P-FSP-12','',NULL),(161,'Lack of accuracy of mathematical calculations in the financial statements and disclosures',54,0,'R-R2P-FSP-13','',NULL),(162,'Lack of related party transactions reported to the financial reporting department are being investigated and resolved',54,0,'R-R2P-FSP-14','',NULL),(163,'Lack of approval and review of all topside entries prior to processing',55,0,'R-R2P-FSP-15','',NULL),(164,'Management biasness for selective customers to provide provisions for bad debts to manipulate receivables to overstate sales linked to performance bonus, if any.',56,0,'O-O2C-APD-1','',NULL),(165,'Lack of segregation of duties between the person recording provision for bad debts and the person authorising and making provision for bad debts.',56,0,'O-O2C-APD-2','',NULL),(166,'Lack of approval for provision amounts and write-offs ',56,0,'O-O2C-APD-3','',NULL),(167,'No formal documented policy to assess and calculate the provision. Management may be using provisions to manipulate data.',57,0,'O-O2C-APD-4','',NULL),(168,'Provisions are not complete resulting in error and misstatement',58,0,'O-O2C-APD-5','',NULL),(169,'Error in the system of lockbox interface',59,0,'O-O2C-CR-1','',NULL),(170,'Mismatch between cash receipts and customer accounts/invoices',60,0,'O-O2C-CR-2','',NULL),(171,'Mismatch between cash receipts and deposits.\r\nCash receipts are not timely deposited into company\'s bank account',60,0,'O-O2C-CR-3','',NULL),(172,'Employee accepts the cash without recording the transaction. ',60,0,'O-O2C-CR-4','',NULL),(173,'Unauthorized discounts / rebate allowance being offered to the customers.',61,0,'O-O2C-CRD-1','',NULL),(174,'Credit memo is posted inaccurately.',62,0,'O-O2C-CRD-2','',NULL),(175,'Risk of errors in cut-off of recording of credit memos',63,0,'O-O2C-CRD-3','',NULL),(176,'Risk of inappropriate assumptions used in estimating the sale forecast.',64,0,'O-O2C-CRD-4','',NULL),(177,'Risk of lack of understanding and inappropriate analysis of rebates, discount etc\r\n',65,0,'O-O2C-CRD-5','',NULL),(178,'Sale return are not estimated and budgeted properly.',65,0,'O-O2C-CRD-6','',NULL),(179,'Risk of overriding return authorisation prior to issuance to provide undue advantage',65,0,'O-O2C-CRD-7','',NULL),(180,'Risk of system not being performing task and processing data accurately due to system bug',66,0,'O-O2C-CRD-8','',NULL),(181,'Risk of unauthorized and inappropriate adjustment in period end reserve',67,0,'O-O2C-CRD-9','',NULL),(182,'At period end , adjustments to revenue & Receivable accounts may be made without getting prior approval from competent authority.',68,0,'O-O2C-APC-1','',NULL),(183,'Adjustments to Revenue & Receivable account may be made in interim period without getting approval from competent level of authority.',69,0,'O-O2C-APC-2','',NULL),(184,'Inappropriate external data to be used in the comparison against recorded sales',70,0,'O-O2C-APC-3','',NULL),(185,'Adjustments may be made to fake and illegitimate customer accounts in order to facilitate misappropriation of receivables. ',71,0,'O-O2C-APC-4','',NULL),(186,'Lack of analysis on timely basis and lack of identification and appropriate adjustments\r\nUnusual trend and variance may not be identified and resolved leading to inappropriate reporting of financial results.',72,0,'O-O2C-APC-5','',NULL),(187,'Unusual trend and variance may not be identified and resolved leading to inappropriate reporting of financial results.',73,0,'O-O2C-APC-6','',NULL),(188,'Un-authorized changes to or disclosure of customer data may be made leading to legal repercussions against the entity.',74,0,'O-O2C-APC-7','',NULL),(189,'Non-compliance with laws and regulations related to customers data leading to fines and penalties upon the entity.',74,0,'O-O2C-APC-8','',NULL),(190,'Revenue from long term contracts may be recognized on inappropriate basis leading to misrepresentation of financial results of the entity.',75,0,'O-O2C-APC-9','',NULL),(191,'Unauthorized transactions may be carried out when relevant staff is un-informed.\r\n',76,0,'O-O2C-PWC-1','',NULL),(192,'Lack of segregation of duties leading to non-identification of erroneous and fraudulent transactions.\r\n\r\n',76,0,'O-O2C-PWC-2','',NULL),(193,'Inefficient and non-effective third party’s operations resulting into poor quality delivery and hence non-satisfaction of customers',77,0,'O-O2C-PWC-3','',NULL),(194,'Unauthorized changes to system or data may be made leading to erroneous reporting of financial results.\r\n\r\n',78,0,'O-O2C-PWC-4','',NULL),(195,'Unauthorized changes to financial data may be made resulting into wrong decision making by management.\r\n',78,0,'O-O2C-PWC-5','',NULL),(196,'Risk of inaccurate record and documents.\r\n',79,0,'O-O2C-PWC-6','',NULL),(197,'Lack of knowledge by staff responsible for recording and/ or maintenance of relevant information.\r\n',79,0,'O-O2C-PWC-7','',NULL),(198,'Risk of ambiguous and fake recording in revenue and accounts receivable accounts.',80,0,'O-O2C-PWC-8','',NULL),(199,'Non-satisfaction of customers leading to deterioration of business reputation and entity’s profits',81,0,'O-O2C-PWC-9','',NULL),(200,'Risk of inaccurate prices used as sales price due to human error or deliberately',82,0,'O-O2C-OP-1','',NULL),(201,'Risk of large number of sales order exceeding the quantity threshold',83,0,'O-O2C-OP-2','',NULL),(202,'Risk of orders levels exceeding credit limits',84,0,'O-O2C-OP-3','',NULL),(203,'Risk of outdated list of contracts',85,0,'O-O2C-OP-4','',NULL),(204,'Risk of overriding terms and conditions used in the contract',86,0,'O-O2C-OP-5','',NULL),(205,'Risk of prices used are not according to rates specified in the master file',87,0,'O-O2C-OP-6','',NULL),(206,'Risk of self review and unauthorized approval of sales order',88,0,'O-O2C-OP-7','',NULL),(207,'Risk of unauthorize approval ',89,0,'O-O2C-OP-8','',NULL),(208,'Risk of unauthorize approval ',90,0,'O-O2C-OP-9','',NULL),(209,'Duplicate customers opened in system',90,0,'O-O2C-OP-10','',NULL),(210,'Outstanding orders appearing in system',90,0,'O-O2C-OP-11','',NULL),(211,'Orders processed but stock not available for despatch resulting in delay',90,0,'O-O2C-OP-12','',NULL),(212,'Risk of difference between invoices generated and total shipments\r\n\r\nRisk of long outstanding shipments pending',91,0,'O-O2C-I-1','',NULL),(213,'Risk of error and omission in manual comparison between period-end invoices and shipment logs to ensure proper cut-off',92,0,'O-O2C-I-2','',NULL),(214,'Risk of inaccuracy in the automatic calculations made by system for invoices\r\n',93,0,'O-O2C-I-3','',NULL),(215,'Risk of inaccurate prices input in system',93,0,'O-O2C-I-4','0',NULL),(216,'Risk of invoices not being generated automatically on shipment',94,0,'O-O2C-I-5','',NULL),(217,'Risk of invoices not being sequentially numbered',94,0,'O-O2C-I-6','',NULL),(218,'Risk of lack of procedure being followed for long term contracts to construct significant assets',94,0,'O-O2C-I-7','',NULL),(219,'Risk of lack of procedure being followed in bill and hold arrangements',94,0,'O-O2C-I-8','',NULL),(220,'Risk of lack of review of exceptional report of invoices not shipped',94,0,'O-O2C-I-9','',NULL),(221,'Risk of lack of review of exceptional report of unusual invoices',94,0,'O-O2C-I-10','',NULL),(222,'Risk of system not preventing duplicate invoices from being generated',94,0,'O-O2C-I-11','',NULL),(223,'Risk of system not taking the products price appropriately',94,0,'O-O2C-I-12','',NULL),(224,'Risk of Invoice reversals and adjustments\r\n\r\nRisk of frequent invoice reversals',94,0,'O-O2C-I-13','',NULL),(225,'',95,0,'101120','0',NULL),(226,'risksome',95,0,'120106','0',NULL),(227,'risksome',95,0,'120106','0',NULL);
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
INSERT INTO `rolls` VALUES (1,'Head of Internal Audit'),(2,'Team Lead'),(3,'Auditor '),(4,'Admin'),(5,'Management');
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
) ENGINE=InnoDB AUTO_INCREMENT=302 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strategic`
--

LOCK TABLES `strategic` WRITE;
/*!40000 ALTER TABLE `strategic` DISABLE KEYS */;
INSERT INTO `strategic` VALUES (269,'First Objective',0,NULL,1,'Medium','First Unit',116,116,'initiated','OK',116,'2018-11-13 02:29:55',1,5,0,2018,1,29,'Low',1,5,1),(270,'Market Diversification',0,NULL,1,'High','Sales',116,116,'initiated','',116,'2018-11-11 09:02:46',1,5,0,2018,1,29,'High',1,5,1),(271,'Inventory Management',0,NULL,1,'Medium','Inventory',116,116,'initiated','',116,'2018-11-13 02:38:39',1,5,0,2018,1,29,'Medium',1,5,1),(272,'Second Objective',0,NULL,1,'Medium','Second Unit',116,116,'initiated','',116,'2018-11-13 02:38:39',1,5,0,2018,1,29,'Medium',1,5,1),(273,'Third Objective',0,NULL,1,'High','Third Unit',116,116,'initiated','',116,'2018-11-13 02:38:39',1,5,0,2018,1,29,'High',1,5,1),(274,'Fourth Objective',0,NULL,1,'Low',NULL,116,116,'deleted',NULL,0,'2018-10-25 07:33:12',0,1,0,2018,0,29,NULL,1,5,1),(275,'Fourth Objective',0,NULL,1,'High','Fourth Unit',116,116,'initiated','',116,'2018-11-11 09:02:47',1,5,0,2018,1,29,'High',1,5,1),(276,'Fifth Objective',0,NULL,1,'Medium','Fifth Unit',117,117,'initiated','',116,'2018-11-13 02:38:39',1,5,0,2018,1,29,'High',1,5,1),(277,'engagement View corrrecton',0,NULL,1,'Medium','engViewCorrec',116,116,'initiated','c',116,'2018-11-13 02:38:40',1,5,0,2018,1,29,'N/A',1,5,1),(278,'ReportingCheck',0,NULL,1,'Low','reportingcheck',116,116,'initiated','check reporting',116,'2018-11-13 02:38:40',1,5,0,2018,1,29,'N/A',1,5,1),(279,'Rc1',0,NULL,1,'Low',NULL,116,116,'deleted',NULL,0,'2018-11-11 07:10:56',0,1,0,2018,0,29,NULL,1,5,1),(280,'Rc',0,NULL,1,'High','RcUnit',116,116,'initiated','comments',116,'2018-11-13 02:38:40',1,5,0,2018,1,29,'Medium',1,5,1),(281,'Rc1',0,NULL,1,'Low','RC1Unit',116,116,'initiated','OK',116,'2018-11-13 02:38:40',1,5,0,2018,1,29,'N/A',1,5,1),(282,'reosurce objective',0,NULL,1,'Medium','resource auditableunit',116,116,'initiated','resource comment',116,'2018-11-12 09:30:42',1,5,0,2018,1,29,'High',1,2,1),(283,'reportingcheckfinal',0,NULL,1,'High','reportingfinalunit',116,116,'initiated','',116,'2018-11-13 08:18:40',1,5,0,2018,1,29,'High',1,5,1),(284,'abilite new1',0,NULL,1,'Low',NULL,116,116,'deleted',NULL,0,'2018-11-13 09:43:11',0,1,0,2018,0,29,NULL,1,5,1),(285,'abilite new1',0,NULL,1,'High','new1',116,116,'initiated','',116,'2018-11-13 09:44:56',1,5,0,2018,1,29,'High',1,2,1),(286,'Z Report',0,NULL,1,'Low',NULL,116,116,'deleted',NULL,0,'2018-11-13 11:23:11',0,1,0,2018,0,29,NULL,1,5,1),(287,'Z Report',0,NULL,1,'High','z unitR',116,116,'initiated','',116,'2018-11-13 11:24:22',1,5,0,2018,1,29,'Medium',1,5,1),(288,'abilite new2',0,NULL,1,'High','new2',116,116,'initiated','',116,'2018-11-13 11:48:56',1,5,0,2018,1,29,'High',1,2,1),(289,'Reporting test',0,NULL,1,'Low',NULL,118,118,'saved',NULL,0,'2018-11-27 11:00:45',0,1,0,2018,0,29,NULL,1,5,1),(290,'abilite check',0,NULL,1,'Low','abiluite check',116,116,'initiated','',116,'2018-12-13 17:22:13',1,5,0,2018,1,29,'Medium',1,5,1),(291,'abilitecheck2',0,NULL,1,'Low','abilitecheck2',116,116,'initiated','',116,'2018-12-13 17:52:41',1,5,0,2018,1,29,'N/A',1,5,1),(292,'acheck2',0,NULL,1,'Low','abilitechck3',116,116,'initiated','',116,'2018-12-13 17:55:50',1,5,0,2018,1,29,'N/A',1,5,1),(293,'REportCheckingExc',0,NULL,1,'Low','reportcheckexc',116,116,'initiated','',116,'2018-12-14 17:32:54',1,5,0,2018,1,29,'N/A',2,7,1),(294,'jobcheckingobjective',0,NULL,1,'Low','processcheckingunit',116,116,'initiated','',116,'2018-12-17 07:07:54',1,5,0,2018,1,29,'N/A',2,9,2),(295,'Market Diversification',0,NULL,1,'Medium','Sales',128,128,'initiated','',127,'2018-12-17 07:50:57',1,5,0,2018,1,31,'High',1,2,1),(296,'Resourcechecking',0,NULL,1,'Low','resourcechecking',116,116,'initiated','',116,'2018-12-17 07:56:31',1,5,0,2018,1,29,'N/A',2,9,1),(297,'xyz',0,NULL,1,'Low',NULL,116,116,'deleted',NULL,0,'2018-12-17 08:07:42',0,1,0,2018,0,29,NULL,1,5,1),(298,'xyz',0,NULL,1,'Low',NULL,116,116,'deleted',NULL,0,'2018-12-17 08:07:43',0,1,0,2018,0,29,NULL,1,5,1),(299,'xyz',0,NULL,1,'Low','xyz unit',116,116,'initiated','',116,'2018-12-17 08:09:37',1,5,0,2018,1,29,'N/A',1,5,1),(300,'testing',0,NULL,1,'Low',NULL,116,116,'initiated',NULL,116,'2018-12-17 10:28:57',0,3,0,2018,0,29,'N/A',1,5,1),(301,'ovbj',0,NULL,1,'Low',NULL,116,116,'initiated',NULL,116,'2018-12-17 10:43:55',0,2,1,2018,0,29,NULL,1,5,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=394 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strategicdepartments`
--

LOCK TABLES `strategicdepartments` WRITE;
/*!40000 ALTER TABLE `strategicdepartments` DISABLE KEYS */;
INSERT INTO `strategicdepartments` VALUES (269,'2',342),(270,'2',343),(271,'2',344),(271,'1',345),(272,'2',346),(273,'2',347),(275,'2',348),(274,'2',349),(276,'2',350),(277,'0',351),(278,'2',352),(279,'0',353),(280,'2',354),(281,'2',355),(282,'2',356),(283,'1',357),(283,'2',358),(283,'3',359),(284,'0',360),(285,'2',361),(286,'1',362),(286,'2',363),(287,'1',364),(287,'2',365),(288,'2',366),(289,'0',367),(290,'1',368),(291,'2',369),(292,'1',370),(292,'2',371),(292,'3',372),(293,'1',373),(293,'2',374),(293,'3',375),(294,'0',376),(294,'1',377),(294,'3',378),(295,'2',379),(296,'1',380),(296,'2',381),(296,'3',382),(297,'1',383),(297,'2',384),(297,'3',385),(298,'1',386),(298,'2',387),(299,'1',388),(298,'3',389),(299,'2',390),(299,'3',391),(300,'2',392),(301,'2',393);
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
) ENGINE=InnoDB AUTO_INCREMENT=817 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strategicrisk`
--

LOCK TABLES `strategicrisk` WRITE;
/*!40000 ALTER TABLE `strategicrisk` DISABLE KEYS */;
INSERT INTO `strategicrisk` VALUES (667,269,1,'Low','a','2','1'),(668,269,2,'High','b','3','2'),(669,269,3,'Low','c','2','1'),(670,269,4,'High','d','3','2'),(671,269,5,'Low','e','2','1'),(672,269,6,'High','f','3','2'),(673,270,1,'Medium','High complexity due to market conditions','1','3'),(674,270,2,'High','','3','2'),(675,270,3,'Medium','','3','1'),(676,270,4,'Medium','','2','2'),(677,270,5,'High','','3','3'),(678,270,6,'Low','','1','1'),(679,271,1,'Low','a','2','1'),(680,271,2,'Low','b','1','2'),(681,271,3,'High','c','3','2'),(682,271,4,'Medium','d','1','3'),(683,271,5,'Medium','e','2','2'),(684,271,6,'High','f','3','3'),(685,272,1,'Low','abc','2','1'),(686,272,2,'Medium','','1','3'),(687,272,3,'Medium','','2','2'),(688,272,4,'Low','','1','1'),(689,272,5,'High','','3','3'),(690,272,6,'High','','3','2'),(691,273,1,'Low','abc','2','1'),(692,273,2,'High','','3','2'),(693,273,3,'N/A','','3','0'),(694,273,4,'High','','3','2'),(695,273,5,'Low','','1','2'),(696,273,6,'High','','3','2'),(697,275,1,'Low','123','2','1'),(698,275,2,'High','','3','2'),(699,275,3,'Low','','1','2'),(700,275,4,'High','','3','2'),(701,275,5,'Medium','','1','3'),(702,275,6,'Medium','','3','1'),(703,276,1,'Low','456','1','2'),(704,276,2,'High','','3','2'),(705,276,3,'Low','','2','1'),(706,276,4,'High','','2','3'),(707,276,5,'Low','','2','1'),(708,276,6,'High','','2','3'),(709,277,1,'N/A','','0','0'),(710,277,2,'N/A','','0','0'),(711,277,3,'N/A','','0','0'),(712,277,4,'N/A','','3','0'),(713,277,5,'N/A','','0','3'),(714,277,6,'N/A','','2','0'),(715,278,1,'N/A','','2','0'),(716,278,2,'N/A','','0','0'),(717,278,3,'N/A','','0','2'),(718,278,4,'N/A','','0','0'),(719,278,5,'N/A','','1','0'),(720,278,6,'N/A','','0','0'),(721,280,1,'N/A','','0','0'),(722,280,2,'N/A','','0','0'),(723,280,3,'N/A','','0','0'),(724,280,4,'N/A','','0','0'),(725,280,5,'N/A','','0','0'),(726,280,6,'N/A','','0','0'),(727,281,1,'N/A','','0','0'),(728,281,2,'N/A','','0','1'),(729,281,3,'N/A','','0','0'),(730,281,4,'N/A','','2','0'),(731,281,5,'N/A','','0','0'),(732,281,6,'N/A','','2','0'),(733,282,1,'N/A','','0','0'),(734,282,2,'N/A','','0','0'),(735,282,3,'N/A','','0','0'),(736,282,4,'N/A','','0','0'),(737,282,5,'N/A','','0','0'),(738,282,6,'N/A','','0','0'),(739,283,1,'N/A','','0','0'),(740,283,2,'N/A','','0','0'),(741,283,3,'N/A','','0','0'),(742,283,4,'N/A','','0','0'),(743,283,5,'N/A','','0','0'),(744,283,6,'N/A','','1','0'),(745,285,1,'Low','','2','1'),(746,285,2,'High','','2','3'),(747,285,3,'Medium','','3','1'),(748,285,4,'High','','3','3'),(749,285,5,'Low','','2','1'),(750,285,6,'Medium','','1','3'),(751,287,1,'N/A','','0','0'),(752,287,2,'N/A','','0','0'),(753,287,3,'N/A','','0','0'),(754,287,4,'N/A','','0','0'),(755,287,5,'N/A','','0','0'),(756,287,6,'N/A','','1','0'),(757,288,1,'Low','abc','2','1'),(758,288,2,'High','','3','2'),(759,288,3,'Medium','','3','1'),(760,288,4,'Medium','','2','2'),(761,288,5,'N/A','','0','2'),(762,288,6,'N/A','','0','0'),(763,290,1,'N/A','','0','2'),(764,290,2,'N/A','','0','0'),(765,290,3,'N/A','','0','0'),(766,290,4,'N/A','','2','0'),(767,290,5,'N/A','','0','0'),(768,290,6,'N/A','','2','0'),(769,291,1,'N/A','','0','0'),(770,291,2,'N/A','','0','0'),(771,291,3,'N/A','','0','0'),(772,291,4,'N/A','','0','0'),(773,291,5,'N/A','','0','0'),(774,291,6,'N/A','','0','0'),(775,292,1,'N/A','','0','0'),(776,292,2,'N/A','','0','0'),(777,292,3,'N/A','','0','0'),(778,292,4,'N/A','','0','0'),(779,292,5,'N/A','','0','0'),(780,292,6,'N/A','','0','0'),(781,293,1,'N/A','','0','0'),(782,293,2,'N/A','','0','0'),(783,293,3,'N/A','','0','0'),(784,293,4,'N/A','','0','0'),(785,293,5,'N/A','','0','0'),(786,293,6,'N/A','','0','0'),(787,294,1,'N/A','','0','0'),(788,294,2,'N/A','','0','0'),(789,294,3,'N/A','','0','0'),(790,294,4,'N/A','','0','0'),(791,294,5,'N/A','','0','0'),(792,294,6,'N/A','','2','0'),(793,295,1,'Low','High complexity due to market conditions','2','1'),(794,295,2,'High','','3','2'),(795,295,3,'Medium','','1','3'),(796,295,4,'Low','','1','2'),(797,295,5,'N/A','','0','0'),(798,295,6,'Low','','1','1'),(799,296,1,'N/A','','0','0'),(800,296,2,'N/A','','0','0'),(801,296,3,'N/A','','0','0'),(802,296,4,'N/A','','0','0'),(803,296,5,'N/A','','0','0'),(804,296,6,'N/A','','0','0'),(805,299,1,'N/A','','0','0'),(806,299,2,'N/A','','0','0'),(807,299,3,'N/A','','0','0'),(808,299,4,'N/A','','0','0'),(809,299,5,'N/A','','0','0'),(810,299,6,'N/A','','0','0'),(811,300,1,'N/A','','0','0'),(812,300,2,'N/A','','0','0'),(813,300,3,'N/A','','0','3'),(814,300,4,'N/A','','2','0'),(815,300,5,'N/A','','2','0'),(816,300,6,'N/A','','0','0');
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
  `subprocessName` varchar(85) NOT NULL DEFAULT '',
  `processId` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`subprocessId`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subprocess`
--

LOCK TABLES `subprocess` WRITE;
/*!40000 ALTER TABLE `subprocess` DISABLE KEYS */;
INSERT INTO `subprocess` VALUES (1,'Requisition',1),(2,'Contract',1),(3,'Order',1),(4,'Receipt',1),(5,'Disbursement',1),(6,'Prepaid Expenditure',1),(7,'Sales Agreement',2),(8,'Deliver products/services',2),(9,'Invoicing',2),(10,'Collections',2),(11,'Acquisition',3),(12,'Disposal ',3),(13,'Depreciation',3),(14,'Position placement',4),(15,'Payroll processing',4),(16,'Payroll disbursement',4),(17,'Terminations',4),(18,'Employee benefits',4),(19,'Accruals & provisions',5),(20,'Sub ledger clsoing and reconciliations',5),(21,'Financial Statements review',5),(22,'Consolidation',5),(23,'Internal & External Reporting',5),(24,'Income tax accounting',6),(25,'Income tax compliance',6),(26,'Cash Management',7),(27,'Funding',7),(28,'Hedging',7),(30,'Vendor Master Data',1),(31,'Pareoll Transactions',4),(32,'Acquisitions',8),(33,'Depriciation',8),(34,'Disposals',8),(35,'Reserves',8),(36,'Year and Close',5),(37,'GL Master Data',5),(38,'Consolidations and  Management Reporting',5),(39,'Business Unit Reporting Package',5),(40,'Process Wide Consideration',5),(42,'Financial Statement Presentation and Disclosures',5),(43,'Account for provision and bad debt',2),(44,'Cash Receipt',2),(45,'Adjustments & period-end close - Revenue & Receivables',2),(46,'Process-wide considerations - Revenue & Receivables',2),(47,'Order processing',2),(48,'Customer returns, discounts & allowances',2);
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
) ENGINE=InnoDB AUTO_INCREMENT=320 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suggestedcontrols`
--

LOCK TABLES `suggestedcontrols` WRITE;
/*!40000 ALTER TABLE `suggestedcontrols` DISABLE KEYS */;
INSERT INTO `suggestedcontrols` VALUES (1,'Automated : Purchase Order can not be issued until and unless vendor is selected by the \"authorized\" as per the company\'s approved policy within the procurment module',1,0,'C-P2P-C-1',''),(2,'Manual : Before the issuance of Purchase Order the authorized person ensures that the bidding process has been undertaken as per the company\'s policy. Documentary evidence of the same is attached with the purchase order for internal records',1,0,'C-P2P-C-2','2'),(3,'All contract authorisation responsibilities are segregated from disbursement, contract initiation and contract management...',1,0,'C-P2P-C-3','1'),(4,'Automated : System does not allow payment of any invoice wihtout Purchase Order/Change Order',1,0,'C-P2P-C-4','0'),(5,'Contract Management System is integrated with the PO Module such that PO can be issued once the contract is signed off by all the authorized in the system',2,0,'C-P2P-C-5','0'),(6,'Contract sign-off process is controlled by contract owner / manager via the contract signoff document (document execution checklist - docex), ',2,0,'C-P2P-C-6','0'),(7,'All contracts developed and executed in compliance with approvals framework.  The approvals framework is maintained by appropriate personnel and changes to it are appropriately authorised by management.',2,0,'C-P2P-C-7','1'),(8,'Contract sign-off process is controlled by contract owner / manager via the contract signoff document (document execution checklist - docex), ',3,0,'C-P2P-C-8','0'),(9,'Criteria exists to enable identification of embedded derivatives.  All new contracts are reviewed against company\'s developed embedded derivative criteria for identification of potential and actual embedded derivatives.  ',3,0,'C-P2P-C-9','1'),(10,'Vendor performance criteira is established at the time of contract signing for all contracts above a certain value. Contract owners manage contract in accordance with that criteria. Results of the evaluation are input into the vendor registration database......',4,0,'101112','2'),(11,'For expenses other than petty cash/specfically identified; no invoice can be processed without a valid PO.',5,0,'C-P2P-C-11',''),(12,'Planned vs. Actual hours are regularly monitored by the Contract Owner. Any variations are investigated for appropriate action.Change work orders are issued when it is expected tha the actual will exceed the planned hours.',6,0,'C-P2P-C-12',''),(13,'Purchase Orders are only issued against the unique authorized purchase requisition.',7,0,'C-P2P-C-13',''),(14,'All purchase orders are based on authorisation in accordance with approved limits (system workflow).  ',8,0,'C-P2P-C-14',''),(15,'Where an order is varied, final order value is authorised in accordance with approval limits (system workflow).  ',8,0,'C-P2P-C-15',''),(16,'Requisitioning,   ordering, goods receipting and payment functions are physically segregated. ',8,0,'C-P2P-C-16',''),(17,'System requires that all mandatory fields (please list) be completed prior processing a purchase order.  If all mandatory fields are not complete, the order cannot be placed with the supplier.',9,0,'C-P2P-C-17',''),(18,'PO is automatically generated from approved PR in the system',9,0,'C-P2P-C-18',''),(19,'Vendor related information is direcly extracted from the vendor Module and is uneditable in the PO.',9,0,'C-P2P-C-19',''),(20,'All Pos\' are reviewed at one level above the preparer',9,0,'C-P2P-C-20',''),(21,'\"Vendor selection\" module is integrated with the Ordering module such that the final price of the selected vendor is automatically extracted into the PO.',9,0,'C-P2P-C-21',''),(22,'A standard chart of accounts exists and all expenditures are classified consistently in accordance with this chart of accounts.  Knowledgeable and trained people perform coding.',10,0,'C-P2P-C-22',''),(23,'Item catalogue is developed and embedded into the system.GL Account codes are already mapped against the goods/services in the item catalogue',10,0,'C-P2P-C-23',''),(24,'\"Vendor selection\" module is integrated with the Ordering module such that the selected vendor is automatically extracted into the PO.',11,0,'C-P2P-C-24',''),(25,'All Pos\' are reviewed at one level above the preparer',11,0,'C-P2P-C-25',''),(26,'Purchasing and order placement function is restricted to trained users / purchasing officers.',11,0,'C-P2P-C-26',''),(42,'GR/IR account is regularly reviewed and cleared.',12,0,'C-P2P-C-27',''),(43,'Outstanding order report run on a monthly basis to detect long outstanding purchase orders which have not been matched to invoices and issues resolved before monthly closing.',12,0,'C-P2P-C-28',''),(44,'Centralised goods receipting function to record and review goods received (I.e. warehouse), where practical.',12,0,'C-P2P-C-29',''),(45,'For items where centralized warehousing is not practical, the person to enter the \"Receipt\" is identified at the time of issuance of PO.Reminders are automatically sent through system at the due date. ',12,0,'C-P2P-C-30',''),(46,'Centralised invoice receipt function to record and distribute invoices received enabling  invoices to be recorded and tracked by the system.',13,0,'C-P2P-C-31',''),(47,'The system is an integrated procurement system which requires the matching of purchase orders to goods receipt or service entry sheet.',14,0,'C-P2P-C-32',''),(48,' Centralised goods receipting function to record and review goods received (I.e. warehouse).',15,0,'C-P2P-C-33',''),(49,' Systems access profiles documented and matched to segregation of duties',16,0,'C-P2P-C-34',''),(50,'The system is an integrated procurement system which requires the matching of purchase orders to goods receipt or service entry sheet.',17,0,'C-P2P-C-35',''),(51,'The system is an integrated procurement system which requires the matching of purchase orders to goods receipt or service entry sheet.',18,0,'C-P2P-C-36',''),(52,'Accounts payable function is segregated from purchasing and receiving and general ledger recording activities.  These functions are segregated to prevent inappropriate disbursement activity.',47,0,'121112','0'),(53,'Systems access profiles for accounts payable personnel are documented and reviewed against segregation of duties requirements.',47,0,'C-P2P-C-38',''),(54,' System matches purchase order, receipt, and invoice (3way match) and automatically generates an accounting entry based on match.  Payment is not made until 3-way match occurs.  ',47,0,'C-P2P-C-39',''),(55,'Comprehensive Authority Matrix is prepared and circulated to all the concerned',48,0,'9899','0'),(56,'Payment authorization limits are defined in the system so that they authomatically go into the work flow of the concerend',48,0,'97111','0'),(57,'In a manual set up Accounts Payable Manager specifically reviews and validate that the invoice is approved by the authorized for payment processing',48,0,'C-P2P-C-42',''),(58,'System control to prevent changing bank accounts during EFT run.',49,0,'C-P2P-C-43',''),(59,'Cheque runs are reviewed by management prior to processing. Unusual or missing payments are investigated and resolved on a timely basis.',49,0,'C-P2P-C-44',''),(60,'Manual comparison of payee\'s name/address to the approved vendor master file. Differences are investigated and resolved on a timely basis.',49,0,'C-P2P-C-45',''),(61,' System matches purchase order, receipt, and invoice (3way match) and automatically generates an accounting entry based on match.  Payment is not made until 3-way match occurs.  ',50,0,'C-P2P-C-46',''),(62,' System matches purchase order, receipt, and invoice (3way match) and automatically generates an accounting entry based on match.  Payment is not made until 3-way match occurs.  ',51,0,'C-P2P-C-47',''),(63,'Restricted access to the vendor masterfile.  Additional restrictions around access to change vendor bank details.',52,0,'C-P2P-C-48',''),(64,'Prequalification questionnaire must be reviewed and approved by appropriate supply management.  Selection of vendors for major contracts is based  on results of completion of a Weighted Decision Analysis and a Vendor Risk Analysis .Vendor is registered into the system only after vendor registration form is approved by the Procurement Head',52,0,'C-P2P-C-49',''),(65,'Restricted access to the vendor masterfile.  Additional restrictions around access to change vendor bank details.',53,0,'C-P2P-C-50',''),(66,'Independent confirmation from vendor of vendor details.',53,0,'C-P2P-C-51',''),(67,'Masterfile change report is  verified against original information.',53,0,'C-P2P-C-52',''),(68,'Identify inactive suppliers and periodically purge  from the system where not used.',53,0,'C-P2P-C-53',''),(69,'Vendor is registered into the system only after vendor registration form is approved by the Procurement Head',54,0,'C-P2P-C-54',''),(70,'Appropriate segregation is maintained between position requirement generator,Hiring funtion & Payroll processing fucntion such that activity in each function is not trigerred unless previous one is appropriately approved and authorized as per the company policy',55,0,'C-H2T-PP-1',''),(71,'HR prepare a pre-hire activity form to update system, organisation structure and costing allocations.',55,0,'C-H2T-PP-2',''),(72,'Authority Matirx is in place detailing the authorities for various hiring positions',55,0,'C-H2T-PP-3',''),(73,'An online portal is created where employees themselves enter the their personal details (Bank Account etc) integrated with the payroll application.Offer letter is also created through the system by compensation team such that when the offer is accepted it is automatically updated in payroll application.',56,0,'C-H2T-PP-4',''),(74,' An independent person (I.e. a person different to the employee master data processor) checks inputs of employee master data against  source documentation.  Evidence of this check is maintained.',56,0,'C-H2T-PP-5',''),(75,'System access to change high risk employee data (eg. Bank details) is appropriately restricted.',57,0,'C-H2T-PP-6',''),(76,'An independent person (I.e. a person different to the employee master data processor) checks inputs of employee master data against  source documentation.  Evidence of this check is maintained.',57,0,'C-H2T-PP-7',''),(77,' Payroll supervisor and / or HR review a master file data change report prior to weekly, fortnightly, monthly pay runs, for reasonableness.  ',57,0,'C-H2T-PP-8',''),(78,' Restricted access to initiate and authorise payroll payment via banking systems.  Two authorities required for each payment.',58,0,'C-H2T-PD-1',''),(79,'Payroll banking information is securely stored from unauthorised users.',58,0,'C-H2T-PD-2',''),(80,'Payroll clearing account is reconciled on a monthly basis to detect differences between payroll journals and payments made by the bank.',58,0,'C-H2T-PD-3',''),(81,'System configured to report all duplicate entries. ',59,0,'C-H2T-PD-4',''),(82,'All duplicate entries are reviewed and resolved by the payroll supervisor.',59,0,'C-H2T-PD-5',''),(83,'Exception reports (eg. Hours worked, net pay variances exceeding reasonable tolerance, duplicate bank accounts, etc) generated by system and all items followed up by payroll supervisor prior to pay run.',59,0,'C-H2T-PD-6',''),(84,'Payroll system is configured to automatically generate payroll based on inputs for hourly employees and data contained in master file',60,0,'C-H2T-PD-7',''),(85,'Exception reports (eg. Hours worked, net pay variances exceeding reasonable tolerance, duplicate bank accounts, etc) generated by system and all items followed up by payroll supervisor prior to pay run.',60,0,' C-H2T-PD-8',''),(86,'Payroll supervisor performs intra-period variance analysis ',60,0,'C-H2T-PD-9',''),(87,' Completed payroll reports are reviewed and approved prior to disbursement',60,0,'C-H2T-PD-10',''),(88,'Monthly cost reports reviewed for significant variances to budget and variances investigated where appropriate',60,0,'C-H2T-PD-11',''),(89,'Exception reports (eg. Hours worked, net pay variances exceeding reasonable tolerance, duplicate bank accounts, etc) generated by system and all items followed up by payroll supervisor prior to pay run.',61,0,'C-H2T-PD-12',''),(90,' Completed payroll reports are reviewed and approved prior to disbursement',61,0,'C-H2T-PD-13',''),(91,'System automatically calculates deductions based on information contained within the Payroll Masterfile.',62,0,'C-H2T-PD-14',''),(92,'From a sample of 25 employee payroll deduction requests, trace to employee authorisation.',62,0,'C-H2T-PD-15',''),(93,'Salary and wage details submitted to employees (payslips) detail all withholdings / deductions made.  Employees contact payroll where incorrect withholdings / deductions are detected.',62,0,'C-H2T-PD-16',''),(94,'Exception reports (eg. Hours worked, net pay variances exceeding reasonable tolerance, duplicate bank accounts, etc) generated by system and all items followed up by payroll supervisor prior to pay run.',63,0,'C-H2T-PD-17',''),(95,'Completed payroll reports are reviewed and approved prior to disbursement',63,0,'C-H2T-PD-18',''),(96,'A payroll disaster recovery plan is in place.',63,0,'C-H2T-PD-19',''),(97,'Use of monthly check list or calendar to  document due dates for payroll disbursement submissions and payments.',63,0,'C-H2T-PD-20',''),(98,'Restricted access to initiate and authorise payroll payment via banking systems.  Two authorities required for each payment.',64,0,'C-H2T-PD-21',''),(99,'Payroll banking information is securely stored from unauthorised users.',64,0,'C-H2T-PD-22',''),(100,' Payroll clearing account is reconciled on a monthly basis to detect differences between payroll journals and payments made by the bank.',64,0,'C-H2T-PD-23',''),(101,'Payroll system is configured to automatically generate payroll based on inputs for hourly employees and data contained in master file',65,0,'C-H2T-PD-24',''),(102,'Payroll supervisor performs intra-period variance analysis ',65,0,'C-H2T-PD-25',''),(103,'Payroll Masterfile reflects current taxation rates for relevant taxes payable.  Payroll Management reviews Masterfile tax rates regularly for accuracy against current legislation.',65,0,'C-H2T-PD-26',''),(104,'Employment taxes reconciled to payroll reports and to the general ledger on a monthly basis',65,0,'C-H2T-PD-27',''),(105,'Monthly cost reports reviewed for significant variances to budget and variances investigated where appropriate',65,0,'C-H2T-PD-28',''),(106,'Payroll supervisor performs intra-period variance analysis ',66,0,'C-H2T-PD-29',''),(107,' Employment taxes reconciled to payroll reports and to the general ledger on a monthly basis',66,0,'C-H2T-PD-30',''),(108,'Use of monthly check list or calendar to  document due dates for payroll disbursement submissions and payments.',66,0,'C-H2T-PD-31',''),(109,' Employment tax submissions are required to be lodged on a prescribed form to prevent inconsistent and / untimely submissions.',67,0,'C-H2T-PD-32',''),(110,'Use of monthly check list or calendar to  document due dates for payroll disbursement submissions and payments.',67,0,'C-H2T-PD-33',''),(111,'BHP B workers compensation experts determine claim amounts in accordance with relevant legislation.',68,0,'C-H2T-PD-34',''),(112,'Existing system payroll information drawn from to determine compensation amount. No manual input necessary to weekly / fortnightly / monthly payment amounts. ',68,0,'C-H2T-PD-35',''),(113,'Timesheets are pre-named based on system roster information.',69,0,'C-H2T-PT-1',''),(114,'Shift supervisors authorise all hourly payroll reports prior to submitting for processing',69,0,'C-H2T-PT-2',''),(115,'System configured to report all duplicate entries. All exceptions followed up by payroll supervisor',69,0,'C-H2T-PT-3',''),(116,'Shift supervisors authorise all hourly payroll reports prior to submitting for processing',70,0,'C-H2T-PT-4',''),(117,'Absence reports generated by the system are reviewed and actioned.',70,0,'C-H2T-PT-5',''),(118,'Observe and assess system reporting capabilities over duplicate transaction detection and reporting.',70,0,'C-H2T-PT-6',''),(119,'Payroll complaints are logged, verified and followed up on in a timely fashion.',70,0,'C-H2T-PT-7',''),(120,'Requests for employee master file changes are recorded on a change request form.  These forms are reviewed for completeness and appropriate authorisation prior to input by the change processor.  ',71,0,'C-H2T-PT-8',''),(121,'Master file changes recorded which are processed into payroll systems via automated uploads (eg. from Rewards on Line)  are validated and reviewed (by appropriate payroll personnel) for completeness and accuracy.  ',71,0,'C-H2T-PT-9',''),(122,'An independent person (I.e. a person different to the employee master data processor) checks inputs of employee master data against source documentation.  Evidence of this check is maintained.',71,0,'C-H2T-PT-10',''),(123,'Payroll supervisor and / or HR review a master file data change report prior to weekly, fortnightly, monthly pay runs, for reasonableness.  ',71,0,'C-H2T-PT-11',''),(124,'Shift supervisors authorise all hourly payroll reports prior to submitting for processing',72,0,'C-H2T-PT-12',''),(125,'Absence reports generated by the system are reviewed and actioned.',72,0,'C-H2T-PT-13',''),(126,'Review payroll complaint log for timeliness and adequacy of payroll complaint resolution.',72,0,'C-H2T-PT-14',''),(127,'Review payroll complaint log for timeliness and adequacy of payroll complaint resolution.',73,0,'C-H2T-PT-15',''),(128,'Master file changes recorded which are processed into payroll systems via automated uploads (eg. from Rewards on Line)  are validated and reviewed (by appropriate payroll personnel) for completeness and accuracy.  ',73,0,'C-H2T-PT-16',''),(129,'An independent person (I.e. a person different to the employee master data processor) checks inputs of employee master data against source documentation.  Evidence of this check is maintained.',73,0,'C-H2T-PT-17',''),(130,'Payroll supervisor and / or HR review a master file data change report prior to weekly, fortnightly, monthly pay runs, for reasonableness.  ',73,0,'C-H2T-PT-18',''),(131,'Acquisition reports are reviewed, with comparisons to budgets or other data for reasonableness of acquisitions by category of asset, location or segment of business. Variances from expectations and unusual items are investigated and resolved on a timely basis.\r\n',74,0,'C-FA-A-1',''),(132,'Mandatory fields must be completed in fixed asset addition requests, including: asset description, asset identifier, location, cost, addition date, tax data, depreciation information. System will not allow upload until mandatory fields have been completed, thus preventing the incomplete processing of asset details in the register.\r\n',75,0,'C-FA-A-2',''),(133,'System has pre-set classifications to enable automated  fixed asset disclosures at reporting date, preventing classification errors from manual determination.',76,0,'C-FA-A-3',''),(134,'All capital expenditures requisitions (including subsequent changes) are reviewed and approved by an appropriate level of management. Unusual items are investigated and resolved prior to approval.',76,0,'C-FA-A-4',''),(135,'Management reviews purchase & sale agreements, with other data to ensure acquisitions are accurately recorded (e.g. investment property, PP&E, investment in venture).',76,0,'C-FA-A-5',''),(136,'Accounting policy manual is followed for capitalisation of assets',77,0,'C-FA-A-6',''),(137,'Review Operating costs by department/cost centre/authorisation/project for expenditure against budgets, prior periods or expectations.  Irregularities are reviewed and resolved in a timely manner.',78,0,'C-FA-A-7',''),(138,'Management reviews and approves the classification of work orders as capital versus expense in nature based on the type of project and company policy. Discrepancies are followed up and resolved.',78,0,'C-FA-A-8',''),(139,'Review of purchase orders and work orders on a regular basis to determine appropriate capital / operating classification.',78,0,'C-FA-A-9',''),(140,'Fixed assets are capitalized as per the requirements of capitalization policy',78,0,'C-FA-A-10',''),(141,'Selection of classification code for each asset must be approved.  The approval process involves reviewing the coding for accuracy.',79,0,'C-FA-A-11',''),(142,'Review capital expenditures outside approved amounts. All exceptions followed up in a timely manner.\r\n',79,0,'C-FA-A-12',''),(143,'The asset register and the general ledger is reconciled regularly (at least quarterly).  Unreconciled differences are followed-up on a timely basis.',80,0,'C-FA-A-13',''),(144,'System access to create final asset in the fixed asset register  is restricted.  ',80,0,'C-FA-A-14',''),(145,'Management reviews periodic reports, with comparisons to budgets or other data for reasonableness of depreciation charges by category of asset, location or segment of business. Variances from expectation outside a predetermined threshold and unusual items are investigated and resolved on a timely basis.',93,0,'C-FA-D-1',''),(146,'Annual review of reports detailing assets not depreciated.  Appropriate accounting consideration given to all assets appearing on the assets not depreciated report. ',93,0,'C-FA-D-2',''),(147,'Edit report run to ensure that all assets classified as held for sale and/or taken out of service do not have depreciation taken against them',81,0,'C-FA-D-3',''),(148,'Periodic review of capital asset register with operational staff to identify assets held for sale and/or taken out of service.  This is done to prevent incorrect charging to depreciation expense.',82,0,'C-FA-D-4',''),(149,'Accounting policy on depreciation and depreciable assets is understood and  applied by staff responsible for calculating depreciation. ',83,0,'C-FA-D-5',''),(150,'System will not allow retrospective depreciation charges once financial year is rolled- over.',84,0,'C-FA-D-6',''),(151,'Access to modify depreciation rates is restricted by user profiles',94,0,'C-FA-D-7',''),(152,'Information used for the calculation of depreciation for new acquisitions is validated against \"acquired assets information check list\" (asset class, date asset put into use, expected useful life or UOP asset, depreciation rate, estimated residual value etc.) for accuracy.',85,0,'C-FA-D-8',''),(153,'Depreciation calculations are performed by the system and are only run after all periodic amendments, adjustments and additions to fixed asset register has been completed',86,0,'C-FA-D-9',''),(154,'Monthly depreciation run is included in schedule of month end procedures resulting in timely and consistent booking of asset values to the general ledger at month-end.',87,0,'C-FA-D-10',''),(155,'Annual review of asset recoverable amount.  Recoverable amount adjustments are posted on a timely basis.',88,0,'C-FA-D-11',''),(156,'Information used for the calculation of depreciation for new acquisitions is validated against \"acquired assets information check list\" (asset class, date asset put into use, expected useful life or UOP asset, depreciation rate, estimated residual value etc.) for accuracy.',89,0,'C-FA-D-12',''),(157,'Accounting policy on depreciation and depreciable assets is understood and  applied by staff responsible for calculating depreciation. ',90,0,'C-FA-D-13',''),(158,'Depreciation charges and accumulated depreciation by asset class are regularly reconciled to the general ledger',91,0,'C-FA-D-14',''),(159,'Year-end asset roll-over is included in schedule of year-end procedures resulting in a timely and consistent roll-forward of asset values in the asset register.',92,0,'C-FA-D-15',''),(160,'Mandatory fields must be completed in fixed asset disposal requests, including asset description, asset identifier, location, cost, date of disposal, tax data, depreciation information.  System will not allow disposal to be processed unless mandatory fields are complete thus reducing the risk of incomplete or inaccurate disposals in the system.',95,0,'C-FA-DR-1',''),(161,'All disposals require review and approval from appropriate level of management prior to processing.\r\nAn authorised asset disposal form is required to authorise disposal and remove asset from site.',95,0,'C-FA-DR-2',''),(162,'Accounting policy manual is followed for disposal of assets.',95,0,'C-FA-DR-3',''),(163,'Reconcile fixed asset register with general ledger after receiving disposal proceeds',96,0,'C-FA-DR-4',''),(164,'Management reviews and approves the calculation and classification of gains and losses on significant disposals/retirements based on disposal/retirement information (e.g. nature of the disposal/retirement, proceeds of disposition etc.) to ensure posted against accumulated depreciation or to the P/L as applicable. Discrepancies are investigated and resolved.',101,0,'C-FA-DR-5',''),(165,'An authorised asset disposal form is required to authorise disposal and remove asset from site.',97,0,'C-FA-DR-6',''),(166,'Initiation of the disposal transaction is segregated from authorisation, recording, receipt of proceeds and physical disposal.',97,0,'C-FA-DR-7',''),(167,'An authorised asset disposal form is required to authorise disposal and remove asset from site.',98,0,'C-FA-DR-8',''),(168,'A comparison is performed between disposals processed to disposal source documents (e.g. approved disposal form, cash proceeds, removal costs, etc). Differences are investigated and resolved on a timely basis.',99,0,'C-FA-DR-9',''),(169,'All disposals require review and approval from appropriate level of management prior to processing.',100,0,'C-FA-DR-10',''),(170,'Accounting policy on disposal of assets and applied by staff responsible for calculating depreciation. ',100,0,'C-FA-DR-11',''),(171,'Reconciliation of the profit / loss on disposals account and proceeds on disposal accounts to the asset register.  Discrepancies are investigated on a timely basis.',100,0,'C-FA-DR-12',''),(172,'Changes/modifications to reserve tracking data are reviewed',102,0,'C-FA-R-1',''),(173,'Comparison of third party reserve engineer determined reserves to internally determined reserves',102,0,'C-FA-R-2',''),(174,'Economic factors used in estimating values are compared with applicable disclosure requirements.\r\nEconomic factors used in estimating values are compared with applicable disclosure requirements.\r\nEconomic factors used in estimating values are compared with applicable disclosure requirements.\r\n',103,0,'C-FA-R-3',''),(175,'Management review of year over year analysis of changes to estimates or classifications in reservoirs.\r\n',104,0,'C-FA-R-4',''),(176,'Manual review and approval of model/reserve tracking outputs reconciliation.',104,0,'C-FA-R-5',''),(177,'Modified terms of lease or contractual obligations considered in preparing or updating reserve models.\r\nModified terms of lease or contractual obligations considered in preparing or updating reserve models.\r\nModified terms of lease or contractual obligations considered in preparing or updating reserve models.\r\n',104,0,'C-FA-R-6',''),(178,'Review compliance of review estimation procedures with applicable regulations.\r\nReview compliance of review estimation procedures with applicable regulations.\r\nReview compliance of review estimation procedures with applicable regulations.\r\n',104,0,'C-FA-R-7',''),(179,'GSAP Finance Key Users / Close Coordinators review close of period posting reports to detect irregular postings to ledger after close of period.',105,0,'C-R2P-YC-1',''),(180,'Incomplete or inaccurate entries in ER',106,0,'C-R2P-YC-2',''),(181,'Incomplete or inaccurate financial reporting',107,0,'C-R2P-YC-3',''),(182,'Prepayments to suppliers are not charged off on timely basis resulting in overstatement of assets and under statement of expenses ',108,0,'C-R2P-YC-4',''),(183,'Financial Accountants approve requests for creation / change in general ledger accounts.  As part of this process, the Accountant assigns and reviews appropriate account groupings to new accounts.',109,0,'C-R2P-GL-1',''),(184,'System Key Users authorise and validate new general ledger account requests and changes to existing account requests for reasonableness and accuracy.',110,0,'C-R2P-GL-2',''),(185,'Financial Accountant reviews system changes against request form requirements for accuracy.',111,0,'C-R2P-GL-3',''),(186,'Group guidelines are established for creation of the company chart of accounts.  Guidelines promote consistent and accurate use of Group established accounts.',112,0,'C-R2P-GL-4',''),(187,'Financial statement groupings for new accounts are specified in the Group guidelines for creation of the company chart of accounts.  Assigned financial statement groupings should not deviate from these guidelines.',113,0,'C-R2P-GL-5',''),(188,'System access to create or change general ledger accounts is appropriately restricted (I.e. to authorised finance staff or System Key Users)',114,0,'C-R2P-GL-6',''),(189,'General ledger master records are reviewed (by Finance Specialist) on an annual basis against company chart of accounts.',115,0,'C-R2P-GL-7',''),(190,'Segregation of duties between person authorising and removing unused accounts from general ledger master data.',116,0,'C-R2P-GL-8',''),(191,'Each entity must declare intercompany counterparties.',117,0,'C-R2P-CMR-1',''),(192,'Group Accounting perform an intercompany mismatch process to determine material mismatches.',118,0,'C-R2P-CMR-2',''),(193,'Group Accounting perform timely follow-up of intercompany mismatch.',119,0,'C-R2P-CMR-3',''),(194,'Investments in group companies and equity accounts are reconciled during the year by the Group Consolidations Accountant.   Relevant information is gathered from group companies during this process.',120,0,'C-R2P-CMR-4',''),(195,'Shareholders funds reconciliations are reviewed by Senior Group Accountant.  ',121,0,'C-R2P-CMR-5',''),(196,'Standard template used for all known reclassification entries that occur.  Standard template developed over time by Group Accounting and reviewed for completeness on a monthly basis.',122,0,'C-R2P-CMR-6',''),(197,'Over time Group Accounting has developed a checklist of monthly consolidation journals.  Checklist of known journal entries is completed and reviewed on a monthly basis.',123,0,'C-R2P-CMR-7',''),(198,'Annual review of consolidation journal entries to validate their relevance.',124,0,'C-R2P-CMR-8',''),(199,'Control copy of consolidation data is taken at cut-over date.',125,0,'C-R2P-CMR-9',''),(200,'Changes after cut-over data require standard form completion and authorisation by Group Accounting (ATOD).',126,0,'C-R2P-CMR-10',''),(201,'Daily reconciliation and follow-up by Group Accounting of the control copy data and the live data.',127,0,'C-R2P-CMR-11',''),(203,'Daily reconciliation by Group Accounting reviewed by Senior Consolidations Accountant.',128,0,'C-R2P-CMR-12',''),(204,'Entities must complete the \'Checker file\' which detects errors  in source system data.',129,0,'C-R2P-CMR-13',''),(205,'CSG\'s and Group Accounting review the completed \'Checker files\' for exceptions.',130,0,'C-R2P-CMR-14',''),(206,'Prior period data is locked in system after publishing of prior period accounts.',131,0,'C-R2P-CMR-15',''),(207,'Changes to prior period data require approval by Group Accounting.',132,0,'C-R2P-CMR-16',''),(208,'Where spreadsheets are used, spreadsheet integrity is reviewed regularly for completeness, accuracy and integrity against source data.',133,0,'C-R2P-CMR-17',''),(209,'Bi-annual review and sign-off by Senior Accountant and Group Accounting Management of supporting spreadsheets / workpapers used in the preparation of financial statements.  ',134,0,'C-R2P-CMR-18',''),(210,'All amounts in the reporting package are cross-referenced (e.g. net PP&E on a supplement PP&E schedule is reconciled to reporting package financial statements, etc.). All differences are investigated and resolved on a timely basis.\r\n\r\n',135,0,'C-R2P-BUP-1',''),(211,'A list of standard reporting package activities (e.g. closing schedule, roles & responsibilities, coordination meetings, etc.) required to prepare the reporting package is maintained and completed prior to issuance of the reporting package.\r\n',136,0,'C-R2P-BUP-2',''),(212,'A manual comparison is performed between the information in the reporting package and the information in the supporting documents (i.e. approved reporting package adjustments, general ledger balances, and disclosure items). Differences are investigated and resolved on a timely basis.',137,0,'C-R2P-BUP-3',''),(213,'A list of standard recurring adjustments required to finalize the reporting package is maintained and compared against the adjustments recorded each period. Differences are investigated and resolved on a timely basis.\r\n\r\n',138,0,'C-R2P-BUP-4',''),(214,'At the unit/subsidiary level (reporting package), business performance reviews are performed. Such reviews include budget versus actual, prior periods versus actual, etc. Variances from expectation outside a predetermined threshold and unusual items are investigated and resolved on a timely basis.\r\n, prior periods versus actual, etc. Variances from expectation outside a predetermined threshold and unusual items are investigated and resolved on a timely basis.\r\n',140,0,'C-R2P-BUP-6',''),(215,'Mathematical calculations in the reporting package spreadsheet are reviewed at the unit/subsidiary level prior to submission to the parent company.',141,0,'C-R2P-BUP-7',''),(216,'The system performs mathematical calculations on the reporting package.',142,0,'C-R2P-BUP-8',''),(217,'The system automatically transfers correct accounts and balances to the reporting package based on a pre-defined account mapping.',143,0,'C-R2P-BUP-9',''),(218,'Reporting package adjustments are reviewed for reasonableness of assumptions, mathematical accuracy, and completeness and accuracy of data inputs in accordance with documented policies and procedures. Resulting adjustments are approved prior to posting.\r\n',139,0,'C-R2P-BUP-5',''),(219,'Financial Controllers at CSG\'s authorise user access and entity changes to asset / CSG financial information. ',144,0,'C-R2P-C-1',''),(220,'Group view to consolidations system is authorised by Manager, Group External Reporting.',145,0,'C-R2P-C-2',''),(221,'Consolidations System Administration Group co-ordinate and authorise access changes  to system (I.e. adding users, deleting users, changes to access rights, etc).',146,0,'C-R2P-C-3',''),(222,'Users are granted read-only access.  Authorisation required for edit access. ',147,0,'C-R2P-C-4',''),(223,'Consolidations system security matrix prevents unauthorised access.',148,0,'C-R2P-C-5',''),(224,'Consolidations System Administration perform annual review  on consolidations system access rights.',148,0,'C-R2P-C-6',''),(225,'All changes to accounts and logic follow a documented change control process.  ',148,0,'C-R2P-C-7',''),(226,'User testing is performed by Consolidations System Administration and Group Accounting users prior to live system update.',148,0,'C-R2P-C-8',''),(227,'Meta data changes (I.e. changes to chart of accounts, organisation structures, entities and system logic) can only be performed by Consolidation System Administrators).',148,0,'C-R2P-C-9',''),(228,'SAP Key Users notify Consolidations System Administrators of new entity / cost centre creation for appropriate changes to consolidations system.\r\n',148,0,'C-R2P-C-10',''),(229,'The list of key personnel who are expected to submit related party transactions is maintained and compared against the personnel who have submitted related party transactions during the period. Personnel who have not submitted related party transactions are investigated and resolved on a timely basis.',149,0,'C-R2P-FSP-1',''),(230,'All amounts in the financial statements are cross-referenced (e.g. footnote balances reconciled to financial statements, etc.). All differences are investigated and resolved on a timely basis.',150,0,'C-R2P-FSP-2',''),(231,'Business performance reviews are performed for financial statements and related disclosure. Such reviews include budget versus actual, prior periods versus actual, etc. Variances from expectation outside a predetermined threshold and unusual items are investigated and resolved on a timely basis.',151,0,'C-R2P-FSP-3',''),(232,'Accounting personnel and senior management perform a periodic review of financial statements for compliance with accounting policies and GAAP. Issues are investigated and resolved on a timely basis.',152,0,'C-R2P-FSP-4',''),(233,'A list of related party transactions (such as receiving or providing accounting, management or other services by or for related parties at no charge or a major stockholder absorbing corporate expenses, etc.) that require recording to and/or disclosure in the financial statements is maintained. This list is compared against related party transactions received from key personnel. Differences are investigated and resolved on a timely basis.',153,0,'C-R2P-FSP-5',''),(234,'A checklist of items to be included in the financial statements and disclosures is completed as part of the period-end financial reporting process. Management reviews the completed checklist for completeness and accuracy. Unusual and missing items are investigated and resolved.',154,0,'C-R2P-FSP-6',''),(235,'A manual comparison is performed between the checklist of items to be disclosed and the financial statements and disclosure items to ensure completeness. Differences are investigated and resolved on a timely basis.',155,0,'C-R2P-FSP-7',''),(236,'A manual comparison is performed between the financial statements / disclosure items and relevant supporting documentation. Differences are investigated and resolved on a timely basis.',156,0,'C-R2P-FSP-8',''),(237,'Financial statements and disclosure items are reviewed and approved by individuals independent of the finance department (e.g. disclosure committee, audit committee, legal department, etc.). Unusual items are investigated and resolved on a timely basis.',157,0,'C-R2P-FSP-9',''),(238,'Disclosure items are reviewed for reasonableness of assumptions, mathematical accuracy, and completeness and accuracy of data inputs in accordance with documented policies and procedures. Resulting changes are approved prior to updating the financial statements and disclosure items.',158,0,'C-R2P-FSP-10',''),(239,'All information obtained from the reporting units/departments is reviewed for inclusion in the financial statements and disclosure items. Differences are investigated and resolved on a timely basis.',159,0,'C-R2P-FSP-11',''),(240,'Reporting units/departments must communicate all information required to prepare the financial statements and disclosure items to the corporate finance department in accordance with the company\'s policy (e.g. certification, email, etc). A review of the information is performed to ensure completeness. Missing or incomplete information is investigated and resolved on a timely basis.',160,0,'C-R2P-FSP-12',''),(241,'Mathematical calculations in the financial statements and disclosure items are manually performed and reviewed.',161,0,'C-R2P-FSP-13',''),(242,'Related party transactions reported to the financial reporting department are investigated and resolved (determining impact on the financial statements) on a timely basis.\r\n',162,0,'C-R2P-FSP-14',''),(243,'All topside entries (direct to the financial statements) are reviewed and approved prior to processing.',163,0,'C-R2P-FSP-15',''),(244,'A review is performed of significantly aged customer balances and known issues in the accounts receivable sub-ledger and the corresponding allowance for doubtful accounts to ensure account balances are adequately reserved. Differences are investigated and resolved on a timely basis.',164,0,'C-O2C-APD-1',''),(245,'Provision for bad debts is made on a regular basis, based on the approved policy, supported by relevant documentation, e.g., aged debtor analysis and credit history and calculations and authorized by a delegated authority who does not have responsibility for managing credit limits, or credit control function.\r\n',165,0,'C-O2C-APD-2',''),(246,'Management approval is required for recording provision amounts and write-offs based on predetermined thresholds.',166,0,'C-O2C-APD-3',''),(247,'The company has a formally documented policy for calculating provision and write off against bad debts and inventory which is approved by the appropriate authorities and communicated to all the concerned departments and complied with.\r\n',167,0,'C-O2C-APD-4',''),(248,'A checklist of areas where provision could occur is prepared on a monthly basis and is circulated to relevant POCs for providing estimated amount of provision to be recorded along with the supporting documents. \r\n',168,0,'C-O2C-APD-5',''),(249,'The electronic file of receipts into the lockbox interfaces with the accounts receivable sub-ledger and applies cash receipts to the customer accounts based on a matching of customer name, customer number, invoice number etc. Unmatched cash receipts are placed into a suspense file to be investigated and manually applied on a timely basis.',169,0,'C-O2C-CR-1',''),(250,'Cash receipts are recorded upon receipt and applied to customer accounts/invoices via remittance advices. Cash receipts without remittance slips are investigated and resolved on a timely basis.',170,0,'C-O2C-CR-2',''),(251,'An independent employee matches the total cash deposited with the cash receipts for the day. Unmatched cash receipts are reported to appropriate level and investigated on a timely basis.',171,0,'C-O2C-CR-3',''),(252,'An independent employee should count the cash collected at\r\nthe end of each day. This person should not be responsible for running the end of the day report and remitting the cash and report for reconciliation.',172,0,'C-O2C-CR-4',''),(253,'Any changes in the sale price, including discounts, is pre-approved by the competent authority and the price is entered in the system by authorized personnel only. \r\n\r\nThe system requires management approval of discounts and allowances in excess of predefined limits.\r\n',173,0,'C-O2C-CRD-1',''),(254,'Management reviews and approves the sales incentive reserve (rebates, discounts, price guarantees, etc.) according to the company\'s sales incentive estimation policy. The Company\'s sales incentive estimation methods (e.g. review of historical trends) are assessed for reasonableness on a periodic basis.\r\n',173,0,'C-O2C-CRD-2',''),(255,'A three way match is performed by comparing the quantities on the credit memo, the sales return receipt documents, and the sales return authorization document. Differences are investigated and resolved on a timely basis.\r\nA comparison is performed between the price per the credit memo and the sales price on the original invoice. Differences are investigated and resolved on a timely basis.\r\n',174,0,'C-O2C-CRD-3',''),(256,'The system achieves transaction cut-off by recording credit memos in the correct accounting period based on the receiving document date.',175,0,'C-O2C-CRD-4',''),(257,'Information regarding anticipated demand, wholesaler inventory levels, and other data to support returns estimates (for example inventory levels in hospitals and pharmacies) are reviewed and approved by financial personnel',176,0,'C-O2C-CRD-5',''),(258,'A product wise quarterly analysis is performed to understand the trend of rebates, discount, allowances and sales return etc.\r\nSuch analysis is reviewed and approved by the appropriate authorities.\r\n',177,0,'C-O2C-CRD-6',''),(259,'Management reviews and approves the sales returns reserve according to the company\'s sales returns estimation policy. The Company\'s sales returns estimation methods (e.g. review of historical trends) are assessed for reasonableness on a periodic basis.',178,0,'C-O2C-CRD-7',''),(260,'Return Authorizations are reviewed/approved prior to issuance',179,0,'C-O2C-CRD-8',''),(261,'On a monthly / quarterly basis, a system log is extracted and is reviewed by the person other than the one who is entering the data in the system with the supporting records to identify any differences.\r\n',180,0,'C-O2C-CRD-9',''),(262,'Period end reserves are reviewed and approved by an authorized personnel only within accounting / finance department. Further, any adjustment required are also reviewed and approved by authorized personnel within accounting / finance department.',181,0,'C-O2C-CRD-10',''),(263,'At period end,  all proposed adjustments  shall be forward to competent level of authority for review and approval.',182,0,'C-O2C-APC-1',''),(264,'Adjustment once made in the GL shall be reviewed by someone who is not involve in making entries in the GL.',182,0,'C-O2C-APC-2',''),(265,'All proposed adjustment during interim period shall be classified in routine and Non-routine adjustments based on nature and amount of adjustment required. \r\n',183,0,'C-O2C-APC-3',''),(266,'Routine adjustments during interim periods are made by Operational level personnel and reviewed and approved by Managerial level personnel.\r\n',183,0,'C-O2C-APC-4',''),(267,'Non-routine adjustments shall be approved by competent level of authority before entering it into GL.\r\n',183,0,'C-O2C-APC-5',''),(268,'A periodic review of all adjustment made during the interim period shall be carried out by someone who is not involve in making adjustment to GL.\r\n',183,0,'C-O2C-APC-6',''),(269,'Sales recorded are compared against external prescription data. Unusual trends are investigated',184,0,'C-O2C-APC-7',''),(270,'Customers accounts to which adjustments are made shall be linked with their contract or agreement to ensure their validity and authenticity by someone who is not involve in making adjustment to customer accounts.',185,0,'C-O2C-APC-8',''),(271,'An account analysis is performed on a periodic basis by personnel with requisite knowledge; review includes analysis of account details and adjustments recorded during the period. Unusual and/or missing items are investigated and resolved on a timely basis.',186,0,'C-O2C-APC-9',''),(272,'Revenue and its related accounts shall be reconciled with general ledger on periodic basis in order to identify un-reconciling items.\r\n\r\n',187,0,'C-O2C-APC-10',''),(273,'Trends and variances in revenue and its related accounts increasing pre-approved limits shall be reviewed and investigated by competent level of authority.\r\n\r\n',187,0,'C-O2C-APC-11',''),(274,'Unresolved substantial trend and variances, if remain any, shall be forward to higher level of authority for their final approval prior to its reporting & disclosure.\r\n',187,0,'C-O2C-APC-12',''),(275,'Customer data shall be duly protected and accessible only to authorized personnel.\r\n\r\n',188,0,'C-O2C-APC-13',''),(276,'Changes to customers data, if any, shall be made subject to approval from competent level of authority. Log for such changes shall be maintained and reviewed periodically by appropriate level of authority. \r\n \r\n',188,0,'C-O2C-APC-14',''),(277,'Disclosure of customers data, if required, shall be made subject to approval of higher management and in some cases from customers as well. \r\n',188,0,'C-O2C-APC-15',''),(278,'Personnel/ department within the entity shall be devoted task to \r\n  • Identify applicable laws and regulations pertaining to customers data.\r\n  • Ensure compliance with applicable laws and regulations throughout the entity.\r\nPeriodic compliance reviews and/or audits shall be carried out by independent third party to identify instances of non-compliance, if any. \r\n',189,0,'C-O2C-APC-16',''),(279,'Basis for revenue recognition under long term arrangements with customers shall be reviewed and approved by competent level of authority with requisite knowledge to ensure compliance with applicable financial reporting framework.',190,0,'C-O2C-APC-17',''),(280,'Comprehensive policies and procedures shall be in place and approved by competent authority.\r\n\r\n',191,0,'C-O2C-PWC-1',''),(281,'Duties and responsibilities are assigned in such a way so as not to compromise on principle of segregation of duties.\r\nAuthorization, recording and custody of cash or fund shall be entrusted to one personnel.\r\n',192,0,'C-O2C-PWC-2',''),(282,'Third party’s operations shall be duly evaluated prior to opting for any sort of outsourcing arrangement.\r\n\r\nPeriodic reviews or audits shall be carried out in order to ensure that third party operations are in lined with company’s policies and procedures.\r\n',193,0,'C-O2C-PWC-3',''),(283,'System and other necessary data shall be duly protected and accessible only to authorized personnel.\r\n\r\n\r\n',194,0,'C-O2C-PWC-4',''),(284,'Changes to data, if any, shall be made subject to approval from competent level of authority. Log for such changes shall be maintained and reviewed periodically by appropriate level of authority. \r\n',195,0,'C-O2C-PWC-5',''),(285,'Competent staff is hired and trained to carry out recording and maintenance of relevant information.\r\n\r\n',196,0,'C-O2C-PWC-6',''),(286,'Relevant information is reviewed by competent authority with requisite knowledge to ensure that information is correct and up to date. Differences, if any, are identified and resolved on timely basis.',197,0,'C-O2C-PWC-7',''),(287,'Every transaction of sale shall be acknowledged from customer and validated from supporting documents prior to its recording in the system.\r\n\r\n',198,0,'C-O2C-PWC-8',''),(288,'Statement of transactions recorded in revenue and accounts receivable accounts shall be reviewed and approved by competent level of authority to ensure it is validated and authentic.\r\n',198,0,'C-O2C-PWC-9',''),(289,'Sales and Accounts Receivable subsidiary ledgers shall be reconciled with General Ledger on periodic basis and unseal differences shall be investigated and resolved.',198,0,'C-O2C-PWC-10',''),(290,'Staff or department is dedicated in order to facilitate and handle customers queries.',199,0,'C-O2C-PWC-11',''),(291,'Log is maintained for all customers queries and are resolved on timely basis.\r\n',199,0,'C-O2C-PWC-12',''),(292,'Log is reviewed by competent authority on periodic basis in order to ensure that customers are facilitated on timely basis.\r\n',199,0,'C-O2C-PWC-13',''),(293,'A manual comparison is performed between the prices on the sales order and the authorized price master information. Differences are investigated and resolved on a timely basis.',200,0,'C-O2C-OP-1',''),(294,'A system generated exception report that identifies sales orders over a predetermined quantity threshold is reviewed to ensure that appropriate approvals were obtained on a periodic basis.',201,0,'C-O2C-OP-2',''),(295,'Sales orders and outstanding receivables are compared to established credit limits before a new order is processed. Orders in excess of credit limits are placed on hold, investigated and resolved with appropriate approvals.\r\n',202,0,'C-O2C-OP-3',''),(296,'Prior to signature all contracts and contract variations are subject to review and approval. Review and approval is required for all non-standard contract terms, contract variations and change orders.\r\n',203,0,'C-O2C-OP-4',''),(297,'Terms granted outside those specified in the master file must be approved in the system by authorized personnel for processing to continue.\r\n',204,0,'C-O2C-OP-5',''),(298,'Prices granted outside those specified in the master file must be approved in the system by authorized personnel for processing to continue.\r\nMonthly review of price Masterfile and exception report by supply chain team. Exception report carries all the changes made by any individual with time and date log.\r\n',205,0,'C-O2C-OP-6',''),(299,'Sales orders are reviewed and approved prior to processing. A review includes examination of evidence for the customer\'s initiation of the order (e.g., customer purchase orders, confirmation with purchasing officers at the customer, etc.).\r\n',206,0,'C-O2C-OP-7',''),(300,'Customer contracts are reviewed and approved by finance/accounting in addition to commercial and legal.\r\n',207,0,'C-O2C-OP-8',''),(301,'Sales order prices are manually populated based on the authorized price master. Prices granted outside those specified in the price master must be approved.',208,0,'C-O2C-OP-9',''),(302,'When customers are opened in system, one key field is made compulsory like VAT number therefore preventing the risk of duplication. Furthermore system often notifies if a similar name customer is opened.',209,0,'C-O2C-OP-10',''),(303,'Sales team on a daily or weekly basis review all outstanding orders for closure. ',210,0,'C-O2C-OP-11',''),(304,'Most ERP systems have in built control to only process an order if the required quantity is appearing in inventory',211,0,'C-O2C-OP-12',''),(305,'A manual comparison is performed between the control totals of invoices generated and total shipments per the shipping system. Differences are investigated and resolved on a timely basis.\r\nPicking list is generated which lists the details of shipments dispatched, if an order has multiple despatches such list can be used to track shipments.\r\n',212,0,'C-O2C-I-1',''),(306,'A manual comparison is performed between invoices recorded and shipment logs at period end to ensure that proper cut-off has been achieved. Differences are investigated and resolved on a timely basis.\r\nIf order processing and invoicing is synced and is systemized, there is no need for manual comparison. System automatically informs any deviation from sales order if its pricing or quantity of shipment delivered. \r\n',213,0,'C-O2C-I-2',''),(307,'The system performs mathematical calculations automatically for invoices.\r\n',214,0,'C-O2C-I-3',''),(308,'Price review is key when inputting prices in the system. Review of exception reports on a monthly basis. 4 eye rule should also be implemented for matching order, invoice and despatch note for transparency.',215,0,'109110','0'),(309,'The invoicing system automatically generates invoices on shipment.',216,0,'C-O2C-I-5',''),(310,'Invoices are sequentially numbered and a check is performed to ensure documents are not missing or duplicated or fall outside of a specified range of numbers. Differences are investigated and resolved on a timely basis.\r\n',217,0,'C-O2C-I-6',''),(311,'For long term contracts to construct significant assets or for system implementation services customer acceptance documents are obtained and checked prior to raising invoices.\r\n\r\n',218,0,'C-O2C-I-7',''),(312,'In case of services, service completion certificate is issued and acknowledged by customer, detailing all the services performed for invoice issuance purposes.',218,0,'C-O2C-I-8',''),(313,'For \"Bill and Hold\" arrangements customer acceptance documents are obtained and checked prior to raising invoices.',219,0,'C-O2C-I-9',''),(314,'The system generated exception report indicating invoices billed not shipped is investigated and resolved on a timely basis.\r\nOutstanding orders report can also be reviewed to check the status of unbilled invoices depending on type of ERP system. ',220,0,'C-O2C-I-10',''),(315,'The system produces exception reports for invoices over a specified amount and invoices containing unusual prices, terms, and discounts. The exceptions are investigated and resolved on a timely basis.\r\n',221,0,'C-O2C-I-11',''),(316,'The system prevents duplicate invoices from being generated for each shipment.',222,0,'C-O2C-I-12',''),(317,'The system populates the invoice price based on the price on an approved pricing source (e.g. sales order, price master file, etc.).',223,0,'C-O2C-I-13',''),(318,'All reversals and adjustments should be approved by head of department.',224,0,'C-O2C-I-14',''),(319,'control',225,0,'101121','0');
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
  `messageread` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`toDoId`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `todo`
--

LOCK TABLES `todo` WRITE;
/*!40000 ALTER TABLE `todo` DISABLE KEYS */;
INSERT INTO `todo` VALUES (22,'todo by head',116,'2018-11-30',118,29,'todoid chck',76,1),(23,'task',116,'2018-12-11',116,29,'re[p task',75,1),(24,'heat todo lead',117,'2018-12-12',117,29,'lead replying head',74,1),(25,'head todo to mgm',119,'2018-12-13',116,29,'hello mgm ',74,1),(26,'mgm to head todo',116,'2018-12-13',116,29,'rep to mgm',74,1),(27,'mgm to lead',117,'2018-12-13',117,29,'leadinggggg mgm',74,1),(28,'hi',117,'2018-12-07',116,29,NULL,75,0),(29,'this is a test text',119,'2018-12-07',116,29,NULL,74,0),(30,'head assigning todo to mngmnt',119,'2018-12-13',116,29,'mngmnt replying to head',74,1),(31,'lead check to head',116,'2018-12-10',117,29,NULL,74,0),(32,'hello auditor from head',118,'2018-12-14',116,29,NULL,74,0),(33,'thats good',117,'2018-12-13',116,29,NULL,74,0),(34,'All is working good.',119,'2018-12-18',118,29,NULL,84,0);
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
  `description` varchar(105) NOT NULL DEFAULT '',
  `assignedBy` varchar(45) NOT NULL DEFAULT '',
  `assignedTo` varchar(45) NOT NULL DEFAULT '',
  `date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `respond` varchar(105) NOT NULL DEFAULT '',
  `todoId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`todologsId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `todologs`
--

LOCK TABLES `todologs` WRITE;
/*!40000 ALTER TABLE `todologs` DISABLE KEYS */;
INSERT INTO `todologs` VALUES (7,'todo by head','118','116','2018-11-30 00:00:00','rep to head',NULL),(8,'heat todo lead','116','117','2018-12-12 00:00:00','lead replying head',NULL),(9,'mgm to head todo','119','116','2018-12-13 00:00:00','rep to mgm',NULL),(10,'mgm to lead','119','117','2018-12-13 00:00:00','rep to lead',NULL),(11,'mgm to lead','117','117','2018-12-13 00:00:00','leadinggggg mgm',NULL),(12,'head todo to mgm','116','119','2018-12-13 00:00:00','mgm rep head',NULL),(13,'head assigning todo to mngmnt','116','119','2018-12-13 00:00:00','mngmnt replying to head',NULL),(14,'task','116','116','2018-12-11 00:00:00','re[p task',NULL),(15,'todo by head','118','116','2018-11-30 00:00:00','todoid chck',22),(16,'head todo to mgm','116','119','2018-12-13 00:00:00','hello mgm ',25),(17,'hello korgentlead from head','116','117','2018-12-13 00:00:00','hi korgenthead from lead',33),(18,'hello korgentlead from head','116','117','2018-12-13 00:00:00','how are you korgentlead?',33),(19,'how are you korgentlead?','116','117','2018-12-13 00:00:00','yes m really great..thankyou',33),(20,'how are you korgentlead?','116','117','2018-12-13 00:00:00','thats good',33),(21,'This is test job to check the accuracy of the system','118','119','2018-12-18 00:00:00','Also check the IR as well',34),(22,'This is test job to check the accuracy of the system','118','119','2018-12-18 00:00:00','Also check the IR as well',34),(23,'This is test job to check the accuracy of the system','118','119','2018-12-18 00:00:00','Ok, mgm is receiving to do task',34),(24,'This is test job to check the accuracy of the system','118','119','2018-12-18 00:00:00','Ok, mgm is receiving to do task',34),(25,'This is test job to check the accuracy of the system','118','119','2018-12-18 00:00:00','All is working good.',34),(26,'This is test job to check the accuracy of the system','118','119','2018-12-18 00:00:00','All is working good.',34);
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
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (0,'no user','123','123',0),(1,'faheem','faheem','test@email.com',1),(2,'test','test','hy1@email.com',2),(3,'hyadmin','hyadmin','hyad',3),(5,'hy2','hy2','hy2@emai.com',5),(8,'hy3','hy3','hy3',8),(9,'mgm','mgm','mgm',9),(114,'korgentadmin@email.com','korgentadmin','korgentadmin@email.com',115),(115,'korgenthead@gmail.com','korgenthead','korgenthead@email.com',116),(116,'korgentlead@email.com','korgentlead','korgentlead@email.com',117),(117,'korgentauditor@email.com','korgentauditor','korgentauditor@email.com',118),(118,'korgentmgm@email.com','korgentmgm','korgentmgm@email.com',119),(119,'hyphensolutions@email.com','hyphen','hyphensolutions@email.com',120),(120,'hyphenhead@email.com.com','hyphen','hyphenhead@email.com.com',121),(121,'hyphenteamlead@email.com','hyphen','hyphenteamlead@email.com',122),(122,'hyphenauditor@email.com','hyphen','hyphenauditor@email.com',123),(123,'hyphenmanagement@email.com','hyphen','hyphenmanagement@email.com',124),(124,'abilitetestadmin@email.com','abilite','abilitetestadmin@email.com',125),(125,'abilitetesthead@email.com','abilite','abilitetesthead@email.com',126),(126,'abilitetestlead@email.com','abilite','abilitetestlead@email.com',127),(127,'abilitetestauditor@email.com','abilite','abilitetestauditor@email.com',128),(128,'abilitetestmanaganement@email.com','abilite','abilitetestmanaganement@email.com',129);
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

-- Dump completed on 2018-12-22  9:31:45
