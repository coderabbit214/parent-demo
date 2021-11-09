-- MySQL dump 10.13  Distrib 8.0.26, for macos11 (x86_64)
--
-- Host: 127.0.0.1    Database: securitys
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `expression` varchar(255) DEFAULT NULL COMMENT '权限表达式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'部门新增/编辑','department:saveOrUpdate'),(2,'部门页面','department:list'),(3,'部门删除','department:delete'),(4,'员工新增/编辑','employee:saveOrUpdate'),(5,'员工页面','employee:list'),(6,'员工删除','employee:delete'),(7,'角色页面','role:list'),(8,'角色删除','role:delete'),(9,'角色新增/编辑','role:saveOrUpdate'),(10,'权限页面','permission:list'),(11,'权限加载','permission:reload'),(12,'员工导入','employee:importXls'),(13,'员工导出','employee:exportXls'),(14,'字典目录新增/编辑','systemDictionary:saveOrUpdate'),(15,'字典目录页面','systemDictionary:list'),(16,'字典目录删除','systemDictionary:delete'),(17,'字典明细新增/编辑','systemDictionaryItem:saveOrUpdate'),(18,'字典明细页面','systemDictionaryItem:list'),(19,'字典明细删除','systemDictionaryItem:delete'),(20,'预约单新增/编辑','appointment:saveOrUpdate'),(21,'预约单状态更改','appointment:updateStatus'),(22,'预约单页面','appointment:list'),(23,'预约单删除','appointment:delete'),(24,'门店新增/编辑','business:saveOrUpdate'),(25,'门店页面','business:list'),(26,'门店删除','business:delete'),(27,'门店收入柱状图报表','businessReport:listBar'),(28,'门店收入报表页面','businessReport:list'),(29,'结算单新增/编辑','consumption:saveOrUpdate'),(30,'结算单页面','consumption:list'),(31,'结算单删除','consumption:delete'),(32,'结算单新增(关联预约单)','consumption:save'),(33,'结算明细新增/编辑','consumptionItem:saveOrUpdate'),(34,'结算明细页面','consumptionItem:list'),(35,'结算明细删除','consumptionItem:delete');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `sn` varchar(255) DEFAULT NULL COMMENT '角色编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'人事经理','hr'),(2,'行政经理','1'),(3,'人事专员','1'),(4,'财务','1'),(5,'市场专员','1'),(6,'老板','1'),(7,'人事','HR'),(8,'行政','sss');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permission` (
  `role_id` bigint DEFAULT NULL,
  `permission_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (8,2),(8,3),(5,3),(5,4),(5,5),(5,6),(5,7),(1,5),(1,7);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `admin` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'admin','管理员','1661de1567027e60aefe69caa8886752',_binary ''),(3,'lucy','adm','d0572253c0e7cdb85b13988712cb152e',_binary '\0'),(5,'tret','摸金校尉','65c3a75171075189a5990c770be0de3d',_binary '\0'),(6,'tertrr','rgwsg','a06e99f2ee96816edc16fbf6462e6b3c',_binary '\0'),(7,'rtr','htewf','710879c441aad1b32f49921179674987',_binary '\0'),(8,'tr','grsea','3f61530e9ed4a00b937076e9f26392dc',_binary ''),(9,'retrete','tretrt1','001acfec8bd613f11637816da45b17bb',_binary ''),(10,'trytry','564654','144c8f65ee7953080c9f287c1aadf4df',_binary '\0'),(12,'453','4543','0955b0c52f0e159e465b110315bd3aa4',_binary '\0'),(13,'6456','5646545','0d9eafa58f2685c7d340d0b07c62798b',_binary '\0'),(14,'76757','','c4ca4238a0b923820dcc509a6f75849b',_binary '\0'),(15,'6756765','666','d1ac549dbfdec4a0d49baec903648bb4',_binary '\0'),(16,'sdffaff','ggsgsg','31f1fd88b964d4f1b5107ad78f333dd7',_binary '\0'),(17,'trytry','etrt','985ca8adc93def0d155f40d5709e8cd5',_binary '\0'),(18,'tytetet','yrtytry','5b92c1419056a1aaf250e2b4e80f49b0',_binary '\0'),(22,'jack','jack','c38ccc9693a077376bf81fc8db2c1689',_binary '\0'),(23,'chen','陈','6711bf0868f1800a3d93c2bdf05367f3',_binary ''),(24,'cc','CC','b9853e9a6a852eaff4e4c4306d97daf7',_binary '');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` bigint DEFAULT NULL,
  `role_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,2),(1,3),(1,4),(10,2),(10,3),(12,8),(12,5),(3,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-09 15:04:59
