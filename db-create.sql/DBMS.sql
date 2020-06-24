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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `impronta_digitale` int(11) NOT NULL,
  `dispositivi_associati` varchar(100) NOT NULL,
  `codice_fiscale_cliente` varchar(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `impronta_digitale_UNIQUE` (`impronta_digitale`),
  KEY `Codice_Fiscale_idx` (`codice_fiscale_cliente`),
  CONSTRAINT `codice_Fiscale` FOREIGN KEY (`codice_fiscale_cliente`) REFERENCES `cliente` (`codice_fiscale`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `carta_di_debito`
--

DROP TABLE IF EXISTS `carta_di_debito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carta_di_debito` (
  `numero` varchar(16) NOT NULL,
  `iban` varchar(31) NOT NULL,
  `data_di_scadenza` date NOT NULL,
  `cvv` int(11) NOT NULL,
  `pin` int(11) NOT NULL,
  `iban_conto_corrente` varchar(45) NOT NULL,
  PRIMARY KEY (`numero`),
  UNIQUE KEY `iban_UNIQUE` (`iban`),
  KEY `iban_conto_corrente_idx` (`iban_conto_corrente`),
  CONSTRAINT `iban_conto_corrente` FOREIGN KEY (`iban_conto_corrente`) REFERENCES `conto_corrente` (`iban`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `carta_prepagata`
--

DROP TABLE IF EXISTS `carta_prepagata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carta_prepagata` (
  `numero` varchar(16) NOT NULL,
  `saldo_contabile` double NOT NULL,
  `saldo_disponibile` double NOT NULL,
  `data_di_scadenza` date NOT NULL,
  `cvv` int(11) NOT NULL,
  `pin` int(11) NOT NULL,
  `id_account` int(11) NOT NULL,
  PRIMARY KEY (`numero`),
  KEY `id_idx` (`id_account`),
  CONSTRAINT `id` FOREIGN KEY (`id_account`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `codice_fiscale` varchar(16) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `citta_di_nascita` varchar(45) NOT NULL,
  `data_di_nascita` date NOT NULL,
  `numero_di_telefono` varchar(15) NOT NULL,
  `indirizzo_di_residenza` varchar(100) NOT NULL,
  `citta_di_residenza` varchar(45) NOT NULL,
  PRIMARY KEY (`codice_fiscale`),
  UNIQUE KEY `codice_fiscale_UNIQUE` (`codice_fiscale`),
  UNIQUE KEY `numero_di_telefono_UNIQUE` (`numero_di_telefono`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `conto_corrente`
--

DROP TABLE IF EXISTS `conto_corrente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conto_corrente` (
  `numero` int(11) NOT NULL,
  `iban` varchar(31) NOT NULL,
  `saldo_disponibile` double NOT NULL,
  `saldo_contabile` double NOT NULL,
  `id_account` int(11) NOT NULL,
  PRIMARY KEY (`iban`),
  UNIQUE KEY `IBAN_conto_corrente_UNIQUE` (`iban`),
  KEY `id_account_idx` (`id_account`),
  CONSTRAINT `id_account` FOREIGN KEY (`id_account`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `operazione_carta_debito`
--

DROP TABLE IF EXISTS `operazione_carta_debito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operazione_carta_debito` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` date NOT NULL,
  `importo` double NOT NULL,
  `tipologia` varchar(45) NOT NULL,
  `numero_carta_proprietario` varchar(16) NOT NULL,
  `numero_carta_beneficiario` varchar(16) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `carta_debito_proprietario_fk_idx` (`numero_carta_proprietario`),
  CONSTRAINT `carta_debito_proprietario_fk` FOREIGN KEY (`numero_carta_proprietario`) REFERENCES `carta_di_debito` (`numero`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `operazione_conto_corrente`
--

DROP TABLE IF EXISTS `operazione_conto_corrente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operazione_conto_corrente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` date NOT NULL,
  `importo` double NOT NULL,
  `tipologia` varchar(45) NOT NULL,
  `iban_proprietario` varchar(31) NOT NULL,
  `iban_beneficiario` varchar(31) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `iban_proprietario_fk_idx` (`iban_proprietario`),
  CONSTRAINT `iban_proprietario_fk` FOREIGN KEY (`iban_proprietario`) REFERENCES `conto_corrente` (`iban`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `operazione_prepagata`
--

DROP TABLE IF EXISTS `operazione_prepagata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operazione_prepagata` (
  `id` int(11) NOT NULL,
  `data` date NOT NULL,
  `importo` double NOT NULL,
  `tipologia` varchar(45) NOT NULL,
  `destinatario` varchar(45) NOT NULL,
  `numero_carta` varchar(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_operazione_carta_prepagata_UNIQUE` (`id`),
  KEY `numero_carta_idx` (`numero_carta`),
  CONSTRAINT `numero_carta` FOREIGN KEY (`numero_carta`) REFERENCES `carta_prepagata` (`numero`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-24 17:29:19
