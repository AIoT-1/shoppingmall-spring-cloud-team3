
--
-- Table structure for table `address`
--
DROP TABLE IF EXISTS `product_category`;
DROP TABLE IF EXISTS `product_image`;
DROP TABLE IF EXISTS `review`;
DROP TABLE IF EXISTS `cart`;
DROP TABLE IF EXISTS `address`;
DROP TABLE IF EXISTS `category`;
DROP TABLE IF EXISTS `order_detail`;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS `point`;
DROP TABLE IF EXISTS `product`;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `product` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `model_number` varchar(10) NOT NULL,
                           `model_name` varchar(120) NOT NULL,
                           `unit_cost` int NOT NULL,
                           `description` text,
                           `thumbnail` varchar(255) DEFAULT NULL,
                           `quantity` int NOT NULL,
                           `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           `deleted_yn` varchar(1) NOT NULL DEFAULT 'N',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `product` DISABLE KEYS */;

-- INSERT INTO `product` VALUES (1 ,'cd_mini11p' ,'11th Mini Album ''SEVENTEENTH HEAVEN'' (Set)' ,62100 ,'-총 3종\n-아웃박스 : 각 버전 1종 / W158 X H161 X D36 (mm)\n-포토북 : 각 버전 1종 / W150 X H150 (mm) / 80 Pages\n-가사지 : 각 버전 1종 / W876 X H146 (mm) / 12 Pages\n-페스티벌 손목밴드 : 각 버전 1종 / W251 X H19 (mm) *초도 한정\n-페이퍼 아트 키트 : 각 버전 4-5종 / W149 X H149 (mm)\n-스티커 : 각 버전 13종 중 1종 랜덤 삽입 / W80 X H80 (mm)\n-포토카드 : 각 버전 26종 중 2종 랜덤 삽입 / W55 X H85 (mm)\n-미니카드 : 각 버전 13종 중 1종 랜덤 삽입 / W55 X H85 (mm)\n- CD-R : 각 버전 1종\n-접지 포스터: 각 버전 1종 / W438 X H292 (mm) *초도 한정' ,'https://github.com/haaazzi/git-practice/blob/main/heaven%20set.png?raw=true' ,100 ,'2024-03-05 23:19:30' ,'N');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

INSERT INTO `product` VALUES (1
                             ,'cd_mini11p'
                             ,'11th Mini Album ''SEVENTEENTH HEAVEN'' (Set)'
                             ,62100
                             ,'-총 3종\n-아웃박스 : 각 버전 1종 / W158 X H161 X D36 (mm)\n-포토북 : 각 버전 1종 / W150 X H150 (mm) / 80 Pages\n-가사지 : 각 버전 1종 / W876 X H146 (mm) / 12 Pages\n-페스티벌 손목밴드 : 각 버전 1종 / W251 X H19 (mm) *초도 한정\n-페이퍼 아트 키트 : 각 버전 4-5종 / W149 X H149 (mm)\n-스티커 : 각 버전 13종 중 1종 랜덤 삽입 / W80 X H80 (mm)\n-포토카드 : 각 버전 26종 중 2종 랜덤 삽입 / W55 X H85 (mm)\n-미니카드 : 각 버전 13종 중 1종 랜덤 삽입 / W55 X H85 (mm)\n- CD-R : 각 버전 1종\n-접지 포스터: 각 버전 1종 / W438 X H292 (mm) *초도 한정'
                             ,'https://github.com/haaazzi/git-practice/blob/main/heaven%20set.png?raw=true'
                             ,100
                             ,'2024-03-05 23:19:30'
                             ,'N')
                           ,(2,'cd_mini11c','11th Mini Album ''SEVENTEENTH HEAVEN'' Carat Ver. (Random)',12600,'-총 1종\n-아웃박스: 1종 / W158 X H161 X D34 (mm)\n-하드커버 바인더: 13종 중 1종 랜덤 삽입 (각 버전별 커버 상이) / W82 X H105 (mm) / 28 포켓\n-북클릿: 13종 중 랜덤 1세트(24 ea) 삽입 / W55 X H85 (mm) *하드커버 바인더와 동일한 멤버 세트로 제공\n-가사지: 1종 / W145 X H145 (mm) / 12 Pages\n- CD-R: 1종\n-포토카드: 52종 중 4종 랜덤 삽입 / W55 X H85 (mm)','https://github.com/haaazzi/git-practice/blob/main/heaven%20carat%20version.png?raw=true',100,'2024-03-06 08:10:39','N'),(3,'cd_mini10p','10th Mini Album ''FML'' (Set)',57900,'-총 3종\n-아웃박스: 각 버전 1종 / W160 X H160 (mm)\n-포토북: 각 버전 1종 / W150 X H150 (mm) / 88 Pages\n-가사지: 각 버전 1종 / W150 X H150 (mm) / 12 Pages\n-CD-R: 각 버전 1종\n-스티커: 각 버전 13종 중 1종 랜덤 삽입 / W110 X H80 (mm)\n-포토카드: 각 버전 26종 중 2종 랜덤 삽입 / W55 X H85 (mm)','https://github.com/haaazzi/git-practice/blob/main/fml.png?raw=true',100,'2024-03-06 08:10:39','N'),(4,'cd_mini10c','10th Mini Album ''FML'' (CARAT Ver.)',12300,'-총 1종\n-아웃박스: 1종 / W160 X H160 (mm)\n-하드커버 바인더: 13종 중 1종 랜덤 삽입 (각 버전별 커버 상이) / W82 X H105 (mm) / 28 포켓\n-북클릿: 13종 중 랜덤 1세트(24 ea) 삽입 / W55 X H85 (mm) *하드커버 바인더와 동일한 멤버 세트로 제공\n-가사지: 1종 / W150 X H150 (mm) / 12 Pages\n- CD-R: 1종\n-포토카드: 52종 중 4종 랜덤 삽입 / W55 X H85 (mm)','https://github.com/haaazzi/git-practice/blob/main/Fml%20carat.png?raw=true',0,'2024-03-06 08:10:39','N'),(5,'cd_mini10w','10th Mini Album ''FML'' (Weverse Albums ver.)',11500,'-총 1종\n-카드 홀더: 1종 / W120 X H80 (mm)\n-QR 카드: 13종 중 1종 랜덤 삽입 / W54 X H86 (mm)\n-셀카 포토카드: 26종 중 2종 랜덤 삽입 / W54 X H86 (mm)\n*QR 카드와 동일 멤버 삽입\n-가이드: 1종 / W115 X H75 (mm)','https://github.com/haaazzi/git-practice/blob/main/fml%20weverse%20version.png?raw=true',100,'2024-03-06 08:10:39','N'),(6,'cd_bss1s','부석순 1st Single Album ''SECOND WIND'' (Special Ver.)',59400,'- 총 1종\n- 카드 홀더: 1종 / W120 X H80\n- QR카드: 3종 중 1종 랜덤 삽입 / W54 X H86\n- 셀피 포토카드: 6종 중 2종 랜덤 삽입 (QR 카드 멤버 동일) / W54 X H86\n- 유저가이드: 1종 / W115 X H75','https://github.com/haaazzi/git-practice/blob/main/bss%20special.png?raw=true',100,'2024-03-06 08:10:39','N'),(7,'cd_bss1p','부석순 1st Single Album ''SECOND WIND''',17800,'- 총 1종\n- 아웃박스: 1종 / W154 X H184\n- 포토북: 1종 / W150 X H180 / 88 Pages\n- CD-R: 1종\n- 가사지: 1종 / W150 X H180 / 8 Pages\n- 포토카드: 6종 중 2종 랜덤 삽입 / W55 X H85\n- 해빗 트래커: 3종 중 1종 랜덤 삽입 / W100 X H100\n- 부석순 카드: 3종 중 1종 랜덤 삽입 / W80 X H80\n- 스티커: 1종 / W100 X H80\n- 접지포스터: 1종 / W180 X H300','https://github.com/haaazzi/git-practice/blob/main/bss.png?raw=true',0,'2024-03-06 08:10:39','N'),(8,'cd_4thp','4th Album ''Face the Sun'' (Set)',99500,'- 총 5종\n\n- 1 CD: 각 버전 1종\n\n- 형광펜: 13종 중 랜덤 1종 / ※ 초회 한정 증정\n\n- 포토북: 각 버전 1종 / W 257 X H 182 (mm) X T 8.5 (mm) / 88 pages\n\n- 페트카드: 각 버전 1종 / W 100 X H 80 (mm)\n\n- 가사지: 1종 / W 257 X H 182 (mm) / 16 pages\n\n- 포토카드: 각 버전 52종 중 랜덤 4종\n\n- 엽서: 각 버전 26종 중 랜덤 2종','https://github.com/haaazzi/git-practice/blob/main/facethesun.png?raw=true',100,'2024-03-06 08:24:16','N'),(9,'cd_4thw','4th Album ''Face the Sun'' (Weverse Albums ver.)',8900,'- 총 1종\n\n- 카드 홀더: 1종 / W 120 X H 80 (mm)\n\n- QR 카드: 13종 중 랜덤 1종 / W 54 X H 86 (mm)\n\n- 포토카드: 26종 중 2종 (QR 카드 멤버와 동일) / W 54 X H 86 (mm)\n\n-가이드: 1종 / W 120 X H 80 (mm)','https://github.com/haaazzi/git-practice/blob/main/facethesun%20weverse.png?raw=true',100,'2024-03-06 08:24:16','N'),(10,'cd_mini9p','9th Mini Album [Attacca] (Set)',53500,'- 총 3종 [Op.1, Op.2, Op.3]\n- 포토북 (78p) : Op.1 3종, Op.2 3종, Op.3 4종 중 각 버전별 랜덤 1종\n- 리릭 케이스 : 각 버전별 디자인 상이\n- 1CD : 각 버전별 디자인 상이\n- 레이어드카드 : 각 버전 13종 중 랜덤 1종 (초도 한정)\n- 포토엽서 : 각 버전 13종 중 랜덤 1종\n- 폴딩카드 : 각 버전 13종 중 랜덤 1종\n- 포토카드 : 각 버전 26종 중 랜덤 2종\n- 포스터 : 총 4종 중 랜덤 1종','https://github.com/haaazzi/git-practice/blob/main/attacca.png?raw=true',0,'2024-03-06 08:24:16','N'),(11,'cd_mini8p','8th Mini Album ''Your Choice'' (SET)',53700,'- 총 3종 [ONE SIDE, OTHER SIDE, BESIDE]\n- 1 CD: 각 버전별 디자인 상이\n- 포토북 (80p): 각 버전 ONE SIDE 3종, OTHER SIDE\n2종, BESIDE 5종으로 구성\n** [ONE SIDE] 3종 중 랜덤 1종\n[OTHER SIDE] 2종 중 랜덤 1종\n[BESIDE] 5종 중 랜덤 1종\n- 포토카드: 각 버전13종 중 랜덤 1종\n- 포스트카드: 각 버전 13종 중 랜덤 1종\n- 스티커: 각 버전 1종\n- 중철 가사지 (8p): 각 버전 1종\n- 투명 책갈피: 각 버전 1종\n- 미니카드: 각 버전 13종 중 랜덤 1종 (**ONE SIDE, BESIDE 버전에만 삽입)\n- 유닛카드: 6종 중 랜덤 1종 (**OTHER SIDE 버전에만 삽입)','https://github.com/haaazzi/git-practice/blob/main/yourchoice.jpeg?raw=true',100,'2024-03-06 08:24:16','N'),(12,'md_birth1','HAPPY S.COUPS DAY BIRTHDAY BOX VER.3',45000,'Photobook : 18.3 x 26\nPhotocard Set : 5.5 x 8.5\nMini Poster Set : 25 x 35\nPhoto Set : 10.2 x 15.2\nPostcard Set : 10 x 15\nInvitation Card : 11 x 16\nBalloon Set : 5 x 12\nSealing Wax : 1.2 x 9.2\nSealing Stamp : 2.5 x 6.5','https://github.com/haaazzi/git-practice/blob/main/birth1.png?raw=true',50,'2024-03-06 08:52:30','N'),(13,'md_birth2','HAPPY JEONGHAN DAY BIRTHDAY BOX VER.3',45000,'Photobook : 18.3 x 26\nPhotocard Set : 5.5 x 8.5\nMini Poster Set : 25 x 35\nPhoto Set : 10.2 x 15.2\nPostcard Set : 10 x 15\nInvitation Card : 11 x 16\nBalloon Set : 5 x 12\nSealing Wax : 1.2 x 9.2\nSealing Stamp : 2.5 x 6.5','https://github.com/haaazzi/git-practice/blob/main/birth2.png?raw=true',50,'2024-03-06 08:52:30','N'),(14,'md_doll1','[JEONGHAN] Pluffy Toram & Blanket Set',65000,'	(Plush Toy) SHELL: 95% POLYESTER 5% POLYURETHANE\nFILL1: POLYESTER FIBER / FILL2: POLYETHYLENE FOAM\n(Blanket Cover) SHELL: 95% POLYESTER 5% POLYURETHANE\nLINING: 95% POLYESTER 5% POLYURETHANE\n(Blanket) 100% POLYESTER\n(Sticker) PAPER\n(Making Log & Photo Card) Paper','https://github.com/haaazzi/git-practice/blob/main/doll1.png?raw=true',50,'2024-03-06 08:52:30','N'),(15,'md_card1','[CAFE 2023]Trading Card Set',7000,'8EA / 1SET\n(RANDOM)','https://github.com/haaazzi/git-practice/blob/main/card1.png?raw=true',50,'2024-03-06 08:52:30','N'),(16,'md_card2','[DREAM] Photo Card',6800,'PHOTO CARD 3EA (RANDOM)','https://github.com/haaazzi/git-practice/blob/main/card2.png?raw=true',50,'2024-03-06 08:52:30','N'),(17,'tr_stick','OFFICIAL LIGHT STICK VER.3',49000,'Light Stick, Dust Bag, Strap, Product Manual\n(Batteries excluded)','https://github.com/haaazzi/git-practice/blob/main/stick.png?raw=true',50,'2024-03-06 08:52:30','N'),(18,'tr_card1','[FOLLOW JAPAN] Photo Card',6900,'PHOTO CARD 3EA (RANDOM)','https://github.com/haaazzi/git-practice/blob/main/trcard1.png?raw=true',50,'2024-03-06 08:52:30','N'),(19,'tr_card2','[FOLLOW SEOUL] Trading Card Set',7000,'8EA / 1SET\n(RANDOM)tr','https://github.com/haaazzi/git-practice/blob/main/trcard2.png?raw=true',50,'2024-03-06 08:52:30','N'),(20,'tr_card3','[CARATLAND 2023] Trading Card Set',7000,'8EA / 1SET\n(RANDOM)','https://github.com/haaazzi/git-practice/blob/main/trcard3.png?raw=true',50,'2024-03-06 08:52:30','N'),(21,'tr_key1','[CARATLAND 2023] Acrylic Keyring',19000,'Acrylic Keyring1EA + Photocard 1EA / 1SETke','https://github.com/haaazzi/git-practice/blob/main/keyring1.png?raw=true',50,'2024-03-06 08:52:30','N'),(22,'tr_card4','[BE THE SUN] Trading Card Set',6000,'8EA / 1SET\n(RANDOM)','https://github.com/haaazzi/git-practice/blob/main/trcard4.png?raw=true',0,'2024-03-06 08:52:30','N'),(23,'dvd_social','PHOTOBOOK ''SOCIAL CLUB : CARAT'' SET',69000,'지역코드 : 1,3,4,5,6 재생시간 : about 60 mins 자막 : KOR, ENG 오디오 : 	DIGITAL STEREO','https://github.com/haaazzi/git-practice/blob/main/social.png?raw=true',100,'2024-03-06 09:03:42','N'),(24,'dvd_c21dc','2021 〈SEVENTEEN in CARAT LAND〉 MEMORY BOOK+ DIGITAL CODE',52000,'재생시간 : about 230 mins 자막 : KOR, ENG, JAP 오디오 : 	DIGITAL STEREO','https://github.com/haaazzi/git-practice/blob/main/c21dc.png?raw=true',100,'2024-03-06 09:07:58','N'),(25,'dvd_c21d','2021 〈SEVENTEEN in CARAT LAND〉 MEMORY BOOK+ DVD',42000,'지역코드 : 1,3,4,5,6 재생시간 : about 230 mins 자막 : KOR, ENG, JAP 오디오 : 	DIGITAL STEREO','https://github.com/haaazzi/git-practice/blob/main/c21d.png?raw=true',100,'2024-03-06 09:07:58','N'),(26,'dvd_c22dc','2022 〈SEVENTEEN in CARAT LAND〉 MEMORY BOOK+ DIGITAL CODE',52000,'재생시간 : about 240 mins 자막 : KOR, ENG, JAP 오디오 : 	DIGITAL STEREO','https://github.com/haaazzi/git-practice/blob/main/c22dc.png?raw=true',0,'2024-03-06 09:07:58','N'),(27,'dvd_c22d','2022 〈SEVENTEEN in CARAT LAND〉 MEMORY BOOK+ DVD',42000,'지역코드 : 1,3,4,5,6 재생시간 : about 230 mins 자막 : KOR, ENG, JAP 오디오 : 	DIGITAL STEREO','https://github.com/haaazzi/git-practice/blob/main/c22d.png?raw=true',100,'2024-03-06 09:07:58','N'),(28,'dvd_c23dc','2023 SVT 7TH FAN MEETING <SEVENTEEN in CARAT LAND> MEMORY BOOK+ DIGITAL CODE',55000,'재생시간 : about 230 mins 자막 : KOR, ENG, JAP 오디오 : 	DIGITAL STEREO','https://github.com/haaazzi/git-practice/blob/main/d23.png?raw=true',100,'2024-03-06 09:08:44','N'),(29,'m_kit','CARAT MEMBERSHIP KIT',15000,'OUT BOX 1 EA : PAPER\nPHOTO BOOK 1 EA : PAPER\nMEMBERSHIP CARD 1 EA : PVC\nPHOTO CARD SET 13 EA : PAPER\nID PHOTO SET 13 EA : PAPER\nPOST CARD 2 EA : PAPER\nSTICKER 1 EA : PAPER\nNAME TAG 1 EA : PS, MAGNET\nBADGE 1 EA : STEEL, RUBBER','https://github.com/haaazzi/git-practice/blob/main/kit.png?raw=true',1000,'2024-03-06 09:14:55','N'),(30,'m_card','CARAT MEMBERSHIP',22000,'위버스 계정(Weverse Account) 회원에 한하여 1개의 ID당 1개의 멤버십 가입 및 구매가 가능합니다.\n위버스(Weverse)와 위버스샵(Weverse Shop)에서 동일한 계정을 사용하셔야 정상적으로 멤버십 혜택을 받으실 수 있습니다.mem','https://github.com/haaazzi/git-practice/blob/main/mem.png?raw=true',1000,'2024-03-06 09:14:55','N'),(31,'p_thename','2022 SVT PHOTOBOOK THE NAME;17',40000,'재생시간 : about 45 mins 자막 : KOR, ENG, JAP 오디오 : 	DIGITAL STEREO','https://github.com/haaazzi/git-practice/blob/main/thename.png?raw=true',100,'2024-03-06 09:17:43','N'),(32,'p_going1','[GOING] Magazine',17000,'	MAGAZINE : W230 x H300 x T11mm / 128pages\nPHOTOCARD : W55 x H85mm (13종 1세트, 2가지 버전 중 랜덤)','https://github.com/haaazzi/git-practice/blob/main/going.jpeg?raw=true',100,'2024-03-06 09:17:43','N'),(33,'p_going2','[GOING] Magazine Vol.2',22000,'	MAGAZINE : W230 x H300 x T11mm / 128pages\nPHOTOCARD : W55 x H85mm (13종 1세트, 2가지 버전 중 랜덤)','https://github.com/haaazzi/git-practice/blob/main/going2.png?raw=true',100,'2024-03-06 09:17:43','N'),(34,'p_nana','NANA TOUR with SEVENTEEN 2024 MOMENT PACKAGE',33000,'재생시간 : about 240 mins 자막 : KOR, ENG, JAP 오디오 : 	DIGITAL STEREO','https://github.com/haaazzi/images-for-assemble-project/blob/add/images/nanatour.png?raw=true',0,'2024-03-13 15:42:32','N'),(35,'p_memory','Memory Book',20000,'재생시간 : about 230 mins 자막 : KOR, ENG, JAP 오디오 : 	DIGITAL STEREO','https://github.com/haaazzi/images-for-assemble-project/blob/add/images/memory.jpeg?raw=true',20,'2024-03-13 15:42:32','N'),(36,'md_doll2','Plush Toy Set',54000,'BONGBONGEE 1EA + DDOONG RANG 1EA / 1SET','https://github.com/haaazzi/images-for-assemble-project/blob/add/images/doll2.png?raw=true',10,'2024-03-13 15:45:20','N');

/*!40000 ALTER TABLE `product` ENABLE KEYS */;

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `user_id` bigint NOT NULL,
                           `type` varchar(50) DEFAULT NULL COMMENT '회사, 집 ....',
                           `zip_code` varchar(10) NOT NULL,
                           `address` varchar(255) NOT NULL,
                           `address_detail` varchar(255) DEFAULT NULL COMMENT '동. 호수',
                           `default_yn` varchar(1) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;

--
-- Table structure for table `cart`
--



/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `user_id` bigint NOT NULL,
                        `product_id` bigint NOT NULL,
                        `quantity` int NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,1,1,1);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;

--
-- Table structure for table `category`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `name` varchar(50) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'CD'),(2,'MERCH'),(3,'TOUR'),(4,'DVD'),(5,'MEMBERSHIP'),(6,'PHOTOBOOK');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

--
-- Table structure for table `order`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `user_id` bigint NOT NULL,
                         `price` int NOT NULL,
                         `order_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'INDEX',
                         `ship_date` datetime DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;

