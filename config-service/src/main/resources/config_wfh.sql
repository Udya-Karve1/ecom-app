USE `sqldb-ecom-config`

Insert into application (application_name) values('PRODUCT-SERVICE');
Insert into application (application_name) values('DISCOVERY-SERVICE');
Insert into application (application_name) values('CUSTOMER-SERVICE');
Insert into application (application_name) values('ORDER-SERVICE');
Insert into application (application_name) values('INVENTORY-SERVICE');
Insert into application (application_name) values('PAYMENT-SERVICE');
Insert into application (application_name) values('ORDER-READ-SERVICE');
Insert into application (application_name) values('ORDER-ORCHESTRATOR-SERVICE');
Insert into application (application_name) values('GATEWAY-SERVICE');
Insert into application (application_name) values('VENTOR-SERVICE');


insert into [profile](profile_name) values ('dev')
insert into [profile](profile_name) values ('default')
insert into [profile](profile_name) values ('local')
insert into [profile](profile_name) values ('test')
insert into [profile](profile_name) values ('prod')
insert into [profile](profile_name) values ('wfh')
insert into [profile](profile_name) values ('wfo')

Insert into properties(application_id, profile_id, [key_name],  key_value) values(3,6,'spring.application.name','customer-service');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(3,6,'server.port','8220');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(3,6,'spring.jpa.hibernate.ddl-auto','update');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(3,6,'spring.datasource.url','jdbc:sqlserver://localhost;databaseName=sqldb-ecom-customer');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(3,6,'spring.datasource.username','sa');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(3,6,'spring.datasource.password','softweb#123');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(3,6,'spring.jpa.show-sql','TRUE');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(3,6,'eureka.client.serviceUrl.defaultZone','http://localhost:8761/eureka/');

Insert into properties(application_id, profile_id, [key_name],  key_value) values(2,6,'spring.application.name','discovery-service');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(2,6,'server.port','8761');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(2,6,'eureka.instance.hostname','localhost');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(2,6,'eureka.client.registerWithEureka','FALSE');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(2,6,'eureka.client.fetchRegistry','FALSE');

Insert into properties(application_id, profile_id, [key_name],  key_value) values(9,6,'spring.application.name','gateway-service');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(9,6,'server.port','8080');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(9,6,'spring.jpa.hibernate.ddl-auto','update');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(9,6,'spring.r2dbc.url','r2dbc:mysql://localhost;databaseName=sqldb-ecom-gateway');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(9,6,'spring.r2dbc.username','sa');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(9,6,'spring.r2dbc.password','softweb#123');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(9,6,'spring.jpa.show-sql','TRUE');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(9,6,'eureka.client.serviceUrl.defaultZone','http://localhost:8761/eureka/');

Insert into properties(application_id, profile_id, [key_name],  key_value) values(5,6,'spring.application.name','inventory-service');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(5,6,'server.port','8230');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(5,6,'eureka.client.serviceUrl.defaultZone','http://localhost:8761/eureka/');

Insert into properties(application_id, profile_id, [key_name],  key_value) values(8,6,'service.endpoints.payment','PAYMENT-SERVICE/payment/v1/api/');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(8,6,'service.endpoints.inventory','PAYMENT-SERVICE/inventory/v1/api/');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(8,6,'spring.application.name','order-orchestrator-service');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(8,6,'spring.kafka.producer.bootstrap-servers','localhost:9092');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(8,6,'spring.kafka.topic.name','order_created_event');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(8,6,'eureka.client.serviceUrl.defaultZone','http://localhost:8761/eureka/');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(8,6,'spring.kafka.consumer.key-deserializer','org.apache.kafka.common.serialization.StringDeserializer');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(8,6,'spring.kafka.consumer.value-deserializer','org.springframework.kafka.support.serializer.JsonDeserializer');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(8,6,'spring.kafka.consumer.properties.spring.json.add.type.headers','FALSE');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(8,6,'spring.topic.order-create','order_created_topic  ');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(8,6,'spring.topic.order-completed','order_completed_topic ');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(8,6,'spring.group-id.order-created','order_created_group_id');

