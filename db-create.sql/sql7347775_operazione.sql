-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: sql7.freemysqlhosting.net    Database: sql7347775
-- ------------------------------------------------------
-- Server version	5.5.62-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `operazione`
--

DROP TABLE IF EXISTS `operazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operazione` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` date NOT NULL,
  `importo` double NOT NULL,
  `tipologia` varchar(45) NOT NULL,
  `iban_conto_corrente` varchar(31) DEFAULT NULL,
  `iban_destinatario` varchar(31) DEFAULT NULL,
  `numero_carta` int(16) DEFAULT NULL,
  `numero_carta_destinatario` int(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `iban_conto_corrente_UNIQUE` (`iban_conto_corrente`),
  UNIQUE KEY `iban_destinatario_UNIQUE` (`iban_destinatario`),
  UNIQUE KEY `numero_carta_UNIQUE` (`numero_carta`),
  UNIQUE KEY `numero_carta_destinatario_UNIQUE` (`numero_carta_destinatario`),
  CONSTRAINT `iban_conto_corrente_fk` FOREIGN KEY (`iban_conto_corrente`) REFERENCES `conto_corrente` (`iban`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `iban_destinatario_fk` FOREIGN KEY (`iban_destinatario`) REFERENCES `conto_corrente` (`iban`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `numero_carta_destinatario_fk` FOREIGN KEY (`numero_carta_destinatario`) REFERENCES `carta_di_debito` (`numero`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `numero_carta_fk` FOREIGN KEY (`numero_carta`) REFERENCES `carta_di_debito` (`numero`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operazione`
--

LOCK TABLES `operazione` WRITE;
/*!40000 ALTER TABLE `operazione` DISABLE KEYS */;
/*!40000 ALTER TABLE `operazione` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-13 17:36:26
