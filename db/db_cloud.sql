CREATE DATABASE  IF NOT EXISTS `db_cloud` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `db_cloud`;
-- MySQL dump 10.16  Distrib 10.1.37-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: db_cloud
-- ------------------------------------------------------
-- Server version	10.1.37-MariaDB-0+deb9u1

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
-- Table structure for table `t_condivisioni`
--

DROP TABLE IF EXISTS `t_condivisioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_condivisioni` (
  `id_condivisione` int(11) NOT NULL AUTO_INCREMENT,
  `id_utente` int(11) DEFAULT NULL,
  `id_documento` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_condivisione`),
  KEY `idxutente` (`id_utente`),
  KEY `idxdocumento` (`id_documento`),
  CONSTRAINT `fk_t_condivisioni_1` FOREIGN KEY (`id_documento`) REFERENCES `t_documenti` (`id_documento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_condivisioni_2` FOREIGN KEY (`id_utente`) REFERENCES `t_utenti` (`id_utente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_condivisioni`
--

LOCK TABLES `t_condivisioni` WRITE;
/*!40000 ALTER TABLE `t_condivisioni` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_condivisioni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_documenti`
--

DROP TABLE IF EXISTS `t_documenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_documenti` (
  `id_documento` int(11) NOT NULL AUTO_INCREMENT,
  `descrizione` varchar(45) DEFAULT NULL,
  `nome_file` varchar(45) DEFAULT NULL,
  `id_utente` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_documento`),
  KEY `idxutenti` (`id_utente`),
  CONSTRAINT `fk_t_documenti_1` FOREIGN KEY (`id_utente`) REFERENCES `t_utenti` (`id_utente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_documenti`
--

LOCK TABLES `t_documenti` WRITE;
/*!40000 ALTER TABLE `t_documenti` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_documenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_tags`
--

DROP TABLE IF EXISTS `t_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_tags` (
  `id_tag` int(11) NOT NULL AUTO_INCREMENT,
  `nome_tag` varchar(45) DEFAULT NULL,
  `id_documento` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_tag`),
  KEY `idxdocumenti` (`id_documento`),
  CONSTRAINT `fk_t_tags_1` FOREIGN KEY (`id_documento`) REFERENCES `t_documenti` (`id_documento`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_tags`
--

LOCK TABLES `t_tags` WRITE;
/*!40000 ALTER TABLE `t_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_utenti`
--

DROP TABLE IF EXISTS `t_utenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_utenti` (
  `id_utente` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `cognome` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `user` varchar(45) DEFAULT NULL,
  `pw` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_utente`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_utenti`
--

LOCK TABLES `t_utenti` WRITE;
/*!40000 ALTER TABLE `t_utenti` DISABLE KEYS */;
INSERT INTO `t_utenti` VALUES (1,'Mimmo','Garrone','mimmo.garrone@gmail.com','mymmo74','pw'),(3,'Francesco','Ramolengo','francy.ramolengo@gmail.com','francis','pw'),(4,'Matteo','Buccin','buccin@gmail.com','matteo.buccin','pw'),(5,'Filippo','Camoscio','f.camoscio@gmail.com','phil','pw');
/*!40000 ALTER TABLE `t_utenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'db_cloud'
--

--
-- Dumping routines for database 'db_cloud'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-04 10:34:05