Insert into properties(application_id, profile_id, [key_name],  key_value) values(7,6,'spring.application.name','order-read-service');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(7,6,'server.port','8250');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(7,6,'spring.jpa.hibernate.ddl-auto','update');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(7,6,'spring.datasource.url','jdbc:sqlserver://localhost;databaseName=customer-order-db');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(7,6,'spring.datasource.username','sa');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(7,6,'spring.datasource.password','softweb#123');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(7,6,'spring.jpa.show-sql','TRUE');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(7,6,'eureka.client.serviceUrl.defaultZone','http://localhost:8761/eureka/');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(7,6,'spring.kafka.consumer.bootstrap-servers','localhost:9092');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(7,6,'spring.kafka.consumer.group-id','order');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(7,6,'spring.kafka.consumer.auto-offset-reset','earliest');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(7,6,'spring.kafka.consumer.key-deserializer','org.apache.kafka.common.serialization.StringDeserializer');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(7,6,'spring.kafka.consumer.value-deserializer','org.springframework.kafka.support.serializer.JsonDeserializer');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(7,6,'spring.kafka.consumer.properties.spring.json.trusted.packages','*');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(7,6,'spring.kafka.topic.name','order_topics');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(7,6,'customer.get-customer.url','http://CUSTOMER-SERVICE/customer/v1/api');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(7,6,'customer.get-product.url ','http://PRODUCT-SERVICE/product/v1/api');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(7,6,'spring.topic.order-completed','order_completed_topic');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(7,6,'spring.group-id.order-completed','order_completed_group_id');

Insert into properties(application_id, profile_id, [key_name],  key_value) values(4,6,'spring.application.name','order-service');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(4,6,'server.port','8270');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(4,6,'spring.jpa.hibernate.ddl-auto','update');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(4,6,'spring.datasource.url','jdbc:sqlserver://localhost;databaseName=order-db');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(4,6,'spring.datasource.username','sa');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(4,6,'spring.datasource.password','softweb#123');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(4,6,'spring.jpa.show-sql','TRUE');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(4,6,'eureka.client.serviceUrl.defaultZone','http://localhost:8761/eureka/');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(4,6,'spring.kafka.producer.bootstrap-servers','localhost:9092');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(4,6,'spring.kafka.producer.key-serializer','org.apache.kafka.common.serialization.StringSerializer');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(4,6,'spring.kafka.producer.properties.spring.json.add.type.headers','FALSE');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(4,6,'spring.kafka.producer.value-serializer','org.springframework.kafka.support.serializer.JsonSerializer');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(4,6,'spring.topic.order-create','order_created_topic');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(4,6,'spring.topic.order-completed','order_completed_topic');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(4,6,'spring.group-id.order-created','order_created_group_id');

Insert into properties(application_id, profile_id, [key_name],  key_value) values(6,6,'spring.application.name','payment-service');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(6,6,'server.port','8240');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(6,6,'customer.bank.url','http://CUSTOMER-SERVICE');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(6,6,'eureka.client.serviceUrl.defaultZone','http://localhost:8761/eureka/');

Insert into properties(application_id, profile_id, [key_name],  key_value) values(1,6,'spring.application.name','product-service');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(1,6,'server.port','8082');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(1,6,'spring.jpa.hibernate.ddl-auto','update');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(1,6,'spring.datasource.url','jdbc:sqlserver://localhost;databaseName=product-db');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(1,6,'spring.datasource.username','sa');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(1,6,'spring.datasource.password','softweb#123');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(1,6,'spring.jpa.show-sql','TRUE');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(1,6,'eureka.client.serviceUrl.defaultZone','http://localhost:8761/eureka/');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(1,6,'spring.config.import','configserver:http://localhost:8888/api/config');

Insert into properties(application_id, profile_id, [key_name],  key_value) values(10,6,'spring.application.name','vendor-service');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(10,6,'server.port','8280');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(10,6,'vendor.datasource.url','jdbc:sqlserver://localhost;databaseName=vendor-db');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(10,6,'vendor.datasource.username','sa');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(10,6,'vendor.datasource.password','softweb#123');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(10,6,'subscription.datasource.url','jdbc:sqlserver://localhost;databaseName=subscription-db');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(10,6,'subscription.datasource.username','sa');
Insert into properties(application_id, profile_id, [key_name],  key_value) values(10,6,'subscription.datasource.password','softweb#123');
