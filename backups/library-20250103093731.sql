-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: library
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator` (
  `admin_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '����Աid',
  `admin_count` varchar(32) NOT NULL COMMENT '����Ա�˺� 6-16λ��ĸ��������',
  `admin_password` char(32) NOT NULL COMMENT '����Ա����',
  `admin_name` varchar(30) NOT NULL COMMENT '����',
  `admin_id_number` char(18) NOT NULL COMMENT '���֤��',
  `admin_contact` char(11) DEFAULT NULL COMMENT '�ֻ���',
  `admin_email` varchar(45) DEFAULT NULL COMMENT '����',
  `admin_keepPass` varchar(45) NOT NULL COMMENT '�ܱ�',
  `isSuper` tinyint unsigned NOT NULL COMMENT '�Ƿ񳬼�����Ա 0Ϊ����Ա��1Ϊ��������Ա',
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `u_count` (`admin_count`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='����Ա��Ϣ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` (`admin_id`, `admin_count`, `admin_password`, `admin_name`, `admin_id_number`, `admin_contact`, `admin_email`, `admin_keepPass`, `isSuper`) VALUES (1,'0070112','25f9e794323b453885f5181f1b624d0b','С��','445122200511118888','13825806666','123456890@qq.com','123',0),(2,'0070124','25f9e794323b453885f5181f1b624d0b','С��','445122200512018888','13829060000','flairziv@gmail.com','123',1),(7,'0070138','25f9e794323b453885f5181f1b624d0b','С��','445122200302060465','13845678269','534563744@qq.com','123',0);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `b_id` int NOT NULL AUTO_INCREMENT COMMENT 'ͼ��id',
  `ISBN` char(10) NOT NULL COMMENT 'ISBN',
  `b_name` varchar(45) NOT NULL COMMENT '����',
  `bookType` int unsigned NOT NULL COMMENT 'ͼ������',
  `author` varchar(45) NOT NULL COMMENT '����',
  `press` varchar(45) DEFAULT NULL COMMENT '������',
  `price` decimal(10,2) DEFAULT NULL,
  `inventory` int unsigned DEFAULT NULL COMMENT '�����',
  PRIMARY KEY (`b_id`),
  UNIQUE KEY `ISBN_UNIQUE` (`ISBN`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='ͼ����Ϣ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`b_id`, `ISBN`, `b_name`, `bookType`, `author`, `press`, `price`, `inventory`) VALUES (1,'9781234567','����',1,'�໪','���ҳ�����',39.99,97),(2,'9789876543','������Щ�¶�',2,'��������','�л����',49.99,148),(3,'9781122334','���֮��',3,'���пƼ���ѧ','���ӹ�ҵ������',59.99,198),(4,'9784455667','��ѧ�Ĺ���',4,'������������','���������',88.00,78),(5,'9787788990','�����Ĺ���',5,'E.H. Gombrich','�������ճ�����',75.50,118),(6,'9781237894','��ѧ',6,'����','������ѧ������',29.99,249),(7,'9789911223','׷���ݵ���',7,'���յ¡�������','�Ϻ����湫˾',45.00,180),(8,'9783344556','����ѧԭ��',8,'����','���ų�����',98.00,90),(9,'9786677889','����ѧ',9,'˹�ٷҡ�P���ޱ�˹','��е��ҵ������',109.99,70),(10,'9787766554','Steve Jobs �Դ�',10,'�ֶ��ء�������ɭ','�»�������',89.99,60),(11,'9781234568','ʱ���ʷ',4,'˹�ٷҡ�����','�������',120.00,50),(12,'9789876544','����¶�',1,'�����ǡ������˹','���ֳ�����',68.00,75),(13,'9785566778','��Ĭ�Ĵ����',1,'��С��','�Ϻ����湫˾',39.50,130),(14,'9788889991','������X������',7,'��Ұ����','������ѧ������',58.00,200),(15,'9781237772','�����ʷ',2,'���߶���������','���ų�����',79.00,220),(16,'9786677888','�ʱ���',2,'���������˼','���������',105.00,45),(17,'9785566888','����ȥ������',10,'���˹����������˹','�й���������',29.99,150),(18,'9781122336','������ʱ��',8,'ά���С�����-�������','���ų�����',85.00,80),(19,'9784556778','��',1,'����١�������','�Ϻ����ĳ�����',52.00,170),(20,'9788899223','1984',4,'���Ρ�������','������ѧ������',42.50,100),(21,'9781000001','�',1,'���','���ų�����',65.00,110),(22,'9782000002','�����˲�',2,'��ӹ','������ѧ������',59.00,95),(23,'9783000003','����ʱ�ڵİ���',1,'�����ǡ������˹','���ֳ�����',82.00,130),(24,'9784000004','����Ⱥ����ҫʱ',4,'˹�ٷҡ�������','���ֳ�����',78.00,90),(25,'9785000005','�Կ���',2,'�������������','���ų�����',49.00,150),(26,'9786000006','������ƫ��',5,'�򡤰�˹͡','�Ϻ����ĳ�����',45.00,180),(27,'9787000007','����¶�',1,'�����ǡ������˹','���ֳ�����',69.50,120),(28,'9788000008','��˹�ֵ�����',7,'���������','������ѧ������',85.00,110),(29,'9789000009','ƽ��������',1,'·ң','������ѧ������',55.00,160),(30,'9781010101','ʱ�������',4,'���塤��Τ��','���ų�����',99.00,75),(31,'9781111111','Χ��',2,'Ǯ����','������ѧ������',60.00,200),(32,'9781212121','��ҹ��',7,'��Ұ����','���ֳ�����',65.00,140),(33,'9781313131','�軨Ů',5,'С����','������ѧ������',72.50,84),(34,'9781414141','�������Ʋ��ƶ�',1,'���˹���Ҹ�˹','���ֳ�����',58.00,100),(35,'9781515151','һ���˵ĳ�ʥ',1,'���޵¡�����','������ѧ������',46.00,200),(36,'9781616161','�����',2,'����ķ�������˹�����ղ�','���ų�����',89.00,149),(37,'9781717171','�����ӻ���',7,'��Ұ����','�Ϻ����湫˾',58.00,160),(38,'9781818181','���μ�',4,'��ж�','������ѧ������',52.00,180),(39,'9781919191','�漣�к�',5,'R��J����������','�������ϳ��湫˾',48.00,209),(40,'9782020202','���װ�',1,'�ն���������','���ֳ�����',39.50,160),(41,'9782121212','������ʷ',2,'���߶���������','���ų�����',79.00,180);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booktype`
--

DROP TABLE IF EXISTS `booktype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booktype` (
  `bt_id` int NOT NULL AUTO_INCREMENT COMMENT 'ͼ������id',
  `bt_name` varchar(45) NOT NULL COMMENT 'ͼ������',
  PRIMARY KEY (`bt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='ͼ��������Ϣ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booktype`
--

LOCK TABLES `booktype` WRITE;
/*!40000 ALTER TABLE `booktype` DISABLE KEYS */;
INSERT INTO `booktype` (`bt_id`, `bt_name`) VALUES (1,'��ѧ'),(2,'��ʷ'),(3,'�Ƽ�'),(4,'��ѧ'),(5,'����'),(6,'����'),(7,'С˵'),(8,'����'),(9,'����'),(10,'�Դ�'),(11,'����');
/*!40000 ALTER TABLE `booktype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrow`
--

DROP TABLE IF EXISTS `borrow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrow` (
  `borrow_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '������Ϣid',
  `r_number` varchar(32) NOT NULL COMMENT '����֤��',
  `borrow_b_id` int unsigned NOT NULL COMMENT 'ͼ��id',
  `borrowDate` int unsigned NOT NULL COMMENT '��������',
  `dueDate` int unsigned NOT NULL COMMENT 'Ӧ������',
  `returnDate` int unsigned DEFAULT NULL COMMENT 'ʵ�ʹ黹����',
  `isReturn` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 ���� false����δ�黹, 1 ���� true�����ѹ黹',
  PRIMARY KEY (`borrow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='������Ϣ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow`
--

LOCK TABLES `borrow` WRITE;
/*!40000 ALTER TABLE `borrow` DISABLE KEYS */;
INSERT INTO `borrow` (`borrow_id`, `r_number`, `borrow_b_id`, `borrowDate`, `dueDate`, `returnDate`, `isReturn`) VALUES (1,'20230070124',1,1735824272,1741008272,NULL,0),(2,'20230070124',33,1735824302,1741008302,NULL,0),(3,'20230070124',39,1735824322,1741008322,NULL,0),(4,'20230070124',36,1735824345,1741008345,NULL,0),(5,'20230744212',1,1735824731,1735911131,NULL,0),(6,'20230744212',2,1735824734,1735911134,NULL,0),(7,'20230744212',3,1735824737,1735911137,NULL,0),(8,'20230744212',4,1735824739,1735911139,NULL,0),(9,'20230744212',5,1735824742,1735824743,NULL,0),(10,'20230744212',6,1735824746,1735911146,1735824764,1),(65,'20230744216',1,1735825144,1743601144,NULL,0),(66,'20230744216',2,1735825147,1743601147,NULL,0),(67,'20230744216',3,1735825151,1743601151,NULL,0),(68,'20230744216',4,1735825154,1743601154,NULL,0),(69,'20230744216',5,1735825156,1743601156,NULL,0),(70,'20230744216',6,1735825159,1743601159,NULL,0);
/*!40000 ALTER TABLE `borrow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reader`
--

DROP TABLE IF EXISTS `reader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reader` (
  `card_number` varchar(32) NOT NULL COMMENT '����֤��',
  `name` varchar(30) NOT NULL COMMENT '����',
  `gender` char(3) DEFAULT NULL COMMENT '�Ա�',
  `dept` varchar(30) DEFAULT NULL COMMENT 'Ժϵ',
  `classes` varchar(30) DEFAULT NULL COMMENT '�༶',
  `contact` char(11) DEFAULT NULL COMMENT '�ֻ���',
  `email` varchar(45) DEFAULT NULL COMMENT '����',
  `loginDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `password` char(32) NOT NULL,
  `keepPass` varchar(45) NOT NULL,
  `reader_type` int unsigned NOT NULL COMMENT '��������',
  PRIMARY KEY (`card_number`),
  UNIQUE KEY `card_number_UNIQUE` (`card_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='������Ϣ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reader`
--

LOCK TABLES `reader` WRITE;
/*!40000 ALTER TABLE `reader` DISABLE KEYS */;
INSERT INTO `reader` (`card_number`, `name`, `gender`, `dept`, `classes`, `contact`, `email`, `loginDate`, `password`, `keepPass`, `reader_type`) VALUES ('20230070124','С��','��','�˹�����ϵ','������23','13829062952','FlairZiv@gmail.com','2025-01-01 23:54:23','25f9e794323b453885f5181f1b624d0b','123',1),('20230744212','С��','��','��Ϣ����ѧԺ','�Ź�172','13569699696','464645646@qq.com','2025-01-02 13:31:57','25f9e794323b453885f5181f1b624d0b','123',4),('20230744216','С��','��','�����Ƽ�ѧԺ','����184','13569694848','346464646@qq.com','2025-01-02 13:38:48','25f9e794323b453885f5181f1b624d0b','123',2),('20230744321','СС','Ů','�����Ƽ�ѧԺ','�Ź�172','15968689898','4645654@qq.com','2025-01-02 13:40:05','25f9e794323b453885f5181f1b624d0b','123',3);
/*!40000 ALTER TABLE `reader` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `readertype`
--

DROP TABLE IF EXISTS `readertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `readertype` (
  `rt_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '��������id',
  `rt_name` varchar(30) NOT NULL COMMENT '��������',
  `maxCount` int unsigned NOT NULL COMMENT '����������',
  `maxDay` int unsigned NOT NULL COMMENT '����������',
  PRIMARY KEY (`rt_id`),
  UNIQUE KEY `rt_name_UNIQUE` (`rt_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='����������Ϣ��';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `readertype`
--

LOCK TABLES `readertype` WRITE;
/*!40000 ALTER TABLE `readertype` DISABLE KEYS */;
INSERT INTO `readertype` (`rt_id`, `rt_name`, `maxCount`, `maxDay`) VALUES (1,'����',25,60),(2,'��ʿ',20,90),(3,'��ʿ��',25,120),(4,'��������',5,1);
/*!40000 ALTER TABLE `readertype` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-03  9:37:32
