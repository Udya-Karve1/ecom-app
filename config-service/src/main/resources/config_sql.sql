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


Insert into properties(application_id, profile_id, key,  value) values(3,2,'spring.application.name','customer-service');
Insert into properties(application_id, profile_id, key,  value) values(3,2,'server.port','8220');
Insert into properties(application_id, profile_id, key,  value) values(3,2,'spring.jpa.hibernate.ddl-auto','update');
Insert into properties(application_id, profile_id, key,  value) values(3,2,'spring.datasource.url','jdbc:mysql://localhost:3306/customer-db');
Insert into properties(application_id, profile_id, key,  value) values(3,2,'spring.datasource.username','root');
Insert into properties(application_id, profile_id, key,  value) values(3,2,'spring.datasource.password','admin123');
Insert into properties(application_id, profile_id, key,  value) values(3,2,'spring.jpa.show-sql','TRUE');
Insert into properties(application_id, profile_id, key,  value) values(3,2,'eureka.client.serviceUrl.defaultZone','http://localhost:8761/eureka/');
Insert into properties(application_id, profile_id, key,  value) values(2,2,'spring.application.name','discovery-service');
Insert into properties(application_id, profile_id, key,  value) values(2,2,'server.port','8761');
Insert into properties(application_id, profile_id, key,  value) values(2,2,'eureka.instance.hostname','localhost');
Insert into properties(application_id, profile_id, key,  value) values(2,2,'eureka.client.registerWithEureka','FALSE');
Insert into properties(application_id, profile_id, key,  value) values(2,2,'eureka.client.fetchRegistry','FALSE');
Insert into properties(application_id, profile_id, key,  value) values(9,2,'spring.application.name','gateway-service');
Insert into properties(application_id, profile_id, key,  value) values(9,2,'server.port','8080');
Insert into properties(application_id, profile_id, key,  value) values(9,2,'spring.jpa.hibernate.ddl-auto','update');
Insert into properties(application_id, profile_id, key,  value) values(9,2,'spring.r2dbc.url','r2dbc:mysql://localhost:3306/gateway-db');
Insert into properties(application_id, profile_id, key,  value) values(9,2,'spring.r2dbc.username','root');
Insert into properties(application_id, profile_id, key,  value) values(9,2,'spring.r2dbc.password','admin123');
Insert into properties(application_id, profile_id, key,  value) values(9,2,'spring.jpa.show-sql','TRUE');
Insert into properties(application_id, profile_id, key,  value) values(9,2,'eureka.client.serviceUrl.defaultZone','http://localhost:8761/eureka/');
Insert into properties(application_id, profile_id, key,  value) values(5,2,'spring.application.name','inventory-service');
Insert into properties(application_id, profile_id, key,  value) values(5,2,'server.port','8230');
Insert into properties(application_id, profile_id, key,  value) values(5,2,'eureka.client.serviceUrl.defaultZone','http://localhost:8761/eureka/');
Insert into properties(application_id, profile_id, key,  value) values(8,2,'service.endpoints.payment','PAYMENT-SERVICE/v1/api/payment/');
Insert into properties(application_id, profile_id, key,  value) values(8,2,'service.endpoints.inventory','PAYMENT-SERVICE/v1/api/inventory/');
Insert into properties(application_id, profile_id, key,  value) values(8,2,'spring.application.name','order-orchestrator-service');
Insert into properties(application_id, profile_id, key,  value) values(8,2,'spring.kafka.producer.bootstrap-servers','localhost:9092');
Insert into properties(application_id, profile_id, key,  value) values(8,2,'spring.kafka.topic.name','order_created_event');
Insert into properties(application_id, profile_id, key,  value) values(8,2,'eureka.client.serviceUrl.defaultZone','http://localhost:8761/eureka/');
Insert into properties(application_id, profile_id, key,  value) values(8,2,'spring.kafka.consumer.key-deserializer','org.apache.kafka.common.serialization.StringDeserializer');
Insert into properties(application_id, profile_id, key,  value) values(8,2,'spring.kafka.consumer.value-deserializer','org.springframework.kafka.support.serializer.JsonDeserializer');
Insert into properties(application_id, profile_id, key,  value) values(8,2,'spring.kafka.consumer.properties.spring.json.add.type.headers','FALSE');
Insert into properties(application_id, profile_id, key,  value) values(8,2,'spring.topic.order-create','order_created_topic  ');
Insert into properties(application_id, profile_id, key,  value) values(8,2,'spring.topic.order-completed','order_completed_topic ');
Insert into properties(application_id, profile_id, key,  value) values(8,2,'spring.group-id.order-created','order_created_group_id');
Insert into properties(application_id, profile_id, key,  value) values(7,2,'spring.application.name','order-read-service');
Insert into properties(application_id, profile_id, key,  value) values(7,2,'server.port','8250');
Insert into properties(application_id, profile_id, key,  value) values(7,2,'spring.jpa.hibernate.ddl-auto','update');
Insert into properties(application_id, profile_id, key,  value) values(7,2,'spring.datasource.url','jdbc:mysql://localhost:3306/customer-order-db');
Insert into properties(application_id, profile_id, key,  value) values(7,2,'spring.datasource.username','root');
Insert into properties(application_id, profile_id, key,  value) values(7,2,'spring.datasource.password','admin123');
Insert into properties(application_id, profile_id, key,  value) values(7,2,'spring.jpa.show-sql','TRUE');
Insert into properties(application_id, profile_id, key,  value) values(7,2,'eureka.client.serviceUrl.defaultZone','http://localhost:8761/eureka/');
Insert into properties(application_id, profile_id, key,  value) values(7,2,'spring.kafka.consumer.bootstrap-servers','localhost:9092');
Insert into properties(application_id, profile_id, key,  value) values(7,2,'spring.kafka.consumer.group-id','order');
Insert into properties(application_id, profile_id, key,  value) values(7,2,'spring.kafka.consumer.auto-offset-reset','earliest');
Insert into properties(application_id, profile_id, key,  value) values(7,2,'spring.kafka.consumer.key-deserializer','org.apache.kafka.common.serialization.StringDeserializer');
Insert into properties(application_id, profile_id, key,  value) values(7,2,'spring.kafka.consumer.value-deserializer','org.springframework.kafka.support.serializer.JsonDeserializer');
Insert into properties(application_id, profile_id, key,  value) values(7,2,'spring.kafka.consumer.properties.spring.json.trusted.packages','*');
Insert into properties(application_id, profile_id, key,  value) values(7,2,'spring.kafka.topic.name','order_topics');
Insert into properties(application_id, profile_id, key,  value) values(7,2,'customer.get-customer.url','http://CUSTOMER-SERVICE/v1/api/customer');
Insert into properties(application_id, profile_id, key,  value) values(7,2,'customer.get-product.url ','http://PRODUCT-SERVICE/v1/api/product');
Insert into properties(application_id, profile_id, key,  value) values(7,2,'spring.topic.order-completed','order_completed_topic');
Insert into properties(application_id, profile_id, key,  value) values(7,2,'spring.group-id.order-completed','order_completed_group_id');
Insert into properties(application_id, profile_id, key,  value) values(4,2,'spring.application.name','order-service');
Insert into properties(application_id, profile_id, key,  value) values(4,2,'server.port','8270');
Insert into properties(application_id, profile_id, key,  value) values(4,2,'spring.jpa.hibernate.ddl-auto','update');
Insert into properties(application_id, profile_id, key,  value) values(4,2,'spring.datasource.url','jdbc:mysql://localhost:3306/order-db');
Insert into properties(application_id, profile_id, key,  value) values(4,2,'spring.datasource.username','root');
Insert into properties(application_id, profile_id, key,  value) values(4,2,'spring.datasource.password','admin123');
Insert into properties(application_id, profile_id, key,  value) values(4,2,'spring.jpa.show-sql','TRUE');
Insert into properties(application_id, profile_id, key,  value) values(4,2,'eureka.client.serviceUrl.defaultZone','http://localhost:8761/eureka/');
Insert into properties(application_id, profile_id, key,  value) values(4,2,'spring.kafka.producer.bootstrap-servers','localhost:9092');
Insert into properties(application_id, profile_id, key,  value) values(4,2,'spring.kafka.producer.key-serializer','org.apache.kafka.common.serialization.StringSerializer');
Insert into properties(application_id, profile_id, key,  value) values(4,2,'spring.kafka.producer.properties.spring.json.add.type.headers','FALSE');
Insert into properties(application_id, profile_id, key,  value) values(4,2,'spring.kafka.producer.value-serializer','org.springframework.kafka.support.serializer.JsonSerializer');
Insert into properties(application_id, profile_id, key,  value) values(4,2,'spring.topic.order-create','order_created_topic');
Insert into properties(application_id, profile_id, key,  value) values(4,2,'spring.topic.order-completed','order_completed_topic');
Insert into properties(application_id, profile_id, key,  value) values(4,2,'spring.group-id.order-created','order_created_group_id');
Insert into properties(application_id, profile_id, key,  value) values(6,2,'spring.application.name','payment-service');
Insert into properties(application_id, profile_id, key,  value) values(6,2,'server.port','8240');
Insert into properties(application_id, profile_id, key,  value) values(6,2,'customer.bank.url','http://CUSTOMER-SERVICE');
Insert into properties(application_id, profile_id, key,  value) values(6,2,'eureka.client.serviceUrl.defaultZone','http://localhost:8761/eureka/');
Insert into properties(application_id, profile_id, key,  value) values(1,2,'spring.application.name','product-service');
Insert into properties(application_id, profile_id, key,  value) values(1,2,'server.port','8082');
Insert into properties(application_id, profile_id, key,  value) values(1,2,'spring.jpa.hibernate.ddl-auto','update');
Insert into properties(application_id, profile_id, key,  value) values(1,2,'spring.datasource.url','jdbc:mysql://localhost:3306/product-db');
Insert into properties(application_id, profile_id, key,  value) values(1,2,'spring.datasource.username','root');
Insert into properties(application_id, profile_id, key,  value) values(1,2,'spring.datasource.password','admin123');
Insert into properties(application_id, profile_id, key,  value) values(1,2,'spring.jpa.show-sql','TRUE');
Insert into properties(application_id, profile_id, key,  value) values(1,2,'eureka.client.serviceUrl.defaultZone','http://localhost:8761/eureka/');
Insert into properties(application_id, profile_id, key,  value) values(1,2,'spring.config.import','configserver:http://localhost:8888/api/config');
Insert into properties(application_id, profile_id, key,  value) values(10,2,'spring.application.name','vendor-service');
Insert into properties(application_id, profile_id, key,  value) values(10,2,'server.port','8280');
Insert into properties(application_id, profile_id, key,  value) values(10,2,'vendor.datasource.url','jdbc:mysql://localhost:3306/vendor-db');
Insert into properties(application_id, profile_id, key,  value) values(10,2,'vendor.datasource.username','root');
Insert into properties(application_id, profile_id, key,  value) values(10,2,'vendor.datasource.password','admin123');
Insert into properties(application_id, profile_id, key,  value) values(10,2,'subscription.datasource.url','jdbc:mysql://localhost:3306/subscription-db');
Insert into properties(application_id, profile_id, key,  value) values(10,2,'subscription.datasource.username','root');
Insert into properties(application_id, profile_id, key,  value) values(10,2,'subscription.datasource.password','admin123');
