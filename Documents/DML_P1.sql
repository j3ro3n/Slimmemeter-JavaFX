DROP TABLE IF EXISTS `readings`;

CREATE TABLE `readings` (
                          `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
                          `type` varchar(255) DEFAULT NULL,
                          `ElektraHoog` int(11) DEFAULT NULL,
                          `ElektraLaag` int(11) DEFAULT NULL,
                          `ElektraHuidig` int(11) DEFAULT NULL,
                          `ElektraZon` int(11) DEFAULT NULL,
                          `Gas` int(11) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
