CREATE TABLE `etudiant_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `operation` varchar(64) NOT NULL,
  `new_data` varchar(512) DEFAULT NULL,
  `old_data` varchar(512) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3