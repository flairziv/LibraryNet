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
  `admin_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `admin_count` varchar(32) NOT NULL COMMENT '管理员账号 6-16位字母或者数字',
  `admin_password` char(32) NOT NULL COMMENT '管理员密码',
  `admin_name` varchar(30) NOT NULL COMMENT '姓名',
  `admin_id_number` char(18) NOT NULL COMMENT '身份证号',
  `admin_contact` char(11) DEFAULT NULL COMMENT '手机号',
  `admin_email` varchar(45) DEFAULT NULL COMMENT '邮箱',
  `admin_keepPass` varchar(45) NOT NULL COMMENT '密保',
  `isSuper` tinyint unsigned NOT NULL COMMENT '是否超级管理员 0为管理员，1为超级管理员',
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `u_count` (`admin_count`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='管理员信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` (`admin_id`, `admin_count`, `admin_password`, `admin_name`, `admin_id_number`, `admin_contact`, `admin_email`, `admin_keepPass`, `isSuper`) VALUES (0,'0070124','25f9e794323b453885f5181f1b624d0b','小张','445122200512018888','13829060000','flairziv@gmail.com','123',1),(1,'0070112','25f9e794323b453885f5181f1b624d0b','小李','445122200511118888','13825806666','123456890@qq.com','123',0);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `b_id` int NOT NULL AUTO_INCREMENT COMMENT '图书id',
  `ISBN` char(10) NOT NULL COMMENT 'ISBN',
  `b_name` varchar(45) NOT NULL COMMENT '书名',
  `bookType` int unsigned NOT NULL COMMENT '图书类型',
  `author` varchar(45) NOT NULL COMMENT '作者',
  `press` varchar(45) DEFAULT NULL COMMENT '出版社',
  `price` decimal(10,2) DEFAULT NULL,
  `inventory` int unsigned DEFAULT NULL COMMENT '库存量',
  PRIMARY KEY (`b_id`),
  UNIQUE KEY `ISBN_UNIQUE` (`ISBN`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='图书信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`b_id`, `ISBN`, `b_name`, `bookType`, `author`, `press`, `price`, `inventory`) VALUES (1,'9781234567','活着',1,'余华','作家出版社',39.99,100),(2,'9789876543','明朝那些事儿',2,'当年明月','中华书局',49.99,150),(3,'9781122334','编程之美',3,'华中科技大学','电子工业出版社',59.99,200),(4,'9784455667','哲学的故事',4,'威尔·杜兰特','人民出版社',88.00,80),(5,'9787788990','艺术的故事',5,'E.H. Gombrich','长江文艺出版社',75.50,120),(6,'9781237894','大学',6,'朱熹','北京大学出版社',29.99,250),(7,'9789911223','追风筝的人',7,'卡勒德·胡赛尼','南海出版公司',45.00,180),(8,'9783344556','经济学原理',8,'曼昆','中信出版社',98.00,90),(9,'9786677889','管理学',9,'斯蒂芬·P·罗宾斯','机械工业出版社',109.99,70),(10,'9787766554','Steve Jobs 自传',10,'沃尔特·艾萨克森','新华出版社',89.99,60),(11,'9781234568','时间简史',4,'斯蒂芬·霍金','宇航出版社',120.00,50),(12,'9789876544','百年孤独',1,'加西亚·马尔克斯','译林出版社',68.00,75),(13,'9785566778','沉默的大多数',1,'王小波','南海出版公司',39.50,130),(14,'9788889991','嫌疑人X的献身',7,'东野圭吾','人民文学出版社',58.00,200),(15,'9781237772','人类简史',2,'尤瓦尔·赫拉利','中信出版社',79.00,220),(16,'9786677888','资本论',2,'卡尔·马克思','人民出版社',105.00,45),(17,'9785566888','活下去的理由',10,'马库斯·奥雷里乌斯','中国社会出版社',29.99,150),(18,'9781122336','大数据时代',8,'维克托·迈尔-舍恩伯格','中信出版社',85.00,80),(19,'9784556778','简爱',1,'夏洛蒂·勃朗特','上海译文出版社',52.00,170),(20,'9788899223','1984',4,'乔治·奥威尔','人民文学出版社',42.50,100),(21,'9781000001','活法',1,'李开复','中信出版社',65.00,110),(22,'9782000002','天龙八部',2,'金庸','人民文学出版社',59.00,95),(23,'9783000003','霍乱时期的爱情',1,'加西亚·马尔克斯','译林出版社',82.00,130),(24,'9784000004','人类群星闪耀时',4,'斯蒂芬·茨威格','译林出版社',78.00,90),(25,'9785000005','自控力',2,'凯利·麦格尼戈尔','中信出版社',49.00,150),(26,'9786000006','傲慢与偏见',5,'简·奥斯汀','上海译文出版社',45.00,180),(27,'9787000007','百年孤独',1,'加西亚·马尔克斯','译林出版社',69.50,120),(28,'9788000008','穆斯林的葬礼',7,'霍达·巴拉卡','人民文学出版社',85.00,110),(29,'9789000009','平凡的世界',1,'路遥','人民文学出版社',55.00,160),(30,'9781010101','时间的秩序',4,'卡洛·罗韦利','中信出版社',99.00,75),(31,'9781111111','围城',2,'钱钟书','人民文学出版社',60.00,200),(32,'9781212121','白夜行',7,'东野圭吾','译林出版社',65.00,140),(33,'9781313131','茶花女',5,'小仲马','人民文学出版社',72.50,85),(34,'9781414141','大卫·科波菲尔',1,'查尔斯·狄更斯','译林出版社',58.00,100),(35,'9781515151','一个人的朝圣',1,'哈罗德·弗莱','人民文学出版社',46.00,200),(36,'9781616161','黑天鹅',2,'纳西姆·尼古拉斯·塔勒布','中信出版社',89.00,150),(37,'9781717171','解忧杂货店',7,'东野圭吾','南海出版公司',58.00,160),(38,'9781818181','西游记',4,'吴承恩','人民文学出版社',52.00,180),(39,'9781919191','奇迹男孩',5,'R·J·帕拉西奥','北京联合出版公司',48.00,210),(40,'9782020202','德米安',1,'赫尔曼·黑塞','译林出版社',39.50,160),(41,'9782121212','极简历史',2,'尤瓦尔·赫拉利','中信出版社',79.00,180);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booktype`
--

DROP TABLE IF EXISTS `booktype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booktype` (
  `bt_id` int NOT NULL AUTO_INCREMENT COMMENT '图书类型id',
  `bt_name` varchar(45) NOT NULL COMMENT '图书类型',
  PRIMARY KEY (`bt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='图书类型信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booktype`
--

LOCK TABLES `booktype` WRITE;
/*!40000 ALTER TABLE `booktype` DISABLE KEYS */;
INSERT INTO `booktype` (`bt_id`, `bt_name`) VALUES (1,'文学'),(2,'历史'),(3,'科技'),(4,'哲学'),(5,'艺术'),(6,'教育'),(7,'小说'),(8,'经济'),(9,'管理'),(10,'自传');
/*!40000 ALTER TABLE `booktype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrow`
--

DROP TABLE IF EXISTS `borrow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrow` (
  `borrow_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '借阅信息id',
  `r_number` varchar(32) NOT NULL COMMENT '借阅证号',
  `borrow_b_id` int unsigned NOT NULL COMMENT '图书id',
  `borrowDate` int unsigned NOT NULL COMMENT '借阅日期',
  `dueDate` int unsigned NOT NULL COMMENT '应还日期',
  `returnDate` int unsigned DEFAULT NULL COMMENT '实际归还日期',
  `isReturn` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 代表 false——未归还, 1 代表 true——已归还',
  PRIMARY KEY (`borrow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='借阅信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow`
--

LOCK TABLES `borrow` WRITE;
/*!40000 ALTER TABLE `borrow` DISABLE KEYS */;
INSERT INTO `borrow` (`borrow_id`, `r_number`, `borrow_b_id`, `borrowDate`, `dueDate`, `returnDate`, `isReturn`) VALUES (1,'20170744212',20,1550628552,1555812552,1550628997,1),(2,'20170744212',20,1550628558,1555812558,1550628568,1),(3,'20170744212',26,1550628582,1555812582,NULL,0),(4,'20170744212',28,1550628593,1555812593,1550810047,1),(5,'20170744212',33,1550628603,1555812603,NULL,0),(6,'20170744212',26,1550629012,1555813012,1550629046,1),(7,'20170744212',31,1550629060,1555813060,1575709962,1),(8,'20170744212',29,1550629168,1555813168,NULL,0),(9,'20170744212',10,1550641036,1555825036,NULL,0),(10,'20170744212',12,1550641489,1555825489,NULL,0),(11,'20170744212',15,1550649684,1555833684,1550729948,1),(12,'20170744212',56,1550649693,1555833693,NULL,0),(13,'20170744212',35,1550649837,1555833837,NULL,0),(14,'20170744214',23,1550651743,1555835743,1550651848,1),(15,'20170744214',15,1550651839,1555835839,NULL,0),(16,'20170744212',6,1550661234,1555845234,1550661252,1),(17,'20170744212',6,1550661286,1555845286,1550661382,1),(18,'20170744212',6,1550661403,1555845403,1550661419,1),(19,'20170744212',6,1550661447,1555845447,NULL,0),(20,'20170744236',26,1550730204,1555914204,1550730209,1),(21,'20170744212',62,1550810039,1555994039,NULL,0),(22,'20170744212',37,1550810122,1555994122,NULL,0),(23,'20170744321',35,1550838149,1558614149,1550838220,1),(24,'20170744321',31,1550838166,1558614166,NULL,0),(25,'20170744220',31,1565009308,1570193308,1565009320,1),(26,'20170744212',40,1575709929,1580893929,NULL,0),(28,'20170744212',20,1550628545,1555812545,1550629006,1);
/*!40000 ALTER TABLE `borrow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reader`
--

DROP TABLE IF EXISTS `reader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reader` (
  `card_number` varchar(32) NOT NULL COMMENT '借阅证号',
  `name` varchar(30) NOT NULL COMMENT '姓名',
  `gender` char(3) DEFAULT NULL COMMENT '性别',
  `dept` varchar(30) DEFAULT NULL COMMENT '院系',
  `classes` varchar(30) DEFAULT NULL COMMENT '班级',
  `contact` char(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(45) DEFAULT NULL COMMENT '邮箱',
  `loginDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `password` char(32) NOT NULL,
  `keepPass` varchar(45) NOT NULL,
  `reader_type` int unsigned NOT NULL COMMENT '读者类型',
  PRIMARY KEY (`card_number`),
  UNIQUE KEY `card_number_UNIQUE` (`card_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='读者信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reader`
--

LOCK TABLES `reader` WRITE;
/*!40000 ALTER TABLE `reader` DISABLE KEYS */;
INSERT INTO `reader` (`card_number`, `name`, `gender`, `dept`, `classes`, `contact`, `email`, `loginDate`, `password`, `keepPass`, `reader_type`) VALUES ('20230070124','小杰','男','人工智能系','物联网23','13829062952','FlairZiv@gmail.com','2025-01-02 07:54:23','25f9e794323b453885f5181f1b624d0b','123',1);
/*!40000 ALTER TABLE `reader` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `readertype`
--

DROP TABLE IF EXISTS `readertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `readertype` (
  `rt_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '读者类型id',
  `rt_name` varchar(30) NOT NULL COMMENT '读者类型',
  `maxCount` int unsigned NOT NULL COMMENT '最大借阅数量',
  `maxDay` int unsigned NOT NULL COMMENT '最大借阅天数',
  PRIMARY KEY (`rt_id`),
  UNIQUE KEY `rt_name_UNIQUE` (`rt_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='读者类型信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `readertype`
--

LOCK TABLES `readertype` WRITE;
/*!40000 ALTER TABLE `readertype` DISABLE KEYS */;
INSERT INTO `readertype` (`rt_id`, `rt_name`, `maxCount`, `maxDay`) VALUES (1,'本科',25,60),(2,'博士',20,90),(3,'博士后',25,120);
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

-- Dump completed on 2025-01-02 20:55:09
