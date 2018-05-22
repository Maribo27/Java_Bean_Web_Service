-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: 
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Current Database: `java_bean`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `java_bean` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `java_bean`;

--
-- Table structure for table `ejb_info`
--

DROP TABLE IF EXISTS `ejb_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ejb_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ejb_info_id_uindex` (`id`),
  UNIQUE KEY `ejb_info_name_uindex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ejb_info`
--

LOCK TABLES `ejb_info` WRITE;
/*!40000 ALTER TABLE `ejb_info` DISABLE KEYS */;
INSERT INTO `ejb_info` (`id`, `name`, `description`) VALUES (1,'Версии',' * EJB 3.2, final release (2013-05-28)\r\n * EJB 3.1, final release (2009-12-10)\r\n * EJB 3.0, final release (2006-05-11)\r\n * EJB 2.1, final release (2003-11-24)\r\n * EJB 2.0, final release (2001-08-22)\r\n * EJB 1.1, final release (1999-12-17)\r\n * EJB 1.0 (1998-03-24)'),(2,'Общие обязанности',' * Обработка транзакции\r\n * Интеграция с услугами сохранения, предлагаемыми API Java Persistence (JPA)\r\n * Контроль параллелизма\r\n * Управляемое событиями программирование с использованием службы сообщений Java и архитектуры Java EE Connector\r\n * Асинхронный вызов метода\r\n * Расписание работы\r\n * Имена и службы каталогов (JNDI)\r\n * Межпроцессное взаимодействие с использованием RMI-IIOP и веб-служб\r\n * Безопасность (JCE и JAAS)\r\n * Развертывание программных компонентов на сервере приложений');
/*!40000 ALTER TABLE `ejb_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ejb_type`
--

DROP TABLE IF EXISTS `ejb_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ejb_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ejb_type_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ejb_type`
--

LOCK TABLES `ejb_type` WRITE;
/*!40000 ALTER TABLE `ejb_type` DISABLE KEYS */;
INSERT INTO `ejb_type` (`id`, `name`, `description`) VALUES (1,'Entity Bean','Entity bean представляет собой компоненту, работающую с постоянной (persistent) информацией, хранящейся, например, в базе данных. Entity beans ассоциируются с элементами баз данных и могут быть доступны одновременно нескольким пользователям. Так как информация в базе данных является постоянной, то и entity beans живут постоянно, выживая, тем самым, после сбоев сервера (когда сервер восстанавливается после сбоя, он может восстановить бин из базы данных).'),(2,'Message-Driven Bean','Message-Driven Beans (MDB). \r\nMDB работает в кооперации с системой сообщений JAVA [Java Messaging System](JMS), которая является абстрактным API для системы Message-Oriented Middleware (MOM), более-менее похожую на то, как JDBC является абстрактным API поверх SQL базы данных.\r\nКоротко, система MOM предоставляет модель сообщений с публичной подпиской, основанной на асинхронной, распределенной очереди сообщений. \r\nMDB обычно реализуются для выполнения некоторых действий при получении сообщений и выступают в роли объектно-ориентированных точек соединения между подсистемами, взаимодействующих посредством JMS.\r\nОтличие MDB от session beans и entity beans состоит в том, что они не предоставляют никаких удаленных или локальных представлений. Другими словами, клиентский код не может получить доступ к MDB, но MDB может использовать другие EJB и другие службы.'),(3,'Session Bean','Session bean представляет собой EJB-компоненту, связанную с одним клиентом. Бины этого типа, как правило, имеют ограниченный срок жизни (хотя это и не обязательно), и редко участвуют в транзакциях. В частности, они обычно не восстанавливаются после сбоя сервера. В качестве примера session bean можно взять бин, который живет в веб-сервере и динамически создает HTML-страницы клиенту, при этом следя за тем, какая именно страница загружена у клиента. Когда же пользователь покидает вэб-узел, или по истечении некоторого времени, session bean уничтожается. Несмотря на то, что в процессе своей работы, session bean мог сохранять некоторую информацию в базе данных, его предназачение заключается все-таки не в отображении состояния или в работе с вечными объектами, а просто в выполнении некоторых функций на стороне сервера от имени одного клиента.\r\nSession beans получаются в двух разных вариантах: \r\n * session beans не имеющий состояний (Stateless session beans - SLSB);\r\n * session beans поддерживающие состояния (Stateful session beans - SFSB).');
/*!40000 ALTER TABLE `ejb_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `method`
--

DROP TABLE IF EXISTS `method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `method` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `necessity` enum('обязателен','если присутствует соответствующее поле','если присутствует соответствующее индексированное поле') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `method_name_uindex` (`name`),
  UNIQUE KEY `method_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `method`
--

LOCK TABLES `method` WRITE;
/*!40000 ALTER TABLE `method` DISABLE KEYS */;
INSERT INTO `method` (`id`, `name`, `description`, `necessity`) VALUES (1,'public boolean equals(Object obj)','Метод сравнивает строку с указанным объектом. Результатом является значение true только в том случае, если аргумент не равен null и является строковым объектом (String), который представляет ту же последовательность символов как и этот объект.','обязателен'),(2,'public int hashCode()','Метод используется для получения уникального целого номера для данного объекта. \r\nКогда необходимо сохранить объект как структуру данных в некой хэш-таблице, этот номер используется для определения его местонахождения в этой таблице. \r\nПо умолчанию, метод hashCode() для объекта возвращает номер ячейки памяти, где объект сохраняется.','обязателен'),(3,'public String toString()','Метод преобразует и возвращает строку.','обязателен'),(4,'public <PropertyType> getPropertyName()','Метод возвращает значение выбранного свойства.','если присутствует соответствующее поле'),(5,'public void setPropertyName (<PropertyType> value)','Метод изменяет значение выбранного свойства.','если присутствует соответствующее поле'),(6,'public boolean isPropertyName()','Метод возвращает значение выбранного логического свойства.','если присутствует соответствующее поле'),(7,'public <PropertyType> getPropertyName(int index)','Метод возвращает значение выбранного индексированного свойства.','если присутствует соответствующее индексированное поле'),(8,'public void setPropertyName(int index, <PropertyType> value)','Метод изменяет значение выбранного индексированного свойства.','если присутствует соответствующее индексированное поле'),(9,'public <PropertyType>[] getPropertyName()','Метод возвращает все значения выбранного массива свойств.','если присутствует соответствующее индексированное поле'),(10,'public void setPropertyName(<PropertyType>[] value)','Метод изменяет все значения выбранного массива свойств.','если присутствует соответствующее индексированное поле');
/*!40000 ALTER TABLE `method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `property_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property`
--

LOCK TABLES `property` WRITE;
/*!40000 ALTER TABLE `property` DISABLE KEYS */;
INSERT INTO `property` (`id`, `name`, `description`) VALUES (1,'Простые свойства','Обычно каждый бин имеет свойства, которые определяют, как он будет работать и/или как он будет выглядеть. Эти свойства являются private или protected полями класса бина, которые доступны для выборки и/или модификации через специальные public методы.\r\nСвойства класса должны быть доступны через get, set и другие методы (так называемые методы доступа), которые должны подчиняться стандартному соглашению об именах. \r\nЭто легко позволяет инструментам автоматически определять и обновлять содержание bean’ов. Многие инструменты даже имеют специализированные редакторы для различных типов свойств.'),(2,'Индексированные свойства','Помимо простых свойств, которые могут принимать только одно значение, существуют и свойства - массивы значений.\r\nОни должны быть описаны как поля-массивы и должны иметь методы для изменения/получения как всех значений вместе, так и определённых.'),(3,'Связанные свойства','Важным аспектом технологии JavaBeans является возможность бинов взаимодействовать с другими объектами, в частности, с другими бинами. JavaBeans реализует такое взаимодействие путем генерации (firing) событий и прослушивания (listening) событий.\r\nВ приложении к бинам взаимодействие объектов с бином через событийную модель выглядит так. Объект, который интересуется тем, что может произойти во внешнем, по отношению к нему, бине, может зарегистрировать себя как слушателя (listener) этого бина. В результате, при возникновении соответствующего события в бине будет вызван определенный метод данного объекта, которому в качестве параметра будет передан объект-событие (event). Причем, если зарегистрировалось несколько слушателей, то эти методы будут последовательно вызваны для каждого слушателя.'),(4,'Ограниченные свойства','Ограниченные свойства введены для того, чтобы была возможность запретить изменение свойства бина, если это необходимо. Т.е. бин будет как-бы спрашивать разрешение у зарегистрированных слушателей на изменение данного свойства. В случае, если слушатель не разрешает ему менять свойство, он генерирует исключение PropertyVetoException. Соответственно set-метод для ограниченного свойства должен иметь в своем описании throws PropertyVetoException. Это заставляет перехватывать это исключение в точке вызова этого set-метода. В результате прикладная программа, использующая этот бин, будет извещена, что ограниченное свойство не было изменено.');
/*!40000 ALTER TABLE `property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name_uindex` (`name`),
  UNIQUE KEY `role_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `name`, `description`) VALUES (1,'Application Assembler','Использует различные развернутые компоненты в специфической среде для создания полного приложения.'),(2,'Deployer','Для EJB, развертывание состоит в процессе установки одного или нескольких EJB компонентов в специфическом EJB Контейнере. Deployer является экспертом в специфической рабочей среде и отвечает за связывание EJB со всеми ресурсами, которые ему нужны для работы (соединение с базой данных, таблицы, другие EJB и тому подобное).'),(3,'Enterprise Bean Provider','Реализует EJB компонент и оформляет его для распространения. Знает о прикладной области, но может не знать об операционной среде, в которой компонент будет использоваться.'),(4,'System Administrator','Он отвечает за создание и поддержку пользователей, баз данных и за общую инфраструктуру ресурсов, необходимых для специфической рабочей среды.');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rule`
--

DROP TABLE IF EXISTS `rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rule_entity_uindex` (`name`),
  UNIQUE KEY `rule_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rule`
--

LOCK TABLES `rule` WRITE;
/*!40000 ALTER TABLE `rule` DISABLE KEYS */;
INSERT INTO `rule` (`id`, `name`, `description`) VALUES (1,'Конструктор','Класс должен иметь конструктор без параметров, с модификатором доступа public. Такой конструктор позволяет инструментам создать объект без дополнительных сложностей с параметрами.'),(2,'Переопределённые методы','Класс должен иметь переопределенные методы equals(), hashCode() и toString().'),(3,'Ограниченные свойства','Ограниченные свойства введены для того, чтобы была возможность запретить изменение свойства бина, если это необходимо. Т.е. бин будет как-бы спрашивать разрешение у зарегистрированных слушателей на изменение данного свойства. В случае, если слушатель не разрешает ему менять свойство, он генерирует исключение PropertyVetoException. Соответственно set-метод для ограниченного свойства должен иметь в своем описании throws PropertyVetoException. Это заставляет перехватывать это исключение в точке вызова этого set-метода. В результате прикладная программа, использующая этот бин, будет извещена, что ограниченное свойство не было изменено.'),(4,'Связанные свойства','Важным аспектом технологии JavaBeans является возможность бинов взаимодействовать с другими объектами, в частности, с другими бинами. JavaBeans реализует такое взаимодействие путем генерации (firing) событий и прослушивания (listening) событий.\r\nВ приложении к бинам взаимодействие объектов с бином через событийную модель выглядит так. Объект, который интересуется тем, что может произойти во внешнем, по отношению к нему, бине, может зарегистрировать себя как слушателя (listener) этого бина. В результате, при возникновении соответствующего события в бине будет вызван определенный метод данного объекта, которому в качестве параметра будет передан объект-событие (event). Причем, если зарегистрировалось несколько слушателей, то эти методы будут последовательно вызваны для каждого слушателя.'),(5,'Сохраняемость','Класс должен быть сериализуем. Это даёт возможность надёжно сохранять, хранить и восстанавливать состояние bean независимым от платформы и виртуальной машины способом.');
/*!40000 ALTER TABLE `rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type_name_uindex` (`name`),
  UNIQUE KEY `type_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` (`id`, `name`, `description`) VALUES (1,'JavaBeans','Классы в языке Java, написанные по определённым правилам. \r\nОни используются для объединения нескольких объектов в один для удобной передачи данных.\r\nСпецификация Sun Microsystems определяет JavaBeans как повторно используемые программные компоненты, которыми можно управлять, используя графические конструкторы и средства IDE.\r\nJavaBeans обеспечивают основу для многократно используемых, встраиваемых и модульных компонентов ПО. \r\nКомпоненты JavaBeans могут принимать различные формы, но наиболее широко они применяются в элементах графического пользовательского интерфейса. \r\nОдна из целей создания JavaBeans — взаимодействие с похожими компонентными структурами. Например, Windows-программа, при наличии соответствующего моста или объекта-обёртки, может использовать компонент JavaBeans так, будто бы он является компонентом COM или ActiveX.\r\nТак как требования в основном изложены в виде соглашения, а не интерфейса, некоторые разработчики рассматривают JavaBeans, как Plain Old Java Objects, которые следуют определённым правилам именования. '),(2,'Enterprise JavaBeans','Enterprise JavaBeans (EJB) является одним из нескольких API Java для модульной разработки корпоративного программного обеспечения. EJB является программным компонентом на стороне сервера, который инкапсулирует бизнес-логику приложения. Веб-контейнер EJB обеспечивает среду выполнения для веб-программного обеспечения, включая компьютерную безопасность, сервлет Java, управление жизненным циклом, обработку транзакций и другие веб-службы. Спецификация EJB является подмножеством спецификации Java EE.');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-22  5:10:41
