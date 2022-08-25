DROP TABLE IF EXISTS `readings`;

CREATE TABLE `readings` (
                          `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
                          `type` varchar(255) DEFAULT NULL,
                          `readingHoog` int(11) DEFAULT NULL,
                          `readingLaag` int(11) DEFAULT NULL,
                          `readingZon` int(11) DEFAULT NULL,
                          `Gas` int(11) DEFAULT NULL,
                          `BuitenTemp` int(11) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
