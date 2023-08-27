-- If you need run flyway without IDE, use the terminal command: mvn clean package spring-boot:run -DskipTests
-- Copying the structure for the table - rest_springboot_db.person
CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(80) NOT NULL,
  `last_name` varchar(80) NOT NULL,
  `address` varchar(150) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);