--
-- Table structure for table `order_detail`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_detail` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `order_id` bigint NOT NULL,
                                `address` varchar(255) NULL,
                                `product_id` bigint NOT NULL,
                                `quantity` int NOT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;

--
-- Table structure for table `point`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `point` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `user_id` bigint NOT NULL,
                         `transaction_type` varchar(25) DEFAULT NULL,
                         `amount` int DEFAULT NULL,
                         `record_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'INDEX',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `point`
--

/*!40000 ALTER TABLE `point` DISABLE KEYS */;
INSERT INTO `point` VALUES (1,2,'가입 포인트',1000000,'2024-03-06 08:34:13'),(2,3,'가입 포인트',1000000,'2024-03-06 08:34:54'),(3,4,'가입 포인트',1000000,'2024-03-06 08:36:34');
/*!40000 ALTER TABLE `point` ENABLE KEYS */;

--
-- Table structure for table `product`
--



--
-- Table structure for table `product_category`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_category` (
                                    `id` bigint NOT NULL AUTO_INCREMENT,
                                    `product_id` bigint NOT NULL,
                                    `category_id` bigint NOT NULL,
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,1),(6,6,1),(7,7,1),(8,8,1),(9,9,1),(10,10,1),(11,11,1),(12,12,2),(13,13,2),(14,14,2),(15,15,2),(16,16,2),(17,17,3),(18,18,3),(19,19,3),(20,20,3),(21,21,3),(22,22,3),(23,23,4),(24,24,4),(25,25,4),(26,26,4),(27,27,4),(28,28,4),(29,29,5),(30,30,5),(31,31,6),(32,32,6),(33,33,6),(34,34,6),(35,35,6),(36,36,2);
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;

--
-- Table structure for table `product_image`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_image` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `product_id` bigint NOT NULL,
                                 `image` varchar(255) DEFAULT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_image`
--

/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;

--
-- Table structure for table `review`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `product_id` bigint NOT NULL,
                          `user_id` bigint NOT NULL,
                          `rating` int NOT NULL,
                          `comment` text NOT NULL,
                          `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          `updated_at` datetime DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,1,2,3,'너무 너무 너무 너무 너무 너무 비싸요','2024-03-06 08:37:17',NULL),(2,1,3,5,'배송 빨라서 좋아요~! ! ! ! ','2024-03-06 08:38:07',NULL),(3,2,2,5,'짱 짱 짱 짱 짱 짱','2024-03-06 08:38:24',NULL),(4,3,4,2,'생각보다 별로에요,,','2024-03-06 08:39:18',NULL);

/*!40000 ALTER TABLE `review` ENABLE KEYS */;

--
-- Table structure for table `user`
--


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `login_id` varchar(50) NOT NULL,
                        `name` varchar(30) DEFAULT NULL,
                        `password` varchar(255) NOT NULL,
                        `birth_date` date DEFAULT NULL,
                        `auth` varchar(10) DEFAULT NULL,
                        `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '인덱스',
                        `last_login_at` datetime DEFAULT NULL,
                        `terminated_yn` varchar(1) NOT NULL DEFAULT 'N',
                        `point` bigint DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin','12345','1900-01-01','ROLE_ADMIN','2024-03-05 15:31:54',NULL,'N',100000),(2,'hani','홍길동','12345','1995-10-04','ROLE_USER','2024-03-06 08:34:13',NULL,'N',1000000),(3,'haazzi','권순영','12345','1996-06-15','ROLE_USER','2024-03-06 08:34:54',NULL,'N',1000000),(4,'bbobbi','김말숙','12345','2003-04-11','ROLE_USER','2024-03-06 08:36:34',NULL,'N',1000000);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
