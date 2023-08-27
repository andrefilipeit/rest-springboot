-- If you need run flyway without IDE, use the terminal command: mvn clean package spring-boot:run -DskipTests
INSERT INTO `person` (`id`, `address`, `first_name`, `gender`, `last_name`) VALUES
	(1, 'RECIFE-PE', 'ANDRÉ', 'MALE', 'SANTOS'),
	(3, 'CDU-VARZEA', 'JAMILLY', 'FEMALE', 'ROBERTA'),
	(6, 'ARAPIRACA-PE', 'NELSON', 'MALE', 'MANDELA'),
	(7, 'NOVO LOCAL', 'NOVO NOME', 'FEMALE', 'LAST NAME');