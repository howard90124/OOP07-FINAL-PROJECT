-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: deliver
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `position` (
  `address` varchar(30) DEFAULT NULL,
  `latitude` varchar(30) DEFAULT NULL,
  `longitude` varchar(30) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES ('台北市士林區文林路110巷9號','25.088','121.527','雞の專家_劍潭'),('台北市信義區忠孝東路五段743巷7號','25.043','121.581','春來麵線'),('106台北市大安區和平東路二段201號','25.034','121.543','Miss Energy 低GI廚房 台北'),('台北市中山區吉林路456巷16-1號','25.067','121.53','樸食'),('114台北市內湖區內湖路一段285巷66號之3號','25.085','121.567','煲柏思維'),('台北市大安區和平東路二段321號','25.025','121.545','盛味豐炭烤燒餅'),('台北市大安區通安街77號','25.03','121.553','UFO車輪餅'),('台北市內湖區江南街65巷29號','25.077','121.578','緯大雞排（內湖店）Gver.'),('台北市士林區大南路313號','25.089','121.518','腿庫*豬腳便當'),('台北市內湖區江南街119號','25.076','121.576','古早味涼麵&雞魯飯'),('台北市士林區福港街9號','25.091','121.518','阿琴自助餐'),('台北市萬大路385之1號','25.023','121.499','鴨寶精緻便當'),('台北市內湖區內湖路一段737巷21號旁','25.08','121.579','萊客潤餅捲(內湖)'),('台北市內湖區康樂街72巷8弄1號','25.069','121.618','東湖頂尖排骨大王'),('台北市松山區八德路四段','25.049','121.569','湯圓妹現烤甜甜圈 八德店'),('台北市大安區泰順街40巷38號','25.024','121.53','Uncle L2 手作舒芙蕾甜點店'),('台北市延平北路','25.09','121.503','億圓花炮煙火公司'),('台北市大安區復興南路二段13-1號','25.033','121.544','大安站那邊'),('台北市信義區松山路574號','25.037','121.578','台灣第一味(台北松山店)'),('台北市中山區雙城街17巷5號','25.065','121.525','台灣大咖哩(雙城)'),('台北市南港區向陽路90號','25.053','121.595','龍記燒臘-南港店'),('台北市大安區龍泉街31號','25.024','121.529','壹森泰食堂'),('台北市松山區民權東路三段160巷25號','25.06','121.547','juice-detour'),('台北市中山區龍江路382號','25.067','121.541','邱太太'),('10491台北市中山區復興北路82號','25.051','121.544','老蔡水煎包-復興店'),('台北市大同區延平北路二段36巷4號','25.055','121.512','廣大順'),('台北市內湖區新明路467號','25.054','121.58','真味肉圓 (台北新明店)'),('台北市八德路三段2號','25.048','121.549','珍蜜便當2020更新'),('台北市中山區中原街119號','25.062','121.529','GABA 元気の源！嘎吧！');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-13 13:48:22
