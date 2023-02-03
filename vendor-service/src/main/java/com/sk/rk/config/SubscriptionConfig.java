package com.sk.rk.config;

import org.springframework.context.annotation.Configuration;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.sk.rk.repository.subscription"
        , entityManagerFactoryRef = "vendorEntityManagerFactory"
        , transactionManagerRef = "vendorSqlPlatformTransactionManager")
public class SubscriptionConfig {

    @Primary
    @Bean
    @ConfigurationProperties("subscription.datasource")
    public DataSourceProperties subscriptionDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource subscriptionSqlDataSource(@Qualifier("subscriptionDataSourceProperties")
                                          DataSourceProperties subscriptionDataSourceProperties) {
        return subscriptionDataSourceProperties
                .initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean subscriptionEntityManagerFactory(
            @Qualifier("subscriptionSqlDataSource") DataSource pgsqlDataSource, EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(pgsqlDataSource)
                .packages("com.sk.rk.repository.subscription")
                .persistenceUnit("subscriptionSql")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager subscriptionSqlPlatformTransactionManager(
            @Qualifier("subscriptionEntityManagerFactory") EntityManagerFactory subscriptionEntityManagerFactory) {
        return new JpaTransactionManager(subscriptionEntityManagerFactory);
    }

}
