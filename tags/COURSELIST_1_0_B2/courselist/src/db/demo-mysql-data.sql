-- MySQL dump 10.9
--
-- Host: localhost    Database: courselist
-- ------------------------------------------------------
-- Server version	4.1.15-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `c_cl_course`
--

DROP TABLE IF EXISTS `c_cl_course`;
CREATE TABLE `c_cl_course` (
  `course_id` varchar(128) NOT NULL default '',
  `prefix` varchar(10) NOT NULL default '',
  `course_code` varchar(10) NOT NULL default '',
  `course_name` varchar(255) NOT NULL default '',
  `description` text,
  `co_requisites` varchar(255) default NULL,
  `is_disabled` tinyint(1) default NULL,
  `teacher_id` varchar(128) default NULL,
  `credit` float default NULL,
  PRIMARY KEY  (`course_id`),
  KEY `FKB5DAD3D5A781800E` (`teacher_id`),
  CONSTRAINT `FKB5DAD3D5A781800E` FOREIGN KEY (`teacher_id`) REFERENCES `c_t_user` (`user_id`)
);

--
-- Dumping data for table `c_cl_course`
--


/*!40000 ALTER TABLE `c_cl_course` DISABLE KEYS */;
LOCK TABLES `c_cl_course` WRITE;
INSERT INTO `c_cl_course` VALUES ('8a9681e2079b7b4201079b7cf1c30001','CS','5390','Computer Animation','animation basics','',1,'8a97857c079c023a01079c0b348d0007',0),('8a9681e2079b7b4201079b7faa4f0002','CS','5389','Computer Graphics','graphix','',1,'8a97857c0791f147010791f16dd60013',0),('8a9681e207a19c4a0107a1b3f847000e','CS','%349','Automata Theory','','',1,NULL,0),('8a9681e207a19c4a0107a1b47ebb000f','CS','5349','Automata Theory','','',0,'8a9681e2079b7b4201079b82e0960004',0),('8a9681e207a19c4a0107a1b4ee600010','CS','5375','Principles of Unix','','',0,'8a97857c079c023a01079c0935630002',0),('8a9681e207a19c4a0107a1b579350011','CS','5390','Computer Networks','','',0,'8a97857c079c023a01079c0b348d0007',0),('8a9681e207a19c4a0107a1b5e05d0012','CS','6304','Computer Architecture','','',0,'8a97857c079c023a01079c0b61300008',0),('8a9681e207a19c4a0107a1b655220013','CS','6320','Natural Language Processing','','',0,'8a97857c079c023a01079c0935630002',0),('8a9681e207a19c4a0107a1b6c91f0014','CS','6324','Information Security','','',0,'8a97857c079c023a01079c0a49e10005',0),('8a9681e207a19c4a0107a1b728880015','CS','6353','Compiler Construction','','',0,'8a97857c0791f147010791f16dd60014',0),('8a9681e207a19c4a0107a1b75a360016','CS','6360','Database Design','','',0,'8a97857c079c023a01079c0935630002',0),('8a9681e207a19c4a0107a1bcb2c8001c','CS','6322','Information Retrieval','','',0,'8a97857c079c023a01079c0b348d0007',0),('8a9681e207a19c4a0107a1bd8bee001d','CS','6354','Advanced Software Engineering','','',0,'8a97857c079c023a01079c0a49e10005',0),('8a9681e207a19c4a0107a1bdfc29001e','CS','6364','Artificial Intelligence','','',0,'8a9681e2079b7b4201079b82e0960004',0),('8a9681e207a19c4a0107a1be427f001f','CS','6368','Telecommunication Network Management','','',0,'8a97857c0791f147010791f16dd60013',0),('8a9681e207a19c4a0107a1bec2740020','CS','6379','Biological Database Systems and Data Mining','','instructor permission',0,'8a97857c079c023a01079c0b348d0007',0),('8a9681e207a19c4a0107a1bf27890021','CS','6381','Combinatorics and Graph Algorithms','','',0,'8a97857c079c023a01079c0a49e10005',0),('8a97857c0791f147010791f16dfe0029','CS','5301','Adv Prof & Tech Communication','','',0,'8a9681e2079b7b4201079b82e0960004',3),('8a97857c0791f147010791f16dfe002a','CS','5303','Computer Science I','Introduction to Computer Science','',0,'8a97857c0791f147010791f16dd60013',3),('8a97857c0791f147010791f16dfe002b','CS','5330','CS II',NULL,NULL,0,'8a97857c0791f147010791f16dd60014',3),('8a97857c0791f147010791f16dfe002c','CS','5333','Discrete Structures',NULL,NULL,0,'8a97857c0791f147010791f16dd60013',3),('8a97857c0791f147010791f16e08002d','CS','5335','Programming Projects in C/C++',NULL,'CS 5303, CS 5330 or equivalent experience',0,'8a97857c0791f147010791f16dd60014',2),('8a97857c0791f147010791f16e08002e','CS','5336','Programming Projects in JAVA',NULL,'CS 5303 or equivalent experience',0,'8a97857c0791f147010791f16dd60013',2),('8a97857c0791f147010791f16e08002f','CS','5343','Algorithm Analysis & Data Structures','','CS 5303, CS 5333',0,'8a97857c079c023a01079c0b61300008',3),('8a97857c0791f147010791f16e080030','CS','5348','Operating Systems Concepts','','CS 5330, CS 5343 & working knowledge of C & UNIX',0,'8a97857c079c023a01079c0b61300008',3),('8a97857c0791f1ba010791f359cb0001','CS','5999','computer animation','fjkfjdsa;','',1,'8a97857c0791f147010791f16dd60013',0),('8a97857c079c023a01079c0504660001','CS','5354','Software Engineering','','CS 5343',0,'8a97857c079c023a01079c0a49e10005',0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `c_cl_course` ENABLE KEYS */;

--
-- Table structure for table `c_cl_course_prerequisite`
--

DROP TABLE IF EXISTS `c_cl_course_prerequisite`;
CREATE TABLE `c_cl_course_prerequisite` (
  `course_prerequisite_id` varchar(128) NOT NULL default '',
  `course_id` varchar(128) NOT NULL default '',
  `prerequisite_id` varchar(128) NOT NULL default '',
  PRIMARY KEY  (`course_id`,`prerequisite_id`),
  KEY `FK8FDA9AE0736D4B8A` (`prerequisite_id`),
  KEY `FK8FDA9AE0F0C99805` (`course_id`),
  CONSTRAINT `FK8FDA9AE0736D4B8A` FOREIGN KEY (`prerequisite_id`) REFERENCES `c_cl_course` (`course_id`),
  CONSTRAINT `FK8FDA9AE0F0C99805` FOREIGN KEY (`course_id`) REFERENCES `c_cl_course` (`course_id`)
);

--
-- Dumping data for table `c_cl_course_prerequisite`
--


/*!40000 ALTER TABLE `c_cl_course_prerequisite` DISABLE KEYS */;
LOCK TABLES `c_cl_course_prerequisite` WRITE;
INSERT INTO `c_cl_course_prerequisite` VALUES ('8a9681e2079b7b4201079b80375f0003','8a9681e2079b7b4201079b7faa4f0002','8a97857c0791f147010791f16dfe002b'),('8a9681e207a19c4a0107a1b882d90017','8a9681e207a19c4a0107a1b47ebb000f','8a97857c0791f147010791f16dfe002c'),('8a9681e207a19c4a0107a1ba1a8b0018','8a9681e207a19c4a0107a1b4ee600010','8a97857c079c023a01079c0504660001'),('8a9681e207a19c4a0107a1ba714f0019','8a9681e207a19c4a0107a1b579350011','8a97857c0791f147010791f16e08002f'),('8a9681e207a19c4a0107a1bbc46e001b','8a9681e207a19c4a0107a1b655220013','8a97857c0791f147010791f16e08002f'),('8a9681e207a19c4a0107a1bae48e001a','8a9681e207a19c4a0107a1b6c91f0014','8a97857c0791f147010791f16e08002f'),('8a9681e207a19c4a0107a1c01b710024','8a9681e207a19c4a0107a1b728880015','8a9681e207a19c4a0107a1b47ebb000f'),('8a9681e207a19c4a0107a1c00a1d0023','8a9681e207a19c4a0107a1b728880015','8a97857c0791f147010791f16e08002f'),('8a9681e207a19c4a0107a1c0ca610026','8a9681e207a19c4a0107a1b75a360016','8a97857c0791f147010791f16e08002f'),('8a9681e207a19c4a0107a1bfa8330022','8a9681e207a19c4a0107a1bcb2c8001c','8a97857c0791f147010791f16e08002f'),('8a9681e207a19c4a0107a1c07e2d0025','8a9681e207a19c4a0107a1bd8bee001d','8a97857c079c023a01079c0504660001'),('8a9681e207a19c4a0107a1c11b510027','8a9681e207a19c4a0107a1bdfc29001e','8a97857c0791f147010791f16e08002f'),('8a9681e207a19c4a0107a1c167030028','8a9681e207a19c4a0107a1be427f001f','8a9681e207a19c4a0107a1b579350011'),('8a9681e207a19c4a0107a1c1e11a0029','8a9681e207a19c4a0107a1bec2740020','8a9681e207a19c4a0107a1b47ebb000f'),('8a9681e207a19c4a0107a1c29e3e002b','8a9681e207a19c4a0107a1bf27890021','8a9681e207a19c4a0107a1bd8bee001d'),('8a9681e207a19c4a0107a1c2561b002a','8a9681e207a19c4a0107a1bf27890021','8a97857c0791f147010791f16e08002f'),('8a97857c0791f147010791f16e120031','8a97857c0791f147010791f16dfe002b','8a97857c0791f147010791f16dfe002a'),('8a97857c0791f147010791f16e120032','8a97857c0791f147010791f16e080030','8a97857c0791f147010791f16e08002f');
UNLOCK TABLES;
/*!40000 ALTER TABLE `c_cl_course_prerequisite` ENABLE KEYS */;

--
-- Table structure for table `c_cl_student_course`
--

DROP TABLE IF EXISTS `c_cl_student_course`;
CREATE TABLE `c_cl_student_course` (
  `student_course_id` varchar(128) NOT NULL default '',
  `course_id` varchar(128) NOT NULL default '',
  `student_id` varchar(128) NOT NULL default '',
  `score` float default NULL,
  `relation_type` varchar(10) NOT NULL default '',
  PRIMARY KEY  (`student_course_id`),
  KEY `FKDF510C59F0C99805` (`course_id`),
  KEY `FKDF510C59B1197F15` (`student_id`),
  CONSTRAINT `FKDF510C59B1197F15` FOREIGN KEY (`student_id`) REFERENCES `c_t_user` (`user_id`),
  CONSTRAINT `FKDF510C59F0C99805` FOREIGN KEY (`course_id`) REFERENCES `c_cl_course` (`course_id`)
);

--
-- Dumping data for table `c_cl_student_course`
--


/*!40000 ALTER TABLE `c_cl_student_course` DISABLE KEYS */;
LOCK TABLES `c_cl_student_course` WRITE;
INSERT INTO `c_cl_student_course` VALUES ('8a9681e2079b7b4201079b8c1dfa0005','8a97857c0791f147010791f16dfe002a','8a97857c0791f147010791f16dd60015',0,'finished'),('8a9681e207aad7200107ab0722d60002','8a97857c0791f147010791f16e08002d','8a97857c0791f147010791f16dd60015',0,'taking'),('8a9681e207aad7200107ab1eac7e0003','8a97857c0791f147010791f16dfe002a','8a97857c0791f147010791f16dd60015',100,'finished'),('8a9681e207aad7200107ab513a620018','8a97857c0791f147010791f16dfe0029','8a9681e207a19c4a0107a1c6ab6f002c',0,'taking'),('8a9681e207aad7200107ab5161d10019','8a97857c0791f147010791f16dfe002a','8a9681e207a19c4a0107a1c6ab6f002c',100,'finished'),('8a9681e207aad7200107ab51954c001a','8a97857c0791f147010791f16e08002f','8a9681e207a19c4a0107a1c6ab6f002c',100,'finished'),('8a9681e207aad7200107ab51d479001b','8a97857c079c023a01079c0504660001','8a9681e207a19c4a0107a1c6ab6f002c',100,'taking'),('8a9681e207aad7200107ab54437a001c','8a97857c0791f147010791f16dfe0029','8a9681e207a19c4a0107a1c713b0002d',0,'taking'),('8a9681e207aad7200107ab545a16001d','8a97857c0791f147010791f16dfe002a','8a9681e207a19c4a0107a1c713b0002d',100,'finished'),('8a9681e207aad7200107ab549bf7001e','8a97857c0791f147010791f16e08002e','8a9681e207a19c4a0107a1c713b0002d',100,'finished'),('8a9681e207aad7200107ab55840e001f','8a97857c0791f147010791f16dfe002a','8a9681e207a19c4a0107a1c776b2002e',100,'finished'),('8a9681e207aad7200107ab55a29b0020','8a97857c0791f147010791f16e08002d','8a9681e207a19c4a0107a1c776b2002e',0,'taking'),('8a9681e207aad7200107ab55c3590021','8a9681e207a19c4a0107a1b5e05d0012','8a9681e207a19c4a0107a1c776b2002e',0,'taking'),('8a9681e207aad7200107ab567ba30022','8a97857c0791f147010791f16dfe002c','8a9681e207a19c4a0107a1c7c503002f',80,'finished'),('8a9681e207aad7200107ab56959d0023','8a97857c0791f147010791f16dfe0029','8a9681e207a19c4a0107a1c7c503002f',0,'taking'),('8a9681e207aad7200107ab56bfaa0024','8a97857c0791f147010791f16e08002f','8a9681e207a19c4a0107a1c7c503002f',100,'finished'),('8a9681e207ae6a2b0107ae6c479b0001','8a97857c0791f147010791f16dfe002a','8a9681e207a19c4a0107a1c940ef0034',70,'finished'),('8a9681e207ae6a2b0107ae6d05b90002','8a97857c0791f147010791f16dfe002a','8a9681e207a19c4a0107a1c7fe660030',80,'finished'),('8a9681e207ae6a2b0107ae6d1bfb0003','8a97857c0791f147010791f16dfe002a','8a9681e207a19c4a0107a1c8f7320033',80,'finished'),('8a9681e207ae6a2b0107ae6d3c050004','8a97857c0791f147010791f16dfe002a','8a9681e207a19c4a0107a1c984ce0035',80,'finished'),('8a9681e207ae6a2b0107ae6d57990005','8a97857c0791f147010791f16dfe002a','8a9681e207a19c4a0107a1c891a40032',70,'finished'),('8a9681e207ae6a2b0107ae6dbc360006','8a97857c0791f147010791f16dfe002a','8a9681e207a19c4a0107a1c7c503002f',100,'finished'),('8a9681e207ae6a2b0107ae6e89b40007','8a97857c0791f147010791f16dfe002b','8a9681e207a19c4a0107a1c940ef0034',70,'finished'),('8a9681e207ae6a2b0107ae6f24cf0008','8a97857c0791f147010791f16dfe002b','8a9681e207a19c4a0107a1c713b0002d',80,'finished'),('8a9681e207ae6a2b0107ae6ffdeb0009','8a97857c0791f147010791f16dfe002a','8a97857c0791f147010791f16dd60016',100,'finished'),('8a9681e207ae6a2b0107ae711a45000a','8a97857c0791f147010791f16dfe002b','8a9681e207a19c4a0107a1c7fe660030',80,'finished'),('8a9681e207ae6a2b0107ae713286000b','8a97857c0791f147010791f16dfe002b','8a9681e207a19c4a0107a1c8f7320033',80,'finished'),('8a9681e207ae6a2b0107ae71494b000c','8a97857c0791f147010791f16dfe002b','8a9681e207a19c4a0107a1ca0a790037',80,'finished'),('8a9681e207ae6a2b0107ae7232d5000d','8a97857c0791f147010791f16dfe002b','8a9681e207a19c4a0107a1c7c503002f',100,'finished'),('8a9681e207ae6a2b0107ae7252d4000e','8a97857c0791f147010791f16dfe002b','8a97857c0791f147010791f16dd60016',100,'finished'),('8a9681e207ae6a2b0107ae726c42000f','8a97857c0791f147010791f16dfe002b','8a9681e207a19c4a0107a1c891a40032',60,'finished'),('8a9681e207ae6a2b0107ae72a59b0010','8a97857c0791f147010791f16dfe002b','8a9681e207a19c4a0107a1c984ce0035',80,'finished'),('8a9681e207ae6a2b0107ae7309ca0011','8a97857c0791f147010791f16dfe002b','8a9681e207a19c4a0107a1c776b2002e',100,'finished'),('8a9681e207ae6a2b0107ae7327490012','8a97857c0791f147010791f16dfe002b','8a9681e207a19c4a0107a1c6ab6f002c',100,'finished'),('8a9681e207ae6a2b0107ae74fbed0013','8a97857c0791f147010791f16dfe002a','8a9681e207a19c4a0107a1c9cab60036',80,'finished'),('8a9681e207ae6a2b0107ae7533660014','8a97857c0791f147010791f16dfe002a','8a9681e207a19c4a0107a1ca956c0039',100,'finished'),('8a9681e207ae6a2b0107ae754cc90015','8a97857c0791f147010791f16dfe002a','8a9681e207a19c4a0107a1cacbd6003a',100,'finished'),('8a9681e207ae6a2b0107ae7567ef0016','8a97857c0791f147010791f16dfe002a','8a9681e207a19c4a0107a1cb7aef003c',70,'finished'),('8a9681e207ae6a2b0107ae7580da0017','8a97857c0791f147010791f16dfe002a','8a97857c0791f147010791f16dd60015',70,'finished'),('8a9681e207ae6a2b0107ae7b280d0018','8a97857c0791f147010791f16dfe002a','8a9681e207a19c4a0107a1cbc16d003d',0,'taking'),('8a9681e207ae6a2b0107ae7b48670019','8a97857c0791f147010791f16dfe002a','8a9681e207a19c4a0107a1cc4bfc003e',0,'taking'),('8a9681e207ae6a2b0107ae7b64e1001a','8a97857c0791f147010791f16dfe002a','8a9681e207a19c4a0107a1c847bf0031',0,'taking'),('8a9681e207ae6a2b0107ae7b86d6001b','8a97857c0791f147010791f16dfe002a','8a9681e207a19c4a0107a1ccedb3003f',0,'taking'),('8a9681e207ae6a2b0107ae7b9dd6001c','8a97857c0791f147010791f16dfe002a','8a9681e207a19c4a0107a1ca48a20038',0,'taking'),('8a9681e207ae6a2b0107ae7cf86e001d','8a97857c0791f147010791f16dfe002b','8a9681e207a19c4a0107a1c9cab60036',0,'taking'),('8a9681e207ae6a2b0107ae7d272d001e','8a97857c0791f147010791f16dfe002b','8a9681e207a19c4a0107a1ca956c0039',0,'taking'),('8a9681e207ae6a2b0107ae7d45ba001f','8a97857c0791f147010791f16dfe002b','8a9681e207a19c4a0107a1cacbd6003a',0,'taking'),('8a9681e207ae6a2b0107ae7d78630020','8a97857c0791f147010791f16dfe002b','8a9681e207a19c4a0107a1cb7aef003c',0,'taking'),('8a9681e207ae6a2b0107ae7da61e0021','8a97857c0791f147010791f16dfe002b','8a97857c0791f147010791f16dd60015',0,'taking'),('8a9681e207ae6a2b0107ae7fb3170022','8a97857c0791f147010791f16dfe002c','8a9681e207a19c4a0107a1c713b0002d',80,'finished'),('8a9681e207ae6a2b0107ae7fd0960023','8a97857c0791f147010791f16dfe002c','8a9681e207a19c4a0107a1c6ab6f002c',80,'finished'),('8a9681e207ae6a2b0107ae7fe8eb0024','8a97857c0791f147010791f16dfe002c','8a9681e207a19c4a0107a1c776b2002e',80,'finished'),('8a9681e207ae6a2b0107ae8003990025','8a97857c0791f147010791f16dfe002c','8a97857c0791f147010791f16dd60016',80,'finished'),('8a9681e207ae6a2b0107ae8096410026','8a97857c0791f147010791f16e08002e','8a9681e207a19c4a0107a1c7c503002f',0,'finished'),('8a9681e207ae6a2b0107ae80b8990027','8a97857c0791f147010791f16e08002e','8a9681e207a19c4a0107a1c6ab6f002c',0,'finished'),('8a9681e207ae6a2b0107ae80dd2d0028','8a97857c0791f147010791f16e08002e','8a9681e207a19c4a0107a1c776b2002e',0,'finished'),('8a9681e207ae6a2b0107ae80f5dc0029','8a97857c0791f147010791f16e08002e','8a97857c0791f147010791f16dd60016',0,'finished'),('8a9681e207ae6a2b0107ae81bd53002a','8a97857c0791f147010791f16e08002f','8a97857c0791f147010791f16dd60016',100,'finished'),('8a9681e207ae6a2b0107ae81d327002b','8a97857c0791f147010791f16e08002f','8a9681e207a19c4a0107a1c713b0002d',100,'finished'),('8a9681e207ae6a2b0107ae81ed21002c','8a97857c0791f147010791f16e08002f','8a9681e207a19c4a0107a1c776b2002e',100,'finished'),('8a9681e207ae6a2b0107ae834d46002d','8a97857c079c023a01079c0504660001','8a9681e207a19c4a0107a1c713b0002d',100,'taking'),('8a9681e207ae6a2b0107ae836401002e','8a97857c079c023a01079c0504660001','8a97857c0791f147010791f16dd60016',100,'taking'),('8a9681e207ae6a2b0107ae83828e002f','8a97857c079c023a01079c0504660001','8a9681e207a19c4a0107a1c7c503002f',100,'taking'),('8a9681e207ae6a2b0107ae839b8d0030','8a97857c079c023a01079c0504660001','8a9681e207a19c4a0107a1c776b2002e',100,'taking'),('8a9681e207ae6a2b0107ae83a95c0031','8a97857c079c023a01079c0504660001','8a9681e207a19c4a0107a1c940ef0034',80,'taking'),('8a9681e207ae6a2b0107ae83bdd20032','8a97857c079c023a01079c0504660001','8a9681e207a19c4a0107a1cb7aef003c',80,'taking'),('8a9681e207ae6a2b0107ae83d2ca0033','8a97857c079c023a01079c0504660001','8a9681e207a19c4a0107a1ca956c0039',100,'taking'),('8a9681e207ae6a2b0107ae83ea9c0034','8a97857c079c023a01079c0504660001','8a9681e207a19c4a0107a1cc4bfc003e',70,'taking'),('8a9681e207ae6a2b0107ae8404b40035','8a97857c079c023a01079c0504660001','8a9681e207a19c4a0107a1ca0a790037',80,'taking'),('8a9681e207ae6a2b0107ae85da7b0036','8a9681e207a19c4a0107a1b47ebb000f','8a9681e207a19c4a0107a1c6ab6f002c',0,'taking'),('8a9681e207ae6a2b0107ae86056f0037','8a9681e207a19c4a0107a1b655220013','8a9681e207a19c4a0107a1c6ab6f002c',0,'taking'),('8a9681e207ae6a2b0107ae86415d0038','8a9681e207a19c4a0107a1b75a360016','8a9681e207a19c4a0107a1c6ab6f002c',0,'taking'),('8a9681e207ae6a2b0107ae86b5810039','8a97857c0791f147010791f16e080030','8a9681e207a19c4a0107a1c713b0002d',0,'taking'),('8a9681e207ae6a2b0107ae8705d1003a','8a9681e207a19c4a0107a1b579350011','8a9681e207a19c4a0107a1c713b0002d',0,'taking'),('8a9681e207ae6a2b0107ae875de9003b','8a9681e207a19c4a0107a1b47ebb000f','8a9681e207a19c4a0107a1c713b0002d',0,'taking'),('8a9681e207ae6a2b0107ae87ffd3003c','8a9681e207a19c4a0107a1b579350011','8a9681e207a19c4a0107a1c776b2002e',0,'taking'),('8a9681e207ae6a2b0107ae8844ac003d','8a9681e207a19c4a0107a1b75a360016','8a9681e207a19c4a0107a1c776b2002e',0,'taking'),('8a9681e207ae6a2b0107ae89e2e7003e','8a9681e207a19c4a0107a1b47ebb000f','8a9681e207a19c4a0107a1c7c503002f',0,'taking'),('8a9681e207ae6a2b0107ae8a0174003f','8a9681e207a19c4a0107a1b6c91f0014','8a9681e207a19c4a0107a1c7c503002f',0,'taking'),('8a9681e207ae6a2b0107ae8a50ab0040','8a9681e207a19c4a0107a1bdfc29001e','8a9681e207a19c4a0107a1c7c503002f',0,'taking'),('8a9681e207ae6a2b0107ae8aad890041','8a97857c0791f147010791f16dfe0029','8a97857c0791f147010791f16dd60016',0,'taking'),('8a9681e207ae6a2b0107ae8ace150042','8a97857c0791f147010791f16e080030','8a97857c0791f147010791f16dd60016',0,'taking'),('8a9681e207ae6a2b0107ae8af1b90043','8a9681e207a19c4a0107a1b579350011','8a97857c0791f147010791f16dd60016',0,'taking'),('8a9681e207ae6a2b0107ae8b1d110044','8a9681e207a19c4a0107a1b655220013','8a97857c0791f147010791f16dd60016',0,'taking'),('8a9681e207ae6a2b0107ae8b9dc40045','8a97857c079c023a01079c0504660001','8a9681e207a19c4a0107a1c7fe660030',0,'taking'),('8a9681e207ae6a2b0107ae8bc03b0046','8a97857c0791f147010791f16e08002e','8a9681e207a19c4a0107a1c7fe660030',0,'taking'),('8a9681e207ae6a2b0107ae8c29430047','8a9681e207a19c4a0107a1b5e05d0012','8a9681e207a19c4a0107a1c7fe660030',0,'taking'),('8a9681e207ae6a2b0107ae8c49250048','8a97857c0791f147010791f16e08002f','8a9681e207a19c4a0107a1c7fe660030',0,'taking'),('8a9681e207ae6a2b0107ae8c8fa30049','8a97857c0791f147010791f16dfe0029','8a9681e207a19c4a0107a1c891a40032',0,'taking'),('8a9681e207ae6a2b0107ae8cc78a004a','8a97857c0791f147010791f16dfe002c','8a9681e207a19c4a0107a1c891a40032',0,'taking'),('8a9681e207ae6a2b0107ae8ce621004b','8a97857c0791f147010791f16e08002f','8a9681e207a19c4a0107a1c891a40032',0,'taking'),('8a9681e207ae6a2b0107ae8d61be004c','8a9681e207a19c4a0107a1b5e05d0012','8a9681e207a19c4a0107a1c8f7320033',0,'taking'),('8a9681e207ae6a2b0107ae8d87d8004d','8a97857c0791f147010791f16e08002d','8a9681e207a19c4a0107a1c8f7320033',0,'taking'),('8a9681e207ae6a2b0107ae8da330004e','8a97857c0791f147010791f16dfe0029','8a9681e207a19c4a0107a1c8f7320033',0,'taking'),('8a9681e207ae6a2b0107ae8e265b004f','8a97857c0791f147010791f16dfe002c','8a9681e207a19c4a0107a1c984ce0035',0,'taking'),('8a9681e207ae6a2b0107ae8e5c2e0050','8a97857c0791f147010791f16e08002f','8a9681e207a19c4a0107a1c984ce0035',0,'taking'),('8a9681e207ae6a2b0107ae8e8b3e0051','8a9681e207a19c4a0107a1b5e05d0012','8a9681e207a19c4a0107a1c984ce0035',0,'taking'),('8a9681e207ae6a2b0107ae8eff3b0052','8a97857c0791f147010791f16dfe002c','8a9681e207a19c4a0107a1c9cab60036',0,'taking'),('8a9681e207ae6a2b0107ae8f39340053','8a97857c079c023a01079c0504660001','8a9681e207a19c4a0107a1c9cab60036',0,'taking'),('8a9681e207ae6a2b0107ae8f80160054','8a97857c0791f147010791f16dfe0029','8a9681e207a19c4a0107a1ca0a790037',0,'taking'),('8a9681e207ae6a2b0107ae8fc3870055','8a97857c0791f147010791f16e08002e','8a9681e207a19c4a0107a1ca0a790037',0,'taking'),('8a9681e207ae6a2b0107ae910b4d0056','8a97857c0791f147010791f16dfe0029','8a9681e207a19c4a0107a1ca956c0039',0,'taking'),('8a9681e207ae6a2b0107ae9156190057','8a97857c0791f147010791f16dfe0029','8a9681e207a19c4a0107a1cacbd6003a',0,'taking'),('8a9681e207ae6a2b0107ae9172110058','8a97857c0791f147010791f16dfe002c','8a9681e207a19c4a0107a1cacbd6003a',0,'taking'),('8a9681e207ae6a2b0107ae919eaa0059','8a97857c0791f147010791f16e08002d','8a9681e207a19c4a0107a1cacbd6003a',0,'taking'),('8a9681e207ae6a2b0107ae91eee5005a','8a97857c0791f147010791f16dfe0029','8a9681e207a19c4a0107a1cbc16d003d',0,'taking'),('8a9681e207ae6a2b0107ae920d7c005b','8a97857c0791f147010791f16dfe002c','8a9681e207a19c4a0107a1cbc16d003d',0,'taking'),('8a9681e207ae6a2b0107ae924c6e005c','8a9681e207a19c4a0107a1b5e05d0012','8a9681e207a19c4a0107a1cbc16d003d',0,'taking');
UNLOCK TABLES;
/*!40000 ALTER TABLE `c_cl_student_course` ENABLE KEYS */;

--
-- Table structure for table `c_cl_teacher`
--

DROP TABLE IF EXISTS `c_cl_teacher`;
CREATE TABLE `c_cl_teacher` (
  `teacher_id` varchar(128) NOT NULL default '',
  `user_id` varchar(128) default NULL,
  PRIMARY KEY  (`teacher_id`),
  KEY `FK76984F68E5DDEB85` (`user_id`),
  CONSTRAINT `FK76984F68E5DDEB85` FOREIGN KEY (`user_id`) REFERENCES `c_t_user` (`user_id`)
);

--
-- Dumping data for table `c_cl_teacher`
--


/*!40000 ALTER TABLE `c_cl_teacher` DISABLE KEYS */;
LOCK TABLES `c_cl_teacher` WRITE;
INSERT INTO `c_cl_teacher` VALUES ('8a9681e207a19c4a0107a1b1f3df000c','8a9681e2079b7b4201079b82e0960004'),('8a97857c0791f147010791f16df40027','8a97857c0791f147010791f16dd60013'),('8a97857c0791f147010791f16df40028','8a97857c0791f147010791f16dd60014'),('8a9681e207a19c4a0107a1afe0e00007','8a97857c079c023a01079c0935630002'),('8a9681e207a19c4a0107a1b0454b0008','8a97857c079c023a01079c0a49e10005'),('8a9681e207a19c4a0107a1b09ceb000a','8a97857c079c023a01079c0b348d0007'),('8a9681e207a19c4a0107a1b0ab1e000b','8a97857c079c023a01079c0b61300008');
UNLOCK TABLES;
/*!40000 ALTER TABLE `c_cl_teacher` ENABLE KEYS */;

--
-- Table structure for table `c_t_group`
--

DROP TABLE IF EXISTS `c_t_group`;
CREATE TABLE `c_t_group` (
  `group_id` varchar(128) NOT NULL default '',
  `group_name` varchar(60) NOT NULL default '',
  `description` text,
  `is_disabled` tinyint(1) default NULL,
  `is_system` tinyint(1) default NULL,
  PRIMARY KEY  (`group_id`)
);

--
-- Dumping data for table `c_t_group`
--


/*!40000 ALTER TABLE `c_t_group` DISABLE KEYS */;
LOCK TABLES `c_t_group` WRITE;
INSERT INTO `c_t_group` VALUES ('8a97857c0791f147010791f16cfa0003','admins','Administrators only',0,1),('8a97857c0791f147010791f16de00019','students','Student group',0,1),('8a97857c0791f147010791f16de0001a','teachers','Teacher group',0,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `c_t_group` ENABLE KEYS */;

--
-- Table structure for table `c_t_group_role`
--

DROP TABLE IF EXISTS `c_t_group_role`;
CREATE TABLE `c_t_group_role` (
  `group_role_id` varchar(128) NOT NULL default '',
  `group_id` varchar(128) NOT NULL default '',
  `role_id` varchar(128) NOT NULL default '',
  PRIMARY KEY  (`group_role_id`),
  KEY `FKE532213D40B327A5` (`role_id`),
  CONSTRAINT `FKE532213D40B327A5` FOREIGN KEY (`role_id`) REFERENCES `c_t_role` (`role_id`)
);

--
-- Dumping data for table `c_t_group_role`
--


/*!40000 ALTER TABLE `c_t_group_role` DISABLE KEYS */;
LOCK TABLES `c_t_group_role` WRITE;
INSERT INTO `c_t_group_role` VALUES ('8a97857c0791f147010791f16de0001b','8a97857c0791f147010791f16de00019','8a97857c0791f147010791f16dd60017'),('8a97857c0791f147010791f16dea001c','8a97857c0791f147010791f16de0001a','8a97857c0791f147010791f16dd60018');
UNLOCK TABLES;
/*!40000 ALTER TABLE `c_t_group_role` ENABLE KEYS */;

--
-- Table structure for table `c_t_role`
--

DROP TABLE IF EXISTS `c_t_role`;
CREATE TABLE `c_t_role` (
  `role_id` varchar(128) NOT NULL default '',
  `role_name` varchar(60) NOT NULL default '',
  `description` text,
  `is_disabled` tinyint(1) default NULL,
  `is_system` tinyint(1) default NULL,
  PRIMARY KEY  (`role_id`)
);

--
-- Dumping data for table `c_t_role`
--


/*!40000 ALTER TABLE `c_t_role` DISABLE KEYS */;
LOCK TABLES `c_t_role` WRITE;
INSERT INTO `c_t_role` VALUES ('8a97857c0791f147010791f16d220005','user','For any valid user',0,1),('8a97857c0791f147010791f16d220006','guest','For guest user',0,1),('8a97857c0791f147010791f16d220007','admin','Role for administrators',0,1),('8a97857c0791f147010791f16dd60017','student','Student role',0,1),('8a97857c0791f147010791f16dd60018','teacher','Teacher role',0,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `c_t_role` ENABLE KEYS */;

--
-- Table structure for table `c_t_role_dependency`
--

DROP TABLE IF EXISTS `c_t_role_dependency`;
CREATE TABLE `c_t_role_dependency` (
  `role_dependency_id` varchar(128) NOT NULL default '',
  `role_id` varchar(128) NOT NULL default '',
  `dependency_role_id` varchar(128) NOT NULL default '',
  PRIMARY KEY  (`role_id`,`dependency_role_id`),
  KEY `FK97AA880D40B327A5` (`role_id`),
  KEY `FK97AA880D553B26B1` (`dependency_role_id`),
  CONSTRAINT `FK97AA880D40B327A5` FOREIGN KEY (`role_id`) REFERENCES `c_t_role` (`role_id`),
  CONSTRAINT `FK97AA880D553B26B1` FOREIGN KEY (`dependency_role_id`) REFERENCES `c_t_role` (`role_id`)
);

--
-- Dumping data for table `c_t_role_dependency`
--


/*!40000 ALTER TABLE `c_t_role_dependency` DISABLE KEYS */;
LOCK TABLES `c_t_role_dependency` WRITE;
INSERT INTO `c_t_role_dependency` VALUES ('8a97857c0791f147010791f16d2c0008','8a97857c0791f147010791f16d220005','8a97857c0791f147010791f16d220006'),('8a97857c0791f147010791f16d2c0009','8a97857c0791f147010791f16d220007','8a97857c0791f147010791f16d220007');
UNLOCK TABLES;
/*!40000 ALTER TABLE `c_t_role_dependency` ENABLE KEYS */;

--
-- Table structure for table `c_t_role_permission`
--

DROP TABLE IF EXISTS `c_t_role_permission`;
CREATE TABLE `c_t_role_permission` (
  `role_permission_id` varchar(128) NOT NULL default '',
  `role_id` varchar(128) NOT NULL default '',
  `permission_Type` varchar(255) NOT NULL default '',
  `permission` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`role_permission_id`)
);

--
-- Dumping data for table `c_t_role_permission`
--


/*!40000 ALTER TABLE `c_t_role_permission` DISABLE KEYS */;
LOCK TABLES `c_t_role_permission` WRITE;
INSERT INTO `c_t_role_permission` VALUES ('8a97857c0791f147010791f16d36000a','8a97857c0791f147010791f16d220006','com.cyclopsgroup.tornado.portal.PagePermissionType','/Index.jelly'),('8a97857c0791f147010791f16d36000b','8a97857c0791f147010791f16d220006','com.cyclopsgroup.tornado.portal.PagePermissionType','/waterview/Index.jelly'),('8a97857c0791f147010791f16d36000c','8a97857c0791f147010791f16d220006','com.cyclopsgroup.tornado.portal.PagePermissionType','/waterview/Overview.jelly'),('8a97857c0791f147010791f16d40000d','8a97857c0791f147010791f16d220006','com.cyclopsgroup.tornado.portal.PagePermissionType','/waterview/help/Index.jelly'),('8a97857c0791f147010791f16d40000e','8a97857c0791f147010791f16d220006','com.cyclopsgroup.tornado.portal.PagePermissionType','/waterview/help/about/.*'),('8a97857c0791f147010791f16d40000f','8a97857c0791f147010791f16d220006','com.cyclopsgroup.tornado.portal.PagePermissionType','/waterview/pub/.*'),('8a97857c0791f147010791f16d400010','8a97857c0791f147010791f16d220006','com.cyclopsgroup.tornado.portal.PagePermissionType','/tornado/UserLogin.jelly'),('8a97857c0791f147010791f16d400011','8a97857c0791f147010791f16d220006','com.cyclopsgroup.tornado.portal.PagePermissionType','/tornado/pub/.*'),('8a97857c0791f147010791f16d400012','8a97857c0791f147010791f16d220005','com.cyclopsgroup.tornado.portal.PagePermissionType','/tornado/user/.*'),('8a97857c0791f147010791f16dea0021','8a97857c0791f147010791f16d220006','com.cyclopsgroup.tornado.portal.PagePermissionType','/courselist/Index.jelly'),('8a97857c0791f147010791f16dea0022','8a97857c0791f147010791f16d220006','com.cyclopsgroup.tornado.portal.PagePermissionType','/courselist/course/Index.jelly'),('8a97857c0791f147010791f16dea0023','8a97857c0791f147010791f16d220006','com.cyclopsgroup.tornado.portal.PagePermissionType','/courselist/course/CourseList.jelly'),('8a97857c0791f147010791f16dea0024','8a97857c0791f147010791f16d220006','com.cyclopsgroup.tornado.portal.PagePermissionType','/courselist/course/CourseDetail.jelly'),('8a97857c0791f147010791f16dea0025','8a97857c0791f147010791f16dd60018','com.cyclopsgroup.tornado.portal.PagePermissionType','/courselist/teacher/.*'),('8a97857c0791f147010791f16df40026','8a97857c0791f147010791f16dd60017','com.cyclopsgroup.tornado.portal.PagePermissionType','/courselist/student/.*');
UNLOCK TABLES;
/*!40000 ALTER TABLE `c_t_role_permission` ENABLE KEYS */;

--
-- Table structure for table `c_t_user`
--

DROP TABLE IF EXISTS `c_t_user`;
CREATE TABLE `c_t_user` (
  `user_id` varchar(128) NOT NULL default '',
  `user_name` varchar(30) NOT NULL default '',
  `password` varchar(60) NOT NULL default '',
  `first_name` varchar(60) default NULL,
  `middle_name` varchar(20) default NULL,
  `last_name` varchar(60) default NULL,
  `gender` char(1) default NULL,
  `email` varchar(60) NOT NULL default '',
  `country` varchar(10) default NULL,
  `language` varchar(10) default NULL,
  `birthday` date default NULL,
  `is_disabled` tinyint(1) default NULL,
  `is_system` tinyint(1) default NULL,
  PRIMARY KEY  (`user_id`)
);

--
-- Dumping data for table `c_t_user`
--


/*!40000 ALTER TABLE `c_t_user` DISABLE KEYS */;
LOCK TABLES `c_t_user` WRITE;
INSERT INTO `c_t_user` VALUES ('8a9681e2079b7b4201079b82e0960004','johnny','am9obm55','Johnny','','Vaughn','M','jvaughn@utd.edu','US','en',NULL,0,0),('8a9681e207a19c4a0107a1c6ab6f002c','jcline','amNsaW5l','Jarvis','','Cline','M','jcline@utd.edu','GB','en',NULL,0,0),('8a9681e207a19c4a0107a1c713b0002d','jallen','amFsbGVu','Joshua','P','Allen','M','jallen@utd.edu','GB','en',NULL,0,0),('8a9681e207a19c4a0107a1c776b2002e','jchristin','amNocmlzdGlu','John','','Christin','M','jchristin@utd.edu','GB','en',NULL,0,0),('8a9681e207a19c4a0107a1c7c503002f','cmenken','Y21lbmtlbg==','Chris','','Menken','M','cmenken@utd.edu','GB','en',NULL,0,0),('8a9681e207a19c4a0107a1c7fe660030','ajones','YWpvbmVz','Amanda','','Jones','F','ajones@utd.edu','GB','en',NULL,0,0),('8a9681e207a19c4a0107a1c847bf0031','lsimpson','bHNpbXBzb24=','Lisa','','Simpson','F','lsimpson@utd.edu','GB','en',NULL,0,0),('8a9681e207a19c4a0107a1c891a40032','eralstin','ZXJhbHN0aW4=','Erin','','Ralstin','F','eralstin@utd.edu','GB','en',NULL,0,0),('8a9681e207a19c4a0107a1c8f7320033','bgrohman','Ymdyb2htYW4=','Bryan','','Grohman','M','bgrohman@utd.edu','GB','en',NULL,0,0),('8a9681e207a19c4a0107a1c940ef0034','adurham','YWR1cmhhbQ==','Ashley','','Durham','F','adurham@utd.edu','GB','en',NULL,0,0),('8a9681e207a19c4a0107a1c984ce0035','lthomas','bHRob21hcw==','Lakeicha','','Thomas','F','lthomas@utd.edu','GB','en',NULL,0,0),('8a9681e207a19c4a0107a1c9cab60036','jherrera','amhlcnJlcmE=','Jose','','Herrera','M','jherrera@utd.edu','GB','en',NULL,0,0),('8a9681e207a19c4a0107a1ca0a790037','cmack','Y21hY2s=','Caitlin','','Mack','F','cmack@utd.edu','GB','en',NULL,0,0),('8a9681e207a19c4a0107a1ca48a20038','pdesai','cGRlc2Fp','Prutha','','Desai','F','pdesai@utd.edu','GB','en',NULL,0,0),('8a9681e207a19c4a0107a1ca956c0039','jjones','ampvbmVz','Julius','','Jones','M','jjones@utd.edu','GB','en',NULL,0,0),('8a9681e207a19c4a0107a1cacbd6003a','jhoward','amhvd2FyZA==','Josh','','Howard','M','jhoward@utd.edu','GB','en',NULL,0,0),('8a9681e207a19c4a0107a1cb0eed003b','wmozart','d21vemFydA==','Wolfgang','A','Mozart','M','wmozart@utd.edu','GB','en',NULL,0,0),('8a9681e207a19c4a0107a1cb7aef003c','ptownshend','cHRvd25zaGVuZA==','Pete','','Townshend','M','ptownshend@utd.edu','GB','en',NULL,0,0),('8a9681e207a19c4a0107a1cbc16d003d','dmatthews','ZG1hdHRoZXdz','Dave','','Matthews','M','dmatthews@utd.edu','GB','en',NULL,0,0),('8a9681e207a19c4a0107a1cc4bfc003e','ehamilton','ZWhhbWlsdG9u','Eric','','Hamilton','M','ehamilton@utd.edu','GB','en',NULL,0,0),('8a9681e207a19c4a0107a1ccedb3003f','tmack','dG1hY2s=','Thomas','','Mack','M','tmack@utd.edu','GB','en',NULL,0,0),('8a9681e207a19c4a0107a1cd31ec0040','jthompson','anRob21wc29u','Jenny','','Thompson','F','jthompson@utd.edu','GB','en',NULL,0,0),('8a97857c0791f147010791f16cd20001','guest','cGFzc3dvcmQ=','System',NULL,'Guest',NULL,'guest@cyclopdgroup.com','US','en',NULL,0,1),('8a97857c0791f147010791f16cf00002','admin','YWRtaW4=','System',NULL,'Administrator',NULL,'admin@cyclopsgroup.com','US','en',NULL,0,1),('8a97857c0791f147010791f16dd60013','john','am9obg==','John',NULL,'Goodman',NULL,'john@cyclopdgroup.com','US','en',NULL,0,0),('8a97857c0791f147010791f16dd60014','tracy','dHJhY3k=','Tracy',NULL,'Mac',NULL,'tracy@cyclopdgroup.com','US','en',NULL,0,0),('8a97857c0791f147010791f16dd60015','tim','dGlt','Tim',NULL,'Allen',NULL,'tim@cyclopdgroup.com','US','en',NULL,0,0),('8a97857c0791f147010791f16dd60016','jiaqi','amlhcWk=','Jiaqi',NULL,'Guo',NULL,'john@cyclopdgroup.com','CN','zh',NULL,0,0),('8a97857c079c023a01079c0935630002','stewart','c3Rld2FydA==','Jon','','Stewart','M','jstewart@utd.edu','US','en',NULL,0,0),('8a97857c079c023a01079c098ff30003','mack','bWFjaw==','Mack','N','Tosh','M','mntosh@utd.edu','US','en',NULL,1,0),('8a97857c079c023a01079c09f5940004','howard','aG93YXJk','Josh','','Howard','M','jhoward@utd.edu','US','en',NULL,1,0),('8a97857c079c023a01079c0a49e10005','vu','dnU=','Hieu','D','Vu','M','drvu@utd.edu','US','en',NULL,0,0),('8a97857c079c023a01079c0aa8000006','palmer','cGFsbWVy','Mary','A','Palmer','F','mpalmer@utd.edu','US','en',NULL,1,0),('8a97857c079c023a01079c0b348d0007','nat','bmF0','Natalie','','Gonzales','F','ngonzales@utd.edu','US','en',NULL,0,0),('8a97857c079c023a01079c0b61300008','ivor','aXZvcg==','Ivor','','Page','M','ivor@utd.edu','US','en',NULL,0,0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `c_t_user` ENABLE KEYS */;

--
-- Table structure for table `c_t_user_group`
--

DROP TABLE IF EXISTS `c_t_user_group`;
CREATE TABLE `c_t_user_group` (
  `user_group_id` varchar(128) NOT NULL default '',
  `user_id` varchar(128) NOT NULL default '',
  `group_id` varchar(128) NOT NULL default '',
  PRIMARY KEY  (`user_id`,`group_id`),
  KEY `FKB6AA812325910F` (`group_id`),
  KEY `FKB6AA812E5DDEB85` (`user_id`),
  CONSTRAINT `FKB6AA812325910F` FOREIGN KEY (`group_id`) REFERENCES `c_t_group` (`group_id`),
  CONSTRAINT `FKB6AA812E5DDEB85` FOREIGN KEY (`user_id`) REFERENCES `c_t_user` (`user_id`)
);

--
-- Dumping data for table `c_t_user_group`
--


/*!40000 ALTER TABLE `c_t_user_group` DISABLE KEYS */;
LOCK TABLES `c_t_user_group` WRITE;
INSERT INTO `c_t_user_group` VALUES ('8a9681e207aad7200107ab382500000d','8a9681e207a19c4a0107a1c6ab6f002c','8a97857c0791f147010791f16de00019'),('8a9681e207aad7200107ab37bad5000b','8a9681e207a19c4a0107a1c713b0002d','8a97857c0791f147010791f16de00019'),('8a9681e207aad7200107ab37ebd9000c','8a9681e207a19c4a0107a1c776b2002e','8a97857c0791f147010791f16de00019'),('8a9681e207aad7200107ab371a040007','8a9681e207a19c4a0107a1c7c503002f','8a97857c0791f147010791f16de00019'),('8a9681e207aad7200107ab369eb70004','8a9681e207a19c4a0107a1c7fe660030','8a97857c0791f147010791f16de00019'),('8a9681e207aad7200107ab3e85db0012','8a9681e207a19c4a0107a1c847bf0031','8a97857c0791f147010791f16de00019'),('8a9681e207aad7200107ab379317000a','8a9681e207a19c4a0107a1c891a40032','8a97857c0791f147010791f16de00019'),('8a9681e207aad7200107ab36c5530005','8a9681e207a19c4a0107a1c8f7320033','8a97857c0791f147010791f16de00019'),('8a9681e207a19c4a0107a1d346bb0041','8a9681e207a19c4a0107a1c940ef0034','8a97857c0791f147010791f16de00019'),('8a9681e207aad7200107ab3eaba50013','8a9681e207a19c4a0107a1c984ce0035','8a97857c0791f147010791f16de00019'),('8a9681e207aad7200107ab38533d000e','8a9681e207a19c4a0107a1c9cab60036','8a97857c0791f147010791f16de00019'),('8a9681e207aad7200107ab36eca30006','8a9681e207a19c4a0107a1ca0a790037','8a97857c0791f147010791f16de00019'),('8a9681e207aad7200107ab3ecf520014','8a9681e207a19c4a0107a1ca48a20038','8a97857c0791f147010791f16de00019'),('8a9681e207aad7200107ab38b5090010','8a9681e207a19c4a0107a1ca956c0039','8a97857c0791f147010791f16de00019'),('8a9681e207aad7200107ab388726000f','8a9681e207a19c4a0107a1cacbd6003a','8a97857c0791f147010791f16de00019'),('8a9681e207aad7200107ab3f74a30017','8a9681e207a19c4a0107a1cb0eed003b','8a97857c0791f147010791f16de00019'),('8a9681e207aad7200107ab3effe80015','8a9681e207a19c4a0107a1cb7aef003c','8a97857c0791f147010791f16de00019'),('8a9681e207aad7200107ab37428b0008','8a9681e207a19c4a0107a1cbc16d003d','8a97857c0791f147010791f16de00019'),('8a9681e207aad7200107ab376bf80009','8a9681e207a19c4a0107a1cc4bfc003e','8a97857c0791f147010791f16de00019'),('8a9681e207aad7200107ab3f22230016','8a9681e207a19c4a0107a1ccedb3003f','8a97857c0791f147010791f16de00019'),('8a9681e207aad7200107ab3e59070011','8a9681e207a19c4a0107a1cd31ec0040','8a97857c0791f147010791f16de00019'),('8a97857c0791f147010791f16d180004','8a97857c0791f147010791f16cf00002','8a97857c0791f147010791f16cfa0003'),('8a97857c0791f147010791f16dea001d','8a97857c0791f147010791f16dd60013','8a97857c0791f147010791f16de0001a'),('8a97857c0791f147010791f16dea001e','8a97857c0791f147010791f16dd60014','8a97857c0791f147010791f16de0001a'),('8a97857c0791f147010791f16dea001f','8a97857c0791f147010791f16dd60015','8a97857c0791f147010791f16de00019'),('8a97857c0791f147010791f16dea0020','8a97857c0791f147010791f16dd60016','8a97857c0791f147010791f16de00019'),('8a9681e207a19c4a0107a1abd9dc0005','8a97857c079c023a01079c0935630002','8a97857c0791f147010791f16de0001a'),('8a9681e207a19c4a0107a1aa77720003','8a97857c079c023a01079c09f5940004','8a97857c0791f147010791f16de0001a'),('8a9681e207a19c4a0107a1ac0acc0006','8a97857c079c023a01079c0a49e10005','8a97857c0791f147010791f16de0001a'),('8a9681e207a19c4a0107a1aba8920004','8a97857c079c023a01079c0b348d0007','8a97857c0791f147010791f16de0001a'),('8a9681e207a19c4a0107a19e98e50001','8a97857c079c023a01079c0b61300008','8a97857c0791f147010791f16de0001a');
UNLOCK TABLES;
/*!40000 ALTER TABLE `c_t_user_group` ENABLE KEYS */;

--
-- Table structure for table `c_t_user_preference`
--

DROP TABLE IF EXISTS `c_t_user_preference`;
CREATE TABLE `c_t_user_preference` (
  `preference_id` varchar(128) NOT NULL default '',
  `user_id` varchar(128) NOT NULL default '',
  `theme_name` varchar(255) NOT NULL default '',
  `default_layout` varchar(255) default NULL,
  `iconset` varchar(255) default NULL,
  `stylesheet` varchar(255) default NULL,
  `window_style` varchar(255) default NULL,
  PRIMARY KEY  (`preference_id`)
);

--
-- Dumping data for table `c_t_user_preference`
--


/*!40000 ALTER TABLE `c_t_user_preference` DISABLE KEYS */;
LOCK TABLES `c_t_user_preference` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `c_t_user_preference` ENABLE KEYS */;

--
-- Table structure for table `c_t_user_role`
--

DROP TABLE IF EXISTS `c_t_user_role`;
CREATE TABLE `c_t_user_role` (
  `user_role_id` varchar(128) NOT NULL default '',
  `user_id` varchar(128) NOT NULL default '',
  `role_id` varchar(128) NOT NULL default '',
  PRIMARY KEY  (`user_role_id`),
  KEY `FK4AB5D08340B327A5` (`role_id`),
  CONSTRAINT `FK4AB5D08340B327A5` FOREIGN KEY (`role_id`) REFERENCES `c_t_role` (`role_id`)
);

--
-- Dumping data for table `c_t_user_role`
--


/*!40000 ALTER TABLE `c_t_user_role` DISABLE KEYS */;
LOCK TABLES `c_t_user_role` WRITE;
INSERT INTO `c_t_user_role` VALUES ('8a9681e207a19c4a0107a1b1f3df000d','8a9681e2079b7b4201079b82e0960004','8a97857c0791f147010791f16dd60018');
UNLOCK TABLES;
/*!40000 ALTER TABLE `c_t_user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

