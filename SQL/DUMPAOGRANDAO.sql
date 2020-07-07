CREATE DATABASE  IF NOT EXISTS `farmacia` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `farmacia`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: farmacia
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `cod_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome_cliente` varchar(100) NOT NULL,
  `cpf_cliente` varchar(45) NOT NULL,
  `email_cliente` varchar(100) DEFAULT NULL,
  `tel_cliente` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cod_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Sandrao','1234123412','','121212112'),(3,'teste','12341234','','12342134'),(4,'teste2','1234','','1234'),(5,'teste3','   .   .   -  ','','(  ) 9     -    '),(6,'teste4','641.516.165-16','kkkkk@hotmail.com','(21) 9 6651-1515'),(7,'teste5','','',''),(9,'teste6','','',''),(10,'Catarina Emily Mendes','661.471.877-08','catarinaemilymendes@gimail.com','(21) 9 8659-6340'),(11,'teste7','297.671.450-95','',''),(12,'daniel','177.522.377-95','filipe.rj149@gmail.com','(21) 9 8518-6767');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enderecos`
--

DROP TABLE IF EXISTS `enderecos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enderecos` (
  `cod_end` int(11) NOT NULL AUTO_INCREMENT,
  `rua` varchar(45) NOT NULL,
  `numero` int(6) NOT NULL,
  `bairro` varchar(45) NOT NULL,
  `cidade` varchar(45) NOT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `comp` varchar(45) DEFAULT NULL,
  `cep` int(10) NOT NULL,
  `cod_end2` int(11) DEFAULT NULL,
  PRIMARY KEY (`cod_end`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enderecos`
--

LOCK TABLES `enderecos` WRITE;
/*!40000 ALTER TABLE `enderecos` DISABLE KEYS */;
INSERT INTO `enderecos` VALUES (1,'rm',182,'cordovil','rj','rj',NULL,21250250,1),(2,'rm',182,'cordovil','rj',NULL,'',21250250,2),(4,'rm',182,'cordovil','rj','rj',NULL,21250250,4),(6,'Alameda Oiapoc',263,'Chácara Santo Antônio','Belford Roxo',NULL,'',26173010,6),(7,'Rua Eduardo Galvão 640',304,'São José do Imbassaí','Maricá',NULL,'',24931840,7),(8,'Travessa da Fonte',594,'Itaipu','Rio de Janeiro',NULL,'',24346060,8),(9,'Rua L',160,'Santa Cruz','Rio de Janeiro',NULL,'',23570037,9),(12,'Beco Quinhentos',346,'Sepetiba','Rio de Janeiro',NULL,'',23535098,10),(13,'Rua Correia Dias',554,'Jardim América','RJ',NULL,'',21864684,13);
/*!40000 ALTER TABLE `enderecos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estoque`
--

DROP TABLE IF EXISTS `estoque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estoque` (
  `idproduto` int(11) NOT NULL AUTO_INCREMENT,
  `nomeproduto` varchar(45) NOT NULL,
  `valorcompra` double NOT NULL,
  `valorvenda` double NOT NULL,
  `quantidade` int(11) NOT NULL,
  `fornecedor` varchar(45) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `receita` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idproduto`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque`
--

LOCK TABLES `estoque` WRITE;
/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
INSERT INTO `estoque` VALUES (3,'produto1',1000,2000,7,'sorrizo ronaldo ltda','Remédio','Sim'),(4,'produto2',2000,3000,20,'mc poze','Remédio','Não'),(5,'produto3',500,1000,12,'renan da penha','Cosmético',NULL),(6,'Tylenol',20,27.5,54,'TYLENOL','Remédio','Não'),(7,'remedio do doentinho do daniel',1000,1200,7,'oswaldo','Remédio','Sim');
/*!40000 ALTER TABLE `estoque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionarios`
--

DROP TABLE IF EXISTS `funcionarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcionarios` (
  `Cod_func` int(11) NOT NULL,
  `CPF` varchar(45) NOT NULL,
  `nomefunc` varchar(45) NOT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  `cargo` varchar(45) NOT NULL,
  `RG` varchar(45) DEFAULT NULL,
  `nascimento` date NOT NULL,
  `cod_end` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `senha` varchar(120) NOT NULL,
  PRIMARY KEY (`Cod_func`),
  KEY `cod_end_idx` (`cod_end`),
  CONSTRAINT `cod_end` FOREIGN KEY (`cod_end`) REFERENCES `enderecos` (`cod_end`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionarios`
--

LOCK TABLES `funcionarios` WRITE;
/*!40000 ALTER TABLE `funcionarios` DISABLE KEYS */;
INSERT INTO `funcionarios` VALUES (1,'134.022.527-16','Filipe Miranda','(21) 98485-4658','Gerente','12.341.234-1 ','2001-09-15',1,'admin','03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4'),(2,'664.987.367-55','Bruno','(21) 99361-0835','Vendedor',NULL,'2000-09-12',2,'brunestor','5994471ABB01112AFCC18159F6CC74B4F511B99806DA59B3CAF5A9C173CACFC5'),(4,'703.795.130-72','Ellen Dias','(21) 91234-3818','Gerente','21.342.135-4 ','2002-02-02',4,'ellendias','03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4'),(6,'686.291.267-44','teste111','(21) 22222-2222','Perfumista',NULL,'2002-12-19',6,'teste111','03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4'),(7,'940.790.007-07','Kamilly Mirella Rezende','(21) 98443-0173','Balconista',NULL,'1978-09-22',7,'kamillymirella','03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4'),(8,'818.172.087-30','Isabela Jéssica Rayssa Caldeira','(21) 99725-6877','Perfumista',NULL,'1955-12-12',8,'isabelajessica','03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4'),(9,'413.919.227-51','Luan Leandro Sales','(21) 98256-0723','Balconista',NULL,'1961-05-24',9,'luanleandro','03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4'),(10,'990.259.927-30','Daiane Esther Vieira','(21) 98120-8437','Farmacêutico',NULL,'1992-08-18',12,'daianeesther','03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4'),(13,'177.522.377-95','juliana','(21) 94848-6468','Diretor',NULL,'2002-10-29',13,'julianawtf','03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4');
/*!40000 ALTER TABLE `funcionarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `usuario` varchar(45) NOT NULL,
  `senha` varchar(120) NOT NULL,
  `permissao` int(1) NOT NULL,
  PRIMARY KEY (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('admin','03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4',1),('brunestor','D19949C3D422029D06515A1BE8B6EBCA9733CA3DFBE1E30FC7253AE638D8139D',1),('ellendias','03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4',1),('ronaldo','03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4',4),('teste','46070D4BF934FB0D4B06D9E2C46E346944E322444900A435D7D9A95E6D7435F5',6);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendas`
--

DROP TABLE IF EXISTS `vendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendas` (
  `idvenda` int(11) NOT NULL AUTO_INCREMENT,
  `nomeproduto` varchar(45) NOT NULL,
  `valortotal` varchar(45) NOT NULL,
  `quantidade` varchar(45) NOT NULL,
  `nomecliente` varchar(45) DEFAULT NULL,
  `nomemedico` varchar(45) DEFAULT NULL,
  `datavenda` date DEFAULT NULL,
  `categoria` varchar(45) NOT NULL,
  `idproduto` int(11) NOT NULL,
  `receita` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idvenda`,`idproduto`),
  KEY `nomeproduto_idx` (`nomeproduto`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendas`
--

LOCK TABLES `vendas` WRITE;
/*!40000 ALTER TABLE `vendas` DISABLE KEYS */;
INSERT INTO `vendas` VALUES (2,'produto1','2000.0','1','arnaldo','mc poze','2019-10-25','Remédio',3,'Sim'),(3,'produto3','1000.0','1','sorrizo ronaldo','','2019-10-25','Cosmético',5,NULL),(4,'Tylenol','55.0','2','NEGO NEY','','2019-11-12','Remédio',6,'Não'),(5,'Tylenol','412.5','15','','','2019-11-14','Remédio',6,'Não'),(6,'Tylenol','27.5','1','gabigol','','2019-11-14','Remédio',6,'Não'),(7,'Tylenol','27.5','1','Catarina Emily Mendes','','2019-11-14','Remédio',6,'Não'),(8,'Tylenol','27.5','1','Catarina Emily Mendes','','2019-11-14','Remédio',6,'Não'),(9,'Tylenol','137.5','5','Catarina Emily Mendes','','2019-11-19','Remédio',6,'Não'),(10,'Tylenol','27.5','1','Juliana','','2019-11-19','Remédio',6,'Não');
/*!40000 ALTER TABLE `vendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'farmacia'
--

--
-- Dumping routines for database 'farmacia'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-19  7:55:19
