CREATE SCHEMA `config-db` ;
USE `config-db`;

CREATE TABLE `application` (
  `application_id` bigint NOT NULL AUTO_INCREMENT,
  `application` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`application_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `profile` (
  `profile_id` bigint NOT NULL AUTO_INCREMENT,
  `profile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`profile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `properties` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `application_id` bigint DEFAULT NULL,
  `key` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `profile_id` bigint DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




Insert into application (application) values('PRODUCT-SERVICE');
Insert into application (application) values('DISCOVERY-SERVICE');
Insert into application (application) values('CUSTOMER-SERVICE');
Insert into application (application) values('ORDER-SERVICE');
Insert into application (application) values('INVENTORY-SERVICE');
Insert into application (application) values('PAYMENT-SERVICE');
Insert into application (application) values('ORDER-READ-SERVICE');
Insert into application (application) values('ORDER-ORCHESTRATOR-SERVICE');
Insert into application (application) values('GATEWAY-SERVICE');
Insert into application (application) values('VENTOR-SERVICE');


insert into `profile`(profile) value ('dev');
insert into `profile`(profile) value ('default');
insert into `profile`(profile) value ('local');
insert into `profile`(profile) value ('test');
insert into `profile`(profile) value ('prod');


Insert into properties (application_id, `key`, label, profile_id, `value`) values(1,'spring.application.name',NULL,2,'product-service');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(1,'server.port',NULL,2,'8082');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(1,'spring.jpa.hibernate.ddl-auto',NULL,2,'update');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(1,'spring.datasource.url',NULL,2,'jdbc:mysql://localhost:3306/product-db');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(1,'spring.datasource.username',NULL,2,'root');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(1,'spring.datasource.password',NULL,2,'admin123');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(1,'spring.jpa.show-sql',NULL,2,'TRUE');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(1,'eureka.client.serviceUrl.defaultZone',NULL,2,'http://localhost:8761/eureka/');


Insert into properties (application_id, `key`, label, profile_id, `value`) values(3,'spring.application.name',NULL,2,'customer-service');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(3,'server.port',NULL,2,'8220');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(3,'spring.jpa.hibernate.ddl-auto',NULL,2,'update');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(3,'spring.datasource.url',NULL,2,'jdbc:mysql://localhost:3306/customer-db');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(3,'spring.datasource.username',NULL,2,'root');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(3,'spring.datasource.password',NULL,2,'admin123');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(3,'spring.jpa.show-sql',NULL,2,'TRUE');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(3,'eureka.client.serviceUrl.defaultZone',NULL,2,'http://localhost:8761/eureka/');


Insert into properties (application_id, `key`, label, profile_id, `value`) values(7,'spring.application.name',NULL,2,'order-read-service');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(7,'server.port',NULL,2,'8250');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(7,'spring.jpa.hibernate.ddl-auto',NULL,2,'update');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(7,'spring.datasource.url',NULL,2,'jdbc:mysql://localhost:3306/customer-order-db');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(7,'spring.datasource.username',NULL,2,'root');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(7,'spring.datasource.password',NULL,2,'admin123');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(7,'spring.jpa.show-sql',NULL,2,'TRUE');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(7,'eureka.client.serviceUrl.defaultZone',NULL,2,'http://localhost:8761/eureka/');

Insert into properties (application_id, `key`, label, profile_id, `value`) values(4,'spring.application.name',NULL,2,'order-service');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(4,'server.port',NULL,2,'8270');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(4,'spring.jpa.hibernate.ddl-auto',NULL,2,'update');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(4,'spring.datasource.url',NULL,2,'jdbc:mysql://localhost:3306/customer-order-db');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(4,'spring.datasource.username',NULL,2,'root');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(4,'spring.datasource.password',NULL,2,'admin123');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(4,'spring.jpa.show-sql',NULL,2,'TRUE');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(4,'eureka.client.serviceUrl.defaultZone',NULL,2,'http://localhost:8761/eureka/');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(4,'spring.kafka.producer.bootstrap-servers',NULL,2,'localhost:9092');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(4,'spring.kafka.producer.key-serializer',NULL,2,'org.apache.kafka.common.serialization.StringSerializer');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(4,'spring.kafka.producer.value-serializer',NULL,2,'org.apache.kafka.common.serialization.StringSerializer');

Insert into properties (application_id, `key`, label, profile_id, `value`) values(6,'spring.application.name',NULL,2,'payment-service');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(6,'server.port',NULL,2,'8240');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(6,'customer.bank.url',NULL,2,'http://CUSTOMER-SERVICE');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(6,'eureka.client.serviceUrl.defaultZone',NULL,2,'http://localhost:8761/eureka/');


Insert into properties (application_id, `key`, label, profile_id, `value`) values(10,'spring.application.name',NULL,2,'vendor-service');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(10,'server.port',NULL,2,'8280');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(10,'vendor.datasource.url',NULL,2,'jdbc:mysql://localhost:3306/vendor-db');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(10,'vendor.datasource.username',NULL,2,'root');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(10,'vendor.datasource.password',NULL,2,'admin123');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(10,'subscription.datasource.url',NULL,2,'jdbc:mysql://localhost:3306/subscription-db');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(10,'subscription.datasource.username',NULL,2,'root');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(10,'subscription.datasource.password',NULL,2,'admin123');

Insert into properties (application_id, `key`, label, profile_id, `value`) values(2,'spring.application.name',NULL,2,'discovery-service');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(2,'server.port',NULL,2,'8761');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(2,'eureka.instance.hostname',NULL,2,'localhost');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(2,'eureka.client.registerWithEureka',NULL,2,'FALSE');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(2,'eureka.client.fetchRegistry',NULL,2,'FALSE');


Insert into properties (application_id, `key`, label, profile_id, `value`) values(9,'spring.application.name',NULL,2,'gateway-service');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(9,'server.port',NULL,2,'8080');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(9,'spring.jpa.hibernate.ddl-auto',NULL,2,'update');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(9,'spring.r2dbc.url',NULL,2,'r2dbc:mysql://localhost:3306/gateway-db');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(9,'spring.r2dbc.username',NULL,2,'root');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(9,'spring.r2dbc.password',NULL,2,'admin123');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(9,'spring.jpa.show-sql',NULL,2,'TRUE');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(9,'eureka.client.serviceUrl.defaultZone',NULL,2,'http://localhost:8761/eureka/');


Insert into properties (application_id, `key`, label, profile_id, `value`) values(5,'spring.application.name',NULL,2,'inventory-service');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(5,'server.port',NULL,2,'8230');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(5,'eureka.client.serviceUrl.defaultZone',NULL,2,'http://localhost:8761/eureka/');


Insert into properties (application_id, `key`, label, profile_id, `value`) values(7,'spring.application.name',NULL,2,'order-read-service');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(7,'server.port',NULL,2,'8250');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(7,'spring.jpa.hibernate.ddl-auto',NULL,2,'update');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(7,'spring.datasource.url',NULL,2,'jdbc:mysql://localhost:3306/customer-order-db');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(7,'spring.datasource.username',NULL,2,'root');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(7,'spring.datasource.password',NULL,2,'admin123');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(7,'spring.jpa.show-sql',NULL,2,'TRUE');
Insert into properties (application_id, `key`, label, profile_id, `value`) values(7,'eureka.client.serviceUrl.defaultZone',NULL,2,'http://localhost:8761/eureka/');






